package com.fogextra;

import static com.fogextra.FOGExtra.*;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.ItemList;

@Mod(
    modid = MODID,
    version = Tags.VERSION,
    name = MODNAME,
    dependencies = "required-after:gregtech;",
    acceptedMinecraftVersions = "1.7.10")
public class FOGExtra {

    @Mod.Instance(Tags.MODID)
    public static FOGExtra instance;
    public static final String MODID = Tags.MODID;
    public static final String MODNAME = Tags.MODNAME;
    public static final String VERSION = Tags.VERSION;
    public static final String ARTHOR = "HFstudio";
    public static final String RESOURCE_ROOT_ID = Tags.MODID;
    public static final Logger LOG = LogManager.getLogger(MODID);

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {}

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        registry();
    }

    public static void registry() {
        FOGExtraItemList.fogAlloySmelterModule.set(
            new MTEAlloySmelterModule(
                21172,
                "NameFOGAlloySmelterModule",
                StatCollector.translateToLocal("NameFOGAlloySmelterModule")));
        FOGExtraItemList.fogAlloyBlastSmelterModule.set(
            new MTEAlloyBlastSmelterModule(
                21173,
                "NameFOGAlloyBlastSmelterModule",
                StatCollector.translateToLocal("NameFOGAlloyBlastSmelterModule")));
        FOGExtraItemList.fogExtractorModule.set(
            new MTEExtractorModule(
                21174,
                "NameFOGExtractorModule",
                StatCollector.translateToLocal("NameFOGExtractorModule")));
    }

    private static final List<ItemStack> MachineStack = new ArrayList<>();

    public static CreativeTabs FOGExtraCreativeTabs = new CreativeTabs("FOGExtraCreativeTabs") {

        @Override
        public Item getTabIconItem() {
            return ItemList.Transdimensional_Alignment_Matrix.getItem();
        }

        @SideOnly(Side.CLIENT)
        public int func_151243_f() {
            return 32758;
        }

        @Override
        public void displayAllReleventItems(List<ItemStack> stackList) {
            stackList.addAll(MachineStack);
            super.displayAllReleventItems(stackList);
        }
    };

    public static void addToMachineList(ItemStack stack) {
        MachineStack.add(stack);
    }
}
