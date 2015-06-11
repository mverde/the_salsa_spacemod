package com.martin.firstmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMartGeneric extends Item 
{
	public ItemMartGeneric(String itemName)
	{
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
		setCreativeTab(CreativeTabs.tabMaterials);
	}
}
