/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class EnchantmentNameParts
/*    */ {
/*  7 */   private static final EnchantmentNameParts instance = new EnchantmentNameParts();
/*  8 */   private Random rand = new Random();
/*  9 */   private String[] namePartsArray = "the elder scrolls klaatu berata niktu xyzzy bless curse light darkness fire air earth water hot dry cold wet ignite snuff embiggen twist shorten stretch fiddle destroy imbue galvanize enchant free limited range of towards inside sphere cube self other ball mental physical grow shrink demon elemental spirit animal creature beast humanoid undead fresh stale ".split(" ");
/*    */   
/*    */   private static final String __OBFID = "CL_00000756";
/*    */   
/*    */   public static EnchantmentNameParts func_178176_a() {
/* 14 */     return instance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String generateNewRandomName() {
/* 22 */     int var1 = this.rand.nextInt(2) + 3;
/* 23 */     String var2 = "";
/*    */     
/* 25 */     for (int var3 = 0; var3 < var1; var3++) {
/*    */       
/* 27 */       if (var3 > 0)
/*    */       {
/* 29 */         var2 = String.valueOf(var2) + " ";
/*    */       }
/*    */       
/* 32 */       var2 = String.valueOf(var2) + this.namePartsArray[this.rand.nextInt(this.namePartsArray.length)];
/*    */     } 
/*    */     
/* 35 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reseedRandomGenerator(long p_148335_1_) {
/* 43 */     this.rand.setSeed(p_148335_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EnchantmentNameParts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */