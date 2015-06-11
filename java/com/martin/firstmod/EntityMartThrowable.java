package com.martin.firstmod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMartThrowable extends EntityThrowable
{

	public EntityMartThrowable(World par1World)
	{
		super(par1World);
	}
	
	public EntityMartThrowable(World par1World, EntityLivingBase par2EntityLivingBase)
	{
		super(par1World, par2EntityLivingBase);
	}
	
	public EntityMartThrowable(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}
	
	protected float getGravityVelocity()
	{
		return 0.01F;
	}
	
	protected float func_70182_d()
	{
		return 0.7F;
	}
	
	protected float func_70183_g()
	{
		return -20.0F;
	}

	@Override
	protected void onImpact(MovingObjectPosition position)
	{
		worldObj.newExplosion(this, position.blockX, position.blockY, position.blockZ, 5F + rand.nextInt(3), true, true);
		
		if (!this.worldObj.isRemote)
		{
			this.setDead();
		}
	}
	
}
