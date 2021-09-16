/*     */ package net.minecraft.client.gui.inventory;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C12PacketUpdateSign;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntitySign;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiEditSign
/*     */   extends GuiScreen
/*     */ {
/*     */   private TileEntitySign tileSign;
/*     */   private int updateCounter;
/*     */   private int editLine;
/*     */   private GuiButton doneBtn;
/*     */   private static final String __OBFID = "CL_00000764";
/*     */   
/*     */   public GuiEditSign(TileEntitySign p_i1097_1_) {
/*  35 */     this.tileSign = p_i1097_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  43 */     this.buttonList.clear();
/*  44 */     Keyboard.enableRepeatEvents(true);
/*  45 */     this.buttonList.add(this.doneBtn = new GuiButton(0, width / 2 - 100, height / 4 + 120, I18n.format("gui.done", new Object[0])));
/*  46 */     this.tileSign.setEditable(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  54 */     Keyboard.enableRepeatEvents(false);
/*  55 */     NetHandlerPlayClient var1 = this.mc.getNetHandler();
/*     */     
/*  57 */     if (var1 != null)
/*     */     {
/*  59 */       var1.addToSendQueue((Packet)new C12PacketUpdateSign(this.tileSign.getPos(), this.tileSign.signText));
/*     */     }
/*     */     
/*  62 */     this.tileSign.setEditable(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  70 */     this.updateCounter++;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  75 */     if (button.enabled)
/*     */     {
/*  77 */       if (button.id == 0) {
/*     */         
/*  79 */         this.tileSign.markDirty();
/*  80 */         this.mc.displayGuiScreen(null);
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
/*  91 */     if (keyCode == 200)
/*     */     {
/*  93 */       this.editLine = this.editLine - 1 & 0x3;
/*     */     }
/*     */     
/*  96 */     if (keyCode == 208 || keyCode == 28 || keyCode == 156)
/*     */     {
/*  98 */       this.editLine = this.editLine + 1 & 0x3;
/*     */     }
/*     */     
/* 101 */     String var3 = this.tileSign.signText[this.editLine].getUnformattedText();
/*     */     
/* 103 */     if (keyCode == 14 && var3.length() > 0)
/*     */     {
/* 105 */       var3 = var3.substring(0, var3.length() - 1);
/*     */     }
/*     */     
/* 108 */     if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && fontRendererObj.getStringWidth(String.valueOf(var3) + typedChar) <= 90)
/*     */     {
/* 110 */       var3 = String.valueOf(var3) + typedChar;
/*     */     }
/*     */     
/* 113 */     this.tileSign.signText[this.editLine] = (IChatComponent)new ChatComponentText(var3);
/*     */     
/* 115 */     if (keyCode == 1)
/*     */     {
/* 117 */       actionPerformed(this.doneBtn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 126 */     drawDefaultBackground();
/* 127 */     drawCenteredString(fontRendererObj, I18n.format("sign.edit", new Object[0]), (width / 2), 40.0F, 16777215);
/* 128 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 129 */     GlStateManager.pushMatrix();
/* 130 */     GlStateManager.translate((width / 2), 0.0F, 50.0F);
/* 131 */     float var4 = 93.75F;
/* 132 */     GlStateManager.scale(-var4, -var4, -var4);
/* 133 */     GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/* 134 */     Block var5 = this.tileSign.getBlockType();
/*     */     
/* 136 */     if (var5 == Blocks.standing_sign) {
/*     */       
/* 138 */       float var6 = (this.tileSign.getBlockMetadata() * 360) / 16.0F;
/* 139 */       GlStateManager.rotate(var6, 0.0F, 1.0F, 0.0F);
/* 140 */       GlStateManager.translate(0.0F, -1.0625F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/* 144 */       int var8 = this.tileSign.getBlockMetadata();
/* 145 */       float var7 = 0.0F;
/*     */       
/* 147 */       if (var8 == 2)
/*     */       {
/* 149 */         var7 = 180.0F;
/*     */       }
/*     */       
/* 152 */       if (var8 == 4)
/*     */       {
/* 154 */         var7 = 90.0F;
/*     */       }
/*     */       
/* 157 */       if (var8 == 5)
/*     */       {
/* 159 */         var7 = -90.0F;
/*     */       }
/*     */       
/* 162 */       GlStateManager.rotate(var7, 0.0F, 1.0F, 0.0F);
/* 163 */       GlStateManager.translate(0.0F, -1.0625F, 0.0F);
/*     */     } 
/*     */     
/* 166 */     if (this.updateCounter / 6 % 2 == 0)
/*     */     {
/* 168 */       this.tileSign.lineBeingEdited = this.editLine;
/*     */     }
/*     */     
/* 171 */     TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)this.tileSign, -0.5D, -0.75D, -0.5D, 0.0F);
/* 172 */     this.tileSign.lineBeingEdited = -1;
/* 173 */     GlStateManager.popMatrix();
/* 174 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiEditSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */