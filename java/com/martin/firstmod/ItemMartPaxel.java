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
public class ItemMartPaxel extends ItemTool
{
	//set of blocks that can be effectively mined
	private static Set blocks = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2,
			Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab,
			Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore,
			Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack,
			Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail,
			Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});
	
	public ItemMartPaxel(ToolMaterial material, String itemName)
	{
		super(3, material, blocks); //par1 = damage to entities
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
	
	@Override
	public boolean func_150897_b(Block block) //allows mining of hard/misc blocks
	{
		return block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : 
			(block != Blocks.diamond_block && block != Blocks.diamond_ore ? 
			(block != Blocks.emerald_ore && block != Blocks.emerald_block ? 
			(block != Blocks.gold_block && block != Blocks.gold_ore ? 
			(block != Blocks.iron_block && block != Blocks.iron_ore ? 
			(block != Blocks.lapis_block && block != Blocks.lapis_ore ? 
			(block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? 
			(block.getMaterial() == Material.rock ? true : 
		    (block.getMaterial() == Material.iron ? true : 
			(block.getMaterial() == Material.anvil))) : 
			this.toolMaterial.getHarvestLevel() >= 2) : 
		    this.toolMaterial.getHarvestLevel() >= 1) : 
		    this.toolMaterial.getHarvestLevel() >= 1) : 
			this.toolMaterial.getHarvestLevel() >= 2) : 
			this.toolMaterial.getHarvestLevel() >= 2) : 
			(this.toolMaterial.getHarvestLevel() >= 2));
	}
	
	@Override
	public float func_150893_a(ItemStack itemStack, Block block) //dunno exactly what this does
	{
		return block.getMaterial() != Material.iron && 
				block.getMaterial() != Material.wood && 
				block.getMaterial() != Material.plants && 
				block.getMaterial() != Material.vine && 
				block.getMaterial() != Material.anvil && 
				block.getMaterial() != Material.rock ? 
				super.func_150893_a(itemStack, block) : this.efficiencyOnProperMaterial;
	}
	
	//cause tool to take 1 damage from attacking instead of 2
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		return true;
	}
}
