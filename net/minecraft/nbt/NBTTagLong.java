/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ public class NBTTagLong
/*     */   extends NBTBase.NBTPrimitive
/*     */ {
/*     */   private long data;
/*     */   private static final String __OBFID = "CL_00001225";
/*     */   
/*     */   NBTTagLong() {}
/*     */   
/*     */   public NBTTagLong(long data) {
/*  17 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  25 */     output.writeLong(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  30 */     sizeTracker.read(64L);
/*  31 */     this.data = input.readLong();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  39 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  44 */     return this.data + "L";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/*  52 */     return new NBTTagLong(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  57 */     if (super.equals(p_equals_1_)) {
/*     */       
/*  59 */       NBTTagLong var2 = (NBTTagLong)p_equals_1_;
/*  60 */       return (this.data == var2.data);
/*     */     } 
/*     */ 
/*     */     
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  70 */     return super.hashCode() ^ (int)(this.data ^ this.data >>> 32L);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong() {
/*  75 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/*  80 */     return (int)(this.data & 0xFFFFFFFFFFFFFFFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public short getShort() {
/*  85 */     return (short)(int)(this.data & 0xFFFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getByte() {
/*  90 */     return (byte)(int)(this.data & 0xFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble() {
/*  95 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFloat() {
/* 100 */     return (float)this.data;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */