/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderWolf;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntitySheep;
/*    */ import net.minecraft.entity.passive.EntityWolf;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.Config;
/*    */ import optifine.CustomColors;
/*    */ 
/*    */ public class LayerWolfCollar implements LayerRenderer {
/* 15 */   private static final ResourceLocation field_177147_a = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
/*    */   
/*    */   private final RenderWolf field_177146_b;
/*    */   private static final String __OBFID = "CL_00002405";
/*    */   
/*    */   public LayerWolfCollar(RenderWolf p_i46104_1_) {
/* 21 */     this.field_177146_b = p_i46104_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177145_a(EntityWolf p_177145_1_, float p_177145_2_, float p_177145_3_, float p_177145_4_, float p_177145_5_, float p_177145_6_, float p_177145_7_, float p_177145_8_) {
/* 26 */     if (p_177145_1_.isTamed() && !p_177145_1_.isInvisible()) {
/*    */       
/* 28 */       this.field_177146_b.bindTexture(field_177147_a);
/* 29 */       EnumDyeColor var9 = EnumDyeColor.func_176764_b(p_177145_1_.func_175546_cu().func_176765_a());
/* 30 */       float[] var10 = EntitySheep.func_175513_a(var9);
/*    */       
/* 32 */       if (Config.isCustomColors())
/*    */       {
/* 34 */         var10 = CustomColors.getWolfCollarColors(var9, var10);
/*    */       }
/*    */       
/* 37 */       GlStateManager.color(var10[0], var10[1], var10[2]);
/* 38 */       this.field_177146_b.getMainModel().render((Entity)p_177145_1_, p_177145_2_, p_177145_3_, p_177145_5_, p_177145_6_, p_177145_7_, p_177145_8_);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 49 */     func_177145_a((EntityWolf)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerWolfCollar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */