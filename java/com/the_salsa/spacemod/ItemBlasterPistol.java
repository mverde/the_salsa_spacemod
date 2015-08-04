package com.the_salsa.spacemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemBlasterPistol extends ItemGunGeneric
{	
	/**
	 * max damage - 2 = ammo capacity
	 */
	public ItemBlasterPistol(String name, double boltSpeed, double range, float damage, int fireTicksMax, int reloadTicksMax, int ammoCapacity, int durability)
	{
		super(name, boltSpeed, range, damage, fireTicksMax, reloadTicksMax, ammoCapacity, durability);
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
            world.playSoundAtEntity(player, SpaceMod.MODID + ":" + "blaster.pistol", 0.75F, 1.0F);
            world.spawnEntityInWorld(bolt);
        }
	}
}
