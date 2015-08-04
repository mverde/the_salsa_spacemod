package com.the_salsa.spacemod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBlasterBolt extends EntityThrowable
{
    private float damage;
    private double originX, originY, originZ;
    private double range;
    private double speed;

    public EntityBlasterBolt(World world)
    {
        super(world);
    }

    /**
     * construct EntityBlasterBolt with speed, range, damage modifiers
     */
    public EntityBlasterBolt(World world, EntityLivingBase shooter, double speed, double range, float damage)
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

    public EntityBlasterBolt(World world, double x, double y, double z)
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
            if (position.entityHit instanceof EntityPlayer)
            {
            	EntityPlayer player = (EntityPlayer) position.entityHit;
            	
            	if (shouldPlayerDeflect(player) && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemPlasmaSaber
            			&& player.isBlocking())
            	{
            		EntityBlasterBolt bolt = new EntityBlasterBolt(player.worldObj, player, this.speed, this.range, this.damage);

                    if (!player.worldObj.isRemote)
                    {
                    	this.setDead();
                    	player.worldObj.playSoundAtEntity(player, SpaceMod.MODID + ":" + "deflect", 0.75F, 1.0F + (rand.nextInt(4) * 0.1F));
                        player.worldObj.spawnEntityInWorld(bolt);
                    }
                    
                    return;
            	}
            }
            
            position.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }

        for (int i = 0; i < 3; ++i)
        {
            this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    /**
     * Checks to see if the player is looking at the mob that it is attempting to deflect BlasterBolts from
     */
    private boolean shouldPlayerDeflect(EntityPlayer player)
    {
    	Vec3 vec3 = player.getLook(1.0F).normalize();
        Vec3 vec31 = Vec3.createVectorHelper(this.posX - player.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (player.posY + (double)player.getEyeHeight()), 
        		this.posZ - player.posZ);
        double d0 = vec31.lengthVector();
        vec31 = vec31.normalize();
        double d1 = vec3.dotProduct(vec31);
        return d1 > 1.0D - 0.025D / d0 && player.canEntityBeSeen(this);
    }
    
    /**
     * Called to update the entity's position/logic.
     * sets EntityBlasterBolt to dead if it gets out of range
     */
    public void onUpdate()
    {
    	super.onUpdate();
    	
    	if ((!this.isInWater() && this.getDistance(originX, originY, originZ) > this.range) || (this.isInWater() && this.getDistance(originX, originY, originZ) > this.range / 10))
    	{   
    		if (!this.worldObj.isRemote)
    	    {
    			this.setDead();
    	    }
    	}
    }
    
    /**
     * EntityBlasterBolts do not fall
     */
    @Override
    protected float getGravityVelocity()
    {
    	return 0F;
    }
}
