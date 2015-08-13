package colVosch.experimentalPhysics.spaceField;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;

public class TensionPoint extends Position
{
	public final int strength;

	public static TensionPoint tensionPointFromNBT(NBTTagCompound compound)
	{
		if (compound == null)
			throw new IllegalArgumentException("The passed compound can not be null");
		if (!compound.hasKey("strength"))
			throw new IllegalArgumentException("The passed compound must contain a strength value");
		if (!(compound.getTag("strength") instanceof NBTTagInt))
			throw new IllegalArgumentException("The passed compound seems to be corrupted,"
					+ "data is of an incorrect type");

		int readStrength = compound.getInteger("strength");
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
				&& obj instanceof TensionPoint
				&& ((TensionPoint)obj).strength == this.strength;
	}
	
	public boolean hasEqualPosition(Position pos)
	{
		return super.equals(pos);
	}
}
