package phaonica.mysecondmod.world.gen.generators;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.objects.blocks.BlockLeaf;
import phaonica.mysecondmod.objects.blocks.BlockLogs;
import phaonica.mysecondmod.objects.blocks.BlockSaplings;
import phaonica.mysecondmod.util.handlers.EnumHandler;

public class WorldGenAluminumTree extends WorldGenAbstractTree
{
	public static final IBlockState LOG = BlockInit.LOGS.getDefaultState().withProperty(BlockLogs.VARIANT, EnumHandler.EnumType.ALUMINIUM);
	public static final IBlockState LEAF = BlockInit.LEAVES.getDefaultState().withProperty(BlockLeaf.VARIANT, EnumHandler.EnumType.ALUMINIUM);
	
	private int minHeight;
	
	public WorldGenAluminumTree()
	{
		super(false);
		this.minHeight = 12;
		
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		
		System.out.println("doing generate");
		
		int height = this.minHeight + rand.nextInt(3); // how much height will differ from min (12-15)
		System.out.println("minHeight: " + minHeight);
		System.out.println("height: " + height);		
		
		boolean hasClearance = true;
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("z: " + z);
		
		int treeHeight = y + height + 1;
		int trunkHeight = treeHeight - 2;
		
		for (int yPos = y; yPos <= treeHeight; yPos++)
		{
			int clearanceWidth = 2;
			
			if ( yPos == y ) clearanceWidth = 1; // if this is the first loop
			
			if ( yPos >= trunkHeight) clearanceWidth = 2; // if ypos is the trunkHeight
			
			BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(); // a block pos that is changeable
			
			for (int xPos = x - clearanceWidth; xPos <= x + clearanceWidth && hasClearance; xPos++)
			{
				for (int zPos = z - clearanceWidth; zPos <= z + clearanceWidth && hasClearance; zPos++)
				{
					if (yPos >= 0 && yPos < world.getHeight())
					{
						if(!this.isReplaceable(world,  new BlockPos(xPos, yPos, zPos))) 
						{
							System.out.println("blocked at:");
							System.out.println("xPos: " + xPos);
							System.out.println("yPos: " + yPos);
							System.out.println("zPos: " + zPos);							
							hasClearance = false;
						}
						
					}
					else
					{
						System.out.println("blocked at:");
						System.out.println("xPos: " + xPos);
						System.out.println("yPos: " + yPos);
						System.out.println("zPos: " + zPos);
						hasClearance = false;
					}
				}
			}
		}
		
		System.out.println("hasClearance: " + hasClearance);

		
		if(!hasClearance)
		{
			return false;
		}
		else
		{
			BlockPos blockBelowSapling = pos.down();
			
			IBlockState stateOfBlockBelowSapling = world.getBlockState(blockBelowSapling);
			
			boolean isSoil = stateOfBlockBelowSapling.getBlock().canSustainPlant(stateOfBlockBelowSapling, world, blockBelowSapling, EnumFacing.UP, (BlockSaplings)BlockInit.SAPLINGS);
			
			if(isSoil && y < world.getHeight() - height - 1)
			{
				System.out.println("isSoil ok AND top of tree is lower than the top of the world");

				stateOfBlockBelowSapling.getBlock().onPlantGrow(stateOfBlockBelowSapling, world, blockBelowSapling, pos);
				
				for (int yPos = y - 3 + height; yPos <= y + height; yPos++)
				{
					int b1 = yPos - (y+height);
					int b2 = 1 - b1 / 2;
					
					for (int xPos = x - b2; xPos <= x + b2; xPos++)
					{
						int b3 = xPos - x;
						for (int zPos = z - b2; zPos <= z + b2; zPos++)
						{
							int b4 = zPos - z;
							if (Math.abs(b3) != b2 || Math.abs(b4) != b2 || rand.nextInt(2) != 0 && b1 != 0)
							{
								BlockPos treePos = new BlockPos(xPos, yPos, zPos);
								IBlockState treeState = world.getBlockState(treePos);
								if( treeState.getBlock().isAir(treeState, world, treePos) || treeState.getBlock().isLeaves(treeState, world, treePos) )
								{
									this.setBlockAndNotifyAdequately(world, treePos, LEAF);
									this.setBlockAndNotifyAdequately(world, treePos.add(0, -0.25*height,0), LEAF);
									this.setBlockAndNotifyAdequately(world, treePos.add(0, -0.50*height,0), LEAF);
								}
							}
						}		
					}
				}
				
				for(int logHeight = 0; logHeight < height; logHeight++)
				{
					BlockPos up = pos.up(logHeight);
					IBlockState logState = world.getBlockState(up);
					if( logState.getBlock().isAir(logState, world, up) || logState.getBlock().isLeaves(logState, world, up) )
					{
						this.setBlockAndNotifyAdequately(world, pos.up(logHeight), LOG);
					}
				}
				
				return true;
				
			}
			else
			{
				System.out.println("is not soil OR tree hits top of world");
		
			}
		}
		
		return true;
		
	}

}
