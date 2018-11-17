package phaonica.mysecondmod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import phaonica.mysecondmod.objects.blocks.BlockBase;
import phaonica.mysecondmod.objects.blocks.BlockOres;

public class BlockInit
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ORE_TUTORAL = new BlockBase("ore_tutorial", Material.IRON);
	
	public static final Block ORE_TUTORIAL_END = new BlockOres("ore_end", "end");
	public static final Block ORE_TUTORIAL_OVERWORLD = new BlockOres("ore_overworld", "overworld");
	public static final Block ORE_TUTORIAL_NETHER = new BlockOres("ore_nether", "nether");
	
}
