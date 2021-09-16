/*     */ package net.minecraft.pathfinding;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.pathfinder.NodeProcessor;
/*     */ import net.minecraft.world.pathfinder.WalkNodeProcessor;
/*     */ 
/*     */ public class PathNavigateGround
/*     */   extends PathNavigate
/*     */ {
/*     */   protected WalkNodeProcessor field_179695_a;
/*     */   private boolean field_179694_f;
/*     */   private static final String __OBFID = "CL_00002246";
/*     */   
/*     */   public PathNavigateGround(EntityLiving p_i45875_1_, World worldIn) {
/*  24 */     super(p_i45875_1_, worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathFinder func_179679_a() {
/*  29 */     this.field_179695_a = new WalkNodeProcessor();
/*  30 */     this.field_179695_a.func_176175_a(true);
/*  31 */     return new PathFinder((NodeProcessor)this.field_179695_a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canNavigate() {
/*  39 */     return !(!this.theEntity.onGround && (!func_179684_h() || !isInLiquid()) && (!this.theEntity.isRiding() || !(this.theEntity instanceof net.minecraft.entity.monster.EntityZombie) || !(this.theEntity.ridingEntity instanceof net.minecraft.entity.passive.EntityChicken)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Vec3 getEntityPosition() {
/*  44 */     return new Vec3(this.theEntity.posX, func_179687_p(), this.theEntity.posZ);
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_179687_p() {
/*  49 */     if (this.theEntity.isInWater() && func_179684_h()) {
/*     */       
/*  51 */       int var1 = (int)(this.theEntity.getEntityBoundingBox()).minY;
/*  52 */       Block var2 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), var1, MathHelper.floor_double(this.theEntity.posZ))).getBlock();
/*  53 */       int var3 = 0;
/*     */ 
/*     */       
/*     */       do {
/*  57 */         if (var2 != Blocks.flowing_water && var2 != Blocks.water)
/*     */         {
/*  59 */           return var1;
/*     */         }
/*     */         
/*  62 */         var1++;
/*  63 */         var2 = this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), var1, MathHelper.floor_double(this.theEntity.posZ))).getBlock();
/*  64 */         ++var3;
/*     */       }
/*  66 */       while (var3 <= 16);
/*     */       
/*  68 */       return (int)(this.theEntity.getEntityBoundingBox()).minY;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return (int)((this.theEntity.getEntityBoundingBox()).minY + 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeSunnyPath() {
/*  81 */     super.removeSunnyPath();
/*     */     
/*  83 */     if (this.field_179694_f) {
/*     */       
/*  85 */       if (this.worldObj.isAgainstSky(new BlockPos(MathHelper.floor_double(this.theEntity.posX), (int)((this.theEntity.getEntityBoundingBox()).minY + 0.5D), MathHelper.floor_double(this.theEntity.posZ)))) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  90 */       for (int var1 = 0; var1 < this.currentPath.getCurrentPathLength(); var1++) {
/*     */         
/*  92 */         PathPoint var2 = this.currentPath.getPathPointFromIndex(var1);
/*     */         
/*  94 */         if (this.worldObj.isAgainstSky(new BlockPos(var2.xCoord, var2.yCoord, var2.zCoord))) {
/*     */           
/*  96 */           this.currentPath.setCurrentPathLength(var1 - 1);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDirectPathBetweenPoints(Vec3 p_75493_1_, Vec3 p_75493_2_, int p_75493_3_, int p_75493_4_, int p_75493_5_) {
/* 109 */     int var6 = MathHelper.floor_double(p_75493_1_.xCoord);
/* 110 */     int var7 = MathHelper.floor_double(p_75493_1_.zCoord);
/* 111 */     double var8 = p_75493_2_.xCoord - p_75493_1_.xCoord;
/* 112 */     double var10 = p_75493_2_.zCoord - p_75493_1_.zCoord;
/* 113 */     double var12 = var8 * var8 + var10 * var10;
/*     */     
/* 115 */     if (var12 < 1.0E-8D)
/*     */     {
/* 117 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 121 */     double var14 = 1.0D / Math.sqrt(var12);
/* 122 */     var8 *= var14;
/* 123 */     var10 *= var14;
/* 124 */     p_75493_3_ += 2;
/* 125 */     p_75493_5_ += 2;
/*     */     
/* 127 */     if (!func_179683_a(var6, (int)p_75493_1_.yCoord, var7, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, var8, var10))
/*     */     {
/* 129 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 133 */     p_75493_3_ -= 2;
/* 134 */     p_75493_5_ -= 2;
/* 135 */     double var16 = 1.0D / Math.abs(var8);
/* 136 */     double var18 = 1.0D / Math.abs(var10);
/* 137 */     double var20 = (var6 * 1) - p_75493_1_.xCoord;
/* 138 */     double var22 = (var7 * 1) - p_75493_1_.zCoord;
/*     */     
/* 140 */     if (var8 >= 0.0D)
/*     */     {
/* 142 */       var20++;
/*     */     }
/*     */     
/* 145 */     if (var10 >= 0.0D)
/*     */     {
/* 147 */       var22++;
/*     */     }
/*     */     
/* 150 */     var20 /= var8;
/* 151 */     var22 /= var10;
/* 152 */     int var24 = (var8 < 0.0D) ? -1 : 1;
/* 153 */     int var25 = (var10 < 0.0D) ? -1 : 1;
/* 154 */     int var26 = MathHelper.floor_double(p_75493_2_.xCoord);
/* 155 */     int var27 = MathHelper.floor_double(p_75493_2_.zCoord);
/* 156 */     int var28 = var26 - var6;
/* 157 */     int var29 = var27 - var7;
/*     */ 
/*     */     
/*     */     do {
/* 161 */       if (var28 * var24 <= 0 && var29 * var25 <= 0)
/*     */       {
/* 163 */         return true;
/*     */       }
/*     */       
/* 166 */       if (var20 < var22)
/*     */       {
/* 168 */         var20 += var16;
/* 169 */         var6 += var24;
/* 170 */         var28 = var26 - var6;
/*     */       }
/*     */       else
/*     */       {
/* 174 */         var22 += var18;
/* 175 */         var7 += var25;
/* 176 */         var29 = var27 - var7;
/*     */       }
/*     */     
/* 179 */     } while (func_179683_a(var6, (int)p_75493_1_.yCoord, var7, p_75493_3_, p_75493_4_, p_75493_5_, p_75493_1_, var8, var10));
/*     */     
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_179683_a(int p_179683_1_, int p_179683_2_, int p_179683_3_, int p_179683_4_, int p_179683_5_, int p_179683_6_, Vec3 p_179683_7_, double p_179683_8_, double p_179683_10_) {
/* 188 */     int var12 = p_179683_1_ - p_179683_4_ / 2;
/* 189 */     int var13 = p_179683_3_ - p_179683_6_ / 2;
/*     */     
/* 191 */     if (!func_179692_b(var12, p_179683_2_, var13, p_179683_4_, p_179683_5_, p_179683_6_, p_179683_7_, p_179683_8_, p_179683_10_))
/*     */     {
/* 193 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 197 */     for (int var14 = var12; var14 < var12 + p_179683_4_; var14++) {
/*     */       
/* 199 */       for (int var15 = var13; var15 < var13 + p_179683_6_; var15++) {
/*     */         
/* 201 */         double var16 = var14 + 0.5D - p_179683_7_.xCoord;
/* 202 */         double var18 = var15 + 0.5D - p_179683_7_.zCoord;
/*     */         
/* 204 */         if (var16 * p_179683_8_ + var18 * p_179683_10_ >= 0.0D) {
/*     */           
/* 206 */           Block var20 = this.worldObj.getBlockState(new BlockPos(var14, p_179683_2_ - 1, var15)).getBlock();
/* 207 */           Material var21 = var20.getMaterial();
/*     */           
/* 209 */           if (var21 == Material.air)
/*     */           {
/* 211 */             return false;
/*     */           }
/*     */           
/* 214 */           if (var21 == Material.water && !this.theEntity.isInWater())
/*     */           {
/* 216 */             return false;
/*     */           }
/*     */           
/* 219 */           if (var21 == Material.lava)
/*     */           {
/* 221 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_179692_b(int p_179692_1_, int p_179692_2_, int p_179692_3_, int p_179692_4_, int p_179692_5_, int p_179692_6_, Vec3 p_179692_7_, double p_179692_8_, double p_179692_10_) {
/* 233 */     Iterator<BlockPos> var12 = BlockPos.getAllInBox(new BlockPos(p_179692_1_, p_179692_2_, p_179692_3_), new BlockPos(p_179692_1_ + p_179692_4_ - 1, p_179692_2_ + p_179692_5_ - 1, p_179692_3_ + p_179692_6_ - 1)).iterator();
/*     */     
/* 235 */     while (var12.hasNext()) {
/*     */       
/* 237 */       BlockPos var13 = var12.next();
/* 238 */       double var14 = var13.getX() + 0.5D - p_179692_7_.xCoord;
/* 239 */       double var16 = var13.getZ() + 0.5D - p_179692_7_.zCoord;
/*     */       
/* 241 */       if (var14 * p_179692_8_ + var16 * p_179692_10_ >= 0.0D) {
/*     */         
/* 243 */         Block var18 = this.worldObj.getBlockState(var13).getBlock();
/*     */         
/* 245 */         if (!var18.isPassable((IBlockAccess)this.worldObj, var13))
/*     */         {
/* 247 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179690_a(boolean p_179690_1_) {
/* 257 */     this.field_179695_a.func_176176_c(p_179690_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179689_e() {
/* 262 */     return this.field_179695_a.func_176173_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179688_b(boolean p_179688_1_) {
/* 267 */     this.field_179695_a.func_176172_b(p_179688_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179691_c(boolean p_179691_1_) {
/* 272 */     this.field_179695_a.func_176175_a(p_179691_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179686_g() {
/* 277 */     return this.field_179695_a.func_176179_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179693_d(boolean p_179693_1_) {
/* 282 */     this.field_179695_a.func_176178_d(p_179693_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179684_h() {
/* 287 */     return this.field_179695_a.func_176174_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179685_e(boolean p_179685_1_) {
/* 292 */     this.field_179694_f = p_179685_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathNavigateGround.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */