package me.creepinson.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandler2 implements IMessageHandler<CreepPacket, IMessage> {

    @Override
    public IMessage onMessage(CreepPacket message, MessageContext ctx) {

        EntityPlayerMP player = ctx.getServerHandler().player;

        player.getServerWorld().createExplosion(player, player.posX, player.posY, player.posZ, 3, false);
        player.attackEntityFrom(DamageSource.OUT_OF_WORLD, Float.MAX_VALUE);


        return null;
    }

}
