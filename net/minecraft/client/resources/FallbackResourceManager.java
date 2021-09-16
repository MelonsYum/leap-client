/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class FallbackResourceManager
/*     */   implements IResourceManager {
/*  20 */   private static final Logger field_177246_b = LogManager.getLogger();
/*  21 */   protected final List resourcePacks = Lists.newArrayList();
/*     */   
/*     */   private final IMetadataSerializer frmMetadataSerializer;
/*     */   private static final String __OBFID = "CL_00001074";
/*     */   
/*     */   public FallbackResourceManager(IMetadataSerializer p_i1289_1_) {
/*  27 */     this.frmMetadataSerializer = p_i1289_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addResourcePack(IResourcePack p_110538_1_) {
/*  32 */     this.resourcePacks.add(p_110538_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set getResourceDomains() {
/*  37 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IResource getResource(ResourceLocation p_110536_1_) throws IOException {
/*  42 */     IResourcePack var2 = null;
/*  43 */     ResourceLocation var3 = getLocationMcmeta(p_110536_1_);
/*     */     
/*  45 */     for (int var4 = this.resourcePacks.size() - 1; var4 >= 0; var4--) {
/*     */       
/*  47 */       IResourcePack var5 = this.resourcePacks.get(var4);
/*     */       
/*  49 */       if (var2 == null && var5.resourceExists(var3))
/*     */       {
/*  51 */         var2 = var5;
/*     */       }
/*     */       
/*  54 */       if (var5.resourceExists(p_110536_1_)) {
/*     */         
/*  56 */         InputStream var6 = null;
/*     */         
/*  58 */         if (var2 != null)
/*     */         {
/*  60 */           var6 = func_177245_a(var3, var2);
/*     */         }
/*     */         
/*  63 */         return new SimpleResource(var5.getPackName(), p_110536_1_, func_177245_a(p_110536_1_, var5), var6, this.frmMetadataSerializer);
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     throw new FileNotFoundException(p_110536_1_.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream func_177245_a(ResourceLocation p_177245_1_, IResourcePack p_177245_2_) throws IOException {
/*  72 */     InputStream var3 = p_177245_2_.getInputStream(p_177245_1_);
/*  73 */     return field_177246_b.isDebugEnabled() ? new ImputStreamLeakedResourceLogger(var3, p_177245_1_, p_177245_2_.getPackName()) : var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getAllResources(ResourceLocation p_135056_1_) throws IOException {
/*  78 */     ArrayList<SimpleResource> var2 = Lists.newArrayList();
/*  79 */     ResourceLocation var3 = getLocationMcmeta(p_135056_1_);
/*  80 */     Iterator<IResourcePack> var4 = this.resourcePacks.iterator();
/*     */     
/*  82 */     while (var4.hasNext()) {
/*     */       
/*  84 */       IResourcePack var5 = var4.next();
/*     */       
/*  86 */       if (var5.resourceExists(p_135056_1_)) {
/*     */         
/*  88 */         InputStream var6 = var5.resourceExists(var3) ? func_177245_a(var3, var5) : null;
/*  89 */         var2.add(new SimpleResource(var5.getPackName(), p_135056_1_, func_177245_a(p_135056_1_, var5), var6, this.frmMetadataSerializer));
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     if (var2.isEmpty())
/*     */     {
/*  95 */       throw new FileNotFoundException(p_135056_1_.toString());
/*     */     }
/*     */ 
/*     */     
/*  99 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static ResourceLocation getLocationMcmeta(ResourceLocation p_110537_0_) {
/* 105 */     return new ResourceLocation(p_110537_0_.getResourceDomain(), String.valueOf(p_110537_0_.getResourcePath()) + ".mcmeta");
/*     */   }
/*     */   
/*     */   static class ImputStreamLeakedResourceLogger
/*     */     extends InputStream
/*     */   {
/*     */     private final InputStream field_177330_a;
/*     */     private final String field_177328_b;
/*     */     private boolean field_177329_c = false;
/*     */     private static final String __OBFID = "CL_00002395";
/*     */     
/*     */     public ImputStreamLeakedResourceLogger(InputStream p_i46093_1_, ResourceLocation p_i46093_2_, String p_i46093_3_) {
/* 117 */       this.field_177330_a = p_i46093_1_;
/* 118 */       ByteArrayOutputStream var4 = new ByteArrayOutputStream();
/* 119 */       (new Exception()).printStackTrace(new PrintStream(var4));
/* 120 */       this.field_177328_b = "Leaked resource: '" + p_i46093_2_ + "' loaded from pack: '" + p_i46093_3_ + "'\n" + var4.toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 125 */       this.field_177330_a.close();
/* 126 */       this.field_177329_c = true;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void finalize() throws Throwable {
/* 131 */       if (!this.field_177329_c)
/*     */       {
/* 133 */         FallbackResourceManager.field_177246_b.warn(this.field_177328_b);
/*     */       }
/*     */       
/* 136 */       super.finalize();
/*     */     }
/*     */ 
/*     */     
/*     */     public int read() throws IOException {
/* 141 */       return this.field_177330_a.read();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\FallbackResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */