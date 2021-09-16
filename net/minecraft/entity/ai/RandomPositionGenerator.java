/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomPositionGenerator
/*     */ {
/*  15 */   private static Vec3 staticVector = new Vec3(0.0D, 0.0D, 0.0D);
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001629";
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 findRandomTarget(EntityCreature p_75463_0_, int p_75463_1_, int p_75463_2_) {
/*  23 */     return findRandomTargetBlock(p_75463_0_, p_75463_1_, p_75463_2_, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 findRandomTargetBlockTowards(EntityCreature p_75464_0_, int p_75464_1_, int p_75464_2_, Vec3 p_75464_3_) {
/*  31 */     staticVector = p_75464_3_.subtract(p_75464_0_.posX, p_75464_0_.posY, p_75464_0_.posZ);
/*  32 */     return findRandomTargetBlock(p_75464_0_, p_75464_1_, p_75464_2_, staticVector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vec3 findRandomTargetBlockAwayFrom(EntityCreature p_75461_0_, int p_75461_1_, int p_75461_2_, Vec3 p_75461_3_) {
/*  40 */     staticVector = (new Vec3(p_75461_0_.posX, p_75461_0_.posY, p_75461_0_.posZ)).subtract(p_75461_3_);
/*  41 */     return findRandomTargetBlock(p_75461_0_, p_75461_1_, p_75461_2_, staticVector);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Vec3 findRandomTargetBlock(EntityCreature p_75462_0_, int p_75462_1_, int p_75462_2_, Vec3 p_75462_3_) {
/*     */     boolean var10;
/*  50 */     Random var4 = p_75462_0_.getRNG();
/*  51 */     boolean var5 = false;
/*  52 */     int var6 = 0;
/*  53 */     int var7 = 0;
/*  54 */     int var8 = 0;
/*  55 */     float var9 = -99999.0F;
/*     */ 
/*     */     
/*  58 */     if (p_75462_0_.hasHome()) {
/*     */       
/*  60 */       double var11 = p_75462_0_.func_180486_cf().distanceSq(MathHelper.floor_double(p_75462_0_.posX), MathHelper.floor_double(p_75462_0_.posY), MathHelper.floor_double(p_75462_0_.posZ)) + 4.0D;
/*  61 */       double var13 = (p_75462_0_.getMaximumHomeDistance() + p_75462_1_);
/*  62 */       var10 = (var11 < var13 * var13);
/*     */     }
/*     */     else {
/*     */       
/*  66 */       var10 = false;
/*     */     } 
/*     */     
/*  69 */     for (int var17 = 0; var17 < 10; var17++) {
/*     */       
/*  71 */       int var12 = var4.nextInt(2 * p_75462_1_ + 1) - p_75462_1_;
/*  72 */       int var18 = var4.nextInt(2 * p_75462_2_ + 1) - p_75462_2_;
/*  73 */       int var14 = var4.nextInt(2 * p_75462_1_ + 1) - p_75462_1_;
/*     */       
/*  75 */       if (p_75462_3_ == null || var12 * p_75462_3_.xCoord + var14 * p_75462_3_.zCoord >= 0.0D) {
/*     */ 
/*     */ 
/*     */         
/*  79 */         if (p_75462_0_.hasHome() && p_75462_1_ > 1) {
/*     */           
/*  81 */           BlockPos blockPos = p_75462_0_.func_180486_cf();
/*     */           
/*  83 */           if (p_75462_0_.posX > blockPos.getX()) {
/*     */             
/*  85 */             var12 -= var4.nextInt(p_75462_1_ / 2);
/*     */           }
/*     */           else {
/*     */             
/*  89 */             var12 += var4.nextInt(p_75462_1_ / 2);
/*     */           } 
/*     */           
/*  92 */           if (p_75462_0_.posZ > blockPos.getZ()) {
/*     */             
/*  94 */             var14 -= var4.nextInt(p_75462_1_ / 2);
/*     */           }
/*     */           else {
/*     */             
/*  98 */             var14 += var4.nextInt(p_75462_1_ / 2);
/*     */           } 
/*     */         } 
/*     */         
/* 102 */         var12 += MathHelper.floor_double(p_75462_0_.posX);
/* 103 */         var18 += MathHelper.floor_double(p_75462_0_.posY);
/* 104 */         var14 += MathHelper.floor_double(p_75462_0_.posZ);
/* 105 */         BlockPos var15 = new BlockPos(var12, var18, var14);
/*     */         
/* 107 */         if (!var10 || p_75462_0_.func_180485_d(var15)) {
/*     */           
/* 109 */           float var16 = p_75462_0_.func_180484_a(var15);
/*     */           
/* 111 */           if (var16 > var9) {
/*     */             
/* 113 */             var9 = var16;
/* 114 */             var6 = var12;
/* 115 */             var7 = var18;
/* 116 */             var8 = var14;
/* 117 */             var5 = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     if (var5)
/*     */     {
/* 125 */       return new Vec3(var6, var7, var8);
/*     */     }
/*     */ 
/*     */     
/* 129 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\RandomPositionGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */