package me.fireclient.mixins;

import me.fireclient.modules.Visuals.JumpCircle;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "jump", at = @At("HEAD"))
    private void onJump(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self == net.minecraft.client.MinecraftClient.getInstance().player) {
            if (JumpCircle.INSTANCE != null && JumpCircle.INSTANCE.isEnabled()) {
                World world = self.world;
                double x = self.getX();
                double z = self.getZ();
                double y = self.getY();
                for (int i = 0; i < 360; i += 15) {
                    double rad = Math.toRadians(i);
                    double offsetX = Math.cos(rad) * 1.2;
                    double offsetZ = Math.sin(rad) * 1.2;
                    world.addParticle(ParticleTypes.END_ROD, x + offsetX, y, z + offsetZ, 0, 0.1, 0);
                }
            }
        }
    }
}
