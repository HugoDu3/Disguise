package fr.jayrex.lib.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.jayrex.lib.util.render.RenderHelper;

import java.util.HashSet;
import java.util.UUID;

public class Utils {

    //MAIN MOD STUFF
    public static final String MODID = "Disguise";
    public static final String VERSION = "Beta";

    public static final String SEVERPROXY = "fr.jayrex.lib.proxy.CommonProxy";
    public static final String CLIENTPROXY = "fr.jayrex.lib.proxy.ClientProxy";

//END MAIN MOD STUFF

    private static Logger logger;

    public static Logger getLogger() {

        if (logger == null) {
            logger = LogManager.getFormatterLogger(MODID);
        }

        return logger;

    }


}
