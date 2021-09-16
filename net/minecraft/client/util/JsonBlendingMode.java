/*     */ package net.minecraft.client.util;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import org.lwjgl.opengl.GL14;
/*     */ 
/*     */ public class JsonBlendingMode
/*     */ {
/*  10 */   private static JsonBlendingMode field_148118_a = null;
/*     */   
/*     */   private final int field_148116_b;
/*     */   private final int field_148117_c;
/*     */   private final int field_148114_d;
/*     */   private final int field_148115_e;
/*     */   private final int field_148112_f;
/*     */   private final boolean field_148113_g;
/*     */   private final boolean field_148119_h;
/*     */   private static final String __OBFID = "CL_00001038";
/*     */   
/*     */   private JsonBlendingMode(boolean p_i45084_1_, boolean p_i45084_2_, int p_i45084_3_, int p_i45084_4_, int p_i45084_5_, int p_i45084_6_, int p_i45084_7_) {
/*  22 */     this.field_148113_g = p_i45084_1_;
/*  23 */     this.field_148116_b = p_i45084_3_;
/*  24 */     this.field_148114_d = p_i45084_4_;
/*  25 */     this.field_148117_c = p_i45084_5_;
/*  26 */     this.field_148115_e = p_i45084_6_;
/*  27 */     this.field_148119_h = p_i45084_2_;
/*  28 */     this.field_148112_f = p_i45084_7_;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonBlendingMode() {
/*  33 */     this(false, true, 1, 0, 1, 0, 32774);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonBlendingMode(int p_i45085_1_, int p_i45085_2_, int p_i45085_3_) {
/*  38 */     this(false, false, p_i45085_1_, p_i45085_2_, p_i45085_1_, p_i45085_2_, p_i45085_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonBlendingMode(int p_i45086_1_, int p_i45086_2_, int p_i45086_3_, int p_i45086_4_, int p_i45086_5_) {
/*  43 */     this(true, false, p_i45086_1_, p_i45086_2_, p_i45086_3_, p_i45086_4_, p_i45086_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148109_a() {
/*  48 */     if (!equals(field_148118_a)) {
/*     */       
/*  50 */       if (field_148118_a == null || this.field_148119_h != field_148118_a.func_148111_b()) {
/*     */         
/*  52 */         field_148118_a = this;
/*     */         
/*  54 */         if (this.field_148119_h) {
/*     */           
/*  56 */           GlStateManager.disableBlend();
/*     */           
/*     */           return;
/*     */         } 
/*  60 */         GlStateManager.enableBlend();
/*     */       } 
/*     */       
/*  63 */       GL14.glBlendEquation(this.field_148112_f);
/*     */       
/*  65 */       if (this.field_148113_g) {
/*     */         
/*  67 */         GlStateManager.tryBlendFuncSeparate(this.field_148116_b, this.field_148114_d, this.field_148117_c, this.field_148115_e);
/*     */       }
/*     */       else {
/*     */         
/*  71 */         GlStateManager.blendFunc(this.field_148116_b, this.field_148114_d);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  78 */     if (this == p_equals_1_)
/*     */     {
/*  80 */       return true;
/*     */     }
/*  82 */     if (!(p_equals_1_ instanceof JsonBlendingMode))
/*     */     {
/*  84 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  88 */     JsonBlendingMode var2 = (JsonBlendingMode)p_equals_1_;
/*  89 */     return (this.field_148112_f != var2.field_148112_f) ? false : ((this.field_148115_e != var2.field_148115_e) ? false : ((this.field_148114_d != var2.field_148114_d) ? false : ((this.field_148119_h != var2.field_148119_h) ? false : ((this.field_148113_g != var2.field_148113_g) ? false : ((this.field_148117_c != var2.field_148117_c) ? false : ((this.field_148116_b == var2.field_148116_b)))))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  95 */     int var1 = this.field_148116_b;
/*  96 */     var1 = 31 * var1 + this.field_148117_c;
/*  97 */     var1 = 31 * var1 + this.field_148114_d;
/*  98 */     var1 = 31 * var1 + this.field_148115_e;
/*  99 */     var1 = 31 * var1 + this.field_148112_f;
/* 100 */     var1 = 31 * var1 + (this.field_148113_g ? 1 : 0);
/* 101 */     var1 = 31 * var1 + (this.field_148119_h ? 1 : 0);
/* 102 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_148111_b() {
/* 107 */     return this.field_148119_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonBlendingMode func_148110_a(JsonObject p_148110_0_) {
/* 112 */     if (p_148110_0_ == null)
/*     */     {
/* 114 */       return new JsonBlendingMode();
/*     */     }
/*     */ 
/*     */     
/* 118 */     int var1 = 32774;
/* 119 */     int var2 = 1;
/* 120 */     int var3 = 0;
/* 121 */     int var4 = 1;
/* 122 */     int var5 = 0;
/* 123 */     boolean var6 = true;
/* 124 */     boolean var7 = false;
/*     */     
/* 126 */     if (JsonUtils.jsonObjectFieldTypeIsString(p_148110_0_, "func")) {
/*     */       
/* 128 */       var1 = func_148108_a(p_148110_0_.get("func").getAsString());
/*     */       
/* 130 */       if (var1 != 32774)
/*     */       {
/* 132 */         var6 = false;
/*     */       }
/*     */     } 
/*     */     
/* 136 */     if (JsonUtils.jsonObjectFieldTypeIsString(p_148110_0_, "srcrgb")) {
/*     */       
/* 138 */       var2 = func_148107_b(p_148110_0_.get("srcrgb").getAsString());
/*     */       
/* 140 */       if (var2 != 1)
/*     */       {
/* 142 */         var6 = false;
/*     */       }
/*     */     } 
/*     */     
/* 146 */     if (JsonUtils.jsonObjectFieldTypeIsString(p_148110_0_, "dstrgb")) {
/*     */       
/* 148 */       var3 = func_148107_b(p_148110_0_.get("dstrgb").getAsString());
/*     */       
/* 150 */       if (var3 != 0)
/*     */       {
/* 152 */         var6 = false;
/*     */       }
/*     */     } 
/*     */     
/* 156 */     if (JsonUtils.jsonObjectFieldTypeIsString(p_148110_0_, "srcalpha")) {
/*     */       
/* 158 */       var4 = func_148107_b(p_148110_0_.get("srcalpha").getAsString());
/*     */       
/* 160 */       if (var4 != 1)
/*     */       {
/* 162 */         var6 = false;
/*     */       }
/*     */       
/* 165 */       var7 = true;
/*     */     } 
/*     */     
/* 168 */     if (JsonUtils.jsonObjectFieldTypeIsString(p_148110_0_, "dstalpha")) {
/*     */       
/* 170 */       var5 = func_148107_b(p_148110_0_.get("dstalpha").getAsString());
/*     */       
/* 172 */       if (var5 != 0)
/*     */       {
/* 174 */         var6 = false;
/*     */       }
/*     */       
/* 177 */       var7 = true;
/*     */     } 
/*     */     
/* 180 */     return var6 ? new JsonBlendingMode() : (var7 ? new JsonBlendingMode(var2, var3, var4, var5, var1) : new JsonBlendingMode(var2, var3, var1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int func_148108_a(String p_148108_0_) {
/* 186 */     String var1 = p_148108_0_.trim().toLowerCase();
/* 187 */     return var1.equals("add") ? 32774 : (var1.equals("subtract") ? 32778 : (var1.equals("reversesubtract") ? 32779 : (var1.equals("reverse_subtract") ? 32779 : (var1.equals("min") ? 32775 : (var1.equals("max") ? 32776 : 32774)))));
/*     */   }
/*     */ 
/*     */   
/*     */   private static int func_148107_b(String p_148107_0_) {
/* 192 */     String var1 = p_148107_0_.trim().toLowerCase();
/* 193 */     var1 = var1.replaceAll("_", "");
/* 194 */     var1 = var1.replaceAll("one", "1");
/* 195 */     var1 = var1.replaceAll("zero", "0");
/* 196 */     var1 = var1.replaceAll("minus", "-");
/* 197 */     return var1.equals("0") ? 0 : (var1.equals("1") ? 1 : (var1.equals("srccolor") ? 768 : (var1.equals("1-srccolor") ? 769 : (var1.equals("dstcolor") ? 774 : (var1.equals("1-dstcolor") ? 775 : (var1.equals("srcalpha") ? 770 : (var1.equals("1-srcalpha") ? 771 : (var1.equals("dstalpha") ? 772 : (var1.equals("1-dstalpha") ? 773 : -1)))))))));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\clien\\util\JsonBlendingMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */