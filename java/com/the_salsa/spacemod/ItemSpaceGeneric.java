package com.the_salsa.spacemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSpaceGeneric extends Item 
{
	/**
	 * construct a basic item with no special abilities
	 */
	public ItemSpaceGeneric(String itemName, int maxStack)
	{
		setUnlocalizedName(SpaceMod.MODID + "_" + itemName);
		setTextureName(SpaceMod.MODID + ":" + itemName);
		setCreativeTab(CreativeTabs.tabMaterials);
		setMaxStackSize(maxStack);
	}
}
