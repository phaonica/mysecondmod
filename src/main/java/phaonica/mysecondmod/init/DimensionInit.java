package phaonica.mysecondmod.init;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import phaonica.mysecondmod.world.dimension.library.DimensionGreatLibrary;

public class DimensionInit
{
	public static final DimensionType LIBRARY = DimensionType.register("Library", "_library", 2, DimensionGreatLibrary.class, false);
	
	public static void registerDimension()
	{
		DimensionManager.registerDimension(2, LIBRARY);
	}
}
