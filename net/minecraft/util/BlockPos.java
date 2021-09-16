/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.collect.AbstractIterator;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class BlockPos
/*     */   extends Vec3i
/*     */ {
/*  10 */   public static final BlockPos ORIGIN = new BlockPos(0, 0, 0);
/*  11 */   private static final int field_177990_b = 1 + MathHelper.calculateLogBaseTwo(MathHelper.roundUpToPowerOfTwo(30000000));
/*  12 */   private static final int field_177991_c = field_177990_b;
/*  13 */   private static final int field_177989_d = 64 - field_177990_b - field_177991_c;
/*  14 */   private static final int field_177987_f = 0 + field_177991_c;
/*  15 */   private static final int field_177988_g = field_177987_f + field_177989_d;
/*  16 */   private static final long field_177994_h = (1L << field_177990_b) - 1L;
/*  17 */   private static final long field_177995_i = (1L << field_177989_d) - 1L;
/*  18 */   private static final long field_177993_j = (1L << field_177991_c) - 1L;
/*     */   
/*     */   private static final String __OBFID = "CL_00002334";
/*     */   
/*     */   public BlockPos(int x, int y, int z) {
/*  23 */     super(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos(double x, double y, double z) {
/*  28 */     super(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos(Entity p_i46032_1_) {
/*  33 */     this(p_i46032_1_.posX, p_i46032_1_.posY, p_i46032_1_.posZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos(Vec3 p_i46033_1_) {
/*  38 */     this(p_i46033_1_.xCoord, p_i46033_1_.yCoord, p_i46033_1_.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos(Vec3i p_i46034_1_) {
/*  43 */     this(p_i46034_1_.getX(), p_i46034_1_.getY(), p_i46034_1_.getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos add(double x, double y, double z) {
/*  55 */     return new BlockPos(getX() + x, getY() + y, getZ() + z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos add(int x, int y, int z) {
/*  67 */     return new BlockPos(getX() + x, getY() + y, getZ() + z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos add(Vec3i vec) {
/*  75 */     return new BlockPos(getX() + vec.getX(), getY() + vec.getY(), getZ() + vec.getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos subtract(Vec3i vec) {
/*  83 */     return new BlockPos(getX() - vec.getX(), getY() - vec.getY(), getZ() - vec.getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos multiply(int factor) {
/*  91 */     return new BlockPos(getX() * factor, getY() * factor, getZ() * factor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetUp() {
/*  99 */     return offsetUp(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetUp(int n) {
/* 107 */     return offset(EnumFacing.UP, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetDown() {
/* 115 */     return offsetDown(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetDown(int n) {
/* 123 */     return offset(EnumFacing.DOWN, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetNorth() {
/* 131 */     return offsetNorth(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetNorth(int n) {
/* 139 */     return offset(EnumFacing.NORTH, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetSouth() {
/* 147 */     return offsetSouth(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetSouth(int n) {
/* 155 */     return offset(EnumFacing.SOUTH, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetWest() {
/* 163 */     return offsetWest(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetWest(int n) {
/* 171 */     return offset(EnumFacing.WEST, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetEast() {
/* 179 */     return offsetEast(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offsetEast(int n) {
/* 187 */     return offset(EnumFacing.EAST, n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offset(EnumFacing facing) {
/* 195 */     return offset(facing, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offset(EnumFacing facing, int n) {
/* 203 */     return new BlockPos(getX() + facing.getFrontOffsetX() * n, getY() + facing.getFrontOffsetY() * n, getZ() + facing.getFrontOffsetZ() * n);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos crossProductBP(Vec3i vec) {
/* 212 */     return new BlockPos(getY() * vec.getZ() - getZ() * vec.getY(), getZ() * vec.getX() - getX() * vec.getZ(), getX() * vec.getY() - getY() * vec.getX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long toLong() {
/* 220 */     return (getX() & field_177994_h) << field_177988_g | (getY() & field_177995_i) << field_177987_f | (getZ() & field_177993_j) << 0L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos fromLong(long serialized) {
/* 228 */     int var2 = (int)(serialized << 64 - field_177988_g - field_177990_b >> 64 - field_177990_b);
/* 229 */     int var3 = (int)(serialized << 64 - field_177987_f - field_177989_d >> 64 - field_177989_d);
/* 230 */     int var4 = (int)(serialized << 64 - field_177991_c >> 64 - field_177991_c);
/* 231 */     return new BlockPos(var2, var3, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable getAllInBox(BlockPos from, BlockPos to) {
/* 242 */     final BlockPos var2 = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
/* 243 */     final BlockPos var3 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
/* 244 */     return new Iterable()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002333";
/*     */         
/*     */         public Iterator iterator() {
/* 249 */           return (Iterator)new AbstractIterator()
/*     */             {
/* 251 */               private BlockPos lastReturned = null;
/*     */               private static final String __OBFID = "CL_00002332";
/*     */               
/*     */               protected BlockPos computeNext0() {
/* 255 */                 if (this.lastReturned == null) {
/*     */                   
/* 257 */                   this.lastReturned = var2;
/* 258 */                   return this.lastReturned;
/*     */                 } 
/* 260 */                 if (this.lastReturned.equals(var3))
/*     */                 {
/* 262 */                   return (BlockPos)endOfData();
/*     */                 }
/*     */ 
/*     */                 
/* 266 */                 int var1 = this.lastReturned.getX();
/* 267 */                 int var2x = this.lastReturned.getY();
/* 268 */                 int var3x = this.lastReturned.getZ();
/*     */                 
/* 270 */                 if (var1 < var3.getX()) {
/*     */                   
/* 272 */                   var1++;
/*     */                 }
/* 274 */                 else if (var2x < var3.getY()) {
/*     */                   
/* 276 */                   var1 = var2.getX();
/* 277 */                   var2x++;
/*     */                 }
/* 279 */                 else if (var3x < var3.getZ()) {
/*     */                   
/* 281 */                   var1 = var2.getX();
/* 282 */                   var2x = var2.getY();
/* 283 */                   var3x++;
/*     */                 } 
/*     */                 
/* 286 */                 this.lastReturned = new BlockPos(var1, var2x, var3x);
/* 287 */                 return this.lastReturned;
/*     */               }
/*     */ 
/*     */               
/*     */               protected Object computeNext() {
/* 292 */                 return computeNext0();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable getAllInBoxMutable(BlockPos from, BlockPos to) {
/* 308 */     final BlockPos var2 = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
/* 309 */     final BlockPos var3 = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
/* 310 */     return new Iterable()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002331";
/*     */         
/*     */         public Iterator iterator() {
/* 315 */           return (Iterator)new AbstractIterator()
/*     */             {
/* 317 */               private BlockPos.MutableBlockPos theBlockPos = null;
/*     */               private static final String __OBFID = "CL_00002330";
/*     */               
/*     */               protected BlockPos.MutableBlockPos computeNext0() {
/* 321 */                 if (this.theBlockPos == null) {
/*     */                   
/* 323 */                   this.theBlockPos = new BlockPos.MutableBlockPos(var2.getX(), var2.getY(), var2.getZ(), null);
/* 324 */                   return this.theBlockPos;
/*     */                 } 
/* 326 */                 if (this.theBlockPos.equals(var3))
/*     */                 {
/* 328 */                   return (BlockPos.MutableBlockPos)endOfData();
/*     */                 }
/*     */ 
/*     */                 
/* 332 */                 int var1 = this.theBlockPos.getX();
/* 333 */                 int var2xx = this.theBlockPos.getY();
/* 334 */                 int var3x = this.theBlockPos.getZ();
/*     */                 
/* 336 */                 if (var1 < var3.getX()) {
/*     */                   
/* 338 */                   var1++;
/*     */                 }
/* 340 */                 else if (var2xx < var3.getY()) {
/*     */                   
/* 342 */                   var1 = var2.getX();
/* 343 */                   var2xx++;
/*     */                 }
/* 345 */                 else if (var3x < var3.getZ()) {
/*     */                   
/* 347 */                   var1 = var2.getX();
/* 348 */                   var2xx = var2.getY();
/* 349 */                   var3x++;
/*     */                 } 
/*     */                 
/* 352 */                 this.theBlockPos.x = var1;
/* 353 */                 this.theBlockPos.y = var2xx;
/* 354 */                 this.theBlockPos.z = var3x;
/* 355 */                 return this.theBlockPos;
/*     */               }
/*     */ 
/*     */               
/*     */               protected Object computeNext() {
/* 360 */                 return computeNext0();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3i crossProduct(Vec3i vec) {
/* 372 */     return crossProductBP(vec);
/*     */   }
/*     */   
/*     */   public static final class MutableBlockPos
/*     */     extends BlockPos
/*     */   {
/*     */     public int x;
/*     */     public int y;
/*     */     public int z;
/*     */     private static final String __OBFID = "CL_00002329";
/*     */     
/*     */     private MutableBlockPos(int x_, int y_, int z_) {
/* 384 */       super(0, 0, 0);
/* 385 */       this.x = x_;
/* 386 */       this.y = y_;
/* 387 */       this.z = z_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getX() {
/* 392 */       return this.x;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getY() {
/* 397 */       return this.y;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getZ() {
/* 402 */       return this.z;
/*     */     }
/*     */ 
/*     */     
/*     */     public Vec3i crossProduct(Vec3i vec) {
/* 407 */       return crossProductBP(vec);
/*     */     }
/*     */ 
/*     */     
/*     */     MutableBlockPos(int p_i46025_1_, int p_i46025_2_, int p_i46025_3_, Object p_i46025_4_) {
/* 412 */       this(p_i46025_1_, p_i46025_2_, p_i46025_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\BlockPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */