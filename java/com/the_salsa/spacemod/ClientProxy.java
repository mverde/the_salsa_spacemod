package com.the_salsa.spacemod;

import java.util.HashMap;
import java.util.Map;

import com.martin.firstmod.EntityMartMob;
import com.martin.firstmod.ModelMartMob;
import com.martin.firstmod.RenderMart;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy
{
	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();
	
	/**
	 * register Entity renderers
	 */
	@Override
	public void registerRendering()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterBolt(new ModelBlasterBolt(), new ResourceLocation(SpaceMod.MODID, "models/entities/blasterbolt.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasmaRocket.class, new RenderPlasmaRocket(new ModelPlasmaRocket(), new ResourceLocation(SpaceMod.MODID, "models/entities/plasmarocket.png")));
		RenderingRegistry.registerEntityRenderingHandler(EntityBasicShip.class, new RenderBasicShip(new ModelBasicShip(), new ResourceLocation(SpaceMod.MODID, "models/entities/basicship.png")));
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
		MinecraftForgeClient.registerItemRenderer(SpaceMod.rocketLauncher, new RenderRocketLauncher());
		
		armorModels.put(SpaceMod.maneuverGear, new ModelManeuverGear(1F));
		armorModels.put(SpaceMod.oxygenHelmet, new ModelOxygenHelmet(1F));
	}
}
