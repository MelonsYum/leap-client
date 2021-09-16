/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelZombie;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.monster.EntityPigZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderPigZombie extends RenderBiped {
/* 13 */   private static final ResourceLocation field_177120_j = new ResourceLocation("textures/entity/zombie_pigman.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00002434";
/*    */   
/*    */   public RenderPigZombie(RenderManager p_i46148_1_) {
/* 18 */     super(p_i46148_1_, (ModelBiped)new ModelZombie(), 0.5F, 1.0F);
/* 19 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/* 20 */     addLayer((LayerRenderer)new LayerBipedArmor(this)
/*    */         {
/*    */           private static final String __OBFID = "CL_00002433";
/*    */           
/*    */           protected void func_177177_a() {
/* 25 */             this.field_177189_c = (ModelBase)new ModelZombie(0.5F, true);
/* 26 */             this.field_177186_d = (ModelBase)new ModelZombie(1.0F, true);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_177119_a(EntityPigZombie p_177119_1_) {
/* 33 */     return field_177120_j;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
/* 41 */     return func_177119_a((EntityPigZombie)p_110775_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 49 */     return func_177119_a((EntityPigZombie)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */