/*    */ package net.minecraft.world.chunk;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class ChunkPrimer
/*    */ {
/*  9 */   private final short[] data = new short[65536];
/*    */   
/*    */   private final IBlockState defaultState;
/*    */   private static final String __OBFID = "CL_00002007";
/*    */   
/*    */   public ChunkPrimer() {
/* 15 */     this.defaultState = Blocks.air.getDefaultState();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState getBlockState(int x, int y, int z) {
/* 20 */     int var4 = x << 12 | z << 8 | y;
/* 21 */     return getBlockState(var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState getBlockState(int index) {
/* 26 */     if (index >= 0 && index < this.data.length) {
/*    */       
/* 28 */       IBlockState var2 = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(this.data[index]);
/* 29 */       return (var2 != null) ? var2 : this.defaultState;
/*    */     } 
/*    */ 
/*    */     
/* 33 */     throw new IndexOutOfBoundsException("The coordinate is out of range");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBlockState(int x, int y, int z, IBlockState state) {
/* 39 */     int var5 = x << 12 | z << 8 | y;
/* 40 */     setBlockState(var5, state);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBlockState(int index, IBlockState state) {
/* 45 */     if (index >= 0 && index < this.data.length) {
/*    */       
/* 47 */       this.data[index] = (short)Block.BLOCK_STATE_IDS.get(state);
/*    */     }
/*    */     else {
/*    */       
/* 51 */       throw new IndexOutOfBoundsException("The coordinate is out of range");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\ChunkPrimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */