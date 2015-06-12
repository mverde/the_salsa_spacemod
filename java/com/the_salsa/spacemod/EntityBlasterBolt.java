package com.the_salsa.spacemod;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBlasterBolt extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";
    private static float damage;
    private static double originX, originY, originZ;
    private static double range;
    private static double speed;

    public EntityBlasterBolt(World p_i1773_1_)
    {
        super(p_i1773_1_);
    }

    /**
     * construct EntityBlasterBolt with speed, range, damage modifiers
     */
    public EntityBlasterBolt(World p_i1774_1_, EntityLivingBase p_i1774_2_, double speed, double range, float damage)
    {
        super(p_i1774_1_, p_i1774_2_);
        this.motionX *= speed;
        this.motionY *= speed;
        this.motionZ *= speed;
        this.damage = damage;
        this.originX = this.posX;
        this.originY = this.posY;
        this.originZ = this.posZ;
        this.range = range;
        this.speed = speed;
    }

    public EntityBlasterBolt(World p_i1775_1_, double p_i1775_2_, double p_i1775_4_, double p_i1775_6_)
    {
        super(p_i1775_1_, p_i1775_2_, p_i1775_4_, p_i1775_6_);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition p_70184_1_)
    {	
        if (p_70184_1_.entityHit != null)
        {  	
            if (p_70184_1_.entityHit instanceof EntityPlayer)
            {
            	EntityPlayer player = (EntityPlayer) p_70184_1_.entityHit;
            	
            	if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemPlasmaSaber
            			&& player.isBlocking())
            	{
            		EntityBlasterBolt bolt = new EntityBlasterBolt(player.worldObj, player, this.speed, this.range, this.damage);

                    if (!player.worldObj.isRemote)
                    {
                    	this.setDead();
                    	player.worldObj.playSoundAtEntity(player, SpaceMod.MODID + ":" + "deflect", 1.0F, 1.0F);
                        player.worldObj.spawnEntityInWorld(bolt);
                    }
                    
                    return;
            	}
            }
            
            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }

        for (int i = 0; i < 8; ++i)
        {
            this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    
    /**
     * Called to update the entity's position/logic.
     * sets EntityBlasterBolt to dead if it gets out of range
     */
    public void onUpdate()
    {
    	if (MathHelper.sqrt_double((this.posX - this.originX) * (this.posX - this.originX) + (this.posY - this.originY) 
    			* (this.posY - this.originY) + (this.posZ - this.originZ) * (this.posZ - this.originZ)) > this.range)
    	{
            for (int i = 0; i < 8; ++i)
            {
                this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
            
    		if (!this.worldObj.isRemote)
    	    {
    			this.setDead();
    	    }
    	}
    	
    	super.onUpdate();
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
