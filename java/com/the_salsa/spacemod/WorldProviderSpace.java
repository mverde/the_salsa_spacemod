package com.the_salsa.spacemod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

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
		return true;
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
	
	@SideOnly(Side.CLIENT)
	public boolean renderClouds()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderVoidFog()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderStars()
	{
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(World world, float f) {
		return 1.5F;
	}
	
	//TODO needed?
	@SideOnly(Side.CLIENT)
	public boolean renderEndSky()
	{
		return true;
	}
}