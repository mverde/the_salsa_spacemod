package com.the_salsa.spacemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemRocketLauncher extends ItemGunGeneric
{
	public ItemRocketLauncher(String name, double boltSpeed, double range, float damage, int fireTicksMax, int reloadTicksMax, int ammoCapacity, int durability)
	{
		super(name, boltSpeed, range, damage, fireTicksMax, reloadTicksMax, ammoCapacity, durability);
	}

	/**
	 * shoots a PlasmaRocket
	 */
	@Override
	public void fire(World world, EntityPlayer player)
	{
		EntityPlasmaRocket rocket = new EntityPlasmaRocket(world, player, boltSpeed, range, damage);

        if (!world.isRemote)
        {
            world.playSoundAtEntity(player, SpaceMod.MODID + ":" + "rocket.shoot", 0.75F, 1.0F);
            world.spawnEntityInWorld(rocket);
        }
	}
}
