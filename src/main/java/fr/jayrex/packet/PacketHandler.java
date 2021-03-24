package me.creepinson.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketHandler implements IMessageHandler<EnderPacket, IMessage> {

    @Override
    public IMessage onMessage(EnderPacket message, MessageContext ctx) {

        EntityPlayerMP player = ctx.getServerHandler().player;

        World world = player.getServerWorld();
        RayTraceResult ray = player.rayTrace(60, 25);
        BlockPos pos = ray.getBlockPos();
        player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        world.spawnParticle(EnumParticleTypes.PORTAL, pos.getX(), pos.getY(), pos.getZ(), 1, 1, 1);
        player.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 75, 1);


        return null;
    }

}
