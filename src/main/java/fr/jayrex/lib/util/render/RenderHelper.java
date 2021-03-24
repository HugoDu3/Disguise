package fr.jayrex.lib.util.render;

import fr.jayrex.render.disguise.RenderDisguises;
import fr.jayrex.render.disguise.RenderUnDisguise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

@SuppressWarnings("rawtypes")
public class RenderHelper {


    public static Render<EntityLivingBase> getRenderFromID(int id, EntityPlayer player) {
        switch (id) {
            case 0:
                return getPlayerDisguise(player);
            case 1:
                return RenderDisguises.pig;
            case 2:
                return RenderDisguises.creeper;
            case 3:
                return RenderDisguises.enderman;
            default:
                return getPlayerDisguise(player);
        }
    }

    private static Render getPlayerDisguise(EntityPlayer player) {
        //player instanceof AbstractClientPlayer
        String s = ((AbstractClientPlayer)player).getSkinType();
        RenderPlayer r = Minecraft.getMinecraft().getRenderManager().getSkinMap().get(s);
        RenderUnDisguise renderplayer = new RenderUnDisguise(Minecraft.getMinecraft().getRenderManager(), s == "default" ? false : true);
        return renderplayer != null ? renderplayer  : new RenderPlayer(Minecraft.getMinecraft().getRenderManager());
    }

//
//    public boolean isDisguiseActive(EntityPlayer p) {
//        return p. != RenderDisguises.player;
//    }


}


