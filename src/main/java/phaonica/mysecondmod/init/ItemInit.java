package phaonica.mysecondmod.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import phaonica.mysecondmod.objects.armour.ArmorBase;
import phaonica.mysecondmod.objects.items.ItemBase;
import phaonica.mysecondmod.objects.items.food.ItemCustomFood;
import phaonica.mysecondmod.objects.items.food.ItemRice;
import phaonica.mysecondmod.objects.tools.ToolPickaxe;
import phaonica.mysecondmod.util.Reference;

public class ItemInit
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// MATERIALS
	public static final ToolMaterial MATERIAL_TOOL_TUTORIAL = EnumHelper.addToolMaterial("material_tool_tutorial", 2, 250, 6.0F, 2.0F, 14);
	public static final ArmorMaterial MATERIAL_ARMOR_TUTORIAL = EnumHelper.addArmorMaterial("material_armor_tutorial", Reference.MOD_ID +  ":tutorial", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F);
	
	// ITEMS
	public static final Item INGOT_TUTORIAL = new ItemBase("ingot_tutorial");

	// TOOLS
	public static final Item PICKAXE_TUTORIAL = new ToolPickaxe("pickaxe_tutorial", MATERIAL_TOOL_TUTORIAL);
	// Note: axe is slightly different. look it up in video

	
	// ARMOR
	public static final Item HELMET_TUTORIAL = new ArmorBase("helmet_tutorial", MATERIAL_ARMOR_TUTORIAL, 1, EntityEquipmentSlot.HEAD);
	
	// FOOD
	public static final Item RICE_BOWL = new ItemCustomFood("rice_bowl", 8, false);
	public static final Item RICE = new ItemRice("rice", 1, false);
	

}
