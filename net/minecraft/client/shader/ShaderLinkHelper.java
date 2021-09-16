/*    */ package net.minecraft.client.shader;
/*    */ 
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import net.minecraft.client.util.JsonException;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class ShaderLinkHelper
/*    */ {
/* 10 */   private static final Logger logger = LogManager.getLogger();
/*    */   
/*    */   private static ShaderLinkHelper staticShaderLinkHelper;
/*    */   private static final String __OBFID = "CL_00001045";
/*    */   
/*    */   public static void setNewStaticShaderLinkHelper() {
/* 16 */     staticShaderLinkHelper = new ShaderLinkHelper();
/*    */   }
/*    */ 
/*    */   
/*    */   public static ShaderLinkHelper getStaticShaderLinkHelper() {
/* 21 */     return staticShaderLinkHelper;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deleteShader(ShaderManager p_148077_1_) {
/* 26 */     p_148077_1_.getFragmentShaderLoader().deleteShader(p_148077_1_);
/* 27 */     p_148077_1_.getVertexShaderLoader().deleteShader(p_148077_1_);
/* 28 */     OpenGlHelper.glDeleteProgram(p_148077_1_.getProgram());
/*    */   }
/*    */ 
/*    */   
/*    */   public int createProgram() throws JsonException {
/* 33 */     int var1 = OpenGlHelper.glCreateProgram();
/*    */     
/* 35 */     if (var1 <= 0)
/*    */     {
/* 37 */       throw new JsonException("Could not create shader program (returned program ID " + var1 + ")");
/*    */     }
/*    */ 
/*    */     
/* 41 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void linkProgram(ShaderManager manager) {
/* 47 */     manager.getFragmentShaderLoader().attachShader(manager);
/* 48 */     manager.getVertexShaderLoader().attachShader(manager);
/* 49 */     OpenGlHelper.glLinkProgram(manager.getProgram());
/* 50 */     int var2 = OpenGlHelper.glGetProgrami(manager.getProgram(), OpenGlHelper.GL_LINK_STATUS);
/*    */     
/* 52 */     if (var2 == 0) {
/*    */       
/* 54 */       logger.warn("Error encountered when linking program containing VS " + manager.getVertexShaderLoader().getShaderFilename() + " and FS " + manager.getFragmentShaderLoader().getShaderFilename() + ". Log output:");
/* 55 */       logger.warn(OpenGlHelper.glGetProgramInfoLog(manager.getProgram(), 32768));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\ShaderLinkHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */