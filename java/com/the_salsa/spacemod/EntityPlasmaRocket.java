package com.the_salsa.spacemod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityPlasmaRocket extends EntityThrowable
{
    private float damage;
    private double originX, originY, originZ;
    private double range;
    private double speed;

    public EntityPlasmaRocket(World world)
    {
        super(world);
    }

    /**
     * construct EntityPlasmaRocket with speed, range, damage modifiers
     */
    public EntityPlasmaRocket(World world, EntityLivingBase shooter, double speed, double range, float damage)
    {
        super(world, shooter);
        this.motionX *= speed;
        this.motionY *= speed;
        this.motionZ *= speed;
        this.damage = damage;
        this.originX = this.posX;
        this.originY = this.posY;
        this.originZ = this.posZ;
        this.range = range;
        this.speed = speed;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), 1.0F);
    }

    public EntityPlasmaRocket(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition position)
    {	
        if (position.entityHit != null)
        {  	
            position.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
        	this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 3F, false);
            this.setDead();
        }
    }
    
    /**
     * Called to update the entity's position/logic.
     * sets EntityPlasmaRocket to dead if it gets out of range
     */
    public void onUpdate()
    {
    	super.onUpdate();
    	this.worldObj.spawnParticle("explode", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    	
    	if ((!this.isInWater() && this.getDistance(originX, originY, originZ) > this.range) || (this.isInWater() && this.getDistance(originX, originY, originZ) > this.range / 10))
    	{   
	        if (!this.worldObj.isRemote)
	        {
	        	if (!this.isInWater())
	        	{
    	        	this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.5F, false);
	        	}
	            this.setDead();
	        }
    	}
    }
    
    /**
     * EntityPlasmaRockets do fall
     */
    @Override
    protected float getGravityVelocity()
    {
    	return 0.005F;
    }
}
