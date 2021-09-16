/*     */ package net.minecraft.pathfinding;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.ChunkCache;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
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
/*     */ public abstract class PathNavigate
/*     */ {
/*     */   protected EntityLiving theEntity;
/*     */   protected World worldObj;
/*     */   protected PathEntity currentPath;
/*     */   protected double speed;
/*     */   private final IAttributeInstance pathSearchRange;
/*     */   private int totalTicks;
/*     */   private int ticksAtLastPos;
/*  38 */   private Vec3 lastPosCheck = new Vec3(0.0D, 0.0D, 0.0D);
/*  39 */   private float field_179682_i = 1.0F;
/*     */   
/*     */   private final PathFinder field_179681_j;
/*     */   private static final String __OBFID = "CL_00001627";
/*     */   
/*     */   public PathNavigate(EntityLiving p_i1671_1_, World worldIn) {
/*  45 */     this.theEntity = p_i1671_1_;
/*  46 */     this.worldObj = worldIn;
/*  47 */     this.pathSearchRange = p_i1671_1_.getEntityAttribute(SharedMonsterAttributes.followRange);
/*  48 */     this.field_179681_j = func_179679_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract PathFinder func_179679_a();
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpeed(double p_75489_1_) {
/*  58 */     this.speed = p_75489_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPathSearchRange() {
/*  66 */     return (float)this.pathSearchRange.getAttributeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final PathEntity getPathToXYZ(double p_75488_1_, double p_75488_3_, double p_75488_5_) {
/*  74 */     return func_179680_a(new BlockPos(MathHelper.floor_double(p_75488_1_), (int)p_75488_3_, MathHelper.floor_double(p_75488_5_)));
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity func_179680_a(BlockPos p_179680_1_) {
/*  79 */     if (!canNavigate())
/*     */     {
/*  81 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  85 */     float var2 = getPathSearchRange();
/*  86 */     this.worldObj.theProfiler.startSection("pathfind");
/*  87 */     BlockPos var3 = new BlockPos((Entity)this.theEntity);
/*  88 */     int var4 = (int)(var2 + 8.0F);
/*  89 */     ChunkCache var5 = new ChunkCache(this.worldObj, var3.add(-var4, -var4, -var4), var3.add(var4, var4, var4), 0);
/*  90 */     PathEntity var6 = this.field_179681_j.func_180782_a((IBlockAccess)var5, (Entity)this.theEntity, p_179680_1_, var2);
/*  91 */     this.worldObj.theProfiler.endSection();
/*  92 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryMoveToXYZ(double p_75492_1_, double p_75492_3_, double p_75492_5_, double p_75492_7_) {
/* 101 */     PathEntity var9 = getPathToXYZ(MathHelper.floor_double(p_75492_1_), (int)p_75492_3_, MathHelper.floor_double(p_75492_5_));
/* 102 */     return setPath(var9, p_75492_7_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179678_a(float p_179678_1_) {
/* 107 */     this.field_179682_i = p_179678_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity getPathToEntityLiving(Entity p_75494_1_) {
/* 115 */     if (!canNavigate())
/*     */     {
/* 117 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 121 */     float var2 = getPathSearchRange();
/* 122 */     this.worldObj.theProfiler.startSection("pathfind");
/* 123 */     BlockPos var3 = (new BlockPos((Entity)this.theEntity)).offsetUp();
/* 124 */     int var4 = (int)(var2 + 16.0F);
/* 125 */     ChunkCache var5 = new ChunkCache(this.worldObj, var3.add(-var4, -var4, -var4), var3.add(var4, var4, var4), 0);
/* 126 */     PathEntity var6 = this.field_179681_j.func_176188_a((IBlockAccess)var5, (Entity)this.theEntity, p_75494_1_, var2);
/* 127 */     this.worldObj.theProfiler.endSection();
/* 128 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean tryMoveToEntityLiving(Entity p_75497_1_, double p_75497_2_) {
/* 137 */     PathEntity var4 = getPathToEntityLiving(p_75497_1_);
/* 138 */     return (var4 != null) ? setPath(var4, p_75497_2_) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setPath(PathEntity p_75484_1_, double p_75484_2_) {
/* 147 */     if (p_75484_1_ == null) {
/*     */       
/* 149 */       this.currentPath = null;
/* 150 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 154 */     if (!p_75484_1_.isSamePath(this.currentPath))
/*     */     {
/* 156 */       this.currentPath = p_75484_1_;
/*     */     }
/*     */     
/* 159 */     removeSunnyPath();
/*     */     
/* 161 */     if (this.currentPath.getCurrentPathLength() == 0)
/*     */     {
/* 163 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 167 */     this.speed = p_75484_2_;
/* 168 */     Vec3 var4 = getEntityPosition();
/* 169 */     this.ticksAtLastPos = this.totalTicks;
/* 170 */     this.lastPosCheck = var4;
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathEntity getPath() {
/* 181 */     return this.currentPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateNavigation() {
/* 186 */     this.totalTicks++;
/*     */     
/* 188 */     if (!noPath()) {
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (canNavigate()) {
/*     */         
/* 194 */         pathFollow();
/*     */       }
/* 196 */       else if (this.currentPath != null && this.currentPath.getCurrentPathIndex() < this.currentPath.getCurrentPathLength()) {
/*     */         
/* 198 */         Vec3 var1 = getEntityPosition();
/* 199 */         Vec3 var2 = this.currentPath.getVectorFromIndex((Entity)this.theEntity, this.currentPath.getCurrentPathIndex());
/*     */         
/* 201 */         if (var1.yCoord > var2.yCoord && !this.theEntity.onGround && MathHelper.floor_double(var1.xCoord) == MathHelper.floor_double(var2.xCoord) && MathHelper.floor_double(var1.zCoord) == MathHelper.floor_double(var2.zCoord))
/*     */         {
/* 203 */           this.currentPath.setCurrentPathIndex(this.currentPath.getCurrentPathIndex() + 1);
/*     */         }
/*     */       } 
/*     */       
/* 207 */       if (!noPath()) {
/*     */         
/* 209 */         Vec3 var1 = this.currentPath.getPosition((Entity)this.theEntity);
/*     */         
/* 211 */         if (var1 != null)
/*     */         {
/* 213 */           this.theEntity.getMoveHelper().setMoveTo(var1.xCoord, var1.yCoord, var1.zCoord, this.speed);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void pathFollow() {
/* 221 */     Vec3 var1 = getEntityPosition();
/* 222 */     int var2 = this.currentPath.getCurrentPathLength();
/*     */     
/* 224 */     for (int var3 = this.currentPath.getCurrentPathIndex(); var3 < this.currentPath.getCurrentPathLength(); var3++) {
/*     */       
/* 226 */       if ((this.currentPath.getPathPointFromIndex(var3)).yCoord != (int)var1.yCoord) {
/*     */         
/* 228 */         var2 = var3;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 233 */     float var8 = this.theEntity.width * this.theEntity.width * this.field_179682_i;
/*     */     
/*     */     int var4;
/* 236 */     for (var4 = this.currentPath.getCurrentPathIndex(); var4 < var2; var4++) {
/*     */       
/* 238 */       Vec3 var5 = this.currentPath.getVectorFromIndex((Entity)this.theEntity, var4);
/*     */       
/* 240 */       if (var1.squareDistanceTo(var5) < var8)
/*     */       {
/* 242 */         this.currentPath.setCurrentPathIndex(var4 + 1);
/*     */       }
/*     */     } 
/*     */     
/* 246 */     var4 = MathHelper.ceiling_float_int(this.theEntity.width);
/* 247 */     int var9 = (int)this.theEntity.height + 1;
/* 248 */     int var6 = var4;
/*     */     
/* 250 */     for (int var7 = var2 - 1; var7 >= this.currentPath.getCurrentPathIndex(); var7--) {
/*     */       
/* 252 */       if (isDirectPathBetweenPoints(var1, this.currentPath.getVectorFromIndex((Entity)this.theEntity, var7), var4, var9, var6)) {
/*     */         
/* 254 */         this.currentPath.setCurrentPathIndex(var7);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 259 */     func_179677_a(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_179677_a(Vec3 p_179677_1_) {
/* 264 */     if (this.totalTicks - this.ticksAtLastPos > 100) {
/*     */       
/* 266 */       if (p_179677_1_.squareDistanceTo(this.lastPosCheck) < 2.25D)
/*     */       {
/* 268 */         clearPathEntity();
/*     */       }
/*     */       
/* 271 */       this.ticksAtLastPos = this.totalTicks;
/* 272 */       this.lastPosCheck = p_179677_1_;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean noPath() {
/* 281 */     return !(this.currentPath != null && !this.currentPath.isFinished());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPathEntity() {
/* 289 */     this.currentPath = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Vec3 getEntityPosition();
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract boolean canNavigate();
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isInLiquid() {
/* 304 */     return !(!this.theEntity.isInWater() && !this.theEntity.func_180799_ab());
/*     */   }
/*     */   
/*     */   protected void removeSunnyPath() {}
/*     */   
/*     */   protected abstract boolean isDirectPathBetweenPoints(Vec3 paramVec31, Vec3 paramVec32, int paramInt1, int paramInt2, int paramInt3);
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathNavigate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */