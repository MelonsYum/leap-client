/*     */ package net.minecraft.world.border;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ 
/*     */ public class WorldBorder
/*     */ {
/*  13 */   private final List listeners = Lists.newArrayList();
/*  14 */   private double centerX = 0.0D;
/*  15 */   private double centerZ = 0.0D;
/*  16 */   private double startDiameter = 6.0E7D;
/*     */   
/*     */   private double endDiameter;
/*     */   private long endTime;
/*     */   private long startTime;
/*     */   private int worldSize;
/*     */   private double damageAmount;
/*     */   private double damageBuffer;
/*     */   private int warningTime;
/*     */   private int warningDistance;
/*     */   private static final String __OBFID = "CL_00002012";
/*     */   
/*     */   public WorldBorder() {
/*  29 */     this.endDiameter = this.startDiameter;
/*  30 */     this.worldSize = 29999984;
/*  31 */     this.damageAmount = 0.2D;
/*  32 */     this.damageBuffer = 5.0D;
/*  33 */     this.warningTime = 15;
/*  34 */     this.warningDistance = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(BlockPos pos) {
/*  39 */     return ((pos.getX() + 1) > minX() && pos.getX() < maxX() && (pos.getZ() + 1) > minZ() && pos.getZ() < maxZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(ChunkCoordIntPair range) {
/*  44 */     return (range.getXEnd() > minX() && range.getXStart() < maxX() && range.getZEnd() > minZ() && range.getZStart() < maxZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(AxisAlignedBB bb) {
/*  49 */     return (bb.maxX > minX() && bb.minX < maxX() && bb.maxZ > minZ() && bb.minZ < maxZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getClosestDistance(Entity p_177745_1_) {
/*  54 */     return getClosestDistance(p_177745_1_.posX, p_177745_1_.posZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getClosestDistance(double x, double z) {
/*  59 */     double var5 = z - minZ();
/*  60 */     double var7 = maxZ() - z;
/*  61 */     double var9 = x - minX();
/*  62 */     double var11 = maxX() - x;
/*  63 */     double var13 = Math.min(var9, var11);
/*  64 */     var13 = Math.min(var13, var5);
/*  65 */     return Math.min(var13, var7);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBorderStatus getStatus() {
/*  70 */     return (this.endDiameter < this.startDiameter) ? EnumBorderStatus.SHRINKING : ((this.endDiameter > this.startDiameter) ? EnumBorderStatus.GROWING : EnumBorderStatus.STATIONARY);
/*     */   }
/*     */ 
/*     */   
/*     */   public double minX() {
/*  75 */     double var1 = getCenterX() - getDiameter() / 2.0D;
/*     */     
/*  77 */     if (var1 < -this.worldSize)
/*     */     {
/*  79 */       var1 = -this.worldSize;
/*     */     }
/*     */     
/*  82 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public double minZ() {
/*  87 */     double var1 = getCenterZ() - getDiameter() / 2.0D;
/*     */     
/*  89 */     if (var1 < -this.worldSize)
/*     */     {
/*  91 */       var1 = -this.worldSize;
/*     */     }
/*     */     
/*  94 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public double maxX() {
/*  99 */     double var1 = getCenterX() + getDiameter() / 2.0D;
/*     */     
/* 101 */     if (var1 > this.worldSize)
/*     */     {
/* 103 */       var1 = this.worldSize;
/*     */     }
/*     */     
/* 106 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public double maxZ() {
/* 111 */     double var1 = getCenterZ() + getDiameter() / 2.0D;
/*     */     
/* 113 */     if (var1 > this.worldSize)
/*     */     {
/* 115 */       var1 = this.worldSize;
/*     */     }
/*     */     
/* 118 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getCenterX() {
/* 123 */     return this.centerX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getCenterZ() {
/* 128 */     return this.centerZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCenter(double x, double z) {
/* 133 */     this.centerX = x;
/* 134 */     this.centerZ = z;
/* 135 */     Iterator<IBorderListener> var5 = getListeners().iterator();
/*     */     
/* 137 */     while (var5.hasNext()) {
/*     */       
/* 139 */       IBorderListener var6 = var5.next();
/* 140 */       var6.onCenterChanged(this, x, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDiameter() {
/* 146 */     if (getStatus() != EnumBorderStatus.STATIONARY) {
/*     */       
/* 148 */       double var1 = ((float)(System.currentTimeMillis() - this.startTime) / (float)(this.endTime - this.startTime));
/*     */       
/* 150 */       if (var1 < 1.0D)
/*     */       {
/* 152 */         return this.startDiameter + (this.endDiameter - this.startDiameter) * var1;
/*     */       }
/*     */       
/* 155 */       setTransition(this.endDiameter);
/*     */     } 
/*     */     
/* 158 */     return this.startDiameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTimeUntilTarget() {
/* 163 */     return (getStatus() != EnumBorderStatus.STATIONARY) ? (this.endTime - System.currentTimeMillis()) : 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getTargetSize() {
/* 168 */     return this.endDiameter;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransition(double newSize) {
/* 173 */     this.startDiameter = newSize;
/* 174 */     this.endDiameter = newSize;
/* 175 */     this.endTime = System.currentTimeMillis();
/* 176 */     this.startTime = this.endTime;
/* 177 */     Iterator<IBorderListener> var3 = getListeners().iterator();
/*     */     
/* 179 */     while (var3.hasNext()) {
/*     */       
/* 181 */       IBorderListener var4 = var3.next();
/* 182 */       var4.onSizeChanged(this, newSize);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTransition(double p_177738_1_, double p_177738_3_, long p_177738_5_) {
/* 188 */     this.startDiameter = p_177738_1_;
/* 189 */     this.endDiameter = p_177738_3_;
/* 190 */     this.startTime = System.currentTimeMillis();
/* 191 */     this.endTime = this.startTime + p_177738_5_;
/* 192 */     Iterator<IBorderListener> var7 = getListeners().iterator();
/*     */     
/* 194 */     while (var7.hasNext()) {
/*     */       
/* 196 */       IBorderListener var8 = var7.next();
/* 197 */       var8.func_177692_a(this, p_177738_1_, p_177738_3_, p_177738_5_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected List getListeners() {
/* 203 */     return Lists.newArrayList(this.listeners);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addListener(IBorderListener listener) {
/* 208 */     this.listeners.add(listener);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(int size) {
/* 213 */     this.worldSize = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 218 */     return this.worldSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamageBuffer() {
/* 223 */     return this.damageBuffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageBuffer(double p_177724_1_) {
/* 228 */     this.damageBuffer = p_177724_1_;
/* 229 */     Iterator<IBorderListener> var3 = getListeners().iterator();
/*     */     
/* 231 */     while (var3.hasNext()) {
/*     */       
/* 233 */       IBorderListener var4 = var3.next();
/* 234 */       var4.func_177695_c(this, p_177724_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_177727_n() {
/* 240 */     return this.damageAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177744_c(double p_177744_1_) {
/* 245 */     this.damageAmount = p_177744_1_;
/* 246 */     Iterator<IBorderListener> var3 = getListeners().iterator();
/*     */     
/* 248 */     while (var3.hasNext()) {
/*     */       
/* 250 */       IBorderListener var4 = var3.next();
/* 251 */       var4.func_177696_b(this, p_177744_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_177749_o() {
/* 257 */     return (this.endTime == this.startTime) ? 0.0D : (Math.abs(this.startDiameter - this.endDiameter) / (this.endTime - this.startTime));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarningTime() {
/* 262 */     return this.warningTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWarningTime(int warningTime) {
/* 267 */     this.warningTime = warningTime;
/* 268 */     Iterator<IBorderListener> var2 = getListeners().iterator();
/*     */     
/* 270 */     while (var2.hasNext()) {
/*     */       
/* 272 */       IBorderListener var3 = var2.next();
/* 273 */       var3.onWarningTimeChanged(this, warningTime);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarningDistance() {
/* 279 */     return this.warningDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWarningDistance(int warningDistance) {
/* 284 */     this.warningDistance = warningDistance;
/* 285 */     Iterator<IBorderListener> var2 = getListeners().iterator();
/*     */     
/* 287 */     while (var2.hasNext()) {
/*     */       
/* 289 */       IBorderListener var3 = var2.next();
/* 290 */       var3.onWarningDistanceChanged(this, warningDistance);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\border\WorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */