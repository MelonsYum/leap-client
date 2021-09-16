/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ public class NBTTagFloat
/*     */   extends NBTBase.NBTPrimitive
/*     */ {
/*     */   private float data;
/*     */   private static final String __OBFID = "CL_00001220";
/*     */   
/*     */   NBTTagFloat() {}
/*     */   
/*     */   public NBTTagFloat(float data) {
/*  18 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  26 */     output.writeFloat(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  31 */     sizeTracker.read(32L);
/*  32 */     this.data = input.readFloat();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  40 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  45 */     return this.data + "f";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/*  53 */     return new NBTTagFloat(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  58 */     if (super.equals(p_equals_1_)) {
/*     */       
/*  60 */       NBTTagFloat var2 = (NBTTagFloat)p_equals_1_;
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
/*  71 */     return super.hashCode() ^ Float.floatToIntBits(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong() {
/*  76 */     return (long)this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/*  81 */     return MathHelper.floor_float(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getShort() {
/*  86 */     return (short)(MathHelper.floor_float(this.data) & 0xFFFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getByte() {
/*  91 */     return (byte)(MathHelper.floor_float(this.data) & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble() {
/*  96 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFloat() {
/* 101 */     return this.data;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */