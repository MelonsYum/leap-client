/*    */ package net.minecraft.client.renderer.texture;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import net.minecraft.client.resources.IResource;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.resources.data.TextureMetadataSection;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.Config;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import shadersmod.client.ShadersTex;
/*    */ 
/*    */ public class SimpleTexture
/*    */   extends AbstractTexture
/*    */ {
/* 18 */   private static final Logger logger = LogManager.getLogger();
/*    */   
/*    */   protected final ResourceLocation textureLocation;
/*    */   private static final String __OBFID = "CL_00001052";
/*    */   
/*    */   public SimpleTexture(ResourceLocation p_i1275_1_) {
/* 24 */     this.textureLocation = p_i1275_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture(IResourceManager p_110551_1_) throws IOException {
/* 29 */     deleteGlTexture();
/* 30 */     InputStream var2 = null;
/*    */ 
/*    */     
/*    */     try {
/* 34 */       IResource var3 = p_110551_1_.getResource(this.textureLocation);
/* 35 */       var2 = var3.getInputStream();
/* 36 */       BufferedImage var4 = TextureUtil.func_177053_a(var2);
/* 37 */       boolean var5 = false;
/* 38 */       boolean var6 = false;
/*    */       
/* 40 */       if (var3.hasMetadata()) {
/*    */         
/*    */         try {
/*    */           
/* 44 */           TextureMetadataSection var11 = (TextureMetadataSection)var3.getMetadata("texture");
/*    */           
/* 46 */           if (var11 != null)
/*    */           {
/* 48 */             var5 = var11.getTextureBlur();
/* 49 */             var6 = var11.getTextureClamp();
/*    */           }
/*    */         
/* 52 */         } catch (RuntimeException var111) {
/*    */           
/* 54 */           logger.warn("Failed reading metadata of: " + this.textureLocation, var111);
/*    */         } 
/*    */       }
/*    */       
/* 58 */       if (Config.isShaders())
/*    */       {
/* 60 */         ShadersTex.loadSimpleTexture(getGlTextureId(), var4, var5, var6, p_110551_1_, this.textureLocation, getMultiTexID());
/*    */       }
/*    */       else
/*    */       {
/* 64 */         TextureUtil.uploadTextureImageAllocate(getGlTextureId(), var4, var5, var6);
/*    */       }
/*    */     
/*    */     } finally {
/*    */       
/* 69 */       if (var2 != null)
/*    */       {
/* 71 */         var2.close();
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\SimpleTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */