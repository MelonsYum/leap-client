/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SimpleReloadableResourceManager
/*     */   implements IReloadableResourceManager {
/*  22 */   private static final Logger logger = LogManager.getLogger();
/*  23 */   private static final Joiner joinerResourcePacks = Joiner.on(", ");
/*  24 */   private final Map domainResourceManagers = Maps.newHashMap();
/*  25 */   private final List reloadListeners = Lists.newArrayList();
/*  26 */   private final Set setResourceDomains = Sets.newLinkedHashSet();
/*     */   
/*     */   private final IMetadataSerializer rmMetadataSerializer;
/*     */   private static final String __OBFID = "CL_00001091";
/*     */   
/*     */   public SimpleReloadableResourceManager(IMetadataSerializer p_i1299_1_) {
/*  32 */     this.rmMetadataSerializer = p_i1299_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reloadResourcePack(IResourcePack p_110545_1_) {
/*  39 */     for (Iterator<String> var2 = p_110545_1_.getResourceDomains().iterator(); var2.hasNext(); var4.addResourcePack(p_110545_1_)) {
/*     */       
/*  41 */       String var3 = var2.next();
/*  42 */       this.setResourceDomains.add(var3);
/*  43 */       FallbackResourceManager var4 = (FallbackResourceManager)this.domainResourceManagers.get(var3);
/*     */       
/*  45 */       if (var4 == null) {
/*     */         
/*  47 */         var4 = new FallbackResourceManager(this.rmMetadataSerializer);
/*  48 */         this.domainResourceManagers.put(var3, var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set getResourceDomains() {
/*  55 */     return this.setResourceDomains;
/*     */   }
/*     */ 
/*     */   
/*     */   public IResource getResource(ResourceLocation p_110536_1_) throws IOException {
/*  60 */     IResourceManager var2 = (IResourceManager)this.domainResourceManagers.get(p_110536_1_.getResourceDomain());
/*     */     
/*  62 */     if (var2 != null)
/*     */     {
/*  64 */       return var2.getResource(p_110536_1_);
/*     */     }
/*     */ 
/*     */     
/*  68 */     throw new FileNotFoundException(p_110536_1_.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List getAllResources(ResourceLocation p_135056_1_) throws IOException {
/*  74 */     IResourceManager var2 = (IResourceManager)this.domainResourceManagers.get(p_135056_1_.getResourceDomain());
/*     */     
/*  76 */     if (var2 != null)
/*     */     {
/*  78 */       return var2.getAllResources(p_135056_1_);
/*     */     }
/*     */ 
/*     */     
/*  82 */     throw new FileNotFoundException(p_135056_1_.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearResources() {
/*  88 */     this.domainResourceManagers.clear();
/*  89 */     this.setResourceDomains.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadResources(List p_110541_1_) {
/*  94 */     clearResources();
/*  95 */     logger.info("Reloading ResourceManager: " + joinerResourcePacks.join(Iterables.transform(p_110541_1_, new Function()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001092";
/*     */               
/*     */               public String apply(IResourcePack p_apply_1_) {
/* 100 */                 return p_apply_1_.getPackName();
/*     */               }
/*     */               
/*     */               public Object apply(Object p_apply_1_) {
/* 104 */                 return apply((IResourcePack)p_apply_1_);
/*     */               }
/*     */             })));
/* 107 */     Iterator<IResourcePack> var2 = p_110541_1_.iterator();
/*     */     
/* 109 */     while (var2.hasNext()) {
/*     */       
/* 111 */       IResourcePack var3 = var2.next();
/* 112 */       reloadResourcePack(var3);
/*     */     } 
/*     */     
/* 115 */     notifyReloadListeners();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerReloadListener(IResourceManagerReloadListener p_110542_1_) {
/* 120 */     this.reloadListeners.add(p_110542_1_);
/* 121 */     p_110542_1_.onResourceManagerReload(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void notifyReloadListeners() {
/* 126 */     Iterator<IResourceManagerReloadListener> var1 = this.reloadListeners.iterator();
/*     */     
/* 128 */     while (var1.hasNext()) {
/*     */       
/* 130 */       IResourceManagerReloadListener var2 = var1.next();
/* 131 */       var2.onResourceManagerReload(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\SimpleReloadableResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */