package com.the_salsa.spacemod;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler
{
	public void registerRendering()
	{}
	
	public void registerItemRenders()
	{}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == SpaceMod.GUI_CUSTOM_INV)
		{
			return new ContainerOxygenTanks(player, player.inventory, ((ExtendedPropertiesPlayer) player.getExtendedProperties("ExtendedPropertiesPlayer")).inventory);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
