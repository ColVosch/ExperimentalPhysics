package colVosch.experimentalPhysics.network.handlers;

import colVosch.experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandlerSyncAdvancedRefiner implements IMessageHandler<PacketSyncAdvancedRefiner, IMessage>
{

	@Override
	public IMessage onMessage(PacketSyncAdvancedRefiner message, MessageContext ctx)
	{
		Synchronizer.synchronize(message);
		return null;
	}

}
