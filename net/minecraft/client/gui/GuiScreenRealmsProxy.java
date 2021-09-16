/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.realms.RealmsButton;
/*     */ import net.minecraft.realms.RealmsScreen;
/*     */ 
/*     */ public class GuiScreenRealmsProxy
/*     */   extends GuiScreen
/*     */ {
/*     */   private RealmsScreen field_154330_a;
/*     */   private static final String __OBFID = "CL_00001847";
/*     */   
/*     */   public GuiScreenRealmsProxy(RealmsScreen p_i1087_1_) {
/*  20 */     this.field_154330_a = p_i1087_1_;
/*  21 */     this.buttonList = Collections.synchronizedList(Lists.newArrayList());
/*     */   }
/*     */ 
/*     */   
/*     */   public RealmsScreen func_154321_a() {
/*  26 */     return this.field_154330_a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  34 */     this.field_154330_a.init();
/*  35 */     super.initGui();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_154325_a(String p_154325_1_, int p_154325_2_, int p_154325_3_, int p_154325_4_) {
/*  40 */     GuiScreen.drawCenteredString(fontRendererObj, p_154325_1_, p_154325_2_, p_154325_3_, p_154325_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_154322_b(String p_154322_1_, int p_154322_2_, int p_154322_3_, int p_154322_4_) {
/*  45 */     drawString(fontRendererObj, p_154322_1_, p_154322_2_, p_154322_3_, p_154322_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
/*  53 */     this.field_154330_a.blit(x, y, textureX, textureY, width, height);
/*  54 */     super.drawTexturedModalRect(x, y, textureX, textureY, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
/*  63 */     drawGradientRect(left, top, right, bottom, startColor, endColor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawDefaultBackground() {
/*  71 */     super.drawDefaultBackground();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/*  79 */     return super.doesGuiPauseGame();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawWorldBackground(int tint) {
/*  84 */     super.drawWorldBackground(tint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  92 */     this.field_154330_a.render(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderToolTip(ItemStack itemIn, int x, int y) {
/*  97 */     super.renderToolTip(itemIn, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCreativeTabHoveringText(String tabName, int mouseX, int mouseY) {
/* 106 */     super.drawCreativeTabHoveringText(tabName, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawHoveringText(List textLines, int x, int y) {
/* 111 */     super.drawHoveringText(textLines, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 119 */     this.field_154330_a.tick();
/* 120 */     super.updateScreen();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_154329_h() {
/* 125 */     return fontRendererObj.FONT_HEIGHT;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_154326_c(String p_154326_1_) {
/* 130 */     return fontRendererObj.getStringWidth(p_154326_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_154319_c(String p_154319_1_, int p_154319_2_, int p_154319_3_, int p_154319_4_) {
/* 135 */     fontRendererObj.drawStringWithShadow(p_154319_1_, p_154319_2_, p_154319_3_, p_154319_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_154323_a(String p_154323_1_, int p_154323_2_) {
/* 140 */     return fontRendererObj.listFormattedStringToWidth(p_154323_1_, p_154323_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void actionPerformed(GuiButton button) throws IOException {
/* 145 */     this.field_154330_a.buttonClicked(((GuiButtonRealmsProxy)button).getRealmsButton());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_154324_i() {
/* 150 */     this.buttonList.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_154327_a(RealmsButton p_154327_1_) {
/* 155 */     this.buttonList.add(p_154327_1_.getProxy());
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_154320_j() {
/* 160 */     ArrayList<RealmsButton> var1 = Lists.newArrayListWithExpectedSize(this.buttonList.size());
/* 161 */     Iterator<GuiButton> var2 = this.buttonList.iterator();
/*     */     
/* 163 */     while (var2.hasNext()) {
/*     */       
/* 165 */       GuiButton var3 = var2.next();
/* 166 */       var1.add(((GuiButtonRealmsProxy)var3).getRealmsButton());
/*     */     } 
/*     */     
/* 169 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_154328_b(RealmsButton p_154328_1_) {
/* 174 */     this.buttonList.remove(p_154328_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 182 */     this.field_154330_a.mouseClicked(mouseX, mouseY, mouseButton);
/* 183 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/* 191 */     this.field_154330_a.mouseEvent();
/* 192 */     super.handleMouseInput();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleKeyboardInput() throws IOException {
/* 200 */     this.field_154330_a.keyboardEvent();
/* 201 */     super.handleKeyboardInput();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int state) {
/* 209 */     this.field_154330_a.mouseReleased(mouseX, mouseY, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
/* 218 */     this.field_154330_a.mouseDragged(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(char typedChar, int keyCode) throws IOException {
/* 227 */     this.field_154330_a.keyPressed(typedChar, keyCode);
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/* 232 */     this.field_154330_a.confirmResult(result, id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 240 */     this.field_154330_a.removed();
/* 241 */     super.onGuiClosed();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenRealmsProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */