/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.client.resources.data.IMetadataSection;
/*    */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import optifine.ReflectorForge;
/*    */ 
/*    */ public class DefaultResourcePack
/*    */   implements IResourcePack {
/* 20 */   public static final Set defaultResourceDomains = (Set)ImmutableSet.of("minecraft", "realms");
/*    */   
/*    */   private final Map field_152781_b;
/*    */   private static final String __OBFID = "CL_00001073";
/*    */   
/*    */   public DefaultResourcePack(Map p_i46346_1_) {
/* 26 */     this.field_152781_b = p_i46346_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream getInputStream(ResourceLocation p_110590_1_) throws IOException {
/* 31 */     InputStream var2 = getResourceStream(p_110590_1_);
/*    */     
/* 33 */     if (var2 != null)
/*    */     {
/* 35 */       return var2;
/*    */     }
/*    */ 
/*    */     
/* 39 */     InputStream var3 = func_152780_c(p_110590_1_);
/*    */     
/* 41 */     if (var3 != null)
/*    */     {
/* 43 */       return var3;
/*    */     }
/*    */ 
/*    */     
/* 47 */     throw new FileNotFoundException(p_110590_1_.getResourcePath());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InputStream func_152780_c(ResourceLocation p_152780_1_) throws IOException {
/* 54 */     File var2 = (File)this.field_152781_b.get(p_152780_1_.toString());
/* 55 */     return (var2 != null && var2.isFile()) ? new FileInputStream(var2) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   private InputStream getResourceStream(ResourceLocation p_110605_1_) {
/* 60 */     String path = "/assets/" + p_110605_1_.getResourceDomain() + "/" + p_110605_1_.getResourcePath();
/* 61 */     InputStream is = ReflectorForge.getOptiFineResourceStream(path);
/* 62 */     return (is != null) ? is : DefaultResourcePack.class.getResourceAsStream("/assets/" + p_110605_1_.getResourceDomain() + "/" + p_110605_1_.getResourcePath());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean resourceExists(ResourceLocation p_110589_1_) {
/* 67 */     return !(getResourceStream(p_110589_1_) == null && !this.field_152781_b.containsKey(p_110589_1_.toString()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getResourceDomains() {
/* 72 */     return defaultResourceDomains;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IMetadataSection getPackMetadata(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
/*    */     try {
/* 79 */       FileInputStream var5 = new FileInputStream((File)this.field_152781_b.get("pack.mcmeta"));
/* 80 */       return AbstractResourcePack.readMetadata(p_135058_1_, var5, p_135058_2_);
/*    */     }
/* 82 */     catch (RuntimeException var4) {
/*    */       
/* 84 */       return null;
/*    */     }
/* 86 */     catch (FileNotFoundException var51) {
/*    */       
/* 88 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BufferedImage getPackImage() throws IOException {
/* 94 */     return TextureUtil.func_177053_a(DefaultResourcePack.class.getResourceAsStream("/" + (new ResourceLocation("pack.png")).getResourcePath()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPackName() {
/* 99 */     return "Default";
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\DefaultResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */