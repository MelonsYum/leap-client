/*     */ package net.minecraft.realms;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class RealmsSliderButton
/*     */   extends RealmsButton
/*     */ {
/*     */   public float value;
/*     */   public boolean sliding;
/*     */   private final float minValue;
/*     */   private final float maxValue;
/*     */   private int steps;
/*     */   private static final String __OBFID = "CL_00001834";
/*     */   
/*     */   public RealmsSliderButton(int p_i1056_1_, int p_i1056_2_, int p_i1056_3_, int p_i1056_4_, int p_i1056_5_, int p_i1056_6_) {
/*  18 */     this(p_i1056_1_, p_i1056_2_, p_i1056_3_, p_i1056_4_, p_i1056_6_, 0, 1.0F, p_i1056_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   public RealmsSliderButton(int p_i1057_1_, int p_i1057_2_, int p_i1057_3_, int p_i1057_4_, int p_i1057_5_, int p_i1057_6_, float p_i1057_7_, float p_i1057_8_) {
/*  23 */     super(p_i1057_1_, p_i1057_2_, p_i1057_3_, p_i1057_4_, 20, "");
/*  24 */     this.value = 1.0F;
/*  25 */     this.minValue = p_i1057_7_;
/*  26 */     this.maxValue = p_i1057_8_;
/*  27 */     this.value = toPct(p_i1057_6_);
/*  28 */     (getProxy()).displayString = getMessage();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  33 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public float toPct(float p_toPct_1_) {
/*  38 */     return MathHelper.clamp_float((clamp(p_toPct_1_) - this.minValue) / (this.maxValue - this.minValue), 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public float toValue(float p_toValue_1_) {
/*  43 */     return clamp(this.minValue + (this.maxValue - this.minValue) * MathHelper.clamp_float(p_toValue_1_, 0.0F, 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public float clamp(float p_clamp_1_) {
/*  48 */     p_clamp_1_ = clampSteps(p_clamp_1_);
/*  49 */     return MathHelper.clamp_float(p_clamp_1_, this.minValue, this.maxValue);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float clampSteps(float p_clampSteps_1_) {
/*  54 */     if (this.steps > 0)
/*     */     {
/*  56 */       p_clampSteps_1_ = (this.steps * Math.round(p_clampSteps_1_ / this.steps));
/*     */     }
/*     */     
/*  59 */     return p_clampSteps_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getYImage(boolean p_getYImage_1_) {
/*  64 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderBg(int p_renderBg_1_, int p_renderBg_2_) {
/*  69 */     if ((getProxy()).visible) {
/*     */       
/*  71 */       if (this.sliding) {
/*     */         
/*  73 */         this.value = (p_renderBg_1_ - (getProxy()).xPosition + 4) / (getProxy().getButtonWidth() - 8);
/*  74 */         this.value = MathHelper.clamp_float(this.value, 0.0F, 1.0F);
/*  75 */         float var3 = toValue(this.value);
/*  76 */         clicked(var3);
/*  77 */         this.value = toPct(var3);
/*  78 */         (getProxy()).displayString = getMessage();
/*     */       } 
/*     */       
/*  81 */       Minecraft.getMinecraft().getTextureManager().bindTexture(WIDGETS_LOCATION);
/*  82 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  83 */       blit((getProxy()).xPosition + (int)(this.value * (getProxy().getButtonWidth() - 8)), (getProxy()).yPosition, 0, 66, 4, 20);
/*  84 */       blit((getProxy()).xPosition + (int)(this.value * (getProxy().getButtonWidth() - 8)) + 4, (getProxy()).yPosition, 196, 66, 4, 20);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clicked(int p_clicked_1_, int p_clicked_2_) {
/*  90 */     this.value = (p_clicked_1_ - (getProxy()).xPosition + 4) / (getProxy().getButtonWidth() - 8);
/*  91 */     this.value = MathHelper.clamp_float(this.value, 0.0F, 1.0F);
/*  92 */     clicked(toValue(this.value));
/*  93 */     (getProxy()).displayString = getMessage();
/*  94 */     this.sliding = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clicked(float p_clicked_1_) {}
/*     */   
/*     */   public void released(int p_released_1_, int p_released_2_) {
/* 101 */     this.sliding = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsSliderButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */