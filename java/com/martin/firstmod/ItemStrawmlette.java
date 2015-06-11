package com.martin.firstmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class ItemStrawmlette extends ItemFood 
{
	public ItemStrawmlette(int food, float saturation, boolean wolfFood, String itemName)
	{
		super(food, saturation, wolfFood);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
