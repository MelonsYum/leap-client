/*     */ package net.minecraft.client.audio;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import io.netty.util.internal.ThreadLocalRandom;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import paulscode.sound.SoundSystem;
/*     */ import paulscode.sound.SoundSystemConfig;
/*     */ import paulscode.sound.SoundSystemException;
/*     */ import paulscode.sound.SoundSystemLogger;
/*     */ import paulscode.sound.Source;
/*     */ import paulscode.sound.codecs.CodecJOrbis;
/*     */ import paulscode.sound.libraries.LibraryLWJGLOpenAL;
/*     */ 
/*     */ 
/*     */ public class SoundManager
/*     */ {
/*  40 */   private static final Marker LOG_MARKER = MarkerManager.getMarker("SOUNDS");
/*  41 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private final SoundHandler sndHandler;
/*     */ 
/*     */   
/*     */   private final GameSettings options;
/*     */ 
/*     */   
/*     */   private SoundSystemStarterThread sndSystem;
/*     */ 
/*     */   
/*     */   private boolean loaded;
/*     */ 
/*     */   
/*  56 */   private int playTime = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private final Map playingSounds = (Map)HashBiMap.create();
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map invPlayingSounds;
/*     */ 
/*     */ 
/*     */   
/*     */   private Map playingSoundPoolEntries;
/*     */ 
/*     */ 
/*     */   
/*     */   private final Multimap categorySounds;
/*     */ 
/*     */   
/*     */   private final List tickableSounds;
/*     */ 
/*     */   
/*     */   private final Map delayedSounds;
/*     */ 
/*     */   
/*     */   private final Map playingSoundsStopTime;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001141";
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundManager(SoundHandler p_i45119_1_, GameSettings p_i45119_2_) {
/*  90 */     this.invPlayingSounds = (Map)((BiMap)this.playingSounds).inverse();
/*  91 */     this.playingSoundPoolEntries = Maps.newHashMap();
/*  92 */     this.categorySounds = (Multimap)HashMultimap.create();
/*  93 */     this.tickableSounds = Lists.newArrayList();
/*  94 */     this.delayedSounds = Maps.newHashMap();
/*  95 */     this.playingSoundsStopTime = Maps.newHashMap();
/*  96 */     this.sndHandler = p_i45119_1_;
/*  97 */     this.options = p_i45119_2_;
/*     */ 
/*     */     
/*     */     try {
/* 101 */       SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
/* 102 */       SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
/*     */     }
/* 104 */     catch (SoundSystemException var4) {
/*     */       
/* 106 */       logger.error(LOG_MARKER, "Error linking with the LibraryJavaSound plug-in", (Throwable)var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadSoundSystem() {
/* 112 */     unloadSoundSystem();
/* 113 */     loadSoundSystem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void loadSoundSystem() {
/* 121 */     if (!this.loaded) {
/*     */       
/*     */       try {
/*     */         
/* 125 */         (new Thread(new Runnable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00001142";
/*     */               
/*     */               public void run() {
/* 130 */                 SoundSystemConfig.setLogger(new SoundSystemLogger()
/*     */                     {
/*     */                       private static final String __OBFID = "CL_00002378";
/*     */                       
/*     */                       public void message(String p_message_1_, int p_message_2_) {
/* 135 */                         if (!p_message_1_.isEmpty())
/*     */                         {
/* 137 */                           SoundManager.logger.info(p_message_1_);
/*     */                         }
/*     */                       }
/*     */                       
/*     */                       public void importantMessage(String p_importantMessage_1_, int p_importantMessage_2_) {
/* 142 */                         if (!p_importantMessage_1_.isEmpty())
/*     */                         {
/* 144 */                           SoundManager.logger.warn(p_importantMessage_1_);
/*     */                         }
/*     */                       }
/*     */                       
/*     */                       public void errorMessage(String p_errorMessage_1_, String p_errorMessage_2_, int p_errorMessage_3_) {
/* 149 */                         if (!p_errorMessage_2_.isEmpty()) {
/*     */                           
/* 151 */                           SoundManager.logger.error("Error in class '" + p_errorMessage_1_ + "'");
/* 152 */                           SoundManager.logger.error(p_errorMessage_2_);
/*     */                         } 
/*     */                       }
/*     */                     },  );
/* 156 */                 SoundManager.this.getClass(); SoundManager.this.sndSystem = new SoundManager.SoundSystemStarterThread(null);
/* 157 */                 SoundManager.this.loaded = true;
/* 158 */                 SoundManager.this.sndSystem.setMasterVolume(SoundManager.this.options.getSoundLevel(SoundCategory.MASTER));
/* 159 */                 SoundManager.logger.info(SoundManager.LOG_MARKER, "Sound engine started");
/*     */               }
/* 161 */             }"Sound Library Loader")).start();
/*     */       }
/* 163 */       catch (RuntimeException var2) {
/*     */         
/* 165 */         logger.error(LOG_MARKER, "Error starting SoundSystem. Turning off sounds & music", var2);
/* 166 */         this.options.setSoundLevel(SoundCategory.MASTER, 0.0F);
/* 167 */         this.options.saveOptions();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getSoundCategoryVolume(SoundCategory p_148595_1_) {
/* 177 */     return (p_148595_1_ != null && p_148595_1_ != SoundCategory.MASTER) ? this.options.getSoundLevel(p_148595_1_) : 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSoundCategoryVolume(SoundCategory p_148601_1_, float p_148601_2_) {
/* 185 */     if (this.loaded)
/*     */     {
/* 187 */       if (p_148601_1_ == SoundCategory.MASTER) {
/*     */         
/* 189 */         this.sndSystem.setMasterVolume(p_148601_2_);
/*     */       }
/*     */       else {
/*     */         
/* 193 */         Iterator<String> var3 = this.categorySounds.get(p_148601_1_).iterator();
/*     */         
/* 195 */         while (var3.hasNext()) {
/*     */           
/* 197 */           String var4 = var3.next();
/* 198 */           ISound var5 = (ISound)this.playingSounds.get(var4);
/* 199 */           float var6 = getNormalizedVolume(var5, (SoundPoolEntry)this.playingSoundPoolEntries.get(var5), p_148601_1_);
/*     */           
/* 201 */           if (var6 <= 0.0F) {
/*     */             
/* 203 */             stopSound(var5);
/*     */             
/*     */             continue;
/*     */           } 
/* 207 */           this.sndSystem.setVolume(var4, var6);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unloadSoundSystem() {
/* 219 */     if (this.loaded) {
/*     */       
/* 221 */       stopAllSounds();
/* 222 */       this.sndSystem.cleanup();
/* 223 */       this.loaded = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopAllSounds() {
/* 232 */     if (this.loaded) {
/*     */       
/* 234 */       Iterator<String> var1 = this.playingSounds.keySet().iterator();
/*     */       
/* 236 */       while (var1.hasNext()) {
/*     */         
/* 238 */         String var2 = var1.next();
/* 239 */         this.sndSystem.stop(var2);
/*     */       } 
/*     */       
/* 242 */       this.playingSounds.clear();
/* 243 */       this.delayedSounds.clear();
/* 244 */       this.tickableSounds.clear();
/* 245 */       this.categorySounds.clear();
/* 246 */       this.playingSoundPoolEntries.clear();
/* 247 */       this.playingSoundsStopTime.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAllSounds() {
/* 253 */     this.playTime++;
/* 254 */     Iterator<ITickableSound> var1 = this.tickableSounds.iterator();
/*     */ 
/*     */     
/* 257 */     while (var1.hasNext()) {
/*     */       
/* 259 */       ITickableSound var2 = var1.next();
/* 260 */       var2.update();
/*     */       
/* 262 */       if (var2.isDonePlaying()) {
/*     */         
/* 264 */         stopSound(var2);
/*     */         
/*     */         continue;
/*     */       } 
/* 268 */       String var3 = (String)this.invPlayingSounds.get(var2);
/* 269 */       this.sndSystem.setVolume(var3, getNormalizedVolume(var2, (SoundPoolEntry)this.playingSoundPoolEntries.get(var2), this.sndHandler.getSound(var2.getSoundLocation()).getSoundCategory()));
/* 270 */       this.sndSystem.setPitch(var3, getNormalizedPitch(var2, (SoundPoolEntry)this.playingSoundPoolEntries.get(var2)));
/* 271 */       this.sndSystem.setPosition(var3, var2.getXPosF(), var2.getYPosF(), var2.getZPosF());
/*     */     } 
/*     */ 
/*     */     
/* 275 */     var1 = this.playingSounds.entrySet().iterator();
/*     */ 
/*     */     
/* 278 */     while (var1.hasNext()) {
/*     */       
/* 280 */       Map.Entry var9 = (Map.Entry)var1.next();
/* 281 */       String var3 = (String)var9.getKey();
/* 282 */       ISound var4 = (ISound)var9.getValue();
/*     */       
/* 284 */       if (!this.sndSystem.playing(var3)) {
/*     */         
/* 286 */         int var5 = ((Integer)this.playingSoundsStopTime.get(var3)).intValue();
/*     */         
/* 288 */         if (var5 <= this.playTime) {
/*     */           
/* 290 */           int var6 = var4.getRepeatDelay();
/*     */           
/* 292 */           if (var4.canRepeat() && var6 > 0)
/*     */           {
/* 294 */             this.delayedSounds.put(var4, Integer.valueOf(this.playTime + var6));
/*     */           }
/*     */           
/* 297 */           var1.remove();
/* 298 */           logger.debug(LOG_MARKER, "Removed channel {} because it's not playing anymore", new Object[] { var3 });
/* 299 */           this.sndSystem.removeSource(var3);
/* 300 */           this.playingSoundsStopTime.remove(var3);
/* 301 */           this.playingSoundPoolEntries.remove(var4);
/*     */ 
/*     */           
/*     */           try {
/* 305 */             this.categorySounds.remove(this.sndHandler.getSound(var4.getSoundLocation()).getSoundCategory(), var3);
/*     */           }
/* 307 */           catch (RuntimeException runtimeException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 312 */           if (var4 instanceof ITickableSound)
/*     */           {
/* 314 */             this.tickableSounds.remove(var4);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 320 */     Iterator<Map.Entry> var10 = this.delayedSounds.entrySet().iterator();
/*     */     
/* 322 */     while (var10.hasNext()) {
/*     */       
/* 324 */       Map.Entry var11 = var10.next();
/*     */       
/* 326 */       if (this.playTime >= ((Integer)var11.getValue()).intValue()) {
/*     */         
/* 328 */         ISound var4 = (ISound)var11.getKey();
/*     */         
/* 330 */         if (var4 instanceof ITickableSound)
/*     */         {
/* 332 */           ((ITickableSound)var4).update();
/*     */         }
/*     */         
/* 335 */         playSound(var4);
/* 336 */         var10.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSoundPlaying(ISound p_148597_1_) {
/* 346 */     if (!this.loaded)
/*     */     {
/* 348 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 352 */     String var2 = (String)this.invPlayingSounds.get(p_148597_1_);
/* 353 */     return (var2 == null) ? false : (!(!this.sndSystem.playing(var2) && (!this.playingSoundsStopTime.containsKey(var2) || ((Integer)this.playingSoundsStopTime.get(var2)).intValue() > this.playTime)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopSound(ISound p_148602_1_) {
/* 359 */     if (this.loaded) {
/*     */       
/* 361 */       String var2 = (String)this.invPlayingSounds.get(p_148602_1_);
/*     */       
/* 363 */       if (var2 != null)
/*     */       {
/* 365 */         this.sndSystem.stop(var2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playSound(ISound p_148611_1_) {
/* 372 */     if (this.loaded)
/*     */     {
/* 374 */       if (this.sndSystem.getMasterVolume() <= 0.0F) {
/*     */         
/* 376 */         logger.debug(LOG_MARKER, "Skipped playing soundEvent: {}, master volume was zero", new Object[] { p_148611_1_.getSoundLocation() });
/*     */       }
/*     */       else {
/*     */         
/* 380 */         SoundEventAccessorComposite var2 = this.sndHandler.getSound(p_148611_1_.getSoundLocation());
/*     */         
/* 382 */         if (var2 == null) {
/*     */           
/* 384 */           logger.warn(LOG_MARKER, "Unable to play unknown soundEvent: {}", new Object[] { p_148611_1_.getSoundLocation() });
/*     */         }
/*     */         else {
/*     */           
/* 388 */           SoundPoolEntry var3 = (SoundPoolEntry)var2.cloneEntry();
/*     */           
/* 390 */           if (var3 == SoundHandler.missing_sound) {
/*     */             
/* 392 */             logger.warn(LOG_MARKER, "Unable to play empty soundEvent: {}", new Object[] { var2.getSoundEventLocation() });
/*     */           }
/*     */           else {
/*     */             
/* 396 */             float var4 = p_148611_1_.getVolume();
/* 397 */             float var5 = 16.0F;
/*     */             
/* 399 */             if (var4 > 1.0F)
/*     */             {
/* 401 */               var5 *= var4;
/*     */             }
/*     */             
/* 404 */             SoundCategory var6 = var2.getSoundCategory();
/* 405 */             float var7 = getNormalizedVolume(p_148611_1_, var3, var6);
/* 406 */             double var8 = getNormalizedPitch(p_148611_1_, var3);
/* 407 */             ResourceLocation var10 = var3.getSoundPoolEntryLocation();
/*     */             
/* 409 */             if (var7 == 0.0F) {
/*     */               
/* 411 */               logger.debug(LOG_MARKER, "Skipped playing sound {}, volume was zero.", new Object[] { var10 });
/*     */             }
/*     */             else {
/*     */               
/* 415 */               boolean var11 = (p_148611_1_.canRepeat() && p_148611_1_.getRepeatDelay() == 0);
/* 416 */               String var12 = MathHelper.func_180182_a((Random)ThreadLocalRandom.current()).toString();
/*     */               
/* 418 */               if (var3.isStreamingSound()) {
/*     */                 
/* 420 */                 this.sndSystem.newStreamingSource(false, var12, getURLForSoundResource(var10), var10.toString(), var11, p_148611_1_.getXPosF(), p_148611_1_.getYPosF(), p_148611_1_.getZPosF(), p_148611_1_.getAttenuationType().getTypeInt(), var5);
/*     */               }
/*     */               else {
/*     */                 
/* 424 */                 this.sndSystem.newSource(false, var12, getURLForSoundResource(var10), var10.toString(), var11, p_148611_1_.getXPosF(), p_148611_1_.getYPosF(), p_148611_1_.getZPosF(), p_148611_1_.getAttenuationType().getTypeInt(), var5);
/*     */               } 
/*     */               
/* 427 */               logger.debug(LOG_MARKER, "Playing sound {} for event {} as channel {}", new Object[] { var3.getSoundPoolEntryLocation(), var2.getSoundEventLocation(), var12 });
/* 428 */               this.sndSystem.setPitch(var12, (float)var8);
/* 429 */               this.sndSystem.setVolume(var12, var7);
/* 430 */               this.sndSystem.play(var12);
/* 431 */               this.playingSoundsStopTime.put(var12, Integer.valueOf(this.playTime + 20));
/* 432 */               this.playingSounds.put(var12, p_148611_1_);
/* 433 */               this.playingSoundPoolEntries.put(p_148611_1_, var3);
/*     */               
/* 435 */               if (var6 != SoundCategory.MASTER)
/*     */               {
/* 437 */                 this.categorySounds.put(var6, var12);
/*     */               }
/*     */               
/* 440 */               if (p_148611_1_ instanceof ITickableSound)
/*     */               {
/* 442 */                 this.tickableSounds.add((ITickableSound)p_148611_1_);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getNormalizedPitch(ISound p_148606_1_, SoundPoolEntry p_148606_2_) {
/* 456 */     return (float)MathHelper.clamp_double(p_148606_1_.getPitch() * p_148606_2_.getPitch(), 0.5D, 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getNormalizedVolume(ISound p_148594_1_, SoundPoolEntry p_148594_2_, SoundCategory p_148594_3_) {
/* 464 */     return (float)MathHelper.clamp_double(p_148594_1_.getVolume() * p_148594_2_.getVolume(), 0.0D, 1.0D) * getSoundCategoryVolume(p_148594_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseAllSounds() {
/* 472 */     Iterator<String> var1 = this.playingSounds.keySet().iterator();
/*     */     
/* 474 */     while (var1.hasNext()) {
/*     */       
/* 476 */       String var2 = var1.next();
/* 477 */       logger.debug(LOG_MARKER, "Pausing channel {}", new Object[] { var2 });
/* 478 */       this.sndSystem.pause(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumeAllSounds() {
/* 487 */     Iterator<String> var1 = this.playingSounds.keySet().iterator();
/*     */     
/* 489 */     while (var1.hasNext()) {
/*     */       
/* 491 */       String var2 = var1.next();
/* 492 */       logger.debug(LOG_MARKER, "Resuming channel {}", new Object[] { var2 });
/* 493 */       this.sndSystem.play(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playDelayedSound(ISound p_148599_1_, int p_148599_2_) {
/* 502 */     this.delayedSounds.put(p_148599_1_, Integer.valueOf(this.playTime + p_148599_2_));
/*     */   }
/*     */ 
/*     */   
/*     */   private static URL getURLForSoundResource(final ResourceLocation p_148612_0_) {
/* 507 */     String var1 = String.format("%s:%s:%s", new Object[] { "mcsounddomain", p_148612_0_.getResourceDomain(), p_148612_0_.getResourcePath() });
/* 508 */     URLStreamHandler var2 = new URLStreamHandler()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001143";
/*     */         
/*     */         protected URLConnection openConnection(URL p_openConnection_1_) {
/* 513 */           return new URLConnection(p_openConnection_1_) {
/*     */               private static final String __OBFID = "CL_00001144";
/*     */               
/*     */               public void connect() {}
/*     */               
/*     */               public InputStream getInputStream() throws IOException {
/* 519 */                 return Minecraft.getMinecraft().getResourceManager().getResource(p_148612_0_).getInputStream();
/*     */               }
/*     */             };
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*     */     try {
/* 527 */       return new URL(null, var1, var2);
/*     */     }
/* 529 */     catch (MalformedURLException var4) {
/*     */       
/* 531 */       throw new Error("TODO: Sanely handle url exception! :D");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListener(EntityPlayer p_148615_1_, float p_148615_2_) {
/* 540 */     if (this.loaded && p_148615_1_ != null) {
/*     */       
/* 542 */       float var3 = p_148615_1_.prevRotationPitch + (p_148615_1_.rotationPitch - p_148615_1_.prevRotationPitch) * p_148615_2_;
/* 543 */       float var4 = p_148615_1_.prevRotationYaw + (p_148615_1_.rotationYaw - p_148615_1_.prevRotationYaw) * p_148615_2_;
/* 544 */       double var5 = p_148615_1_.prevPosX + (p_148615_1_.posX - p_148615_1_.prevPosX) * p_148615_2_;
/* 545 */       double var7 = p_148615_1_.prevPosY + (p_148615_1_.posY - p_148615_1_.prevPosY) * p_148615_2_ + p_148615_1_.getEyeHeight();
/* 546 */       double var9 = p_148615_1_.prevPosZ + (p_148615_1_.posZ - p_148615_1_.prevPosZ) * p_148615_2_;
/* 547 */       float var11 = MathHelper.cos((var4 + 90.0F) * 0.017453292F);
/* 548 */       float var12 = MathHelper.sin((var4 + 90.0F) * 0.017453292F);
/* 549 */       float var13 = MathHelper.cos(-var3 * 0.017453292F);
/* 550 */       float var14 = MathHelper.sin(-var3 * 0.017453292F);
/* 551 */       float var15 = MathHelper.cos((-var3 + 90.0F) * 0.017453292F);
/* 552 */       float var16 = MathHelper.sin((-var3 + 90.0F) * 0.017453292F);
/* 553 */       float var17 = var11 * var13;
/* 554 */       float var19 = var12 * var13;
/* 555 */       float var20 = var11 * var15;
/* 556 */       float var22 = var12 * var15;
/* 557 */       this.sndSystem.setListenerPosition((float)var5, (float)var7, (float)var9);
/* 558 */       this.sndSystem.setListenerOrientation(var17, var14, var19, var20, var16, var22);
/*     */     } 
/*     */   }
/*     */   
/*     */   class SoundSystemStarterThread
/*     */     extends SoundSystem
/*     */   {
/*     */     private static final String __OBFID = "CL_00001145";
/*     */     
/*     */     private SoundSystemStarterThread() {}
/*     */     
/*     */     public boolean playing(String p_playing_1_) {
/* 570 */       Object var2 = SoundSystemConfig.THREAD_SYNC;
/*     */       
/* 572 */       synchronized (SoundSystemConfig.THREAD_SYNC) {
/*     */         
/* 574 */         if (this.soundLibrary == null)
/*     */         {
/* 576 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 580 */         Source var3 = (Source)this.soundLibrary.getSources().get(p_playing_1_);
/* 581 */         return (var3 == null) ? false : (!(!var3.playing() && !var3.paused() && !var3.preLoad));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     SoundSystemStarterThread(Object p_i45118_2_) {
/* 588 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */