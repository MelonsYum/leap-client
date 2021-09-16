/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerSaddle;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.EntityPig;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderPig extends RenderLiving {
/* 11 */   private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001019";
/*    */   
/*    */   public RenderPig(RenderManager p_i46149_1_, ModelBase p_i46149_2_, float p_i46149_3_) {
/* 16 */     super(p_i46149_1_, p_i46149_2_, p_i46149_3_);
/* 17 */     addLayer((LayerRenderer)new LayerSaddle(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180583_a(EntityPig p_180583_1_) {
/* 22 */     return pigTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 30 */     return func_180583_a((EntityPig)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderPig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */