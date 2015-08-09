package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.settings.SubstanceProperty;
import colVosch.experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerInsertionLock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAdvancedRefinerInsertionLock extends BlockAdvancedRefinerPart implements ITileEntityProvider
{

	public static final String NAME = "blockAdvancedRefinerInsertionLock";

	public BlockAdvancedRefinerInsertionLock()
	{
		super(SubstanceProperty.IRON);
		setBlockTextureName(ExperimentalPhysics.MODID + ":advancedRefinerInputTemp");
		GameRegistry.registerTileEntity(TileEntityAdvancedRefinerInsertionLock.class, "tileEntityAdvancedRefinerInsertionLock");
	}

//	@Override
//	public void registerBlockIcons(IIconRegister iconRegister)
//	{
//		icons[0] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerInputTemp");
//	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAdvancedRefinerInsertionLock();
	}
	

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float par1, float par2, float par3)
    {
		player.openGui(ExperimentalPhysics.instance, 1, world, x, y, z);
		return true;
	}
	
	@Override
	public void form(World world, int x, int y, int z, int xCore, int yCore, int zCore)
	{
		super.form(world, x, y, z, xCore, yCore, zCore);
		((TileEntityAdvancedRefinerInsertionLock) world.getTileEntity(x, y, z)).form(xCore, yCore, zCore);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {	
		((TileEntityAdvancedRefinerInsertionLock) world.getTileEntity(x, y, z)).dropItems();
		super.breakBlock(world, x, y, z, block, meta);
    }
}
