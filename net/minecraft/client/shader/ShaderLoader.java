/*     */ package net.minecraft.client.shader;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.util.JsonException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.lwjgl.BufferUtils;
/*     */ 
/*     */ public class ShaderLoader
/*     */ {
/*     */   private final ShaderType shaderType;
/*     */   private final String shaderFilename;
/*     */   private int shader;
/*  21 */   private int shaderAttachCount = 0;
/*     */   
/*     */   private static final String __OBFID = "CL_00001043";
/*     */   
/*     */   private ShaderLoader(ShaderType type, int shaderId, String filename) {
/*  26 */     this.shaderType = type;
/*  27 */     this.shader = shaderId;
/*  28 */     this.shaderFilename = filename;
/*     */   }
/*     */ 
/*     */   
/*     */   public void attachShader(ShaderManager manager) {
/*  33 */     this.shaderAttachCount++;
/*  34 */     OpenGlHelper.glAttachShader(manager.getProgram(), this.shader);
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteShader(ShaderManager manager) {
/*  39 */     this.shaderAttachCount--;
/*     */     
/*  41 */     if (this.shaderAttachCount <= 0) {
/*     */       
/*  43 */       OpenGlHelper.glDeleteShader(this.shader);
/*  44 */       this.shaderType.getLoadedShaders().remove(this.shaderFilename);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getShaderFilename() {
/*  50 */     return this.shaderFilename;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ShaderLoader loadShader(IResourceManager resourceManager, ShaderType type, String filename) throws IOException {
/*  55 */     ShaderLoader var3 = (ShaderLoader)type.getLoadedShaders().get(filename);
/*     */     
/*  57 */     if (var3 == null) {
/*     */       
/*  59 */       ResourceLocation var4 = new ResourceLocation("shaders/program/" + filename + type.getShaderExtension());
/*  60 */       BufferedInputStream var5 = new BufferedInputStream(resourceManager.getResource(var4).getInputStream());
/*  61 */       byte[] var6 = func_177064_a(var5);
/*  62 */       ByteBuffer var7 = BufferUtils.createByteBuffer(var6.length);
/*  63 */       var7.put(var6);
/*  64 */       var7.position(0);
/*  65 */       int var8 = OpenGlHelper.glCreateShader(type.getShaderMode());
/*  66 */       OpenGlHelper.glShaderSource(var8, var7);
/*  67 */       OpenGlHelper.glCompileShader(var8);
/*     */       
/*  69 */       if (OpenGlHelper.glGetShaderi(var8, OpenGlHelper.GL_COMPILE_STATUS) == 0) {
/*     */         
/*  71 */         String var9 = StringUtils.trim(OpenGlHelper.glGetShaderInfoLog(var8, 32768));
/*  72 */         JsonException var10 = new JsonException("Couldn't compile " + type.getShaderName() + " program: " + var9);
/*  73 */         var10.func_151381_b(var4.getResourcePath());
/*  74 */         throw var10;
/*     */       } 
/*     */       
/*  77 */       var3 = new ShaderLoader(type, var8, filename);
/*  78 */       type.getLoadedShaders().put(filename, var3);
/*     */     } 
/*     */     
/*  81 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static byte[] func_177064_a(BufferedInputStream p_177064_0_) throws IOException {
/*     */     byte[] var1;
/*     */     try {
/*  90 */       var1 = IOUtils.toByteArray(p_177064_0_);
/*     */     }
/*     */     finally {
/*     */       
/*  94 */       p_177064_0_.close();
/*     */     } 
/*     */     
/*  97 */     return var1;
/*     */   }
/*     */   
/*     */   public enum ShaderType
/*     */   {
/* 102 */     VERTEX("VERTEX", 0, "vertex", ".vsh", OpenGlHelper.GL_VERTEX_SHADER),
/* 103 */     FRAGMENT("FRAGMENT", 1, "fragment", ".fsh", OpenGlHelper.GL_FRAGMENT_SHADER);
/*     */ 
/*     */ 
/*     */     
/* 107 */     private final Map loadedShaders = Maps.newHashMap(); private final String shaderName; private final String shaderExtension;
/*     */     private final int shaderMode;
/* 109 */     private static final ShaderType[] $VALUES = new ShaderType[] { VERTEX, FRAGMENT };
/*     */     
/*     */     private static final String __OBFID = "CL_00001044";
/*     */     
/*     */     ShaderType(String p_i45090_1_, int p_i45090_2_, String p_i45090_3_, String p_i45090_4_, int p_i45090_5_) {
/* 114 */       this.shaderName = p_i45090_3_;
/* 115 */       this.shaderExtension = p_i45090_4_;
/* 116 */       this.shaderMode = p_i45090_5_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String getShaderName() {
/* 121 */       return this.shaderName;
/*     */     }
/*     */ 
/*     */     
/*     */     protected String getShaderExtension() {
/* 126 */       return this.shaderExtension;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getShaderMode() {
/* 131 */       return this.shaderMode;
/*     */     }
/*     */ 
/*     */     
/*     */     protected Map getLoadedShaders() {
/* 136 */       return this.loadedShaders;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\ShaderLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */