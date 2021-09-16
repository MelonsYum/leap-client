/*     */ package net.minecraft.client.resources;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.hash.Hashing;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.common.util.concurrent.FutureCallback;
/*     */ import com.google.common.util.concurrent.Futures;
/*     */ import com.google.common.util.concurrent.ListenableFuture;
/*     */ import com.google.common.util.concurrent.SettableFuture;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiScreenWorking;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*     */ import net.minecraft.client.resources.data.PackMetadataSection;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.HttpUtil;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ResourcePackRepository {
/*  40 */   private static final Logger field_177320_c = LogManager.getLogger();
/*  41 */   private static final FileFilter resourcePackFilter = new FileFilter()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001088";
/*     */       
/*     */       public boolean accept(File p_accept_1_) {
/*  46 */         boolean var2 = (p_accept_1_.isFile() && p_accept_1_.getName().endsWith(".zip"));
/*  47 */         boolean var3 = (p_accept_1_.isDirectory() && (new File(p_accept_1_, "pack.mcmeta")).isFile());
/*  48 */         return !(!var2 && !var3);
/*     */       }
/*     */     };
/*     */   private final File dirResourcepacks;
/*     */   public final IResourcePack rprDefaultResourcePack;
/*     */   private final File field_148534_e;
/*     */   public final IMetadataSerializer rprMetadataSerializer;
/*     */   private IResourcePack field_148532_f;
/*  56 */   private final ReentrantLock field_177321_h = new ReentrantLock();
/*     */   private ListenableFuture field_177322_i;
/*  58 */   private List repositoryEntriesAll = Lists.newArrayList();
/*  59 */   private List repositoryEntries = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00001087";
/*     */   
/*     */   public ResourcePackRepository(File p_i45101_1_, File p_i45101_2_, IResourcePack p_i45101_3_, IMetadataSerializer p_i45101_4_, GameSettings p_i45101_5_) {
/*  64 */     this.dirResourcepacks = p_i45101_1_;
/*  65 */     this.field_148534_e = p_i45101_2_;
/*  66 */     this.rprDefaultResourcePack = p_i45101_3_;
/*  67 */     this.rprMetadataSerializer = p_i45101_4_;
/*  68 */     fixDirResourcepacks();
/*  69 */     updateRepositoryEntriesAll();
/*  70 */     Iterator<String> var6 = p_i45101_5_.resourcePacks.iterator();
/*     */     
/*  72 */     while (var6.hasNext()) {
/*     */       
/*  74 */       String var7 = var6.next();
/*  75 */       Iterator<Entry> var8 = this.repositoryEntriesAll.iterator();
/*     */       
/*  77 */       while (var8.hasNext()) {
/*     */         
/*  79 */         Entry var9 = var8.next();
/*     */         
/*  81 */         if (var9.getResourcePackName().equals(var7)) {
/*     */           
/*  83 */           this.repositoryEntries.add(var9);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void fixDirResourcepacks() {
/*  92 */     if (!this.dirResourcepacks.isDirectory() && (!this.dirResourcepacks.delete() || !this.dirResourcepacks.mkdirs()))
/*     */     {
/*  94 */       field_177320_c.debug("Unable to create resourcepack folder: " + this.dirResourcepacks);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private List getResourcePackFiles() {
/* 100 */     return this.dirResourcepacks.isDirectory() ? Arrays.<File>asList(this.dirResourcepacks.listFiles(resourcePackFilter)) : Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateRepositoryEntriesAll() {
/* 105 */     ArrayList<Entry> var1 = Lists.newArrayList();
/* 106 */     Iterator<File> var2 = getResourcePackFiles().iterator();
/*     */     
/* 108 */     while (var2.hasNext()) {
/*     */       
/* 110 */       File var3 = var2.next();
/* 111 */       Entry var4 = new Entry(var3, null);
/*     */       
/* 113 */       if (!this.repositoryEntriesAll.contains(var4)) {
/*     */ 
/*     */         
/*     */         try {
/* 117 */           var4.updateResourcePack();
/* 118 */           var1.add(var4);
/*     */         }
/* 120 */         catch (Exception var6) {
/*     */           
/* 122 */           var1.remove(var4);
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/* 127 */       int var5 = this.repositoryEntriesAll.indexOf(var4);
/*     */       
/* 129 */       if (var5 > -1 && var5 < this.repositoryEntriesAll.size())
/*     */       {
/* 131 */         var1.add(this.repositoryEntriesAll.get(var5));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 136 */     this.repositoryEntriesAll.removeAll(var1);
/* 137 */     var2 = this.repositoryEntriesAll.iterator();
/*     */     
/* 139 */     while (var2.hasNext()) {
/*     */       
/* 141 */       Entry var7 = (Entry)var2.next();
/* 142 */       var7.closeResourcePack();
/*     */     } 
/*     */     
/* 145 */     this.repositoryEntriesAll = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getRepositoryEntriesAll() {
/* 150 */     return (List)ImmutableList.copyOf(this.repositoryEntriesAll);
/*     */   }
/*     */ 
/*     */   
/*     */   public List getRepositoryEntries() {
/* 155 */     return (List)ImmutableList.copyOf(this.repositoryEntries);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148527_a(List p_148527_1_) {
/* 160 */     this.repositoryEntries.clear();
/* 161 */     this.repositoryEntries.addAll(p_148527_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public File getDirResourcepacks() {
/* 166 */     return this.dirResourcepacks;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListenableFuture func_180601_a(String p_180601_1_, String p_180601_2_) {
/*     */     String var3;
/* 173 */     if (p_180601_2_.matches("^[a-f0-9]{40}$")) {
/*     */       
/* 175 */       var3 = p_180601_2_;
/*     */     }
/*     */     else {
/*     */       
/* 179 */       var3 = p_180601_1_.substring(p_180601_1_.lastIndexOf("/") + 1);
/*     */       
/* 181 */       if (var3.contains("?"))
/*     */       {
/* 183 */         var3 = var3.substring(0, var3.indexOf("?"));
/*     */       }
/*     */       
/* 186 */       if (!var3.endsWith(".zip"))
/*     */       {
/* 188 */         return Futures.immediateFailedFuture(new IllegalArgumentException("Invalid filename; must end in .zip"));
/*     */       }
/*     */       
/* 191 */       var3 = "legacy_" + var3.replaceAll("\\W", "");
/*     */     } 
/*     */     
/* 194 */     final File var4 = new File(this.field_148534_e, var3);
/* 195 */     this.field_177321_h.lock();
/*     */ 
/*     */     
/*     */     try {
/* 199 */       func_148529_f();
/*     */       
/* 201 */       if (var4.exists() && p_180601_2_.length() == 40) {
/*     */         
/*     */         try {
/*     */           
/* 205 */           String var5 = Hashing.sha1().hashBytes(Files.toByteArray(var4)).toString();
/*     */           
/* 207 */           if (var5.equals(p_180601_2_)) {
/*     */             
/* 209 */             ListenableFuture var16 = func_177319_a(var4);
/* 210 */             return var16;
/*     */           } 
/*     */           
/* 213 */           field_177320_c.warn("File " + var4 + " had wrong hash (expected " + p_180601_2_ + ", found " + var5 + "). Deleting it.");
/* 214 */           FileUtils.deleteQuietly(var4);
/*     */         }
/* 216 */         catch (IOException var13) {
/*     */           
/* 218 */           field_177320_c.warn("File " + var4 + " couldn't be hashed. Deleting it.", var13);
/* 219 */           FileUtils.deleteQuietly(var4);
/*     */         } 
/*     */       }
/*     */       
/* 223 */       final GuiScreenWorking var15 = new GuiScreenWorking();
/* 224 */       Map var6 = Minecraft.func_175596_ai();
/* 225 */       final Minecraft var7 = Minecraft.getMinecraft();
/* 226 */       Futures.getUnchecked((Future)var7.addScheduledTask(new Runnable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001089";
/*     */               
/*     */               public void run() {
/* 231 */                 var7.displayGuiScreen((GuiScreen)var15);
/*     */               }
/*     */             }));
/* 234 */       final SettableFuture var8 = SettableFuture.create();
/* 235 */       this.field_177322_i = HttpUtil.func_180192_a(var4, p_180601_1_, var6, 52428800, (IProgressUpdate)var15, var7.getProxy());
/* 236 */       Futures.addCallback(this.field_177322_i, new FutureCallback()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002394";
/*     */             
/*     */             public void onSuccess(Object p_onSuccess_1_) {
/* 241 */               ResourcePackRepository.this.func_177319_a(var4);
/* 242 */               var8.set(null);
/*     */             }
/*     */             
/*     */             public void onFailure(Throwable p_onFailure_1_) {
/* 246 */               var8.setException(p_onFailure_1_);
/*     */             }
/*     */           });
/* 249 */       ListenableFuture var9 = this.field_177322_i;
/* 250 */       return var9;
/*     */     }
/*     */     finally {
/*     */       
/* 254 */       this.field_177321_h.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ListenableFuture func_177319_a(File p_177319_1_) {
/* 260 */     this.field_148532_f = new FileResourcePack(p_177319_1_);
/* 261 */     return Minecraft.getMinecraft().func_175603_A();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IResourcePack getResourcePackInstance() {
/* 269 */     return this.field_148532_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148529_f() {
/* 274 */     this.field_177321_h.lock();
/*     */ 
/*     */     
/*     */     try {
/* 278 */       if (this.field_177322_i != null)
/*     */       {
/* 280 */         this.field_177322_i.cancel(true);
/*     */       }
/*     */       
/* 283 */       this.field_177322_i = null;
/* 284 */       this.field_148532_f = null;
/*     */     }
/*     */     finally {
/*     */       
/* 288 */       this.field_177321_h.unlock();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public class Entry
/*     */   {
/*     */     private final File resourcePackFile;
/*     */     private IResourcePack reResourcePack;
/*     */     private PackMetadataSection rePackMetadataSection;
/*     */     private BufferedImage texturePackIcon;
/*     */     private ResourceLocation locationTexturePackIcon;
/*     */     private static final String __OBFID = "CL_00001090";
/*     */     
/*     */     private Entry(File p_i1295_2_) {
/* 303 */       this.resourcePackFile = p_i1295_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateResourcePack() throws IOException {
/* 308 */       this.reResourcePack = this.resourcePackFile.isDirectory() ? new FolderResourcePack(this.resourcePackFile) : new FileResourcePack(this.resourcePackFile);
/* 309 */       this.rePackMetadataSection = (PackMetadataSection)this.reResourcePack.getPackMetadata(ResourcePackRepository.this.rprMetadataSerializer, "pack");
/*     */ 
/*     */       
/*     */       try {
/* 313 */         this.texturePackIcon = this.reResourcePack.getPackImage();
/*     */       }
/* 315 */       catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 320 */       if (this.texturePackIcon == null)
/*     */       {
/* 322 */         this.texturePackIcon = ResourcePackRepository.this.rprDefaultResourcePack.getPackImage();
/*     */       }
/*     */       
/* 325 */       closeResourcePack();
/*     */     }
/*     */ 
/*     */     
/*     */     public void bindTexturePackIcon(TextureManager p_110518_1_) {
/* 330 */       if (this.locationTexturePackIcon == null)
/*     */       {
/* 332 */         this.locationTexturePackIcon = p_110518_1_.getDynamicTextureLocation("texturepackicon", new DynamicTexture(this.texturePackIcon));
/*     */       }
/*     */       
/* 335 */       p_110518_1_.bindTexture(this.locationTexturePackIcon);
/*     */     }
/*     */ 
/*     */     
/*     */     public void closeResourcePack() {
/* 340 */       if (this.reResourcePack instanceof Closeable)
/*     */       {
/* 342 */         IOUtils.closeQuietly((Closeable)this.reResourcePack);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public IResourcePack getResourcePack() {
/* 348 */       return this.reResourcePack;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getResourcePackName() {
/* 353 */       return this.reResourcePack.getPackName();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getTexturePackDescription() {
/* 358 */       return (this.rePackMetadataSection == null) ? (EnumChatFormatting.RED + "Invalid pack.mcmeta (or missing 'pack' section)") : this.rePackMetadataSection.func_152805_a().getFormattedText();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object p_equals_1_) {
/* 363 */       return (this == p_equals_1_) ? true : ((p_equals_1_ instanceof Entry) ? toString().equals(p_equals_1_.toString()) : false);
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 368 */       return toString().hashCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 373 */       return String.format("%s:%s:%d", new Object[] { this.resourcePackFile.getName(), this.resourcePackFile.isDirectory() ? "folder" : "zip", Long.valueOf(this.resourcePackFile.lastModified()) });
/*     */     }
/*     */ 
/*     */     
/*     */     Entry(File p_i1296_2_, Object p_i1296_3_) {
/* 378 */       this(p_i1296_2_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\ResourcePackRepository.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */