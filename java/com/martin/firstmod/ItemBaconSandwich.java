package com.martin.firstmod;

import net.minecraft.item.ItemFood;

public class ItemBaconSandwich extends ItemFood
{
	public ItemBaconSandwich(int food, float saturation, boolean wolfFood, String itemName)
	{
		super(food, saturation, wolfFood);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
}
