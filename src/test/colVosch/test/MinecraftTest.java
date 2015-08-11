package colVosch.test;

import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.Ignore;

import cpw.mods.fml.relauncher.FMLRelaunchLog;
import cpw.mods.fml.relauncher.Side;

@Ignore
public class MinecraftTest
{
	private static boolean initialized = false;
	
	@BeforeClass
	public static void setUpMinecraft()
	{
		if (!initialized) {
			initialize();
			initialized = true;
		}
	}

	private static void initialize()
	{
		try {
			Field side = FMLRelaunchLog.class.getDeclaredField("side");
			side.setAccessible(true);
			side.set(null, Side.CLIENT);
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
