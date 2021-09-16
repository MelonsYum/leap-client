/*     */ package net.minecraft.client.renderer.vertex;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class VertexFormat
/*     */ {
/*  11 */   private static final Logger field_177357_a = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VertexFormat(VertexFormat p_i46097_1_) {
/*  22 */     this();
/*     */     
/*  24 */     for (int var2 = 0; var2 < p_i46097_1_.func_177345_h(); var2++)
/*     */     {
/*  26 */       func_177349_a(p_i46097_1_.func_177348_c(var2));
/*     */     }
/*     */     
/*  29 */     this.field_177353_d = p_i46097_1_.func_177338_f();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  34 */   private final List field_177355_b = Lists.newArrayList();
/*  35 */   private final List field_177356_c = Lists.newArrayList();
/*  36 */   private int field_177353_d = 0;
/*  37 */   private int field_177354_e = -1;
/*  38 */   private List field_177351_f = Lists.newArrayList();
/*  39 */   private int field_177352_g = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00002401";
/*     */   
/*     */   public void clear() {
/*  44 */     this.field_177355_b.clear();
/*  45 */     this.field_177356_c.clear();
/*  46 */     this.field_177354_e = -1;
/*  47 */     this.field_177351_f.clear();
/*  48 */     this.field_177352_g = -1;
/*  49 */     this.field_177353_d = 0;
/*     */   }
/*     */   public VertexFormat() {}
/*     */   
/*     */   public void func_177349_a(VertexFormatElement p_177349_1_) {
/*  54 */     if (p_177349_1_.func_177374_g() && func_177341_i()) {
/*     */       
/*  56 */       field_177357_a.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
/*     */     }
/*     */     else {
/*     */       
/*  60 */       this.field_177355_b.add(p_177349_1_);
/*  61 */       this.field_177356_c.add(Integer.valueOf(this.field_177353_d));
/*  62 */       p_177349_1_.func_177371_a(this.field_177353_d);
/*  63 */       this.field_177353_d += p_177349_1_.func_177368_f();
/*     */       
/*  65 */       switch (SwitchEnumUseage.field_177382_a[p_177349_1_.func_177375_c().ordinal()]) {
/*     */         
/*     */         case 1:
/*  68 */           this.field_177352_g = p_177349_1_.func_177373_a();
/*     */           break;
/*     */         
/*     */         case 2:
/*  72 */           this.field_177354_e = p_177349_1_.func_177373_a();
/*     */           break;
/*     */         
/*     */         case 3:
/*  76 */           this.field_177351_f.add(p_177349_1_.func_177369_e(), Integer.valueOf(p_177349_1_.func_177373_a()));
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean func_177350_b() {
/*  83 */     return (this.field_177352_g >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_177342_c() {
/*  88 */     return this.field_177352_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177346_d() {
/*  93 */     return (this.field_177354_e >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_177340_e() {
/*  98 */     return this.field_177354_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177347_a(int p_177347_1_) {
/* 103 */     return (this.field_177351_f.size() - 1 >= p_177347_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_177344_b(int p_177344_1_) {
/* 108 */     return ((Integer)this.field_177351_f.get(p_177344_1_)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 113 */     String var1 = "format: " + this.field_177355_b.size() + " elements: ";
/*     */     
/* 115 */     for (int var2 = 0; var2 < this.field_177355_b.size(); var2++) {
/*     */       
/* 117 */       var1 = String.valueOf(var1) + ((VertexFormatElement)this.field_177355_b.get(var2)).toString();
/*     */       
/* 119 */       if (var2 != this.field_177355_b.size() - 1)
/*     */       {
/* 121 */         var1 = String.valueOf(var1) + " ";
/*     */       }
/*     */     } 
/*     */     
/* 125 */     return var1;
/*     */   }
/*     */   
/*     */   private boolean func_177341_i() {
/*     */     VertexFormatElement var2;
/* 130 */     Iterator<VertexFormatElement> var1 = this.field_177355_b.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 135 */       if (!var1.hasNext())
/*     */       {
/* 137 */         return false;
/*     */       }
/*     */       
/* 140 */       var2 = var1.next();
/*     */     }
/* 142 */     while (!var2.func_177374_g());
/*     */     
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_177338_f() {
/* 149 */     return this.field_177353_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177343_g() {
/* 154 */     return this.field_177355_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_177345_h() {
/* 159 */     return this.field_177355_b.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public VertexFormatElement func_177348_c(int p_177348_1_) {
/* 164 */     return this.field_177355_b.get(p_177348_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 169 */     if (this == p_equals_1_)
/*     */     {
/* 171 */       return true;
/*     */     }
/* 173 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */       
/* 175 */       VertexFormat var2 = (VertexFormat)p_equals_1_;
/* 176 */       return (this.field_177353_d != var2.field_177353_d) ? false : (!this.field_177355_b.equals(var2.field_177355_b) ? false : this.field_177356_c.equals(var2.field_177356_c));
/*     */     } 
/*     */ 
/*     */     
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 186 */     int var1 = this.field_177355_b.hashCode();
/* 187 */     var1 = 31 * var1 + this.field_177356_c.hashCode();
/* 188 */     var1 = 31 * var1 + this.field_177353_d;
/* 189 */     return var1;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumUseage
/*     */   {
/* 194 */     static final int[] field_177382_a = new int[(VertexFormatElement.EnumUseage.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002400";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 201 */         field_177382_a[VertexFormatElement.EnumUseage.NORMAL.ordinal()] = 1;
/*     */       }
/* 203 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 210 */         field_177382_a[VertexFormatElement.EnumUseage.COLOR.ordinal()] = 2;
/*     */       }
/* 212 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 219 */         field_177382_a[VertexFormatElement.EnumUseage.UV.ordinal()] = 3;
/*     */       }
/* 221 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\vertex\VertexFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */