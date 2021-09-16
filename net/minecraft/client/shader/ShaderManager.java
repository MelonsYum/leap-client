/*     */ package net.minecraft.client.shader;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.util.JsonBlendingMode;
/*     */ import net.minecraft.client.util.JsonException;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ShaderManager
/*     */ {
/*  28 */   private static final Logger logger = LogManager.getLogger();
/*  29 */   private static final ShaderDefault defaultShaderUniform = new ShaderDefault();
/*  30 */   private static ShaderManager staticShaderManager = null;
/*  31 */   private static int currentProgram = -1;
/*     */   
/*     */   private static boolean field_148000_e = true;
/*     */   
/*  35 */   private final Map shaderSamplers = Maps.newHashMap();
/*  36 */   private final List samplerNames = Lists.newArrayList();
/*  37 */   private final List shaderSamplerLocations = Lists.newArrayList();
/*  38 */   private final List shaderUniforms = Lists.newArrayList();
/*  39 */   private final List shaderUniformLocations = Lists.newArrayList();
/*  40 */   private final Map mappedShaderUniforms = Maps.newHashMap();
/*     */   
/*     */   private final int program;
/*     */   private final String programFilename;
/*     */   private final boolean useFaceCulling;
/*     */   private boolean isDirty;
/*     */   private final JsonBlendingMode field_148016_p;
/*     */   private final List field_148015_q;
/*     */   private final List field_148014_r;
/*     */   private final ShaderLoader vertexShaderLoader;
/*     */   private final ShaderLoader fragmentShaderLoader;
/*     */   private static final String __OBFID = "CL_00001040";
/*     */   
/*     */   public ShaderManager(IResourceManager resourceManager, String programName) throws JsonException {
/*  54 */     JsonParser var3 = new JsonParser();
/*  55 */     ResourceLocation var4 = new ResourceLocation("shaders/program/" + programName + ".json");
/*  56 */     this.programFilename = programName;
/*  57 */     InputStream var5 = null;
/*     */ 
/*     */     
/*     */     try {
/*  61 */       var5 = resourceManager.getResource(var4).getInputStream();
/*  62 */       JsonObject var6 = var3.parse(IOUtils.toString(var5, Charsets.UTF_8)).getAsJsonObject();
/*  63 */       String var7 = JsonUtils.getJsonObjectStringFieldValue(var6, "vertex");
/*  64 */       String var28 = JsonUtils.getJsonObjectStringFieldValue(var6, "fragment");
/*  65 */       JsonArray var9 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(var6, "samplers", null);
/*     */       
/*  67 */       if (var9 != null) {
/*     */         
/*  69 */         int var10 = 0;
/*     */         
/*  71 */         for (Iterator<JsonElement> var11 = var9.iterator(); var11.hasNext(); var10++) {
/*     */           
/*  73 */           JsonElement var12 = var11.next();
/*     */ 
/*     */           
/*     */           try {
/*  77 */             parseSampler(var12);
/*     */           }
/*  79 */           catch (Exception var25) {
/*     */             
/*  81 */             JsonException var14 = JsonException.func_151379_a(var25);
/*  82 */             var14.func_151380_a("samplers[" + var10 + "]");
/*  83 */             throw var14;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  88 */       JsonArray var29 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(var6, "attributes", null);
/*     */ 
/*     */       
/*  91 */       if (var29 != null) {
/*     */         
/*  93 */         int var30 = 0;
/*  94 */         this.field_148015_q = Lists.newArrayListWithCapacity(var29.size());
/*  95 */         this.field_148014_r = Lists.newArrayListWithCapacity(var29.size());
/*     */         
/*  97 */         for (Iterator<JsonElement> var32 = var29.iterator(); var32.hasNext(); var30++) {
/*     */           
/*  99 */           JsonElement var13 = var32.next();
/*     */ 
/*     */           
/*     */           try {
/* 103 */             this.field_148014_r.add(JsonUtils.getJsonElementStringValue(var13, "attribute"));
/*     */           }
/* 105 */           catch (Exception var24) {
/*     */             
/* 107 */             JsonException var15 = JsonException.func_151379_a(var24);
/* 108 */             var15.func_151380_a("attributes[" + var30 + "]");
/* 109 */             throw var15;
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 115 */         this.field_148015_q = null;
/* 116 */         this.field_148014_r = null;
/*     */       } 
/*     */       
/* 119 */       JsonArray var31 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(var6, "uniforms", null);
/*     */       
/* 121 */       if (var31 != null) {
/*     */         
/* 123 */         int var33 = 0;
/*     */         
/* 125 */         for (Iterator<JsonElement> var34 = var31.iterator(); var34.hasNext(); var33++) {
/*     */           
/* 127 */           JsonElement var36 = var34.next();
/*     */ 
/*     */           
/*     */           try {
/* 131 */             parseUniform(var36);
/*     */           }
/* 133 */           catch (Exception var23) {
/*     */             
/* 135 */             JsonException var16 = JsonException.func_151379_a(var23);
/* 136 */             var16.func_151380_a("uniforms[" + var33 + "]");
/* 137 */             throw var16;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 142 */       this.field_148016_p = JsonBlendingMode.func_148110_a(JsonUtils.getJsonObjectFieldOrDefault(var6, "blend", null));
/* 143 */       this.useFaceCulling = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var6, "cull", true);
/* 144 */       this.vertexShaderLoader = ShaderLoader.loadShader(resourceManager, ShaderLoader.ShaderType.VERTEX, var7);
/* 145 */       this.fragmentShaderLoader = ShaderLoader.loadShader(resourceManager, ShaderLoader.ShaderType.FRAGMENT, var28);
/* 146 */       this.program = ShaderLinkHelper.getStaticShaderLinkHelper().createProgram();
/* 147 */       ShaderLinkHelper.getStaticShaderLinkHelper().linkProgram(this);
/* 148 */       setupUniforms();
/*     */       
/* 150 */       if (this.field_148014_r != null) {
/*     */         
/* 152 */         Iterator<String> var32 = this.field_148014_r.iterator();
/*     */         
/* 154 */         while (var32.hasNext())
/*     */         {
/* 156 */           String var35 = var32.next();
/* 157 */           int var37 = OpenGlHelper.glGetAttribLocation(this.program, var35);
/* 158 */           this.field_148015_q.add(Integer.valueOf(var37));
/*     */         }
/*     */       
/*     */       } 
/* 162 */     } catch (Exception var26) {
/*     */       
/* 164 */       JsonException var8 = JsonException.func_151379_a(var26);
/* 165 */       var8.func_151381_b(var4.getResourcePath());
/* 166 */       throw var8;
/*     */     }
/*     */     finally {
/*     */       
/* 170 */       IOUtils.closeQuietly(var5);
/*     */     } 
/*     */     
/* 173 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteShader() {
/* 178 */     ShaderLinkHelper.getStaticShaderLinkHelper().deleteShader(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void endShader() {
/* 183 */     OpenGlHelper.glUseProgram(0);
/* 184 */     currentProgram = -1;
/* 185 */     staticShaderManager = null;
/* 186 */     field_148000_e = true;
/*     */     
/* 188 */     for (int var1 = 0; var1 < this.shaderSamplerLocations.size(); var1++) {
/*     */       
/* 190 */       if (this.shaderSamplers.get(this.samplerNames.get(var1)) != null) {
/*     */         
/* 192 */         GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + var1);
/* 193 */         GlStateManager.func_179144_i(0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void useShader() {
/* 200 */     this.isDirty = false;
/* 201 */     staticShaderManager = this;
/* 202 */     this.field_148016_p.func_148109_a();
/*     */     
/* 204 */     if (this.program != currentProgram) {
/*     */       
/* 206 */       OpenGlHelper.glUseProgram(this.program);
/* 207 */       currentProgram = this.program;
/*     */     } 
/*     */     
/* 210 */     if (this.useFaceCulling) {
/*     */       
/* 212 */       GlStateManager.enableCull();
/*     */     }
/*     */     else {
/*     */       
/* 216 */       GlStateManager.disableCull();
/*     */     } 
/*     */     
/* 219 */     for (int var1 = 0; var1 < this.shaderSamplerLocations.size(); var1++) {
/*     */       
/* 221 */       if (this.shaderSamplers.get(this.samplerNames.get(var1)) != null) {
/*     */         
/* 223 */         GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit + var1);
/* 224 */         GlStateManager.func_179098_w();
/* 225 */         Object var2 = this.shaderSamplers.get(this.samplerNames.get(var1));
/* 226 */         int var3 = -1;
/*     */         
/* 228 */         if (var2 instanceof Framebuffer) {
/*     */           
/* 230 */           var3 = ((Framebuffer)var2).framebufferTexture;
/*     */         }
/* 232 */         else if (var2 instanceof ITextureObject) {
/*     */           
/* 234 */           var3 = ((ITextureObject)var2).getGlTextureId();
/*     */         }
/* 236 */         else if (var2 instanceof Integer) {
/*     */           
/* 238 */           var3 = ((Integer)var2).intValue();
/*     */         } 
/*     */         
/* 241 */         if (var3 != -1) {
/*     */           
/* 243 */           GlStateManager.func_179144_i(var3);
/* 244 */           OpenGlHelper.glUniform1i(OpenGlHelper.glGetUniformLocation(this.program, this.samplerNames.get(var1)), var1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 249 */     Iterator<ShaderUniform> var4 = this.shaderUniforms.iterator();
/*     */     
/* 251 */     while (var4.hasNext()) {
/*     */       
/* 253 */       ShaderUniform var5 = var4.next();
/* 254 */       var5.upload();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 260 */     this.isDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShaderUniform getShaderUniform(String p_147991_1_) {
/* 268 */     return this.mappedShaderUniforms.containsKey(p_147991_1_) ? (ShaderUniform)this.mappedShaderUniforms.get(p_147991_1_) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShaderUniform getShaderUniformOrDefault(String p_147984_1_) {
/* 276 */     return this.mappedShaderUniforms.containsKey(p_147984_1_) ? (ShaderUniform)this.mappedShaderUniforms.get(p_147984_1_) : defaultShaderUniform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupUniforms() {
/* 284 */     int var1 = 0;
/*     */ 
/*     */ 
/*     */     
/* 288 */     for (int var2 = 0; var1 < this.samplerNames.size(); var2++) {
/*     */       
/* 290 */       String var3 = this.samplerNames.get(var1);
/* 291 */       int var4 = OpenGlHelper.glGetUniformLocation(this.program, var3);
/*     */       
/* 293 */       if (var4 == -1) {
/*     */         
/* 295 */         logger.warn("Shader " + this.programFilename + "could not find sampler named " + var3 + " in the specified shader program.");
/* 296 */         this.shaderSamplers.remove(var3);
/* 297 */         this.samplerNames.remove(var2);
/* 298 */         var2--;
/*     */       }
/*     */       else {
/*     */         
/* 302 */         this.shaderSamplerLocations.add(Integer.valueOf(var4));
/*     */       } 
/*     */       
/* 305 */       var1++;
/*     */     } 
/*     */     
/* 308 */     Iterator<ShaderUniform> var5 = this.shaderUniforms.iterator();
/*     */     
/* 310 */     while (var5.hasNext()) {
/*     */       
/* 312 */       ShaderUniform var6 = var5.next();
/* 313 */       String var3 = var6.getShaderName();
/* 314 */       int var4 = OpenGlHelper.glGetUniformLocation(this.program, var3);
/*     */       
/* 316 */       if (var4 == -1) {
/*     */         
/* 318 */         logger.warn("Could not find uniform named " + var3 + " in the specified" + " shader program.");
/*     */         
/*     */         continue;
/*     */       } 
/* 322 */       this.shaderUniformLocations.add(Integer.valueOf(var4));
/* 323 */       var6.setUniformLocation(var4);
/* 324 */       this.mappedShaderUniforms.put(var3, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseSampler(JsonElement p_147996_1_) {
/* 331 */     JsonObject var2 = JsonUtils.getElementAsJsonObject(p_147996_1_, "sampler");
/* 332 */     String var3 = JsonUtils.getJsonObjectStringFieldValue(var2, "name");
/*     */     
/* 334 */     if (!JsonUtils.jsonObjectFieldTypeIsString(var2, "file")) {
/*     */       
/* 336 */       this.shaderSamplers.put(var3, null);
/* 337 */       this.samplerNames.add(var3);
/*     */     }
/*     */     else {
/*     */       
/* 341 */       this.samplerNames.add(var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSamplerTexture(String p_147992_1_, Object p_147992_2_) {
/* 350 */     if (this.shaderSamplers.containsKey(p_147992_1_))
/*     */     {
/* 352 */       this.shaderSamplers.remove(p_147992_1_);
/*     */     }
/*     */     
/* 355 */     this.shaderSamplers.put(p_147992_1_, p_147992_2_);
/* 356 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseUniform(JsonElement p_147987_1_) throws JsonException {
/* 361 */     JsonObject var2 = JsonUtils.getElementAsJsonObject(p_147987_1_, "uniform");
/* 362 */     String var3 = JsonUtils.getJsonObjectStringFieldValue(var2, "name");
/* 363 */     int var4 = ShaderUniform.parseType(JsonUtils.getJsonObjectStringFieldValue(var2, "type"));
/* 364 */     int var5 = JsonUtils.getJsonObjectIntegerFieldValue(var2, "count");
/* 365 */     float[] var6 = new float[Math.max(var5, 16)];
/* 366 */     JsonArray var7 = JsonUtils.getJsonObjectJsonArrayField(var2, "values");
/*     */     
/* 368 */     if (var7.size() != var5 && var7.size() > 1)
/*     */     {
/* 370 */       throw new JsonException("Invalid amount of values specified (expected " + var5 + ", found " + var7.size() + ")");
/*     */     }
/*     */ 
/*     */     
/* 374 */     int var8 = 0;
/*     */     
/* 376 */     for (Iterator<JsonElement> var9 = var7.iterator(); var9.hasNext(); var8++) {
/*     */       
/* 378 */       JsonElement var10 = var9.next();
/*     */ 
/*     */       
/*     */       try {
/* 382 */         var6[var8] = JsonUtils.getJsonElementFloatValue(var10, "value");
/*     */       }
/* 384 */       catch (Exception var13) {
/*     */         
/* 386 */         JsonException var12 = JsonException.func_151379_a(var13);
/* 387 */         var12.func_151380_a("values[" + var8 + "]");
/* 388 */         throw var12;
/*     */       } 
/*     */     } 
/*     */     
/* 392 */     if (var5 > 1 && var7.size() == 1)
/*     */     {
/* 394 */       while (var8 < var5) {
/*     */         
/* 396 */         var6[var8] = var6[0];
/* 397 */         var8++;
/*     */       } 
/*     */     }
/*     */     
/* 401 */     int var14 = (var5 > 1 && var5 <= 4 && var4 < 8) ? (var5 - 1) : 0;
/* 402 */     ShaderUniform var15 = new ShaderUniform(var3, var4 + var14, var5, this);
/*     */     
/* 404 */     if (var4 <= 3) {
/*     */       
/* 406 */       var15.set((int)var6[0], (int)var6[1], (int)var6[2], (int)var6[3]);
/*     */     }
/* 408 */     else if (var4 <= 7) {
/*     */       
/* 410 */       var15.func_148092_b(var6[0], var6[1], var6[2], var6[3]);
/*     */     }
/*     */     else {
/*     */       
/* 414 */       var15.set(var6);
/*     */     } 
/*     */     
/* 417 */     this.shaderUniforms.add(var15);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ShaderLoader getVertexShaderLoader() {
/* 423 */     return this.vertexShaderLoader;
/*     */   }
/*     */ 
/*     */   
/*     */   public ShaderLoader getFragmentShaderLoader() {
/* 428 */     return this.fragmentShaderLoader;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProgram() {
/* 433 */     return this.program;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\ShaderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */