package com.martin.firstmod;

import net.minecraft.item.ItemSpade;

public class ItemMartShovel extends ItemSpade
{
	public ItemMartShovel(ToolMaterial material, String itemName)
	{
		super(material);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
