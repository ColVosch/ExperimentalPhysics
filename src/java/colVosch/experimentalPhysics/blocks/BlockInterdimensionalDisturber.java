package colVosch.experimentalPhysics.blocks;

import colVosch.experimentalPhysics.spaceField.SpaceFieldManager;
import colVosch.experimentalPhysics.util.Position;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockInterdimensionalDisturber extends ModBlock
{
	
	
	public BlockInterdimensionalDisturber()
	{
		super(Material.iron);
		setStepSound(soundTypeMetal);
		setHardness(18.0F);
	}
	
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int meta)
	{
		if (!world.isRemote)
		{
			SpaceFieldManager.spaceFields.get(world.provider.dimensionId).addTensionPoint(new Position(x, y, z), 10);
		}
	}
	
	@Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int oldMeta) 
    {
    	if (!world.isRemote)
		{
			SpaceFieldManager.spaceFields.get(world.provider.dimensionId).removeTensionPoint(new Position(x, y, z));
		}
    }
}
