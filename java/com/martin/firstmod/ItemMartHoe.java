package com.martin.firstmod;

import net.minecraft.item.ItemHoe;

public class ItemMartHoe extends ItemHoe
{
	public ItemMartHoe(ToolMaterial material, String itemName)
	{
		super(material);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
