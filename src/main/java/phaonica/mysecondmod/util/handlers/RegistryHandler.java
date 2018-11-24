package phaonica.mysecondmod.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import phaonica.mysecondmod.Main;
import phaonica.mysecondmod.commands.CommandDimensionTeleport;
import phaonica.mysecondmod.init.BiomeInit;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.init.EntityInit;
import phaonica.mysecondmod.init.ItemInit;
import phaonica.mysecondmod.util.IHasModel;
import phaonica.mysecondmod.world.gen.WorldGenCustomOres;
import phaonica.mysecondmod.world.gen.WorldGenCustomStructures;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent	
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for (Item item: ItemInit.ITEMS) {
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for (Block block: BlockInit.BLOCKS)
		{
			if ( block instanceof IHasModel )
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void initRegistries()
	{
		SoundsHandler.registerSounds();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void otherRegistries()
	{
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(),  0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
		
		BiomeInit.registerBiomes();
		EntityInit.registerEntities();
		RenderHandler.RegisterEntityRenders();
		
		
	}
	
	public static void serverRegistries(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandDimensionTeleport());
	}
	
}
