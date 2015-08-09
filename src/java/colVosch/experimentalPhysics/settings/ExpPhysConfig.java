package colVosch.experimentalPhysics.settings;

import java.io.File;

import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import static colVosch.experimentalPhysics.settings.Settings.*;

public class ExpPhysConfig
{
	
	public static void init(File location)
	{
		Configuration config = new Configuration(location);
		config.load();
		setMaxHeatIron(config.getInt("maxHeatIron", Configuration.CATEGORY_GENERAL, 1530, 100, 3000, StatCollector.translateToLocal("propperty.maxHeatIron.comment"), StatCollector.translateToLocal("propperty.maxHeatIron.display")));
		setCoolDownConstantIron(config.getFloat("coolDownConstantIron", Configuration.CATEGORY_GENERAL, 0.1f, 0.0f, 1.0f, StatCollector.translateToLocal("propperty.coolDownConstantIron.comment"), StatCollector.translateToLocal("propperty.coolDownConstantIron.display")));
		setCoolDownFactor(config.getFloat("coolDownFactor", Configuration.CATEGORY_GENERAL, 0.1f, 0.0f, 1.0f, StatCollector.translateToLocal("propperty.coolDownFactor.comment"), "propperty.coolDownFactor.display"));
		setSpaceTensionRangeAmplifier(config.getInt("spaceTensionRangeAmplifier", Configuration.CATEGORY_GENERAL, 50, 1, 100, StatCollector.translateToLocal("propperty.spaceTensionRangeAmplifier.comment"), "propperty.spaceTensionRangeAmplifier.display"));
		setSpaceEventFrequency(100 - config.getInt("spaceEventFrequency", Configuration.CATEGORY_GENERAL, 90, 0, 100, StatCollector.translateToLocal("propperty.spaceEventFrequency.comment"), "propperty.spaceEventFrequency.display"));		// TODO 100 - x???

		config.save();
	}
}
