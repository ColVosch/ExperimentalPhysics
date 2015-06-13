package colVosch.experimentalPhysics.client.renderers;

import org.lwjgl.opengl.GL11;

import colVosch.experimentalPhysics.ExperimentalPhysics;
import colVosch.experimentalPhysics.client.models.ModelEndStoneAsteroid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEndStoneAsteroid extends Render
{
	
	private ModelEndStoneAsteroid model;
	private ResourceLocation texture;

	public RenderEndStoneAsteroid()
	{
		this.model = new ModelEndStoneAsteroid();
		this.texture = new ResourceLocation(ExperimentalPhysics.MODID, "textures/models/endStoneAsteroid.png");
	}
	
	@Override @SideOnly(Side.CLIENT)
	public void doRender(Entity entityAsteroid, double x, double y, double z, float f1, float f2)
	{
		GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture(null));                   
			GL11.glPushMatrix();
				float angle = (System.currentTimeMillis() / 200) % 360;
				GL11.glRotatef(angle, angle, angle, 1.0F);
				this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return texture;
	}
	
}
