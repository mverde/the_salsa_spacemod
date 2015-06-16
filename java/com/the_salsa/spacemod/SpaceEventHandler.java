package com.the_salsa.spacemod;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
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
	    // Players

	    if (event.entity instanceof EntityPlayer)
	    {
	         event.entity.registerExtendedProperties("ExtendedPropertiesPlayerGravity", new ExtendedPropertiesPlayerGravity());
	    }
	}
	
	/**
	 * Used to modify gravity if the player is in a dimension with modified gravity.
	 */
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{	
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
			
			ExtendedPropertiesPlayerGravity extendedProperties;
			
			if (event.player.getExtendedProperties("ExtendedPropertiesPlayerGravity") != null)
			{
				extendedProperties = (ExtendedPropertiesPlayerGravity) event.player.getExtendedProperties("ExtendedPropertiesPlayerGravity");
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
		else if (event.player != null && event.player.getExtendedProperties("ExtendedPropertiesPlayerGravity") != null 
				&& ((ExtendedPropertiesPlayerGravity) event.player.getExtendedProperties("ExtendedPropertiesPlayerGravity")).getFalling())
		{
			((ExtendedPropertiesPlayerGravity) event.player.getExtendedProperties("ExtendedPropertiesPlayerGravity")).setFalling(false);
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
