package phaonica.mysecondmod.init;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import phaonica.mysecondmod.world.biomes.BiomeLibrary;
import phaonica.mysecondmod.world.biomes.BiomeTutorial;

public class BiomeInit
{
	public static final Biome TUTORIAL = new BiomeTutorial();
	public static final Biome LIBRARY_DIMENSION = new BiomeLibrary();
	
	public static void registerBiomes() 
	{
		initBiome(TUTORIAL, "Tutorial", BiomeType.WARM, Type.HILLS, Type.MOUNTAIN, Type.DRY);
		initBiome(LIBRARY_DIMENSION, "Library", BiomeType.WARM, Type.SPOOKY, Type.DENSE, Type.DRY);
	}	
	
	private static Biome initBiome(Biome biome, String name, BiomeType biomeType, Type...types)
	{
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.println("biome registered");;
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biomeType, new BiomeEntry(biome,10));
		BiomeManager.addSpawnBiome(biome);
		System.out.print("biome added");
		return biome;
	}
}
