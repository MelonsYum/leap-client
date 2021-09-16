/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ public class Language
/*    */   implements Comparable
/*    */ {
/*    */   private final String languageCode;
/*    */   private final String region;
/*    */   private final String name;
/*    */   private final boolean bidirectional;
/*    */   private static final String __OBFID = "CL_00001095";
/*    */   
/*    */   public Language(String p_i1303_1_, String p_i1303_2_, String p_i1303_3_, boolean p_i1303_4_) {
/* 13 */     this.languageCode = p_i1303_1_;
/* 14 */     this.region = p_i1303_2_;
/* 15 */     this.name = p_i1303_3_;
/* 16 */     this.bidirectional = p_i1303_4_;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLanguageCode() {
/* 21 */     return this.languageCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBidirectional() {
/* 26 */     return this.bidirectional;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return String.format("%s (%s)", new Object[] { this.name, this.region });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 36 */     return (this == p_equals_1_) ? true : (!(p_equals_1_ instanceof Language) ? false : this.languageCode.equals(((Language)p_equals_1_).languageCode));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 41 */     return this.languageCode.hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Language p_compareTo_1_) {
/* 46 */     return this.languageCode.compareTo(p_compareTo_1_.languageCode);
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object p_compareTo_1_) {
/* 51 */     return compareTo((Language)p_compareTo_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\Language.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */