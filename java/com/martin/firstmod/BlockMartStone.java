package com.martin.firstmod;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMartStone extends Block //metadata block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockMartStone(String itemName)
	{
		super(Material.rock);
		setBlockName(FirstMod.MODID + "_" + itemName);
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.0F); //hardness for tools, stone is 1.5F
		setResistance(5F); //blast resistance for all ores
		setStepSound(Block.soundTypeStone);
		setHarvestLevel("pickaxe", 2);
	}
	
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return FirstMod.martDust;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(int var4 = 0; var4 < 3; var4++)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[4];
		
		for(int i = 0; i < icons.length; i++)
		{
			icons[i] = par1IconRegister.registerIcon(FirstMod.MODID + ":" + "martStone" + i);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
	{
		switch(par2)
		{
		case 0:
			return icons[0];
		case 1:
			if(ForgeDirection.getOrientation(par1) == ForgeDirection.UP || ForgeDirection.getOrientation(par1) == ForgeDirection.DOWN)
				return icons[2];
			else
				return icons[1];
		case 2:
			return icons[3];
		default:
			System.out.println("Problems with getting the icon for BlockSamStone");
			return null;
				
		}
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if(world.getBlockMetadata(x, y, z) == 1)
		{
			setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
		}
		else
		{
			setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}
	}
}
