package thevaderdude.tutorial;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

@Mod(modid = "tm", name = "Tutorial Mod", version = "1.0")
public class TutorialMod {
	
	public static Item itemTable;
	public static Block blockTable;
	public static Item itemCheese;
	public static Item itemGrilledCheese;
	public static Item cheeseSword;
	public static Item cheeseAxe;
	public static Item cheeseShovel;
	public static Item cheesePickaxe;
	public static Item cheeseHoe;
	public static Block blockContainerTest;
	
	public static final Item.ToolMaterial cheeseToolMaterial = EnumHelper.addToolMaterial("cheeseToolMaterial", 4, 2000, 20.0F, 5.0F, 30);
	@Instance("tm")
	public static TutorialMod modInstance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//Item/Block init and registering`
		//Config handling	
		cheeseSword = new ItemCheeseSword(cheeseToolMaterial);
		cheeseAxe = new ItemCheeseAxe(cheeseToolMaterial);
		cheeseShovel = new ItemCheeseShovel(cheeseToolMaterial);
		//cheesePickAxe
		blockContainerTest = new BlockContainerTest(Material.rock).setBlockTextureName("tm:blockcontainertest").setCreativeTab(tabTutorialMod).setBlockName("BlockContainerTest");
		itemTable = new ItemTable().setUnlocalizedName("ItemTable").setTextureName("tm:itemtable").setCreativeTab(tabTutorialMod);
		blockTable = new BlockTable(Material.wood).setBlockName("BlockTable").setBlockTextureName("tm:blocktable").setCreativeTab(tabTutorialMod);
		itemCheese = new ItemFood(2, 0.2F, false).setUnlocalizedName("ItemCheese").setTextureName("tm:itemcheese").setCreativeTab(tabTutorialMod);
		itemGrilledCheese = new ItemFood(6, 0.6F, true).setUnlocalizedName("ItemGrilledCheese").setTextureName("tm:itemgrilledcheese").setCreativeTab(tabTutorialMod);
		GameRegistry.registerItem(itemCheese, itemCheese.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(itemTable, itemTable.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockTable, blockTable.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(itemGrilledCheese, itemGrilledCheese.getUnlocalizedName().substring(5));
		GameRegistry.addSmelting(itemCheese, new ItemStack(itemGrilledCheese), 10.0F);
		GameRegistry.registerBlock(blockContainerTest, blockTable.getUnlocalizedName().substring(5));
	}
	
	@EventHandler 
	public void init(FMLInitializationEvent event) {
		//Proxy, TileEntity, entity, GUI, and Packet Registering
		GameRegistry.addRecipe(new ItemStack(itemTable, 2), new Object[]{"WWW", " W ", " W ", 'W' , Blocks.planks});
		GameRegistry.addRecipe(new ItemStack(blockTable, 10), new Object[] { "CRC", "RRR", "CRC", 'C', Blocks.coal_block, 'R', Items.redstone});
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	public static CreativeTabs tabTutorialMod = new CreativeTabs("tabTutorialMod") {

		@Override
		public Item getTabIconItem() {
			
			return new ItemStack(itemCheese).getItem();
		}
		
	};

}
