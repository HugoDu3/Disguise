package fr.jayrex.lib.proxy;

import fr.jayrex.core.CreepzDisguises;
import fr.jayrex.handler.GuiHandler;
import fr.jayrex.handler.event.RenderingHandler;
import fr.jayrex.lib.util.config.ConfigUtils;
import fr.jayrex.lib.util.render.RenderHelper;
import fr.jayrex.render.disguise.RenderDisguises;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {

    public static KeyBinding[] keyBindings;
    public static RenderHelper renderUtils = new RenderHelper();


    @Override
    public void registerRenderers() {


    }

    @Override
    public void preInit() {
        super.preInit();
        ConfigUtils.clientPreInit();
    }

    public void init() {
        super.init();
        MinecraftForge.EVENT_BUS.register(new fr.jayrex.handler.event.RenderingHandler());


        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager) {
                return RenderDisguises.pig;
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager) {
                return RenderDisguises.creeper;
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager) {
                return RenderDisguises.player;
            }
        });

        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, new IRenderFactory() {
            @Override
            public Render createRenderFor(RenderManager manager) {
                return RenderDisguises.enderman;
            }
        });

        RenderingHandler.init();

        //KEYBINDS

        // declare an array of key bindings
        keyBindings = new KeyBinding[3];

        // instantiate the key bindings
        if (ConfigUtils.teleportEnabled) {
            keyBindings[1] = new KeyBinding("Téléportation", Keyboard.KEY_T, "Disguise");
        }
        if (ConfigUtils.explodeEnabled) {
            keyBindings[2] = new KeyBinding("Explosion", Keyboard.KEY_P, "Disguise");
        }
        // register all the key bindings
        for (int i = 0; i < keyBindings.length; ++i) {
            ClientRegistry.registerKeyBinding(keyBindings[i]);


        }
    }

}
