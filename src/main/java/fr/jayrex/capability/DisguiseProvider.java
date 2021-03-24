package fr.jayrex.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class DisguiseProvider implements ICapabilitySerializable<NBTBase>

{

    @CapabilityInject(IDisguise.class)

    public static final Capability<IDisguise> DISGUISE = null;


    private IDisguise instance = DISGUISE.getDefaultInstance();


    @Override

    public boolean hasCapability(Capability<?> capability, EnumFacing facing)

    {

        return capability == DISGUISE;

    }


    @Override

    public <T> T getCapability(Capability<T> capability, EnumFacing facing)

    {

        return capability == DISGUISE ? DISGUISE.<T>cast(this.instance) : null;

    }


    @Override

    public NBTBase serializeNBT()

    {

        return DISGUISE.getStorage().writeNBT(DISGUISE, this.instance, null);

    }


    @Override

    public void deserializeNBT(NBTBase nbt)

    {

        DISGUISE.getStorage().readNBT(DISGUISE, this.instance, null, nbt);

    }

}
