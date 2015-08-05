package colVosch.experimentalPhysics.entitys;

import colVosch.experimentalPhysics.reference.Particles;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityEndStoneAsteroid extends Entity
{

	public EntityEndStoneAsteroid(World world)
	{
		super(world);
		setSize(0.5F, 0.5F);
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
			boolean isOnGround = !(worldObj.isAirBlock((int) posX, (int) posY - 1, (int) posZ));
			boolean isImmobile = Math.abs(motionX) < 0.01D && Math.abs(motionY) < 0.01D && Math.abs(motionZ) < 0.01D;
			if (isOnGround || isImmobile) {
				makeImpact();
			}
		} else {
			for (int i = 0; i < 50; i ++) {
				//worldObj.spawnParticle("portal", posX + 0.5F, posY + 0.5F, posZ + 0.5F, rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
				worldObj.spawnParticle(Particles.DEPTHSUSPEND, posX + rand.nextDouble(), posY + rand.nextDouble(), posZ + rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), rand.nextDouble());
				worldObj.spawnParticle(Particles.SMOKE, posX + 0.5F, posY + 0.5F, posZ + 0.5F, rand.nextDouble(), rand.nextDouble(), rand.nextDouble());				
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
