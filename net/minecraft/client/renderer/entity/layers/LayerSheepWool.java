/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelSheep1;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderSheep;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntitySheep;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.Config;
/*    */ import optifine.CustomColors;
/*    */ 
/*    */ public class LayerSheepWool implements LayerRenderer {
/* 15 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
/*    */   private final RenderSheep sheepRenderer;
/* 17 */   private final ModelSheep1 sheepModel = new ModelSheep1();
/*    */   
/*    */   private static final String __OBFID = "CL_00002413";
/*    */   
/*    */   public LayerSheepWool(RenderSheep p_i46112_1_) {
/* 22 */     this.sheepRenderer = p_i46112_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntitySheep p_177162_1_, float p_177162_2_, float p_177162_3_, float p_177162_4_, float p_177162_5_, float p_177162_6_, float p_177162_7_, float p_177162_8_) {
/* 27 */     if (!p_177162_1_.getSheared() && !p_177162_1_.isInvisible()) {
/*    */       
/* 29 */       this.sheepRenderer.bindTexture(TEXTURE);
/*    */       
/* 31 */       if (p_177162_1_.hasCustomName() && "jeb_".equals(p_177162_1_.getCustomNameTag())) {
/*    */         
/* 33 */         boolean var91 = true;
/* 34 */         int var10 = p_177162_1_.ticksExisted / 25 + p_177162_1_.getEntityId();
/* 35 */         int var11 = (EnumDyeColor.values()).length;
/* 36 */         int var12 = var10 % var11;
/* 37 */         int var13 = (var10 + 1) % var11;
/* 38 */         float var14 = ((p_177162_1_.ticksExisted % 25) + p_177162_4_) / 25.0F;
/* 39 */         float[] var15 = EntitySheep.func_175513_a(EnumDyeColor.func_176764_b(var12));
/* 40 */         float[] var16 = EntitySheep.func_175513_a(EnumDyeColor.func_176764_b(var13));
/*    */         
/* 42 */         if (Config.isCustomColors()) {
/*    */           
/* 44 */           var15 = CustomColors.getSheepColors(EnumDyeColor.func_176764_b(var12), var15);
/* 45 */           var16 = CustomColors.getSheepColors(EnumDyeColor.func_176764_b(var13), var16);
/*    */         } 
/*    */         
/* 48 */         GlStateManager.color(var15[0] * (1.0F - var14) + var16[0] * var14, var15[1] * (1.0F - var14) + var16[1] * var14, var15[2] * (1.0F - var14) + var16[2] * var14);
/*    */       }
/*    */       else {
/*    */         
/* 52 */         float[] var9 = EntitySheep.func_175513_a(p_177162_1_.func_175509_cj());
/*    */         
/* 54 */         if (Config.isCustomColors())
/*    */         {
/* 56 */           var9 = CustomColors.getSheepColors(p_177162_1_.func_175509_cj(), var9);
/*    */         }
/*    */         
/* 59 */         GlStateManager.color(var9[0], var9[1], var9[2]);
/*    */       } 
/*    */       
/* 62 */       this.sheepModel.setModelAttributes(this.sheepRenderer.getMainModel());
/* 63 */       this.sheepModel.setLivingAnimations((EntityLivingBase)p_177162_1_, p_177162_2_, p_177162_3_, p_177162_4_);
/* 64 */       this.sheepModel.render((Entity)p_177162_1_, p_177162_2_, p_177162_3_, p_177162_5_, p_177162_6_, p_177162_7_, p_177162_8_);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 75 */     doRenderLayer((EntitySheep)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerSheepWool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */