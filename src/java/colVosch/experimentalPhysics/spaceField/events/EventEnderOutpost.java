package colVosch.experimentalPhysics.spaceField.events;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class EventEnderOutpost implements ISpaceFieldEvent
{

	@Override
	public void trigger(World world, Position pos)
	{
		world.createExplosion((Entity) null, pos.x, pos.y, pos.z, 8, true);
		world.setBlock(pos.x, pos.y, pos.z, Blocks.mob_spawner);
		TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(pos.x, pos.y, pos.z);
		spawner.func_145881_a().setEntityName("Enderman");
	}

	@Override
	public boolean canTrigger(World world, Position pos, float strength)
	{
		return pos.getBlock(world) != Blocks.air;
	}

}
