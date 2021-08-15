package bo_yakitarako.itabashi.items;

import bo_yakitarako.itabashi.ItabashiCore;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemSpinachSeed extends ItemSeeds implements IPlantable {

	public ItemSpinachSeed() {
		super(ItabashiCore.cropSpinach, Blocks.farmland);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x,
			int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1) {
			return false;
		}

		boolean canEditFromTop = player.canPlayerEdit(x, y, z, side, itemStack)
				&& player.canPlayerEdit(x, y + 1, z, side, itemStack);
		boolean canPlaceCrop = world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this)
				&& world.isAirBlock(x, y + 1, z);

		if (canEditFromTop && canPlaceCrop) {
			world.setBlock(x, y + 1, z, ItabashiCore.cropSpinach);
			itemStack.stackSize -= 1;
			return true;
		}
		return false;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return ItabashiCore.cropSpinach;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}
}
