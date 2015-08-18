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
		assertEquals(0.0f, field.getTensionAt(posBase), 0.0001f);
		
		int strength = 10;
		field.addTensionPoint(posBase, strength);
		assertEquals(strength, field.getTensionAt(posBase), 0.0001f);
		
		Position posNear = new Position(2, 2, 2);
		Position posMiddle = new Position(1, 1, 1);
		field.addTensionPoint(posNear, -strength);
		assertEquals(0.0f, field.getTensionAt(posMiddle), 0.0001f);
		
		field.removeTensionPoint(posBase);
		field.removeTensionPoint(posNear);
		assertEquals(0.0f, field.getTensionAt(posBase), 0.0001f);
		assertEquals(0.0f, field.getTensionAt(posNear), 0.0001f);
		
		Position unexistant = new Position(6, 6, 6);
		field.removeTensionPoint(unexistant);
		assertEquals(0.0f, field.getTensionAt(posBase), 0.0001f);
		assertEquals(0.0f, field.getTensionAt(posNear), 0.0001f);
	}
	
}
