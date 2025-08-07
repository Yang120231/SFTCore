package org.leodreamer.sftcore.mixin;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ParallelHatchPartMachine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParallelHatchPartMachine.class)
public class GTParallelHatchMixin {
    @Mutable
    @Final
    @Shadow(remap = false)
    private int maxParallel;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void gtceu$modifyMaxParallel(IMachineBlockEntity holder, int tier, CallbackInfo ci) {
        this.maxParallel *= 16;
    }
}
