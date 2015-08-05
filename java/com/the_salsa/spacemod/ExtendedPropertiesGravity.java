package com.the_salsa.spacemod;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPropertiesGravity implements IExtendedEntityProperties
{
	public static final String EXT_PROP_NAME = "ExtendedPropertiesGravity";
	protected Entity entity;
	protected boolean falling = false;
	protected double xMotion;
	protected double yMotion;
	protected double zMotion;
	
    @Override
	public void init(Entity entity, World world)
    {
		this.entity = entity;
		this.falling = false;
		this.xMotion = entity.motionX;
		this.yMotion = entity.motionY;
		this.zMotion = entity.motionZ;
    }

	@Override
	public void saveNBTData(NBTTagCompound parCompound)
	{
		// good idea to keep your extended properties in a sub-compound to 
	    // avoid conflicts with other possible extended properties,
	    // even from other mods

	    NBTTagCompound compound = new NBTTagCompound();
	    
	    compound.setDouble("motionX", this.xMotion);
	    compound.setDouble("motionY", this.yMotion);
	    compound.setDouble("motionZ", this.zMotion);
	    compound.setBoolean("isFalling", this.falling);
	    
		parCompound.setTag(EXT_PROP_NAME, compound);
	}

	@Override
    public void loadNBTData(NBTTagCompound parCompound) {}
	
	public boolean getFalling()
	{
		return this.falling;
	}
	
	public void setFalling(boolean val)
	{
		this.falling = val;
	}
	
	public void updateVelocity()
	{
		this.xMotion = this.entity.motionX;
		this.yMotion = this.entity.motionY;
		this.zMotion = this.entity.motionZ;
	}
	
	public void setVelocity()
	{
		this.entity.setVelocity(this.xMotion, this.yMotion, this.zMotion);
	}
    
	public boolean isInGravitationalField()
	{
		int x = MathHelper.floor_double(entity.posX);
		int y = MathHelper.floor_double(entity.posY);
		int z = MathHelper.floor_double(entity.posZ);
		
		for (int y2 = y; y2 > (y - 10); y2--)
		{
			if (!entity.worldObj.isAirBlock(x, y2, z))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void grabBlock()
	{
		this.xMotion = 0D;
		this.yMotion = 0.02D;
		this.zMotion = 0D;
	}
}
