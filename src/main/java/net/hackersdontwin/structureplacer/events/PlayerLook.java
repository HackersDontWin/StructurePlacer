package net.hackersdontwin.structureplacer.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import net.hackersdontwin.structureplacer.StructurePlacer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerLook implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(StructurePlacer.playerBlocks.containsKey(player) && (player.getLocation().distance(StructurePlacer.playerBlocks.get(player)) > 5 || player.getTargetBlockExact(5) == null || player.getTargetBlockExact(5).getType() == Material.AIR)) {
			player.sendBlockChange(StructurePlacer.playerBlocks.get(player), Material.AIR.createBlockData());
		}
		if(player.getTargetBlockExact(5) != null && player.getTargetBlockExact(5).getType() != null && player.getTargetBlockExact(5).getType() != Material.AIR) {
			if(player.getInventory().getItemInMainHand().getType() == Material.NETHER_STAR) {
				if(StructurePlacer.playerBlocks.containsKey(player)) {
					player.sendBlockChange(StructurePlacer.playerBlocks.get(player), Material.AIR.createBlockData());
				}
				StructurePlacer.placeGhostBlock(player);
			}
		}
	}

}
