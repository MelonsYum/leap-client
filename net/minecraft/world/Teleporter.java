/*     */ package net.minecraft.world;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockPortal;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3i;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Teleporter
/*     */ {
/*     */   private final WorldServer worldServerInstance;
/*     */   private final Random random;
/*  24 */   private final LongHashMap destinationCoordinateCache = new LongHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   private final List destinationCoordinateKeys = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00000153";
/*     */   
/*     */   public Teleporter(WorldServer worldIn) {
/*  35 */     this.worldServerInstance = worldIn;
/*  36 */     this.random = new Random(worldIn.getSeed());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180266_a(Entity p_180266_1_, float p_180266_2_) {
/*  41 */     if (this.worldServerInstance.provider.getDimensionId() != 1) {
/*     */       
/*  43 */       if (!func_180620_b(p_180266_1_, p_180266_2_))
/*     */       {
/*  45 */         makePortal(p_180266_1_);
/*  46 */         func_180620_b(p_180266_1_, p_180266_2_);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  51 */       int var3 = MathHelper.floor_double(p_180266_1_.posX);
/*  52 */       int var4 = MathHelper.floor_double(p_180266_1_.posY) - 1;
/*  53 */       int var5 = MathHelper.floor_double(p_180266_1_.posZ);
/*  54 */       byte var6 = 1;
/*  55 */       byte var7 = 0;
/*     */       
/*  57 */       for (int var8 = -2; var8 <= 2; var8++) {
/*     */         
/*  59 */         for (int var9 = -2; var9 <= 2; var9++) {
/*     */           
/*  61 */           for (int var10 = -1; var10 < 3; var10++) {
/*     */             
/*  63 */             int var11 = var3 + var9 * var6 + var8 * var7;
/*  64 */             int var12 = var4 + var10;
/*  65 */             int var13 = var5 + var9 * var7 - var8 * var6;
/*  66 */             boolean var14 = (var10 < 0);
/*  67 */             this.worldServerInstance.setBlockState(new BlockPos(var11, var12, var13), var14 ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  72 */       p_180266_1_.setLocationAndAngles(var3, var4, var5, p_180266_1_.rotationYaw, 0.0F);
/*  73 */       p_180266_1_.motionX = p_180266_1_.motionY = p_180266_1_.motionZ = 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180620_b(Entity p_180620_1_, float p_180620_2_) {
/*  79 */     boolean var3 = true;
/*  80 */     double var4 = -1.0D;
/*  81 */     int var6 = MathHelper.floor_double(p_180620_1_.posX);
/*  82 */     int var7 = MathHelper.floor_double(p_180620_1_.posZ);
/*  83 */     boolean var8 = true;
/*  84 */     Object var9 = BlockPos.ORIGIN;
/*  85 */     long var10 = ChunkCoordIntPair.chunkXZ2Int(var6, var7);
/*     */     
/*  87 */     if (this.destinationCoordinateCache.containsItem(var10)) {
/*     */       
/*  89 */       PortalPosition var12 = (PortalPosition)this.destinationCoordinateCache.getValueByKey(var10);
/*  90 */       var4 = 0.0D;
/*  91 */       var9 = var12;
/*  92 */       var12.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
/*  93 */       var8 = false;
/*     */     }
/*     */     else {
/*     */       
/*  97 */       BlockPos var34 = new BlockPos(p_180620_1_);
/*     */       
/*  99 */       for (int var13 = -128; var13 <= 128; var13++) {
/*     */ 
/*     */ 
/*     */         
/* 103 */         for (int var14 = -128; var14 <= 128; var14++) {
/*     */           
/* 105 */           for (BlockPos var15 = var34.add(var13, this.worldServerInstance.getActualHeight() - 1 - var34.getY(), var14); var15.getY() >= 0; var15 = var16) {
/*     */             
/* 107 */             BlockPos var16 = var15.offsetDown();
/*     */             
/* 109 */             if (this.worldServerInstance.getBlockState(var15).getBlock() == Blocks.portal) {
/*     */               
/* 111 */               while (this.worldServerInstance.getBlockState(var16 = var15.offsetDown()).getBlock() == Blocks.portal)
/*     */               {
/* 113 */                 var15 = var16;
/*     */               }
/*     */               
/* 116 */               double var17 = var15.distanceSq((Vec3i)var34);
/*     */               
/* 118 */               if (var4 < 0.0D || var17 < var4) {
/*     */                 
/* 120 */                 var4 = var17;
/* 121 */                 var9 = var15;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (var4 >= 0.0D) {
/*     */       
/* 131 */       if (var8) {
/*     */         
/* 133 */         this.destinationCoordinateCache.add(var10, new PortalPosition((BlockPos)var9, this.worldServerInstance.getTotalWorldTime()));
/* 134 */         this.destinationCoordinateKeys.add(Long.valueOf(var10));
/*     */       } 
/*     */       
/* 137 */       double var35 = ((BlockPos)var9).getX() + 0.5D;
/* 138 */       double var36 = ((BlockPos)var9).getY() + 0.5D;
/* 139 */       double var37 = ((BlockPos)var9).getZ() + 0.5D;
/* 140 */       EnumFacing var18 = null;
/*     */       
/* 142 */       if (this.worldServerInstance.getBlockState(((BlockPos)var9).offsetWest()).getBlock() == Blocks.portal)
/*     */       {
/* 144 */         var18 = EnumFacing.NORTH;
/*     */       }
/*     */       
/* 147 */       if (this.worldServerInstance.getBlockState(((BlockPos)var9).offsetEast()).getBlock() == Blocks.portal)
/*     */       {
/* 149 */         var18 = EnumFacing.SOUTH;
/*     */       }
/*     */       
/* 152 */       if (this.worldServerInstance.getBlockState(((BlockPos)var9).offsetNorth()).getBlock() == Blocks.portal)
/*     */       {
/* 154 */         var18 = EnumFacing.EAST;
/*     */       }
/*     */       
/* 157 */       if (this.worldServerInstance.getBlockState(((BlockPos)var9).offsetSouth()).getBlock() == Blocks.portal)
/*     */       {
/* 159 */         var18 = EnumFacing.WEST;
/*     */       }
/*     */       
/* 162 */       EnumFacing var19 = EnumFacing.getHorizontal(p_180620_1_.getTeleportDirection());
/*     */       
/* 164 */       if (var18 != null) {
/*     */         
/* 166 */         EnumFacing var20 = var18.rotateYCCW();
/* 167 */         BlockPos var21 = ((BlockPos)var9).offset(var18);
/* 168 */         boolean var22 = func_180265_a(var21);
/* 169 */         boolean var23 = func_180265_a(var21.offset(var20));
/*     */         
/* 171 */         if (var23 && var22) {
/*     */           
/* 173 */           var9 = ((BlockPos)var9).offset(var20);
/* 174 */           var18 = var18.getOpposite();
/* 175 */           var20 = var20.getOpposite();
/* 176 */           BlockPos var24 = ((BlockPos)var9).offset(var18);
/* 177 */           var22 = func_180265_a(var24);
/* 178 */           var23 = func_180265_a(var24.offset(var20));
/*     */         } 
/*     */         
/* 181 */         float var38 = 0.5F;
/* 182 */         float var25 = 0.5F;
/*     */         
/* 184 */         if (!var23 && var22) {
/*     */           
/* 186 */           var38 = 1.0F;
/*     */         }
/* 188 */         else if (var23 && !var22) {
/*     */           
/* 190 */           var38 = 0.0F;
/*     */         }
/* 192 */         else if (var23) {
/*     */           
/* 194 */           var25 = 0.0F;
/*     */         } 
/*     */         
/* 197 */         var35 = ((BlockPos)var9).getX() + 0.5D;
/* 198 */         var36 = ((BlockPos)var9).getY() + 0.5D;
/* 199 */         var37 = ((BlockPos)var9).getZ() + 0.5D;
/* 200 */         var35 += (var20.getFrontOffsetX() * var38 + var18.getFrontOffsetX() * var25);
/* 201 */         var37 += (var20.getFrontOffsetZ() * var38 + var18.getFrontOffsetZ() * var25);
/* 202 */         float var26 = 0.0F;
/* 203 */         float var27 = 0.0F;
/* 204 */         float var28 = 0.0F;
/* 205 */         float var29 = 0.0F;
/*     */         
/* 207 */         if (var18 == var19) {
/*     */           
/* 209 */           var26 = 1.0F;
/* 210 */           var27 = 1.0F;
/*     */         }
/* 212 */         else if (var18 == var19.getOpposite()) {
/*     */           
/* 214 */           var26 = -1.0F;
/* 215 */           var27 = -1.0F;
/*     */         }
/* 217 */         else if (var18 == var19.rotateY()) {
/*     */           
/* 219 */           var28 = 1.0F;
/* 220 */           var29 = -1.0F;
/*     */         }
/*     */         else {
/*     */           
/* 224 */           var28 = -1.0F;
/* 225 */           var29 = 1.0F;
/*     */         } 
/*     */         
/* 228 */         double var30 = p_180620_1_.motionX;
/* 229 */         double var32 = p_180620_1_.motionZ;
/* 230 */         p_180620_1_.motionX = var30 * var26 + var32 * var29;
/* 231 */         p_180620_1_.motionZ = var30 * var28 + var32 * var27;
/* 232 */         p_180620_1_.rotationYaw = p_180620_2_ - (var19.getHorizontalIndex() * 90) + (var18.getHorizontalIndex() * 90);
/*     */       }
/*     */       else {
/*     */         
/* 236 */         p_180620_1_.motionX = p_180620_1_.motionY = p_180620_1_.motionZ = 0.0D;
/*     */       } 
/*     */       
/* 239 */       p_180620_1_.setLocationAndAngles(var35, var36, var37, p_180620_1_.rotationYaw, p_180620_1_.rotationPitch);
/* 240 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_180265_a(BlockPos p_180265_1_) {
/* 250 */     return !(this.worldServerInstance.isAirBlock(p_180265_1_) && this.worldServerInstance.isAirBlock(p_180265_1_.offsetUp()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean makePortal(Entity p_85188_1_) {
/* 255 */     byte var2 = 16;
/* 256 */     double var3 = -1.0D;
/* 257 */     int var5 = MathHelper.floor_double(p_85188_1_.posX);
/* 258 */     int var6 = MathHelper.floor_double(p_85188_1_.posY);
/* 259 */     int var7 = MathHelper.floor_double(p_85188_1_.posZ);
/* 260 */     int var8 = var5;
/* 261 */     int var9 = var6;
/* 262 */     int var10 = var7;
/* 263 */     int var11 = 0;
/* 264 */     int var12 = this.random.nextInt(4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int var13;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     for (var13 = var5 - var2; var13 <= var5 + var2; var13++) {
/*     */       
/* 283 */       double var14 = var13 + 0.5D - p_85188_1_.posX;
/*     */       
/* 285 */       for (int i = var7 - var2; i <= var7 + var2; i++) {
/*     */         
/* 287 */         double var17 = i + 0.5D - p_85188_1_.posZ;
/*     */ 
/*     */         
/* 290 */         for (int var19 = this.worldServerInstance.getActualHeight() - 1; var19 >= 0; var19--) {
/*     */           
/* 292 */           if (this.worldServerInstance.isAirBlock(new BlockPos(var13, var19, i))) {
/*     */             
/* 294 */             while (var19 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(var13, var19 - 1, i)))
/*     */             {
/* 296 */               var19--;
/*     */             }
/*     */             int j;
/* 299 */             label176: for (j = var12; j < var12 + 4; j++) {
/*     */               
/* 301 */               int var21 = j % 2;
/* 302 */               int var22 = 1 - var21;
/*     */               
/* 304 */               if (j % 4 >= 2) {
/*     */                 
/* 306 */                 var21 = -var21;
/* 307 */                 var22 = -var22;
/*     */               } 
/*     */               
/* 310 */               for (int var23 = 0; var23 < 3; var23++) {
/*     */                 
/* 312 */                 for (int var24 = 0; var24 < 4; var24++) {
/*     */                   
/* 314 */                   for (int var25 = -1; var25 < 4; ) {
/*     */                     
/* 316 */                     int var26 = var13 + (var24 - 1) * var21 + var23 * var22;
/* 317 */                     int var27 = var19 + var25;
/* 318 */                     int var28 = i + (var24 - 1) * var22 - var23 * var21;
/*     */                     
/* 320 */                     if (var25 >= 0 || this.worldServerInstance.getBlockState(new BlockPos(var26, var27, var28)).getBlock().getMaterial().isSolid()) { if (var25 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(var26, var27, var28)))
/*     */                         break label176; 
/*     */                       var25++; }
/*     */                     
/*     */                     break label176;
/*     */                   } 
/*     */                 } 
/*     */               } 
/* 328 */               double var32 = var19 + 0.5D - p_85188_1_.posY;
/* 329 */               double var33 = var14 * var14 + var32 * var32 + var17 * var17;
/*     */               
/* 331 */               if (var3 < 0.0D || var33 < var3) {
/*     */                 
/* 333 */                 var3 = var33;
/* 334 */                 var8 = var13;
/* 335 */                 var9 = var19;
/* 336 */                 var10 = i;
/* 337 */                 var11 = j % 4;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 345 */     if (var3 < 0.0D)
/*     */     {
/* 347 */       for (var13 = var5 - var2; var13 <= var5 + var2; var13++) {
/*     */         
/* 349 */         double var14 = var13 + 0.5D - p_85188_1_.posX;
/*     */         
/* 351 */         for (int i = var7 - var2; i <= var7 + var2; i++) {
/*     */           
/* 353 */           double var17 = i + 0.5D - p_85188_1_.posZ;
/*     */ 
/*     */           
/* 356 */           for (int var19 = this.worldServerInstance.getActualHeight() - 1; var19 >= 0; var19--) {
/*     */             
/* 358 */             if (this.worldServerInstance.isAirBlock(new BlockPos(var13, var19, i))) {
/*     */               
/* 360 */               while (var19 > 0 && this.worldServerInstance.isAirBlock(new BlockPos(var13, var19 - 1, i)))
/*     */               {
/* 362 */                 var19--;
/*     */               }
/*     */               int j;
/* 365 */               label175: for (j = var12; j < var12 + 2; j++) {
/*     */                 
/* 367 */                 int var21 = j % 2;
/* 368 */                 int var22 = 1 - var21;
/*     */                 
/* 370 */                 for (int var23 = 0; var23 < 4; var23++) {
/*     */                   
/* 372 */                   for (int var24 = -1; var24 < 4; ) {
/*     */                     
/* 374 */                     int var25 = var13 + (var23 - 1) * var21;
/* 375 */                     int var26 = var19 + var24;
/* 376 */                     int var27 = i + (var23 - 1) * var22;
/*     */                     
/* 378 */                     if (var24 >= 0 || this.worldServerInstance.getBlockState(new BlockPos(var25, var26, var27)).getBlock().getMaterial().isSolid()) { if (var24 >= 0 && !this.worldServerInstance.isAirBlock(new BlockPos(var25, var26, var27)))
/*     */                         break label175; 
/*     */                       var24++; }
/*     */                     
/*     */                     break label175;
/*     */                   } 
/*     */                 } 
/* 385 */                 double var32 = var19 + 0.5D - p_85188_1_.posY;
/* 386 */                 double var33 = var14 * var14 + var32 * var32 + var17 * var17;
/*     */                 
/* 388 */                 if (var3 < 0.0D || var33 < var3) {
/*     */                   
/* 390 */                   var3 = var33;
/* 391 */                   var8 = var13;
/* 392 */                   var9 = var19;
/* 393 */                   var10 = i;
/* 394 */                   var11 = j % 2;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 403 */     int var29 = var8;
/* 404 */     int var15 = var9;
/* 405 */     int var16 = var10;
/* 406 */     int var30 = var11 % 2;
/* 407 */     int var18 = 1 - var30;
/*     */     
/* 409 */     if (var11 % 4 >= 2) {
/*     */       
/* 411 */       var30 = -var30;
/* 412 */       var18 = -var18;
/*     */     } 
/*     */     
/* 415 */     if (var3 < 0.0D) {
/*     */       
/* 417 */       var9 = MathHelper.clamp_int(var9, 70, this.worldServerInstance.getActualHeight() - 10);
/* 418 */       var15 = var9;
/*     */       
/* 420 */       for (int var19 = -1; var19 <= 1; var19++) {
/*     */         
/* 422 */         for (int i = 1; i < 3; i++) {
/*     */           
/* 424 */           for (int var21 = -1; var21 < 3; var21++) {
/*     */             
/* 426 */             int var22 = var29 + (i - 1) * var30 + var19 * var18;
/* 427 */             int var23 = var15 + var21;
/* 428 */             int var24 = var16 + (i - 1) * var18 - var19 * var30;
/* 429 */             boolean var34 = (var21 < 0);
/* 430 */             this.worldServerInstance.setBlockState(new BlockPos(var22, var23, var24), var34 ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 436 */     IBlockState var31 = Blocks.portal.getDefaultState().withProperty((IProperty)BlockPortal.field_176550_a, (var30 != 0) ? (Comparable)EnumFacing.Axis.X : (Comparable)EnumFacing.Axis.Z);
/*     */     
/* 438 */     for (int var20 = 0; var20 < 4; var20++) {
/*     */       int var21;
/* 440 */       for (var21 = 0; var21 < 4; var21++) {
/*     */         
/* 442 */         for (int var22 = -1; var22 < 4; var22++) {
/*     */           
/* 444 */           int var23 = var29 + (var21 - 1) * var30;
/* 445 */           int var24 = var15 + var22;
/* 446 */           int var25 = var16 + (var21 - 1) * var18;
/* 447 */           boolean var35 = !(var21 != 0 && var21 != 3 && var22 != -1 && var22 != 3);
/* 448 */           this.worldServerInstance.setBlockState(new BlockPos(var23, var24, var25), var35 ? Blocks.obsidian.getDefaultState() : var31, 2);
/*     */         } 
/*     */       } 
/*     */       
/* 452 */       for (var21 = 0; var21 < 4; var21++) {
/*     */         
/* 454 */         for (int var22 = -1; var22 < 4; var22++) {
/*     */           
/* 456 */           int var23 = var29 + (var21 - 1) * var30;
/* 457 */           int var24 = var15 + var22;
/* 458 */           int var25 = var16 + (var21 - 1) * var18;
/* 459 */           this.worldServerInstance.notifyNeighborsOfStateChange(new BlockPos(var23, var24, var25), this.worldServerInstance.getBlockState(new BlockPos(var23, var24, var25)).getBlock());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 464 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeStalePortalLocations(long p_85189_1_) {
/* 473 */     if (p_85189_1_ % 100L == 0L) {
/*     */       
/* 475 */       Iterator<Long> var3 = this.destinationCoordinateKeys.iterator();
/* 476 */       long var4 = p_85189_1_ - 600L;
/*     */       
/* 478 */       while (var3.hasNext()) {
/*     */         
/* 480 */         Long var6 = var3.next();
/* 481 */         PortalPosition var7 = (PortalPosition)this.destinationCoordinateCache.getValueByKey(var6.longValue());
/*     */         
/* 483 */         if (var7 == null || var7.lastUpdateTime < var4) {
/*     */           
/* 485 */           var3.remove();
/* 486 */           this.destinationCoordinateCache.remove(var6.longValue());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public class PortalPosition
/*     */     extends BlockPos
/*     */   {
/*     */     public long lastUpdateTime;
/*     */     private static final String __OBFID = "CL_00000154";
/*     */     
/*     */     public PortalPosition(BlockPos p_i45747_2_, long p_i45747_3_) {
/* 499 */       super(p_i45747_2_.getX(), p_i45747_2_.getY(), p_i45747_2_.getZ());
/* 500 */       this.lastUpdateTime = p_i45747_3_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\Teleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */