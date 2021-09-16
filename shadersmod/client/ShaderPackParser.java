/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.CharArrayReader;
/*     */ import java.io.CharArrayWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import optifine.Config;
/*     */ import optifine.StrUtils;
/*     */ 
/*     */ 
/*     */ public class ShaderPackParser
/*     */ {
/*  29 */   private static final Pattern PATTERN_INCLUDE = Pattern.compile("^\\s*#include\\s+\"([A-Za-z0-9_/\\.]+)\".*$");
/*  30 */   private static final Set<String> setConstNames = makeSetConstNames();
/*     */ 
/*     */   
/*     */   public static ShaderOption[] parseShaderPackOptions(IShaderPack shaderPack, String[] programNames, List<Integer> listDimensions) {
/*  34 */     if (shaderPack == null)
/*     */     {
/*  36 */       return new ShaderOption[0];
/*     */     }
/*     */ 
/*     */     
/*  40 */     HashMap<Object, Object> mapOptions = new HashMap<>();
/*  41 */     collectShaderOptions(shaderPack, "/shaders", programNames, (Map)mapOptions);
/*  42 */     Iterator<Integer> options = listDimensions.iterator();
/*     */     
/*  44 */     while (options.hasNext()) {
/*     */       
/*  46 */       int sos = ((Integer)options.next()).intValue();
/*  47 */       String comp = "/shaders/world" + sos;
/*  48 */       collectShaderOptions(shaderPack, comp, programNames, (Map)mapOptions);
/*     */     } 
/*     */     
/*  51 */     Collection options1 = mapOptions.values();
/*  52 */     ShaderOption[] sos1 = (ShaderOption[])options1.toArray((Object[])new ShaderOption[options1.size()]);
/*  53 */     Comparator<? super ShaderOption> comp1 = new Comparator()
/*     */       {
/*     */         public int compare(ShaderOption o1, ShaderOption o2)
/*     */         {
/*  57 */           return o1.getName().compareToIgnoreCase(o2.getName());
/*     */         }
/*     */         
/*     */         public int compare(Object x0, Object x1) {
/*  61 */           return compare((ShaderOption)x0, (ShaderOption)x1);
/*     */         }
/*     */       };
/*  64 */     Arrays.sort(sos1, comp1);
/*  65 */     return sos1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void collectShaderOptions(IShaderPack shaderPack, String dir, String[] programNames, Map<String, ShaderOption> mapOptions) {
/*  71 */     for (int i = 0; i < programNames.length; i++) {
/*     */       
/*  73 */       String programName = programNames[i];
/*     */       
/*  75 */       if (!programName.equals("")) {
/*     */         
/*  77 */         String vsh = String.valueOf(dir) + "/" + programName + ".vsh";
/*  78 */         String fsh = String.valueOf(dir) + "/" + programName + ".fsh";
/*  79 */         collectShaderOptions(shaderPack, vsh, mapOptions);
/*  80 */         collectShaderOptions(shaderPack, fsh, mapOptions);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void collectShaderOptions(IShaderPack sp, String path, Map<String, ShaderOption> mapOptions) {
/*  87 */     String[] lines = getLines(sp, path);
/*     */     
/*  89 */     for (int i = 0; i < lines.length; i++) {
/*     */       
/*  91 */       String line = lines[i];
/*  92 */       ShaderOption so = getShaderOption(line, path);
/*     */       
/*  94 */       if (so != null && (!so.checkUsed() || isOptionUsed(so, lines))) {
/*     */         
/*  96 */         String key = so.getName();
/*  97 */         ShaderOption so2 = mapOptions.get(key);
/*     */         
/*  99 */         if (so2 != null) {
/*     */           
/* 101 */           if (!Config.equals(so2.getValueDefault(), so.getValueDefault())) {
/*     */             
/* 103 */             Config.warn("Ambiguous shader option: " + so.getName());
/* 104 */             Config.warn(" - in " + Config.arrayToString((Object[])so2.getPaths()) + ": " + so2.getValueDefault());
/* 105 */             Config.warn(" - in " + Config.arrayToString((Object[])so.getPaths()) + ": " + so.getValueDefault());
/* 106 */             so2.setEnabled(false);
/*     */           } 
/*     */           
/* 109 */           if (so2.getDescription() == null || so2.getDescription().length() <= 0)
/*     */           {
/* 111 */             so2.setDescription(so.getDescription());
/*     */           }
/*     */           
/* 114 */           so2.addPaths(so.getPaths());
/*     */         }
/*     */         else {
/*     */           
/* 118 */           mapOptions.put(key, so);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isOptionUsed(ShaderOption so, String[] lines) {
/* 126 */     for (int i = 0; i < lines.length; i++) {
/*     */       
/* 128 */       String line = lines[i];
/*     */       
/* 130 */       if (so.isUsedInLine(line))
/*     */       {
/* 132 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] getLines(IShaderPack sp, String path) {
/*     */     try {
/* 143 */       String e = loadFile(path, sp, 0);
/*     */       
/* 145 */       if (e == null)
/*     */       {
/* 147 */         return new String[0];
/*     */       }
/*     */ 
/*     */       
/* 151 */       ByteArrayInputStream is = new ByteArrayInputStream(e.getBytes());
/* 152 */       String[] lines = Config.readLines(is);
/* 153 */       return lines;
/*     */     
/*     */     }
/* 156 */     catch (IOException var5) {
/*     */       
/* 158 */       Config.dbg(String.valueOf(var5.getClass().getName()) + ": " + var5.getMessage());
/* 159 */       return new String[0];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static ShaderOption getShaderOption(String line, String path) {
/* 165 */     ShaderOption so = null;
/*     */     
/* 167 */     if (so == null)
/*     */     {
/* 169 */       so = ShaderOptionSwitch.parseOption(line, path);
/*     */     }
/*     */     
/* 172 */     if (so == null)
/*     */     {
/* 174 */       so = ShaderOptionVariable.parseOption(line, path);
/*     */     }
/*     */     
/* 177 */     if (so != null)
/*     */     {
/* 179 */       return so;
/*     */     }
/*     */ 
/*     */     
/* 183 */     if (so == null)
/*     */     {
/* 185 */       so = ShaderOptionSwitchConst.parseOption(line, path);
/*     */     }
/*     */     
/* 188 */     if (so == null)
/*     */     {
/* 190 */       so = ShaderOptionVariableConst.parseOption(line, path);
/*     */     }
/*     */     
/* 193 */     return (so != null && setConstNames.contains(so.getName())) ? so : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Set<String> makeSetConstNames() {
/* 199 */     HashSet<String> set = new HashSet();
/* 200 */     set.add("shadowMapResolution");
/* 201 */     set.add("shadowDistance");
/* 202 */     set.add("shadowIntervalSize");
/* 203 */     set.add("generateShadowMipmap");
/* 204 */     set.add("generateShadowColorMipmap");
/* 205 */     set.add("shadowHardwareFiltering");
/* 206 */     set.add("shadowHardwareFiltering0");
/* 207 */     set.add("shadowHardwareFiltering1");
/* 208 */     set.add("shadowtex0Mipmap");
/* 209 */     set.add("shadowtexMipmap");
/* 210 */     set.add("shadowtex1Mipmap");
/* 211 */     set.add("shadowcolor0Mipmap");
/* 212 */     set.add("shadowColor0Mipmap");
/* 213 */     set.add("shadowcolor1Mipmap");
/* 214 */     set.add("shadowColor1Mipmap");
/* 215 */     set.add("shadowtex0Nearest");
/* 216 */     set.add("shadowtexNearest");
/* 217 */     set.add("shadow0MinMagNearest");
/* 218 */     set.add("shadowtex1Nearest");
/* 219 */     set.add("shadow1MinMagNearest");
/* 220 */     set.add("shadowcolor0Nearest");
/* 221 */     set.add("shadowColor0Nearest");
/* 222 */     set.add("shadowColor0MinMagNearest");
/* 223 */     set.add("shadowcolor1Nearest");
/* 224 */     set.add("shadowColor1Nearest");
/* 225 */     set.add("shadowColor1MinMagNearest");
/* 226 */     set.add("wetnessHalflife");
/* 227 */     set.add("drynessHalflife");
/* 228 */     set.add("eyeBrightnessHalflife");
/* 229 */     set.add("centerDepthHalflife");
/* 230 */     set.add("sunPathRotation");
/* 231 */     set.add("ambientOcclusionLevel");
/* 232 */     set.add("superSamplingLevel");
/* 233 */     set.add("noiseTextureResolution");
/* 234 */     return set;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ShaderProfile[] parseProfiles(Properties props, ShaderOption[] shaderOptions) {
/* 239 */     String PREFIX_PROFILE = "profile.";
/* 240 */     ArrayList<ShaderProfile> list = new ArrayList();
/* 241 */     Set keys = props.keySet();
/* 242 */     Iterator<String> profs = keys.iterator();
/*     */     
/* 244 */     while (profs.hasNext()) {
/*     */       
/* 246 */       String key = profs.next();
/*     */       
/* 248 */       if (key.startsWith(PREFIX_PROFILE)) {
/*     */         
/* 250 */         String name = key.substring(PREFIX_PROFILE.length());
/* 251 */         props.getProperty(key);
/* 252 */         HashSet<String> parsedProfiles = new HashSet();
/* 253 */         ShaderProfile p = parseProfile(name, props, parsedProfiles, shaderOptions);
/*     */         
/* 255 */         if (p != null)
/*     */         {
/* 257 */           list.add(p);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 262 */     if (list.size() <= 0)
/*     */     {
/* 264 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 268 */     ShaderProfile[] profs1 = list.<ShaderProfile>toArray(new ShaderProfile[list.size()]);
/* 269 */     return profs1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ShaderProfile parseProfile(String name, Properties props, Set<String> parsedProfiles, ShaderOption[] shaderOptions) {
/* 275 */     String PREFIX_PROFILE = "profile.";
/* 276 */     String key = String.valueOf(PREFIX_PROFILE) + name;
/*     */     
/* 278 */     if (parsedProfiles.contains(key)) {
/*     */       
/* 280 */       Config.warn("[Shaders] Profile already parsed: " + name);
/* 281 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 285 */     parsedProfiles.add(name);
/* 286 */     ShaderProfile prof = new ShaderProfile(name);
/* 287 */     String val = props.getProperty(key);
/* 288 */     String[] parts = Config.tokenize(val, " ");
/*     */     
/* 290 */     for (int i = 0; i < parts.length; i++) {
/*     */       
/* 292 */       String part = parts[i];
/*     */       
/* 294 */       if (part.startsWith(PREFIX_PROFILE)) {
/*     */         
/* 296 */         String tokens = part.substring(PREFIX_PROFILE.length());
/* 297 */         ShaderProfile option = parseProfile(tokens, props, parsedProfiles, shaderOptions);
/*     */         
/* 299 */         if (prof != null)
/*     */         {
/* 301 */           prof.addOptionValues(option);
/* 302 */           prof.addDisabledPrograms(option.getDisabledPrograms());
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 307 */         String[] var16 = Config.tokenize(part, ":=");
/*     */ 
/*     */         
/* 310 */         if (var16.length == 1) {
/*     */           
/* 312 */           String var17 = var16[0];
/* 313 */           boolean value = true;
/*     */           
/* 315 */           if (var17.startsWith("!")) {
/*     */             
/* 317 */             value = false;
/* 318 */             var17 = var17.substring(1);
/*     */           } 
/*     */           
/* 321 */           String so = "program.";
/*     */           
/* 323 */           if (!value && var17.startsWith("program.")) {
/*     */             
/* 325 */             String var20 = var17.substring(so.length());
/*     */             
/* 327 */             if (!Shaders.isProgramPath(var20))
/*     */             {
/* 329 */               Config.warn("Invalid program: " + var20 + " in profile: " + prof.getName());
/*     */             }
/*     */             else
/*     */             {
/* 333 */               prof.addDisabledProgram(var20);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 338 */             ShaderOption so1 = ShaderUtils.getShaderOption(var17, shaderOptions);
/*     */             
/* 340 */             if (!(so1 instanceof ShaderOptionSwitch))
/*     */             {
/* 342 */               Config.warn("[Shaders] Invalid option: " + var17);
/*     */             }
/*     */             else
/*     */             {
/* 346 */               prof.addOptionValue(var17, String.valueOf(value));
/* 347 */               so1.setVisible(true);
/*     */             }
/*     */           
/*     */           } 
/* 351 */         } else if (var16.length != 2) {
/*     */           
/* 353 */           Config.warn("[Shaders] Invalid option value: " + part);
/*     */         }
/*     */         else {
/*     */           
/* 357 */           String var17 = var16[0];
/* 358 */           String var18 = var16[1];
/* 359 */           ShaderOption var19 = ShaderUtils.getShaderOption(var17, shaderOptions);
/*     */           
/* 361 */           if (var19 == null) {
/*     */             
/* 363 */             Config.warn("[Shaders] Invalid option: " + part);
/*     */           }
/* 365 */           else if (!var19.isValidValue(var18)) {
/*     */             
/* 367 */             Config.warn("[Shaders] Invalid value: " + part);
/*     */           }
/*     */           else {
/*     */             
/* 371 */             var19.setVisible(true);
/* 372 */             prof.addOptionValue(var17, var18);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 378 */     return prof;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, ShaderOption[]> parseGuiScreens(Properties props, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
/* 384 */     HashMap<Object, Object> map = new HashMap<>();
/* 385 */     parseGuiScreen("screen", props, (Map)map, shaderProfiles, shaderOptions);
/* 386 */     return map.isEmpty() ? null : (Map)map;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean parseGuiScreen(String key, Properties props, Map<String, ShaderOption[]> map, ShaderProfile[] shaderProfiles, ShaderOption[] shaderOptions) {
/* 391 */     String val = props.getProperty(key);
/*     */     
/* 393 */     if (val == null)
/*     */     {
/* 395 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 399 */     ArrayList<ShaderOptionProfile> list = new ArrayList();
/* 400 */     HashSet<String> setNames = new HashSet();
/* 401 */     String[] opNames = Config.tokenize(val, " ");
/*     */     
/* 403 */     for (int scrOps = 0; scrOps < opNames.length; scrOps++) {
/*     */       
/* 405 */       String opName = opNames[scrOps];
/*     */       
/* 407 */       if (opName.equals("<empty>")) {
/*     */         
/* 409 */         list.add(null);
/*     */       }
/* 411 */       else if (setNames.contains(opName)) {
/*     */         
/* 413 */         Config.warn("[Shaders] Duplicate option: " + opName + ", key: " + key);
/*     */       }
/*     */       else {
/*     */         
/* 417 */         setNames.add(opName);
/*     */         
/* 419 */         if (opName.equals("<profile>")) {
/*     */           
/* 421 */           if (shaderProfiles == null)
/*     */           {
/* 423 */             Config.warn("[Shaders] Option profile can not be used, no profiles defined: " + opName + ", key: " + key);
/*     */           }
/*     */           else
/*     */           {
/* 427 */             ShaderOptionProfile so = new ShaderOptionProfile(shaderProfiles, shaderOptions);
/* 428 */             list.add(so);
/*     */           }
/*     */         
/* 431 */         } else if (opName.equals("*")) {
/*     */           
/* 433 */           ShaderOptionRest var14 = new ShaderOptionRest("<rest>");
/* 434 */           list.add(var14);
/*     */         }
/* 436 */         else if (opName.startsWith("[") && opName.endsWith("]")) {
/*     */           
/* 438 */           String var16 = StrUtils.removePrefixSuffix(opName, "[", "]");
/*     */           
/* 440 */           if (!var16.matches("^[a-zA-Z0-9_]+$"))
/*     */           {
/* 442 */             Config.warn("[Shaders] Invalid screen: " + opName + ", key: " + key);
/*     */           }
/* 444 */           else if (!parseGuiScreen("screen." + var16, props, map, shaderProfiles, shaderOptions))
/*     */           {
/* 446 */             Config.warn("[Shaders] Invalid screen: " + opName + ", key: " + key);
/*     */           }
/*     */           else
/*     */           {
/* 450 */             ShaderOptionScreen optionScreen = new ShaderOptionScreen(var16);
/* 451 */             list.add(optionScreen);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 456 */           ShaderOption var15 = ShaderUtils.getShaderOption(opName, shaderOptions);
/*     */           
/* 458 */           if (var15 == null) {
/*     */             
/* 460 */             Config.warn("[Shaders] Invalid option: " + opName + ", key: " + key);
/* 461 */             list.add(null);
/*     */           }
/*     */           else {
/*     */             
/* 465 */             var15.setVisible(true);
/* 466 */             list.add(var15);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 472 */     ShaderOption[] var13 = list.<ShaderOption>toArray(new ShaderOption[list.size()]);
/* 473 */     map.put(key, var13);
/* 474 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static BufferedReader resolveIncludes(BufferedReader reader, String filePath, IShaderPack shaderPack, int includeLevel) throws IOException {
/* 480 */     String fileDir = "/";
/* 481 */     int pos = filePath.lastIndexOf("/");
/*     */     
/* 483 */     if (pos >= 0)
/*     */     {
/* 485 */       fileDir = filePath.substring(0, pos);
/*     */     }
/*     */     
/* 488 */     CharArrayWriter caw = new CharArrayWriter();
/*     */ 
/*     */     
/*     */     while (true) {
/* 492 */       String car = reader.readLine();
/*     */       
/* 494 */       if (car == null) {
/*     */         
/* 496 */         CharArrayReader car1 = new CharArrayReader(caw.toCharArray());
/* 497 */         return new BufferedReader(car1);
/*     */       } 
/*     */       
/* 500 */       Matcher m = PATTERN_INCLUDE.matcher(car);
/*     */       
/* 502 */       if (m.matches()) {
/*     */         
/* 504 */         String fileInc = m.group(1);
/* 505 */         boolean absolute = fileInc.startsWith("/");
/* 506 */         String filePathInc = absolute ? ("/shaders" + fileInc) : (String.valueOf(fileDir) + "/" + fileInc);
/* 507 */         car = loadFile(filePathInc, shaderPack, includeLevel);
/*     */         
/* 509 */         if (car == null)
/*     */         {
/* 511 */           throw new IOException("Included file not found: " + filePath);
/*     */         }
/*     */       } 
/*     */       
/* 515 */       caw.write(car);
/* 516 */       caw.write("\n");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String loadFile(String filePath, IShaderPack shaderPack, int includeLevel) throws IOException {
/* 522 */     if (includeLevel >= 10)
/*     */     {
/* 524 */       throw new IOException("#include depth exceeded: " + includeLevel + ", file: " + filePath);
/*     */     }
/*     */ 
/*     */     
/* 528 */     includeLevel++;
/* 529 */     InputStream in = shaderPack.getResourceAsStream(filePath);
/*     */     
/* 531 */     if (in == null)
/*     */     {
/* 533 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 537 */     InputStreamReader isr = new InputStreamReader(in, "ASCII");
/* 538 */     BufferedReader br = new BufferedReader(isr);
/* 539 */     br = resolveIncludes(br, filePath, shaderPack, includeLevel);
/* 540 */     CharArrayWriter caw = new CharArrayWriter();
/*     */ 
/*     */     
/*     */     while (true) {
/* 544 */       String line = br.readLine();
/*     */       
/* 546 */       if (line == null)
/*     */       {
/* 548 */         return caw.toString();
/*     */       }
/*     */       
/* 551 */       caw.write(line);
/* 552 */       caw.write("\n");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShaderPackParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */