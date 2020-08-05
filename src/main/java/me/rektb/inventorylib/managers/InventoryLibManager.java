package me.rektb.inventorylib.managers;

import me.rektb.inventorylib.InventoryLib;
import me.rektb.inventorylib.inv.Menu;
import me.rektb.inventorylib.test.TestCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryLibManager implements Listener {
    private static InventoryLibManager instance;
    private JavaPlugin plugin;
    private ArrayList<Menu> menuList = new ArrayList<Menu>();
    private int task;

    public InventoryLibManager() {
    }

    public void init(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        if(plugin instanceof InventoryLib) {
            plugin.getCommand("invlibtest").setExecutor(new TestCommandExecutor());
        }
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            for(Menu m : menuList) {
                if(m.getInventory().getViewers().size()<1) continue; //todo close
                m.getProvider().update((Player) m.getInventory().getViewers().get(0), m.getContents());
            }
        }, 0, 1);
    }

    public void stop() {
        List<Inventory> toClose = menuList.stream().map(Menu::getInventory).collect(Collectors.toList());
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(toClose.contains(p.getOpenInventory().getTopInventory())) {
                p.closeInventory();
            }
        }
        clearMenuList();
        Bukkit.getScheduler().cancelTask(task);
    }

    public void addMenu(Menu m) {
        menuList.add(m);
    }

    public void removeMenu(Menu m) {
        menuList.remove(m);
    }

    public void clearMenuList() {
        menuList.clear();
    }



    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory clicked = e.getClickedInventory();
        Inventory inventory = e.getInventory();
        if(inventory==null) {
            return;
        }
        Menu target = null;
        for(Menu m : menuList) {
            if(m.getInventory().equals(inventory)) { target = m; }
        }
        if(target==null) return;
        if(clicked!=null&&!clicked.equals(inventory)) {
            if(!e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                return;
            }

        }

        e.setCancelled(true);
        target.click(e);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inventory = e.getInventory();
        if(inventory==null) {
            return;
        }
        Menu target = null;
        for(Menu m : menuList) {
            if(m.getInventory().equals(inventory)) { target = m; }
        }
        if(target==null) return;
        e.setCancelled(true);
    }


    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Inventory clicked = e.getInventory();
        if(clicked==null) return;
        Menu target = null;
        for(Menu m : menuList) {
            if(m.getInventory().equals(clicked)) { target = m; }
        }
        if(target==null) return;
        target.close((Player) e.getPlayer());
        menuList.remove(target);
    }

    public static InventoryLibManager getInstance() {
        if(instance==null) {
            instance = new InventoryLibManager();
        }
        return instance;
    }

    public List<Inventory> getInventories() {
        return menuList.stream().map(Menu::getInventory).collect(Collectors.toList());
    }

}
