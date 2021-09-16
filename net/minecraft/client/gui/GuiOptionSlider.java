/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class GuiOptionSlider
/*    */   extends GuiButton
/*    */ {
/*    */   private float sliderValue;
/*    */   public boolean dragging;
/*    */   private GameSettings.Options options;
/*    */   private final float field_146132_r;
/*    */   private final float field_146131_s;
/*    */   private static final String __OBFID = "CL_00000680";
/*    */   
/*    */   public GuiOptionSlider(int p_i45016_1_, int p_i45016_2_, int p_i45016_3_, GameSettings.Options p_i45016_4_) {
/* 19 */     this(p_i45016_1_, p_i45016_2_, p_i45016_3_, p_i45016_4_, 0.0F, 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public GuiOptionSlider(int p_i45017_1_, int p_i45017_2_, int p_i45017_3_, GameSettings.Options p_i45017_4_, float p_i45017_5_, float p_i45017_6_) {
/* 24 */     super(p_i45017_1_, p_i45017_2_, p_i45017_3_, 150, 20, "");
/* 25 */     this.sliderValue = 1.0F;
/* 26 */     this.options = p_i45017_4_;
/* 27 */     this.field_146132_r = p_i45017_5_;
/* 28 */     this.field_146131_s = p_i45017_6_;
/* 29 */     Minecraft var7 = Minecraft.getMinecraft();
/* 30 */     this.sliderValue = p_i45017_4_.normalizeValue(var7.gameSettings.getOptionFloatValue(p_i45017_4_));
/* 31 */     this.displayString = var7.gameSettings.getKeyBinding(p_i45017_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getHoverState(boolean mouseOver) {
/* 40 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
/* 48 */     if (this.visible) {
/*    */       
/* 50 */       if (this.dragging) {
/*    */         
/* 52 */         this.sliderValue = (mouseX - this.xPosition + 4) / (this.width - 8);
/* 53 */         this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
/* 54 */         float var4 = this.options.denormalizeValue(this.sliderValue);
/* 55 */         mc.gameSettings.setOptionFloatValue(this.options, var4);
/* 56 */         this.sliderValue = this.options.normalizeValue(var4);
/* 57 */         this.displayString = mc.gameSettings.getKeyBinding(this.options);
/*    */       } 
/*    */       
/* 60 */       mc.getTextureManager().bindTexture(buttonTextures);
/* 61 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 62 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
/* 63 */       drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/* 73 */     if (super.mousePressed(mc, mouseX, mouseY)) {
/*    */       
/* 75 */       this.sliderValue = (mouseX - this.xPosition + 4) / (this.width - 8);
/* 76 */       this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
/* 77 */       mc.gameSettings.setOptionFloatValue(this.options, this.options.denormalizeValue(this.sliderValue));
/* 78 */       this.displayString = mc.gameSettings.getKeyBinding(this.options);
/* 79 */       this.dragging = true;
/* 80 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 84 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY) {
/* 93 */     this.dragging = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiOptionSlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */