/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.realms.RealmsClickableScrolledSelectionList;
/*     */ import net.minecraft.realms.Tezzelator;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class GuiClickableScrolledSelectionListProxy
/*     */   extends GuiSlot
/*     */ {
/*     */   private final RealmsClickableScrolledSelectionList field_178046_u;
/*     */   private static final String __OBFID = "CL_00001939";
/*     */   
/*     */   public GuiClickableScrolledSelectionListProxy(RealmsClickableScrolledSelectionList p_i45526_1_, int p_i45526_2_, int p_i45526_3_, int p_i45526_4_, int p_i45526_5_, int p_i45526_6_) {
/*  15 */     super(Minecraft.getMinecraft(), p_i45526_2_, p_i45526_3_, p_i45526_4_, p_i45526_5_, p_i45526_6_);
/*  16 */     this.field_178046_u = p_i45526_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  21 */     return this.field_178046_u.getItemCount();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/*  29 */     this.field_178046_u.selectItem(slotIndex, isDoubleClick, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int slotIndex) {
/*  37 */     return this.field_178046_u.isSelectedItem(slotIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawBackground() {
/*  42 */     this.field_178046_u.renderBackground();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/*  47 */     this.field_178046_u.renderItem(p_180791_1_, p_180791_2_, p_180791_3_, p_180791_4_, p_180791_5_, p_180791_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178044_e() {
/*  52 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178042_f() {
/*  57 */     return this.mouseY;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178045_g() {
/*  62 */     return this.mouseX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getContentHeight() {
/*  70 */     return this.field_178046_u.getMaxPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/*  75 */     return this.field_178046_u.getScrollbarPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178039_p() {
/*  80 */     super.func_178039_p();
/*     */     
/*  82 */     if (this.scrollMultiplier > 0.0F && Mouse.getEventButtonState())
/*     */     {
/*  84 */       this.field_178046_u.customMouseEvent(this.top, this.bottom, this.headerPadding, this.amountScrolled, this.slotHeight);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178043_a(int p_178043_1_, int p_178043_2_, int p_178043_3_, Tezzelator p_178043_4_) {
/*  90 */     this.field_178046_u.renderSelected(p_178043_1_, p_178043_2_, p_178043_3_, p_178043_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawSelectionBox(int p_148120_1_, int p_148120_2_, int p_148120_3_, int p_148120_4_) {
/*  98 */     int var5 = getSize();
/*     */     
/* 100 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 102 */       int var7 = p_148120_2_ + var6 * this.slotHeight + this.headerPadding;
/* 103 */       int var8 = this.slotHeight - 4;
/*     */       
/* 105 */       if (var7 > this.bottom || var7 + var8 < this.top)
/*     */       {
/* 107 */         func_178040_a(var6, p_148120_1_, var7);
/*     */       }
/*     */       
/* 110 */       if (this.showSelectionBox && isSelected(var6))
/*     */       {
/* 112 */         func_178043_a(this.width, var7, var8, Tezzelator.instance);
/*     */       }
/*     */       
/* 115 */       drawSlot(var6, p_148120_1_, var7, var8, p_148120_3_, p_148120_4_);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiClickableScrolledSelectionListProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */