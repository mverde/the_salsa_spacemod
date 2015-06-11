package com.martin.firstmod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMartStone extends ItemBlock //required for metadata blocks
{
	public ItemMartStone(Block block)
	{
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		String name = "";
		switch(itemStack.getItemDamage())
		{
		case 0:
			name = "ore";
			break;
		case 1:
			name = "wall";
			break;
		case 2:
			name = "block";
			break;
		default:
			System.out.println("Invalid metadata for Block MartStone");
			name = "broken";
			break;
		}
		return getUnlocalizedName() + "." + name;
	}
	
	@Override
	public int getMetadata(int par1)
	{
		return par1;
	}
}
