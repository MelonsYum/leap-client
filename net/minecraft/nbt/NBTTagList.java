/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NBTTagList
/*     */   extends NBTBase {
/*  14 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */   
/*  17 */   private List tagList = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   private byte tagType = 0;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001224";
/*     */ 
/*     */ 
/*     */   
/*     */   void write(DataOutput output) throws IOException {
/*  30 */     if (!this.tagList.isEmpty()) {
/*     */       
/*  32 */       this.tagType = ((NBTBase)this.tagList.get(0)).getId();
/*     */     }
/*     */     else {
/*     */       
/*  36 */       this.tagType = 0;
/*     */     } 
/*     */     
/*  39 */     output.writeByte(this.tagType);
/*  40 */     output.writeInt(this.tagList.size());
/*     */     
/*  42 */     for (int var2 = 0; var2 < this.tagList.size(); var2++)
/*     */     {
/*  44 */       ((NBTBase)this.tagList.get(var2)).write(output);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void read(DataInput input, int depth, NBTSizeTracker sizeTracker) throws IOException {
/*  50 */     if (depth > 512)
/*     */     {
/*  52 */       throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */     }
/*     */ 
/*     */     
/*  56 */     sizeTracker.read(8L);
/*  57 */     this.tagType = input.readByte();
/*  58 */     int var4 = input.readInt();
/*  59 */     this.tagList = Lists.newArrayList();
/*     */     
/*  61 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  63 */       NBTBase var6 = NBTBase.createNewByType(this.tagType);
/*  64 */       var6.read(input, depth + 1, sizeTracker);
/*  65 */       this.tagList.add(var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getId() {
/*  75 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  80 */     String var1 = "[";
/*  81 */     int var2 = 0;
/*     */     
/*  83 */     for (Iterator<NBTBase> var3 = this.tagList.iterator(); var3.hasNext(); var2++) {
/*     */       
/*  85 */       NBTBase var4 = var3.next();
/*  86 */       var1 = String.valueOf(var1) + var2 + ':' + var4 + ',';
/*     */     } 
/*     */     
/*  89 */     return String.valueOf(var1) + "]";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendTag(NBTBase nbt) {
/*  98 */     if (this.tagType == 0) {
/*     */       
/* 100 */       this.tagType = nbt.getId();
/*     */     }
/* 102 */     else if (this.tagType != nbt.getId()) {
/*     */       
/* 104 */       LOGGER.warn("Adding mismatching tag types to tag list");
/*     */       
/*     */       return;
/*     */     } 
/* 108 */     this.tagList.add(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int idx, NBTBase nbt) {
/* 116 */     if (idx >= 0 && idx < this.tagList.size()) {
/*     */       
/* 118 */       if (this.tagType == 0) {
/*     */         
/* 120 */         this.tagType = nbt.getId();
/*     */       }
/* 122 */       else if (this.tagType != nbt.getId()) {
/*     */         
/* 124 */         LOGGER.warn("Adding mismatching tag types to tag list");
/*     */         
/*     */         return;
/*     */       } 
/* 128 */       this.tagList.set(idx, nbt);
/*     */     }
/*     */     else {
/*     */       
/* 132 */       LOGGER.warn("index out of bounds to set tag in tag list");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase removeTag(int i) {
/* 141 */     return this.tagList.remove(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNoTags() {
/* 149 */     return this.tagList.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getCompoundTagAt(int i) {
/* 157 */     if (i >= 0 && i < this.tagList.size()) {
/*     */       
/* 159 */       NBTBase var2 = this.tagList.get(i);
/* 160 */       return (var2.getId() == 10) ? (NBTTagCompound)var2 : new NBTTagCompound();
/*     */     } 
/*     */ 
/*     */     
/* 164 */     return new NBTTagCompound();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getIntArray(int i) {
/* 170 */     if (i >= 0 && i < this.tagList.size()) {
/*     */       
/* 172 */       NBTBase var2 = this.tagList.get(i);
/* 173 */       return (var2.getId() == 11) ? ((NBTTagIntArray)var2).getIntArray() : new int[0];
/*     */     } 
/*     */ 
/*     */     
/* 177 */     return new int[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDouble(int i) {
/* 183 */     if (i >= 0 && i < this.tagList.size()) {
/*     */       
/* 185 */       NBTBase var2 = this.tagList.get(i);
/* 186 */       return (var2.getId() == 6) ? ((NBTTagDouble)var2).getDouble() : 0.0D;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFloat(int i) {
/* 196 */     if (i >= 0 && i < this.tagList.size()) {
/*     */       
/* 198 */       NBTBase var2 = this.tagList.get(i);
/* 199 */       return (var2.getId() == 5) ? ((NBTTagFloat)var2).getFloat() : 0.0F;
/*     */     } 
/*     */ 
/*     */     
/* 203 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStringTagAt(int i) {
/* 212 */     if (i >= 0 && i < this.tagList.size()) {
/*     */       
/* 214 */       NBTBase var2 = this.tagList.get(i);
/* 215 */       return (var2.getId() == 8) ? var2.getString() : var2.toString();
/*     */     } 
/*     */ 
/*     */     
/* 219 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase get(int idx) {
/* 228 */     return (idx >= 0 && idx < this.tagList.size()) ? this.tagList.get(idx) : new NBTTagEnd();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tagCount() {
/* 236 */     return this.tagList.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase copy() {
/* 244 */     NBTTagList var1 = new NBTTagList();
/* 245 */     var1.tagType = this.tagType;
/* 246 */     Iterator<NBTBase> var2 = this.tagList.iterator();
/*     */     
/* 248 */     while (var2.hasNext()) {
/*     */       
/* 250 */       NBTBase var3 = var2.next();
/* 251 */       NBTBase var4 = var3.copy();
/* 252 */       var1.tagList.add(var4);
/*     */     } 
/*     */     
/* 255 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 260 */     if (super.equals(p_equals_1_)) {
/*     */       
/* 262 */       NBTTagList var2 = (NBTTagList)p_equals_1_;
/*     */       
/* 264 */       if (this.tagType == var2.tagType)
/*     */       {
/* 266 */         return this.tagList.equals(var2.tagList);
/*     */       }
/*     */     } 
/*     */     
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 275 */     return super.hashCode() ^ this.tagList.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTagType() {
/* 280 */     return this.tagType;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTTagList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */