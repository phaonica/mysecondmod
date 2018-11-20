package phaonica.mysecondmod.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import phaonica.mysecondmod.util.Reference;

public class SoundsHandler
{
	public static SoundEvent ENTITY_CENTAUR_AMBIENT;
	public static SoundEvent ENTITY_CENTAUR_HURT;
	public static SoundEvent ENTITY_CENTAUR_DEATH;
	
	public static void registerSounds()
	{
		ENTITY_CENTAUR_AMBIENT = registerSound("entity.centaur.ambient");
		ENTITY_CENTAUR_HURT = registerSound("entity.centaur.hurt");
		ENTITY_CENTAUR_DEATH = registerSound("entity.centaur.death");
	}
	
	private static SoundEvent registerSound(String name)
	{
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID,name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
	
}
