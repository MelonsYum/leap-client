/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class ChatComponentTranslation
/*     */   extends ChatComponentStyle {
/*     */   private final String key;
/*     */   private final Object[] formatArgs;
/*  16 */   private final Object syncLock = new Object();
/*  17 */   private long lastTranslationUpdateTimeInMilliseconds = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  24 */   List children = Lists.newArrayList();
/*  25 */   public static final Pattern stringVariablePattern = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");
/*     */   
/*     */   private static final String __OBFID = "CL_00001270";
/*     */   
/*     */   public ChatComponentTranslation(String translationKey, Object... args) {
/*  30 */     this.key = translationKey;
/*  31 */     this.formatArgs = args;
/*  32 */     Object[] var3 = args;
/*  33 */     int var4 = args.length;
/*     */     
/*  35 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  37 */       Object var6 = var3[var5];
/*     */       
/*  39 */       if (var6 instanceof IChatComponent)
/*     */       {
/*  41 */         ((IChatComponent)var6).getChatStyle().setParentStyle(getChatStyle());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   synchronized void ensureInitialized() {
/*  51 */     Object var1 = this.syncLock;
/*     */     
/*  53 */     synchronized (this.syncLock) {
/*     */       
/*  55 */       long var2 = StatCollector.getLastTranslationUpdateTimeInMilliseconds();
/*     */       
/*  57 */       if (var2 == this.lastTranslationUpdateTimeInMilliseconds) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  62 */       this.lastTranslationUpdateTimeInMilliseconds = var2;
/*  63 */       this.children.clear();
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/*  68 */       initializeFromFormat(StatCollector.translateToLocal(this.key));
/*     */     }
/*  70 */     catch (ChatComponentTranslationFormatException var6) {
/*     */       
/*  72 */       this.children.clear();
/*     */ 
/*     */       
/*     */       try {
/*  76 */         initializeFromFormat(StatCollector.translateToFallback(this.key));
/*     */       }
/*  78 */       catch (ChatComponentTranslationFormatException var5) {
/*     */         
/*  80 */         throw var6;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeFromFormat(String format) {
/*  90 */     boolean var2 = false;
/*  91 */     Matcher var3 = stringVariablePattern.matcher(format);
/*  92 */     int var4 = 0;
/*  93 */     int var5 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  99 */       for (; var3.find(var5); var5 = var7) {
/*     */         
/* 101 */         int var6 = var3.start();
/* 102 */         int var7 = var3.end();
/*     */         
/* 104 */         if (var6 > var5) {
/*     */           
/* 106 */           ChatComponentText var8 = new ChatComponentText(String.format(format.substring(var5, var6), new Object[0]));
/* 107 */           var8.getChatStyle().setParentStyle(getChatStyle());
/* 108 */           this.children.add(var8);
/*     */         } 
/*     */         
/* 111 */         String var14 = var3.group(2);
/* 112 */         String var9 = format.substring(var6, var7);
/*     */         
/* 114 */         if ("%".equals(var14) && "%%".equals(var9)) {
/*     */           
/* 116 */           ChatComponentText var15 = new ChatComponentText("%");
/* 117 */           var15.getChatStyle().setParentStyle(getChatStyle());
/* 118 */           this.children.add(var15);
/*     */         }
/*     */         else {
/*     */           
/* 122 */           if (!"s".equals(var14))
/*     */           {
/* 124 */             throw new ChatComponentTranslationFormatException(this, "Unsupported format: '" + var9 + "'");
/*     */           }
/*     */           
/* 127 */           String var10 = var3.group(1);
/* 128 */           int var11 = (var10 != null) ? (Integer.parseInt(var10) - 1) : var4++;
/*     */           
/* 130 */           if (var11 < this.formatArgs.length)
/*     */           {
/* 132 */             this.children.add(getFormatArgumentAsComponent(var11));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 137 */       if (var5 < format.length())
/*     */       {
/* 139 */         ChatComponentText var13 = new ChatComponentText(String.format(format.substring(var5), new Object[0]));
/* 140 */         var13.getChatStyle().setParentStyle(getChatStyle());
/* 141 */         this.children.add(var13);
/*     */       }
/*     */     
/* 144 */     } catch (IllegalFormatException var12) {
/*     */       
/* 146 */       throw new ChatComponentTranslationFormatException(this, var12);
/*     */     } 
/*     */   }
/*     */   
/*     */   private IChatComponent getFormatArgumentAsComponent(int index) {
/*     */     Object var3;
/* 152 */     if (index >= this.formatArgs.length)
/*     */     {
/* 154 */       throw new ChatComponentTranslationFormatException(this, index);
/*     */     }
/*     */ 
/*     */     
/* 158 */     Object var2 = this.formatArgs[index];
/*     */ 
/*     */     
/* 161 */     if (var2 instanceof IChatComponent) {
/*     */       
/* 163 */       var3 = var2;
/*     */     }
/*     */     else {
/*     */       
/* 167 */       var3 = new ChatComponentText((var2 == null) ? "null" : var2.toString());
/* 168 */       ((IChatComponent)var3).getChatStyle().setParentStyle(getChatStyle());
/*     */     } 
/*     */     
/* 171 */     return (IChatComponent)var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent setChatStyle(ChatStyle style) {
/* 177 */     super.setChatStyle(style);
/* 178 */     Object[] var2 = this.formatArgs;
/* 179 */     int var3 = var2.length;
/*     */     
/* 181 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 183 */       Object var5 = var2[var4];
/*     */       
/* 185 */       if (var5 instanceof IChatComponent)
/*     */       {
/* 187 */         ((IChatComponent)var5).getChatStyle().setParentStyle(getChatStyle());
/*     */       }
/*     */     } 
/*     */     
/* 191 */     if (this.lastTranslationUpdateTimeInMilliseconds > -1L) {
/*     */       
/* 193 */       Iterator<IChatComponent> var6 = this.children.iterator();
/*     */       
/* 195 */       while (var6.hasNext()) {
/*     */         
/* 197 */         IChatComponent var7 = var6.next();
/* 198 */         var7.getChatStyle().setParentStyle(style);
/*     */       } 
/*     */     } 
/*     */     
/* 202 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 207 */     ensureInitialized();
/* 208 */     return Iterators.concat(createDeepCopyIterator(this.children), createDeepCopyIterator(this.siblings));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnformattedTextForChat() {
/* 217 */     ensureInitialized();
/* 218 */     StringBuilder var1 = new StringBuilder();
/* 219 */     Iterator<IChatComponent> var2 = this.children.iterator();
/*     */     
/* 221 */     while (var2.hasNext()) {
/*     */       
/* 223 */       IChatComponent var3 = var2.next();
/* 224 */       var1.append(var3.getUnformattedTextForChat());
/*     */     } 
/*     */     
/* 227 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatComponentTranslation createCopy() {
/* 235 */     Object[] var1 = new Object[this.formatArgs.length];
/*     */     
/* 237 */     for (int var2 = 0; var2 < this.formatArgs.length; var2++) {
/*     */       
/* 239 */       if (this.formatArgs[var2] instanceof IChatComponent) {
/*     */         
/* 241 */         var1[var2] = ((IChatComponent)this.formatArgs[var2]).createCopy();
/*     */       }
/*     */       else {
/*     */         
/* 245 */         var1[var2] = this.formatArgs[var2];
/*     */       } 
/*     */     } 
/*     */     
/* 249 */     ChatComponentTranslation var5 = new ChatComponentTranslation(this.key, var1);
/* 250 */     var5.setChatStyle(getChatStyle().createShallowCopy());
/* 251 */     Iterator<IChatComponent> var3 = getSiblings().iterator();
/*     */     
/* 253 */     while (var3.hasNext()) {
/*     */       
/* 255 */       IChatComponent var4 = var3.next();
/* 256 */       var5.appendSibling(var4.createCopy());
/*     */     } 
/*     */     
/* 259 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 264 */     if (this == p_equals_1_)
/*     */     {
/* 266 */       return true;
/*     */     }
/* 268 */     if (!(p_equals_1_ instanceof ChatComponentTranslation))
/*     */     {
/* 270 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 274 */     ChatComponentTranslation var2 = (ChatComponentTranslation)p_equals_1_;
/* 275 */     return (Arrays.equals(this.formatArgs, var2.formatArgs) && this.key.equals(var2.key) && super.equals(p_equals_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 281 */     int var1 = super.hashCode();
/* 282 */     var1 = 31 * var1 + this.key.hashCode();
/* 283 */     var1 = 31 * var1 + Arrays.hashCode(this.formatArgs);
/* 284 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 289 */     return "TranslatableComponent{key='" + this.key + '\'' + ", args=" + Arrays.toString(this.formatArgs) + ", siblings=" + this.siblings + ", style=" + getChatStyle() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKey() {
/* 294 */     return this.key;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] getFormatArgs() {
/* 299 */     return this.formatArgs;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentTranslation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */