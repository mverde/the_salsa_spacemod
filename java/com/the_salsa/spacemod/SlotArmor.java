package com.the_salsa.spacemod;

import javax.swing.Icon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SlotArmor extends Slot
{
	final int armorType;
	final EntityPlayer player;
	
	public SlotArmor(EntityPlayer player, IInventory inventory, int par3, int par4, int par5, int par6)
	{
		super(inventory, par3, par4, par5);
		this.player = player;
		this.armorType = par6;
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
	
	/**
	 * Check if item is valid for this slot 
	 */
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		Item item = (stack == null ? null : stack.getItem());
		return item != null && item.isValidArmor(stack, armorType, player);
	}
	
	/**
	 * Gets the icon index on items.png that is used as the background of the slot
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getBackgroundIconIndex()
	{
		return ItemArmor.func_94602_b(this.armorType);
	}
}
