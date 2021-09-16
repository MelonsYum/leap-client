/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.audio.SoundHandler;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class GuiButton extends Gui {
/*  11 */   protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
/*     */ 
/*     */   
/*     */   protected int width;
/*     */ 
/*     */   
/*     */   protected int height;
/*     */ 
/*     */   
/*     */   public int xPosition;
/*     */ 
/*     */   
/*     */   public int yPosition;
/*     */   
/*     */   public String displayString;
/*     */   
/*     */   public int id;
/*     */   
/*     */   public boolean enabled;
/*     */   
/*     */   public boolean visible;
/*     */   
/*     */   protected boolean hovered;
/*     */   
/*     */   private static final String __OBFID = "CL_00000668";
/*     */ 
/*     */   
/*     */   public GuiButton(int buttonId, int x, int y, String buttonText) {
/*  39 */     this(buttonId, x, y, 200, 20, buttonText);
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
/*  44 */     this.width = 200;
/*  45 */     this.height = 20;
/*  46 */     this.enabled = true;
/*  47 */     this.visible = true;
/*  48 */     this.id = buttonId;
/*  49 */     this.xPosition = x;
/*  50 */     this.yPosition = y;
/*  51 */     this.width = widthIn;
/*  52 */     this.height = heightIn;
/*  53 */     this.displayString = buttonText;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getHoverState(boolean mouseOver) {
/*  62 */     byte var2 = 1;
/*     */     
/*  64 */     if (!this.enabled) {
/*     */       
/*  66 */       var2 = 0;
/*     */     }
/*  68 */     else if (mouseOver) {
/*     */       
/*  70 */       var2 = 2;
/*     */     } 
/*     */     
/*  73 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/*  81 */     if (this.visible) {
/*     */       
/*  83 */       FontRenderer var4 = mc.fontRendererObj;
/*  84 */       mc.getTextureManager().bindTexture(buttonTextures);
/*  85 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  86 */       this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/*  87 */       int var5 = getHoverState(this.hovered);
/*  88 */       GlStateManager.enableBlend();
/*  89 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  90 */       GlStateManager.blendFunc(770, 771);
/*  91 */       drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.width / 2, this.height);
/*  92 */       drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + var5 * 20, this.width / 2, this.height);
/*  93 */       mouseDragged(mc, mouseX, mouseY);
/*  94 */       int var6 = 14737632;
/*     */       
/*  96 */       if (!this.enabled) {
/*     */         
/*  98 */         var6 = 10526880;
/*     */       }
/* 100 */       else if (this.hovered) {
/*     */         
/* 102 */         var6 = 16777120;
/*     */       } 
/*     */       
/* 105 */       drawCenteredString(var4, this.displayString, (this.xPosition + this.width / 2), (this.yPosition + (this.height - 8) / 2), var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/* 125 */     return (this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMouseOver() {
/* 133 */     return this.hovered;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawButtonForegroundLayer(int mouseX, int mouseY) {}
/*     */   
/*     */   public void playPressSound(SoundHandler soundHandlerIn) {
/* 140 */     soundHandlerIn.playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getButtonWidth() {
/* 145 */     return this.width;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175211_a(int p_175211_1_) {
/* 150 */     this.width = p_175211_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */