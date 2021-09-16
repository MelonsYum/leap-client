/*    */ package net.minecraft.client.renderer.texture;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class LayeredTexture
/*    */   extends AbstractTexture
/*    */ {
/* 17 */   private static final Logger logger = LogManager.getLogger();
/*    */   
/*    */   public final List layeredTextureNames;
/*    */   private static final String __OBFID = "CL_00001051";
/*    */   
/*    */   public LayeredTexture(String... p_i1274_1_) {
/* 23 */     this.layeredTextureNames = Lists.newArrayList((Object[])p_i1274_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void loadTexture(IResourceManager p_110551_1_) throws IOException {
/* 28 */     deleteGlTexture();
/* 29 */     BufferedImage var2 = null;
/*    */ 
/*    */     
/*    */     try {
/* 33 */       Iterator<String> var3 = this.layeredTextureNames.iterator();
/*    */       
/* 35 */       while (var3.hasNext()) {
/*    */         
/* 37 */         String var4 = var3.next();
/*    */         
/* 39 */         if (var4 != null)
/*    */         {
/* 41 */           InputStream var5 = p_110551_1_.getResource(new ResourceLocation(var4)).getInputStream();
/* 42 */           BufferedImage var6 = TextureUtil.func_177053_a(var5);
/*    */           
/* 44 */           if (var2 == null)
/*    */           {
/* 46 */             var2 = new BufferedImage(var6.getWidth(), var6.getHeight(), 2);
/*    */           }
/*    */           
/* 49 */           var2.getGraphics().drawImage(var6, 0, 0, null);
/*    */         }
/*    */       
/*    */       } 
/* 53 */     } catch (IOException var7) {
/*    */       
/* 55 */       logger.error("Couldn't load layered image", var7);
/*    */       
/*    */       return;
/*    */     } 
/* 59 */     TextureUtil.uploadTextureImage(getGlTextureId(), var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\LayeredTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */