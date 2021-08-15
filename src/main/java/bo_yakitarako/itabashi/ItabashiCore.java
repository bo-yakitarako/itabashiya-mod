package bo_yakitarako.itabashi;

import bo_yakitarako.itabashi.blocks.BlockSpinach;
import bo_yakitarako.itabashi.items.ItemEffected;
import bo_yakitarako.itabashi.items.ItemHasCraftDurability;
import bo_yakitarako.itabashi.items.ItemHotWater;
import bo_yakitarako.itabashi.items.ItemKnife;
import bo_yakitarako.itabashi.items.ItemRamen;
import bo_yakitarako.itabashi.items.ItemSpinachSeed;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = ItabashiCore.MOD_ID, version = ItabashiCore.VERSION)
public class ItabashiCore {
	public static final String MOD_ID = "itabashi";
	public static final String VERSION = "1.1.0";

	public static Item ramen;
	public static Item vessel;
	public static Item vesselFilled;
	public static Item dough;
	public static Item men;
	public static Item bindingMen;
	public static Item boiledMen;
	public static Item wrappedRawPork;
	public static Item wrappedPork;
	public static Item roastedPork;
	public static Item spinachSeed;
	public static Item spinach;
	public static Item boiledSpinach;
	public static Item knife;
	public static Item hotWater;

	public static Block cropSpinach;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ItabashiCore.registerItabashiItems(event);
		ItabashiCore.registerItabashiBlocks(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		final int IGNORE_DAMAGE = 32767;
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.ramen), "SPS", "MMM", " V ", 'S',
				ItabashiCore.boiledSpinach, 'P', ItabashiCore.roastedPork, 'M', ItabashiCore.boiledMen, 'V',
				ItabashiCore.vesselFilled);
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.dough), "MMM", "MWM", "MMM", 'M', Items.wheat, 'W',
				Items.water_bucket);
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.bindingMen), "MMM", "MMM", "MMM", 'M',
				ItabashiCore.men);
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.wrappedRawPork), "TTT", "PPP", "TTT", 'T', Items.string,
				'P', Items.porkchop);
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.vessel), "B B", "BBB", 'B', Items.brick);
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.vesselFilled), "BBB", "CWC", " V ", 'B', Items.bone,
				'C', Items.cooked_chicken, 'W', ItabashiCore.hotWater, 'V', ItabashiCore.vessel);
		GameRegistry.addShapedRecipe(new ItemStack(ItabashiCore.knife), "  I", " I ", "S  ", 'I', Items.iron_sword, 'S',
				Items.stick);
		GameRegistry.addShapelessRecipe(new ItemStack(ItabashiCore.men),
				new ItemStack(ItabashiCore.dough, 1, IGNORE_DAMAGE),
				new ItemStack(ItabashiCore.knife, 1, IGNORE_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(ItabashiCore.roastedPork),
				new ItemStack(ItabashiCore.wrappedPork, 1, IGNORE_DAMAGE),
				new ItemStack(ItabashiCore.knife, 1, IGNORE_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(ItabashiCore.boiledMen),
				new ItemStack(ItabashiCore.bindingMen), new ItemStack(ItabashiCore.hotWater));
		GameRegistry.addShapelessRecipe(new ItemStack(ItabashiCore.wrappedPork),
				new ItemStack(ItabashiCore.wrappedRawPork), new ItemStack(ItabashiCore.hotWater));
		GameRegistry.addShapelessRecipe(new ItemStack(ItabashiCore.boiledSpinach, 3),
				new ItemStack(ItabashiCore.spinach), new ItemStack(ItabashiCore.spinach),
				new ItemStack(ItabashiCore.spinach), new ItemStack(ItabashiCore.hotWater));
		GameRegistry.addSmelting(Items.water_bucket, new ItemStack(ItabashiCore.hotWater), 0);
	}

	private static void registerItabashiItems(FMLPreInitializationEvent event) {
		ItemRegistry registry = new ItemRegistry();
		ItabashiCore.ramen = new ItemRamen();
		GameRegistry.registerItem(ItabashiCore.ramen, ItabashiCore.ramen.getUnlocalizedName());
		ItabashiCore.vessel = registry.register("vessel", CreativeTabs.tabMaterials);
		ItabashiCore.vesselFilled = registry.register("vessel_filled", CreativeTabs.tabMaterials);
		ItabashiCore.dough = new ItemHasCraftDurability(8).setUnlocalizedName("itabashi_dough")
				.setTextureName("itabashi:dough").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(ItabashiCore.dough, ItabashiCore.dough.getUnlocalizedName());
		ItabashiCore.men = registry.register("men", CreativeTabs.tabMaterials);
		ItabashiCore.bindingMen = registry.register("binding_men", CreativeTabs.tabMaterials);
		ItabashiCore.boiledMen = registry.register("boiled_men", CreativeTabs.tabMaterials);
		ItabashiCore.wrappedRawPork = registry.register("wrapped_raw_pork", CreativeTabs.tabMaterials);
		ItabashiCore.wrappedPork = new ItemHasCraftDurability(2).setUnlocalizedName("itabashi_wrapped_pork")
				.setTextureName("itabashi:wrapped_pork").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(ItabashiCore.wrappedPork, ItabashiCore.wrappedPork.getUnlocalizedName());
		ItabashiCore.roastedPork = registry.register("roasted_pork", CreativeTabs.tabFood);
		ItabashiCore.spinachSeed = new ItemSpinachSeed().setUnlocalizedName("itabashi_spinach_seed")
				.setTextureName("itabashi:spinach_seed").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(ItabashiCore.spinachSeed, "itabashi_spinach_seed");
		ItabashiCore.spinach = registry.register("spinach", CreativeTabs.tabMaterials);
		ItabashiCore.boiledSpinach = new ItemEffected().setUnlocalizedName("itabashi_boiled_spinach")
				.setTextureName("itabashi:spinach").setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(ItabashiCore.boiledSpinach, ItabashiCore.boiledSpinach.getUnlocalizedName());
		ItabashiCore.knife = new ItemKnife().setUnlocalizedName("itabashi_knife").setTextureName("itabashi:knife")
				.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(ItabashiCore.knife, ItabashiCore.knife.getUnlocalizedName());
		ItabashiCore.hotWater = new ItemHotWater();
		GameRegistry.registerItem(ItabashiCore.hotWater, ItabashiCore.hotWater.getUnlocalizedName());
	}

	private static void registerItabashiBlocks(FMLPreInitializationEvent event) {
		ItabashiCore.cropSpinach = new BlockSpinach().setBlockName("crop_spinach")
				.setBlockTextureName("itabashi:crop_spinach");
		GameRegistry.registerBlock(ItabashiCore.cropSpinach, "crop_spinach");
	}
}
