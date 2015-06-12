package com.the_salsa.spacemod;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class SpaceEventHandler implements IWorldGenerator
{	
	boolean hasJumped = false;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		return;
	}
	
	/**
	 * Used to modify gravity if the player is in a dimension with modified gravity.
	 */
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if (event.player.dimension == 0 && !event.player.capabilities.isCreativeMode && event.player.isAirBorne)
		{
			hasJumped = true;
			
			if (event.player.motionX > 0)
			{
				event.player.motionX += 0.003;
			}
			else if (event.player.motionX < 0)
			{
				event.player.motionX -= 0.003;
			}
			
			event.player.motionY += 0.035;
			
			if (event.player.motionZ > 0)
			{
				event.player.motionZ += 0.003;
			}
			else if (event.player.motionZ < 0)
			{
				event.player.motionZ -= 0.003;
			}
		}
		else if (!event.player.capabilities.isCreativeMode && hasJumped)
		{
			hasJumped = false;
			event.player.fallDistance = 0F;
			
			if (event.player.motionX > 0)
			{
				event.player.motionX -= 0.003;
			}
			else if (event.player.motionX < 0)
			{
				event.player.motionX += 0.003;
			}
			
			if (event.player.motionZ > 0)
			{
				event.player.motionZ -= 0.003;
			}
			else if (event.player.motionZ < 0)
			{
				event.player.motionZ += 0.003;
			}
		}
	}
	
	/**
	 * Used to make the player hold a rifle like a drawn bow at all times
	 */
	@SubscribeEvent
	public void onPlayerRenderEvent(RenderPlayerEvent.Pre event)
	{
		EntityPlayer player = (EntityPlayer) event.entity;
		
		if(player.getHeldItem() != null)
		{
			if(player.getHeldItem().getItem() instanceof ItemBlasterRifle)
			{
				event.renderer.modelArmorChestplate.aimedBow = event.renderer.modelArmor.aimedBow = event.renderer.modelBipedMain.aimedBow = true;
			}
		}
	}
}
