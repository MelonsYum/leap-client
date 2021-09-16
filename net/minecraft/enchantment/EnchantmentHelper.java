/*     */ package net.minecraft.enchantment;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ 
/*     */ 
/*     */ public class EnchantmentHelper
/*     */ {
/*  27 */   private static final Random enchantmentRand = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private static final ModifierDamage enchantmentModifierDamage = new ModifierDamage(null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private static final ModifierLiving enchantmentModifierLiving = new ModifierLiving(null);
/*  38 */   private static final HurtIterator field_151388_d = new HurtIterator(null);
/*  39 */   private static final DamageIterator field_151389_e = new DamageIterator(null);
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000107";
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEnchantmentLevel(int p_77506_0_, ItemStack p_77506_1_) {
/*  47 */     if (p_77506_1_ == null)
/*     */     {
/*  49 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  53 */     NBTTagList var2 = p_77506_1_.getEnchantmentTagList();
/*     */     
/*  55 */     if (var2 == null)
/*     */     {
/*  57 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  61 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/*  63 */       short var4 = var2.getCompoundTagAt(var3).getShort("id");
/*  64 */       short var5 = var2.getCompoundTagAt(var3).getShort("lvl");
/*     */       
/*  66 */       if (var4 == p_77506_0_)
/*     */       {
/*  68 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/*  72 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map getEnchantments(ItemStack p_82781_0_) {
/*  82 */     LinkedHashMap<Integer, Integer> var1 = Maps.newLinkedHashMap();
/*  83 */     NBTTagList var2 = (p_82781_0_.getItem() == Items.enchanted_book) ? Items.enchanted_book.func_92110_g(p_82781_0_) : p_82781_0_.getEnchantmentTagList();
/*     */     
/*  85 */     if (var2 != null)
/*     */     {
/*  87 */       for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */         
/*  89 */         short var4 = var2.getCompoundTagAt(var3).getShort("id");
/*  90 */         short var5 = var2.getCompoundTagAt(var3).getShort("lvl");
/*  91 */         var1.put(Integer.valueOf(var4), Integer.valueOf(var5));
/*     */       } 
/*     */     }
/*     */     
/*  95 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setEnchantments(Map p_82782_0_, ItemStack p_82782_1_) {
/* 103 */     NBTTagList var2 = new NBTTagList();
/* 104 */     Iterator<Integer> var3 = p_82782_0_.keySet().iterator();
/*     */     
/* 106 */     while (var3.hasNext()) {
/*     */       
/* 108 */       int var4 = ((Integer)var3.next()).intValue();
/* 109 */       Enchantment var5 = Enchantment.func_180306_c(var4);
/*     */       
/* 111 */       if (var5 != null) {
/*     */         
/* 113 */         NBTTagCompound var6 = new NBTTagCompound();
/* 114 */         var6.setShort("id", (short)var4);
/* 115 */         var6.setShort("lvl", (short)((Integer)p_82782_0_.get(Integer.valueOf(var4))).intValue());
/* 116 */         var2.appendTag((NBTBase)var6);
/*     */         
/* 118 */         if (p_82782_1_.getItem() == Items.enchanted_book)
/*     */         {
/* 120 */           Items.enchanted_book.addEnchantment(p_82782_1_, new EnchantmentData(var5, ((Integer)p_82782_0_.get(Integer.valueOf(var4))).intValue()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     if (var2.tagCount() > 0) {
/*     */       
/* 127 */       if (p_82782_1_.getItem() != Items.enchanted_book)
/*     */       {
/* 129 */         p_82782_1_.setTagInfo("ench", (NBTBase)var2);
/*     */       }
/*     */     }
/* 132 */     else if (p_82782_1_.hasTagCompound()) {
/*     */       
/* 134 */       p_82782_1_.getTagCompound().removeTag("ench");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMaxEnchantmentLevel(int p_77511_0_, ItemStack[] p_77511_1_) {
/* 143 */     if (p_77511_1_ == null)
/*     */     {
/* 145 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 149 */     int var2 = 0;
/* 150 */     ItemStack[] var3 = p_77511_1_;
/* 151 */     int var4 = p_77511_1_.length;
/*     */     
/* 153 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 155 */       ItemStack var6 = var3[var5];
/* 156 */       int var7 = getEnchantmentLevel(p_77511_0_, var6);
/*     */       
/* 158 */       if (var7 > var2)
/*     */       {
/* 160 */         var2 = var7;
/*     */       }
/*     */     } 
/*     */     
/* 164 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void applyEnchantmentModifier(IModifier p_77518_0_, ItemStack p_77518_1_) {
/* 173 */     if (p_77518_1_ != null) {
/*     */       
/* 175 */       NBTTagList var2 = p_77518_1_.getEnchantmentTagList();
/*     */       
/* 177 */       if (var2 != null)
/*     */       {
/* 179 */         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */           
/* 181 */           short var4 = var2.getCompoundTagAt(var3).getShort("id");
/* 182 */           short var5 = var2.getCompoundTagAt(var3).getShort("lvl");
/*     */           
/* 184 */           if (Enchantment.func_180306_c(var4) != null)
/*     */           {
/* 186 */             p_77518_0_.calculateModifier(Enchantment.func_180306_c(var4), var5);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void applyEnchantmentModifierArray(IModifier p_77516_0_, ItemStack[] p_77516_1_) {
/* 198 */     ItemStack[] var2 = p_77516_1_;
/* 199 */     int var3 = p_77516_1_.length;
/*     */     
/* 201 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 203 */       ItemStack var5 = var2[var4];
/* 204 */       applyEnchantmentModifier(p_77516_0_, var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEnchantmentModifierDamage(ItemStack[] p_77508_0_, DamageSource p_77508_1_) {
/* 213 */     enchantmentModifierDamage.damageModifier = 0;
/* 214 */     enchantmentModifierDamage.source = p_77508_1_;
/* 215 */     applyEnchantmentModifierArray(enchantmentModifierDamage, p_77508_0_);
/*     */     
/* 217 */     if (enchantmentModifierDamage.damageModifier > 25)
/*     */     {
/* 219 */       enchantmentModifierDamage.damageModifier = 25;
/*     */     }
/*     */     
/* 222 */     return (enchantmentModifierDamage.damageModifier + 1 >> 1) + enchantmentRand.nextInt((enchantmentModifierDamage.damageModifier >> 1) + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_152377_a(ItemStack p_152377_0_, EnumCreatureAttribute p_152377_1_) {
/* 227 */     enchantmentModifierLiving.livingModifier = 0.0F;
/* 228 */     enchantmentModifierLiving.entityLiving = p_152377_1_;
/* 229 */     applyEnchantmentModifier(enchantmentModifierLiving, p_152377_0_);
/* 230 */     return enchantmentModifierLiving.livingModifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_151384_a(EntityLivingBase p_151384_0_, Entity p_151384_1_) {
/* 235 */     field_151388_d.field_151363_b = p_151384_1_;
/* 236 */     field_151388_d.field_151364_a = p_151384_0_;
/*     */     
/* 238 */     if (p_151384_0_ != null)
/*     */     {
/* 240 */       applyEnchantmentModifierArray(field_151388_d, p_151384_0_.getInventory());
/*     */     }
/*     */     
/* 243 */     if (p_151384_1_ instanceof net.minecraft.entity.player.EntityPlayer)
/*     */     {
/* 245 */       applyEnchantmentModifier(field_151388_d, p_151384_0_.getHeldItem());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_151385_b(EntityLivingBase p_151385_0_, Entity p_151385_1_) {
/* 251 */     field_151389_e.field_151366_a = p_151385_0_;
/* 252 */     field_151389_e.field_151365_b = p_151385_1_;
/*     */     
/* 254 */     if (p_151385_0_ != null)
/*     */     {
/* 256 */       applyEnchantmentModifierArray(field_151389_e, p_151385_0_.getInventory());
/*     */     }
/*     */     
/* 259 */     if (p_151385_0_ instanceof net.minecraft.entity.player.EntityPlayer)
/*     */     {
/* 261 */       applyEnchantmentModifier(field_151389_e, p_151385_0_.getHeldItem());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getRespiration(EntityLivingBase p_77501_0_) {
/* 270 */     return getEnchantmentLevel(Enchantment.field_180313_o.effectId, p_77501_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getFireAspectModifier(EntityLivingBase p_90036_0_) {
/* 275 */     return getEnchantmentLevel(Enchantment.fireAspect.effectId, p_90036_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_180319_a(Entity p_180319_0_) {
/* 280 */     return getMaxEnchantmentLevel(Enchantment.field_180317_h.effectId, p_180319_0_.getInventory());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_180318_b(Entity p_180318_0_) {
/* 285 */     return getMaxEnchantmentLevel(Enchantment.field_180316_k.effectId, p_180318_0_.getInventory());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getEfficiencyModifier(EntityLivingBase p_77509_0_) {
/* 293 */     return getEnchantmentLevel(Enchantment.efficiency.effectId, p_77509_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getSilkTouchModifier(EntityLivingBase p_77502_0_) {
/* 301 */     return (getEnchantmentLevel(Enchantment.silkTouch.effectId, p_77502_0_.getHeldItem()) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFortuneModifier(EntityLivingBase p_77517_0_) {
/* 309 */     return getEnchantmentLevel(Enchantment.fortune.effectId, p_77517_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_151386_g(EntityLivingBase p_151386_0_) {
/* 314 */     return getEnchantmentLevel(Enchantment.luckOfTheSea.effectId, p_151386_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_151387_h(EntityLivingBase p_151387_0_) {
/* 319 */     return getEnchantmentLevel(Enchantment.lure.effectId, p_151387_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLootingModifier(EntityLivingBase p_77519_0_) {
/* 327 */     return getEnchantmentLevel(Enchantment.looting.effectId, p_77519_0_.getHeldItem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getAquaAffinityModifier(EntityLivingBase p_77510_0_) {
/* 335 */     return (getMaxEnchantmentLevel(Enchantment.aquaAffinity.effectId, p_77510_0_.getInventory()) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack func_92099_a(Enchantment p_92099_0_, EntityLivingBase p_92099_1_) {
/* 340 */     ItemStack[] var2 = p_92099_1_.getInventory();
/* 341 */     int var3 = var2.length;
/*     */     
/* 343 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 345 */       ItemStack var5 = var2[var4];
/*     */       
/* 347 */       if (var5 != null && getEnchantmentLevel(p_92099_0_.effectId, var5) > 0)
/*     */       {
/* 349 */         return var5;
/*     */       }
/*     */     } 
/*     */     
/* 353 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int calcItemStackEnchantability(Random p_77514_0_, int p_77514_1_, int p_77514_2_, ItemStack p_77514_3_) {
/* 362 */     Item var4 = p_77514_3_.getItem();
/* 363 */     int var5 = var4.getItemEnchantability();
/*     */     
/* 365 */     if (var5 <= 0)
/*     */     {
/* 367 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 371 */     if (p_77514_2_ > 15)
/*     */     {
/* 373 */       p_77514_2_ = 15;
/*     */     }
/*     */     
/* 376 */     int var6 = p_77514_0_.nextInt(8) + 1 + (p_77514_2_ >> 1) + p_77514_0_.nextInt(p_77514_2_ + 1);
/* 377 */     return (p_77514_1_ == 0) ? Math.max(var6 / 3, 1) : ((p_77514_1_ == 1) ? (var6 * 2 / 3 + 1) : Math.max(var6, p_77514_2_ * 2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack addRandomEnchantment(Random p_77504_0_, ItemStack p_77504_1_, int p_77504_2_) {
/* 386 */     List var3 = buildEnchantmentList(p_77504_0_, p_77504_1_, p_77504_2_);
/* 387 */     boolean var4 = (p_77504_1_.getItem() == Items.book);
/*     */     
/* 389 */     if (var4)
/*     */     {
/* 391 */       p_77504_1_.setItem((Item)Items.enchanted_book);
/*     */     }
/*     */     
/* 394 */     if (var3 != null) {
/*     */       
/* 396 */       Iterator<EnchantmentData> var5 = var3.iterator();
/*     */       
/* 398 */       while (var5.hasNext()) {
/*     */         
/* 400 */         EnchantmentData var6 = var5.next();
/*     */         
/* 402 */         if (var4) {
/*     */           
/* 404 */           Items.enchanted_book.addEnchantment(p_77504_1_, var6);
/*     */           
/*     */           continue;
/*     */         } 
/* 408 */         p_77504_1_.addEnchantment(var6.enchantmentobj, var6.enchantmentLevel);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 413 */     return p_77504_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List buildEnchantmentList(Random p_77513_0_, ItemStack p_77513_1_, int p_77513_2_) {
/* 422 */     Item var3 = p_77513_1_.getItem();
/* 423 */     int var4 = var3.getItemEnchantability();
/*     */     
/* 425 */     if (var4 <= 0)
/*     */     {
/* 427 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 431 */     var4 /= 2;
/* 432 */     var4 = 1 + p_77513_0_.nextInt((var4 >> 1) + 1) + p_77513_0_.nextInt((var4 >> 1) + 1);
/* 433 */     int var5 = var4 + p_77513_2_;
/* 434 */     float var6 = (p_77513_0_.nextFloat() + p_77513_0_.nextFloat() - 1.0F) * 0.15F;
/* 435 */     int var7 = (int)(var5 * (1.0F + var6) + 0.5F);
/*     */     
/* 437 */     if (var7 < 1)
/*     */     {
/* 439 */       var7 = 1;
/*     */     }
/*     */     
/* 442 */     ArrayList<EnchantmentData> var8 = null;
/* 443 */     Map var9 = mapEnchantmentData(var7, p_77513_1_);
/*     */     
/* 445 */     if (var9 != null && !var9.isEmpty()) {
/*     */       
/* 447 */       EnchantmentData var10 = (EnchantmentData)WeightedRandom.getRandomItem(p_77513_0_, var9.values());
/*     */       
/* 449 */       if (var10 != null) {
/*     */         
/* 451 */         var8 = Lists.newArrayList();
/* 452 */         var8.add(var10);
/*     */         
/* 454 */         for (int var11 = var7; p_77513_0_.nextInt(50) <= var11; var11 >>= 1) {
/*     */           
/* 456 */           Iterator<Integer> var12 = var9.keySet().iterator();
/*     */           
/* 458 */           while (var12.hasNext()) {
/*     */             
/* 460 */             Integer var13 = var12.next();
/* 461 */             boolean var14 = true;
/* 462 */             Iterator<EnchantmentData> var15 = var8.iterator();
/*     */ 
/*     */ 
/*     */             
/* 466 */             while (var15.hasNext()) {
/*     */               
/* 468 */               EnchantmentData var16 = var15.next();
/*     */               
/* 470 */               if (var16.enchantmentobj.canApplyTogether(Enchantment.func_180306_c(var13.intValue()))) {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */               
/* 475 */               var14 = false;
/*     */             } 
/*     */             
/* 478 */             if (!var14)
/*     */             {
/* 480 */               var12.remove();
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 487 */           if (!var9.isEmpty()) {
/*     */             
/* 489 */             EnchantmentData var17 = (EnchantmentData)WeightedRandom.getRandomItem(p_77513_0_, var9.values());
/* 490 */             var8.add(var17);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 496 */     return var8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map mapEnchantmentData(int p_77505_0_, ItemStack p_77505_1_) {
/* 506 */     Item var2 = p_77505_1_.getItem();
/* 507 */     HashMap<Integer, EnchantmentData> var3 = null;
/* 508 */     boolean var4 = (p_77505_1_.getItem() == Items.book);
/* 509 */     Enchantment[] var5 = Enchantment.enchantmentsList;
/* 510 */     int var6 = var5.length;
/*     */     
/* 512 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/* 514 */       Enchantment var8 = var5[var7];
/*     */       
/* 516 */       if (var8 != null && (var8.type.canEnchantItem(var2) || var4))
/*     */       {
/* 518 */         for (int var9 = var8.getMinLevel(); var9 <= var8.getMaxLevel(); var9++) {
/*     */           
/* 520 */           if (p_77505_0_ >= var8.getMinEnchantability(var9) && p_77505_0_ <= var8.getMaxEnchantability(var9)) {
/*     */             
/* 522 */             if (var3 == null)
/*     */             {
/* 524 */               var3 = Maps.newHashMap();
/*     */             }
/*     */             
/* 527 */             var3.put(Integer.valueOf(var8.effectId), new EnchantmentData(var8, var9));
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 533 */     return var3;
/*     */   }
/*     */   
/*     */   static final class DamageIterator
/*     */     implements IModifier
/*     */   {
/*     */     public EntityLivingBase field_151366_a;
/*     */     public Entity field_151365_b;
/*     */     private static final String __OBFID = "CL_00000109";
/*     */     
/*     */     private DamageIterator() {}
/*     */     
/*     */     public void calculateModifier(Enchantment p_77493_1_, int p_77493_2_) {
/* 546 */       p_77493_1_.func_151368_a(this.field_151366_a, this.field_151365_b, p_77493_2_);
/*     */     }
/*     */ 
/*     */     
/*     */     DamageIterator(Object p_i45359_1_) {
/* 551 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   static final class HurtIterator
/*     */     implements IModifier
/*     */   {
/*     */     public EntityLivingBase field_151364_a;
/*     */     public Entity field_151363_b;
/*     */     private static final String __OBFID = "CL_00000110";
/*     */     
/*     */     private HurtIterator() {}
/*     */     
/*     */     public void calculateModifier(Enchantment p_77493_1_, int p_77493_2_) {
/* 565 */       p_77493_1_.func_151367_b(this.field_151364_a, this.field_151363_b, p_77493_2_);
/*     */     }
/*     */ 
/*     */     
/*     */     HurtIterator(Object p_i45360_1_) {
/* 570 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   static interface IModifier
/*     */   {
/*     */     void calculateModifier(Enchantment param1Enchantment, int param1Int);
/*     */   }
/*     */   
/*     */   static final class ModifierDamage
/*     */     implements IModifier
/*     */   {
/*     */     public int damageModifier;
/*     */     public DamageSource source;
/*     */     private static final String __OBFID = "CL_00000114";
/*     */     
/*     */     private ModifierDamage() {}
/*     */     
/*     */     public void calculateModifier(Enchantment p_77493_1_, int p_77493_2_) {
/* 589 */       this.damageModifier += p_77493_1_.calcModifierDamage(p_77493_2_, this.source);
/*     */     }
/*     */ 
/*     */     
/*     */     ModifierDamage(Object p_i1929_1_) {
/* 594 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   static final class ModifierLiving
/*     */     implements IModifier
/*     */   {
/*     */     public float livingModifier;
/*     */     public EnumCreatureAttribute entityLiving;
/*     */     private static final String __OBFID = "CL_00000112";
/*     */     
/*     */     private ModifierLiving() {}
/*     */     
/*     */     public void calculateModifier(Enchantment p_77493_1_, int p_77493_2_) {
/* 608 */       this.livingModifier += p_77493_1_.func_152376_a(p_77493_2_, this.entityLiving);
/*     */     }
/*     */ 
/*     */     
/*     */     ModifierLiving(Object p_i1928_1_) {
/* 613 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */