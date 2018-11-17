package world.gen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.objects.blocks.BlockOres;
import phaonica.mysecondmod.util.handlers.EnumHandler;

public class WorldGenCustomOres implements IWorldGenerator
{
	private WorldGenerator ore_nether_tutorial, ore_overworld_tutorial, ore_end_tutorial;
	private WorldGenerator ore_nether_aluminium, ore_overworld_aluminium, ore_end_aluminium;
	
	public WorldGenCustomOres()
	{
		ore_nether_tutorial = new WorldGenMinable(BlockInit.ORE_TUTORIAL_NETHER.getDefaultState().withProperty(BlockOres.VARIANT,EnumHandler.EnumType.TUTORIAL), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_overworld_tutorial = new WorldGenMinable(BlockInit.ORE_TUTORIAL_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT,EnumHandler.EnumType.TUTORIAL), 9, BlockMatcher.forBlock(Blocks.STONE));
		ore_end_tutorial = new WorldGenMinable(BlockInit.ORE_TUTORIAL_END.getDefaultState().withProperty(BlockOres.VARIANT,EnumHandler.EnumType.TUTORIAL), 9, BlockMatcher.forBlock(Blocks.END_STONE));

		ore_nether_aluminium = new WorldGenMinable(BlockInit.ORE_TUTORIAL_NETHER.getDefaultState().withProperty(BlockOres.VARIANT,EnumHandler.EnumType.ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_overworld_aluminium = new WorldGenMinable(BlockInit.ORE_TUTORIAL_OVERWORLD.getDefaultState().withProperty(BlockOres.VARIANT,EnumHandler.EnumType.ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.STONE));
		ore_end_aluminium= new WorldGenMinable(BlockInit.ORE_TUTORIAL_END.getDefaultState().withProperty(BlockOres.VARIANT,EnumHandler.EnumType.ALUMINIUM), 9, BlockMatcher.forBlock(Blocks.END_STONE));
	}
	

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.getDimension())
		{
			case -1: 
				runGenerator(ore_nether_tutorial, world, random, chunkX, chunkZ, 50, 0, 200); // NETHER
				runGenerator(ore_nether_aluminium, world, random, chunkX, chunkZ, 50, 0, 200); // NETHER
				break;
			case 0: 
				runGenerator(ore_overworld_tutorial, world, random, chunkX, chunkZ, 50, 0, 100); // OVERWORLD
				runGenerator(ore_overworld_aluminium, world, random, chunkX, chunkZ, 50, 0, 100); // OVERWORLD
				break;
			case 1:
				runGenerator(ore_end_tutorial, world, random, chunkX, chunkZ, 50, 0, 256); // OVERWORLD
				runGenerator(ore_end_aluminium, world, random, chunkX, chunkZ, 50, 0, 256); // OVERWORLD
				break;
		}
	}
	
	
	
	
	public void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
	{
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256)
		{
			throw new IllegalArgumentException("ore generated out of bounds");
		}
		
		int heightDiff = maxHeight - minHeight + 1;
		
		for (int i = 0; i < chance; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int z = chunkZ * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}




}
