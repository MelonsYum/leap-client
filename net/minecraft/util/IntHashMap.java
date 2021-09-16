/*     */ package net.minecraft.util;
/*     */ 
/*     */ 
/*     */ public class IntHashMap
/*     */ {
/*   6 */   private transient Entry[] slots = new Entry[16];
/*     */ 
/*     */   
/*     */   private transient int count;
/*     */ 
/*     */   
/*  12 */   private int threshold = 12;
/*     */ 
/*     */   
/*  15 */   private final float growFactor = 0.75F;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001490";
/*     */ 
/*     */ 
/*     */   
/*     */   private static int computeHash(int p_76044_0_) {
/*  23 */     p_76044_0_ ^= p_76044_0_ >>> 20 ^ p_76044_0_ >>> 12;
/*  24 */     return p_76044_0_ ^ p_76044_0_ >>> 7 ^ p_76044_0_ >>> 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getSlotIndex(int p_76043_0_, int p_76043_1_) {
/*  32 */     return p_76043_0_ & p_76043_1_ - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object lookup(int p_76041_1_) {
/*  40 */     int var2 = computeHash(p_76041_1_);
/*     */     
/*  42 */     for (Entry var3 = this.slots[getSlotIndex(var2, this.slots.length)]; var3 != null; var3 = var3.nextEntry) {
/*     */       
/*  44 */       if (var3.hashEntry == p_76041_1_)
/*     */       {
/*  46 */         return var3.valueEntry;
/*     */       }
/*     */     } 
/*     */     
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsItem(int p_76037_1_) {
/*  58 */     return (lookupEntry(p_76037_1_) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Entry lookupEntry(int p_76045_1_) {
/*  66 */     int var2 = computeHash(p_76045_1_);
/*     */     
/*  68 */     for (Entry var3 = this.slots[getSlotIndex(var2, this.slots.length)]; var3 != null; var3 = var3.nextEntry) {
/*     */       
/*  70 */       if (var3.hashEntry == p_76045_1_)
/*     */       {
/*  72 */         return var3;
/*     */       }
/*     */     } 
/*     */     
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addKey(int p_76038_1_, Object p_76038_2_) {
/*  84 */     int var3 = computeHash(p_76038_1_);
/*  85 */     int var4 = getSlotIndex(var3, this.slots.length);
/*     */     
/*  87 */     for (Entry var5 = this.slots[var4]; var5 != null; var5 = var5.nextEntry) {
/*     */       
/*  89 */       if (var5.hashEntry == p_76038_1_) {
/*     */         
/*  91 */         var5.valueEntry = p_76038_2_;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*  96 */     insert(var3, p_76038_1_, p_76038_2_, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void grow(int p_76047_1_) {
/* 104 */     Entry[] var2 = this.slots;
/* 105 */     int var3 = var2.length;
/*     */     
/* 107 */     if (var3 == 1073741824) {
/*     */       
/* 109 */       this.threshold = Integer.MAX_VALUE;
/*     */     }
/*     */     else {
/*     */       
/* 113 */       Entry[] var4 = new Entry[p_76047_1_];
/* 114 */       copyTo(var4);
/* 115 */       this.slots = var4;
/* 116 */       this.threshold = (int)(p_76047_1_ * 0.75F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyTo(Entry[] p_76048_1_) {
/* 125 */     Entry[] var2 = this.slots;
/* 126 */     int var3 = p_76048_1_.length;
/*     */     
/* 128 */     for (int var4 = 0; var4 < var2.length; var4++) {
/*     */       
/* 130 */       Entry var5 = var2[var4];
/*     */       
/* 132 */       if (var5 != null) {
/*     */         Entry var6;
/* 134 */         var2[var4] = null;
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 139 */           var6 = var5.nextEntry;
/* 140 */           int var7 = getSlotIndex(var5.slotHash, var3);
/* 141 */           var5.nextEntry = p_76048_1_[var7];
/* 142 */           p_76048_1_[var7] = var5;
/* 143 */           var5 = var6;
/*     */         }
/* 145 */         while (var6 != null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object removeObject(int p_76049_1_) {
/* 155 */     Entry var2 = removeEntry(p_76049_1_);
/* 156 */     return (var2 == null) ? null : var2.valueEntry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Entry removeEntry(int p_76036_1_) {
/* 164 */     int var2 = computeHash(p_76036_1_);
/* 165 */     int var3 = getSlotIndex(var2, this.slots.length);
/* 166 */     Entry var4 = this.slots[var3];
/*     */     
/*     */     Entry var5;
/*     */     
/* 170 */     for (var5 = var4; var5 != null; var5 = var6) {
/*     */       
/* 172 */       Entry var6 = var5.nextEntry;
/*     */       
/* 174 */       if (var5.hashEntry == p_76036_1_) {
/*     */         
/* 176 */         this.count--;
/*     */         
/* 178 */         if (var4 == var5) {
/*     */           
/* 180 */           this.slots[var3] = var6;
/*     */         }
/*     */         else {
/*     */           
/* 184 */           var4.nextEntry = var6;
/*     */         } 
/*     */         
/* 187 */         return var5;
/*     */       } 
/*     */       
/* 190 */       var4 = var5;
/*     */     } 
/*     */     
/* 193 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearMap() {
/* 201 */     Entry[] var1 = this.slots;
/*     */     
/* 203 */     for (int var2 = 0; var2 < var1.length; var2++)
/*     */     {
/* 205 */       var1[var2] = null;
/*     */     }
/*     */     
/* 208 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void insert(int p_76040_1_, int p_76040_2_, Object p_76040_3_, int p_76040_4_) {
/* 216 */     Entry var5 = this.slots[p_76040_4_];
/* 217 */     this.slots[p_76040_4_] = new Entry(p_76040_1_, p_76040_2_, p_76040_3_, var5);
/*     */     
/* 219 */     if (this.count++ >= this.threshold)
/*     */     {
/* 221 */       grow(2 * this.slots.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class Entry
/*     */   {
/*     */     final int hashEntry;
/*     */     Object valueEntry;
/*     */     Entry nextEntry;
/*     */     final int slotHash;
/*     */     private static final String __OBFID = "CL_00001491";
/*     */     
/*     */     Entry(int p_i1552_1_, int p_i1552_2_, Object p_i1552_3_, Entry p_i1552_4_) {
/* 235 */       this.valueEntry = p_i1552_3_;
/* 236 */       this.nextEntry = p_i1552_4_;
/* 237 */       this.hashEntry = p_i1552_2_;
/* 238 */       this.slotHash = p_i1552_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public final int getHash() {
/* 243 */       return this.hashEntry;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Object getValue() {
/* 248 */       return this.valueEntry;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean equals(Object p_equals_1_) {
/* 253 */       if (!(p_equals_1_ instanceof Entry))
/*     */       {
/* 255 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 259 */       Entry var2 = (Entry)p_equals_1_;
/* 260 */       Integer var3 = Integer.valueOf(getHash());
/* 261 */       Integer var4 = Integer.valueOf(var2.getHash());
/*     */       
/* 263 */       if (var3 == var4 || (var3 != null && var3.equals(var4))) {
/*     */         
/* 265 */         Object var5 = getValue();
/* 266 */         Object var6 = var2.getValue();
/*     */         
/* 268 */         if (var5 == var6 || (var5 != null && var5.equals(var6)))
/*     */         {
/* 270 */           return true;
/*     */         }
/*     */       } 
/*     */       
/* 274 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final int hashCode() {
/* 280 */       return IntHashMap.computeHash(this.hashEntry);
/*     */     }
/*     */ 
/*     */     
/*     */     public final String toString() {
/* 285 */       return String.valueOf(getHash()) + "=" + getValue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\IntHashMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */