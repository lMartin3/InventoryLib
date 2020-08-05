package me.rektb.inventorylib.test;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!command.getName().equalsIgnoreCase("invlibtest")) { return false; }
        if(!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        ExampleInventory.getMenu().open(p);
        return false;
    }
}
