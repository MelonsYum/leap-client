/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiYesNo
/*     */   extends GuiScreen
/*     */ {
/*     */   protected GuiYesNoCallback parentScreen;
/*     */   protected String messageLine1;
/*     */   private String messageLine2;
/*  17 */   private final List field_175298_s = Lists.newArrayList();
/*     */   
/*     */   protected String confirmButtonText;
/*     */   
/*     */   protected String cancelButtonText;
/*     */   
/*     */   protected int parentButtonClickedId;
/*     */   
/*     */   private int ticksUntilEnable;
/*     */   
/*     */   private static final String __OBFID = "CL_00000684";
/*     */   
/*     */   public GuiYesNo(GuiYesNoCallback p_i1082_1_, String p_i1082_2_, String p_i1082_3_, int p_i1082_4_) {
/*  30 */     this.parentScreen = p_i1082_1_;
/*  31 */     this.messageLine1 = p_i1082_2_;
/*  32 */     this.messageLine2 = p_i1082_3_;
/*  33 */     this.parentButtonClickedId = p_i1082_4_;
/*  34 */     this.confirmButtonText = I18n.format("gui.yes", new Object[0]);
/*  35 */     this.cancelButtonText = I18n.format("gui.no", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiYesNo(GuiYesNoCallback p_i1083_1_, String p_i1083_2_, String p_i1083_3_, String p_i1083_4_, String p_i1083_5_, int p_i1083_6_) {
/*  40 */     this.parentScreen = p_i1083_1_;
/*  41 */     this.messageLine1 = p_i1083_2_;
/*  42 */     this.messageLine2 = p_i1083_3_;
/*  43 */     this.confirmButtonText = p_i1083_4_;
/*  44 */     this.cancelButtonText = p_i1083_5_;
/*  45 */     this.parentButtonClickedId = p_i1083_6_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  53 */     this.buttonList.add(new GuiOptionButton(0, width / 2 - 155, height / 6 + 96, this.confirmButtonText));
/*  54 */     this.buttonList.add(new GuiOptionButton(1, width / 2 - 155 + 160, height / 6 + 96, this.cancelButtonText));
/*  55 */     this.field_175298_s.clear();
/*  56 */     this.field_175298_s.addAll(fontRendererObj.listFormattedStringToWidth(this.messageLine2, width - 50));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  61 */     this.parentScreen.confirmClicked((button.id == 0), this.parentButtonClickedId);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  69 */     drawDefaultBackground();
/*  70 */     drawCenteredString(fontRendererObj, this.messageLine1, (width / 2), 70.0F, 16777215);
/*  71 */     int var4 = 90;
/*     */     
/*  73 */     for (Iterator<String> var5 = this.field_175298_s.iterator(); var5.hasNext(); var4 += fontRendererObj.FONT_HEIGHT) {
/*     */       
/*  75 */       String var6 = var5.next();
/*  76 */       drawCenteredString(fontRendererObj, var6, (width / 2), var4, 16777215);
/*     */     } 
/*     */     
/*  79 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setButtonDelay(int p_146350_1_) {
/*  87 */     this.ticksUntilEnable = p_146350_1_;
/*     */ 
/*     */     
/*  90 */     for (Iterator<GuiButton> var2 = this.buttonList.iterator(); var2.hasNext(); var3.enabled = false)
/*     */     {
/*  92 */       GuiButton var3 = var2.next();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 101 */     super.updateScreen();
/*     */ 
/*     */     
/* 104 */     if (--this.ticksUntilEnable == 0)
/*     */     {
/* 106 */       for (Iterator<GuiButton> var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = true)
/*     */       {
/* 108 */         GuiButton var2 = var1.next();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiYesNo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */