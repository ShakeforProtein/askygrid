package com.wasteofplastic.askygrid.generators;

import java.util.Random;
import java.util.TreeMap;

import org.bukkit.Material;

public class BlockProbability {
	
	/**
	 * Tree map of all materials and their probabilities as a ratio to the sum of all probabilities
	 */
	TreeMap<Integer, Material> probMap = new TreeMap<Integer, Material>();
	/**
	 * Sum of all probabilities
	 */
	int total = 0;
	
	
	/**
	 * Adds a material and associated probability
	 * @param material
	 * @param prob
	 */
	public void addBlock(Material material, int prob) {
		probMap.put(total, material);
		total += prob;
	}
	
	/**
	 * This picks a random block with the following constraints:
	 * A cactus is never chosen as the bottom block.
	 * Water or lava never is placed above sugar cane or cactuses because when they grow, they will touch the
	 * liquid and cause it to flow.
	 * @param random - random object
	 * @param bottom - if true, result will never be CACTUS
	 * @param noLiquid - if true, result will never be water or lava
	 * @return Material selected
	 */
	
	public Material getBlock(Random random, boolean bottom, boolean noLiquid) {
		Material temp = probMap.floorEntry(random.nextInt(total)).getValue();
		if (bottom && temp.equals(Material.CACTUS)) {
			return getBlock(random, bottom, noLiquid);
		} else if (noLiquid && (temp.equals(Material.STATIONARY_WATER) || temp.equals(Material.STATIONARY_LAVA))) {
			return getBlock(random, bottom, noLiquid);
		}
		return temp;
	}
	
}