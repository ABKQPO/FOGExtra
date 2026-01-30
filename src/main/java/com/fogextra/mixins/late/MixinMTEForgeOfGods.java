package com.fogextra.mixins.late;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.fogextra.MTEAlloyBlastSmelterModule;
import com.fogextra.MTEAlloySmelterModule;
import com.fogextra.MTEExtractorModule;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import tectech.thing.metaTileEntity.multi.godforge.MTEForgeOfGods;

@Mixin(value = MTEForgeOfGods.class, remap = false)
public abstract class MixinMTEForgeOfGods {

    @WrapOperation(
        method = "determineCompositionMilestoneLevel",
        at = @At(value = "CONSTANT", args = "classValue=tectech/thing/metaTileEntity/multi/godforge/MTESmeltingModule"))
    private boolean wrapInstanceOfMTESmeltingModule(Object obj, Operation<Boolean> original) {
        return original.call(obj) || obj instanceof MTEAlloyBlastSmelterModule;
    }

    @WrapOperation(
        method = "determineCompositionMilestoneLevel",
        at = @At(value = "CONSTANT", args = "classValue=tectech/thing/metaTileEntity/multi/godforge/MTEMoltenModule"))
    private boolean wrapInstanceOfMTEMoltenModule(Object obj, Operation<Boolean> original) {
        return original.call(obj) || obj instanceof MTEExtractorModule;
    }

    @WrapOperation(
        method = "determineCompositionMilestoneLevel",
        at = @At(value = "CONSTANT", args = "classValue=tectech/thing/metaTileEntity/multi/godforge/MTEPlasmaModule"))
    private boolean wrapInstanceOfMTEPlasmaModule(Object obj, Operation<Boolean> original) {
        return original.call(obj) || obj instanceof MTEAlloySmelterModule;
    }
}
