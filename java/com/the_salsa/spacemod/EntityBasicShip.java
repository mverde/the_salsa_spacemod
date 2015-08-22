package com.the_salsa.spacemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBasicShip extends Entity
{	
    /** true if no player in ship */
    private boolean isShipEmpty;
    private double speedMultiplier;
    private int shipPosRotationIncrements;
    private double shipX;
    private double shipY;
    private double shipZ;
    private double shipYaw;
    private double shipPitch;
    private double shipRotationPitch;
    @SideOnly(Side.CLIENT)
    private double velocityX;
    @SideOnly(Side.CLIENT)
    private double velocityY;
    @SideOnly(Side.CLIENT)
    private double velocityZ;
    
    private int fireTicksMax = 5;

    public EntityBasicShip(World world)
    {
        super(world);
        this.isShipEmpty = true;
        this.speedMultiplier = 0.07D;
        this.preventEntitySpawning = true;
        this.setSize(1.5F, 0.6F);
        this.yOffset = this.height / 2.0F;
        this.shipRotationPitch = 0.0D;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(17, new Integer(0));
        this.dataWatcher.addObject(18, new Integer(1));
        this.dataWatcher.addObject(19, new Float(0.0F));
        this.dataWatcher.addObject(20, new Integer(this.fireTicksMax));
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    public EntityBasicShip(World world, double x, double y, double z)
    {
        this(world);
        this.setPosition(x, y + (double)this.yOffset, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.shipRotationPitch = 0.0D;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)this.height * 0.0D - 0.30000001192092896D + 0.8D;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damageSource, float var2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else if (!this.worldObj.isRemote && !this.isDead)
        {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + var2 * 10.0F);
            this.setBeenAttacked();
            boolean flag = damageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer)damageSource.getEntity()).capabilities.isCreativeMode;

            if (flag || this.getDamageTaken() > 40.0F)
            {
                if (this.riddenByEntity != null)
                {
                    this.riddenByEntity.mountEntity(this);
                }

                if (!flag)
                {
                    this.func_145778_a(SpaceMod.basicShip, 1, 0.0F);
                }

                this.setDead();
            }

            return true;
        }
        else
        {
            return true;
        }
    }

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double xPos, double yPos, double zPos, float yaw, float pitch, int incrementModifier)
    {
        if (this.isShipEmpty)
        {
            this.shipPosRotationIncrements = incrementModifier + 5;
        }
        else
        {
            double d3 = xPos - this.posX;
            double d4 = yPos - this.posY;
            double d5 = zPos - this.posZ;
            double d6 = d3 * d3 + d4 * d4 + d5 * d5;

            if (d6 <= 1.0D)
            {
                return;
            }

            this.shipPosRotationIncrements = 3;
        }

        this.shipX = xPos;
        this.shipY = yPos;
        this.shipZ = zPos;
        this.shipYaw = (double)yaw;
        this.shipPitch = (double)pitch;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
    }

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    @SideOnly(Side.CLIENT)
    public void setVelocity(double xMotion, double yMotion, double zMotion)
    {
        this.velocityX = this.motionX = xMotion;
        this.velocityY = this.motionY = yMotion;
        this.velocityZ = this.motionZ = zMotion;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        
    	ExtendedPropertiesShip properties = (ExtendedPropertiesShip) this.getExtendedProperties(ExtendedPropertiesShip.EX_PROP_NAME);
        
    	if (properties != null)
    	{
    		properties.updateFireTimer(false);
    	}

        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        byte b0 = 5;
        double d0 = 0.0D;

        for (int i = 0; i < b0; ++i)
        {
            double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 0) / (double)b0 - 0.125D;
            double d3 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 1) / (double)b0 - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d3, this.boundingBox.maxZ);

            if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
            {
                d0 += 1.0D / (double)b0;
            }
        }

        double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        double d2;
        double d4;
        int j;

        if (d10 > 0.26249999999999996D)
        {
            d2 = Math.cos((double)this.rotationYaw * Math.PI / 180.0D);
            d4 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D);
        }

        double d11;
        double d12;

        if (this.worldObj.isRemote && this.isShipEmpty)
        {
            if (this.shipPosRotationIncrements > 0)
            {
                d2 = this.posX + (this.shipX - this.posX) / (double)this.shipPosRotationIncrements;
                d4 = this.posY + (this.shipY - this.posY) / (double)this.shipPosRotationIncrements;
                d11 = this.posZ + (this.shipZ - this.posZ) / (double)this.shipPosRotationIncrements;
                d12 = MathHelper.wrapAngleTo180_double(this.shipYaw - (double)this.rotationYaw);
                this.rotationYaw = (float)((double)this.rotationYaw + d12 / (double)this.shipPosRotationIncrements);
                this.rotationPitch = (float)((double)this.rotationPitch + (this.shipPitch - (double)this.rotationPitch) / (double)this.shipPosRotationIncrements);
                --this.shipPosRotationIncrements;
                this.setPosition(d2, d4, d11);
                this.setRotation(this.rotationYaw, this.rotationPitch);
            }
            else
            {
                d2 = this.posX + this.motionX;
                d4 = this.posY + this.motionY;
                d11 = this.posZ + this.motionZ;
                this.setPosition(d2, d4, d11);

                this.motionX *= 0.9900000095367432D;
                this.motionY *= 0.949999988079071D;
                this.motionZ *= 0.9900000095367432D;
            }
        }
        else
        {
            if (d0 < 1.0D)
            {
                d2 = d0 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d2;
            }
            else
            {
                if (this.motionY < 0.0D)
                {
                    this.motionY /= 2.0D;
                }

                this.motionY += 0.007000000216066837D;
            }
            
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)
            {
            	EntityLivingBase rider = (EntityLivingBase)this.riddenByEntity;
            	
            	if (rider.moveForward > 0)
            	{
            		if (this.posY < this.worldObj.getHeightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ)) + 4)
            		{
            			this.setPosition(this.posX, this.posY + 0.2D, this.posZ);
            		}
            		else
            		{
            			this.speedMultiplier += 0.02F;
            		
            			if (this.speedMultiplier > 0.75F)
            			{
            				this.speedMultiplier = 0.75F;
            			}
            		}
            	}
            	else if (rider.moveForward < 0)
            	{
            		this.speedMultiplier -= 0.02F;
            		
            		if (this.speedMultiplier < 0)
            		{
            			this.speedMultiplier = 0F;
            		}
            	}
            	
            	boolean flag = true;
    			Vec3 riderLookVec = rider.getLookVec();
    			
            	if (this.speedMultiplier > 0)
            	{	
        			float riderYawAdjusted = rider.rotationYawHead >= 0 ? rider.rotationYawHead : 360F + rider.rotationYawHead;
        			float shipYawAdjusted = 360F - (this.rotationYaw >= 0 ? this.rotationYaw : 360F + this.rotationYaw);
        			float riderViewEdgeLeft = riderYawAdjusted - 80F >= 0 ? riderYawAdjusted - 80F : 360 + riderYawAdjusted - 80F;
        			float riderViewEdgeRight = riderYawAdjusted + 80F < 360 ? riderYawAdjusted + 80F : riderYawAdjusted + 80F - 360F;
        			float shipViewEdgeLeft = shipYawAdjusted - 80F >= 0 ? shipYawAdjusted - 80F : 360 + shipYawAdjusted - 80F;
        			float shipViewEdgeRight = shipYawAdjusted + 80F < 360 ? shipYawAdjusted + 80F : shipYawAdjusted + 80F - 360F;
                	
                	//Somewhat complicated/nested calculations incoming
                	//These insure that the ship rotates with the rider's head correctly 
                	//and only if the rider is looking in the 180 degree arc on the front side of the ship
                	
                	if (riderYawAdjusted >= 0 && riderYawAdjusted < 90) //first quadrant
                	{
                    	if (shipYawAdjusted >= 0 && shipYawAdjusted < 90)
                    	{ 		
                    		this.rotationYaw += riderYawAdjusted > shipYawAdjusted ? -2.0F : 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 90 && shipYawAdjusted < 180 && shipViewEdgeLeft <= riderViewEdgeRight)
                    	{
                    		this.rotationYaw += 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 180 && shipYawAdjusted < 270)
                    	{
                    		flag = false;
                    	}
                    	else if (shipYawAdjusted >= 270 && shipYawAdjusted <= 360 && riderViewEdgeLeft <= 180 ? shipViewEdgeRight >= riderViewEdgeLeft : shipViewEdgeRight <= riderViewEdgeLeft)
                    	{
                    		this.rotationYaw -= 2.0F;
                    	}
                	}
                	else if (riderYawAdjusted >= 90 && riderYawAdjusted < 180) //second quadrant
                	{
                    	if (shipYawAdjusted >= 0 && shipYawAdjusted < 90 && shipViewEdgeRight >= riderViewEdgeLeft)
                    	{
                    		this.rotationYaw -= 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 90 && shipYawAdjusted < 180)
                    	{
                    		this.rotationYaw += riderYawAdjusted > shipYawAdjusted ? -2.0F : 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 180 && shipYawAdjusted < 270 && shipViewEdgeLeft <= riderViewEdgeRight)
                    	{
                    		this.rotationYaw += 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 270 && shipYawAdjusted <= 360)
                    	{
                    		flag = false;
                    	}
                	}
                	else if (riderYawAdjusted >= 180 && riderYawAdjusted < 270) //third quadrant
                	{
                    	if (shipYawAdjusted >= 0 && shipYawAdjusted < 90)
                    	{
                    		flag = false;
                    	}
                    	else if (shipYawAdjusted >= 90 && shipYawAdjusted < 180 && shipViewEdgeRight >= riderViewEdgeLeft)
                    	{
                    		this.rotationYaw -= 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 180 && shipYawAdjusted < 270)
                    	{
                    		this.rotationYaw += riderYawAdjusted > shipYawAdjusted ? -2.0F : 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 270 && shipYawAdjusted <= 360 && shipViewEdgeLeft <= riderViewEdgeRight)
                    	{
                    		this.rotationYaw += 2.0F;
                    	}
                	}
                	else if (riderYawAdjusted >= 270 && riderYawAdjusted <= 360) //fourth quadrant
                	{
                    	if (shipYawAdjusted >= 0 && shipYawAdjusted < 90 && (riderViewEdgeRight >= 180 ? shipViewEdgeLeft >= 180 && shipViewEdgeLeft <= riderViewEdgeRight : 
                    		shipViewEdgeLeft >= 180 ? shipViewEdgeLeft >= riderViewEdgeRight : shipViewEdgeLeft <= riderViewEdgeRight))
                    	{
                    		this.rotationYaw += 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 90 && shipYawAdjusted < 180)
                    	{
                    		flag = false;
                    	}
                    	else if (shipYawAdjusted >= 180 && shipYawAdjusted < 270 && shipViewEdgeRight >= riderViewEdgeLeft)
                    	{
                    		this.rotationYaw -= 2.0F;
                    	}
                    	else if (shipYawAdjusted >= 270 && shipYawAdjusted <= 360)
                    	{
                    		this.rotationYaw += riderYawAdjusted > shipYawAdjusted ? -2.0F : 2.0F;
                    	}
                	}
                	
                	if (flag)
                	{
                		if (this.shipRotationPitch < riderLookVec.yCoord)
                		{
                			this.shipRotationPitch = this.shipRotationPitch + 0.025F <= riderLookVec.yCoord ? this.shipRotationPitch + 0.025F : (float)riderLookVec.yCoord;
                		}
                		else if (this.shipRotationPitch > riderLookVec.yCoord)
                		{
                			this.shipRotationPitch = this.shipRotationPitch - 0.025F >= riderLookVec.yCoord ? this.shipRotationPitch - 0.025F : (float)riderLookVec.yCoord;
                		}
                	}
            	}
            	else
            	{
            		if (this.shipRotationPitch < 0)
            		{
            			this.shipRotationPitch = this.shipRotationPitch + 0.025F <= 0 ? this.shipRotationPitch + 0.025F : 0.0F;
            		}
            		else if (this.shipRotationPitch > 0)
            		{
            			this.shipRotationPitch = this.shipRotationPitch - 0.025F >= 0 ? this.shipRotationPitch - 0.025F : 0.0F;
            		}
            	}
            	
            	double riderLookXZMag = Math.sqrt(riderLookVec.xCoord * riderLookVec.xCoord + riderLookVec.zCoord * riderLookVec.zCoord);
            	double rotationYawRad = (this.rotationYaw / 360.0D) * (2 * Math.PI);
            	this.motionX = Math.sin(rotationYawRad) * this.speedMultiplier * riderLookXZMag;
            	this.motionZ = Math.cos(rotationYawRad)  * this.speedMultiplier * riderLookXZMag;
            	this.motionY = this.shipRotationPitch * this.speedMultiplier;
            	
        		if (Mouse.isButtonDown(1) && this.speedMultiplier > 0 && properties != null && properties.canShipFire())
        		{
        			fire(rider, rotationYawRad);
        			properties.updateFireTimer(true);
        		}
        		
    			if (rider.moveForward < 0 && this.speedMultiplier <= 0 && Math.abs(this.shipRotationPitch) == 0 && Math.abs(riderLookVec.yCoord) < 0.3
    					&& this.posY > this.worldObj.getHeightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ)) + 1)
        		{
    				this.shipRotationPitch = 0.0D;
        			this.setPositionAndRotation(this.posX, this.posY - 0.2D, this.posZ, (float) this.rotationYaw, 0.0F);
        		}
    			else if (this.isCollided || this.posY <= this.worldObj.getHeightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ)) + 1)
        		{
        			this.motionX *= 0.25F;
        			this.motionZ *= 0.25F;
        			
        			if (this.speedMultiplier >= 0.55 && !this.worldObj.isRemote)
        			{
        				this.setDead();
        				this.func_145778_a(SpaceMod.basicShip, 1, 0.0F);
        			}
        		}
            }
            else
            {
            	this.speedMultiplier = 0.0D;
            	
            	this.motionX *= 0.95D;
            	this.motionZ *= 0.95D;
            	
            	if (Math.abs(this.motionX) < 0.01)
            	{
            		this.motionX = 0.0D;
            	}
            	
            	if (Math.abs(this.motionZ) < 0.01)
            	{
            		this.motionZ = 0.0D;
            	}
            	
            	if (this.dimension == 2)
            	{
            		this.motionY = 0.0D;
            	}
            }

            int l;

            for (l = 0; l < 4; ++l)
            {
                int i1 = MathHelper.floor_double(this.posX + ((double)(l % 2) - 0.5D) * 0.8D);
                j = MathHelper.floor_double(this.posZ + ((double)(l / 2) - 0.5D) * 0.8D);

                for (int j1 = 0; j1 < 2; ++j1)
                {
                    int k = MathHelper.floor_double(this.posY) + j1;
                    Block block = this.worldObj.getBlock(i1, k, j);

                    if (block == Blocks.snow_layer)
                    {
                        this.worldObj.setBlockToAir(i1, k, j);
                        this.isCollidedHorizontally = false;
                    }
                    else if (block == Blocks.waterlily)
                    {
                        this.worldObj.func_147480_a(i1, k, j, true);
                        this.isCollidedHorizontally = false;
                    }
                }
            }

            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.setRotation(this.rotationYaw, (float)this.shipRotationPitch * 90F);

            if (!this.worldObj.isRemote)
            {
                List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

                if (list != null && !list.isEmpty())
                {
                    for (int k1 = 0; k1 < list.size(); ++k1)
                    {
                        Entity entity = (Entity)list.get(k1);

                        if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityBoat)
                        {
                            entity.applyEntityCollision(this);
                        }
                    }
                }

                if (this.riddenByEntity != null && this.riddenByEntity.isDead)
                {
                    this.riddenByEntity = null;
                }
            }
        }
    }
    
    public void fire(EntityLivingBase rider, double rotationYawRad)
    {
		EntityShipBlasterBolt bolt = new EntityShipBlasterBolt(this.worldObj, rider, 0.5D, 40.0D, 10.0F);
		double sqrtXYZ = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		double unitVecX = this.motionX / sqrtXYZ;
		double unitVecZ = this.motionZ / sqrtXYZ;
		double unitVecY = this.motionY / sqrtXYZ;
		double unitVecXZMag = Math.sqrt(unitVecX * unitVecX + unitVecZ * unitVecZ);
		
		bolt.setPositionAndRotation(this.posX + Math.sin(rotationYawRad) * unitVecXZMag * 5, this.posY + unitVecY * 5, this.posZ + Math.cos(rotationYawRad) * unitVecXZMag * 5, this.rotationYaw, this.rotationPitch);
		bolt.motionX = unitVecX * 4;
		bolt.motionY = unitVecY * 4;
		bolt.motionZ = unitVecZ * 4;

        if (!this.worldObj.isRemote)
        {
            this.worldObj.playSoundAtEntity(this, SpaceMod.MODID + ":" + "blaster.ship", 0.75F, 1.0F);
            this.worldObj.spawnEntityInWorld(bolt);
        }
    }
    
    public int getFireTicksMax()
    {
    	return this.dataWatcher.getWatchableObjectInt(20);
    }

    public void updateRiderPosition()
    {
        if (this.riddenByEntity != null)
        {
            this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound tagCompound) {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound tagCompound) {}

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * First layer of player interaction
     */
    public boolean interactFirst(EntityPlayer player)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != player)
        {
            return true;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                player.mountEntity(this);
            }

            return true;
        }
    }

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
     */
    protected void updateFallState(double distanceFallen, boolean onGround)
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.posY);
        int k = MathHelper.floor_double(this.posZ);

        if (onGround)
        {
            this.fallDistance = 0.0F;
            this.motionY = 0.0F;
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
        }
        else if (this.worldObj.getBlock(i, j - 1, k).getMaterial() != Material.water && distanceFallen < 0.0D)
        {
            this.fallDistance = (float)((double)this.fallDistance - distanceFallen);
        }
    }

    /**
     * Sets the damage taken from the last hit.
     */
    public void setDamageTaken(float damage)
    {
        this.dataWatcher.updateObject(19, Float.valueOf(damage));
    }

    /**
     * Gets the damage taken from the last hit.
     */
    public float getDamageTaken()
    {
        return this.dataWatcher.getWatchableObjectFloat(19);
    }

    /**
     * Sets the time to count down from since the last time entity was hit.
     */
    public void setTimeSinceHit(int time)
    {
        this.dataWatcher.updateObject(17, Integer.valueOf(time));
    }

    /**
     * Gets the time since the last hit.
     */
    public int getTimeSinceHit()
    {
        return this.dataWatcher.getWatchableObjectInt(17);
    }

    /**
     * Sets the forward direction of the entity.
     */
    public void setForwardDirection(int dir)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(dir));
    }

    /**
     * Gets the forward direction of the entity.
     */
    public int getForwardDirection()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    /**
     * true if no player in ship
     */
    @SideOnly(Side.CLIENT)
    public void setisShipEmpty(boolean empty)
    {
        this.isShipEmpty = empty;
    }
}
