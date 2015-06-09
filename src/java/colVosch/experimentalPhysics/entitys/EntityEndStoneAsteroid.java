package colVosch.experimentalPhysics.entitys;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityEndStoneAsteroid extends Entity
{

	public EntityEndStoneAsteroid(World world)
	{
		super(world);
		setSize(2.5F, 2.5F);
		
	}
	
	public EntityEndStoneAsteroid(World world, double x, double y, double z)
	{
		this(world);
		setPosition(x, y, z);
	}

	@Override
	protected void entityInit() 
	{
		setVelocity(rand.nextDouble() - 0.5D, rand.nextDouble() * - 3, rand.nextDouble() - 0.5D);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {}

	@Override
	public void onEntityUpdate()
	{
		moveEntity(motionX, motionY, motionZ);
		
		if (!worldObj.isRemote) {
			Block block = worldObj.getBlock((int) posX, (int) posY - 1, (int) posZ);
			boolean isOnGround = !(block.isAir(worldObj, (int) posX, (int) posY, (int) posZ) || block == Blocks.air);
			boolean isImmobile = Math.abs(motionX) < 0.01D && Math.abs(motionY) < 0.01D && Math.abs(motionZ) < 0.01D;
			if (isOnGround || isImmobile) {
				makeImpact();
			}
		}

		motionY -= 0.1D;
	}
	
	private void makeImpact()
	{
		worldObj.createExplosion(this, posX, posY, posZ, 5, true);
		this.setDead();
	}
}
