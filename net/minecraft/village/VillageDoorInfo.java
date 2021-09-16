/*     */ package net.minecraft.village;
/*     */ 
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ 
/*     */ public class VillageDoorInfo
/*     */ {
/*     */   private final BlockPos field_179859_a;
/*     */   private final BlockPos field_179857_b;
/*     */   private final EnumFacing field_179858_c;
/*     */   private int lastActivityTimestamp;
/*     */   private boolean isDetachedFromVillageFlag;
/*     */   private int doorOpeningRestrictionCounter;
/*     */   private static final String __OBFID = "CL_00001630";
/*     */   
/*     */   public VillageDoorInfo(BlockPos p_i45871_1_, int p_i45871_2_, int p_i45871_3_, int p_i45871_4_) {
/*  18 */     this(p_i45871_1_, func_179854_a(p_i45871_2_, p_i45871_3_), p_i45871_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static EnumFacing func_179854_a(int p_179854_0_, int p_179854_1_) {
/*  23 */     return (p_179854_0_ < 0) ? EnumFacing.WEST : ((p_179854_0_ > 0) ? EnumFacing.EAST : ((p_179854_1_ < 0) ? EnumFacing.NORTH : EnumFacing.SOUTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public VillageDoorInfo(BlockPos p_i45872_1_, EnumFacing p_i45872_2_, int p_i45872_3_) {
/*  28 */     this.field_179859_a = p_i45872_1_;
/*  29 */     this.field_179858_c = p_i45872_2_;
/*  30 */     this.field_179857_b = p_i45872_1_.offset(p_i45872_2_, 2);
/*  31 */     this.lastActivityTimestamp = p_i45872_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDistanceSquared(int p_75474_1_, int p_75474_2_, int p_75474_3_) {
/*  39 */     return (int)this.field_179859_a.distanceSq(p_75474_1_, p_75474_2_, p_75474_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179848_a(BlockPos p_179848_1_) {
/*  44 */     return (int)p_179848_1_.distanceSq((Vec3i)func_179852_d());
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179846_b(BlockPos p_179846_1_) {
/*  49 */     return (int)this.field_179857_b.distanceSq((Vec3i)p_179846_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179850_c(BlockPos p_179850_1_) {
/*  54 */     int var2 = p_179850_1_.getX() - this.field_179859_a.getX();
/*  55 */     int var3 = p_179850_1_.getZ() - this.field_179859_a.getY();
/*  56 */     return (var2 * this.field_179858_c.getFrontOffsetX() + var3 * this.field_179858_c.getFrontOffsetZ() >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetDoorOpeningRestrictionCounter() {
/*  61 */     this.doorOpeningRestrictionCounter = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementDoorOpeningRestrictionCounter() {
/*  66 */     this.doorOpeningRestrictionCounter++;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDoorOpeningRestrictionCounter() {
/*  71 */     return this.doorOpeningRestrictionCounter;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_179852_d() {
/*  76 */     return this.field_179859_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_179856_e() {
/*  81 */     return this.field_179857_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179847_f() {
/*  86 */     return this.field_179858_c.getFrontOffsetX() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179855_g() {
/*  91 */     return this.field_179858_c.getFrontOffsetZ() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInsidePosY() {
/*  96 */     return this.lastActivityTimestamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179849_a(int p_179849_1_) {
/* 101 */     this.lastActivityTimestamp = p_179849_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179851_i() {
/* 106 */     return this.isDetachedFromVillageFlag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179853_a(boolean p_179853_1_) {
/* 111 */     this.isDetachedFromVillageFlag = p_179853_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\village\VillageDoorInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */