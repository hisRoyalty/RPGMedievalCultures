package com.hisroyalty.rmg;

import com.hisroyalty.rmg.block.ModBlocks;
import com.hisroyalty.rmg.item.RMGPotions;
import com.hisroyalty.rmg.item.TinctureItem;
import com.hisroyalty.rmg.lp.event.MyListener;
import com.hisroyalty.rmg.network.ModMessages;
import com.mojang.logging.LogUtils;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RMGMod.MODID)
public class RMGMod
{
    // Define mod id in a common place for everything to reference

    public static final String MODID = "rmg";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);



    public static final DeferredRegister<BannerPattern> BANNER_PATTERNS = DeferredRegister.create(Registries.BANNER_PATTERN, MODID);
    public static final RegistryObject<BannerPattern> EMPIRE_BANNER_PATTERN = BANNER_PATTERNS.register("empire", () -> new BannerPattern("empire"));

    public static final RegistryObject<Item> EMPIRE_BANNER_PATTERN_ITEM = ITEMS.register("empire_banner_pattern", () -> new BannerPatternItem(TagInit.EMPIRE_BANNER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<BannerPattern> KINGDOM_BANNER_PATTERN = BANNER_PATTERNS.register("kingdom", () -> new BannerPattern("kingdom"));

    public static final RegistryObject<Item> KINGDOM_BANNER_PATTERN_ITEM = ITEMS.register("kingdom_banner_pattern", () -> new BannerPatternItem(TagInit.KINGDOM_BANNER, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<BannerPattern> MOTHERLAND_BANNER_PATTERN = BANNER_PATTERNS.register("motherland", () -> new BannerPattern("motherland"));

    public static final RegistryObject<Item> MOTHERLAND_BANNER_PATTERN_ITEM = ITEMS.register("motherland_banner_pattern", () -> new BannerPatternItem(TagInit.MOTHERLAND_BANNER, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<BannerPattern> COMMONWEALTH_BANNER_PATTERN = BANNER_PATTERNS.register("commonwealth", () -> new BannerPattern("commonwealth"));

    public static final RegistryObject<Item> COMMONWEALTH_BANNER_PATTERN_ITEM = ITEMS.register("commonwealth_banner_pattern", () -> new BannerPatternItem(TagInit.COMMONWEALTH_BANNER, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<BannerPattern> HIGHLAND_BANNER_PATTERN = BANNER_PATTERNS.register("highland", () -> new BannerPattern("highland"));

    public static final RegistryObject<Item> HIGHLAND_BANNER_PATTERN_ITEM = ITEMS.register("highland_banner_pattern", () -> new BannerPatternItem(TagInit.HIGHLAND_BANNER, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<BannerPattern> NOMAD_BANNER_PATTERN = BANNER_PATTERNS.register("nomad", () -> new BannerPattern("nomad"));
    public static final RegistryObject<Item> NOMAD_BANNER_PATTERN_ITEM = ITEMS.register("nomad_banner_pattern", () -> new BannerPatternItem(TagInit.NOMAD_BANNER, new Item.Properties().stacksTo(1)));

    /*
    new items: crafting oil, canvas, quilted canvas, finished leather, padded canvas, hardwood shaft, finished shaft, small steel blade, large steel blade, small iron blade, large iron blade, small stone blade, large stone blade, herbal extract, aqua regia, aqua fortis
    4 more here: sharpening kit, polishing kit, reinforcement kit, bowstring kit
     */

    public static final RegistryObject<Item> CRAFTING_OIL = ITEMS.register("crafting_oil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CANVAS = ITEMS.register("canvas", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> QUILTED_CANVAS = ITEMS.register("quilted_canvas", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FINISHED_LEATHER = ITEMS.register("finished_leather", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PADDED_CANVAS = ITEMS.register("padded_canvas", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HARDWOOD_SHAFT = ITEMS.register("hardwood_shaft", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FINISHED_SHAFT = ITEMS.register("finished_shaft", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SMALL_STEEL_BLADE = ITEMS.register("small_steel_blade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_STEEL_BLADE = ITEMS.register("large_steel_blade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SMALL_IRON_BLADE = ITEMS.register("small_iron_blade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_IRON_BLADE = ITEMS.register("large_iron_blade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SMALL_STONE_BLADE = ITEMS.register("small_stone_blade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LARGE_STONE_BLADE = ITEMS.register("large_stone_blade", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HERBAL_EXTRACT = ITEMS.register("herbal_extract", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHARPENING_KIT = ITEMS.register("sharpening_kit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POLISHING_KIT = ITEMS.register("polishing_kit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REINFORCEMENT_KIT = ITEMS.register("reinforcement_kit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOWSTRING_KIT = ITEMS.register("bowstring_kit", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DZIWER_BAR = ITEMS.register("dziwer_bar", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BULAT_BAR = ITEMS.register("bulat_bar", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TINCTURE = ITEMS.register("tincture", () -> new TinctureItem(new Item.Properties().stacksTo(3)));

    public static final RegistryObject<Item> RAW_LIMONITE = ITEMS.register("raw_limonite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_MAGNETITE = ITEMS.register("raw_magnetite", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PYRITE = ITEMS.register("raw_pyrite", () -> new Item(new Item.Properties()));



    public RMGMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();



        TagInit.init();
        ITEMS.register(modEventBus);
        ModBlocks.register(modEventBus);
        BANNER_PATTERNS.register(modEventBus);
        RMGPotions.register(modEventBus);
        ModMessages.register();

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(EMPIRE_BANNER_PATTERN_ITEM);
            event.accept(KINGDOM_BANNER_PATTERN_ITEM);
            event.accept(MOTHERLAND_BANNER_PATTERN_ITEM);
            event.accept(COMMONWEALTH_BANNER_PATTERN_ITEM);
            event.accept(HIGHLAND_BANNER_PATTERN_ITEM);
            event.accept(NOMAD_BANNER_PATTERN_ITEM);
            event.accept(CRAFTING_OIL);
            event.accept(CANVAS);
            event.accept(QUILTED_CANVAS);
            event.accept(FINISHED_LEATHER);
            event.accept(PADDED_CANVAS);
            event.accept(HARDWOOD_SHAFT);
            event.accept(FINISHED_SHAFT);
            event.accept(SMALL_STEEL_BLADE);
            event.accept(LARGE_STEEL_BLADE);
            event.accept(SMALL_IRON_BLADE);
            event.accept(LARGE_IRON_BLADE);
            event.accept(SMALL_STONE_BLADE);
            event.accept(LARGE_STONE_BLADE);
            event.accept(HERBAL_EXTRACT);
            event.accept(SHARPENING_KIT);
            event.accept(POLISHING_KIT);
            event.accept(REINFORCEMENT_KIT);
            event.accept(BOWSTRING_KIT);
            event.accept(RAW_LIMONITE);
            event.accept(RAW_MAGNETITE);
            event.accept(RAW_PYRITE);
            event.accept(DZIWER_BAR);
            event.accept(BULAT_BAR);




        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), RMGPotions.RESISTANCE.get()));
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), Potions.REGENERATION));
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), Potions.LONG_NIGHT_VISION));
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), Potions.STRENGTH));
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), RMGPotions.HASTE.get()));
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), RMGPotions.ABSORPTION.get()));
            event.accept(PotionUtils.setPotion(TINCTURE.get().getDefaultInstance(), Potions.SWIFTNESS));

        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.LIMONITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_LIMONITE_ORE);
            event.accept(ModBlocks.MAGNETITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_MAGNETITE_ORE);
            event.accept(ModBlocks.PYRITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_PYRITE_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)

    {

        // Do something when the server starts
        LOGGER.info("[RMG]: Rising Kingdoms Core Mod Loaded!");
    }


    @SubscribeEvent
    public void onServerStarted(ServerStartingEvent event)
    {
        if (ModList.get().isLoaded("luckperms")) {
            new MyListener(this, LuckPermsProvider.get()).register();
        }
    }




    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
