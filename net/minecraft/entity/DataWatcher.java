/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.locks.ReadWriteLock;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.Rotations;
/*     */ import org.apache.commons.lang3.ObjectUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataWatcher
/*     */ {
/*     */   private final Entity owner;
/*     */   private boolean isBlank = true;
/*  27 */   private static final Map dataTypes = Maps.newHashMap();
/*  28 */   private final Map watchedObjects = Maps.newHashMap();
/*     */   
/*     */   private boolean objectChanged;
/*     */   
/*  32 */   private ReadWriteLock lock = new ReentrantReadWriteLock();
/*     */   
/*     */   private static final String __OBFID = "CL_00001559";
/*     */   
/*     */   public DataWatcher(Entity owner) {
/*  37 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addObject(int id, Object object) {
/*  46 */     Integer var3 = (Integer)dataTypes.get(object.getClass());
/*     */     
/*  48 */     if (var3 == null)
/*     */     {
/*  50 */       throw new IllegalArgumentException("Unknown data type: " + object.getClass());
/*     */     }
/*  52 */     if (id > 31)
/*     */     {
/*  54 */       throw new IllegalArgumentException("Data value id is too big with " + id + "! (Max is " + '\037' + ")");
/*     */     }
/*  56 */     if (this.watchedObjects.containsKey(Integer.valueOf(id)))
/*     */     {
/*  58 */       throw new IllegalArgumentException("Duplicate id value for " + id + "!");
/*     */     }
/*     */ 
/*     */     
/*  62 */     WatchableObject var4 = new WatchableObject(var3.intValue(), id, object);
/*  63 */     this.lock.writeLock().lock();
/*  64 */     this.watchedObjects.put(Integer.valueOf(id), var4);
/*  65 */     this.lock.writeLock().unlock();
/*  66 */     this.isBlank = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addObjectByDataType(int id, int type) {
/*  75 */     WatchableObject var3 = new WatchableObject(type, id, null);
/*  76 */     this.lock.writeLock().lock();
/*  77 */     this.watchedObjects.put(Integer.valueOf(id), var3);
/*  78 */     this.lock.writeLock().unlock();
/*  79 */     this.isBlank = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getWatchableObjectByte(int id) {
/*  87 */     return ((Byte)getWatchedObject(id).getObject()).byteValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public short getWatchableObjectShort(int id) {
/*  92 */     return ((Short)getWatchedObject(id).getObject()).shortValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWatchableObjectInt(int id) {
/* 100 */     return ((Integer)getWatchedObject(id).getObject()).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWatchableObjectFloat(int id) {
/* 105 */     return ((Float)getWatchedObject(id).getObject()).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWatchableObjectString(int id) {
/* 113 */     return (String)getWatchedObject(id).getObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getWatchableObjectItemStack(int id) {
/* 121 */     return (ItemStack)getWatchedObject(id).getObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WatchableObject getWatchedObject(int id) {
/*     */     WatchableObject var2;
/* 129 */     this.lock.readLock().lock();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 134 */       var2 = (WatchableObject)this.watchedObjects.get(Integer.valueOf(id));
/*     */     }
/* 136 */     catch (Throwable var6) {
/*     */       
/* 138 */       CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting synched entity data");
/* 139 */       CrashReportCategory var5 = var4.makeCategory("Synched entity data");
/* 140 */       var5.addCrashSection("Data ID", Integer.valueOf(id));
/* 141 */       throw new ReportedException(var4);
/*     */     } 
/*     */     
/* 144 */     this.lock.readLock().unlock();
/* 145 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getWatchableObjectRotations(int id) {
/* 150 */     return (Rotations)getWatchedObject(id).getObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateObject(int id, Object newData) {
/* 158 */     WatchableObject var3 = getWatchedObject(id);
/*     */     
/* 160 */     if (ObjectUtils.notEqual(newData, var3.getObject())) {
/*     */       
/* 162 */       var3.setObject(newData);
/* 163 */       this.owner.func_145781_i(id);
/* 164 */       var3.setWatched(true);
/* 165 */       this.objectChanged = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setObjectWatched(int id) {
/* 171 */     (getWatchedObject(id)).watched = true;
/* 172 */     this.objectChanged = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasObjectChanged() {
/* 180 */     return this.objectChanged;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeWatchedListToPacketBuffer(List objectsList, PacketBuffer buffer) throws IOException {
/* 189 */     if (objectsList != null) {
/*     */       
/* 191 */       Iterator<WatchableObject> var2 = objectsList.iterator();
/*     */       
/* 193 */       while (var2.hasNext()) {
/*     */         
/* 195 */         WatchableObject var3 = var2.next();
/* 196 */         writeWatchableObjectToPacketBuffer(buffer, var3);
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     buffer.writeByte(127);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getChanged() {
/* 205 */     ArrayList<WatchableObject> var1 = null;
/*     */     
/* 207 */     if (this.objectChanged) {
/*     */       
/* 209 */       this.lock.readLock().lock();
/* 210 */       Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator();
/*     */       
/* 212 */       while (var2.hasNext()) {
/*     */         
/* 214 */         WatchableObject var3 = var2.next();
/*     */         
/* 216 */         if (var3.isWatched()) {
/*     */           
/* 218 */           var3.setWatched(false);
/*     */           
/* 220 */           if (var1 == null)
/*     */           {
/* 222 */             var1 = Lists.newArrayList();
/*     */           }
/*     */           
/* 225 */           var1.add(var3);
/*     */         } 
/*     */       } 
/*     */       
/* 229 */       this.lock.readLock().unlock();
/*     */     } 
/*     */     
/* 232 */     this.objectChanged = false;
/* 233 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeTo(PacketBuffer buffer) throws IOException {
/* 238 */     this.lock.readLock().lock();
/* 239 */     Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator();
/*     */     
/* 241 */     while (var2.hasNext()) {
/*     */       
/* 243 */       WatchableObject var3 = var2.next();
/* 244 */       writeWatchableObjectToPacketBuffer(buffer, var3);
/*     */     } 
/*     */     
/* 247 */     this.lock.readLock().unlock();
/* 248 */     buffer.writeByte(127);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getAllWatched() {
/* 253 */     ArrayList<WatchableObject> var1 = null;
/* 254 */     this.lock.readLock().lock();
/*     */ 
/*     */     
/* 257 */     for (Iterator<WatchableObject> var2 = this.watchedObjects.values().iterator(); var2.hasNext(); var1.add(var3)) {
/*     */       
/* 259 */       WatchableObject var3 = var2.next();
/*     */       
/* 261 */       if (var1 == null)
/*     */       {
/* 263 */         var1 = Lists.newArrayList();
/*     */       }
/*     */     } 
/*     */     
/* 267 */     this.lock.readLock().unlock();
/* 268 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeWatchableObjectToPacketBuffer(PacketBuffer buffer, WatchableObject object) throws IOException {
/*     */     ItemStack var3;
/*     */     BlockPos var4;
/*     */     Rotations var5;
/* 277 */     int var2 = (object.getObjectType() << 5 | object.getDataValueId() & 0x1F) & 0xFF;
/* 278 */     buffer.writeByte(var2);
/*     */     
/* 280 */     switch (object.getObjectType()) {
/*     */       
/*     */       case 0:
/* 283 */         buffer.writeByte(((Byte)object.getObject()).byteValue());
/*     */         break;
/*     */       
/*     */       case 1:
/* 287 */         buffer.writeShort(((Short)object.getObject()).shortValue());
/*     */         break;
/*     */       
/*     */       case 2:
/* 291 */         buffer.writeInt(((Integer)object.getObject()).intValue());
/*     */         break;
/*     */       
/*     */       case 3:
/* 295 */         buffer.writeFloat(((Float)object.getObject()).floatValue());
/*     */         break;
/*     */       
/*     */       case 4:
/* 299 */         buffer.writeString((String)object.getObject());
/*     */         break;
/*     */       
/*     */       case 5:
/* 303 */         var3 = (ItemStack)object.getObject();
/* 304 */         buffer.writeItemStackToBuffer(var3);
/*     */         break;
/*     */       
/*     */       case 6:
/* 308 */         var4 = (BlockPos)object.getObject();
/* 309 */         buffer.writeInt(var4.getX());
/* 310 */         buffer.writeInt(var4.getY());
/* 311 */         buffer.writeInt(var4.getZ());
/*     */         break;
/*     */       
/*     */       case 7:
/* 315 */         var5 = (Rotations)object.getObject();
/* 316 */         buffer.writeFloat(var5.func_179415_b());
/* 317 */         buffer.writeFloat(var5.func_179416_c());
/* 318 */         buffer.writeFloat(var5.func_179413_d());
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List readWatchedListFromPacketBuffer(PacketBuffer buffer) throws IOException {
/* 328 */     ArrayList<WatchableObject> var1 = null;
/*     */     
/* 330 */     for (byte var2 = buffer.readByte(); var2 != Byte.MAX_VALUE; var2 = buffer.readByte()) {
/*     */       int var6, var7, var8; float var9, var10, var11;
/* 332 */       if (var1 == null)
/*     */       {
/* 334 */         var1 = Lists.newArrayList();
/*     */       }
/*     */       
/* 337 */       int var3 = (var2 & 0xE0) >> 5;
/* 338 */       int var4 = var2 & 0x1F;
/* 339 */       WatchableObject var5 = null;
/*     */       
/* 341 */       switch (var3) {
/*     */         
/*     */         case 0:
/* 344 */           var5 = new WatchableObject(var3, var4, Byte.valueOf(buffer.readByte()));
/*     */           break;
/*     */         
/*     */         case 1:
/* 348 */           var5 = new WatchableObject(var3, var4, Short.valueOf(buffer.readShort()));
/*     */           break;
/*     */         
/*     */         case 2:
/* 352 */           var5 = new WatchableObject(var3, var4, Integer.valueOf(buffer.readInt()));
/*     */           break;
/*     */         
/*     */         case 3:
/* 356 */           var5 = new WatchableObject(var3, var4, Float.valueOf(buffer.readFloat()));
/*     */           break;
/*     */         
/*     */         case 4:
/* 360 */           var5 = new WatchableObject(var3, var4, buffer.readStringFromBuffer(32767));
/*     */           break;
/*     */         
/*     */         case 5:
/* 364 */           var5 = new WatchableObject(var3, var4, buffer.readItemStackFromBuffer());
/*     */           break;
/*     */         
/*     */         case 6:
/* 368 */           var6 = buffer.readInt();
/* 369 */           var7 = buffer.readInt();
/* 370 */           var8 = buffer.readInt();
/* 371 */           var5 = new WatchableObject(var3, var4, new BlockPos(var6, var7, var8));
/*     */           break;
/*     */         
/*     */         case 7:
/* 375 */           var9 = buffer.readFloat();
/* 376 */           var10 = buffer.readFloat();
/* 377 */           var11 = buffer.readFloat();
/* 378 */           var5 = new WatchableObject(var3, var4, new Rotations(var9, var10, var11));
/*     */           break;
/*     */       } 
/* 381 */       var1.add(var5);
/*     */     } 
/*     */     
/* 384 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateWatchedObjectsFromList(List p_75687_1_) {
/* 389 */     this.lock.writeLock().lock();
/* 390 */     Iterator<WatchableObject> var2 = p_75687_1_.iterator();
/*     */     
/* 392 */     while (var2.hasNext()) {
/*     */       
/* 394 */       WatchableObject var3 = var2.next();
/* 395 */       WatchableObject var4 = (WatchableObject)this.watchedObjects.get(Integer.valueOf(var3.getDataValueId()));
/*     */       
/* 397 */       if (var4 != null) {
/*     */         
/* 399 */         var4.setObject(var3.getObject());
/* 400 */         this.owner.func_145781_i(var3.getDataValueId());
/*     */       } 
/*     */     } 
/*     */     
/* 404 */     this.lock.writeLock().unlock();
/* 405 */     this.objectChanged = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsBlank() {
/* 410 */     return this.isBlank;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_111144_e() {
/* 415 */     this.objectChanged = false;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 420 */     dataTypes.put(Byte.class, Integer.valueOf(0));
/* 421 */     dataTypes.put(Short.class, Integer.valueOf(1));
/* 422 */     dataTypes.put(Integer.class, Integer.valueOf(2));
/* 423 */     dataTypes.put(Float.class, Integer.valueOf(3));
/* 424 */     dataTypes.put(String.class, Integer.valueOf(4));
/* 425 */     dataTypes.put(ItemStack.class, Integer.valueOf(5));
/* 426 */     dataTypes.put(BlockPos.class, Integer.valueOf(6));
/* 427 */     dataTypes.put(Rotations.class, Integer.valueOf(7));
/*     */   }
/*     */ 
/*     */   
/*     */   public static class WatchableObject
/*     */   {
/*     */     private final int objectType;
/*     */     private final int dataValueId;
/*     */     private Object watchedObject;
/*     */     private boolean watched;
/*     */     private static final String __OBFID = "CL_00001560";
/*     */     
/*     */     public WatchableObject(int type, int id, Object object) {
/* 440 */       this.dataValueId = id;
/* 441 */       this.watchedObject = object;
/* 442 */       this.objectType = type;
/* 443 */       this.watched = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getDataValueId() {
/* 448 */       return this.dataValueId;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setObject(Object object) {
/* 453 */       this.watchedObject = object;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getObject() {
/* 458 */       return this.watchedObject;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getObjectType() {
/* 463 */       return this.objectType;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isWatched() {
/* 468 */       return this.watched;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setWatched(boolean watched) {
/* 473 */       this.watched = watched;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\DataWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */