/*    */ package net.minecraft.client.util;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class JsonException
/*    */   extends IOException
/*    */ {
/* 11 */   private final List field_151383_a = Lists.newArrayList();
/*    */   
/*    */   private final String field_151382_b;
/*    */   private static final String __OBFID = "CL_00001414";
/*    */   
/*    */   public JsonException(String p_i45279_1_) {
/* 17 */     this.field_151383_a.add(new Entry(null));
/* 18 */     this.field_151382_b = p_i45279_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonException(String p_i45280_1_, Throwable p_i45280_2_) {
/* 23 */     super(p_i45280_2_);
/* 24 */     this.field_151383_a.add(new Entry(null));
/* 25 */     this.field_151382_b = p_i45280_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_151380_a(String p_151380_1_) {
/* 30 */     ((Entry)this.field_151383_a.get(0)).func_151373_a(p_151380_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_151381_b(String p_151381_1_) {
/* 35 */     (this.field_151383_a.get(0)).field_151376_a = p_151381_1_;
/* 36 */     this.field_151383_a.add(0, new Entry(null));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 41 */     return "Invalid " + ((Entry)this.field_151383_a.get(this.field_151383_a.size() - 1)).toString() + ": " + this.field_151382_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JsonException func_151379_a(Exception p_151379_0_) {
/* 46 */     if (p_151379_0_ instanceof JsonException)
/*    */     {
/* 48 */       return (JsonException)p_151379_0_;
/*    */     }
/*    */ 
/*    */     
/* 52 */     String var1 = p_151379_0_.getMessage();
/*    */     
/* 54 */     if (p_151379_0_ instanceof java.io.FileNotFoundException)
/*    */     {
/* 56 */       var1 = "File not found";
/*    */     }
/*    */     
/* 59 */     return new JsonException(var1, p_151379_0_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Entry
/*    */   {
/* 71 */     private String field_151376_a = null;
/* 72 */     private final List field_151375_b = Lists.newArrayList();
/*    */     
/*    */     private static final String __OBFID = "CL_00001416";
/*    */     
/*    */     private void func_151373_a(String p_151373_1_) {
/* 77 */       this.field_151375_b.add(0, p_151373_1_);
/*    */     }
/*    */ 
/*    */     
/*    */     public String func_151372_b() {
/* 82 */       return StringUtils.join(this.field_151375_b, "->");
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 87 */       return (this.field_151376_a != null) ? (!this.field_151375_b.isEmpty() ? (String.valueOf(this.field_151376_a) + " " + func_151372_b()) : this.field_151376_a) : (!this.field_151375_b.isEmpty() ? ("(Unknown file) " + func_151372_b()) : "(Unknown file)");
/*    */     }
/*    */ 
/*    */     
/*    */     Entry(Object p_i45278_1_) {
/* 92 */       this();
/*    */     }
/*    */     
/*    */     private Entry() {}
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\clien\\util\JsonException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */