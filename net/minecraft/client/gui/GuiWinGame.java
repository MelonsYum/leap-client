/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GuiWinGame extends GuiScreen {
/*  21 */   private static final Logger logger = LogManager.getLogger();
/*  22 */   private static final ResourceLocation field_146576_f = new ResourceLocation("textures/gui/title/minecraft.png");
/*  23 */   private static final ResourceLocation field_146577_g = new ResourceLocation("textures/misc/vignette.png");
/*     */   private int field_146581_h;
/*     */   private List field_146582_i;
/*     */   private int field_146579_r;
/*  27 */   private float field_146578_s = 0.5F;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000719";
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  35 */     this.field_146581_h++;
/*  36 */     float var1 = (this.field_146579_r + height + height + 24) / this.field_146578_s;
/*     */     
/*  38 */     if (this.field_146581_h > var1)
/*     */     {
/*  40 */       sendRespawnPacket();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/*  50 */     if (keyCode == 1)
/*     */     {
/*  52 */       sendRespawnPacket();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendRespawnPacket() {
/*  58 */     this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.PERFORM_RESPAWN));
/*  59 */     this.mc.displayGuiScreen(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  75 */     if (this.field_146582_i == null) {
/*     */       
/*  77 */       this.field_146582_i = Lists.newArrayList();
/*     */ 
/*     */       
/*     */       try {
/*  81 */         String var1 = "";
/*  82 */         String var2 = EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + EnumChatFormatting.GREEN + EnumChatFormatting.AQUA;
/*  83 */         short var3 = 274;
/*  84 */         BufferedReader var4 = new BufferedReader(new InputStreamReader(this.mc.getResourceManager().getResource(new ResourceLocation("texts/end.txt")).getInputStream(), Charsets.UTF_8));
/*  85 */         Random var5 = new Random(8124371L);
/*     */ 
/*     */         
/*  88 */         while ((var1 = var4.readLine()) != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  93 */           for (var1 = var1.replaceAll("PLAYERNAME", this.mc.getSession().getUsername()); var1.contains(var2); var1 = String.valueOf(var7) + EnumChatFormatting.WHITE + EnumChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, var5.nextInt(4) + 3) + var8) {
/*     */             
/*  95 */             int i = var1.indexOf(var2);
/*  96 */             String var7 = var1.substring(0, i);
/*  97 */             String var8 = var1.substring(i + var2.length());
/*     */           } 
/*     */           
/* 100 */           this.field_146582_i.addAll(this.mc.fontRendererObj.listFormattedStringToWidth(var1, var3));
/* 101 */           this.field_146582_i.add("");
/*     */         } 
/*     */         
/* 104 */         for (int var6 = 0; var6 < 8; var6++)
/*     */         {
/* 106 */           this.field_146582_i.add("");
/*     */         }
/*     */         
/* 109 */         var4 = new BufferedReader(new InputStreamReader(this.mc.getResourceManager().getResource(new ResourceLocation("texts/credits.txt")).getInputStream(), Charsets.UTF_8));
/*     */         
/* 111 */         while ((var1 = var4.readLine()) != null) {
/*     */           
/* 113 */           var1 = var1.replaceAll("PLAYERNAME", this.mc.getSession().getUsername());
/* 114 */           var1 = var1.replaceAll("\t", "    ");
/* 115 */           this.field_146582_i.addAll(this.mc.fontRendererObj.listFormattedStringToWidth(var1, var3));
/* 116 */           this.field_146582_i.add("");
/*     */         } 
/*     */         
/* 119 */         this.field_146579_r = this.field_146582_i.size() * 12;
/*     */       }
/* 121 */       catch (Exception var9) {
/*     */         
/* 123 */         logger.error("Couldn't load credits", var9);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawWinGameScreen(int p_146575_1_, int p_146575_2_, float p_146575_3_) {
/* 130 */     Tessellator var4 = Tessellator.getInstance();
/* 131 */     WorldRenderer var5 = var4.getWorldRenderer();
/* 132 */     this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
/* 133 */     var5.startDrawingQuads();
/* 134 */     var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 135 */     int var6 = width;
/* 136 */     float var7 = 0.0F - (this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
/* 137 */     float var8 = height - (this.field_146581_h + p_146575_3_) * 0.5F * this.field_146578_s;
/* 138 */     float var9 = 0.015625F;
/* 139 */     float var10 = (this.field_146581_h + p_146575_3_ - 0.0F) * 0.02F;
/* 140 */     float var11 = (this.field_146579_r + height + height + 24) / this.field_146578_s;
/* 141 */     float var12 = (var11 - 20.0F - this.field_146581_h + p_146575_3_) * 0.005F;
/*     */     
/* 143 */     if (var12 < var10)
/*     */     {
/* 145 */       var10 = var12;
/*     */     }
/*     */     
/* 148 */     if (var10 > 1.0F)
/*     */     {
/* 150 */       var10 = 1.0F;
/*     */     }
/*     */     
/* 153 */     var10 *= var10;
/* 154 */     var10 = var10 * 96.0F / 255.0F;
/* 155 */     var5.func_178986_b(var10, var10, var10);
/* 156 */     var5.addVertexWithUV(0.0D, height, zLevel, 0.0D, (var7 * var9));
/* 157 */     var5.addVertexWithUV(var6, height, zLevel, (var6 * var9), (var7 * var9));
/* 158 */     var5.addVertexWithUV(var6, 0.0D, zLevel, (var6 * var9), (var8 * var9));
/* 159 */     var5.addVertexWithUV(0.0D, 0.0D, zLevel, 0.0D, (var8 * var9));
/* 160 */     var4.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 168 */     drawWinGameScreen(mouseX, mouseY, partialTicks);
/* 169 */     Tessellator var4 = Tessellator.getInstance();
/* 170 */     WorldRenderer var5 = var4.getWorldRenderer();
/* 171 */     short var6 = 274;
/* 172 */     int var7 = width / 2 - var6 / 2;
/* 173 */     int var8 = height + 50;
/* 174 */     float var9 = -(this.field_146581_h + partialTicks) * this.field_146578_s;
/* 175 */     GlStateManager.pushMatrix();
/* 176 */     GlStateManager.translate(0.0F, var9, 0.0F);
/* 177 */     this.mc.getTextureManager().bindTexture(field_146576_f);
/* 178 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 179 */     drawTexturedModalRect(var7, var8, 0, 0, 155, 44);
/* 180 */     drawTexturedModalRect(var7 + 155, var8, 0, 45, 155, 44);
/* 181 */     var5.func_178991_c(16777215);
/* 182 */     int var10 = var8 + 200;
/*     */     
/*     */     int var11;
/* 185 */     for (var11 = 0; var11 < this.field_146582_i.size(); var11++) {
/*     */       
/* 187 */       if (var11 == this.field_146582_i.size() - 1) {
/*     */         
/* 189 */         float var12 = var10 + var9 - (height / 2 - 6);
/*     */         
/* 191 */         if (var12 < 0.0F)
/*     */         {
/* 193 */           GlStateManager.translate(0.0F, -var12, 0.0F);
/*     */         }
/*     */       } 
/*     */       
/* 197 */       if (var10 + var9 + 12.0F + 8.0F > 0.0F && var10 + var9 < height) {
/*     */         
/* 199 */         String var13 = this.field_146582_i.get(var11);
/*     */         
/* 201 */         if (var13.startsWith("[C]")) {
/*     */           
/* 203 */           fontRendererObj.drawStringWithShadow(var13.substring(3), (var7 + (var6 - fontRendererObj.getStringWidth(var13.substring(3))) / 2), var10, 16777215);
/*     */         }
/*     */         else {
/*     */           
/* 207 */           fontRendererObj.fontRandom.setSeed(var11 * 4238972211L + (this.field_146581_h / 4));
/* 208 */           fontRendererObj.drawStringWithShadow(var13, var7, var10, 16777215);
/*     */         } 
/*     */       } 
/*     */       
/* 212 */       var10 += 12;
/*     */     } 
/*     */     
/* 215 */     GlStateManager.popMatrix();
/* 216 */     this.mc.getTextureManager().bindTexture(field_146577_g);
/* 217 */     GlStateManager.enableBlend();
/* 218 */     GlStateManager.blendFunc(0, 769);
/* 219 */     var5.startDrawingQuads();
/* 220 */     var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 221 */     var11 = width;
/* 222 */     int var14 = height;
/* 223 */     var5.addVertexWithUV(0.0D, var14, zLevel, 0.0D, 1.0D);
/* 224 */     var5.addVertexWithUV(var11, var14, zLevel, 1.0D, 1.0D);
/* 225 */     var5.addVertexWithUV(var11, 0.0D, zLevel, 1.0D, 0.0D);
/* 226 */     var5.addVertexWithUV(0.0D, 0.0D, zLevel, 0.0D, 0.0D);
/* 227 */     var4.draw();
/* 228 */     GlStateManager.disableBlend();
/* 229 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiWinGame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */