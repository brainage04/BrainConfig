package com.github.brainage04.brainconfig.config;

import com.github.brainage04.brainconfig.BrainConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyGuiScreen extends GuiScreen {
    public static int elementWidth = 150;
    public static int elementWidthBig = 210;
    public static int elementHeight = 20;

    public static class BooleanButtonInfo {
        public String title;
        public Boolean value;

        BooleanButtonInfo(String title, Boolean value) {
            this.title = title;
            this.value = value;
        }
    }

    public static List<BooleanButtonInfo> buttonInfo = new ArrayList<BooleanButtonInfo>(){{
            add(new BooleanButtonInfo("test1", true));
            add(new BooleanButtonInfo("test2", true));
            add(new BooleanButtonInfo("test3", true));
            add(new BooleanButtonInfo("test4", true));
    }};

    public int elementMargin = 5;
    public int screenMargin = 20;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Draw the background tint
        drawDefaultBackground();

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        String text = BrainConfig.MOD_NAME + " Config Menu";
        int textWidth = fr.getStringWidth(text);

        // Draw the title center aligned
        fr.drawString(text, width / 2 - textWidth / 2, screenMargin, 0xFFFFFFFF);

        // Draw buttons
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void initGui() {
        super.initGui();

        int y = screenMargin * 2 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;

        // Add buttons to the gui list during gui initialization
        for (int i = 0; i < buttonInfo.size(); i++) {
            int x;

            if (i % 2 == 0) { // left hand side
                x = width / 2 - elementWidth - elementMargin;

                if (i != 0) {
                    y += 20 + elementMargin;
                }
            } else {
                x = width / 2 + elementMargin;
            }

            this.buttonList.add(new GuiButton(i, x, y, elementWidth, elementHeight, buttonInfo.get(i).title + ": " + buttonInfo.get(i).value));
        }

        this.buttonList.add(new GuiButton(this.buttonList.size(), (width - elementWidthBig) / 2, height - screenMargin * 2, elementWidthBig, elementHeight, "Save and Back to Game"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        // last button
        if (button.id == this.buttonList.size() - 1) {
            Minecraft.getMinecraft().displayGuiScreen(null);
            return;
        }

        // all other buttons
        buttonInfo.get(button.id).value = !buttonInfo.get(button.id).value; // update the boolean associated with the button
        this.buttonList.get(button.id).displayString = buttonInfo.get(button.id).title + ": " + buttonInfo.get(button.id).value;
    }
}
