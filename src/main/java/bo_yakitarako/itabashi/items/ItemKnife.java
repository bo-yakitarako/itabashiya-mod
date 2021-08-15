package bo_yakitarako.itabashi.items;

import bo_yakitarako.itabashi.ItabashiCore;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKnife extends ItemHasCraftDurability {

	public ItemKnife() {
		super(64);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x,
			int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlock(x, y, z);
		if (block != Blocks.tallgrass) {
			return false;
		}
		itemStack.damageItem(1, player);
		if (!world.isRemote && world.rand.nextInt(20) == 0) {
			EntityItem dropItem = new EntityItem(world, x, y, z, new ItemStack(ItabashiCore.spinachSeed));
			world.spawnEntityInWorld(dropItem);
		}
		world.setBlockToAir(x, y, z);
		world.playSoundEffect(x, y, z, Block.soundTypeGrass.getBreakSound(),
				(block.stepSound.getVolume() + 1.0F) / 2.0F,
				block.stepSound.getPitch() * 0.8F);
		return true;
	}

}
