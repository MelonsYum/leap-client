/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.client.resources.data.IMetadataSection;
/*    */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public abstract class AbstractResourcePack
/*    */   implements IResourcePack {
/* 23 */   private static final Logger resourceLog = LogManager.getLogger();
/*    */   
/*    */   public final File resourcePackFile;
/*    */   private static final String __OBFID = "CL_00001072";
/*    */   
/*    */   public AbstractResourcePack(File p_i1287_1_) {
/* 29 */     this.resourcePackFile = p_i1287_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   private static String locationToName(ResourceLocation p_110592_0_) {
/* 34 */     return String.format("%s/%s/%s", new Object[] { "assets", p_110592_0_.getResourceDomain(), p_110592_0_.getResourcePath() });
/*    */   }
/*    */ 
/*    */   
/*    */   protected static String getRelativeName(File p_110595_0_, File p_110595_1_) {
/* 39 */     return p_110595_0_.toURI().relativize(p_110595_1_.toURI()).getPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getInputStream(ResourceLocation p_110590_1_) throws IOException {
/* 44 */     return getInputStreamByName(locationToName(p_110590_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean resourceExists(ResourceLocation p_110589_1_) {
/* 49 */     return hasResourceName(locationToName(p_110589_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract InputStream getInputStreamByName(String paramString) throws IOException;
/*    */   
/*    */   protected abstract boolean hasResourceName(String paramString);
/*    */   
/*    */   protected void logNameNotLowercase(String p_110594_1_) {
/* 58 */     resourceLog.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", new Object[] { p_110594_1_, this.resourcePackFile });
/*    */   }
/*    */ 
/*    */   
/*    */   public IMetadataSection getPackMetadata(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
/* 63 */     return readMetadata(p_135058_1_, getInputStreamByName("pack.mcmeta"), p_135058_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   static IMetadataSection readMetadata(IMetadataSerializer p_110596_0_, InputStream p_110596_1_, String p_110596_2_) {
/* 68 */     JsonObject var3 = null;
/* 69 */     BufferedReader var4 = null;
/*    */ 
/*    */     
/*    */     try {
/* 73 */       var4 = new BufferedReader(new InputStreamReader(p_110596_1_, Charsets.UTF_8));
/* 74 */       var3 = (new JsonParser()).parse(var4).getAsJsonObject();
/*    */     }
/* 76 */     catch (RuntimeException var9) {
/*    */       
/* 78 */       throw new JsonParseException(var9);
/*    */     }
/*    */     finally {
/*    */       
/* 82 */       IOUtils.closeQuietly(var4);
/*    */     } 
/*    */     
/* 85 */     return p_110596_0_.parseMetadataSection(p_110596_2_, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public BufferedImage getPackImage() throws IOException {
/* 90 */     return TextureUtil.func_177053_a(getInputStreamByName("pack.png"));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPackName() {
/* 95 */     return this.resourcePackFile.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\AbstractResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */