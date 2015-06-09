package colVosch.experimentalPhysics.spaceField.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import colVosch.experimentalPhysics.util.Position;
import net.minecraft.world.World;

public class SpaceFieldEvents
{
	public static List<ISpaceFieldEvent> events;

	public static void init()
	{
		events = new ArrayList<ISpaceFieldEvent>();
	    
	    events.add(new EventEnderInvasion());
	    events.add(new EventEnderOutpost());
	    events.add(new EventEndStoneAsteroid());
	}
	
	public static void triggerSpaceFieldEventAt(World world, Position pos, float strength, Random rndGen)
	{
		//TODO remove debug code
		System.out.println(pos);
		
		List<ISpaceFieldEvent> possibleEvents = new ArrayList<ISpaceFieldEvent> (events);
		Collections.shuffle(possibleEvents, rndGen);
		Iterator<ISpaceFieldEvent> i = possibleEvents.iterator();
		boolean triggered = false;
		ISpaceFieldEvent event;
		while (i.hasNext() && !triggered) {
			event = i.next();
			if (event.canTrigger(world, pos, strength)) {
				event.trigger(world, pos);
				triggered = true;
			} else {
				i.remove();
			}
		}
	}
}
