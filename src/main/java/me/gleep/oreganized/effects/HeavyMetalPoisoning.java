package me.gleep.oreganized.effects;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import me.gleep.oreganized.Oreganized;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HeavyMetalPoisoning extends Effect {
    boolean init;
    public HeavyMetalPoisoning() {
        super(EffectType.HARMFUL, 0x7A3181);
        this.init = false;
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public boolean shouldRenderInvText(EffectInstance effect) {
        return true;
    }

    @Override
    public boolean shouldRenderHUD(EffectInstance effect) {
        return true;
    }

    @Override
    public boolean shouldRender(EffectInstance effect) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (entityLivingBaseIn.getActivePotionEffect(this) != null) {
            int duration = entityLivingBaseIn.getActivePotionEffect(this).getDuration();

            EffectInstance poison = null;
            EffectInstance nausea = null;
            int poisonAmp = Math.round((float) Math.sqrt(amplifier));

            if (entityLivingBaseIn.getActivePotionEffect(Effects.POISON) != null) {
                if (entityLivingBaseIn.getActivePotionEffect(Effects.POISON).getAmplifier() < poisonAmp) {
                    poison = new EffectInstance(Effects.POISON, duration, poisonAmp, false, false);
                }
            } else {
                poison = new EffectInstance(Effects.POISON, duration, poisonAmp, false, false);
            }

            if (entityLivingBaseIn.getActivePotionEffect(Effects.NAUSEA) != null) {
                if (entityLivingBaseIn.getActivePotionEffect(Effects.NAUSEA).getAmplifier() < amplifier) {
                    nausea = new EffectInstance(Effects.NAUSEA, duration, amplifier, false, false);
                }
            } else {
                nausea = new EffectInstance(Effects.NAUSEA, duration, amplifier, false, false);
            }

            if (poison != null) {
                entityLivingBaseIn.addPotionEffect(poison);
            }
            if (nausea != null) {
                entityLivingBaseIn.addPotionEffect(nausea);
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return !init;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void renderHUDEffect(EffectInstance effect, AbstractGui gui, MatrixStack mStack, int x, int y, float z, float alpha) {

    }
}
