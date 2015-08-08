package colVosch.experimentalPhysics.constants;

public class SubstanceProperty
{
	public static final SubstanceProperty IRON = new SubstanceProperty()
			.setMaxHeat(ExpPhysConfig.getMaxHeatIron())
			.setCoolDownConstant(ExpPhysConfig.getCoolDownConstantIron());
	private int maxHeat;
	private float coolDownConstant;

	public int getMaxHeat()
	{
		return maxHeat;
	}

	public SubstanceProperty setMaxHeat(int maxHeat)
	{
		this.maxHeat = maxHeat;
		return this;
	}

	public float getCoolDownConstant()
	{
		return this.coolDownConstant;
	}
	
	public SubstanceProperty setCoolDownConstant(float coolDownConstant)
	{
		this.coolDownConstant = coolDownConstant;
		return this;
	}
}
