package com.the_salsa.spacemod;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ItemGunGeneric extends Item
{
	protected double boltSpeed;
	protected double range;
	protected float damage ;
	protected int fireTicksMax;
	protected int reloadTicksMax;
	protected int ammoCapacity;
	protected int durability;
	protected Random rand;
	
	/**
	 * max damage - 2 = ammo capacity
	 */
	public ItemGunGeneric(String name, double boltSpeed, double range, float damage, int fireTicksMax, int reloadTicksMax, int ammoCapacity, int durability)
	{
		super();
		rand = new Random();
		this.boltSpeed = boltSpeed;
		this.range = range;
		this.damage = damage;
		this.fireTicksMax = fireTicksMax;
		this.reloadTicksMax = reloadTicksMax;
		this.ammoCapacity = ammoCapacity;
		setUnlocalizedName(SpaceMod.MODID + "_" + name);
		setTextureName(SpaceMod.MODID + ":" + name);
		setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxDamage(durability);
		this.setMaxStackSize(1);
	}
	
	/**
	 * Each gun must implement this method
	 */
	public abstract void fire(World world, EntityPlayer player);
	
	/**
	 * this method used to set up NBTTag for each BlasterRifle
	 */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
	    itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setInteger("reloadTicks", 0);
	    itemStack.stackTagCompound.setInteger("fireTicks", fireTicksMax);
	    itemStack.stackTagCompound.setBoolean("reloadTimerOn", false);
	    itemStack.stackTagCompound.setBoolean("fireTimerOn", false);
	    itemStack.stackTagCompound.setInteger("currentAmmo", 0);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int duration)
	{	
		return;
	}
	
    /**
     * shoots an EntityBlasterBolt if the player is in Creative mode or has ammo; reloads if no ammo
     */
	@Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {	
		if (item.stackTagCompound == null || item.stackTagCompound.getBoolean("reloadTimerOn") || 
				item.stackTagCompound.getInteger("fireTicks") != fireTicksMax)
		{
			return item;
		}

		if (player.capabilities.isCreativeMode)
        {
        	player.setItemInUse(item, this.getMaxItemUseDuration(item));
        	fire(world, player);
        }
        else if (item.stackTagCompound.getInteger("currentAmmo") > 0)
        {
        	player.setItemInUse(item, this.getMaxItemUseDuration(item));
        	item.damageItem(1, player);
        	item.stackTagCompound.setInteger("currentAmmo", item.stackTagCompound.getInteger("currentAmmo") - 1);
        	fire(world, player);
        }
        else if (item.stackTagCompound.getInteger("currentAmmo") <= 0)
        {
        	player.setItemInUse(item, this.getMaxItemUseDuration(item));
            
        	if (!world.isRemote && player.inventory.hasItem(SpaceMod.gasCanister))
        	{
        		item.stackTagCompound.setBoolean("reloadTimerOn", true);
        		
        		world.playSoundAtEntity(player, SpaceMod.MODID + ":" + "reload", 1.0F, 1.0F);
            	player.inventory.consumeInventoryItem(SpaceMod.gasCanister);
            	player.inventory.addItemStackToInventory(new ItemStack(SpaceMod.emptyCanister));
        	}
        	else
        	{
        		world.playSoundAtEntity(player, "random.click", 1.0F, 1.0F);
        	}
        }
		
        item.stackTagCompound.setBoolean("fireTimerOn", true);
        return item;
    }
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     * 
     * Used here to implement a reloading timer when the player is out of ammo.
     */
	@Override
    public void onUpdate(ItemStack item, World world, Entity entity, int par4, boolean par5)
	{
		if (item.stackTagCompound == null)
		{
			item.stackTagCompound = new NBTTagCompound();
			item.stackTagCompound.setInteger("reloadTicks", 0);
			item.stackTagCompound.setInteger("fireTicks", fireTicksMax);
		    item.stackTagCompound.setBoolean("reloadTimerOn", false);
		    item.stackTagCompound.setBoolean("fireTimerOn", false);
		    item.stackTagCompound.setInteger("currentAmmo", this.ammoCapacity);
		}
		else
		{
			if (item.stackTagCompound.getBoolean("reloadTimerOn"))
			{
				int ticks = item.stackTagCompound.getInteger("reloadTicks") + 1;
				
				if (ticks >= reloadTicksMax)
				{	
					item.stackTagCompound.setInteger("reloadTicks", 0);
					item.stackTagCompound.setBoolean("reloadTimerOn", false);
					item.stackTagCompound.setInteger("currentAmmo", this.ammoCapacity);
				}
				else
				{
					item.stackTagCompound.setInteger("reloadTicks", ticks);
				}
			}
			
			if (item.stackTagCompound.getBoolean("fireTimerOn"))
			{
				int ticks = item.stackTagCompound.getInteger("fireTicks") - 1;
				
				if (ticks <= 0)
				{
					item.stackTagCompound.setInteger("fireTicks", fireTicksMax);
					item.stackTagCompound.setBoolean("fireTimerOn", false);
				}
				else
				{
					item.stackTagCompound.setInteger("fireTicks", ticks);
				}
			}
		}
		
		super.onUpdate(item, world, entity, par4, par5);
	}
	
    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack item)
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
    
    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack item)
    {
        return 72000;
    }
    
    /**
     * adds stats tooltip to each gun
     */
    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean flag)
    {
    	if (item.getItem() != null && item.getItem() instanceof ItemRocketLauncher)
    	{
    		list.add("\u00a7a" + "Damage: " + this.damage + " + AOE");
    	}
    	else
    	{
    		list.add("\u00a7a" + "Damage: " + this.damage);
    	}
    	list.add("\u00a7a" + "Range: " + this.range + "m");
    	list.add("\u00a7a" + "Capacity: " + this.ammoCapacity);
    }
}
