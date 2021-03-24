package fr.jayrex.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EnderPacket implements IMessage {


    public EnderPacket() {

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
