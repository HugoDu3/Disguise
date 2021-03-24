package fr.jayrex.core;

import fr.jayrex.capability.Disguise;
import fr.jayrex.capability.DisguiseStorage;
import fr.jayrex.capability.IDisguise;
import fr.jayrex.command.CommandDisguise;
import fr.jayrex.handler.KeysHandler;
import fr.jayrex.lib.proxy.CommonProxy;
import fr.jayrex.lib.util.Utils;
import fr.jayrex.lib.util.config.ConfigUtils;
import fr.jayrex.packet.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Random;
import java.util.concurrent.Callable;

@Mod(modid = Utils.MODID, version = Utils.VERSION)
public class HeDisguises {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Utils.MODID);

    @SidedProxy(serverSide = Utils.SEVERPROXY, clientSide = Utils.CLIENTPROXY)
    public static CommonProxy proxy;

    public int movingBlockID;

    @Mod.Instance(Utils.MODID)
    public static HeDisguises instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        int entityID = MathHelper.getRandomUUID().hashCode();
        movingBlockID = entityID;

        Utils.getLogger().info("Pre Init");

        ConfigUtils.preInit();

        proxy.preInit();
        proxy.registerRenderers();

    }
    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDisguise());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        INSTANCE.registerMessage(PacketHandler2.class, CreepPacket.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketHandler.class, EnderPacket.class, 1, Side.SERVER);
        INSTANCE.registerMessage(DisguisePacketHandler.class, DisguisePacket.class, 2, Side.SERVER);
        CapabilityManager.INSTANCE.register(IDisguise.class, new DisguiseStorage(), Disguise.class);
        Utils.getLogger().info("Init");
        MinecraftForge.EVENT_BUS.register(new fr.jayrex.handler.event.EventHandler());
        MinecraftForge.EVENT_BUS.register(new KeysHandler());
        proxy.init();

    }

    public static class DisguiseFactory implements Callable<Disguise> {
        @Override
        public Disguise call() throws Exception {
            return new Disguise();
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Utils.getLogger().info("Post Init");
        proxy.postInit();
    }

    @SuppressWarnings("unchecked")
    public static void registerEntity(Class<?> entityClass, String name, int ID, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(Utils.MODID, name), (Class<? extends Entity>) entityClass, name, ID, instance, 64, 10, true, color1, color2);
    }

    @SuppressWarnings("unchecked")
    public static void registerEntity(Class<?> entityClass, String name, int ID) {
        long seed = name.hashCode();
        Random rand = new Random(seed);
        int primaryColor = rand.nextInt() * 16777215;
        int secondaryColor = rand.nextInt() * 16777215;

        EntityRegistry.registerModEntity(new ResourceLocation(Utils.MODID, name), (Class<? extends Entity>) entityClass, name, ID, instance, 64, 10, true, primaryColor, secondaryColor);
    }

    @SuppressWarnings("unchecked")
    public static void registerEntityNoEgg(Class<?> entityClass, String name, int ID) {
        EntityRegistry.registerModEntity(new ResourceLocation(Utils.MODID, name), (Class<? extends Entity>) entityClass, name, ID, instance, 64, 10, true);
    }

}
