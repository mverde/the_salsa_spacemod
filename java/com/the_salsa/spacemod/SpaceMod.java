package com.the_salsa.spacemod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import com.the_salsa.spacemod.OxygenInventoryPacket.InventoryMessageHandler;
import com.the_salsa.spacemod.OxygenPacket.OxygenMessageHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = SpaceMod.MODID, version = SpaceMod.VERSION)
public class SpaceMod
{
	//Identifiers
	public static final String MODID = "the_salsa_spacemod";
	@Instance(MODID)
	public static SpaceMod instance;
    public static final String VERSION = "1.0";
    public static int startEntityId = 500;
    private static int modGuiIndex = 0;
    public static final int GUI_CUSTOM_INV = modGuiIndex++;
    
    //Materials
    public static ToolMaterial plasma = EnumHelper.addToolMaterial("plasma", 3, 1000, 10.0F, 8.0F, 0);
    
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
    public static Item maneuverGear;
    public static Item oxygenHelmet;
    public static Item rocketLauncher;
    public static Item basicShip;
    
    //Blocks
    public static Block spaceRock;
    
    //ArmorMaterials
    public static ArmorMaterial maneuverArmor = EnumHelper.addArmorMaterial("maneuverArmor", 15, new int[] { 1, 3, 0, 0 }, 0);
    
    //biomes
    public static BiomeGenBase spacebiome = new BiomeSpace(25).setBiomeName("Space");
    
    //dimensions
    public static int dimensionSpace = 2;
    
    //proxies
    @SidedProxy(clientSide = "com.the_salsa.spacemod.ClientProxy", serverSide = "com.the_salsa.spacemod.CommonProxy")
    public static CommonProxy proxy;
    
    //event handlers
    SpaceEventHandler handler = new SpaceEventHandler(Minecraft.getMinecraft());
    //KeyHandler keyHandler = new KeyHandler();
    
    //network channels
    public static SimpleNetworkWrapper wrapper;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//Register Entity renderers
    	proxy.registerRendering();
    	
    	//Register Packets
    	wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("oxygenchannel");
    	wrapper.registerMessage(OxygenMessageHandler.class, OxygenPacket.class, 0, Side.CLIENT);
    	wrapper.registerMessage(InventoryMessageHandler.class, OxygenInventoryPacket.class, 1, Side.SERVER);
    	
    	//Initialize new Items
    	blasterPistol = new ItemBlasterPistol("blasterPistol", 4.0D, 40.0D, 10.0F, 10, 20, 20, 1000);
    	gasCanister = new ItemSpaceGeneric("gasCanister", 1);
    	emptyCanister = new ItemSpaceGeneric("emptyCanister", 16);
    	blasterRifle = new ItemBlasterRifle("blasterRifle", 5.0D, 60.0D, 8.0F, 3, 40, 40, 1500);
    	spaceTeleporter = new ItemSpaceTeleporter("spaceTeleporter");
    	plasmaSaberBlue = new ItemPlasmaSaberBlue(plasma, "plasmaSaberBlue");
    	plasmaSaberGreen = new ItemPlasmaSaberGreen(plasma, "plasmaSaberGreen");
    	plasmaSaberRed = new ItemPlasmaSaberRed(plasma, "plasmaSaberRed");
    	plasmaSaberPurple = new ItemPlasmaSaberPurple(plasma, "plasmaSaberPurple");
    	plasmaSaberYellow = new ItemPlasmaSaberYellow(plasma, "plasmaSaberYellow");
    	plasmaSaberRainbow = new ItemPlasmaSaberRainbow(plasma, "plasmaSaberRainbow");
    	maneuverGear = new ItemManeuverGear(maneuverArmor, 1, 1, "maneuverGear");
    	oxygenHelmet = new ItemOxygenHelmet(maneuverArmor, 1, 0, "oxygenHelmet");
    	rocketLauncher = new ItemRocketLauncher("rocketLauncher", 0.5D, 70.0D, 12.0F, 30, 60, 2, 500);
    	basicShip = new ItemBasicShip("basicShip");
    	
    	//Initialize new Blocks
    	spaceRock = new BlockSpaceRock();
    	
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
    	GameRegistry.registerItem(maneuverGear, "maneuverGear");
    	GameRegistry.registerItem(oxygenHelmet, "OxygenHelmet");
    	GameRegistry.registerItem(rocketLauncher, "RocketLauncher");
    	GameRegistry.registerItem(basicShip, "basicShip");
    	
    	//Register new Blocks
    	GameRegistry.registerBlock(spaceRock, "SpaceRock");
    	
    	//Register new Entities
    	EntityRegistry.registerModEntity(EntityBlasterBolt.class, "blasterbolt", 0, this, 80, 3, true);
    	EntityRegistry.registerModEntity(EntityTrooperBase.class, "entitytrooper", 1, this, 80, 3, true);
    	EntityRegistry.registerModEntity(EntityPlasmaRocket.class, "plasmarocket", 2, this, 80, 3, true);
    	EntityRegistry.registerModEntity(EntityShipBlasterBolt.class, "shipblasterbolt", 3, this, 80, 3, true);
    	EntityRegistry.registerModEntity(EntityBasicShip.class, "basicship", 4, this, 80, 3, true);
    	registerEntityEgg(EntityTrooperBase.class, 0x000000, 0xffffff);
		EntityRegistry.addSpawn(EntityTrooperBase.class, 3, 1, 3, EnumCreatureType.ambient, spacebiome);
    	
    	//Register new Biomes
    	BiomeDictionary.registerBiomeType(spacebiome, BiomeDictionary.Type.DRY);

    	//Register new WorldGenerators
    	GameRegistry.registerWorldGenerator(handler, 0);

    	//Register new Dimensions
    	DimensionManager.registerProviderType(dimensionSpace, WorldProviderSpace.class, false);
    	DimensionManager.registerDimension(dimensionSpace, dimensionSpace);
    	
    	//Register Item renderers
    	proxy.registerItemRenders();
    	
    	//Register EventHandlers
    	MinecraftForge.EVENT_BUS.register(handler);
    	FMLCommonHandler.instance().bus().register(handler);
		//FMLCommonHandler.instance().bus().register(keyHandler);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//Register GuiHandler
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
    }
    
    @SuppressWarnings("unchecked")
    public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor)
    {
    	int id = getUniqueEntityId();
    	EntityList.IDtoClassMapping.put(id,  entity);
    	EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, primaryColor, secondaryColor));
    }
    
    public static int getUniqueEntityId()
    {
    	do
    	{
    		startEntityId++;
    	}
    	while (EntityList.getStringFromID(startEntityId) != null);
    	
    	return startEntityId;
    }
}
