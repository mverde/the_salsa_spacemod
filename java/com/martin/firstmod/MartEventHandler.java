package com.martin.firstmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class MartEventHandler implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
	IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.dimensionId)
		{
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
	}

	private void generateEnd(World world, Random random, int i, int j) {}

	private void generateSurface(World world, Random random, int x, int z)
	{
		addOreSpawn(FirstMod.martStone, 0, Blocks.stone, world, random, x, z, 16, 16, 5 + random.nextInt(5), 4, 20, 60);
		
		//for loop to make more common
		for (int i = 0; i < 3; i++)
		{
			int posX = x + random.nextInt(16);
			int posY = 50 + random.nextInt(35);
			int posZ = z + random.nextInt(16);
			new WorldGenMartPlant().generate(world, random, posX, posY, posZ);
		}
		
		int posX = x + random.nextInt(16);
		int posY = 10 + random.nextInt(40);
		int posZ = z + random.nextInt(16);
		new WorldGenMartDungeon().generate(world, random, posX, posY, posZ);
	}

	private void generateNether(World world, Random random, int x, int z)
	{
		addOreSpawn(FirstMod.martStone, 0, Blocks.netherrack, world, random, x, z, 16, 16, 5 + random.nextInt(5), 4, 20, 60);
	}
	
	/**
	 * Adds an Ore Spawn to Minecraft.  Simply register all Ores to spawn with this method in your Generation method in your
	 * IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The metadata of the Block
	 * @param The Block where to generate in
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addOreSpawn(Block block, int metadata, Block target, World world, Random random, int blockXPos, int blockZPos, int maxX,
	int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16: "addOreSpawn: The maximum X must be greater than 0 and less than 16";
		assert minY > 0: "addOreSpawn: the minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0: "addOreSpawn: the maxumum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16: "addOreSpawn: the maxumum Z must be greater than 0 and less than 16";
		
		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++)
		{
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(diffBtwnMinMaxY);
			int posZ = blockZPos + random.nextInt(maxZ);
			(new WorldGenMinable(block, metadata, maxVeinSize, target)).generate(world, random, posX, posY, posZ);
		}
	}
}