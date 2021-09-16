/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBlaze;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.monster.EntityBlaze;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderBlaze extends RenderLiving {
/* 10 */   private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000980";
/*    */   
/*    */   public RenderBlaze(RenderManager p_i46191_1_) {
/* 15 */     super(p_i46191_1_, (ModelBase)new ModelBlaze(), 0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityBlaze p_110775_1_) {
/* 23 */     return blazeTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 31 */     return getEntityTexture((EntityBlaze)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */