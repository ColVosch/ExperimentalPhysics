package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.settings.SubstanceProperty;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockAdvancedRefinerCasing extends BlockAdvancedRefinerPart
{
	
	public BlockAdvancedRefinerCasing()
	{
		super(SubstanceProperty.IRON);
		setBlockTextureName(ExperimentalPhysics.MODID + ":advancedRefinerCasingFull");
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUpLeft"	), (byte) 3);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUp"		), (byte) 7);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingUpRight"	), (byte) 6);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingLeft"		), (byte) 11);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingMiddle"	), (byte) 15);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingRight"	), (byte) 14);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDownLeft"	), (byte) 9);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDown"		), (byte) 13);
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingDownRight"), (byte) 12);	
		registerIcon(iconRegister.registerIcon(ExperimentalPhysics.MODID + ":advancedRefinerCasingFull"		), (byte) 0);
	}
}
