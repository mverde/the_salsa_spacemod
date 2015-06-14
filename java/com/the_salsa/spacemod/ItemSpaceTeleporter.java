package com.the_salsa.spacemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemSpaceTeleporter extends Item
{	
	public ItemSpaceTeleporter(String itemName)
	{
		setUnlocalizedName(SpaceMod.MODID + "_" + itemName);
		setTextureName(SpaceMod.MODID + ":" + itemName);
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	/**
	 * cause the player to teleport to space or back, with fancy fenagling so a portal isn't created
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		if (player != null && !player.worldObj.isRemote)
		{
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			
			SpaceTeleporter teleporter;
			
			if (player.dimension != 2)
			{
				teleporter = new SpaceTeleporter(playerMP.mcServer.worldServerForDimension(2));
				teleporter.teleport(playerMP, 2);
				player.setPositionAndUpdate(player.posX, player.posY + 100, player.posZ);
			}
			else if (player.dimension == 2)
			{
				teleporter = new SpaceTeleporter(playerMP.mcServer.worldServerForDimension(0));
				teleporter.teleport(playerMP, 0);
			}
		}
		
		return itemStack;
	}
}
