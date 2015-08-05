package colVosch.experimentalPhysics.reference;

import net.minecraft.util.StatCollector;

public class Localization
{
	
	public static String localize(String unlocalizedString)
	{
		return StatCollector.translateToLocal(unlocalizedString);
	}
	
	public static final class Container
	{
		public static final String REFINER_ADVANCED = "container.refinerAdvanced";
		public static final String HEATER_FURNACE = "container.advancedRefinerHeaterFurnace";
		public static final String ADVANCED_REFINER_INSERTION_LOCK = "container.advancedRefinerInsertionLock";
		public static final String REFINER = "container.refiner";
		
	}
}
