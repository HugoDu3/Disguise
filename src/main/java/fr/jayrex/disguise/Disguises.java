package fr.jayrex.disguise;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Disguises {
    private static Map<UUID, PlayerDisguise> disguisesMap = new HashMap<>();

    public static Map<UUID, PlayerDisguise> getDisguises() {
        return disguisesMap;
    }

    public static PlayerDisguise createDisguise(UUID uniqueID, String s) {
        Class<? extends Entity> clazz = null;
        Entity entity = null;
        try {
            clazz = EntityList.getClass(new ResourceLocation(s));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PlayerDisguise(uniqueID, clazz);
    }
}
