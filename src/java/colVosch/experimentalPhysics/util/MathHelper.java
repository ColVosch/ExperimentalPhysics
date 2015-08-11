package colVosch.experimentalPhysics.util;

public class MathHelper
{
	public static int boolArrayToInt(boolean[] bits)
	{
		if (bits == null)
			throw new IllegalArgumentException("The argument can not be null");
		if (bits.length > 32)
			throw new IllegalArgumentException("The passed array can have only as many bytes as an integer");

		int result = 0;
		for (int e = 0; e < bits.length; e++)
		{
			result += bits[e] ? Math.pow(2, e) : 0;
		}
		return result;
	}
	

	/**Returns the difference of two integer values<br><br>
	 * <code>
	 * |a - b|
	 * </code>
	 * @param a
	 * @param b
	 */
	public static long diff(int a, int b)
	{	
		return Math.abs((long)a - (long)b);
	}
}
