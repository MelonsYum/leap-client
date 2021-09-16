/*    */ package net.minecraft.client.renderer.texture;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import optifine.Config;
/*    */ import shadersmod.client.ShadersTex;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DynamicTexture
/*    */   extends AbstractTexture
/*    */ {
/*    */   private final int[] dynamicTextureData;
/*    */   private final int width;
/*    */   private final int height;
/*    */   private static final String __OBFID = "CL_00001048";
/*    */   private boolean shadersInitialized;
/*    */   
/*    */   public DynamicTexture(BufferedImage p_i1270_1_) {
/* 23 */     this(p_i1270_1_.getWidth(), p_i1270_1_.getHeight());
/* 24 */     p_i1270_1_.getRGB(0, 0, p_i1270_1_.getWidth(), p_i1270_1_.getHeight(), this.dynamicTextureData, 0, p_i1270_1_.getWidth());
/* 25 */     updateDynamicTexture();
/*    */   }
/*    */ 
/*    */   
/*    */   public DynamicTexture(int p_i1271_1_, int p_i1271_2_) {
/* 30 */     this.shadersInitialized = false;
/* 31 */     this.width = p_i1271_1_;
/* 32 */     this.height = p_i1271_2_;
/* 33 */     this.dynamicTextureData = new int[p_i1271_1_ * p_i1271_2_ * 3];
/*    */     
/* 35 */     if (Config.isShaders()) {
/*    */       
/* 37 */       ShadersTex.initDynamicTexture(getGlTextureId(), p_i1271_1_, p_i1271_2_, this);
/* 38 */       this.shadersInitialized = true;
/*    */     }
/*    */     else {
/*    */       
/* 42 */       TextureUtil.allocateTexture(getGlTextureId(), p_i1271_1_, p_i1271_2_);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture(IResourceManager p_110551_1_) throws IOException {}
/*    */   
/*    */   public void updateDynamicTexture() {
/* 50 */     if (Config.isShaders()) {
/*    */       
/* 52 */       if (!this.shadersInitialized) {
/*    */         
/* 54 */         ShadersTex.initDynamicTexture(getGlTextureId(), this.width, this.height, this);
/* 55 */         this.shadersInitialized = true;
/*    */       } 
/*    */       
/* 58 */       ShadersTex.updateDynamicTexture(getGlTextureId(), this.dynamicTextureData, this.width, this.height, this);
/*    */     }
/*    */     else {
/*    */       
/* 62 */       TextureUtil.uploadTexture(getGlTextureId(), this.dynamicTextureData, this.width, this.height);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] getTextureData() {
/* 68 */     return this.dynamicTextureData;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\DynamicTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */