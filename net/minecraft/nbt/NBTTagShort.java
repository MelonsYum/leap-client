/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ public class NBTTagShort
/*     */   extends NBTBase.NBTPrimitive
/*     */ {
/*     */   private short data;
/*     */   private static final String __OBFID = "CL_00001227";
/*     */   
/*     */   public NBTTagShort() {}
/*     */   
/*     */   public NBTTagShort(short data) {
/*  17 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  25 */     output.writeShort(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  30 */     sizeTracker.read(16L);
/*  31 */     this.data = input.readShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  39 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  44 */     return this.data + "s";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/*  52 */     return new NBTTagShort(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  57 */     if (super.equals(p_equals_1_)) {
/*     */       
/*  59 */       NBTTagShort var2 = (NBTTagShort)p_equals_1_;
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
/*  70 */     return super.hashCode() ^ this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLong() {
/*  75 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInt() {
/*  80 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getShort() {
/*  85 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getByte() {
/*  90 */     return (byte)(this.data & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDouble() {
/*  95 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFloat() {
/* 100 */     return this.data;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagShort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */