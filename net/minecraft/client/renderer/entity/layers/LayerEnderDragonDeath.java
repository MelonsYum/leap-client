/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.EntityDragon;
/*    */ 
/*    */ public class LayerEnderDragonDeath
/*    */   implements LayerRenderer
/*    */ {
/*    */   private static final String __OBFID = "CL_00002420";
/*    */   
/*    */   public void func_177213_a(EntityDragon p_177213_1_, float p_177213_2_, float p_177213_3_, float p_177213_4_, float p_177213_5_, float p_177213_6_, float p_177213_7_, float p_177213_8_) {
/* 17 */     if (p_177213_1_.deathTicks > 0) {
/*    */       
/* 19 */       Tessellator var9 = Tessellator.getInstance();
/* 20 */       WorldRenderer var10 = var9.getWorldRenderer();
/* 21 */       RenderHelper.disableStandardItemLighting();
/* 22 */       float var11 = (p_177213_1_.deathTicks + p_177213_4_) / 200.0F;
/* 23 */       float var12 = 0.0F;
/*    */       
/* 25 */       if (var11 > 0.8F)
/*    */       {
/* 27 */         var12 = (var11 - 0.8F) / 0.2F;
/*    */       }
/*    */       
/* 30 */       Random var13 = new Random(432L);
/* 31 */       GlStateManager.func_179090_x();
/* 32 */       GlStateManager.shadeModel(7425);
/* 33 */       GlStateManager.enableBlend();
/* 34 */       GlStateManager.blendFunc(770, 1);
/* 35 */       GlStateManager.disableAlpha();
/* 36 */       GlStateManager.enableCull();
/* 37 */       GlStateManager.depthMask(false);
/* 38 */       GlStateManager.pushMatrix();
/* 39 */       GlStateManager.translate(0.0F, -1.0F, -2.0F);
/*    */       
/* 41 */       for (int var14 = 0; var14 < (var11 + var11 * var11) / 2.0F * 60.0F; var14++) {
/*    */         
/* 43 */         GlStateManager.rotate(var13.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/* 44 */         GlStateManager.rotate(var13.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/* 45 */         GlStateManager.rotate(var13.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
/* 46 */         GlStateManager.rotate(var13.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
/* 47 */         GlStateManager.rotate(var13.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
/* 48 */         GlStateManager.rotate(var13.nextFloat() * 360.0F + var11 * 90.0F, 0.0F, 0.0F, 1.0F);
/* 49 */         var10.startDrawing(6);
/* 50 */         float var15 = var13.nextFloat() * 20.0F + 5.0F + var12 * 10.0F;
/* 51 */         float var16 = var13.nextFloat() * 2.0F + 1.0F + var12 * 2.0F;
/* 52 */         var10.func_178974_a(16777215, (int)(255.0F * (1.0F - var12)));
/* 53 */         var10.addVertex(0.0D, 0.0D, 0.0D);
/* 54 */         var10.func_178974_a(16711935, 0);
/* 55 */         var10.addVertex(-0.866D * var16, var15, (-0.5F * var16));
/* 56 */         var10.addVertex(0.866D * var16, var15, (-0.5F * var16));
/* 57 */         var10.addVertex(0.0D, var15, (1.0F * var16));
/* 58 */         var10.addVertex(-0.866D * var16, var15, (-0.5F * var16));
/* 59 */         var9.draw();
/*    */       } 
/*    */       
/* 62 */       GlStateManager.popMatrix();
/* 63 */       GlStateManager.depthMask(true);
/* 64 */       GlStateManager.disableCull();
/* 65 */       GlStateManager.disableBlend();
/* 66 */       GlStateManager.shadeModel(7424);
/* 67 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 68 */       GlStateManager.func_179098_w();
/* 69 */       GlStateManager.enableAlpha();
/* 70 */       RenderHelper.enableStandardItemLighting();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 76 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 81 */     func_177213_a((EntityDragon)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerEnderDragonDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */