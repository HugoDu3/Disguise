package fr.jayrex.render.disguise;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;

public enum RenderDisguises {
    INSTANCE;

    public static Render pig = new RenderDisguise(Minecraft.getMinecraft().getRenderManager(), new ModelPig(),
            new ResourceLocation("textures/entity/pig/pig.png"));

    public static Render creeper = new RenderDisguise(Minecraft.getMinecraft().getRenderManager(),
            new ModelCreeper(), new ResourceLocation("textures/entity/creeper/creeper.png"));
    public static Render enderman = new RenderDisguise(Minecraft.getMinecraft().getRenderManager(),
            new ModelEnderman(1), new ResourceLocation("textures/entity/enderman/enderman.png"));

    public static Render player = new RenderUnDisguise(Minecraft.getMinecraft().getRenderManager());

}
