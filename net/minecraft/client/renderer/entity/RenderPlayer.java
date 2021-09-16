/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelPlayer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerArrow;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerCape;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerDeadmau5Head;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderPlayer
/*     */   extends RendererLivingEntity
/*     */ {
/*     */   private boolean field_177140_a;
/*     */   private static final String __OBFID = "CL_00001020";
/*     */   
/*     */   public RenderPlayer(RenderManager p_i46102_1_) {
/*  31 */     this(p_i46102_1_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderPlayer(RenderManager p_i46103_1_, boolean p_i46103_2_) {
/*  36 */     super(p_i46103_1_, (ModelBase)new ModelPlayer(0.0F, p_i46103_2_), 0.5F);
/*  37 */     this.field_177140_a = p_i46103_2_;
/*  38 */     addLayer((LayerRenderer)new LayerBipedArmor(this));
/*  39 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/*  40 */     addLayer((LayerRenderer)new LayerArrow(this));
/*  41 */     addLayer((LayerRenderer)new LayerDeadmau5Head(this));
/*  42 */     addLayer((LayerRenderer)new LayerCape(this));
/*  43 */     addLayer((LayerRenderer)new LayerCustomHead((func_177136_g()).bipedHead));
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelPlayer func_177136_g() {
/*  48 */     return (ModelPlayer)super.getMainModel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180596_a(AbstractClientPlayer p_180596_1_, double p_180596_2_, double p_180596_4_, double p_180596_6_, float p_180596_8_, float p_180596_9_) {
/*  53 */     if (!p_180596_1_.func_175144_cb() || this.renderManager.livingPlayer == p_180596_1_) {
/*     */       
/*  55 */       double var10 = p_180596_4_;
/*     */       
/*  57 */       if (p_180596_1_.isSneaking() && !(p_180596_1_ instanceof net.minecraft.client.entity.EntityPlayerSP))
/*     */       {
/*  59 */         var10 = p_180596_4_ - 0.125D;
/*     */       }
/*     */       
/*  62 */       func_177137_d(p_180596_1_);
/*  63 */       super.doRender((EntityLivingBase)p_180596_1_, p_180596_2_, var10, p_180596_6_, p_180596_8_, p_180596_9_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177137_d(AbstractClientPlayer p_177137_1_) {
/*  69 */     ModelPlayer var2 = func_177136_g();
/*     */     
/*  71 */     if (p_177137_1_.func_175149_v()) {
/*     */       
/*  73 */       var2.func_178719_a(false);
/*  74 */       var2.bipedHead.showModel = true;
/*  75 */       var2.bipedHeadwear.showModel = true;
/*     */     }
/*     */     else {
/*     */       
/*  79 */       ItemStack var3 = p_177137_1_.inventory.getCurrentItem();
/*  80 */       var2.func_178719_a(true);
/*  81 */       var2.bipedHeadwear.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.HAT);
/*  82 */       var2.field_178730_v.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.JACKET);
/*  83 */       var2.field_178733_c.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.LEFT_PANTS_LEG);
/*  84 */       var2.field_178731_d.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.RIGHT_PANTS_LEG);
/*  85 */       var2.field_178734_a.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.LEFT_SLEEVE);
/*  86 */       var2.field_178732_b.showModel = p_177137_1_.func_175148_a(EnumPlayerModelParts.RIGHT_SLEEVE);
/*  87 */       var2.heldItemLeft = 0;
/*  88 */       var2.aimedBow = false;
/*  89 */       var2.isSneak = p_177137_1_.isSneaking();
/*     */       
/*  91 */       if (var3 == null) {
/*     */         
/*  93 */         var2.heldItemRight = 0;
/*     */       }
/*     */       else {
/*     */         
/*  97 */         var2.heldItemRight = 1;
/*     */         
/*  99 */         if (p_177137_1_.getItemInUseCount() > 0) {
/*     */           
/* 101 */           EnumAction var4 = var3.getItemUseAction();
/*     */           
/* 103 */           if (var4 == EnumAction.BLOCK) {
/*     */             
/* 105 */             var2.heldItemRight = 3;
/*     */           }
/* 107 */           else if (var4 == EnumAction.BOW) {
/*     */             
/* 109 */             var2.aimedBow = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_180594_a(AbstractClientPlayer p_180594_1_) {
/* 118 */     return p_180594_1_.getLocationSkin();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82422_c() {
/* 123 */     GlStateManager.translate(0.0F, 0.1875F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(AbstractClientPlayer p_77041_1_, float p_77041_2_) {
/* 132 */     float var3 = 0.9375F;
/* 133 */     GlStateManager.scale(var3, var3, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderOffsetLivingLabel(AbstractClientPlayer p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
/* 138 */     if (p_96449_10_ < 100.0D) {
/*     */       
/* 140 */       Scoreboard var12 = p_96449_1_.getWorldScoreboard();
/* 141 */       ScoreObjective var13 = var12.getObjectiveInDisplaySlot(2);
/*     */       
/* 143 */       if (var13 != null) {
/*     */         
/* 145 */         Score var14 = var12.getValueFromObjective(p_96449_1_.getName(), var13);
/* 146 */         renderLivingLabel((Entity)p_96449_1_, String.valueOf(var14.getScorePoints()) + " " + var13.getDisplayName(), p_96449_2_, p_96449_4_, p_96449_6_, 64);
/* 147 */         p_96449_4_ += ((getFontRendererFromRenderManager()).FONT_HEIGHT * 1.15F * p_96449_9_);
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     super.func_177069_a((Entity)p_96449_1_, p_96449_2_, p_96449_4_, p_96449_6_, p_96449_8_, p_96449_9_, p_96449_10_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177138_b(AbstractClientPlayer p_177138_1_) {
/* 156 */     float var2 = 1.0F;
/* 157 */     GlStateManager.color(var2, var2, var2);
/* 158 */     ModelPlayer var3 = func_177136_g();
/* 159 */     func_177137_d(p_177138_1_);
/* 160 */     var3.swingProgress = 0.0F;
/* 161 */     var3.isSneak = false;
/* 162 */     var3.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, (Entity)p_177138_1_);
/* 163 */     var3.func_178725_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177139_c(AbstractClientPlayer p_177139_1_) {
/* 168 */     float var2 = 1.0F;
/* 169 */     GlStateManager.color(var2, var2, var2);
/* 170 */     ModelPlayer var3 = func_177136_g();
/* 171 */     func_177137_d(p_177139_1_);
/* 172 */     var3.isSneak = false;
/* 173 */     var3.swingProgress = 0.0F;
/* 174 */     var3.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, (Entity)p_177139_1_);
/* 175 */     var3.func_178726_b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingAt(AbstractClientPlayer p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
/* 183 */     if (p_77039_1_.isEntityAlive() && p_77039_1_.isPlayerSleeping()) {
/*     */       
/* 185 */       super.renderLivingAt((EntityLivingBase)p_77039_1_, p_77039_2_ + p_77039_1_.field_71079_bU, p_77039_4_ + p_77039_1_.field_71082_cx, p_77039_6_ + p_77039_1_.field_71089_bV);
/*     */     }
/*     */     else {
/*     */       
/* 189 */       super.renderLivingAt((EntityLivingBase)p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180595_a(AbstractClientPlayer p_180595_1_, float p_180595_2_, float p_180595_3_, float p_180595_4_) {
/* 195 */     if (p_180595_1_.isEntityAlive() && p_180595_1_.isPlayerSleeping()) {
/*     */       
/* 197 */       GlStateManager.rotate(p_180595_1_.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
/* 198 */       GlStateManager.rotate(getDeathMaxRotation((EntityLivingBase)p_180595_1_), 0.0F, 0.0F, 1.0F);
/* 199 */       GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/* 203 */       super.rotateCorpse((EntityLivingBase)p_180595_1_, p_180595_2_, p_180595_3_, p_180595_4_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 213 */     preRenderCallback((AbstractClientPlayer)p_77041_1_, p_77041_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 218 */     func_180595_a((AbstractClientPlayer)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
/* 226 */     renderLivingAt((AbstractClientPlayer)p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
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
/* 237 */     func_180596_a((AbstractClientPlayer)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBase getMainModel() {
/* 242 */     return (ModelBase)func_177136_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 250 */     return func_180594_a((AbstractClientPlayer)p_110775_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_177069_a(Entity p_177069_1_, double p_177069_2_, double p_177069_4_, double p_177069_6_, String p_177069_8_, float p_177069_9_, double p_177069_10_) {
/* 255 */     renderOffsetLivingLabel((AbstractClientPlayer)p_177069_1_, p_177069_2_, p_177069_4_, p_177069_6_, p_177069_8_, p_177069_9_, p_177069_10_);
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
/* 266 */     func_180596_a((AbstractClientPlayer)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */