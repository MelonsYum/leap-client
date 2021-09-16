/*     */ package net.minecraft.client.audio;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.client.resources.IResourceManager;
/*     */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SoundHandler
/*     */   implements IResourceManagerReloadListener, IUpdatePlayerListBox
/*     */ {
/*  32 */   private static final Logger logger = LogManager.getLogger();
/*  33 */   private static final Gson field_147699_c = (new GsonBuilder()).registerTypeAdapter(SoundList.class, new SoundListSerializer()).create();
/*  34 */   private static final ParameterizedType field_147696_d = new ParameterizedType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001148";
/*     */       
/*     */       public Type[] getActualTypeArguments() {
/*  39 */         return new Type[] { String.class, SoundList.class };
/*     */       }
/*     */       
/*     */       public Type getRawType() {
/*  43 */         return Map.class;
/*     */       }
/*     */       
/*     */       public Type getOwnerType() {
/*  47 */         return null;
/*     */       }
/*     */     };
/*  50 */   public static final SoundPoolEntry missing_sound = new SoundPoolEntry(new ResourceLocation("meta:missing_sound"), 0.0D, 0.0D, false);
/*  51 */   private final SoundRegistry sndRegistry = new SoundRegistry();
/*     */   
/*     */   private final SoundManager sndManager;
/*     */   private final IResourceManager mcResourceManager;
/*     */   private static final String __OBFID = "CL_00001147";
/*     */   
/*     */   public SoundHandler(IResourceManager manager, GameSettings p_i45122_2_) {
/*  58 */     this.mcResourceManager = manager;
/*  59 */     this.sndManager = new SoundManager(this, p_i45122_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(IResourceManager p_110549_1_) {
/*  64 */     this.sndManager.reloadSoundSystem();
/*  65 */     this.sndRegistry.clearMap();
/*  66 */     Iterator<String> var2 = p_110549_1_.getResourceDomains().iterator();
/*     */     
/*  68 */     while (var2.hasNext()) {
/*     */       
/*  70 */       String var3 = var2.next();
/*     */ 
/*     */       
/*     */       try {
/*  74 */         List var4 = p_110549_1_.getAllResources(new ResourceLocation(var3, "sounds.json"));
/*  75 */         Iterator<IResource> var5 = var4.iterator();
/*     */         
/*  77 */         while (var5.hasNext()) {
/*     */           
/*  79 */           IResource var6 = var5.next();
/*     */ 
/*     */           
/*     */           try {
/*  83 */             Map var7 = getSoundMap(var6.getInputStream());
/*  84 */             Iterator<Map.Entry> var8 = var7.entrySet().iterator();
/*     */             
/*  86 */             while (var8.hasNext())
/*     */             {
/*  88 */               Map.Entry var9 = var8.next();
/*  89 */               loadSoundResource(new ResourceLocation(var3, (String)var9.getKey()), (SoundList)var9.getValue());
/*     */             }
/*     */           
/*  92 */           } catch (RuntimeException var10) {
/*     */             
/*  94 */             logger.warn("Invalid sounds.json", var10);
/*     */           }
/*     */         
/*     */         } 
/*  98 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Map getSoundMap(InputStream p_175085_1_) {
/*     */     Map var2;
/*     */     try {
/* 111 */       var2 = (Map)field_147699_c.fromJson(new InputStreamReader(p_175085_1_), field_147696_d);
/*     */     }
/*     */     finally {
/*     */       
/* 115 */       IOUtils.closeQuietly(p_175085_1_);
/*     */     } 
/*     */     
/* 118 */     return var2;
/*     */   }
/*     */   
/*     */   private void loadSoundResource(ResourceLocation p_147693_1_, SoundList p_147693_2_) {
/*     */     SoundEventAccessorComposite var3;
/* 123 */     boolean var4 = !this.sndRegistry.containsKey(p_147693_1_);
/*     */ 
/*     */     
/* 126 */     if (!var4 && !p_147693_2_.canReplaceExisting()) {
/*     */       
/* 128 */       var3 = (SoundEventAccessorComposite)this.sndRegistry.getObject(p_147693_1_);
/*     */     }
/*     */     else {
/*     */       
/* 132 */       if (!var4)
/*     */       {
/* 134 */         logger.debug("Replaced sound event location {}", new Object[] { p_147693_1_ });
/*     */       }
/*     */       
/* 137 */       var3 = new SoundEventAccessorComposite(p_147693_1_, 1.0D, 1.0D, p_147693_2_.getSoundCategory());
/* 138 */       this.sndRegistry.registerSound(var3);
/*     */     } 
/*     */     
/* 141 */     Iterator<SoundList.SoundEntry> var5 = p_147693_2_.getSoundList().iterator();
/*     */     
/* 143 */     while (var5.hasNext()) {
/*     */       Object var10; ResourceLocation var11; InputStream var12;
/* 145 */       SoundList.SoundEntry var6 = var5.next();
/* 146 */       String var7 = var6.getSoundEntryName();
/* 147 */       ResourceLocation var8 = new ResourceLocation(var7);
/* 148 */       String var9 = var7.contains(":") ? var8.getResourceDomain() : p_147693_1_.getResourceDomain();
/*     */ 
/*     */       
/* 151 */       switch (SwitchType.field_148765_a[var6.getSoundEntryType().ordinal()]) {
/*     */         
/*     */         case 1:
/* 154 */           var11 = new ResourceLocation(var9, "sounds/" + var8.getResourcePath() + ".ogg");
/* 155 */           var12 = null;
/*     */ 
/*     */           
/*     */           try {
/* 159 */             var12 = this.mcResourceManager.getResource(var11).getInputStream();
/*     */           }
/* 161 */           catch (FileNotFoundException var18) {
/*     */             
/* 163 */             logger.warn("File {} does not exist, cannot add it to event {}", new Object[] { var11, p_147693_1_ });
/*     */             
/*     */             continue;
/* 166 */           } catch (IOException var19) {
/*     */             
/* 168 */             logger.warn("Could not load sound file " + var11 + ", cannot add it to event " + p_147693_1_, var19);
/*     */ 
/*     */             
/*     */             continue;
/*     */           } finally {
/* 173 */             IOUtils.closeQuietly(var12);
/*     */           } 
/*     */           
/* 176 */           var10 = new SoundEventAccessor(new SoundPoolEntry(var11, var6.getSoundEntryPitch(), var6.getSoundEntryVolume(), var6.isStreaming()), var6.getSoundEntryWeight());
/*     */           break;
/*     */         
/*     */         case 2:
/* 180 */           var10 = new ISoundEventAccessor(var9, var6)
/*     */             {
/*     */               final ResourceLocation field_148726_a;
/*     */               private static final String __OBFID = "CL_00001149";
/*     */               
/*     */               public int getWeight() {
/* 186 */                 SoundEventAccessorComposite var1 = (SoundEventAccessorComposite)SoundHandler.this.sndRegistry.getObject(this.field_148726_a);
/* 187 */                 return (var1 == null) ? 0 : var1.getWeight();
/*     */               }
/*     */               
/*     */               public SoundPoolEntry getEntry() {
/* 191 */                 SoundEventAccessorComposite var1 = (SoundEventAccessorComposite)SoundHandler.this.sndRegistry.getObject(this.field_148726_a);
/* 192 */                 return (var1 == null) ? SoundHandler.missing_sound : (SoundPoolEntry)var1.cloneEntry();
/*     */               }
/*     */               
/*     */               public Object cloneEntry() {
/* 196 */                 return getEntry();
/*     */               }
/*     */             };
/*     */           break;
/*     */         
/*     */         default:
/* 202 */           throw new IllegalStateException("IN YOU FACE");
/*     */       } 
/*     */       
/* 205 */       var3.addSoundToEventPool((ISoundEventAccessor)var10);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEventAccessorComposite getSound(ResourceLocation p_147680_1_) {
/* 211 */     return (SoundEventAccessorComposite)this.sndRegistry.getObject(p_147680_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(ISound p_147682_1_) {
/* 219 */     this.sndManager.playSound(p_147682_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playDelayedSound(ISound p_147681_1_, int p_147681_2_) {
/* 227 */     this.sndManager.playDelayedSound(p_147681_1_, p_147681_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setListener(EntityPlayer p_147691_1_, float p_147691_2_) {
/* 232 */     this.sndManager.setListener(p_147691_1_, p_147691_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void pauseSounds() {
/* 237 */     this.sndManager.pauseAllSounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopSounds() {
/* 242 */     this.sndManager.stopAllSounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void unloadSounds() {
/* 247 */     this.sndManager.unloadSoundSystem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 255 */     this.sndManager.updateAllSounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resumeSounds() {
/* 260 */     this.sndManager.resumeAllSounds();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSoundLevel(SoundCategory p_147684_1_, float volume) {
/* 265 */     if (p_147684_1_ == SoundCategory.MASTER && volume <= 0.0F)
/*     */     {
/* 267 */       stopSounds();
/*     */     }
/*     */     
/* 270 */     this.sndManager.setSoundCategoryVolume(p_147684_1_, volume);
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopSound(ISound p_147683_1_) {
/* 275 */     this.sndManager.stopSound(p_147683_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEventAccessorComposite getRandomSoundFromCategories(SoundCategory... p_147686_1_) {
/* 283 */     ArrayList<SoundEventAccessorComposite> var2 = Lists.newArrayList();
/* 284 */     Iterator<ResourceLocation> var3 = this.sndRegistry.getKeys().iterator();
/*     */     
/* 286 */     while (var3.hasNext()) {
/*     */       
/* 288 */       ResourceLocation var4 = var3.next();
/* 289 */       SoundEventAccessorComposite var5 = (SoundEventAccessorComposite)this.sndRegistry.getObject(var4);
/*     */       
/* 291 */       if (ArrayUtils.contains((Object[])p_147686_1_, var5.getSoundCategory()))
/*     */       {
/* 293 */         var2.add(var5);
/*     */       }
/*     */     } 
/*     */     
/* 297 */     if (var2.isEmpty())
/*     */     {
/* 299 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 303 */     return var2.get((new Random()).nextInt(var2.size()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSoundPlaying(ISound p_147692_1_) {
/* 309 */     return this.sndManager.isSoundPlaying(p_147692_1_);
/*     */   }
/*     */   
/*     */   static final class SwitchType
/*     */   {
/* 314 */     static final int[] field_148765_a = new int[(SoundList.SoundEntry.Type.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00001150";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 321 */         field_148765_a[SoundList.SoundEntry.Type.FILE.ordinal()] = 1;
/*     */       }
/* 323 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 330 */         field_148765_a[SoundList.SoundEntry.Type.SOUND_EVENT.ordinal()] = 2;
/*     */       }
/* 332 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */