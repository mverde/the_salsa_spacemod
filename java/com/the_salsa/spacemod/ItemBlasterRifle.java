package com.the_salsa.spacemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemBlasterRifle extends ItemGunGeneric
{
	private static double spreadModifier = 0.03D;
	
	/**
	 * max damage - 2 = ammo capacity
	 */
	public ItemBlasterRifle(String name, double boltSpeed, double range, float damage, int fireTicksMax, int reloadTicksMax, int ammoCapacity)
	{
		super(name, boltSpeed, range, damage, fireTicksMax, reloadTicksMax, ammoCapacity);
	}
	
	/**
	 * shoots a BlasterBolt
	 */
	@Override
	public void fire(World world, EntityPlayer player)
	{
		EntityBlasterBolt bolt = new EntityBlasterBolt(world, player, boltSpeed, range, damage);

        if (!world.isRemote)
        {
            world.playSoundAtEntity(player, SpaceMod.MODID + ":" + "blaster.rifle", 0.75F, 1.0F);
            
            double xRand = spreadModifier * rand.nextInt(3);
            double yRand = spreadModifier * rand.nextInt(3);
            double zRand = spreadModifier * rand.nextInt(3);
            
            if (rand.nextInt(2) == 0)
            {
            	bolt.motionX += 0.0001 * xRand;
            }
            else
            {
            	bolt.motionX -= 0.0001 * xRand;
            }
            
            if (rand.nextInt(2) == 0)
            {
            	bolt.motionY += yRand;
            }
            else
            {
            	bolt.motionY -= yRand;
            }
            
            if (rand.nextInt(2) == 0)
            {
            	bolt.motionZ += zRand;
            }
            else
            {
            	bolt.motionZ -= zRand;
            }
            
            world.spawnEntityInWorld(bolt);
        }
	}
}