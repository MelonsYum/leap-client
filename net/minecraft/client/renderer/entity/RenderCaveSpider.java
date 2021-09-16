/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityCaveSpider;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderCaveSpider
/*    */   extends RenderSpider {
/* 12 */   private static final ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000982";
/*    */   
/*    */   public RenderCaveSpider(RenderManager p_i46189_1_) {
/* 17 */     super(p_i46189_1_);
/* 18 */     this.shadowSize *= 0.7F;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180585_a(EntityCaveSpider p_180585_1_, float p_180585_2_) {
/* 23 */     GlStateManager.scale(0.7F, 0.7F, 0.7F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180586_a(EntityCaveSpider p_180586_1_) {
/* 28 */     return caveSpiderTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntitySpider p_110775_1_) {
/* 36 */     return func_180586_a((EntityCaveSpider)p_110775_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 45 */     func_180585_a((EntityCaveSpider)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 53 */     return func_180586_a((EntityCaveSpider)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */