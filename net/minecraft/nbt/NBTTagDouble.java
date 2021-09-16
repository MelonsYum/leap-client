/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ public class NBTTagDouble
/*     */   extends NBTBase.NBTPrimitive
/*     */ {
/*     */   private double data;
/*     */   private static final String __OBFID = "CL_00001218";
/*     */   
/*     */   NBTTagDouble() {}
/*     */   
/*     */   public NBTTagDouble(double data) {
/*  18 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  26 */     output.writeDouble(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  31 */     sizeTracker.read(64L);
/*  32 */     this.data = input.readDouble();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  40 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  45 */     return this.data + "d";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/*  53 */     return new NBTTagDouble(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  58 */     if (super.equals(p_equals_1_)) {
/*     */       
/*  60 */       NBTTagDouble var2 = (NBTTagDouble)p_equals_1_;
/*  61 */       return (this.data == var2.data);
/*     */     } 
/*     */ 
/*     */     
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  71 */     long var1 = Double.doubleToLongBits(this.data);
/*  72 */     return super.hashCode() ^ (int)(var1 ^ var1 >>> 32L);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong() {
/*  77 */     return (long)Math.floor(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/*  82 */     return MathHelper.floor_double(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getShort() {
/*  87 */     return (short)(MathHelper.floor_double(this.data) & 0xFFFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getByte() {
/*  92 */     return (byte)(MathHelper.floor_double(this.data) & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble() {
/*  97 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFloat() {
/* 102 */     return (float)this.data;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */