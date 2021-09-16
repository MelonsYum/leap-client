/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import net.minecraft.nbt.NBTTagIntArray;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
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
/*     */ public class StructureBoundingBox
/*     */ {
/*     */   public int minX;
/*     */   public int minY;
/*     */   public int minZ;
/*     */   public int maxX;
/*     */   public int maxY;
/*     */   public int maxZ;
/*     */   private static final String __OBFID = "CL_00000442";
/*     */   
/*     */   public StructureBoundingBox() {}
/*     */   
/*     */   public StructureBoundingBox(int[] p_i43000_1_) {
/*  34 */     if (p_i43000_1_.length == 6) {
/*     */       
/*  36 */       this.minX = p_i43000_1_[0];
/*  37 */       this.minY = p_i43000_1_[1];
/*  38 */       this.minZ = p_i43000_1_[2];
/*  39 */       this.maxX = p_i43000_1_[3];
/*  40 */       this.maxY = p_i43000_1_[4];
/*  41 */       this.maxZ = p_i43000_1_[5];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox getNewBoundingBox() {
/*  50 */     return new StructureBoundingBox(2147483647, 2147483647, 2147483647, -2147483648, -2147483648, -2147483648);
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox func_175897_a(int p_175897_0_, int p_175897_1_, int p_175897_2_, int p_175897_3_, int p_175897_4_, int p_175897_5_, int p_175897_6_, int p_175897_7_, int p_175897_8_, EnumFacing p_175897_9_) {
/*  55 */     switch (SwitchEnumFacing.field_175895_a[p_175897_9_.ordinal()]) {
/*     */       
/*     */       case 1:
/*  58 */         return new StructureBoundingBox(p_175897_0_ + p_175897_3_, p_175897_1_ + p_175897_4_, p_175897_2_ - p_175897_8_ + 1 + p_175897_5_, p_175897_0_ + p_175897_6_ - 1 + p_175897_3_, p_175897_1_ + p_175897_7_ - 1 + p_175897_4_, p_175897_2_ + p_175897_5_);
/*     */       
/*     */       case 2:
/*  61 */         return new StructureBoundingBox(p_175897_0_ + p_175897_3_, p_175897_1_ + p_175897_4_, p_175897_2_ + p_175897_5_, p_175897_0_ + p_175897_6_ - 1 + p_175897_3_, p_175897_1_ + p_175897_7_ - 1 + p_175897_4_, p_175897_2_ + p_175897_8_ - 1 + p_175897_5_);
/*     */       
/*     */       case 3:
/*  64 */         return new StructureBoundingBox(p_175897_0_ - p_175897_8_ + 1 + p_175897_5_, p_175897_1_ + p_175897_4_, p_175897_2_ + p_175897_3_, p_175897_0_ + p_175897_5_, p_175897_1_ + p_175897_7_ - 1 + p_175897_4_, p_175897_2_ + p_175897_6_ - 1 + p_175897_3_);
/*     */       
/*     */       case 4:
/*  67 */         return new StructureBoundingBox(p_175897_0_ + p_175897_5_, p_175897_1_ + p_175897_4_, p_175897_2_ + p_175897_3_, p_175897_0_ + p_175897_8_ - 1 + p_175897_5_, p_175897_1_ + p_175897_7_ - 1 + p_175897_4_, p_175897_2_ + p_175897_6_ - 1 + p_175897_3_);
/*     */     } 
/*     */     
/*  70 */     return new StructureBoundingBox(p_175897_0_ + p_175897_3_, p_175897_1_ + p_175897_4_, p_175897_2_ + p_175897_5_, p_175897_0_ + p_175897_6_ - 1 + p_175897_3_, p_175897_1_ + p_175897_7_ - 1 + p_175897_4_, p_175897_2_ + p_175897_8_ - 1 + p_175897_5_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox func_175899_a(int p_175899_0_, int p_175899_1_, int p_175899_2_, int p_175899_3_, int p_175899_4_, int p_175899_5_) {
/*  76 */     return new StructureBoundingBox(Math.min(p_175899_0_, p_175899_3_), Math.min(p_175899_1_, p_175899_4_), Math.min(p_175899_2_, p_175899_5_), Math.max(p_175899_0_, p_175899_3_), Math.max(p_175899_1_, p_175899_4_), Math.max(p_175899_2_, p_175899_5_));
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox(StructureBoundingBox p_i2031_1_) {
/*  81 */     this.minX = p_i2031_1_.minX;
/*  82 */     this.minY = p_i2031_1_.minY;
/*  83 */     this.minZ = p_i2031_1_.minZ;
/*  84 */     this.maxX = p_i2031_1_.maxX;
/*  85 */     this.maxY = p_i2031_1_.maxY;
/*  86 */     this.maxZ = p_i2031_1_.maxZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox(int p_i2032_1_, int p_i2032_2_, int p_i2032_3_, int p_i2032_4_, int p_i2032_5_, int p_i2032_6_) {
/*  91 */     this.minX = p_i2032_1_;
/*  92 */     this.minY = p_i2032_2_;
/*  93 */     this.minZ = p_i2032_3_;
/*  94 */     this.maxX = p_i2032_4_;
/*  95 */     this.maxY = p_i2032_5_;
/*  96 */     this.maxZ = p_i2032_6_;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox(Vec3i p_i45626_1_, Vec3i p_i45626_2_) {
/* 101 */     this.minX = Math.min(p_i45626_1_.getX(), p_i45626_2_.getX());
/* 102 */     this.minY = Math.min(p_i45626_1_.getY(), p_i45626_2_.getY());
/* 103 */     this.minZ = Math.min(p_i45626_1_.getZ(), p_i45626_2_.getZ());
/* 104 */     this.maxX = Math.max(p_i45626_1_.getX(), p_i45626_2_.getX());
/* 105 */     this.maxY = Math.max(p_i45626_1_.getY(), p_i45626_2_.getY());
/* 106 */     this.maxZ = Math.max(p_i45626_1_.getZ(), p_i45626_2_.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox(int p_i2033_1_, int p_i2033_2_, int p_i2033_3_, int p_i2033_4_) {
/* 111 */     this.minX = p_i2033_1_;
/* 112 */     this.minZ = p_i2033_2_;
/* 113 */     this.maxX = p_i2033_3_;
/* 114 */     this.maxZ = p_i2033_4_;
/* 115 */     this.minY = 1;
/* 116 */     this.maxY = 512;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersectsWith(StructureBoundingBox p_78884_1_) {
/* 124 */     return (this.maxX >= p_78884_1_.minX && this.minX <= p_78884_1_.maxX && this.maxZ >= p_78884_1_.minZ && this.minZ <= p_78884_1_.maxZ && this.maxY >= p_78884_1_.minY && this.minY <= p_78884_1_.maxY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersectsWith(int p_78885_1_, int p_78885_2_, int p_78885_3_, int p_78885_4_) {
/* 132 */     return (this.maxX >= p_78885_1_ && this.minX <= p_78885_3_ && this.maxZ >= p_78885_2_ && this.minZ <= p_78885_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void expandTo(StructureBoundingBox p_78888_1_) {
/* 140 */     this.minX = Math.min(this.minX, p_78888_1_.minX);
/* 141 */     this.minY = Math.min(this.minY, p_78888_1_.minY);
/* 142 */     this.minZ = Math.min(this.minZ, p_78888_1_.minZ);
/* 143 */     this.maxX = Math.max(this.maxX, p_78888_1_.maxX);
/* 144 */     this.maxY = Math.max(this.maxY, p_78888_1_.maxY);
/* 145 */     this.maxZ = Math.max(this.maxZ, p_78888_1_.maxZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void offset(int p_78886_1_, int p_78886_2_, int p_78886_3_) {
/* 153 */     this.minX += p_78886_1_;
/* 154 */     this.minY += p_78886_2_;
/* 155 */     this.minZ += p_78886_3_;
/* 156 */     this.maxX += p_78886_1_;
/* 157 */     this.maxY += p_78886_2_;
/* 158 */     this.maxZ += p_78886_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175898_b(Vec3i p_175898_1_) {
/* 163 */     return (p_175898_1_.getX() >= this.minX && p_175898_1_.getX() <= this.maxX && p_175898_1_.getZ() >= this.minZ && p_175898_1_.getZ() <= this.maxZ && p_175898_1_.getY() >= this.minY && p_175898_1_.getY() <= this.maxY);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3i func_175896_b() {
/* 168 */     return new Vec3i(this.maxX - this.minX, this.maxY - this.minY, this.maxZ - this.minZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSize() {
/* 176 */     return this.maxX - this.minX + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getYSize() {
/* 184 */     return this.maxY - this.minY + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZSize() {
/* 192 */     return this.maxZ - this.minZ + 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3i func_180717_f() {
/* 197 */     return (Vec3i)new BlockPos(this.minX + (this.maxX - this.minX + 1) / 2, this.minY + (this.maxY - this.minY + 1) / 2, this.minZ + (this.maxZ - this.minZ + 1) / 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 202 */     return Objects.toStringHelper(this).add("x0", this.minX).add("y0", this.minY).add("z0", this.minZ).add("x1", this.maxX).add("y1", this.maxY).add("z1", this.maxZ).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagIntArray func_151535_h() {
/* 207 */     return new NBTTagIntArray(new int[] { this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 212 */     static final int[] field_175895_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00001999";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 219 */         field_175895_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 221 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 228 */         field_175895_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 230 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 237 */         field_175895_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 239 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 246 */         field_175895_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 248 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureBoundingBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */