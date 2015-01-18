package morethanhidden.DoomShrine.renderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import morethanhidden.DoomShrine.TileDoomShrine;
import morethanhidden.DoomShrine.models.ModelDoomShrine;


public class RenderDoomShrine extends TileEntitySpecialRenderer {
        
        private final ModelDoomShrine model;
        EntityItem entItem = null;
        
        public RenderDoomShrine() {
                this.model = new ModelDoomShrine();
        }
        
        private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
                int meta = world.getBlockMetadata(x, y, z);
                GL11.glPushMatrix();
                GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
                GL11.glPopMatrix();
        }
        
        @Override
		public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f1) {

            model.renderDoomShrine(x, y, z);
            
    		TileDoomShrine tileEntity = (TileDoomShrine)te;
    		int slot = 0;
    		if(tileEntity.getStackInSlot(slot)!= null){
    		if((entItem == null) || entItem.getEntityItem().getItem() != tileEntity.getStackInSlot(slot).getItem())
    		
    		entItem = new EntityItem(tileEntity.getWorldObj(), x, y, z, tileEntity.getStackInSlot(slot));
    		
    		GL11.glPushMatrix();
    			this.entItem.hoverStart = 0.0F;
    			RenderItem.renderInFrame = true;
    			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.08F, (float)z + 0.5F);
    			GL11.glRotatef(0, 90, 1, 1);
    			RenderManager.instance.renderEntityWithPosYaw(this.entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
    			RenderItem.renderInFrame = false;
    		GL11.glPopMatrix();
    		}
			
		}

        private void adjustLightFixture(World world, int i, int j, int k, Block block) {
                Tessellator tess = Tessellator.instance;
                float brightness = block.getLightValue(world, i, j, k);
                int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
                int modulousModifier = skyLight % 65536;
                int divModifier = skyLight / 65536;
                tess.setColorOpaque_F(brightness, brightness, brightness);
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,  (float) modulousModifier,  divModifier);
        }
        

	
}