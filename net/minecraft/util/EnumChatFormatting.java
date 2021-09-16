/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public enum EnumChatFormatting
/*     */ {
/*  12 */   BLACK("BLACK", 0, "BLACK", '0', 0),
/*  13 */   DARK_BLUE("DARK_BLUE", 1, "DARK_BLUE", '1', 1),
/*  14 */   DARK_GREEN("DARK_GREEN", 2, "DARK_GREEN", '2', 2),
/*  15 */   DARK_AQUA("DARK_AQUA", 3, "DARK_AQUA", '3', 3),
/*  16 */   DARK_RED("DARK_RED", 4, "DARK_RED", '4', 4),
/*  17 */   DARK_PURPLE("DARK_PURPLE", 5, "DARK_PURPLE", '5', 5),
/*  18 */   GOLD("GOLD", 6, "GOLD", '6', 6),
/*  19 */   GRAY("GRAY", 7, "GRAY", '7', 7),
/*  20 */   DARK_GRAY("DARK_GRAY", 8, "DARK_GRAY", '8', 8),
/*  21 */   BLUE("BLUE", 9, "BLUE", '9', 9),
/*  22 */   GREEN("GREEN", 10, "GREEN", 'a', 10),
/*  23 */   AQUA("AQUA", 11, "AQUA", 'b', 11),
/*  24 */   RED("RED", 12, "RED", 'c', 12),
/*  25 */   LIGHT_PURPLE("LIGHT_PURPLE", 13, "LIGHT_PURPLE", 'd', 13),
/*  26 */   YELLOW("YELLOW", 14, "YELLOW", 'e', 14),
/*  27 */   WHITE("WHITE", 15, "WHITE", 'f', 15),
/*  28 */   OBFUSCATED("OBFUSCATED", 16, "OBFUSCATED", 'k', true),
/*  29 */   BOLD("BOLD", 17, "BOLD", 'l', true),
/*  30 */   STRIKETHROUGH("STRIKETHROUGH", 18, "STRIKETHROUGH", 'm', true),
/*  31 */   UNDERLINE("UNDERLINE", 19, "UNDERLINE", 'n', true),
/*  32 */   ITALIC("ITALIC", 20, "ITALIC", 'o', true),
/*  33 */   RESET("RESET", 21, "RESET", 'r', -1);
/*     */   private static final Map nameMapping;
/*     */   private static final Pattern formattingCodePattern;
/*     */   
/*     */   static {
/*  38 */     nameMapping = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     formattingCodePattern = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-OR]");
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
/*     */ 
/*     */     
/*  58 */     $VALUES = new EnumChatFormatting[] { BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD, GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE, OBFUSCATED, BOLD, STRIKETHROUGH, UNDERLINE, ITALIC, RESET };
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     EnumChatFormatting[] var0 = values();
/* 185 */     int var1 = var0.length;
/*     */     
/* 187 */     for (int var2 = 0; var2 < var1; var2++) {
/*     */       
/* 189 */       EnumChatFormatting var3 = var0[var2];
/* 190 */       nameMapping.put(func_175745_c(var3.field_175748_y), var3);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final String field_175748_y;
/*     */   private final char formattingCode;
/*     */   private final boolean fancyStyling;
/*     */   private final String controlString;
/*     */   private final int field_175747_C;
/*     */   private static final EnumChatFormatting[] $VALUES;
/*     */   private static final String __OBFID = "CL_00000342";
/*     */   
/*     */   private static String func_175745_c(String p_175745_0_) {
/*     */     return p_175745_0_.toLowerCase().replaceAll("[^a-z]", "");
/*     */   }
/*     */   
/*     */   EnumChatFormatting(String p_i46293_1_, int p_i46293_2_, String p_i46293_3_, char p_i46293_4_, boolean p_i46293_5_, int p_i46293_6_) {
/*     */     this.field_175748_y = p_i46293_3_;
/*     */     this.formattingCode = p_i46293_4_;
/*     */     this.fancyStyling = p_i46293_5_;
/*     */     this.field_175747_C = p_i46293_6_;
/*     */     this.controlString = "§" + p_i46293_4_;
/*     */   }
/*     */   
/*     */   public int func_175746_b() {
/*     */     return this.field_175747_C;
/*     */   }
/*     */   
/*     */   public boolean isFancyStyling() {
/*     */     return this.fancyStyling;
/*     */   }
/*     */   
/*     */   public boolean isColor() {
/*     */     return (!this.fancyStyling && this != RESET);
/*     */   }
/*     */   
/*     */   public String getFriendlyName() {
/*     */     return name().toLowerCase();
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return this.controlString;
/*     */   }
/*     */   
/*     */   public static String getTextWithoutFormattingCodes(String p_110646_0_) {
/*     */     return (p_110646_0_ == null) ? null : formattingCodePattern.matcher(p_110646_0_).replaceAll("");
/*     */   }
/*     */   
/*     */   public static EnumChatFormatting getValueByName(String p_96300_0_) {
/*     */     return (p_96300_0_ == null) ? null : (EnumChatFormatting)nameMapping.get(func_175745_c(p_96300_0_));
/*     */   }
/*     */   
/*     */   public static EnumChatFormatting func_175744_a(int p_175744_0_) {
/*     */     if (p_175744_0_ < 0)
/*     */       return RESET; 
/*     */     EnumChatFormatting[] var1 = values();
/*     */     int var2 = var1.length;
/*     */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       EnumChatFormatting var4 = var1[var3];
/*     */       if (var4.func_175746_b() == p_175744_0_)
/*     */         return var4; 
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   
/*     */   public static Collection getValidValues(boolean p_96296_0_, boolean p_96296_1_) {
/*     */     ArrayList<String> var2 = Lists.newArrayList();
/*     */     EnumChatFormatting[] var3 = values();
/*     */     int var4 = var3.length;
/*     */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       EnumChatFormatting var6 = var3[var5];
/*     */       if ((!var6.isColor() || p_96296_0_) && (!var6.isFancyStyling() || p_96296_1_))
/*     */         var2.add(var6.getFriendlyName()); 
/*     */     } 
/*     */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EnumChatFormatting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */