/*     */ package net.minecraft.util;
/*     */ 
/*     */ 
/*     */ public class LongHashMap
/*     */ {
/*   6 */   private transient Entry[] hashArray = new Entry[4096];
/*     */ 
/*     */   
/*     */   private transient int numHashElements;
/*     */ 
/*     */   
/*     */   private int field_180201_c;
/*     */ 
/*     */   
/*  15 */   private int capacity = 3072;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private final float percentUseable = 0.75F;
/*     */   
/*     */   private volatile transient int modCount;
/*     */   
/*     */   private static final String __OBFID = "CL_00001492";
/*     */ 
/*     */   
/*     */   public LongHashMap() {
/*  28 */     this.field_180201_c = this.hashArray.length - 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getHashedKey(long originalKey) {
/*  36 */     return (int)(originalKey ^ originalKey >>> 27L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int hash(int integer) {
/*  44 */     integer ^= integer >>> 20 ^ integer >>> 12;
/*  45 */     return integer ^ integer >>> 7 ^ integer >>> 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getHashIndex(int p_76158_0_, int p_76158_1_) {
/*  53 */     return p_76158_0_ & p_76158_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumHashElements() {
/*  58 */     return this.numHashElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getValueByKey(long p_76164_1_) {
/*  66 */     int var3 = getHashedKey(p_76164_1_);
/*     */     
/*  68 */     for (Entry var4 = this.hashArray[getHashIndex(var3, this.field_180201_c)]; var4 != null; var4 = var4.nextEntry) {
/*     */       
/*  70 */       if (var4.key == p_76164_1_)
/*     */       {
/*  72 */         return var4.value;
/*     */       }
/*     */     } 
/*     */     
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsItem(long p_76161_1_) {
/*  81 */     return (getEntry(p_76161_1_) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   final Entry getEntry(long p_76160_1_) {
/*  86 */     int var3 = getHashedKey(p_76160_1_);
/*     */     
/*  88 */     for (Entry var4 = this.hashArray[getHashIndex(var3, this.field_180201_c)]; var4 != null; var4 = var4.nextEntry) {
/*     */       
/*  90 */       if (var4.key == p_76160_1_)
/*     */       {
/*  92 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(long p_76163_1_, Object p_76163_3_) {
/* 104 */     int var4 = getHashedKey(p_76163_1_);
/* 105 */     int var5 = getHashIndex(var4, this.field_180201_c);
/*     */     
/* 107 */     for (Entry var6 = this.hashArray[var5]; var6 != null; var6 = var6.nextEntry) {
/*     */       
/* 109 */       if (var6.key == p_76163_1_) {
/*     */         
/* 111 */         var6.value = p_76163_3_;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 116 */     this.modCount++;
/* 117 */     createKey(var4, p_76163_1_, p_76163_3_, var5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void resizeTable(int p_76153_1_) {
/* 125 */     Entry[] var2 = this.hashArray;
/* 126 */     int var3 = var2.length;
/*     */     
/* 128 */     if (var3 == 1073741824) {
/*     */       
/* 130 */       this.capacity = Integer.MAX_VALUE;
/*     */     }
/*     */     else {
/*     */       
/* 134 */       Entry[] var4 = new Entry[p_76153_1_];
/* 135 */       copyHashTableTo(var4);
/* 136 */       this.hashArray = var4;
/* 137 */       this.field_180201_c = this.hashArray.length - 1;
/* 138 */       float var10001 = p_76153_1_;
/* 139 */       getClass();
/* 140 */       this.capacity = (int)(var10001 * 0.75F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyHashTableTo(Entry[] p_76154_1_) {
/* 149 */     Entry[] var2 = this.hashArray;
/* 150 */     int var3 = p_76154_1_.length;
/*     */     
/* 152 */     for (int var4 = 0; var4 < var2.length; var4++) {
/*     */       
/* 154 */       Entry var5 = var2[var4];
/*     */       
/* 156 */       if (var5 != null) {
/*     */         Entry var6;
/* 158 */         var2[var4] = null;
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 163 */           var6 = var5.nextEntry;
/* 164 */           int var7 = getHashIndex(var5.hash, var3 - 1);
/* 165 */           var5.nextEntry = p_76154_1_[var7];
/* 166 */           p_76154_1_[var7] = var5;
/* 167 */           var5 = var6;
/*     */         }
/* 169 */         while (var6 != null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove(long p_76159_1_) {
/* 179 */     Entry var3 = removeKey(p_76159_1_);
/* 180 */     return (var3 == null) ? null : var3.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Entry removeKey(long p_76152_1_) {
/* 188 */     int var3 = getHashedKey(p_76152_1_);
/* 189 */     int var4 = getHashIndex(var3, this.field_180201_c);
/* 190 */     Entry var5 = this.hashArray[var4];
/*     */     
/*     */     Entry var6;
/*     */     
/* 194 */     for (var6 = var5; var6 != null; var6 = var7) {
/*     */       
/* 196 */       Entry var7 = var6.nextEntry;
/*     */       
/* 198 */       if (var6.key == p_76152_1_) {
/*     */         
/* 200 */         this.modCount++;
/* 201 */         this.numHashElements--;
/*     */         
/* 203 */         if (var5 == var6) {
/*     */           
/* 205 */           this.hashArray[var4] = var7;
/*     */         }
/*     */         else {
/*     */           
/* 209 */           var5.nextEntry = var7;
/*     */         } 
/*     */         
/* 212 */         return var6;
/*     */       } 
/*     */       
/* 215 */       var5 = var6;
/*     */     } 
/*     */     
/* 218 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createKey(int p_76156_1_, long p_76156_2_, Object p_76156_4_, int p_76156_5_) {
/* 226 */     Entry var6 = this.hashArray[p_76156_5_];
/* 227 */     this.hashArray[p_76156_5_] = new Entry(p_76156_1_, p_76156_2_, p_76156_4_, var6);
/*     */     
/* 229 */     if (this.numHashElements++ >= this.capacity)
/*     */     {
/* 231 */       resizeTable(2 * this.hashArray.length);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getKeyDistribution() {
/* 237 */     int countValid = 0;
/*     */     
/* 239 */     for (int i = 0; i < this.hashArray.length; i++) {
/*     */       
/* 241 */       if (this.hashArray[i] != null)
/*     */       {
/* 243 */         countValid++;
/*     */       }
/*     */     } 
/*     */     
/* 247 */     return 1.0D * countValid / this.numHashElements;
/*     */   }
/*     */ 
/*     */   
/*     */   static class Entry
/*     */   {
/*     */     final long key;
/*     */     Object value;
/*     */     Entry nextEntry;
/*     */     final int hash;
/*     */     private static final String __OBFID = "CL_00001493";
/*     */     
/*     */     Entry(int p_i1553_1_, long p_i1553_2_, Object p_i1553_4_, Entry p_i1553_5_) {
/* 260 */       this.value = p_i1553_4_;
/* 261 */       this.nextEntry = p_i1553_5_;
/* 262 */       this.key = p_i1553_2_;
/* 263 */       this.hash = p_i1553_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public final long getKey() {
/* 268 */       return this.key;
/*     */     }
/*     */ 
/*     */     
/*     */     public final Object getValue() {
/* 273 */       return this.value;
/*     */     }
/*     */ 
/*     */     
/*     */     public final boolean equals(Object p_equals_1_) {
/* 278 */       if (!(p_equals_1_ instanceof Entry))
/*     */       {
/* 280 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 284 */       Entry var2 = (Entry)p_equals_1_;
/* 285 */       Long var3 = Long.valueOf(getKey());
/* 286 */       Long var4 = Long.valueOf(var2.getKey());
/*     */       
/* 288 */       if (var3 == var4 || (var3 != null && var3.equals(var4))) {
/*     */         
/* 290 */         Object var5 = getValue();
/* 291 */         Object var6 = var2.getValue();
/*     */         
/* 293 */         if (var5 == var6 || (var5 != null && var5.equals(var6)))
/*     */         {
/* 295 */           return true;
/*     */         }
/*     */       } 
/*     */       
/* 299 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public final int hashCode() {
/* 305 */       return LongHashMap.getHashedKey(this.key);
/*     */     }
/*     */ 
/*     */     
/*     */     public final String toString() {
/* 310 */       return String.valueOf(getKey()) + "=" + getValue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\LongHashMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */