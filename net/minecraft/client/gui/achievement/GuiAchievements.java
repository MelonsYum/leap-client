/*     */ package net.minecraft.client.gui.achievement;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiOptionButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.IProgressMeter;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatFileWriter;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class GuiAchievements extends GuiScreen implements IProgressMeter {
/*  28 */   private static final int field_146572_y = AchievementList.minDisplayColumn * 24 - 112;
/*  29 */   private static final int field_146571_z = AchievementList.minDisplayRow * 24 - 112;
/*  30 */   private static final int field_146559_A = AchievementList.maxDisplayColumn * 24 - 77;
/*  31 */   private static final int field_146560_B = AchievementList.maxDisplayRow * 24 - 77;
/*  32 */   private static final ResourceLocation field_146561_C = new ResourceLocation("textures/gui/achievement/achievement_background.png");
/*     */   protected GuiScreen parentScreen;
/*  34 */   protected int field_146555_f = 256;
/*  35 */   protected int field_146557_g = 202;
/*     */   protected int field_146563_h;
/*     */   protected int field_146564_i;
/*  38 */   protected float field_146570_r = 1.0F;
/*     */   
/*     */   protected double field_146569_s;
/*     */   protected double field_146568_t;
/*     */   protected double field_146567_u;
/*     */   protected double field_146566_v;
/*     */   protected double field_146565_w;
/*     */   protected double field_146573_x;
/*     */   private int field_146554_D;
/*     */   private StatFileWriter statFileWriter;
/*     */   private boolean loadingAchievements = true;
/*     */   private static final String __OBFID = "CL_00000722";
/*     */   
/*     */   public GuiAchievements(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_) {
/*  52 */     this.parentScreen = p_i45026_1_;
/*  53 */     this.statFileWriter = p_i45026_2_;
/*  54 */     short var3 = 141;
/*  55 */     short var4 = 141;
/*  56 */     this.field_146569_s = this.field_146567_u = this.field_146565_w = (AchievementList.openInventory.displayColumn * 24 - var3 / 2 - 12);
/*  57 */     this.field_146568_t = this.field_146566_v = this.field_146573_x = (AchievementList.openInventory.displayRow * 24 - var4 / 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  65 */     this.mc.getNetHandler().addToSendQueue((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
/*  66 */     this.buttonList.clear();
/*  67 */     this.buttonList.add(new GuiOptionButton(1, width / 2 + 24, height / 2 + 74, 80, 20, I18n.format("gui.done", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  72 */     if (!this.loadingAchievements)
/*     */     {
/*  74 */       if (button.id == 1)
/*     */       {
/*  76 */         this.mc.displayGuiScreen(this.parentScreen);
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
/*  87 */     if (keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
/*     */       
/*  89 */       this.mc.displayGuiScreen(null);
/*  90 */       this.mc.setIngameFocus();
/*     */     }
/*     */     else {
/*     */       
/*  94 */       super.keyTyped(typedChar, keyCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 103 */     if (this.loadingAchievements) {
/*     */       
/* 105 */       drawDefaultBackground();
/* 106 */       drawCenteredString(fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), (width / 2), (height / 2), 16777215);
/* 107 */       drawCenteredString(fontRendererObj, lanSearchStates[(int)(Minecraft.getSystemTime() / 150L % lanSearchStates.length)], (width / 2), (height / 2 + fontRendererObj.FONT_HEIGHT * 2), 16777215);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 113 */       if (Mouse.isButtonDown(0)) {
/*     */         
/* 115 */         int i = (width - this.field_146555_f) / 2;
/* 116 */         int var5 = (height - this.field_146557_g) / 2;
/* 117 */         int var6 = i + 8;
/* 118 */         int var7 = var5 + 17;
/*     */         
/* 120 */         if ((this.field_146554_D == 0 || this.field_146554_D == 1) && mouseX >= var6 && mouseX < var6 + 224 && mouseY >= var7 && mouseY < var7 + 155)
/*     */         {
/* 122 */           if (this.field_146554_D == 0) {
/*     */             
/* 124 */             this.field_146554_D = 1;
/*     */           }
/*     */           else {
/*     */             
/* 128 */             this.field_146567_u -= ((mouseX - this.field_146563_h) * this.field_146570_r);
/* 129 */             this.field_146566_v -= ((mouseY - this.field_146564_i) * this.field_146570_r);
/* 130 */             this.field_146565_w = this.field_146569_s = this.field_146567_u;
/* 131 */             this.field_146573_x = this.field_146568_t = this.field_146566_v;
/*     */           } 
/*     */           
/* 134 */           this.field_146563_h = mouseX;
/* 135 */           this.field_146564_i = mouseY;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 140 */         this.field_146554_D = 0;
/*     */       } 
/*     */       
/* 143 */       int var4 = Mouse.getDWheel();
/* 144 */       float var11 = this.field_146570_r;
/*     */       
/* 146 */       if (var4 < 0) {
/*     */         
/* 148 */         this.field_146570_r += 0.25F;
/*     */       }
/* 150 */       else if (var4 > 0) {
/*     */         
/* 152 */         this.field_146570_r -= 0.25F;
/*     */       } 
/*     */       
/* 155 */       this.field_146570_r = MathHelper.clamp_float(this.field_146570_r, 1.0F, 2.0F);
/*     */       
/* 157 */       if (this.field_146570_r != var11) {
/*     */         
/* 159 */         float var10000 = var11 - this.field_146570_r;
/* 160 */         float var12 = var11 * this.field_146555_f;
/* 161 */         float var8 = var11 * this.field_146557_g;
/* 162 */         float var9 = this.field_146570_r * this.field_146555_f;
/* 163 */         float var10 = this.field_146570_r * this.field_146557_g;
/* 164 */         this.field_146567_u -= ((var9 - var12) * 0.5F);
/* 165 */         this.field_146566_v -= ((var10 - var8) * 0.5F);
/* 166 */         this.field_146565_w = this.field_146569_s = this.field_146567_u;
/* 167 */         this.field_146573_x = this.field_146568_t = this.field_146566_v;
/*     */       } 
/*     */       
/* 170 */       if (this.field_146565_w < field_146572_y)
/*     */       {
/* 172 */         this.field_146565_w = field_146572_y;
/*     */       }
/*     */       
/* 175 */       if (this.field_146573_x < field_146571_z)
/*     */       {
/* 177 */         this.field_146573_x = field_146571_z;
/*     */       }
/*     */       
/* 180 */       if (this.field_146565_w >= field_146559_A)
/*     */       {
/* 182 */         this.field_146565_w = (field_146559_A - 1);
/*     */       }
/*     */       
/* 185 */       if (this.field_146573_x >= field_146560_B)
/*     */       {
/* 187 */         this.field_146573_x = (field_146560_B - 1);
/*     */       }
/*     */       
/* 190 */       drawDefaultBackground();
/* 191 */       drawAchievementScreen(mouseX, mouseY, partialTicks);
/* 192 */       GlStateManager.disableLighting();
/* 193 */       GlStateManager.disableDepth();
/* 194 */       drawTitle();
/* 195 */       GlStateManager.enableLighting();
/* 196 */       GlStateManager.enableDepth();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doneLoading() {
/* 202 */     if (this.loadingAchievements)
/*     */     {
/* 204 */       this.loadingAchievements = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 213 */     if (!this.loadingAchievements) {
/*     */       
/* 215 */       this.field_146569_s = this.field_146567_u;
/* 216 */       this.field_146568_t = this.field_146566_v;
/* 217 */       double var1 = this.field_146565_w - this.field_146567_u;
/* 218 */       double var3 = this.field_146573_x - this.field_146566_v;
/*     */       
/* 220 */       if (var1 * var1 + var3 * var3 < 4.0D) {
/*     */         
/* 222 */         this.field_146567_u += var1;
/* 223 */         this.field_146566_v += var3;
/*     */       }
/*     */       else {
/*     */         
/* 227 */         this.field_146567_u += var1 * 0.85D;
/* 228 */         this.field_146566_v += var3 * 0.85D;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawTitle() {
/* 235 */     int var1 = (width - this.field_146555_f) / 2;
/* 236 */     int var2 = (height - this.field_146557_g) / 2;
/* 237 */     fontRendererObj.drawString(I18n.format("gui.achievements", new Object[0]), (var1 + 15), (var2 + 5), 4210752);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawAchievementScreen(int p_146552_1_, int p_146552_2_, float p_146552_3_) {
/* 242 */     int var4 = MathHelper.floor_double(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * p_146552_3_);
/* 243 */     int var5 = MathHelper.floor_double(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * p_146552_3_);
/*     */     
/* 245 */     if (var4 < field_146572_y)
/*     */     {
/* 247 */       var4 = field_146572_y;
/*     */     }
/*     */     
/* 250 */     if (var5 < field_146571_z)
/*     */     {
/* 252 */       var5 = field_146571_z;
/*     */     }
/*     */     
/* 255 */     if (var4 >= field_146559_A)
/*     */     {
/* 257 */       var4 = field_146559_A - 1;
/*     */     }
/*     */     
/* 260 */     if (var5 >= field_146560_B)
/*     */     {
/* 262 */       var5 = field_146560_B - 1;
/*     */     }
/*     */     
/* 265 */     int var6 = (width - this.field_146555_f) / 2;
/* 266 */     int var7 = (height - this.field_146557_g) / 2;
/* 267 */     int var8 = var6 + 16;
/* 268 */     int var9 = var7 + 17;
/* 269 */     zLevel = 0.0F;
/* 270 */     GlStateManager.depthFunc(518);
/* 271 */     GlStateManager.pushMatrix();
/* 272 */     GlStateManager.translate(var8, var9, -200.0F);
/* 273 */     GlStateManager.scale(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
/* 274 */     GlStateManager.func_179098_w();
/* 275 */     GlStateManager.disableLighting();
/* 276 */     GlStateManager.enableRescaleNormal();
/* 277 */     GlStateManager.enableColorMaterial();
/* 278 */     int var10 = var4 + 288 >> 4;
/* 279 */     int var11 = var5 + 288 >> 4;
/* 280 */     int var12 = (var4 + 288) % 16;
/* 281 */     int var13 = (var5 + 288) % 16;
/* 282 */     boolean var14 = true;
/* 283 */     boolean var15 = true;
/* 284 */     boolean var16 = true;
/* 285 */     boolean var17 = true;
/* 286 */     boolean var18 = true;
/* 287 */     Random var19 = new Random();
/* 288 */     float var20 = 16.0F / this.field_146570_r;
/* 289 */     float var21 = 16.0F / this.field_146570_r;
/*     */ 
/*     */     
/*     */     int var22;
/*     */ 
/*     */     
/* 295 */     for (var22 = 0; var22 * var20 - var13 < 155.0F; var22++) {
/*     */       
/* 297 */       float f = 0.6F - (var11 + var22) / 25.0F * 0.3F;
/* 298 */       GlStateManager.color(f, f, f, 1.0F);
/*     */       
/* 300 */       for (int var24 = 0; var24 * var21 - var12 < 224.0F; var24++) {
/*     */         
/* 302 */         var19.setSeed((this.mc.getSession().getPlayerID().hashCode() + var10 + var24 + (var11 + var22) * 16));
/* 303 */         int i = var19.nextInt(1 + var11 + var22) + (var11 + var22) / 2;
/* 304 */         TextureAtlasSprite var26 = func_175371_a((Block)Blocks.sand);
/*     */         
/* 306 */         if (i <= 37 && var11 + var22 != 35) {
/*     */           
/* 308 */           if (i == 22) {
/*     */             
/* 310 */             if (var19.nextInt(2) == 0)
/*     */             {
/* 312 */               var26 = func_175371_a(Blocks.diamond_ore);
/*     */             }
/*     */             else
/*     */             {
/* 316 */               var26 = func_175371_a(Blocks.redstone_ore);
/*     */             }
/*     */           
/* 319 */           } else if (i == 10) {
/*     */             
/* 321 */             var26 = func_175371_a(Blocks.iron_ore);
/*     */           }
/* 323 */           else if (i == 8) {
/*     */             
/* 325 */             var26 = func_175371_a(Blocks.coal_ore);
/*     */           }
/* 327 */           else if (i > 4) {
/*     */             
/* 329 */             var26 = func_175371_a(Blocks.stone);
/*     */           }
/* 331 */           else if (i > 0) {
/*     */             
/* 333 */             var26 = func_175371_a(Blocks.dirt);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 338 */           Block var27 = Blocks.bedrock;
/* 339 */           var26 = func_175371_a(var27);
/*     */         } 
/*     */         
/* 342 */         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 343 */         func_175175_a(var24 * 16 - var12, var22 * 16 - var13, var26, 16, 16);
/*     */       } 
/*     */     } 
/*     */     
/* 347 */     GlStateManager.enableDepth();
/* 348 */     GlStateManager.depthFunc(515);
/* 349 */     this.mc.getTextureManager().bindTexture(field_146561_C);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     for (var22 = 0; var22 < AchievementList.achievementList.size(); var22++) {
/*     */       
/* 356 */       Achievement var34 = AchievementList.achievementList.get(var22);
/*     */       
/* 358 */       if (var34.parentAchievement != null) {
/*     */         
/* 360 */         int var24 = var34.displayColumn * 24 - var4 + 11;
/* 361 */         int i = var34.displayRow * 24 - var5 + 11;
/* 362 */         int var37 = var34.parentAchievement.displayColumn * 24 - var4 + 11;
/* 363 */         int var40 = var34.parentAchievement.displayRow * 24 - var5 + 11;
/* 364 */         boolean var28 = this.statFileWriter.hasAchievementUnlocked(var34);
/* 365 */         boolean var29 = this.statFileWriter.canUnlockAchievement(var34);
/* 366 */         int var30 = this.statFileWriter.func_150874_c(var34);
/*     */         
/* 368 */         if (var30 <= 4) {
/*     */           
/* 370 */           int var31 = -16777216;
/*     */           
/* 372 */           if (var28) {
/*     */             
/* 374 */             var31 = -6250336;
/*     */           }
/* 376 */           else if (var29) {
/*     */             
/* 378 */             var31 = -16711936;
/*     */           } 
/*     */           
/* 381 */           drawHorizontalLine(var24, var37, i, var31);
/* 382 */           drawVerticalLine(var37, i, var40, var31);
/*     */           
/* 384 */           if (var24 > var37) {
/*     */             
/* 386 */             drawTexturedModalRect(var24 - 11 - 7, i - 5, 114, 234, 7, 11);
/*     */           }
/* 388 */           else if (var24 < var37) {
/*     */             
/* 390 */             drawTexturedModalRect(var24 + 11, i - 5, 107, 234, 7, 11);
/*     */           }
/* 392 */           else if (i > var40) {
/*     */             
/* 394 */             drawTexturedModalRect(var24 - 5, i - 11 - 7, 96, 234, 11, 7);
/*     */           }
/* 396 */           else if (i < var40) {
/*     */             
/* 398 */             drawTexturedModalRect(var24 - 5, i + 11, 96, 241, 11, 7);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 404 */     Achievement var33 = null;
/* 405 */     float var23 = (p_146552_1_ - var8) * this.field_146570_r;
/* 406 */     float var35 = (p_146552_2_ - var9) * this.field_146570_r;
/* 407 */     RenderHelper.enableGUIStandardItemLighting();
/* 408 */     GlStateManager.disableLighting();
/* 409 */     GlStateManager.enableRescaleNormal();
/* 410 */     GlStateManager.enableColorMaterial();
/*     */ 
/*     */ 
/*     */     
/* 414 */     for (int var25 = 0; var25 < AchievementList.achievementList.size(); var25++) {
/*     */       
/* 416 */       Achievement var38 = AchievementList.achievementList.get(var25);
/* 417 */       int var40 = var38.displayColumn * 24 - var4;
/* 418 */       int var41 = var38.displayRow * 24 - var5;
/*     */       
/* 420 */       if (var40 >= -24 && var41 >= -24 && var40 <= 224.0F * this.field_146570_r && var41 <= 155.0F * this.field_146570_r) {
/*     */         
/* 422 */         int var42 = this.statFileWriter.func_150874_c(var38);
/*     */ 
/*     */         
/* 425 */         if (this.statFileWriter.hasAchievementUnlocked(var38)) {
/*     */           
/* 427 */           float var43 = 0.75F;
/* 428 */           GlStateManager.color(var43, var43, var43, 1.0F);
/*     */         }
/* 430 */         else if (this.statFileWriter.canUnlockAchievement(var38)) {
/*     */           
/* 432 */           float var43 = 1.0F;
/* 433 */           GlStateManager.color(var43, var43, var43, 1.0F);
/*     */         }
/* 435 */         else if (var42 < 3) {
/*     */           
/* 437 */           float var43 = 0.3F;
/* 438 */           GlStateManager.color(var43, var43, var43, 1.0F);
/*     */         }
/* 440 */         else if (var42 == 3) {
/*     */           
/* 442 */           float var43 = 0.2F;
/* 443 */           GlStateManager.color(var43, var43, var43, 1.0F);
/*     */         }
/*     */         else {
/*     */           
/* 447 */           if (var42 != 4) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/* 452 */           float var43 = 0.1F;
/* 453 */           GlStateManager.color(var43, var43, var43, 1.0F);
/*     */         } 
/*     */         
/* 456 */         this.mc.getTextureManager().bindTexture(field_146561_C);
/*     */         
/* 458 */         if (var38.getSpecial()) {
/*     */           
/* 460 */           drawTexturedModalRect(var40 - 2, var41 - 2, 26, 202, 26, 26);
/*     */         }
/*     */         else {
/*     */           
/* 464 */           drawTexturedModalRect(var40 - 2, var41 - 2, 0, 202, 26, 26);
/*     */         } 
/*     */         
/* 467 */         if (!this.statFileWriter.canUnlockAchievement(var38)) {
/*     */           
/* 469 */           float f = 0.1F;
/* 470 */           GlStateManager.color(f, f, f, 1.0F);
/* 471 */           this.itemRender.func_175039_a(false);
/*     */         } 
/*     */         
/* 474 */         GlStateManager.enableLighting();
/* 475 */         GlStateManager.enableCull();
/* 476 */         this.itemRender.func_180450_b(var38.theItemStack, var40 + 3, var41 + 3);
/* 477 */         GlStateManager.blendFunc(770, 771);
/* 478 */         GlStateManager.disableLighting();
/*     */         
/* 480 */         if (!this.statFileWriter.canUnlockAchievement(var38))
/*     */         {
/* 482 */           this.itemRender.func_175039_a(true);
/*     */         }
/*     */         
/* 485 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         
/* 487 */         if (var23 >= var40 && var23 <= (var40 + 22) && var35 >= var41 && var35 <= (var41 + 22))
/*     */         {
/* 489 */           var33 = var38;
/*     */         }
/*     */       } 
/*     */       continue;
/*     */     } 
/* 494 */     GlStateManager.disableDepth();
/* 495 */     GlStateManager.enableBlend();
/* 496 */     GlStateManager.popMatrix();
/* 497 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 498 */     this.mc.getTextureManager().bindTexture(field_146561_C);
/* 499 */     drawTexturedModalRect(var6, var7, 0, 0, this.field_146555_f, this.field_146557_g);
/* 500 */     zLevel = 0.0F;
/* 501 */     GlStateManager.depthFunc(515);
/* 502 */     GlStateManager.disableDepth();
/* 503 */     GlStateManager.func_179098_w();
/* 504 */     super.drawScreen(p_146552_1_, p_146552_2_, p_146552_3_);
/*     */     
/* 506 */     if (var33 != null) {
/*     */       
/* 508 */       String var36 = var33.getStatName().getUnformattedText();
/* 509 */       String var39 = var33.getDescription();
/* 510 */       int var40 = p_146552_1_ + 12;
/* 511 */       int var41 = p_146552_2_ - 4;
/* 512 */       int var42 = this.statFileWriter.func_150874_c(var33);
/*     */       
/* 514 */       if (this.statFileWriter.canUnlockAchievement(var33)) {
/*     */         
/* 516 */         int var30 = Math.max(fontRendererObj.getStringWidth(var36), 120);
/* 517 */         int var31 = fontRendererObj.splitStringWidth(var39, var30);
/*     */         
/* 519 */         if (this.statFileWriter.hasAchievementUnlocked(var33))
/*     */         {
/* 521 */           var31 += 12;
/*     */         }
/*     */         
/* 524 */         drawGradientRect((var40 - 3), (var41 - 3), (var40 + var30 + 3), (var41 + var31 + 3 + 12), -1073741824, -1073741824);
/* 525 */         fontRendererObj.drawSplitString(var39, var40, var41 + 12, var30, -6250336);
/*     */         
/* 527 */         if (this.statFileWriter.hasAchievementUnlocked(var33))
/*     */         {
/* 529 */           fontRendererObj.drawStringWithShadow(I18n.format("achievement.taken", new Object[0]), var40, (var41 + var31 + 4), -7302913);
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 537 */       else if (var42 == 3) {
/*     */         
/* 539 */         var36 = I18n.format("achievement.unknown", new Object[0]);
/* 540 */         int var30 = Math.max(fontRendererObj.getStringWidth(var36), 120);
/* 541 */         String var44 = (new ChatComponentTranslation("achievement.requires", new Object[] { var33.parentAchievement.getStatName() })).getUnformattedText();
/* 542 */         int var32 = fontRendererObj.splitStringWidth(var44, var30);
/* 543 */         drawGradientRect((var40 - 3), (var41 - 3), (var40 + var30 + 3), (var41 + var32 + 12 + 3), -1073741824, -1073741824);
/* 544 */         fontRendererObj.drawSplitString(var44, var40, var41 + 12, var30, -9416624);
/*     */       }
/* 546 */       else if (var42 < 3) {
/*     */         
/* 548 */         int var30 = Math.max(fontRendererObj.getStringWidth(var36), 120);
/* 549 */         String var44 = (new ChatComponentTranslation("achievement.requires", new Object[] { var33.parentAchievement.getStatName() })).getUnformattedText();
/* 550 */         int var32 = fontRendererObj.splitStringWidth(var44, var30);
/* 551 */         drawGradientRect((var40 - 3), (var41 - 3), (var40 + var30 + 3), (var41 + var32 + 12 + 3), -1073741824, -1073741824);
/* 552 */         fontRendererObj.drawSplitString(var44, var40, var41 + 12, var30, -9416624);
/*     */       }
/*     */       else {
/*     */         
/* 556 */         var36 = null;
/*     */       } 
/*     */ 
/*     */       
/* 560 */       if (var36 != null)
/*     */       {
/* 562 */         fontRendererObj.drawStringWithShadow(var36, var40, var41, this.statFileWriter.canUnlockAchievement(var33) ? (var33.getSpecial() ? -128 : -1) : (var33.getSpecial() ? -8355776 : -8355712));
/*     */       }
/*     */     } 
/*     */     
/* 566 */     GlStateManager.enableDepth();
/* 567 */     GlStateManager.enableLighting();
/* 568 */     RenderHelper.disableStandardItemLighting();
/*     */   }
/*     */ 
/*     */   
/*     */   private TextureAtlasSprite func_175371_a(Block p_175371_1_) {
/* 573 */     return Minecraft.getMinecraft().getBlockRendererDispatcher().func_175023_a().func_178122_a(p_175371_1_.getDefaultState());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 581 */     return !this.loadingAchievements;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\achievement\GuiAchievements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */