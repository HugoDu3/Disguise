package fr.jayrex.disguise;

import net.minecraft.entity.Entity;

import java.util.UUID;

public class PlayerDisguise {
    public Class<? extends Entity> type;
    public UUID playerID;

    public PlayerDisguise(UUID playerID, Class<? extends Entity> entity) {
        this.type = entity;
        this.playerID = playerID;
    }
}
