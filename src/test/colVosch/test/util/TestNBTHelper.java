package colVosch.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import colVosch.experimentalPhysics.util.NBTHelper;
import colVosch.test.MinecraftTest;
import net.minecraft.nbt.NBTTagCompound;

public class TestNBTHelper extends MinecraftTest
{

	@Test(expected = IllegalArgumentException.class)
	public final void testCreateAndGetNBTBaseForNullCompound()
	{
		NBTHelper.createAndGetNBTBase(null, "compound", NBTTagCompound.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testCreateAndGetNBTBaseForNullKey()
	{
		NBTHelper.createAndGetNBTBase(new NBTTagCompound(), null, NBTTagCompound.class);
	}
	
	@Test
	public final void testCreateAndGetNBTBaseForNormal()
	{
		NBTTagCompound outerCompound = new NBTTagCompound();
		assertTrue(NBTHelper.createAndGetNBTBase(outerCompound, "compound", NBTTagCompound.class) instanceof NBTTagCompound);
		assertTrue(NBTHelper.createAndGetNBTBase(outerCompound, "", NBTTagCompound.class) instanceof NBTTagCompound);
		
		NBTTagCompound smallCompound = new NBTTagCompound();
		String key1 = "small";
		outerCompound.setTag(key1, smallCompound);
		assertTrue(smallCompound == NBTHelper.createAndGetNBTBase(outerCompound, key1, NBTTagCompound.class));
		
		String key2 = "wrongType";
		outerCompound.setBoolean(key2, false);
		assertTrue(NBTHelper.createAndGetNBTBase(outerCompound, key2, NBTTagCompound.class) instanceof NBTTagCompound);
	}
}
