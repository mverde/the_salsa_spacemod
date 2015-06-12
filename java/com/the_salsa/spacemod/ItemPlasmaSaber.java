package com.the_salsa.spacemod;

import java.util.List;

import com.martin.firstmod.FirstMod;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemPlasmaSaber extends ItemSword
{	
	public ItemPlasmaSaber(ToolMaterial material, String name)
	{
		super(material);
		setUnlocalizedName(SpaceMod.MODID + "_" + name);
		setTextureName(SpaceMod.MODID + ":" + name);
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

