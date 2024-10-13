package com.hisroyalty.rmg;

import com.hisroyalty.rmg.RMGMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = RMGMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();



    private static final ForgeConfigSpec.IntValue EMP_SPAWN_X = BUILDER
            .comment("Empire Spawn X Coordinate")
            .defineInRange("emp_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue EMP_SPAWN_Y = BUILDER
            .comment("Empire Spawn Y Coordinate")
            .defineInRange("emp_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue EMP_SPAWN_Z = BUILDER
            .comment("Empire Spawn Z Coordinate")
            .defineInRange("emp_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue KNG_SPAWN_X = BUILDER
            .comment("Kingdom Spawn X Coordinate")
            .defineInRange("kng_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue KNG_SPAWN_Y = BUILDER
            .comment("Kingdom Spawn Y Coordinate")
            .defineInRange("kng_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue KNG_SPAWN_Z = BUILDER
            .comment("Kingdom Spawn Z Coordinate")
            .defineInRange("kng_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue MTH_SPAWN_X = BUILDER
            .comment("Motherland Spawn X Coordinate")
            .defineInRange("mth_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue MTH_SPAWN_Y = BUILDER
            .comment("Motherland Spawn Y Coordinate")
            .defineInRange("mth_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue MTH_SPAWN_Z = BUILDER
            .comment("Motherland Spawn Z Coordinate")
            .defineInRange("mth_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue CMW_SPAWN_X = BUILDER
            .comment("Commonwealth Spawn X Coordinate")
            .defineInRange("cmw_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue CMW_SPAWN_Y = BUILDER
            .comment("Commonwealth Spawn Y Coordinate")
            .defineInRange("cmw_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue CMW_SPAWN_Z = BUILDER
            .comment("Commonwealth Spawn Z Coordinate")
            .defineInRange("cmw_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue HGH_SPAWN_X = BUILDER
            .comment("Highland Spawn X Coordinate")
            .defineInRange("hgh_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue HGH_SPAWN_Y = BUILDER
            .comment("Highland Spawn Y Coordinate")
            .defineInRange("hgh_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue HGH_SPAWN_Z = BUILDER
            .comment("Highland Spawn Z Coordinate")
            .defineInRange("hgh_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue NMD_SPAWN_X = BUILDER
            .comment("Nomad Spawn X Coordinate")
            .defineInRange("nmd_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue NMD_SPAWN_Y = BUILDER
            .comment("Nomad Spawn Y Coordinate")
            .defineInRange("nmd_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue NMD_SPAWN_Z = BUILDER
            .comment("Nomad Spawn Z Coordinate")
            .defineInRange("nmd_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue KOR_SPAWN_X = BUILDER
            .comment("Kingdom of Rose Spawn X Coordinate")
            .defineInRange("kor_x", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue KOR_SPAWN_Y = BUILDER
            .comment("Kingdom of Rose Spawn Y Coordinate")
            .defineInRange("kor_y", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.IntValue KOR_SPAWN_Z = BUILDER
            .comment("Kingdom of Rose Spawn Z Coordinate")
            .defineInRange("kor_z", 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int empSpawnX;
    public static int empSpawnY;
    public static int empSpawnZ;
    public static int kngSpawnX;
    public static int kngSpawnY;
    public static int kngSpawnZ;
    public static int mthSpawnX;
    public static int mthSpawnY;
    public static int mthSpawnZ;
    public static int cmwSpawnX;
    public static int cmwSpawnY;
    public static int cmwSpawnZ;
    public static int hghSpawnX;
    public static int hghSpawnY;
    public static int hghSpawnZ;
    public static int nmdSpawnX;
    public static int nmdSpawnY;
    public static int nmdSpawnZ;
    public static int korSpawnX;
    public static int korSpawnY;
    public static int korSpawnZ;
    public static String playerNameFormat;
    public static String chatMessageFormat;

    private static boolean validateItemName(final Object obj)
    {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        empSpawnX = EMP_SPAWN_X.get();
        empSpawnY = EMP_SPAWN_Y.get();
        empSpawnZ = EMP_SPAWN_Z.get();
        kngSpawnX = KNG_SPAWN_X.get();
        kngSpawnY = KNG_SPAWN_Y.get();
        kngSpawnZ = KNG_SPAWN_Z.get();
        mthSpawnX = MTH_SPAWN_X.get();
        mthSpawnY = MTH_SPAWN_Y.get();
        mthSpawnZ = MTH_SPAWN_Z.get();
        cmwSpawnX = CMW_SPAWN_X.get();
        cmwSpawnY = CMW_SPAWN_Y.get();
        cmwSpawnZ = CMW_SPAWN_Z.get();
        hghSpawnX = HGH_SPAWN_X.get();
        hghSpawnY = HGH_SPAWN_Y.get();
        hghSpawnZ = HGH_SPAWN_Z.get();
        nmdSpawnX = NMD_SPAWN_X.get();
        nmdSpawnY = NMD_SPAWN_Y.get();
        nmdSpawnZ = NMD_SPAWN_Z.get();
        korSpawnX = KOR_SPAWN_X.get();
        korSpawnY = KOR_SPAWN_Y.get();
        korSpawnZ = KOR_SPAWN_Z.get();
    }
}
