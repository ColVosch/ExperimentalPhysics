package colVosch.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import colVosch.experimentalPhysics.util.NBTHelper;
import colVosch.test.MinecraftTest;
import cpw.mods.fml.relauncher.FMLRelaunchLog;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.NBTTagCompound;

public class TestNBTHelper extends MinecraftTest
{

	@Test(expected = IllegalArgumentException.class)
	public final void testCreateAndGetTagCompoundForNullCompound()
	{
		NBTHelper.createAndGetTagCompound(null, "compound");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateAndGetTagCompoundForNullKey()
	{
		NBTHelper.createAndGetTagCompound(new NBTTagCompound(), null);
	}
	
	@Test
	public final void testCreateAndGetTagCompoundFoNormal()
	{
		NBTTagCompound outerCompound = new NBTTagCompound();
		assertTrue(NBTHelper.createAndGetTagCompound(outerCompound, "compound") instanceof NBTTagCompound);
		assertTrue(NBTHelper.createAndGetTagCompound(outerCompound, "") instanceof NBTTagCompound);
		
		NBTTagCompound smallCompound = new NBTTagCompound();
		String key1 = "small";
		outerCompound.setTag(key1, smallCompound);
		assertTrue(smallCompound == NBTHelper.createAndGetTagCompound(outerCompound, key1));
		
		String key2 = "wrongType";
		outerCompound.setBoolean(key2, false);
		assertTrue(NBTHelper.createAndGetTagCompound(outerCompound, key2) instanceof NBTTagCompound);
	}
	
	@Test
	public final void testCreateAndGetTagList()
	{
		fail("Not yet implemented"); // TODO
	}
	
}
