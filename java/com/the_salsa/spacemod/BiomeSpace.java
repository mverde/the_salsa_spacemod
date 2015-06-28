package com.the_salsa.spacemod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenDesert;

public class BiomeSpace extends BiomeGenDesert
{
	public BiomeSpace(int biomeID)
	{
		super(biomeID);
		
		this.topBlock = null;
		this.fillerBlock = null;
		this.spawnableMonsterList.clear();
		
		this.theBiomeDecorator.cactiPerChunk = 0;
		this.theBiomeDecorator.deadBushPerChunk = 0;
		this.setDisableRain();
		this.setTemperatureRainfall(0F, 0F);
	}
	
	@Override
	public void decorate(World world, Random rand, int par3, int par4) {}
	
	@Override
	public void genTerrainBlocks(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_)
	{
		for (int i = 0; i < 255; i++)
		{
			p_150560_3_[i] = Blocks.air;
		}
	}
}
