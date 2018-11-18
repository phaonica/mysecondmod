package phaonica.mysecondmod.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import phaonica.mysecondmod.Main;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.init.ItemInit;
import phaonica.mysecondmod.objects.blocks.item.ItemBlockVariants;
import phaonica.mysecondmod.util.IHasModel;
import phaonica.mysecondmod.util.handlers.EnumHandler;
import phaonica.mysecondmod.util.interfaces.IMetaName;

public class BlockPlank extends Block implements IMetaName, IHasModel
{	
	public static final PropertyEnum<EnumHandler.EnumType> VARIANT = PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class);

	private String name, dimension;
	
	public BlockPlank(String name)
	{
		super(Material.WOOD);
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumHandler.EnumType.TUTORIAL));
		
		setCreativeTab(CreativeTabs.MATERIALS);

		
		this.name = name;	
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override	
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for(EnumHandler.EnumType customblockplanks$enumtype : EnumHandler.EnumType.values())
		{
			items.add(new ItemStack(this,1,customblockplanks$enumtype.getMeta()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, (int)(getMetaFromState(world.getBlockState(pos))));
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	
	public String getSpecialName(ItemStack stack)
	{
		return EnumHandler.EnumType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels()
	{

		for(int i = 0; i < EnumHandler.EnumType.values().length; i++)
		{
			// will do "ore_end_tutorial", and "ore_nether_aluminum" for example
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this),  i ,  "planks_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
		
	}
}
