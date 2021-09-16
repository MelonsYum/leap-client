/*     */ package net.minecraft.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagIntArray;
/*     */ import net.minecraft.util.StatCollector;
/*     */ 
/*     */ public class ItemFireworkCharge
/*     */   extends Item
/*     */ {
/*     */   private static final String __OBFID = "CL_00000030";
/*     */   
/*     */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/*  16 */     if (renderPass != 1)
/*     */     {
/*  18 */       return super.getColorFromItemStack(stack, renderPass);
/*     */     }
/*     */ 
/*     */     
/*  22 */     NBTBase var3 = func_150903_a(stack, "Colors");
/*     */     
/*  24 */     if (!(var3 instanceof NBTTagIntArray))
/*     */     {
/*  26 */       return 9079434;
/*     */     }
/*     */ 
/*     */     
/*  30 */     NBTTagIntArray var4 = (NBTTagIntArray)var3;
/*  31 */     int[] var5 = var4.getIntArray();
/*     */     
/*  33 */     if (var5.length == 1)
/*     */     {
/*  35 */       return var5[0];
/*     */     }
/*     */ 
/*     */     
/*  39 */     int var6 = 0;
/*  40 */     int var7 = 0;
/*  41 */     int var8 = 0;
/*  42 */     int[] var9 = var5;
/*  43 */     int var10 = var5.length;
/*     */     
/*  45 */     for (int var11 = 0; var11 < var10; var11++) {
/*     */       
/*  47 */       int var12 = var9[var11];
/*  48 */       var6 += (var12 & 0xFF0000) >> 16;
/*  49 */       var7 += (var12 & 0xFF00) >> 8;
/*  50 */       var8 += (var12 & 0xFF) >> 0;
/*     */     } 
/*     */     
/*  53 */     var6 /= var5.length;
/*  54 */     var7 /= var5.length;
/*  55 */     var8 /= var5.length;
/*  56 */     return var6 << 16 | var7 << 8 | var8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NBTBase func_150903_a(ItemStack p_150903_0_, String p_150903_1_) {
/*  64 */     if (p_150903_0_.hasTagCompound()) {
/*     */       
/*  66 */       NBTTagCompound var2 = p_150903_0_.getTagCompound().getCompoundTag("Explosion");
/*     */       
/*  68 */       if (var2 != null)
/*     */       {
/*  70 */         return var2.getTag(p_150903_1_);
/*     */       }
/*     */     } 
/*     */     
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
/*  85 */     if (stack.hasTagCompound()) {
/*     */       
/*  87 */       NBTTagCompound var5 = stack.getTagCompound().getCompoundTag("Explosion");
/*     */       
/*  89 */       if (var5 != null)
/*     */       {
/*  91 */         func_150902_a(var5, tooltip);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_150902_a(NBTTagCompound p_150902_0_, List<String> p_150902_1_) {
/*  98 */     byte var2 = p_150902_0_.getByte("Type");
/*     */     
/* 100 */     if (var2 >= 0 && var2 <= 4) {
/*     */       
/* 102 */       p_150902_1_.add(StatCollector.translateToLocal("item.fireworksCharge.type." + var2).trim());
/*     */     }
/*     */     else {
/*     */       
/* 106 */       p_150902_1_.add(StatCollector.translateToLocal("item.fireworksCharge.type").trim());
/*     */     } 
/*     */     
/* 109 */     int[] var3 = p_150902_0_.getIntArray("Colors");
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (var3.length > 0) {
/*     */       
/* 115 */       boolean var4 = true;
/* 116 */       String var5 = "";
/* 117 */       int[] var6 = var3;
/* 118 */       int var7 = var3.length;
/*     */       
/* 120 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/* 122 */         int var9 = var6[var8];
/*     */         
/* 124 */         if (!var4)
/*     */         {
/* 126 */           var5 = String.valueOf(var5) + ", ";
/*     */         }
/*     */         
/* 129 */         var4 = false;
/* 130 */         boolean var10 = false;
/*     */         
/* 132 */         for (int var11 = 0; var11 < ItemDye.dyeColors.length; var11++) {
/*     */           
/* 134 */           if (var9 == ItemDye.dyeColors[var11]) {
/*     */             
/* 136 */             var10 = true;
/* 137 */             var5 = String.valueOf(var5) + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.func_176766_a(var11).func_176762_d());
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 142 */         if (!var10)
/*     */         {
/* 144 */           var5 = String.valueOf(var5) + StatCollector.translateToLocal("item.fireworksCharge.customColor");
/*     */         }
/*     */       } 
/*     */       
/* 148 */       p_150902_1_.add(var5);
/*     */     } 
/*     */     
/* 151 */     int[] var13 = p_150902_0_.getIntArray("FadeColors");
/*     */ 
/*     */     
/* 154 */     if (var13.length > 0) {
/*     */       
/* 156 */       boolean bool = true;
/* 157 */       String var15 = String.valueOf(StatCollector.translateToLocal("item.fireworksCharge.fadeTo")) + " ";
/* 158 */       int[] var17 = var13;
/* 159 */       int var8 = var13.length;
/*     */       
/* 161 */       for (int var9 = 0; var9 < var8; var9++) {
/*     */         
/* 163 */         int var18 = var17[var9];
/*     */         
/* 165 */         if (!bool)
/*     */         {
/* 167 */           var15 = String.valueOf(var15) + ", ";
/*     */         }
/*     */         
/* 170 */         bool = false;
/* 171 */         boolean var19 = false;
/*     */         
/* 173 */         for (int var12 = 0; var12 < 16; var12++) {
/*     */           
/* 175 */           if (var18 == ItemDye.dyeColors[var12]) {
/*     */             
/* 177 */             var19 = true;
/* 178 */             var15 = String.valueOf(var15) + StatCollector.translateToLocal("item.fireworksCharge." + EnumDyeColor.func_176766_a(var12).func_176762_d());
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 183 */         if (!var19)
/*     */         {
/* 185 */           var15 = String.valueOf(var15) + StatCollector.translateToLocal("item.fireworksCharge.customColor");
/*     */         }
/*     */       } 
/*     */       
/* 189 */       p_150902_1_.add(var15);
/*     */     } 
/*     */     
/* 192 */     boolean var14 = p_150902_0_.getBoolean("Trail");
/*     */     
/* 194 */     if (var14)
/*     */     {
/* 196 */       p_150902_1_.add(StatCollector.translateToLocal("item.fireworksCharge.trail"));
/*     */     }
/*     */     
/* 199 */     boolean var16 = p_150902_0_.getBoolean("Flicker");
/*     */     
/* 201 */     if (var16)
/*     */     {
/* 203 */       p_150902_1_.add(StatCollector.translateToLocal("item.fireworksCharge.flicker"));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemFireworkCharge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */