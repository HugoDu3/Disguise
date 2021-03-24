package fr.jayrex.command;

import com.google.common.collect.Lists;
import fr.jayrex.disguise.Disguises;
import fr.jayrex.lib.util.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;

public class CommandDisguise extends CommandBase {
    private final ArrayList<String> aliases = Lists.newArrayList(Utils.MODID, "d", "disguise", "dis");

    @Override
    public String getName() {
        return "disguise";
    }

    @Override
    public ArrayList<String> getAliases() {
        return aliases;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "disguise <entitytype>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) return;
        if (!(sender instanceof EntityPlayer)) {
            sender.sendMessage(new TextComponentString("Only players can execute this command!"));
            return;
        }
        EntityPlayer player = (EntityPlayer) sender;
        String s = args[0];
        Disguises.getDisguises().put(player.getUniqueID(), Disguises.createDisguise(player.getUniqueID(), s));
    }
}
