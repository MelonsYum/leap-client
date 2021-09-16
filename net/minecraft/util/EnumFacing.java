/*     */ package net.minecraft.util;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ public enum EnumFacing implements IStringSerializable { private final int index; private final int opposite; private final int horizontalIndex; private final String name; private final Axis axis; private final AxisDirection axisDirection; private final Vec3i directionVec; public static final EnumFacing[] VALUES; private static final EnumFacing[] HORIZONTALS; private static final Map NAME_LOOKUP; private static final String __OBFID = "CL_00001201"; private static final EnumFacing[] $VALUES; EnumFacing(String p_i46016_1_, int p_i46016_2_, int indexIn, int oppositeIn, int horizontalIndexIn, String nameIn, AxisDirection axisDirectionIn, Axis axisIn, Vec3i directionVecIn) { this.index = indexIn; this.horizontalIndex = horizontalIndexIn; this.opposite = oppositeIn; this.name = nameIn; this.axis = axisIn; this.axisDirection = axisDirectionIn; this.directionVec = directionVecIn; } public int getIndex() { return this.index; } public int getHorizontalIndex() { return this.horizontalIndex; } public AxisDirection getAxisDirection() { return this.axisDirection; } public EnumFacing getOpposite() { return VALUES[this.opposite]; } public EnumFacing rotateAround(Axis axis) { switch (SwitchPlane.AXIS_LOOKUP[axis.ordinal()]) { case 1: if (this != WEST && this != EAST)
/*     */           return rotateX();  return this;
/*     */       case 2: if (this != UP && this != DOWN)
/*     */           return rotateY();  return this;
/*     */       case 3: if (this != NORTH && this != SOUTH)
/*  12 */           return rotateZ();  return this; }  throw new IllegalStateException("Unable to get CW facing for axis " + axis); } DOWN("DOWN", 0, 0, 1, -1, "down", AxisDirection.NEGATIVE, Axis.Y, new Vec3i(0, -1, 0)),
/*  13 */   UP("UP", 1, 1, 0, -1, "up", AxisDirection.POSITIVE, Axis.Y, new Vec3i(0, 1, 0)),
/*  14 */   NORTH("NORTH", 2, 2, 3, 2, "north", AxisDirection.NEGATIVE, Axis.Z, new Vec3i(0, 0, -1)),
/*  15 */   SOUTH("SOUTH", 3, 3, 2, 0, "south", AxisDirection.POSITIVE, Axis.Z, new Vec3i(0, 0, 1)),
/*  16 */   WEST("WEST", 4, 4, 5, 1, "west", AxisDirection.NEGATIVE, Axis.X, new Vec3i(-1, 0, 0)),
/*  17 */   EAST("EAST", 5, 5, 4, 3, "east", AxisDirection.POSITIVE, Axis.X, new Vec3i(1, 0, 0));
/*     */   public EnumFacing rotateY() { switch (SwitchPlane.FACING_LOOKUP[ordinal()]) { case 1: return EAST;case 2: return SOUTH;case 3: return WEST;
/*     */       case 4: return NORTH; }  throw new IllegalStateException("Unable to get Y-rotated facing of " + this); }
/*     */   private EnumFacing rotateX() { switch (SwitchPlane.FACING_LOOKUP[ordinal()]) { case 1: return DOWN;
/*     */       default: throw new IllegalStateException("Unable to get X-rotated facing of " + this);
/*     */       case 3: return UP;
/*     */       case 5: return NORTH;
/*     */       case 6: break; }  return SOUTH; }
/*     */   private EnumFacing rotateZ() { switch (SwitchPlane.FACING_LOOKUP[ordinal()]) { case 2: return DOWN;
/*     */       default: throw new IllegalStateException("Unable to get Z-rotated facing of " + this);
/*     */       case 4: return UP;
/*     */       case 5: return EAST;
/*     */       case 6: break; }  return WEST; }
/*     */   public EnumFacing rotateYCCW() { switch (SwitchPlane.FACING_LOOKUP[ordinal()]) { case 1: return WEST;
/*     */       case 2: return NORTH;
/*     */       case 3: return EAST;
/*     */       case 4: return SOUTH; }  throw new IllegalStateException("Unable to get CCW facing of " + this); }
/*     */   public int getFrontOffsetX() { return (this.axis == Axis.X) ? this.axisDirection.getOffset() : 0; }
/*  35 */   public int getFrontOffsetY() { return (this.axis == Axis.Y) ? this.axisDirection.getOffset() : 0; } static { VALUES = new EnumFacing[6];
/*     */ 
/*     */     
/*  38 */     HORIZONTALS = new EnumFacing[4];
/*  39 */     NAME_LOOKUP = Maps.newHashMap();
/*     */ 
/*     */     
/*  42 */     $VALUES = new EnumFacing[] { DOWN, UP, NORTH, SOUTH, WEST, EAST };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 338 */     EnumFacing[] var0 = values();
/* 339 */     int var1 = var0.length;
/*     */     
/* 341 */     for (int var2 = 0; var2 < var1; var2++)
/*     */     
/* 343 */     { EnumFacing var3 = var0[var2];
/* 344 */       VALUES[var3.index] = var3;
/*     */       
/* 346 */       if (var3.getAxis().isHorizontal())
/*     */       {
/* 348 */         HORIZONTALS[var3.horizontalIndex] = var3;
/*     */       }
/*     */       
/* 351 */       NAME_LOOKUP.put(var3.getName2().toLowerCase(), var3); }  } public int getFrontOffsetZ() { return (this.axis == Axis.Z) ? this.axisDirection.getOffset() : 0; } public String getName2() { return this.name; } public Axis getAxis() { return this.axis; } public static EnumFacing byName(String name) { return (name == null) ? null : (EnumFacing)NAME_LOOKUP.get(name.toLowerCase()); } public static EnumFacing getFront(int index) { return VALUES[MathHelper.abs_int(index % VALUES.length)]; } public static EnumFacing getHorizontal(int p_176731_0_) { return HORIZONTALS[MathHelper.abs_int(p_176731_0_ % HORIZONTALS.length)]; } public static EnumFacing fromAngle(double angle) { return getHorizontal(MathHelper.floor_double(angle / 90.0D + 0.5D) & 0x3); } public static EnumFacing random(Random rand) { return values()[rand.nextInt((values()).length)]; }
/*     */   public static EnumFacing func_176737_a(float p_176737_0_, float p_176737_1_, float p_176737_2_) { EnumFacing var3 = NORTH; float var4 = Float.MIN_VALUE; EnumFacing[] var5 = values(); int var6 = var5.length; for (int var7 = 0; var7 < var6; var7++) { EnumFacing var8 = var5[var7]; float var9 = p_176737_0_ * var8.directionVec.getX() + p_176737_1_ * var8.directionVec.getY() + p_176737_2_ * var8.directionVec.getZ(); if (var9 > var4) { var4 = var9; var3 = var8; }  }  return var3; }
/*     */   public String toString() { return this.name; }
/*     */   public String getName() { return this.name; }
/*     */   public Vec3i getDirectionVec() { return this.directionVec; }
/* 356 */   public enum Axis implements Predicate, IStringSerializable { X("X", 0, "X", 0, "x", EnumFacing.Plane.HORIZONTAL),
/* 357 */     Y("Y", 1, "Y", 1, "y", EnumFacing.Plane.VERTICAL),
/* 358 */     Z("Z", 2, "Z", 2, "z", EnumFacing.Plane.HORIZONTAL);
/* 359 */     private static final Map NAME_LOOKUP = Maps.newHashMap();
/*     */     private final String name;
/*     */     private final EnumFacing.Plane plane;
/* 362 */     private static final Axis[] $VALUES = new Axis[] { X, Y, Z };
/*     */     
/*     */     private static final String __OBFID = "CL_00002321";
/* 365 */     private static final Axis[] $VALUES$ = new Axis[] { X, Y, Z };
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 419 */       Axis[] var0 = values();
/* 420 */       int var1 = var0.length;
/*     */       
/* 422 */       for (int var2 = 0; var2 < var1; var2++)
/*     */       
/* 424 */       { Axis var3 = var0[var2];
/* 425 */         NAME_LOOKUP.put(var3.getName2().toLowerCase(), var3); } 
/*     */     } Axis(String p_i46390_1_, int p_i46390_2_, String p_i46015_1_, int p_i46015_2_, String name, EnumFacing.Plane plane) { this.name = name; this.plane = plane; } public static Axis byName(String name) { return (name == null) ? null : (Axis)NAME_LOOKUP.get(name.toLowerCase()); } public String getName2() { return this.name; } public boolean isVertical() { return (this.plane == EnumFacing.Plane.VERTICAL); } public boolean isHorizontal() { return (this.plane == EnumFacing.Plane.HORIZONTAL); } public String toString() { return this.name; }
/*     */     public boolean apply(EnumFacing facing) { return (facing != null && facing.getAxis() == this); }
/*     */     public EnumFacing.Plane getPlane() { return this.plane; }
/*     */     public String getName() { return this.name; }
/*     */     public boolean apply(Object p_apply_1_) { return apply((EnumFacing)p_apply_1_); } }
/* 431 */   public enum AxisDirection { POSITIVE("POSITIVE", 0, "POSITIVE", 0, 1, "Towards positive"),
/* 432 */     NEGATIVE("NEGATIVE", 1, "NEGATIVE", 1, -1, "Towards negative");
/*     */     private final int offset;
/*     */     private final String description;
/* 435 */     private static final AxisDirection[] $VALUES = new AxisDirection[] { POSITIVE, NEGATIVE }; private static final String __OBFID = "CL_00002320";
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     AxisDirection(String p_i46391_1_, int p_i46391_2_, String p_i46014_1_, int p_i46014_2_, int offset, String description) {
/* 441 */       this.offset = offset;
/* 442 */       this.description = description;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getOffset() {
/* 447 */       return this.offset;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 452 */       return this.description;
/*     */     } }
/*     */ 
/*     */   
/*     */   public enum Plane implements Predicate, Iterable {
/* 457 */     HORIZONTAL("HORIZONTAL", 0, "HORIZONTAL", 0),
/* 458 */     VERTICAL("VERTICAL", 1, "VERTICAL", 1);
/* 459 */     private static final Plane[] $VALUES = new Plane[] { HORIZONTAL, VERTICAL };
/*     */     
/*     */     private static final String __OBFID = "CL_00002319";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     public EnumFacing[] facings() {
/* 468 */       switch (EnumFacing.SwitchPlane.PLANE_LOOKUP[ordinal()]) {
/*     */         
/*     */         case 1:
/* 471 */           return new EnumFacing[] { EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST };
/*     */         case 2:
/* 473 */           return new EnumFacing[] { EnumFacing.UP, EnumFacing.DOWN };
/*     */       } 
/* 475 */       throw new Error("Someone's been tampering with the universe!");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public EnumFacing random(Random rand) {
/* 481 */       EnumFacing[] var2 = facings();
/* 482 */       return var2[rand.nextInt(var2.length)];
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean apply(EnumFacing facing) {
/* 487 */       return (facing != null && facing.getAxis().getPlane() == this);
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator iterator() {
/* 492 */       return (Iterator)Iterators.forArray((Object[])facings());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean apply(Object p_apply_1_) {
/* 497 */       return apply((EnumFacing)p_apply_1_);
/*     */     }
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
/*     */ 
/*     */   
/*     */   static final class SwitchPlane
/*     */   {
/*     */     static final int[] AXIS_LOOKUP;
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
/* 524 */     static final int[] FACING_LOOKUP = new int[(EnumFacing.values()).length]; static final int[] PLANE_LOOKUP = new int[(EnumFacing.Plane.values()).length]; private static final String __OBFID = "CL_00002322";
/*     */     static {
/*     */       try {
/* 527 */         FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 529 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 535 */         FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
/*     */       }
/* 537 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 543 */         FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 3;
/*     */       }
/* 545 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 551 */         FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 4;
/*     */       }
/* 553 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 559 */         FACING_LOOKUP[EnumFacing.UP.ordinal()] = 5;
/*     */       }
/* 561 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 567 */         FACING_LOOKUP[EnumFacing.DOWN.ordinal()] = 6;
/*     */       }
/* 569 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 574 */       AXIS_LOOKUP = new int[(EnumFacing.Axis.values()).length];
/*     */       
/*     */       try {
/* 577 */         AXIS_LOOKUP[EnumFacing.Axis.X.ordinal()] = 1;
/*     */       }
/* 579 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 585 */         AXIS_LOOKUP[EnumFacing.Axis.Y.ordinal()] = 2;
/*     */       }
/* 587 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 593 */         AXIS_LOOKUP[EnumFacing.Axis.Z.ordinal()] = 3;
/*     */       }
/* 595 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   } }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EnumFacing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */