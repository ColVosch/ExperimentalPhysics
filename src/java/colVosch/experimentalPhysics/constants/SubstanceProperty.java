package colVosch.experimentalPhysics.constants;

public class SubstanceProperty
{
	public static final SubstanceProperty IRON = new SubstanceProperty()
			.setMaxHeat(ExpPhysConfig.getLimitHeatTierIron())
			.setThermConstant(ExpPhysConfig.getThermConstantTierIron())
			.setMassPerBlock(PhysicConstants.IRON_MASS_PER_BLOCK);
	private int maxHeat;
	private float thermConstant;
	private int massPerBlock;

	public int getMaxHeat()
	{
		return maxHeat;
	}

	public SubstanceProperty setMaxHeat(int maxHeat)
	{
		this.maxHeat = maxHeat;
		return this;
	}

	public float getThermConstant()
	{
		return thermConstant;
	}

	public SubstanceProperty setThermConstant(float thermConstant)
	{
		this.thermConstant = thermConstant;
		return this;
	}

	public int getMassPerBlock()
	{
		return massPerBlock;
	}

	public SubstanceProperty setMassPerBlock(int massPerBlock)
	{
		this.massPerBlock = massPerBlock;
		return this;
	}
	
}
