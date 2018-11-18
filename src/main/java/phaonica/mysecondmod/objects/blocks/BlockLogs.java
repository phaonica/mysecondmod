package phaonica.mysecondmod.objects.blocks;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import phaonica.mysecondmod.Main;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.init.ItemInit;
import phaonica.mysecondmod.objects.blocks.item.ItemBlockVariants;
import phaonica.mysecondmod.util.IHasModel;
import phaonica.mysecondmod.util.handlers.EnumHandler;
import phaonica.mysecondmod.util.interfaces.IMetaName;

public class BlockLogs extends BlockLog implements IMetaName, IHasModel
{
	
	private String name;
	
	public static final PropertyEnum<EnumHandler.EnumType> VARIANT = 
			PropertyEnum.<EnumHandler.EnumType>create("variant", EnumHandler.EnumType.class, new Predicate<EnumHandler.EnumType>()
	{
		public boolean apply(@Nullable EnumHandler.EnumType apply )
		{
			return apply.getMeta() < 2;
		}
	});
	
	
	public BlockLogs(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.WOOD);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,  EnumHandler.EnumType.TUTORIAL).withProperty(LOG_AXIS, EnumAxis.Y));
		
		setCreativeTab(CreativeTabs.MATERIALS);

		
		this.name = name;	
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariants(this).setRegistryName(this.getRegistryName()));
	
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
		IBlockState state =  this.getDefaultState().withProperty(VARIANT, EnumHandler.EnumType.byMetadata((meta&1)%2));
		
		switch(meta & 6)
		{
			case 0 : 
				state = state.withProperty(LOG_AXIS,  EnumAxis.Y);
				break;
			case 2 : 
				state = state.withProperty(LOG_AXIS,  EnumAxis.X);
				break;
			case 4 : 
				state = state.withProperty(LOG_AXIS,  EnumAxis.Z);
				break;
			default : 
				state = state.withProperty(LOG_AXIS,  EnumAxis.NONE);
				break;
		}
		
		return state;
	
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state)
	{	
		int i = 0;
		i = i | ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta();
		switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
		{
			case X:
				i |= 2;
				break;
			case Y:
				i |= 4;
				break;
			case Z: 
				i |= 6;
				break;
		}
		
		return i;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS});
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumHandler.EnumType)state.getValue(VARIANT)).getMeta());
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
	public void registerModels()
	{

		for(int i = 0; i < EnumHandler.EnumType.values().length; i++)
		{
			// will do "ore_end_tutorial", and "ore_nether_aluminum" for example
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this),  i ,  "log_" + EnumHandler.EnumType.values()[i].getName(), "inventory");
		}
		
	}
	
	
	
	
	
	

}
