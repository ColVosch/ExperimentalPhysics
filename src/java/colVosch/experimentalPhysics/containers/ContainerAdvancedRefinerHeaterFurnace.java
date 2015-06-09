package colVosch.experimentalPhysics.containers;

import colVosch.experimentalPhysics.tileEntitys.TileEntityAdvancedRefinerHeaterFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class ContainerAdvancedRefinerHeaterFurnace extends ModContainerBasic
{
	private TileEntityAdvancedRefinerHeaterFurnace heater;

	public ContainerAdvancedRefinerHeaterFurnace(EntityPlayer player, TileEntityAdvancedRefinerHeaterFurnace heater)
	{
		this.heater = heater;
		
		addSlotToContainer(new Slot(this.heater, 0, 80, 48));
		addPlayerInventoryToContainer(player.inventory, 8, 84);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return true;
	}

}
