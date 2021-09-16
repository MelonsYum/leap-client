/*     */ package net.minecraft.world.gen.layer;
/*     */ 
/*     */ public class GenLayerEdge
/*     */   extends GenLayer
/*     */ {
/*     */   private final Mode field_151627_c;
/*     */   private static final String __OBFID = "CL_00000547";
/*     */   
/*     */   public GenLayerEdge(long p_i45474_1_, GenLayer p_i45474_3_, Mode p_i45474_4_) {
/*  10 */     super(p_i45474_1_);
/*  11 */     this.parent = p_i45474_3_;
/*  12 */     this.field_151627_c = p_i45474_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/*  21 */     switch (SwitchMode.field_151642_a[this.field_151627_c.ordinal()]) {
/*     */ 
/*     */       
/*     */       default:
/*  25 */         return getIntsCoolWarm(areaX, areaY, areaWidth, areaHeight);
/*     */       
/*     */       case 2:
/*  28 */         return getIntsHeatIce(areaX, areaY, areaWidth, areaHeight);
/*     */       case 3:
/*     */         break;
/*  31 */     }  return getIntsSpecial(areaX, areaY, areaWidth, areaHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] getIntsCoolWarm(int p_151626_1_, int p_151626_2_, int p_151626_3_, int p_151626_4_) {
/*  37 */     int var5 = p_151626_1_ - 1;
/*  38 */     int var6 = p_151626_2_ - 1;
/*  39 */     int var7 = 1 + p_151626_3_ + 1;
/*  40 */     int var8 = 1 + p_151626_4_ + 1;
/*  41 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/*  42 */     int[] var10 = IntCache.getIntCache(p_151626_3_ * p_151626_4_);
/*     */     
/*  44 */     for (int var11 = 0; var11 < p_151626_4_; var11++) {
/*     */       
/*  46 */       for (int var12 = 0; var12 < p_151626_3_; var12++) {
/*     */         
/*  48 */         initChunkSeed((var12 + p_151626_1_), (var11 + p_151626_2_));
/*  49 */         int var13 = var9[var12 + 1 + (var11 + 1) * var7];
/*     */         
/*  51 */         if (var13 == 1) {
/*     */           
/*  53 */           int var14 = var9[var12 + 1 + (var11 + 1 - 1) * var7];
/*  54 */           int var15 = var9[var12 + 1 + 1 + (var11 + 1) * var7];
/*  55 */           int var16 = var9[var12 + 1 - 1 + (var11 + 1) * var7];
/*  56 */           int var17 = var9[var12 + 1 + (var11 + 1 + 1) * var7];
/*  57 */           boolean var18 = !(var14 != 3 && var15 != 3 && var16 != 3 && var17 != 3);
/*  58 */           boolean var19 = !(var14 != 4 && var15 != 4 && var16 != 4 && var17 != 4);
/*     */           
/*  60 */           if (var18 || var19)
/*     */           {
/*  62 */             var13 = 2;
/*     */           }
/*     */         } 
/*     */         
/*  66 */         var10[var12 + var11 * p_151626_3_] = var13;
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     return var10;
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] getIntsHeatIce(int p_151624_1_, int p_151624_2_, int p_151624_3_, int p_151624_4_) {
/*  75 */     int var5 = p_151624_1_ - 1;
/*  76 */     int var6 = p_151624_2_ - 1;
/*  77 */     int var7 = 1 + p_151624_3_ + 1;
/*  78 */     int var8 = 1 + p_151624_4_ + 1;
/*  79 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/*  80 */     int[] var10 = IntCache.getIntCache(p_151624_3_ * p_151624_4_);
/*     */     
/*  82 */     for (int var11 = 0; var11 < p_151624_4_; var11++) {
/*     */       
/*  84 */       for (int var12 = 0; var12 < p_151624_3_; var12++) {
/*     */         
/*  86 */         int var13 = var9[var12 + 1 + (var11 + 1) * var7];
/*     */         
/*  88 */         if (var13 == 4) {
/*     */           
/*  90 */           int var14 = var9[var12 + 1 + (var11 + 1 - 1) * var7];
/*  91 */           int var15 = var9[var12 + 1 + 1 + (var11 + 1) * var7];
/*  92 */           int var16 = var9[var12 + 1 - 1 + (var11 + 1) * var7];
/*  93 */           int var17 = var9[var12 + 1 + (var11 + 1 + 1) * var7];
/*  94 */           boolean var18 = !(var14 != 2 && var15 != 2 && var16 != 2 && var17 != 2);
/*  95 */           boolean var19 = !(var14 != 1 && var15 != 1 && var16 != 1 && var17 != 1);
/*     */           
/*  97 */           if (var19 || var18)
/*     */           {
/*  99 */             var13 = 3;
/*     */           }
/*     */         } 
/*     */         
/* 103 */         var10[var12 + var11 * p_151624_3_] = var13;
/*     */       } 
/*     */     } 
/*     */     
/* 107 */     return var10;
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] getIntsSpecial(int p_151625_1_, int p_151625_2_, int p_151625_3_, int p_151625_4_) {
/* 112 */     int[] var5 = this.parent.getInts(p_151625_1_, p_151625_2_, p_151625_3_, p_151625_4_);
/* 113 */     int[] var6 = IntCache.getIntCache(p_151625_3_ * p_151625_4_);
/*     */     
/* 115 */     for (int var7 = 0; var7 < p_151625_4_; var7++) {
/*     */       
/* 117 */       for (int var8 = 0; var8 < p_151625_3_; var8++) {
/*     */         
/* 119 */         initChunkSeed((var8 + p_151625_1_), (var7 + p_151625_2_));
/* 120 */         int var9 = var5[var8 + var7 * p_151625_3_];
/*     */         
/* 122 */         if (var9 != 0 && nextInt(13) == 0)
/*     */         {
/* 124 */           var9 |= 1 + nextInt(15) << 8 & 0xF00;
/*     */         }
/*     */         
/* 127 */         var6[var8 + var7 * p_151625_3_] = var9;
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     return var6;
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */   {
/* 136 */     COOL_WARM("COOL_WARM", 0),
/* 137 */     HEAT_ICE("HEAT_ICE", 1),
/* 138 */     SPECIAL("SPECIAL", 2);
/*     */     
/* 140 */     private static final Mode[] $VALUES = new Mode[] { COOL_WARM, HEAT_ICE, SPECIAL };
/*     */     private static final String __OBFID = "CL_00000549";
/*     */     
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/*     */   static final class SwitchMode {
/* 148 */     static final int[] field_151642_a = new int[(GenLayerEdge.Mode.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00000548";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 155 */         field_151642_a[GenLayerEdge.Mode.COOL_WARM.ordinal()] = 1;
/*     */       }
/* 157 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 164 */         field_151642_a[GenLayerEdge.Mode.HEAT_ICE.ordinal()] = 2;
/*     */       }
/* 166 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 173 */         field_151642_a[GenLayerEdge.Mode.SPECIAL.ordinal()] = 3;
/*     */       }
/* 175 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerEdge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */