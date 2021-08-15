package bo_yakitarako.itabashi.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHasCraftDurability extends Item {

	public ItemHasCraftDurability(int durability) {
		super();
		this.setMaxDamage(durability);
		this.setMaxStackSize(1);
		this.setNoRepair();
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
		return false;
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack) {
		if (itemStack != null && itemStack.getItem() == this) {
			itemStack.setItemDamage(itemStack.getItemDamage() + 1);
		}
		return itemStack;
	}

}
