/*    */ package net.minecraft.nbt;
/*    */ 
/*    */ import java.io.DataInput;
/*    */ import java.io.DataOutput;
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ public class NBTTagIntArray
/*    */   extends NBTBase
/*    */ {
/*    */   private int[] intArray;
/*    */   private static final String __OBFID = "CL_00001221";
/*    */   
/*    */   NBTTagIntArray() {}
/*    */   
/*    */   public NBTTagIntArray(int[] p_i45132_1_) {
/* 18 */     this.intArray = p_i45132_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void write(DataOutput output) throws IOException {
/* 26 */     output.writeInt(this.intArray.length);
/*    */     
/* 28 */     for (int var2 = 0; var2 < this.intArray.length; var2++)
/*    */     {
/* 30 */       output.writeInt(this.intArray[var2]);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/* 36 */     int var4 = input.readInt();
/* 37 */     sizeTracker.read((32 * var4));
/* 38 */     this.intArray = new int[var4];
/*    */     
/* 40 */     for (int var5 = 0; var5 < var4; var5++)
/*    */     {
/* 42 */       this.intArray[var5] = input.readInt();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public byte getId() {
/* 51 */     return 11;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     String var1 = "[";
/* 57 */     int[] var2 = this.intArray;
/* 58 */     int var3 = var2.length;
/*    */     
/* 60 */     for (int var4 = 0; var4 < var3; var4++) {
/*    */       
/* 62 */       int var5 = var2[var4];
/* 63 */       var1 = String.valueOf(var1) + var5 + ",";
/*    */     } 
/*    */     
/* 66 */     return String.valueOf(var1) + "]";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTBase copy() {
/* 74 */     int[] var1 = new int[this.intArray.length];
/* 75 */     System.arraycopy(this.intArray, 0, var1, 0, this.intArray.length);
/* 76 */     return new NBTTagIntArray(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 81 */     return super.equals(p_equals_1_) ? Arrays.equals(this.intArray, ((NBTTagIntArray)p_equals_1_).intArray) : false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 86 */     return super.hashCode() ^ Arrays.hashCode(this.intArray);
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getIntArray() {
/* 91 */     return this.intArray;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagIntArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */