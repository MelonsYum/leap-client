/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WeightedRandom
/*    */ {
/*    */   private static final String __OBFID = "CL_00001503";
/*    */   
/*    */   public static int getTotalWeight(Collection p_76272_0_) {
/* 16 */     int var1 = 0;
/*    */ 
/*    */     
/* 19 */     for (Iterator<Item> var2 = p_76272_0_.iterator(); var2.hasNext(); var1 += var3.itemWeight)
/*    */     {
/* 21 */       Item var3 = var2.next();
/*    */     }
/*    */     
/* 24 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Item getRandomItem(Random p_76273_0_, Collection p_76273_1_, int p_76273_2_) {
/* 32 */     if (p_76273_2_ <= 0)
/*    */     {
/* 34 */       throw new IllegalArgumentException();
/*    */     }
/*    */ 
/*    */     
/* 38 */     int var3 = p_76273_0_.nextInt(p_76273_2_);
/* 39 */     return func_180166_a(p_76273_1_, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Item func_180166_a(Collection p_180166_0_, int p_180166_1_) {
/*    */     Item var3;
/* 45 */     Iterator<Item> var2 = p_180166_0_.iterator();
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 50 */       if (!var2.hasNext())
/*    */       {
/* 52 */         return null;
/*    */       }
/*    */       
/* 55 */       var3 = var2.next();
/* 56 */       p_180166_1_ -= var3.itemWeight;
/*    */     }
/* 58 */     while (p_180166_1_ >= 0);
/*    */     
/* 60 */     return var3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Item getRandomItem(Random p_76271_0_, Collection p_76271_1_) {
/* 68 */     return getRandomItem(p_76271_0_, p_76271_1_, getTotalWeight(p_76271_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   public static class Item
/*    */   {
/*    */     protected int itemWeight;
/*    */     private static final String __OBFID = "CL_00001504";
/*    */     
/*    */     public Item(int p_i1556_1_) {
/* 78 */       this.itemWeight = p_i1556_1_;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\WeightedRandom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */