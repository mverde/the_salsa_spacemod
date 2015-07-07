package com.the_salsa.spacemod;

import java.util.HashMap;
import java.util.Map;

import com.martin.firstmod.EntityMartMob;
import com.martin.firstmod.ModelMartMob;
import com.martin.firstmod.RenderMart;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
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
		RenderingRegistry.registerEntityRenderingHandler(EntityBlasterBolt.class, new RenderBlasterBolt(new ModelBlasterBolt()));
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == SpaceMod.GUI_CUSTOM_INV)
		{
			return new GuiCustomPlayerInventory(player, player.inventory, ((ExtendedPropertiesPlayer) player.getExtendedProperties("ExtendedPropertiesPlayer")).inventory);
		}
		
		return null;
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
		armorModels.put(SpaceMod.oxygenHelmet, new ModelOxygenHelmet(1F));
	}
}
