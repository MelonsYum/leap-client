/*    */ package net.minecraft.nbt;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ public class NBTTagByteArray
/*    */   extends NBTBase
/*    */ {
/*    */   private byte[] data;
/*    */   private static final String __OBFID = "CL_00001213";
/*    */   
/*    */   NBTTagByteArray() {}
/*    */   
/*    */   public NBTTagByteArray(byte[] data) {
/* 18 */     this.data = data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput output) throws IOException {
/* 26 */     output.writeInt(this.data.length);
/* 27 */     output.write(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/* 32 */     int var4 = input.readInt();
/* 33 */     sizeTracker.read((8 * var4));
/* 34 */     this.data = new byte[var4];
/* 35 */     input.readFully(this.data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getId() {
/* 43 */     return 7;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 48 */     return "[" + this.data.length + " bytes]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 56 */     byte[] var1 = new byte[this.data.length];
/* 57 */     System.arraycopy(this.data, 0, var1, 0, this.data.length);
/* 58 */     return new NBTTagByteArray(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 63 */     return super.equals(p_equals_1_) ? Arrays.equals(this.data, ((NBTTagByteArray)p_equals_1_).data) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 68 */     return super.hashCode() ^ Arrays.hashCode(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getByteArray() {
/* 73 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagByteArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */