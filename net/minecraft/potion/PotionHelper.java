/*     */ package net.minecraft.potion;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import optifine.Config;
/*     */ import optifine.CustomColors;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PotionHelper
/*     */ {
/*  17 */   public static final String field_77924_a = null;
/*     */   public static final String sugarEffect;
/*     */   public static final String ghastTearEffect = "+0-1-2-3&4-4+13";
/*     */   public static final String spiderEyeEffect;
/*     */   public static final String fermentedSpiderEyeEffect;
/*     */   public static final String speckledMelonEffect;
/*     */   public static final String blazePowderEffect;
/*     */   public static final String magmaCreamEffect;
/*     */   public static final String redstoneEffect;
/*     */   public static final String glowstoneEffect;
/*     */   public static final String gunpowderEffect;
/*     */   public static final String goldenCarrotEffect;
/*     */   public static final String field_151423_m;
/*     */   public static final String field_179538_n;
/*  31 */   private static final Map field_179539_o = Maps.newHashMap();
/*  32 */   private static final Map field_179540_p = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   private static final Map field_77925_n;
/*     */ 
/*     */   
/*     */   private static final String[] potionPrefixes;
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkFlag(int p_77914_0_, int p_77914_1_) {
/*  43 */     return ((p_77914_0_ & 1 << p_77914_1_) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int isFlagSet(int p_77910_0_, int p_77910_1_) {
/*  51 */     return checkFlag(p_77910_0_, p_77910_1_) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int isFlagUnset(int p_77916_0_, int p_77916_1_) {
/*  59 */     return checkFlag(p_77916_0_, p_77916_1_) ? 0 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_77909_a(int p_77909_0_) {
/*  64 */     return func_77908_a(p_77909_0_, 5, 4, 3, 2, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int calcPotionLiquidColor(Collection p_77911_0_) {
/*  72 */     int var1 = 3694022;
/*     */     
/*  74 */     if (p_77911_0_ != null && !p_77911_0_.isEmpty()) {
/*     */       
/*  76 */       float var2 = 0.0F;
/*  77 */       float var3 = 0.0F;
/*  78 */       float var4 = 0.0F;
/*  79 */       float var5 = 0.0F;
/*  80 */       Iterator<PotionEffect> var6 = p_77911_0_.iterator();
/*     */       
/*  82 */       while (var6.hasNext()) {
/*     */         
/*  84 */         PotionEffect var7 = var6.next();
/*     */         
/*  86 */         if (var7.func_180154_f()) {
/*     */           
/*  88 */           int var8 = Potion.potionTypes[var7.getPotionID()].getLiquidColor();
/*     */           
/*  90 */           if (Config.isCustomColors())
/*     */           {
/*  92 */             var8 = CustomColors.getPotionColor(var7.getPotionID(), var8);
/*     */           }
/*     */           
/*  95 */           for (int var9 = 0; var9 <= var7.getAmplifier(); var9++) {
/*     */             
/*  97 */             var2 += (var8 >> 16 & 0xFF) / 255.0F;
/*  98 */             var3 += (var8 >> 8 & 0xFF) / 255.0F;
/*  99 */             var4 += (var8 >> 0 & 0xFF) / 255.0F;
/* 100 */             var5++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 105 */       if (var5 == 0.0F)
/*     */       {
/* 107 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 111 */       var2 = var2 / var5 * 255.0F;
/* 112 */       var3 = var3 / var5 * 255.0F;
/* 113 */       var4 = var4 / var5 * 255.0F;
/* 114 */       return (int)var2 << 16 | (int)var3 << 8 | (int)var4;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 119 */     if (Config.isCustomColors())
/*     */     {
/* 121 */       var1 = CustomColors.getPotionColor(0, var1);
/*     */     }
/*     */     
/* 124 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean func_82817_b(Collection potionEffects) {
/* 130 */     Iterator<PotionEffect> var1 = potionEffects.iterator();
/*     */     
/* 132 */     while (var1.hasNext()) {
/*     */       
/* 134 */       PotionEffect var2 = var1.next();
/*     */       
/* 136 */       if (!var2.getIsAmbient())
/*     */       {
/* 138 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_77915_a(int dataValue, boolean bypassCache) {
/* 147 */     if (!bypassCache) {
/*     */       
/* 149 */       if (field_77925_n.containsKey(Integer.valueOf(dataValue)))
/*     */       {
/* 151 */         return ((Integer)field_77925_n.get(Integer.valueOf(dataValue))).intValue();
/*     */       }
/*     */ 
/*     */       
/* 155 */       int var2 = calcPotionLiquidColor(getPotionEffects(dataValue, false));
/* 156 */       field_77925_n.put(Integer.valueOf(dataValue), Integer.valueOf(var2));
/* 157 */       return var2;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 162 */     return calcPotionLiquidColor(getPotionEffects(dataValue, true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String func_77905_c(int p_77905_0_) {
/* 168 */     int var1 = func_77909_a(p_77905_0_);
/* 169 */     return potionPrefixes[var1];
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_77904_a(boolean p_77904_0_, boolean p_77904_1_, boolean p_77904_2_, int p_77904_3_, int p_77904_4_, int p_77904_5_, int p_77904_6_) {
/* 174 */     int var7 = 0;
/*     */     
/* 176 */     if (p_77904_0_) {
/*     */       
/* 178 */       var7 = isFlagUnset(p_77904_6_, p_77904_4_);
/*     */     }
/* 180 */     else if (p_77904_3_ != -1) {
/*     */       
/* 182 */       if (p_77904_3_ == 0 && countSetFlags(p_77904_6_) == p_77904_4_)
/*     */       {
/* 184 */         var7 = 1;
/*     */       }
/* 186 */       else if (p_77904_3_ == 1 && countSetFlags(p_77904_6_) > p_77904_4_)
/*     */       {
/* 188 */         var7 = 1;
/*     */       }
/* 190 */       else if (p_77904_3_ == 2 && countSetFlags(p_77904_6_) < p_77904_4_)
/*     */       {
/* 192 */         var7 = 1;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 197 */       var7 = isFlagSet(p_77904_6_, p_77904_4_);
/*     */     } 
/*     */     
/* 200 */     if (p_77904_1_)
/*     */     {
/* 202 */       var7 *= p_77904_5_;
/*     */     }
/*     */     
/* 205 */     if (p_77904_2_)
/*     */     {
/* 207 */       var7 *= -1;
/*     */     }
/*     */     
/* 210 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int countSetFlags(int p_77907_0_) {
/* 220 */     for (int var1 = 0; p_77907_0_ > 0; var1++)
/*     */     {
/* 222 */       p_77907_0_ &= p_77907_0_ - 1;
/*     */     }
/*     */     
/* 225 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int parsePotionEffects(String p_77912_0_, int p_77912_1_, int p_77912_2_, int p_77912_3_) {
/* 230 */     if (p_77912_1_ < p_77912_0_.length() && p_77912_2_ >= 0 && p_77912_1_ < p_77912_2_) {
/*     */       
/* 232 */       int var4 = p_77912_0_.indexOf('|', p_77912_1_);
/*     */ 
/*     */ 
/*     */       
/* 236 */       if (var4 >= 0 && var4 < p_77912_2_) {
/*     */         
/* 238 */         int i = parsePotionEffects(p_77912_0_, p_77912_1_, var4 - 1, p_77912_3_);
/*     */         
/* 240 */         if (i > 0)
/*     */         {
/* 242 */           return i;
/*     */         }
/*     */ 
/*     */         
/* 246 */         int var17 = parsePotionEffects(p_77912_0_, var4 + 1, p_77912_2_, p_77912_3_);
/* 247 */         return (var17 > 0) ? var17 : 0;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 252 */       int var5 = p_77912_0_.indexOf('&', p_77912_1_);
/*     */       
/* 254 */       if (var5 >= 0 && var5 < p_77912_2_) {
/*     */         
/* 256 */         int var17 = parsePotionEffects(p_77912_0_, p_77912_1_, var5 - 1, p_77912_3_);
/*     */         
/* 258 */         if (var17 <= 0)
/*     */         {
/* 260 */           return 0;
/*     */         }
/*     */ 
/*     */         
/* 264 */         int var18 = parsePotionEffects(p_77912_0_, var5 + 1, p_77912_2_, p_77912_3_);
/* 265 */         return (var18 <= 0) ? 0 : ((var17 > var18) ? var17 : var18);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 270 */       boolean var6 = false;
/* 271 */       boolean var7 = false;
/* 272 */       boolean var8 = false;
/* 273 */       boolean var9 = false;
/* 274 */       boolean var10 = false;
/* 275 */       byte var11 = -1;
/* 276 */       int var12 = 0;
/* 277 */       int var13 = 0;
/* 278 */       int var14 = 0;
/*     */       
/* 280 */       for (int var15 = p_77912_1_; var15 < p_77912_2_; var15++) {
/*     */         
/* 282 */         char var16 = p_77912_0_.charAt(var15);
/*     */         
/* 284 */         if (var16 >= '0' && var16 <= '9') {
/*     */           
/* 286 */           if (var6)
/*     */           {
/* 288 */             var13 = var16 - 48;
/* 289 */             var7 = true;
/*     */           }
/*     */           else
/*     */           {
/* 293 */             var12 *= 10;
/* 294 */             var12 += var16 - 48;
/* 295 */             var8 = true;
/*     */           }
/*     */         
/* 298 */         } else if (var16 == '*') {
/*     */           
/* 300 */           var6 = true;
/*     */         }
/* 302 */         else if (var16 == '!') {
/*     */           
/* 304 */           if (var8) {
/*     */             
/* 306 */             var14 += func_77904_a(var9, var7, var10, var11, var12, var13, p_77912_3_);
/* 307 */             var9 = false;
/* 308 */             var10 = false;
/* 309 */             var6 = false;
/* 310 */             var7 = false;
/* 311 */             var8 = false;
/* 312 */             var13 = 0;
/* 313 */             var12 = 0;
/* 314 */             var11 = -1;
/*     */           } 
/*     */           
/* 317 */           var9 = true;
/*     */         }
/* 319 */         else if (var16 == '-') {
/*     */           
/* 321 */           if (var8) {
/*     */             
/* 323 */             var14 += func_77904_a(var9, var7, var10, var11, var12, var13, p_77912_3_);
/* 324 */             var9 = false;
/* 325 */             var10 = false;
/* 326 */             var6 = false;
/* 327 */             var7 = false;
/* 328 */             var8 = false;
/* 329 */             var13 = 0;
/* 330 */             var12 = 0;
/* 331 */             var11 = -1;
/*     */           } 
/*     */           
/* 334 */           var10 = true;
/*     */         }
/* 336 */         else if (var16 != '=' && var16 != '<' && var16 != '>') {
/*     */           
/* 338 */           if (var16 == '+' && var8)
/*     */           {
/* 340 */             var14 += func_77904_a(var9, var7, var10, var11, var12, var13, p_77912_3_);
/* 341 */             var9 = false;
/* 342 */             var10 = false;
/* 343 */             var6 = false;
/* 344 */             var7 = false;
/* 345 */             var8 = false;
/* 346 */             var13 = 0;
/* 347 */             var12 = 0;
/* 348 */             var11 = -1;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 353 */           if (var8) {
/*     */             
/* 355 */             var14 += func_77904_a(var9, var7, var10, var11, var12, var13, p_77912_3_);
/* 356 */             var9 = false;
/* 357 */             var10 = false;
/* 358 */             var6 = false;
/* 359 */             var7 = false;
/* 360 */             var8 = false;
/* 361 */             var13 = 0;
/* 362 */             var12 = 0;
/* 363 */             var11 = -1;
/*     */           } 
/*     */           
/* 366 */           if (var16 == '=') {
/*     */             
/* 368 */             var11 = 0;
/*     */           }
/* 370 */           else if (var16 == '<') {
/*     */             
/* 372 */             var11 = 2;
/*     */           }
/* 374 */           else if (var16 == '>') {
/*     */             
/* 376 */             var11 = 1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 381 */       if (var8)
/*     */       {
/* 383 */         var14 += func_77904_a(var9, var7, var10, var11, var12, var13, p_77912_3_);
/*     */       }
/*     */       
/* 386 */       return var14;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 392 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getPotionEffects(int p_77917_0_, boolean p_77917_1_) {
/* 401 */     ArrayList<PotionEffect> var2 = null;
/* 402 */     Potion[] var3 = Potion.potionTypes;
/* 403 */     int var4 = var3.length;
/*     */     
/* 405 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 407 */       Potion var6 = var3[var5];
/*     */       
/* 409 */       if (var6 != null && (!var6.isUsable() || p_77917_1_)) {
/*     */         
/* 411 */         String var7 = (String)field_179539_o.get(Integer.valueOf(var6.getId()));
/*     */         
/* 413 */         if (var7 != null) {
/*     */           
/* 415 */           int var8 = parsePotionEffects(var7, 0, var7.length(), p_77917_0_);
/*     */           
/* 417 */           if (var8 > 0) {
/*     */             
/* 419 */             int var9 = 0;
/* 420 */             String var10 = (String)field_179540_p.get(Integer.valueOf(var6.getId()));
/*     */             
/* 422 */             if (var10 != null) {
/*     */               
/* 424 */               var9 = parsePotionEffects(var10, 0, var10.length(), p_77917_0_);
/*     */               
/* 426 */               if (var9 < 0)
/*     */               {
/* 428 */                 var9 = 0;
/*     */               }
/*     */             } 
/*     */             
/* 432 */             if (var6.isInstant()) {
/*     */               
/* 434 */               var8 = 1;
/*     */             }
/*     */             else {
/*     */               
/* 438 */               var8 = 1200 * (var8 * 3 + (var8 - 1) * 2);
/* 439 */               var8 >>= var9;
/* 440 */               var8 = (int)Math.round(var8 * var6.getEffectiveness());
/*     */               
/* 442 */               if ((p_77917_0_ & 0x4000) != 0)
/*     */               {
/* 444 */                 var8 = (int)Math.round(var8 * 0.75D + 0.5D);
/*     */               }
/*     */             } 
/*     */             
/* 448 */             if (var2 == null)
/*     */             {
/* 450 */               var2 = Lists.newArrayList();
/*     */             }
/*     */             
/* 453 */             PotionEffect var11 = new PotionEffect(var6.getId(), var8, var9);
/*     */             
/* 455 */             if ((p_77917_0_ & 0x4000) != 0)
/*     */             {
/* 457 */               var11.setSplashPotion(true);
/*     */             }
/*     */             
/* 460 */             var2.add(var11);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 466 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int brewBitOperations(int p_77906_0_, int p_77906_1_, boolean p_77906_2_, boolean p_77906_3_, boolean p_77906_4_) {
/* 474 */     if (p_77906_4_) {
/*     */       
/* 476 */       if (!checkFlag(p_77906_0_, p_77906_1_))
/*     */       {
/* 478 */         return 0;
/*     */       }
/*     */     }
/* 481 */     else if (p_77906_2_) {
/*     */       
/* 483 */       p_77906_0_ &= 1 << p_77906_1_ ^ 0xFFFFFFFF;
/*     */     }
/* 485 */     else if (p_77906_3_) {
/*     */       
/* 487 */       if ((p_77906_0_ & 1 << p_77906_1_) == 0)
/*     */       {
/* 489 */         p_77906_0_ |= 1 << p_77906_1_;
/*     */       }
/*     */       else
/*     */       {
/* 493 */         p_77906_0_ &= 1 << p_77906_1_ ^ 0xFFFFFFFF;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 498 */       p_77906_0_ |= 1 << p_77906_1_;
/*     */     } 
/*     */     
/* 501 */     return p_77906_0_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int applyIngredient(int p_77913_0_, String p_77913_1_) {
/* 509 */     byte var2 = 0;
/* 510 */     int var3 = p_77913_1_.length();
/* 511 */     boolean var4 = false;
/* 512 */     boolean var5 = false;
/* 513 */     boolean var6 = false;
/* 514 */     boolean var7 = false;
/* 515 */     int var8 = 0;
/*     */     
/* 517 */     for (int var9 = var2; var9 < var3; var9++) {
/*     */       
/* 519 */       char var10 = p_77913_1_.charAt(var9);
/*     */       
/* 521 */       if (var10 >= '0' && var10 <= '9') {
/*     */         
/* 523 */         var8 *= 10;
/* 524 */         var8 += var10 - 48;
/* 525 */         var4 = true;
/*     */       }
/* 527 */       else if (var10 == '!') {
/*     */         
/* 529 */         if (var4) {
/*     */           
/* 531 */           p_77913_0_ = brewBitOperations(p_77913_0_, var8, var6, var5, var7);
/* 532 */           var7 = false;
/* 533 */           var5 = false;
/* 534 */           var6 = false;
/* 535 */           var4 = false;
/* 536 */           var8 = 0;
/*     */         } 
/*     */         
/* 539 */         var5 = true;
/*     */       }
/* 541 */       else if (var10 == '-') {
/*     */         
/* 543 */         if (var4) {
/*     */           
/* 545 */           p_77913_0_ = brewBitOperations(p_77913_0_, var8, var6, var5, var7);
/* 546 */           var7 = false;
/* 547 */           var5 = false;
/* 548 */           var6 = false;
/* 549 */           var4 = false;
/* 550 */           var8 = 0;
/*     */         } 
/*     */         
/* 553 */         var6 = true;
/*     */       }
/* 555 */       else if (var10 == '+') {
/*     */         
/* 557 */         if (var4)
/*     */         {
/* 559 */           p_77913_0_ = brewBitOperations(p_77913_0_, var8, var6, var5, var7);
/* 560 */           var7 = false;
/* 561 */           var5 = false;
/* 562 */           var6 = false;
/* 563 */           var4 = false;
/* 564 */           var8 = 0;
/*     */         }
/*     */       
/* 567 */       } else if (var10 == '&') {
/*     */         
/* 569 */         if (var4) {
/*     */           
/* 571 */           p_77913_0_ = brewBitOperations(p_77913_0_, var8, var6, var5, var7);
/* 572 */           var7 = false;
/* 573 */           var5 = false;
/* 574 */           var6 = false;
/* 575 */           var4 = false;
/* 576 */           var8 = 0;
/*     */         } 
/*     */         
/* 579 */         var7 = true;
/*     */       } 
/*     */     } 
/*     */     
/* 583 */     if (var4)
/*     */     {
/* 585 */       p_77913_0_ = brewBitOperations(p_77913_0_, var8, var6, var5, var7);
/*     */     }
/*     */     
/* 588 */     return p_77913_0_ & 0x7FFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_77908_a(int p_77908_0_, int p_77908_1_, int p_77908_2_, int p_77908_3_, int p_77908_4_, int p_77908_5_) {
/* 593 */     return (checkFlag(p_77908_0_, p_77908_1_) ? 16 : 0) | (checkFlag(p_77908_0_, p_77908_2_) ? 8 : 0) | (checkFlag(p_77908_0_, p_77908_3_) ? 4 : 0) | (checkFlag(p_77908_0_, p_77908_4_) ? 2 : 0) | (checkFlag(p_77908_0_, p_77908_5_) ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearPotionColorCache() {
/* 598 */     field_77925_n.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 603 */     field_179539_o.put(Integer.valueOf(Potion.regeneration.getId()), "0 & !1 & !2 & !3 & 0+6");
/* 604 */     sugarEffect = "-0+1-2-3&4-4+13";
/* 605 */     field_179539_o.put(Integer.valueOf(Potion.moveSpeed.getId()), "!0 & 1 & !2 & !3 & 1+6");
/* 606 */     magmaCreamEffect = "+0+1-2-3&4-4+13";
/* 607 */     field_179539_o.put(Integer.valueOf(Potion.fireResistance.getId()), "0 & 1 & !2 & !3 & 0+6");
/* 608 */     speckledMelonEffect = "+0-1+2-3&4-4+13";
/* 609 */     field_179539_o.put(Integer.valueOf(Potion.heal.getId()), "0 & !1 & 2 & !3");
/* 610 */     spiderEyeEffect = "-0-1+2-3&4-4+13";
/* 611 */     field_179539_o.put(Integer.valueOf(Potion.poison.getId()), "!0 & !1 & 2 & !3 & 2+6");
/* 612 */     fermentedSpiderEyeEffect = "-0+3-4+13";
/* 613 */     field_179539_o.put(Integer.valueOf(Potion.weakness.getId()), "!0 & !1 & !2 & 3 & 3+6");
/* 614 */     field_179539_o.put(Integer.valueOf(Potion.harm.getId()), "!0 & !1 & 2 & 3");
/* 615 */     field_179539_o.put(Integer.valueOf(Potion.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & 3+6");
/* 616 */     blazePowderEffect = "+0-1-2+3&4-4+13";
/* 617 */     field_179539_o.put(Integer.valueOf(Potion.damageBoost.getId()), "0 & !1 & !2 & 3 & 3+6");
/* 618 */     goldenCarrotEffect = "-0+1+2-3+13&4-4";
/* 619 */     field_179539_o.put(Integer.valueOf(Potion.nightVision.getId()), "!0 & 1 & 2 & !3 & 2+6");
/* 620 */     field_179539_o.put(Integer.valueOf(Potion.invisibility.getId()), "!0 & 1 & 2 & 3 & 2+6");
/* 621 */     field_151423_m = "+0-1+2+3+13&4-4";
/* 622 */     field_179539_o.put(Integer.valueOf(Potion.waterBreathing.getId()), "0 & !1 & 2 & 3 & 2+6");
/* 623 */     field_179538_n = "+0+1-2+3&4-4+13";
/* 624 */     field_179539_o.put(Integer.valueOf(Potion.jump.getId()), "0 & 1 & !2 & 3");
/* 625 */     glowstoneEffect = "+5-6-7";
/* 626 */     field_179540_p.put(Integer.valueOf(Potion.moveSpeed.getId()), "5");
/* 627 */     field_179540_p.put(Integer.valueOf(Potion.digSpeed.getId()), "5");
/* 628 */     field_179540_p.put(Integer.valueOf(Potion.damageBoost.getId()), "5");
/* 629 */     field_179540_p.put(Integer.valueOf(Potion.regeneration.getId()), "5");
/* 630 */     field_179540_p.put(Integer.valueOf(Potion.harm.getId()), "5");
/* 631 */     field_179540_p.put(Integer.valueOf(Potion.heal.getId()), "5");
/* 632 */     field_179540_p.put(Integer.valueOf(Potion.resistance.getId()), "5");
/* 633 */     field_179540_p.put(Integer.valueOf(Potion.poison.getId()), "5");
/* 634 */     field_179540_p.put(Integer.valueOf(Potion.jump.getId()), "5");
/* 635 */     redstoneEffect = "-5+6-7";
/* 636 */     gunpowderEffect = "+14&13-13";
/* 637 */     field_77925_n = Maps.newHashMap();
/* 638 */     potionPrefixes = new String[] { "potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky" };
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\potion\PotionHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */