/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.realms.RealmsButton;
/*     */ 
/*     */ public class GuiButtonRealmsProxy
/*     */   extends GuiButton
/*     */ {
/*     */   private RealmsButton field_154318_o;
/*     */   private static final String __OBFID = "CL_00001848";
/*     */   
/*     */   public GuiButtonRealmsProxy(RealmsButton p_i46321_1_, int p_i46321_2_, int p_i46321_3_, int p_i46321_4_, String p_i46321_5_) {
/*  13 */     super(p_i46321_2_, p_i46321_3_, p_i46321_4_, p_i46321_5_);
/*  14 */     this.field_154318_o = p_i46321_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiButtonRealmsProxy(RealmsButton p_i1090_1_, int p_i1090_2_, int p_i1090_3_, int p_i1090_4_, String p_i1090_5_, int p_i1090_6_, int p_i1090_7_) {
/*  19 */     super(p_i1090_2_, p_i1090_3_, p_i1090_4_, p_i1090_6_, p_i1090_7_, p_i1090_5_);
/*  20 */     this.field_154318_o = p_i1090_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/*  25 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getEnabled() {
/*  30 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean p_154313_1_) {
/*  35 */     this.enabled = p_154313_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setText(String p_154311_1_) {
/*  40 */     this.displayString = p_154311_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getButtonWidth() {
/*  45 */     return super.getButtonWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPositionY() {
/*  50 */     return this.yPosition;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/*  59 */     if (super.mousePressed(mc, mouseX, mouseY))
/*     */     {
/*  61 */       this.field_154318_o.clicked(mouseX, mouseY);
/*     */     }
/*     */     
/*  64 */     return super.mousePressed(mc, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY) {
/*  72 */     this.field_154318_o.released(mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
/*  80 */     this.field_154318_o.renderBg(mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   public RealmsButton getRealmsButton() {
/*  85 */     return this.field_154318_o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHoverState(boolean mouseOver) {
/*  94 */     return this.field_154318_o.getYImage(mouseOver);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_154312_c(boolean p_154312_1_) {
/*  99 */     return super.getHoverState(p_154312_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_175232_g() {
/* 104 */     return this.height;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiButtonRealmsProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */