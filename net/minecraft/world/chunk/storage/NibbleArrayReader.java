/*    */ package net.minecraft.world.chunk.storage;
/*    */ 
/*    */ 
/*    */ public class NibbleArrayReader
/*    */ {
/*    */   public final byte[] data;
/*    */   private final int depthBits;
/*    */   private final int depthBitsPlusFour;
/*    */   private static final String __OBFID = "CL_00000376";
/*    */   
/*    */   public NibbleArrayReader(byte[] dataIn, int depthBitsIn) {
/* 12 */     this.data = dataIn;
/* 13 */     this.depthBits = depthBitsIn;
/* 14 */     this.depthBitsPlusFour = depthBitsIn + 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int get(int p_76686_1_, int p_76686_2_, int p_76686_3_) {
/* 19 */     int var4 = p_76686_1_ << this.depthBitsPlusFour | p_76686_3_ << this.depthBits | p_76686_2_;
/* 20 */     int var5 = var4 >> 1;
/* 21 */     int var6 = var4 & 0x1;
/* 22 */     return (var6 == 0) ? (this.data[var5] & 0xF) : (this.data[var5] >> 4 & 0xF);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\NibbleArrayReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */