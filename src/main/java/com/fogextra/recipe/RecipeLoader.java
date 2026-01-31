package com.fogextra.recipe;

import static gregtech.api.enums.Mods.*;
import static gregtech.api.util.GTRecipeBuilder.*;
import static gregtech.api.util.GTRecipeConstants.*;
import static gregtech.api.util.GTRecipeConstants.SCANNING;

import net.minecraftforge.fluids.FluidStack;

import com.fogextra.FOGExtraItemList;

import goodgenerator.util.ItemRefer;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.MaterialsUEVplus;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTRecipeConstants;
import gregtech.api.util.GTUtility;
import gregtech.api.util.recipe.Scanning;
import gtPlusPlus.core.material.MaterialMisc;
import gtPlusPlus.core.material.MaterialsElements;
import gtPlusPlus.core.material.Particle;
import gtPlusPlus.xmod.gregtech.api.enums.GregtechItemList;
import tectech.recipe.TTRecipeAdder;
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

        TTRecipeAdder.addResearchableAssemblylineRecipe(
            CustomItemList.Machine_Multi_QuarkGluonPlasmaModule.get(1),
            768000000,
            131072,
            (int) TierEU.RECIPE_UXV,
            1,
            new Object[] { CustomItemList.Machine_Multi_QuarkGluonPlasmaModule.get(16),
                ItemList.Machine_Multi_PlasmaForge.get(16), ItemRefer.Compact_Fusion_MK5.get(4), ItemList.ZPM5.get(4),
                CustomItemList.Godforge_HarmonicPhononTransmissionConduit.get(32), ItemList.Robot_Arm_UXV.get(16),
                ItemList.Conveyor_Module_UXV.get(32), ItemList.Electric_Pump_UXV.get(64),
                GTOreDictUnificator.get(OrePrefixes.plateSuperdense, Materials.SuperconductorUMVBase, 64),
                GTOreDictUnificator.get(OrePrefixes.plateSuperdense, MaterialsUEVplus.MagMatter, 32),
                GTOreDictUnificator.get(OrePrefixes.plateSuperdense, MaterialsUEVplus.Eternity, 32),
                GTOreDictUnificator.get(OrePrefixes.plateSuperdense, MaterialsUEVplus.Universium, 16),
                new Object[] { OrePrefixes.circuit.get(Materials.UMV), 64L } },
            new FluidStack[] { MaterialMisc.MUTATED_LIVING_SOLDER.getFluidStack(147456),
                MaterialsUEVplus.ExcitedDTEC.getFluid(2048000), Materials.Lead.getPlasma(36864),
                MaterialsUEVplus.TranscendentMetal.getMolten(147456) },
            FOGExtraItemList.fogSolarMuonCatalystModule.get(1),
            300 * SECONDS,
            (int) TierEU.RECIPE_UMV);

        GTValues.RA.stdBuilder()
            .itemInputs(GTUtility.copyAmount(0, Particle.getBaseParticle(Particle.MUON)))
            .fluidInputs(
                Materials.Lutetium.getPlasma(2304),
                Materials.Uranium.getPlasma(2304),
                Materials.Europium.getPlasma(2304),
                Materials.Silicon.getPlasma(2304),
                Materials.Silver.getPlasma(2304),
                Materials.Indium.getPlasma(2304),
                Materials.Hydrogen.getPlasma(16000),
                Materials.Fluorine.getPlasma(16000))
            .fluidOutputs(MaterialsUEVplus.QuarkGluonPlasma.getFluid(6400000))
            .eut(2013265192)
            .duration(200)
            .addTo(FOGERecipeMaps.SolarMuonCatalyst);

        GTValues.RA.stdBuilder()
            .metadata(SolorMuonCatalystMetadata.INSTANCE, true)
            .itemInputs(GTUtility.copyAmount(0, Particle.getBaseParticle(Particle.MUON)))
            .fluidInputs(
                MaterialsUEVplus.Space.getMolten(100),
                MaterialsUEVplus.Time.getMolten(50),
                Materials.Ichorium.getPlasma(1440),
                new FluidStack(MaterialsElements.STANDALONE.HYPOGEN.getPlasma(), 1440),
                Materials.Flerovium.getPlasma(1440),
                new FluidStack(MaterialsElements.STANDALONE.CHRONOMATIC_GLASS.getPlasma(), 1440),
                Materials.Bedrockium.getPlasma(1440),
                new FluidStack(MaterialsElements.STANDALONE.DRAGON_METAL.getPlasma(), 1440))
            .fluidOutputs(MaterialsUEVplus.MagMatter.getMolten(64000))
            .eut(2013265192)
            .duration(200)
            .addTo(FOGERecipeMaps.SolarMuonCatalyst);
    }
}
