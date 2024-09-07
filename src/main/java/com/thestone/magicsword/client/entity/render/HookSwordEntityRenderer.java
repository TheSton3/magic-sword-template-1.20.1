package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.FrozenAxeEntityModel;
import com.thestone.magicsword.client.entity.model.HookSwordEntityModel;
import com.thestone.magicsword.entity.projectile.FrozenAxeEntity;
import com.thestone.magicsword.entity.projectile.HookSwordEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
/*
Код рендера взят с https://github.com/CammiePone/Hookshot/tree/1.19-dev
 */
public class HookSwordEntityRenderer extends EntityRenderer<HookSwordEntity> {
    public static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/hook_sword_entity.png");
    private static final Identifier CHAIN_TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/chain.png");
    private static final RenderLayer CHAIN_LAYER = RenderLayer.getEntitySmoothCutout(CHAIN_TEXTURE);
    private final HookSwordEntityModel model;

    public HookSwordEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new HookSwordEntityModel(context.getPart(ModModelLayers.HOOK_SWORD_ENTITY));
    }

    @Override
    public void render(HookSwordEntity hookSwordEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (hookSwordEntity.getOwner() instanceof PlayerEntity player) {
            Arm mainArm = MinecraftClient.getInstance().options.getMainArm().getValue();
            Hand activeHand = player.getActiveHand();

            matrixStack.push();
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, hookSwordEntity.prevYaw, hookSwordEntity.getYaw()) - 90.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, hookSwordEntity.prevPitch, hookSwordEntity.getPitch()) + 90.0F));
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(this.getTexture(hookSwordEntity)));
            model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();

            matrixStack.push();
            boolean rightHandIsActive = (mainArm == Arm.RIGHT && activeHand == Hand.MAIN_HAND) || (mainArm == Arm.LEFT && activeHand == Hand.OFF_HAND);
            double bodyYawToRads = Math.toRadians(player.bodyYaw);
            double radius = rightHandIsActive ? -0.4D : 0.4D;
            double startX = player.getX() + radius * Math.cos(bodyYawToRads);
            double startY = player.getY() + (player.getHeight() / 3D);
            double startZ = player.getZ() + radius * Math.sin(bodyYawToRads);
            float distanceX = (float) (startX - hookSwordEntity.getX());
            float distanceY = (float) (startY - hookSwordEntity.getY());
            float distanceZ = (float) (startZ - hookSwordEntity.getZ());

            renderChain(distanceX, distanceY, distanceZ, i, hookSwordEntity.age, matrixStack, vertexConsumerProvider, i);
            matrixStack.pop();
        }
    }

    public void renderChain(float x, float y, float z, float tickDelta, int age, MatrixStack stack, VertexConsumerProvider provider, int light) {
        float lengthXY = MathHelper.sqrt(x * x + z * z);
        float squaredLength = x * x + y * y + z * z;
        float length = MathHelper.sqrt(squaredLength);

        stack.push();
        stack.multiply(RotationAxis.POSITIVE_Y.rotation((float) (-Math.atan2(z, x)) - 1.5707964F));
        stack.multiply(RotationAxis.POSITIVE_X.rotation((float) (-Math.atan2(lengthXY, y)) - 1.5707964F));
        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(25));
        stack.push();
        stack.translate(0.015, -0.2, 0);

        VertexConsumer vertexConsumer = provider.getBuffer(CHAIN_LAYER);
        float vertX1 = 0F;
        float vertY1 = 0.25F;
        float vertX2 = MathHelper.sin(6.2831855F) * 0.125F;
        float vertY2 = MathHelper.cos(6.2831855F) * 0.125F;
        float minU = 0F;
        float maxU = 0.1875F;
        float minV = 0.0F - ((float) age + tickDelta) * 0.01F;
        float maxV = MathHelper.sqrt(squaredLength) / 8F - ((float) age + tickDelta) * 0.01F;
        MatrixStack.Entry entry = stack.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();

        vertexConsumer.vertex(matrix4f, vertX1, vertY1, 0F).color(0, 0, 0, 255).texture(minU, minV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, vertX1, vertY1, length).color(255, 255, 255, 255).texture(minU, maxV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, vertX2, vertY2, length).color(255, 255, 255, 255).texture(maxU, maxV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, vertX2, vertY2, 0F).color(0, 0, 0, 255).texture(maxU, minV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();

        stack.pop();
        stack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(90));
        stack.translate(-0.015, -0.2, 0);

        entry = stack.peek();
        matrix4f = entry.getPositionMatrix();
        matrix3f = entry.getNormalMatrix();

        vertexConsumer.vertex(matrix4f, vertX1, vertY1, 0F).color(0, 0, 0, 255).texture(minU, minV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, vertX1, vertY1, length).color(255, 255, 255, 255).texture(minU, maxV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, vertX2, vertY2, length).color(255, 255, 255, 255).texture(maxU, maxV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();
        vertexConsumer.vertex(matrix4f, vertX2, vertY2, 0F).color(0, 0, 0, 255).texture(maxU, minV).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).next();

        stack.pop();
    }

    @Override
    public Identifier getTexture(HookSwordEntity entity) {
        return TEXTURE;
    }


}