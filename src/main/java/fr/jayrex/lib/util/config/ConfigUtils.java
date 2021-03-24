package fr.jayrex.lib.util.config;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ConfigUtils {

    private static Configuration configcore = null;

    public static final String categoryName_SpecialAbilities = "SpecialAbilities";

    public static boolean explodeEnabled;

    public static boolean teleportEnabled;


    public static void preInit() {


        File coref = new File(Loader.instance().getConfigDir(), "disguise.cfg");
        configcore = new Configuration(coref);


    }

    public static void clientPreInit() {

        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
        syncFromFiles();
    }


    public static Configuration getConfig() {
        return configcore;

    }

    public static void syncFromFiles() {

        syncConfig(true, true);

    }

    public static void syncFromGui() {


        syncConfig(false, true);


    }

    public static void syncFromFields() {

        syncConfig(false, false);

    }

    private static void syncConfig(boolean loadFromConfigFile, boolean readFieldsFromConfig) {

        if (loadFromConfigFile) {

            configcore.load();

            Property enableExplode = configcore.get(categoryName_SpecialAbilities, "togglecreeperexplode", true);
            enableExplode.setLanguageKey("config.specialabilities.togglecreeperexplode.name");
            enableExplode.setComment(I18n.format("config.specialabilities.togglecreeperexplode.comment"));

            Property enableTeleport = configcore.get(categoryName_SpecialAbilities, "toggleenderteleport", true);
            enableExplode.setLanguageKey("config.specialabilities.toggleenderteleport.name");
            enableExplode.setComment(I18n.format("config.specialabilities.toggleenderteleport.comment"));

            List<String> orderSpecialAbilities = new ArrayList<String>();
            orderSpecialAbilities.add(enableExplode.getName());
            orderSpecialAbilities.add(enableTeleport.getName());


            configcore.setCategoryPropertyOrder(categoryName_SpecialAbilities, orderSpecialAbilities);

            if (readFieldsFromConfig) {

                explodeEnabled = enableExplode.getBoolean();
                teleportEnabled = enableTeleport.getBoolean();

            }

            enableTeleport.set(explodeEnabled);


            if (configcore.hasChanged()) {
                configcore.save();
            }

        }
    }
}
