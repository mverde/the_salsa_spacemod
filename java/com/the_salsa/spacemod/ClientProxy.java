package com.the_salsa.spacemod;

import com.the_salsa.spacemod.EntityBlasterBolt;
import com.the_salsa.spacemod.RenderBlasterBolt;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	/**
	 * register Entity renderers
	 */
	@Override
	public void registerRendering()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterBolt());
	}
	
	/**
	 * register Item renderers
	 */
	@Override
	public void registerItemRenders()
	{
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaber, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.blasterPistol, new RenderBlasterPistol());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.blasterRifle, new RenderBlasterRifle());
	}
}
