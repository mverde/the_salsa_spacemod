package com.martin.firstmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBerry extends ItemFood 
{
	//Food item with 2 potion effects
	private int secondpotionId;
	private int secondpotionDuration;
	private int secondpotionAmplifier;
	private float secondpotionEffectProbability;
	
	public ItemBerry(int food, float saturation, boolean wolfFood, String itemName) //food in .5 bits, saturation starts at 5F and can go higher, golden carrot gives 1.2
	{
		super(food, saturation, wolfFood);
		setUnlocalizedName(FirstMod.MODID + "_" + itemName);
		setTextureName(FirstMod.MODID + ":" + itemName);
		setAlwaysEdible();
		setPotionEffect(Potion.moveSpeed.id, 20, 0, 0.75F);
		setSecondPotionEffect(Potion.confusion.id, 8, 0, 1F);
	}
	
	@Override
	protected void onFoodEaten(ItemStack par1itemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onFoodEaten(par1itemStack, par2World, par3EntityPlayer);
		
		if(!par2World.isRemote && secondpotionId > 0 && par2World.rand.nextFloat() < secondpotionEffectProbability)
		{
			par3EntityPlayer.addPotionEffect(new PotionEffect(secondpotionId, secondpotionDuration * 20, secondpotionAmplifier));
		}
	}
	
	public ItemFood setSecondPotionEffect(int par1, int par2, int par3, float par4)
	{
		secondpotionId = par1;
		secondpotionDuration = par2;
		secondpotionAmplifier = par3;
		secondpotionEffectProbability = par4;
		return this;
	}
}
