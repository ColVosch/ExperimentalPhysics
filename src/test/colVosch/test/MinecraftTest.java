package colVosch.test;

import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.Ignore;

import cpw.mods.fml.relauncher.FMLRelaunchLog;
import cpw.mods.fml.relauncher.Side;

@Ignore
public class MinecraftTest
{
	protected static final float PRECISION = 0.0001f;
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
		// Initialize FMLRelaunchLog without starting everything
		try {
			Field side = FMLRelaunchLog.class.getDeclaredField("side");
			side.setAccessible(true);
			side.set(null, Side.CLIENT);
			side.setAccessible(false);
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
