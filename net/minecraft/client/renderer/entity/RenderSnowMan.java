/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelSnowMan;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.monster.EntitySnowman;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSnowMan extends RenderLiving {
/* 12 */   private static final ResourceLocation snowManTextures = new ResourceLocation("textures/entity/snowman.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001025";
/*    */   
/*    */   public RenderSnowMan(RenderManager p_i46140_1_) {
/* 17 */     super(p_i46140_1_, (ModelBase)new ModelSnowMan(), 0.5F);
/* 18 */     addLayer((LayerRenderer)new LayerSnowmanHead(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180587_a(EntitySnowman p_180587_1_) {
/* 23 */     return snowManTextures;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelSnowMan func_177123_g() {
/* 28 */     return (ModelSnowMan)super.getMainModel();
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelBase getMainModel() {
/* 33 */     return (ModelBase)func_177123_g();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 41 */     return func_180587_a((EntitySnowman)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSnowMan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */