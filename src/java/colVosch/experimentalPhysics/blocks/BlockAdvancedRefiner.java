package colVosch.experimentalPhysics.blocks;

import java.util.List;

import colVosch.experimentalPhysics.constants.SubstanceProperty;
import colVosch.experimentalPhysics.network.PacketController;
import colVosch.experimentalPhysics.network.handlers.HandlerCoords;
import colVosch.experimentalPhysics.network.packets.PacketCoords;
import colVosch.experimentalPhysics.tileEntitys.TileEntityAdvancedRefiner;
import colVosch.experimentalPhysics.tileEntitys.TileEntityStoring;
import colVosch.experimentalPhysics.util.MultiblockHelper;
import colVosch.experimentalPhysics.util.Position;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAdvancedRefiner extends BlockAdvancedRefinerPart implements ITileEntityProvider
{
	public static final String NAME = "blockAdvancedRefiner";
	
	
	public BlockAdvancedRefiner()
	{
		super(SubstanceProperty.IRON);
		GameRegistry.registerTileEntity(TileEntityAdvancedRefiner.class, TileEntityAdvancedRefiner.NAME);
	}
	
	@Override
	public boolean canTextureConnect(IBlockAccess access, Position thisPos, Position connectorPos)
	{
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAdvancedRefiner();
	}
	
	
	public void casingAdded(World world, int x, int y, int z)
	{
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).recalculateConstants();
		if (canForm(world, x, y, z))
		{
			formStructure(world, x, y, z);
		}	
	}

	private boolean canForm(World world, int x, int y, int z)
	{
		boolean canForm = true;
		List<Position> surroundingBlocks = MultiblockHelper.getCube(x, y, z, 1);
		for (Position block : surroundingBlocks)
		{
			canForm = (block.getBlock(world) instanceof BlockAdvancedRefinerPart && (block.getMeta(world) == 0));
			if (!canForm)
			{
				return false;
			}
		}
		return canForm;
	}

	public void formStructure(World world, int x, int y, int z)
	{	
		if (!world.isRemote)
		{
			PacketController.getNetworkWrapper().sendToAll(new PacketCoords(x, y, z, HandlerCoords.ID_FORM_ADVANCED_REFINER));
		}
		for (Position casing : MultiblockHelper.getCube(x, y, z, 1))
		{
			Block block = casing.getBlock(world);
			if (block instanceof BlockAdvancedRefinerPart)
			{
				((BlockAdvancedRefinerPart) block).form(world, casing.x, casing.y, casing.z, x, y, z);
			}
		}
	}

	@Override
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).form();
	}

	
	public void casingRemoved(World world, int x, int y, int z)
	{
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).recalculateConstants();
		unFormStructure(world, x, y, z);
	}

	public void unFormStructure(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			PacketController.getNetworkWrapper().sendToAll(new PacketCoords(x, y, z, HandlerCoords.ID_UNFORM_ADVANCED_REFINER));
		}
		for (Position casing : MultiblockHelper.getCube(x, y, z, 1))
		{
			if (casing.getBlock(world) instanceof BlockAdvancedRefinerPart)
			{
				((BlockAdvancedRefinerPart) casing.getBlock(world)).unForm(world, casing.x, casing.y, casing.z);
			}
		}
	}

	@Override
	public void unForm(World world, int x, int y, int z) 
	{
		super.unForm(world, x, y, z);
		((TileEntityAdvancedRefiner) world.getTileEntity(x, y, z)).unForm();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
		((TileEntityStoring) world.getTileEntity(x, y, z)).dropItems();
		super.breakBlock(world, x, y, z, block, meta);
    }

//	public float getAverageThermalConstant(World world, int x, int y, int z)
//	{
//		float sum = 0f;
//		for (Position p : MultiblockHelper.getCube(x, y, z, 1))
//		{
//			sum += p.getBlock(world) instanceof BlockAdvancedRefinerPart 
//					? ((BlockAdvancedRefinerPart) p.getBlock(world)).getSubstancePropperty().getThermConstant() 
//					: 1;
//		}
//		return sum / 27f;
//	}
	
//	public int getMass(World world, int x, int y, int z)
//	{
//		int sum = 0;
//		for (Position p : MultiblockHelper.getCube(x, y, z, 1))
//		{
//			sum += p.getBlock(world) instanceof BlockAdvancedRefinerPart 
//					? ((BlockAdvancedRefinerPart) p.getBlock(world)).getSubstancePropperty().getMassPerBlock() 
//					: 0;
//		}
//		return sum;
//	}

	public short getMaxStructureHeat(World world, int xCoord, int yCoord, int zCoord)
	{
		short maxHeat = Short.MIN_VALUE;
		for (Position pos : MultiblockHelper.getCube(xCoord, yCoord, zCoord, 1))
		{
			Block block = pos.getBlock(world);
			if (block instanceof BlockAdvancedRefinerPart)
			{
				maxHeat = (short) Math.max(maxHeat, ((BlockAdvancedRefinerPart) block).getSubstancePropperty().getMaxHeat());
			}
		}
		return maxHeat;
	}

	public float getAverageCoolDownConstant(World world, int x, int y, int z)
	{
		float sum = 0f;
		for (Position p : MultiblockHelper.getCube(x, y, z, 1))
		{
			sum += p.getBlock(world) instanceof BlockAdvancedRefinerPart 
					? ((BlockAdvancedRefinerPart) p.getBlock(world)).getCoolDownConstant() 
					: 1;
		}
		return sum / 27f;
	}

}
