/*    */ package net.minecraft.world.gen;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.ChunkPrimer;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ 
/*    */ 
/*    */ public class MapGenBase
/*    */ {
/* 11 */   protected int range = 8;
/*    */ 
/*    */   
/* 14 */   protected Random rand = new Random();
/*    */   
/*    */   protected World worldObj;
/*    */   
/*    */   private static final String __OBFID = "CL_00000394";
/*    */ 
/*    */   
/*    */   public void func_175792_a(IChunkProvider p_175792_1_, World worldIn, int p_175792_3_, int p_175792_4_, ChunkPrimer p_175792_5_) {
/* 22 */     int var6 = this.range;
/* 23 */     this.worldObj = worldIn;
/* 24 */     this.rand.setSeed(worldIn.getSeed());
/* 25 */     long var7 = this.rand.nextLong();
/* 26 */     long var9 = this.rand.nextLong();
/*    */     
/* 28 */     for (int var11 = p_175792_3_ - var6; var11 <= p_175792_3_ + var6; var11++) {
/*    */       
/* 30 */       for (int var12 = p_175792_4_ - var6; var12 <= p_175792_4_ + var6; var12++) {
/*    */         
/* 32 */         long var13 = var11 * var7;
/* 33 */         long var15 = var12 * var9;
/* 34 */         this.rand.setSeed(var13 ^ var15 ^ worldIn.getSeed());
/* 35 */         func_180701_a(worldIn, var11, var12, p_175792_3_, p_175792_4_, p_175792_5_);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected void func_180701_a(World worldIn, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {}
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\MapGenBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */