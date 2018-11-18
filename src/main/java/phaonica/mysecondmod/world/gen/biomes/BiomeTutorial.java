package phaonica.mysecondmod.world.gen.biomes;

import java.util.Random;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.objects.blocks.BlockOres;
import phaonica.mysecondmod.objects.blocks.BlockPlank;
import phaonica.mysecondmod.util.handlers.EnumHandler;
import phaonica.mysecondmod.world.gen.generators.WorldGenTutorialTree;

public class BiomeTutorial extends Biome
{
	protected static final WorldGenAbstractTree TREE = new WorldGenTutorialTree();
	
	public BiomeTutorial()
	{
		super(new BiomeProperties("Tutorial")
				.setBaseHeight(1.5F)
				.setHeightVariation(1.2F)
				.setTemperature(0.6F)
				.setRainDisabled()
				.setWaterColor(16711680));	
		
		
		topBlock = Blocks.DIRT.getDefaultState(); // NOTE: IF YOU HAVE A CUSTOM DIRT, YOU NEED TO DECLARE CANSUSTAINPLANTS() IN THAT BLOCK
		//topBlock = BlockInit.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, EnumHandler.EnumType.TUTORIAL);
		fillerBlock = BlockInit.ORE_TUTORIAL_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT, EnumHandler.EnumType.TUTORIAL);
		
		// look in 
		this.decorator.coalGen = new WorldGenMinable(BlockInit.PLANKS.getDefaultState().withProperty(BlockPlank.VARIANT, EnumHandler.EnumType.TUTORIAL), 10);
		
		this.decorator.treesPerChunk = 2;
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableMonsterList.clear();
		
		this.spawnableCreatureList.add(new SpawnListEntry(EntityWither.class,100,10,50));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 120, 40, 40));
		
		
	}
	
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand)
	{
		return TREE;
	}
}
