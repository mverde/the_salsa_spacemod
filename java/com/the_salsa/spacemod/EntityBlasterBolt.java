package com.the_salsa.spacemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBlasterBolt extends EntityThrowable
{
    private static final String __OBFID = "CL_00001722";
    private static float damage;
    private static double originX, originY, originZ;
    private static double range;

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
