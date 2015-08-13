package colVosch.test.spaceField;

import static org.junit.Assert.*;

import org.junit.Test;

import colVosch.experimentalPhysics.spaceField.TensionPoint;
import colVosch.experimentalPhysics.util.Position;
import colVosch.test.MinecraftTest;
import net.minecraft.nbt.NBTTagCompound;

public class TestTensionPoint extends MinecraftTest
{
	
	@Test
	public final void testNBT_IOForCorrect()
	{
		TensionPoint tensionPoint = new TensionPoint(new Position(1, 2, 3), 7);
		NBTTagCompound nbtData = new NBTTagCompound();
		tensionPoint.writeToNBT(nbtData);
		TensionPoint readTensionPoint = TensionPoint.tensionPointFromNBT(nbtData);
		assertEquals(tensionPoint, readTensionPoint);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForNull()
	{
		TensionPoint.tensionPointFromNBT(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForEmptyCompound()
	{
		TensionPoint.tensionPointFromNBT(new NBTTagCompound());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForPositionCompound()
	{
		TensionPoint.tensionPointFromNBT((new Position(0, 0, 0))
				.writeToNBT(new NBTTagCompound()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testNBT_IOForCorruptedCompound()
	{
		NBTTagCompound corrupted = new NBTTagCompound();
		(new TensionPoint(new Position(0, 0, 0), 10)).writeToNBT(corrupted);
		corrupted.removeTag("sttrength");
		corrupted.setString("strength", "evil, corrupted tag");
		TensionPoint.tensionPointFromNBT(corrupted);
	}
	
	@Test
	public final void testEquals()
	{
		Position basePosition = new Position(1, 2, 3);
		TensionPoint base = new TensionPoint(basePosition, 10);
		TensionPoint same = new TensionPoint(basePosition, 10);
		TensionPoint differentPosition = new TensionPoint(new Position(3, 2, 1), 10);
		TensionPoint differentTension = new TensionPoint(basePosition, 5);
		
		assertTrue(base.equals(same));
		assertFalse(base.equals(differentPosition));
		assertFalse(base.equals(differentTension));
		assertFalse(base.equals(null));
		assertFalse(base.equals(basePosition));
	}
	
	@Test
	public final void testHasEqualPosition()
	{
		Position basePosition = new Position(6, 6, 6);
		Position samePosition = new Position(6, 6, 6);
		Position differentPosition = new Position(0, 4, 2);
		TensionPoint baseTensionPoint = new TensionPoint(basePosition, 0);
		TensionPoint equalyLocated =  new TensionPoint(samePosition, 1);
		TensionPoint differentlyLocated = new TensionPoint(differentPosition, 0);
		
		assertTrue(baseTensionPoint.hasEqualPosition(basePosition));
		assertTrue(baseTensionPoint.hasEqualPosition(samePosition));
		assertTrue(baseTensionPoint.hasEqualPosition(equalyLocated));
		
		assertFalse(baseTensionPoint.hasEqualPosition(differentPosition));
		assertFalse(baseTensionPoint.hasEqualPosition(differentlyLocated));
	}
	
}
