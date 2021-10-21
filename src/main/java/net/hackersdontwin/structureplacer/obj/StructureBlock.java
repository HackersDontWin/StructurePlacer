package net.hackersdontwin.structureplacer.obj;

import org.bukkit.Material;

public class StructureBlock {

	private Material material;
	private int posX;
	private int posY;
	private int posZ;
	private String orientation;

	public StructureBlock(Material material, int posX, int posY, int posZ) {
		this.material = material;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.orientation = "";
	}

	public StructureBlock(Material material, int posX, int posY, int posZ, String orientation) {
		this.material = material;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.orientation = orientation;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getPosZ() {
		return posZ;
	}

	public Material getMaterial() {
		return material;
	}

}
