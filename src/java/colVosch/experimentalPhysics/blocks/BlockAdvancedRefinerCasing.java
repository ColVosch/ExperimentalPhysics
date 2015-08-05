package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.constants.Tier;
import colVosch.experimentalPhysics.constants.Tiers;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockAdvancedRefinerCasing extends BlockAdvancedRefinerPart
{
	public static final String NAME = "blockAdvancedRefinerCasing";
	
	public BlockAdvancedRefinerCasing()
	{
		super();
		setBlockName(NAME);
		setBlockTextureName(ExperimentalPhysics.MODID + ":advancedRefinerCasingFull");
		GameRegistry.registerBlock(this, NAME);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[3] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUpLeft");
		icons[7] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUp");
		icons[6] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUpRight");
		icons[11] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingLeft");
		icons[15] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingMiddle");
		icons[14] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingRight");
		icons[9] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDownLeft");
		icons[13] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDown");
		icons[12] = iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDownRight");	
		icons[0] =  iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingFull");
	}

	@Override
	public Tier getTier() 
	{
		return Tiers.tierIron;
	}
}
