package colVosch.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import colVosch.experimentalPhysics.util.MultiBlockHelper;
import colVosch.experimentalPhysics.util.Position;

public class TestMultiBlockHelper extends MinecraftTest
{
	
	@Test
	public final void testGetCubeForNormal()
	{
		List<Position> cube1x1 = MultiBlockHelper.getCube(0, 0, 0, 0);
		assertEquals(1, cube1x1.size());
		assertTrue(cube1x1.contains(new Position(0, 0, 0)));
		
		List<Position> cube3x3 = MultiBlockHelper.getCube(0, 0, 0, 1);
		assertEquals(3 * 3 * 3, cube3x3.size());
		assertTrue(cube3x3.contains(new Position(-1, -1, -1)));
		assertTrue(cube3x3.contains(new Position(-1, -1, 1)));
		assertTrue(cube3x3.contains(new Position(-1, 1, -1)));
		assertTrue(cube3x3.contains(new Position(-1, 1, 1)));
		assertTrue(cube3x3.contains(new Position(1, -1, -1)));
		assertTrue(cube3x3.contains(new Position(1, -1, 1)));
		assertTrue(cube3x3.contains(new Position(1, 1, -1)));
		assertTrue(cube3x3.contains(new Position(1, 1, 1)));
		assertTrue(cube3x3.contains(new Position(0, -1, 0)));
		assertTrue(cube3x3.contains(new Position(0, 0, 0)));
		assertTrue(cube3x3.contains(new Position(0, 1, 0)));
		
		List<Position> cube5x5 = MultiBlockHelper.getCube(0, 0, 0, 2);
		assertEquals(5 * 5 * 5, cube5x5.size());
		assertTrue(cube5x5.contains(new Position(-2, -2, -2)));
		assertTrue(cube5x5.contains(new Position(-2, -2, 2)));
		assertTrue(cube5x5.contains(new Position(-2, 2, -2)));
		assertTrue(cube5x5.contains(new Position(-2, 2, 2)));
		assertTrue(cube5x5.contains(new Position(2, -2, -2)));
		assertTrue(cube5x5.contains(new Position(2, -2, 2)));
		assertTrue(cube5x5.contains(new Position(2, 2, -2)));
		assertTrue(cube5x5.contains(new Position(2, 2, 2)));
		assertTrue(cube5x5.contains(new Position(0, -2, 0)));
		assertTrue(cube5x5.contains(new Position(0, -1, 0)));
		assertTrue(cube5x5.contains(new Position(0, 0, 0)));
		assertTrue(cube5x5.contains(new Position(0, 1, 0)));
		assertTrue(cube5x5.contains(new Position(0, 2, 0)));
	}
	
	@Test
	public final void testGetBlocksInCube()
	{
//		fail("Not yet implemented"); // TODO
	}
	
}
