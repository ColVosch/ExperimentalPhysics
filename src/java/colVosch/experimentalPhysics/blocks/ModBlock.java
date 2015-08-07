package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.util.ConnectedTextureHelper;
import colVosch.experimentalPhysics.util.Position;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;


/** Class specifying a lot of standard block behavior, as well as implementing connected texture functionality.
 * Sets the texture to MODID:blockName.index, index specifying witch sides are connecting with another texture,
 * for further reference see {@link #registerIcon(IIcon, byte) registerIcon(...)}.<br/>
 * For connected textures to work {@link #canTextureConnect(IBlockAccess, Position, Position) canTextureConnect(...)}
 * needs to be overwritten. Also, in most cases, {@link #registerStandardConnectedTextureIcons(IIconRegister)
 * registerStandardConnectedTextureIcons(...)} should be called from {@link Block#registerBlockIcons(IIconRegister)},
 * witch needs to be overwritten.<br/>
 * If textures are to behave normally, only the texture MODID:blockName.0 needs to be given and nothing else
 * needs to be done.
 * @author ColVosch
 *
 */
public abstract class ModBlock extends Block implements IConnectedTexture
{
	private IIcon[] icons = null;
	
	/**
	 * @return The name of the block used for registration in the GameRegistry and for textures
	 */
	public abstract String getBlockName();
	
	/** Calls constructor of Block, sets block name and texture (according to the pattern MODID:blockname.0) and
	 * registers it in the GameRegistry
	 * @see net.minecraft.block.Block#Block(Material)
	 * @param material
	 */
	public ModBlock(Material material)
	{
		super(material);
		setBlockName(getBlockName());
		setBlockTextureName(ExperimentalPhysics.MODID + ":" + getBlockName() + ".0");
		GameRegistry.registerBlock(this, getBlockName());
	}
	
	/** Defines standard connected texture behavior.
	 * @see net.minecraft.block.Block#getIcon(net.minecraft.world.IBlockAccess, int, int, int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess access, int x, int y, int z, int side)
	{
		if (icons == null)
		{
			return this.blockIcon;
		}
		IIcon connTexIcon = icons[ConnectedTextureHelper.getIconIndex(access, new Position(x, y, z), side)];
		return connTexIcon == null ? this.blockIcon : connTexIcon;
	}
	
	/** This needs to be overwritten to activate connected texture behavior. 
	 * @see colVosch.experimentalPhysics.blocks.IConnectedTexture#canTextureConnect(...)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean canTextureConnect(IBlockAccess access, Position connectorPos, Position connecteePos)
	{
		return false;
	}
	
	/** Add an icon to the ModBlock. If no connected texture behavior is required, only the icon with index 0
	 * needs to be specified.
	 * @param icon The icon to be added
	 * @param index The index of the icon in the range 0 - 15. Used for connected texture behavior. The index
	 * 		is a bitpool of sides that should be "open" to another texture, the 0d bit being at the top, the 
	 * 		1st at the right and so on. The index 6 (= 2 + 4 = 2^1 + 2^2) will therefore specify the icon to
	 * 		be used when in the need of a texture "open" to the right and the bottom.
	 */
	@SideOnly(Side.CLIENT)
	protected void registerIcon(IIcon icon, byte index)
	{
		if (icons == null)
		{
			icons = new IIcon[16];
		}
		if (index >= 0 
				&& index <= 16 
				&& icon != null) {
			icons[index] = icon;
		}
	}
	
	@SideOnly(Side.CLIENT)
	protected void registerStandardConnectedTextureIcons(IIconRegister iconRegister)
	{
		for (byte i = 0; i < 16; i ++) {
			registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID 
					+ ":"
					+ getBlockName()
					+ "."
					+ Byte.toString(i)), i);
		}
	}
}
