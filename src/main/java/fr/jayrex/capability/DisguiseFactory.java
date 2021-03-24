package fr.jayrex.capability;

import java.util.concurrent.Callable;

public class DisguiseFactory implements Callable<Disguise> {
    @Override
    public Disguise call() throws Exception {
        return new Disguise();
    }
}

