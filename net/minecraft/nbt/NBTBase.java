/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public abstract class NBTBase
/*     */ {
/*   9 */   public static final String[] NBT_TYPES = new String[] { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]" };
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001229";
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void write(DataOutput paramDataOutput) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   abstract void read(DataInput paramDataInput, int paramInt, NBTSizeTracker paramNBTSizeTracker) throws IOException;
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String toString();
/*     */ 
/*     */   
/*     */   public abstract byte getId();
/*     */ 
/*     */   
/*     */   protected static NBTBase createNewByType(byte id) {
/*  31 */     switch (id) {
/*     */       
/*     */       case 0:
/*  34 */         return new NBTTagEnd();
/*     */       
/*     */       case 1:
/*  37 */         return new NBTTagByte();
/*     */       
/*     */       case 2:
/*  40 */         return new NBTTagShort();
/*     */       
/*     */       case 3:
/*  43 */         return new NBTTagInt();
/*     */       
/*     */       case 4:
/*  46 */         return new NBTTagLong();
/*     */       
/*     */       case 5:
/*  49 */         return new NBTTagFloat();
/*     */       
/*     */       case 6:
/*  52 */         return new NBTTagDouble();
/*     */       
/*     */       case 7:
/*  55 */         return new NBTTagByteArray();
/*     */       
/*     */       case 8:
/*  58 */         return new NBTTagString();
/*     */       
/*     */       case 9:
/*  61 */         return new NBTTagList();
/*     */       
/*     */       case 10:
/*  64 */         return new NBTTagCompound();
/*     */       
/*     */       case 11:
/*  67 */         return new NBTTagIntArray();
/*     */     } 
/*     */     
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract NBTBase copy();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNoTags() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  89 */     if (!(p_equals_1_ instanceof NBTBase))
/*     */     {
/*  91 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  95 */     NBTBase var2 = (NBTBase)p_equals_1_;
/*  96 */     return (getId() == var2.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 102 */     return getId();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getString() {
/* 107 */     return toString();
/*     */   }
/*     */   
/*     */   public static abstract class NBTPrimitive extends NBTBase {
/*     */     private static final String __OBFID = "CL_00001230";
/*     */     
/*     */     public abstract long getLong();
/*     */     
/*     */     public abstract int getInt();
/*     */     
/*     */     public abstract short getShort();
/*     */     
/*     */     public abstract byte getByte();
/*     */     
/*     */     public abstract double getDouble();
/*     */     
/*     */     public abstract float getFloat();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\NBTBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */