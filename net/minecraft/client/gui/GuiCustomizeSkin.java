/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*     */ 
/*     */ public class GuiCustomizeSkin
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen field_175361_a;
/*     */   private String field_175360_f;
/*     */   private static final String __OBFID = "CL_00001932";
/*     */   
/*     */   public GuiCustomizeSkin(GuiScreen p_i45516_1_) {
/*  15 */     this.field_175361_a = p_i45516_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  23 */     int var1 = 0;
/*  24 */     this.field_175360_f = I18n.format("options.skinCustomisation.title", new Object[0]);
/*  25 */     EnumPlayerModelParts[] var2 = EnumPlayerModelParts.values();
/*  26 */     int var3 = var2.length;
/*     */     
/*  28 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  30 */       EnumPlayerModelParts var5 = var2[var4];
/*  31 */       this.buttonList.add(new ButtonPart(var5.func_179328_b(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), 150, 20, var5, null));
/*  32 */       var1++;
/*     */     } 
/*     */     
/*  35 */     if (var1 % 2 == 1)
/*     */     {
/*  37 */       var1++;
/*     */     }
/*     */     
/*  40 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 24 * (var1 >> 1), I18n.format("gui.done", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  45 */     if (button.enabled)
/*     */     {
/*  47 */       if (button.id == 200) {
/*     */         
/*  49 */         this.mc.gameSettings.saveOptions();
/*  50 */         this.mc.displayGuiScreen(this.field_175361_a);
/*     */       }
/*  52 */       else if (button instanceof ButtonPart) {
/*     */         
/*  54 */         EnumPlayerModelParts var2 = ((ButtonPart)button).field_175234_p;
/*  55 */         this.mc.gameSettings.func_178877_a(var2);
/*  56 */         button.displayString = func_175358_a(var2);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  66 */     drawDefaultBackground();
/*  67 */     drawCenteredString(fontRendererObj, this.field_175360_f, (width / 2), 20.0F, 16777215);
/*  68 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String func_175358_a(EnumPlayerModelParts p_175358_1_) {
/*     */     String var2;
/*  75 */     if (this.mc.gameSettings.func_178876_d().contains(p_175358_1_)) {
/*     */       
/*  77 */       var2 = I18n.format("options.on", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/*  81 */       var2 = I18n.format("options.off", new Object[0]);
/*     */     } 
/*     */     
/*  84 */     return String.valueOf(p_175358_1_.func_179326_d().getFormattedText()) + ": " + var2;
/*     */   }
/*     */   
/*     */   class ButtonPart
/*     */     extends GuiButton
/*     */   {
/*     */     private final EnumPlayerModelParts field_175234_p;
/*     */     private static final String __OBFID = "CL_00001930";
/*     */     
/*     */     private ButtonPart(int p_i45514_2_, int p_i45514_3_, int p_i45514_4_, int p_i45514_5_, int p_i45514_6_, EnumPlayerModelParts p_i45514_7_) {
/*  94 */       super(p_i45514_2_, p_i45514_3_, p_i45514_4_, p_i45514_5_, p_i45514_6_, GuiCustomizeSkin.this.func_175358_a(p_i45514_7_));
/*  95 */       this.field_175234_p = p_i45514_7_;
/*     */     }
/*     */ 
/*     */     
/*     */     ButtonPart(int p_i45515_2_, int p_i45515_3_, int p_i45515_4_, int p_i45515_5_, int p_i45515_6_, EnumPlayerModelParts p_i45515_7_, Object p_i45515_8_) {
/* 100 */       this(p_i45515_2_, p_i45515_3_, p_i45515_4_, p_i45515_5_, p_i45515_6_, p_i45515_7_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiCustomizeSkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */