package com.martin.firstmod;

import net.minecraft.item.ItemAxe;

public class ItemMartAxe extends ItemAxe
{
	public ItemMartAxe(ToolMaterial material, String itemName)
	{
		super(material);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
