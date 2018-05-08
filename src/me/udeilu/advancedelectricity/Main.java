package me.udeilu.advancedelectricity;

import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.HandledBlock;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.Slimefun;
import me.mrCookieSlime.Slimefun.api.energy.ItemEnergy;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;

public class Main extends JavaPlugin {	
	public static Main plugin;
	FileConfiguration config = getConfig();
	public void onEnable() {
		plugin = this;
		if(getServer().getPluginManager().isPluginEnabled("Slimefun")) {
			System.out.println("[" + getName() + "] " + getName() + " v" + getDescription().getVersion() + " has been enabled!");
		} else {
			System.err.println("[" + getName() + "] Slimefun not found!");
			System.err.println("Please install Slimefun");
			System.err.println("Without it, this Plugin will not work");
			System.err.println("You can download it here:");
			System.err.println("http://dev.bukkit.org/bukkit-plugins/slimefun");
			getServer().getPluginManager().disablePlugin(this);
			return;	
		}
		
		plugin.saveDefaultConfig();

		
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			@Override
			public void run() {
				for (Player p: Bukkit.getOnlinePlayers()) {
					for(ItemStack armor: p.getInventory().getArmorContents()) {
						if(SlimefunManager.isItemSimiliar(armor, Items.ADVANCED_HELMET, false)) {
							if(p.getWorld().getTime() < 12300 || p.getWorld().getTime() > 23850) {
								if (p.getEyeLocation().getBlock().getLightFromSky() == 15) {
									ItemEnergy.chargeInventory(p, Float.valueOf((float) 1.5));
								}
							}
							if(p.getWorld().getTime() > 12300 || p.getWorld().getTime() < 23850) {
								Bukkit.getScheduler().runTaskLater(Main.plugin, () -> p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 2), true), 0);
							}
						}
					}
				}
			}
			
		}, 0L, 100L);
		
		Category AE = new Category(new CustomItem(Items.BASIC_EMP, "&aAdvanced &eElectricity", new String[] {"", "&a> Click to open"}));
		new SlimefunItem(AE, Items.BASIC_EMP, "BASIC_EMP", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.LEAD_INGOT, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.TINY_URANIUM, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.BATTERY, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BATTERY}).register();
		new SlimefunItem(AE, Items.ADVANCED_EMP, "ADVANCED_EMP", RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] {SlimefunItems.LEAD_INGOT, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.LEAD_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.TINY_URANIUM, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.BATTERY, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.BATTERY}).register();
		new SlimefunItem(AE, Items.ADVANCED_HELMET, "ADVANCED_HELMET", RecipeType.ARMOR_FORGE, new ItemStack[] {SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.SOLAR_GENERATOR, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.THIN_GLASS), SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.SMALL_CAPACITOR, null, SlimefunItems.SMALL_CAPACITOR}).register();
		new SlimefunItem(AE, Items.ADVANCED_CHESTPLATE, "ADVANCED_CHESTPLATE", RecipeType.ARMOR_FORGE, new ItemStack[] {SlimefunItems.DAMASCUS_STEEL_INGOT, null, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.CARBONADO, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.DAMASCUS_STEEL_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.DAMASCUS_STEEL_INGOT}).register();
		new SlimefunItem(Categories.MAGIC, Items.ARCHWING, "ARCHWING", RecipeType.MAGIC_WORKBENCH, new ItemStack[] {SlimefunItems.ELYTRA_SCALE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELYTRA_SCALE, SlimefunItems.RUNE_AIR, SlimefunItems.INFUSED_ELYTRA, SlimefunItems.RUNE_RAINBOW, SlimefunItems.ELYTRA_SCALE, SlimefunItems.FLASK_OF_KNOWLEDGE, SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.FLASK_OF_KNOWLEDGE}).register();
		new SlimefunItem(Categories.MAGIC, Items.ARCHWING_CONTROLLER, "ARCHWING_CONTROLLER", RecipeType.MAGIC_WORKBENCH, new ItemStack[] {null, null, null, null, SlimefunItems.ELYTRA_SCALE, null, null, null, null}).register();
		new InventoryListener(this);
		new SlimefunItem(Categories.TOOLS, Items.EXPLOSIVE_SHOVEL, "EXPLOSIVE_SHOVEL", RecipeType.MAGIC_WORKBENCH,
		new ItemStack[] {null, SlimefunItems.SYNTHETIC_DIAMOND, null, null, new ItemStack(Material.TNT), null, null, SlimefunItems.FERROSILICON, null},
		new String[] {"unbreakable-blocks"}, new Object[] {Arrays.asList("BEDROCK", "BARRIER", "COMMAND", "COMMAND_CHAIN", "COMMAND_REPEATING")})
		.register(true, new BlockBreakHandler() {
			@SuppressWarnings({ "unchecked" })
			final String[] explosiveblacklist = Slimefun.getItemValue("EXPLOSIVE_SHOVEL", "unbreakable-blocks") != null ? ((List<String>) Slimefun.getItemValue("EXPLOSIVE_SHOVEL", "unbreakable-blocks")).toArray(new String[((List<String>) Slimefun.getItemValue("EXPLOSIVE_SHOVEL", "unbreakable-blocks")).size()]): new String[] {"BEDROCK", "BARRIER", "COMMAND", "COMMAND_CHAIN", "COMMAND_REPEATING"};
			@SuppressWarnings("deprecation")
			@Override
			public boolean onBlockBreak(BlockBreakEvent e, ItemStack item, int fortune, List<ItemStack> drops) {
				if (SlimefunManager.isItemSimiliar(item, Items.EXPLOSIVE_SHOVEL, true)) {
					e.getBlock().getWorld().createExplosion(e.getBlock().getLocation(), 0.0F);
					e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);
					for (int x = -1; x <= 1; x++) {
						for (int y = -1; y <= 1; y++) {
							for (int z = -1; z <= 1; z++) {
								Block b = e.getBlock().getRelative(x, y, z);
								if (b.getType() != Material.AIR && !StringUtils.equals(b.getType().toString(), explosiveblacklist)) {
									if (CSCoreLib.getLib().getProtectionManager().canBuild(e.getPlayer().getUniqueId(), b)) {
										b.getWorld().playEffect(b.getLocation(), Effect.STEP_SOUND, b.getType());
										SlimefunItem sfItem = BlockStorage.check(b);
										boolean allow = true;
										if (sfItem != null && !(sfItem instanceof HandledBlock)) {
											if (SlimefunItem.blockhandler.containsKey(sfItem.getName())) {
												allow = SlimefunItem.blockhandler.get(sfItem.getName()).onBreak(e.getPlayer(), e.getBlock(), sfItem, UnregisterReason.PLAYER_BREAK);
											}
											if (allow) {
												drops.add(BlockStorage.retrieve(e.getBlock()));
											}
										}
										else if (b.getType().equals(Material.SKULL)) {
											b.breakNaturally();
										}
										else if (b.getType().name().endsWith("_SHULKER_BOX")) {
											b.breakNaturally();
										}
										else {
											for (ItemStack drop: b.getDrops()) {
												b.getWorld().dropItemNaturally(b.getLocation(), (b.getType().toString().endsWith("_ORE") && !b.getType().equals(Material.IRON_ORE) && !b.getType().equals(Material.GOLD_ORE)) ? new CustomItem(drop, fortune): drop);
											}
											b.setType(Material.AIR);
										}
									}
								}
							}
						}
					}
					return true;
				}
				else return false;
			}
		});
	}
}
