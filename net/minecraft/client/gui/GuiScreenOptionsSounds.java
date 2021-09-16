/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.audio.SoundCategory;
/*     */ import net.minecraft.client.audio.SoundHandler;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class GuiScreenOptionsSounds
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen field_146505_f;
/*     */   private final GameSettings game_settings_4;
/*  20 */   protected String field_146507_a = "Options";
/*     */   
/*     */   private String field_146508_h;
/*     */   private static final String __OBFID = "CL_00000716";
/*     */   
/*     */   public GuiScreenOptionsSounds(GuiScreen p_i45025_1_, GameSettings p_i45025_2_) {
/*  26 */     this.field_146505_f = p_i45025_1_;
/*  27 */     this.game_settings_4 = p_i45025_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  35 */     byte var1 = 0;
/*  36 */     this.field_146507_a = I18n.format("options.sounds.title", new Object[0]);
/*  37 */     this.field_146508_h = I18n.format("options.off", new Object[0]);
/*  38 */     this.buttonList.add(new Button(SoundCategory.MASTER.getCategoryId(), width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), SoundCategory.MASTER, true));
/*  39 */     int var6 = var1 + 2;
/*  40 */     SoundCategory[] var2 = SoundCategory.values();
/*  41 */     int var3 = var2.length;
/*     */     
/*  43 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  45 */       SoundCategory var5 = var2[var4];
/*     */       
/*  47 */       if (var5 != SoundCategory.MASTER) {
/*     */         
/*  49 */         this.buttonList.add(new Button(var5.getCategoryId(), width / 2 - 155 + var6 % 2 * 160, height / 6 - 12 + 24 * (var6 >> 1), var5, false));
/*  50 */         var6++;
/*     */       } 
/*     */     } 
/*     */     
/*  54 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, I18n.format("gui.done", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  59 */     if (button.enabled)
/*     */     {
/*  61 */       if (button.id == 200) {
/*     */         
/*  63 */         this.mc.gameSettings.saveOptions();
/*  64 */         this.mc.displayGuiScreen(this.field_146505_f);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  74 */     drawDefaultBackground();
/*  75 */     drawCenteredString(fontRendererObj, this.field_146507_a, (width / 2), 15.0F, 16777215);
/*  76 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getSoundVolume(SoundCategory p_146504_1_) {
/*  81 */     float var2 = this.game_settings_4.getSoundLevel(p_146504_1_);
/*  82 */     return (var2 == 0.0F) ? this.field_146508_h : (String.valueOf((int)(var2 * 100.0F)) + "%");
/*     */   }
/*     */   
/*     */   class Button
/*     */     extends GuiButton {
/*     */     private final SoundCategory field_146153_r;
/*     */     private final String field_146152_s;
/*  89 */     public float field_146156_o = 1.0F;
/*     */     
/*     */     public boolean field_146155_p;
/*     */     private static final String __OBFID = "CL_00000717";
/*     */     
/*     */     public Button(int p_i45024_2_, int p_i45024_3_, int p_i45024_4_, SoundCategory p_i45024_5_, boolean p_i45024_6_) {
/*  95 */       super(p_i45024_2_, p_i45024_3_, p_i45024_4_, p_i45024_6_ ? 310 : 150, 20, "");
/*  96 */       this.field_146153_r = p_i45024_5_;
/*  97 */       this.field_146152_s = I18n.format("soundCategory." + p_i45024_5_.getCategoryName(), new Object[0]);
/*  98 */       this.displayString = String.valueOf(this.field_146152_s) + ": " + GuiScreenOptionsSounds.this.getSoundVolume(p_i45024_5_);
/*  99 */       this.field_146156_o = GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(p_i45024_5_);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getHoverState(boolean mouseOver) {
/* 104 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
/* 109 */       if (this.visible) {
/*     */         
/* 111 */         if (this.field_146155_p) {
/*     */           
/* 113 */           this.field_146156_o = (mouseX - this.xPosition + 4) / (this.width - 8);
/* 114 */           this.field_146156_o = MathHelper.clamp_float(this.field_146156_o, 0.0F, 1.0F);
/* 115 */           mc.gameSettings.setSoundLevel(this.field_146153_r, this.field_146156_o);
/* 116 */           mc.gameSettings.saveOptions();
/* 117 */           this.displayString = String.valueOf(this.field_146152_s) + ": " + GuiScreenOptionsSounds.this.getSoundVolume(this.field_146153_r);
/*     */         } 
/*     */         
/* 120 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 121 */         drawTexturedModalRect(this.xPosition + (int)(this.field_146156_o * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/* 122 */         drawTexturedModalRect(this.xPosition + (int)(this.field_146156_o * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/* 128 */       if (super.mousePressed(mc, mouseX, mouseY)) {
/*     */         
/* 130 */         this.field_146156_o = (mouseX - this.xPosition + 4) / (this.width - 8);
/* 131 */         this.field_146156_o = MathHelper.clamp_float(this.field_146156_o, 0.0F, 1.0F);
/* 132 */         mc.gameSettings.setSoundLevel(this.field_146153_r, this.field_146156_o);
/* 133 */         mc.gameSettings.saveOptions();
/* 134 */         this.displayString = String.valueOf(this.field_146152_s) + ": " + GuiScreenOptionsSounds.this.getSoundVolume(this.field_146153_r);
/* 135 */         this.field_146155_p = true;
/* 136 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 140 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void playPressSound(SoundHandler soundHandlerIn) {}
/*     */ 
/*     */     
/*     */     public void mouseReleased(int mouseX, int mouseY) {
/* 148 */       if (this.field_146155_p) {
/*     */         
/* 150 */         if (this.field_146153_r == SoundCategory.MASTER) {
/*     */           
/* 152 */           float f = 1.0F;
/*     */         }
/*     */         else {
/*     */           
/* 156 */           GuiScreenOptionsSounds.this.game_settings_4.getSoundLevel(this.field_146153_r);
/*     */         } 
/*     */         
/* 159 */         GuiScreenOptionsSounds.this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*     */       } 
/*     */       
/* 162 */       this.field_146155_p = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenOptionsSounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */