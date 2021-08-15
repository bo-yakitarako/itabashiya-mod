package bo_yakitarako.itabashi.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;

public class ItemHotWater extends ItemEffected {

	public ItemHotWater() {
		this.setUnlocalizedName("itabashi_hot_water");
		this.setTextureName("bucket_water");
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setContainerItem(Items.bucket);
		this.setMaxStackSize(1);
	}
}
