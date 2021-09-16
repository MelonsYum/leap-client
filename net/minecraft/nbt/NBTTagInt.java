/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ public class NBTTagInt
/*     */   extends NBTBase.NBTPrimitive
/*     */ {
/*     */   private int data;
/*     */   private static final String __OBFID = "CL_00001223";
/*     */   
/*     */   NBTTagInt() {}
/*     */   
/*     */   public NBTTagInt(int data) {
/*  17 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  25 */     output.writeInt(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  30 */     sizeTracker.read(32L);
/*  31 */     this.data = input.readInt();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  39 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  44 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/*  52 */     return new NBTTagInt(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  57 */     if (super.equals(p_equals_1_)) {
/*     */       
/*  59 */       NBTTagInt var2 = (NBTTagInt)p_equals_1_;
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
/*  85 */     return (short)(this.data & 0xFFFF);
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


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */