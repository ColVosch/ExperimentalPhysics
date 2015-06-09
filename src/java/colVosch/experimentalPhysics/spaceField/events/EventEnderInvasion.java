package colVosch.experimentalPhysics.spaceField.events;

import colVosch.experimentalPhysics.util.MathHelper;
import colVosch.experimentalPhysics.util.Position;
import colVosch.experimentalPhysics.util.WorldHelper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.world.World;

public class EventEnderInvasion implements ISpaceFieldEvent
{

	@Override
	public boolean trigger(World world, Position pos)
	{
		int y = WorldHelper.findGround(world, pos);
		if (MathHelper.diff(pos.y, y) <= 5)
		{
			Position triggeringPos = new Position(pos.x, y, pos.z);
			for (int i = 0; i < 6; i++)
			{
				EntityEnderman enderman = new EntityEnderman(world);
				int x = world.rand.nextInt(4) - 2 + triggeringPos.x;
				int z = world.rand.nextInt(4) - 2 + triggeringPos.z;
				enderman.setPosition(x, triggeringPos.y, z);
				world.spawnEntityInWorld(enderman);
			}
			return true;
		}
		else
		{
			return false;
		}
	}

}
