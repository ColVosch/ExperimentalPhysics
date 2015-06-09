package colVosch.experimentalPhysics.spaceField;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.nbt.NBTTagCompound;

public class TensionPoint extends Position
{

	public int strength;

	public static TensionPoint tensionPointFromNBT(NBTTagCompound compound)
	{
		int strength;
		strength = compound.getInteger("strength");
		return new TensionPoint(Position.positionFromNBT(compound), strength);
	}
	
	public TensionPoint(Position pos, int strength)
	{
		super(pos.x, pos.y, pos.z);
		this.strength = strength < 50 ? strength : 50;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("strength", strength);
		return compound;
	}
}
