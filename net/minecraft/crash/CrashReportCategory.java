/*     */ package net.minecraft.crash;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class CrashReportCategory
/*     */ {
/*     */   private final CrashReport theCrashReport;
/*     */   private final String name;
/*  15 */   private final List children = Lists.newArrayList();
/*  16 */   private StackTraceElement[] stackTrace = new StackTraceElement[0];
/*     */   
/*     */   private static final String __OBFID = "CL_00001409";
/*     */   
/*     */   public CrashReportCategory(CrashReport p_i1353_1_, String name) {
/*  21 */     this.theCrashReport = p_i1353_1_;
/*  22 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCoordinateInfo(double x, double y, double z) {
/*  27 */     return String.format("%.2f,%.2f,%.2f - %s", new Object[] { Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), getCoordinateInfo(new BlockPos(x, y, z)) });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCoordinateInfo(BlockPos pos) {
/*  32 */     int var1 = pos.getX();
/*  33 */     int var2 = pos.getY();
/*  34 */     int var3 = pos.getZ();
/*  35 */     StringBuilder var4 = new StringBuilder();
/*     */ 
/*     */     
/*     */     try {
/*  39 */       var4.append(String.format("World: (%d,%d,%d)", new Object[] { Integer.valueOf(var1), Integer.valueOf(var2), Integer.valueOf(var3) }));
/*     */     }
/*  41 */     catch (Throwable var17) {
/*     */       
/*  43 */       var4.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  46 */     var4.append(", ");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  59 */       int var5 = var1 >> 4;
/*  60 */       int var6 = var3 >> 4;
/*  61 */       int var7 = var1 & 0xF;
/*  62 */       int var8 = var2 >> 4;
/*  63 */       int var9 = var3 & 0xF;
/*  64 */       int var10 = var5 << 4;
/*  65 */       int var11 = var6 << 4;
/*  66 */       int var12 = (var5 + 1 << 4) - 1;
/*  67 */       int var13 = (var6 + 1 << 4) - 1;
/*  68 */       var4.append(String.format("Chunk: (at %d,%d,%d in %d,%d; contains blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(var7), Integer.valueOf(var8), Integer.valueOf(var9), Integer.valueOf(var5), Integer.valueOf(var6), Integer.valueOf(var10), Integer.valueOf(var11), Integer.valueOf(var12), Integer.valueOf(var13) }));
/*     */     }
/*  70 */     catch (Throwable var16) {
/*     */       
/*  72 */       var4.append("(Error finding chunk loc)");
/*     */     } 
/*     */     
/*  75 */     var4.append(", ");
/*     */ 
/*     */     
/*     */     try {
/*  79 */       int var5 = var1 >> 9;
/*  80 */       int var6 = var3 >> 9;
/*  81 */       int var7 = var5 << 5;
/*  82 */       int var8 = var6 << 5;
/*  83 */       int var9 = (var5 + 1 << 5) - 1;
/*  84 */       int var10 = (var6 + 1 << 5) - 1;
/*  85 */       int var11 = var5 << 9;
/*  86 */       int var12 = var6 << 9;
/*  87 */       int var13 = (var5 + 1 << 9) - 1;
/*  88 */       int var14 = (var6 + 1 << 9) - 1;
/*  89 */       var4.append(String.format("Region: (%d,%d; contains chunks %d,%d to %d,%d, blocks %d,0,%d to %d,255,%d)", new Object[] { Integer.valueOf(var5), Integer.valueOf(var6), Integer.valueOf(var7), Integer.valueOf(var8), Integer.valueOf(var9), Integer.valueOf(var10), Integer.valueOf(var11), Integer.valueOf(var12), Integer.valueOf(var13), Integer.valueOf(var14) }));
/*     */     }
/*  91 */     catch (Throwable var15) {
/*     */       
/*  93 */       var4.append("(Error finding world loc)");
/*     */     } 
/*     */     
/*  96 */     return var4.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCrashSectionCallable(String sectionName, Callable callable) {
/*     */     try {
/* 106 */       addCrashSection(sectionName, callable.call());
/*     */     }
/* 108 */     catch (Throwable var4) {
/*     */       
/* 110 */       addCrashSectionThrowable(sectionName, var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCrashSection(String sectionName, Object value) {
/* 119 */     this.children.add(new Entry(sectionName, value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCrashSectionThrowable(String sectionName, Throwable throwable) {
/* 127 */     addCrashSection(sectionName, throwable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPrunedStackTrace(int size) {
/* 136 */     StackTraceElement[] var2 = Thread.currentThread().getStackTrace();
/*     */     
/* 138 */     if (var2.length <= 0)
/*     */     {
/* 140 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 144 */     this.stackTrace = new StackTraceElement[var2.length - 3 - size];
/* 145 */     System.arraycopy(var2, 3 + size, this.stackTrace, 0, this.stackTrace.length);
/* 146 */     return this.stackTrace.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean firstTwoElementsOfStackTraceMatch(StackTraceElement s1, StackTraceElement s2) {
/* 155 */     if (this.stackTrace.length != 0 && s1 != null) {
/*     */       
/* 157 */       StackTraceElement var3 = this.stackTrace[0];
/*     */       
/* 159 */       if (var3.isNativeMethod() == s1.isNativeMethod() && var3.getClassName().equals(s1.getClassName()) && var3.getFileName().equals(s1.getFileName()) && var3.getMethodName().equals(s1.getMethodName())) {
/*     */         
/* 161 */         if (((s2 != null) ? true : false) != ((this.stackTrace.length > 1) ? true : false))
/*     */         {
/* 163 */           return false;
/*     */         }
/* 165 */         if (s2 != null && !this.stackTrace[1].equals(s2))
/*     */         {
/* 167 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 171 */         this.stackTrace[0] = s1;
/* 172 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 177 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trimStackTraceEntriesFromBottom(int amount) {
/* 191 */     StackTraceElement[] var2 = new StackTraceElement[this.stackTrace.length - amount];
/* 192 */     System.arraycopy(this.stackTrace, 0, var2, 0, var2.length);
/* 193 */     this.stackTrace = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void appendToStringBuilder(StringBuilder builder) {
/* 198 */     builder.append("-- ").append(this.name).append(" --\n");
/* 199 */     builder.append("Details:");
/* 200 */     Iterator<Entry> var2 = this.children.iterator();
/*     */     
/* 202 */     while (var2.hasNext()) {
/*     */       
/* 204 */       Entry var3 = var2.next();
/* 205 */       builder.append("\n\t");
/* 206 */       builder.append(var3.getKey());
/* 207 */       builder.append(": ");
/* 208 */       builder.append(var3.getValue());
/*     */     } 
/*     */     
/* 211 */     if (this.stackTrace != null && this.stackTrace.length > 0) {
/*     */       
/* 213 */       builder.append("\nStacktrace:");
/* 214 */       StackTraceElement[] var6 = this.stackTrace;
/* 215 */       int var7 = var6.length;
/*     */       
/* 217 */       for (int var4 = 0; var4 < var7; var4++) {
/*     */         
/* 219 */         StackTraceElement var5 = var6[var4];
/* 220 */         builder.append("\n\tat ");
/* 221 */         builder.append(var5.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public StackTraceElement[] getStackTrace() {
/* 228 */     return this.stackTrace;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addBlockInfo(CrashReportCategory category, final BlockPos pos, final Block blockIn, final int blockData) {
/* 233 */     final int var4 = Block.getIdFromBlock(blockIn);
/* 234 */     category.addCrashSectionCallable("Block type", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001426";
/*     */ 
/*     */           
/*     */           public String call() {
/*     */             try {
/* 241 */               return String.format("ID #%d (%s // %s)", new Object[] { Integer.valueOf(this.val$var4), this.val$blockIn.getUnlocalizedName(), this.val$blockIn.getClass().getCanonicalName() });
/*     */             }
/* 243 */             catch (Throwable var2) {
/*     */               
/* 245 */               return "ID #" + var4;
/*     */             } 
/*     */           }
/*     */         });
/* 249 */     category.addCrashSectionCallable("Block data value", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001441";
/*     */           
/*     */           public String call() {
/* 254 */             if (blockData < 0)
/*     */             {
/* 256 */               return "Unknown? (Got " + blockData + ")";
/*     */             }
/*     */ 
/*     */             
/* 260 */             String var1 = String.format("%4s", new Object[] { Integer.toBinaryString(this.val$blockData) }).replace(" ", "0");
/* 261 */             return String.format("%1$d / 0x%1$X / 0b%2$s", new Object[] { Integer.valueOf(this.val$blockData), var1 });
/*     */           }
/*     */         });
/*     */     
/* 265 */     category.addCrashSectionCallable("Block location", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001465";
/*     */           
/*     */           public String call() {
/* 270 */             return CrashReportCategory.getCoordinateInfo(pos);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addBlockInfo(CrashReportCategory category, final BlockPos pos, final IBlockState state) {
/* 277 */     category.addCrashSectionCallable("Block", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002617";
/*     */           
/*     */           public String func_175753_a() {
/* 282 */             return state.toString();
/*     */           }
/*     */           
/*     */           public Object call() {
/* 286 */             return func_175753_a();
/*     */           }
/*     */         });
/* 289 */     category.addCrashSectionCallable("Block location", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002616";
/*     */           
/*     */           public String func_175751_a() {
/* 294 */             return CrashReportCategory.getCoordinateInfo(pos);
/*     */           }
/*     */           
/*     */           public Object call() {
/* 298 */             return func_175751_a();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static class Entry
/*     */   {
/*     */     private final String key;
/*     */     private final String value;
/*     */     private static final String __OBFID = "CL_00001489";
/*     */     
/*     */     public Entry(String key, Object value) {
/* 311 */       this.key = key;
/*     */       
/* 313 */       if (value == null) {
/*     */         
/* 315 */         this.value = "~~NULL~~";
/*     */       }
/* 317 */       else if (value instanceof Throwable) {
/*     */         
/* 319 */         Throwable var3 = (Throwable)value;
/* 320 */         this.value = "~~ERROR~~ " + var3.getClass().getSimpleName() + ": " + var3.getMessage();
/*     */       }
/*     */       else {
/*     */         
/* 324 */         this.value = value.toString();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String getKey() {
/* 330 */       return this.key;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 335 */       return this.value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\crash\CrashReportCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */