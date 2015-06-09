package colVosch.experimentalPhysics.network;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.network.handlers.HandlerCoords;
import colVosch.experimentalPhysics.network.handlers.HandlerSyncAdvancedRefiner;
import colVosch.experimentalPhysics.network.handlers.HandlerSyncRefiner;
import colVosch.experimentalPhysics.network.packets.PacketCoords;
import colVosch.experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;
import colVosch.experimentalPhysics.network.packets.PacketSyncRefiner;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketController
{
	private static SimpleNetworkWrapper network;

	private static int currentDiscriminator = 0;

	public static void registerPackets()
	{
		getNetworkWrapper().registerMessage(HandlerSyncAdvancedRefiner.class, PacketSyncAdvancedRefiner.class, getNextDiscriminator(), Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerSyncRefiner.class, PacketSyncRefiner.class, getNextDiscriminator(), Side.CLIENT);
		getNetworkWrapper().registerMessage(HandlerCoords.class, PacketCoords.class, getNextDiscriminator(), Side.CLIENT);
	}

	private static int getNextDiscriminator()
	{
		return currentDiscriminator ++;
	}
	
	public static SimpleNetworkWrapper getNetworkWrapper()
	{
		if (network == null)
		{
			network = NetworkRegistry.INSTANCE.newSimpleChannel(ExperimentalPhysics.MODID);
		}
		return network;
	}
}
