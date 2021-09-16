/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelZombie;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityGiantZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderGiantZombie extends RenderLiving {
/* 15 */   private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
/*    */   
/*    */   private float scale;
/*    */   
/*    */   private static final String __OBFID = "CL_00000998";
/*    */ 
/*    */   
/*    */   public RenderGiantZombie(RenderManager p_i46173_1_, ModelBase p_i46173_2_, float p_i46173_3_, float p_i46173_4_) {
/* 23 */     super(p_i46173_1_, p_i46173_2_, p_i46173_3_ * p_i46173_4_);
/* 24 */     this.scale = p_i46173_4_;
/* 25 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/* 26 */     addLayer((LayerRenderer)new LayerBipedArmor(this)
/*    */         {
/*    */           private static final String __OBFID = "CL_00002444";
/*    */           
/*    */           protected void func_177177_a() {
/* 31 */             this.field_177189_c = (ModelBase)new ModelZombie(0.5F, true);
/* 32 */             this.field_177186_d = (ModelBase)new ModelZombie(1.0F, true);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_82422_c() {
/* 39 */     GlStateManager.translate(0.0F, 0.1875F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityGiantZombie p_77041_1_, float p_77041_2_) {
/* 48 */     GlStateManager.scale(this.scale, this.scale, this.scale);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityGiantZombie p_110775_1_) {
/* 56 */     return zombieTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 65 */     preRenderCallback((EntityGiantZombie)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 73 */     return getEntityTexture((EntityGiantZombie)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderGiantZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */