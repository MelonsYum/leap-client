/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemDoor;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityDispenser;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class StructureComponent
/*     */ {
/*     */   protected StructureBoundingBox boundingBox;
/*     */   protected EnumFacing coordBaseMode;
/*     */   protected int componentType;
/*     */   private static final String __OBFID = "CL_00000511";
/*     */   
/*     */   public StructureComponent() {}
/*     */   
/*     */   protected StructureComponent(int p_i2091_1_) {
/*  37 */     this.componentType = p_i2091_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_143010_b() {
/*  42 */     NBTTagCompound var1 = new NBTTagCompound();
/*  43 */     var1.setString("id", MapGenStructureIO.func_143036_a(this));
/*  44 */     var1.setTag("BB", (NBTBase)this.boundingBox.func_151535_h());
/*  45 */     var1.setInteger("O", (this.coordBaseMode == null) ? -1 : this.coordBaseMode.getHorizontalIndex());
/*  46 */     var1.setInteger("GD", this.componentType);
/*  47 */     writeStructureToNBT(var1);
/*  48 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void writeStructureToNBT(NBTTagCompound paramNBTTagCompound);
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_143009_a(World worldIn, NBTTagCompound p_143009_2_) {
/*  58 */     if (p_143009_2_.hasKey("BB"))
/*     */     {
/*  60 */       this.boundingBox = new StructureBoundingBox(p_143009_2_.getIntArray("BB"));
/*     */     }
/*     */     
/*  63 */     int var3 = p_143009_2_.getInteger("O");
/*  64 */     this.coordBaseMode = (var3 == -1) ? null : EnumFacing.getHorizontal(var3);
/*  65 */     this.componentType = p_143009_2_.getInteger("GD");
/*  66 */     readStructureFromNBT(p_143009_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void readStructureFromNBT(NBTTagCompound paramNBTTagCompound);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract boolean addComponentParts(World paramWorld, Random paramRandom, StructureBoundingBox paramStructureBoundingBox);
/*     */ 
/*     */ 
/*     */   
/*     */   public StructureBoundingBox getBoundingBox() {
/*  87 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getComponentType() {
/*  95 */     return this.componentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StructureComponent findIntersecting(List p_74883_0_, StructureBoundingBox p_74883_1_) {
/*     */     StructureComponent var3;
/* 103 */     Iterator<StructureComponent> var2 = p_74883_0_.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 108 */       if (!var2.hasNext())
/*     */       {
/* 110 */         return null;
/*     */       }
/*     */       
/* 113 */       var3 = var2.next();
/*     */     }
/* 115 */     while (var3.getBoundingBox() == null || !var3.getBoundingBox().intersectsWith(p_74883_1_));
/*     */     
/* 117 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180776_a() {
/* 122 */     return new BlockPos(this.boundingBox.func_180717_f());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isLiquidInStructureBoundingBox(World worldIn, StructureBoundingBox p_74860_2_) {
/* 130 */     int var3 = Math.max(this.boundingBox.minX - 1, p_74860_2_.minX);
/* 131 */     int var4 = Math.max(this.boundingBox.minY - 1, p_74860_2_.minY);
/* 132 */     int var5 = Math.max(this.boundingBox.minZ - 1, p_74860_2_.minZ);
/* 133 */     int var6 = Math.min(this.boundingBox.maxX + 1, p_74860_2_.maxX);
/* 134 */     int var7 = Math.min(this.boundingBox.maxY + 1, p_74860_2_.maxY);
/* 135 */     int var8 = Math.min(this.boundingBox.maxZ + 1, p_74860_2_.maxZ);
/*     */     
/*     */     int var9;
/*     */     
/* 139 */     for (var9 = var3; var9 <= var6; var9++) {
/*     */       
/* 141 */       for (int var10 = var5; var10 <= var8; var10++) {
/*     */         
/* 143 */         if (worldIn.getBlockState(new BlockPos(var9, var4, var10)).getBlock().getMaterial().isLiquid())
/*     */         {
/* 145 */           return true;
/*     */         }
/*     */         
/* 148 */         if (worldIn.getBlockState(new BlockPos(var9, var7, var10)).getBlock().getMaterial().isLiquid())
/*     */         {
/* 150 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     for (var9 = var3; var9 <= var6; var9++) {
/*     */       
/* 157 */       for (int var10 = var4; var10 <= var7; var10++) {
/*     */         
/* 159 */         if (worldIn.getBlockState(new BlockPos(var9, var10, var5)).getBlock().getMaterial().isLiquid())
/*     */         {
/* 161 */           return true;
/*     */         }
/*     */         
/* 164 */         if (worldIn.getBlockState(new BlockPos(var9, var10, var8)).getBlock().getMaterial().isLiquid())
/*     */         {
/* 166 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     for (var9 = var5; var9 <= var8; var9++) {
/*     */       
/* 173 */       for (int var10 = var4; var10 <= var7; var10++) {
/*     */         
/* 175 */         if (worldIn.getBlockState(new BlockPos(var3, var10, var9)).getBlock().getMaterial().isLiquid())
/*     */         {
/* 177 */           return true;
/*     */         }
/*     */         
/* 180 */         if (worldIn.getBlockState(new BlockPos(var6, var10, var9)).getBlock().getMaterial().isLiquid())
/*     */         {
/* 182 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getXWithOffset(int p_74865_1_, int p_74865_2_) {
/* 192 */     if (this.coordBaseMode == null)
/*     */     {
/* 194 */       return p_74865_1_;
/*     */     }
/*     */ 
/*     */     
/* 198 */     switch (SwitchEnumFacing.field_176100_a[this.coordBaseMode.ordinal()]) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/* 202 */         return this.boundingBox.minX + p_74865_1_;
/*     */       
/*     */       case 3:
/* 205 */         return this.boundingBox.maxX - p_74865_2_;
/*     */       
/*     */       case 4:
/* 208 */         return this.boundingBox.minX + p_74865_2_;
/*     */     } 
/*     */     
/* 211 */     return p_74865_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getYWithOffset(int p_74862_1_) {
/* 218 */     return (this.coordBaseMode == null) ? p_74862_1_ : (p_74862_1_ + this.boundingBox.minY);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getZWithOffset(int p_74873_1_, int p_74873_2_) {
/* 223 */     if (this.coordBaseMode == null)
/*     */     {
/* 225 */       return p_74873_2_;
/*     */     }
/*     */ 
/*     */     
/* 229 */     switch (SwitchEnumFacing.field_176100_a[this.coordBaseMode.ordinal()]) {
/*     */       
/*     */       case 1:
/* 232 */         return this.boundingBox.maxZ - p_74873_2_;
/*     */       
/*     */       case 2:
/* 235 */         return this.boundingBox.minZ + p_74873_2_;
/*     */       
/*     */       case 3:
/*     */       case 4:
/* 239 */         return this.boundingBox.minZ + p_74873_1_;
/*     */     } 
/*     */     
/* 242 */     return p_74873_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getMetadataWithOffset(Block p_151555_1_, int p_151555_2_) {
/* 252 */     if (p_151555_1_ == Blocks.rail) {
/*     */       
/* 254 */       if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.EAST)
/*     */       {
/* 256 */         if (p_151555_2_ == 1)
/*     */         {
/* 258 */           return 0;
/*     */         }
/*     */         
/* 261 */         return 1;
/*     */       }
/*     */     
/* 264 */     } else if (p_151555_1_ instanceof net.minecraft.block.BlockDoor) {
/*     */       
/* 266 */       if (this.coordBaseMode == EnumFacing.SOUTH)
/*     */       {
/* 268 */         if (p_151555_2_ == 0)
/*     */         {
/* 270 */           return 2;
/*     */         }
/*     */         
/* 273 */         if (p_151555_2_ == 2)
/*     */         {
/* 275 */           return 0;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 280 */         if (this.coordBaseMode == EnumFacing.WEST)
/*     */         {
/* 282 */           return p_151555_2_ + 1 & 0x3;
/*     */         }
/*     */         
/* 285 */         if (this.coordBaseMode == EnumFacing.EAST)
/*     */         {
/* 287 */           return p_151555_2_ + 3 & 0x3;
/*     */         }
/*     */       }
/*     */     
/* 291 */     } else if (p_151555_1_ != Blocks.stone_stairs && p_151555_1_ != Blocks.oak_stairs && p_151555_1_ != Blocks.nether_brick_stairs && p_151555_1_ != Blocks.stone_brick_stairs && p_151555_1_ != Blocks.sandstone_stairs) {
/*     */       
/* 293 */       if (p_151555_1_ == Blocks.ladder) {
/*     */         
/* 295 */         if (this.coordBaseMode == EnumFacing.SOUTH)
/*     */         {
/* 297 */           if (p_151555_2_ == EnumFacing.NORTH.getIndex())
/*     */           {
/* 299 */             return EnumFacing.SOUTH.getIndex();
/*     */           }
/*     */           
/* 302 */           if (p_151555_2_ == EnumFacing.SOUTH.getIndex())
/*     */           {
/* 304 */             return EnumFacing.NORTH.getIndex();
/*     */           }
/*     */         }
/* 307 */         else if (this.coordBaseMode == EnumFacing.WEST)
/*     */         {
/* 309 */           if (p_151555_2_ == EnumFacing.NORTH.getIndex())
/*     */           {
/* 311 */             return EnumFacing.WEST.getIndex();
/*     */           }
/*     */           
/* 314 */           if (p_151555_2_ == EnumFacing.SOUTH.getIndex())
/*     */           {
/* 316 */             return EnumFacing.EAST.getIndex();
/*     */           }
/*     */           
/* 319 */           if (p_151555_2_ == EnumFacing.WEST.getIndex())
/*     */           {
/* 321 */             return EnumFacing.NORTH.getIndex();
/*     */           }
/*     */           
/* 324 */           if (p_151555_2_ == EnumFacing.EAST.getIndex())
/*     */           {
/* 326 */             return EnumFacing.SOUTH.getIndex();
/*     */           }
/*     */         }
/* 329 */         else if (this.coordBaseMode == EnumFacing.EAST)
/*     */         {
/* 331 */           if (p_151555_2_ == EnumFacing.NORTH.getIndex())
/*     */           {
/* 333 */             return EnumFacing.EAST.getIndex();
/*     */           }
/*     */           
/* 336 */           if (p_151555_2_ == EnumFacing.SOUTH.getIndex())
/*     */           {
/* 338 */             return EnumFacing.WEST.getIndex();
/*     */           }
/*     */           
/* 341 */           if (p_151555_2_ == EnumFacing.WEST.getIndex())
/*     */           {
/* 343 */             return EnumFacing.NORTH.getIndex();
/*     */           }
/*     */           
/* 346 */           if (p_151555_2_ == EnumFacing.EAST.getIndex())
/*     */           {
/* 348 */             return EnumFacing.SOUTH.getIndex();
/*     */           }
/*     */         }
/*     */       
/* 352 */       } else if (p_151555_1_ == Blocks.stone_button) {
/*     */         
/* 354 */         if (this.coordBaseMode == EnumFacing.SOUTH)
/*     */         {
/* 356 */           if (p_151555_2_ == 3)
/*     */           {
/* 358 */             return 4;
/*     */           }
/*     */           
/* 361 */           if (p_151555_2_ == 4)
/*     */           {
/* 363 */             return 3;
/*     */           }
/*     */         }
/* 366 */         else if (this.coordBaseMode == EnumFacing.WEST)
/*     */         {
/* 368 */           if (p_151555_2_ == 3)
/*     */           {
/* 370 */             return 1;
/*     */           }
/*     */           
/* 373 */           if (p_151555_2_ == 4)
/*     */           {
/* 375 */             return 2;
/*     */           }
/*     */           
/* 378 */           if (p_151555_2_ == 2)
/*     */           {
/* 380 */             return 3;
/*     */           }
/*     */           
/* 383 */           if (p_151555_2_ == 1)
/*     */           {
/* 385 */             return 4;
/*     */           }
/*     */         }
/* 388 */         else if (this.coordBaseMode == EnumFacing.EAST)
/*     */         {
/* 390 */           if (p_151555_2_ == 3)
/*     */           {
/* 392 */             return 2;
/*     */           }
/*     */           
/* 395 */           if (p_151555_2_ == 4)
/*     */           {
/* 397 */             return 1;
/*     */           }
/*     */           
/* 400 */           if (p_151555_2_ == 2)
/*     */           {
/* 402 */             return 3;
/*     */           }
/*     */           
/* 405 */           if (p_151555_2_ == 1)
/*     */           {
/* 407 */             return 4;
/*     */           }
/*     */         }
/*     */       
/* 411 */       } else if (p_151555_1_ != Blocks.tripwire_hook && !(p_151555_1_ instanceof net.minecraft.block.BlockDirectional)) {
/*     */         
/* 413 */         if (p_151555_1_ == Blocks.piston || p_151555_1_ == Blocks.sticky_piston || p_151555_1_ == Blocks.lever || p_151555_1_ == Blocks.dispenser)
/*     */         {
/* 415 */           if (this.coordBaseMode == EnumFacing.SOUTH)
/*     */           {
/* 417 */             if (p_151555_2_ == EnumFacing.NORTH.getIndex() || p_151555_2_ == EnumFacing.SOUTH.getIndex())
/*     */             {
/* 419 */               return EnumFacing.getFront(p_151555_2_).getOpposite().getIndex();
/*     */             }
/*     */           }
/* 422 */           else if (this.coordBaseMode == EnumFacing.WEST)
/*     */           {
/* 424 */             if (p_151555_2_ == EnumFacing.NORTH.getIndex())
/*     */             {
/* 426 */               return EnumFacing.WEST.getIndex();
/*     */             }
/*     */             
/* 429 */             if (p_151555_2_ == EnumFacing.SOUTH.getIndex())
/*     */             {
/* 431 */               return EnumFacing.EAST.getIndex();
/*     */             }
/*     */             
/* 434 */             if (p_151555_2_ == EnumFacing.WEST.getIndex())
/*     */             {
/* 436 */               return EnumFacing.NORTH.getIndex();
/*     */             }
/*     */             
/* 439 */             if (p_151555_2_ == EnumFacing.EAST.getIndex())
/*     */             {
/* 441 */               return EnumFacing.SOUTH.getIndex();
/*     */             }
/*     */           }
/* 444 */           else if (this.coordBaseMode == EnumFacing.EAST)
/*     */           {
/* 446 */             if (p_151555_2_ == EnumFacing.NORTH.getIndex())
/*     */             {
/* 448 */               return EnumFacing.EAST.getIndex();
/*     */             }
/*     */             
/* 451 */             if (p_151555_2_ == EnumFacing.SOUTH.getIndex())
/*     */             {
/* 453 */               return EnumFacing.WEST.getIndex();
/*     */             }
/*     */             
/* 456 */             if (p_151555_2_ == EnumFacing.WEST.getIndex())
/*     */             {
/* 458 */               return EnumFacing.NORTH.getIndex();
/*     */             }
/*     */             
/* 461 */             if (p_151555_2_ == EnumFacing.EAST.getIndex())
/*     */             {
/* 463 */               return EnumFacing.SOUTH.getIndex();
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       } else {
/*     */         
/* 470 */         EnumFacing var3 = EnumFacing.getHorizontal(p_151555_2_);
/*     */         
/* 472 */         if (this.coordBaseMode == EnumFacing.SOUTH)
/*     */         {
/* 474 */           if (var3 == EnumFacing.SOUTH || var3 == EnumFacing.NORTH)
/*     */           {
/* 476 */             return var3.getOpposite().getHorizontalIndex();
/*     */           }
/*     */         }
/* 479 */         else if (this.coordBaseMode == EnumFacing.WEST)
/*     */         {
/* 481 */           if (var3 == EnumFacing.NORTH)
/*     */           {
/* 483 */             return EnumFacing.WEST.getHorizontalIndex();
/*     */           }
/*     */           
/* 486 */           if (var3 == EnumFacing.SOUTH)
/*     */           {
/* 488 */             return EnumFacing.EAST.getHorizontalIndex();
/*     */           }
/*     */           
/* 491 */           if (var3 == EnumFacing.WEST)
/*     */           {
/* 493 */             return EnumFacing.NORTH.getHorizontalIndex();
/*     */           }
/*     */           
/* 496 */           if (var3 == EnumFacing.EAST)
/*     */           {
/* 498 */             return EnumFacing.SOUTH.getHorizontalIndex();
/*     */           }
/*     */         }
/* 501 */         else if (this.coordBaseMode == EnumFacing.EAST)
/*     */         {
/* 503 */           if (var3 == EnumFacing.NORTH)
/*     */           {
/* 505 */             return EnumFacing.EAST.getHorizontalIndex();
/*     */           }
/*     */           
/* 508 */           if (var3 == EnumFacing.SOUTH)
/*     */           {
/* 510 */             return EnumFacing.WEST.getHorizontalIndex();
/*     */           }
/*     */           
/* 513 */           if (var3 == EnumFacing.WEST)
/*     */           {
/* 515 */             return EnumFacing.NORTH.getHorizontalIndex();
/*     */           }
/*     */           
/* 518 */           if (var3 == EnumFacing.EAST)
/*     */           {
/* 520 */             return EnumFacing.SOUTH.getHorizontalIndex();
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/* 525 */     } else if (this.coordBaseMode == EnumFacing.SOUTH) {
/*     */       
/* 527 */       if (p_151555_2_ == 2)
/*     */       {
/* 529 */         return 3;
/*     */       }
/*     */       
/* 532 */       if (p_151555_2_ == 3)
/*     */       {
/* 534 */         return 2;
/*     */       }
/*     */     }
/* 537 */     else if (this.coordBaseMode == EnumFacing.WEST) {
/*     */       
/* 539 */       if (p_151555_2_ == 0)
/*     */       {
/* 541 */         return 2;
/*     */       }
/*     */       
/* 544 */       if (p_151555_2_ == 1)
/*     */       {
/* 546 */         return 3;
/*     */       }
/*     */       
/* 549 */       if (p_151555_2_ == 2)
/*     */       {
/* 551 */         return 0;
/*     */       }
/*     */       
/* 554 */       if (p_151555_2_ == 3)
/*     */       {
/* 556 */         return 1;
/*     */       }
/*     */     }
/* 559 */     else if (this.coordBaseMode == EnumFacing.EAST) {
/*     */       
/* 561 */       if (p_151555_2_ == 0)
/*     */       {
/* 563 */         return 2;
/*     */       }
/*     */       
/* 566 */       if (p_151555_2_ == 1)
/*     */       {
/* 568 */         return 3;
/*     */       }
/*     */       
/* 571 */       if (p_151555_2_ == 2)
/*     */       {
/* 573 */         return 1;
/*     */       }
/*     */       
/* 576 */       if (p_151555_2_ == 3)
/*     */       {
/* 578 */         return 0;
/*     */       }
/*     */     } 
/*     */     
/* 582 */     return p_151555_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175811_a(World worldIn, IBlockState p_175811_2_, int p_175811_3_, int p_175811_4_, int p_175811_5_, StructureBoundingBox p_175811_6_) {
/* 587 */     BlockPos var7 = new BlockPos(getXWithOffset(p_175811_3_, p_175811_5_), getYWithOffset(p_175811_4_), getZWithOffset(p_175811_3_, p_175811_5_));
/*     */     
/* 589 */     if (p_175811_6_.func_175898_b((Vec3i)var7))
/*     */     {
/* 591 */       worldIn.setBlockState(var7, p_175811_2_, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockState func_175807_a(World worldIn, int p_175807_2_, int p_175807_3_, int p_175807_4_, StructureBoundingBox p_175807_5_) {
/* 597 */     int var6 = getXWithOffset(p_175807_2_, p_175807_4_);
/* 598 */     int var7 = getYWithOffset(p_175807_3_);
/* 599 */     int var8 = getZWithOffset(p_175807_2_, p_175807_4_);
/* 600 */     return !p_175807_5_.func_175898_b((Vec3i)new BlockPos(var6, var7, var8)) ? Blocks.air.getDefaultState() : worldIn.getBlockState(new BlockPos(var6, var7, var8));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fillWithAir(World worldIn, StructureBoundingBox p_74878_2_, int p_74878_3_, int p_74878_4_, int p_74878_5_, int p_74878_6_, int p_74878_7_, int p_74878_8_) {
/* 609 */     for (int var9 = p_74878_4_; var9 <= p_74878_7_; var9++) {
/*     */       
/* 611 */       for (int var10 = p_74878_3_; var10 <= p_74878_6_; var10++) {
/*     */         
/* 613 */         for (int var11 = p_74878_5_; var11 <= p_74878_8_; var11++)
/*     */         {
/* 615 */           func_175811_a(worldIn, Blocks.air.getDefaultState(), var10, var9, var11, p_74878_2_);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175804_a(World worldIn, StructureBoundingBox p_175804_2_, int p_175804_3_, int p_175804_4_, int p_175804_5_, int p_175804_6_, int p_175804_7_, int p_175804_8_, IBlockState p_175804_9_, IBlockState p_175804_10_, boolean p_175804_11_) {
/* 623 */     for (int var12 = p_175804_4_; var12 <= p_175804_7_; var12++) {
/*     */       
/* 625 */       for (int var13 = p_175804_3_; var13 <= p_175804_6_; var13++) {
/*     */         
/* 627 */         for (int var14 = p_175804_5_; var14 <= p_175804_8_; var14++) {
/*     */           
/* 629 */           if (!p_175804_11_ || func_175807_a(worldIn, var13, var12, var14, p_175804_2_).getBlock().getMaterial() != Material.air)
/*     */           {
/* 631 */             if (var12 != p_175804_4_ && var12 != p_175804_7_ && var13 != p_175804_3_ && var13 != p_175804_6_ && var14 != p_175804_5_ && var14 != p_175804_8_) {
/*     */               
/* 633 */               func_175811_a(worldIn, p_175804_10_, var13, var12, var14, p_175804_2_);
/*     */             }
/*     */             else {
/*     */               
/* 637 */               func_175811_a(worldIn, p_175804_9_, var13, var12, var14, p_175804_2_);
/*     */             } 
/*     */           }
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
/*     */   protected void fillWithRandomizedBlocks(World worldIn, StructureBoundingBox p_74882_2_, int p_74882_3_, int p_74882_4_, int p_74882_5_, int p_74882_6_, int p_74882_7_, int p_74882_8_, boolean p_74882_9_, Random p_74882_10_, BlockSelector p_74882_11_) {
/* 651 */     for (int var12 = p_74882_4_; var12 <= p_74882_7_; var12++) {
/*     */       
/* 653 */       for (int var13 = p_74882_3_; var13 <= p_74882_6_; var13++) {
/*     */         
/* 655 */         for (int var14 = p_74882_5_; var14 <= p_74882_8_; var14++) {
/*     */           
/* 657 */           if (!p_74882_9_ || func_175807_a(worldIn, var13, var12, var14, p_74882_2_).getBlock().getMaterial() != Material.air) {
/*     */             
/* 659 */             p_74882_11_.selectBlocks(p_74882_10_, var13, var12, var14, !(var12 != p_74882_4_ && var12 != p_74882_7_ && var13 != p_74882_3_ && var13 != p_74882_6_ && var14 != p_74882_5_ && var14 != p_74882_8_));
/* 660 */             func_175811_a(worldIn, p_74882_11_.func_180780_a(), var13, var12, var14, p_74882_2_);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175805_a(World worldIn, StructureBoundingBox p_175805_2_, Random p_175805_3_, float p_175805_4_, int p_175805_5_, int p_175805_6_, int p_175805_7_, int p_175805_8_, int p_175805_9_, int p_175805_10_, IBlockState p_175805_11_, IBlockState p_175805_12_, boolean p_175805_13_) {
/* 669 */     for (int var14 = p_175805_6_; var14 <= p_175805_9_; var14++) {
/*     */       
/* 671 */       for (int var15 = p_175805_5_; var15 <= p_175805_8_; var15++) {
/*     */         
/* 673 */         for (int var16 = p_175805_7_; var16 <= p_175805_10_; var16++) {
/*     */           
/* 675 */           if (p_175805_3_.nextFloat() <= p_175805_4_ && (!p_175805_13_ || func_175807_a(worldIn, var15, var14, var16, p_175805_2_).getBlock().getMaterial() != Material.air))
/*     */           {
/* 677 */             if (var14 != p_175805_6_ && var14 != p_175805_9_ && var15 != p_175805_5_ && var15 != p_175805_8_ && var16 != p_175805_7_ && var16 != p_175805_10_) {
/*     */               
/* 679 */               func_175811_a(worldIn, p_175805_12_, var15, var14, var16, p_175805_2_);
/*     */             }
/*     */             else {
/*     */               
/* 683 */               func_175811_a(worldIn, p_175805_11_, var15, var14, var16, p_175805_2_);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175809_a(World worldIn, StructureBoundingBox p_175809_2_, Random p_175809_3_, float p_175809_4_, int p_175809_5_, int p_175809_6_, int p_175809_7_, IBlockState p_175809_8_) {
/* 693 */     if (p_175809_3_.nextFloat() < p_175809_4_)
/*     */     {
/* 695 */       func_175811_a(worldIn, p_175809_8_, p_175809_5_, p_175809_6_, p_175809_7_, p_175809_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180777_a(World worldIn, StructureBoundingBox p_180777_2_, int p_180777_3_, int p_180777_4_, int p_180777_5_, int p_180777_6_, int p_180777_7_, int p_180777_8_, IBlockState p_180777_9_, boolean p_180777_10_) {
/* 701 */     float var11 = (p_180777_6_ - p_180777_3_ + 1);
/* 702 */     float var12 = (p_180777_7_ - p_180777_4_ + 1);
/* 703 */     float var13 = (p_180777_8_ - p_180777_5_ + 1);
/* 704 */     float var14 = p_180777_3_ + var11 / 2.0F;
/* 705 */     float var15 = p_180777_5_ + var13 / 2.0F;
/*     */     
/* 707 */     for (int var16 = p_180777_4_; var16 <= p_180777_7_; var16++) {
/*     */       
/* 709 */       float var17 = (var16 - p_180777_4_) / var12;
/*     */       
/* 711 */       for (int var18 = p_180777_3_; var18 <= p_180777_6_; var18++) {
/*     */         
/* 713 */         float var19 = (var18 - var14) / var11 * 0.5F;
/*     */         
/* 715 */         for (int var20 = p_180777_5_; var20 <= p_180777_8_; var20++) {
/*     */           
/* 717 */           float var21 = (var20 - var15) / var13 * 0.5F;
/*     */           
/* 719 */           if (!p_180777_10_ || func_175807_a(worldIn, var18, var16, var20, p_180777_2_).getBlock().getMaterial() != Material.air) {
/*     */             
/* 721 */             float var22 = var19 * var19 + var17 * var17 + var21 * var21;
/*     */             
/* 723 */             if (var22 <= 1.05F)
/*     */             {
/* 725 */               func_175811_a(worldIn, p_180777_9_, var18, var16, var20, p_180777_2_);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void clearCurrentPositionBlocksUpwards(World worldIn, int p_74871_2_, int p_74871_3_, int p_74871_4_, StructureBoundingBox p_74871_5_) {
/* 738 */     BlockPos var6 = new BlockPos(getXWithOffset(p_74871_2_, p_74871_4_), getYWithOffset(p_74871_3_), getZWithOffset(p_74871_2_, p_74871_4_));
/*     */     
/* 740 */     if (p_74871_5_.func_175898_b((Vec3i)var6))
/*     */     {
/* 742 */       while (!worldIn.isAirBlock(var6) && var6.getY() < 255) {
/*     */         
/* 744 */         worldIn.setBlockState(var6, Blocks.air.getDefaultState(), 2);
/* 745 */         var6 = var6.offsetUp();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175808_b(World worldIn, IBlockState p_175808_2_, int p_175808_3_, int p_175808_4_, int p_175808_5_, StructureBoundingBox p_175808_6_) {
/* 752 */     int var7 = getXWithOffset(p_175808_3_, p_175808_5_);
/* 753 */     int var8 = getYWithOffset(p_175808_4_);
/* 754 */     int var9 = getZWithOffset(p_175808_3_, p_175808_5_);
/*     */     
/* 756 */     if (p_175808_6_.func_175898_b((Vec3i)new BlockPos(var7, var8, var9)))
/*     */     {
/* 758 */       while ((worldIn.isAirBlock(new BlockPos(var7, var8, var9)) || worldIn.getBlockState(new BlockPos(var7, var8, var9)).getBlock().getMaterial().isLiquid()) && var8 > 1) {
/*     */         
/* 760 */         worldIn.setBlockState(new BlockPos(var7, var8, var9), p_175808_2_, 2);
/* 761 */         var8--;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_180778_a(World worldIn, StructureBoundingBox p_180778_2_, Random p_180778_3_, int p_180778_4_, int p_180778_5_, int p_180778_6_, List p_180778_7_, int p_180778_8_) {
/* 768 */     BlockPos var9 = new BlockPos(getXWithOffset(p_180778_4_, p_180778_6_), getYWithOffset(p_180778_5_), getZWithOffset(p_180778_4_, p_180778_6_));
/*     */     
/* 770 */     if (p_180778_2_.func_175898_b((Vec3i)var9) && worldIn.getBlockState(var9).getBlock() != Blocks.chest) {
/*     */       
/* 772 */       IBlockState var10 = Blocks.chest.getDefaultState();
/* 773 */       worldIn.setBlockState(var9, Blocks.chest.func_176458_f(worldIn, var9, var10), 2);
/* 774 */       TileEntity var11 = worldIn.getTileEntity(var9);
/*     */       
/* 776 */       if (var11 instanceof net.minecraft.tileentity.TileEntityChest)
/*     */       {
/* 778 */         WeightedRandomChestContent.generateChestContents(p_180778_3_, p_180778_7_, (IInventory)var11, p_180778_8_);
/*     */       }
/*     */       
/* 781 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 785 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_175806_a(World worldIn, StructureBoundingBox p_175806_2_, Random p_175806_3_, int p_175806_4_, int p_175806_5_, int p_175806_6_, int p_175806_7_, List p_175806_8_, int p_175806_9_) {
/* 791 */     BlockPos var10 = new BlockPos(getXWithOffset(p_175806_4_, p_175806_6_), getYWithOffset(p_175806_5_), getZWithOffset(p_175806_4_, p_175806_6_));
/*     */     
/* 793 */     if (p_175806_2_.func_175898_b((Vec3i)var10) && worldIn.getBlockState(var10).getBlock() != Blocks.dispenser) {
/*     */       
/* 795 */       worldIn.setBlockState(var10, Blocks.dispenser.getStateFromMeta(getMetadataWithOffset(Blocks.dispenser, p_175806_7_)), 2);
/* 796 */       TileEntity var11 = worldIn.getTileEntity(var10);
/*     */       
/* 798 */       if (var11 instanceof TileEntityDispenser)
/*     */       {
/* 800 */         WeightedRandomChestContent.func_177631_a(p_175806_3_, p_175806_8_, (TileEntityDispenser)var11, p_175806_9_);
/*     */       }
/*     */       
/* 803 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 807 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_175810_a(World worldIn, StructureBoundingBox p_175810_2_, Random p_175810_3_, int p_175810_4_, int p_175810_5_, int p_175810_6_, EnumFacing p_175810_7_) {
/* 813 */     BlockPos var8 = new BlockPos(getXWithOffset(p_175810_4_, p_175810_6_), getYWithOffset(p_175810_5_), getZWithOffset(p_175810_4_, p_175810_6_));
/*     */     
/* 815 */     if (p_175810_2_.func_175898_b((Vec3i)var8))
/*     */     {
/* 817 */       ItemDoor.func_179235_a(worldIn, var8, p_175810_7_.rotateYCCW(), Blocks.oak_door);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static abstract class BlockSelector
/*     */   {
/* 828 */     protected IBlockState field_151562_a = Blocks.air.getDefaultState();
/*     */     
/*     */     private static final String __OBFID = "CL_00000512";
/*     */     
/*     */     public abstract void selectBlocks(Random param1Random, int param1Int1, int param1Int2, int param1Int3, boolean param1Boolean);
/*     */     
/*     */     public IBlockState func_180780_a() {
/* 835 */       return this.field_151562_a;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 841 */     static final int[] field_176100_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00001969";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 848 */         field_176100_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 850 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 857 */         field_176100_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 859 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 866 */         field_176100_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 868 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 875 */         field_176100_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 877 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */