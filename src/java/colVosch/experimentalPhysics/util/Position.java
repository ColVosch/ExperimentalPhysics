package colVosch.experimentalPhysics.util;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

/**A data object storing a set of three integer coordinates
 * @author ColVosch
 *
 */
public class Position
{
	public final int x;
	public final int y;
	public final int z;
	
	public static Position positionFromNBT(NBTTagCompound compound)
	{
		if (compound == null)
			throw new IllegalArgumentException("The passed compound can not be null");
		if (!(compound.hasKey("x") && compound.hasKey("y") && compound.hasKey("z")))
			throw new IllegalArgumentException("Passed tag compound must contain x, y and z coordinates");
		
		int x, y, z;
		NBTBase xBase = compound.getTag("x");
		NBTBase yBase = compound.getTag("y");
		NBTBase zBase = compound.getTag("z");
		
		if (!(xBase  instanceof NBTTagInt
				&& yBase  instanceof NBTTagInt
				&& zBase  instanceof NBTTagInt))
			throw new IllegalArgumentException("The passed compound seems to be corrupted,"
					+ "data is of an incorrect type");
		
		x = ((NBTTagInt)xBase).func_150287_d();
		y = ((NBTTagInt)yBase).func_150287_d();
		z = ((NBTTagInt)zBase).func_150287_d();
		return new Position(x, y, z);
	}
	
	public Position(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position(int[] coords)
	{
		this(coords[0], coords[1], coords[2]);

		if (coords.length != 3)
			throw new IllegalArgumentException("The passed array must have a length of exactly three");
	
	}
	
	public int[] toIntArray()
	{
		int[] coords = {x, y, z};
		return coords;
	}

	public float getDistance(Position pos)
	{
		float dist = (float) sqrt(pow(this.x - pos.x, 2) + pow(this.y - pos.y, 2) + pow(this.z - pos.z, 2));
		return dist;
	}
	
	public TileEntity getTileEntity(IBlockAccess world)
	{
		return world.getTileEntity(x, y, z);
	}
	
	public Block getBlock(IBlockAccess world)
	{
		return world.getBlock(x, y, z);
	}
	
	public boolean isAirBlock(IBlockAccess world)
	{
		return world.isAirBlock(x, y, z);
	}
	
	public int getMeta(IBlockAccess world)
	{
		return world.getBlockMetadata(x, y, z);
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setInteger("x", x);
		compound.setInteger("y", y);
		compound.setInteger("z", z);
		return compound;
	}
	
	@Override
	public String toString()
	{
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + ", " + Integer.toString(z) + ")";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Position)
		{
			Position pos = (Position)obj;
			if (this.x == pos.x
					&& this.y == pos.y
					&& this.z == pos.z) {
				return true;
			}
		}
		return false;
	}
}
