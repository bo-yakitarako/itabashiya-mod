package bo_yakitarako.itabashi.blocks;

import java.util.ArrayList;
import java.util.Random;

import bo_yakitarako.itabashi.ItabashiCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSpinach extends BlockCrops implements IPlantable, IGrowable {
	private static final int GROWTH_LIMIT = 4;

	private IIcon[] icons;

	public BlockSpinach() {
		super();
		this.setTickRandomly(true);
		this.setBlockBounds(0f, 0f, 0f, 1f, 0.25f, 1);
		this.setHardness(0);
		this.disableStats();
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.getBlockLightValue(x, y + 1, z) < 9) {
			return;
		}
		int metadata = world.getBlockMetadata(x, y, z);
		if (metadata >= GROWTH_LIMIT) {
			return;
		}
		int growthSpeed = world.getBlock(x, y - 1, z).isFertile(world, x, y - 1, z) ? 3 : 1;
		if (rand.nextInt(6 / growthSpeed) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, metadata + 1, 2);
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean replaceable = world.getBlock(x, y, z).isReplaceable(world, x, y, z);
		boolean stayable = this.canBlockStay(world, x, y, z);
		return replaceable && stayable;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block underBlock = world.getBlock(x, y - 1, z);
		return underBlock.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			int metadata = world.getBlockMetadata(x, y, z);
			this.dropBlockAsItem(world, x, y, z, metadata, 0);
			world.setBlock(x, y, z, Blocks.air, 0, 2);
		}
	}

	@Override
	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.farmland;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	/** 骨粉を使用できるかどうか */
	@Override
	public boolean func_149851_a(World world, int x, int y, int z,
			boolean p_149851_5_) {
		return world.getBlockMetadata(x, y, z) < GROWTH_LIMIT;
	}

	/** 骨粉を適用するかどうか */
	@Override
	public boolean func_149852_a(World world, Random rand, int x, int y,
			int z) {
		return true;
	}

	/** 骨粉を適用する */
	@Override
	public void func_149853_b(World world, Random rand, int x, int y,
			int z) {
		this.func_149863_m(world, x, y, z);
	}

	/** 骨粉を使用したときの成長させる処理 */
	@Override
	public void func_149863_m(World world, int x, int y, int z) {
		int growth = MathHelper.getRandomIntegerInRange(world.rand, 1, 3);
		int newMetadata = world.getBlockMetadata(x, y, z) + growth;
		if (newMetadata > GROWTH_LIMIT) {
			newMetadata = GROWTH_LIMIT;
		}
		world.setBlockMetadataWithNotify(x, y, z, newMetadata, 2);
	}

	/** あたり判定 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y,
			int z) {
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1; // 草と同じやつ
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		Item basic = metadata < GROWTH_LIMIT ? ItabashiCore.spinachSeed : ItabashiCore.spinach;
		drops.add(new ItemStack(basic, 1));
		if (metadata < GROWTH_LIMIT) {
			return drops;
		}
		drops.add(new ItemStack(ItabashiCore.spinachSeed, 1, 0));
		for (int i = 0; i < 2 + fortune; i++) {
			if (world.rand.nextInt(2) == 0) {
				drops.add(new ItemStack(ItabashiCore.spinachSeed, 1, 0));
			}
		}
		return drops;
	}

	/** 種のアイテムを返す */
	@Override
	protected Item func_149866_i() {
		return ItabashiCore.spinachSeed;
	}

	/** 作物のアイテムを返す */
	@Override
	protected Item func_149865_P() {
		return ItabashiCore.spinach;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		return this.func_149866_i();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (metadata < 0) {
			return this.icons[0];
		}
		if (metadata > GROWTH_LIMIT) {
			return this.icons[GROWTH_LIMIT];
		}
		return this.icons[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry) {
		this.icons = new IIcon[GROWTH_LIMIT + 1];
		for (int i = 0; i < icons.length; i++) {
			String iconName = this.getTextureName() + "_stage_" + i;
			this.icons[i] = registry.registerIcon(iconName);
		}
	}
}
