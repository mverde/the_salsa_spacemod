package com.the_salsa.spacemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class SpaceTeleporter extends Teleporter
{
	   private final WorldServer worldServerInstance;

	   public SpaceTeleporter(WorldServer par1WorldServer)
	   {
	      super(par1WorldServer);
	      this.worldServerInstance = par1WorldServer;
	   }
	   
	   public void teleport(EntityPlayerMP player, int dimension)
	   {
			player.mcServer.getConfigurationManager().transferPlayerToDimension(player, dimension, this);

			// get the height value so you don't get stuck in solid blocks or worse, in the void
			double dy = player.worldObj.getHeightValue(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posZ));
			
			player.setPositionAndUpdate(player.posX, dy + 1.0D, player.posZ);
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
