package colVosch.experimentalPhysics.settings;

public class Settings
{
	private static float coolDownFactor;
	private static int maxHeatIron;
	private static float coolDownConstantIron;
	private static int spaceTensionRangeAmplifier;
	private static int spaceEventFrequency;

	protected static void setCoolDownFactor(float coolDownFactor)
	{
		Settings.coolDownFactor = coolDownFactor;
	}

	protected static void setMaxHeatIron(int maxHeatIron)
	{
		Settings.maxHeatIron = maxHeatIron;
	}

	protected static void setCoolDownConstantIron(float coolDownConstantIron)
	{
		Settings.coolDownConstantIron = coolDownConstantIron;
	}

	protected static void setSpaceTensionRangeAmplifier(int spaceTensionRangeAmplifier)
	{
		Settings.spaceTensionRangeAmplifier = spaceTensionRangeAmplifier;
	}

	protected static void setSpaceEventFrequency(int spaceEventFrequency)
	{
		Settings.spaceEventFrequency = spaceEventFrequency;
	}

	public static float getCoolDownFactor()
	{
		return coolDownFactor;
	}

	public static int getMaxHeatIron()
	{
		return maxHeatIron;
	}

	public static float getCoolDownConstantIron()
	{
		return coolDownConstantIron;
	}

	public static int getSpaceTensionRangeAmplifier()
	{
		return spaceTensionRangeAmplifier;
	}

	public static int getSpaceEventFrequency()
	{
		return spaceEventFrequency;
	}
	
}
