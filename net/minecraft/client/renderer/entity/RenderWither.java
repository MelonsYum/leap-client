/*     */ package net.minecraft.client.renderer.entity;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelWither;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.entity.boss.EntityWither;
/*     */ import net.minecraft.entity.boss.IBossDisplayData;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderWither extends RenderLiving {
/*  15 */   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
/*  16 */   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00001034";
/*     */   
/*     */   public RenderWither(RenderManager p_i46130_1_) {
/*  21 */     super(p_i46130_1_, (ModelBase)new ModelWither(0.0F), 1.0F);
/*  22 */     addLayer((LayerRenderer)new LayerWitherAura(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180591_a(EntityWither p_180591_1_, double p_180591_2_, double p_180591_4_, double p_180591_6_, float p_180591_8_, float p_180591_9_) {
/*  27 */     BossStatus.setBossStatus((IBossDisplayData)p_180591_1_, true);
/*  28 */     super.doRender((EntityLiving)p_180591_1_, p_180591_2_, p_180591_4_, p_180591_6_, p_180591_8_, p_180591_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityWither p_110775_1_) {
/*  36 */     int var2 = p_110775_1_.getInvulTime();
/*  37 */     return (var2 > 0 && (var2 > 80 || var2 / 5 % 2 != 1)) ? invulnerableWitherTextures : witherTextures;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180592_a(EntityWither p_180592_1_, float p_180592_2_) {
/*  42 */     float var3 = 2.0F;
/*  43 */     int var4 = p_180592_1_.getInvulTime();
/*     */     
/*  45 */     if (var4 > 0)
/*     */     {
/*  47 */       var3 -= (var4 - p_180592_2_) / 220.0F * 0.5F;
/*     */     }
/*     */     
/*  50 */     GlStateManager.scale(var3, var3, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  61 */     func_180591_a((EntityWither)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/*  70 */     func_180592_a((EntityWither)p_77041_1_, p_77041_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  81 */     func_180591_a((EntityWither)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/*  89 */     return getEntityTexture((EntityWither)p_110775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 100 */     func_180591_a((EntityWither)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */