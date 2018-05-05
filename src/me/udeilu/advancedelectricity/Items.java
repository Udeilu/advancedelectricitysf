package me.udeilu.advancedelectricity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

public class Items {
	public static ItemStack BASIC_EMP = null;
	public static ItemStack ADVANCED_EMP = null;
	public static ItemStack EXPLOSIVE_SHOVEL = null;
	public static ItemStack ADVANCED_HELMET = null;
	public static ItemStack ADVANCED_CHESTPLATE = null;
	public static ItemStack ARCHWING = null;
	public static ItemStack ARCHWING_CONTROLLER = null;
			
	static {
		try {
			BASIC_EMP = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&aBasic Electromagnetic Pulse", new String[] {"", "&fA short burst of electromagnetic energy", "", "&3Range: &f10 blocks"});
			ADVANCED_EMP = new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJhOGViYzRjNmE4MTczMDk0NzQ5OWJmN2UxZDVlNzNmZWQ2YzFiYjJjMDUxZTk2ZDM1ZWIxNmQyNDYxMGU3In19fQ=="), "&cAdvanced Electromagnetic Pulse", new String[] {"", "&fA short burst of electromagnetic energy", "", "&3Range: &f20 blocks"});
			EXPLOSIVE_SHOVEL = new CustomItem(new ItemStack(Material.DIAMOND_SPADE), "&eExplosive Shovel", new String[] {"", "&fAllows you to dig a good bit", "&fof Blocks at once...", "", "&9Works with Fortune"});
			ADVANCED_HELMET = new CustomItem(new ItemStack(Material.IRON_HELMET), "&9Advanced Helmet", new String[] {"", "&fGives you night vision."});
			ADVANCED_CHESTPLATE = new CustomItem(new ItemStack(Material.IRON_CHESTPLATE), "&9Advanced Chestplate", new String[] {"", "&fPrevents you from  being affected", "&fby an electromagnetic pulse."});
			ARCHWING = new CustomItem(new ItemStack(Material.ELYTRA), "&9Archwing", new String[] {"", "&fThe &9Archwing &fis an all-environment", "&fflight system.", "", "&9Requires &9Archwing &bController &9in main hand"});
			ARCHWING_CONTROLLER = new CustomItem(new ItemStack(Material.FEATHER), "&9Archwing &bController", new String[] {"", "&fThe &9Archwing &bController &fis an", "&fitem that enables the deployment", "&fof &9Archwings&f."});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
