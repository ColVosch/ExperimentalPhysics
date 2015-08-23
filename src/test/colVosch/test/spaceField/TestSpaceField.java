package colVosch.test.spaceField;

import static org.junit.Assert.*;

import org.junit.Test;

import colVosch.experimentalPhysics.spaceField.SpaceField;
import colVosch.experimentalPhysics.util.Position;
import colVosch.test.MinecraftTest;

public class TestSpaceField extends MinecraftTest
{
	
	@Test
	public final void testReadFromNBT()
	{
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testWriteToNBT()
	{
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testUpdate()
	{
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public final void testTension()
	{
		SpaceField field = new SpaceField(0);
		Position posBase = new Position(0, 0, 0);
		assertEquals(0.0f, field.getTensionAt(posBase), PRECISION);
		
		int strength = 10;
		field.addTensionPoint(posBase, strength);
		assertEquals(strength, field.getTensionAt(posBase), PRECISION);
		
		Position posNear = new Position(2, 2, 2);
		Position posMiddle = new Position(1, 1, 1);
		field.addTensionPoint(posNear, -strength);
		assertEquals(0.0f, field.getTensionAt(posMiddle), PRECISION);
		
		field.removeTensionPoint(posBase);
		field.removeTensionPoint(posNear);
		assertEquals(0.0f, field.getTensionAt(posBase), PRECISION);
		assertEquals(0.0f, field.getTensionAt(posNear), PRECISION);
		
		Position unexistant = new Position(6, 6, 6);
		field.removeTensionPoint(unexistant);
		assertEquals(0.0f, field.getTensionAt(posBase), PRECISION);
		assertEquals(0.0f, field.getTensionAt(posNear), PRECISION);
	}
	
}
