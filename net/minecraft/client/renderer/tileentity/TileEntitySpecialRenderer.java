/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ 
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class TileEntitySpecialRenderer
/*    */ {
/* 11 */   protected static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[] { new ResourceLocation("textures/blocks/destroy_stage_0.png"), new ResourceLocation("textures/blocks/destroy_stage_1.png"), new ResourceLocation("textures/blocks/destroy_stage_2.png"), new ResourceLocation("textures/blocks/destroy_stage_3.png"), new ResourceLocation("textures/blocks/destroy_stage_4.png"), new ResourceLocation("textures/blocks/destroy_stage_5.png"), new ResourceLocation("textures/blocks/destroy_stage_6.png"), new ResourceLocation("textures/blocks/destroy_stage_7.png"), new ResourceLocation("textures/blocks/destroy_stage_8.png"), new ResourceLocation("textures/blocks/destroy_stage_9.png") };
/*    */   
/*    */   protected TileEntityRendererDispatcher rendererDispatcher;
/*    */   private static final String __OBFID = "CL_00000964";
/*    */   
/*    */   public abstract void renderTileEntityAt(TileEntity paramTileEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, int paramInt);
/*    */   
/*    */   protected void bindTexture(ResourceLocation p_147499_1_) {
/* 19 */     TextureManager var2 = this.rendererDispatcher.renderEngine;
/*    */     
/* 21 */     if (var2 != null)
/*    */     {
/* 23 */       var2.bindTexture(p_147499_1_);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected World getWorld() {
/* 29 */     return this.rendererDispatcher.worldObj;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRendererDispatcher(TileEntityRendererDispatcher p_147497_1_) {
/* 34 */     this.rendererDispatcher = p_147497_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public FontRenderer getFontRenderer() {
/* 39 */     return this.rendererDispatcher.getFontRenderer();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntitySpecialRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */