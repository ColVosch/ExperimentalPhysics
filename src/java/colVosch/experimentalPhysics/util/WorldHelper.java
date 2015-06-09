package colVosch.experimentalPhysics.util;

import net.minecraft.world.World;

public class WorldHelper
{
	/**Searches for the nearest non-air-block, looking downwards.
	 * @param world
	 * @param pos
	 * @return The y-coordinate of the nearest air block that is one above a non-air-block, looking downwards.
	 */
	public static int findGround(World world, Position pos)
	{
		int y = -1;
		for (int i = pos.y; (y == -1) && (i >= 0); i--) {
			if (world.isAirBlock(pos.x, i, pos.z) && world.isAirBlock(pos.x, i + 1, pos.z)) {
				y = i + 1;
			}
		}
		return y;
	}
}
