package colVosch.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import colVosch.experimentalPhysics.util.Position;
import colVosch.test.MinecraftTest;
import net.minecraft.nbt.NBTTagCompound;

public class TestPosition extends MinecraftTest
{
	
	@Test
	public final void testNBT_IOForCorrect()
	{
		Position pos = new Position(1, 2, 3);
		NBTTagCompound nbtData = new NBTTagCompound();
		pos.writeToNBT(nbtData);
		Position readPos = Position.positionFromNBT(nbtData);
		assertEquals(pos, readPos);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForNull()
	{
		Position.positionFromNBT(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForEmptyCompound()
	{
		Position.positionFromNBT(new NBTTagCompound());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForCorruptedCompound()
	{
		NBTTagCompound corrupted = new NBTTagCompound();
		corrupted.setString("x", "evil, corrupted tag");
		corrupted.setString("y", "evil, corrupted tag");
		corrupted.setString("z", "evil, corrupted tag");
		Position.positionFromNBT(corrupted);
	}
	
	@Test
	public final void testConstructorIntIntInt()
	{
		int x = -431;
		int y = 78;
		int z = 0;
		Position pos = new Position(x, y, z);
		assertEquals(x, pos.x);
		assertEquals(y, pos.y);
		assertEquals(z, pos.z);
	}
	
	@Test
	public final void testConstructorIntArrayForCorrect()
	{
		int[] coords = {234, 99, -666};
		Position pos = new Position(coords);
		assertEquals(coords[0], pos.x);
		assertEquals(coords[1], pos.y);
		assertEquals(coords[2], pos.z);
	}
	
	@Test(expected = Exception.class)
	public final void testConstructorIntArrayForToSmall()
	{
		int[] coords = {0, 0};
		@SuppressWarnings("unused")
		Position pos = new Position(coords);
	}
	
	@Test(expected = Exception.class)
	public final void testConstructorIntArrayForToLarge()
	{
		int[] coords = {0, 0, 0, 0};
		@SuppressWarnings("unused")
		Position pos = new Position(coords);
	}
	
	@Test(expected = Exception.class)
	public final void testConstructorIntArrayForNull()
	{
		@SuppressWarnings("unused")
		Position pos = new Position(null);
	}
	
	@Test
	public final void testToIntArray()
	{
		int x = -5422;
		int y = 2;
		int z = 8888;
		Position pos = new Position(x, y, z);
		int[] coords = pos.toIntArray();
		assertEquals(pos.x, coords[0]);
		assertEquals(pos.y, coords[1]);
		assertEquals(pos.z, coords[2]);
	}
	
	@Test
	public final void testGetDistance()
	{
		Position pos0 = new Position(0, 0, 0);
		Position pos5awayX = new Position(3, 4, 0);
		Position pos5awayY = new Position(-3, 0, -4);
		Position pos5awayZ = new Position(0, -3, 4);
		Position posSqrt3away = new Position(-1, -1, -1);
		
		assertEquals(5.0f, pos0.getDistance(pos5awayX), 0.001f);
		assertEquals(5.0f, pos0.getDistance(pos5awayY), 0.001f);
		assertEquals(5.0f, pos0.getDistance(pos5awayZ), 0.001f);
		assertEquals(Math.sqrt(3.0f), pos0.getDistance(posSqrt3away), 0.001f);
	}
	
	@Test
	public final void testEquals()
	{
		Position base = new Position(1, 2, 3);
		Position same = new Position(1, 2, 3);
		Position different = new Position(3, 2, 1);
		
		assertTrue(base.equals(same));
		assertFalse(base.equals(different));
		assertFalse(base.equals(null));
		assertFalse(base.equals(new Object()));
	}
	
}
