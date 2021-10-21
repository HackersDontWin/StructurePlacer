package net.hackersdontwin.structureplacer.events;

import net.hackersdontwin.structureplacer.StructurePlacer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemSwitch implements Listener {

	private StructurePlacer plugin;

	public PlayerItemSwitch(StructurePlacer plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		if(player.getInventory().getItem(event.getNewSlot()) != null && player.getInventory().getItem(event.getNewSlot()).getType() != Material.NETHER_STAR) {

			plugin.getStructureManager().clearPreviousPlacedStructure(player);
		}

		if(player.getInventory().getItem(event.getNewSlot()) != null && player.getInventory().getItem(event.getNewSlot()).getType() == Material.NETHER_STAR) {
			//StructurePlacer.placeGhostBlock(player);
		}
	}

}
