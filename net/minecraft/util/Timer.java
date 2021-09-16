/*     */ package net.minecraft.util;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Timer
/*     */ {
/*     */   float ticksPerSecond;
/*     */   private double lastHRTime;
/*     */   public int elapsedTicks;
/*     */   public float renderPartialTicks;
/*  30 */   public float timerSpeed = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float elapsedPartialTicks;
/*     */ 
/*     */ 
/*     */   
/*     */   private long lastSyncSysClock;
/*     */ 
/*     */ 
/*     */   
/*     */   private long lastSyncHRClock;
/*     */ 
/*     */ 
/*     */   
/*     */   private long field_74285_i;
/*     */ 
/*     */ 
/*     */   
/*  51 */   private double timeSyncAdjustment = 1.0D;
/*     */   
/*     */   private static final String __OBFID = "CL_00000658";
/*     */   
/*     */   public Timer(float p_i1018_1_) {
/*  56 */     this.ticksPerSecond = p_i1018_1_;
/*  57 */     this.lastSyncSysClock = Minecraft.getSystemTime();
/*  58 */     this.lastSyncHRClock = System.nanoTime() / 1000000L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTimer() {
/*  66 */     long var1 = Minecraft.getSystemTime();
/*  67 */     long var3 = var1 - this.lastSyncSysClock;
/*  68 */     long var5 = System.nanoTime() / 1000000L;
/*  69 */     double var7 = var5 / 1000.0D;
/*     */     
/*  71 */     if (var3 <= 1000L && var3 >= 0L) {
/*     */       
/*  73 */       this.field_74285_i += var3;
/*     */       
/*  75 */       if (this.field_74285_i > 1000L) {
/*     */         
/*  77 */         long var9 = var5 - this.lastSyncHRClock;
/*  78 */         double var11 = this.field_74285_i / var9;
/*  79 */         this.timeSyncAdjustment += (var11 - this.timeSyncAdjustment) * 0.20000000298023224D;
/*  80 */         this.lastSyncHRClock = var5;
/*  81 */         this.field_74285_i = 0L;
/*     */       } 
/*     */       
/*  84 */       if (this.field_74285_i < 0L)
/*     */       {
/*  86 */         this.lastSyncHRClock = var5;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  91 */       this.lastHRTime = var7;
/*     */     } 
/*     */     
/*  94 */     this.lastSyncSysClock = var1;
/*  95 */     double var13 = (var7 - this.lastHRTime) * this.timeSyncAdjustment;
/*  96 */     this.lastHRTime = var7;
/*  97 */     var13 = MathHelper.clamp_double(var13, 0.0D, 1.0D);
/*  98 */     this.elapsedPartialTicks = (float)(this.elapsedPartialTicks + var13 * this.timerSpeed * this.ticksPerSecond);
/*  99 */     this.elapsedTicks = (int)this.elapsedPartialTicks;
/* 100 */     this.elapsedPartialTicks -= this.elapsedTicks;
/*     */     
/* 102 */     if (this.elapsedTicks > 10)
/*     */     {
/* 104 */       this.elapsedTicks = 10;
/*     */     }
/*     */     
/* 107 */     this.renderPartialTicks = this.elapsedPartialTicks;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Timer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */