package com.the_salsa.spacemod;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlasterRifle extends ItemBow
{
	private static double boltSpeed = 2.0D;
	private static double range = 60.0D;
	private static double spreadModifier = 0.04D;
	private static float damage = 6.0F;
	private static int fireTicksMax = 4;
	private static int reloadTicksMax = 80;
	private static Random rand = new Random();
	
	/**
	 * max damage - 2 = ammo capacity
	 */
	public ItemBlasterRifle(String name)
	{
		super();
		setUnlocalizedName(SpaceMod.MODID + "_" + name);
		setTextureName(SpaceMod.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxDamage(42);
	}
	
	/**
	 * this method used to set up NBTTag for each BlasterPistol
	 */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
	    itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setInteger("reloadTicks", 0);
	    itemStack.stackTagCompound.setInteger("fireTicks", fireTicksMax);
	    itemStack.stackTagCompound.setBoolean("reloadTimerOn", false);
	    itemStack.stackTagCompound.setBoolean("fireTimerOn", false);
	}
	
    /**
     * this method overridden to stop ItemBlasterPistol from releasing an arrow
     */
	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
	{	
		return;
	}
	
    /**
     * shoots an EntityBlasterBolt if the player is in Creative mode or has ammo; reloads if no ammo
     */
	@Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {	
		if (p_77659_1_.stackTagCompound == null || p_77659_1_.stackTagCompound.getBoolean("reloadTimerOn") || 
				p_77659_1_.stackTagCompound.getInteger("fireTicks") != fireTicksMax)
		{
			return p_77659_1_;
		}

		if (p_77659_3_.capabilities.isCreativeMode)
        {
        	p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        	
        	fire(p_77659_2_, p_77659_3_);
        }
        else if (p_77659_1_.getItemDamage() < this.getMaxDamage())
        {
        	p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        	
            p_77659_1_.damageItem(1, p_77659_3_);
            
            if (p_77659_1_.getItemDamage() == this.getMaxDamage() - 1)
            {	
            	if (!p_77659_2_.isRemote && p_77659_3_.inventory.hasItem(SpaceMod.gasCanister))
            	{
            		p_77659_1_.stackTagCompound.setBoolean("reloadTimerOn", true);
            		
            		p_77659_2_.playSoundAtEntity(p_77659_3_, SpaceMod.MODID + ":" + "reload", 1.0F, 1.0F);
                    p_77659_1_.damageItem(-(this.getMaxDamage() - 1), p_77659_3_);
                	p_77659_3_.inventory.consumeInventoryItem(SpaceMod.gasCanister);
                	p_77659_3_.inventory.addItemStackToInventory(new ItemStack(SpaceMod.emptyCanister));
            	}
            	else
            	{
                	p_77659_1_.damageItem(-1, p_77659_3_);
            	}
            }
            else if (p_77659_1_.getItemDamage() < this.getMaxDamage() - 1)
            {
            	p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
            	
            	fire(p_77659_2_, p_77659_3_);
            }
        }
		
        p_77659_1_.stackTagCompound.setBoolean("fireTimerOn", true);
        return p_77659_1_;
    }
	
	/**
	 * shoots a BlasterBolt
	 */
	public void fire(World world, EntityPlayer player)
	{
		EntityBlasterBolt bolt = new EntityBlasterBolt(world, player, boltSpeed, range, damage);

        if (!world.isRemote)
        {
            world.playSoundAtEntity(player, SpaceMod.MODID + ":" + "blaster.rifle", 1.0F, 1.0F);
            
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
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     * 
     * Used here to implement a reloading timer when the player is out of ammo.
     */
	@Override
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
	{
		if (p_77663_1_.stackTagCompound == null)
		{
			p_77663_1_.stackTagCompound = new NBTTagCompound();
			p_77663_1_.stackTagCompound.setInteger("reloadTicks", 0);
			p_77663_1_.stackTagCompound.setInteger("fireTicks", fireTicksMax);
		    p_77663_1_.stackTagCompound.setBoolean("reloadTimerOn", false);
		    p_77663_1_.stackTagCompound.setBoolean("fireTimerOn", false);
		}
		else
		{
			if (p_77663_1_.stackTagCompound.getBoolean("reloadTimerOn"))
			{
				int ticks = p_77663_1_.stackTagCompound.getInteger("reloadTicks") + 1;
				
				if (ticks >= reloadTicksMax)
				{	
					p_77663_1_.stackTagCompound.setInteger("reloadTicks", 0);
					p_77663_1_.stackTagCompound.setBoolean("reloadTimerOn", false);
				}
				else
				{
					p_77663_1_.stackTagCompound.setInteger("reloadTicks", ticks);
				}
			}
			
			if (p_77663_1_.stackTagCompound.getBoolean("fireTimerOn"))
			{
				int ticks = p_77663_1_.stackTagCompound.getInteger("fireTicks") - 1;
				
				if (ticks <= 0)
				{
					p_77663_1_.stackTagCompound.setInteger("fireTicks", fireTicksMax);
					p_77663_1_.stackTagCompound.setBoolean("fireTimerOn", false);
				}
				else
				{
					p_77663_1_.stackTagCompound.setInteger("fireTicks", ticks);
				}
			}
		}
		
		super.onUpdate(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
	}
	
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack p_77661_1_)
    {
        return EnumAction.none;
    }
    
    /**
     * return the enchantability factor of the item, most of the time is based on material. (not this time)
     */
    @Override
    public int getItemEnchantability()
    {
        return 0;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister p_94581_1_)
    {
        return;
    }

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
        return null;
    }
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack p_77626_1_)
    {
        return 72000;
    }
}