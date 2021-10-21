package net.hackersdontwin.structureplacer.events;

import net.hackersdontwin.structureplacer.StructurePlacer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerLook implements Listener {

	private StructurePlacer plugin;
	private final int DISTANCE = 20;

	public PlayerLook(StructurePlacer plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(player.getTargetBlock(null, DISTANCE) == null || player.getTargetBlock(null, DISTANCE).getType() == Material.AIR) {
			plugin.getStructureManager().clearPreviousPlacedStructure(player);
		}
		if(player.getTargetBlock(null, DISTANCE) != null && player.getTargetBlock(null, DISTANCE) != null && player.getTargetBlock(null, DISTANCE).getType() != Material.AIR) {
			if(player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
				Location lookLocation = player.getTargetBlock(null, DISTANCE).getLocation();

				if(player.getTargetBlock(null, DISTANCE).getType() != Material.GRASS) {
					lookLocation.setY(lookLocation.getY() + 1);
				}

				String orientation = "FORWARDS";
				// When player is looking forwards (Do nothing, this is the same as the .structure file)
				if((player.getLocation().getYaw() >= 135f && player.getLocation().getYaw() <= 180.0f) || (player.getLocation().getYaw() >= -180.0f && player.getLocation().getYaw() <= -135.0f)) {
					orientation = "FORWARDS";
				}

				// When player is looking backwards
				if(player.getLocation().getYaw() >= -45.0f && player.getLocation().getYaw() <= 45.0f) {
					orientation = "BACKWARDS";
				}

				// When player is looking to the right
				if(player.getLocation().getYaw() > 45.0f && player.getLocation().getYaw() < 135.0f) {
					orientation = "RIGHT";
				}

				// When player is looking to the left
				if(player.getLocation().getYaw() > -135.0f && player.getLocation().getYaw() < -45.0f) {
					orientation = "LEFT";
				}

				plugin.getStructureManager().showStructure(player, lookLocation, "mystructure", orientation);
			}
		}
	}

}
