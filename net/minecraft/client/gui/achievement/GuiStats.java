/*     */ package net.minecraft.client.gui.achievement;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiSlot;
/*     */ import net.minecraft.client.gui.IProgressMeter;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatCrafting;
/*     */ import net.minecraft.stats.StatFileWriter;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ public class GuiStats extends GuiScreen implements IProgressMeter {
/*  34 */   protected String screenTitle = "Select world";
/*     */   
/*     */   protected GuiScreen parentScreen;
/*     */   
/*     */   private StatsGeneral generalStats;
/*     */   private StatsItem itemStats;
/*     */   private StatsBlock blockStats;
/*     */   private StatsMobsList mobStats;
/*     */   private StatFileWriter field_146546_t;
/*     */   private GuiSlot displaySlot;
/*     */   private boolean doesGuiPauseGame = true;
/*     */   private static final String __OBFID = "CL_00000723";
/*     */   
/*     */   public GuiStats(GuiScreen p_i1071_1_, StatFileWriter p_i1071_2_) {
/*  48 */     this.parentScreen = p_i1071_1_;
/*  49 */     this.field_146546_t = p_i1071_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  57 */     this.screenTitle = I18n.format("gui.stats", new Object[0]);
/*  58 */     this.doesGuiPauseGame = true;
/*  59 */     this.mc.getNetHandler().addToSendQueue((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  67 */     super.handleMouseInput();
/*     */     
/*  69 */     if (this.displaySlot != null)
/*     */     {
/*  71 */       this.displaySlot.func_178039_p();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175366_f() {
/*  77 */     this.generalStats = new StatsGeneral(this.mc);
/*  78 */     this.generalStats.registerScrollButtons(1, 1);
/*  79 */     this.itemStats = new StatsItem(this.mc);
/*  80 */     this.itemStats.registerScrollButtons(1, 1);
/*  81 */     this.blockStats = new StatsBlock(this.mc);
/*  82 */     this.blockStats.registerScrollButtons(1, 1);
/*  83 */     this.mobStats = new StatsMobsList(this.mc);
/*  84 */     this.mobStats.registerScrollButtons(1, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createButtons() {
/*  89 */     this.buttonList.add(new GuiButton(0, width / 2 + 4, height - 28, 150, 20, I18n.format("gui.done", new Object[0])));
/*  90 */     this.buttonList.add(new GuiButton(1, width / 2 - 160, height - 52, 80, 20, I18n.format("stat.generalButton", new Object[0])));
/*     */     
/*     */     GuiButton var1;
/*     */     
/*  94 */     this.buttonList.add(var1 = new GuiButton(2, width / 2 - 80, height - 52, 80, 20, I18n.format("stat.blocksButton", new Object[0]))); GuiButton var2;
/*  95 */     this.buttonList.add(var2 = new GuiButton(3, width / 2, height - 52, 80, 20, I18n.format("stat.itemsButton", new Object[0]))); GuiButton var3;
/*  96 */     this.buttonList.add(var3 = new GuiButton(4, width / 2 + 80, height - 52, 80, 20, I18n.format("stat.mobsButton", new Object[0])));
/*     */     
/*  98 */     if (this.blockStats.getSize() == 0)
/*     */     {
/* 100 */       var1.enabled = false;
/*     */     }
/*     */     
/* 103 */     if (this.itemStats.getSize() == 0)
/*     */     {
/* 105 */       var2.enabled = false;
/*     */     }
/*     */     
/* 108 */     if (this.mobStats.getSize() == 0)
/*     */     {
/* 110 */       var3.enabled = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 116 */     if (button.enabled)
/*     */     {
/* 118 */       if (button.id == 0) {
/*     */         
/* 120 */         this.mc.displayGuiScreen(this.parentScreen);
/*     */       }
/* 122 */       else if (button.id == 1) {
/*     */         
/* 124 */         this.displaySlot = this.generalStats;
/*     */       }
/* 126 */       else if (button.id == 3) {
/*     */         
/* 128 */         this.displaySlot = this.itemStats;
/*     */       }
/* 130 */       else if (button.id == 2) {
/*     */         
/* 132 */         this.displaySlot = this.blockStats;
/*     */       }
/* 134 */       else if (button.id == 4) {
/*     */         
/* 136 */         this.displaySlot = this.mobStats;
/*     */       }
/*     */       else {
/*     */         
/* 140 */         this.displaySlot.actionPerformed(button);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 150 */     if (this.doesGuiPauseGame) {
/*     */       
/* 152 */       drawDefaultBackground();
/* 153 */       drawCenteredString(fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), (width / 2), (height / 2), 16777215);
/* 154 */       drawCenteredString(fontRendererObj, lanSearchStates[(int)(Minecraft.getSystemTime() / 150L % lanSearchStates.length)], (width / 2), (height / 2 + fontRendererObj.FONT_HEIGHT * 2), 16777215);
/*     */     }
/*     */     else {
/*     */       
/* 158 */       this.displaySlot.drawScreen(mouseX, mouseY, partialTicks);
/* 159 */       drawCenteredString(fontRendererObj, this.screenTitle, (width / 2), 20.0F, 16777215);
/* 160 */       super.drawScreen(mouseX, mouseY, partialTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void doneLoading() {
/* 166 */     if (this.doesGuiPauseGame) {
/*     */       
/* 168 */       func_175366_f();
/* 169 */       createButtons();
/* 170 */       this.displaySlot = this.generalStats;
/* 171 */       this.doesGuiPauseGame = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 180 */     return !this.doesGuiPauseGame;
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawStatsScreen(int p_146521_1_, int p_146521_2_, Item p_146521_3_) {
/* 185 */     drawButtonBackground(p_146521_1_ + 1, p_146521_2_ + 1);
/* 186 */     GlStateManager.enableRescaleNormal();
/* 187 */     RenderHelper.enableGUIStandardItemLighting();
/* 188 */     this.itemRender.func_175042_a(new ItemStack(p_146521_3_, 1, 0), p_146521_1_ + 2, p_146521_2_ + 2);
/* 189 */     RenderHelper.disableStandardItemLighting();
/* 190 */     GlStateManager.disableRescaleNormal();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawButtonBackground(int p_146531_1_, int p_146531_2_) {
/* 198 */     drawSprite(p_146531_1_, p_146531_2_, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSprite(int p_146527_1_, int p_146527_2_, int p_146527_3_, int p_146527_4_) {
/* 206 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 207 */     this.mc.getTextureManager().bindTexture(statIcons);
/* 208 */     float var5 = 0.0078125F;
/* 209 */     float var6 = 0.0078125F;
/* 210 */     boolean var7 = true;
/* 211 */     boolean var8 = true;
/* 212 */     Tessellator var9 = Tessellator.getInstance();
/* 213 */     WorldRenderer var10 = var9.getWorldRenderer();
/* 214 */     var10.startDrawingQuads();
/* 215 */     var10.addVertexWithUV((p_146527_1_ + 0), (p_146527_2_ + 18), zLevel, ((p_146527_3_ + 0) * 0.0078125F), ((p_146527_4_ + 18) * 0.0078125F));
/* 216 */     var10.addVertexWithUV((p_146527_1_ + 18), (p_146527_2_ + 18), zLevel, ((p_146527_3_ + 18) * 0.0078125F), ((p_146527_4_ + 18) * 0.0078125F));
/* 217 */     var10.addVertexWithUV((p_146527_1_ + 18), (p_146527_2_ + 0), zLevel, ((p_146527_3_ + 18) * 0.0078125F), ((p_146527_4_ + 0) * 0.0078125F));
/* 218 */     var10.addVertexWithUV((p_146527_1_ + 0), (p_146527_2_ + 0), zLevel, ((p_146527_3_ + 0) * 0.0078125F), ((p_146527_4_ + 0) * 0.0078125F));
/* 219 */     var9.draw();
/*     */   }
/*     */   
/*     */   abstract class Stats
/*     */     extends GuiSlot {
/* 224 */     protected int field_148218_l = -1;
/*     */     protected List statsHolder;
/*     */     protected Comparator statSorter;
/* 227 */     protected int field_148217_o = -1;
/*     */     
/*     */     protected int field_148215_p;
/*     */     private static final String __OBFID = "CL_00000730";
/*     */     
/*     */     protected Stats(Minecraft mcIn) {
/* 233 */       super(mcIn, GuiStats.width, GuiStats.height, 32, GuiStats.height - 64, 20);
/* 234 */       setShowSelectionBox(false);
/* 235 */       setHasListHeader(true, 20);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 242 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {
/* 247 */       GuiStats.this.drawDefaultBackground();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
/* 252 */       if (!Mouse.isButtonDown(0))
/*     */       {
/* 254 */         this.field_148218_l = -1;
/*     */       }
/*     */       
/* 257 */       if (this.field_148218_l == 0) {
/*     */         
/* 259 */         GuiStats.this.drawSprite(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 0, 0);
/*     */       }
/*     */       else {
/*     */         
/* 263 */         GuiStats.this.drawSprite(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 0, 18);
/*     */       } 
/*     */       
/* 266 */       if (this.field_148218_l == 1) {
/*     */         
/* 268 */         GuiStats.this.drawSprite(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 0, 0);
/*     */       }
/*     */       else {
/*     */         
/* 272 */         GuiStats.this.drawSprite(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 0, 18);
/*     */       } 
/*     */       
/* 275 */       if (this.field_148218_l == 2) {
/*     */         
/* 277 */         GuiStats.this.drawSprite(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 0, 0);
/*     */       }
/*     */       else {
/*     */         
/* 281 */         GuiStats.this.drawSprite(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 0, 18);
/*     */       } 
/*     */       
/* 284 */       if (this.field_148217_o != -1) {
/*     */         
/* 286 */         short var4 = 79;
/* 287 */         byte var5 = 18;
/*     */         
/* 289 */         if (this.field_148217_o == 1) {
/*     */           
/* 291 */           var4 = 129;
/*     */         }
/* 293 */         else if (this.field_148217_o == 2) {
/*     */           
/* 295 */           var4 = 179;
/*     */         } 
/*     */         
/* 298 */         if (this.field_148215_p == 1)
/*     */         {
/* 300 */           var5 = 36;
/*     */         }
/*     */         
/* 303 */         GuiStats.this.drawSprite(p_148129_1_ + var4, p_148129_2_ + 1, var5, 0);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_148132_a(int p_148132_1_, int p_148132_2_) {
/* 309 */       this.field_148218_l = -1;
/*     */       
/* 311 */       if (p_148132_1_ >= 79 && p_148132_1_ < 115) {
/*     */         
/* 313 */         this.field_148218_l = 0;
/*     */       }
/* 315 */       else if (p_148132_1_ >= 129 && p_148132_1_ < 165) {
/*     */         
/* 317 */         this.field_148218_l = 1;
/*     */       }
/* 319 */       else if (p_148132_1_ >= 179 && p_148132_1_ < 215) {
/*     */         
/* 321 */         this.field_148218_l = 2;
/*     */       } 
/*     */       
/* 324 */       if (this.field_148218_l >= 0) {
/*     */         
/* 326 */         func_148212_h(this.field_148218_l);
/* 327 */         this.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected final int getSize() {
/* 333 */       return this.statsHolder.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected final StatCrafting func_148211_c(int p_148211_1_) {
/* 338 */       return this.statsHolder.get(p_148211_1_);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected abstract String func_148210_b(int param1Int);
/*     */ 
/*     */     
/*     */     protected void func_148209_a(StatBase p_148209_1_, int p_148209_2_, int p_148209_3_, boolean p_148209_4_) {
/* 347 */       if (p_148209_1_ != null) {
/*     */         
/* 349 */         String var5 = p_148209_1_.func_75968_a(GuiStats.this.field_146546_t.writeStat(p_148209_1_));
/* 350 */         GuiStats.this.drawString(GuiStats.fontRendererObj, var5, p_148209_2_ - GuiStats.fontRendererObj.getStringWidth(var5), p_148209_3_ + 5, p_148209_4_ ? 16777215 : 9474192);
/*     */       }
/*     */       else {
/*     */         
/* 354 */         String var5 = "-";
/* 355 */         GuiStats.this.drawString(GuiStats.fontRendererObj, var5, p_148209_2_ - GuiStats.fontRendererObj.getStringWidth(var5), p_148209_3_ + 5, p_148209_4_ ? 16777215 : 9474192);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_148142_b(int p_148142_1_, int p_148142_2_) {
/* 361 */       if (p_148142_2_ >= this.top && p_148142_2_ <= this.bottom) {
/*     */         
/* 363 */         int var3 = getSlotIndexFromScreenCoords(p_148142_1_, p_148142_2_);
/* 364 */         int var4 = this.width / 2 - 92 - 16;
/*     */         
/* 366 */         if (var3 >= 0) {
/*     */           
/* 368 */           if (p_148142_1_ < var4 + 40 || p_148142_1_ > var4 + 40 + 20) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 373 */           StatCrafting var5 = func_148211_c(var3);
/* 374 */           func_148213_a(var5, p_148142_1_, p_148142_2_);
/*     */         }
/*     */         else {
/*     */           
/* 378 */           String var9 = "";
/*     */           
/* 380 */           if (p_148142_1_ >= var4 + 115 - 18 && p_148142_1_ <= var4 + 115) {
/*     */             
/* 382 */             var9 = func_148210_b(0);
/*     */           }
/* 384 */           else if (p_148142_1_ >= var4 + 165 - 18 && p_148142_1_ <= var4 + 165) {
/*     */             
/* 386 */             var9 = func_148210_b(1);
/*     */           }
/*     */           else {
/*     */             
/* 390 */             if (p_148142_1_ < var4 + 215 - 18 || p_148142_1_ > var4 + 215) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/* 395 */             var9 = func_148210_b(2);
/*     */           } 
/*     */           
/* 398 */           var9 = I18n.format(var9, new Object[0]).trim();
/*     */           
/* 400 */           if (var9.length() > 0) {
/*     */             
/* 402 */             int var6 = p_148142_1_ + 12;
/* 403 */             int var7 = p_148142_2_ - 12;
/* 404 */             int var8 = GuiStats.fontRendererObj.getStringWidth(var9);
/* 405 */             GuiStats.this.drawGradientRect((var6 - 3), (var7 - 3), (var6 + var8 + 3), (var7 + 8 + 3), -1073741824, -1073741824);
/* 406 */             GuiStats.fontRendererObj.drawStringWithShadow(var9, var6, var7, -1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_148213_a(StatCrafting p_148213_1_, int p_148213_2_, int p_148213_3_) {
/* 414 */       if (p_148213_1_ != null) {
/*     */         
/* 416 */         Item var4 = p_148213_1_.func_150959_a();
/* 417 */         ItemStack var5 = new ItemStack(var4);
/* 418 */         String var6 = var5.getUnlocalizedName();
/* 419 */         String var7 = I18n.format(String.valueOf(var6) + ".name", new Object[0]).trim();
/*     */         
/* 421 */         if (var7.length() > 0) {
/*     */           
/* 423 */           int var8 = p_148213_2_ + 12;
/* 424 */           int var9 = p_148213_3_ - 12;
/* 425 */           int var10 = GuiStats.fontRendererObj.getStringWidth(var7);
/* 426 */           GuiStats.this.drawGradientRect((var8 - 3), (var9 - 3), (var8 + var10 + 3), (var9 + 8 + 3), -1073741824, -1073741824);
/* 427 */           GuiStats.fontRendererObj.drawStringWithShadow(var7, var8, var9, -1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_148212_h(int p_148212_1_) {
/* 434 */       if (p_148212_1_ != this.field_148217_o) {
/*     */         
/* 436 */         this.field_148217_o = p_148212_1_;
/* 437 */         this.field_148215_p = -1;
/*     */       }
/* 439 */       else if (this.field_148215_p == -1) {
/*     */         
/* 441 */         this.field_148215_p = 1;
/*     */       }
/*     */       else {
/*     */         
/* 445 */         this.field_148217_o = -1;
/* 446 */         this.field_148215_p = 0;
/*     */       } 
/*     */       
/* 449 */       Collections.sort(this.statsHolder, this.statSorter);
/*     */     }
/*     */   }
/*     */   
/*     */   class StatsBlock
/*     */     extends Stats
/*     */   {
/*     */     private static final String __OBFID = "CL_00000724";
/*     */     
/*     */     public StatsBlock(Minecraft mcIn) {
/* 459 */       super(mcIn);
/* 460 */       this.statsHolder = Lists.newArrayList();
/* 461 */       Iterator<StatCrafting> var3 = StatList.objectMineStats.iterator();
/*     */       
/* 463 */       while (var3.hasNext()) {
/*     */         
/* 465 */         StatCrafting var4 = var3.next();
/* 466 */         boolean var5 = false;
/* 467 */         int var6 = Item.getIdFromItem(var4.func_150959_a());
/*     */         
/* 469 */         if (GuiStats.this.field_146546_t.writeStat((StatBase)var4) > 0) {
/*     */           
/* 471 */           var5 = true;
/*     */         }
/* 473 */         else if (StatList.objectUseStats[var6] != null && GuiStats.this.field_146546_t.writeStat(StatList.objectUseStats[var6]) > 0) {
/*     */           
/* 475 */           var5 = true;
/*     */         }
/* 477 */         else if (StatList.objectCraftStats[var6] != null && GuiStats.this.field_146546_t.writeStat(StatList.objectCraftStats[var6]) > 0) {
/*     */           
/* 479 */           var5 = true;
/*     */         } 
/*     */         
/* 482 */         if (var5)
/*     */         {
/* 484 */           this.statsHolder.add(var4);
/*     */         }
/*     */       } 
/*     */       
/* 488 */       this.statSorter = new Comparator()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000725";
/*     */           
/*     */           public int compare(StatCrafting p_compare_1_, StatCrafting p_compare_2_) {
/* 493 */             int var3 = Item.getIdFromItem(p_compare_1_.func_150959_a());
/* 494 */             int var4 = Item.getIdFromItem(p_compare_2_.func_150959_a());
/* 495 */             StatBase var5 = null;
/* 496 */             StatBase var6 = null;
/*     */             
/* 498 */             if (GuiStats.StatsBlock.this.field_148217_o == 2) {
/*     */               
/* 500 */               var5 = StatList.mineBlockStatArray[var3];
/* 501 */               var6 = StatList.mineBlockStatArray[var4];
/*     */             }
/* 503 */             else if (GuiStats.StatsBlock.this.field_148217_o == 0) {
/*     */               
/* 505 */               var5 = StatList.objectCraftStats[var3];
/* 506 */               var6 = StatList.objectCraftStats[var4];
/*     */             }
/* 508 */             else if (GuiStats.StatsBlock.this.field_148217_o == 1) {
/*     */               
/* 510 */               var5 = StatList.objectUseStats[var3];
/* 511 */               var6 = StatList.objectUseStats[var4];
/*     */             } 
/*     */             
/* 514 */             if (var5 != null || var6 != null) {
/*     */               
/* 516 */               if (var5 == null)
/*     */               {
/* 518 */                 return 1;
/*     */               }
/*     */               
/* 521 */               if (var6 == null)
/*     */               {
/* 523 */                 return -1;
/*     */               }
/*     */               
/* 526 */               int var7 = (GuiStats.StatsBlock.access$0(GuiStats.StatsBlock.this)).field_146546_t.writeStat(var5);
/* 527 */               int var8 = (GuiStats.StatsBlock.access$0(GuiStats.StatsBlock.this)).field_146546_t.writeStat(var6);
/*     */               
/* 529 */               if (var7 != var8)
/*     */               {
/* 531 */                 return (var7 - var8) * GuiStats.StatsBlock.this.field_148215_p;
/*     */               }
/*     */             } 
/*     */             
/* 535 */             return var3 - var4;
/*     */           }
/*     */           
/*     */           public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 539 */             return compare((StatCrafting)p_compare_1_, (StatCrafting)p_compare_2_);
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
/* 546 */       super.drawListHeader(p_148129_1_, p_148129_2_, p_148129_3_);
/*     */       
/* 548 */       if (this.field_148218_l == 0) {
/*     */         
/* 550 */         GuiStats.this.drawSprite(p_148129_1_ + 115 - 18 + 1, p_148129_2_ + 1 + 1, 18, 18);
/*     */       }
/*     */       else {
/*     */         
/* 554 */         GuiStats.this.drawSprite(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 18, 18);
/*     */       } 
/*     */       
/* 557 */       if (this.field_148218_l == 1) {
/*     */         
/* 559 */         GuiStats.this.drawSprite(p_148129_1_ + 165 - 18 + 1, p_148129_2_ + 1 + 1, 36, 18);
/*     */       }
/*     */       else {
/*     */         
/* 563 */         GuiStats.this.drawSprite(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 36, 18);
/*     */       } 
/*     */       
/* 566 */       if (this.field_148218_l == 2) {
/*     */         
/* 568 */         GuiStats.this.drawSprite(p_148129_1_ + 215 - 18 + 1, p_148129_2_ + 1 + 1, 54, 18);
/*     */       }
/*     */       else {
/*     */         
/* 572 */         GuiStats.this.drawSprite(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 54, 18);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 578 */       StatCrafting var7 = func_148211_c(p_180791_1_);
/* 579 */       Item var8 = var7.func_150959_a();
/* 580 */       GuiStats.this.drawStatsScreen(p_180791_2_ + 40, p_180791_3_, var8);
/* 581 */       int var9 = Item.getIdFromItem(var8);
/* 582 */       func_148209_a(StatList.objectCraftStats[var9], p_180791_2_ + 115, p_180791_3_, (p_180791_1_ % 2 == 0));
/* 583 */       func_148209_a(StatList.objectUseStats[var9], p_180791_2_ + 165, p_180791_3_, (p_180791_1_ % 2 == 0));
/* 584 */       func_148209_a((StatBase)var7, p_180791_2_ + 215, p_180791_3_, (p_180791_1_ % 2 == 0));
/*     */     }
/*     */ 
/*     */     
/*     */     protected String func_148210_b(int p_148210_1_) {
/* 589 */       return (p_148210_1_ == 0) ? "stat.crafted" : ((p_148210_1_ == 1) ? "stat.used" : "stat.mined");
/*     */     }
/*     */   }
/*     */   
/*     */   class StatsGeneral
/*     */     extends GuiSlot
/*     */   {
/*     */     private static final String __OBFID = "CL_00000726";
/*     */     
/*     */     public StatsGeneral(Minecraft mcIn) {
/* 599 */       super(mcIn, GuiStats.width, GuiStats.height, 32, GuiStats.height - 64, 10);
/* 600 */       setShowSelectionBox(false);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 605 */       return StatList.generalStats.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 612 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getContentHeight() {
/* 617 */       return getSize() * 10;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {
/* 622 */       GuiStats.this.drawDefaultBackground();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 627 */       StatBase var7 = StatList.generalStats.get(p_180791_1_);
/* 628 */       GuiStats.this.drawString(GuiStats.fontRendererObj, var7.getStatName().getUnformattedText(), p_180791_2_ + 2, p_180791_3_ + 1, (p_180791_1_ % 2 == 0) ? 16777215 : 9474192);
/* 629 */       String var8 = var7.func_75968_a(GuiStats.this.field_146546_t.writeStat(var7));
/* 630 */       GuiStats.this.drawString(GuiStats.fontRendererObj, var8, p_180791_2_ + 2 + 213 - GuiStats.fontRendererObj.getStringWidth(var8), p_180791_3_ + 1, (p_180791_1_ % 2 == 0) ? 16777215 : 9474192);
/*     */     }
/*     */   }
/*     */   
/*     */   class StatsItem
/*     */     extends Stats
/*     */   {
/*     */     private static final String __OBFID = "CL_00000727";
/*     */     
/*     */     public StatsItem(Minecraft mcIn) {
/* 640 */       super(mcIn);
/* 641 */       this.statsHolder = Lists.newArrayList();
/* 642 */       Iterator<StatCrafting> var3 = StatList.itemStats.iterator();
/*     */       
/* 644 */       while (var3.hasNext()) {
/*     */         
/* 646 */         StatCrafting var4 = var3.next();
/* 647 */         boolean var5 = false;
/* 648 */         int var6 = Item.getIdFromItem(var4.func_150959_a());
/*     */         
/* 650 */         if (GuiStats.this.field_146546_t.writeStat((StatBase)var4) > 0) {
/*     */           
/* 652 */           var5 = true;
/*     */         }
/* 654 */         else if (StatList.objectBreakStats[var6] != null && GuiStats.this.field_146546_t.writeStat(StatList.objectBreakStats[var6]) > 0) {
/*     */           
/* 656 */           var5 = true;
/*     */         }
/* 658 */         else if (StatList.objectCraftStats[var6] != null && GuiStats.this.field_146546_t.writeStat(StatList.objectCraftStats[var6]) > 0) {
/*     */           
/* 660 */           var5 = true;
/*     */         } 
/*     */         
/* 663 */         if (var5)
/*     */         {
/* 665 */           this.statsHolder.add(var4);
/*     */         }
/*     */       } 
/*     */       
/* 669 */       this.statSorter = new Comparator()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000728";
/*     */           
/*     */           public int compare(StatCrafting p_compare_1_, StatCrafting p_compare_2_) {
/* 674 */             int var3 = Item.getIdFromItem(p_compare_1_.func_150959_a());
/* 675 */             int var4 = Item.getIdFromItem(p_compare_2_.func_150959_a());
/* 676 */             StatBase var5 = null;
/* 677 */             StatBase var6 = null;
/*     */             
/* 679 */             if (GuiStats.StatsItem.this.field_148217_o == 0) {
/*     */               
/* 681 */               var5 = StatList.objectBreakStats[var3];
/* 682 */               var6 = StatList.objectBreakStats[var4];
/*     */             }
/* 684 */             else if (GuiStats.StatsItem.this.field_148217_o == 1) {
/*     */               
/* 686 */               var5 = StatList.objectCraftStats[var3];
/* 687 */               var6 = StatList.objectCraftStats[var4];
/*     */             }
/* 689 */             else if (GuiStats.StatsItem.this.field_148217_o == 2) {
/*     */               
/* 691 */               var5 = StatList.objectUseStats[var3];
/* 692 */               var6 = StatList.objectUseStats[var4];
/*     */             } 
/*     */             
/* 695 */             if (var5 != null || var6 != null) {
/*     */               
/* 697 */               if (var5 == null)
/*     */               {
/* 699 */                 return 1;
/*     */               }
/*     */               
/* 702 */               if (var6 == null)
/*     */               {
/* 704 */                 return -1;
/*     */               }
/*     */               
/* 707 */               int var7 = (GuiStats.StatsItem.access$0(GuiStats.StatsItem.this)).field_146546_t.writeStat(var5);
/* 708 */               int var8 = (GuiStats.StatsItem.access$0(GuiStats.StatsItem.this)).field_146546_t.writeStat(var6);
/*     */               
/* 710 */               if (var7 != var8)
/*     */               {
/* 712 */                 return (var7 - var8) * GuiStats.StatsItem.this.field_148215_p;
/*     */               }
/*     */             } 
/*     */             
/* 716 */             return var3 - var4;
/*     */           }
/*     */           
/*     */           public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 720 */             return compare((StatCrafting)p_compare_1_, (StatCrafting)p_compare_2_);
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
/* 727 */       super.drawListHeader(p_148129_1_, p_148129_2_, p_148129_3_);
/*     */       
/* 729 */       if (this.field_148218_l == 0) {
/*     */         
/* 731 */         GuiStats.this.drawSprite(p_148129_1_ + 115 - 18 + 1, p_148129_2_ + 1 + 1, 72, 18);
/*     */       }
/*     */       else {
/*     */         
/* 735 */         GuiStats.this.drawSprite(p_148129_1_ + 115 - 18, p_148129_2_ + 1, 72, 18);
/*     */       } 
/*     */       
/* 738 */       if (this.field_148218_l == 1) {
/*     */         
/* 740 */         GuiStats.this.drawSprite(p_148129_1_ + 165 - 18 + 1, p_148129_2_ + 1 + 1, 18, 18);
/*     */       }
/*     */       else {
/*     */         
/* 744 */         GuiStats.this.drawSprite(p_148129_1_ + 165 - 18, p_148129_2_ + 1, 18, 18);
/*     */       } 
/*     */       
/* 747 */       if (this.field_148218_l == 2) {
/*     */         
/* 749 */         GuiStats.this.drawSprite(p_148129_1_ + 215 - 18 + 1, p_148129_2_ + 1 + 1, 36, 18);
/*     */       }
/*     */       else {
/*     */         
/* 753 */         GuiStats.this.drawSprite(p_148129_1_ + 215 - 18, p_148129_2_ + 1, 36, 18);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 759 */       StatCrafting var7 = func_148211_c(p_180791_1_);
/* 760 */       Item var8 = var7.func_150959_a();
/* 761 */       GuiStats.this.drawStatsScreen(p_180791_2_ + 40, p_180791_3_, var8);
/* 762 */       int var9 = Item.getIdFromItem(var8);
/* 763 */       func_148209_a(StatList.objectBreakStats[var9], p_180791_2_ + 115, p_180791_3_, (p_180791_1_ % 2 == 0));
/* 764 */       func_148209_a(StatList.objectCraftStats[var9], p_180791_2_ + 165, p_180791_3_, (p_180791_1_ % 2 == 0));
/* 765 */       func_148209_a((StatBase)var7, p_180791_2_ + 215, p_180791_3_, (p_180791_1_ % 2 == 0));
/*     */     }
/*     */ 
/*     */     
/*     */     protected String func_148210_b(int p_148210_1_) {
/* 770 */       return (p_148210_1_ == 1) ? "stat.crafted" : ((p_148210_1_ == 2) ? "stat.used" : "stat.depleted");
/*     */     }
/*     */   }
/*     */   
/*     */   class StatsMobsList
/*     */     extends GuiSlot {
/* 776 */     private final List field_148222_l = Lists.newArrayList();
/*     */     
/*     */     private static final String __OBFID = "CL_00000729";
/*     */     
/*     */     public StatsMobsList(Minecraft mcIn) {
/* 781 */       super(mcIn, GuiStats.width, GuiStats.height, 32, GuiStats.height - 64, GuiStats.fontRendererObj.FONT_HEIGHT * 4);
/* 782 */       setShowSelectionBox(false);
/* 783 */       Iterator<EntityList.EntityEggInfo> var3 = EntityList.entityEggs.values().iterator();
/*     */       
/* 785 */       while (var3.hasNext()) {
/*     */         
/* 787 */         EntityList.EntityEggInfo var4 = var3.next();
/*     */         
/* 789 */         if (GuiStats.this.field_146546_t.writeStat(var4.field_151512_d) > 0 || GuiStats.this.field_146546_t.writeStat(var4.field_151513_e) > 0)
/*     */         {
/* 791 */           this.field_148222_l.add(var4);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 798 */       return this.field_148222_l.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 805 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getContentHeight() {
/* 810 */       return getSize() * GuiStats.fontRendererObj.FONT_HEIGHT * 4;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {
/* 815 */       GuiStats.this.drawDefaultBackground();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 820 */       EntityList.EntityEggInfo var7 = this.field_148222_l.get(p_180791_1_);
/* 821 */       String var8 = I18n.format("entity." + EntityList.getStringFromID(var7.spawnedID) + ".name", new Object[0]);
/* 822 */       int var9 = GuiStats.this.field_146546_t.writeStat(var7.field_151512_d);
/* 823 */       int var10 = GuiStats.this.field_146546_t.writeStat(var7.field_151513_e);
/* 824 */       String var11 = I18n.format("stat.entityKills", new Object[] { Integer.valueOf(var9), var8 });
/* 825 */       String var12 = I18n.format("stat.entityKilledBy", new Object[] { var8, Integer.valueOf(var10) });
/*     */       
/* 827 */       if (var9 == 0)
/*     */       {
/* 829 */         var11 = I18n.format("stat.entityKills.none", new Object[] { var8 });
/*     */       }
/*     */       
/* 832 */       if (var10 == 0)
/*     */       {
/* 834 */         var12 = I18n.format("stat.entityKilledBy.none", new Object[] { var8 });
/*     */       }
/*     */       
/* 837 */       GuiStats.this.drawString(GuiStats.fontRendererObj, var8, p_180791_2_ + 2 - 10, p_180791_3_ + 1, 16777215);
/* 838 */       GuiStats.this.drawString(GuiStats.fontRendererObj, var11, p_180791_2_ + 2, p_180791_3_ + 1 + GuiStats.fontRendererObj.FONT_HEIGHT, (var9 == 0) ? 6316128 : 9474192);
/* 839 */       GuiStats.this.drawString(GuiStats.fontRendererObj, var12, p_180791_2_ + 2, p_180791_3_ + 1 + GuiStats.fontRendererObj.FONT_HEIGHT * 2, (var10 == 0) ? 6316128 : 9474192);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\achievement\GuiStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */