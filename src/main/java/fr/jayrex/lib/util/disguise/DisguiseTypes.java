package fr.jayrex.lib.util.disguise;

import fr.jayrex.render.disguise.RenderDisguise;
import fr.jayrex.render.disguise.RenderUnDisguise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelPig;
import net.minecraft.util.ResourceLocation;

public enum DisguiseTypes {

    CREEPER(new ModelCreeper(), new ResourceLocation("textures/entity/creeper/creeper.png")),
    PIG(new ModelPig(), new ResourceLocation("textures/entity/pig/pig.png")),
    PLAYER();

    private RenderDisguise disguise;
    private RenderUnDisguise player;

    private DisguiseTypes(ModelBase model, ResourceLocation texture) {
        disguise = new RenderDisguise(Minecraft.getMinecraft().getRenderManager(), model, texture);
    }

    private DisguiseTypes() {
        player = new RenderUnDisguise(Minecraft.getMinecraft().getRenderManager());
    }

    public RenderDisguise getDisguise() {
        return disguise;
    }

    public RenderUnDisguise getPlayer() {
        return player;
    }

}