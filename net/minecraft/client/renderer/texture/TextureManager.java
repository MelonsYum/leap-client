/*     */ package net.minecraft.client.renderer.texture;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.RandomMobs;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import shadersmod.client.ShadersTex;
/*     */ 
/*     */ 
/*     */ public class TextureManager
/*     */   implements ITickable, IResourceManagerReloadListener
/*     */ {
/*  26 */   private static final Logger logger = LogManager.getLogger();
/*  27 */   public final Map mapTextureObjects = Maps.newHashMap();
/*  28 */   private final List listTickables = Lists.newArrayList();
/*  29 */   private final Map mapTextureCounters = Maps.newHashMap();
/*     */   
/*     */   private IResourceManager theResourceManager;
/*     */   private static final String __OBFID = "CL_00001064";
/*     */   
/*     */   public TextureManager(IResourceManager p_i1284_1_) {
/*  35 */     this.theResourceManager = p_i1284_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void bindTexture(ResourceLocation resource) {
/*  40 */     if (Config.isRandomMobs())
/*     */     {
/*  42 */       resource = RandomMobs.getTextureLocation(resource);
/*     */     }
/*     */     
/*  45 */     Object var2 = this.mapTextureObjects.get(resource);
/*     */     
/*  47 */     if (var2 == null) {
/*     */       
/*  49 */       var2 = new SimpleTexture(resource);
/*  50 */       loadTexture(resource, (ITextureObject)var2);
/*     */     } 
/*     */     
/*  53 */     if (Config.isShaders()) {
/*     */       
/*  55 */       ShadersTex.bindTexture((ITextureObject)var2);
/*     */     }
/*     */     else {
/*     */       
/*  59 */       TextureUtil.bindTexture(((ITextureObject)var2).getGlTextureId());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loadTickableTexture(ResourceLocation p_110580_1_, ITickableTextureObject p_110580_2_) {
/*  65 */     if (loadTexture(p_110580_1_, p_110580_2_)) {
/*     */       
/*  67 */       this.listTickables.add(p_110580_2_);
/*  68 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean loadTexture(ResourceLocation p_110579_1_, final ITextureObject p_110579_2_) {
/*  78 */     boolean var3 = true;
/*  79 */     Object p_110579_2_2 = p_110579_2_;
/*     */ 
/*     */     
/*     */     try {
/*  83 */       p_110579_2_.loadTexture(this.theResourceManager);
/*     */     }
/*  85 */     catch (IOException var8) {
/*     */       
/*  87 */       logger.warn("Failed to load texture: " + p_110579_1_, var8);
/*  88 */       p_110579_2_2 = TextureUtil.missingTexture;
/*  89 */       this.mapTextureObjects.put(p_110579_1_, p_110579_2_2);
/*  90 */       var3 = false;
/*     */     }
/*  92 */     catch (Throwable var9) {
/*     */       
/*  94 */       CrashReport var5 = CrashReport.makeCrashReport(var9, "Registering texture");
/*  95 */       CrashReportCategory var6 = var5.makeCategory("Resource location being registered");
/*  96 */       var6.addCrashSection("Resource location", p_110579_1_);
/*  97 */       var6.addCrashSectionCallable("Texture object class", new Callable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001065";
/*     */             
/*     */             public String call() {
/* 102 */               return p_110579_2_.getClass().getName();
/*     */             }
/*     */           });
/* 105 */       throw new ReportedException(var5);
/*     */     } 
/*     */     
/* 108 */     this.mapTextureObjects.put(p_110579_1_, p_110579_2_2);
/* 109 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public ITextureObject getTexture(ResourceLocation p_110581_1_) {
/* 114 */     return (ITextureObject)this.mapTextureObjects.get(p_110581_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getDynamicTextureLocation(String p_110578_1_, DynamicTexture p_110578_2_) {
/* 119 */     if (p_110578_1_.equals("logo"))
/*     */     {
/* 121 */       p_110578_2_ = Config.getMojangLogoTexture(p_110578_2_);
/*     */     }
/*     */     
/* 124 */     Integer var3 = (Integer)this.mapTextureCounters.get(p_110578_1_);
/*     */     
/* 126 */     if (var3 == null) {
/*     */       
/* 128 */       var3 = Integer.valueOf(1);
/*     */     }
/*     */     else {
/*     */       
/* 132 */       var3 = Integer.valueOf(var3.intValue() + 1);
/*     */     } 
/*     */     
/* 135 */     this.mapTextureCounters.put(p_110578_1_, var3);
/* 136 */     ResourceLocation var4 = new ResourceLocation(String.format("dynamic/%s_%d", new Object[] { p_110578_1_, var3 }));
/* 137 */     loadTexture(var4, p_110578_2_);
/* 138 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 143 */     Iterator<ITickable> var1 = this.listTickables.iterator();
/*     */     
/* 145 */     while (var1.hasNext()) {
/*     */       
/* 147 */       ITickable var2 = var1.next();
/* 148 */       var2.tick();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void deleteTexture(ResourceLocation p_147645_1_) {
/* 154 */     ITextureObject var2 = getTexture(p_147645_1_);
/*     */     
/* 156 */     if (var2 != null)
/*     */     {
/* 158 */       TextureUtil.deleteTexture(var2.getGlTextureId());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(IResourceManager resourceManager) {
/* 164 */     Config.dbg("*** Reloading textures ***");
/* 165 */     Config.log("Resource packs: " + Config.getResourcePackNames());
/* 166 */     Iterator<ResourceLocation> it = this.mapTextureObjects.keySet().iterator();
/*     */     
/* 168 */     while (it.hasNext()) {
/*     */       
/* 170 */       ResourceLocation var2 = it.next();
/*     */       
/* 172 */       if (var2.getResourcePath().startsWith("mcpatcher/")) {
/*     */         
/* 174 */         ITextureObject var3 = (ITextureObject)this.mapTextureObjects.get(var2);
/*     */         
/* 176 */         if (var3 instanceof AbstractTexture) {
/*     */           
/* 178 */           AbstractTexture at = (AbstractTexture)var3;
/* 179 */           at.deleteGlTexture();
/*     */         } 
/*     */         
/* 182 */         it.remove();
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     Iterator<Map.Entry> var21 = this.mapTextureObjects.entrySet().iterator();
/*     */     
/* 188 */     while (var21.hasNext()) {
/*     */       
/* 190 */       Map.Entry var31 = var21.next();
/* 191 */       loadTexture((ResourceLocation)var31.getKey(), (ITextureObject)var31.getValue());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\TextureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */