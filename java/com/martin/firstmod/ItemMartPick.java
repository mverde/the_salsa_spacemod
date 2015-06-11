package com.martin.firstmod;

import net.minecraft.item.ItemPickaxe;

public class ItemMartPick extends ItemPickaxe 
{
	public ItemMartPick(ToolMaterial material, String itemName)
	{
		super(material);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
