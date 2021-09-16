/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.command.server.CommandBlockLogic;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiCommandBlock extends GuiScreen {
/*  16 */   private static final Logger field_146488_a = LogManager.getLogger();
/*     */   
/*     */   private GuiTextField commandTextField;
/*     */   
/*     */   private GuiTextField field_146486_g;
/*     */   
/*     */   private final CommandBlockLogic localCommandBlock;
/*     */   
/*     */   private GuiButton doneBtn;
/*     */   
/*     */   private GuiButton cancelBtn;
/*     */   
/*     */   private GuiButton field_175390_s;
/*     */   
/*     */   private boolean field_175389_t;
/*     */   private static final String __OBFID = "CL_00000748";
/*     */   
/*     */   public GuiCommandBlock(CommandBlockLogic p_i45032_1_) {
/*  34 */     this.localCommandBlock = p_i45032_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  42 */     this.commandTextField.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  50 */     Keyboard.enableRepeatEvents(true);
/*  51 */     this.buttonList.clear();
/*  52 */     this.buttonList.add(this.doneBtn = new GuiButton(0, width / 2 - 4 - 150, height / 4 + 120 + 12, 150, 20, I18n.format("gui.done", new Object[0])));
/*  53 */     this.buttonList.add(this.cancelBtn = new GuiButton(1, width / 2 + 4, height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*  54 */     this.buttonList.add(this.field_175390_s = new GuiButton(4, width / 2 + 150 - 20, 150, 20, 20, "O"));
/*  55 */     this.commandTextField = new GuiTextField(2, fontRendererObj, width / 2 - 150, 50, 300, 20);
/*  56 */     this.commandTextField.setMaxStringLength(32767);
/*  57 */     this.commandTextField.setFocused(true);
/*  58 */     this.commandTextField.setText(this.localCommandBlock.getCustomName());
/*  59 */     this.field_146486_g = new GuiTextField(3, fontRendererObj, width / 2 - 150, 150, 276, 20);
/*  60 */     this.field_146486_g.setMaxStringLength(32767);
/*  61 */     this.field_146486_g.setEnabled(false);
/*  62 */     this.field_146486_g.setText("-");
/*  63 */     this.field_175389_t = this.localCommandBlock.func_175571_m();
/*  64 */     func_175388_a();
/*  65 */     this.doneBtn.enabled = (this.commandTextField.getText().trim().length() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  73 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  78 */     if (button.enabled)
/*     */     {
/*  80 */       if (button.id == 1) {
/*     */         
/*  82 */         this.localCommandBlock.func_175573_a(this.field_175389_t);
/*  83 */         this.mc.displayGuiScreen(null);
/*     */       }
/*  85 */       else if (button.id == 0) {
/*     */         
/*  87 */         PacketBuffer var2 = new PacketBuffer(Unpooled.buffer());
/*  88 */         var2.writeByte(this.localCommandBlock.func_145751_f());
/*  89 */         this.localCommandBlock.func_145757_a((ByteBuf)var2);
/*  90 */         var2.writeString(this.commandTextField.getText());
/*  91 */         var2.writeBoolean(this.localCommandBlock.func_175571_m());
/*  92 */         this.mc.getNetHandler().addToSendQueue((Packet)new C17PacketCustomPayload("MC|AdvCdm", var2));
/*     */         
/*  94 */         if (!this.localCommandBlock.func_175571_m())
/*     */         {
/*  96 */           this.localCommandBlock.func_145750_b(null);
/*     */         }
/*     */         
/*  99 */         this.mc.displayGuiScreen(null);
/*     */       }
/* 101 */       else if (button.id == 4) {
/*     */         
/* 103 */         this.localCommandBlock.func_175573_a(!this.localCommandBlock.func_175571_m());
/* 104 */         func_175388_a();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 115 */     this.commandTextField.textboxKeyTyped(typedChar, keyCode);
/* 116 */     this.field_146486_g.textboxKeyTyped(typedChar, keyCode);
/* 117 */     this.doneBtn.enabled = (this.commandTextField.getText().trim().length() > 0);
/*     */     
/* 119 */     if (keyCode != 28 && keyCode != 156) {
/*     */       
/* 121 */       if (keyCode == 1)
/*     */       {
/* 123 */         actionPerformed(this.cancelBtn);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 128 */       actionPerformed(this.doneBtn);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 137 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 138 */     this.commandTextField.mouseClicked(mouseX, mouseY, mouseButton);
/* 139 */     this.field_146486_g.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 147 */     drawDefaultBackground();
/* 148 */     drawCenteredString(fontRendererObj, I18n.format("advMode.setCommand", new Object[0]), (width / 2), 20.0F, 16777215);
/* 149 */     drawString(fontRendererObj, I18n.format("advMode.command", new Object[0]), width / 2 - 150, 37, 10526880);
/* 150 */     this.commandTextField.drawTextBox();
/* 151 */     byte var4 = 75;
/* 152 */     byte var5 = 0;
/* 153 */     FontRenderer var10001 = fontRendererObj;
/* 154 */     String var10002 = I18n.format("advMode.nearestPlayer", new Object[0]);
/* 155 */     int var10003 = width / 2 - 150;
/* 156 */     int var7 = var5 + 1;
/* 157 */     drawString(var10001, var10002, var10003, var4 + var5 * fontRendererObj.FONT_HEIGHT, 10526880);
/* 158 */     drawString(fontRendererObj, I18n.format("advMode.randomPlayer", new Object[0]), width / 2 - 150, var4 + var7++ * fontRendererObj.FONT_HEIGHT, 10526880);
/* 159 */     drawString(fontRendererObj, I18n.format("advMode.allPlayers", new Object[0]), width / 2 - 150, var4 + var7++ * fontRendererObj.FONT_HEIGHT, 10526880);
/* 160 */     drawString(fontRendererObj, I18n.format("advMode.allEntities", new Object[0]), width / 2 - 150, var4 + var7++ * fontRendererObj.FONT_HEIGHT, 10526880);
/* 161 */     drawString(fontRendererObj, "", width / 2 - 150, var4 + var7++ * fontRendererObj.FONT_HEIGHT, 10526880);
/*     */     
/* 163 */     if (this.field_146486_g.getText().length() > 0) {
/*     */       
/* 165 */       int var6 = var4 + var7 * fontRendererObj.FONT_HEIGHT + 16;
/* 166 */       drawString(fontRendererObj, I18n.format("advMode.previousOutput", new Object[0]), width / 2 - 150, var6, 10526880);
/* 167 */       this.field_146486_g.drawTextBox();
/*     */     } 
/*     */     
/* 170 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175388_a() {
/* 175 */     if (this.localCommandBlock.func_175571_m()) {
/*     */       
/* 177 */       this.field_175390_s.displayString = "O";
/*     */       
/* 179 */       if (this.localCommandBlock.getLastOutput() != null)
/*     */       {
/* 181 */         this.field_146486_g.setText(this.localCommandBlock.getLastOutput().getUnformattedText());
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 186 */       this.field_175390_s.displayString = "X";
/* 187 */       this.field_146486_g.setText("-");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */