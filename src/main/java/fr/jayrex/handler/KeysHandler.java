package fr.jayrex.handler;

import fr.jayrex.capability.DisguiseProvider;
import fr.jayrex.core.HeDisguises;
import fr.jayrex.lib.proxy.ClientProxy;
import fr.jayrex.lib.util.config.ConfigUtils;
import fr.jayrex.packet.CreepPacket;
import fr.jayrex.packet.EnderPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KeysHandler {
    private boolean teleported;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(KeyInputEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().player;

        KeyBinding[] keyBindings = ClientProxy.keyBindings;


        if (player.getCapability(DisguiseProvider.DISGUISE, null) != null) {
            if (ConfigUtils.teleportEnabled) {
                if (player.getCapability(DisguiseProvider.DISGUISE, null).getDisguiseID() == 3) {
                    if (keyBindings[1].isPressed()) {
                    	HeDisguises.INSTANCE.sendToServer(new EnderPacket());
                    }
                }
            }
            if (ConfigUtils.explodeEnabled) {
                if (player.getCapability(DisguiseProvider.DISGUISE, null).getDisguiseID() == 2) {
                    if (keyBindings[2].isPressed()) {
                    	HeDisguises.INSTANCE.sendToServer(new CreepPacket());

                    }
                }
            }
        }
    }

}