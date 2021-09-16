/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderBiped extends RenderLiving {
/* 13 */   private static final ResourceLocation field_177118_j = new ResourceLocation("textures/entity/steve.png");
/*    */   
/*    */   protected ModelBiped modelBipedMain;
/*    */   protected float field_77070_b;
/*    */   private static final String __OBFID = "CL_00001001";
/*    */   
/*    */   public RenderBiped(RenderManager p_i46168_1_, ModelBiped p_i46168_2_, float p_i46168_3_) {
/* 20 */     this(p_i46168_1_, p_i46168_2_, p_i46168_3_, 1.0F);
/* 21 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public RenderBiped(RenderManager p_i46169_1_, ModelBiped p_i46169_2_, float p_i46169_3_, float p_i46169_4_) {
/* 26 */     super(p_i46169_1_, (ModelBase)p_i46169_2_, p_i46169_3_);
/* 27 */     this.modelBipedMain = p_i46169_2_;
/* 28 */     this.field_77070_b = p_i46169_4_;
/* 29 */     addLayer((LayerRenderer)new LayerCustomHead(p_i46169_2_.bipedHead));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
/* 37 */     return field_177118_j;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_82422_c() {
/* 42 */     GlStateManager.translate(0.0F, 0.1875F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 50 */     return getEntityTexture((EntityLiving)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderBiped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */