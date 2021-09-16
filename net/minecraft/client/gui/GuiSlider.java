/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ 
/*     */ public class GuiSlider
/*     */   extends GuiButton {
/*   9 */   private float field_175227_p = 1.0F;
/*     */   
/*     */   public boolean field_175228_o;
/*     */   private String field_175226_q;
/*     */   private final float field_175225_r;
/*     */   private final float field_175224_s;
/*     */   private final GuiPageButtonList.GuiResponder field_175223_t;
/*     */   private FormatHelper field_175222_u;
/*     */   private static final String __OBFID = "CL_00001954";
/*     */   
/*     */   public GuiSlider(GuiPageButtonList.GuiResponder p_i45541_1_, int p_i45541_2_, int p_i45541_3_, int p_i45541_4_, String p_i45541_5_, float p_i45541_6_, float p_i45541_7_, float p_i45541_8_, FormatHelper p_i45541_9_) {
/*  20 */     super(p_i45541_2_, p_i45541_3_, p_i45541_4_, 150, 20, "");
/*  21 */     this.field_175226_q = p_i45541_5_;
/*  22 */     this.field_175225_r = p_i45541_6_;
/*  23 */     this.field_175224_s = p_i45541_7_;
/*  24 */     this.field_175227_p = (p_i45541_8_ - p_i45541_6_) / (p_i45541_7_ - p_i45541_6_);
/*  25 */     this.field_175222_u = p_i45541_9_;
/*  26 */     this.field_175223_t = p_i45541_1_;
/*  27 */     this.displayString = func_175221_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175220_c() {
/*  32 */     return this.field_175225_r + (this.field_175224_s - this.field_175225_r) * this.field_175227_p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175218_a(float p_175218_1_, boolean p_175218_2_) {
/*  37 */     this.field_175227_p = (p_175218_1_ - this.field_175225_r) / (this.field_175224_s - this.field_175225_r);
/*  38 */     this.displayString = func_175221_e();
/*     */     
/*  40 */     if (p_175218_2_)
/*     */     {
/*  42 */       this.field_175223_t.func_175320_a(this.id, func_175220_c());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175217_d() {
/*  48 */     return this.field_175227_p;
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_175221_e() {
/*  53 */     return (this.field_175222_u == null) ? (String.valueOf(I18n.format(this.field_175226_q, new Object[0])) + ": " + func_175220_c()) : this.field_175222_u.func_175318_a(this.id, I18n.format(this.field_175226_q, new Object[0]), func_175220_c());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getHoverState(boolean mouseOver) {
/*  62 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
/*  70 */     if (this.visible) {
/*     */       
/*  72 */       if (this.field_175228_o) {
/*     */         
/*  74 */         this.field_175227_p = (mouseX - this.xPosition + 4) / (this.width - 8);
/*     */         
/*  76 */         if (this.field_175227_p < 0.0F)
/*     */         {
/*  78 */           this.field_175227_p = 0.0F;
/*     */         }
/*     */         
/*  81 */         if (this.field_175227_p > 1.0F)
/*     */         {
/*  83 */           this.field_175227_p = 1.0F;
/*     */         }
/*     */         
/*  86 */         this.displayString = func_175221_e();
/*  87 */         this.field_175223_t.func_175320_a(this.id, func_175220_c());
/*     */       } 
/*     */       
/*  90 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  91 */       drawTexturedModalRect(this.xPosition + (int)(this.field_175227_p * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/*  92 */       drawTexturedModalRect(this.xPosition + (int)(this.field_175227_p * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175219_a(float p_175219_1_) {
/*  98 */     this.field_175227_p = p_175219_1_;
/*  99 */     this.displayString = func_175221_e();
/* 100 */     this.field_175223_t.func_175320_a(this.id, func_175220_c());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/* 109 */     if (super.mousePressed(mc, mouseX, mouseY)) {
/*     */       
/* 111 */       this.field_175227_p = (mouseX - this.xPosition + 4) / (this.width - 8);
/*     */       
/* 113 */       if (this.field_175227_p < 0.0F)
/*     */       {
/* 115 */         this.field_175227_p = 0.0F;
/*     */       }
/*     */       
/* 118 */       if (this.field_175227_p > 1.0F)
/*     */       {
/* 120 */         this.field_175227_p = 1.0F;
/*     */       }
/*     */       
/* 123 */       this.displayString = func_175221_e();
/* 124 */       this.field_175223_t.func_175320_a(this.id, func_175220_c());
/* 125 */       this.field_175228_o = true;
/* 126 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY) {
/* 139 */     this.field_175228_o = false;
/*     */   }
/*     */   
/*     */   public static interface FormatHelper {
/*     */     String func_175318_a(int param1Int, String param1String, float param1Float);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */