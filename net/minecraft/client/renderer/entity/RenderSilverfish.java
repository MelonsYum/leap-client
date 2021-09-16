/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelSilverfish;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySilverfish;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSilverfish extends RenderLiving {
/* 11 */   private static final ResourceLocation silverfishTextures = new ResourceLocation("textures/entity/silverfish.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001022";
/*    */   
/*    */   public RenderSilverfish(RenderManager p_i46144_1_) {
/* 16 */     super(p_i46144_1_, (ModelBase)new ModelSilverfish(), 0.3F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float func_180584_a(EntitySilverfish p_180584_1_) {
/* 21 */     return 180.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntitySilverfish p_110775_1_) {
/* 29 */     return silverfishTextures;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
/* 34 */     return func_180584_a((EntitySilverfish)p_77037_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 42 */     return getEntityTexture((EntitySilverfish)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */