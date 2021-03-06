package colVosch.experimentalPhysics.util;

import java.sql.Timestamp;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper
{	
	public static NBTBase createAndGetNBTBase(NBTTagCompound compound, String key, Class<? extends NBTBase> type)
	{
		if (compound == null)
			throw new IllegalArgumentException("The passed compound can not be null");
		if (key == null)
			throw new IllegalArgumentException("The passed key can not be null");
		
		NBTBase tag = compound.getTag(key);
		if (tag != null && !type.isInstance(tag))
		{
			FMLLog.warning("Unexpected tag: \"%s\" in compound: \"%s\", expected tag of type \"%s\". "
					+ "Assuming data corruption.", tag.toString(), compound.toString(), type.getName());
			compound.setTag(key + "_corrupted_" + new Timestamp((new java.util.Date()).getTime()), tag);
			compound.removeTag(key);
			tag = null;
		}
		if (tag == null)
		{
			try {
				tag = type.newInstance();
			}
			catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		compound.setTag(key, tag);
		return tag;
	}
	
	public boolean checkTagCompound(String[] keys, Class<? extends NBTBase>[] types, NBTTagCompound compound)
	{
		return false;		// TODO
	}
}
