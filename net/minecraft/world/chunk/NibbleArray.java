/*    */ package net.minecraft.world.chunk;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NibbleArray
/*    */ {
/*    */   private final byte[] data;
/*    */   private static final String __OBFID = "CL_00000371";
/*    */   
/*    */   public NibbleArray() {
/* 14 */     this.data = new byte[2048];
/*    */   }
/*    */ 
/*    */   
/*    */   public NibbleArray(byte[] storageArray) {
/* 19 */     this.data = storageArray;
/*    */     
/* 21 */     if (storageArray.length != 2048)
/*    */     {
/* 23 */       throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + storageArray.length);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int get(int x, int y, int z) {
/* 32 */     return getFromIndex(getCoordinateIndex(x, y, z));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(int x, int y, int z, int value) {
/* 40 */     setIndex(getCoordinateIndex(x, y, z), value);
/*    */   }
/*    */ 
/*    */   
/*    */   private int getCoordinateIndex(int x, int y, int z) {
/* 45 */     return y << 8 | z << 4 | x;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFromIndex(int index) {
/* 50 */     int var2 = func_177478_c(index);
/* 51 */     return func_177479_b(index) ? (this.data[var2] & 0xF) : (this.data[var2] >> 4 & 0xF);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIndex(int index, int value) {
/* 56 */     int var3 = func_177478_c(index);
/*    */     
/* 58 */     if (func_177479_b(index)) {
/*    */       
/* 60 */       this.data[var3] = (byte)(this.data[var3] & 0xF0 | value & 0xF);
/*    */     }
/*    */     else {
/*    */       
/* 64 */       this.data[var3] = (byte)(this.data[var3] & 0xF | (value & 0xF) << 4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean func_177479_b(int p_177479_1_) {
/* 70 */     return ((p_177479_1_ & 0x1) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   private int func_177478_c(int p_177478_1_) {
/* 75 */     return p_177478_1_ >> 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getData() {
/* 80 */     return this.data;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\NibbleArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */