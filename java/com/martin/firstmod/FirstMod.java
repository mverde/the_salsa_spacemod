package com.martin.firstmod;

import com.the_salsa.spacemod.BiomeSpace;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = FirstMod.MODID, version = FirstMod.VERSION)
public class FirstMod
{
    public static final String MODID = "martin_firstmod";
    public static final String VERSION = "1.0";
    public static Item key;
    public static Item martDust;
    public static Item martIngot;
    public static Item berry;
    public static Item strawmlette;
	public static ToolMaterial martium = EnumHelper.addToolMaterial("martium", 3, 1000, 9.5F, 3.5F, 10);
	public static Item martPick;
	public static Item martAxe;
	public static Item martShovel;
	public static Item martHoe;
	public static Item martSword;
	public static Item martPaxel;
	public static Item martSpax;
	public static ArmorMaterial martArmor = EnumHelper.addArmorMaterial("martArmor", 20, new int[] { 3, 7, 6, 3 }, 10);
    public static Item martHelmet;
    public static Item martChest;
    public static Item martLegs;
    public static Item martBoots;
    public static Item baconBellyBuster;
    public static Item martSeed;
    public static Item martThrow;
    public static Block martStone;
    public static Block martPlant;
    public static Block martTE;
    
    MartEventHandler handler = new MartEventHandler();
    static int startEntityId = 300;
    
    @SidedProxy(clientSide = "com.martin.firstmod.ClientProxyMart", serverSide = "com.martin.firstmod.CommonProxyMart")
    public static CommonProxyMart proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.registerRendering();
    	
    	//New items
    	key = new ItemKey();
    	martDust = new ItemMartGeneric("martDust");
    	martIngot = new ItemMartGeneric("martIngot");
    	berry = new ItemBerry(3, 0.3F, true, "berry");
    	strawmlette = new ItemStrawmlette(6, 1F, false, "strawmlette");
    	baconBellyBuster = new ItemBaconSandwich(4, 0.2F, false, "baconBellyBuster");
    	martPick = new ItemMartPick(martium, "martPick");
    	martAxe = new ItemMartAxe(martium, "martAxe");
    	martShovel = new ItemMartShovel(martium, "martShovel");
    	martHoe = new ItemMartHoe(martium, "martHoe");
    	martSword = new ItemMartSword(martium, "martSword");
    	martPaxel = new ItemMartPaxel(martium, "martPaxel");
    	martSpax = new ItemMartSpax(martium, "martSpax");
    	martHelmet = new ItemMartArmor(martArmor, 0, "martHelmet");
    	martChest = new ItemMartArmor(martArmor, 1, "martChest");
    	martLegs = new ItemMartArmor(martArmor, 2, "martLegs");
    	martBoots = new ItemMartArmor(martArmor, 3, "martBoots");
    	martThrow = new ItemMartThrow("martThrow");
    	//New Blocks
    	martStone = new BlockMartStone("martStone");
    	martPlant = new BlockMartPlant();
    	martSeed = new ItemMartSeed(martPlant);
    	martTE = new BlockMartTE();
    	
    	
    	GameRegistry.registerItem(key, "Key");
    	GameRegistry.registerItem(martDust, "MartDust");
    	GameRegistry.registerItem(martIngot,  "MartIngot");
    	GameRegistry.registerItem(berry, "Berry");
    	GameRegistry.registerItem(strawmlette, "Strawmlette");
    	GameRegistry.registerItem(martPick, "MartPickaxe");
    	GameRegistry.registerItem(martAxe, "MartAxe");
    	GameRegistry.registerItem(martShovel, "MartShovel");
    	GameRegistry.registerItem(martHoe, "MartHoe");
    	GameRegistry.registerItem(martSword, "MartSword");
    	GameRegistry.registerItem(martPaxel, "MartPaxel");
    	GameRegistry.registerItem(martSpax, "MartSpax");
    	GameRegistry.registerItem(martHelmet, "MartHelmet");
    	GameRegistry.registerItem(martChest, "MartChest");
    	GameRegistry.registerItem(martLegs, "MartLegs");
    	GameRegistry.registerItem(martBoots, "MartBoots");
    	GameRegistry.registerItem(baconBellyBuster, "BaconBellyBuster");
    	GameRegistry.registerItem(martSeed, "MartSeed");
    	GameRegistry.registerItem(martThrow, "MartThrowable");
    	//New Blocks
    	GameRegistry.registerBlock(martStone, ItemMartStone.class, "MartStone"); //register metadata blocks like this
    	GameRegistry.registerBlock(martPlant,  "MartPlant");
    	GameRegistry.registerBlock(martTE, "MartTE");
    	//Misc
    	GameRegistry.registerTileEntity(TileEntityMart.class, "TE_martTE");
    	GameRegistry.registerWorldGenerator(handler, 0);
    	OreDictionary.registerOre("ingotMart", new ItemStack(martIngot));	//must be after initialization and registration
    	//EntityRegistry.registerModEntity(EntityMartMob.class, "martmob", 0, this, 80, 3, true);
    	//registerEntityEgg(EntityMartMob.class, 0xd8bb9d, 0xa63c1a);
    	//EntityRegistry.registerModEntity(EntityMartThrowable.class, "martthrow", 1, this, 80, 3, true);
    	
    	//for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; i++)
    	//{
    		//if (BiomeGenBase.getBiomeGenArray()[i] != null && !(BiomeGenBase.getBiomeGenArray()[i] instanceof BiomeSpace))
    		//{
    			//EntityRegistry.addSpawn(EntityMartMob.class, 10, 1, 3, EnumCreatureType.monster, BiomeGenBase.getBiomeGenArray()[i]);
    		//}
    	//}
    	
    	proxy.registerItemRenders();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//Recipes
    	GameRegistry.addRecipe(new ItemStack(Items.apple), 
    		"XXX",
    		"XXX",
    		"XXX",
    		'X', Blocks.leaves
    	);
    	GameRegistry.addRecipe(new ItemStack(Items.arrow, 16),
    		" X",
    		"YZ",
    		'X', Blocks.gravel,
    		'Y', Blocks.planks,
    		'Z', Blocks.leaves
    	);
    	GameRegistry.addRecipe(new ShapedOreRecipe(this.martLegs,
    		"XXX",
    		"X X",
    		"   ",
    		'X', "ingotMart"
    	));
    	GameRegistry.addRecipe(new ShapedOreRecipe(this.martChest,
        		"X X",
        		"XXX",
        		"XXX",
        		'X', "ingotMart"
        ));
    	GameRegistry.addRecipe(new ShapedOreRecipe(this.martLegs,
        		"XXX",
        		"X X",
        		"X X",
        		'X', "ingotMart"
        ));
    	GameRegistry.addRecipe(new ShapedOreRecipe(this.martBoots,
        		"   ",
        		"X X",
        		"X X",
        		'X', "ingotMart"
        ));
    	GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 64),
    		Blocks.obsidian, Blocks.obsidian
    	);
    	GameRegistry.addShapelessRecipe(new ItemStack(baconBellyBuster, 64), Items.bread, Items.cooked_porkchop);
    	GameRegistry.addSmelting(Items.coal, new ItemStack(Items.diamond), 0.2F);
    	GameRegistry.addSmelting(this.martDust, new ItemStack(this.martIngot), 0.2F);
    	
    	ItemStack enchantedSword = new ItemStack(Items.stone_sword);
    	enchantedSword.addEnchantment(Enchantment.sharpness, 1);
    	GameRegistry.addShapelessRecipe(enchantedSword,
    		Items.flint, Items.stone_sword
    	);
    	
    	//Dungeon changes
    	DungeonHooks.removeDungeonMob("Spider");
    	DungeonHooks.addDungeonMob("Creeper", 100);
    	ChestGenHooks.removeItem(ChestGenHooks.DUNGEON_CHEST, new ItemStack(Items.saddle));
    	ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(new ItemStack(Items.diamond), 2, 10, 1));
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
