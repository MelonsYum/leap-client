/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelEnderMite;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityEndermite;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderEndermite extends RenderLiving {
/* 11 */   private static final ResourceLocation field_177108_a = new ResourceLocation("textures/entity/endermite.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00002445";
/*    */   
/*    */   public RenderEndermite(RenderManager p_i46181_1_) {
/* 16 */     super(p_i46181_1_, (ModelBase)new ModelEnderMite(), 0.3F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_177107_a(EntityEndermite p_177107_1_) {
/* 21 */     return 180.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_177106_b(EntityEndermite p_177106_1_) {
/* 26 */     return field_177108_a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
/* 31 */     return func_177107_a((EntityEndermite)p_77037_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 39 */     return func_177106_b((EntityEndermite)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderEndermite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */