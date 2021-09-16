/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.collect.AbstractIterator;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang3.ClassUtils;
/*     */ 
/*     */ public class ClassInheratanceMultiMap
/*     */   extends AbstractSet
/*     */ {
/*  16 */   private final Multimap field_180218_a = (Multimap)HashMultimap.create();
/*  17 */   private final Set field_180216_b = Sets.newIdentityHashSet();
/*     */   
/*     */   private final Class field_180217_c;
/*     */   private static final String __OBFID = "CL_00002266";
/*     */   
/*     */   public ClassInheratanceMultiMap(Class<?> p_i45909_1_) {
/*  23 */     this.field_180217_c = p_i45909_1_;
/*  24 */     this.field_180216_b.add(p_i45909_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180213_a(Class<?> p_180213_1_) {
/*  29 */     Iterator var2 = this.field_180218_a.get(func_180212_a(p_180213_1_, false)).iterator();
/*     */     
/*  31 */     while (var2.hasNext()) {
/*     */       
/*  33 */       Object var3 = var2.next();
/*     */       
/*  35 */       if (p_180213_1_.isAssignableFrom(var3.getClass()))
/*     */       {
/*  37 */         this.field_180218_a.put(p_180213_1_, var3);
/*     */       }
/*     */     } 
/*     */     
/*  41 */     this.field_180216_b.add(p_180213_1_);
/*     */   }
/*     */   
/*     */   protected Class func_180212_a(Class p_180212_1_, boolean p_180212_2_) {
/*     */     Class var4;
/*  46 */     Iterator<Class<?>> var3 = ClassUtils.hierarchy(p_180212_1_, ClassUtils.Interfaces.INCLUDE).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  51 */       if (!var3.hasNext())
/*     */       {
/*  53 */         throw new IllegalArgumentException("Don't know how to search for " + p_180212_1_);
/*     */       }
/*     */       
/*  56 */       var4 = var3.next();
/*     */     }
/*  58 */     while (!this.field_180216_b.contains(var4));
/*     */     
/*  60 */     if (var4 == this.field_180217_c && p_180212_2_)
/*     */     {
/*  62 */       func_180213_a(p_180212_1_);
/*     */     }
/*     */     
/*  65 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object p_add_1_) {
/*  70 */     Iterator<Class<?>> var2 = this.field_180216_b.iterator();
/*     */     
/*  72 */     while (var2.hasNext()) {
/*     */       
/*  74 */       Class var3 = var2.next();
/*     */       
/*  76 */       if (var3.isAssignableFrom(p_add_1_.getClass()))
/*     */       {
/*  78 */         this.field_180218_a.put(var3, p_add_1_);
/*     */       }
/*     */     } 
/*     */     
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Object p_remove_1_) {
/*  87 */     Object var2 = p_remove_1_;
/*  88 */     boolean var3 = false;
/*  89 */     Iterator<Class<?>> var4 = this.field_180216_b.iterator();
/*     */     
/*  91 */     while (var4.hasNext()) {
/*     */       
/*  93 */       Class var5 = var4.next();
/*     */       
/*  95 */       if (var5.isAssignableFrom(var2.getClass()))
/*     */       {
/*  97 */         var3 |= this.field_180218_a.remove(var5, var2);
/*     */       }
/*     */     } 
/*     */     
/* 101 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable func_180215_b(final Class p_180215_1_) {
/* 106 */     return new Iterable()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002265";
/*     */         
/*     */         public Iterator iterator() {
/* 111 */           Iterator var1 = ClassInheratanceMultiMap.this.field_180218_a.get(ClassInheratanceMultiMap.this.func_180212_a(p_180215_1_, true)).iterator();
/* 112 */           return (Iterator)Iterators.filter(var1, p_180215_1_);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 119 */     final Iterator var1 = this.field_180218_a.get(this.field_180217_c).iterator();
/* 120 */     return (Iterator)new AbstractIterator()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002264";
/*     */         
/*     */         protected Object computeNext() {
/* 125 */           return !var1.hasNext() ? endOfData() : var1.next();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 132 */     return this.field_180218_a.get(this.field_180217_c).size();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ClassInheratanceMultiMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */