package phaonica.mysecondmod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import phaonica.mysecondmod.objects.blocks.BlockBase;
import phaonica.mysecondmod.objects.blocks.BlockLeaf;
import phaonica.mysecondmod.objects.blocks.BlockLogs;
import phaonica.mysecondmod.objects.blocks.BlockOres;
import phaonica.mysecondmod.objects.blocks.BlockPlank;
import phaonica.mysecondmod.objects.blocks.BlockSantaHat;
import phaonica.mysecondmod.objects.blocks.BlockSaplings;
import phaonica.mysecondmod.objects.blocks.machines.sinterer.BlockSinteringFurnace;

public class BlockInit
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ORE_TUTORAL = new BlockBase("ore_tutorial", Material.IRON);
	
	public static final Block ORE_TUTORIAL_END = new BlockOres("ore_end", "end");
	public static final Block ORE_TUTORIAL_OVERWORLD = new BlockOres("ore_overworld", "overworld");
	public static final Block ORE_TUTORIAL_NETHER = new BlockOres("ore_nether", "nether");
	
	public static final Block PLANKS = new BlockPlank("planks");
	public static final Block LOGS = new BlockLogs("log");
	public static final Block LEAVES = new BlockLeaf("leaves");
	public static final Block SAPLINGS = new BlockSaplings("sapling");

	public static final Block SANTA_HAT = new BlockSantaHat("santa_hat");
	
	public static final Block SINTERING_FURNACE = new BlockSinteringFurnace("sintering_furnace");

}
