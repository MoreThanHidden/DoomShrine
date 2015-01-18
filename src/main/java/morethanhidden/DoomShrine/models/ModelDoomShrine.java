package morethanhidden.DoomShrine.models;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class ModelDoomShrine extends ModelBase
{
  //fields
	private static final ResourceLocation shrine_texture = new ResourceLocation("doomshrine:textures/models/DoomShrine.png");
    ModelRenderer TopArea;
    ModelRenderer BottomArea;
    ModelRenderer Line1;
    ModelRenderer Line2;
    ModelRenderer Line3;
    ModelRenderer Line4;
  
  public ModelDoomShrine()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      TopArea = new ModelRenderer(this, 0, 24);
      TopArea.addBox(-6F, -8F, -6F, 12, 8, 12);
      TopArea.setRotationPoint(0F, 8F, 0F);
      TopArea.setTextureSize(64, 64);
      TopArea.mirror = true;
      setRotation(TopArea, 0F, 0F, 0F);
      BottomArea = new ModelRenderer(this, 0, 0);
      BottomArea.addBox(-8F, -8F, -8F, 16, 8, 16);
      BottomArea.setRotationPoint(0F, 16F, 0F);
      BottomArea.setTextureSize(64, 64);
      BottomArea.mirror = true;
      setRotation(BottomArea, 0F, 0F, 0F);
      Line1 = new ModelRenderer(this, 48, 24);
      Line1.addBox(0F, 0F, 0F, 2, 6, 2);
      Line1.setRotationPoint(-6F, 2F, -6F);
      Line1.setTextureSize(64, 64);
      Line1.mirror = true;
      setRotation(Line1, 0F, 3.141593F, 0F);
      Line2 = new ModelRenderer(this, 48, 24);
      Line2.addBox(0F, 0F, 0F, 2, 6, 2);
      Line2.setRotationPoint(6F, 2F, 6F);
      Line2.setTextureSize(64, 64);
      Line2.mirror = true;
      setRotation(Line2, 0F, 0F, 0F);
      Line3 = new ModelRenderer(this, 48, 24);
      Line3.addBox(0F, 0F, 0F, 2, 6, 2);
      Line3.setRotationPoint(-6F, 2F, 6F);
      Line3.setTextureSize(64, 64);
      Line3.mirror = true;
      setRotation(Line3, 0F, -1.570796F, 0F);
      Line4 = new ModelRenderer(this, 48, 24);
      Line4.addBox(0F, 0F, 0F, 2, 6, 2);
      Line4.setRotationPoint(6F, 2F, -6F);
      Line4.setTextureSize(64, 64);
      Line4.mirror = true;
      setRotation(Line4, 0F, 1.570796F, 0F);
  }
  
  public void renderDoomShrine(double x, double y, double z)
  {
  GL11.glPushMatrix(); 
	GL11.glTranslatef((float) x + 0.5F, (float) y + 1.0F, (float) z + 0.5F); 
  	GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
  	FMLClientHandler.instance().getClient().renderEngine.bindTexture(shrine_texture);
  	this.render((Entity)null, 0.0F, -0.5F, -0.1F, 0.0F, 0.0F, 0.0625F);
  GL11.glPopMatrix();
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    TopArea.render(f5);
    BottomArea.render(f5);
    Line1.render(f5);
    Line2.render(f5);
    Line3.render(f5);
    Line4.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
