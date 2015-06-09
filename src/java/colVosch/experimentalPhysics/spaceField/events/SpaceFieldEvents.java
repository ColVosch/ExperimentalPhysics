package colVosch.experimentalPhysics.spaceField.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.world.World;

public class SpaceFieldEvents
{
	public static List<ISpaceFieldEvent> eventsPos;
	public static List<ISpaceFieldEvent> eventsPosMin10;
	public static List<ISpaceFieldEvent> eventsNeg;
	public static List<ISpaceFieldEvent> eventsNegMin10;

	public static void init()
	{
		eventsPos = new ArrayList<ISpaceFieldEvent>();
		eventsPosMin10 = new ArrayList<ISpaceFieldEvent>();
	    eventsNeg = new ArrayList<ISpaceFieldEvent>();     
	    eventsNegMin10 = new ArrayList<ISpaceFieldEvent>();
	    
	    eventsPos.add(new EventEnderInvasion());
	    eventsPos.add(new EventEnderOutpost());
	}
	
	public static void triggerSpaceFieldEventAt(World world, Position pos, float strength, Random rndGen)
	{
		//TODO remove debug code
		System.out.println(pos);
		
		List<ISpaceFieldEvent> choosable = new ArrayList<ISpaceFieldEvent>();
		if (strength > 0)
		{
			choosable.addAll(eventsPos);
			if (strength > 10)
			{
				choosable.addAll(eventsPosMin10);
			}
		}
		else
		{
			choosable.addAll(eventsNeg);
			if (strength > 10)
			{
				choosable.addAll(eventsNegMin10);
			}
		}
		if (!choosable.isEmpty())
		{
			boolean triggered = false;
			for (int i = 0; i < 42 && !triggered; i++)
			{
				triggered = choosable.get(rndGen.nextInt(choosable.size()-1)).trigger(world, pos);				
			}
		}
	}

}
