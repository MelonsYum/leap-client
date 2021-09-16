/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import net.minecraft.client.AnvilConverterException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.storage.ISaveFormat;
/*     */ import net.minecraft.world.storage.ISaveHandler;
/*     */ import net.minecraft.world.storage.SaveFormatComparator;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GuiSelectWorld
/*     */   extends GuiScreen implements GuiYesNoCallback {
/*  23 */   private static final Logger logger = LogManager.getLogger();
/*  24 */   private final DateFormat field_146633_h = new SimpleDateFormat();
/*     */   protected GuiScreen field_146632_a;
/*  26 */   protected String field_146628_f = "Select world";
/*     */   private boolean field_146634_i;
/*     */   private int field_146640_r;
/*     */   private java.util.List field_146639_s;
/*     */   private List field_146638_t;
/*     */   private String field_146637_u;
/*     */   private String field_146636_v;
/*  33 */   private String[] field_146635_w = new String[4];
/*     */   
/*     */   private boolean field_146643_x;
/*     */   private GuiButton field_146642_y;
/*     */   private GuiButton field_146641_z;
/*     */   private GuiButton field_146630_A;
/*     */   private GuiButton field_146631_B;
/*     */   private static final String __OBFID = "CL_00000711";
/*     */   
/*     */   public GuiSelectWorld(GuiScreen p_i1054_1_) {
/*  43 */     this.field_146632_a = p_i1054_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  51 */     this.field_146628_f = I18n.format("selectWorld.title", new Object[0]);
/*     */ 
/*     */     
/*     */     try {
/*  55 */       func_146627_h();
/*     */     }
/*  57 */     catch (AnvilConverterException var2) {
/*     */       
/*  59 */       logger.error("Couldn't load level list", (Throwable)var2);
/*  60 */       this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load worlds", var2.getMessage()));
/*     */       
/*     */       return;
/*     */     } 
/*  64 */     this.field_146637_u = I18n.format("selectWorld.world", new Object[0]);
/*  65 */     this.field_146636_v = I18n.format("selectWorld.conversion", new Object[0]);
/*  66 */     this.field_146635_w[WorldSettings.GameType.SURVIVAL.getID()] = I18n.format("gameMode.survival", new Object[0]);
/*  67 */     this.field_146635_w[WorldSettings.GameType.CREATIVE.getID()] = I18n.format("gameMode.creative", new Object[0]);
/*  68 */     this.field_146635_w[WorldSettings.GameType.ADVENTURE.getID()] = I18n.format("gameMode.adventure", new Object[0]);
/*  69 */     this.field_146635_w[WorldSettings.GameType.SPECTATOR.getID()] = I18n.format("gameMode.spectator", new Object[0]);
/*  70 */     this.field_146638_t = new List(this.mc);
/*  71 */     this.field_146638_t.registerScrollButtons(4, 5);
/*  72 */     func_146618_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  80 */     super.handleMouseInput();
/*  81 */     this.field_146638_t.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146627_h() throws AnvilConverterException {
/*  86 */     ISaveFormat var1 = this.mc.getSaveLoader();
/*  87 */     this.field_146639_s = var1.getSaveList();
/*  88 */     Collections.sort(this.field_146639_s);
/*  89 */     this.field_146640_r = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String func_146621_a(int p_146621_1_) {
/*  94 */     return ((SaveFormatComparator)this.field_146639_s.get(p_146621_1_)).getFileName();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String func_146614_d(int p_146614_1_) {
/*  99 */     String var2 = ((SaveFormatComparator)this.field_146639_s.get(p_146614_1_)).getDisplayName();
/*     */     
/* 101 */     if (StringUtils.isEmpty(var2))
/*     */     {
/* 103 */       var2 = String.valueOf(I18n.format("selectWorld.world", new Object[0])) + " " + (p_146614_1_ + 1);
/*     */     }
/*     */     
/* 106 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146618_g() {
/* 111 */     this.buttonList.add(this.field_146641_z = new GuiButton(1, width / 2 - 154, height - 52, 150, 20, I18n.format("selectWorld.select", new Object[0])));
/* 112 */     this.buttonList.add(new GuiButton(3, width / 2 + 4, height - 52, 150, 20, I18n.format("selectWorld.create", new Object[0])));
/* 113 */     this.buttonList.add(this.field_146630_A = new GuiButton(6, width / 2 - 154, height - 28, 72, 20, I18n.format("selectWorld.rename", new Object[0])));
/* 114 */     this.buttonList.add(this.field_146642_y = new GuiButton(2, width / 2 - 76, height - 28, 72, 20, I18n.format("selectWorld.delete", new Object[0])));
/* 115 */     this.buttonList.add(this.field_146631_B = new GuiButton(7, width / 2 + 4, height - 28, 72, 20, I18n.format("selectWorld.recreate", new Object[0])));
/* 116 */     this.buttonList.add(new GuiButton(0, width / 2 + 82, height - 28, 72, 20, I18n.format("gui.cancel", new Object[0])));
/* 117 */     this.field_146641_z.enabled = false;
/* 118 */     this.field_146642_y.enabled = false;
/* 119 */     this.field_146630_A.enabled = false;
/* 120 */     this.field_146631_B.enabled = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 125 */     if (button.enabled)
/*     */     {
/* 127 */       if (button.id == 2) {
/*     */         
/* 129 */         String var2 = func_146614_d(this.field_146640_r);
/*     */         
/* 131 */         if (var2 != null)
/*     */         {
/* 133 */           this.field_146643_x = true;
/* 134 */           GuiYesNo var3 = func_152129_a(this, var2, this.field_146640_r);
/* 135 */           this.mc.displayGuiScreen(var3);
/*     */         }
/*     */       
/* 138 */       } else if (button.id == 1) {
/*     */         
/* 140 */         func_146615_e(this.field_146640_r);
/*     */       }
/* 142 */       else if (button.id == 3) {
/*     */         
/* 144 */         this.mc.displayGuiScreen(new GuiCreateWorld(this));
/*     */       }
/* 146 */       else if (button.id == 6) {
/*     */         
/* 148 */         this.mc.displayGuiScreen(new GuiRenameWorld(this, func_146621_a(this.field_146640_r)));
/*     */       }
/* 150 */       else if (button.id == 0) {
/*     */         
/* 152 */         this.mc.displayGuiScreen(this.field_146632_a);
/*     */       }
/* 154 */       else if (button.id == 7) {
/*     */         
/* 156 */         GuiCreateWorld var5 = new GuiCreateWorld(this);
/* 157 */         ISaveHandler var6 = this.mc.getSaveLoader().getSaveLoader(func_146621_a(this.field_146640_r), false);
/* 158 */         WorldInfo var4 = var6.loadWorldInfo();
/* 159 */         var6.flush();
/* 160 */         var5.func_146318_a(var4);
/* 161 */         this.mc.displayGuiScreen(var5);
/*     */       }
/*     */       else {
/*     */         
/* 165 */         this.field_146638_t.actionPerformed(button);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146615_e(int p_146615_1_) {
/* 172 */     this.mc.displayGuiScreen(null);
/*     */     
/* 174 */     if (!this.field_146634_i) {
/*     */       
/* 176 */       this.field_146634_i = true;
/* 177 */       String var2 = func_146621_a(p_146615_1_);
/*     */       
/* 179 */       if (var2 == null)
/*     */       {
/* 181 */         var2 = "World" + p_146615_1_;
/*     */       }
/*     */       
/* 184 */       String var3 = func_146614_d(p_146615_1_);
/*     */       
/* 186 */       if (var3 == null)
/*     */       {
/* 188 */         var3 = "World" + p_146615_1_;
/*     */       }
/*     */       
/* 191 */       if (this.mc.getSaveLoader().canLoadWorld(var2))
/*     */       {
/* 193 */         this.mc.launchIntegratedServer(var2, var3, null);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/* 200 */     if (this.field_146643_x) {
/*     */       
/* 202 */       this.field_146643_x = false;
/*     */       
/* 204 */       if (result) {
/*     */         
/* 206 */         ISaveFormat var3 = this.mc.getSaveLoader();
/* 207 */         var3.flushCache();
/* 208 */         var3.deleteWorldDirectory(func_146621_a(id));
/*     */ 
/*     */         
/*     */         try {
/* 212 */           func_146627_h();
/*     */         }
/* 214 */         catch (AnvilConverterException var5) {
/*     */           
/* 216 */           logger.error("Couldn't load level list", (Throwable)var5);
/*     */         } 
/*     */       } 
/*     */       
/* 220 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 229 */     this.field_146638_t.drawScreen(mouseX, mouseY, partialTicks);
/* 230 */     drawCenteredString(fontRendererObj, this.field_146628_f, (width / 2), 20.0F, 16777215);
/* 231 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public static GuiYesNo func_152129_a(GuiYesNoCallback p_152129_0_, String p_152129_1_, int p_152129_2_) {
/* 236 */     String var3 = I18n.format("selectWorld.deleteQuestion", new Object[0]);
/* 237 */     String var4 = "'" + p_152129_1_ + "' " + I18n.format("selectWorld.deleteWarning", new Object[0]);
/* 238 */     String var5 = I18n.format("selectWorld.deleteButton", new Object[0]);
/* 239 */     String var6 = I18n.format("gui.cancel", new Object[0]);
/* 240 */     GuiYesNo var7 = new GuiYesNo(p_152129_0_, var3, var4, var5, var6, p_152129_2_);
/* 241 */     return var7;
/*     */   }
/*     */   
/*     */   class List
/*     */     extends GuiSlot
/*     */   {
/*     */     private static final String __OBFID = "CL_00000712";
/*     */     
/*     */     public List(Minecraft mcIn) {
/* 250 */       super(mcIn, GuiSelectWorld.width, GuiSelectWorld.height, 32, GuiSelectWorld.height - 64, 36);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 255 */       return GuiSelectWorld.this.field_146639_s.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/* 260 */       GuiSelectWorld.this.field_146640_r = slotIndex;
/* 261 */       boolean var5 = (GuiSelectWorld.this.field_146640_r >= 0 && GuiSelectWorld.this.field_146640_r < getSize());
/* 262 */       GuiSelectWorld.this.field_146641_z.enabled = var5;
/* 263 */       GuiSelectWorld.this.field_146642_y.enabled = var5;
/* 264 */       GuiSelectWorld.this.field_146630_A.enabled = var5;
/* 265 */       GuiSelectWorld.this.field_146631_B.enabled = var5;
/*     */       
/* 267 */       if (isDoubleClick && var5)
/*     */       {
/* 269 */         GuiSelectWorld.this.func_146615_e(slotIndex);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 275 */       return (slotIndex == GuiSelectWorld.this.field_146640_r);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getContentHeight() {
/* 280 */       return GuiSelectWorld.this.field_146639_s.size() * 36;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {
/* 285 */       GuiSelectWorld.this.drawDefaultBackground();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 290 */       SaveFormatComparator var7 = GuiSelectWorld.this.field_146639_s.get(p_180791_1_);
/* 291 */       String var8 = var7.getDisplayName();
/*     */       
/* 293 */       if (StringUtils.isEmpty(var8))
/*     */       {
/* 295 */         var8 = String.valueOf(GuiSelectWorld.this.field_146637_u) + " " + (p_180791_1_ + 1);
/*     */       }
/*     */       
/* 298 */       String var9 = var7.getFileName();
/* 299 */       var9 = String.valueOf(var9) + " (" + GuiSelectWorld.this.field_146633_h.format(new Date(var7.getLastTimePlayed()));
/* 300 */       var9 = String.valueOf(var9) + ")";
/* 301 */       String var10 = "";
/*     */       
/* 303 */       if (var7.requiresConversion()) {
/*     */         
/* 305 */         var10 = String.valueOf(GuiSelectWorld.this.field_146636_v) + " " + var10;
/*     */       }
/*     */       else {
/*     */         
/* 309 */         var10 = GuiSelectWorld.this.field_146635_w[var7.getEnumGameType().getID()];
/*     */         
/* 311 */         if (var7.isHardcoreModeEnabled())
/*     */         {
/* 313 */           var10 = EnumChatFormatting.DARK_RED + I18n.format("gameMode.hardcore", new Object[0]) + EnumChatFormatting.RESET;
/*     */         }
/*     */         
/* 316 */         if (var7.getCheatsEnabled())
/*     */         {
/* 318 */           var10 = String.valueOf(var10) + ", " + I18n.format("selectWorld.cheats", new Object[0]);
/*     */         }
/*     */       } 
/*     */       
/* 322 */       GuiSelectWorld.this.drawString(GuiSelectWorld.fontRendererObj, var8, p_180791_2_ + 2, p_180791_3_ + 1, 16777215);
/* 323 */       GuiSelectWorld.this.drawString(GuiSelectWorld.fontRendererObj, var9, p_180791_2_ + 2, p_180791_3_ + 12, 8421504);
/* 324 */       GuiSelectWorld.this.drawString(GuiSelectWorld.fontRendererObj, var10, p_180791_2_ + 2, p_180791_3_ + 12 + 10, 8421504);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSelectWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */