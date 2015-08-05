package com.the_salsa.spacemod;

import org.lwjgl.input.Keyboard;

import com.martin.firstmod.FirstMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemManeuverGear extends ItemArmor
{
	public static final int burstTicksMax = 10;
	
	public ItemManeuverGear(ArmorMaterial material, int renderIdx, int armorType, String name)
	{
		super(material, renderIdx, armorType);
		setUnlocalizedName(SpaceMod.MODID + "_" + name);
		setTextureName(SpaceMod.MODID + ":" + name);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
	/**
	 * this method used to set up NBTTag for each ManeuverGear
	 */
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		itemStack.stackTagCompound = new NBTTagCompound();
	    itemStack.stackTagCompound.setInteger("burstTicks", 0);
	    itemStack.stackTagCompound.setBoolean("burst", false);
	    itemStack.stackTagCompound.setInteger("lastKey", -1);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		return SpaceMod.MODID + ":" + "models/armor/maneuvergear.png";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		ModelBiped armorModel =  ClientProxy.armorModels.get(this);
		
		if (armorModel != null)
		{
			armorModel.isSneak = entityLiving.isSneaking();
		}
		
		return armorModel;
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{	
		ExtendedPropertiesPlayer properties = (ExtendedPropertiesPlayer) player.getExtendedProperties("ExtendedPropertiesPlayer");
		
		if (itemStack.stackTagCompound != null && !itemStack.stackTagCompound.getBoolean("burst") && properties != null && properties.getFalling() 
				&& !player.isCollided && !Keyboard.isRepeatEvent() && Minecraft.getMinecraft().inGameHasFocus)
		{
			switch (itemStack.stackTagCompound.getInteger("lastKey"))
			{
			case 0: if (Keyboard.isKeyDown(Keyboard.KEY_W)) return; else itemStack.stackTagCompound.setInteger("lastKey", -1); break;
			case 1: if (Keyboard.isKeyDown(Keyboard.KEY_S)) return; else itemStack.stackTagCompound.setInteger("lastKey", -1); break;
			case 2: if (Keyboard.isKeyDown(Keyboard.KEY_A)) return; else itemStack.stackTagCompound.setInteger("lastKey", -1); break;
			case 3: if (Keyboard.isKeyDown(Keyboard.KEY_D)) return; else itemStack.stackTagCompound.setInteger("lastKey", -1); break;
			case 4: if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) return; else itemStack.stackTagCompound.setInteger("lastKey", -1); break;
			case 5: if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) return; else itemStack.stackTagCompound.setInteger("lastKey", -1); break;
			default: break;		
			}
			
			double yMotion = player.motionY;
			boolean keyPressed = false;
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W))
			{
				player.moveEntityWithHeading(0F, 1.2F);
				player.motionY = yMotion;
				keyPressed = true;
				itemStack.stackTagCompound.setInteger("lastKey", 0);
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				player.moveEntityWithHeading(0F, -1.2F);
				player.motionY = yMotion;
				keyPressed = true;
				itemStack.stackTagCompound.setInteger("lastKey", 1);
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_A))
			{
				player.moveEntityWithHeading(1.2F, 0F);
				player.motionY = yMotion;
				keyPressed = true;
				itemStack.stackTagCompound.setInteger("lastKey", 2);
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				player.moveEntityWithHeading(-1.2F, 0F);
				player.motionY = yMotion;
				keyPressed = true;
				itemStack.stackTagCompound.setInteger("lastKey", 3);
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			{
				player.motionY += 0.12D;
				keyPressed = true;
				itemStack.stackTagCompound.setInteger("lastKey", 4);
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
			{
				player.motionY -= 0.12D;
				keyPressed = true;
				itemStack.stackTagCompound.setInteger("lastKey", 5);
			}
			
			if (keyPressed)
			{
				properties.updateVelocity();
				itemStack.stackTagCompound.setBoolean("burst", true);
				
		        for (int i = 0; i < 5; ++i)
		        {
		            player.worldObj.spawnParticle("explode", player.posX, player.posY - 0.5D, player.posZ, 0.0D, 0.0D, 0.0D);
		        }
			}
		}
		else if (itemStack.stackTagCompound == null)
		{
		    itemStack.stackTagCompound = new NBTTagCompound();
		    itemStack.stackTagCompound.setInteger("burstTicks", 0);
		    itemStack.stackTagCompound.setBoolean("burst", false);
		    itemStack.stackTagCompound.setInteger("lastKey", -1);
		}
		else if (itemStack.stackTagCompound.getBoolean("burst"))
		{
			int ticks = itemStack.stackTagCompound.getInteger("burstTicks") + 1;
			
			if (ticks >= burstTicksMax)
			{	
				itemStack.stackTagCompound.setInteger("burstTicks", 0);
				itemStack.stackTagCompound.setBoolean("burst", false);
			}
			else
			{
				itemStack.stackTagCompound.setInteger("burstTicks", ticks);
			}
		}
	}
}
