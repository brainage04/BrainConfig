package com.github.brainage04.brainconfig;

import com.github.brainage04.brainconfig.keybinds.ConfigKeyBind;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = BrainConfig.MOD_ID, useMetadata=true)
public class BrainConfig {
    public static final String MOD_ID = "brainconfig";
    public static final String MOD_NAME = "BrainConfig";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static final KeyBinding CONFIG_KEYBIND = new ConfigKeyBind();

    private void registerKeyBinds(KeyBinding[] keybinds) {
        for (KeyBinding keybind : keybinds) {
            ClientRegistry.registerKeyBinding(keybind);
        }
    }

    private void registerEvents(Object[] events) {
        for (Object event : events) {
            MinecraftForge.EVENT_BUS.register(event);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerKeyBinds(new KeyBinding[]{
                CONFIG_KEYBIND
        });

        LOGGER.info(MOD_NAME + " pre-initialised.");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        registerEvents(new Object[]{
                CONFIG_KEYBIND
        });


        LOGGER.info(MOD_NAME + " initialised.");
    }
}
