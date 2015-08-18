package colVosch.experimentalPhysics.settings;

public class Settings
{
	private static float coolDownFactor = 0.02f;
	private static int maxHeatIron = 1538;
	private static float coolDownConstantIron = 0.1f;
	private static int spaceTensionRangeAmplifier = 1;
	private static int spaceEventDelay = 20;
	private static float spaceTensionImpactDampener = 11.11111111f;

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

	protected static void setSpaceEventDelay(int spaceEventDelay)
	{
		Settings.spaceEventDelay = spaceEventDelay;
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

	public static float getSpaceTensionImpactDampener()
	{
		return spaceTensionImpactDampener;
	}
	
	public static int getSpaceEventFrequency()
	{
		return spaceEventDelay;
	}
	
}
