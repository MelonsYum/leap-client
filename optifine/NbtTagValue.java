/*     */ package optifine;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagByte;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagDouble;
/*     */ import net.minecraft.nbt.NBTTagFloat;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.nbt.NBTTagShort;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import org.apache.commons.lang3.StringEscapeUtils;
/*     */ 
/*     */ public class NbtTagValue
/*     */ {
/*  20 */   private String[] parents = null;
/*  21 */   private String name = null;
/*  22 */   private int type = 0;
/*  23 */   private String value = null;
/*     */   
/*     */   private static final int TYPE_TEXT = 0;
/*     */   private static final int TYPE_PATTERN = 1;
/*     */   private static final int TYPE_IPATTERN = 2;
/*     */   private static final int TYPE_REGEX = 3;
/*     */   private static final int TYPE_IREGEX = 4;
/*     */   private static final String PREFIX_PATTERN = "pattern:";
/*     */   private static final String PREFIX_IPATTERN = "ipattern:";
/*     */   private static final String PREFIX_REGEX = "regex:";
/*     */   private static final String PREFIX_IREGEX = "iregex:";
/*     */   
/*     */   public NbtTagValue(String tag, String value) {
/*  36 */     String[] names = Config.tokenize(tag, ".");
/*  37 */     this.parents = Arrays.<String>copyOfRange(names, 0, names.length - 1);
/*  38 */     this.name = names[names.length - 1];
/*     */     
/*  40 */     if (value.startsWith("pattern:")) {
/*     */       
/*  42 */       this.type = 1;
/*  43 */       value = value.substring("pattern:".length());
/*     */     }
/*  45 */     else if (value.startsWith("ipattern:")) {
/*     */       
/*  47 */       this.type = 2;
/*  48 */       value = value.substring("ipattern:".length()).toLowerCase();
/*     */     }
/*  50 */     else if (value.startsWith("regex:")) {
/*     */       
/*  52 */       this.type = 3;
/*  53 */       value = value.substring("regex:".length());
/*     */     }
/*  55 */     else if (value.startsWith("iregex:")) {
/*     */       
/*  57 */       this.type = 4;
/*  58 */       value = value.substring("iregex:".length()).toLowerCase();
/*     */     }
/*     */     else {
/*     */       
/*  62 */       this.type = 0;
/*     */     } 
/*     */     
/*  65 */     value = StringEscapeUtils.unescapeJava(value);
/*  66 */     this.value = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matches(NBTTagCompound nbt) {
/*  71 */     if (nbt == null)
/*     */     {
/*  73 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  77 */     Object tagBase = nbt;
/*     */     
/*  79 */     for (int i = 0; i < this.parents.length; i++) {
/*     */       
/*  81 */       String tag = this.parents[i];
/*  82 */       tagBase = getChildTag((NBTBase)tagBase, tag);
/*     */       
/*  84 */       if (tagBase == null)
/*     */       {
/*  86 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  90 */     if (this.name.equals("*"))
/*     */     {
/*  92 */       return matchesAnyChild((NBTBase)tagBase);
/*     */     }
/*     */ 
/*     */     
/*  96 */     NBTBase var5 = getChildTag((NBTBase)tagBase, this.name);
/*     */     
/*  98 */     if (var5 == null)
/*     */     {
/* 100 */       return false;
/*     */     }
/* 102 */     if (matches(var5))
/*     */     {
/* 104 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean matchesAnyChild(NBTBase tagBase) {
/* 116 */     if (tagBase instanceof NBTTagCompound) {
/*     */       
/* 118 */       NBTTagCompound tagList = (NBTTagCompound)tagBase;
/* 119 */       Set count = tagList.getKeySet();
/* 120 */       Iterator<String> i = count.iterator();
/*     */       
/* 122 */       while (i.hasNext()) {
/*     */         
/* 124 */         String nbtBase = i.next();
/* 125 */         NBTBase nbtBase1 = tagList.getTag(nbtBase);
/*     */         
/* 127 */         if (matches(nbtBase1))
/*     */         {
/* 129 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     if (tagBase instanceof NBTTagList) {
/*     */       
/* 136 */       NBTTagList var7 = (NBTTagList)tagBase;
/* 137 */       int var8 = var7.tagCount();
/*     */       
/* 139 */       for (int var9 = 0; var9 < var8; var9++) {
/*     */         
/* 141 */         NBTBase var10 = var7.get(var9);
/*     */         
/* 143 */         if (matches(var10))
/*     */         {
/* 145 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static NBTBase getChildTag(NBTBase tagBase, String tag) {
/* 155 */     if (tagBase instanceof NBTTagCompound) {
/*     */       
/* 157 */       NBTTagCompound tagList1 = (NBTTagCompound)tagBase;
/* 158 */       return tagList1.getTag(tag);
/*     */     } 
/* 160 */     if (tagBase instanceof NBTTagList) {
/*     */       
/* 162 */       NBTTagList tagList = (NBTTagList)tagBase;
/* 163 */       int index = Config.parseInt(tag, -1);
/* 164 */       return (index < 0) ? null : tagList.get(index);
/*     */     } 
/*     */ 
/*     */     
/* 168 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean matches(NBTBase nbtBase) {
/* 174 */     if (nbtBase == null)
/*     */     {
/* 176 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 180 */     String nbtValue = getValue(nbtBase);
/*     */     
/* 182 */     if (nbtValue == null)
/*     */     {
/* 184 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 188 */     switch (this.type) {
/*     */       
/*     */       case 0:
/* 191 */         return nbtValue.equals(this.value);
/*     */       
/*     */       case 1:
/* 194 */         return matchesPattern(nbtValue, this.value);
/*     */       
/*     */       case 2:
/* 197 */         return matchesPattern(nbtValue.toLowerCase(), this.value);
/*     */       
/*     */       case 3:
/* 200 */         return matchesRegex(nbtValue, this.value);
/*     */       
/*     */       case 4:
/* 203 */         return matchesRegex(nbtValue.toLowerCase(), this.value);
/*     */     } 
/*     */     
/* 206 */     throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean matchesPattern(String str, String pattern) {
/* 214 */     return StrUtils.equalsMask(str, pattern, '*', '?');
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean matchesRegex(String str, String regex) {
/* 219 */     return str.matches(regex);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getValue(NBTBase nbtBase) {
/* 224 */     if (nbtBase == null)
/*     */     {
/* 226 */       return null;
/*     */     }
/* 228 */     if (nbtBase instanceof NBTTagString) {
/*     */       
/* 230 */       NBTTagString d6 = (NBTTagString)nbtBase;
/* 231 */       return d6.getString();
/*     */     } 
/* 233 */     if (nbtBase instanceof NBTTagInt) {
/*     */       
/* 235 */       NBTTagInt d5 = (NBTTagInt)nbtBase;
/* 236 */       return Integer.toString(d5.getInt());
/*     */     } 
/* 238 */     if (nbtBase instanceof NBTTagByte) {
/*     */       
/* 240 */       NBTTagByte d4 = (NBTTagByte)nbtBase;
/* 241 */       return Byte.toString(d4.getByte());
/*     */     } 
/* 243 */     if (nbtBase instanceof NBTTagShort) {
/*     */       
/* 245 */       NBTTagShort d3 = (NBTTagShort)nbtBase;
/* 246 */       return Short.toString(d3.getShort());
/*     */     } 
/* 248 */     if (nbtBase instanceof NBTTagLong) {
/*     */       
/* 250 */       NBTTagLong d2 = (NBTTagLong)nbtBase;
/* 251 */       return Long.toString(d2.getLong());
/*     */     } 
/* 253 */     if (nbtBase instanceof NBTTagFloat) {
/*     */       
/* 255 */       NBTTagFloat d1 = (NBTTagFloat)nbtBase;
/* 256 */       return Float.toString(d1.getFloat());
/*     */     } 
/* 258 */     if (nbtBase instanceof NBTTagDouble) {
/*     */       
/* 260 */       NBTTagDouble d = (NBTTagDouble)nbtBase;
/* 261 */       return Double.toString(d.getDouble());
/*     */     } 
/*     */ 
/*     */     
/* 265 */     return nbtBase.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 271 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 273 */     for (int i = 0; i < this.parents.length; i++) {
/*     */       
/* 275 */       String parent = this.parents[i];
/*     */       
/* 277 */       if (i > 0)
/*     */       {
/* 279 */         sb.append(".");
/*     */       }
/*     */       
/* 282 */       sb.append(parent);
/*     */     } 
/*     */     
/* 285 */     if (sb.length() > 0)
/*     */     {
/* 287 */       sb.append(".");
/*     */     }
/*     */     
/* 290 */     sb.append(this.name);
/* 291 */     sb.append(" = ");
/* 292 */     sb.append(this.value);
/* 293 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\NbtTagValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */