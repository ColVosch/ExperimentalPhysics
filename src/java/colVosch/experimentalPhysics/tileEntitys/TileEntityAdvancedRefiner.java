package colVosch.experimentalPhysics.tileEntitys;

import java.util.ArrayList;
import java.util.List;

import colVosch.experimentalPhysics.blocks.BlockAdvancedRefiner;
import colVosch.experimentalPhysics.items.ModItems;
import colVosch.experimentalPhysics.network.PacketController;
import colVosch.experimentalPhysics.network.handlers.ISynchronizable;
import colVosch.experimentalPhysics.network.packets.PacketSyncAdvancedRefiner;
import colVosch.experimentalPhysics.reference.Localization;
import colVosch.experimentalPhysics.settings.Settings;
import colVosch.experimentalPhysics.util.Position;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityAdvancedRefiner extends TileEntityStoring implements ISynchronizable<PacketSyncAdvancedRefiner>
{
	
	public static final int REQUIRED_PROGRESS = 10000;
	public static final String NAME = "tileEntityAdvancedRefiner";
	
	private static final int INPUT = 0;
	private static final int OUTPUT_PRIMARY = 1;
	private static final int OUTPUT_SECONDARY = 2;
	private static final int VALUE_NOT_DEFINED = -1111;
	
	private boolean formed = false;
	private short progress = -1;
	private short refiningSpeed = 1;
	private float temperature = 20.0f;
	private float coolDownFactor = VALUE_NOT_DEFINED;
	private int roomTemp = VALUE_NOT_DEFINED;
	private short maxHeat = 500;
	private float dustChance = 0.0f;
	
	// TODO Server starting event!
	private List<IMultiblockInput> inputs = new ArrayList<IMultiblockInput>();
	private List<Position> inputPositions = new ArrayList<Position>();
	private List<IMultiblockOutput> outputs = new ArrayList<IMultiblockOutput>();
	private List<Position> outputPositions = new ArrayList<Position>();
	private List<IAdvancedRefinerModifier> modifiers = new ArrayList<IAdvancedRefinerModifier>();
	private List<Position> modifierPositions = new ArrayList<Position>();
	private List<IAdvancedRefinerHeater> heaters = new ArrayList<IAdvancedRefinerHeater>();
	private List<Position> heaterPositions = new ArrayList<Position>();	

	public TileEntityAdvancedRefiner()
	{
		super(3);		// This TileEntity will have 3 slots: input_primary, input_secondary and output
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		progress = tagCompound.getShort("progress");
		temperature = tagCompound.getFloat("temperature");
		formed = tagCompound.getBoolean("formed");
		
		readPositionListFromNBT(tagCompound.getCompoundTag("inputCoords"), inputPositions);
		readPositionListFromNBT(tagCompound.getCompoundTag("outputCoords"), outputPositions);
		readPositionListFromNBT(tagCompound.getCompoundTag("modifierCoords"), modifierPositions);
		readPositionListFromNBT(tagCompound.getCompoundTag("heaterCoords"), heaterPositions);
	}
	
	private void readPositionListFromNBT(NBTTagCompound coordCompound, List<Position> posList)
	{
		boolean shouldContinue = true;
		for (int i = 0; shouldContinue && i < 27; i++)
		{
			int[] coords = coordCompound.getIntArray(Integer.toString(i));
			if (coords.length == 3)
			{
				posList.add(new Position(coords));
			}
			else
			{
				shouldContinue = false;
			}
		}
	}
	
	private void initInteractorsFromCoords()
	{
		TileEntity tile;
		for (Position p : inputPositions)
		{
			tile = p.getTileEntity(worldObj);
			if (!inputs.contains(tile))
			{
				inputs.add((IMultiblockInput) tile);
			}
		}
		for (Position p : outputPositions)
		{
			tile = p.getTileEntity(worldObj);
			if (!outputs.contains(tile))
			{
				outputs.add((IMultiblockOutput) tile);
			}
		}			
		for (Position p : modifierPositions)
		{
			tile = p.getTileEntity(worldObj);
			if (!modifiers.contains(tile))
			{
				modifiers.add((IAdvancedRefinerModifier) tile);
			}
		}
		for (Position p : heaterPositions)
		{
			tile = p.getTileEntity(worldObj);
			if (!heaters.contains(tile))
			{
				heaters.add((IAdvancedRefinerHeater) tile);
			}
		}
	}
	
	public void recalculateStructureProppertys()
	{
		float materialCoolDown = ((BlockAdvancedRefiner) getPosition().getBlock(worldObj))
									.getAverageCoolDownConstant(worldObj, xCoord, yCoord, zCoord);
		float globalCoolDown = Settings.getCoolDownFactor();
		coolDownFactor = materialCoolDown * globalCoolDown;
		
		maxHeat = ((BlockAdvancedRefiner) getPosition().getBlock(worldObj)).getMaxStructureHeat(worldObj, xCoord, yCoord, zCoord);
	}
	
	public void synchronize(PacketSyncAdvancedRefiner message)
	{
		formed = message.formed;
		progress = message.progress;
		refiningSpeed = message.refiningSpeed;
		temperature = message.heat;
		maxHeat = message.maxHeat;
		dustChance = message.dustChance;
	}

	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setInteger("progress", progress);
		tagCompound.setFloat("temperature", temperature);
		tagCompound.setBoolean("formed", formed);
		
		if (inputPositions != null && !inputPositions.isEmpty())
		{
			tagCompound.setTag("inputCoords", writePositionListToNBT(inputPositions));
		}
		if (outputPositions != null && !outputPositions.isEmpty())
		{
			tagCompound.setTag("outputCoords", writePositionListToNBT(outputPositions));
		}
		if (modifierPositions != null && !modifierPositions.isEmpty())
		{
			tagCompound.setTag("modifierCoords", writePositionListToNBT(modifierPositions));
		}
		if (heaterPositions != null && !heaterPositions.isEmpty())
		{
			tagCompound.setTag("heaterCoords", writePositionListToNBT(heaterPositions));
		}
	}
	
	private NBTTagCompound writePositionListToNBT(List<Position> posList)
	{
		NBTTagCompound coordCompound = new NBTTagCompound();
		for (int i = 0; i < posList.size(); i++)
		{
			coordCompound.setIntArray(Integer.toString(i), posList.get(i).toIntArray());
		}
		return coordCompound;
	}
	
	@Override
	public String getInventoryName()
	{
		return Localization.Container.REFINER_ADVANCED;
	}
	
	
	public void form()
	{
		formed = true;
		recalculateStructureProppertys();
	}
	
	public void registerInput(IMultiblockInput input)
	{
		if (!inputs.contains(input))
		{
			inputs.add(input);
			inputPositions.add(new Position(input.getCoords()));
		}
	}
		
	public void registerOutput(IMultiblockOutput output)
	{
		if (!outputs.contains(output))
		{
			outputs.add(output);
			outputPositions.add(new Position(output.getCoords()));
			outputAllItems();
		}
	}
	
	public void registerModifyer(IAdvancedRefinerModifier modifier)
	{
		if (!modifiers.contains(modifier))
		{
			modifiers.add(modifier);
			modifierPositions.add(new Position(modifier.getCoords()));
			computeAttributes();
		}
	}
	
	public void registerHeater(IAdvancedRefinerHeater heater)
	{
		if (!heaters.contains(heater))
		{
			heaters.add(heater);
			heaterPositions.add(new Position(heater.getCoords()));
		}
	}
	
	private void computeAttributes()
	{
		if (modifiers != null)
		{
			for (IAdvancedRefinerModifier modifier : modifiers)
			{
				refiningSpeed += modifier.getRefiningSpeedChange();
				maxHeat += modifier.getMaxHeatChange();
				dustChance += modifier.getDustChanceChange();
			}
		}
	}
	
	
	@Override
	public void updateEntity()
	{
		coolDown();
		if (formed)							// True if the structure is active
		{
			increaseHeat();
			if (temperature > maxHeat)		// The temperature has breached the limit, action should be taken
			{
				overheat();
			}
			if (progress == -1)				// True if the structure is waiting for the right conditions to start 
			{
				if (canStartRefining())
				{
					progress = 0;
				}
			}
			if (progress >= 0 && progress < REQUIRED_PROGRESS)		// True if the structure is currently working
			{
				progress += (int) (1f + ((float) refiningSpeed * temperature / 100));
			}
			if (progress >= REQUIRED_PROGRESS)						// True if the structure has finished working
			{
				if (!worldObj.isRemote)		// On the server side, update the clients
				{
					PacketController
						.getNetworkWrapper()
						.sendToAll(
								new PacketSyncAdvancedRefiner(xCoord, yCoord, zCoord, 
										formed, progress, refiningSpeed, temperature, maxHeat, dustChance));
				}
				process();
				progress = -1;				// Go back into waiting mode again
			}
		}	
	}
	
	private void overheat()
	{
		worldObj.createExplosion((Entity) null, xCoord + 0.5f, yCoord + 0.5f, zCoord + 0.5f, 26, true);
	}

	private void coolDown()
	{	
		temperature -= getCoolDownFactor() * (temperature - getRoomTemp());
	}

	private void increaseHeat()
	{
		if (heaters == null || heaters.isEmpty())
		{
			initInteractorsFromCoords();
		}
		for (IAdvancedRefinerHeater heater : heaters)
		{
			if (heater != null)
			{
				temperature += heater.getTemperatureIncrease();
			}
		}
	}
	
	/**
	 * @return The coolDownFactor. Will be calculated if it has not been yet
	 */
	public float getCoolDownFactor()
	{
		if (coolDownFactor == VALUE_NOT_DEFINED)
		{
			recalculateStructureProppertys();
		}
		assert coolDownFactor < 1 && coolDownFactor > 0 : "Wrong coolDown factor, would lead to float overflow";
		return coolDownFactor;
	}
	
	/**
	 * @return the 'room temperature' depending on the biome
	 */
	public int getRoomTemp()
	{
		if (roomTemp == VALUE_NOT_DEFINED)
		{
			roomTemp = (int) (worldObj.getBiomeGenForCoords(xCoord, yCoord).temperature * 25f);

		}
		return roomTemp;
	}
	
	private boolean canStartRefining()
	{
		if (inventory[INPUT] == null)
		{
			inputItem();
		}
		return inventory[INPUT] != null && formed 
				&& (inventory[OUTPUT_PRIMARY] == null 
						|| inventory[OUTPUT_PRIMARY].stackSize < inventory[OUTPUT_PRIMARY].getMaxStackSize()) 
				&& (inventory[OUTPUT_SECONDARY] == null 
						|| inventory[OUTPUT_SECONDARY].stackSize < inventory[OUTPUT_SECONDARY].getMaxStackSize()) 
				&& temperature <= getMaxHeat();			// TODO questionable?
	}
	
	private float getMaxHeat()
	{
		if (maxHeat == VALUE_NOT_DEFINED)
		{
			recalculateStructureProppertys();
		}
		return maxHeat;
	}

	private void inputItem()
	{
		if (!inputs.isEmpty())
		{
			for (IMultiblockInput input : inputs)
			{
				if (input != null)
				{
					ItemStack inputStack = input.inputItem(Items.ender_pearl);
					if (inputStack != null && inventory[INPUT] == null)
					{
						inventory[INPUT] = inputStack;
						return;
					}
				}
			}
		}
		else
		{
			initInteractorsFromCoords();
		}
	}
	
	private void process()
	{
		decrStackSize(INPUT, 1);
		
		if (inventory[OUTPUT_PRIMARY] == null)
		{
			inventory[OUTPUT_PRIMARY] = new ItemStack(ModItems.enderPearlCore);
		}
		else
		{
			inventory[OUTPUT_PRIMARY].stackSize ++;
		}
		
		int dustAmount = 0;
		float dustChance = this.dustChance;
		while (dustChance > 1)
		{
			dustAmount ++;
			dustChance --;
		}
		if (worldObj.rand.nextFloat() <= dustChance)
		{
			dustAmount ++;
		}
		
		if (inventory[OUTPUT_SECONDARY] == null)
		{
			inventory[OUTPUT_SECONDARY] = new ItemStack(Items.cookie, dustAmount);
		}
		else if (inventory[OUTPUT_SECONDARY].stackSize + dustAmount < inventory[OUTPUT_SECONDARY].getMaxStackSize())
		{
			inventory[OUTPUT_SECONDARY].stackSize += dustAmount;
		}		
		else
		{
			inventory[OUTPUT_SECONDARY].stackSize = inventory[OUTPUT_SECONDARY].getMaxStackSize();
		}
		outputAllItems();
	}
	
	private void outputAllItems()
	{
		if (outputItem(inventory[OUTPUT_PRIMARY]))
		{
			inventory[OUTPUT_PRIMARY] = null;
		}
		if (outputItem(inventory[OUTPUT_SECONDARY]))
		{
			inventory[OUTPUT_SECONDARY] = null;
		}
	}
	
	private boolean outputItem(ItemStack outputStack)
	{
		if (outputs.isEmpty())
		{
			initInteractorsFromCoords();
		}
		for (IMultiblockOutput output : outputs)
		{
			if (output.outputItem(outputStack))
			{
				return true;
			}
		}
		return false;
	}

	
	public void unForm()
	{
		formed = false;
		progress = -1;
		
		inputs.clear();
		inputPositions.clear();
		outputs.clear();
		outputPositions.clear();
		modifiers.clear();
		modifierPositions.clear();
		heaters.clear();
		heaterPositions.clear();
	}

	
	public int getProgress()
	{
		return progress;
	}

	public float getHeat()
	{
		return temperature;
	}

}
