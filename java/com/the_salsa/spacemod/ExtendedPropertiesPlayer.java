package com.the_salsa.spacemod;

import com.the_salsa.spacemod.OxygenPacket.OxygenMessageHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ExtendedPropertiesPlayer extends ExtendedPropertiesGravity
{	
	/**
	 * Like ExtendedPropertiesGravity but with more properties for the player, such as custom inventory, oxygen supply, etc.
	 */
	public static final String EX_PROP_NAME = "ExtendedPropertiesPlayer";
	//public final InventoryOxygenTanks inventory = new InventoryOxygenTanks();
	private EntityPlayer player;
	public int currentOxygen, maxOxygen;
	
	@Override
	public void init(Entity player, World world)
	{	
		if (player != null && !(player instanceof EntityPlayer))
		{
			System.out.println("Attempted to apply extended player properties to nonplayer entity!");
			return;
		}
		
		super.init(player, world);
		this.player = (EntityPlayer) player;
		this.currentOxygen = this.maxOxygen = 18000;
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
	    compound.setInteger("currentOxygen", this.currentOxygen);
	    compound.setInteger("maxOxygen", this.maxOxygen);
		parCompound.setTag(this.EX_PROP_NAME, compound);
		
		//this.inventory.writeToNBT(parCompound);
	}
	
	@Override
	public void loadNBTData(NBTTagCompound parCompound)
	{
		NBTTagCompound properties = (NBTTagCompound) parCompound.getTag(this.EX_PROP_NAME);
		// Get our data from the custom tag compound
		this.currentOxygen = properties.getInteger("currentOxygen");
		this.maxOxygen = properties.getInteger("maxOxygen");
		// Just so you know it's working, add this line:
		System.out.println("[PLAYER PROPS] O2 from NBT: " + this.currentOxygen + "/" + this.maxOxygen);
		//this.inventory.readFromNBT(parCompound);
	}
	
	/**
	 * Attempts to consume some oxygen from the player's reserve
	 * @return true if amount units of oxygen were consumed or false if the player's oxygen was insufficient
	 */
	public boolean depleteOxygen(int amount)
	{
		boolean enough = this.currentOxygen >= amount;
		this.currentOxygen -= (enough ? amount : this.currentOxygen);
		sync();
		
		return enough;
	}
	
	/**
	 * Refills the player's oxygen reserve
	 */
	public void refillOxygen()
	{
		this.currentOxygen = this.maxOxygen;
		sync();
	}
	
	/**
	 * Sets current oxygen to amount of maxOxygen, whichever is lesser
	 */
	public void setCurrentOxygen(int amount)
	{
		this.currentOxygen = (amount < this.maxOxygen ? amount : this.maxOxygen);
		sync();
	}
	
	/**
	 * Sets max oxygen to amount or 0 if amount is less than 0
	 */
	public void setMaxOxygen(int amount)
	{
		this.maxOxygen = (amount > 0 ? amount : 0);
		sync();
	}
	
	/**
	 * Sends a packet to the client containing oxygen data from the server for the player
	 */
	public final void sync()
	{
		if (!player.worldObj.isRemote)
		{
			//System.out.println("[EXTENDED PROPS] attempting to send oxygen packet to client...");
			OxygenPacket packet = new OxygenPacket("" + this.maxOxygen + this.currentOxygen);
			SpaceMod.wrapper.sendTo(packet, (EntityPlayerMP) player);
		}
	}
}
