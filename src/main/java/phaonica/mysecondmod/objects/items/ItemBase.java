package phaonica.mysecondmod.objects.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import phaonica.mysecondmod.Main;
import phaonica.mysecondmod.init.ItemInit;
import phaonica.mysecondmod.util.IHasModel;

public class ItemBase extends Item implements IHasModel
{
	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");	
	}

}
