package com.thestone.magicsword.client.entity.animations;// Save this class in your mod and generate all required imports

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

/**
 * Made with Blockbench 4.12.2
 * Exported for Minecraft version 1.19 or later with Yarn mappings
 *
 * @author Author
 */
public class BloodMageAnimations {
    public static final Animation ANIM_BARON_WALK = Animation.Builder.create(1.0F).looping()
            .addBoneAnimation("baron", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -6.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.25F, AnimationHelper.createTranslationalVector(0.0F, -3.48F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -6.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createTranslationalVector(0.0F, -3.87F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -4.75F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.75F, AnimationHelper.createRotationalVector(-22.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_knee", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 22.5F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_elbow", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-77.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-10.42F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_knee", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(47.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(27.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(32.5F, 0.0F, -22.5F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_elbow", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-75.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();

    public static final Animation ANIM_BARON_IDLE = Animation.Builder.create(1.0F).looping()
            .addBoneAnimation("baron", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -3.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -2.75F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -3.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-9.9162F, 1.2988F, -0.1127F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-9.9162F, 1.2988F, -0.1127F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_knee", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(-9.9162F, -1.2988F, 0.1127F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(-9.9162F, -1.2988F, 0.1127F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_knee", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();

    public static final Animation ANIM_BARON_ATTACK = Animation.Builder.create(0.5417F)
            .addBoneAnimation("baron", new Transformation(Transformation.Targets.TRANSLATE,
                    new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -3.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createTranslationalVector(0.0F, -3.5F, 0.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -3.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("right_arm", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(15.0F, 0.0F, 35.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-60.0F, 0.0F, 35.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-90.0F, -10.0F, 35.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("left_arm", new Transformation(Transformation.Targets.ROTATE,
                    new Keyframe(0.0F, AnimationHelper.createRotationalVector(15.0F, 0.0F, -35.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.25F, AnimationHelper.createRotationalVector(-60.0F, 0.0F, -35.0F), Transformation.Interpolations.LINEAR),
                    new Keyframe(0.5F, AnimationHelper.createRotationalVector(-90.0F, 10.0F, -35.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();
}