/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelCreeper;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerCreeperCharge;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityCreeper;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderCreeper extends RenderLiving {
/* 14 */   private static final ResourceLocation creeperTextures = new ResourceLocation("textures/entity/creeper/creeper.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000985";
/*    */   
/*    */   public RenderCreeper(RenderManager p_i46186_1_) {
/* 19 */     super(p_i46186_1_, (ModelBase)new ModelCreeper(), 0.5F);
/* 20 */     addLayer((LayerRenderer)new LayerCreeperCharge(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180570_a(EntityCreeper p_180570_1_, float p_180570_2_) {
/* 25 */     float var3 = p_180570_1_.getCreeperFlashIntensity(p_180570_2_);
/* 26 */     float var4 = 1.0F + MathHelper.sin(var3 * 100.0F) * var3 * 0.01F;
/* 27 */     var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
/* 28 */     var3 *= var3;
/* 29 */     var3 *= var3;
/* 30 */     float var5 = (1.0F + var3 * 0.4F) * var4;
/* 31 */     float var6 = (1.0F + var3 * 0.1F) / var4;
/* 32 */     GlStateManager.scale(var5, var6, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int func_180571_a(EntityCreeper p_180571_1_, float p_180571_2_, float p_180571_3_) {
/* 37 */     float var4 = p_180571_1_.getCreeperFlashIntensity(p_180571_3_);
/*    */     
/* 39 */     if ((int)(var4 * 10.0F) % 2 == 0)
/*    */     {
/* 41 */       return 0;
/*    */     }
/*    */ 
/*    */     
/* 45 */     int var5 = (int)(var4 * 0.2F * 255.0F);
/* 46 */     var5 = MathHelper.clamp_int(var5, 0, 255);
/* 47 */     return var5 << 24 | 0xFFFFFF;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityCreeper p_110775_1_) {
/* 56 */     return creeperTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 65 */     func_180570_a((EntityCreeper)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
/* 73 */     return func_180571_a((EntityCreeper)p_77030_1_, p_77030_2_, p_77030_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 81 */     return getEntityTexture((EntityCreeper)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */