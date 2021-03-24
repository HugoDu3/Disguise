package fr.jayrex.capability;

public class Disguise implements IDisguise {
    protected int id = 0;

    @Override
    public int getDisguiseID() {
        return id;
    }

    @Override
    public void setDisguiseID(int id) {
        this.id = id;
    }
}
