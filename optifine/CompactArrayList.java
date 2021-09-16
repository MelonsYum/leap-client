/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ public class CompactArrayList
/*     */ {
/*     */   private ArrayList list;
/*     */   private int initialCapacity;
/*     */   private float loadFactor;
/*     */   private int countValid;
/*     */   
/*     */   public CompactArrayList() {
/*  14 */     this(10, 0.75F);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompactArrayList(int initialCapacity) {
/*  19 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompactArrayList(int initialCapacity, float loadFactor) {
/*  24 */     this.list = null;
/*  25 */     this.initialCapacity = 0;
/*  26 */     this.loadFactor = 1.0F;
/*  27 */     this.countValid = 0;
/*  28 */     this.list = new ArrayList(initialCapacity);
/*  29 */     this.initialCapacity = initialCapacity;
/*  30 */     this.loadFactor = loadFactor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int index, Object element) {
/*  35 */     if (element != null)
/*     */     {
/*  37 */       this.countValid++;
/*     */     }
/*     */     
/*  40 */     this.list.add(index, element);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(Object element) {
/*  45 */     if (element != null)
/*     */     {
/*  47 */       this.countValid++;
/*     */     }
/*     */     
/*  50 */     return this.list.add(element);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object set(int index, Object element) {
/*  55 */     Object oldElement = this.list.set(index, element);
/*     */     
/*  57 */     if (element != oldElement) {
/*     */       
/*  59 */       if (oldElement == null)
/*     */       {
/*  61 */         this.countValid++;
/*     */       }
/*     */       
/*  64 */       if (element == null)
/*     */       {
/*  66 */         this.countValid--;
/*     */       }
/*     */     } 
/*     */     
/*  70 */     return oldElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object remove(int index) {
/*  75 */     Object oldElement = this.list.remove(index);
/*     */     
/*  77 */     if (oldElement != null)
/*     */     {
/*  79 */       this.countValid--;
/*     */     }
/*     */     
/*  82 */     return oldElement;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/*  87 */     this.list.clear();
/*  88 */     this.countValid = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void compact() {
/*  93 */     if (this.countValid <= 0 && this.list.size() <= 0) {
/*     */       
/*  95 */       clear();
/*     */     }
/*  97 */     else if (this.list.size() > this.initialCapacity) {
/*     */       
/*  99 */       float currentLoadFactor = this.countValid * 1.0F / this.list.size();
/*     */       
/* 101 */       if (currentLoadFactor <= this.loadFactor) {
/*     */         
/* 103 */         int dstIndex = 0;
/*     */         
/*     */         int i;
/* 106 */         for (i = 0; i < this.list.size(); i++) {
/*     */           
/* 108 */           Object wr = this.list.get(i);
/*     */           
/* 110 */           if (wr != null) {
/*     */             
/* 112 */             if (i != dstIndex)
/*     */             {
/* 114 */               this.list.set(dstIndex, wr);
/*     */             }
/*     */             
/* 117 */             dstIndex++;
/*     */           } 
/*     */         } 
/*     */         
/* 121 */         for (i = this.list.size() - 1; i >= dstIndex; i--)
/*     */         {
/* 123 */           this.list.remove(i);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object elem) {
/* 131 */     return this.list.contains(elem);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object get(int index) {
/* 136 */     return this.list.get(index);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 141 */     return this.list.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 146 */     return this.list.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCountValid() {
/* 151 */     return this.countValid;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CompactArrayList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */