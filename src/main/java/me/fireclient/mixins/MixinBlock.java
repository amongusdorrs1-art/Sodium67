package me.fireclient.mixins;

import me.fireclient.modules.World.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {
    @Inject(method = "shouldDrawSide", at = @At("HEAD"), cancellable = true)
    private static void onShouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction side, CallbackInfoReturnable<Boolean> cir) {
        if (Xray.INSTANCE != null && Xray.INSTANCE.isEnabled()) {
            String name = state.getBlock().getTranslationKey();
            boolean isOre = name.contains("ore") || name.contains("diamond") || name.contains("gold")
                         || name.contains("iron") || name.contains("emerald") || name.contains("lapis")
                         || name.contains("redstone") || name.contains("chest") || name.contains("spawner");
            if (!isOre) {
                cir.setReturnValue(false);
            }
        }
    }
}
