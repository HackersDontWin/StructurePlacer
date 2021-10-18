package net.hackersdontwin.structureplacer;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.hackersdontwin.structureplacer.events.PlayerItemSwitch;
import net.hackersdontwin.structureplacer.events.PlayerLook;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class StructurePlacer extends JavaPlugin {

	private ProtocolManager protocolManager;
	public static Map<Player, Location> playerBlocks = new HashMap<>();

	@Override
	public void onEnable() {
		protocolManager = ProtocolLibrary.getProtocolManager();

		Bukkit.getPluginManager().registerEvents(new PlayerLook(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerItemSwitch(), this);
	}

	public static void placeGhostBlock(Player player) {
		Location location = player.getTargetBlockExact(5).getLocation();
		if(player.getTargetBlockExact(5).getType() != Material.GRASS) {
			location.setY(location.getY() + 1);
		}
		player.sendBlockChange(location, Material.STONE.createBlockData());
		StructurePlacer.playerBlocks.put(player, location);
	}
}
