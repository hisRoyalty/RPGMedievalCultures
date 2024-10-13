package com.hisroyalty.rmg.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

import java.util.List;

public class TinctureItem extends PotionItem {
    public TinctureItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level world, LivingEntity pEntityLiving) {
        if (!world.isClientSide) {
            List<MobEffectInstance> effects = PotionUtils.getMobEffects(pStack);
            if (!effects.isEmpty()) {
                MobEffectInstance effect = effects.get(0);
                MobEffectInstance currentEffect = pEntityLiving.getEffect(effect.getEffect());

                int newDuration = currentEffect != null ? currentEffect.getDuration() : 10;
                int newAmplifier = currentEffect != null ? currentEffect.getAmplifier() : 0;

                if (currentEffect == null || currentEffect.getAmplifier() < 2 && currentEffect.getAmplifier() >= 0) {
                    newAmplifier = (currentEffect != null ? currentEffect.getAmplifier() : 0) + 1;
                }


                if (pStack.getTag().getString("Potion").equals("rmg:absorption")) {
                }

                pEntityLiving.addEffect(new MobEffectInstance(effect.getEffect(), newDuration, newAmplifier));
            }
        }

        return super.finishUsingItem(pStack, world, pEntityLiving);
    }
}
