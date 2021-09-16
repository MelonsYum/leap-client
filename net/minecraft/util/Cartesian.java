/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cartesian
/*     */ {
/*     */   private static final String __OBFID = "CL_00002327";
/*     */   
/*     */   public static Iterable cartesianProduct(Class clazz, Iterable sets) {
/*  26 */     return new Product(clazz, (Iterable[])toArray(Iterable.class, sets), null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Iterable cartesianProduct(Iterable sets) {
/*  34 */     return arraysAsLists(cartesianProduct(Object.class, sets));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Iterable arraysAsLists(Iterable arrays) {
/*  42 */     return Iterables.transform(arrays, new GetList(null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object[] toArray(Class clazz, Iterable it) {
/*  50 */     ArrayList<Object> var2 = Lists.newArrayList();
/*  51 */     Iterator var3 = it.iterator();
/*     */     
/*  53 */     while (var3.hasNext()) {
/*     */       
/*  55 */       Object var4 = var3.next();
/*  56 */       var2.add(var4);
/*     */     } 
/*     */     
/*  59 */     return var2.toArray(createArray(clazz, var2.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   private static Object[] createArray(Class<?> p_179319_0_, int p_179319_1_) {
/*  64 */     return (Object[])Array.newInstance(p_179319_0_, p_179319_1_);
/*     */   }
/*     */   
/*     */   static class GetList
/*     */     implements Function
/*     */   {
/*     */     private static final String __OBFID = "CL_00002325";
/*     */     
/*     */     private GetList() {}
/*     */     
/*     */     public List apply(Object[] array) {
/*  75 */       return Arrays.asList(array);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object apply(Object p_apply_1_) {
/*  80 */       return apply((Object[])p_apply_1_);
/*     */     }
/*     */ 
/*     */     
/*     */     GetList(Object p_i46022_1_) {
/*  85 */       this();
/*     */     }
/*     */   }
/*     */   
/*     */   static class Product
/*     */     implements Iterable
/*     */   {
/*     */     private final Class clazz;
/*     */     private final Iterable[] iterables;
/*     */     private static final String __OBFID = "CL_00002324";
/*     */     
/*     */     private Product(Class clazz, Iterable[] iterables) {
/*  97 */       this.clazz = clazz;
/*  98 */       this.iterables = iterables;
/*     */     }
/*     */ 
/*     */     
/*     */     public Iterator iterator() {
/* 103 */       return (this.iterables.length <= 0) ? Collections.<Object[]>singletonList(Cartesian.createArray(this.clazz, 0)).iterator() : (Iterator)new ProductIterator(this.clazz, this.iterables, null);
/*     */     }
/*     */ 
/*     */     
/*     */     Product(Class p_i46021_1_, Iterable[] p_i46021_2_, Object p_i46021_3_) {
/* 108 */       this(p_i46021_1_, p_i46021_2_);
/*     */     }
/*     */     
/*     */     static class ProductIterator
/*     */       extends UnmodifiableIterator
/*     */     {
/*     */       private int index;
/*     */       private final Iterable[] iterables;
/*     */       private final Iterator[] iterators;
/*     */       private final Object[] results;
/*     */       private static final String __OBFID = "CL_00002323";
/*     */       
/*     */       private ProductIterator(Class clazz, Iterable[] iterables) {
/* 121 */         this.index = -2;
/* 122 */         this.iterables = iterables;
/* 123 */         this.iterators = (Iterator[])Cartesian.createArray(Iterator.class, this.iterables.length);
/*     */         
/* 125 */         for (int var3 = 0; var3 < this.iterables.length; var3++)
/*     */         {
/* 127 */           this.iterators[var3] = iterables[var3].iterator();
/*     */         }
/*     */         
/* 130 */         this.results = Cartesian.createArray(clazz, this.iterators.length);
/*     */       }
/*     */ 
/*     */       
/*     */       private void endOfData() {
/* 135 */         this.index = -1;
/* 136 */         Arrays.fill((Object[])this.iterators, (Object)null);
/* 137 */         Arrays.fill(this.results, (Object)null);
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean hasNext() {
/* 142 */         if (this.index == -2) {
/*     */           
/* 144 */           this.index = 0;
/* 145 */           Iterator[] var5 = this.iterators;
/* 146 */           int var2 = var5.length;
/*     */           
/* 148 */           for (int var3 = 0; var3 < var2; var3++) {
/*     */             
/* 150 */             Iterator var4 = var5[var3];
/*     */             
/* 152 */             if (!var4.hasNext()) {
/*     */               
/* 154 */               endOfData();
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 159 */           return true;
/*     */         } 
/*     */ 
/*     */         
/* 163 */         if (this.index >= this.iterators.length)
/*     */         {
/* 165 */           for (this.index = this.iterators.length - 1; this.index >= 0; this.index--) {
/*     */             
/* 167 */             Iterator<?> var1 = this.iterators[this.index];
/*     */             
/* 169 */             if (var1.hasNext()) {
/*     */               break;
/*     */             }
/*     */ 
/*     */             
/* 174 */             if (this.index == 0) {
/*     */               
/* 176 */               endOfData();
/*     */               
/*     */               break;
/*     */             } 
/* 180 */             var1 = this.iterables[this.index].iterator();
/* 181 */             this.iterators[this.index] = var1;
/*     */             
/* 183 */             if (!var1.hasNext()) {
/*     */               
/* 185 */               endOfData();
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/* 191 */         return (this.index >= 0);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Object[] next0() {
/* 197 */         if (!hasNext())
/*     */         {
/* 199 */           throw new NoSuchElementException();
/*     */         }
/*     */ 
/*     */         
/* 203 */         while (this.index < this.iterators.length) {
/*     */           
/* 205 */           this.results[this.index] = this.iterators[this.index].next();
/* 206 */           this.index++;
/*     */         } 
/*     */         
/* 209 */         return (Object[])this.results.clone();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public Object next() {
/* 215 */         return next0();
/*     */       }
/*     */ 
/*     */       
/*     */       ProductIterator(Class p_i46019_1_, Iterable[] p_i46019_2_, Object p_i46019_3_) {
/* 220 */         this(p_i46019_1_, p_i46019_2_);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Cartesian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */