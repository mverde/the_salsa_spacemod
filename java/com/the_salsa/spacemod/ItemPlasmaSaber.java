package com.the_salsa.spacemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ItemPlasmaSaber extends ItemSword
{	
	public ItemPlasmaSaber(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(SpaceMod.MODID + "_" + name);
		setTextureName(SpaceMod.MODID + ":" + name);
	}
	
	/**
	 * this method used to set up NBTTag for each PlasmaSaber
	 */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setBoolean("equipped", false);
	}
	
	@Override
    public void onUpdate(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_)
	{
		if (p_77663_1_.stackTagCompound == null)
		{
			p_77663_1_.stackTagCompound = new NBTTagCompound();
			p_77663_1_.stackTagCompound.setBoolean("equipped", false);
		}
		
		EntityPlayer player = (EntityPlayer) p_77663_3_;
		
		if (player.getHeldItem() != null && player.getHeldItem().isItemEqual(p_77663_1_)
				&& !p_77663_1_.stackTagCompound.getBoolean("equipped"))
		{
			p_77663_1_.stackTagCompound.setBoolean("equipped", true);
			
			if (!player.worldObj.isRemote)
			{
				player.worldObj.playSoundAtEntity(player, SpaceMod.MODID + ":" + "saberon", 1F, 1F);
			}
		}
		else if ((player.getHeldItem() == null || !player.getHeldItem().isItemEqual(p_77663_1_))
				&& p_77663_1_.stackTagCompound.getBoolean("equipped"))
		{
			p_77663_1_.stackTagCompound.setBoolean("equipped", false);
			
			if (!player.worldObj.isRemote)
			{
				player.worldObj.playSoundAtEntity(player, SpaceMod.MODID + ":" + "saberoff", 1F, 1F);
			}
		}
	}
	
	@Override
    public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
    {
        p_77644_3_.worldObj.playSoundAtEntity(p_77644_3_, SpaceMod.MODID + ":" +  "hit", 1F, 1F);
        super.hitEntity(p_77644_1_, p_77644_2_, p_77644_3_);
        return true;
    }
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
		entityLiving.worldObj.playSoundAtEntity(entityLiving, SpaceMod.MODID + ":" + "swing", 1F, 1F);
		super.onEntitySwing(entityLiving, stack);
		return false;
    }
}

