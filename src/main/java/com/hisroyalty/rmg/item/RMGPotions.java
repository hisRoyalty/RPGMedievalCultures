package com.hisroyalty.rmg.item;

import com.hisroyalty.rmg.RMGMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RMGPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, RMGMod.MODID);

    public static final RegistryObject<Potion> RESISTANCE = POTIONS.register("resistance",
            () -> new Potion(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1800, 0)));
    public static final RegistryObject<Potion> HASTE = POTIONS.register("haste",
            () -> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 0)));
    public static final RegistryObject<Potion> ABSORPTION = POTIONS.register("absorption",
            () -> new Potion(new MobEffectInstance(MobEffects.ABSORPTION, 1800, -1)));



    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }

}
