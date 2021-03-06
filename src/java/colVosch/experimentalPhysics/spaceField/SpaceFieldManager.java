package colVosch.experimentalPhysics.spaceField;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;

public class SpaceFieldManager
{	
	public static HashMap<Integer, SpaceField> spaceFields = new HashMap<Integer, SpaceField>();	// TODO replace with getter
	
	@SubscribeEvent
	public void saveField(Save saveEvent)
	{
		if (!(saveEvent.world instanceof WorldServer)) {
			System.out.println("Saving the world on the client side?");		// TODO check
			return;
		}
		
		WorldServer world = (WorldServer) saveEvent.world;
		
		NBTTagCompound spaceFieldTag = new NBTTagCompound();
		spaceFieldTag.setTag("Data", spaceFields.get(world.provider.dimensionId).writeToNBT());
		
		File path = (new File(world.getChunkSaveLocation(), "ExperimentalPhysics"));	// TODO Move to File I/O
		File location = new File(path, "SpaceFieldDIM"+ Integer.toString(world.provider.dimensionId) +".dat");
		if (!location.exists()) {
			path.mkdirs();
		}
		try {
            CompressedStreamTools.write(spaceFieldTag, location);
        }
        catch (IOException e) {
            FMLLog.log(Level.WARN, e, "Unable to write space field data to %s", location.getAbsolutePath());
            return;
        }
	}
	
	@SubscribeEvent
	public void loadField(Load loadEvent)
	{
		if (!(loadEvent.world instanceof WorldServer)) {return;}
		
		WorldServer world = (WorldServer) loadEvent.world;
		SpaceField field = new SpaceField(world.provider.dimensionId);
		
		File path = (new File(world.getChunkSaveLocation(), "ExperimentalPhysics"));
		File location = new File(path, "SpaceFieldDIM"+ Integer.toString(world.provider.dimensionId) +".dat");
		
		if (location.exists())
		{
			NBTTagCompound spaceFieldTag;
			try
			{
				spaceFieldTag = CompressedStreamTools.read(location);
			}
			catch (IOException e)
			{
				FMLLog.log(Level.WARN, e, "Unable to read space field data at %s - it will be ignored", location.getAbsolutePath());
				return;
			}
			field.readFromNBT(spaceFieldTag.getTag("Data"));
		}
		spaceFields.put(world.provider.dimensionId, field);
	}
	
	@SubscribeEvent
	public void updateSpaceField(WorldTickEvent e)		// TODO ServerTickEvent?
	{
		if (e.side == Side.SERVER)
		{
			spaceFields.get(e.world.provider.dimensionId).update();
			//spaceFields.get(e.world.provider.dimensionId).tryTriggerSpaceFieldEvent();
		}
	}
}