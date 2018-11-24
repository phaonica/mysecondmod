package phaonica.mysecondmod.objects.blocks.machines.sinterer;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import phaonica.mysecondmod.init.BlockInit;
import phaonica.mysecondmod.init.ItemInit;

public class SinteringFurnaceRecipes
{
	private static final SinteringFurnaceRecipes INSTANCE = new SinteringFurnaceRecipes();
	private final Table<ItemStack, ItemStack, ItemStack> smeltingList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static SinteringFurnaceRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private SinteringFurnaceRecipes()
	{
		// ALL THESE RECIPES SEEM TO WORK
		
		// mc item + mc item = mc item
		addSinteringRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.DIAMOND), 5.0F);

		// custom item + custome item = custom item
		addSinteringRecipe(new ItemStack(BlockInit.ORE_TUTORIAL_OVERWORLD), new ItemStack(ItemInit.PICKAXE_TUTORIAL), new ItemStack(ItemInit.INGOT_TUTORIAL), 5.0F);

		// custom item + mc item = mc item
		addSinteringRecipe(new ItemStack(ItemInit.INGOT_TUTORIAL), new ItemStack(Items.GOLD_INGOT), new ItemStack(Items.EMERALD), 5.0F);
	}
	
	public void addSinteringRecipe(ItemStack input1, ItemStack input2, ItemStack result, float experience)
	{
		if(getSinteringResult(input1, input2) != ItemStack.EMPTY) return;
		this.smeltingList.put(input1, input2, result);
		this.experienceList.put(result,  Float.valueOf(experience));
	}
	
	public ItemStack getSinteringResult(ItemStack input1, ItemStack input2)
	{
		for(Entry<ItemStack, Map<ItemStack, ItemStack>> entry : this.smeltingList.columnMap().entrySet())
		{
			if(this.compareItemStacks(input1, (ItemStack)entry.getKey()))
			{
				for(Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
				{
					if(this.compareItemStacks(input2, (ItemStack)ent.getKey()))
					{
						return (ItemStack)ent.getValue();
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public Table<ItemStack, ItemStack, ItemStack> getDualSmeltingList()
	{
		return this.smeltingList;
	}
	
	public float getSinteringExperience(ItemStack stack)
	{
		for ( Entry<ItemStack, Float> entry : this.experienceList.entrySet() )
		{
			if(this.compareItemStacks(stack,  (ItemStack)entry.getKey()))
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}
}
