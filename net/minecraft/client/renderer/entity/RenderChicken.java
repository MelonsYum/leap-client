/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityChicken;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderChicken
/*    */   extends RenderLiving {
/* 12 */   private static final ResourceLocation chickenTextures = new ResourceLocation("textures/entity/chicken.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000983";
/*    */   
/*    */   public RenderChicken(RenderManager p_i46188_1_, ModelBase p_i46188_2_, float p_i46188_3_) {
/* 17 */     super(p_i46188_1_, p_i46188_2_, p_i46188_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180568_a(EntityChicken p_180568_1_) {
/* 22 */     return chickenTextures;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_180569_a(EntityChicken p_180569_1_, float p_180569_2_) {
/* 27 */     float var3 = p_180569_1_.field_70888_h + (p_180569_1_.field_70886_e - p_180569_1_.field_70888_h) * p_180569_2_;
/* 28 */     float var4 = p_180569_1_.field_70884_g + (p_180569_1_.destPos - p_180569_1_.field_70884_g) * p_180569_2_;
/* 29 */     return (MathHelper.sin(var3) + 1.0F) * var4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
/* 37 */     return func_180569_a((EntityChicken)p_77044_1_, p_77044_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 45 */     return func_180568_a((EntityChicken)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */