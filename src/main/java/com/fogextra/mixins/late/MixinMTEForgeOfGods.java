package com.fogextra.mixins.late;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.fogextra.machine.MTEAlloyBlastSmelterModule;
import com.fogextra.machine.MTEAlloySmelterModule;
import com.fogextra.machine.MTEExtractorModule;
import com.fogextra.machine.MTESolarMuonCatalystModule;
import com.fogextra.mixinHelper.IFOGModule;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;

import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import tectech.thing.metaTileEntity.multi.godforge.MTEBaseModule;
import tectech.thing.metaTileEntity.multi.godforge.MTEForgeOfGods;

@Mixin(value = MTEForgeOfGods.class, remap = false)
public abstract class MixinMTEForgeOfGods {

    @Shadow
    private int ringAmount;

    @WrapOperation(
        method = "determineCompositionMilestoneLevel",
        at = @At(value = "CONSTANT", args = "classValue=tectech/thing/metaTileEntity/multi/godforge/MTESmeltingModule"))
    private boolean wrapInstanceOfMTESmeltingModule(Object obj, Operation<Boolean> original,
        @Local(ordinal = 0) int[] uniqueModuleCount, @Local(ordinal = 0) LocalIntRef smelting,
        @Local(ordinal = 1) LocalIntRef molten, @Local(ordinal = 2) LocalIntRef plasma,
        @Local(ordinal = 3) LocalIntRef exotic, @Local(ordinal = 4) LocalIntRef exoticMagmatter) {
        if (obj instanceof MTESolarMuonCatalystModule) {
            uniqueModuleCount[3] = 1;
            uniqueModuleCount[4] = 1;
            exotic.set(exotic.get() + 1);
            exoticMagmatter.set(exoticMagmatter.get() + 1);
            return false;
        }

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

    @Inject(
        method = "onPostTick",
        at = @At(
            value = "INVOKE",
            target = "Ltectech/thing/metaTileEntity/multi/godforge/util/GodforgeMath;allowModuleConnection(Ltectech/thing/metaTileEntity/multi/godforge/MTEBaseModule;Ltectech/thing/metaTileEntity/multi/godforge/MTEForgeOfGods;)Z"))
    private void onModuleLoop(IGregTechTileEntity aBaseMetaTileEntity, long aTick, CallbackInfo ci,
        @Local MTEBaseModule module) {
        if (module instanceof IFOGModule fogModule) {
            fogModule.setRingAmount(ringAmount);
        }
    }

}
