package com.github.brainage04.brainconfig.keybinds;

import com.github.brainage04.brainconfig.BrainConfig;
import com.github.brainage04.brainconfig.config.MyGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class ConfigKeyBind extends KeyBinding {
    public ConfigKeyBind() {
        super("Open Config GUI", Keyboard.KEY_NUMPADENTER, BrainConfig.MOD_NAME);
    }

    @SubscribeEvent
    void tick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (this.isPressed() && Minecraft.getMinecraft().currentScreen == null) {
            Minecraft.getMinecraft().displayGuiScreen(new MyGuiScreen());
        }
    }
}
