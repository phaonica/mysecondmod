package phaonica.mysecondmod.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCentaur extends EntityCow
{
	public EntityCentaur(World worldIn)
	{
		super(worldIn);
	}
	
	@Override
	public EntityCow createChild(EntityAgeable ageable)
	{
		return new EntityCentaur(world);
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return super.getAmbientSound();
	}
	@Override
	protected SoundEvent getHurtSound(DamageSource source)
	{
		return super.getHurtSound(source);
	}
	
	@Override
	protected SoundEvent getDeathSound()
	{
		return super.getDeathSound();
	}
	
	

}
