package colVosch.experimentalPhysics.tileEntitys;

import colVosch.experimentalPhysics.reference.Localization;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileEntityAdvancedRefinerInsertionLock extends TileEntityStoring implements IMultiblockInput, IMultiblockOutput
{
	private static int OUTPUT = 0;
	
	private TileEntityAdvancedRefiner refiner;

	public TileEntityAdvancedRefinerInsertionLock()
	{
		super(1);		// This TileEntity will have 1 slot: output
	}
	
	@Override
	public String getInventoryName()
	{
		return Localization.Container.ADVANCED_REFINER_INSERTION_LOCK;
	}

	@Override
	public int[] getCoords()
	{
		int[] coords = {xCoord, yCoord, zCoord};
		return coords;
	}

	public void form(int xCore, int yCore, int zCore)
	{
		refiner = ((TileEntityAdvancedRefiner) worldObj.getTileEntity(xCore, yCore, zCore));
		refiner.registerInput(this);
		refiner.registerOutput(this);
	}

	@Override
	public ItemStack inputItem(Item item)
	{
		if (inventory[OUTPUT] != null && inventory[OUTPUT].getItem() == item)
		{
			ItemStack retStack = inventory[0].splitStack(1);
			if (inventory[OUTPUT].stackSize <= 0)
			{
				inventory[OUTPUT] = null;
			}
			return retStack;
		}
		else
		{
			return null;
		}	
	}

	@Override
	public boolean outputItem(ItemStack item)
	{
		if (item != null && !worldObj.isRemote)
		{
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord, zCoord, item));
		}
		return true;
	}

}
