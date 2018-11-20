package phaonica.mysecondmod.util.handlers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import phaonica.mysecondmod.entity.EntityCentaur;
import phaonica.mysecondmod.entity.render.RenderCentaur;

public class RenderHandler
{
	public static void RegisterEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCentaur.class, new IRenderFactory<EntityCentaur>()
				{
					@Override
					public Render<? super EntityCentaur> createRenderFor(RenderManager manager)
					{
						// TODO Auto-generated method stub
						return new RenderCentaur(manager);
					}
				});
	}
}
