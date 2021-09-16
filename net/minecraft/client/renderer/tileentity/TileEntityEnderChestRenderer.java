/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelChest;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.tileentity.TileEntityEnderChest;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class TileEntityEnderChestRenderer
/*    */   extends TileEntitySpecialRenderer {
/* 11 */   private static final ResourceLocation field_147520_b = new ResourceLocation("textures/entity/chest/ender.png");
/* 12 */   private ModelChest field_147521_c = new ModelChest();
/*    */   
/*    */   private static final String __OBFID = "CL_00000967";
/*    */   
/*    */   public void func_180540_a(TileEntityEnderChest p_180540_1_, double p_180540_2_, double p_180540_4_, double p_180540_6_, float p_180540_8_, int p_180540_9_) {
/* 17 */     int var10 = 0;
/*    */     
/* 19 */     if (p_180540_1_.hasWorldObj())
/*    */     {
/* 21 */       var10 = p_180540_1_.getBlockMetadata();
/*    */     }
/*    */     
/* 24 */     if (p_180540_9_ >= 0) {
/*    */       
/* 26 */       bindTexture(DESTROY_STAGES[p_180540_9_]);
/* 27 */       GlStateManager.matrixMode(5890);
/* 28 */       GlStateManager.pushMatrix();
/* 29 */       GlStateManager.scale(4.0F, 4.0F, 1.0F);
/* 30 */       GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
/* 31 */       GlStateManager.matrixMode(5888);
/*    */     }
/*    */     else {
/*    */       
/* 35 */       bindTexture(field_147520_b);
/*    */     } 
/*    */     
/* 38 */     GlStateManager.pushMatrix();
/* 39 */     GlStateManager.enableRescaleNormal();
/* 40 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     GlStateManager.translate((float)p_180540_2_, (float)p_180540_4_ + 1.0F, (float)p_180540_6_ + 1.0F);
/* 42 */     GlStateManager.scale(1.0F, -1.0F, -1.0F);
/* 43 */     GlStateManager.translate(0.5F, 0.5F, 0.5F);
/* 44 */     short var11 = 0;
/*    */     
/* 46 */     if (var10 == 2)
/*    */     {
/* 48 */       var11 = 180;
/*    */     }
/*    */     
/* 51 */     if (var10 == 3)
/*    */     {
/* 53 */       var11 = 0;
/*    */     }
/*    */     
/* 56 */     if (var10 == 4)
/*    */     {
/* 58 */       var11 = 90;
/*    */     }
/*    */     
/* 61 */     if (var10 == 5)
/*    */     {
/* 63 */       var11 = -90;
/*    */     }
/*    */     
/* 66 */     GlStateManager.rotate(var11, 0.0F, 1.0F, 0.0F);
/* 67 */     GlStateManager.translate(-0.5F, -0.5F, -0.5F);
/* 68 */     float var12 = p_180540_1_.prevLidAngle + (p_180540_1_.field_145972_a - p_180540_1_.prevLidAngle) * p_180540_8_;
/* 69 */     var12 = 1.0F - var12;
/* 70 */     var12 = 1.0F - var12 * var12 * var12;
/* 71 */     this.field_147521_c.chestLid.rotateAngleX = -(var12 * 3.1415927F / 2.0F);
/* 72 */     this.field_147521_c.renderAll();
/* 73 */     GlStateManager.disableRescaleNormal();
/* 74 */     GlStateManager.popMatrix();
/* 75 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */     
/* 77 */     if (p_180540_9_ >= 0) {
/*    */       
/* 79 */       GlStateManager.matrixMode(5890);
/* 80 */       GlStateManager.popMatrix();
/* 81 */       GlStateManager.matrixMode(5888);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 87 */     func_180540_a((TileEntityEnderChest)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityEnderChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */