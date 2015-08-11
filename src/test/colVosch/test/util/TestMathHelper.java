package colVosch.test.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import colVosch.experimentalPhysics.util.MathHelper;
import colVosch.test.MinecraftTest;

public class TestMathHelper extends MinecraftTest
{
	
	@Test(expected = IllegalArgumentException.class)
	public final void testBoolArrayToIntForNull()
	{
		MathHelper.boolArrayToInt(null);
	}
	
	@Test
	public final void testBoolArrayToIntForNormal()
	{
		boolean[] emptyArray = {};
		assertEquals("Wrong handling of zero length array", 0, MathHelper.boolArrayToInt(emptyArray));
		
		boolean[] value7Array = {true, true, true};
		assertEquals("Wrong handling of normal array", 7, MathHelper.boolArrayToInt(value7Array));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testBoolArrayToIntForOversized()
	{
		boolean[] oversizedArray = new boolean[40];
		MathHelper.boolArrayToInt(oversizedArray);
	}
	
	@Test
	public final void testDiff()
	{
		assertEquals(5, MathHelper.diff(1, 6));
		assertEquals(5, MathHelper.diff(0, 5));
		assertEquals(5, MathHelper.diff(-3, 2));
		assertEquals(0, MathHelper.diff(0, 0));
		
		assertEquals(Integer.MAX_VALUE + 1l, MathHelper.diff(-1, Integer.MAX_VALUE));
	}
	
}
