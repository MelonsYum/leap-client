/*     */ package net.minecraft.world.pathfinder;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WalkNodeProcessor
/*     */   extends NodeProcessor
/*     */ {
/*     */   private boolean field_176180_f;
/*     */   private boolean field_176181_g;
/*     */   private boolean field_176183_h;
/*     */   private boolean field_176184_i;
/*     */   private boolean field_176182_j;
/*     */   private static final String __OBFID = "CL_00001965";
/*     */   
/*     */   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
/*  28 */     super.func_176162_a(p_176162_1_, p_176162_2_);
/*  29 */     this.field_176182_j = this.field_176183_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176163_a() {
/*  34 */     super.func_176163_a();
/*  35 */     this.field_176183_h = this.field_176182_j;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint func_176161_a(Entity p_176161_1_) {
/*     */     int var2;
/*  42 */     if (this.field_176184_i && p_176161_1_.isInWater()) {
/*     */       
/*  44 */       var2 = (int)(p_176161_1_.getEntityBoundingBox()).minY;
/*     */       
/*  46 */       for (Block var3 = this.field_176169_a.getBlockState(new BlockPos(MathHelper.floor_double(p_176161_1_.posX), var2, MathHelper.floor_double(p_176161_1_.posZ))).getBlock(); var3 == Blocks.flowing_water || var3 == Blocks.water; var3 = this.field_176169_a.getBlockState(new BlockPos(MathHelper.floor_double(p_176161_1_.posX), var2, MathHelper.floor_double(p_176161_1_.posZ))).getBlock())
/*     */       {
/*  48 */         var2++;
/*     */       }
/*     */       
/*  51 */       this.field_176183_h = false;
/*     */     }
/*     */     else {
/*     */       
/*  55 */       var2 = MathHelper.floor_double((p_176161_1_.getEntityBoundingBox()).minY + 0.5D);
/*     */     } 
/*     */     
/*  58 */     return func_176159_a(MathHelper.floor_double((p_176161_1_.getEntityBoundingBox()).minX), var2, MathHelper.floor_double((p_176161_1_.getEntityBoundingBox()).minZ));
/*     */   }
/*     */ 
/*     */   
/*     */   public PathPoint func_176160_a(Entity p_176160_1_, double p_176160_2_, double p_176160_4_, double p_176160_6_) {
/*  63 */     return func_176159_a(MathHelper.floor_double(p_176160_2_ - (p_176160_1_.width / 2.0F)), MathHelper.floor_double(p_176160_4_), MathHelper.floor_double(p_176160_6_ - (p_176160_1_.width / 2.0F)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_176164_a(PathPoint[] p_176164_1_, Entity p_176164_2_, PathPoint p_176164_3_, PathPoint p_176164_4_, float p_176164_5_) {
/*  68 */     int var6 = 0;
/*  69 */     byte var7 = 0;
/*     */     
/*  71 */     if (func_176177_a(p_176164_2_, p_176164_3_.xCoord, p_176164_3_.yCoord + 1, p_176164_3_.zCoord) == 1)
/*     */     {
/*  73 */       var7 = 1;
/*     */     }
/*     */     
/*  76 */     PathPoint var8 = func_176171_a(p_176164_2_, p_176164_3_.xCoord, p_176164_3_.yCoord, p_176164_3_.zCoord + 1, var7);
/*  77 */     PathPoint var9 = func_176171_a(p_176164_2_, p_176164_3_.xCoord - 1, p_176164_3_.yCoord, p_176164_3_.zCoord, var7);
/*  78 */     PathPoint var10 = func_176171_a(p_176164_2_, p_176164_3_.xCoord + 1, p_176164_3_.yCoord, p_176164_3_.zCoord, var7);
/*  79 */     PathPoint var11 = func_176171_a(p_176164_2_, p_176164_3_.xCoord, p_176164_3_.yCoord, p_176164_3_.zCoord - 1, var7);
/*     */     
/*  81 */     if (var8 != null && !var8.visited && var8.distanceTo(p_176164_4_) < p_176164_5_)
/*     */     {
/*  83 */       p_176164_1_[var6++] = var8;
/*     */     }
/*     */     
/*  86 */     if (var9 != null && !var9.visited && var9.distanceTo(p_176164_4_) < p_176164_5_)
/*     */     {
/*  88 */       p_176164_1_[var6++] = var9;
/*     */     }
/*     */     
/*  91 */     if (var10 != null && !var10.visited && var10.distanceTo(p_176164_4_) < p_176164_5_)
/*     */     {
/*  93 */       p_176164_1_[var6++] = var10;
/*     */     }
/*     */     
/*  96 */     if (var11 != null && !var11.visited && var11.distanceTo(p_176164_4_) < p_176164_5_)
/*     */     {
/*  98 */       p_176164_1_[var6++] = var11;
/*     */     }
/*     */     
/* 101 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   private PathPoint func_176171_a(Entity p_176171_1_, int p_176171_2_, int p_176171_3_, int p_176171_4_, int p_176171_5_) {
/* 106 */     PathPoint var6 = null;
/* 107 */     int var7 = func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_, p_176171_4_);
/*     */     
/* 109 */     if (var7 == 2)
/*     */     {
/* 111 */       return func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_);
/*     */     }
/*     */ 
/*     */     
/* 115 */     if (var7 == 1)
/*     */     {
/* 117 */       var6 = func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_);
/*     */     }
/*     */     
/* 120 */     if (var6 == null && p_176171_5_ > 0 && var7 != -3 && var7 != -4 && func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_ + p_176171_5_, p_176171_4_) == 1) {
/*     */       
/* 122 */       var6 = func_176159_a(p_176171_2_, p_176171_3_ + p_176171_5_, p_176171_4_);
/* 123 */       p_176171_3_ += p_176171_5_;
/*     */     } 
/*     */     
/* 126 */     if (var6 != null) {
/*     */       
/* 128 */       int var8 = 0;
/*     */       
/*     */       int var9;
/* 131 */       for (var9 = 0; p_176171_3_ > 0; var6 = func_176159_a(p_176171_2_, p_176171_3_, p_176171_4_)) {
/*     */         
/* 133 */         var9 = func_176177_a(p_176171_1_, p_176171_2_, p_176171_3_ - 1, p_176171_4_);
/*     */         
/* 135 */         if (this.field_176183_h && var9 == -1)
/*     */         {
/* 137 */           return null;
/*     */         }
/*     */         
/* 140 */         if (var9 != 1) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 145 */         if (var8++ >= p_176171_1_.getMaxFallHeight())
/*     */         {
/* 147 */           return null;
/*     */         }
/*     */         
/* 150 */         p_176171_3_--;
/*     */         
/* 152 */         if (p_176171_3_ <= 0)
/*     */         {
/* 154 */           return null;
/*     */         }
/*     */       } 
/*     */       
/* 158 */       if (var9 == -2)
/*     */       {
/* 160 */         return null;
/*     */       }
/*     */     } 
/*     */     
/* 164 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int func_176177_a(Entity p_176177_1_, int p_176177_2_, int p_176177_3_, int p_176177_4_) {
/* 170 */     return func_176170_a(this.field_176169_a, p_176177_1_, p_176177_2_, p_176177_3_, p_176177_4_, this.field_176168_c, this.field_176165_d, this.field_176166_e, this.field_176183_h, this.field_176181_g, this.field_176180_f);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_176170_a(IBlockAccess p_176170_0_, Entity p_176170_1_, int p_176170_2_, int p_176170_3_, int p_176170_4_, int p_176170_5_, int p_176170_6_, int p_176170_7_, boolean p_176170_8_, boolean p_176170_9_, boolean p_176170_10_) {
/* 175 */     boolean var11 = false;
/* 176 */     BlockPos var12 = new BlockPos(p_176170_1_);
/*     */     
/* 178 */     for (int var13 = p_176170_2_; var13 < p_176170_2_ + p_176170_5_; var13++) {
/*     */       
/* 180 */       for (int var14 = p_176170_3_; var14 < p_176170_3_ + p_176170_6_; var14++) {
/*     */         
/* 182 */         for (int var15 = p_176170_4_; var15 < p_176170_4_ + p_176170_7_; var15++) {
/*     */           
/* 184 */           BlockPos var16 = new BlockPos(var13, var14, var15);
/* 185 */           Block var17 = p_176170_0_.getBlockState(var16).getBlock();
/*     */           
/* 187 */           if (var17.getMaterial() != Material.air) {
/*     */             
/* 189 */             if (var17 != Blocks.trapdoor && var17 != Blocks.iron_trapdoor) {
/*     */               
/* 191 */               if (var17 != Blocks.flowing_water && var17 != Blocks.water)
/*     */               {
/* 193 */                 if (!p_176170_10_ && var17 instanceof net.minecraft.block.BlockDoor && var17.getMaterial() == Material.wood)
/*     */                 {
/* 195 */                   return 0;
/*     */                 }
/*     */               }
/*     */               else
/*     */               {
/* 200 */                 if (p_176170_8_)
/*     */                 {
/* 202 */                   return -1;
/*     */                 }
/*     */                 
/* 205 */                 var11 = true;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 210 */               var11 = true;
/*     */             } 
/*     */             
/* 213 */             if (p_176170_1_.worldObj.getBlockState(var16).getBlock() instanceof net.minecraft.block.BlockRailBase) {
/*     */               
/* 215 */               if (!(p_176170_1_.worldObj.getBlockState(var12).getBlock() instanceof net.minecraft.block.BlockRailBase) && !(p_176170_1_.worldObj.getBlockState(var12.offsetDown()).getBlock() instanceof net.minecraft.block.BlockRailBase))
/*     */               {
/* 217 */                 return -3;
/*     */               }
/*     */             }
/* 220 */             else if (!var17.isPassable(p_176170_0_, var16) && (!p_176170_9_ || !(var17 instanceof net.minecraft.block.BlockDoor) || var17.getMaterial() != Material.wood)) {
/*     */               
/* 222 */               if (var17 instanceof net.minecraft.block.BlockFence || var17 instanceof net.minecraft.block.BlockFenceGate || var17 instanceof net.minecraft.block.BlockWall)
/*     */               {
/* 224 */                 return -3;
/*     */               }
/*     */               
/* 227 */               if (var17 == Blocks.trapdoor || var17 == Blocks.iron_trapdoor)
/*     */               {
/* 229 */                 return -4;
/*     */               }
/*     */               
/* 232 */               Material var18 = var17.getMaterial();
/*     */               
/* 234 */               if (var18 != Material.lava)
/*     */               {
/* 236 */                 return 0;
/*     */               }
/*     */               
/* 239 */               if (!p_176170_1_.func_180799_ab())
/*     */               {
/* 241 */                 return -2;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 249 */     return var11 ? 2 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176175_a(boolean p_176175_1_) {
/* 254 */     this.field_176180_f = p_176175_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176172_b(boolean p_176172_1_) {
/* 259 */     this.field_176181_g = p_176172_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176176_c(boolean p_176176_1_) {
/* 264 */     this.field_176183_h = p_176176_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176178_d(boolean p_176178_1_) {
/* 269 */     this.field_176184_i = p_176178_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176179_b() {
/* 274 */     return this.field_176180_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176174_d() {
/* 279 */     return this.field_176184_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176173_e() {
/* 284 */     return this.field_176183_h;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\pathfinder\WalkNodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */