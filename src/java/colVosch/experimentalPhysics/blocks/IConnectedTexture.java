package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.world.IBlockAccess;

public interface IConnectedTexture
{
	
	/**Used to determine if the texture of a block (the connector) can connect to another (the connectee)
	 * @param access the world both blocks are in
	 * @param connectorPos the position this block
	 * @param connecteePos the position of the block this block should connect to
	 * @return whether the textures can connect or not
	 */
	public abstract boolean canTextureConnect(IBlockAccess access, Position connectorPos, Position connecteePos);
}
