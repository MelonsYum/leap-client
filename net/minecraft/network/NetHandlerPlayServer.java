/*      */ package net.minecraft.network;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.util.concurrent.Futures;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import io.netty.util.concurrent.Future;
/*      */ import io.netty.util.concurrent.GenericFutureListener;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.Future;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.command.ICommandSender;
/*      */ import net.minecraft.command.server.CommandBlockLogic;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.ContainerBeacon;
/*      */ import net.minecraft.inventory.ContainerMerchant;
/*      */ import net.minecraft.inventory.ContainerRepair;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.Slot;
/*      */ import net.minecraft.item.ItemEditableBook;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.ItemWritableBook;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagString;
/*      */ import net.minecraft.network.play.INetHandlerPlayServer;
/*      */ import net.minecraft.network.play.client.C00PacketKeepAlive;
/*      */ import net.minecraft.network.play.client.C01PacketChatMessage;
/*      */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*      */ import net.minecraft.network.play.client.C03PacketPlayer;
/*      */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*      */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*      */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*      */ import net.minecraft.network.play.client.C0APacketAnimation;
/*      */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*      */ import net.minecraft.network.play.client.C0CPacketInput;
/*      */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*      */ import net.minecraft.network.play.client.C0EPacketClickWindow;
/*      */ import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
/*      */ import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
/*      */ import net.minecraft.network.play.client.C11PacketEnchantItem;
/*      */ import net.minecraft.network.play.client.C12PacketUpdateSign;
/*      */ import net.minecraft.network.play.client.C13PacketPlayerAbilities;
/*      */ import net.minecraft.network.play.client.C14PacketTabComplete;
/*      */ import net.minecraft.network.play.client.C15PacketClientSettings;
/*      */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*      */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*      */ import net.minecraft.network.play.client.C18PacketSpectate;
/*      */ import net.minecraft.network.play.client.C19PacketResourcePackStatus;
/*      */ import net.minecraft.network.play.server.S00PacketKeepAlive;
/*      */ import net.minecraft.network.play.server.S02PacketChat;
/*      */ import net.minecraft.network.play.server.S07PacketRespawn;
/*      */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*      */ import net.minecraft.network.play.server.S18PacketEntityTeleport;
/*      */ import net.minecraft.network.play.server.S23PacketBlockChange;
/*      */ import net.minecraft.network.play.server.S2FPacketSetSlot;
/*      */ import net.minecraft.network.play.server.S32PacketConfirmTransaction;
/*      */ import net.minecraft.network.play.server.S3APacketTabComplete;
/*      */ import net.minecraft.network.play.server.S40PacketDisconnect;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.gui.IUpdatePlayerListBox;
/*      */ import net.minecraft.server.management.UserListBansEntry;
/*      */ import net.minecraft.server.management.UserListEntry;
/*      */ import net.minecraft.stats.AchievementList;
/*      */ import net.minecraft.stats.StatBase;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.tileentity.TileEntityCommandBlock;
/*      */ import net.minecraft.tileentity.TileEntitySign;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatAllowedCharacters;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.IThreadListener;
/*      */ import net.minecraft.util.IntHashMap;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import org.apache.commons.lang3.StringUtils;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public class NetHandlerPlayServer implements INetHandlerPlayServer, IUpdatePlayerListBox {
/*   98 */   private static final Logger logger = LogManager.getLogger();
/*      */   
/*      */   public final NetworkManager netManager;
/*      */   
/*      */   private final MinecraftServer serverController;
/*      */   
/*      */   public EntityPlayerMP playerEntity;
/*      */   
/*      */   private int networkTickCount;
/*      */   
/*      */   private int field_175090_f;
/*      */   
/*      */   private int floatingTickCount;
/*      */   
/*      */   private boolean field_147366_g;
/*      */   
/*      */   private int field_147378_h;
/*      */   
/*      */   private long lastPingTime;
/*      */   
/*      */   private long lastSentPingPacket;
/*      */   private int chatSpamThresholdCount;
/*      */   private int itemDropThreshold;
/*  121 */   private IntHashMap field_147372_n = new IntHashMap();
/*      */   
/*      */   private double lastPosX;
/*      */   private double lastPosY;
/*      */   private double lastPosZ;
/*      */   private boolean hasMoved = true;
/*      */   private static final String __OBFID = "CL_00001452";
/*      */   
/*      */   public NetHandlerPlayServer(MinecraftServer server, NetworkManager networkManagerIn, EntityPlayerMP playerIn) {
/*  130 */     this.serverController = server;
/*  131 */     this.netManager = networkManagerIn;
/*  132 */     networkManagerIn.setNetHandler((INetHandler)this);
/*  133 */     this.playerEntity = playerIn;
/*  134 */     playerIn.playerNetServerHandler = this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  142 */     this.field_147366_g = false;
/*  143 */     this.networkTickCount++;
/*  144 */     this.serverController.theProfiler.startSection("keepAlive");
/*      */     
/*  146 */     if (this.networkTickCount - this.lastSentPingPacket > 40L) {
/*      */       
/*  148 */       this.lastSentPingPacket = this.networkTickCount;
/*  149 */       this.lastPingTime = currentTimeMillis();
/*  150 */       this.field_147378_h = (int)this.lastPingTime;
/*  151 */       sendPacket((Packet)new S00PacketKeepAlive(this.field_147378_h));
/*      */     } 
/*      */     
/*  154 */     this.serverController.theProfiler.endSection();
/*      */     
/*  156 */     if (this.chatSpamThresholdCount > 0)
/*      */     {
/*  158 */       this.chatSpamThresholdCount--;
/*      */     }
/*      */     
/*  161 */     if (this.itemDropThreshold > 0)
/*      */     {
/*  163 */       this.itemDropThreshold--;
/*      */     }
/*      */     
/*  166 */     if (this.playerEntity.getLastActiveTime() > 0L && this.serverController.getMaxPlayerIdleMinutes() > 0 && MinecraftServer.getCurrentTimeMillis() - this.playerEntity.getLastActiveTime() > (this.serverController.getMaxPlayerIdleMinutes() * 1000 * 60))
/*      */     {
/*  168 */       kickPlayerFromServer("You have been idle for too long!");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public NetworkManager getNetworkManager() {
/*  174 */     return this.netManager;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void kickPlayerFromServer(String reason) {
/*  182 */     final ChatComponentText var2 = new ChatComponentText(reason);
/*  183 */     this.netManager.sendPacket((Packet)new S40PacketDisconnect((IChatComponent)var2), new GenericFutureListener()
/*      */         {
/*      */           private static final String __OBFID = "CL_00001453";
/*      */           
/*      */           public void operationComplete(Future p_operationComplete_1_) {
/*  188 */             NetHandlerPlayServer.this.netManager.closeChannel((IChatComponent)var2);
/*      */           }
/*  190 */         },  new GenericFutureListener[0]);
/*  191 */     this.netManager.disableAutoRead();
/*  192 */     Futures.getUnchecked((Future)this.serverController.addScheduledTask(new Runnable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00001454";
/*      */             
/*      */             public void run() {
/*  197 */               NetHandlerPlayServer.this.netManager.checkDisconnected();
/*      */             }
/*      */           }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processInput(C0CPacketInput packetIn) {
/*  208 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  209 */     this.playerEntity.setEntityActionState(packetIn.getStrafeSpeed(), packetIn.getForwardSpeed(), packetIn.isJumping(), packetIn.isSneaking());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processPlayer(C03PacketPlayer packetIn) {
/*  217 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  218 */     WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.dimension);
/*  219 */     this.field_147366_g = true;
/*      */     
/*  221 */     if (!this.playerEntity.playerConqueredTheEnd) {
/*      */       
/*  223 */       double var3 = this.playerEntity.posX;
/*  224 */       double var5 = this.playerEntity.posY;
/*  225 */       double var7 = this.playerEntity.posZ;
/*  226 */       double var9 = 0.0D;
/*  227 */       double var11 = packetIn.getPositionX() - this.lastPosX;
/*  228 */       double var13 = packetIn.getPositionY() - this.lastPosY;
/*  229 */       double var15 = packetIn.getPositionZ() - this.lastPosZ;
/*      */       
/*  231 */       if (packetIn.func_149466_j()) {
/*      */         
/*  233 */         var9 = var11 * var11 + var13 * var13 + var15 * var15;
/*      */         
/*  235 */         if (!this.hasMoved && var9 < 0.25D)
/*      */         {
/*  237 */           this.hasMoved = true;
/*      */         }
/*      */       } 
/*      */       
/*  241 */       if (this.hasMoved) {
/*      */         
/*  243 */         this.field_175090_f = this.networkTickCount;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  248 */         if (this.playerEntity.ridingEntity != null) {
/*      */           
/*  250 */           float var47 = this.playerEntity.rotationYaw;
/*  251 */           float var18 = this.playerEntity.rotationPitch;
/*  252 */           this.playerEntity.ridingEntity.updateRiderPosition();
/*  253 */           double d1 = this.playerEntity.posX;
/*  254 */           double d2 = this.playerEntity.posY;
/*  255 */           double d3 = this.playerEntity.posZ;
/*      */           
/*  257 */           if (packetIn.getRotating()) {
/*      */             
/*  259 */             var47 = packetIn.getYaw();
/*  260 */             var18 = packetIn.getPitch();
/*      */           } 
/*      */           
/*  263 */           this.playerEntity.onGround = packetIn.func_149465_i();
/*  264 */           this.playerEntity.onUpdateEntity();
/*  265 */           this.playerEntity.setPositionAndRotation(d1, d2, d3, var47, var18);
/*      */           
/*  267 */           if (this.playerEntity.ridingEntity != null)
/*      */           {
/*  269 */             this.playerEntity.ridingEntity.updateRiderPosition();
/*      */           }
/*      */           
/*  272 */           this.serverController.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
/*      */           
/*  274 */           if (this.playerEntity.ridingEntity != null) {
/*      */             
/*  276 */             if (var9 > 4.0D) {
/*      */               
/*  278 */               Entity var48 = this.playerEntity.ridingEntity;
/*  279 */               this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S18PacketEntityTeleport(var48));
/*  280 */               setPlayerLocation(this.playerEntity.posX, this.playerEntity.posY, this.playerEntity.posZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*      */             } 
/*      */             
/*  283 */             this.playerEntity.ridingEntity.isAirBorne = true;
/*      */           } 
/*      */           
/*  286 */           if (this.hasMoved) {
/*      */             
/*  288 */             this.lastPosX = this.playerEntity.posX;
/*  289 */             this.lastPosY = this.playerEntity.posY;
/*  290 */             this.lastPosZ = this.playerEntity.posZ;
/*      */           } 
/*      */           
/*  293 */           var2.updateEntity((Entity)this.playerEntity);
/*      */           
/*      */           return;
/*      */         } 
/*  297 */         if (this.playerEntity.isPlayerSleeping()) {
/*      */           
/*  299 */           this.playerEntity.onUpdateEntity();
/*  300 */           this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*  301 */           var2.updateEntity((Entity)this.playerEntity);
/*      */           
/*      */           return;
/*      */         } 
/*  305 */         double var17 = this.playerEntity.posY;
/*  306 */         this.lastPosX = this.playerEntity.posX;
/*  307 */         this.lastPosY = this.playerEntity.posY;
/*  308 */         this.lastPosZ = this.playerEntity.posZ;
/*  309 */         double var19 = this.playerEntity.posX;
/*  310 */         double var21 = this.playerEntity.posY;
/*  311 */         double var23 = this.playerEntity.posZ;
/*  312 */         float var25 = this.playerEntity.rotationYaw;
/*  313 */         float var26 = this.playerEntity.rotationPitch;
/*      */         
/*  315 */         if (packetIn.func_149466_j() && packetIn.getPositionY() == -999.0D)
/*      */         {
/*  317 */           packetIn.func_149469_a(false);
/*      */         }
/*      */         
/*  320 */         if (packetIn.func_149466_j()) {
/*      */           
/*  322 */           var19 = packetIn.getPositionX();
/*  323 */           var21 = packetIn.getPositionY();
/*  324 */           var23 = packetIn.getPositionZ();
/*      */           
/*  326 */           if (Math.abs(packetIn.getPositionX()) > 3.0E7D || Math.abs(packetIn.getPositionZ()) > 3.0E7D) {
/*      */             
/*  328 */             kickPlayerFromServer("Illegal position");
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*  333 */         if (packetIn.getRotating()) {
/*      */           
/*  335 */           var25 = packetIn.getYaw();
/*  336 */           var26 = packetIn.getPitch();
/*      */         } 
/*      */         
/*  339 */         this.playerEntity.onUpdateEntity();
/*  340 */         this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, var25, var26);
/*      */         
/*  342 */         if (!this.hasMoved) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  347 */         double var27 = var19 - this.playerEntity.posX;
/*  348 */         double var29 = var21 - this.playerEntity.posY;
/*  349 */         double var31 = var23 - this.playerEntity.posZ;
/*  350 */         double var33 = Math.min(Math.abs(var27), Math.abs(this.playerEntity.motionX));
/*  351 */         double var35 = Math.min(Math.abs(var29), Math.abs(this.playerEntity.motionY));
/*  352 */         double var37 = Math.min(Math.abs(var31), Math.abs(this.playerEntity.motionZ));
/*  353 */         double var39 = var33 * var33 + var35 * var35 + var37 * var37;
/*      */         
/*  355 */         if (var39 > 100.0D && (!this.serverController.isSinglePlayer() || !this.serverController.getServerOwner().equals(this.playerEntity.getName()))) {
/*      */           
/*  357 */           logger.warn(String.valueOf(this.playerEntity.getName()) + " moved too quickly! " + var27 + "," + var29 + "," + var31 + " (" + var33 + ", " + var35 + ", " + var37 + ")");
/*  358 */           setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*      */           
/*      */           return;
/*      */         } 
/*  362 */         float var41 = 0.0625F;
/*  363 */         boolean var42 = var2.getCollidingBoundingBoxes((Entity)this.playerEntity, this.playerEntity.getEntityBoundingBox().contract(var41, var41, var41)).isEmpty();
/*      */         
/*  365 */         if (this.playerEntity.onGround && !packetIn.func_149465_i() && var29 > 0.0D)
/*      */         {
/*  367 */           this.playerEntity.jump();
/*      */         }
/*      */         
/*  370 */         this.playerEntity.moveEntity(var27, var29, var31);
/*  371 */         this.playerEntity.onGround = packetIn.func_149465_i();
/*  372 */         double var43 = var29;
/*  373 */         var27 = var19 - this.playerEntity.posX;
/*  374 */         var29 = var21 - this.playerEntity.posY;
/*      */         
/*  376 */         if (var29 > -0.5D || var29 < 0.5D)
/*      */         {
/*  378 */           var29 = 0.0D;
/*      */         }
/*      */         
/*  381 */         var31 = var23 - this.playerEntity.posZ;
/*  382 */         var39 = var27 * var27 + var29 * var29 + var31 * var31;
/*  383 */         boolean var45 = false;
/*      */         
/*  385 */         if (var39 > 0.0625D && !this.playerEntity.isPlayerSleeping() && !this.playerEntity.theItemInWorldManager.isCreative()) {
/*      */           
/*  387 */           var45 = true;
/*  388 */           logger.warn(String.valueOf(this.playerEntity.getName()) + " moved wrongly!");
/*      */         } 
/*      */         
/*  391 */         this.playerEntity.setPositionAndRotation(var19, var21, var23, var25, var26);
/*  392 */         this.playerEntity.addMovementStat(this.playerEntity.posX - var3, this.playerEntity.posY - var5, this.playerEntity.posZ - var7);
/*      */         
/*  394 */         if (!this.playerEntity.noClip) {
/*      */           
/*  396 */           boolean var46 = var2.getCollidingBoundingBoxes((Entity)this.playerEntity, this.playerEntity.getEntityBoundingBox().contract(var41, var41, var41)).isEmpty();
/*      */           
/*  398 */           if (var42 && (var45 || !var46) && !this.playerEntity.isPlayerSleeping()) {
/*      */             
/*  400 */             setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, var25, var26);
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*  405 */         AxisAlignedBB var49 = this.playerEntity.getEntityBoundingBox().expand(var41, var41, var41).addCoord(0.0D, -0.55D, 0.0D);
/*      */         
/*  407 */         if (!this.serverController.isFlightAllowed() && !this.playerEntity.capabilities.allowFlying && !var2.checkBlockCollision(var49)) {
/*      */           
/*  409 */           if (var43 >= -0.03125D) {
/*      */             
/*  411 */             this.floatingTickCount++;
/*      */             
/*  413 */             if (this.floatingTickCount > 80) {
/*      */               
/*  415 */               logger.warn(String.valueOf(this.playerEntity.getName()) + " was kicked for floating too long!");
/*  416 */               kickPlayerFromServer("Flying is not enabled on this server");
/*      */ 
/*      */               
/*      */               return;
/*      */             } 
/*      */           } 
/*      */         } else {
/*  423 */           this.floatingTickCount = 0;
/*      */         } 
/*      */         
/*  426 */         this.playerEntity.onGround = packetIn.func_149465_i();
/*  427 */         this.serverController.getConfigurationManager().serverUpdateMountedMovingPlayer(this.playerEntity);
/*  428 */         this.playerEntity.handleFalling(this.playerEntity.posY - var17, packetIn.func_149465_i());
/*      */       }
/*  430 */       else if (this.networkTickCount - this.field_175090_f > 20) {
/*      */         
/*  432 */         setPlayerLocation(this.lastPosX, this.lastPosY, this.lastPosZ, this.playerEntity.rotationYaw, this.playerEntity.rotationPitch);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPlayerLocation(double x, double y, double z, float yaw, float pitch) {
/*  439 */     func_175089_a(x, y, z, yaw, pitch, Collections.emptySet());
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175089_a(double p_175089_1_, double p_175089_3_, double p_175089_5_, float p_175089_7_, float p_175089_8_, Set p_175089_9_) {
/*  444 */     this.hasMoved = false;
/*  445 */     this.lastPosX = p_175089_1_;
/*  446 */     this.lastPosY = p_175089_3_;
/*  447 */     this.lastPosZ = p_175089_5_;
/*      */     
/*  449 */     if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.X))
/*      */     {
/*  451 */       this.lastPosX += this.playerEntity.posX;
/*      */     }
/*      */     
/*  454 */     if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Y))
/*      */     {
/*  456 */       this.lastPosY += this.playerEntity.posY;
/*      */     }
/*      */     
/*  459 */     if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Z))
/*      */     {
/*  461 */       this.lastPosZ += this.playerEntity.posZ;
/*      */     }
/*      */     
/*  464 */     float var10 = p_175089_7_;
/*  465 */     float var11 = p_175089_8_;
/*      */     
/*  467 */     if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT))
/*      */     {
/*  469 */       var10 = p_175089_7_ + this.playerEntity.rotationYaw;
/*      */     }
/*      */     
/*  472 */     if (p_175089_9_.contains(S08PacketPlayerPosLook.EnumFlags.X_ROT))
/*      */     {
/*  474 */       var11 = p_175089_8_ + this.playerEntity.rotationPitch;
/*      */     }
/*      */     
/*  477 */     this.playerEntity.setPositionAndRotation(this.lastPosX, this.lastPosY, this.lastPosZ, var10, var11);
/*  478 */     this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S08PacketPlayerPosLook(p_175089_1_, p_175089_3_, p_175089_5_, p_175089_7_, p_175089_8_, p_175089_9_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processPlayerDigging(C07PacketPlayerDigging packetIn) {
/*      */     double var4, var6, var8, var10;
/*  488 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  489 */     WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.dimension);
/*  490 */     BlockPos var3 = packetIn.func_179715_a();
/*  491 */     this.playerEntity.markPlayerActive();
/*      */     
/*  493 */     switch (SwitchAction.field_180224_a[packetIn.func_180762_c().ordinal()]) {
/*      */       
/*      */       case 1:
/*  496 */         if (!this.playerEntity.func_175149_v())
/*      */         {
/*  498 */           this.playerEntity.dropOneItem(false);
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2:
/*  504 */         if (!this.playerEntity.func_175149_v())
/*      */         {
/*  506 */           this.playerEntity.dropOneItem(true);
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case 3:
/*  512 */         this.playerEntity.stopUsingItem();
/*      */         return;
/*      */       
/*      */       case 4:
/*      */       case 5:
/*      */       case 6:
/*  518 */         var4 = this.playerEntity.posX - var3.getX() + 0.5D;
/*  519 */         var6 = this.playerEntity.posY - var3.getY() + 0.5D + 1.5D;
/*  520 */         var8 = this.playerEntity.posZ - var3.getZ() + 0.5D;
/*  521 */         var10 = var4 * var4 + var6 * var6 + var8 * var8;
/*      */         
/*  523 */         if (var10 > 36.0D) {
/*      */           return;
/*      */         }
/*      */         
/*  527 */         if (var3.getY() >= this.serverController.getBuildLimit()) {
/*      */           return;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  533 */         if (packetIn.func_180762_c() == C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
/*      */           
/*  535 */           if (!this.serverController.isBlockProtected((World)var2, var3, (EntityPlayer)this.playerEntity) && var2.getWorldBorder().contains(var3))
/*      */           {
/*  537 */             this.playerEntity.theItemInWorldManager.func_180784_a(var3, packetIn.func_179714_b());
/*      */           }
/*      */           else
/*      */           {
/*  541 */             this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange((World)var2, var3));
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  546 */           if (packetIn.func_180762_c() == C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
/*      */             
/*  548 */             this.playerEntity.theItemInWorldManager.func_180785_a(var3);
/*      */           }
/*  550 */           else if (packetIn.func_180762_c() == C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
/*      */             
/*  552 */             this.playerEntity.theItemInWorldManager.func_180238_e();
/*      */           } 
/*      */           
/*  555 */           if (var2.getBlockState(var3).getBlock().getMaterial() != Material.air)
/*      */           {
/*  557 */             this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange((World)var2, var3));
/*      */           }
/*      */         } 
/*      */         return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  565 */     throw new IllegalArgumentException("Invalid player action");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processPlayerBlockPlacement(C08PacketPlayerBlockPlacement packetIn) {
/*  574 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  575 */     WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.dimension);
/*  576 */     ItemStack var3 = this.playerEntity.inventory.getCurrentItem();
/*  577 */     boolean var4 = false;
/*  578 */     BlockPos var5 = packetIn.func_179724_a();
/*  579 */     EnumFacing var6 = EnumFacing.getFront(packetIn.getPlacedBlockDirection());
/*  580 */     this.playerEntity.markPlayerActive();
/*      */     
/*  582 */     if (packetIn.getPlacedBlockDirection() == 255) {
/*      */       
/*  584 */       if (var3 == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  589 */       this.playerEntity.theItemInWorldManager.tryUseItem((EntityPlayer)this.playerEntity, (World)var2, var3);
/*      */     }
/*  591 */     else if (var5.getY() >= this.serverController.getBuildLimit() - 1 && (var6 == EnumFacing.UP || var5.getY() >= this.serverController.getBuildLimit())) {
/*      */       
/*  593 */       ChatComponentTranslation var7 = new ChatComponentTranslation("build.tooHigh", new Object[] { Integer.valueOf(this.serverController.getBuildLimit()) });
/*  594 */       var7.getChatStyle().setColor(EnumChatFormatting.RED);
/*  595 */       this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S02PacketChat((IChatComponent)var7));
/*  596 */       var4 = true;
/*      */     }
/*      */     else {
/*      */       
/*  600 */       if (this.hasMoved && this.playerEntity.getDistanceSq(var5.getX() + 0.5D, var5.getY() + 0.5D, var5.getZ() + 0.5D) < 64.0D && !this.serverController.isBlockProtected((World)var2, var5, (EntityPlayer)this.playerEntity) && var2.getWorldBorder().contains(var5))
/*      */       {
/*  602 */         this.playerEntity.theItemInWorldManager.func_180236_a((EntityPlayer)this.playerEntity, (World)var2, var3, var5, var6, packetIn.getPlacedBlockOffsetX(), packetIn.getPlacedBlockOffsetY(), packetIn.getPlacedBlockOffsetZ());
/*      */       }
/*      */       
/*  605 */       var4 = true;
/*      */     } 
/*      */     
/*  608 */     if (var4) {
/*      */       
/*  610 */       this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange((World)var2, var5));
/*  611 */       this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange((World)var2, var5.offset(var6)));
/*      */     } 
/*      */     
/*  614 */     var3 = this.playerEntity.inventory.getCurrentItem();
/*      */     
/*  616 */     if (var3 != null && var3.stackSize == 0) {
/*      */       
/*  618 */       this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = null;
/*  619 */       var3 = null;
/*      */     } 
/*      */     
/*  622 */     if (var3 == null || var3.getMaxItemUseDuration() == 0) {
/*      */       
/*  624 */       this.playerEntity.isChangingQuantityOnly = true;
/*  625 */       this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem] = ItemStack.copyItemStack(this.playerEntity.inventory.mainInventory[this.playerEntity.inventory.currentItem]);
/*  626 */       Slot var8 = this.playerEntity.openContainer.getSlotFromInventory((IInventory)this.playerEntity.inventory, this.playerEntity.inventory.currentItem);
/*  627 */       this.playerEntity.openContainer.detectAndSendChanges();
/*  628 */       this.playerEntity.isChangingQuantityOnly = false;
/*      */       
/*  630 */       if (!ItemStack.areItemStacksEqual(this.playerEntity.inventory.getCurrentItem(), packetIn.getStack()))
/*      */       {
/*  632 */         sendPacket((Packet)new S2FPacketSetSlot(this.playerEntity.openContainer.windowId, var8.slotNumber, this.playerEntity.inventory.getCurrentItem()));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175088_a(C18PacketSpectate p_175088_1_) {
/*  639 */     PacketThreadUtil.func_180031_a((Packet)p_175088_1_, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*      */     
/*  641 */     if (this.playerEntity.func_175149_v()) {
/*      */       
/*  643 */       Entity var2 = null;
/*  644 */       WorldServer[] var3 = this.serverController.worldServers;
/*  645 */       int var4 = var3.length;
/*      */       
/*  647 */       for (int var5 = 0; var5 < var4; var5++) {
/*      */         
/*  649 */         WorldServer var6 = var3[var5];
/*      */         
/*  651 */         if (var6 != null) {
/*      */           
/*  653 */           var2 = p_175088_1_.func_179727_a(var6);
/*      */           
/*  655 */           if (var2 != null) {
/*      */             break;
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  662 */       if (var2 != null) {
/*      */         
/*  664 */         this.playerEntity.func_175399_e((Entity)this.playerEntity);
/*  665 */         this.playerEntity.mountEntity(null);
/*      */         
/*  667 */         if (var2.worldObj != this.playerEntity.worldObj) {
/*      */           
/*  669 */           WorldServer var7 = this.playerEntity.getServerForPlayer();
/*  670 */           WorldServer var8 = (WorldServer)var2.worldObj;
/*  671 */           this.playerEntity.dimension = var2.dimension;
/*  672 */           sendPacket((Packet)new S07PacketRespawn(this.playerEntity.dimension, var7.getDifficulty(), var7.getWorldInfo().getTerrainType(), this.playerEntity.theItemInWorldManager.getGameType()));
/*  673 */           var7.removePlayerEntityDangerously((Entity)this.playerEntity);
/*  674 */           this.playerEntity.isDead = false;
/*  675 */           this.playerEntity.setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
/*      */           
/*  677 */           if (this.playerEntity.isEntityAlive()) {
/*      */             
/*  679 */             var7.updateEntityWithOptionalForce((Entity)this.playerEntity, false);
/*  680 */             var8.spawnEntityInWorld((Entity)this.playerEntity);
/*  681 */             var8.updateEntityWithOptionalForce((Entity)this.playerEntity, false);
/*      */           } 
/*      */           
/*  684 */           this.playerEntity.setWorld((World)var8);
/*  685 */           this.serverController.getConfigurationManager().func_72375_a(this.playerEntity, var7);
/*  686 */           this.playerEntity.setPositionAndUpdate(var2.posX, var2.posY, var2.posZ);
/*  687 */           this.playerEntity.theItemInWorldManager.setWorld(var8);
/*  688 */           this.serverController.getConfigurationManager().updateTimeAndWeatherForPlayer(this.playerEntity, var8);
/*  689 */           this.serverController.getConfigurationManager().syncPlayerInventory(this.playerEntity);
/*      */         }
/*      */         else {
/*      */           
/*  693 */           this.playerEntity.setPositionAndUpdate(var2.posX, var2.posY, var2.posZ);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175086_a(C19PacketResourcePackStatus p_175086_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDisconnect(IChatComponent reason) {
/*  706 */     logger.info(String.valueOf(this.playerEntity.getName()) + " lost connection: " + reason);
/*  707 */     this.serverController.refreshStatusNextTick();
/*  708 */     ChatComponentTranslation var2 = new ChatComponentTranslation("multiplayer.player.left", new Object[] { this.playerEntity.getDisplayName() });
/*  709 */     var2.getChatStyle().setColor(EnumChatFormatting.YELLOW);
/*  710 */     this.serverController.getConfigurationManager().sendChatMsg((IChatComponent)var2);
/*  711 */     this.playerEntity.mountEntityAndWakeUp();
/*  712 */     this.serverController.getConfigurationManager().playerLoggedOut(this.playerEntity);
/*      */     
/*  714 */     if (this.serverController.isSinglePlayer() && this.playerEntity.getName().equals(this.serverController.getServerOwner())) {
/*      */       
/*  716 */       logger.info("Stopping singleplayer server as player logged out");
/*  717 */       this.serverController.initiateShutdown();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacket(final Packet packetIn) {
/*  723 */     if (packetIn instanceof S02PacketChat) {
/*      */       
/*  725 */       S02PacketChat var2 = (S02PacketChat)packetIn;
/*  726 */       EntityPlayer.EnumChatVisibility var3 = this.playerEntity.getChatVisibility();
/*      */       
/*  728 */       if (var3 == EntityPlayer.EnumChatVisibility.HIDDEN) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  733 */       if (var3 == EntityPlayer.EnumChatVisibility.SYSTEM && !var2.isChat()) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  741 */       this.netManager.sendPacket(packetIn);
/*      */     }
/*  743 */     catch (Throwable var5) {
/*      */       
/*  745 */       CrashReport var6 = CrashReport.makeCrashReport(var5, "Sending packet");
/*  746 */       CrashReportCategory var4 = var6.makeCategory("Packet being sent");
/*  747 */       var4.addCrashSectionCallable("Packet class", new Callable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002270";
/*      */             
/*      */             public String func_180225_a() {
/*  752 */               return packetIn.getClass().getCanonicalName();
/*      */             }
/*      */             
/*      */             public Object call() {
/*  756 */               return func_180225_a();
/*      */             }
/*      */           });
/*  759 */       throw new ReportedException(var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processHeldItemChange(C09PacketHeldItemChange packetIn) {
/*  768 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*      */     
/*  770 */     if (packetIn.getSlotId() >= 0 && packetIn.getSlotId() < InventoryPlayer.getHotbarSize()) {
/*      */       
/*  772 */       this.playerEntity.inventory.currentItem = packetIn.getSlotId();
/*  773 */       this.playerEntity.markPlayerActive();
/*      */     }
/*      */     else {
/*      */       
/*  777 */       logger.warn(String.valueOf(this.playerEntity.getName()) + " tried to set an invalid carried item");
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processChatMessage(C01PacketChatMessage packetIn) {
/*  786 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*      */     
/*  788 */     if (this.playerEntity.getChatVisibility() == EntityPlayer.EnumChatVisibility.HIDDEN) {
/*      */       
/*  790 */       ChatComponentTranslation var4 = new ChatComponentTranslation("chat.cannotSend", new Object[0]);
/*  791 */       var4.getChatStyle().setColor(EnumChatFormatting.RED);
/*  792 */       sendPacket((Packet)new S02PacketChat((IChatComponent)var4));
/*      */     }
/*      */     else {
/*      */       
/*  796 */       this.playerEntity.markPlayerActive();
/*  797 */       String var2 = packetIn.getMessage();
/*  798 */       var2 = StringUtils.normalizeSpace(var2);
/*      */       
/*  800 */       for (int var3 = 0; var3 < var2.length(); var3++) {
/*      */         
/*  802 */         if (!ChatAllowedCharacters.isAllowedCharacter(var2.charAt(var3))) {
/*      */           
/*  804 */           kickPlayerFromServer("Illegal characters in chat");
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*  809 */       if (var2.startsWith("/")) {
/*      */         
/*  811 */         handleSlashCommand(var2);
/*      */       }
/*      */       else {
/*      */         
/*  815 */         ChatComponentTranslation var5 = new ChatComponentTranslation("chat.type.text", new Object[] { this.playerEntity.getDisplayName(), var2 });
/*  816 */         this.serverController.getConfigurationManager().sendChatMsgImpl((IChatComponent)var5, false);
/*      */       } 
/*      */       
/*  819 */       this.chatSpamThresholdCount += 20;
/*      */       
/*  821 */       if (this.chatSpamThresholdCount > 200 && !this.serverController.getConfigurationManager().canSendCommands(this.playerEntity.getGameProfile()))
/*      */       {
/*  823 */         kickPlayerFromServer("disconnect.spam");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleSlashCommand(String command) {
/*  833 */     this.serverController.getCommandManager().executeCommand((ICommandSender)this.playerEntity, command);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175087_a(C0APacketAnimation p_175087_1_) {
/*  838 */     PacketThreadUtil.func_180031_a((Packet)p_175087_1_, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  839 */     this.playerEntity.markPlayerActive();
/*  840 */     this.playerEntity.swingItem();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processEntityAction(C0BPacketEntityAction packetIn) {
/*  849 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  850 */     this.playerEntity.markPlayerActive();
/*      */     
/*  852 */     switch (SwitchAction.field_180222_b[packetIn.func_180764_b().ordinal()]) {
/*      */       
/*      */       case 1:
/*  855 */         this.playerEntity.setSneaking(true);
/*      */         return;
/*      */       
/*      */       case 2:
/*  859 */         this.playerEntity.setSneaking(false);
/*      */         return;
/*      */       
/*      */       case 3:
/*  863 */         this.playerEntity.setSprinting(true);
/*      */         return;
/*      */       
/*      */       case 4:
/*  867 */         this.playerEntity.setSprinting(false);
/*      */         return;
/*      */       
/*      */       case 5:
/*  871 */         this.playerEntity.wakeUpPlayer(false, true, true);
/*  872 */         this.hasMoved = false;
/*      */         return;
/*      */       
/*      */       case 6:
/*  876 */         if (this.playerEntity.ridingEntity instanceof EntityHorse)
/*      */         {
/*  878 */           ((EntityHorse)this.playerEntity.ridingEntity).setJumpPower(packetIn.func_149512_e());
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case 7:
/*  884 */         if (this.playerEntity.ridingEntity instanceof EntityHorse)
/*      */         {
/*  886 */           ((EntityHorse)this.playerEntity.ridingEntity).openGUI((EntityPlayer)this.playerEntity);
/*      */         }
/*      */         return;
/*      */     } 
/*      */ 
/*      */     
/*  892 */     throw new IllegalArgumentException("Invalid client command!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processUseEntity(C02PacketUseEntity packetIn) {
/*  902 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  903 */     WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.dimension);
/*  904 */     Entity var3 = packetIn.getEntityFromWorld((World)var2);
/*  905 */     this.playerEntity.markPlayerActive();
/*      */     
/*  907 */     if (var3 != null) {
/*      */       
/*  909 */       boolean var4 = this.playerEntity.canEntityBeSeen(var3);
/*  910 */       double var5 = 36.0D;
/*      */       
/*  912 */       if (!var4)
/*      */       {
/*  914 */         var5 = 9.0D;
/*      */       }
/*      */       
/*  917 */       if (this.playerEntity.getDistanceSqToEntity(var3) < var5)
/*      */       {
/*  919 */         if (packetIn.getAction() == C02PacketUseEntity.Action.INTERACT) {
/*      */           
/*  921 */           this.playerEntity.interactWith(var3);
/*      */         }
/*  923 */         else if (packetIn.getAction() == C02PacketUseEntity.Action.INTERACT_AT) {
/*      */           
/*  925 */           var3.func_174825_a((EntityPlayer)this.playerEntity, packetIn.func_179712_b());
/*      */         }
/*  927 */         else if (packetIn.getAction() == C02PacketUseEntity.Action.ATTACK) {
/*      */           
/*  929 */           if (var3 instanceof EntityItem || var3 instanceof net.minecraft.entity.item.EntityXPOrb || var3 instanceof net.minecraft.entity.projectile.EntityArrow || var3 == this.playerEntity) {
/*      */             
/*  931 */             kickPlayerFromServer("Attempting to attack an invalid entity");
/*  932 */             this.serverController.logWarning("Player " + this.playerEntity.getName() + " tried to attack an invalid entity");
/*      */             
/*      */             return;
/*      */           } 
/*  936 */           this.playerEntity.attackTargetEntityWithCurrentItem(var3);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processClientStatus(C16PacketClientStatus packetIn) {
/*  948 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*  949 */     this.playerEntity.markPlayerActive();
/*  950 */     C16PacketClientStatus.EnumState var2 = packetIn.getStatus();
/*      */     
/*  952 */     switch (SwitchAction.field_180223_c[var2.ordinal()]) {
/*      */       
/*      */       case 1:
/*  955 */         if (this.playerEntity.playerConqueredTheEnd) {
/*      */           
/*  957 */           this.playerEntity = this.serverController.getConfigurationManager().recreatePlayerEntity(this.playerEntity, 0, true); break;
/*      */         } 
/*  959 */         if (this.playerEntity.getServerForPlayer().getWorldInfo().isHardcoreModeEnabled()) {
/*      */           
/*  961 */           if (this.serverController.isSinglePlayer() && this.playerEntity.getName().equals(this.serverController.getServerOwner())) {
/*      */             
/*  963 */             this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
/*  964 */             this.serverController.deleteWorldAndStopServer();
/*      */             
/*      */             break;
/*      */           } 
/*  968 */           UserListBansEntry var3 = new UserListBansEntry(this.playerEntity.getGameProfile(), null, "(You just lost the game)", null, "Death in Hardcore");
/*  969 */           this.serverController.getConfigurationManager().getBannedPlayers().addEntry((UserListEntry)var3);
/*  970 */           this.playerEntity.playerNetServerHandler.kickPlayerFromServer("You have died. Game over, man, it's game over!");
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/*  975 */         if (this.playerEntity.getHealth() > 0.0F) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/*  980 */         this.playerEntity = this.serverController.getConfigurationManager().recreatePlayerEntity(this.playerEntity, 0, false);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*  986 */         this.playerEntity.getStatFile().func_150876_a(this.playerEntity);
/*      */         break;
/*      */       
/*      */       case 3:
/*  990 */         this.playerEntity.triggerAchievement((StatBase)AchievementList.openInventory);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processCloseWindow(C0DPacketCloseWindow packetIn) {
/*  999 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1000 */     this.playerEntity.closeContainer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processClickWindow(C0EPacketClickWindow packetIn) {
/* 1010 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1011 */     this.playerEntity.markPlayerActive();
/*      */     
/* 1013 */     if (this.playerEntity.openContainer.windowId == packetIn.getWindowId() && this.playerEntity.openContainer.getCanCraft((EntityPlayer)this.playerEntity))
/*      */     {
/* 1015 */       if (this.playerEntity.func_175149_v()) {
/*      */         
/* 1017 */         ArrayList<ItemStack> var2 = Lists.newArrayList();
/*      */         
/* 1019 */         for (int var3 = 0; var3 < this.playerEntity.openContainer.inventorySlots.size(); var3++)
/*      */         {
/* 1021 */           var2.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(var3)).getStack());
/*      */         }
/*      */         
/* 1024 */         this.playerEntity.updateCraftingInventory(this.playerEntity.openContainer, var2);
/*      */       }
/*      */       else {
/*      */         
/* 1028 */         ItemStack var5 = this.playerEntity.openContainer.slotClick(packetIn.getSlotId(), packetIn.getUsedButton(), packetIn.getMode(), (EntityPlayer)this.playerEntity);
/*      */         
/* 1030 */         if (ItemStack.areItemStacksEqual(packetIn.getClickedItem(), var5)) {
/*      */           
/* 1032 */           this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S32PacketConfirmTransaction(packetIn.getWindowId(), packetIn.getActionNumber(), true));
/* 1033 */           this.playerEntity.isChangingQuantityOnly = true;
/* 1034 */           this.playerEntity.openContainer.detectAndSendChanges();
/* 1035 */           this.playerEntity.updateHeldItem();
/* 1036 */           this.playerEntity.isChangingQuantityOnly = false;
/*      */         }
/*      */         else {
/*      */           
/* 1040 */           this.field_147372_n.addKey(this.playerEntity.openContainer.windowId, Short.valueOf(packetIn.getActionNumber()));
/* 1041 */           this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S32PacketConfirmTransaction(packetIn.getWindowId(), packetIn.getActionNumber(), false));
/* 1042 */           this.playerEntity.openContainer.setCanCraft((EntityPlayer)this.playerEntity, false);
/* 1043 */           ArrayList<ItemStack> var6 = Lists.newArrayList();
/*      */           
/* 1045 */           for (int var4 = 0; var4 < this.playerEntity.openContainer.inventorySlots.size(); var4++)
/*      */           {
/* 1047 */             var6.add(((Slot)this.playerEntity.openContainer.inventorySlots.get(var4)).getStack());
/*      */           }
/*      */           
/* 1050 */           this.playerEntity.updateCraftingInventory(this.playerEntity.openContainer, var6);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processEnchantItem(C11PacketEnchantItem packetIn) {
/* 1062 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1063 */     this.playerEntity.markPlayerActive();
/*      */     
/* 1065 */     if (this.playerEntity.openContainer.windowId == packetIn.getId() && this.playerEntity.openContainer.getCanCraft((EntityPlayer)this.playerEntity) && !this.playerEntity.func_175149_v()) {
/*      */       
/* 1067 */       this.playerEntity.openContainer.enchantItem((EntityPlayer)this.playerEntity, packetIn.getButton());
/* 1068 */       this.playerEntity.openContainer.detectAndSendChanges();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processCreativeInventoryAction(C10PacketCreativeInventoryAction packetIn) {
/* 1077 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*      */     
/* 1079 */     if (this.playerEntity.theItemInWorldManager.isCreative()) {
/*      */       
/* 1081 */       boolean var2 = (packetIn.getSlotId() < 0);
/* 1082 */       ItemStack var3 = packetIn.getStack();
/*      */       
/* 1084 */       if (var3 != null && var3.hasTagCompound() && var3.getTagCompound().hasKey("BlockEntityTag", 10)) {
/*      */         
/* 1086 */         NBTTagCompound var4 = var3.getTagCompound().getCompoundTag("BlockEntityTag");
/*      */         
/* 1088 */         if (var4.hasKey("x") && var4.hasKey("y") && var4.hasKey("z")) {
/*      */           
/* 1090 */           BlockPos var5 = new BlockPos(var4.getInteger("x"), var4.getInteger("y"), var4.getInteger("z"));
/* 1091 */           TileEntity var6 = this.playerEntity.worldObj.getTileEntity(var5);
/*      */           
/* 1093 */           if (var6 != null) {
/*      */             
/* 1095 */             NBTTagCompound var7 = new NBTTagCompound();
/* 1096 */             var6.writeToNBT(var7);
/* 1097 */             var7.removeTag("x");
/* 1098 */             var7.removeTag("y");
/* 1099 */             var7.removeTag("z");
/* 1100 */             var3.setTagInfo("BlockEntityTag", (NBTBase)var7);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1105 */       boolean var8 = (packetIn.getSlotId() >= 1 && packetIn.getSlotId() < 36 + InventoryPlayer.getHotbarSize());
/* 1106 */       boolean var9 = !(var3 != null && var3.getItem() == null);
/* 1107 */       boolean var10 = !(var3 != null && (var3.getMetadata() < 0 || var3.stackSize > 64 || var3.stackSize <= 0));
/*      */       
/* 1109 */       if (var8 && var9 && var10) {
/*      */         
/* 1111 */         if (var3 == null) {
/*      */           
/* 1113 */           this.playerEntity.inventoryContainer.putStackInSlot(packetIn.getSlotId(), null);
/*      */         }
/*      */         else {
/*      */           
/* 1117 */           this.playerEntity.inventoryContainer.putStackInSlot(packetIn.getSlotId(), var3);
/*      */         } 
/*      */         
/* 1120 */         this.playerEntity.inventoryContainer.setCanCraft((EntityPlayer)this.playerEntity, true);
/*      */       }
/* 1122 */       else if (var2 && var9 && var10 && this.itemDropThreshold < 200) {
/*      */         
/* 1124 */         this.itemDropThreshold += 20;
/* 1125 */         EntityItem var11 = this.playerEntity.dropPlayerItemWithRandomChoice(var3, true);
/*      */         
/* 1127 */         if (var11 != null)
/*      */         {
/* 1129 */           var11.setAgeToCreativeDespawnTime();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processConfirmTransaction(C0FPacketConfirmTransaction packetIn) {
/* 1142 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1143 */     Short var2 = (Short)this.field_147372_n.lookup(this.playerEntity.openContainer.windowId);
/*      */     
/* 1145 */     if (var2 != null && packetIn.getUid() == var2.shortValue() && this.playerEntity.openContainer.windowId == packetIn.getId() && !this.playerEntity.openContainer.getCanCraft((EntityPlayer)this.playerEntity) && !this.playerEntity.func_175149_v())
/*      */     {
/* 1147 */       this.playerEntity.openContainer.setCanCraft((EntityPlayer)this.playerEntity, true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void processUpdateSign(C12PacketUpdateSign packetIn) {
/* 1153 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1154 */     this.playerEntity.markPlayerActive();
/* 1155 */     WorldServer var2 = this.serverController.worldServerForDimension(this.playerEntity.dimension);
/* 1156 */     BlockPos var3 = packetIn.func_179722_a();
/*      */     
/* 1158 */     if (var2.isBlockLoaded(var3)) {
/*      */       
/* 1160 */       TileEntity var4 = var2.getTileEntity(var3);
/*      */       
/* 1162 */       if (!(var4 instanceof TileEntitySign)) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1167 */       TileEntitySign var5 = (TileEntitySign)var4;
/*      */       
/* 1169 */       if (!var5.getIsEditable() || var5.func_145911_b() != this.playerEntity) {
/*      */         
/* 1171 */         this.serverController.logWarning("Player " + this.playerEntity.getName() + " just tried to change non-editable sign");
/*      */         
/*      */         return;
/*      */       } 
/* 1175 */       System.arraycopy(packetIn.func_180768_b(), 0, var5.signText, 0, 4);
/* 1176 */       var5.markDirty();
/* 1177 */       var2.markBlockForUpdate(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processKeepAlive(C00PacketKeepAlive packetIn) {
/* 1186 */     if (packetIn.getKey() == this.field_147378_h) {
/*      */       
/* 1188 */       int var2 = (int)(currentTimeMillis() - this.lastPingTime);
/* 1189 */       this.playerEntity.ping = (this.playerEntity.ping * 3 + var2) / 4;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private long currentTimeMillis() {
/* 1195 */     return System.nanoTime() / 1000000L;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processPlayerAbilities(C13PacketPlayerAbilities packetIn) {
/* 1203 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1204 */     this.playerEntity.capabilities.isFlying = (packetIn.isFlying() && this.playerEntity.capabilities.allowFlying);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processTabComplete(C14PacketTabComplete packetIn) {
/* 1212 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1213 */     ArrayList<String> var2 = Lists.newArrayList();
/* 1214 */     Iterator<String> var3 = this.serverController.func_180506_a((ICommandSender)this.playerEntity, packetIn.getMessage(), packetIn.func_179709_b()).iterator();
/*      */     
/* 1216 */     while (var3.hasNext()) {
/*      */       
/* 1218 */       String var4 = var3.next();
/* 1219 */       var2.add(var4);
/*      */     } 
/*      */     
/* 1222 */     this.playerEntity.playerNetServerHandler.sendPacket((Packet)new S3APacketTabComplete(var2.<String>toArray(new String[var2.size()])));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processClientSettings(C15PacketClientSettings packetIn) {
/* 1231 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/* 1232 */     this.playerEntity.handleClientSettings(packetIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processVanilla250Packet(C17PacketCustomPayload packetIn) {
/* 1240 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.playerEntity.getServerForPlayer());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1245 */     if ("MC|BEdit".equals(packetIn.getChannelName())) {
/*      */       
/* 1247 */       PacketBuffer var2 = new PacketBuffer(Unpooled.wrappedBuffer(packetIn.getBufferData()));
/*      */ 
/*      */       
/*      */       try {
/* 1251 */         ItemStack var3 = var2.readItemStackFromBuffer();
/*      */         
/* 1253 */         if (var3 == null) {
/*      */           return;
/*      */         }
/*      */ 
/*      */         
/* 1258 */         if (!ItemWritableBook.validBookPageTagContents(var3.getTagCompound()))
/*      */         {
/* 1260 */           throw new IOException("Invalid book tag!");
/*      */         }
/*      */         
/* 1263 */         ItemStack var4 = this.playerEntity.inventory.getCurrentItem();
/*      */         
/* 1265 */         if (var4 != null) {
/*      */           
/* 1267 */           if (var3.getItem() == Items.writable_book && var3.getItem() == var4.getItem())
/*      */           {
/* 1269 */             var4.setTagInfo("pages", (NBTBase)var3.getTagCompound().getTagList("pages", 8));
/*      */           }
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/* 1275 */       } catch (Exception var38) {
/*      */         
/* 1277 */         logger.error("Couldn't handle book info", var38);
/*      */ 
/*      */         
/*      */         return;
/*      */       } finally {
/* 1282 */         var2.release();
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/* 1287 */     if ("MC|BSign".equals(packetIn.getChannelName())) {
/*      */       
/* 1289 */       PacketBuffer var2 = new PacketBuffer(Unpooled.wrappedBuffer(packetIn.getBufferData()));
/*      */ 
/*      */       
/*      */       try {
/* 1293 */         ItemStack var3 = var2.readItemStackFromBuffer();
/*      */         
/* 1295 */         if (var3 != null) {
/*      */           
/* 1297 */           if (!ItemEditableBook.validBookTagContents(var3.getTagCompound()))
/*      */           {
/* 1299 */             throw new IOException("Invalid book tag!");
/*      */           }
/*      */           
/* 1302 */           ItemStack var4 = this.playerEntity.inventory.getCurrentItem();
/*      */           
/* 1304 */           if (var4 == null) {
/*      */             return;
/*      */           }
/*      */ 
/*      */           
/* 1309 */           if (var3.getItem() == Items.written_book && var4.getItem() == Items.writable_book) {
/*      */             
/* 1311 */             var4.setTagInfo("author", (NBTBase)new NBTTagString(this.playerEntity.getName()));
/* 1312 */             var4.setTagInfo("title", (NBTBase)new NBTTagString(var3.getTagCompound().getString("title")));
/* 1313 */             var4.setTagInfo("pages", (NBTBase)var3.getTagCompound().getTagList("pages", 8));
/* 1314 */             var4.setItem(Items.written_book);
/*      */           } 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/* 1320 */       } catch (Exception var36) {
/*      */         
/* 1322 */         logger.error("Couldn't sign book", var36);
/*      */ 
/*      */         
/*      */         return;
/*      */       } finally {
/* 1327 */         var2.release();
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/* 1332 */     if ("MC|TrSel".equals(packetIn.getChannelName())) {
/*      */       
/*      */       try
/*      */       {
/* 1336 */         int var40 = packetIn.getBufferData().readInt();
/* 1337 */         Container var42 = this.playerEntity.openContainer;
/*      */         
/* 1339 */         if (var42 instanceof ContainerMerchant)
/*      */         {
/* 1341 */           ((ContainerMerchant)var42).setCurrentRecipeIndex(var40);
/*      */         }
/*      */       }
/* 1344 */       catch (Exception var35)
/*      */       {
/* 1346 */         logger.error("Couldn't select trade", var35);
/*      */       }
/*      */     
/* 1349 */     } else if ("MC|AdvCdm".equals(packetIn.getChannelName())) {
/*      */       
/* 1351 */       if (!this.serverController.isCommandBlockEnabled()) {
/*      */         
/* 1353 */         this.playerEntity.addChatMessage((IChatComponent)new ChatComponentTranslation("advMode.notEnabled", new Object[0]));
/*      */       }
/* 1355 */       else if (this.playerEntity.canCommandSenderUseCommand(2, "") && this.playerEntity.capabilities.isCreativeMode) {
/*      */         
/* 1357 */         PacketBuffer var2 = packetIn.getBufferData();
/*      */ 
/*      */         
/*      */         try {
/* 1361 */           byte var43 = var2.readByte();
/* 1362 */           CommandBlockLogic var46 = null;
/*      */           
/* 1364 */           if (var43 == 0) {
/*      */             
/* 1366 */             TileEntity var5 = this.playerEntity.worldObj.getTileEntity(new BlockPos(var2.readInt(), var2.readInt(), var2.readInt()));
/*      */             
/* 1368 */             if (var5 instanceof TileEntityCommandBlock)
/*      */             {
/* 1370 */               var46 = ((TileEntityCommandBlock)var5).getCommandBlockLogic();
/*      */             }
/*      */           }
/* 1373 */           else if (var43 == 1) {
/*      */             
/* 1375 */             Entity var48 = this.playerEntity.worldObj.getEntityByID(var2.readInt());
/*      */             
/* 1377 */             if (var48 instanceof EntityMinecartCommandBlock)
/*      */             {
/* 1379 */               var46 = ((EntityMinecartCommandBlock)var48).func_145822_e();
/*      */             }
/*      */           } 
/*      */           
/* 1383 */           String var49 = var2.readStringFromBuffer(var2.readableBytes());
/* 1384 */           boolean var6 = var2.readBoolean();
/*      */           
/* 1386 */           if (var46 != null)
/*      */           {
/* 1388 */             var46.setCommand(var49);
/* 1389 */             var46.func_175573_a(var6);
/*      */             
/* 1391 */             if (!var6)
/*      */             {
/* 1393 */               var46.func_145750_b(null);
/*      */             }
/*      */             
/* 1396 */             var46.func_145756_e();
/* 1397 */             this.playerEntity.addChatMessage((IChatComponent)new ChatComponentTranslation("advMode.setCommand.success", new Object[] { var49 }));
/*      */           }
/*      */         
/* 1400 */         } catch (Exception var33) {
/*      */           
/* 1402 */           logger.error("Couldn't set command block", var33);
/*      */         }
/*      */         finally {
/*      */           
/* 1406 */           var2.release();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1411 */         this.playerEntity.addChatMessage((IChatComponent)new ChatComponentTranslation("advMode.notAllowed", new Object[0]));
/*      */       }
/*      */     
/* 1414 */     } else if ("MC|Beacon".equals(packetIn.getChannelName())) {
/*      */       
/* 1416 */       if (this.playerEntity.openContainer instanceof ContainerBeacon) {
/*      */         
/*      */         try {
/*      */           
/* 1420 */           PacketBuffer var2 = packetIn.getBufferData();
/* 1421 */           int var44 = var2.readInt();
/* 1422 */           int var47 = var2.readInt();
/* 1423 */           ContainerBeacon var50 = (ContainerBeacon)this.playerEntity.openContainer;
/* 1424 */           Slot var51 = var50.getSlot(0);
/*      */           
/* 1426 */           if (var51.getHasStack())
/*      */           {
/* 1428 */             var51.decrStackSize(1);
/* 1429 */             IInventory var7 = var50.func_180611_e();
/* 1430 */             var7.setField(1, var44);
/* 1431 */             var7.setField(2, var47);
/* 1432 */             var7.markDirty();
/*      */           }
/*      */         
/* 1435 */         } catch (Exception var32) {
/*      */           
/* 1437 */           logger.error("Couldn't set beacon", var32);
/*      */         }
/*      */       
/*      */       }
/* 1441 */     } else if ("MC|ItemName".equals(packetIn.getChannelName()) && this.playerEntity.openContainer instanceof ContainerRepair) {
/*      */       
/* 1443 */       ContainerRepair var41 = (ContainerRepair)this.playerEntity.openContainer;
/*      */       
/* 1445 */       if (packetIn.getBufferData() != null && packetIn.getBufferData().readableBytes() >= 1) {
/*      */         
/* 1447 */         String var45 = ChatAllowedCharacters.filterAllowedCharacters(packetIn.getBufferData().readStringFromBuffer(32767));
/*      */         
/* 1449 */         if (var45.length() <= 30)
/*      */         {
/* 1451 */           var41.updateItemName(var45);
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1456 */         var41.updateItemName("");
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final class SwitchAction
/*      */   {
/*      */     static final int[] field_180224_a;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1499 */     static final int[] field_180222_b = new int[(C0BPacketEntityAction.Action.values()).length]; static final int[] field_180223_c = new int[(C16PacketClientStatus.EnumState.values()).length]; private static final String __OBFID = "CL_00002269";
/*      */     
/*      */     static {
/*      */       try {
/* 1503 */         field_180222_b[C0BPacketEntityAction.Action.START_SNEAKING.ordinal()] = 1;
/*      */       }
/* 1505 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1512 */         field_180222_b[C0BPacketEntityAction.Action.STOP_SNEAKING.ordinal()] = 2;
/*      */       }
/* 1514 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1521 */         field_180222_b[C0BPacketEntityAction.Action.START_SPRINTING.ordinal()] = 3;
/*      */       }
/* 1523 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1530 */         field_180222_b[C0BPacketEntityAction.Action.STOP_SPRINTING.ordinal()] = 4;
/*      */       }
/* 1532 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1539 */         field_180222_b[C0BPacketEntityAction.Action.STOP_SLEEPING.ordinal()] = 5;
/*      */       }
/* 1541 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1548 */         field_180222_b[C0BPacketEntityAction.Action.RIDING_JUMP.ordinal()] = 6;
/*      */       }
/* 1550 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1557 */         field_180222_b[C0BPacketEntityAction.Action.OPEN_INVENTORY.ordinal()] = 7;
/*      */       }
/* 1559 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1564 */       field_180224_a = new int[(C07PacketPlayerDigging.Action.values()).length];
/*      */ 
/*      */       
/*      */       try {
/* 1568 */         field_180224_a[C07PacketPlayerDigging.Action.DROP_ITEM.ordinal()] = 1;
/*      */       }
/* 1570 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1577 */         field_180224_a[C07PacketPlayerDigging.Action.DROP_ALL_ITEMS.ordinal()] = 2;
/*      */       }
/* 1579 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1586 */         field_180224_a[C07PacketPlayerDigging.Action.RELEASE_USE_ITEM.ordinal()] = 3;
/*      */       }
/* 1588 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1595 */         field_180224_a[C07PacketPlayerDigging.Action.START_DESTROY_BLOCK.ordinal()] = 4;
/*      */       }
/* 1597 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1604 */         field_180224_a[C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK.ordinal()] = 5;
/*      */       }
/* 1606 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1613 */         field_180224_a[C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK.ordinal()] = 6;
/*      */       }
/* 1615 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\NetHandlerPlayServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */