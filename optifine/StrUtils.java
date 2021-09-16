/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ public class StrUtils
/*     */ {
/*     */   public static boolean equalsMask(String str, String mask, char wildChar, char wildCharSingle) {
/*  10 */     if (mask != null && str != null) {
/*     */       
/*  12 */       if (mask.indexOf(wildChar) < 0)
/*     */       {
/*  14 */         return (mask.indexOf(wildCharSingle) < 0) ? mask.equals(str) : equalsMaskSingle(str, mask, wildCharSingle);
/*     */       }
/*     */ 
/*     */       
/*  18 */       ArrayList<String> tokens = new ArrayList();
/*  19 */       char c = wildChar;
/*     */       
/*  21 */       if (mask.startsWith(c))
/*     */       {
/*  23 */         tokens.add("");
/*     */       }
/*     */       
/*  26 */       StringTokenizer tok = new StringTokenizer(mask, c);
/*     */       
/*  28 */       while (tok.hasMoreElements())
/*     */       {
/*  30 */         tokens.add(tok.nextToken());
/*     */       }
/*     */       
/*  33 */       if (mask.endsWith(c))
/*     */       {
/*  35 */         tokens.add("");
/*     */       }
/*     */       
/*  38 */       String startTok = tokens.get(0);
/*     */       
/*  40 */       if (!startsWithMaskSingle(str, startTok, wildCharSingle))
/*     */       {
/*  42 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  46 */       String endTok = tokens.get(tokens.size() - 1);
/*     */       
/*  48 */       if (!endsWithMaskSingle(str, endTok, wildCharSingle))
/*     */       {
/*  50 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  54 */       int currPos = 0;
/*     */       
/*  56 */       for (int i = 0; i < tokens.size(); i++) {
/*     */         
/*  58 */         String token = tokens.get(i);
/*     */         
/*  60 */         if (token.length() > 0) {
/*     */           
/*  62 */           int foundPos = indexOfMaskSingle(str, token, currPos, wildCharSingle);
/*     */           
/*  64 */           if (foundPos < 0)
/*     */           {
/*  66 */             return false;
/*     */           }
/*     */           
/*  69 */           currPos = foundPos + token.length();
/*     */         } 
/*     */       } 
/*     */       
/*  73 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     return (mask == str);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean equalsMaskSingle(String str, String mask, char wildCharSingle) {
/*  86 */     if (str != null && mask != null) {
/*     */       
/*  88 */       if (str.length() != mask.length())
/*     */       {
/*  90 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  94 */       for (int i = 0; i < mask.length(); i++) {
/*     */         
/*  96 */         char maskChar = mask.charAt(i);
/*     */         
/*  98 */         if (maskChar != wildCharSingle && str.charAt(i) != maskChar)
/*     */         {
/* 100 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 104 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 109 */     return (str == mask);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int indexOfMaskSingle(String str, String mask, int startPos, char wildCharSingle) {
/* 115 */     if (str != null && mask != null) {
/*     */       
/* 117 */       if (startPos >= 0 && startPos <= str.length()) {
/*     */         
/* 119 */         if (str.length() < startPos + mask.length())
/*     */         {
/* 121 */           return -1;
/*     */         }
/*     */ 
/*     */         
/* 125 */         for (int i = startPos; i + mask.length() <= str.length(); i++) {
/*     */           
/* 127 */           String subStr = str.substring(i, i + mask.length());
/*     */           
/* 129 */           if (equalsMaskSingle(subStr, mask, wildCharSingle))
/*     */           {
/* 131 */             return i;
/*     */           }
/*     */         } 
/*     */         
/* 135 */         return -1;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 140 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 145 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean endsWithMaskSingle(String str, String mask, char wildCharSingle) {
/* 151 */     if (str != null && mask != null) {
/*     */       
/* 153 */       if (str.length() < mask.length())
/*     */       {
/* 155 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 159 */       String subStr = str.substring(str.length() - mask.length(), str.length());
/* 160 */       return equalsMaskSingle(subStr, mask, wildCharSingle);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 165 */     return (str == mask);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean startsWithMaskSingle(String str, String mask, char wildCharSingle) {
/* 171 */     if (str != null && mask != null) {
/*     */       
/* 173 */       if (str.length() < mask.length())
/*     */       {
/* 175 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 179 */       String subStr = str.substring(0, mask.length());
/* 180 */       return equalsMaskSingle(subStr, mask, wildCharSingle);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 185 */     return (str == mask);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean equalsMask(String str, String[] masks, char wildChar) {
/* 191 */     for (int i = 0; i < masks.length; i++) {
/*     */       
/* 193 */       String mask = masks[i];
/*     */       
/* 195 */       if (equalsMask(str, mask, wildChar))
/*     */       {
/* 197 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equalsMask(String str, String mask, char wildChar) {
/* 206 */     if (mask != null && str != null) {
/*     */       
/* 208 */       if (mask.indexOf(wildChar) < 0)
/*     */       {
/* 210 */         return mask.equals(str);
/*     */       }
/*     */ 
/*     */       
/* 214 */       ArrayList<String> tokens = new ArrayList();
/* 215 */       char c = wildChar;
/*     */       
/* 217 */       if (mask.startsWith(c))
/*     */       {
/* 219 */         tokens.add("");
/*     */       }
/*     */       
/* 222 */       StringTokenizer tok = new StringTokenizer(mask, c);
/*     */       
/* 224 */       while (tok.hasMoreElements())
/*     */       {
/* 226 */         tokens.add(tok.nextToken());
/*     */       }
/*     */       
/* 229 */       if (mask.endsWith(c))
/*     */       {
/* 231 */         tokens.add("");
/*     */       }
/*     */       
/* 234 */       String startTok = tokens.get(0);
/*     */       
/* 236 */       if (!str.startsWith(startTok))
/*     */       {
/* 238 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 242 */       String endTok = tokens.get(tokens.size() - 1);
/*     */       
/* 244 */       if (!str.endsWith(endTok))
/*     */       {
/* 246 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 250 */       int currPos = 0;
/*     */       
/* 252 */       for (int i = 0; i < tokens.size(); i++) {
/*     */         
/* 254 */         String token = tokens.get(i);
/*     */         
/* 256 */         if (token.length() > 0) {
/*     */           
/* 258 */           int foundPos = str.indexOf(token, currPos);
/*     */           
/* 260 */           if (foundPos < 0)
/*     */           {
/* 262 */             return false;
/*     */           }
/*     */           
/* 265 */           currPos = foundPos + token.length();
/*     */         } 
/*     */       } 
/*     */       
/* 269 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     return (mask == str);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] split(String str, String separators) {
/* 282 */     if (str != null && str.length() > 0) {
/*     */       
/* 284 */       if (separators == null)
/*     */       {
/* 286 */         return new String[] { str };
/*     */       }
/*     */ 
/*     */       
/* 290 */       ArrayList<String> tokens = new ArrayList();
/* 291 */       int startPos = 0;
/*     */       
/* 293 */       for (int i = 0; i < str.length(); i++) {
/*     */         
/* 295 */         char ch = str.charAt(i);
/*     */         
/* 297 */         if (equals(ch, separators)) {
/*     */           
/* 299 */           tokens.add(str.substring(startPos, i));
/* 300 */           startPos = i + 1;
/*     */         } 
/*     */       } 
/*     */       
/* 304 */       tokens.add(str.substring(startPos, str.length()));
/* 305 */       return tokens.<String>toArray(new String[tokens.size()]);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 310 */     return new String[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean equals(char ch, String matches) {
/* 316 */     for (int i = 0; i < matches.length(); i++) {
/*     */       
/* 318 */       if (matches.charAt(i) == ch)
/*     */       {
/* 320 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 324 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean equalsTrim(String a, String b) {
/* 329 */     if (a != null)
/*     */     {
/* 331 */       a = a.trim();
/*     */     }
/*     */     
/* 334 */     if (b != null)
/*     */     {
/* 336 */       b = b.trim();
/*     */     }
/*     */     
/* 339 */     return equals(a, b);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(String string) {
/* 344 */     return (string == null) ? true : ((string.trim().length() <= 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String stringInc(String str) {
/* 349 */     int val = parseInt(str, -1);
/*     */     
/* 351 */     if (val == -1)
/*     */     {
/* 353 */       return "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 358 */     int i = ++val;
/* 359 */     return (i.length() > str.length()) ? "" : fillLeft(val, str.length(), '0');
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int parseInt(String s, int defVal) {
/* 365 */     if (s == null)
/*     */     {
/* 367 */       return defVal;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 373 */       return Integer.parseInt(s);
/*     */     }
/* 375 */     catch (NumberFormatException var3) {
/*     */       
/* 377 */       return defVal;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isFilled(String string) {
/* 384 */     return !isEmpty(string);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String addIfNotContains(String target, String source) {
/* 389 */     for (int i = 0; i < source.length(); i++) {
/*     */       
/* 391 */       if (target.indexOf(source.charAt(i)) < 0)
/*     */       {
/* 393 */         target = String.valueOf(target) + source.charAt(i);
/*     */       }
/*     */     } 
/*     */     
/* 397 */     return target;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String fillLeft(String s, int len, char fillChar) {
/* 402 */     if (s == null)
/*     */     {
/* 404 */       s = "";
/*     */     }
/*     */     
/* 407 */     if (s.length() >= len)
/*     */     {
/* 409 */       return s;
/*     */     }
/*     */ 
/*     */     
/* 413 */     StringBuffer buf = new StringBuffer(s);
/*     */     
/* 415 */     while (buf.length() < len)
/*     */     {
/* 417 */       buf.insert(0, fillChar);
/*     */     }
/*     */     
/* 420 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String fillRight(String s, int len, char fillChar) {
/* 426 */     if (s == null)
/*     */     {
/* 428 */       s = "";
/*     */     }
/*     */     
/* 431 */     if (s.length() >= len)
/*     */     {
/* 433 */       return s;
/*     */     }
/*     */ 
/*     */     
/* 437 */     StringBuffer buf = new StringBuffer(s);
/*     */     
/* 439 */     while (buf.length() < len)
/*     */     {
/* 441 */       buf.append(fillChar);
/*     */     }
/*     */     
/* 444 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean equals(Object a, Object b) {
/* 450 */     return (a == b) ? true : ((a != null && a.equals(b)) ? true : ((b != null && b.equals(a))));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean startsWith(String str, String[] prefixes) {
/* 455 */     if (str == null)
/*     */     {
/* 457 */       return false;
/*     */     }
/* 459 */     if (prefixes == null)
/*     */     {
/* 461 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 465 */     for (int i = 0; i < prefixes.length; i++) {
/*     */       
/* 467 */       String prefix = prefixes[i];
/*     */       
/* 469 */       if (str.startsWith(prefix))
/*     */       {
/* 471 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 475 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean endsWith(String str, String[] suffixes) {
/* 481 */     if (str == null)
/*     */     {
/* 483 */       return false;
/*     */     }
/* 485 */     if (suffixes == null)
/*     */     {
/* 487 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 491 */     for (int i = 0; i < suffixes.length; i++) {
/*     */       
/* 493 */       String suffix = suffixes[i];
/*     */       
/* 495 */       if (str.endsWith(suffix))
/*     */       {
/* 497 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 501 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String removePrefix(String str, String prefix) {
/* 507 */     if (str != null && prefix != null) {
/*     */       
/* 509 */       if (str.startsWith(prefix))
/*     */       {
/* 511 */         str = str.substring(prefix.length());
/*     */       }
/*     */       
/* 514 */       return str;
/*     */     } 
/*     */ 
/*     */     
/* 518 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String removeSuffix(String str, String suffix) {
/* 524 */     if (str != null && suffix != null) {
/*     */       
/* 526 */       if (str.endsWith(suffix))
/*     */       {
/* 528 */         str = str.substring(0, str.length() - suffix.length());
/*     */       }
/*     */       
/* 531 */       return str;
/*     */     } 
/*     */ 
/*     */     
/* 535 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String replaceSuffix(String str, String suffix, String suffixNew) {
/* 541 */     if (str != null && suffix != null) {
/*     */       
/* 543 */       if (suffixNew == null)
/*     */       {
/* 545 */         suffixNew = "";
/*     */       }
/*     */       
/* 548 */       if (str.endsWith(suffix))
/*     */       {
/* 550 */         str = str.substring(0, str.length() - suffix.length());
/*     */       }
/*     */       
/* 553 */       return String.valueOf(str) + suffixNew;
/*     */     } 
/*     */ 
/*     */     
/* 557 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int findPrefix(String[] strs, String prefix) {
/* 563 */     if (strs != null && prefix != null) {
/*     */       
/* 565 */       for (int i = 0; i < strs.length; i++) {
/*     */         
/* 567 */         String str = strs[i];
/*     */         
/* 569 */         if (str.startsWith(prefix))
/*     */         {
/* 571 */           return i;
/*     */         }
/*     */       } 
/*     */       
/* 575 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 579 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int findSuffix(String[] strs, String suffix) {
/* 585 */     if (strs != null && suffix != null) {
/*     */       
/* 587 */       for (int i = 0; i < strs.length; i++) {
/*     */         
/* 589 */         String str = strs[i];
/*     */         
/* 591 */         if (str.endsWith(suffix))
/*     */         {
/* 593 */           return i;
/*     */         }
/*     */       } 
/*     */       
/* 597 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 601 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] remove(String[] strs, int start, int end) {
/* 607 */     if (strs == null)
/*     */     {
/* 609 */       return strs;
/*     */     }
/* 611 */     if (end > 0 && start < strs.length) {
/*     */       
/* 613 */       if (start >= end)
/*     */       {
/* 615 */         return strs;
/*     */       }
/*     */ 
/*     */       
/* 619 */       ArrayList<String> list = new ArrayList(strs.length);
/*     */       
/* 621 */       for (int strsNew = 0; strsNew < strs.length; strsNew++) {
/*     */         
/* 623 */         String str = strs[strsNew];
/*     */         
/* 625 */         if (strsNew < start || strsNew >= end)
/*     */         {
/* 627 */           list.add(str);
/*     */         }
/*     */       } 
/*     */       
/* 631 */       String[] var6 = list.<String>toArray(new String[list.size()]);
/* 632 */       return var6;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 637 */     return strs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String removeSuffix(String str, String[] suffixes) {
/* 643 */     if (str != null && suffixes != null) {
/*     */       
/* 645 */       int strLen = str.length();
/*     */       
/* 647 */       for (int i = 0; i < suffixes.length; i++) {
/*     */         
/* 649 */         String suffix = suffixes[i];
/* 650 */         str = removeSuffix(str, suffix);
/*     */         
/* 652 */         if (str.length() != strLen) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 658 */       return str;
/*     */     } 
/*     */ 
/*     */     
/* 662 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String removePrefix(String str, String[] prefixes) {
/* 668 */     if (str != null && prefixes != null) {
/*     */       
/* 670 */       int strLen = str.length();
/*     */       
/* 672 */       for (int i = 0; i < prefixes.length; i++) {
/*     */         
/* 674 */         String prefix = prefixes[i];
/* 675 */         str = removePrefix(str, prefix);
/*     */         
/* 677 */         if (str.length() != strLen) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 683 */       return str;
/*     */     } 
/*     */ 
/*     */     
/* 687 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String removePrefixSuffix(String str, String[] prefixes, String[] suffixes) {
/* 693 */     str = removePrefix(str, prefixes);
/* 694 */     str = removeSuffix(str, suffixes);
/* 695 */     return str;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String removePrefixSuffix(String str, String prefix, String suffix) {
/* 700 */     return removePrefixSuffix(str, new String[] { prefix }, new String[] { suffix });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSegment(String str, String start, String end) {
/* 705 */     if (str != null && start != null && end != null) {
/*     */       
/* 707 */       int posStart = str.indexOf(start);
/*     */       
/* 709 */       if (posStart < 0)
/*     */       {
/* 711 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 715 */       int posEnd = str.indexOf(end, posStart);
/* 716 */       return (posEnd < 0) ? null : str.substring(posStart, posEnd + end.length());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 721 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\StrUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */