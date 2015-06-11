package com.the_salsa.spacemod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeSpace extends BiomeGenBase
{
	public BiomeSpace(int biomeID)
	{
		super(biomeID);
		
		this.spawnableCreatureList.clear();
		this.topBlock = null;
		this.fillerBlock = null;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		
		this.theBiomeDecorator.cactiPerChunk = 0;
		this.theBiomeDecorator.deadBushPerChunk = 0;
		
		this.flowers.clear();
	}
	
	@Override
	public void decorate(World world, Random rand, int par3, int par4) {}
	
	@Override
	public void genTerrainBlocks(World p_150560_1_, Random p_150560_2_, Block[] p_150560_3_, byte[] p_150560_4_, int p_150560_5_, int p_150560_6_, double p_150560_7_)
	{
	        /*boolean flag = true;
	        Block block = this.topBlock;
	        byte b0 = (byte)(this.field_150604_aj & 255);
	        Block block1 = this.fillerBlock;
	        int k = -1;
	        int l = (int)(p_150560_7_ / 3.0D + 3.0D + p_150560_2_.nextDouble() * 0.25D);
	        int i1 = p_150560_5_ & 15;
	        int j1 = p_150560_6_ & 15;
	        int k1 = p_150560_3_.length / 256;

	        for (int l1 = 255; l1 >= 0; --l1)
	        {
	            int i2 = (j1 * 16 + i1) * k1 + l1;

	            if (l1 <= 0 + p_150560_2_.nextInt(5))
	            {
	                p_150560_3_[i2] = Blocks.bedrock;
	            }
	            else
	            {
	                Block block2 = p_150560_3_[i2];

	                if (block2 != null && block2.getMaterial() != Material.air)
	                {
	                    if (block2 == Blocks.stone)
	                    {
	                        if (k == -1)
	                        {
	                            if (l <= 0)
	                            {
	                                block = null;
	                                b0 = 0;
	                                block1 = Blocks.stone;
	                            }
	                            else if (l1 >= 59 && l1 <= 64)
	                            {
	                                block = this.topBlock;
	                                b0 = (byte)(this.field_150604_aj & 255);
	                                block1 = this.fillerBlock;
	                            }

	                            if (l1 < 63 && (block == null || block.getMaterial() == Material.air))
	                            {
	                                if (this.getFloatTemperature(p_150560_5_, l1, p_150560_6_) < 0.15F)
	                                {
	                                    block = Blocks.ice;
	                                    b0 = 0;
	                                }
	                                else
	                                {
	                                    block = Blocks.water;
	                                    b0 = 0;
	                                }
	                            }

	                            k = l;

	                            if (l1 >= 62)
	                            {
	                                p_150560_3_[i2] = block;
	                                p_150560_4_[i2] = b0;
	                            }
	                            else if (l1 < 56 - l)
	                            {
	                                block = null;
	                                block1 = Blocks.stone;
	                                p_150560_3_[i2] = Blocks.gravel;
	                            }
	                            else
	                            {
	                                p_150560_3_[i2] = block1;
	                            }
	                        }
	                        else if (k > 0)
	                        {
	                            --k;
	                            p_150560_3_[i2] = block1;

	                            if (k == 0 && block1 == Blocks.sand)
	                            {
	                                k = p_150560_2_.nextInt(4) + Math.max(0, l1 - 63);
	                                block1 = Blocks.sandstone;
	                            }
	                        }
	                    }
	                }
	                else
	                {
	                    k = -1;
	                }
	            }
	        }*/
		
		for (int i = 0; i < 255; i++)
		{
			p_150560_3_[i] = Blocks.air;
		}
	}
}
