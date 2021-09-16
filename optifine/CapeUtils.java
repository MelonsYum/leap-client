/*    */ package optifine;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import leap.Client;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.renderer.IImageBuffer;
/*    */ import net.minecraft.client.renderer.ImageBufferDownload;
/*    */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*    */ import net.minecraft.client.renderer.texture.ITextureObject;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.io.FilenameUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CapeUtils
/*    */ {
/*    */   public static void downloadCape(final AbstractClientPlayer player) {
/* 23 */     String username = player.getNameClear();
/*    */     
/* 25 */     if (username != null && !username.isEmpty()) {
/*    */       
/* 27 */       String ofCapeUrl = "http://s.optifine.net/capes/" + username + ".png";
/* 28 */       if (username.equalsIgnoreCase((Minecraft.getMinecraft()).session.getUsername())) if (Client.getModule("Capes").isEnabled())
/*    */         {
/* 30 */           ofCapeUrl = "https://i.imgur.com/2QCntFV.png";
/*    */         }
/*    */       
/* 33 */       String mptHash = FilenameUtils.getBaseName(ofCapeUrl);
/* 34 */       final ResourceLocation rl = new ResourceLocation("capeof/" + mptHash);
/* 35 */       TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
/* 36 */       ITextureObject tex = textureManager.getTexture(rl);
/*    */       
/* 38 */       if (tex != null && tex instanceof ThreadDownloadImageData) {
/*    */         
/* 40 */         ThreadDownloadImageData thePlayer = (ThreadDownloadImageData)tex;
/*    */         
/* 42 */         if (thePlayer.imageFound != null) {
/*    */           
/* 44 */           if (thePlayer.imageFound.booleanValue())
/*    */           {
/* 46 */             player.setLocationOfCape(rl);
/*    */           }
/*    */           
/*    */           return;
/*    */         } 
/*    */       } 
/*    */       
/* 53 */       IImageBuffer iib = new IImageBuffer()
/*    */         {
/* 55 */           ImageBufferDownload ibd = new ImageBufferDownload();
/*    */           
/*    */           public BufferedImage parseUserSkin(BufferedImage var1) {
/* 58 */             return CapeUtils.parseCape(var1);
/*    */           }
/*    */           
/*    */           public void func_152634_a() {
/* 62 */             player.setLocationOfCape(rl);
/*    */           }
/*    */         };
/* 65 */       ThreadDownloadImageData textureCape = new ThreadDownloadImageData(null, ofCapeUrl, null, iib);
/* 66 */       textureCape.pipeline = true;
/* 67 */       textureManager.loadTexture(rl, (ITextureObject)textureCape);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static BufferedImage parseCape(BufferedImage img) {
/* 73 */     int imageWidth = 64;
/* 74 */     int imageHeight = 32;
/* 75 */     int srcWidth = img.getWidth();
/*    */     
/* 77 */     for (int srcHeight = img.getHeight(); imageWidth < srcWidth || imageHeight < srcHeight; imageHeight *= 2)
/*    */     {
/* 79 */       imageWidth *= 2;
/*    */     }
/*    */     
/* 82 */     BufferedImage imgNew = new BufferedImage(imageWidth, imageHeight, 2);
/* 83 */     Graphics g = imgNew.getGraphics();
/* 84 */     g.drawImage(img, 0, 0, null);
/* 85 */     g.dispose();
/* 86 */     return imgNew;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CapeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */