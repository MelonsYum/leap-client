/*     */ package net.minecraft.block.state.pattern;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ public class FactoryBlockPattern
/*     */ {
/*  19 */   private static final Joiner field_177667_a = Joiner.on(",");
/*  20 */   private final List field_177665_b = Lists.newArrayList();
/*  21 */   private final Map field_177666_c = Maps.newHashMap();
/*     */   
/*     */   private int field_177663_d;
/*     */   private int field_177664_e;
/*     */   private static final String __OBFID = "CL_00002021";
/*     */   
/*     */   private FactoryBlockPattern() {
/*  28 */     this.field_177666_c.put(Character.valueOf(' '), Predicates.alwaysTrue());
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryBlockPattern aisle(String... p_177659_1_) {
/*  33 */     if (!ArrayUtils.isEmpty((Object[])p_177659_1_) && !StringUtils.isEmpty(p_177659_1_[0])) {
/*     */       
/*  35 */       if (this.field_177665_b.isEmpty()) {
/*     */         
/*  37 */         this.field_177663_d = p_177659_1_.length;
/*  38 */         this.field_177664_e = p_177659_1_[0].length();
/*     */       } 
/*     */       
/*  41 */       if (p_177659_1_.length != this.field_177663_d)
/*     */       {
/*  43 */         throw new IllegalArgumentException("Expected aisle with height of " + this.field_177663_d + ", but was given one with a height of " + p_177659_1_.length + ")");
/*     */       }
/*     */ 
/*     */       
/*  47 */       String[] var2 = p_177659_1_;
/*  48 */       int var3 = p_177659_1_.length;
/*     */       
/*  50 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/*  52 */         String var5 = var2[var4];
/*     */         
/*  54 */         if (var5.length() != this.field_177664_e)
/*     */         {
/*  56 */           throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.field_177664_e + ", found one with " + var5.length() + ")");
/*     */         }
/*     */         
/*  59 */         char[] var6 = var5.toCharArray();
/*  60 */         int var7 = var6.length;
/*     */         
/*  62 */         for (int var8 = 0; var8 < var7; var8++) {
/*     */           
/*  64 */           char var9 = var6[var8];
/*     */           
/*  66 */           if (!this.field_177666_c.containsKey(Character.valueOf(var9)))
/*     */           {
/*  68 */             this.field_177666_c.put(Character.valueOf(var9), null);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  73 */       this.field_177665_b.add(p_177659_1_);
/*  74 */       return this;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  79 */     throw new IllegalArgumentException("Empty pattern for aisle");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FactoryBlockPattern start() {
/*  85 */     return new FactoryBlockPattern();
/*     */   }
/*     */ 
/*     */   
/*     */   public FactoryBlockPattern where(char p_177662_1_, Predicate p_177662_2_) {
/*  90 */     this.field_177666_c.put(Character.valueOf(p_177662_1_), p_177662_2_);
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPattern build() {
/*  96 */     return new BlockPattern(func_177658_c());
/*     */   }
/*     */ 
/*     */   
/*     */   private Predicate[][][] func_177658_c() {
/* 101 */     func_177657_d();
/* 102 */     Predicate[][][] var1 = (Predicate[][][])Array.newInstance(Predicate.class, new int[] { this.field_177665_b.size(), this.field_177663_d, this.field_177664_e });
/*     */     
/* 104 */     for (int var2 = 0; var2 < this.field_177665_b.size(); var2++) {
/*     */       
/* 106 */       for (int var3 = 0; var3 < this.field_177663_d; var3++) {
/*     */         
/* 108 */         for (int var4 = 0; var4 < this.field_177664_e; var4++)
/*     */         {
/* 110 */           var1[var2][var3][var4] = (Predicate)this.field_177666_c.get(Character.valueOf(((String[])this.field_177665_b.get(var2))[var3].charAt(var4)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_177657_d() {
/* 120 */     ArrayList var1 = Lists.newArrayList();
/* 121 */     Iterator<Map.Entry> var2 = this.field_177666_c.entrySet().iterator();
/*     */     
/* 123 */     while (var2.hasNext()) {
/*     */       
/* 125 */       Map.Entry var3 = var2.next();
/*     */       
/* 127 */       if (var3.getValue() == null)
/*     */       {
/* 129 */         var1.add(var3.getKey());
/*     */       }
/*     */     } 
/*     */     
/* 133 */     if (!var1.isEmpty())
/*     */     {
/* 135 */       throw new IllegalStateException("Predicates for character(s) " + field_177667_a.join(var1) + " are missing");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\pattern\FactoryBlockPattern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */