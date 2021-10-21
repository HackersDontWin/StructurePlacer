package net.hackersdontwin.structureplacer.obj;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.hackersdontwin.structureplacer.StructurePlacer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Structure {

	private List<StructureBlock> structureBlocks = new ArrayList<>();

	private StructurePlacer plugin;
	private String structureName;

	public Structure(StructurePlacer plugin, String structureName) {
		this.plugin = plugin;
		this.structureName = structureName;

		loadStructureBlocks();
	}

	private void loadStructureBlocks() {
		File structureFile = new File(plugin.getDataFolder(), "structures/" + structureName + ".structure");

		try {
			if(structureFile.exists()) {
				JsonObject structureObj = plugin.getGson().fromJson(new FileReader(structureFile), JsonObject.class);

				JsonArray blocksObj = structureObj.getAsJsonArray("blocks");
				for(JsonElement blockElm : blocksObj) {
					JsonObject blockObj = (JsonObject) blockElm;

					Material material = Material.getMaterial(blockObj.get("type").getAsString());
					int posX = blockObj.get("posX").getAsInt();
					int posY = blockObj.get("posY").getAsInt();
					int posZ = blockObj.get("posZ").getAsInt();

					StructureBlock structureBlock = new StructureBlock(material, posX, posY, posZ);
					structureBlocks.add(structureBlock);
				}

			} else {
				Bukkit.getLogger().severe("ERROR: Could not load structure with name " + structureName + "!");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void showStructure(Player player, Location location, String direction) {
		plugin.getStructureManager().clearPreviousPlacedStructure(player);
		List<Location> structureBlockLocations = new ArrayList<>();

		switch(direction) {
			case "BACKWARDS": {
				for(StructureBlock structureBlock : structureBlocks) {
					Location structureBlockLocation = new Location(Bukkit.getWorld("world"), location.getX() - structureBlock.getPosX(), location.getY() + structureBlock.getPosY(), location.getZ() - structureBlock.getPosZ());
					player.sendBlockChange(structureBlockLocation, structureBlock.getMaterial().createBlockData());
					structureBlockLocations.add(new Location(Bukkit.getWorld("world"), structureBlockLocation.getX(), structureBlockLocation.getY(), structureBlockLocation.getZ()));
				}
			}
			break;

			case "RIGHT": {
				for(StructureBlock structureBlock : structureBlocks) {
					Location structureBlockLocation = new Location(Bukkit.getWorld("world"), location.getX() - structureBlock.getPosZ(), location.getY() + structureBlock.getPosY(), location.getZ() - structureBlock.getPosX());
					player.sendBlockChange(structureBlockLocation, structureBlock.getMaterial().createBlockData());
					structureBlockLocations.add(new Location(Bukkit.getWorld("world"), structureBlockLocation.getX(), structureBlockLocation.getY(), structureBlockLocation.getZ()));
				}
			}
			break;

			case "LEFT": {
				for(StructureBlock structureBlock : structureBlocks) {
					Location structureBlockLocation = new Location(Bukkit.getWorld("world"), location.getX() + structureBlock.getPosZ(), location.getY() + structureBlock.getPosY(), location.getZ() + structureBlock.getPosX());
					player.sendBlockChange(structureBlockLocation, structureBlock.getMaterial().createBlockData());
					structureBlockLocations.add(new Location(Bukkit.getWorld("world"), structureBlockLocation.getX(), structureBlockLocation.getY(), structureBlockLocation.getZ()));
				}
			}
			break;

			default: {
				for(StructureBlock structureBlock : structureBlocks) {
					Location structureBlockLocation = new Location(Bukkit.getWorld("world"), location.getX() + structureBlock.getPosX(), location.getY() + structureBlock.getPosY(), location.getZ() + structureBlock.getPosZ());
					player.sendBlockChange(structureBlockLocation, structureBlock.getMaterial().createBlockData());
					structureBlockLocations.add(new Location(Bukkit.getWorld("world"), structureBlockLocation.getX(), structureBlockLocation.getY(), structureBlockLocation.getZ()));
				}
			}
		}

		plugin.getStructureManager().updatePreviousPlacedStructure(player, structureBlockLocations);
	}

	public String getStructureName() {
		return this.structureName;
	}

}
