package colVosch.experimentalPhysics.client;

import static cpw.mods.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;

import colVosch.experimentalPhysics.CommonProxy;
import colVosch.experimentalPhysics.client.renderers.RenderEndStoneAsteroid;
import colVosch.experimentalPhysics.entitys.EntityEndStoneAsteroid;

public class ClientProxy extends CommonProxy
{
	public void registerRenderers() 
	{
		registerEntityRenderingHandler(EntityEndStoneAsteroid.class, new RenderEndStoneAsteroid());
	}

}
