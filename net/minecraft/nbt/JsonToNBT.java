/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.Stack;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class JsonToNBT
/*     */ {
/*  14 */   private static final Logger logger = LogManager.getLogger();
/*  15 */   private static final Pattern field_179273_b = Pattern.compile("\\[[-+\\d|,\\s]+\\]");
/*     */   
/*     */   private static final String __OBFID = "CL_00001232";
/*     */   
/*     */   public static NBTTagCompound func_180713_a(String p_180713_0_) throws NBTException {
/*  20 */     p_180713_0_ = p_180713_0_.trim();
/*     */     
/*  22 */     if (!p_180713_0_.startsWith("{"))
/*     */     {
/*  24 */       throw new NBTException("Invalid tag encountered, expected '{' as first char.");
/*     */     }
/*  26 */     if (func_150310_b(p_180713_0_) != 1)
/*     */     {
/*  28 */       throw new NBTException("Encountered multiple top tags, only one expected");
/*     */     }
/*     */ 
/*     */     
/*  32 */     return (NBTTagCompound)func_150316_a("tag", p_180713_0_).func_150489_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static int func_150310_b(String p_150310_0_) throws NBTException {
/*  38 */     int var1 = 0;
/*  39 */     boolean var2 = false;
/*  40 */     Stack<Character> var3 = new Stack();
/*     */     
/*  42 */     for (int var4 = 0; var4 < p_150310_0_.length(); var4++) {
/*     */       
/*  44 */       char var5 = p_150310_0_.charAt(var4);
/*     */       
/*  46 */       if (var5 == '"') {
/*     */         
/*  48 */         if (func_179271_b(p_150310_0_, var4))
/*     */         {
/*  50 */           if (!var2)
/*     */           {
/*  52 */             throw new NBTException("Illegal use of \\\": " + p_150310_0_);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/*  57 */           var2 = !var2;
/*     */         }
/*     */       
/*  60 */       } else if (!var2) {
/*     */         
/*  62 */         if (var5 != '{' && var5 != '[') {
/*     */           
/*  64 */           if (var5 == '}' && (var3.isEmpty() || ((Character)var3.pop()).charValue() != '{'))
/*     */           {
/*  66 */             throw new NBTException("Unbalanced curly brackets {}: " + p_150310_0_);
/*     */           }
/*     */           
/*  69 */           if (var5 == ']' && (var3.isEmpty() || ((Character)var3.pop()).charValue() != '['))
/*     */           {
/*  71 */             throw new NBTException("Unbalanced square brackets []: " + p_150310_0_);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/*  76 */           if (var3.isEmpty())
/*     */           {
/*  78 */             var1++;
/*     */           }
/*     */           
/*  81 */           var3.push(Character.valueOf(var5));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     if (var2)
/*     */     {
/*  88 */       throw new NBTException("Unbalanced quotation: " + p_150310_0_);
/*     */     }
/*  90 */     if (!var3.isEmpty())
/*     */     {
/*  92 */       throw new NBTException("Unbalanced brackets: " + p_150310_0_);
/*     */     }
/*     */ 
/*     */     
/*  96 */     if (var1 == 0 && !p_150310_0_.isEmpty())
/*     */     {
/*  98 */       var1 = 1;
/*     */     }
/*     */     
/* 101 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static Any func_179272_a(String... p_179272_0_) throws NBTException {
/* 107 */     return func_150316_a(p_179272_0_[0], p_179272_0_[1]);
/*     */   }
/*     */ 
/*     */   
/*     */   static Any func_150316_a(String p_150316_0_, String p_150316_1_) throws NBTException {
/* 112 */     p_150316_1_ = p_150316_1_.trim();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (p_150316_1_.startsWith("{")) {
/*     */       
/* 119 */       p_150316_1_ = p_150316_1_.substring(1, p_150316_1_.length() - 1);
/*     */       
/*     */       Compound var5;
/* 122 */       for (var5 = new Compound(p_150316_0_); p_150316_1_.length() > 0; p_150316_1_ = p_150316_1_.substring(var3.length() + 1)) {
/*     */         
/* 124 */         String var3 = func_150314_a(p_150316_1_, true);
/*     */         
/* 126 */         if (var3.length() > 0) {
/*     */           
/* 128 */           boolean var4 = false;
/* 129 */           var5.field_150491_b.add(func_179270_a(var3, var4));
/*     */         } 
/*     */         
/* 132 */         if (p_150316_1_.length() < var3.length() + 1) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 137 */         char var6 = p_150316_1_.charAt(var3.length());
/*     */         
/* 139 */         if (var6 != ',' && var6 != '{' && var6 != '}' && var6 != '[' && var6 != ']')
/*     */         {
/* 141 */           throw new NBTException("Unexpected token '" + var6 + "' at: " + p_150316_1_.substring(var3.length()));
/*     */         }
/*     */       } 
/*     */       
/* 145 */       return var5;
/*     */     } 
/* 147 */     if (p_150316_1_.startsWith("[") && !field_179273_b.matcher(p_150316_1_).matches()) {
/*     */       
/* 149 */       p_150316_1_ = p_150316_1_.substring(1, p_150316_1_.length() - 1);
/*     */       
/*     */       List var2;
/* 152 */       for (var2 = new List(p_150316_0_); p_150316_1_.length() > 0; p_150316_1_ = p_150316_1_.substring(var3.length() + 1)) {
/*     */         
/* 154 */         String var3 = func_150314_a(p_150316_1_, false);
/*     */         
/* 156 */         if (var3.length() > 0) {
/*     */           
/* 158 */           boolean var4 = true;
/* 159 */           var2.field_150492_b.add(func_179270_a(var3, var4));
/*     */         } 
/*     */         
/* 162 */         if (p_150316_1_.length() < var3.length() + 1) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 167 */         char var6 = p_150316_1_.charAt(var3.length());
/*     */         
/* 169 */         if (var6 != ',' && var6 != '{' && var6 != '}' && var6 != '[' && var6 != ']')
/*     */         {
/* 171 */           throw new NBTException("Unexpected token '" + var6 + "' at: " + p_150316_1_.substring(var3.length()));
/*     */         }
/*     */       } 
/*     */       
/* 175 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 179 */     return new Primitive(p_150316_0_, p_150316_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Any func_179270_a(String p_179270_0_, boolean p_179270_1_) throws NBTException {
/* 185 */     String var2 = func_150313_b(p_179270_0_, p_179270_1_);
/* 186 */     String var3 = func_150311_c(p_179270_0_, p_179270_1_);
/* 187 */     return func_179272_a(new String[] { var2, var3 });
/*     */   }
/*     */ 
/*     */   
/*     */   private static String func_150314_a(String p_150314_0_, boolean p_150314_1_) throws NBTException {
/* 192 */     int var2 = func_150312_a(p_150314_0_, ':');
/* 193 */     int var3 = func_150312_a(p_150314_0_, ',');
/*     */     
/* 195 */     if (p_150314_1_) {
/*     */       
/* 197 */       if (var2 == -1)
/*     */       {
/* 199 */         throw new NBTException("Unable to locate name/value separator for string: " + p_150314_0_);
/*     */       }
/*     */       
/* 202 */       if (var3 != -1 && var3 < var2)
/*     */       {
/* 204 */         throw new NBTException("Name error at: " + p_150314_0_);
/*     */       }
/*     */     }
/* 207 */     else if (var2 == -1 || var2 > var3) {
/*     */       
/* 209 */       var2 = -1;
/*     */     } 
/*     */     
/* 212 */     return func_179269_a(p_150314_0_, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String func_179269_a(String p_179269_0_, int p_179269_1_) throws NBTException {
/* 217 */     Stack<Character> var2 = new Stack();
/* 218 */     int var3 = p_179269_1_ + 1;
/* 219 */     boolean var4 = false;
/* 220 */     boolean var5 = false;
/* 221 */     boolean var6 = false;
/*     */     
/* 223 */     for (int var7 = 0; var3 < p_179269_0_.length(); var3++) {
/*     */       
/* 225 */       char var8 = p_179269_0_.charAt(var3);
/*     */       
/* 227 */       if (var8 == '"') {
/*     */         
/* 229 */         if (func_179271_b(p_179269_0_, var3))
/*     */         {
/* 231 */           if (!var4)
/*     */           {
/* 233 */             throw new NBTException("Illegal use of \\\": " + p_179269_0_);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 238 */           var4 = !var4;
/*     */           
/* 240 */           if (var4 && !var6)
/*     */           {
/* 242 */             var5 = true;
/*     */           }
/*     */           
/* 245 */           if (!var4)
/*     */           {
/* 247 */             var7 = var3;
/*     */           }
/*     */         }
/*     */       
/* 251 */       } else if (!var4) {
/*     */         
/* 253 */         if (var8 != '{' && var8 != '[') {
/*     */           
/* 255 */           if (var8 == '}' && (var2.isEmpty() || ((Character)var2.pop()).charValue() != '{'))
/*     */           {
/* 257 */             throw new NBTException("Unbalanced curly brackets {}: " + p_179269_0_);
/*     */           }
/*     */           
/* 260 */           if (var8 == ']' && (var2.isEmpty() || ((Character)var2.pop()).charValue() != '['))
/*     */           {
/* 262 */             throw new NBTException("Unbalanced square brackets []: " + p_179269_0_);
/*     */           }
/*     */           
/* 265 */           if (var8 == ',' && var2.isEmpty())
/*     */           {
/* 267 */             return p_179269_0_.substring(0, var3);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 272 */           var2.push(Character.valueOf(var8));
/*     */         } 
/*     */       } 
/*     */       
/* 276 */       if (!Character.isWhitespace(var8)) {
/*     */         
/* 278 */         if (!var4 && var5 && var7 != var3)
/*     */         {
/* 280 */           return p_179269_0_.substring(0, var7 + 1);
/*     */         }
/*     */         
/* 283 */         var6 = true;
/*     */       } 
/*     */     } 
/*     */     
/* 287 */     return p_179269_0_.substring(0, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String func_150313_b(String p_150313_0_, boolean p_150313_1_) throws NBTException {
/* 292 */     if (p_150313_1_) {
/*     */       
/* 294 */       p_150313_0_ = p_150313_0_.trim();
/*     */       
/* 296 */       if (p_150313_0_.startsWith("{") || p_150313_0_.startsWith("["))
/*     */       {
/* 298 */         return "";
/*     */       }
/*     */     } 
/*     */     
/* 302 */     int var2 = func_150312_a(p_150313_0_, ':');
/*     */     
/* 304 */     if (var2 == -1) {
/*     */       
/* 306 */       if (p_150313_1_)
/*     */       {
/* 308 */         return "";
/*     */       }
/*     */ 
/*     */       
/* 312 */       throw new NBTException("Unable to locate name/value separator for string: " + p_150313_0_);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 317 */     return p_150313_0_.substring(0, var2).trim();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String func_150311_c(String p_150311_0_, boolean p_150311_1_) throws NBTException {
/* 323 */     if (p_150311_1_) {
/*     */       
/* 325 */       p_150311_0_ = p_150311_0_.trim();
/*     */       
/* 327 */       if (p_150311_0_.startsWith("{") || p_150311_0_.startsWith("["))
/*     */       {
/* 329 */         return p_150311_0_;
/*     */       }
/*     */     } 
/*     */     
/* 333 */     int var2 = func_150312_a(p_150311_0_, ':');
/*     */     
/* 335 */     if (var2 == -1) {
/*     */       
/* 337 */       if (p_150311_1_)
/*     */       {
/* 339 */         return p_150311_0_;
/*     */       }
/*     */ 
/*     */       
/* 343 */       throw new NBTException("Unable to locate name/value separator for string: " + p_150311_0_);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 348 */     return p_150311_0_.substring(var2 + 1).trim();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int func_150312_a(String p_150312_0_, char p_150312_1_) {
/* 354 */     int var2 = 0;
/*     */     
/* 356 */     for (boolean var3 = true; var2 < p_150312_0_.length(); var2++) {
/*     */       
/* 358 */       char var4 = p_150312_0_.charAt(var2);
/*     */       
/* 360 */       if (var4 == '"') {
/*     */         
/* 362 */         if (!func_179271_b(p_150312_0_, var2))
/*     */         {
/* 364 */           var3 = !var3;
/*     */         }
/*     */       }
/* 367 */       else if (var3) {
/*     */         
/* 369 */         if (var4 == p_150312_1_)
/*     */         {
/* 371 */           return var2;
/*     */         }
/*     */         
/* 374 */         if (var4 == '{' || var4 == '[')
/*     */         {
/* 376 */           return -1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 381 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean func_179271_b(String p_179271_0_, int p_179271_1_) {
/* 386 */     return (p_179271_1_ > 0 && p_179271_0_.charAt(p_179271_1_ - 1) == '\\' && !func_179271_b(p_179271_0_, p_179271_1_ - 1));
/*     */   }
/*     */   
/*     */   static abstract class Any
/*     */   {
/*     */     protected String field_150490_a;
/*     */     private static final String __OBFID = "CL_00001233";
/*     */     
/*     */     public abstract NBTBase func_150489_a();
/*     */   }
/*     */   
/*     */   static class Compound
/*     */     extends Any {
/* 399 */     protected java.util.List field_150491_b = Lists.newArrayList();
/*     */     
/*     */     private static final String __OBFID = "CL_00001234";
/*     */     
/*     */     public Compound(String p_i45137_1_) {
/* 404 */       this.field_150490_a = p_i45137_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase func_150489_a() {
/* 409 */       NBTTagCompound var1 = new NBTTagCompound();
/* 410 */       Iterator<JsonToNBT.Any> var2 = this.field_150491_b.iterator();
/*     */       
/* 412 */       while (var2.hasNext()) {
/*     */         
/* 414 */         JsonToNBT.Any var3 = var2.next();
/* 415 */         var1.setTag(var3.field_150490_a, var3.func_150489_a());
/*     */       } 
/*     */       
/* 418 */       return var1;
/*     */     }
/*     */   }
/*     */   
/*     */   static class List
/*     */     extends Any {
/* 424 */     protected java.util.List field_150492_b = Lists.newArrayList();
/*     */     
/*     */     private static final String __OBFID = "CL_00001235";
/*     */     
/*     */     public List(String p_i45138_1_) {
/* 429 */       this.field_150490_a = p_i45138_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase func_150489_a() {
/* 434 */       NBTTagList var1 = new NBTTagList();
/* 435 */       Iterator<JsonToNBT.Any> var2 = this.field_150492_b.iterator();
/*     */       
/* 437 */       while (var2.hasNext()) {
/*     */         
/* 439 */         JsonToNBT.Any var3 = var2.next();
/* 440 */         var1.appendTag(var3.func_150489_a());
/*     */       } 
/*     */       
/* 443 */       return var1;
/*     */     }
/*     */   }
/*     */   
/*     */   static class Primitive
/*     */     extends Any {
/* 449 */     private static final Pattern field_179265_c = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");
/* 450 */     private static final Pattern field_179263_d = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
/* 451 */     private static final Pattern field_179264_e = Pattern.compile("[-+]?[0-9]+[b|B]");
/* 452 */     private static final Pattern field_179261_f = Pattern.compile("[-+]?[0-9]+[l|L]");
/* 453 */     private static final Pattern field_179262_g = Pattern.compile("[-+]?[0-9]+[s|S]");
/* 454 */     private static final Pattern field_179267_h = Pattern.compile("[-+]?[0-9]+");
/* 455 */     private static final Pattern field_179268_i = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
/* 456 */     private static final Splitter field_179266_j = Splitter.on(',').omitEmptyStrings();
/*     */     
/*     */     protected String field_150493_b;
/*     */     private static final String __OBFID = "CL_00001236";
/*     */     
/*     */     public Primitive(String p_i45139_1_, String p_i45139_2_) {
/* 462 */       this.field_150490_a = p_i45139_1_;
/* 463 */       this.field_150493_b = p_i45139_2_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public NBTBase func_150489_a() {
/*     */       try {
/* 470 */         if (field_179265_c.matcher(this.field_150493_b).matches())
/*     */         {
/* 472 */           return new NBTTagDouble(Double.parseDouble(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
/*     */         }
/*     */         
/* 475 */         if (field_179263_d.matcher(this.field_150493_b).matches())
/*     */         {
/* 477 */           return new NBTTagFloat(Float.parseFloat(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
/*     */         }
/*     */         
/* 480 */         if (field_179264_e.matcher(this.field_150493_b).matches())
/*     */         {
/* 482 */           return new NBTTagByte(Byte.parseByte(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
/*     */         }
/*     */         
/* 485 */         if (field_179261_f.matcher(this.field_150493_b).matches())
/*     */         {
/* 487 */           return new NBTTagLong(Long.parseLong(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
/*     */         }
/*     */         
/* 490 */         if (field_179262_g.matcher(this.field_150493_b).matches())
/*     */         {
/* 492 */           return new NBTTagShort(Short.parseShort(this.field_150493_b.substring(0, this.field_150493_b.length() - 1)));
/*     */         }
/*     */         
/* 495 */         if (field_179267_h.matcher(this.field_150493_b).matches())
/*     */         {
/* 497 */           return new NBTTagInt(Integer.parseInt(this.field_150493_b));
/*     */         }
/*     */         
/* 500 */         if (field_179268_i.matcher(this.field_150493_b).matches())
/*     */         {
/* 502 */           return new NBTTagDouble(Double.parseDouble(this.field_150493_b));
/*     */         }
/*     */         
/* 505 */         if (this.field_150493_b.equalsIgnoreCase("true") || this.field_150493_b.equalsIgnoreCase("false"))
/*     */         {
/* 507 */           return new NBTTagByte((byte)(Boolean.parseBoolean(this.field_150493_b) ? 1 : 0));
/*     */         }
/*     */       }
/* 510 */       catch (NumberFormatException var6) {
/*     */         
/* 512 */         this.field_150493_b = this.field_150493_b.replaceAll("\\\\\"", "\"");
/* 513 */         return new NBTTagString(this.field_150493_b);
/*     */       } 
/*     */       
/* 516 */       if (this.field_150493_b.startsWith("[") && this.field_150493_b.endsWith("]")) {
/*     */         
/* 518 */         String var7 = this.field_150493_b.substring(1, this.field_150493_b.length() - 1);
/* 519 */         String[] var8 = (String[])Iterables.toArray(field_179266_j.split(var7), String.class);
/*     */ 
/*     */         
/*     */         try {
/* 523 */           int[] var3 = new int[var8.length];
/*     */           
/* 525 */           for (int var4 = 0; var4 < var8.length; var4++)
/*     */           {
/* 527 */             var3[var4] = Integer.parseInt(var8[var4].trim());
/*     */           }
/*     */           
/* 530 */           return new NBTTagIntArray(var3);
/*     */         }
/* 532 */         catch (NumberFormatException var5) {
/*     */           
/* 534 */           return new NBTTagString(this.field_150493_b);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 539 */       if (this.field_150493_b.startsWith("\"") && this.field_150493_b.endsWith("\""))
/*     */       {
/* 541 */         this.field_150493_b = this.field_150493_b.substring(1, this.field_150493_b.length() - 1);
/*     */       }
/*     */       
/* 544 */       this.field_150493_b = this.field_150493_b.replaceAll("\\\\\"", "\"");
/* 545 */       StringBuilder var1 = new StringBuilder();
/*     */       
/* 547 */       for (int var2 = 0; var2 < this.field_150493_b.length(); var2++) {
/*     */         
/* 549 */         if (var2 < this.field_150493_b.length() - 1 && this.field_150493_b.charAt(var2) == '\\' && this.field_150493_b.charAt(var2 + 1) == '\\') {
/*     */           
/* 551 */           var1.append('\\');
/* 552 */           var2++;
/*     */         }
/*     */         else {
/*     */           
/* 556 */           var1.append(this.field_150493_b.charAt(var2));
/*     */         } 
/*     */       } 
/*     */       
/* 560 */       return new NBTTagString(var1.toString());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\JsonToNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */