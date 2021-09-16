/*     */ package net.minecraft.entity;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
/*     */ import net.minecraft.entity.item.EntityFallingBlock;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.item.EntityPainting;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.entity.projectile.EntityFireball;
/*     */ import net.minecraft.entity.projectile.EntityFishHook;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S04PacketEntityEquipment;
/*     */ import net.minecraft.network.play.server.S0APacketUseBed;
/*     */ import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
/*     */ import net.minecraft.network.play.server.S0EPacketSpawnObject;
/*     */ import net.minecraft.network.play.server.S0FPacketSpawnMob;
/*     */ import net.minecraft.network.play.server.S10PacketSpawnPainting;
/*     */ import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
/*     */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*     */ import net.minecraft.network.play.server.S14PacketEntity;
/*     */ import net.minecraft.network.play.server.S18PacketEntityTeleport;
/*     */ import net.minecraft.network.play.server.S19PacketEntityHeadLook;
/*     */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*     */ import net.minecraft.network.play.server.S1CPacketEntityMetadata;
/*     */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*     */ import net.minecraft.network.play.server.S20PacketEntityProperties;
/*     */ import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.storage.MapData;
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
/*     */ public class EntityTrackerEntry
/*     */ {
/*  65 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   public Entity trackedEntity;
/*     */   
/*     */   public int trackingDistanceThreshold;
/*     */   
/*     */   public int updateFrequency;
/*     */   
/*     */   public int encodedPosX;
/*     */   
/*     */   public int encodedPosY;
/*     */   
/*     */   public int encodedPosZ;
/*     */   
/*     */   public int encodedRotationYaw;
/*     */   
/*     */   public int encodedRotationPitch;
/*     */   
/*     */   public int lastHeadMotion;
/*     */   
/*     */   public double lastTrackedEntityMotionX;
/*     */   
/*     */   public double lastTrackedEntityMotionY;
/*     */   
/*     */   public double motionZ;
/*     */   
/*     */   public int updateCounter;
/*     */   
/*     */   private double lastTrackedEntityPosX;
/*     */   
/*     */   private double lastTrackedEntityPosY;
/*     */   
/*     */   private double lastTrackedEntityPosZ;
/*     */   
/*     */   private boolean firstUpdateDone;
/*     */   
/*     */   private boolean sendVelocityUpdates;
/*     */   
/*     */   private int ticksSinceLastForcedTeleport;
/*     */   
/*     */   private Entity field_85178_v;
/*     */   
/*     */   private boolean ridingEntity;
/*     */   
/*     */   private boolean field_180234_y;
/*     */   
/*     */   public boolean playerEntitiesUpdated;
/* 112 */   public Set trackingPlayers = Sets.newHashSet();
/*     */   
/*     */   private static final String __OBFID = "CL_00001443";
/*     */   
/*     */   public EntityTrackerEntry(Entity p_i1525_1_, int p_i1525_2_, int p_i1525_3_, boolean p_i1525_4_) {
/* 117 */     this.trackedEntity = p_i1525_1_;
/* 118 */     this.trackingDistanceThreshold = p_i1525_2_;
/* 119 */     this.updateFrequency = p_i1525_3_;
/* 120 */     this.sendVelocityUpdates = p_i1525_4_;
/* 121 */     this.encodedPosX = MathHelper.floor_double(p_i1525_1_.posX * 32.0D);
/* 122 */     this.encodedPosY = MathHelper.floor_double(p_i1525_1_.posY * 32.0D);
/* 123 */     this.encodedPosZ = MathHelper.floor_double(p_i1525_1_.posZ * 32.0D);
/* 124 */     this.encodedRotationYaw = MathHelper.floor_float(p_i1525_1_.rotationYaw * 256.0F / 360.0F);
/* 125 */     this.encodedRotationPitch = MathHelper.floor_float(p_i1525_1_.rotationPitch * 256.0F / 360.0F);
/* 126 */     this.lastHeadMotion = MathHelper.floor_float(p_i1525_1_.getRotationYawHead() * 256.0F / 360.0F);
/* 127 */     this.field_180234_y = p_i1525_1_.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 132 */     return (p_equals_1_ instanceof EntityTrackerEntry) ? ((((EntityTrackerEntry)p_equals_1_).trackedEntity.getEntityId() == this.trackedEntity.getEntityId())) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 137 */     return this.trackedEntity.getEntityId();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePlayerList(List p_73122_1_) {
/* 142 */     this.playerEntitiesUpdated = false;
/*     */     
/* 144 */     if (!this.firstUpdateDone || this.trackedEntity.getDistanceSq(this.lastTrackedEntityPosX, this.lastTrackedEntityPosY, this.lastTrackedEntityPosZ) > 16.0D) {
/*     */       
/* 146 */       this.lastTrackedEntityPosX = this.trackedEntity.posX;
/* 147 */       this.lastTrackedEntityPosY = this.trackedEntity.posY;
/* 148 */       this.lastTrackedEntityPosZ = this.trackedEntity.posZ;
/* 149 */       this.firstUpdateDone = true;
/* 150 */       this.playerEntitiesUpdated = true;
/* 151 */       updatePlayerEntities(p_73122_1_);
/*     */     } 
/*     */     
/* 154 */     if (this.field_85178_v != this.trackedEntity.ridingEntity || (this.trackedEntity.ridingEntity != null && this.updateCounter % 60 == 0)) {
/*     */       
/* 156 */       this.field_85178_v = this.trackedEntity.ridingEntity;
/* 157 */       func_151259_a((Packet)new S1BPacketEntityAttach(0, this.trackedEntity, this.trackedEntity.ridingEntity));
/*     */     } 
/*     */     
/* 160 */     if (this.trackedEntity instanceof EntityItemFrame && this.updateCounter % 10 == 0) {
/*     */       
/* 162 */       EntityItemFrame var2 = (EntityItemFrame)this.trackedEntity;
/* 163 */       ItemStack var3 = var2.getDisplayedItem();
/*     */       
/* 165 */       if (var3 != null && var3.getItem() instanceof net.minecraft.item.ItemMap) {
/*     */         
/* 167 */         MapData var4 = Items.filled_map.getMapData(var3, this.trackedEntity.worldObj);
/* 168 */         Iterator<EntityPlayer> var5 = p_73122_1_.iterator();
/*     */         
/* 170 */         while (var5.hasNext()) {
/*     */           
/* 172 */           EntityPlayer var6 = var5.next();
/* 173 */           EntityPlayerMP var7 = (EntityPlayerMP)var6;
/* 174 */           var4.updateVisiblePlayers((EntityPlayer)var7, var3);
/* 175 */           Packet var8 = Items.filled_map.createMapDataPacket(var3, this.trackedEntity.worldObj, (EntityPlayer)var7);
/*     */           
/* 177 */           if (var8 != null)
/*     */           {
/* 179 */             var7.playerNetServerHandler.sendPacket(var8);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 184 */       sendMetadataToAllAssociatedPlayers();
/*     */     } 
/*     */     
/* 187 */     if (this.updateCounter % this.updateFrequency == 0 || this.trackedEntity.isAirBorne || this.trackedEntity.getDataWatcher().hasObjectChanged()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (this.trackedEntity.ridingEntity == null) {
/*     */         
/* 194 */         this.ticksSinceLastForcedTeleport++;
/* 195 */         int var23 = MathHelper.floor_double(this.trackedEntity.posX * 32.0D);
/* 196 */         int var24 = MathHelper.floor_double(this.trackedEntity.posY * 32.0D);
/* 197 */         int var25 = MathHelper.floor_double(this.trackedEntity.posZ * 32.0D);
/* 198 */         int var27 = MathHelper.floor_float(this.trackedEntity.rotationYaw * 256.0F / 360.0F);
/* 199 */         int var28 = MathHelper.floor_float(this.trackedEntity.rotationPitch * 256.0F / 360.0F);
/* 200 */         int var29 = var23 - this.encodedPosX;
/* 201 */         int var30 = var24 - this.encodedPosY;
/* 202 */         int var9 = var25 - this.encodedPosZ;
/* 203 */         Object var10 = null;
/* 204 */         boolean var11 = !(Math.abs(var29) < 4 && Math.abs(var30) < 4 && Math.abs(var9) < 4 && this.updateCounter % 60 != 0);
/* 205 */         boolean var12 = !(Math.abs(var27 - this.encodedRotationYaw) < 4 && Math.abs(var28 - this.encodedRotationPitch) < 4);
/*     */         
/* 207 */         if (this.updateCounter > 0 || this.trackedEntity instanceof EntityArrow)
/*     */         {
/* 209 */           if (var29 >= -128 && var29 < 128 && var30 >= -128 && var30 < 128 && var9 >= -128 && var9 < 128 && this.ticksSinceLastForcedTeleport <= 400 && !this.ridingEntity && this.field_180234_y == this.trackedEntity.onGround) {
/*     */             
/* 211 */             if (var11 && var12)
/*     */             {
/* 213 */               var10 = new S14PacketEntity.S17PacketEntityLookMove(this.trackedEntity.getEntityId(), (byte)var29, (byte)var30, (byte)var9, (byte)var27, (byte)var28, this.trackedEntity.onGround);
/*     */             }
/* 215 */             else if (var11)
/*     */             {
/* 217 */               var10 = new S14PacketEntity.S15PacketEntityRelMove(this.trackedEntity.getEntityId(), (byte)var29, (byte)var30, (byte)var9, this.trackedEntity.onGround);
/*     */             }
/* 219 */             else if (var12)
/*     */             {
/* 221 */               var10 = new S14PacketEntity.S16PacketEntityLook(this.trackedEntity.getEntityId(), (byte)var27, (byte)var28, this.trackedEntity.onGround);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 226 */             this.field_180234_y = this.trackedEntity.onGround;
/* 227 */             this.ticksSinceLastForcedTeleport = 0;
/* 228 */             var10 = new S18PacketEntityTeleport(this.trackedEntity.getEntityId(), var23, var24, var25, (byte)var27, (byte)var28, this.trackedEntity.onGround);
/*     */           } 
/*     */         }
/*     */         
/* 232 */         if (this.sendVelocityUpdates) {
/*     */           
/* 234 */           double var13 = this.trackedEntity.motionX - this.lastTrackedEntityMotionX;
/* 235 */           double var15 = this.trackedEntity.motionY - this.lastTrackedEntityMotionY;
/* 236 */           double var17 = this.trackedEntity.motionZ - this.motionZ;
/* 237 */           double var19 = 0.02D;
/* 238 */           double var21 = var13 * var13 + var15 * var15 + var17 * var17;
/*     */           
/* 240 */           if (var21 > var19 * var19 || (var21 > 0.0D && this.trackedEntity.motionX == 0.0D && this.trackedEntity.motionY == 0.0D && this.trackedEntity.motionZ == 0.0D)) {
/*     */             
/* 242 */             this.lastTrackedEntityMotionX = this.trackedEntity.motionX;
/* 243 */             this.lastTrackedEntityMotionY = this.trackedEntity.motionY;
/* 244 */             this.motionZ = this.trackedEntity.motionZ;
/* 245 */             func_151259_a((Packet)new S12PacketEntityVelocity(this.trackedEntity.getEntityId(), this.lastTrackedEntityMotionX, this.lastTrackedEntityMotionY, this.motionZ));
/*     */           } 
/*     */         } 
/*     */         
/* 249 */         if (var10 != null)
/*     */         {
/* 251 */           func_151259_a((Packet)var10);
/*     */         }
/*     */         
/* 254 */         sendMetadataToAllAssociatedPlayers();
/*     */         
/* 256 */         if (var11) {
/*     */           
/* 258 */           this.encodedPosX = var23;
/* 259 */           this.encodedPosY = var24;
/* 260 */           this.encodedPosZ = var25;
/*     */         } 
/*     */         
/* 263 */         if (var12) {
/*     */           
/* 265 */           this.encodedRotationYaw = var27;
/* 266 */           this.encodedRotationPitch = var28;
/*     */         } 
/*     */         
/* 269 */         this.ridingEntity = false;
/*     */       }
/*     */       else {
/*     */         
/* 273 */         int var23 = MathHelper.floor_float(this.trackedEntity.rotationYaw * 256.0F / 360.0F);
/* 274 */         int var24 = MathHelper.floor_float(this.trackedEntity.rotationPitch * 256.0F / 360.0F);
/* 275 */         boolean var26 = !(Math.abs(var23 - this.encodedRotationYaw) < 4 && Math.abs(var24 - this.encodedRotationPitch) < 4);
/*     */         
/* 277 */         if (var26) {
/*     */           
/* 279 */           func_151259_a((Packet)new S14PacketEntity.S16PacketEntityLook(this.trackedEntity.getEntityId(), (byte)var23, (byte)var24, this.trackedEntity.onGround));
/* 280 */           this.encodedRotationYaw = var23;
/* 281 */           this.encodedRotationPitch = var24;
/*     */         } 
/*     */         
/* 284 */         this.encodedPosX = MathHelper.floor_double(this.trackedEntity.posX * 32.0D);
/* 285 */         this.encodedPosY = MathHelper.floor_double(this.trackedEntity.posY * 32.0D);
/* 286 */         this.encodedPosZ = MathHelper.floor_double(this.trackedEntity.posZ * 32.0D);
/* 287 */         sendMetadataToAllAssociatedPlayers();
/* 288 */         this.ridingEntity = true;
/*     */       } 
/*     */       
/* 291 */       int i = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
/*     */       
/* 293 */       if (Math.abs(i - this.lastHeadMotion) >= 4) {
/*     */         
/* 295 */         func_151259_a((Packet)new S19PacketEntityHeadLook(this.trackedEntity, (byte)i));
/* 296 */         this.lastHeadMotion = i;
/*     */       } 
/*     */       
/* 299 */       this.trackedEntity.isAirBorne = false;
/*     */     } 
/*     */     
/* 302 */     this.updateCounter++;
/*     */     
/* 304 */     if (this.trackedEntity.velocityChanged) {
/*     */       
/* 306 */       func_151261_b((Packet)new S12PacketEntityVelocity(this.trackedEntity));
/* 307 */       this.trackedEntity.velocityChanged = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendMetadataToAllAssociatedPlayers() {
/* 317 */     DataWatcher var1 = this.trackedEntity.getDataWatcher();
/*     */     
/* 319 */     if (var1.hasObjectChanged())
/*     */     {
/* 321 */       func_151261_b((Packet)new S1CPacketEntityMetadata(this.trackedEntity.getEntityId(), var1, false));
/*     */     }
/*     */     
/* 324 */     if (this.trackedEntity instanceof EntityLivingBase) {
/*     */       
/* 326 */       ServersideAttributeMap var2 = (ServersideAttributeMap)((EntityLivingBase)this.trackedEntity).getAttributeMap();
/* 327 */       Set var3 = var2.getAttributeInstanceSet();
/*     */       
/* 329 */       if (!var3.isEmpty())
/*     */       {
/* 331 */         func_151261_b((Packet)new S20PacketEntityProperties(this.trackedEntity.getEntityId(), var3));
/*     */       }
/*     */       
/* 334 */       var3.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_151259_a(Packet p_151259_1_) {
/* 340 */     Iterator<EntityPlayerMP> var2 = this.trackingPlayers.iterator();
/*     */     
/* 342 */     while (var2.hasNext()) {
/*     */       
/* 344 */       EntityPlayerMP var3 = var2.next();
/* 345 */       var3.playerNetServerHandler.sendPacket(p_151259_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_151261_b(Packet p_151261_1_) {
/* 351 */     func_151259_a(p_151261_1_);
/*     */     
/* 353 */     if (this.trackedEntity instanceof EntityPlayerMP)
/*     */     {
/* 355 */       ((EntityPlayerMP)this.trackedEntity).playerNetServerHandler.sendPacket(p_151261_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendDestroyEntityPacketToTrackedPlayers() {
/* 361 */     Iterator<EntityPlayerMP> var1 = this.trackingPlayers.iterator();
/*     */     
/* 363 */     while (var1.hasNext()) {
/*     */       
/* 365 */       EntityPlayerMP var2 = var1.next();
/* 366 */       var2.func_152339_d(this.trackedEntity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFromTrackedPlayers(EntityPlayerMP p_73118_1_) {
/* 372 */     if (this.trackingPlayers.contains(p_73118_1_)) {
/*     */       
/* 374 */       p_73118_1_.func_152339_d(this.trackedEntity);
/* 375 */       this.trackingPlayers.remove(p_73118_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePlayerEntity(EntityPlayerMP p_73117_1_) {
/* 381 */     if (p_73117_1_ != this.trackedEntity)
/*     */     {
/* 383 */       if (func_180233_c(p_73117_1_)) {
/*     */         
/* 385 */         if (!this.trackingPlayers.contains(p_73117_1_) && (isPlayerWatchingThisChunk(p_73117_1_) || this.trackedEntity.forceSpawn)) {
/*     */           
/* 387 */           this.trackingPlayers.add(p_73117_1_);
/* 388 */           Packet var2 = func_151260_c();
/* 389 */           p_73117_1_.playerNetServerHandler.sendPacket(var2);
/*     */           
/* 391 */           if (!this.trackedEntity.getDataWatcher().getIsBlank())
/*     */           {
/* 393 */             p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S1CPacketEntityMetadata(this.trackedEntity.getEntityId(), this.trackedEntity.getDataWatcher(), true));
/*     */           }
/*     */           
/* 396 */           NBTTagCompound var3 = this.trackedEntity.func_174819_aU();
/*     */           
/* 398 */           if (var3 != null)
/*     */           {
/* 400 */             p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S49PacketUpdateEntityNBT(this.trackedEntity.getEntityId(), var3));
/*     */           }
/*     */           
/* 403 */           if (this.trackedEntity instanceof EntityLivingBase) {
/*     */             
/* 405 */             ServersideAttributeMap var4 = (ServersideAttributeMap)((EntityLivingBase)this.trackedEntity).getAttributeMap();
/* 406 */             Collection var5 = var4.getWatchedAttributes();
/*     */             
/* 408 */             if (!var5.isEmpty())
/*     */             {
/* 410 */               p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S20PacketEntityProperties(this.trackedEntity.getEntityId(), var5));
/*     */             }
/*     */           } 
/*     */           
/* 414 */           this.lastTrackedEntityMotionX = this.trackedEntity.motionX;
/* 415 */           this.lastTrackedEntityMotionY = this.trackedEntity.motionY;
/* 416 */           this.motionZ = this.trackedEntity.motionZ;
/*     */           
/* 418 */           if (this.sendVelocityUpdates && !(var2 instanceof S0FPacketSpawnMob))
/*     */           {
/* 420 */             p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S12PacketEntityVelocity(this.trackedEntity.getEntityId(), this.trackedEntity.motionX, this.trackedEntity.motionY, this.trackedEntity.motionZ));
/*     */           }
/*     */           
/* 423 */           if (this.trackedEntity.ridingEntity != null)
/*     */           {
/* 425 */             p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S1BPacketEntityAttach(0, this.trackedEntity, this.trackedEntity.ridingEntity));
/*     */           }
/*     */           
/* 428 */           if (this.trackedEntity instanceof EntityLiving && ((EntityLiving)this.trackedEntity).getLeashedToEntity() != null)
/*     */           {
/* 430 */             p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S1BPacketEntityAttach(1, this.trackedEntity, ((EntityLiving)this.trackedEntity).getLeashedToEntity()));
/*     */           }
/*     */           
/* 433 */           if (this.trackedEntity instanceof EntityLivingBase)
/*     */           {
/* 435 */             for (int var7 = 0; var7 < 5; var7++) {
/*     */               
/* 437 */               ItemStack var10 = ((EntityLivingBase)this.trackedEntity).getEquipmentInSlot(var7);
/*     */               
/* 439 */               if (var10 != null)
/*     */               {
/* 441 */                 p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S04PacketEntityEquipment(this.trackedEntity.getEntityId(), var7, var10));
/*     */               }
/*     */             } 
/*     */           }
/*     */           
/* 446 */           if (this.trackedEntity instanceof EntityPlayer) {
/*     */             
/* 448 */             EntityPlayer var8 = (EntityPlayer)this.trackedEntity;
/*     */             
/* 450 */             if (var8.isPlayerSleeping())
/*     */             {
/* 452 */               p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S0APacketUseBed(var8, new BlockPos(this.trackedEntity)));
/*     */             }
/*     */           } 
/*     */           
/* 456 */           if (this.trackedEntity instanceof EntityLivingBase) {
/*     */             
/* 458 */             EntityLivingBase var9 = (EntityLivingBase)this.trackedEntity;
/* 459 */             Iterator<PotionEffect> var11 = var9.getActivePotionEffects().iterator();
/*     */             
/* 461 */             while (var11.hasNext())
/*     */             {
/* 463 */               PotionEffect var6 = var11.next();
/* 464 */               p_73117_1_.playerNetServerHandler.sendPacket((Packet)new S1DPacketEntityEffect(this.trackedEntity.getEntityId(), var6));
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/* 469 */       } else if (this.trackingPlayers.contains(p_73117_1_)) {
/*     */         
/* 471 */         this.trackingPlayers.remove(p_73117_1_);
/* 472 */         p_73117_1_.func_152339_d(this.trackedEntity);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180233_c(EntityPlayerMP p_180233_1_) {
/* 479 */     double var2 = p_180233_1_.posX - (this.encodedPosX / 32);
/* 480 */     double var4 = p_180233_1_.posZ - (this.encodedPosZ / 32);
/* 481 */     return (var2 >= -this.trackingDistanceThreshold && var2 <= this.trackingDistanceThreshold && var4 >= -this.trackingDistanceThreshold && var4 <= this.trackingDistanceThreshold && this.trackedEntity.func_174827_a(p_180233_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPlayerWatchingThisChunk(EntityPlayerMP p_73121_1_) {
/* 486 */     return p_73121_1_.getServerForPlayer().getPlayerManager().isPlayerWatchingChunk(p_73121_1_, this.trackedEntity.chunkCoordX, this.trackedEntity.chunkCoordZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePlayerEntities(List<EntityPlayerMP> p_73125_1_) {
/* 491 */     for (int var2 = 0; var2 < p_73125_1_.size(); var2++)
/*     */     {
/* 493 */       updatePlayerEntity(p_73125_1_.get(var2));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private Packet func_151260_c() {
/* 499 */     if (this.trackedEntity.isDead)
/*     */     {
/* 501 */       logger.warn("Fetching addPacket for removed entity");
/*     */     }
/*     */     
/* 504 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityItem)
/*     */     {
/* 506 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 2, 1);
/*     */     }
/* 508 */     if (this.trackedEntity instanceof EntityPlayerMP)
/*     */     {
/* 510 */       return (Packet)new S0CPacketSpawnPlayer((EntityPlayer)this.trackedEntity);
/*     */     }
/* 512 */     if (this.trackedEntity instanceof EntityMinecart) {
/*     */       
/* 514 */       EntityMinecart var9 = (EntityMinecart)this.trackedEntity;
/* 515 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 10, var9.func_180456_s().func_180039_a());
/*     */     } 
/* 517 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityBoat)
/*     */     {
/* 519 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 1);
/*     */     }
/* 521 */     if (this.trackedEntity instanceof net.minecraft.entity.passive.IAnimals) {
/*     */       
/* 523 */       this.lastHeadMotion = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
/* 524 */       return (Packet)new S0FPacketSpawnMob((EntityLivingBase)this.trackedEntity);
/*     */     } 
/* 526 */     if (this.trackedEntity instanceof EntityFishHook) {
/*     */       
/* 528 */       EntityPlayer var8 = ((EntityFishHook)this.trackedEntity).angler;
/* 529 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 90, (var8 != null) ? var8.getEntityId() : this.trackedEntity.getEntityId());
/*     */     } 
/* 531 */     if (this.trackedEntity instanceof EntityArrow) {
/*     */       
/* 533 */       Entity var7 = ((EntityArrow)this.trackedEntity).shootingEntity;
/* 534 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 60, (var7 != null) ? var7.getEntityId() : this.trackedEntity.getEntityId());
/*     */     } 
/* 536 */     if (this.trackedEntity instanceof net.minecraft.entity.projectile.EntitySnowball)
/*     */     {
/* 538 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 61);
/*     */     }
/* 540 */     if (this.trackedEntity instanceof EntityPotion)
/*     */     {
/* 542 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 73, ((EntityPotion)this.trackedEntity).getPotionDamage());
/*     */     }
/* 544 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityExpBottle)
/*     */     {
/* 546 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 75);
/*     */     }
/* 548 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityEnderPearl)
/*     */     {
/* 550 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 65);
/*     */     }
/* 552 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityEnderEye)
/*     */     {
/* 554 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 72);
/*     */     }
/* 556 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityFireworkRocket)
/*     */     {
/* 558 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 76);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 564 */     if (this.trackedEntity instanceof EntityFireball) {
/*     */       
/* 566 */       EntityFireball var6 = (EntityFireball)this.trackedEntity;
/* 567 */       S0EPacketSpawnObject var2 = null;
/* 568 */       byte var10 = 63;
/*     */       
/* 570 */       if (this.trackedEntity instanceof net.minecraft.entity.projectile.EntitySmallFireball) {
/*     */         
/* 572 */         var10 = 64;
/*     */       }
/* 574 */       else if (this.trackedEntity instanceof net.minecraft.entity.projectile.EntityWitherSkull) {
/*     */         
/* 576 */         var10 = 66;
/*     */       } 
/*     */       
/* 579 */       if (var6.shootingEntity != null) {
/*     */         
/* 581 */         var2 = new S0EPacketSpawnObject(this.trackedEntity, var10, ((EntityFireball)this.trackedEntity).shootingEntity.getEntityId());
/*     */       }
/*     */       else {
/*     */         
/* 585 */         var2 = new S0EPacketSpawnObject(this.trackedEntity, var10, 0);
/*     */       } 
/*     */       
/* 588 */       var2.func_149003_d((int)(var6.accelerationX * 8000.0D));
/* 589 */       var2.func_149000_e((int)(var6.accelerationY * 8000.0D));
/* 590 */       var2.func_149007_f((int)(var6.accelerationZ * 8000.0D));
/* 591 */       return (Packet)var2;
/*     */     } 
/* 593 */     if (this.trackedEntity instanceof net.minecraft.entity.projectile.EntityEgg)
/*     */     {
/* 595 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 62);
/*     */     }
/* 597 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityTNTPrimed)
/*     */     {
/* 599 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 50);
/*     */     }
/* 601 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityEnderCrystal)
/*     */     {
/* 603 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 51);
/*     */     }
/* 605 */     if (this.trackedEntity instanceof EntityFallingBlock) {
/*     */       
/* 607 */       EntityFallingBlock var5 = (EntityFallingBlock)this.trackedEntity;
/* 608 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 70, Block.getStateId(var5.getBlock()));
/*     */     } 
/* 610 */     if (this.trackedEntity instanceof net.minecraft.entity.item.EntityArmorStand)
/*     */     {
/* 612 */       return (Packet)new S0EPacketSpawnObject(this.trackedEntity, 78);
/*     */     }
/* 614 */     if (this.trackedEntity instanceof EntityPainting)
/*     */     {
/* 616 */       return (Packet)new S10PacketSpawnPainting((EntityPainting)this.trackedEntity);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 622 */     if (this.trackedEntity instanceof EntityItemFrame) {
/*     */       
/* 624 */       EntityItemFrame var4 = (EntityItemFrame)this.trackedEntity;
/* 625 */       S0EPacketSpawnObject var2 = new S0EPacketSpawnObject(this.trackedEntity, 71, var4.field_174860_b.getHorizontalIndex());
/* 626 */       BlockPos var3 = var4.func_174857_n();
/* 627 */       var2.func_148996_a(MathHelper.floor_float((var3.getX() * 32)));
/* 628 */       var2.func_148995_b(MathHelper.floor_float((var3.getY() * 32)));
/* 629 */       var2.func_149005_c(MathHelper.floor_float((var3.getZ() * 32)));
/* 630 */       return (Packet)var2;
/*     */     } 
/* 632 */     if (this.trackedEntity instanceof EntityLeashKnot) {
/*     */       
/* 634 */       EntityLeashKnot var1 = (EntityLeashKnot)this.trackedEntity;
/* 635 */       S0EPacketSpawnObject var2 = new S0EPacketSpawnObject(this.trackedEntity, 77);
/* 636 */       BlockPos var3 = var1.func_174857_n();
/* 637 */       var2.func_148996_a(MathHelper.floor_float((var3.getX() * 32)));
/* 638 */       var2.func_148995_b(MathHelper.floor_float((var3.getY() * 32)));
/* 639 */       var2.func_149005_c(MathHelper.floor_float((var3.getZ() * 32)));
/* 640 */       return (Packet)var2;
/*     */     } 
/* 642 */     if (this.trackedEntity instanceof EntityXPOrb)
/*     */     {
/* 644 */       return (Packet)new S11PacketSpawnExperienceOrb((EntityXPOrb)this.trackedEntity);
/*     */     }
/*     */ 
/*     */     
/* 648 */     throw new IllegalArgumentException("Don't know how to add " + this.trackedEntity.getClass() + "!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTrackedPlayerSymmetric(EntityPlayerMP p_73123_1_) {
/* 659 */     if (this.trackingPlayers.contains(p_73123_1_)) {
/*     */       
/* 661 */       this.trackingPlayers.remove(p_73123_1_);
/* 662 */       p_73123_1_.func_152339_d(this.trackedEntity);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityTrackerEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */