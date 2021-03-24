package fr.jayrex.handler.event;

import fr.jayrex.disguise.Disguises;
import fr.jayrex.disguise.PlayerDisguise;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderingHandler {
    private static Map<Class<? extends Entity>, IRenderFactory<? extends Entity>> map;
    private static Map<Class<? extends Entity>, Render<? extends Entity>> map2;
    private static Map<Class<? extends Entity>, Render<? extends Entity>> renderDisguiseMap = new HashMap<>();
    public static void init() {
        try {
            Field field = RenderingRegistry.class.getDeclaredField("INSTANCE");
            field.setAccessible(true);
            RenderingRegistry object = (RenderingRegistry) field.get(null);
            field = object.getClass().getDeclaredField("entityRenderers");
            field.setAccessible(true);
            map = (Map<Class<? extends Entity>, IRenderFactory<? extends Entity>>) field.get(object);
            // TODO: add map for old entity renderers that don't use IRenderFactory
            map2 = (Map<Class<? extends Entity>, Render<? extends Entity>>) field.get(object);
            // TODO: add map for old entity renderers that don't use IRenderFactory

            for(Class<? extends Entity> clazz : map.keySet()) {
                IRenderFactory<? extends Entity> factory = map.get(clazz);
                Render render = factory.createRenderFor(Minecraft.getMinecraft().getRenderManager());
                renderDisguiseMap.put(clazz, render);
            }
            for(Class<? extends Entity> clazz : Minecraft.getMinecraft().getRenderManager().entityRenderMap.keySet()) {
                //IRenderFactory<? extends Entity> factory = map.get(clazz);
                //Render render = factory.createRenderFor(Minecraft.getMinecraft().getRenderManager());
                System.out.println(clazz.getName());
                renderDisguiseMap.put(clazz, Minecraft.getMinecraft().getRenderManager().entityRenderMap.get(clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void playerRenderPre(RenderPlayerEvent.Pre event) {
        if (Disguises.getDisguises().containsKey(event.getEntityPlayer().getUniqueID())) {
            event.setCanceled(true);
            try {
                PlayerDisguise d = Disguises.getDisguises().get(event.getEntityPlayer().getUniqueID());
                //System.out.println(renderDisguiseMap);
                Render render = renderDisguiseMap.get(d.type);
                Entity entity = d.type.cast(event.getEntityLiving());
                System.out.println(entity);
                render.doRender(entity, event.getX(), event.getY(), event.getZ(), event.getEntity().rotationYaw, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<Class<? extends Entity>, IRenderFactory<? extends Entity>> getEntityRendererMap() {
        return map;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void playerRenderPost(RenderPlayerEvent.Post event) {
        
    }

}
