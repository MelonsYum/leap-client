/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NBTTagCompound
/*     */   extends NBTBase
/*     */ {
/*     */   public NBTTagCompound() {
/*  24 */     this.tagMap = Maps.newHashMap();
/*     */   }
/*     */   
/*     */   private static final Logger logger = LogManager.getLogger();
/*     */   private Map tagMap;
/*     */   private static final String __OBFID = "CL_00001215";
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  32 */     Iterator<String> var2 = this.tagMap.keySet().iterator();
/*     */     
/*  34 */     while (var2.hasNext()) {
/*     */       
/*  36 */       String var3 = var2.next();
/*  37 */       NBTBase var4 = (NBTBase)this.tagMap.get(var3);
/*  38 */       writeEntry(var3, var4, output);
/*     */     } 
/*     */     
/*  41 */     output.writeByte(0);
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  46 */     if (depth > 512)
/*     */     {
/*  48 */       throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */     }
/*     */ 
/*     */     
/*  52 */     this.tagMap.clear();
/*     */     
/*     */     byte var4;
/*  55 */     while ((var4 = readType(input, sizeTracker)) != 0) {
/*     */       
/*  57 */       String var5 = readKey(input, sizeTracker);
/*  58 */       sizeTracker.read((16 * var5.length()));
/*  59 */       NBTBase var6 = readNBT(var4, var5, input, depth + 1, sizeTracker);
/*  60 */       this.tagMap.put(var5, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getKeySet() {
/*  70 */     return this.tagMap.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  78 */     return 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTag(String key, NBTBase value) {
/*  86 */     this.tagMap.put(key, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByte(String key, byte value) {
/*  94 */     this.tagMap.put(key, new NBTTagByte(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShort(String key, short value) {
/* 102 */     this.tagMap.put(key, new NBTTagShort(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteger(String key, int value) {
/* 110 */     this.tagMap.put(key, new NBTTagInt(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(String key, long value) {
/* 118 */     this.tagMap.put(key, new NBTTagLong(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloat(String key, float value) {
/* 126 */     this.tagMap.put(key, new NBTTagFloat(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDouble(String key, double value) {
/* 134 */     this.tagMap.put(key, new NBTTagDouble(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setString(String key, String value) {
/* 142 */     this.tagMap.put(key, new NBTTagString(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteArray(String key, byte[] value) {
/* 150 */     this.tagMap.put(key, new NBTTagByteArray(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntArray(String key, int[] value) {
/* 158 */     this.tagMap.put(key, new NBTTagIntArray(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(String key, boolean value) {
/* 166 */     setByte(key, (byte)(value ? 1 : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase getTag(String key) {
/* 174 */     return (NBTBase)this.tagMap.get(key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getTagType(String key) {
/* 182 */     NBTBase var2 = (NBTBase)this.tagMap.get(key);
/* 183 */     return (var2 != null) ? var2.getId() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasKey(String key) {
/* 191 */     return this.tagMap.containsKey(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasKey(String key, int type) {
/* 196 */     byte var3 = getTagType(key);
/*     */     
/* 198 */     if (var3 == type)
/*     */     {
/* 200 */       return true;
/*     */     }
/* 202 */     if (type != 99) {
/*     */       
/* 204 */       if (var3 > 0);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 209 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 213 */     return !(var3 != 1 && var3 != 2 && var3 != 3 && var3 != 4 && var3 != 5 && var3 != 6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getByte(String key) {
/*     */     try {
/* 224 */       return !hasKey(key, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(key)).getByte();
/*     */     }
/* 226 */     catch (ClassCastException var3) {
/*     */       
/* 228 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getShort(String key) {
/*     */     try {
/* 239 */       return !hasKey(key, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(key)).getShort();
/*     */     }
/* 241 */     catch (ClassCastException var3) {
/*     */       
/* 243 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInteger(String key) {
/*     */     try {
/* 254 */       return !hasKey(key, 99) ? 0 : ((NBTBase.NBTPrimitive)this.tagMap.get(key)).getInt();
/*     */     }
/* 256 */     catch (ClassCastException var3) {
/*     */       
/* 258 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong(String key) {
/*     */     try {
/* 269 */       return !hasKey(key, 99) ? 0L : ((NBTBase.NBTPrimitive)this.tagMap.get(key)).getLong();
/*     */     }
/* 271 */     catch (ClassCastException var3) {
/*     */       
/* 273 */       return 0L;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(String key) {
/*     */     try {
/* 284 */       return !hasKey(key, 99) ? 0.0F : ((NBTBase.NBTPrimitive)this.tagMap.get(key)).getFloat();
/*     */     }
/* 286 */     catch (ClassCastException var3) {
/*     */       
/* 288 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(String key) {
/*     */     try {
/* 299 */       return !hasKey(key, 99) ? 0.0D : ((NBTBase.NBTPrimitive)this.tagMap.get(key)).getDouble();
/*     */     }
/* 301 */     catch (ClassCastException var3) {
/*     */       
/* 303 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString(String key) {
/*     */     try {
/* 314 */       return !hasKey(key, 8) ? "" : ((NBTBase)this.tagMap.get(key)).getString();
/*     */     }
/* 316 */     catch (ClassCastException var3) {
/*     */       
/* 318 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getByteArray(String key) {
/*     */     try {
/* 329 */       return !hasKey(key, 7) ? new byte[0] : ((NBTTagByteArray)this.tagMap.get(key)).getByteArray();
/*     */     }
/* 331 */     catch (ClassCastException var3) {
/*     */       
/* 333 */       throw new ReportedException(createCrashReport(key, 7, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getIntArray(String key) {
/*     */     try {
/* 344 */       return !hasKey(key, 11) ? new int[0] : ((NBTTagIntArray)this.tagMap.get(key)).getIntArray();
/*     */     }
/* 346 */     catch (ClassCastException var3) {
/*     */       
/* 348 */       throw new ReportedException(createCrashReport(key, 11, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getCompoundTag(String key) {
/*     */     try {
/* 360 */       return !hasKey(key, 10) ? new NBTTagCompound() : (NBTTagCompound)this.tagMap.get(key);
/*     */     }
/* 362 */     catch (ClassCastException var3) {
/*     */       
/* 364 */       throw new ReportedException(createCrashReport(key, 10, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList getTagList(String key, int type) {
/*     */     try {
/* 375 */       if (getTagType(key) != 9)
/*     */       {
/* 377 */         return new NBTTagList();
/*     */       }
/*     */ 
/*     */       
/* 381 */       NBTTagList var3 = (NBTTagList)this.tagMap.get(key);
/* 382 */       return (var3.tagCount() > 0 && var3.getTagType() != type) ? new NBTTagList() : var3;
/*     */     
/*     */     }
/* 385 */     catch (ClassCastException var4) {
/*     */       
/* 387 */       throw new ReportedException(createCrashReport(key, 9, var4));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean(String key) {
/* 397 */     return (getByte(key) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTag(String key) {
/* 405 */     this.tagMap.remove(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 410 */     String var1 = "{";
/*     */ 
/*     */     
/* 413 */     for (Iterator<String> var2 = this.tagMap.keySet().iterator(); var2.hasNext(); var1 = String.valueOf(var1) + var3 + ':' + this.tagMap.get(var3) + ',')
/*     */     {
/* 415 */       String var3 = var2.next();
/*     */     }
/*     */     
/* 418 */     return String.valueOf(var1) + "}";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNoTags() {
/* 426 */     return this.tagMap.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private CrashReport createCrashReport(final String key, final int expectedType, ClassCastException ex) {
/* 434 */     CrashReport var4 = CrashReport.makeCrashReport(ex, "Reading NBT data");
/* 435 */     CrashReportCategory var5 = var4.makeCategoryDepth("Corrupt NBT tag", 1);
/* 436 */     var5.addCrashSectionCallable("Tag type found", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001216";
/*     */           
/*     */           public String call() {
/* 441 */             return NBTBase.NBT_TYPES[((NBTBase)NBTTagCompound.this.tagMap.get(key)).getId()];
/*     */           }
/*     */         });
/* 444 */     var5.addCrashSectionCallable("Tag type expected", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001217";
/*     */           
/*     */           public String call() {
/* 449 */             return NBTBase.NBT_TYPES[expectedType];
/*     */           }
/*     */         });
/* 452 */     var5.addCrashSection("Tag name", key);
/* 453 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/* 461 */     NBTTagCompound var1 = new NBTTagCompound();
/* 462 */     Iterator<String> var2 = this.tagMap.keySet().iterator();
/*     */     
/* 464 */     while (var2.hasNext()) {
/*     */       
/* 466 */       String var3 = var2.next();
/* 467 */       var1.setTag(var3, ((NBTBase)this.tagMap.get(var3)).copy());
/*     */     } 
/*     */     
/* 470 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 475 */     if (super.equals(p_equals_1_)) {
/*     */       
/* 477 */       NBTTagCompound var2 = (NBTTagCompound)p_equals_1_;
/* 478 */       return this.tagMap.entrySet().equals(var2.tagMap.entrySet());
/*     */     } 
/*     */ 
/*     */     
/* 482 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 488 */     return super.hashCode() ^ this.tagMap.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeEntry(String name, NBTBase data, DataOutput output) throws IOException {
/* 493 */     output.writeByte(data.getId());
/*     */     
/* 495 */     if (data.getId() != 0) {
/*     */       
/* 497 */       output.writeUTF(name);
/* 498 */       data.write(output);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static byte readType(DataInput input, NBTSizeTracker sizeTracker) throws IOException {
/* 504 */     return input.readByte();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String readKey(DataInput input, NBTSizeTracker sizeTracker) throws IOException {
/* 509 */     return input.readUTF();
/*     */   }
/*     */ 
/*     */   
/*     */   static NBTBase readNBT(byte id, String key, DataInput input, int depth, NBTSizeTracker sizeTracker) {
/* 514 */     NBTBase var5 = NBTBase.createNewByType(id);
/*     */ 
/*     */     
/*     */     try {
/* 518 */       var5.read(input, depth, sizeTracker);
/* 519 */       return var5;
/*     */     }
/* 521 */     catch (IOException var9) {
/*     */       
/* 523 */       CrashReport var7 = CrashReport.makeCrashReport(var9, "Loading NBT data");
/* 524 */       CrashReportCategory var8 = var7.makeCategory("NBT Tag");
/* 525 */       var8.addCrashSection("Tag name", key);
/* 526 */       var8.addCrashSection("Tag type", Byte.valueOf(id));
/* 527 */       throw new ReportedException(var7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(NBTTagCompound other) {
/* 537 */     Iterator<String> var2 = other.tagMap.keySet().iterator();
/*     */     
/* 539 */     while (var2.hasNext()) {
/*     */       
/* 541 */       String var3 = var2.next();
/* 542 */       NBTBase var4 = (NBTBase)other.tagMap.get(var3);
/*     */       
/* 544 */       if (var4.getId() == 10) {
/*     */         
/* 546 */         if (hasKey(var3, 10)) {
/*     */           
/* 548 */           NBTTagCompound var5 = getCompoundTag(var3);
/* 549 */           var5.merge((NBTTagCompound)var4);
/*     */           
/*     */           continue;
/*     */         } 
/* 553 */         setTag(var3, var4.copy());
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 558 */       setTag(var3, var4.copy());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagCompound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */