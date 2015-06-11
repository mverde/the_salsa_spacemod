package com.martin.firstmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMartThrow extends Item
{
	public ItemMartThrow(String name)
	{
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName(FirstMod.MODID + "_" + name);
		setTextureName(FirstMod.MODID + ":" + name);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (!par3EntityPlayer.capabilities.isCreativeMode)
		{
			par1ItemStack.stackSize--;
		}
		
		if (!par2World.isRemote)
		{
			par2World.spawnEntityInWorld(new EntityMartThrowable(par2World, par3EntityPlayer));
		}
		
		return par1ItemStack;
	}
}
