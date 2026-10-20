package com.enderdevelopment.plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kit") && sender instanceof Player) {
            Player player = (Player) sender;
            openKitMenu(player);
            return true;
        }
        return false;
    }

    private void openKitMenu(Player player) {
        Inventory kitMenu = Bukkit.createInventory(null, 9, "Twilight Kits");

        // Kit 1: Twilight Warrior
        ItemStack kit1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta kit1Meta = kit1.getItemMeta();
        kit1Meta.setDisplayName("Twilight Warrior");
        kit1Meta.setLore(Arrays.asList("Right-click to see items"));
        kit1.setItemMeta(kit1Meta);
        kitMenu.setItem(0, kit1);

        // Kit 2: Twilight Archer
        ItemStack kit2 = new ItemStack(Material.BOW);
        ItemMeta kit2Meta = kit2.getItemMeta();
        kit2Meta.setDisplayName("Twilight Archer");
        kit2Meta.setLore(Arrays.asList("Right-click to see items"));
        kit2.setItemMeta(kit2Meta);
        kitMenu.setItem(1, kit2);

        // Kit 3: Twilight Mage
        ItemStack kit3 = new ItemStack(Material.BLAZE_ROD);
        ItemMeta kit3Meta = kit3.getItemMeta();
        kit3Meta.setDisplayName("Twilight Mage");
        kit3Meta.setLore(Arrays.asList("Right-click to see items"));
        kit3.setItemMeta(kit3Meta);
        kitMenu.setItem(2, kit3);

        // Kit 4: Twilight Tank
        ItemStack kit4 = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta kit4Meta = kit4.getItemMeta();
        kit4Meta.setDisplayName("Twilight Tank");
        kit4Meta.setLore(Arrays.asList("Right-click to see items"));
        kit4.setItemMeta(kit4Meta);
        kitMenu.setItem(3, kit4);

        player.openInventory(kitMenu);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Twilight Kits")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                String kitName = clickedItem.getItemMeta().getDisplayName();
                showKitItems(player, kitName);
            }
        }
    }

    private void showKitItems(Player player, String kitName) {
        Inventory kitItems = Bukkit.createInventory(null, 9, kitName + " Items");

        switch (kitName) {
            case "Twilight Warrior":
                kitItems.addItem(new ItemStack(Material.DIAMOND_SWORD));
                kitItems.addItem(new ItemStack(Material.DIAMOND_HELMET));
                kitItems.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
                kitItems.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
                kitItems.addItem(new ItemStack(Material.DIAMOND_BOOTS));
                break;
            case "Twilight Archer":
                kitItems.addItem(new ItemStack(Material.BOW));
                kitItems.addItem(new ItemStack(Material.ARROW, 64));
                kitItems.addItem(new ItemStack(Material.LEATHER_HELMET));
                kitItems.addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
                kitItems.addItem(new ItemStack(Material.LEATHER_LEGGINGS));
                kitItems.addItem(new ItemStack(Material.LEATHER_BOOTS));
                break;
            case "Twilight Mage":
                kitItems.addItem(new ItemStack(Material.BLAZE_ROD));
                kitItems.addItem(new ItemStack(Material.POTION, 1, (short) 16421));
                kitItems.addItem(new ItemStack(Material.POTION, 1, (short) 16420));
                kitItems.addItem(new ItemStack(Material.POTION, 1, (short) 16428));
                kitItems.addItem(new ItemStack(Material.LEATHER_HELMET));
                kitItems.addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
                kitItems.addItem(new ItemStack(Material.LEATHER_LEGGINGS));
                kitItems.addItem(new ItemStack(Material.LEATHER_BOOTS));
                break;
            case "Twilight Tank":
                kitItems.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
                kitItems.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
                kitItems.addItem(new ItemStack(Material.DIAMOND_BOOTS));
                kitItems.addItem(new ItemStack(Material.SHIELD));
                kitItems.addItem(new ItemStack(Material.GOLDEN_APPLE, 5));
                break;
        }

        player.openInventory(kitItems);
    }
}