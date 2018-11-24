package phaonica.mysecondmod.util.handlers;

import net.minecraftforge.fml.common.registry.GameRegistry;
import phaonica.mysecondmod.objects.blocks.machines.sinterer.TileEntitySinteringFurnace;

public class TileEntityHandler
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySinteringFurnace.class, "sintering_furnace");
	}
}
