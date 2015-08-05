package com.the_salsa.spacemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOxygenHelmet extends ItemArmor
{
	public ItemOxygenHelmet(ArmorMaterial material, int renderIdx, int armorType, String name)
	{
		super(material, renderIdx, armorType);
		setUnlocalizedName(SpaceMod.MODID + "_" + name);
		setTextureName(SpaceMod.MODID + ":" + name);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabCombat);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer)
	{
		return SpaceMod.MODID + ":" + "models/armor/oxygenhelmet.png";
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
	
	/**
	 * The oxygen helmet consumes oxygen from the player every tick if they are wearing maneuver gear
	 * with filled oxygen tanks in exchange for keeping them alive in space and under water
	 */
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		//TODO: add check for oxygen tanks with oxygen in them
		//TODO: add check for two tanks to make depletion slower
		if (!world.isRemote && player.getEquipmentInSlot(3) != null && player.getEquipmentInSlot(3).getItem() instanceof ItemManeuverGear)
		{
			ExtendedPropertiesPlayer properties = (ExtendedPropertiesPlayer) player.getExtendedProperties(ExtendedPropertiesPlayer.EX_PROP_NAME);
			
			if (properties != null)
			{
				if (properties.depleteOxygen(1))
				{
					if (player.isInWater())
					{
						player.setAir(300);
					}
				}
			}
		}
	}
}
