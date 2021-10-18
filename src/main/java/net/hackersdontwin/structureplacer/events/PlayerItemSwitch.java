package net.hackersdontwin.structureplacer.events;

import net.hackersdontwin.structureplacer.StructurePlacer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemSwitch implements Listener {

	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		if(player.getInventory().getItem(event.getNewSlot()) != null && player.getInventory().getItem(event.getNewSlot()).getType() != Material.NETHER_STAR) {
			if(StructurePlacer.playerBlocks.containsKey(player)) {
				player.sendBlockChange(StructurePlacer.playerBlocks.get(player), Material.AIR.createBlockData());
				StructurePlacer.playerBlocks.remove(player);
			}
		}

		if(player.getInventory().getItem(event.getNewSlot()) != null && player.getInventory().getItem(event.getNewSlot()).getType() == Material.NETHER_STAR) {
			StructurePlacer.placeGhostBlock(player);
		}
	}

}
