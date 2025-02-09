package com.thestone.magicsword.client.entity.render;

import com.thestone.magicsword.MagicSword;
import com.thestone.magicsword.client.entity.ModModelLayers;
import com.thestone.magicsword.client.entity.model.BloodMageModel;
import com.thestone.magicsword.entity.BloodMageEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class BloodMageRenderer extends MobEntityRenderer<BloodMageEntity, BloodMageModel<BloodMageEntity>> {
    private static final Identifier TEXTURE = new Identifier(MagicSword.MOD_ID, "textures/entity/blood_mage_entity.png");
    private static final Identifier EXPLOSION_BEAM_TEXTURE = new Identifier("textures/entity/guardian_beam.png");
    private static final RenderLayer LAYER = RenderLayer.getEntityCutoutNoCull(EXPLOSION_BEAM_TEXTURE);

    public BloodMageRenderer(EntityRendererFactory.Context context) {
        super(context, new BloodMageModel<>(context.getPart(ModModelLayers.BLOOD_MAGE_ENTITY)), 0.6f);
    }

    @Override
    public Identifier getTexture(BloodMageEntity entity) {
        return TEXTURE;
    }

    @Override
    public boolean shouldRender(BloodMageEntity mobEntity, Frustum frustum, double d, double e, double f) {
        if (super.shouldRender(mobEntity, frustum, d, e, f)) {
            return true;
        } else {
            if (mobEntity.hasBeamTarget()) {
                LivingEntity livingEntity = mobEntity.getBeamTarget();
                if (livingEntity != null) {
                    Vec3d vec3d = this.fromLerpedPosition(livingEntity, (double) livingEntity.getHeight() * 0.5, 1.0F);
                    Vec3d vec3d2 = this.fromLerpedPosition(mobEntity, (double) mobEntity.getStandingEyeHeight(), 1.0F);
                    return frustum.isVisible(new Box(vec3d2.x, vec3d2.y, vec3d2.z, vec3d.x, vec3d.y, vec3d.z));
                }
            }

            return false;
        }
    }


    private Vec3d fromLerpedPosition(LivingEntity entity, double yOffset, float delta) {
        double d = MathHelper.lerp(delta, entity.lastRenderX, entity.getX());
        double e = MathHelper.lerp(delta, entity.lastRenderY, entity.getY()) + yOffset;
        double f = MathHelper.lerp(delta, entity.lastRenderZ, entity.getZ());
        return new Vec3d(d, e, f);
    }

    @Override
    public void render(BloodMageEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
        LivingEntity livingEntity = mobEntity.getBeamTarget();
        if (livingEntity != null) {
            float j = mobEntity.getBeamTicks() + g;
            float k = j * 0.5F % 1.0F;
            float l = mobEntity.getStandingEyeHeight();
            matrixStack.push();
            matrixStack.translate(0.0F, l, 0.0F);
            Vec3d vec3d = this.fromLerpedPosition(livingEntity, (double) livingEntity.getHeight() * 0.5, g);
            Vec3d vec3d2 = this.fromLerpedPosition(mobEntity, (double) l, g);
            Vec3d vec3d3 = vec3d.subtract(vec3d2);
            float m = (float) (vec3d3.length() + 1.0);
            vec3d3 = vec3d3.normalize();
            float n = (float) Math.acos(vec3d3.y);
            float o = (float) Math.atan2(vec3d3.z, vec3d3.x);
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(((float) (Math.PI / 2) - o) * (180.0F / (float) Math.PI)));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(n * (180.0F / (float) Math.PI)));
            float q = j * 0.05F * -1.5F;
            float x = MathHelper.cos(q + (float) (Math.PI * 3.0 / 4.0)) * 0.282F;
            float y = MathHelper.sin(q + (float) (Math.PI * 3.0 / 4.0)) * 0.282F;
            float z = MathHelper.cos(q + (float) (Math.PI / 4)) * 0.282F;
            float aa = MathHelper.sin(q + (float) (Math.PI / 4)) * 0.282F;
            float ab = MathHelper.cos(q + ((float) Math.PI * 5.0F / 4.0F)) * 0.282F;
            float ac = MathHelper.sin(q + ((float) Math.PI * 5.0F / 4.0F)) * 0.282F;
            float ad = MathHelper.cos(q + ((float) Math.PI * 7.0F / 4.0F)) * 0.282F;
            float ae = MathHelper.sin(q + ((float) Math.PI * 7.0F / 4.0F)) * 0.282F;
            float af = MathHelper.cos(q + (float) Math.PI) * 0.2F;
            float ag = MathHelper.sin(q + (float) Math.PI) * 0.2F;
            float ah = MathHelper.cos(q + 0.0F) * 0.2F;
            float ai = MathHelper.sin(q + 0.0F) * 0.2F;
            float aj = MathHelper.cos(q + (float) (Math.PI / 2)) * 0.2F;
            float ak = MathHelper.sin(q + (float) (Math.PI / 2)) * 0.2F;
            float al = MathHelper.cos(q + (float) (Math.PI * 3.0 / 2.0)) * 0.2F;
            float am = MathHelper.sin(q + (float) (Math.PI * 3.0 / 2.0)) * 0.2F;
            float aq = -1.0F + k;
            float ar = m * 2.5F + aq;
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(LAYER);
            MatrixStack.Entry entry = matrixStack.peek();
            Matrix4f matrix4f = entry.getPositionMatrix();
            Matrix3f matrix3f = entry.getNormalMatrix();
            vertex(vertexConsumer, matrix4f, matrix3f, af, m, ag, 255, 0, 0, 0.4999F, ar);
            vertex(vertexConsumer, matrix4f, matrix3f, af, 0.0F, ag, 255, 0, 0, 0.4999F, aq);
            vertex(vertexConsumer, matrix4f, matrix3f, ah, 0.0F, ai, 255, 0, 0, 0.0F, aq);
            vertex(vertexConsumer, matrix4f, matrix3f, ah, m, ai, 255, 0, 0, 0.0F, ar);
            vertex(vertexConsumer, matrix4f, matrix3f, aj, m, ak, 255, 0, 0, 0.4999F, ar);
            vertex(vertexConsumer, matrix4f, matrix3f, aj, 0.0F, ak, 255, 0, 0, 0.4999F, aq);
            vertex(vertexConsumer, matrix4f, matrix3f, al, 0.0F, am, 255, 0, 0, 0.0F, aq);
            vertex(vertexConsumer, matrix4f, matrix3f, al, m, am, 255, 0, 0, 0.0F, ar);
            float as = 0.0F;
            if (mobEntity.age % 2 == 0) {
                as = 0.5F;
            }

            vertex(vertexConsumer, matrix4f, matrix3f, x, m, y, 255, 0, 0, 0.5F, as + 0.5F);
            vertex(vertexConsumer, matrix4f, matrix3f, z, m, aa, 255, 0, 0, 1.0F, as + 0.5F);
            vertex(vertexConsumer, matrix4f, matrix3f, ad, m, ae, 255, 0, 0, 1.0F, as);
            vertex(vertexConsumer, matrix4f, matrix3f, ab, m, ac, 255, 0, 0, 0.5F, as);
            matrixStack.pop();
        }
    }

    private static void vertex(
            VertexConsumer vertexConsumer, Matrix4f positionMatrix, Matrix3f normalMatrix, float x, float y, float z, int red, int green, int blue, float u, float v
    ) {
        vertexConsumer.vertex(positionMatrix, x, y, z)
                .color(red, green, blue, 255)
                .texture(u, v)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(LightmapTextureManager.MAX_LIGHT_COORDINATE)
                .normal(normalMatrix, 0.0F, 1.0F, 0.0F)
                .next();
    }
}
