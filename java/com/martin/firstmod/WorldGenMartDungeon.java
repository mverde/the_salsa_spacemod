package com.martin.firstmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenMartDungeon extends WorldGenerator implements IWorldGenerator
{
	protected Block[] getValidSpawnBlocks() {
		return new Block[] {
		};
	}

	public boolean locationIsValidSpawn(World world, int i, int j, int k){
		int distanceToAir = 0;
		Block check = world.getBlock(i, j, k);

		while (check != Blocks.air){
			if (distanceToAir > 3){
				return false;
			}

			distanceToAir++;
			check = world.getBlock(i, j + distanceToAir, k);
		}

		j += distanceToAir - 1;

		Block block = world.getBlock(i, j, k);
		Block blockAbove = world.getBlock(i, j+1, k);
		Block blockBelow = world.getBlock(i, j-1, k);
		
		for (Block x : getValidSpawnBlocks()){
			if (blockAbove != Blocks.air){
				return false;
			}
			if (block == x){
				return true;
			}else if (block == Blocks.grass && blockBelow == x){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) { }

	public boolean generate(World world, Random rand, int i, int j, int k) {
		//check that each corner is one of the valid spawn blocks
		if(!locationIsValidSpawn(world, i, j, k) || !locationIsValidSpawn(world, i + 7, j, k) || !locationIsValidSpawn(world, i + 7, j, k + 10) || !locationIsValidSpawn(world, i, j, k + 10))
		{
			return false;
		}

		k = k - 10;
		i = i - 10;

		world.setBlock(i + 0, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 0, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 1, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 2, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 3, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 4, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 5, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 6, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 7, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 8, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 9, Blocks.iron_block);
		world.setBlock(i + 0, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 1, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 2, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 3, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 4, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 5, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 6, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 7, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 8, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 9, Blocks.iron_block);
		world.setBlock(i + 0, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 1, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 2, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 3, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 4, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 5, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 6, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 7, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 8, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 9, Blocks.iron_block);
		world.setBlock(i + 0, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 1, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 2, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 3, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 4, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 5, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 6, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 7, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 8, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 9, Blocks.iron_block);
		world.setBlock(i + 0, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 0, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 1, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 1, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 1, j + 1, k + 2, Blocks.iron_block);
		world.setBlock(i + 1, j + 1, k + 3, Blocks.iron_block);
		world.setBlock(i + 1, j + 1, k + 7, Blocks.iron_block);
		world.setBlock(i + 1, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 1, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 1, j + 2, k + 3, Blocks.iron_block);
		world.setBlock(i + 1, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 1, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 1, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 1, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 1, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 1, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 2, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 2, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 2, j + 1, k + 6, Blocks.iron_block);
		world.setBlock(i + 2, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 2, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 2, j + 2, k + 6, Blocks.iron_block);
		world.setBlock(i + 2, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 2, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 2, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 2, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 2, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 2, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 3, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 3, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 3, j + 1, k + 4, Blocks.iron_block);
		world.setBlock(i + 3, j + 1, k + 8, Blocks.iron_block);
		world.setBlock(i + 3, j + 1, k + 9, Blocks.iron_block);
		world.setBlock(i + 3, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 3, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 3, j + 2, k + 9, Blocks.iron_block);
		world.setBlock(i + 3, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 3, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 3, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 3, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 3, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 3, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 4, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 4, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 4, j + 1, k + 3, Blocks.iron_block);
		world.setBlock(i + 4, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 4, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 4, j + 2, k + 3, Blocks.iron_block);
		world.setBlock(i + 4, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 4, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 4, j + 3, k + 3, Blocks.iron_block);
		world.setBlock(i + 4, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 4, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 4, j + 4, k + 5, Blocks.glowstone);
		world.setBlock(i + 4, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 4, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 5, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 5, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 5, j + 1, k + 8, Blocks.iron_block);
		world.setBlock(i + 5, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 5, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 5, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 5, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 5, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 5, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 5, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 5, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 6, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 1, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 2, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 3, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 6, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 7, Blocks.iron_block);
		world.setBlock(i + 6, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 6, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 6, j + 2, k + 7, Blocks.iron_block);
		world.setBlock(i + 6, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 6, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 6, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 6, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 6, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 6, j + 5, k + 10, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 0, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 1, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 2, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 3, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 4, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 5, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 6, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 7, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 8, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 9, Blocks.iron_block);
		world.setBlock(i + 7, j + 0, k + 10, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 0, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 1, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 2, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 3, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 4, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 5, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 6, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 7, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 8, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 9, Blocks.iron_block);
		world.setBlock(i + 7, j + 1, k + 10, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 0, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 1, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 2, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 3, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 4, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 5, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 6, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 7, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 8, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 9, Blocks.iron_block);
		world.setBlock(i + 7, j + 2, k + 10, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 0, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 1, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 2, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 3, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 4, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 5, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 6, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 7, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 8, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 9, Blocks.iron_block);
		world.setBlock(i + 7, j + 3, k + 10, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 0, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 1, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 2, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 3, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 4, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 5, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 6, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 7, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 8, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 9, Blocks.iron_block);
		world.setBlock(i + 7, j + 4, k + 10, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 0, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 1, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 2, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 3, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 4, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 5, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 6, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 7, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 8, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 9, Blocks.iron_block);
		world.setBlock(i + 7, j + 5, k + 10, Blocks.iron_block);
		
		world.setBlock(i + 5, j + 1, k + 1, Blocks.mob_spawner);
		TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(i + 5, j + 1, k + 1);
		
		if (spawner != null)
		{
			spawner.func_145881_a().setEntityName("Skeleton");
		}
		else
		{
			System.err.println("Failed to fetch mob spawner entity at (" + i + ", " + j + ", " + k + ")");
		}

		return true;
	}
}