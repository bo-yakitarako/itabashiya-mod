package bo_yakitarako.itabashi.items;

import bo_yakitarako.itabashi.ItabashiCore;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemRamen extends ItemFood {

	public ItemRamen() {
		super(14, 100.0F, false);
		this.setUnlocalizedName("itabashi_ramen");
		this.setTextureName("itabashi:ramen");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		super.onEaten(itemStack, world, player);
		return new ItemStack(ItabashiCore.vessel);
	}

	@Override
	protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
		super.onFoodEaten(itemStack, world, player);
		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 36000, 4));
			player.addPotionEffect(new PotionEffect(Potion.resistance.id, 12000, 0));
			player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 12000, 0));
		}
	}
}
