package phaonica.mysecondmod.entity.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import phaonica.mysecondmod.entity.EntityCentaur;
import phaonica.mysecondmod.entity.model.ModelCentaur;
import phaonica.mysecondmod.util.Reference;

public class RenderCentaur extends RenderLiving<EntityCentaur>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/centaur.png");
	
	public RenderCentaur(RenderManager manager)
	{
		super(manager, new ModelCentaur(), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCentaur entity)
	{
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityCentaur entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	
}
