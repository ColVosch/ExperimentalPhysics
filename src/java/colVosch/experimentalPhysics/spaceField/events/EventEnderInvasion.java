package colVosch.experimentalPhysics.spaceField.events;

import colVosch.experimentalPhysics.util.MathHelper;
import colVosch.experimentalPhysics.util.Position;
import colVosch.experimentalPhysics.util.WorldHelper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.World;

public class EventEnderInvasion implements ISpaceFieldEvent
{

	@Override
	public void trigger(World world, Position pos)
	{
		int y = WorldHelper.findGround(world, pos);
		Position triggeringPos = new Position(pos.x, y, pos.z);
		for (int i = 0; i < 6; i++)
		{
			EntityEnderman enderman = new EntityEnderman(world);
			int x = world.rand.nextInt(4) - 2 + triggeringPos.x;
			int z = world.rand.nextInt(4) - 2 + triggeringPos.z;
			enderman.setPosition(x, triggeringPos.y, z);
			world.spawnEntityInWorld(enderman);
		}
	}

	@Override
	public boolean canTrigger(World world, Position pos, float strength) 
	{	
		int y = WorldHelper.findGround(world, pos);
		return MathHelper.diff(pos.y, y) <= 5;
	}

}
