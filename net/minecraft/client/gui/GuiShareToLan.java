/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ public class GuiShareToLan
/*     */   extends GuiScreen {
/*     */   private final GuiScreen field_146598_a;
/*     */   private GuiButton field_146596_f;
/*     */   private GuiButton field_146597_g;
/*  15 */   private String field_146599_h = "survival";
/*     */   
/*     */   private boolean field_146600_i;
/*     */   private static final String __OBFID = "CL_00000713";
/*     */   
/*     */   public GuiShareToLan(GuiScreen p_i1055_1_) {
/*  21 */     this.field_146598_a = p_i1055_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  29 */     this.buttonList.clear();
/*  30 */     this.buttonList.add(new GuiButton(101, width / 2 - 155, height - 28, 150, 20, I18n.format("lanServer.start", new Object[0])));
/*  31 */     this.buttonList.add(new GuiButton(102, width / 2 + 5, height - 28, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*  32 */     this.buttonList.add(this.field_146597_g = new GuiButton(104, width / 2 - 155, 100, 150, 20, I18n.format("selectWorld.gameMode", new Object[0])));
/*  33 */     this.buttonList.add(this.field_146596_f = new GuiButton(103, width / 2 + 5, 100, 150, 20, I18n.format("selectWorld.allowCommands", new Object[0])));
/*  34 */     func_146595_g();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146595_g() {
/*  39 */     this.field_146597_g.displayString = String.valueOf(I18n.format("selectWorld.gameMode", new Object[0])) + " " + I18n.format("selectWorld.gameMode." + this.field_146599_h, new Object[0]);
/*  40 */     this.field_146596_f.displayString = String.valueOf(I18n.format("selectWorld.allowCommands", new Object[0])) + " ";
/*     */     
/*  42 */     if (this.field_146600_i) {
/*     */       
/*  44 */       this.field_146596_f.displayString = String.valueOf(this.field_146596_f.displayString) + I18n.format("options.on", new Object[0]);
/*     */     }
/*     */     else {
/*     */       
/*  48 */       this.field_146596_f.displayString = String.valueOf(this.field_146596_f.displayString) + I18n.format("options.off", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  54 */     if (button.id == 102) {
/*     */       
/*  56 */       this.mc.displayGuiScreen(this.field_146598_a);
/*     */     }
/*  58 */     else if (button.id == 104) {
/*     */       
/*  60 */       if (this.field_146599_h.equals("spectator")) {
/*     */         
/*  62 */         this.field_146599_h = "creative";
/*     */       }
/*  64 */       else if (this.field_146599_h.equals("creative")) {
/*     */         
/*  66 */         this.field_146599_h = "adventure";
/*     */       }
/*  68 */       else if (this.field_146599_h.equals("adventure")) {
/*     */         
/*  70 */         this.field_146599_h = "survival";
/*     */       }
/*     */       else {
/*     */         
/*  74 */         this.field_146599_h = "spectator";
/*     */       } 
/*     */       
/*  77 */       func_146595_g();
/*     */     }
/*  79 */     else if (button.id == 103) {
/*     */       
/*  81 */       this.field_146600_i = !this.field_146600_i;
/*  82 */       func_146595_g();
/*     */     }
/*  84 */     else if (button.id == 101) {
/*     */       Object var3;
/*  86 */       this.mc.displayGuiScreen(null);
/*  87 */       String var2 = this.mc.getIntegratedServer().shareToLAN(WorldSettings.GameType.getByName(this.field_146599_h), this.field_146600_i);
/*     */ 
/*     */       
/*  90 */       if (var2 != null) {
/*     */         
/*  92 */         var3 = new ChatComponentTranslation("commands.publish.started", new Object[] { var2 });
/*     */       }
/*     */       else {
/*     */         
/*  96 */         var3 = new ChatComponentText("commands.publish.failed");
/*     */       } 
/*     */       
/*  99 */       this.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 108 */     drawDefaultBackground();
/* 109 */     drawCenteredString(fontRendererObj, I18n.format("lanServer.title", new Object[0]), (width / 2), 50.0F, 16777215);
/* 110 */     drawCenteredString(fontRendererObj, I18n.format("lanServer.otherPlayers", new Object[0]), (width / 2), 82.0F, 16777215);
/* 111 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiShareToLan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */