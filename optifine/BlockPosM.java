/*     */ package optifine;
/*     */ 
/*     */ import com.google.common.collect.AbstractIterator;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class BlockPosM
/*     */   extends BlockPos
/*     */ {
/*     */   private int mx;
/*     */   private int my;
/*     */   private int mz;
/*     */   private int level;
/*     */   private BlockPosM[] facings;
/*     */   private boolean needsUpdate;
/*     */   
/*     */   public BlockPosM(int x, int y, int z) {
/*  20 */     this(x, y, z, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosM(double xIn, double yIn, double zIn) {
/*  25 */     this(MathHelper.floor_double(xIn), MathHelper.floor_double(yIn), MathHelper.floor_double(zIn));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosM(int x, int y, int z, int level) {
/*  30 */     super(0, 0, 0);
/*  31 */     this.mx = x;
/*  32 */     this.my = y;
/*  33 */     this.mz = z;
/*  34 */     this.level = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getX() {
/*  42 */     return this.mx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getY() {
/*  50 */     return this.my;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZ() {
/*  58 */     return this.mz;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXyz(int x, int y, int z) {
/*  63 */     this.mx = x;
/*  64 */     this.my = y;
/*  65 */     this.mz = z;
/*  66 */     this.needsUpdate = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setXyz(double xIn, double yIn, double zIn) {
/*  71 */     setXyz(MathHelper.floor_double(xIn), MathHelper.floor_double(yIn), MathHelper.floor_double(zIn));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offset(EnumFacing facing) {
/*  79 */     if (this.level <= 0)
/*     */     {
/*  81 */       return super.offset(facing, 1);
/*     */     }
/*     */ 
/*     */     
/*  85 */     if (this.facings == null)
/*     */     {
/*  87 */       this.facings = new BlockPosM[EnumFacing.VALUES.length];
/*     */     }
/*     */     
/*  90 */     if (this.needsUpdate)
/*     */     {
/*  92 */       update();
/*     */     }
/*     */     
/*  95 */     int index = facing.getIndex();
/*  96 */     BlockPosM bpm = this.facings[index];
/*     */     
/*  98 */     if (bpm == null) {
/*     */       
/* 100 */       int nx = this.mx + facing.getFrontOffsetX();
/* 101 */       int ny = this.my + facing.getFrontOffsetY();
/* 102 */       int nz = this.mz + facing.getFrontOffsetZ();
/* 103 */       bpm = new BlockPosM(nx, ny, nz, this.level - 1);
/* 104 */       this.facings[index] = bpm;
/*     */     } 
/*     */     
/* 107 */     return bpm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos offset(EnumFacing facing, int n) {
/* 116 */     return (n == 1) ? offset(facing) : super.offset(facing, n);
/*     */   }
/*     */ 
/*     */   
/*     */   private void update() {
/* 121 */     for (int i = 0; i < 6; i++) {
/*     */       
/* 123 */       BlockPosM bpm = this.facings[i];
/*     */       
/* 125 */       if (bpm != null) {
/*     */         
/* 127 */         EnumFacing facing = EnumFacing.VALUES[i];
/* 128 */         int nx = this.mx + facing.getFrontOffsetX();
/* 129 */         int ny = this.my + facing.getFrontOffsetY();
/* 130 */         int nz = this.mz + facing.getFrontOffsetZ();
/* 131 */         bpm.setXyz(nx, ny, nz);
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     this.needsUpdate = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Iterable getAllInBoxMutable(BlockPos from, BlockPos to) {
/* 140 */     final BlockPos posFrom = new BlockPos(Math.min(from.getX(), to.getX()), Math.min(from.getY(), to.getY()), Math.min(from.getZ(), to.getZ()));
/* 141 */     final BlockPos posTo = new BlockPos(Math.max(from.getX(), to.getX()), Math.max(from.getY(), to.getY()), Math.max(from.getZ(), to.getZ()));
/* 142 */     return new Iterable()
/*     */       {
/*     */         public Iterator iterator()
/*     */         {
/* 146 */           return (Iterator)new AbstractIterator()
/*     */             {
/* 148 */               private BlockPosM theBlockPosM = null;
/*     */               
/*     */               protected BlockPosM computeNext0() {
/* 151 */                 if (this.theBlockPosM == null) {
/*     */                   
/* 153 */                   this.theBlockPosM = new BlockPosM(posFrom.getX(), posFrom.getY(), posFrom.getZ(), 3);
/* 154 */                   return this.theBlockPosM;
/*     */                 } 
/* 156 */                 if (this.theBlockPosM.equals(posTo))
/*     */                 {
/* 158 */                   return (BlockPosM)endOfData();
/*     */                 }
/*     */ 
/*     */                 
/* 162 */                 int bx = this.theBlockPosM.getX();
/* 163 */                 int by = this.theBlockPosM.getY();
/* 164 */                 int bz = this.theBlockPosM.getZ();
/*     */                 
/* 166 */                 if (bx < posTo.getX()) {
/*     */                   
/* 168 */                   bx++;
/*     */                 }
/* 170 */                 else if (by < posTo.getY()) {
/*     */                   
/* 172 */                   bx = posFrom.getX();
/* 173 */                   by++;
/*     */                 }
/* 175 */                 else if (bz < posTo.getZ()) {
/*     */                   
/* 177 */                   bx = posFrom.getX();
/* 178 */                   by = posFrom.getY();
/* 179 */                   bz++;
/*     */                 } 
/*     */                 
/* 182 */                 this.theBlockPosM.setXyz(bx, by, bz);
/* 183 */                 return this.theBlockPosM;
/*     */               }
/*     */ 
/*     */               
/*     */               protected Object computeNext() {
/* 188 */                 return computeNext0();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getImmutable() {
/* 197 */     return new BlockPos(getX(), getY(), getZ());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\BlockPosM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */