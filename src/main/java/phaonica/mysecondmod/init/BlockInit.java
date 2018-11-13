package phaonica.mysecondmod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import phaonica.mysecondmod.objects.blocks.BlockBase;

public class BlockInit
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ORE_TUTORAL = new BlockBase("ore_tutorial", Material.IRON);
	
}
