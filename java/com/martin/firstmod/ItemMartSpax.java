package com.martin.firstmod;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.Item.ToolMaterial;

//Custom Tool template
public class ItemMartSpax extends ItemTool
{
	//set of blocks that can be effectively mined
	private static Set blocks = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, 
			Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.grass, Blocks.dirt, Blocks.sand,
			Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});
	
	public ItemMartSpax(ToolMaterial material, String itemName)
	{
		super(3, material, blocks); //par1 = damage to entities
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
	
	@Override
	public boolean func_150897_b(Block block) //allows mining of hard/misc blocks
	{
		 return block == Blocks.snow_layer ? true : block == Blocks.snow;
	}
	
	@Override
	public float func_150893_a(ItemStack itemStack, Block block) //dunno exactly what this does
	{
		return block.getMaterial() != Material.wood && 
				block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? 
				super.func_150893_a(itemStack, block) : this.efficiencyOnProperMaterial;
	}
}
