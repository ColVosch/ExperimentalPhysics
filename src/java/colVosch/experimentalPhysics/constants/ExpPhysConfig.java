package colVosch.experimentalPhysics.constants;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public class ExpPhysConfig
{
	
	private static float coolDownFactor;
	private static int spaceTensionRangeAmplifier;
	private static int spaceEventFrequency;
	private static int maxHeatIron;
	private static float coolDownConstantIron;
	
	public static void init(File location)
	{
		Configuration config = new Configuration(location);
		config.load();
		maxHeatIron = config.getInt("maxHeatIron", Configuration.CATEGORY_GENERAL, 1530, 100, 3000, StatCollector.translateToLocal("propperty.maxHeatIron.comment"), StatCollector.translateToLocal("propperty.maxHeatIron.display"));
		coolDownConstantIron = config.getFloat("coolDownConstantIron", Configuration.CATEGORY_GENERAL, 0.1f, 0.0f, 1.0f, StatCollector.translateToLocal("propperty.coolDownConstantIron.comment"), StatCollector.translateToLocal("propperty.coolDownConstantIron.display"));	// TODO change to float < 0
		coolDownFactor = config.getFloat("coolDownFactor", Configuration.CATEGORY_GENERAL, 0.1f, 0.0f, 1.0f, StatCollector.translateToLocal("propperty.coolDownFactor.comment"), "propperty.coolDownFactor.display");														// TODO change to float < 0
		spaceTensionRangeAmplifier = config.getInt("spaceTensionRangeAmplifier", Configuration.CATEGORY_GENERAL, 50, 1, 100, StatCollector.translateToLocal("propperty.spaceTensionRangeAmplifier.comment"), "propperty.spaceTensionRangeAmplifier.display");
		spaceEventFrequency = 100 - config.getInt("spaceEventFrequency", Configuration.CATEGORY_GENERAL, 90, 0, 100, StatCollector.translateToLocal("propperty.spaceEventFrequency.comment"), "propperty.spaceEventFrequency.display");

		config.save();
	}

	public static float getCoolDownFactor()
	{
		return coolDownFactor;
	}

	public static int getSpaceTensionRangeAmplifier()
	{
		return spaceTensionRangeAmplifier;
	}

	public static int getSpaceEventFrequency() 
	{
		return spaceEventFrequency;
	}

	public static int getMaxHeatIron()
	{
		return maxHeatIron;
	}

	public static float getCoolDownConstantIron()
	{
		return coolDownConstantIron;
	}
}
