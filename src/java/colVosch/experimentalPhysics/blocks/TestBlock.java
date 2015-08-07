package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.util.Position;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;

public class TestBlock extends ModBlock
{
	
	public TestBlock()
	{
		super(Material.iron);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) 
	{
		super.registerBlockIcons(iconRegister);
		registerStandardConnectedTextureIcons(iconRegister);
	}
	
	@Override
	public boolean canTextureConnect(IBlockAccess access, Position connectorPos, Position connecteePos)
	{
		return connecteePos.getBlock(access) instanceof TestBlock;
	}
}
