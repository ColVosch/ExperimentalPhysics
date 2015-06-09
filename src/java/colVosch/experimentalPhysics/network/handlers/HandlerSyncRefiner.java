package colVosch.experimentalPhysics.network.handlers;

import colVosch.experimentalPhysics.network.packets.PacketSyncRefiner;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandlerSyncRefiner implements IMessageHandler<PacketSyncRefiner, IMessage>
{
	@Override
	public IMessage onMessage(PacketSyncRefiner message, MessageContext ctx)
	{
		Synchronizer.synchronize(message);
		return null;
	}

}
