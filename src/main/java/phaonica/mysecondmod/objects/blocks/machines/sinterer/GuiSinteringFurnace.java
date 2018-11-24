package phaonica.mysecondmod.objects.blocks.machines.sinterer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import phaonica.mysecondmod.util.Reference;

public class GuiSinteringFurnace extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/sintering_furnace.png");
	
	private final InventoryPlayer player;
	private final TileEntitySinteringFurnace tileEntity;
	
	public GuiSinteringFurnace(InventoryPlayer player, TileEntitySinteringFurnace tileEntity)
	{
		super(new ContainerSinteringFurnace(player, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String tileName = this.tileEntity.getDisplayName().getUnformattedText();
		
		// getting the middle of the gui and the middle of the name
		int x_size = this.xSize;
		int x_middle = x_size / 2;
		int string_middle = this.fontRenderer.getStringWidth(tileName) / 2;
		int string_location_x = x_middle - string_middle + 3;
		
		int string_location_y = 8;
		
		int color = 4210752;		
		
		this.fontRenderer.drawString(tileName, string_location_x, string_location_y, color);
		
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize-96+2, color);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); // white
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		if (TileEntitySinteringFurnace.isBurning(tileEntity))
		{
			int k = this.getBurnLeftScaled(13);
			this.drawTexturedModalRect(this.guiLeft + 8, this.guiTop+54+12-k, 176, 12-k, 14, k+1);
		}
		
		int l = this.getCookProgressScaled(24); // arrow is 24 long
		this.drawTexturedModalRect(this.guiLeft+44, this.guiTop+36, 176, 14, l+1, 16);
		
	}
	
	private int getBurnLeftScaled(int pixels)
	{
		int i = this.tileEntity.getField(1);
		if ( i == 0 ) i = 200;
		return this.tileEntity.getField(0) * pixels / i ;
	}
	
	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileEntity.getField(2);
		int j = this.tileEntity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
