package phaonica.mysecondmod.init;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import phaonica.mysecondmod.Main;
import phaonica.mysecondmod.entity.EntityCentaur;
import phaonica.mysecondmod.util.Reference;

public class EntityInit
{
	public static void registerEntities()
	{
		registerEntity("centaur", EntityCentaur.class, Reference.ENTITY_CENTAUR, 50, 11437146, 000000);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
}
