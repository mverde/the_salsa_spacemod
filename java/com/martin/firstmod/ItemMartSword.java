package com.martin.firstmod;

import net.minecraft.item.ItemSword;

public class ItemMartSword extends ItemSword
{
	public ItemMartSword(ToolMaterial material, String itemName)
	{
		super(material);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
