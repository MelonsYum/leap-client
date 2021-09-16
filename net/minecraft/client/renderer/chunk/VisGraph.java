/*     */ package net.minecraft.client.renderer.chunk;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.BitSet;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import optifine.IntegerCache;
/*     */ 
/*     */ 
/*     */ public class VisGraph
/*     */ {
/*  14 */   private static final int field_178616_a = (int)Math.pow(16.0D, 0.0D);
/*  15 */   private static final int field_178614_b = (int)Math.pow(16.0D, 1.0D);
/*  16 */   private static final int field_178615_c = (int)Math.pow(16.0D, 2.0D);
/*  17 */   private final BitSet field_178612_d = new BitSet(4096);
/*  18 */   private static final int[] field_178613_e = new int[1352];
/*  19 */   private int field_178611_f = 4096;
/*     */   
/*     */   private static final String __OBFID = "CL_00002450";
/*     */   
/*     */   public void func_178606_a(BlockPos p_178606_1_) {
/*  24 */     this.field_178612_d.set(func_178608_c(p_178606_1_), true);
/*  25 */     this.field_178611_f--;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_178608_c(BlockPos p_178608_0_) {
/*  30 */     return func_178605_a(p_178608_0_.getX() & 0xF, p_178608_0_.getY() & 0xF, p_178608_0_.getZ() & 0xF);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_178605_a(int p_178605_0_, int p_178605_1_, int p_178605_2_) {
/*  35 */     return p_178605_0_ << 0 | p_178605_1_ << 8 | p_178605_2_ << 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public SetVisibility func_178607_a() {
/*  40 */     SetVisibility var1 = new SetVisibility();
/*     */     
/*  42 */     if (4096 - this.field_178611_f < 256) {
/*     */       
/*  44 */       var1.func_178618_a(true);
/*     */     }
/*  46 */     else if (this.field_178611_f == 0) {
/*     */       
/*  48 */       var1.func_178618_a(false);
/*     */     }
/*     */     else {
/*     */       
/*  52 */       int[] var2 = field_178613_e;
/*  53 */       int var3 = var2.length;
/*     */       
/*  55 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/*  57 */         int var5 = var2[var4];
/*     */         
/*  59 */         if (!this.field_178612_d.get(var5))
/*     */         {
/*  61 */           var1.func_178620_a(func_178604_a(var5));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  66 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set func_178609_b(BlockPos p_178609_1_) {
/*  71 */     return func_178604_a(func_178608_c(p_178609_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   private Set func_178604_a(int p_178604_1_) {
/*  76 */     EnumSet<EnumFacing> var2 = EnumSet.noneOf(EnumFacing.class);
/*  77 */     ArrayDeque<Integer> var3 = new ArrayDeque(384);
/*  78 */     var3.add(IntegerCache.valueOf(p_178604_1_));
/*  79 */     this.field_178612_d.set(p_178604_1_, true);
/*     */     
/*  81 */     while (!var3.isEmpty()) {
/*     */       
/*  83 */       int var4 = ((Integer)var3.poll()).intValue();
/*  84 */       func_178610_a(var4, var2);
/*  85 */       EnumFacing[] var5 = EnumFacing.VALUES;
/*  86 */       int var6 = var5.length;
/*     */       
/*  88 */       for (int var7 = 0; var7 < var6; var7++) {
/*     */         
/*  90 */         EnumFacing var8 = var5[var7];
/*  91 */         int var9 = func_178603_a(var4, var8);
/*     */         
/*  93 */         if (var9 >= 0 && !this.field_178612_d.get(var9)) {
/*     */           
/*  95 */           this.field_178612_d.set(var9, true);
/*  96 */           var3.add(IntegerCache.valueOf(var9));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178610_a(int p_178610_1_, Set<EnumFacing> p_178610_2_) {
/* 106 */     int var3 = p_178610_1_ >> 0 & 0xF;
/*     */     
/* 108 */     if (var3 == 0) {
/*     */       
/* 110 */       p_178610_2_.add(EnumFacing.WEST);
/*     */     }
/* 112 */     else if (var3 == 15) {
/*     */       
/* 114 */       p_178610_2_.add(EnumFacing.EAST);
/*     */     } 
/*     */     
/* 117 */     int var4 = p_178610_1_ >> 8 & 0xF;
/*     */     
/* 119 */     if (var4 == 0) {
/*     */       
/* 121 */       p_178610_2_.add(EnumFacing.DOWN);
/*     */     }
/* 123 */     else if (var4 == 15) {
/*     */       
/* 125 */       p_178610_2_.add(EnumFacing.UP);
/*     */     } 
/*     */     
/* 128 */     int var5 = p_178610_1_ >> 4 & 0xF;
/*     */     
/* 130 */     if (var5 == 0) {
/*     */       
/* 132 */       p_178610_2_.add(EnumFacing.NORTH);
/*     */     }
/* 134 */     else if (var5 == 15) {
/*     */       
/* 136 */       p_178610_2_.add(EnumFacing.SOUTH);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_178603_a(int p_178603_1_, EnumFacing p_178603_2_) {
/* 142 */     switch (SwitchEnumFacing.field_178617_a[p_178603_2_.ordinal()]) {
/*     */       
/*     */       case 1:
/* 145 */         if ((p_178603_1_ >> 8 & 0xF) == 0)
/*     */         {
/* 147 */           return -1;
/*     */         }
/*     */         
/* 150 */         return p_178603_1_ - field_178615_c;
/*     */       
/*     */       case 2:
/* 153 */         if ((p_178603_1_ >> 8 & 0xF) == 15)
/*     */         {
/* 155 */           return -1;
/*     */         }
/*     */         
/* 158 */         return p_178603_1_ + field_178615_c;
/*     */       
/*     */       case 3:
/* 161 */         if ((p_178603_1_ >> 4 & 0xF) == 0)
/*     */         {
/* 163 */           return -1;
/*     */         }
/*     */         
/* 166 */         return p_178603_1_ - field_178614_b;
/*     */       
/*     */       case 4:
/* 169 */         if ((p_178603_1_ >> 4 & 0xF) == 15)
/*     */         {
/* 171 */           return -1;
/*     */         }
/*     */         
/* 174 */         return p_178603_1_ + field_178614_b;
/*     */       
/*     */       case 5:
/* 177 */         if ((p_178603_1_ >> 0 & 0xF) == 0)
/*     */         {
/* 179 */           return -1;
/*     */         }
/*     */         
/* 182 */         return p_178603_1_ - field_178616_a;
/*     */       
/*     */       case 6:
/* 185 */         if ((p_178603_1_ >> 0 & 0xF) == 15)
/*     */         {
/* 187 */           return -1;
/*     */         }
/*     */         
/* 190 */         return p_178603_1_ + field_178616_a;
/*     */     } 
/*     */     
/* 193 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 199 */     boolean var0 = false;
/* 200 */     boolean var1 = true;
/* 201 */     int var2 = 0;
/*     */     
/* 203 */     for (int var3 = 0; var3 < 16; var3++) {
/*     */       
/* 205 */       for (int var4 = 0; var4 < 16; var4++) {
/*     */         
/* 207 */         for (int var5 = 0; var5 < 16; var5++) {
/*     */           
/* 209 */           if (var3 == 0 || var3 == 15 || var4 == 0 || var4 == 15 || var5 == 0 || var5 == 15)
/*     */           {
/* 211 */             field_178613_e[var2++] = func_178605_a(var3, var4, var5);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 220 */     static final int[] field_178617_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002449";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 227 */         field_178617_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 229 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 236 */         field_178617_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 238 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 245 */         field_178617_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 247 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 254 */         field_178617_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 256 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 263 */         field_178617_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 265 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 272 */         field_178617_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 274 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\chunk\VisGraph.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */