/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.IntHashMap;
/*     */ 
/*     */ public class GuiPageButtonList
/*     */   extends GuiListExtended {
/*  13 */   private final List field_178074_u = Lists.newArrayList();
/*  14 */   private final IntHashMap field_178073_v = new IntHashMap();
/*  15 */   private final List field_178072_w = Lists.newArrayList();
/*     */   
/*     */   private final GuiListEntry[][] field_178078_x;
/*     */   private int field_178077_y;
/*     */   private GuiResponder field_178076_z;
/*     */   private Gui field_178075_A;
/*     */   private static final String __OBFID = "CL_00001950";
/*     */   
/*     */   public GuiPageButtonList(Minecraft mcIn, int p_i45536_2_, int p_i45536_3_, int p_i45536_4_, int p_i45536_5_, int p_i45536_6_, GuiResponder p_i45536_7_, GuiListEntry[]... p_i45536_8_) {
/*  24 */     super(mcIn, p_i45536_2_, p_i45536_3_, p_i45536_4_, p_i45536_5_, p_i45536_6_);
/*  25 */     this.field_178076_z = p_i45536_7_;
/*  26 */     this.field_178078_x = p_i45536_8_;
/*  27 */     this.field_148163_i = false;
/*  28 */     func_178069_s();
/*  29 */     func_178055_t();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178069_s() {
/*  34 */     GuiListEntry[][] var1 = this.field_178078_x;
/*  35 */     int var2 = var1.length;
/*     */     
/*  37 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  39 */       GuiListEntry[] var4 = var1[var3];
/*     */       
/*  41 */       for (int var5 = 0; var5 < var4.length; var5 += 2) {
/*     */         
/*  43 */         GuiListEntry var6 = var4[var5];
/*  44 */         GuiListEntry var7 = (var5 < var4.length - 1) ? var4[var5 + 1] : null;
/*  45 */         Gui var8 = func_178058_a(var6, 0, (var7 == null));
/*  46 */         Gui var9 = func_178058_a(var7, 160, (var6 == null));
/*  47 */         GuiEntry var10 = new GuiEntry(var8, var9);
/*  48 */         this.field_178074_u.add(var10);
/*     */         
/*  50 */         if (var6 != null && var8 != null) {
/*     */           
/*  52 */           this.field_178073_v.addKey(var6.func_178935_b(), var8);
/*     */           
/*  54 */           if (var8 instanceof GuiTextField)
/*     */           {
/*  56 */             this.field_178072_w.add((GuiTextField)var8);
/*     */           }
/*     */         } 
/*     */         
/*  60 */         if (var7 != null && var9 != null) {
/*     */           
/*  62 */           this.field_178073_v.addKey(var7.func_178935_b(), var9);
/*     */           
/*  64 */           if (var9 instanceof GuiTextField)
/*     */           {
/*  66 */             this.field_178072_w.add((GuiTextField)var9);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178055_t() {
/*  75 */     this.field_178074_u.clear();
/*     */     
/*  77 */     for (int var1 = 0; var1 < (this.field_178078_x[this.field_178077_y]).length; var1 += 2) {
/*     */       
/*  79 */       GuiListEntry var2 = this.field_178078_x[this.field_178077_y][var1];
/*  80 */       GuiListEntry var3 = (var1 < (this.field_178078_x[this.field_178077_y]).length - 1) ? this.field_178078_x[this.field_178077_y][var1 + 1] : null;
/*  81 */       Gui var4 = (Gui)this.field_178073_v.lookup(var2.func_178935_b());
/*  82 */       Gui var5 = (var3 != null) ? (Gui)this.field_178073_v.lookup(var3.func_178935_b()) : null;
/*  83 */       GuiEntry var6 = new GuiEntry(var4, var5);
/*  84 */       this.field_178074_u.add(var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178059_e() {
/*  90 */     return this.field_178077_y;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178057_f() {
/*  95 */     return this.field_178078_x.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public Gui func_178056_g() {
/* 100 */     return this.field_178075_A;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178071_h() {
/* 105 */     if (this.field_178077_y > 0) {
/*     */       
/* 107 */       int var1 = this.field_178077_y--;
/* 108 */       func_178055_t();
/* 109 */       func_178060_e(var1, this.field_178077_y);
/* 110 */       this.amountScrolled = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178064_i() {
/* 116 */     if (this.field_178077_y < this.field_178078_x.length - 1) {
/*     */       
/* 118 */       int var1 = this.field_178077_y++;
/* 119 */       func_178055_t();
/* 120 */       func_178060_e(var1, this.field_178077_y);
/* 121 */       this.amountScrolled = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Gui func_178061_c(int p_178061_1_) {
/* 127 */     return (Gui)this.field_178073_v.lookup(p_178061_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178060_e(int p_178060_1_, int p_178060_2_) {
/* 132 */     GuiListEntry[] var3 = this.field_178078_x[p_178060_1_];
/* 133 */     int var4 = var3.length;
/*     */     
/*     */     int var5;
/*     */     
/* 137 */     for (var5 = 0; var5 < var4; var5++) {
/*     */       
/* 139 */       GuiListEntry var6 = var3[var5];
/*     */       
/* 141 */       if (var6 != null)
/*     */       {
/* 143 */         func_178066_a((Gui)this.field_178073_v.lookup(var6.func_178935_b()), false);
/*     */       }
/*     */     } 
/*     */     
/* 147 */     var3 = this.field_178078_x[p_178060_2_];
/* 148 */     var4 = var3.length;
/*     */     
/* 150 */     for (var5 = 0; var5 < var4; var5++) {
/*     */       
/* 152 */       GuiListEntry var6 = var3[var5];
/*     */       
/* 154 */       if (var6 != null)
/*     */       {
/* 156 */         func_178066_a((Gui)this.field_178073_v.lookup(var6.func_178935_b()), true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178066_a(Gui p_178066_1_, boolean p_178066_2_) {
/* 163 */     if (p_178066_1_ instanceof GuiButton) {
/*     */       
/* 165 */       ((GuiButton)p_178066_1_).visible = p_178066_2_;
/*     */     }
/* 167 */     else if (p_178066_1_ instanceof GuiTextField) {
/*     */       
/* 169 */       ((GuiTextField)p_178066_1_).setVisible(p_178066_2_);
/*     */     }
/* 171 */     else if (p_178066_1_ instanceof GuiLabel) {
/*     */       
/* 173 */       ((GuiLabel)p_178066_1_).visible = p_178066_2_;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Gui func_178058_a(GuiListEntry p_178058_1_, int p_178058_2_, boolean p_178058_3_) {
/* 179 */     return (p_178058_1_ instanceof GuiSlideEntry) ? func_178067_a(this.width / 2 - 155 + p_178058_2_, 0, (GuiSlideEntry)p_178058_1_) : ((p_178058_1_ instanceof GuiButtonEntry) ? func_178065_a(this.width / 2 - 155 + p_178058_2_, 0, (GuiButtonEntry)p_178058_1_) : ((p_178058_1_ instanceof EditBoxEntry) ? func_178068_a(this.width / 2 - 155 + p_178058_2_, 0, (EditBoxEntry)p_178058_1_) : ((p_178058_1_ instanceof GuiLabelEntry) ? func_178063_a(this.width / 2 - 155 + p_178058_2_, 0, (GuiLabelEntry)p_178058_1_, p_178058_3_) : null)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_148179_a(int p_148179_1_, int p_148179_2_, int p_148179_3_) {
/* 184 */     boolean var4 = super.func_148179_a(p_148179_1_, p_148179_2_, p_148179_3_);
/* 185 */     int var5 = getSlotIndexFromScreenCoords(p_148179_1_, p_148179_2_);
/*     */     
/* 187 */     if (var5 >= 0) {
/*     */       
/* 189 */       GuiEntry var6 = func_178070_d(var5);
/*     */       
/* 191 */       if (this.field_178075_A != var6.field_178028_d && this.field_178075_A != null && this.field_178075_A instanceof GuiTextField)
/*     */       {
/* 193 */         ((GuiTextField)this.field_178075_A).setFocused(false);
/*     */       }
/*     */       
/* 196 */       this.field_178075_A = var6.field_178028_d;
/*     */     } 
/*     */     
/* 199 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiSlider func_178067_a(int p_178067_1_, int p_178067_2_, GuiSlideEntry p_178067_3_) {
/* 204 */     GuiSlider var4 = new GuiSlider(this.field_178076_z, p_178067_3_.func_178935_b(), p_178067_1_, p_178067_2_, p_178067_3_.func_178936_c(), p_178067_3_.func_178943_e(), p_178067_3_.func_178944_f(), p_178067_3_.func_178942_g(), p_178067_3_.func_178945_a());
/* 205 */     var4.visible = p_178067_3_.func_178934_d();
/* 206 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiListButton func_178065_a(int p_178065_1_, int p_178065_2_, GuiButtonEntry p_178065_3_) {
/* 211 */     GuiListButton var4 = new GuiListButton(this.field_178076_z, p_178065_3_.func_178935_b(), p_178065_1_, p_178065_2_, p_178065_3_.func_178936_c(), p_178065_3_.func_178940_a());
/* 212 */     var4.visible = p_178065_3_.func_178934_d();
/* 213 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiTextField func_178068_a(int p_178068_1_, int p_178068_2_, EditBoxEntry p_178068_3_) {
/* 218 */     GuiTextField var4 = new GuiTextField(p_178068_3_.func_178935_b(), this.mc.fontRendererObj, p_178068_1_, p_178068_2_, 150, 20);
/* 219 */     var4.setText(p_178068_3_.func_178936_c());
/* 220 */     var4.func_175207_a(this.field_178076_z);
/* 221 */     var4.setVisible(p_178068_3_.func_178934_d());
/* 222 */     var4.func_175205_a(p_178068_3_.func_178950_a());
/* 223 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private GuiLabel func_178063_a(int p_178063_1_, int p_178063_2_, GuiLabelEntry p_178063_3_, boolean p_178063_4_) {
/*     */     GuiLabel var5;
/* 230 */     if (p_178063_4_) {
/*     */       
/* 232 */       var5 = new GuiLabel(this.mc.fontRendererObj, p_178063_3_.func_178935_b(), p_178063_1_, p_178063_2_, this.width - p_178063_1_ * 2, 20, -1);
/*     */     }
/*     */     else {
/*     */       
/* 236 */       var5 = new GuiLabel(this.mc.fontRendererObj, p_178063_3_.func_178935_b(), p_178063_1_, p_178063_2_, 150, 20, -1);
/*     */     } 
/*     */     
/* 239 */     var5.visible = p_178063_3_.func_178934_d();
/* 240 */     var5.func_175202_a(p_178063_3_.func_178936_c());
/* 241 */     var5.func_175203_a();
/* 242 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178062_a(char p_178062_1_, int p_178062_2_) {
/* 247 */     if (this.field_178075_A instanceof GuiTextField) {
/*     */       
/* 249 */       GuiTextField var3 = (GuiTextField)this.field_178075_A;
/*     */ 
/*     */       
/* 252 */       if (!GuiScreen.func_175279_e(p_178062_2_)) {
/*     */         
/* 254 */         if (p_178062_2_ == 15) {
/*     */           
/* 256 */           var3.setFocused(false);
/* 257 */           int var12 = this.field_178072_w.indexOf(this.field_178075_A);
/*     */           
/* 259 */           if (GuiScreen.isShiftKeyDown()) {
/*     */             
/* 261 */             if (var12 == 0)
/*     */             {
/* 263 */               var12 = this.field_178072_w.size() - 1;
/*     */             }
/*     */             else
/*     */             {
/* 267 */               var12--;
/*     */             }
/*     */           
/* 270 */           } else if (var12 == this.field_178072_w.size() - 1) {
/*     */             
/* 272 */             var12 = 0;
/*     */           }
/*     */           else {
/*     */             
/* 276 */             var12++;
/*     */           } 
/*     */           
/* 279 */           this.field_178075_A = this.field_178072_w.get(var12);
/* 280 */           var3 = (GuiTextField)this.field_178075_A;
/* 281 */           var3.setFocused(true);
/* 282 */           int var13 = var3.yPosition + this.slotHeight;
/* 283 */           int var6 = var3.yPosition;
/*     */           
/* 285 */           if (var13 > this.bottom)
/*     */           {
/* 287 */             this.amountScrolled += (var13 - this.bottom);
/*     */           }
/* 289 */           else if (var6 < this.top)
/*     */           {
/* 291 */             this.amountScrolled = var6;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 296 */           var3.textboxKeyTyped(p_178062_1_, p_178062_2_);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 301 */         String var4 = GuiScreen.getClipboardString();
/* 302 */         String[] var5 = var4.split(";");
/* 303 */         int var6 = this.field_178072_w.indexOf(this.field_178075_A);
/* 304 */         int var7 = var6;
/* 305 */         String[] var8 = var5;
/* 306 */         int var9 = var5.length;
/*     */         
/* 308 */         for (int var10 = 0; var10 < var9; var10++) {
/*     */           
/* 310 */           String var11 = var8[var10];
/* 311 */           ((GuiTextField)this.field_178072_w.get(var7)).setText(var11);
/*     */           
/* 313 */           if (var7 == this.field_178072_w.size() - 1) {
/*     */             
/* 315 */             var7 = 0;
/*     */           }
/*     */           else {
/*     */             
/* 319 */             var7++;
/*     */           } 
/*     */           
/* 322 */           if (var7 == var6) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiEntry func_178070_d(int p_178070_1_) {
/* 333 */     return this.field_178074_u.get(p_178070_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 338 */     return this.field_178074_u.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListWidth() {
/* 346 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/* 351 */     return super.getScrollBarX() + 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiListExtended.IGuiListEntry getListEntry(int p_148180_1_) {
/* 359 */     return func_178070_d(p_148180_1_);
/*     */   }
/*     */   
/*     */   public static class EditBoxEntry
/*     */     extends GuiListEntry
/*     */   {
/*     */     private final Predicate field_178951_a;
/*     */     private static final String __OBFID = "CL_00001948";
/*     */     
/*     */     public EditBoxEntry(int p_i45534_1_, String p_i45534_2_, boolean p_i45534_3_, Predicate p_i45534_4_) {
/* 369 */       super(p_i45534_1_, p_i45534_2_, p_i45534_3_);
/* 370 */       this.field_178951_a = (Predicate)Objects.firstNonNull(p_i45534_4_, Predicates.alwaysTrue());
/*     */     }
/*     */ 
/*     */     
/*     */     public Predicate func_178950_a() {
/* 375 */       return this.field_178951_a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiButtonEntry
/*     */     extends GuiListEntry
/*     */   {
/*     */     private final boolean field_178941_a;
/*     */     private static final String __OBFID = "CL_00001949";
/*     */     
/*     */     public GuiButtonEntry(int p_i45535_1_, String p_i45535_2_, boolean p_i45535_3_, boolean p_i45535_4_) {
/* 386 */       super(p_i45535_1_, p_i45535_2_, p_i45535_3_);
/* 387 */       this.field_178941_a = p_i45535_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_178940_a() {
/* 392 */       return this.field_178941_a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiEntry
/*     */     implements GuiListExtended.IGuiListEntry {
/* 398 */     private final Minecraft field_178031_a = Minecraft.getMinecraft();
/*     */     
/*     */     private final Gui field_178029_b;
/*     */     private final Gui field_178030_c;
/*     */     private Gui field_178028_d;
/*     */     private static final String __OBFID = "CL_00001947";
/*     */     
/*     */     public GuiEntry(Gui p_i45533_1_, Gui p_i45533_2_) {
/* 406 */       this.field_178029_b = p_i45533_1_;
/* 407 */       this.field_178030_c = p_i45533_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public Gui func_178022_a() {
/* 412 */       return this.field_178029_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public Gui func_178021_b() {
/* 417 */       return this.field_178030_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/* 422 */       func_178017_a(this.field_178029_b, y, mouseX, mouseY, false);
/* 423 */       func_178017_a(this.field_178030_c, y, mouseX, mouseY, false);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178017_a(Gui p_178017_1_, int p_178017_2_, int p_178017_3_, int p_178017_4_, boolean p_178017_5_) {
/* 428 */       if (p_178017_1_ != null)
/*     */       {
/* 430 */         if (p_178017_1_ instanceof GuiButton) {
/*     */           
/* 432 */           func_178024_a((GuiButton)p_178017_1_, p_178017_2_, p_178017_3_, p_178017_4_, p_178017_5_);
/*     */         }
/* 434 */         else if (p_178017_1_ instanceof GuiTextField) {
/*     */           
/* 436 */           func_178027_a((GuiTextField)p_178017_1_, p_178017_2_, p_178017_5_);
/*     */         }
/* 438 */         else if (p_178017_1_ instanceof GuiLabel) {
/*     */           
/* 440 */           func_178025_a((GuiLabel)p_178017_1_, p_178017_2_, p_178017_3_, p_178017_4_, p_178017_5_);
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178024_a(GuiButton p_178024_1_, int p_178024_2_, int p_178024_3_, int p_178024_4_, boolean p_178024_5_) {
/* 447 */       p_178024_1_.yPosition = p_178024_2_;
/*     */       
/* 449 */       if (!p_178024_5_)
/*     */       {
/* 451 */         p_178024_1_.drawButton(this.field_178031_a, p_178024_3_, p_178024_4_);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178027_a(GuiTextField p_178027_1_, int p_178027_2_, boolean p_178027_3_) {
/* 457 */       p_178027_1_.yPosition = p_178027_2_;
/*     */       
/* 459 */       if (!p_178027_3_)
/*     */       {
/* 461 */         p_178027_1_.drawTextBox();
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178025_a(GuiLabel p_178025_1_, int p_178025_2_, int p_178025_3_, int p_178025_4_, boolean p_178025_5_) {
/* 467 */       p_178025_1_.field_146174_h = p_178025_2_;
/*     */       
/* 469 */       if (!p_178025_5_)
/*     */       {
/* 471 */         p_178025_1_.drawLabel(this.field_178031_a, p_178025_3_, p_178025_4_);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
/* 477 */       func_178017_a(this.field_178029_b, p_178011_3_, 0, 0, true);
/* 478 */       func_178017_a(this.field_178030_c, p_178011_3_, 0, 0, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/* 483 */       boolean var7 = func_178026_a(this.field_178029_b, p_148278_2_, p_148278_3_, p_148278_4_);
/* 484 */       boolean var8 = func_178026_a(this.field_178030_c, p_148278_2_, p_148278_3_, p_148278_4_);
/* 485 */       return !(!var7 && !var8);
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_178026_a(Gui p_178026_1_, int p_178026_2_, int p_178026_3_, int p_178026_4_) {
/* 490 */       if (p_178026_1_ == null)
/*     */       {
/* 492 */         return false;
/*     */       }
/* 494 */       if (p_178026_1_ instanceof GuiButton)
/*     */       {
/* 496 */         return func_178023_a((GuiButton)p_178026_1_, p_178026_2_, p_178026_3_, p_178026_4_);
/*     */       }
/*     */ 
/*     */       
/* 500 */       if (p_178026_1_ instanceof GuiTextField)
/*     */       {
/* 502 */         func_178018_a((GuiTextField)p_178026_1_, p_178026_2_, p_178026_3_, p_178026_4_);
/*     */       }
/*     */       
/* 505 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean func_178023_a(GuiButton p_178023_1_, int p_178023_2_, int p_178023_3_, int p_178023_4_) {
/* 511 */       boolean var5 = p_178023_1_.mousePressed(this.field_178031_a, p_178023_2_, p_178023_3_);
/*     */       
/* 513 */       if (var5)
/*     */       {
/* 515 */         this.field_178028_d = p_178023_1_;
/*     */       }
/*     */       
/* 518 */       return var5;
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178018_a(GuiTextField p_178018_1_, int p_178018_2_, int p_178018_3_, int p_178018_4_) {
/* 523 */       p_178018_1_.mouseClicked(p_178018_2_, p_178018_3_, p_178018_4_);
/*     */       
/* 525 */       if (p_178018_1_.isFocused())
/*     */       {
/* 527 */         this.field_178028_d = p_178018_1_;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
/* 533 */       func_178016_b(this.field_178029_b, x, y, mouseEvent);
/* 534 */       func_178016_b(this.field_178030_c, x, y, mouseEvent);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178016_b(Gui p_178016_1_, int p_178016_2_, int p_178016_3_, int p_178016_4_) {
/* 539 */       if (p_178016_1_ != null)
/*     */       {
/* 541 */         if (p_178016_1_ instanceof GuiButton)
/*     */         {
/* 543 */           func_178019_b((GuiButton)p_178016_1_, p_178016_2_, p_178016_3_, p_178016_4_);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_178019_b(GuiButton p_178019_1_, int p_178019_2_, int p_178019_3_, int p_178019_4_) {
/* 550 */       p_178019_1_.mouseReleased(p_178019_2_, p_178019_3_);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GuiLabelEntry
/*     */     extends GuiListEntry
/*     */   {
/*     */     private static final String __OBFID = "CL_00001946";
/*     */     
/*     */     public GuiLabelEntry(int p_i45532_1_, String p_i45532_2_, boolean p_i45532_3_) {
/* 560 */       super(p_i45532_1_, p_i45532_2_, p_i45532_3_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class GuiListEntry
/*     */   {
/*     */     private final int field_178939_a;
/*     */     private final String field_178937_b;
/*     */     private final boolean field_178938_c;
/*     */     private static final String __OBFID = "CL_00001945";
/*     */     
/*     */     public GuiListEntry(int p_i45531_1_, String p_i45531_2_, boolean p_i45531_3_) {
/* 573 */       this.field_178939_a = p_i45531_1_;
/* 574 */       this.field_178937_b = p_i45531_2_;
/* 575 */       this.field_178938_c = p_i45531_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_178935_b() {
/* 580 */       return this.field_178939_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public String func_178936_c() {
/* 585 */       return this.field_178937_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_178934_d() {
/* 590 */       return this.field_178938_c;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface GuiResponder
/*     */   {
/*     */     void func_175321_a(int param1Int, boolean param1Boolean);
/*     */     
/*     */     void func_175320_a(int param1Int, float param1Float);
/*     */     
/*     */     void func_175319_a(int param1Int, String param1String);
/*     */   }
/*     */   
/*     */   public static class GuiSlideEntry
/*     */     extends GuiListEntry
/*     */   {
/*     */     private final GuiSlider.FormatHelper field_178949_a;
/*     */     private final float field_178947_b;
/*     */     private final float field_178948_c;
/*     */     private final float field_178946_d;
/*     */     private static final String __OBFID = "CL_00001944";
/*     */     
/*     */     public GuiSlideEntry(int p_i45530_1_, String p_i45530_2_, boolean p_i45530_3_, GuiSlider.FormatHelper p_i45530_4_, float p_i45530_5_, float p_i45530_6_, float p_i45530_7_) {
/* 613 */       super(p_i45530_1_, p_i45530_2_, p_i45530_3_);
/* 614 */       this.field_178949_a = p_i45530_4_;
/* 615 */       this.field_178947_b = p_i45530_5_;
/* 616 */       this.field_178948_c = p_i45530_6_;
/* 617 */       this.field_178946_d = p_i45530_7_;
/*     */     }
/*     */ 
/*     */     
/*     */     public GuiSlider.FormatHelper func_178945_a() {
/* 622 */       return this.field_178949_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public float func_178943_e() {
/* 627 */       return this.field_178947_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public float func_178944_f() {
/* 632 */       return this.field_178948_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public float func_178942_g() {
/* 637 */       return this.field_178946_d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiPageButtonList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */