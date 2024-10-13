package com.hisroyalty.rmg;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BannerPattern;

public class TagInit {
    public static TagKey<BannerPattern> EMPIRE_BANNER = bannerPatternTag("pattern_item/empire");
    public static TagKey<BannerPattern> KINGDOM_BANNER = bannerPatternTag("pattern_item/kingdom");

    public static TagKey<BannerPattern> MOTHERLAND_BANNER = bannerPatternTag("pattern_item/motherland");
    public static TagKey<BannerPattern> COMMONWEALTH_BANNER = bannerPatternTag("pattern_item/commonwealth");
    public static TagKey<BannerPattern> HIGHLAND_BANNER = bannerPatternTag("pattern_item/highland");
    public static TagKey<BannerPattern> NOMAD_BANNER = bannerPatternTag("pattern_item/nomad");






    public static void init() {

    }


    public static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(RMGMod.MODID, path));
    }
    public static TagKey<BannerPattern> bannerPatternTag(String path) {
        return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(RMGMod.MODID,path));
    }

}
