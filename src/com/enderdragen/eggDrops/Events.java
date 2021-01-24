package com.enderdragen.eggDrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

@SuppressWarnings("deprecation")
public class Events implements Listener {
  Plugin plugin = Main.instance;
  
  public static ItemStack eggDrop() {
    ItemStack is = new ItemStack(Material.EGG);
    ItemMeta ismet = is.getItemMeta();
    ismet.setDisplayName("6d86hK0U54V1pfR372un23r17hoD6b2A");
    is.setItemMeta(ismet);
    return is;
  }
  
  @EventHandler
  public void onEgg(PlayerEggThrowEvent e) {
    BukkitScheduler scheduler = this.plugin.getServer().getScheduler();
    Player player = e.getPlayer();
    Egg egg = e.getEgg();
    final World w = egg.getWorld();
    Location l = egg.getLocation();
    l.getX();
    l.getY();
    l.getZ();
    
    if (player.hasPermission("eggDrop.use")) {
      final Item bomb = w.dropItem(l.add(new Vector(0.0D, 0.0D, 0.0D)), eggDrop());
      if (this.plugin.getConfig().getBoolean("messages.enable"))
        Main.sm(player, this.plugin.getConfig().getString("messages.throw")); 
      scheduler.scheduleSyncDelayedTask(this.plugin, new Runnable() {
            public void run() {
            	 player.getWorld().dropItemNaturally(player.getLocation(), new ItemStack(getRandomItem()));
            	
              bomb.remove();
            }
          }, 20L);
      
    }
   
  }




static ItemStack getRandomItem() {
  Material[] items = Material.values();
  int randomInt = (int)(Math.random() * items.length);
  ItemStack item = new ItemStack(items[randomInt]);
  return item;
}

  
  @EventHandler
  public void onChickSpawn(CreatureSpawnEvent e) {
    LivingEntity livingEntity = e.getEntity();
    if (livingEntity.getType() == EntityType.CHICKEN) {
      e.setCancelled(this.plugin.getConfig().getBoolean("disable-chicken")); 
  } 
}
  
  @EventHandler
  public void onPick(PlayerPickupItemEvent e) {
    if (e.getItem().getItemStack().isSimilar(eggDrop()))
      e.setCancelled(true); 
  }
}
