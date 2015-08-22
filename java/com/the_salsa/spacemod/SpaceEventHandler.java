package com.the_salsa.spacemod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import org.lwjgl.opengl.GL11;

import com.the_salsa.spacemod.core.EntityTick;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class SpaceEventHandler extends Gui implements IWorldGenerator
{	
	private Minecraft mc;
	private static final int BUFF_ICON_SIZE = 32;
	private static final int BUFF_ICON_SPACING = 22; // 2 pixels between buff icons
	private static final int BUFF_ICON_BASE_U_OFFSET = 0;
	private static final int BUFF_ICON_BASE_V_OFFSET = 0;
	private static final int BUFF_ICONS_PER_ROW = 2;
	private static float fovMod = 0.0F;
	
	public SpaceEventHandler(Minecraft minecraft)
	{
		super();
		this.mc = minecraft;
	}
	
    /**
     * sets the light level of the block that this entity occupies in order to make it "glow"
     */
    private void addLight(EntityPlayer entity)
    {
    	entity.worldObj.setLightValue(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY, (int)entity.posZ, 7);
        entity.worldObj.markBlockRangeForRenderUpdate((int)entity.posX, (int)entity.posY, (int)entity.posX, 12, 12, 12);
        entity.worldObj.markBlockForUpdate((int)entity.posX, (int)entity.posY, (int)entity.posZ);
    }
    
    /**
     * updates the light level of blocks surrounding the entity so that the "glow" goes away as the entity passes by
     */
    private void updateLight(EntityPlayer entity)
    {
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY +1, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY +1, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY +1, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY +1, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY +1, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY +1, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY +1, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY +1, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY +1, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY -1, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY -1, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY -1, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY -1, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY -1, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY -1, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY -1, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY -1, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY -1, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX +1, (int)entity.posY, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY, (int)entity.posZ -1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX -1, (int)entity.posY, (int)entity.posZ);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY, (int)entity.posZ +1);
        entity.worldObj.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY, (int)entity.posZ -1);
    }
	
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
		addOreSpawn(SpaceMod.spaceRock, 0, Blocks.air, world, random, x, z, 16, 16, 10 + random.nextInt(50), 1, 30, 0, 256);
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
	
	/**
	 * Gives each entity extended properties that allow the manipulation of its gravity in space
	 */
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	    // Register extended entity properties
		if (!(event.entity instanceof EntityBlasterBolt || event.entity instanceof EntityPlayer))
		{
		    event.entity.registerExtendedProperties(ExtendedPropertiesGravity.EX_PROP_NAME, new ExtendedPropertiesGravity());
		}
		else if (event.entity instanceof EntityPlayer)
		{
			event.entity.registerExtendedProperties(ExtendedPropertiesPlayer.EX_PROP_NAME, new ExtendedPropertiesPlayer());
		}
		
		if (event.entity instanceof EntityBasicShip)
		{
			event.entity.registerExtendedProperties(ExtendedPropertiesShip.EX_PROP_NAME, new ExtendedPropertiesShip());
			// ((ExtendedPropertiesShip)event.entity.getExtendedProperties(ExtendedPropertiesShip.EX_PROP_NAME)).setFireTicksMax(((EntityBasicShip)event.entity).getFireTicksMax());
			((ExtendedPropertiesShip)event.entity.getExtendedProperties(ExtendedPropertiesShip.EX_PROP_NAME)).setFireTicksMax(5);
		}
	}
	
	/**
	 * Used to modify gravity and oxygen if the player is in a dimension with modified gravity or no breathable atmosphere.
	 */
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{	
		if (event.player.getHeldItem() != null && event.player.getHeldItem().getItem() instanceof ItemPlasmaSaber)
		{
			addLight(event.player);
		}
		updateLight(event.player);
		//TODO: add check for oxygen tanks with oxygen in them
		if (event.player.dimension == 2 && !event.player.capabilities.isCreativeMode && 
				(event.player.getEquipmentInSlot(4) == null || !(event.player.getEquipmentInSlot(4).getItem() instanceof ItemOxygenHelmet)
				|| event.player.getEquipmentInSlot(3) == null || !(event.player.getEquipmentInSlot(3).getItem() instanceof ItemManeuverGear)))
		{
			//find out how to use rendergameoverlay and another extended property instead of this to have suffocation work like drowning
			//maybe set the player's oxygen pretty low if they aren't wearing gear and reset it to the levels of the oxygen tanks when they are
			event.player.attackEntityFrom(DamageSource.drown, 0.5F);
		}
		//else if (event.player)
		
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
			
			ExtendedPropertiesPlayer extendedProperties;
			
			if (event.player.getExtendedProperties("ExtendedPropertiesPlayer") != null)
			{
				extendedProperties = (ExtendedPropertiesPlayer) event.player.getExtendedProperties("ExtendedPropertiesPlayer");
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
		else if (event.player != null && event.player.getExtendedProperties("ExtendedPropertiesPlayer") != null 
				&& ((ExtendedPropertiesPlayer) event.player.getExtendedProperties("ExtendedPropertiesPlayer")).getFalling())
		{
			((ExtendedPropertiesPlayer) event.player.getExtendedProperties("ExtendedPropertiesPlayer")).setFalling(false);
		}
		
        if (event.player.isDead)
        {
        	event.player.worldObj.updateLightByType(EnumSkyBlock.Block, (int)event.player.posX, (int)event.player.posY, (int)event.player.posZ);
        }
	}
	
	/**
	 * Used to make nonplayer entities have no gravity in space
	 */
	@SubscribeEvent
	public void onEntityTick(EntityTick event)
	{
		if (event.entity instanceof EntityBasicShip && (event.entity.riddenByEntity != null || (event.entity.motionX == 0 && event.entity.motionY == 0 && event.entity.motionZ == 0)))
		{
			return;
		}
		
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
	public void onPlayerRenderEventPre(RenderPlayerEvent.Pre event)
	{
		EntityPlayer player = (EntityPlayer) event.entity;
		
		if(player.getHeldItem() != null)
		{
			if(player.getHeldItem().getItem() instanceof ItemBlasterRifle || player.getHeldItem().getItem() instanceof ItemRocketLauncher)
			{
				event.renderer.modelArmorChestplate.aimedBow = event.renderer.modelArmor.aimedBow = event.renderer.modelBipedMain.aimedBow = true;
			}
		}
		
//		if (player.ridingEntity != null && player.ridingEntity instanceof EntityBasicShip)
//		{
//			Vec3 riderLookVec = player.getLookVec();
//			double riderLookXZMag = MathHelper.sqrt_double((riderLookVec.xCoord * riderLookVec.xCoord + riderLookVec.zCoord * riderLookVec.zCoord));
//			double riderLookSin = Math.abs(riderLookXZMag) > 0.0F ? riderLookVec.yCoord / riderLookXZMag : 1.0F;
//			GL11.glPushMatrix();
//			GL11.glRotatef(90.0F * (float)Math.asin(Math.abs(riderLookSin) < 0.5F ? riderLookSin : riderLookSin < 1.0F && riderLookVec.yCoord < 0 ? -0.5F : 0.5F), 1.0F, 0.0F, 0.0F);
//		}
	}
	
	@SubscribeEvent
	public void onPlayerRenderEventPost(RenderPlayerEvent.Post event)
	{
//		EntityPlayer player = (EntityPlayer) event.entity;
//		
//		if (player.ridingEntity != null && player.ridingEntity instanceof EntityBasicShip)
//		{
//			Vec3 riderLookVec = player.getLookVec();
//			double riderLookXZMag = MathHelper.sqrt_double((riderLookVec.xCoord * riderLookVec.xCoord + riderLookVec.zCoord * riderLookVec.zCoord));
//			double riderLookSin = Math.abs(riderLookXZMag) > 0.0F ? riderLookVec.yCoord / riderLookXZMag : 1.0F;
//			GL11.glRotatef(-90.0F * (float)Math.asin(Math.abs(riderLookSin) < 0.5F ? riderLookSin : riderLookSin < 1.0F && riderLookVec.yCoord < 0 ? -0.5F : 0.5F), 1.0F, 0.0F, 0.0F);
//			GL11.glPopMatrix();
//		}
	}
	
	@SubscribeEvent
	public void onPlayerFOVUpdate(FOVUpdateEvent event)
	{
		if (event.entity.ridingEntity != null && event.entity.ridingEntity instanceof EntityBasicShip)
		{
			event.newfov = 2.0F;
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			((ExtendedPropertiesPlayer) event.entity.getExtendedProperties(ExtendedPropertiesPlayer.EX_PROP_NAME)).sync();
		}
	}
	
	@SubscribeEvent
	public void onRenderGameOverlayEvent(RenderGameOverlayEvent event)
	{
		ExtendedPropertiesPlayer properties = (ExtendedPropertiesPlayer) this.mc.thePlayer.getExtendedProperties(ExtendedPropertiesPlayer.EX_PROP_NAME);
		
		if (event.isCancelable() || event.type != ElementType.EXPERIENCE || properties == null)
		{
			return;
		}
		
		int xPos = 390;
		int yPos = 10;
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.mc.renderEngine.bindTexture(new ResourceLocation(SpaceMod.MODID, "textures/gui/oxygenGui.png"));
        this.drawTexturedModalRect(
                xPos, yPos, 
                BUFF_ICON_BASE_U_OFFSET % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE + (BUFF_ICON_SIZE * ((properties.maxOxygen - properties.currentOxygen)/(properties.maxOxygen / 8))), BUFF_ICON_BASE_V_OFFSET / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
                BUFF_ICON_SIZE, BUFF_ICON_SIZE);
        this.drawTexturedModalRect(
                xPos + BUFF_ICON_SPACING, yPos, 
                BUFF_ICON_BASE_U_OFFSET % BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE + (BUFF_ICON_SIZE * ((properties.maxOxygen - properties.currentOxygen)/(properties.maxOxygen / 8))), BUFF_ICON_BASE_V_OFFSET / BUFF_ICONS_PER_ROW * BUFF_ICON_SIZE,
                BUFF_ICON_SIZE, BUFF_ICON_SIZE);
	}
}
