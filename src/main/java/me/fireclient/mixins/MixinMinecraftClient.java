package me.fireclient.mixins;

import me.fireclient.gui.ClickGuiScreen;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "executeChatCommand", at = @At("HEAD"), cancellable = true)
    private void onChatCommand(String command, CallbackInfo ci) {
        if (command.equalsIgnoreCase(".telephone")) {
            ClickGuiScreen.toggleHudHidden();
            ci.cancel();
        }
    }
}
