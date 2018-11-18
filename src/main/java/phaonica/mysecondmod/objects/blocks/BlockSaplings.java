package phaonica.mysecondmod.objects.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
//import phaonica.mysecondmod.world.feature.tree.WorldGenAluminumTree;
//import phaonica.mysecondmod.world.feature.tree.WorldGenTutorialTree;
import phaonica.mysecondmod.Main;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.init.ItemInit;
import phaonica.mysecondmod.objects.blocks.item.ItemBlockVariants;
import phaonica.mysecondmod.util.IHasModel;
import phaonica.mysecondmod.util.handlers.EnumHandler;
import phaonica.mysecondmod.util.interfaces.IMetaName;
import phaonica.mysecondmod.world.gen.generators.WorldGenAluminumTree;
import phaonica.mysecondmod.world.gen.generators.WorldGenTutorialTree;

public class BlockSaplings extends BlockBush implements IGrowable, IMetaName, IHasModel
{
    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);

    private String name;
    
	public static final PropertyEnum<EnumHandler.EnumType> VARIANT = 
			PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class, new Predicate<EnumHandler.EnumType>()
	{
		public boolean apply(@Nullable EnumHandler.EnumType apply )
		{
			return apply.getMeta() < 2;
		}
	});
	
	public BlockSaplings(String name)
	{
		System.out.println("postInit called");

		setUnlocalizedName(name);
		setRegistryName(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.TUTORIAL).withProperty(STAGE, Integer.valueOf(0)));
		
		setCreativeTab(CreativeTabs.MATERIALS);

	
		this.name = name;	
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	
	}
	
	// SAPLING SHAPE
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return SAPLING_AABB;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	 @Override
	public boolean isFullCube(IBlockState state)
	{
		 return false;
	}
	
	 // VARIANTS
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(EnumHandler.EnumType customblockplanks$enumtype : EnumHandler.EnumType.values())
		{
			items.add(new ItemStack(this,1,customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	
	public String getSpecialName(ItemStack stack)
	{
		return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta & 1)).withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		i = i | ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
		i = i | ((Integer)state.getValue(STAGE)).intValue() << 3;
		return i;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT, STAGE});
	}
	
	@Override
	public void registerModels()
	{

		for(int i = 0; i < EnumHandler.EnumType.values().length; i++)
		{
			// will do "ore_end_tutorial", and "ore_nether_aluminum" for example
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this),  i ,  "sapling_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
		
	}
	
	
	// TREE CODE
	

	
	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		
		System.out.println("grow called");

		// TODO Auto-generated method stub
		if (((Integer)state.getValue(STAGE)).intValue() == 0)
		{
			System.out.println("not doing generateTree");

			// if it's in state 0, no growing
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
			
		}
		else
		{
			System.out.println("doing generateTree");

			this.generateTree(worldIn, rand, pos, state);
		}
	}
	
	public void generateTree(World world, Random rand, BlockPos pos, IBlockState state)
	{
		System.out.println("generateTree called");

		if ( !TerrainGen.saplingGrowTree(world, rand, pos) ) 
		{
			System.out.println("doing saplingGrowTree");
			return;			
		}
		else
		{
			System.out.println("not doing saplingGrowTree");
		}
		
		WorldGenerator gen = (WorldGenerator)(rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false));
		boolean flag = false;
		
		switch((EnumHandler.EnumType)state.getValue(VARIANT))
		{
		case TUTORIAL:
			System.out.println("doing TUTORIAL");
			gen = new WorldGenTutorialTree();
			break;
		case ALUMINIUM:
			System.out.println("doing ALUMINIUM");
			gen = new WorldGenAluminumTree();
			break;
		default:
			System.out.println("doing none");
		}
		
		
		
		IBlockState iBlockState = Blocks.AIR.getDefaultState();
		if(flag)
		{
			world.setBlockState(pos.add(0,0,0), iBlockState, 4);
			world.setBlockState(pos.add(1,0,0), iBlockState, 4);
			world.setBlockState(pos.add(0,0,1), iBlockState, 4);
			world.setBlockState(pos.add(1,0,1), iBlockState, 4);
		}
		else
		{
			world.setBlockState(pos, iBlockState,4);
		}
		
		if (!gen.generate(world, rand, pos))
		{
			if(flag)
			{
				world.setBlockState(pos.add(0,0,0), iBlockState, 4);
				world.setBlockState(pos.add(1,0,0), iBlockState, 4);
				world.setBlockState(pos.add(0,0,1), iBlockState, 4);
				world.setBlockState(pos.add(1,0,1), iBlockState, 4);
			}
			else
			{
				world.setBlockState(pos, iBlockState,4);
			}			
		}
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
	       if (!worldIn.isRemote)
	        {
	            super.updateTick(worldIn, pos, state, rand); //Calls the BlockBush version

	            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0)
	            {
	                this.grow(worldIn, rand, pos, state);
	            }
	        }
	}
	
	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
	{
		System.out.println("canGrow called");

		return true;
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
	{
		System.out.println("canUseBonemeal called");

		return (double)worldIn.rand.nextFloat() < 8.45D;
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state)
	{
		System.out.println("canSustainBush called");

		// return blocks that sapling can grown on
        return state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.FARMLAND;
	}
	
	
	

	
	
		
	
	
}
