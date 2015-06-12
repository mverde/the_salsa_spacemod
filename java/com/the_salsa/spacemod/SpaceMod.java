package com.the_salsa.spacemod;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = SpaceMod.MODID, version = SpaceMod.VERSION)
public class SpaceMod
{
	//Identifiers
	public static final String MODID = "the_salsa_spacemod";
    public static final String VERSION = "1.0";
    
    //Materials
    public static ToolMaterial plasma = EnumHelper.addToolMaterial("plasma", 3, 1000, 10.0F, 6.0F, 6);
    
    //Items
    public static Item blasterPistol;
    public static Item gasCanister;
    public static Item emptyCanister;
    public static Item blasterRifle;
    public static Item spaceTeleporter;
    public static Item plasmaSaberBlue;
    public static Item plasmaSaberGreen;
    public static Item plasmaSaberRed;
    public static Item plasmaSaberPurple;
    public static Item plasmaSaberYellow;
    public static Item plasmaSaberRainbow;
    
    //biomes
    public static BiomeGenBase spacebiome = new BiomeSpace(25).setBiomeName("Space");
    
    //dimensions
    public static int dimensionSpace = 2;
    
    @SidedProxy(clientSide = "com.the_salsa.spacemod.ClientProxy", serverSide = "com.the_salsa.spacemod.CommonProxy")
    public static CommonProxy proxy;
    
    SpaceEventHandler handler = new SpaceEventHandler();
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//Register Entity renderers
    	proxy.registerRendering();
    	
    	//Initialize new Items
    	blasterPistol = new ItemBlasterPistol("blasterPistol");
    	gasCanister = new ItemSpaceGeneric("gasCanister", 1);
    	emptyCanister = new ItemSpaceGeneric("emptyCanister", 16);
    	blasterRifle = new ItemBlasterRifle("blasterRifle");
    	spaceTeleporter = new ItemSpaceTeleporter("spaceTeleporter");
    	plasmaSaberBlue = new ItemPlasmaSaberBlue(plasma, "plasmaSaberBlue");
    	plasmaSaberGreen = new ItemPlasmaSaberGreen(plasma, "plasmaSaberGreen");
    	plasmaSaberRed = new ItemPlasmaSaberRed(plasma, "plasmaSaberRed");
    	plasmaSaberPurple = new ItemPlasmaSaberPurple(plasma, "plasmaSaberPurple");
    	plasmaSaberYellow = new ItemPlasmaSaberYellow(plasma, "plasmaSaberYellow");
    	plasmaSaberRainbow = new ItemPlasmaSaberRainbow(plasma, "plasmaSaberRainbow");
    	
    	//Register new Items
    	GameRegistry.registerItem(blasterPistol, "BlasterPistol");
    	GameRegistry.registerItem(gasCanister, "GasCanister");
    	GameRegistry.registerItem(emptyCanister, "EmptyCanister");
    	GameRegistry.registerItem(blasterRifle, "BlasterRifle");
    	GameRegistry.registerItem(spaceTeleporter, "SpaceTeleporter");
    	GameRegistry.registerItem(plasmaSaberBlue, "PlasmaSaberBlue");
    	GameRegistry.registerItem(plasmaSaberGreen, "PlasmaSaberGreen");
    	GameRegistry.registerItem(plasmaSaberRed, "PlasmaSaberRed");
    	GameRegistry.registerItem(plasmaSaberPurple, "PlasmaSaberPurple");
    	GameRegistry.registerItem(plasmaSaberYellow, "PlasmaSaberYellow");
    	GameRegistry.registerItem(plasmaSaberRainbow, "PlasmaSaberRainbow");
    	
    	//Register new Entities
    	EntityRegistry.registerModEntity(EntityBlasterBolt.class, "blasterbolt", 0, this, 80, 3, true);
    	
    	//Register new Biomes
    	BiomeDictionary.registerBiomeType(spacebiome, BiomeDictionary.Type.DRY);
    	//BiomeManager.addSpawn

    	//Register new Dimensions
    	DimensionManager.registerProviderType(dimensionSpace, WorldProviderSpace.class, false);
    	DimensionManager.registerDimension(dimensionSpace, dimensionSpace);
    	
    	//Register Item renderers
    	proxy.registerItemRenders();
    	
    	//Register EventHandler
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
    }
}
