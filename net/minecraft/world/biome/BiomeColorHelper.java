/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ public class BiomeColorHelper
/*    */ {
/*  9 */   private static final ColorResolver field_180291_a = new ColorResolver()
/*    */     {
/*    */       private static final String __OBFID = "CL_00002148";
/*    */       
/*    */       public int func_180283_a(BiomeGenBase p_180283_1_, BlockPos p_180283_2_) {
/* 14 */         return p_180283_1_.func_180627_b(p_180283_2_);
/*    */       }
/*    */     };
/* 17 */   private static final ColorResolver field_180289_b = new ColorResolver()
/*    */     {
/*    */       private static final String __OBFID = "CL_00002147";
/*    */       
/*    */       public int func_180283_a(BiomeGenBase p_180283_1_, BlockPos p_180283_2_) {
/* 22 */         return p_180283_1_.func_180625_c(p_180283_2_);
/*    */       }
/*    */     };
/* 25 */   private static final ColorResolver field_180290_c = new ColorResolver()
/*    */     {
/*    */       private static final String __OBFID = "CL_00002146";
/*    */       
/*    */       public int func_180283_a(BiomeGenBase p_180283_1_, BlockPos p_180283_2_) {
/* 30 */         return p_180283_1_.waterColorMultiplier;
/*    */       }
/*    */     };
/*    */   
/*    */   private static final String __OBFID = "CL_00002149";
/*    */   
/*    */   private static int func_180285_a(IBlockAccess p_180285_0_, BlockPos p_180285_1_, ColorResolver p_180285_2_) {
/* 37 */     int var3 = 0;
/* 38 */     int var4 = 0;
/* 39 */     int var5 = 0;
/*    */ 
/*    */     
/* 42 */     for (Iterator<BlockPos.MutableBlockPos> var6 = BlockPos.getAllInBoxMutable(p_180285_1_.add(-1, 0, -1), p_180285_1_.add(1, 0, 1)).iterator(); var6.hasNext(); var5 += var8 & 0xFF) {
/*    */       
/* 44 */       BlockPos.MutableBlockPos var7 = var6.next();
/* 45 */       int var8 = p_180285_2_.func_180283_a(p_180285_0_.getBiomeGenForCoords((BlockPos)var7), (BlockPos)var7);
/* 46 */       var3 += (var8 & 0xFF0000) >> 16;
/* 47 */       var4 += (var8 & 0xFF00) >> 8;
/*    */     } 
/*    */     
/* 50 */     return (var3 / 9 & 0xFF) << 16 | (var4 / 9 & 0xFF) << 8 | var5 / 9 & 0xFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int func_180286_a(IBlockAccess p_180286_0_, BlockPos p_180286_1_) {
/* 55 */     return func_180285_a(p_180286_0_, p_180286_1_, field_180291_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int func_180287_b(IBlockAccess p_180287_0_, BlockPos p_180287_1_) {
/* 60 */     return func_180285_a(p_180287_0_, p_180287_1_, field_180289_b);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int func_180288_c(IBlockAccess p_180288_0_, BlockPos p_180288_1_) {
/* 65 */     return func_180285_a(p_180288_0_, p_180288_1_, field_180290_c);
/*    */   }
/*    */   
/*    */   static interface ColorResolver {
/*    */     int func_180283_a(BiomeGenBase param1BiomeGenBase, BlockPos param1BlockPos);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeColorHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */