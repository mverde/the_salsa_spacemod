package com.the_salsa.spacemod;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();
	
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
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaberBlue, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaberGreen, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaberRed, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaberPurple, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaberYellow, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.plasmaSaberRainbow, new RenderPlasmaSaber());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.blasterPistol, new RenderBlasterPistol());
		MinecraftForgeClient.registerItemRenderer(SpaceMod.blasterRifle, new RenderBlasterRifle());
		
		armorModels.put(SpaceMod.maneuverGear, new ModelManeuverGear(1F));
	}
}
