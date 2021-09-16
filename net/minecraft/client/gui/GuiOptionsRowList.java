/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ 
/*     */ public class GuiOptionsRowList
/*     */   extends GuiListExtended {
/*  10 */   private final List field_148184_k = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00000677";
/*     */   
/*     */   public GuiOptionsRowList(Minecraft mcIn, int p_i45015_2_, int p_i45015_3_, int p_i45015_4_, int p_i45015_5_, int p_i45015_6_, GameSettings.Options... p_i45015_7_) {
/*  15 */     super(mcIn, p_i45015_2_, p_i45015_3_, p_i45015_4_, p_i45015_5_, p_i45015_6_);
/*  16 */     this.field_148163_i = false;
/*     */     
/*  18 */     for (int var8 = 0; var8 < p_i45015_7_.length; var8 += 2) {
/*     */       
/*  20 */       GameSettings.Options var9 = p_i45015_7_[var8];
/*  21 */       GameSettings.Options var10 = (var8 < p_i45015_7_.length - 1) ? p_i45015_7_[var8 + 1] : null;
/*  22 */       GuiButton var11 = func_148182_a(mcIn, p_i45015_2_ / 2 - 155, 0, var9);
/*  23 */       GuiButton var12 = func_148182_a(mcIn, p_i45015_2_ / 2 - 155 + 160, 0, var10);
/*  24 */       this.field_148184_k.add(new Row(var11, var12));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiButton func_148182_a(Minecraft mcIn, int p_148182_2_, int p_148182_3_, GameSettings.Options p_148182_4_) {
/*  30 */     if (p_148182_4_ == null)
/*     */     {
/*  32 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  36 */     int var5 = p_148182_4_.returnEnumOrdinal();
/*  37 */     return p_148182_4_.getEnumFloat() ? new GuiOptionSlider(var5, p_148182_2_, p_148182_3_, p_148182_4_) : new GuiOptionButton(var5, p_148182_2_, p_148182_3_, p_148182_4_, mcIn.gameSettings.getKeyBinding(p_148182_4_));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Row func_180792_c(int p_180792_1_) {
/*  43 */     return this.field_148184_k.get(p_180792_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  48 */     return this.field_148184_k.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListWidth() {
/*  56 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/*  61 */     return super.getScrollBarX() + 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiListExtended.IGuiListEntry getListEntry(int p_148180_1_) {
/*  69 */     return func_180792_c(p_148180_1_);
/*     */   }
/*     */   
/*     */   public static class Row
/*     */     implements GuiListExtended.IGuiListEntry {
/*  74 */     private final Minecraft field_148325_a = Minecraft.getMinecraft();
/*     */     
/*     */     private final GuiButton field_148323_b;
/*     */     private final GuiButton field_148324_c;
/*     */     private static final String __OBFID = "CL_00000678";
/*     */     
/*     */     public Row(GuiButton p_i45014_1_, GuiButton p_i45014_2_) {
/*  81 */       this.field_148323_b = p_i45014_1_;
/*  82 */       this.field_148324_c = p_i45014_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/*  87 */       if (this.field_148323_b != null) {
/*     */         
/*  89 */         this.field_148323_b.yPosition = y;
/*  90 */         this.field_148323_b.drawButton(this.field_148325_a, mouseX, mouseY);
/*     */       } 
/*     */       
/*  93 */       if (this.field_148324_c != null) {
/*     */         
/*  95 */         this.field_148324_c.yPosition = y;
/*  96 */         this.field_148324_c.drawButton(this.field_148325_a, mouseX, mouseY);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/* 102 */       if (this.field_148323_b.mousePressed(this.field_148325_a, p_148278_2_, p_148278_3_)) {
/*     */         
/* 104 */         if (this.field_148323_b instanceof GuiOptionButton) {
/*     */           
/* 106 */           this.field_148325_a.gameSettings.setOptionValue(((GuiOptionButton)this.field_148323_b).returnEnumOptions(), 1);
/* 107 */           this.field_148323_b.displayString = this.field_148325_a.gameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(this.field_148323_b.id));
/*     */         } 
/*     */         
/* 110 */         return true;
/*     */       } 
/* 112 */       if (this.field_148324_c != null && this.field_148324_c.mousePressed(this.field_148325_a, p_148278_2_, p_148278_3_)) {
/*     */         
/* 114 */         if (this.field_148324_c instanceof GuiOptionButton) {
/*     */           
/* 116 */           this.field_148325_a.gameSettings.setOptionValue(((GuiOptionButton)this.field_148324_c).returnEnumOptions(), 1);
/* 117 */           this.field_148324_c.displayString = this.field_148325_a.gameSettings.getKeyBinding(GameSettings.Options.getEnumOptions(this.field_148324_c.id));
/*     */         } 
/*     */         
/* 120 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 124 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
/* 130 */       if (this.field_148323_b != null)
/*     */       {
/* 132 */         this.field_148323_b.mouseReleased(x, y);
/*     */       }
/*     */       
/* 135 */       if (this.field_148324_c != null)
/*     */       {
/* 137 */         this.field_148324_c.mouseReleased(x, y);
/*     */       }
/*     */     }
/*     */     
/*     */     public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiOptionsRowList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */