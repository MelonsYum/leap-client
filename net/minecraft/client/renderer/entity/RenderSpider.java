/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelSpider;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySpider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSpider extends RenderLiving {
/* 12 */   private static final ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001027";
/*    */   
/*    */   public RenderSpider(RenderManager p_i46139_1_) {
/* 17 */     super(p_i46139_1_, (ModelBase)new ModelSpider(), 1.0F);
/* 18 */     addLayer((LayerRenderer)new LayerSpiderEyes(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getDeathMaxRotation(EntitySpider p_77037_1_) {
/* 23 */     return 180.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntitySpider p_110775_1_) {
/* 31 */     return spiderTextures;
/*    */   }
/*    */ 
/*    */   
/*    */   protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
/* 36 */     return getDeathMaxRotation((EntitySpider)p_77037_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 44 */     return getEntityTexture((EntitySpider)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */