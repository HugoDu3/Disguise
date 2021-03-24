package fr.jayrex.lib.util.config;


import fr.jayrex.lib.util.Utils;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigEventHandler {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void changed(ConfigChangedEvent.OnConfigChangedEvent event) {

        if (event.getModID().equals(Utils.MODID)) {

            ConfigUtils.syncFromGui();

        }

    }


}
