package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.constants.SubstanceProperty;
import net.minecraft.block.material.Material;

public abstract class PhysicsBlock extends ModBlock
{
	private SubstanceProperty substanceProperty;
	
	public PhysicsBlock(Material material, SubstanceProperty substanceProperty)
	{
		super(material);
		this.substanceProperty = substanceProperty;
	}
	
	public SubstanceProperty getSubstancePropperty()
	{
		return substanceProperty;
	}
	
	public int getMaxHeat()
	{
		return substanceProperty.getMaxHeat();
	}
	
	public float getCoolDownConstant()
	{
		return substanceProperty.getCoolDownConstant();
	}
}
