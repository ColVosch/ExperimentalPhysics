package colVosch.experimentalPhysics;

import colVosch.experimentalPhysics.blocks.ModBlocks;
import colVosch.experimentalPhysics.constants.ExpPhysConfig;
import colVosch.experimentalPhysics.entitys.EntityEndStoneAsteroid;
import colVosch.experimentalPhysics.guis.GuiHandler;
import colVosch.experimentalPhysics.items.ModItems;
import colVosch.experimentalPhysics.network.PacketController;
import colVosch.experimentalPhysics.recipes.VanillaRecipes;
import colVosch.experimentalPhysics.spaceField.SpaceFieldManager;
import colVosch.experimentalPhysics.spaceField.events.SpaceFieldEvents;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraftforge.common.MinecraftForge;

	@Mod(modid=ExperimentalPhysics.MODID, name="Experimental Physics", version="0.0.2")
	public class ExperimentalPhysics  
	{	        
		public static final String MODID = "experimentalphysics";
		
		@Instance(MODID)
		public static ExperimentalPhysics instance;
		@SidedProxy(clientSide="colVosch.experimentalPhysics.client.ClientProxy", serverSide="colVosch.experimentalPhysics.CommonProxy")
		public static CommonProxy proxy;
		
		public static SpaceFieldManager spaceFieldManager;
		
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) 
        {  
        	spaceFieldManager = new SpaceFieldManager();
        	SpaceFieldEvents.init();
        	
        	registerEventHandlers();
        	
        	ExpPhysConfig.init(event.getSuggestedConfigurationFile());
        	
        	ModBlocks.register();
        	ModItems.register();
        	registerEntitys();
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event)
        {
        	VanillaRecipes.register();
        	GuiHandler.register();
			PacketController.registerPackets();        	
			proxy.registerRenderers();
        }
       
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {}

        private void registerEventHandlers()
        {			
			MinecraftForge.EVENT_BUS.register(spaceFieldManager);
			
			FMLCommonHandler.instance().bus().register(spaceFieldManager);
        }
        
        private void registerEntitys()
        {
        	EntityRegistry.registerModEntity(EntityEndStoneAsteroid.class, "endStoneAsteroid", 0, this, 150, 5, true);
        }
	}

