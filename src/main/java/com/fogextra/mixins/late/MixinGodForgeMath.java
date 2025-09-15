package com.fogextra.mixins.late;

import static tectech.thing.metaTileEntity.multi.godforge.upgrade.ForgeOfGodsUpgrade.*;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.fogextra.MTEAlloyBlastSmelterModule;
import com.fogextra.MTEAlloySmelterModule;
import com.fogextra.MTEExtractorModule;

import tectech.thing.metaTileEntity.multi.godforge.MTEBaseModule;
import tectech.thing.metaTileEntity.multi.godforge.MTEForgeOfGods;
import tectech.thing.metaTileEntity.multi.godforge.util.GodforgeMath;

@Mixin(value = GodforgeMath.class, remap = false)
public abstract class MixinGodForgeMath {

    @Inject(method = "allowModuleConnection", at = @At("HEAD"), cancellable = true)
    private static void recipesLoader(MTEBaseModule module, MTEForgeOfGods godforge,
        CallbackInfoReturnable<Boolean> cir) {
        if (module instanceof MTEAlloySmelterModule) {
            cir.setReturnValue(true);
            return;
        }
        if ((module instanceof MTEAlloyBlastSmelterModule || module instanceof MTEExtractorModule)
            && godforge.isUpgradeActive(FDIM)) {
            cir.setReturnValue(true);
        }
    }
}
