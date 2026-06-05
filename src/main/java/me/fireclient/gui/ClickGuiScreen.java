package me.fireclient.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;

public class ClickGuiScreen extends Screen {
    private static boolean hudHidden = false;
    private static final Identifier BACKGROUND = new Identifier("firecheats", "gui/background.png");
    private static final Identifier BUTTON_TEXTURE = new Identifier("firecheats", "gui/button.png");

    public ClickGuiScreen() {
        super(new LiteralText("FireCheats"));
    }

    public static boolean isHudHidden() { return hudHidden; }
    public static void toggleHudHidden() { hudHidden = !hudHidden; }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(BACKGROUND);
        drawTexture(matrices, 0, 0, 0, 0, width, height, width, height);
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.client.openScreen(null);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
