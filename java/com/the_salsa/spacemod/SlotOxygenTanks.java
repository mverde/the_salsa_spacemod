package com.the_salsa.spacemod;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOxygenTanks extends Slot
{
	public SlotOxygenTanks(IInventory inventory, int par2, int par3, int par4)
	{
		super(inventory, par2, par3, par4);
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	/**
	 * Check if stack is valid for this slot (only ItemOxygenTanks allowed)
	 */
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return stack.getItem() instanceof ItemManeuverGear;
	}
}
