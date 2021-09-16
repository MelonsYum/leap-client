/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.util.IntHashMap;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityTracker
/*     */ {
/*  46 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private final WorldServer theWorld;
/*     */ 
/*     */   
/*  52 */   private Set trackedEntities = Sets.newHashSet();
/*     */ 
/*     */   
/*  55 */   private IntHashMap trackedEntityHashTable = new IntHashMap();
/*     */   
/*     */   private int maxTrackingDistanceThreshold;
/*     */   private static final String __OBFID = "CL_00001431";
/*     */   
/*     */   public EntityTracker(WorldServer p_i1516_1_) {
/*  61 */     this.theWorld = p_i1516_1_;
/*  62 */     this.maxTrackingDistanceThreshold = p_i1516_1_.func_73046_m().getConfigurationManager().getEntityViewDistance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackEntity(Entity p_72786_1_) {
/*  67 */     if (p_72786_1_ instanceof EntityPlayerMP) {
/*     */       
/*  69 */       trackEntity(p_72786_1_, 512, 2);
/*  70 */       EntityPlayerMP var2 = (EntityPlayerMP)p_72786_1_;
/*  71 */       Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
/*     */       
/*  73 */       while (var3.hasNext())
/*     */       {
/*  75 */         EntityTrackerEntry var4 = var3.next();
/*     */         
/*  77 */         if (var4.trackedEntity != var2)
/*     */         {
/*  79 */           var4.updatePlayerEntity(var2);
/*     */         }
/*     */       }
/*     */     
/*  83 */     } else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntityFishHook) {
/*     */       
/*  85 */       addEntityToTracker(p_72786_1_, 64, 5, true);
/*     */     }
/*  87 */     else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntityArrow) {
/*     */       
/*  89 */       addEntityToTracker(p_72786_1_, 64, 20, false);
/*     */     }
/*  91 */     else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntitySmallFireball) {
/*     */       
/*  93 */       addEntityToTracker(p_72786_1_, 64, 10, false);
/*     */     }
/*  95 */     else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntityFireball) {
/*     */       
/*  97 */       addEntityToTracker(p_72786_1_, 64, 10, false);
/*     */     }
/*  99 */     else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntitySnowball) {
/*     */       
/* 101 */       addEntityToTracker(p_72786_1_, 64, 10, true);
/*     */     }
/* 103 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityEnderPearl) {
/*     */       
/* 105 */       addEntityToTracker(p_72786_1_, 64, 10, true);
/*     */     }
/* 107 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityEnderEye) {
/*     */       
/* 109 */       addEntityToTracker(p_72786_1_, 64, 4, true);
/*     */     }
/* 111 */     else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntityEgg) {
/*     */       
/* 113 */       addEntityToTracker(p_72786_1_, 64, 10, true);
/*     */     }
/* 115 */     else if (p_72786_1_ instanceof net.minecraft.entity.projectile.EntityPotion) {
/*     */       
/* 117 */       addEntityToTracker(p_72786_1_, 64, 10, true);
/*     */     }
/* 119 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityExpBottle) {
/*     */       
/* 121 */       addEntityToTracker(p_72786_1_, 64, 10, true);
/*     */     }
/* 123 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityFireworkRocket) {
/*     */       
/* 125 */       addEntityToTracker(p_72786_1_, 64, 10, true);
/*     */     }
/* 127 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityItem) {
/*     */       
/* 129 */       addEntityToTracker(p_72786_1_, 64, 20, true);
/*     */     }
/* 131 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityMinecart) {
/*     */       
/* 133 */       addEntityToTracker(p_72786_1_, 80, 3, true);
/*     */     }
/* 135 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityBoat) {
/*     */       
/* 137 */       addEntityToTracker(p_72786_1_, 80, 3, true);
/*     */     }
/* 139 */     else if (p_72786_1_ instanceof net.minecraft.entity.passive.EntitySquid) {
/*     */       
/* 141 */       addEntityToTracker(p_72786_1_, 64, 3, true);
/*     */     }
/* 143 */     else if (p_72786_1_ instanceof net.minecraft.entity.boss.EntityWither) {
/*     */       
/* 145 */       addEntityToTracker(p_72786_1_, 80, 3, false);
/*     */     }
/* 147 */     else if (p_72786_1_ instanceof net.minecraft.entity.passive.EntityBat) {
/*     */       
/* 149 */       addEntityToTracker(p_72786_1_, 80, 3, false);
/*     */     }
/* 151 */     else if (p_72786_1_ instanceof net.minecraft.entity.boss.EntityDragon) {
/*     */       
/* 153 */       addEntityToTracker(p_72786_1_, 160, 3, true);
/*     */     }
/* 155 */     else if (p_72786_1_ instanceof net.minecraft.entity.passive.IAnimals) {
/*     */       
/* 157 */       addEntityToTracker(p_72786_1_, 80, 3, true);
/*     */     }
/* 159 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityTNTPrimed) {
/*     */       
/* 161 */       addEntityToTracker(p_72786_1_, 160, 10, true);
/*     */     }
/* 163 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityFallingBlock) {
/*     */       
/* 165 */       addEntityToTracker(p_72786_1_, 160, 20, true);
/*     */     }
/* 167 */     else if (p_72786_1_ instanceof EntityHanging) {
/*     */       
/* 169 */       addEntityToTracker(p_72786_1_, 160, 2147483647, false);
/*     */     }
/* 171 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityArmorStand) {
/*     */       
/* 173 */       addEntityToTracker(p_72786_1_, 160, 3, true);
/*     */     }
/* 175 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityXPOrb) {
/*     */       
/* 177 */       addEntityToTracker(p_72786_1_, 160, 20, true);
/*     */     }
/* 179 */     else if (p_72786_1_ instanceof net.minecraft.entity.item.EntityEnderCrystal) {
/*     */       
/* 181 */       addEntityToTracker(p_72786_1_, 256, 2147483647, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void trackEntity(Entity p_72791_1_, int p_72791_2_, int p_72791_3_) {
/* 187 */     addEntityToTracker(p_72791_1_, p_72791_2_, p_72791_3_, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntityToTracker(Entity p_72785_1_, int p_72785_2_, final int p_72785_3_, boolean p_72785_4_) {
/* 195 */     if (p_72785_2_ > this.maxTrackingDistanceThreshold)
/*     */     {
/* 197 */       p_72785_2_ = this.maxTrackingDistanceThreshold;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 202 */       if (this.trackedEntityHashTable.containsItem(p_72785_1_.getEntityId()))
/*     */       {
/* 204 */         throw new IllegalStateException("Entity is already tracked!");
/*     */       }
/*     */       
/* 207 */       EntityTrackerEntry var5 = new EntityTrackerEntry(p_72785_1_, p_72785_2_, p_72785_3_, p_72785_4_);
/* 208 */       this.trackedEntities.add(var5);
/* 209 */       this.trackedEntityHashTable.addKey(p_72785_1_.getEntityId(), var5);
/* 210 */       var5.updatePlayerEntities(this.theWorld.playerEntities);
/*     */     }
/* 212 */     catch (Throwable var11) {
/*     */       
/* 214 */       CrashReport var6 = CrashReport.makeCrashReport(var11, "Adding entity to track");
/* 215 */       CrashReportCategory var7 = var6.makeCategory("Entity To Track");
/* 216 */       var7.addCrashSection("Tracking range", String.valueOf(p_72785_2_) + " blocks");
/* 217 */       var7.addCrashSectionCallable("Update interval", new Callable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001432";
/*     */             
/*     */             public String call() {
/* 222 */               String var1 = "Once per " + p_72785_3_ + " ticks";
/*     */               
/* 224 */               if (p_72785_3_ == Integer.MAX_VALUE)
/*     */               {
/* 226 */                 var1 = "Maximum (" + var1 + ")";
/*     */               }
/*     */               
/* 229 */               return var1;
/*     */             }
/*     */           });
/* 232 */       p_72785_1_.addEntityCrashInfo(var7);
/* 233 */       CrashReportCategory var8 = var6.makeCategory("Entity That Is Already Tracked");
/* 234 */       ((EntityTrackerEntry)this.trackedEntityHashTable.lookup(p_72785_1_.getEntityId())).trackedEntity.addEntityCrashInfo(var8);
/*     */ 
/*     */       
/*     */       try {
/* 238 */         throw new ReportedException(var6);
/*     */       }
/* 240 */       catch (ReportedException var10) {
/*     */         
/* 242 */         logger.error("\"Silently\" catching entity tracking error.", (Throwable)var10);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void untrackEntity(Entity p_72790_1_) {
/* 249 */     if (p_72790_1_ instanceof EntityPlayerMP) {
/*     */       
/* 251 */       EntityPlayerMP var2 = (EntityPlayerMP)p_72790_1_;
/* 252 */       Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
/*     */       
/* 254 */       while (var3.hasNext()) {
/*     */         
/* 256 */         EntityTrackerEntry var4 = var3.next();
/* 257 */         var4.removeFromTrackedPlayers(var2);
/*     */       } 
/*     */     } 
/*     */     
/* 261 */     EntityTrackerEntry var5 = (EntityTrackerEntry)this.trackedEntityHashTable.removeObject(p_72790_1_.getEntityId());
/*     */     
/* 263 */     if (var5 != null) {
/*     */       
/* 265 */       this.trackedEntities.remove(var5);
/* 266 */       var5.sendDestroyEntityPacketToTrackedPlayers();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTrackedEntities() {
/* 272 */     ArrayList<EntityPlayerMP> var1 = Lists.newArrayList();
/* 273 */     Iterator<EntityTrackerEntry> var2 = this.trackedEntities.iterator();
/*     */     
/* 275 */     while (var2.hasNext()) {
/*     */       
/* 277 */       EntityTrackerEntry var3 = var2.next();
/* 278 */       var3.updatePlayerList(this.theWorld.playerEntities);
/*     */       
/* 280 */       if (var3.playerEntitiesUpdated && var3.trackedEntity instanceof EntityPlayerMP)
/*     */       {
/* 282 */         var1.add((EntityPlayerMP)var3.trackedEntity);
/*     */       }
/*     */     } 
/*     */     
/* 286 */     for (int var6 = 0; var6 < var1.size(); var6++) {
/*     */       
/* 288 */       EntityPlayerMP var7 = var1.get(var6);
/* 289 */       Iterator<EntityTrackerEntry> var4 = this.trackedEntities.iterator();
/*     */       
/* 291 */       while (var4.hasNext()) {
/*     */         
/* 293 */         EntityTrackerEntry var5 = var4.next();
/*     */         
/* 295 */         if (var5.trackedEntity != var7)
/*     */         {
/* 297 */           var5.updatePlayerEntity(var7);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180245_a(EntityPlayerMP p_180245_1_) {
/* 305 */     Iterator<EntityTrackerEntry> var2 = this.trackedEntities.iterator();
/*     */     
/* 307 */     while (var2.hasNext()) {
/*     */       
/* 309 */       EntityTrackerEntry var3 = var2.next();
/*     */       
/* 311 */       if (var3.trackedEntity == p_180245_1_) {
/*     */         
/* 313 */         var3.updatePlayerEntities(this.theWorld.playerEntities);
/*     */         
/*     */         continue;
/*     */       } 
/* 317 */       var3.updatePlayerEntity(p_180245_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToAllTrackingEntity(Entity p_151247_1_, Packet p_151247_2_) {
/* 324 */     EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(p_151247_1_.getEntityId());
/*     */     
/* 326 */     if (var3 != null)
/*     */     {
/* 328 */       var3.func_151259_a(p_151247_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_151248_b(Entity p_151248_1_, Packet p_151248_2_) {
/* 334 */     EntityTrackerEntry var3 = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(p_151248_1_.getEntityId());
/*     */     
/* 336 */     if (var3 != null)
/*     */     {
/* 338 */       var3.func_151261_b(p_151248_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayerFromTrackers(EntityPlayerMP p_72787_1_) {
/* 344 */     Iterator<EntityTrackerEntry> var2 = this.trackedEntities.iterator();
/*     */     
/* 346 */     while (var2.hasNext()) {
/*     */       
/* 348 */       EntityTrackerEntry var3 = var2.next();
/* 349 */       var3.removeTrackedPlayerSymmetric(p_72787_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_85172_a(EntityPlayerMP p_85172_1_, Chunk p_85172_2_) {
/* 355 */     Iterator<EntityTrackerEntry> var3 = this.trackedEntities.iterator();
/*     */     
/* 357 */     while (var3.hasNext()) {
/*     */       
/* 359 */       EntityTrackerEntry var4 = var3.next();
/*     */       
/* 361 */       if (var4.trackedEntity != p_85172_1_ && var4.trackedEntity.chunkCoordX == p_85172_2_.xPosition && var4.trackedEntity.chunkCoordZ == p_85172_2_.zPosition)
/*     */       {
/* 363 */         var4.updatePlayerEntity(p_85172_1_);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */