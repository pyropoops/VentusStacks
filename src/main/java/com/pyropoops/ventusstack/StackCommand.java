package com.pyropoops.ventusstack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StackCommand implements CommandExecutor {

    private String c(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("ventusstacks.stack")) {
            sender.sendMessage(c("&cYou do not have permission to do that!"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(c("&cYou must be a player to do this!"));
            return true;
        }
        Player player = (Player) sender;
        ItemStack hand = player.getItemInHand();
        if (hand == null || hand.getType() == Material.AIR) {
            sender.sendMessage(c("&cYou must be holding something to do that!"));
            return true;
        }
        int count = 0;
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            ItemStack itemStack = player.getInventory().getContents()[i];
            if (itemStack == null) continue;
            if (itemStack.isSimilar(hand)) {
                count += itemStack.getAmount();
                itemStack.setAmount(0);
                player.getInventory().setItem(i, itemStack);
            }
        }
        hand.setAmount(count);
        player.getInventory().setItemInHand(hand);
        player.sendMessage(c("&aSuccessfully stacked the item in your hand!"));
        return true;
    }
}
