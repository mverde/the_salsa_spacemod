package com.martin.firstmod;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMartPlant extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		for (int l = 0; l < 64; l++)
		{
			int xl = x + rand.nextInt(8) - rand.nextInt(8);
			int yl = y + rand.nextInt(4) - rand.nextInt(4);
			int zl = y + rand.nextInt(8) - rand.nextInt(8);
			
			if (FirstMod.martPlant.canPlaceBlockAt(world, xl, yl, zl) && world.getBlock(xl, yl - 1, zl) == Blocks.grass)
			{
				world.setBlock(xl, yl, zl, FirstMod.martPlant, rand.nextInt(3), 2);	//spawn plant with random metadata
			}
		}
		
		return true;
	}
	
}
