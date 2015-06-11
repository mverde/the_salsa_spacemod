package com.the_salsa.spacemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemSpaceTeleporter extends Item
{
	public class SpaceTeleporter extends Teleporter
	{
		   private final WorldServer worldServerInstance;

		   public SpaceTeleporter(WorldServer par1WorldServer)
		   {
		      super(par1WorldServer);
		      this.worldServerInstance = par1WorldServer;
		   }

		   @Override
		   public void placeInPortal(Entity pEntity, double p2, double p3, double p4, float p5)
		   {
		       int i = MathHelper.floor_double(pEntity.posX);
		       int j = MathHelper.floor_double(pEntity.posY);
		       int k = MathHelper.floor_double(pEntity.posZ);
		       this.worldServerInstance.getBlock(i, j, k); //dummy load to maybe gen chunk
		       int height = this.worldServerInstance.getHeightValue(i, k);
		       pEntity.setPosition( i, height, k );
		       return;
		   }  // dont do ANY portal junk, just grab a dummy block then SHOVE the player setPosition() at height
	}
	
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
			
			if (playerMP.dimension != 2)
			{
				SpaceTeleporter teleporter = new SpaceTeleporter(playerMP.mcServer.worldServerForDimension(2));
				
				playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 2, teleporter);

				// get the height value so you don't get stuck in solid blocks or worse, in the void
				double dy = playerMP.worldObj.getHeightValue(
				MathHelper.floor_double(playerMP.posX), MathHelper.floor_double(playerMP.posZ));

				// still seem to need to set the position, +1 so you don't get in the void
				player.setPositionAndUpdate(playerMP.posX, dy + 1, playerMP.posZ);
			}
			else if (playerMP.dimension == 2)
			{
				SpaceTeleporter teleporter = new SpaceTeleporter(playerMP.mcServer.worldServerForDimension(0));
				
				playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, teleporter);

				// get the height value so you don't get stuck in solid blocks or worse, in the void
				double dy = playerMP.worldObj.getHeightValue(
				MathHelper.floor_double(playerMP.posX), MathHelper.floor_double(playerMP.posZ));

				// still seem to need to set the position, +1 so you don't get in the void
				player.setPositionAndUpdate(playerMP.posX, dy + 1, playerMP.posZ);
			}
		}
		
		return itemStack;
	}
}
