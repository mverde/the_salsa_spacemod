package com.martin.firstmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMartArmor extends ItemArmor
{
	public ItemMartArmor(ArmorMaterial material, int armorType, String itemName)
	{
		super(material, 0, armorType); //always 0 as second par for custom armor
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
	}
	
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == FirstMod.martHelmet || stack.getItem() == FirstMod.martChest || stack.getItem() == FirstMod.martBoots)
		{
			return FirstMod.MODID + ":models/armor/martArmor1.png";
		}
		else if(stack.getItem() == FirstMod.martLegs)
		{
			return FirstMod.MODID + ":models/armor/martArmor2.png";
		}
		else
		{
			System.out.println("Invalid Item");
			return null;
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if(itemStack.getItem().getUnlocalizedName().equals("item.martin_firstmod_martHelmet")) //martHelmet allows underwater breathing
		{
			if(player.isInWater())
			{
				player.setAir(20);
			}
		}
	}
}
