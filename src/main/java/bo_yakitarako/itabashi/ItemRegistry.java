package bo_yakitarako.itabashi;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemRegistry {

	public Item register(String name, CreativeTabs creativeTab) {
		Item item = new Item().setUnlocalizedName("itabashi_" + name).setTextureName("itabashi:" + name)
				.setCreativeTab(creativeTab);
		GameRegistry.registerItem(item, name);
		return item;
	}
}
