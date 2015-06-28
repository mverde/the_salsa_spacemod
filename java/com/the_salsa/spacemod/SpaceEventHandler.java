package com.the_salsa.spacemod;

import java.util.ArrayList;
import java.util.Random;

import com.the_salsa.spacemod.core.EntityTick;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class SpaceEventHandler implements IWorldGenerator
{	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.dimensionId == 2)
		{
			generateSpace(world, random, chunkX * 16, chunkZ * 16);
		}
	}
	
	private void generateSpace(World world, Random random, int x, int z)
	{
		addOreSpawn(SpaceMod.spaceRock, 0, Blocks.air, world, random, x, z, 16, 16, 10 + random.nextInt(50), 1, 15, 0, 256);
	}
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	    // Register extended entity properties
		if (!(event.entity instanceof EntityBlasterBolt))
		{
		    event.entity.registerExtendedProperties("ExtendedPropertiesGravity", new ExtendedPropertiesGravity());
		}
	}
	
	/**
	 * Used to modify gravity and oxygen if the player is in a dimension with modified gravity or no breathable atmosphere.
	 */
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{	
		if (event.player.dimension == 2 && !event.player.capabilities.isCreativeMode && 
				(event.player.getEquipmentInSlot(4) == null || !(event.player.getEquipmentInSlot(4).getItem() instanceof ItemOxygenHelmet)))
		{
			//find out how to use rendergameoverlay and another extended property instead of this to have suffocation work like drowning
			event.player.attackEntityFrom(DamageSource.drown, 0.5F);
		}
		
		if (event.player.dimension == 2 && !event.player.capabilities.isCreativeMode && 
				(event.player.motionY < -0.0784000015258789D || event.player.motionY > -0.0784000015258789D))
		{	
			event.player.fallDistance = 0F;
			
			if (!event.player.worldObj.isRemote && event.player.posY < -10D)
			{
				EntityPlayerMP playerMP = (EntityPlayerMP) event.player;
				
				SpaceTeleporter teleporter = new SpaceTeleporter(playerMP.mcServer.worldServerForDimension(0));
				teleporter.teleport(playerMP, 0);
				playerMP.setPositionAndUpdate(playerMP.posX, 300D, playerMP.posZ);
			}
			
			ExtendedPropertiesGravity extendedProperties;
			
			if (event.player.getExtendedProperties("ExtendedPropertiesGravity") != null)
			{
				extendedProperties = (ExtendedPropertiesGravity) event.player.getExtendedProperties("ExtendedPropertiesGravity");
			}
			else
			{
				return;
			}
			
			if (extendedProperties.isInGravitationalField())
			{
				return;
			}
			else if (!extendedProperties.getFalling())
			{
				extendedProperties.setFalling(true);
				extendedProperties.updateVelocity();
			}
			
			if (event.player.isCollidedHorizontally)
			{
				extendedProperties.grabBlock();
			}
			
			extendedProperties.setVelocity();
		}
		else if (event.player != null && event.player.getExtendedProperties("ExtendedPropertiesGravity") != null 
				&& ((ExtendedPropertiesGravity) event.player.getExtendedProperties("ExtendedPropertiesGravity")).getFalling())
		{
			((ExtendedPropertiesGravity) event.player.getExtendedProperties("ExtendedPropertiesGravity")).setFalling(false);
		}
	}
	
	/**
	 * Used to make nonplayer entities have no gravity in space
	 */
	@SubscribeEvent
	public void onEntityTick(EntityTick event)
	{
		if (event.entity.dimension == 2 &&
				(event.entity.motionY < -0.0784000015258789D || event.entity.motionY > -0.0784000015258789D))
		{	
			event.entity.fallDistance = 0F;
			
			ExtendedPropertiesGravity extendedProperties;
			
			if (event.entity.getExtendedProperties("ExtendedPropertiesGravity") != null)
			{
				extendedProperties = (ExtendedPropertiesGravity) event.entity.getExtendedProperties("ExtendedPropertiesGravity");
			}
			else
			{
				return;
			}
			
			if (extendedProperties.isInGravitationalField())
			{
				return;
			}
			else if (!extendedProperties.getFalling())
			{
				extendedProperties.setFalling(true);
				extendedProperties.updateVelocity();
			}
			
			if (event.entity.isCollidedHorizontally)
			{
				extendedProperties.grabBlock();
			}
			
			extendedProperties.setVelocity();
		}
		else if (event.entity != null && event.entity.getExtendedProperties("ExtendedPropertiesGravity") != null 
				&& ((ExtendedPropertiesGravity) event.entity.getExtendedProperties("ExtendedPropertiesGravity")).getFalling())
		{
			((ExtendedPropertiesGravity) event.entity.getExtendedProperties("ExtendedPropertiesGravity")).setFalling(false);
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
	
	/**
	 * Adds an Ore Spawn to Minecraft.  Simply register all Ores to spawn with this method in your Generation method in your
	 * IWorldGeneration extending Class
	 * 
	 * @param The Block to spawn
	 * @param The metadata of the Block
	 * @param The Block where to generate in
	 * @param The World to spawn in
	 * @param A Random object for retrieving random positions within the world to spawn the Block
	 * @param An int for passing the X-Coordinate for the Generation method
	 * @param An int for passing the Z-Coordinate for the Generation method
	 * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
	 * @param An int for setting the maximum size of a vein
	 * @param An int for the Number of chances available for the Block to spawn per-chunk
	 * @param An int for the minimum Y-Coordinate height at which this block may spawn
	 * @param An int for the maximum Y-Coordinate height at which this block may spawn
	 **/
	public void addOreSpawn(Block block, int metadata, Block target, World world, Random random, int blockXPos, int blockZPos, int maxX,
	int maxZ, int maxVeinSize, int chancesToSpawn, int chanceModifier, int minY, int maxY)
	{
		int maxPossY = minY + (maxY - 1);
		assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
		assert maxX > 0 && maxX <= 16: "addOreSpawn: The maximum X must be greater than 0 and less than 16";
		assert minY > 0: "addOreSpawn: the minimum Y must be greater than 0";
		assert maxY < 256 && maxY > 0: "addOreSpawn: the maxumum Y must be less than 256 but greater than 0";
		assert maxZ > 0 && maxZ <= 16: "addOreSpawn: the maxumum Z must be greater than 0 and less than 16";
		
		int diffBtwnMinMaxY = maxY - minY;
		for (int x = 0; x < chancesToSpawn; x++)
		{
			if (random.nextInt(chanceModifier) == 0)
			{
				int posX = blockXPos + random.nextInt(maxX);
				int posY = minY + random.nextInt(diffBtwnMinMaxY);
				int posZ = blockZPos + random.nextInt(maxZ);
				(new WorldGenMinable(block, metadata, maxVeinSize, target)).generate(world, random, posX, posY, posZ);
			}
		}
	}
}
