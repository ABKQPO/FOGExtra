package com.fogextra.machine;

import static gregtech.api.util.GTUtility.*;
import static gregtech.common.misc.WirelessNetworkManager.*;
import static net.minecraft.util.EnumChatFormatting.*;

import java.math.BigInteger;
import java.util.ArrayList;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.NotNull;

import com.fogextra.gui.FOGModuleGui;
import com.gtnewhorizon.gtnhlib.util.numberformatting.NumberFormatUtil;

import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;
import gregtech.api.util.GTRecipe;
import gregtech.api.util.MultiblockTooltipBuilder;
import gregtech.api.util.OverclockCalculator;
import gregtech.common.gui.modularui.multiblock.base.MTEMultiBlockBaseGui;
import tectech.thing.metaTileEntity.multi.godforge.MTEBaseModule;

@IMetaTileEntity.SkipGenerateDescription
public class MTEAlloyBlastSmelterModule extends MTEBaseModule {

    private long EUt = 0;
    private int currentParallel = 0;

    public MTEAlloyBlastSmelterModule(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public MTEAlloyBlastSmelterModule(String aName) {
        super(aName);
    }

    @Override
    public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new MTEAlloyBlastSmelterModule(mName);
    }

    long wirelessEUt = 0;

    @Override
    protected ProcessingLogic createProcessingLogic() {
        return new ProcessingLogic() {

            @NotNull
            @Override
            protected CheckRecipeResult validateRecipe(@NotNull GTRecipe recipe) {

                if (recipe.mEUt > getProcessingVoltage()) {
                    return CheckRecipeResultRegistry.insufficientPower(recipe.mEUt);
                }

                wirelessEUt = (long) recipe.mEUt * getActualParallel();
                if (getUserEU(userUUID).compareTo(BigInteger.valueOf(wirelessEUt * recipe.mDuration)) < 0) {
                    return CheckRecipeResultRegistry.insufficientPower(wirelessEUt * recipe.mDuration);
                }
                return CheckRecipeResultRegistry.SUCCESSFUL;
            }

            @NotNull
            @Override
            protected OverclockCalculator createOverclockCalculator(@NotNull GTRecipe recipe) {
                return super.createOverclockCalculator(recipe).setEUt(getSafeProcessingVoltage())
                    .setRecipeHeat(recipe.mSpecialValue)
                    .setHeatOC(true)
                    .setHeatDiscount(true)
                    .setMachineHeat(Math.max(recipe.mSpecialValue, getHeatForOC()))
                    .setHeatDiscountMultiplier(getHeatEnergyDiscount())
                    .setDurationDecreasePerOC(getOverclockTimeFactor());

            }

            @NotNull
            @Override
            protected CheckRecipeResult onRecipeStart(@NotNull GTRecipe recipe) {
                if (!addEUToGlobalEnergyMap(userUUID, -calculatedEut * duration)) {
                    return CheckRecipeResultRegistry.insufficientPower(calculatedEut * duration);
                }
                addToPowerTally(
                    BigInteger.valueOf(calculatedEut)
                        .multiply(BigInteger.valueOf(duration)));
                addToRecipeTally(calculatedParallels);
                currentParallel = calculatedParallels;
                EUt = calculatedEut;
                overwriteCalculatedEut(0);
                setCurrentRecipeHeat(recipe.mSpecialValue);
                return CheckRecipeResultRegistry.SUCCESSFUL;
            }
        };
    }

    @Override
    protected void setProcessingLogicPower(ProcessingLogic logic) {
        logic.setAvailableVoltage(Long.MAX_VALUE);
        logic.setAvailableAmperage(Integer.MAX_VALUE);
        logic.setAmperageOC(false);
        logic.setUnlimitedTierSkips();
        logic.setMaxParallel(getActualParallel());
        logic.setSpeedBonus(getSpeedBonus());
        logic.setEuModifier(getEnergyDiscount());
        logic.enablePerfectOverclock();
    }

    @Override
    public double getSpeedBonus() {
        return processingSpeedBonus / 15;
    }

    @Override
    public double getEnergyDiscount() {
        return energyDiscount / 5;
    }

    @Override
    public RecipeMap<?> getRecipeMap() {
        return RecipeMaps.alloyBlastSmelterRecipes;
    }

    @Override
    protected @NotNull MTEMultiBlockBaseGui<?> getGui() {
        return new FOGModuleGui(this);
    }

    @Override
    public String[] getInfoData() {
        ArrayList<String> str = new ArrayList<>();
        str.add(
            StatCollector.translateToLocalFormatted(
                "GT5U.infodata.progress",
                GREEN + NumberFormatUtil.formatNumber(mProgresstime / 20) + RESET,
                YELLOW + NumberFormatUtil.formatNumber(mMaxProgresstime / 20) + RESET));
        str.add(
            StatCollector.translateToLocalFormatted(
                "tt.infodata.multi.currently_using",
                RED + (getBaseMetaTileEntity().isActive() ? NumberFormatUtil.formatNumber(EUt) : "0") + RESET));
        str.add(
            YELLOW + StatCollector.translateToLocalFormatted(
                "tt.infodata.multi.max_parallel",
                RESET + NumberFormatUtil.formatNumber(getActualParallel())));
        str.add(
            YELLOW + StatCollector.translateToLocalFormatted(
                "GT5U.infodata.parallel.current",
                RESET + (getBaseMetaTileEntity().isActive() ? NumberFormatUtil.formatNumber(currentParallel) : "0")));
        str.add(
            YELLOW + StatCollector.translateToLocalFormatted(
                "tt.infodata.multi.multiplier.recipe_time",
                RESET + NumberFormatUtil.formatNumber(getSpeedBonus())));
        str.add(
            YELLOW + StatCollector.translateToLocalFormatted(
                "tt.infodata.multi.multiplier.energy",
                RESET + NumberFormatUtil.formatNumber(getEnergyDiscount())));
        str.add(
            YELLOW + StatCollector.translateToLocalFormatted(
                "tt.infodata.multi.divisor.recipe_time.non_perfect_oc",
                RESET + NumberFormatUtil.formatNumber(getOverclockTimeFactor())));
        return str.toArray(new String[0]);
    }

    @Override
    public MultiblockTooltipBuilder createTooltip() {
        final MultiblockTooltipBuilder tt = new MultiblockTooltipBuilder();
        tt.addMachineType(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.recipe_type"))
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.0"))
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.1"))
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.2"))
            .addSeparator(EnumChatFormatting.AQUA, 74)
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.3"))
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.4"))
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.5"))
            .addInfo(StatCollector.translateToLocal("fogextra.machine.fog_alloy_blast_smelter_module.tooltip.6"))
            .beginStructureBlock(7, 7, 13, false)
            .addStructureInfo(
                EnumChatFormatting.GOLD + "20"
                    + EnumChatFormatting.GRAY
                    + StatCollector.translateToLocal("fogextra.machine.casing.singularity_shielding"))
            .addStructureInfo(
                EnumChatFormatting.GOLD + "20"
                    + EnumChatFormatting.GRAY
                    + StatCollector.translateToLocal("fogextra.machine.casing.gravitationally_severed"))
            .addStructureInfo(
                EnumChatFormatting.GOLD + "5"
                    + EnumChatFormatting.GRAY
                    + StatCollector.translateToLocal("fogextra.machine.casing.phonon_transmission"))
            .addStructureInfo(
                EnumChatFormatting.GOLD + "5"
                    + EnumChatFormatting.GRAY
                    + StatCollector.translateToLocal("fogextra.machine.casing.matter_guidance"))
            .addStructureInfo(
                EnumChatFormatting.GOLD + "1"
                    + EnumChatFormatting.GRAY
                    + StatCollector.translateToLocal("fogextra.machine.casing.energy_siphon"))
            .toolTipFinisher(EnumChatFormatting.AQUA, 74);
        return tt;
    }

}
