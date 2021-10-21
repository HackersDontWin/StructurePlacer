package net.hackersdontwin.structureplacer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.hackersdontwin.structureplacer.events.PlayerItemSwitch;
import net.hackersdontwin.structureplacer.events.PlayerLook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class StructurePlacer extends JavaPlugin {

	private Gson gson;
	private StructureManager structureManager;

	@Override
	public void onEnable() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.structureManager = new StructureManager(this);

		Bukkit.getPluginManager().registerEvents(new PlayerLook(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerItemSwitch(this), this);
	}

	public Gson getGson() {
		return gson;
	}

	public StructureManager getStructureManager() {
		return structureManager;
	}
}
