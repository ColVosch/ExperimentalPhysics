package colVosch.experimentalPhysics.spaceField.events;

import colVosch.experimentalPhysics.entitys.EntityEndStoneAsteroid;
import colVosch.experimentalPhysics.util.Position;
import colVosch.experimentalPhysics.util.WorldHelper;
import net.minecraft.world.World;

public class EventEndStoneAsteroid implements ISpaceFieldEvent
{

	@Override
	public void trigger(World world, Position pos)
	{
		EntityEndStoneAsteroid asteroid = new EntityEndStoneAsteroid(world, pos.x, pos.y, pos.z);
		world.spawnEntityInWorld(asteroid);
	}

	@Override
	public boolean canTrigger(World world, Position pos, float strength) {		
		return pos.y - WorldHelper.findGround(world, pos) <= 10 && pos.isAirBlock(world);
	}

}
