package me.udeilu.advancedelectricity;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;
import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;

public class InventoryListener implements Listener {
	public InventoryListener(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onInteract(PlayerInteractEvent e) throws InterruptedException {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (SlimefunManager.isItemSimiliar(p.getInventory().getItemInMainHand(), Items.BASIC_EMP, false)) {
				e.setCancelled(true);
				PlayerInventory.consumeItemInHand(p);
				for (Player other : Bukkit.getOnlinePlayers()) {
					if (other.getLocation().distance(p.getLocation()) <= 10) {
						if(SlimefunManager.isItemSimiliar(other.getInventory().getChestplate(), Items.ADVANCED_CHESTPLATE, false)) {
							other.getInventory().getChestplate().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
							Bukkit.getScheduler().runTaskLater (Main.plugin, () -> other.getInventory().getChestplate().removeEnchantment(Enchantment.ARROW_INFINITE), 20);
							return;
						}
						ItemEnergy.chargeInventory(other, Float.valueOf(-10000));
						other.sendTitle("§4EMP", null, 10, 70, 20);
						other.setGlowing(true);
						Bukkit.getScheduler().runTaskLater(Main.plugin, () -> other.setGlowing(false), 40);
					}
				}				
			}
			if (SlimefunManager.isItemSimiliar(p.getInventory().getItemInMainHand(), Items.ADVANCED_EMP, false)) {
				e.setCancelled(true);
				PlayerInventory.consumeItemInHand(p);
				for (Player other : Bukkit.getOnlinePlayers()) {
					if (other.getLocation().distance(p.getLocation()) <= 20) {
						if(SlimefunManager.isItemSimiliar(other.getInventory().getChestplate(), Items.ADVANCED_CHESTPLATE, false)) {
							other.getInventory().getChestplate().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
							Bukkit.getScheduler().runTaskLater (Main.plugin, () -> other.getInventory().getChestplate().removeEnchantment(Enchantment.ARROW_INFINITE), 20);
							return;
						}
						ItemEnergy.chargeInventory(other, Float.valueOf(-10000));
						other.sendTitle("§4EMP", null, 10, 70, 20);
						other.setGlowing(true);
						Bukkit.getScheduler().runTaskLater(Main.plugin, () -> other.setGlowing(false), 40);
					}
				}				
			}
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onPlayerDamage(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player)) {
			Player player = (Player)e.getEntity();
			if (SlimefunManager.isItemSimiliar(player.getEquipment().getChestplate(), Items.ADVANCED_CHESTPLATE, false)) {
				e.setDamage(e.getDamage() / 2);
				player.getInventory().getChestplate().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
				Bukkit.getScheduler().runTaskLater(Main.plugin, () -> player.getInventory().getChestplate().removeEnchantment(Enchantment.ARROW_INFINITE), 40);
			}
		}
	}
	
    @SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        if(SlimefunManager.isItemSimiliar(p.getInventory().getChestplate(), Items.ARCHWING, false)) p.getEquipment().getChestplate().setDurability((short)0);
        if(!SlimefunManager.isItemSimiliar(p.getItemInHand(), Items.ARCHWING_CONTROLLER, false)) return;
        if ((p.isGliding()) && (p.getLocation().getPitch() < 0) && (SlimefunManager.isItemSimiliar(p.getEquipment().getChestplate(), Items.ARCHWING, false))) {
        	p.setVelocity(p.getLocation().getDirection().multiply(Main.plugin.getConfig().getDouble("archwing-base")));
        }
        if((p.isGliding()) && (p.isSneaking())) {
        	p.setVelocity(p.getLocation().getDirection().normalize().multiply(Main.plugin.getConfig().getDouble("archwing-boost")));
        	loc.getWorld().spigot().playEffect(loc, Effect.LAVA_POP, 0, 0, 0.2F, 0.0F, 0.2F, 0.0F, 30, 10);
        }
    }
	
    @SuppressWarnings("deprecation")
    @EventHandler(ignoreCancelled = true)
    public void onToggleSneak(PlayerToggleSneakEvent event) {
        Player p = event.getPlayer();
        if(!SlimefunManager.isItemSimiliar(p.getEquipment().getChestplate(), Items.ARCHWING, false)) return;
        if (event.isSneaking()) {
        	if(Main.plugin.getConfig().getBoolean("archwing-shift-launch") == false) return;
        	if(p.isOnGround()) {
        		if(!SlimefunManager.isItemSimiliar(p.getItemInHand(), Items.ARCHWING_CONTROLLER, false)) return;
        		Location loc = p.getLocation();
                
        		Vector dir = loc.getDirection().add(new Vector(0, 3, 0));
        		p.setVelocity(p.getVelocity().add(dir));
        		loc.getWorld().spigot().playEffect(loc, Effect.DRAGON_BREATH, 0, 0, 0.5F, 0.5F, 0.5F, 0.0F, 30, 30);
        		p.playSound(loc, Sound.ENTITY_ENDERDRAGON_GROWL, 0.5F, 2.0F);
        	}
        }
    }
}
