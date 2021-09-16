/*     */ package optifine;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.NextTickListEntry;
/*     */ 
/*     */ public class NextTickHashSet
/*     */   extends TreeSet {
/*  16 */   private LongHashMap longHashMap = new LongHashMap();
/*  17 */   private int minX = Integer.MIN_VALUE;
/*  18 */   private int minZ = Integer.MIN_VALUE;
/*  19 */   private int maxX = Integer.MIN_VALUE;
/*  20 */   private int maxZ = Integer.MIN_VALUE;
/*     */   
/*     */   private static final int UNDEFINED = -2147483648;
/*     */   
/*     */   public NextTickHashSet(Set oldSet) {
/*  25 */     Iterator it = oldSet.iterator();
/*     */     
/*  27 */     while (it.hasNext()) {
/*     */       
/*  29 */       Object obj = it.next();
/*  30 */       add(obj);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Object obj) {
/*  36 */     if (!(obj instanceof NextTickListEntry))
/*     */     {
/*  38 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  42 */     NextTickListEntry entry = (NextTickListEntry)obj;
/*  43 */     Set set = getSubSet(entry, false);
/*  44 */     return (set == null) ? false : set.contains(entry);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(Object obj) {
/*  50 */     if (!(obj instanceof NextTickListEntry))
/*     */     {
/*  52 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  56 */     NextTickListEntry entry = (NextTickListEntry)obj;
/*     */     
/*  58 */     if (entry == null)
/*     */     {
/*  60 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  64 */     Set<NextTickListEntry> set = getSubSet(entry, true);
/*  65 */     boolean added = set.add(entry);
/*  66 */     boolean addedParent = super.add(obj);
/*     */     
/*  68 */     if (added != addedParent)
/*     */     {
/*  70 */       throw new IllegalStateException("Added: " + added + ", addedParent: " + addedParent);
/*     */     }
/*     */ 
/*     */     
/*  74 */     return addedParent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object obj) {
/*  82 */     if (!(obj instanceof NextTickListEntry))
/*     */     {
/*  84 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  88 */     NextTickListEntry entry = (NextTickListEntry)obj;
/*  89 */     Set set = getSubSet(entry, false);
/*     */     
/*  91 */     if (set == null)
/*     */     {
/*  93 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  97 */     boolean removed = set.remove(entry);
/*  98 */     boolean removedParent = super.remove(entry);
/*     */     
/* 100 */     if (removed != removedParent)
/*     */     {
/* 102 */       throw new IllegalStateException("Added: " + removed + ", addedParent: " + removedParent);
/*     */     }
/*     */ 
/*     */     
/* 106 */     return removedParent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Set getSubSet(NextTickListEntry entry, boolean autoCreate) {
/* 114 */     if (entry == null)
/*     */     {
/* 116 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 120 */     BlockPos pos = entry.field_180282_a;
/* 121 */     int cx = pos.getX() >> 4;
/* 122 */     int cz = pos.getZ() >> 4;
/* 123 */     return getSubSet(cx, cz, autoCreate);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Set getSubSet(int cx, int cz, boolean autoCreate) {
/* 129 */     long key = ChunkCoordIntPair.chunkXZ2Int(cx, cz);
/* 130 */     HashSet set = (HashSet)this.longHashMap.getValueByKey(key);
/*     */     
/* 132 */     if (set == null && autoCreate) {
/*     */       
/* 134 */       set = new HashSet();
/* 135 */       this.longHashMap.add(key, set);
/*     */     } 
/*     */     
/* 138 */     return set;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 143 */     if (this.minX == Integer.MIN_VALUE)
/*     */     {
/* 145 */       return super.iterator();
/*     */     }
/* 147 */     if (size() <= 0)
/*     */     {
/* 149 */       return (Iterator)Iterators.emptyIterator();
/*     */     }
/*     */ 
/*     */     
/* 153 */     int cMinX = this.minX >> 4;
/* 154 */     int cMinZ = this.minZ >> 4;
/* 155 */     int cMaxX = this.maxX >> 4;
/* 156 */     int cMaxZ = this.maxZ >> 4;
/* 157 */     ArrayList<Iterator> listIterators = new ArrayList();
/*     */     
/* 159 */     for (int x = cMinX; x <= cMaxX; x++) {
/*     */       
/* 161 */       for (int z = cMinZ; z <= cMaxZ; z++) {
/*     */         
/* 163 */         Set set = getSubSet(x, z, false);
/*     */         
/* 165 */         if (set != null)
/*     */         {
/* 167 */           listIterators.add(set.iterator());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 172 */     if (listIterators.size() <= 0)
/*     */     {
/* 174 */       return (Iterator)Iterators.emptyIterator();
/*     */     }
/* 176 */     if (listIterators.size() == 1)
/*     */     {
/* 178 */       return listIterators.get(0);
/*     */     }
/*     */ 
/*     */     
/* 182 */     return Iterators.concat(listIterators.iterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIteratorLimits(int minX, int minZ, int maxX, int maxZ) {
/* 189 */     this.minX = Math.min(minX, maxX);
/* 190 */     this.minZ = Math.min(minZ, maxZ);
/* 191 */     this.maxX = Math.max(minX, maxX);
/* 192 */     this.maxZ = Math.max(minZ, maxZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearIteratorLimits() {
/* 197 */     this.minX = Integer.MIN_VALUE;
/* 198 */     this.minZ = Integer.MIN_VALUE;
/* 199 */     this.maxX = Integer.MIN_VALUE;
/* 200 */     this.maxZ = Integer.MIN_VALUE;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\NextTickHashSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */