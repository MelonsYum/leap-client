/*     */ package net.minecraft.client.shader;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.util.JsonException;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ShaderGroup
/*     */ {
/*     */   public Framebuffer mainFramebuffer;
/*     */   private IResourceManager resourceManager;
/*     */   private String shaderGroupName;
/*  31 */   public final List listShaders = Lists.newArrayList();
/*  32 */   private final Map mapFramebuffers = Maps.newHashMap();
/*  33 */   private final List listFramebuffers = Lists.newArrayList();
/*     */   
/*     */   private Matrix4f projectionMatrix;
/*     */   private int mainFramebufferWidth;
/*     */   private int mainFramebufferHeight;
/*     */   private float field_148036_j;
/*     */   private float field_148037_k;
/*     */   private static final String __OBFID = "CL_00001041";
/*     */   
/*     */   public ShaderGroup(TextureManager p_i1050_1_, IResourceManager p_i1050_2_, Framebuffer p_i1050_3_, ResourceLocation p_i1050_4_) throws JsonException {
/*  43 */     this.resourceManager = p_i1050_2_;
/*  44 */     this.mainFramebuffer = p_i1050_3_;
/*  45 */     this.field_148036_j = 0.0F;
/*  46 */     this.field_148037_k = 0.0F;
/*  47 */     this.mainFramebufferWidth = p_i1050_3_.framebufferWidth;
/*  48 */     this.mainFramebufferHeight = p_i1050_3_.framebufferHeight;
/*  49 */     this.shaderGroupName = p_i1050_4_.toString();
/*  50 */     resetProjectionMatrix();
/*  51 */     parseGroup(p_i1050_1_, p_i1050_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void parseGroup(TextureManager p_152765_1_, ResourceLocation p_152765_2_) throws JsonException {
/*  56 */     JsonParser var3 = new JsonParser();
/*  57 */     InputStream var4 = null;
/*     */ 
/*     */     
/*     */     try {
/*  61 */       IResource var5 = this.resourceManager.getResource(p_152765_2_);
/*  62 */       var4 = var5.getInputStream();
/*  63 */       JsonObject var22 = var3.parse(IOUtils.toString(var4, Charsets.UTF_8)).getAsJsonObject();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       if (JsonUtils.jsonObjectFieldTypeIsArray(var22, "targets")) {
/*     */         
/*  72 */         JsonArray var7 = var22.getAsJsonArray("targets");
/*  73 */         int var8 = 0;
/*     */         
/*  75 */         for (Iterator<JsonElement> var9 = var7.iterator(); var9.hasNext(); var8++) {
/*     */           
/*  77 */           JsonElement var10 = var9.next();
/*     */ 
/*     */           
/*     */           try {
/*  81 */             initTarget(var10);
/*     */           }
/*  83 */           catch (Exception var19) {
/*     */             
/*  85 */             JsonException var12 = JsonException.func_151379_a(var19);
/*  86 */             var12.func_151380_a("targets[" + var8 + "]");
/*  87 */             throw var12;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  92 */       if (JsonUtils.jsonObjectFieldTypeIsArray(var22, "passes")) {
/*     */         
/*  94 */         JsonArray var7 = var22.getAsJsonArray("passes");
/*  95 */         int var8 = 0;
/*     */         
/*  97 */         for (Iterator<JsonElement> var9 = var7.iterator(); var9.hasNext(); var8++) {
/*     */           
/*  99 */           JsonElement var10 = var9.next();
/*     */ 
/*     */           
/*     */           try {
/* 103 */             parsePass(p_152765_1_, var10);
/*     */           }
/* 105 */           catch (Exception var18) {
/*     */             
/* 107 */             JsonException var12 = JsonException.func_151379_a(var18);
/* 108 */             var12.func_151380_a("passes[" + var8 + "]");
/* 109 */             throw var12;
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 114 */     } catch (Exception var20) {
/*     */       
/* 116 */       JsonException var6 = JsonException.func_151379_a(var20);
/* 117 */       var6.func_151381_b(p_152765_2_.getResourcePath());
/* 118 */       throw var6;
/*     */     }
/*     */     finally {
/*     */       
/* 122 */       IOUtils.closeQuietly(var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void initTarget(JsonElement p_148027_1_) throws JsonException {
/* 128 */     if (JsonUtils.jsonElementTypeIsString(p_148027_1_)) {
/*     */       
/* 130 */       addFramebuffer(p_148027_1_.getAsString(), this.mainFramebufferWidth, this.mainFramebufferHeight);
/*     */     }
/*     */     else {
/*     */       
/* 134 */       JsonObject var2 = JsonUtils.getElementAsJsonObject(p_148027_1_, "target");
/* 135 */       String var3 = JsonUtils.getJsonObjectStringFieldValue(var2, "name");
/* 136 */       int var4 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var2, "width", this.mainFramebufferWidth);
/* 137 */       int var5 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var2, "height", this.mainFramebufferHeight);
/*     */       
/* 139 */       if (this.mapFramebuffers.containsKey(var3))
/*     */       {
/* 141 */         throw new JsonException(String.valueOf(var3) + " is already defined");
/*     */       }
/*     */       
/* 144 */       addFramebuffer(var3, var4, var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parsePass(TextureManager p_152764_1_, JsonElement p_152764_2_) throws JsonException {
/* 150 */     JsonObject var3 = JsonUtils.getElementAsJsonObject(p_152764_2_, "pass");
/* 151 */     String var4 = JsonUtils.getJsonObjectStringFieldValue(var3, "name");
/* 152 */     String var5 = JsonUtils.getJsonObjectStringFieldValue(var3, "intarget");
/* 153 */     String var6 = JsonUtils.getJsonObjectStringFieldValue(var3, "outtarget");
/* 154 */     Framebuffer var7 = getFramebuffer(var5);
/* 155 */     Framebuffer var8 = getFramebuffer(var6);
/*     */     
/* 157 */     if (var7 == null)
/*     */     {
/* 159 */       throw new JsonException("Input target '" + var5 + "' does not exist");
/*     */     }
/* 161 */     if (var8 == null)
/*     */     {
/* 163 */       throw new JsonException("Output target '" + var6 + "' does not exist");
/*     */     }
/*     */ 
/*     */     
/* 167 */     Shader var9 = addShader(var4, var7, var8);
/* 168 */     JsonArray var10 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(var3, "auxtargets", null);
/*     */     
/* 170 */     if (var10 != null) {
/*     */       
/* 172 */       int var11 = 0;
/*     */       
/* 174 */       for (Iterator<JsonElement> var12 = var10.iterator(); var12.hasNext(); var11++) {
/*     */         
/* 176 */         JsonElement var13 = var12.next();
/*     */ 
/*     */         
/*     */         try {
/* 180 */           JsonObject var14 = JsonUtils.getElementAsJsonObject(var13, "auxtarget");
/* 181 */           String var30 = JsonUtils.getJsonObjectStringFieldValue(var14, "name");
/* 182 */           String var16 = JsonUtils.getJsonObjectStringFieldValue(var14, "id");
/* 183 */           Framebuffer var17 = getFramebuffer(var16);
/*     */           
/* 185 */           if (var17 == null)
/*     */           {
/* 187 */             ResourceLocation var18 = new ResourceLocation("textures/effect/" + var16 + ".png");
/*     */ 
/*     */             
/*     */             try {
/* 191 */               this.resourceManager.getResource(var18);
/*     */             }
/* 193 */             catch (FileNotFoundException var24) {
/*     */               
/* 195 */               throw new JsonException("Render target or texture '" + var16 + "' does not exist");
/*     */             } 
/*     */             
/* 198 */             p_152764_1_.bindTexture(var18);
/* 199 */             ITextureObject var19 = p_152764_1_.getTexture(var18);
/* 200 */             int var20 = JsonUtils.getJsonObjectIntegerFieldValue(var14, "width");
/* 201 */             int var21 = JsonUtils.getJsonObjectIntegerFieldValue(var14, "height");
/* 202 */             boolean var22 = JsonUtils.getJsonObjectBooleanFieldValue(var14, "bilinear");
/*     */             
/* 204 */             if (var22) {
/*     */               
/* 206 */               GL11.glTexParameteri(3553, 10241, 9729);
/* 207 */               GL11.glTexParameteri(3553, 10240, 9729);
/*     */             }
/*     */             else {
/*     */               
/* 211 */               GL11.glTexParameteri(3553, 10241, 9728);
/* 212 */               GL11.glTexParameteri(3553, 10240, 9728);
/*     */             } 
/*     */             
/* 215 */             var9.addAuxFramebuffer(var30, Integer.valueOf(var19.getGlTextureId()), var20, var21);
/*     */           }
/*     */           else
/*     */           {
/* 219 */             var9.addAuxFramebuffer(var30, var17, var17.framebufferTextureWidth, var17.framebufferTextureHeight);
/*     */           }
/*     */         
/* 222 */         } catch (Exception var25) {
/*     */           
/* 224 */           JsonException var15 = JsonException.func_151379_a(var25);
/* 225 */           var15.func_151380_a("auxtargets[" + var11 + "]");
/* 226 */           throw var15;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 231 */     JsonArray var26 = JsonUtils.getJsonObjectJsonArrayFieldOrDefault(var3, "uniforms", null);
/*     */     
/* 233 */     if (var26 != null) {
/*     */       
/* 235 */       int var27 = 0;
/*     */       
/* 237 */       for (Iterator<JsonElement> var28 = var26.iterator(); var28.hasNext(); var27++) {
/*     */         
/* 239 */         JsonElement var29 = var28.next();
/*     */ 
/*     */         
/*     */         try {
/* 243 */           initUniform(var29);
/*     */         }
/* 245 */         catch (Exception var23) {
/*     */           
/* 247 */           JsonException var31 = JsonException.func_151379_a(var23);
/* 248 */           var31.func_151380_a("uniforms[" + var27 + "]");
/* 249 */           throw var31;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void initUniform(JsonElement p_148028_1_) throws JsonException {
/* 258 */     JsonObject var2 = JsonUtils.getElementAsJsonObject(p_148028_1_, "uniform");
/* 259 */     String var3 = JsonUtils.getJsonObjectStringFieldValue(var2, "name");
/* 260 */     ShaderUniform var4 = ((Shader)this.listShaders.get(this.listShaders.size() - 1)).getShaderManager().getShaderUniform(var3);
/*     */     
/* 262 */     if (var4 == null)
/*     */     {
/* 264 */       throw new JsonException("Uniform '" + var3 + "' does not exist");
/*     */     }
/*     */ 
/*     */     
/* 268 */     float[] var5 = new float[4];
/* 269 */     int var6 = 0;
/* 270 */     JsonArray var7 = JsonUtils.getJsonObjectJsonArrayField(var2, "values");
/*     */     
/* 272 */     for (Iterator<JsonElement> var8 = var7.iterator(); var8.hasNext(); var6++) {
/*     */       
/* 274 */       JsonElement var9 = var8.next();
/*     */ 
/*     */       
/*     */       try {
/* 278 */         var5[var6] = JsonUtils.getJsonElementFloatValue(var9, "value");
/*     */       }
/* 280 */       catch (Exception var12) {
/*     */         
/* 282 */         JsonException var11 = JsonException.func_151379_a(var12);
/* 283 */         var11.func_151380_a("values[" + var6 + "]");
/* 284 */         throw var11;
/*     */       } 
/*     */     } 
/*     */     
/* 288 */     switch (var6) {
/*     */       default:
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 295 */         var4.set(var5[0]);
/*     */ 
/*     */       
/*     */       case 2:
/* 299 */         var4.set(var5[0], var5[1]);
/*     */ 
/*     */       
/*     */       case 3:
/* 303 */         var4.set(var5[0], var5[1], var5[2]);
/*     */       case 4:
/*     */         break;
/*     */     } 
/* 307 */     var4.set(var5[0], var5[1], var5[2], var5[3]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Framebuffer func_177066_a(String p_177066_1_) {
/* 314 */     return (Framebuffer)this.mapFramebuffers.get(p_177066_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFramebuffer(String p_148020_1_, int p_148020_2_, int p_148020_3_) {
/* 319 */     Framebuffer var4 = new Framebuffer(p_148020_2_, p_148020_3_, true);
/* 320 */     var4.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 321 */     this.mapFramebuffers.put(p_148020_1_, var4);
/*     */     
/* 323 */     if (p_148020_2_ == this.mainFramebufferWidth && p_148020_3_ == this.mainFramebufferHeight)
/*     */     {
/* 325 */       this.listFramebuffers.add(var4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteShaderGroup() {
/* 331 */     Iterator<Framebuffer> var1 = this.mapFramebuffers.values().iterator();
/*     */     
/* 333 */     while (var1.hasNext()) {
/*     */       
/* 335 */       Framebuffer var2 = var1.next();
/* 336 */       var2.deleteFramebuffer();
/*     */     } 
/*     */     
/* 339 */     var1 = this.listShaders.iterator();
/*     */     
/* 341 */     while (var1.hasNext()) {
/*     */       
/* 343 */       Shader var3 = (Shader)var1.next();
/* 344 */       var3.deleteShader();
/*     */     } 
/*     */     
/* 347 */     this.listShaders.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public Shader addShader(String p_148023_1_, Framebuffer p_148023_2_, Framebuffer p_148023_3_) throws JsonException {
/* 352 */     Shader var4 = new Shader(this.resourceManager, p_148023_1_, p_148023_2_, p_148023_3_);
/* 353 */     this.listShaders.add(this.listShaders.size(), var4);
/* 354 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetProjectionMatrix() {
/* 359 */     this.projectionMatrix = new Matrix4f();
/* 360 */     this.projectionMatrix.setIdentity();
/* 361 */     this.projectionMatrix.m00 = 2.0F / this.mainFramebuffer.framebufferTextureWidth;
/* 362 */     this.projectionMatrix.m11 = 2.0F / -this.mainFramebuffer.framebufferTextureHeight;
/* 363 */     this.projectionMatrix.m22 = -0.0020001999F;
/* 364 */     this.projectionMatrix.m33 = 1.0F;
/* 365 */     this.projectionMatrix.m03 = -1.0F;
/* 366 */     this.projectionMatrix.m13 = 1.0F;
/* 367 */     this.projectionMatrix.m23 = -1.0001999F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void createBindFramebuffers(int p_148026_1_, int p_148026_2_) {
/* 372 */     this.mainFramebufferWidth = this.mainFramebuffer.framebufferTextureWidth;
/* 373 */     this.mainFramebufferHeight = this.mainFramebuffer.framebufferTextureHeight;
/* 374 */     resetProjectionMatrix();
/* 375 */     Iterator<Shader> var3 = this.listShaders.iterator();
/*     */     
/* 377 */     while (var3.hasNext()) {
/*     */       
/* 379 */       Shader var4 = var3.next();
/* 380 */       var4.setProjectionMatrix(this.projectionMatrix);
/*     */     } 
/*     */     
/* 383 */     var3 = this.listFramebuffers.iterator();
/*     */     
/* 385 */     while (var3.hasNext()) {
/*     */       
/* 387 */       Framebuffer var5 = (Framebuffer)var3.next();
/* 388 */       var5.createBindFramebuffer(p_148026_1_, p_148026_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadShaderGroup(float p_148018_1_) {
/* 394 */     if (p_148018_1_ < this.field_148037_k) {
/*     */       
/* 396 */       this.field_148036_j += 1.0F - this.field_148037_k;
/* 397 */       this.field_148036_j += p_148018_1_;
/*     */     }
/*     */     else {
/*     */       
/* 401 */       this.field_148036_j += p_148018_1_ - this.field_148037_k;
/*     */     } 
/*     */     
/* 404 */     for (this.field_148037_k = p_148018_1_; this.field_148036_j > 20.0F; this.field_148036_j -= 20.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     Iterator<Shader> var2 = this.listShaders.iterator();
/*     */     
/* 411 */     while (var2.hasNext()) {
/*     */       
/* 413 */       Shader var3 = var2.next();
/* 414 */       var3.loadShader(this.field_148036_j / 20.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final String getShaderGroupName() {
/* 420 */     return this.shaderGroupName;
/*     */   }
/*     */ 
/*     */   
/*     */   private Framebuffer getFramebuffer(String p_148017_1_) {
/* 425 */     return (p_148017_1_ == null) ? null : (p_148017_1_.equals("minecraft:main") ? this.mainFramebuffer : (Framebuffer)this.mapFramebuffers.get(p_148017_1_));
/*     */   }
/*     */   public List<Shader> getShaders() {
/* 428 */     return this.listShaders;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\ShaderGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */