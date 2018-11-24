package phaonica.mysecondmod.objects.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import phaonica.mysecondmod.commands.util.Teleport;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.init.ItemInit;
import phaonica.mysecondmod.objects.blocks.item.ItemBlockVariants;
import phaonica.mysecondmod.util.handlers.EnumHandler;

public class BlockTeleporter extends BlockBase
{

	public BlockTeleporter(String name)
	{
		super(name, Material.ROCK);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			Teleport.teleportToDimension(playerIn, -1, playerIn.getPosition().getX(), playerIn.getPosition().getY() + 5, playerIn.getPosition().getZ());		
			return true;			
		}
		
		return false;
	}

}
