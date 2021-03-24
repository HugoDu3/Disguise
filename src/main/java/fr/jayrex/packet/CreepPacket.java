package fr.jayrex.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CreepPacket implements IMessage {

    public CreepPacket() {


    }


    @Override
    public void toBytes(ByteBuf buf) {


    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // Reads the int back from the buf. Note that if you have multiple
        // values, you must read in the same order you wrote.


    }
}
