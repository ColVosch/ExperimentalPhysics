package colVosch.experimentalPhysics.spaceField;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.nbt.NBTTagCompound;

public class TensionPoint extends Position
{
	public final int strength;

	public static TensionPoint tensionPointFromNBT(NBTTagCompound compound)
	{
		int readStrength;
		readStrength = compound.getInteger("strength");
		return new TensionPoint(Position.positionFromNBT(compound), readStrength);
	}
	
	public TensionPoint(Position pos, int strength)
	{
		super(pos.x, pos.y, pos.z);
		// TODO IllegalArgument should maybe throw an exception???
		this.strength = strength < 50 ? strength : 50;
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("strength", strength);
		return compound;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj)
				&& ((TensionPoint)obj).strength == this.strength;
	}
	
	public boolean hasEqualPosition(Position pos)
	{
		return super.equals(pos);
//		return this.x == pos.x
//				&& this.y == pos.y
//				&& this.z == pos.z;
	}
}
