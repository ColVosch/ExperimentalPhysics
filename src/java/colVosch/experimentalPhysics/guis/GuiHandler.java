package colVosch.experimentalPhysics.guis;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.containers.ContainerAdvancedRefinerHeaterFurnace;
import colVosch.experimentalPhysics.containers.ContainerAdvancedRefinerInsertionLock;
import colVosch.experimentalPhysics.containers.ContainerEmpty;
import colVosch.experimentalPhysics.containers.ContainerRefiner;
import colVosch.experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerDisplay;
import colVosch.experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerHeaterFurnace;
import colVosch.experimentalPhysics.tileEntitys.TileEntityRefiner;
import colVosch.experimentalPhysics.tileEntitys.TileEntityStoring;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
	public static final int ID_REFINER = 0;
	public static final int ID_ADVANCED_REFINER_INSERTION_LOCK = 1;
	public static final int ID_ADVANCED_REFINER_DISPLAY = 2;
	public static final int ID_ADVANCED_REFINER_HEATER_FURNACE = 3;
	
	public static void register()
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(ExperimentalPhysics.instance, new GuiHandler());
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		switch(ID)
		{
			case ID_REFINER:
			{
				return new ContainerRefiner(player, (TileEntityRefiner) tile);
			}
			case ID_ADVANCED_REFINER_INSERTION_LOCK:
			{
				return new ContainerAdvancedRefinerInsertionLock(player, (TileEntityStoring) tile);
			}
			case ID_ADVANCED_REFINER_DISPLAY:
			{
				return new ContainerEmpty();
			}
			case ID_ADVANCED_REFINER_HEATER_FURNACE:
			{
				return new ContainerAdvancedRefinerHeaterFurnace(player, (TileEntityAdvancedRefinerHeaterFurnace) tile);
			}
			default:
			{
				return null;
			}
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		switch(ID)
		{
			case ID_REFINER:
			{
				return new GuiRefiner(player, (TileEntityRefiner) tile);
			}
			case ID_ADVANCED_REFINER_INSERTION_LOCK:
			{
				return new GuiAdvancedRefinerInsertionLock(player, (TileEntityStoring) tile);
			}
			case ID_ADVANCED_REFINER_DISPLAY:
			{
				return new GuiAdvancedRefinerDisplay(((TileEntityAdvancedRefinerDisplay) tile));
			}
			case ID_ADVANCED_REFINER_HEATER_FURNACE:
			{
				return new GuiAdvancedRefinerHeaterFurnace(player, (TileEntityAdvancedRefinerHeaterFurnace) tile);
			}
			default:
			{
				return null;
			}
		}
	}
}
