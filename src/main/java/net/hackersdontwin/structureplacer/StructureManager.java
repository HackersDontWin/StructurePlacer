package net.hackersdontwin.structureplacer;

import net.hackersdontwin.structureplacer.obj.Structure;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StructureManager {

	private StructurePlacer plugin;

	private Map<String, Structure> structureMap = new HashMap<>();
	private Map<Player, List<Location>> prevPlacedStructure = new HashMap<>();

	public StructureManager(StructurePlacer plugin) {
		this.plugin = plugin;

		loadStructures();
	}

	private void loadStructures() {
		File structureDirectory = new File(plugin.getDataFolder(), "structures");
		File[] structureFiles = structureDirectory.listFiles();
		if(structureFiles != null) {
			for(File structureFile : structureFiles) {
				Structure structure = new Structure(plugin, structureFile.getName().replace(".structure", ""));
				structureMap.put(structure.getStructureName(), structure);
				Bukkit.getLogger().info("Structure '" + structure.getStructureName() + "' was loaded successfully!");
			}
		}
	}

	public void showStructure(Player player, Location location, String structureName, String direction) {
		if(structureMap.containsKey(structureName)) {
			structureMap.get(structureName).showStructure(player, location, direction);
		} else {
			Bukkit.getLogger().severe("ERROR: Attempted to load structure '" + structureName + "' but it doesn't exist!");
		}
	}

	public Map<Player, List<Location>> getPreviousPlacedStructure() {
		return prevPlacedStructure;
	}

	public void clearPreviousPlacedStructure(Player player) {
		if(prevPlacedStructure.containsKey(player)) {
			List<Location> structureBlockLocations = prevPlacedStructure.get(player);
			for(Location structureBlockLocation : structureBlockLocations) {
				player.sendBlockChange(structureBlockLocation, Material.AIR.createBlockData());
				//Bukkit.getLogger().info(structureBlockLocation.getBlockX() + " " + structureBlockLocation.getBlockY() + " " + structureBlockLocation.getBlockZ());
			}
			prevPlacedStructure.remove(player);
		}
	}

	public void updatePreviousPlacedStructure(Player player, List<Location> structureBlockLocations) {
		prevPlacedStructure.put(player, structureBlockLocations);
		//Bukkit.getLogger().info(structureBlockLocations.toString());
	}

}
