package com.the_salsa.spacemod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockSpaceRock extends Block
{
	private static String name = "spaceRock";
	
	protected BlockSpaceRock(Material p_i45394_1_)
	{
		super(p_i45394_1_);
	}
	
	public BlockSpaceRock()
	{
		super(Material.rock);
		setBlockName(SpaceMod.MODID + "_" + name);
		setBlockTextureName(SpaceMod.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3.0F);
		setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 3);
	}

}
