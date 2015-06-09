package colVosch.experimentalPhysics.spaceField.events;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.world.World;

public interface ISpaceFieldEvent
{
	/**Triggers a space field event at the given position
	 * @param world
	 * @param pos
	 * @return Whether or not the space field event succeeded.
	 */
	public void trigger(World world, Position pos);
	public boolean canTrigger(World world, Position pos, float strength);

}
