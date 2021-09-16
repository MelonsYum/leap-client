/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.model.ModelBox;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.RenderHelper;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.projectile.EntityArrow;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class LayerArrow
/*    */   implements LayerRenderer {
/*    */   private final RendererLivingEntity field_177168_a;
/*    */   private static final String __OBFID = "CL_00002426";
/*    */   
/*    */   public LayerArrow(RendererLivingEntity p_i46124_1_) {
/* 20 */     this.field_177168_a = p_i46124_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 25 */     int var9 = p_177141_1_.getArrowCountInEntity();
/*    */     
/* 27 */     if (var9 > 0) {
/*    */       
/* 29 */       EntityArrow var10 = new EntityArrow(p_177141_1_.worldObj, p_177141_1_.posX, p_177141_1_.posY, p_177141_1_.posZ);
/* 30 */       Random var11 = new Random(p_177141_1_.getEntityId());
/* 31 */       RenderHelper.disableStandardItemLighting();
/*    */       
/* 33 */       for (int var12 = 0; var12 < var9; var12++) {
/*    */         
/* 35 */         GlStateManager.pushMatrix();
/* 36 */         ModelRenderer var13 = this.field_177168_a.getMainModel().getRandomModelBox(var11);
/* 37 */         ModelBox var14 = var13.cubeList.get(var11.nextInt(var13.cubeList.size()));
/* 38 */         var13.postRender(0.0625F);
/* 39 */         float var15 = var11.nextFloat();
/* 40 */         float var16 = var11.nextFloat();
/* 41 */         float var17 = var11.nextFloat();
/* 42 */         float var18 = (var14.posX1 + (var14.posX2 - var14.posX1) * var15) / 16.0F;
/* 43 */         float var19 = (var14.posY1 + (var14.posY2 - var14.posY1) * var16) / 16.0F;
/* 44 */         float var20 = (var14.posZ1 + (var14.posZ2 - var14.posZ1) * var17) / 16.0F;
/* 45 */         GlStateManager.translate(var18, var19, var20);
/* 46 */         var15 = var15 * 2.0F - 1.0F;
/* 47 */         var16 = var16 * 2.0F - 1.0F;
/* 48 */         var17 = var17 * 2.0F - 1.0F;
/* 49 */         var15 *= -1.0F;
/* 50 */         var16 *= -1.0F;
/* 51 */         var17 *= -1.0F;
/* 52 */         float var21 = MathHelper.sqrt_float(var15 * var15 + var17 * var17);
/* 53 */         var10.prevRotationYaw = var10.rotationYaw = (float)(Math.atan2(var15, var17) * 180.0D / Math.PI);
/* 54 */         var10.prevRotationPitch = var10.rotationPitch = (float)(Math.atan2(var16, var21) * 180.0D / Math.PI);
/* 55 */         double var22 = 0.0D;
/* 56 */         double var24 = 0.0D;
/* 57 */         double var26 = 0.0D;
/* 58 */         this.field_177168_a.func_177068_d().renderEntityWithPosYaw((Entity)var10, var22, var24, var26, 0.0F, p_177141_4_);
/* 59 */         GlStateManager.popMatrix();
/*    */       } 
/*    */       
/* 62 */       RenderHelper.enableStandardItemLighting();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 68 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */