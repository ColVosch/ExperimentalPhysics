package colVosch.experimentalPhysics.util;

import colVosch.experimentalPhysics.blocks.IConnectedTexture;
import net.minecraft.world.IBlockAccess;

public class ConnectedTextureHelper
{
	
	/**Returns the index of the correct texture. The index represents a sum of flags:<br>
	 * 1: open to the right<br>
	 * 2: open to the bottom<br>
	 * 4: open to the left<br>
	 * 8: open to the top<br>
	 * So for example 6 is referring to the texture used when connecting to the bottom and left block
	 * @return The icon
	 */
	public static int getIconIndex(IBlockAccess access, Position connectorPos, int blockSide)
	{
		assert connectorPos.getBlock(access) instanceof IConnectedTexture
			: "Invalid arguments: 'connectorPos' must describe an IConnectedTexture";
		
		boolean[] connectingSides;
		connectingSides = new boolean[4];
		int x = connectorPos.x;
		int y = connectorPos.y;
		int z = connectorPos.z;
		switch(blockSide)
		{
			case 0: case 1:
			{
				connectingSides[0] = shouldBlockSidesConnect(access, connectorPos, x+1, y, z);
				connectingSides[1] = shouldBlockSidesConnect(access, connectorPos, x, y, z+1);
				connectingSides[2] = shouldBlockSidesConnect(access, connectorPos, x-1, y, z);
				connectingSides[3] = shouldBlockSidesConnect(access, connectorPos, x, y, z-1);
				break;
			}
			case 2:
			{
				connectingSides[0] = shouldBlockSidesConnect(access, connectorPos, x-1, y, z);
				connectingSides[1] = shouldBlockSidesConnect(access, connectorPos, x, y-1, z);
				connectingSides[2] = shouldBlockSidesConnect(access, connectorPos, x+1, y, z);
				connectingSides[3] = shouldBlockSidesConnect(access, connectorPos, x, y+1, z);
				break;
			}
			case 3:
			{
				connectingSides[0] = shouldBlockSidesConnect(access, connectorPos, x+1, y, z);
				connectingSides[1] = shouldBlockSidesConnect(access, connectorPos, x, y-1, z);
				connectingSides[2] = shouldBlockSidesConnect(access, connectorPos, x-1, y, z);
				connectingSides[3] = shouldBlockSidesConnect(access, connectorPos, x, y+1, z);
				break;
			}
			case 4:
			{
				connectingSides[0] = shouldBlockSidesConnect(access, connectorPos, x, y, z+1);
				connectingSides[1] = shouldBlockSidesConnect(access, connectorPos, x, y-1, z);
				connectingSides[2] = shouldBlockSidesConnect(access, connectorPos, x, y, z-1);
				connectingSides[3] = shouldBlockSidesConnect(access, connectorPos, x, y+1, z);
				break;
			}
			case 5:
			{
				connectingSides[0] = shouldBlockSidesConnect(access, connectorPos, x, y, z-1);
				connectingSides[1] = shouldBlockSidesConnect(access, connectorPos, x, y-1, z);
				connectingSides[2] = shouldBlockSidesConnect(access, connectorPos, x, y, z+1);
				connectingSides[3] = shouldBlockSidesConnect(access, connectorPos, x, y+1, z);
				break;
			}
		}
		return MathHelper.boolArrayToInt(connectingSides);
	}
	
	private static boolean shouldBlockSidesConnect(IBlockAccess access, Position connectorPos, int connecteeX, int connecteeY, int connecteeZ)
	{
		Position connecteePos = new Position(connecteeX, connecteeY, connecteeZ);
		return (connecteePos.getBlock(access) instanceof IConnectedTexture) 
				&& ((IConnectedTexture) connectorPos.getBlock(access)).canTextureConnect(access, connectorPos, connecteePos);
	}
}
