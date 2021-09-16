/*    */ package net.minecraft.client.stream;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Metadata
/*    */ {
/* 10 */   private static final Gson field_152811_a = new Gson();
/*    */   
/*    */   private final String field_152812_b;
/*    */   private String field_152813_c;
/*    */   private Map field_152814_d;
/*    */   private static final String __OBFID = "CL_00001823";
/*    */   
/*    */   public Metadata(String p_i46345_1_, String p_i46345_2_) {
/* 18 */     this.field_152812_b = p_i46345_1_;
/* 19 */     this.field_152813_c = p_i46345_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Metadata(String p_i1030_1_) {
/* 24 */     this(p_i1030_1_, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_152807_a(String p_152807_1_) {
/* 29 */     this.field_152813_c = p_152807_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_152809_a() {
/* 34 */     return (this.field_152813_c == null) ? this.field_152812_b : this.field_152813_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_152808_a(String p_152808_1_, String p_152808_2_) {
/* 39 */     if (this.field_152814_d == null)
/*    */     {
/* 41 */       this.field_152814_d = Maps.newHashMap();
/*    */     }
/*    */     
/* 44 */     if (this.field_152814_d.size() > 50)
/*    */     {
/* 46 */       throw new IllegalArgumentException("Metadata payload is full, cannot add more to it!");
/*    */     }
/* 48 */     if (p_152808_1_ == null)
/*    */     {
/* 50 */       throw new IllegalArgumentException("Metadata payload key cannot be null!");
/*    */     }
/* 52 */     if (p_152808_1_.length() > 255)
/*    */     {
/* 54 */       throw new IllegalArgumentException("Metadata payload key is too long!");
/*    */     }
/* 56 */     if (p_152808_2_ == null)
/*    */     {
/* 58 */       throw new IllegalArgumentException("Metadata payload value cannot be null!");
/*    */     }
/* 60 */     if (p_152808_2_.length() > 255)
/*    */     {
/* 62 */       throw new IllegalArgumentException("Metadata payload value is too long!");
/*    */     }
/*    */ 
/*    */     
/* 66 */     this.field_152814_d.put(p_152808_1_, p_152808_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_152806_b() {
/* 72 */     return (this.field_152814_d != null && !this.field_152814_d.isEmpty()) ? field_152811_a.toJson(this.field_152814_d) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_152810_c() {
/* 77 */     return this.field_152812_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 82 */     return Objects.toStringHelper(this).add("name", this.field_152812_b).add("description", this.field_152813_c).add("data", func_152806_b()).toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\Metadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */