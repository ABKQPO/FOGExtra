package com.fogextra.recipe;

import java.util.Comparator;

import com.fogextra.FOGExtraItemList;

import gregtech.api.gui.modularui.GTUITextures;
import gregtech.api.recipe.RecipeMap;
import gregtech.api.recipe.RecipeMapBackend;
import gregtech.api.recipe.RecipeMapBuilder;
import gregtech.api.util.GTRecipe;

public class FOGERecipeMaps {

    public static final RecipeMap<RecipeMapBackend> SolarMuonCatalyst = RecipeMapBuilder
        .of("foge.recipe.SolarMuonCatalyst", RecipeMapBackend::new)
        .maxIO(1, 0, 9, 1)
        .progressBar(GTUITextures.PROGRESSBAR_ARROW_MULTIPLE)
        .frontend(GeneralFrontend::new)
        .neiHandlerInfo(
            builder -> builder.setDisplayStack(FOGExtraItemList.fogSolarMuonCatalystModule.get(1))
                .setMaxRecipesPerPage(1))
        .neiRecipeComparator(
            Comparator
                .<GTRecipe, Boolean>comparing(
                    recipe -> recipe.getMetadataOrDefault(SolorMuonCatalystMetadata.INSTANCE, false))
                .thenComparing(GTRecipe::compareTo))
        .build();
}
