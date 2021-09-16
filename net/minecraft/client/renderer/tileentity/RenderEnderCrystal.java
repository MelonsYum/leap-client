/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelEnderCrystal;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityEnderCrystal;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderEnderCrystal
/*    */   extends Render {
/* 15 */   private static final ResourceLocation enderCrystalTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
/* 16 */   private ModelBase field_76995_b = (ModelBase)new ModelEnderCrystal(0.0F, true);
/*    */   
/*    */   private static final String __OBFID = "CL_00000987";
/*    */   
/*    */   public RenderEnderCrystal(RenderManager p_i46184_1_) {
/* 21 */     super(p_i46184_1_);
/* 22 */     this.shadowSize = 0.5F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityEnderCrystal p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 33 */     float var10 = p_76986_1_.innerRotation + p_76986_9_;
/* 34 */     GlStateManager.pushMatrix();
/* 35 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
/* 36 */     bindTexture(enderCrystalTextures);
/* 37 */     float var11 = MathHelper.sin(var10 * 0.2F) / 2.0F + 0.5F;
/* 38 */     var11 += var11 * var11;
/* 39 */     this.field_76995_b.render((Entity)p_76986_1_, 0.0F, var10 * 3.0F, var11 * 0.2F, 0.0F, 0.0F, 0.0625F);
/* 40 */     GlStateManager.popMatrix();
/* 41 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180554_a(EntityEnderCrystal p_180554_1_) {
/* 46 */     return enderCrystalTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 54 */     return func_180554_a((EntityEnderCrystal)p_110775_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 65 */     doRender((EntityEnderCrystal)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\RenderEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */