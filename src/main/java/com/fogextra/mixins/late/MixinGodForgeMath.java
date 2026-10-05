package com.fogextra.mixins.late;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.fogextra.machine.MTEAlloyBlastSmelterModule;
import com.fogextra.machine.MTEAlloySmelterModule;
import com.fogextra.machine.MTEExtractorModule;
import com.fogextra.machine.MTESolarMuonCatalystModule;

import tectech.thing.metaTileEntity.multi.godforge.MTEBaseModule;
import tectech.thing.metaTileEntity.multi.godforge.upgrade.ForgeOfGodsUpgrade;
import tectech.thing.metaTileEntity.multi.godforge.util.ForgeOfGodsData;
import tectech.thing.metaTileEntity.multi.godforge.util.GodforgeMath;

@Mixin(value = GodforgeMath.class, remap = false)
public abstract class MixinGodForgeMath {

    @Inject(method = "allowModuleConnection", at = @At("HEAD"), cancellable = true)
    private static void recipesLoader(MTEBaseModule module, ForgeOfGodsData data, CallbackInfoReturnable<Boolean> cir) {
        if (module instanceof MTEAlloySmelterModule) {
            cir.setReturnValue(true);
            return;
        }
        if ((module instanceof MTEAlloyBlastSmelterModule || module instanceof MTEExtractorModule)
            && data.isUpgradeActive(ForgeOfGodsUpgrade.FDIM)) {
            cir.setReturnValue(true);
            return;
        }
        if (module instanceof MTESolarMuonCatalystModule && data.isUpgradeActive(ForgeOfGodsUpgrade.QGPIU)) {
            cir.setReturnValue(true);
        }
    }

    @ModifyConstant(
        method = "calculateMaxParallelForModules(Ltectech/thing/metaTileEntity/multi/godforge/MTEBaseModule;Ltectech/thing/metaTileEntity/multi/godforge/util/ForgeOfGodsData;I)V",
        constant = @Constant(intValue = 0, ordinal = 0))
    private static int onCalculateMaxParallelForModules(int constant, MTEBaseModule module, ForgeOfGodsData data,
        int fuelFactor) {
        if (module instanceof MTEAlloySmelterModule) {
            return 4096;
        } else if (module instanceof MTEExtractorModule) {
            return 2048;
        } else if (module instanceof MTEAlloyBlastSmelterModule) {
            return 512;
        } else if (module instanceof MTESolarMuonCatalystModule) {
            return 256;
        }
        return constant;
    }

}
