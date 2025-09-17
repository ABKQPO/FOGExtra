package com.fogextra;

import static gregtech.api.enums.Mods.*;
import static gregtech.api.util.GTRecipeBuilder.*;
import static gregtech.api.util.GTRecipeConstants.*;
import static gregtech.api.util.GTRecipeConstants.SCANNING;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeConstants;
import gregtech.api.util.recipe.Scanning;
import gtPlusPlus.core.material.MaterialMisc;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;
import tectech.thing.CustomItemList;

public class RecipeLoader {

    public static void loadRecipe() {
        GTValues.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, ItemList.AlloySmelterUIV.get(1))
            .metadata(SCANNING, new Scanning(24 * HOURS, TierEU.RECIPE_LV))
            .itemInputs(
                CustomItemList.Godforge_SingularityShieldingCasing.get(4),
                ItemList.AlloySmelterUIV.get(64),
                ItemList.AlloySmelterUIV.get(64),
                ItemList.ZPM4.get(1),
                GTOreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUIV, 16),
                ItemList.Robot_Arm_UIV.get(16),
                ItemList.Conveyor_Module_UIV.get(32),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.SixPhasedCopper, 16),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.Creon, 8),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.Mellion, 8),
                new Object[] { OrePrefixes.circuit.get(Materials.UIV), 32L })
            .fluidInputs(
                MaterialMisc.MUTATED_LIVING_SOLDER.getFluidStack(147456),
                MaterialsUEVplus.ExcitedDTEC.getFluid(2048000),
                Materials.Lead.getPlasma(36864),
                MaterialsUEVplus.TranscendentMetal.getMolten(147456))
            .itemOutputs(FOGExtraItemList.fogAlloySmelterModule.get(1))
            .eut(TierEU.RECIPE_UMV)
            .duration(300 * SECONDS)
            .addTo(GTRecipeConstants.AssemblyLine);

        GTValues.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, ItemList.FluidExtractorUIV.get(1))
            .metadata(SCANNING, new Scanning(24 * HOURS, TierEU.RECIPE_LV))
            .itemInputs(
                CustomItemList.Godforge_SingularityShieldingCasing.get(4),
                ItemList.ExtractorUIV.get(64),
                ItemList.FluidExtractorUIV.get(64),
                ItemList.ZPM4.get(1),
                GTOreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUIV, 16),
                ItemList.Robot_Arm_UIV.get(16),
                ItemList.Conveyor_Module_UIV.get(32),
                ItemList.Electric_Pump_UIV.get(64),
                ItemList.Relativistic_Heat_Capacitor.get(8),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.SixPhasedCopper, 16),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.Creon, 8),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.Mellion, 8),
                new Object[] { OrePrefixes.circuit.get(Materials.UIV), 32L })
            .fluidInputs(
                MaterialMisc.MUTATED_LIVING_SOLDER.getFluidStack(147456),
                MaterialsUEVplus.ExcitedDTEC.getFluid(2048000),
                Materials.Lead.getPlasma(36864),
                MaterialsUEVplus.TranscendentMetal.getMolten(147456))
            .itemOutputs(FOGExtraItemList.fogExtractorModule.get(1))
            .eut(TierEU.RECIPE_UMV)
            .duration(300 * SECONDS)
            .addTo(GTRecipeConstants.AssemblyLine);

        GTValues.RA.stdBuilder()
            .metadata(RESEARCH_ITEM, GregtechItemList.Mega_AlloyBlastSmelter.get(1))
            .metadata(SCANNING, new Scanning(24 * HOURS, TierEU.RECIPE_LV))
            .itemInputs(
                CustomItemList.Godforge_SingularityShieldingCasing.get(4),
                GregtechItemList.Mega_AlloyBlastSmelter.get(64),
                GregtechItemList.Mega_AlloyBlastSmelter.get(64),
                ItemList.ZPM4.get(1),
                GTOreDictUnificator.get(OrePrefixes.wireGt16, Materials.SuperconductorUIV, 16),
                ItemList.Robot_Arm_UIV.get(16),
                ItemList.Conveyor_Module_UIV.get(32),
                ItemList.Electric_Pump_UIV.get(64),
                ItemList.Relativistic_Heat_Capacitor.get(32),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.SixPhasedCopper, 16),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.Creon, 8),
                GTOreDictUnificator.get(OrePrefixes.plateDense, MaterialsUEVplus.Mellion, 8),
                new Object[] { OrePrefixes.circuit.get(Materials.UIV), 32L })
            .fluidInputs(
                MaterialMisc.MUTATED_LIVING_SOLDER.getFluidStack(147456),
                MaterialsUEVplus.ExcitedDTEC.getFluid(2048000),
                MaterialsUEVplus.PhononMedium.getFluid(32000),
                MaterialsUEVplus.TranscendentMetal.getMolten(147456))
            .itemOutputs(FOGExtraItemList.fogAlloyBlastSmelterModule.get(1))
            .eut(TierEU.RECIPE_UMV)
            .duration(300 * SECONDS)
            .addTo(GTRecipeConstants.AssemblyLine);
    }
}
