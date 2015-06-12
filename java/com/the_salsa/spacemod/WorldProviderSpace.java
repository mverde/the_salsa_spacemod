package com.the_salsa.spacemod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderSpace extends WorldProvider
{	
	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(SpaceMod.spacebiome, 0F);
		this.dimensionId = SpaceMod.dimensionSpace;
	}
	
	@Override
	public String getSaveFolder()
	{
		return "DIM-SPACE";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getWelcomeMessage()
	{
		return "Entering The Final Frontier";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public String getDepartMessage()
	{
		return "Leaving The Final Frontier";
	}
	
	@Override
	public boolean canRespawnHere()
	{
		return false;
	}
	
	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderSpace(worldObj, worldObj.getSeed(), true);
	}
	
	@Override
	public String getDimensionName() {
		return "Space";
	}
	
	/**
	 * gets the hard-coded portal location to use when entering this dimension
	 */
	@Override
	public ChunkCoordinates getEntrancePortalLocation()
	{
		return null;
	}
	
	@Override
	public boolean canDoLightning(Chunk chunk)
	{
		return false;
	}
	
	@Override
	public boolean canDoRainSnowIce(Chunk chunk)
	{
		return false;
	}
	
	@Override
    public float calculateCelestialAngle(long par1, float par3)
    {
        return 0.5F;
    }
	
	@Override
	public boolean getWorldHasVoidParticles()
	{
		return false;
	}
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return 0;
	}
	
	@Override
	public double getHorizon()
	{
		return -1024D;
	}
	
	@Override
	public IRenderHandler getSkyRenderer() {
		return new SpaceSkyRenderer();
	}

	@Override
	public IRenderHandler getCloudRenderer() {
		return new SpaceCloudRenderer();
	}
	
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
    {
        return Vec3.createVectorHelper(0D, 0D, 0D);
    }
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isSkyColored()
	{
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float par1, float par2)
    {
        return Vec3.createVectorHelper(0D, 0D, 0D);
    }
	
	@SideOnly(Side.CLIENT)
	public boolean renderClouds()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderStars()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public double getVoidFogYFactor()
	{
		return 1.0D;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public float getStarBrightness(float f)
	{
		return 1.5F;
	}
	
	//TODO needed?
	@SideOnly(Side.CLIENT)
	public boolean renderEndSky()
	{
		return true;
	}
}