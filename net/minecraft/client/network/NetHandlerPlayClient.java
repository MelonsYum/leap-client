/*      */ package net.minecraft.client.network;
/*      */ 
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.util.concurrent.FutureCallback;
/*      */ import com.google.common.util.concurrent.Futures;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import leap.ui.MainMenu;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.client.ClientBrandRetriever;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.audio.GuardianSound;
/*      */ import net.minecraft.client.audio.ISound;
/*      */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*      */ import net.minecraft.client.entity.EntityPlayerSP;
/*      */ import net.minecraft.client.gui.GuiChat;
/*      */ import net.minecraft.client.gui.GuiDisconnected;
/*      */ import net.minecraft.client.gui.GuiDownloadTerrain;
/*      */ import net.minecraft.client.gui.GuiMerchant;
/*      */ import net.minecraft.client.gui.GuiMultiplayer;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.GuiScreenBook;
/*      */ import net.minecraft.client.gui.GuiScreenDemo;
/*      */ import net.minecraft.client.gui.GuiScreenRealmsProxy;
/*      */ import net.minecraft.client.gui.GuiWinGame;
/*      */ import net.minecraft.client.gui.GuiYesNo;
/*      */ import net.minecraft.client.gui.GuiYesNoCallback;
/*      */ import net.minecraft.client.gui.IProgressMeter;
/*      */ import net.minecraft.client.gui.inventory.GuiContainerCreative;
/*      */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*      */ import net.minecraft.client.multiplayer.ServerData;
/*      */ import net.minecraft.client.multiplayer.ServerList;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.particle.EntityFX;
/*      */ import net.minecraft.client.particle.EntityPickupFX;
/*      */ import net.minecraft.client.player.inventory.ContainerLocalMenu;
/*      */ import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
/*      */ import net.minecraft.client.resources.I18n;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.client.stream.Metadata;
/*      */ import net.minecraft.client.stream.MetadataAchievement;
/*      */ import net.minecraft.client.stream.MetadataCombat;
/*      */ import net.minecraft.client.stream.MetadataPlayerDeath;
/*      */ import net.minecraft.creativetab.CreativeTabs;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLeashKnot;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IMerchant;
/*      */ import net.minecraft.entity.NpcMerchant;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*      */ import net.minecraft.entity.ai.attributes.IAttribute;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*      */ import net.minecraft.entity.effect.EntityLightningBolt;
/*      */ import net.minecraft.entity.item.EntityArmorStand;
/*      */ import net.minecraft.entity.item.EntityBoat;
/*      */ import net.minecraft.entity.item.EntityEnderCrystal;
/*      */ import net.minecraft.entity.item.EntityEnderEye;
/*      */ import net.minecraft.entity.item.EntityEnderPearl;
/*      */ import net.minecraft.entity.item.EntityExpBottle;
/*      */ import net.minecraft.entity.item.EntityFallingBlock;
/*      */ import net.minecraft.entity.item.EntityFireworkRocket;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.item.EntityItemFrame;
/*      */ import net.minecraft.entity.item.EntityMinecart;
/*      */ import net.minecraft.entity.item.EntityPainting;
/*      */ import net.minecraft.entity.item.EntityTNTPrimed;
/*      */ import net.minecraft.entity.item.EntityXPOrb;
/*      */ import net.minecraft.entity.monster.EntityGuardian;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.entity.projectile.EntityArrow;
/*      */ import net.minecraft.entity.projectile.EntityEgg;
/*      */ import net.minecraft.entity.projectile.EntityFishHook;
/*      */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*      */ import net.minecraft.entity.projectile.EntityPotion;
/*      */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*      */ import net.minecraft.entity.projectile.EntitySnowball;
/*      */ import net.minecraft.entity.projectile.EntityWitherSkull;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.AnimalChest;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.InventoryBasic;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemMap;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.network.INetHandler;
/*      */ import net.minecraft.network.NetworkManager;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.PacketBuffer;
/*      */ import net.minecraft.network.PacketThreadUtil;
/*      */ import net.minecraft.network.play.INetHandlerPlayClient;
/*      */ import net.minecraft.network.play.client.C00PacketKeepAlive;
/*      */ import net.minecraft.network.play.client.C03PacketPlayer;
/*      */ import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
/*      */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*      */ import net.minecraft.network.play.client.C19PacketResourcePackStatus;
/*      */ import net.minecraft.network.play.server.S00PacketKeepAlive;
/*      */ import net.minecraft.network.play.server.S01PacketJoinGame;
/*      */ import net.minecraft.network.play.server.S02PacketChat;
/*      */ import net.minecraft.network.play.server.S03PacketTimeUpdate;
/*      */ import net.minecraft.network.play.server.S04PacketEntityEquipment;
/*      */ import net.minecraft.network.play.server.S05PacketSpawnPosition;
/*      */ import net.minecraft.network.play.server.S06PacketUpdateHealth;
/*      */ import net.minecraft.network.play.server.S07PacketRespawn;
/*      */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*      */ import net.minecraft.network.play.server.S09PacketHeldItemChange;
/*      */ import net.minecraft.network.play.server.S0APacketUseBed;
/*      */ import net.minecraft.network.play.server.S0BPacketAnimation;
/*      */ import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
/*      */ import net.minecraft.network.play.server.S0DPacketCollectItem;
/*      */ import net.minecraft.network.play.server.S0EPacketSpawnObject;
/*      */ import net.minecraft.network.play.server.S0FPacketSpawnMob;
/*      */ import net.minecraft.network.play.server.S10PacketSpawnPainting;
/*      */ import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
/*      */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*      */ import net.minecraft.network.play.server.S13PacketDestroyEntities;
/*      */ import net.minecraft.network.play.server.S14PacketEntity;
/*      */ import net.minecraft.network.play.server.S18PacketEntityTeleport;
/*      */ import net.minecraft.network.play.server.S19PacketEntityHeadLook;
/*      */ import net.minecraft.network.play.server.S19PacketEntityStatus;
/*      */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*      */ import net.minecraft.network.play.server.S1CPacketEntityMetadata;
/*      */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*      */ import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
/*      */ import net.minecraft.network.play.server.S1FPacketSetExperience;
/*      */ import net.minecraft.network.play.server.S20PacketEntityProperties;
/*      */ import net.minecraft.network.play.server.S21PacketChunkData;
/*      */ import net.minecraft.network.play.server.S22PacketMultiBlockChange;
/*      */ import net.minecraft.network.play.server.S23PacketBlockChange;
/*      */ import net.minecraft.network.play.server.S24PacketBlockAction;
/*      */ import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
/*      */ import net.minecraft.network.play.server.S26PacketMapChunkBulk;
/*      */ import net.minecraft.network.play.server.S27PacketExplosion;
/*      */ import net.minecraft.network.play.server.S28PacketEffect;
/*      */ import net.minecraft.network.play.server.S29PacketSoundEffect;
/*      */ import net.minecraft.network.play.server.S2APacketParticles;
/*      */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*      */ import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
/*      */ import net.minecraft.network.play.server.S2DPacketOpenWindow;
/*      */ import net.minecraft.network.play.server.S2EPacketCloseWindow;
/*      */ import net.minecraft.network.play.server.S2FPacketSetSlot;
/*      */ import net.minecraft.network.play.server.S30PacketWindowItems;
/*      */ import net.minecraft.network.play.server.S31PacketWindowProperty;
/*      */ import net.minecraft.network.play.server.S32PacketConfirmTransaction;
/*      */ import net.minecraft.network.play.server.S33PacketUpdateSign;
/*      */ import net.minecraft.network.play.server.S34PacketMaps;
/*      */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*      */ import net.minecraft.network.play.server.S36PacketSignEditorOpen;
/*      */ import net.minecraft.network.play.server.S37PacketStatistics;
/*      */ import net.minecraft.network.play.server.S38PacketPlayerListItem;
/*      */ import net.minecraft.network.play.server.S39PacketPlayerAbilities;
/*      */ import net.minecraft.network.play.server.S3APacketTabComplete;
/*      */ import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
/*      */ import net.minecraft.network.play.server.S3CPacketUpdateScore;
/*      */ import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
/*      */ import net.minecraft.network.play.server.S3EPacketTeams;
/*      */ import net.minecraft.network.play.server.S3FPacketCustomPayload;
/*      */ import net.minecraft.network.play.server.S40PacketDisconnect;
/*      */ import net.minecraft.network.play.server.S41PacketServerDifficulty;
/*      */ import net.minecraft.network.play.server.S42PacketCombatEvent;
/*      */ import net.minecraft.network.play.server.S43PacketCamera;
/*      */ import net.minecraft.network.play.server.S44PacketWorldBorder;
/*      */ import net.minecraft.network.play.server.S45PacketTitle;
/*      */ import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
/*      */ import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
/*      */ import net.minecraft.network.play.server.S48PacketResourcePackSend;
/*      */ import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.realms.DisconnectedRealmsScreen;
/*      */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*      */ import net.minecraft.scoreboard.Score;
/*      */ import net.minecraft.scoreboard.ScoreObjective;
/*      */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.stats.Achievement;
/*      */ import net.minecraft.stats.AchievementList;
/*      */ import net.minecraft.stats.StatBase;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.tileentity.TileEntitySign;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.IThreadListener;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.StringUtils;
/*      */ import net.minecraft.village.MerchantRecipeList;
/*      */ import net.minecraft.world.Explosion;
/*      */ import net.minecraft.world.IInteractionObject;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import net.minecraft.world.storage.MapData;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ 
/*      */ public class NetHandlerPlayClient
/*      */   implements INetHandlerPlayClient
/*      */ {
/*  219 */   private static final Logger logger = LogManager.getLogger();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final NetworkManager netManager;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final GameProfile field_175107_d;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final GuiScreen guiScreenServer;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Minecraft gameController;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WorldClient clientWorldController;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doneLoadingTerrain;
/*      */ 
/*      */ 
/*      */   
/*  253 */   private final Map playerInfoMap = Maps.newHashMap();
/*  254 */   public int currentServerMaxPlayers = 20;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean field_147308_k = false;
/*      */ 
/*      */   
/*  261 */   private final Random avRandomizer = new Random();
/*      */   
/*      */   private static final String __OBFID = "CL_00000878";
/*      */   
/*      */   public NetHandlerPlayClient(Minecraft mcIn, GuiScreen p_i46300_2_, NetworkManager p_i46300_3_, GameProfile p_i46300_4_) {
/*  266 */     this.gameController = mcIn;
/*  267 */     this.guiScreenServer = p_i46300_2_;
/*  268 */     this.netManager = p_i46300_3_;
/*  269 */     this.field_175107_d = p_i46300_4_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void cleanup() {
/*  277 */     this.clientWorldController = null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleJoinGame(S01PacketJoinGame packetIn) {
/*  286 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  287 */     this.gameController.playerController = new PlayerControllerMP(this.gameController, this);
/*  288 */     this.clientWorldController = new WorldClient(this, new WorldSettings(0L, packetIn.func_149198_e(), false, packetIn.func_149195_d(), packetIn.func_149196_i()), packetIn.func_149194_f(), packetIn.func_149192_g(), this.gameController.mcProfiler);
/*  289 */     this.gameController.gameSettings.difficulty = packetIn.func_149192_g();
/*  290 */     this.gameController.loadWorld(this.clientWorldController);
/*  291 */     this.gameController.thePlayer.dimension = packetIn.func_149194_f();
/*  292 */     this.gameController.displayGuiScreen((GuiScreen)new GuiDownloadTerrain(this));
/*  293 */     this.gameController.thePlayer.setEntityId(packetIn.func_149197_c());
/*  294 */     this.currentServerMaxPlayers = packetIn.func_149193_h();
/*  295 */     this.gameController.thePlayer.func_175150_k(packetIn.func_179744_h());
/*  296 */     this.gameController.playerController.setGameType(packetIn.func_149198_e());
/*  297 */     this.gameController.gameSettings.sendSettingsToServer();
/*  298 */     this.netManager.sendPacket((Packet)new C17PacketCustomPayload("MC|Brand", (new PacketBuffer(Unpooled.buffer())).writeString(ClientBrandRetriever.getClientModName())));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnObject(S0EPacketSpawnObject packetIn) {
/*  306 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  307 */     double var2 = packetIn.func_148997_d() / 32.0D;
/*  308 */     double var4 = packetIn.func_148998_e() / 32.0D;
/*  309 */     double var6 = packetIn.func_148994_f() / 32.0D;
/*  310 */     Object var8 = null;
/*      */     
/*  312 */     if (packetIn.func_148993_l() == 10) {
/*      */       
/*  314 */       var8 = EntityMinecart.func_180458_a((World)this.clientWorldController, var2, var4, var6, EntityMinecart.EnumMinecartType.func_180038_a(packetIn.func_149009_m()));
/*      */     }
/*  316 */     else if (packetIn.func_148993_l() == 90) {
/*      */       
/*  318 */       Entity var9 = this.clientWorldController.getEntityByID(packetIn.func_149009_m());
/*      */       
/*  320 */       if (var9 instanceof EntityPlayer)
/*      */       {
/*  322 */         var8 = new EntityFishHook((World)this.clientWorldController, var2, var4, var6, (EntityPlayer)var9);
/*      */       }
/*      */       
/*  325 */       packetIn.func_149002_g(0);
/*      */     }
/*  327 */     else if (packetIn.func_148993_l() == 60) {
/*      */       
/*  329 */       var8 = new EntityArrow((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  331 */     else if (packetIn.func_148993_l() == 61) {
/*      */       
/*  333 */       var8 = new EntitySnowball((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  335 */     else if (packetIn.func_148993_l() == 71) {
/*      */       
/*  337 */       var8 = new EntityItemFrame((World)this.clientWorldController, new BlockPos(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6)), EnumFacing.getHorizontal(packetIn.func_149009_m()));
/*  338 */       packetIn.func_149002_g(0);
/*      */     }
/*  340 */     else if (packetIn.func_148993_l() == 77) {
/*      */       
/*  342 */       var8 = new EntityLeashKnot((World)this.clientWorldController, new BlockPos(MathHelper.floor_double(var2), MathHelper.floor_double(var4), MathHelper.floor_double(var6)));
/*  343 */       packetIn.func_149002_g(0);
/*      */     }
/*  345 */     else if (packetIn.func_148993_l() == 65) {
/*      */       
/*  347 */       var8 = new EntityEnderPearl((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  349 */     else if (packetIn.func_148993_l() == 72) {
/*      */       
/*  351 */       var8 = new EntityEnderEye((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  353 */     else if (packetIn.func_148993_l() == 76) {
/*      */       
/*  355 */       var8 = new EntityFireworkRocket((World)this.clientWorldController, var2, var4, var6, null);
/*      */     }
/*  357 */     else if (packetIn.func_148993_l() == 63) {
/*      */       
/*  359 */       var8 = new EntityLargeFireball((World)this.clientWorldController, var2, var4, var6, packetIn.func_149010_g() / 8000.0D, packetIn.func_149004_h() / 8000.0D, packetIn.func_148999_i() / 8000.0D);
/*  360 */       packetIn.func_149002_g(0);
/*      */     }
/*  362 */     else if (packetIn.func_148993_l() == 64) {
/*      */       
/*  364 */       var8 = new EntitySmallFireball((World)this.clientWorldController, var2, var4, var6, packetIn.func_149010_g() / 8000.0D, packetIn.func_149004_h() / 8000.0D, packetIn.func_148999_i() / 8000.0D);
/*  365 */       packetIn.func_149002_g(0);
/*      */     }
/*  367 */     else if (packetIn.func_148993_l() == 66) {
/*      */       
/*  369 */       var8 = new EntityWitherSkull((World)this.clientWorldController, var2, var4, var6, packetIn.func_149010_g() / 8000.0D, packetIn.func_149004_h() / 8000.0D, packetIn.func_148999_i() / 8000.0D);
/*  370 */       packetIn.func_149002_g(0);
/*      */     }
/*  372 */     else if (packetIn.func_148993_l() == 62) {
/*      */       
/*  374 */       var8 = new EntityEgg((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  376 */     else if (packetIn.func_148993_l() == 73) {
/*      */       
/*  378 */       var8 = new EntityPotion((World)this.clientWorldController, var2, var4, var6, packetIn.func_149009_m());
/*  379 */       packetIn.func_149002_g(0);
/*      */     }
/*  381 */     else if (packetIn.func_148993_l() == 75) {
/*      */       
/*  383 */       var8 = new EntityExpBottle((World)this.clientWorldController, var2, var4, var6);
/*  384 */       packetIn.func_149002_g(0);
/*      */     }
/*  386 */     else if (packetIn.func_148993_l() == 1) {
/*      */       
/*  388 */       var8 = new EntityBoat((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  390 */     else if (packetIn.func_148993_l() == 50) {
/*      */       
/*  392 */       var8 = new EntityTNTPrimed((World)this.clientWorldController, var2, var4, var6, null);
/*      */     }
/*  394 */     else if (packetIn.func_148993_l() == 78) {
/*      */       
/*  396 */       var8 = new EntityArmorStand((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  398 */     else if (packetIn.func_148993_l() == 51) {
/*      */       
/*  400 */       var8 = new EntityEnderCrystal((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  402 */     else if (packetIn.func_148993_l() == 2) {
/*      */       
/*  404 */       var8 = new EntityItem((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*  406 */     else if (packetIn.func_148993_l() == 70) {
/*      */       
/*  408 */       var8 = new EntityFallingBlock((World)this.clientWorldController, var2, var4, var6, Block.getStateById(packetIn.func_149009_m() & 0xFFFF));
/*  409 */       packetIn.func_149002_g(0);
/*      */     } 
/*      */     
/*  412 */     if (var8 != null) {
/*      */       
/*  414 */       ((Entity)var8).serverPosX = packetIn.func_148997_d();
/*  415 */       ((Entity)var8).serverPosY = packetIn.func_148998_e();
/*  416 */       ((Entity)var8).serverPosZ = packetIn.func_148994_f();
/*  417 */       ((Entity)var8).rotationPitch = (packetIn.func_149008_j() * 360) / 256.0F;
/*  418 */       ((Entity)var8).rotationYaw = (packetIn.func_149006_k() * 360) / 256.0F;
/*  419 */       Entity[] var12 = ((Entity)var8).getParts();
/*      */       
/*  421 */       if (var12 != null) {
/*      */         
/*  423 */         int var10 = packetIn.func_149001_c() - ((Entity)var8).getEntityId();
/*      */         
/*  425 */         for (int var11 = 0; var11 < var12.length; var11++)
/*      */         {
/*  427 */           var12[var11].setEntityId(var12[var11].getEntityId() + var10);
/*      */         }
/*      */       } 
/*      */       
/*  431 */       ((Entity)var8).setEntityId(packetIn.func_149001_c());
/*  432 */       this.clientWorldController.addEntityToWorld(packetIn.func_149001_c(), (Entity)var8);
/*      */       
/*  434 */       if (packetIn.func_149009_m() > 0) {
/*      */         
/*  436 */         if (packetIn.func_148993_l() == 60) {
/*      */           
/*  438 */           Entity var13 = this.clientWorldController.getEntityByID(packetIn.func_149009_m());
/*      */           
/*  440 */           if (var13 instanceof EntityLivingBase && var8 instanceof EntityArrow)
/*      */           {
/*  442 */             ((EntityArrow)var8).shootingEntity = var13;
/*      */           }
/*      */         } 
/*      */         
/*  446 */         ((Entity)var8).setVelocity(packetIn.func_149010_g() / 8000.0D, packetIn.func_149004_h() / 8000.0D, packetIn.func_148999_i() / 8000.0D);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb packetIn) {
/*  456 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  457 */     EntityXPOrb var2 = new EntityXPOrb((World)this.clientWorldController, packetIn.func_148984_d(), packetIn.func_148983_e(), packetIn.func_148982_f(), packetIn.func_148986_g());
/*  458 */     var2.serverPosX = packetIn.func_148984_d();
/*  459 */     var2.serverPosY = packetIn.func_148983_e();
/*  460 */     var2.serverPosZ = packetIn.func_148982_f();
/*  461 */     var2.rotationYaw = 0.0F;
/*  462 */     var2.rotationPitch = 0.0F;
/*  463 */     var2.setEntityId(packetIn.func_148985_c());
/*  464 */     this.clientWorldController.addEntityToWorld(packetIn.func_148985_c(), (Entity)var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity packetIn) {
/*  472 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  473 */     double var2 = packetIn.func_149051_d() / 32.0D;
/*  474 */     double var4 = packetIn.func_149050_e() / 32.0D;
/*  475 */     double var6 = packetIn.func_149049_f() / 32.0D;
/*  476 */     EntityLightningBolt var8 = null;
/*      */     
/*  478 */     if (packetIn.func_149053_g() == 1)
/*      */     {
/*  480 */       var8 = new EntityLightningBolt((World)this.clientWorldController, var2, var4, var6);
/*      */     }
/*      */     
/*  483 */     if (var8 != null) {
/*      */       
/*  485 */       var8.serverPosX = packetIn.func_149051_d();
/*  486 */       var8.serverPosY = packetIn.func_149050_e();
/*  487 */       var8.serverPosZ = packetIn.func_149049_f();
/*  488 */       var8.rotationYaw = 0.0F;
/*  489 */       var8.rotationPitch = 0.0F;
/*  490 */       var8.setEntityId(packetIn.func_149052_c());
/*  491 */       this.clientWorldController.addWeatherEffect((Entity)var8);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnPainting(S10PacketSpawnPainting packetIn) {
/*  500 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  501 */     EntityPainting var2 = new EntityPainting((World)this.clientWorldController, packetIn.func_179837_b(), packetIn.func_179836_c(), packetIn.func_148961_h());
/*  502 */     this.clientWorldController.addEntityToWorld(packetIn.func_148965_c(), (Entity)var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityVelocity(S12PacketEntityVelocity packetIn) {
/*  510 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  511 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.entityID);
/*      */     
/*  513 */     if (var2 != null)
/*      */     {
/*  515 */       var2.setVelocity(packetIn.motionX / 8000.0D, packetIn.motionY / 8000.0D, packetIn.motionZ / 8000.0D);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityMetadata(S1CPacketEntityMetadata packetIn) {
/*  525 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  526 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149375_d());
/*      */     
/*  528 */     if (var2 != null && packetIn.func_149376_c() != null)
/*      */     {
/*  530 */       var2.getDataWatcher().updateWatchedObjectsFromList(packetIn.func_149376_c());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnPlayer(S0CPacketSpawnPlayer packetIn) {
/*  539 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  540 */     double var2 = packetIn.func_148942_f() / 32.0D;
/*  541 */     double var4 = packetIn.func_148949_g() / 32.0D;
/*  542 */     double var6 = packetIn.func_148946_h() / 32.0D;
/*  543 */     float var8 = (packetIn.func_148941_i() * 360) / 256.0F;
/*  544 */     float var9 = (packetIn.func_148945_j() * 360) / 256.0F;
/*  545 */     EntityOtherPlayerMP var10 = new EntityOtherPlayerMP((World)this.gameController.theWorld, func_175102_a(packetIn.func_179819_c()).func_178845_a());
/*  546 */     var10.prevPosX = var10.lastTickPosX = (var10.serverPosX = packetIn.func_148942_f());
/*  547 */     var10.prevPosY = var10.lastTickPosY = (var10.serverPosY = packetIn.func_148949_g());
/*  548 */     var10.prevPosZ = var10.lastTickPosZ = (var10.serverPosZ = packetIn.func_148946_h());
/*  549 */     int var11 = packetIn.func_148947_k();
/*      */     
/*  551 */     if (var11 == 0) {
/*      */       
/*  553 */       var10.inventory.mainInventory[var10.inventory.currentItem] = null;
/*      */     }
/*      */     else {
/*      */       
/*  557 */       var10.inventory.mainInventory[var10.inventory.currentItem] = new ItemStack(Item.getItemById(var11), 1, 0);
/*      */     } 
/*      */     
/*  560 */     var10.setPositionAndRotation(var2, var4, var6, var8, var9);
/*  561 */     this.clientWorldController.addEntityToWorld(packetIn.func_148943_d(), (Entity)var10);
/*  562 */     List var12 = packetIn.func_148944_c();
/*      */     
/*  564 */     if (var12 != null)
/*      */     {
/*  566 */       var10.getDataWatcher().updateWatchedObjectsFromList(var12);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityTeleport(S18PacketEntityTeleport packetIn) {
/*  575 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  576 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149451_c());
/*      */     
/*  578 */     if (var2 != null) {
/*      */       
/*  580 */       var2.serverPosX = packetIn.func_149449_d();
/*  581 */       var2.serverPosY = packetIn.func_149448_e();
/*  582 */       var2.serverPosZ = packetIn.func_149446_f();
/*  583 */       double var3 = var2.serverPosX / 32.0D;
/*  584 */       double var5 = var2.serverPosY / 32.0D + 0.015625D;
/*  585 */       double var7 = var2.serverPosZ / 32.0D;
/*  586 */       float var9 = (packetIn.func_149450_g() * 360) / 256.0F;
/*  587 */       float var10 = (packetIn.func_149447_h() * 360) / 256.0F;
/*      */       
/*  589 */       if (Math.abs(var2.posX - var3) < 0.03125D && Math.abs(var2.posY - var5) < 0.015625D && Math.abs(var2.posZ - var7) < 0.03125D) {
/*      */         
/*  591 */         var2.func_180426_a(var2.posX, var2.posY, var2.posZ, var9, var10, 3, true);
/*      */       }
/*      */       else {
/*      */         
/*  595 */         var2.func_180426_a(var3, var5, var7, var9, var10, 3, true);
/*      */       } 
/*      */       
/*  598 */       var2.onGround = packetIn.func_179697_g();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHeldItemChange(S09PacketHeldItemChange packetIn) {
/*  607 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/*  609 */     if (packetIn.func_149385_c() >= 0 && packetIn.func_149385_c() < InventoryPlayer.getHotbarSize())
/*      */     {
/*  611 */       this.gameController.thePlayer.inventory.currentItem = packetIn.func_149385_c();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityMovement(S14PacketEntity packetIn) {
/*  622 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  623 */     Entity var2 = packetIn.func_149065_a((World)this.clientWorldController);
/*      */     
/*  625 */     if (var2 != null) {
/*      */       
/*  627 */       var2.serverPosX += packetIn.func_149062_c();
/*  628 */       var2.serverPosY += packetIn.func_149061_d();
/*  629 */       var2.serverPosZ += packetIn.func_149064_e();
/*  630 */       double var3 = var2.serverPosX / 32.0D;
/*  631 */       double var5 = var2.serverPosY / 32.0D;
/*  632 */       double var7 = var2.serverPosZ / 32.0D;
/*  633 */       float var9 = packetIn.func_149060_h() ? ((packetIn.func_149066_f() * 360) / 256.0F) : var2.rotationYaw;
/*  634 */       float var10 = packetIn.func_149060_h() ? ((packetIn.func_149063_g() * 360) / 256.0F) : var2.rotationPitch;
/*  635 */       var2.func_180426_a(var3, var5, var7, var9, var10, 3, false);
/*  636 */       var2.onGround = packetIn.func_179742_g();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleEntityHeadLook(S19PacketEntityHeadLook packetIn) {
/*  646 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  647 */     Entity var2 = packetIn.func_149381_a((World)this.clientWorldController);
/*      */     
/*  649 */     if (var2 != null) {
/*      */       
/*  651 */       float var3 = (packetIn.func_149380_c() * 360) / 256.0F;
/*  652 */       var2.setRotationYawHead(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDestroyEntities(S13PacketDestroyEntities packetIn) {
/*  663 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/*  665 */     for (int var2 = 0; var2 < (packetIn.func_149098_c()).length; var2++)
/*      */     {
/*  667 */       this.clientWorldController.removeEntityFromWorld(packetIn.func_149098_c()[var2]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handlePlayerPosLook(S08PacketPlayerPosLook packetIn) {
/*  678 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  679 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/*  680 */     double var3 = packetIn.func_148932_c();
/*  681 */     double var5 = packetIn.func_148928_d();
/*  682 */     double var7 = packetIn.func_148933_e();
/*  683 */     float var9 = packetIn.func_148931_f();
/*  684 */     float var10 = packetIn.func_148930_g();
/*      */     
/*  686 */     if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X)) {
/*      */       
/*  688 */       var3 += var2.posX;
/*      */     }
/*      */     else {
/*      */       
/*  692 */       var2.motionX = 0.0D;
/*      */     } 
/*      */     
/*  695 */     if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y)) {
/*      */       
/*  697 */       var5 += var2.posY;
/*      */     }
/*      */     else {
/*      */       
/*  701 */       var2.motionY = 0.0D;
/*      */     } 
/*      */     
/*  704 */     if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Z)) {
/*      */       
/*  706 */       var7 += var2.posZ;
/*      */     }
/*      */     else {
/*      */       
/*  710 */       var2.motionZ = 0.0D;
/*      */     } 
/*      */     
/*  713 */     if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.X_ROT))
/*      */     {
/*  715 */       var10 += var2.rotationPitch;
/*      */     }
/*      */     
/*  718 */     if (packetIn.func_179834_f().contains(S08PacketPlayerPosLook.EnumFlags.Y_ROT))
/*      */     {
/*  720 */       var9 += var2.rotationYaw;
/*      */     }
/*      */     
/*  723 */     var2.setPositionAndRotation(var3, var5, var7, var9, var10);
/*  724 */     this.netManager.sendPacket((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(var2.posX, (var2.getEntityBoundingBox()).minY, var2.posZ, var2.rotationYaw, var2.rotationPitch, false));
/*      */     
/*  726 */     if (!this.doneLoadingTerrain) {
/*      */       
/*  728 */       this.gameController.thePlayer.prevPosX = this.gameController.thePlayer.posX;
/*  729 */       this.gameController.thePlayer.prevPosY = this.gameController.thePlayer.posY;
/*  730 */       this.gameController.thePlayer.prevPosZ = this.gameController.thePlayer.posZ;
/*  731 */       this.doneLoadingTerrain = true;
/*  732 */       this.gameController.displayGuiScreen(null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMultiBlockChange(S22PacketMultiBlockChange packetIn) {
/*  743 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  744 */     S22PacketMultiBlockChange.BlockUpdateData[] var2 = packetIn.func_179844_a();
/*  745 */     int var3 = var2.length;
/*      */     
/*  747 */     for (int var4 = 0; var4 < var3; var4++) {
/*      */       
/*  749 */       S22PacketMultiBlockChange.BlockUpdateData var5 = var2[var4];
/*  750 */       this.clientWorldController.func_180503_b(var5.func_180090_a(), var5.func_180088_c());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleChunkData(S21PacketChunkData packetIn) {
/*  759 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/*  761 */     if (packetIn.func_149274_i()) {
/*      */       
/*  763 */       if (packetIn.func_149276_g() == 0) {
/*      */         
/*  765 */         this.clientWorldController.doPreChunk(packetIn.func_149273_e(), packetIn.func_149271_f(), false);
/*      */         
/*      */         return;
/*      */       } 
/*  769 */       this.clientWorldController.doPreChunk(packetIn.func_149273_e(), packetIn.func_149271_f(), true);
/*      */     } 
/*      */     
/*  772 */     this.clientWorldController.invalidateBlockReceiveRegion(packetIn.func_149273_e() << 4, 0, packetIn.func_149271_f() << 4, (packetIn.func_149273_e() << 4) + 15, 256, (packetIn.func_149271_f() << 4) + 15);
/*  773 */     Chunk var2 = this.clientWorldController.getChunkFromChunkCoords(packetIn.func_149273_e(), packetIn.func_149271_f());
/*  774 */     var2.func_177439_a(packetIn.func_149272_d(), packetIn.func_149276_g(), packetIn.func_149274_i());
/*  775 */     this.clientWorldController.markBlockRangeForRenderUpdate(packetIn.func_149273_e() << 4, 0, packetIn.func_149271_f() << 4, (packetIn.func_149273_e() << 4) + 15, 256, (packetIn.func_149271_f() << 4) + 15);
/*      */     
/*  777 */     if (!packetIn.func_149274_i() || !(this.clientWorldController.provider instanceof net.minecraft.world.WorldProviderSurface))
/*      */     {
/*  779 */       var2.resetRelightChecks();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleBlockChange(S23PacketBlockChange packetIn) {
/*  788 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  789 */     this.clientWorldController.func_180503_b(packetIn.func_179827_b(), packetIn.func_180728_a());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDisconnect(S40PacketDisconnect packetIn) {
/*  797 */     this.netManager.closeChannel(packetIn.func_149165_c());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDisconnect(IChatComponent reason) {
/*  805 */     this.gameController.loadWorld(null);
/*      */     
/*  807 */     if (this.guiScreenServer != null) {
/*      */       
/*  809 */       if (this.guiScreenServer instanceof GuiScreenRealmsProxy)
/*      */       {
/*  811 */         this.gameController.displayGuiScreen((GuiScreen)(new DisconnectedRealmsScreen(((GuiScreenRealmsProxy)this.guiScreenServer).func_154321_a(), "disconnect.lost", reason)).getProxy());
/*      */       }
/*      */       else
/*      */       {
/*  815 */         this.gameController.displayGuiScreen((GuiScreen)new GuiDisconnected(this.guiScreenServer, "disconnect.lost", reason));
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  820 */       this.gameController.displayGuiScreen((GuiScreen)new GuiDisconnected((GuiScreen)new GuiMultiplayer((GuiScreen)new MainMenu()), "disconnect.lost", reason));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void addToSendQueue(Packet p_147297_1_) {
/*  826 */     this.netManager.sendPacket(p_147297_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleCollectItem(S0DPacketCollectItem packetIn) {
/*  831 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  832 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149354_c());
/*  833 */     Object var3 = this.clientWorldController.getEntityByID(packetIn.func_149353_d());
/*      */     
/*  835 */     if (var3 == null)
/*      */     {
/*  837 */       var3 = this.gameController.thePlayer;
/*      */     }
/*      */     
/*  840 */     if (var2 != null) {
/*      */       
/*  842 */       if (var2 instanceof EntityXPOrb) {
/*      */         
/*  844 */         this.clientWorldController.playSoundAtEntity(var2, "random.orb", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */       }
/*      */       else {
/*      */         
/*  848 */         this.clientWorldController.playSoundAtEntity(var2, "random.pop", 0.2F, ((this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*      */       } 
/*      */       
/*  851 */       this.gameController.effectRenderer.addEffect((EntityFX)new EntityPickupFX((World)this.clientWorldController, var2, (Entity)var3, 0.5F));
/*  852 */       this.clientWorldController.removeEntityFromWorld(packetIn.func_149354_c());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleChat(S02PacketChat packetIn) {
/*  861 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/*  863 */     if (packetIn.func_179841_c() == 2) {
/*      */       
/*  865 */       this.gameController.ingameGUI.func_175188_a(packetIn.func_148915_c(), false);
/*      */     }
/*      */     else {
/*      */       
/*  869 */       this.gameController.ingameGUI.getChatGUI().printChatMessage(packetIn.func_148915_c());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleAnimation(S0BPacketAnimation packetIn) {
/*  879 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  880 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_148978_c());
/*      */     
/*  882 */     if (var2 != null)
/*      */     {
/*  884 */       if (packetIn.func_148977_d() == 0) {
/*      */         
/*  886 */         EntityLivingBase var3 = (EntityLivingBase)var2;
/*  887 */         var3.swingItem();
/*      */       }
/*  889 */       else if (packetIn.func_148977_d() == 1) {
/*      */         
/*  891 */         var2.performHurtAnimation();
/*      */       }
/*  893 */       else if (packetIn.func_148977_d() == 2) {
/*      */         
/*  895 */         EntityPlayer var4 = (EntityPlayer)var2;
/*  896 */         var4.wakeUpPlayer(false, false, false);
/*      */       }
/*  898 */       else if (packetIn.func_148977_d() == 4) {
/*      */         
/*  900 */         this.gameController.effectRenderer.func_178926_a(var2, EnumParticleTypes.CRIT);
/*      */       }
/*  902 */       else if (packetIn.func_148977_d() == 5) {
/*      */         
/*  904 */         this.gameController.effectRenderer.func_178926_a(var2, EnumParticleTypes.CRIT_MAGIC);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUseBed(S0APacketUseBed packetIn) {
/*  915 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  916 */     packetIn.getPlayer((World)this.clientWorldController).func_180469_a(packetIn.func_179798_a());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSpawnMob(S0FPacketSpawnMob packetIn) {
/*  925 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  926 */     double var2 = packetIn.func_149023_f() / 32.0D;
/*  927 */     double var4 = packetIn.func_149034_g() / 32.0D;
/*  928 */     double var6 = packetIn.func_149029_h() / 32.0D;
/*  929 */     float var8 = (packetIn.func_149028_l() * 360) / 256.0F;
/*  930 */     float var9 = (packetIn.func_149030_m() * 360) / 256.0F;
/*  931 */     EntityLivingBase var10 = (EntityLivingBase)EntityList.createEntityByID(packetIn.func_149025_e(), (World)this.gameController.theWorld);
/*  932 */     var10.serverPosX = packetIn.func_149023_f();
/*  933 */     var10.serverPosY = packetIn.func_149034_g();
/*  934 */     var10.serverPosZ = packetIn.func_149029_h();
/*  935 */     var10.rotationYawHead = (packetIn.func_149032_n() * 360) / 256.0F;
/*  936 */     Entity[] var11 = var10.getParts();
/*      */     
/*  938 */     if (var11 != null) {
/*      */       
/*  940 */       int var12 = packetIn.func_149024_d() - var10.getEntityId();
/*      */       
/*  942 */       for (int var13 = 0; var13 < var11.length; var13++)
/*      */       {
/*  944 */         var11[var13].setEntityId(var11[var13].getEntityId() + var12);
/*      */       }
/*      */     } 
/*      */     
/*  948 */     var10.setEntityId(packetIn.func_149024_d());
/*  949 */     var10.setPositionAndRotation(var2, var4, var6, var8, var9);
/*  950 */     var10.motionX = (packetIn.func_149026_i() / 8000.0F);
/*  951 */     var10.motionY = (packetIn.func_149033_j() / 8000.0F);
/*  952 */     var10.motionZ = (packetIn.func_149031_k() / 8000.0F);
/*  953 */     this.clientWorldController.addEntityToWorld(packetIn.func_149024_d(), (Entity)var10);
/*  954 */     List var14 = packetIn.func_149027_c();
/*      */     
/*  956 */     if (var14 != null)
/*      */     {
/*  958 */       var10.getDataWatcher().updateWatchedObjectsFromList(var14);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleTimeUpdate(S03PacketTimeUpdate packetIn) {
/*  964 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  965 */     this.gameController.theWorld.func_82738_a(packetIn.func_149366_c());
/*  966 */     this.gameController.theWorld.setWorldTime(packetIn.func_149365_d());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSpawnPosition(S05PacketSpawnPosition packetIn) {
/*  971 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  972 */     this.gameController.thePlayer.func_180473_a(packetIn.func_179800_a(), true);
/*  973 */     this.gameController.theWorld.getWorldInfo().setSpawn(packetIn.func_179800_a());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEntityAttach(S1BPacketEntityAttach packetIn) {
/*  978 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*  979 */     Object var2 = this.clientWorldController.getEntityByID(packetIn.func_149403_d());
/*  980 */     Entity var3 = this.clientWorldController.getEntityByID(packetIn.func_149402_e());
/*      */     
/*  982 */     if (packetIn.func_149404_c() == 0) {
/*      */       
/*  984 */       boolean var4 = false;
/*      */       
/*  986 */       if (packetIn.func_149403_d() == this.gameController.thePlayer.getEntityId()) {
/*      */         
/*  988 */         var2 = this.gameController.thePlayer;
/*      */         
/*  990 */         if (var3 instanceof EntityBoat)
/*      */         {
/*  992 */           ((EntityBoat)var3).setIsBoatEmpty(false);
/*      */         }
/*      */         
/*  995 */         var4 = (((Entity)var2).ridingEntity == null && var3 != null);
/*      */       }
/*  997 */       else if (var3 instanceof EntityBoat) {
/*      */         
/*  999 */         ((EntityBoat)var3).setIsBoatEmpty(true);
/*      */       } 
/*      */       
/* 1002 */       if (var2 == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1007 */       ((Entity)var2).mountEntity(var3);
/*      */       
/* 1009 */       if (var4)
/*      */       {
/* 1011 */         GameSettings var5 = this.gameController.gameSettings;
/* 1012 */         this.gameController.ingameGUI.setRecordPlaying(I18n.format("mount.onboard", new Object[] { GameSettings.getKeyDisplayString(var5.keyBindSneak.getKeyCode()) }), false);
/*      */       }
/*      */     
/* 1015 */     } else if (packetIn.func_149404_c() == 1 && var2 instanceof EntityLiving) {
/*      */       
/* 1017 */       if (var3 != null) {
/*      */         
/* 1019 */         ((EntityLiving)var2).setLeashedToEntity(var3, false);
/*      */       }
/*      */       else {
/*      */         
/* 1023 */         ((EntityLiving)var2).clearLeashed(false, false);
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
/*      */   public void handleEntityStatus(S19PacketEntityStatus packetIn) {
/* 1036 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1037 */     Entity var2 = packetIn.func_149161_a((World)this.clientWorldController);
/*      */     
/* 1039 */     if (var2 != null)
/*      */     {
/* 1041 */       if (packetIn.func_149160_c() == 21) {
/*      */         
/* 1043 */         this.gameController.getSoundHandler().playSound((ISound)new GuardianSound((EntityGuardian)var2));
/*      */       }
/*      */       else {
/*      */         
/* 1047 */         var2.handleHealthUpdate(packetIn.func_149160_c());
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleUpdateHealth(S06PacketUpdateHealth packetIn) {
/* 1054 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1055 */     this.gameController.thePlayer.setPlayerSPHealth(packetIn.getHealth());
/* 1056 */     this.gameController.thePlayer.getFoodStats().setFoodLevel(packetIn.getFoodLevel());
/* 1057 */     this.gameController.thePlayer.getFoodStats().setFoodSaturationLevel(packetIn.getSaturationLevel());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSetExperience(S1FPacketSetExperience packetIn) {
/* 1062 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1063 */     this.gameController.thePlayer.setXPStats(packetIn.func_149397_c(), packetIn.func_149396_d(), packetIn.func_149395_e());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleRespawn(S07PacketRespawn packetIn) {
/* 1068 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/* 1070 */     if (packetIn.func_149082_c() != this.gameController.thePlayer.dimension) {
/*      */       
/* 1072 */       this.doneLoadingTerrain = false;
/* 1073 */       Scoreboard var2 = this.clientWorldController.getScoreboard();
/* 1074 */       this.clientWorldController = new WorldClient(this, new WorldSettings(0L, packetIn.func_149083_e(), false, this.gameController.theWorld.getWorldInfo().isHardcoreModeEnabled(), packetIn.func_149080_f()), packetIn.func_149082_c(), packetIn.func_149081_d(), this.gameController.mcProfiler);
/* 1075 */       this.clientWorldController.setWorldScoreboard(var2);
/* 1076 */       this.gameController.loadWorld(this.clientWorldController);
/* 1077 */       this.gameController.thePlayer.dimension = packetIn.func_149082_c();
/* 1078 */       this.gameController.displayGuiScreen((GuiScreen)new GuiDownloadTerrain(this));
/*      */     } 
/*      */     
/* 1081 */     this.gameController.setDimensionAndSpawnPlayer(packetIn.func_149082_c());
/* 1082 */     this.gameController.playerController.setGameType(packetIn.func_149083_e());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleExplosion(S27PacketExplosion packetIn) {
/* 1090 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1091 */     Explosion var2 = new Explosion((World)this.gameController.theWorld, null, packetIn.func_149148_f(), packetIn.func_149143_g(), packetIn.func_149145_h(), packetIn.func_149146_i(), packetIn.func_149150_j());
/* 1092 */     var2.doExplosionB(true);
/* 1093 */     this.gameController.thePlayer.motionX += packetIn.func_149149_c();
/* 1094 */     this.gameController.thePlayer.motionY += packetIn.func_149144_d();
/* 1095 */     this.gameController.thePlayer.motionZ += packetIn.func_149147_e();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleOpenWindow(S2DPacketOpenWindow packetIn) {
/* 1104 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1105 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/*      */     
/* 1107 */     if ("minecraft:container".equals(packetIn.func_148902_e())) {
/*      */       
/* 1109 */       var2.displayGUIChest((IInventory)new InventoryBasic(packetIn.func_179840_c(), packetIn.func_148898_f()));
/* 1110 */       var2.openContainer.windowId = packetIn.func_148901_c();
/*      */     }
/* 1112 */     else if ("minecraft:villager".equals(packetIn.func_148902_e())) {
/*      */       
/* 1114 */       var2.displayVillagerTradeGui((IMerchant)new NpcMerchant((EntityPlayer)var2, packetIn.func_179840_c()));
/* 1115 */       var2.openContainer.windowId = packetIn.func_148901_c();
/*      */     }
/* 1117 */     else if ("EntityHorse".equals(packetIn.func_148902_e())) {
/*      */       
/* 1119 */       Entity var3 = this.clientWorldController.getEntityByID(packetIn.func_148897_h());
/*      */       
/* 1121 */       if (var3 instanceof EntityHorse)
/*      */       {
/* 1123 */         var2.displayGUIHorse((EntityHorse)var3, (IInventory)new AnimalChest(packetIn.func_179840_c(), packetIn.func_148898_f()));
/* 1124 */         var2.openContainer.windowId = packetIn.func_148901_c();
/*      */       }
/*      */     
/* 1127 */     } else if (!packetIn.func_148900_g()) {
/*      */       
/* 1129 */       var2.displayGui((IInteractionObject)new LocalBlockIntercommunication(packetIn.func_148902_e(), packetIn.func_179840_c()));
/* 1130 */       var2.openContainer.windowId = packetIn.func_148901_c();
/*      */     }
/*      */     else {
/*      */       
/* 1134 */       ContainerLocalMenu var4 = new ContainerLocalMenu(packetIn.func_148902_e(), packetIn.func_179840_c(), packetIn.func_148898_f());
/* 1135 */       var2.displayGUIChest((IInventory)var4);
/* 1136 */       var2.openContainer.windowId = packetIn.func_148901_c();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSetSlot(S2FPacketSetSlot packetIn) {
/* 1145 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1146 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/*      */     
/* 1148 */     if (packetIn.func_149175_c() == -1) {
/*      */       
/* 1150 */       var2.inventory.setItemStack(packetIn.func_149174_e());
/*      */     }
/*      */     else {
/*      */       
/* 1154 */       boolean var3 = false;
/*      */       
/* 1156 */       if (this.gameController.currentScreen instanceof GuiContainerCreative) {
/*      */         
/* 1158 */         GuiContainerCreative var4 = (GuiContainerCreative)this.gameController.currentScreen;
/* 1159 */         var3 = (var4.func_147056_g() != CreativeTabs.tabInventory.getTabIndex());
/*      */       } 
/*      */       
/* 1162 */       if (packetIn.func_149175_c() == 0 && packetIn.func_149173_d() >= 36 && packetIn.func_149173_d() < 45) {
/*      */         
/* 1164 */         ItemStack var5 = var2.inventoryContainer.getSlot(packetIn.func_149173_d()).getStack();
/*      */         
/* 1166 */         if (packetIn.func_149174_e() != null && (var5 == null || var5.stackSize < (packetIn.func_149174_e()).stackSize))
/*      */         {
/* 1168 */           (packetIn.func_149174_e()).animationsToGo = 5;
/*      */         }
/*      */         
/* 1171 */         var2.inventoryContainer.putStackInSlot(packetIn.func_149173_d(), packetIn.func_149174_e());
/*      */       }
/* 1173 */       else if (packetIn.func_149175_c() == var2.openContainer.windowId && (packetIn.func_149175_c() != 0 || !var3)) {
/*      */         
/* 1175 */         var2.openContainer.putStackInSlot(packetIn.func_149173_d(), packetIn.func_149174_e());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleConfirmTransaction(S32PacketConfirmTransaction packetIn) {
/* 1186 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1187 */     Container var2 = null;
/* 1188 */     EntityPlayerSP var3 = this.gameController.thePlayer;
/*      */     
/* 1190 */     if (packetIn.func_148889_c() == 0) {
/*      */       
/* 1192 */       var2 = var3.inventoryContainer;
/*      */     }
/* 1194 */     else if (packetIn.func_148889_c() == var3.openContainer.windowId) {
/*      */       
/* 1196 */       var2 = var3.openContainer;
/*      */     } 
/*      */     
/* 1199 */     if (var2 != null && !packetIn.func_148888_e())
/*      */     {
/* 1201 */       addToSendQueue((Packet)new C0FPacketConfirmTransaction(packetIn.func_148889_c(), packetIn.func_148890_d(), true));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleWindowItems(S30PacketWindowItems packetIn) {
/* 1210 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1211 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/*      */     
/* 1213 */     if (packetIn.func_148911_c() == 0) {
/*      */       
/* 1215 */       var2.inventoryContainer.putStacksInSlots(packetIn.func_148910_d());
/*      */     }
/* 1217 */     else if (packetIn.func_148911_c() == var2.openContainer.windowId) {
/*      */       
/* 1219 */       var2.openContainer.putStacksInSlots(packetIn.func_148910_d());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleSignEditorOpen(S36PacketSignEditorOpen packetIn) {
/* 1228 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1229 */     Object var2 = this.clientWorldController.getTileEntity(packetIn.func_179777_a());
/*      */     
/* 1231 */     if (!(var2 instanceof TileEntitySign)) {
/*      */       
/* 1233 */       var2 = new TileEntitySign();
/* 1234 */       ((TileEntity)var2).setWorldObj((World)this.clientWorldController);
/* 1235 */       ((TileEntity)var2).setPos(packetIn.func_179777_a());
/*      */     } 
/*      */     
/* 1238 */     this.gameController.thePlayer.func_175141_a((TileEntitySign)var2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateSign(S33PacketUpdateSign packetIn) {
/* 1246 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1247 */     boolean var2 = false;
/*      */     
/* 1249 */     if (this.gameController.theWorld.isBlockLoaded(packetIn.func_179704_a())) {
/*      */       
/* 1251 */       TileEntity var3 = this.gameController.theWorld.getTileEntity(packetIn.func_179704_a());
/*      */       
/* 1253 */       if (var3 instanceof TileEntitySign) {
/*      */         
/* 1255 */         TileEntitySign var4 = (TileEntitySign)var3;
/*      */         
/* 1257 */         if (var4.getIsEditable()) {
/*      */           
/* 1259 */           System.arraycopy(packetIn.func_180753_b(), 0, var4.signText, 0, 4);
/* 1260 */           var4.markDirty();
/*      */         } 
/*      */         
/* 1263 */         var2 = true;
/*      */       } 
/*      */     } 
/*      */     
/* 1267 */     if (!var2 && this.gameController.thePlayer != null)
/*      */     {
/* 1269 */       this.gameController.thePlayer.addChatMessage((IChatComponent)new ChatComponentText("Unable to locate sign at " + packetIn.func_179704_a().getX() + ", " + packetIn.func_179704_a().getY() + ", " + packetIn.func_179704_a().getZ()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateTileEntity(S35PacketUpdateTileEntity packetIn) {
/* 1279 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/* 1281 */     if (this.gameController.theWorld.isBlockLoaded(packetIn.func_179823_a())) {
/*      */       
/* 1283 */       TileEntity var2 = this.gameController.theWorld.getTileEntity(packetIn.func_179823_a());
/* 1284 */       int var3 = packetIn.getTileEntityType();
/*      */       
/* 1286 */       if ((var3 == 1 && var2 instanceof net.minecraft.tileentity.TileEntityMobSpawner) || (var3 == 2 && var2 instanceof net.minecraft.tileentity.TileEntityCommandBlock) || (var3 == 3 && var2 instanceof net.minecraft.tileentity.TileEntityBeacon) || (var3 == 4 && var2 instanceof net.minecraft.tileentity.TileEntitySkull) || (var3 == 5 && var2 instanceof net.minecraft.tileentity.TileEntityFlowerPot) || (var3 == 6 && var2 instanceof net.minecraft.tileentity.TileEntityBanner))
/*      */       {
/* 1288 */         var2.readFromNBT(packetIn.getNbtCompound());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleWindowProperty(S31PacketWindowProperty packetIn) {
/* 1298 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1299 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/*      */     
/* 1301 */     if (var2.openContainer != null && var2.openContainer.windowId == packetIn.func_149182_c())
/*      */     {
/* 1303 */       var2.openContainer.updateProgressBar(packetIn.func_149181_d(), packetIn.func_149180_e());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEntityEquipment(S04PacketEntityEquipment packetIn) {
/* 1309 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1310 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149389_d());
/*      */     
/* 1312 */     if (var2 != null)
/*      */     {
/* 1314 */       var2.setCurrentItemOrArmor(packetIn.func_149388_e(), packetIn.func_149390_c());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleCloseWindow(S2EPacketCloseWindow packetIn) {
/* 1323 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1324 */     this.gameController.thePlayer.func_175159_q();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleBlockAction(S24PacketBlockAction packetIn) {
/* 1334 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1335 */     this.gameController.theWorld.addBlockEvent(packetIn.func_179825_a(), packetIn.getBlockType(), packetIn.getData1(), packetIn.getData2());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleBlockBreakAnim(S25PacketBlockBreakAnim packetIn) {
/* 1343 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1344 */     this.gameController.theWorld.sendBlockBreakProgress(packetIn.func_148845_c(), packetIn.func_179821_b(), packetIn.func_148846_g());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleMapChunkBulk(S26PacketMapChunkBulk packetIn) {
/* 1349 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/* 1351 */     for (int var2 = 0; var2 < packetIn.func_149254_d(); var2++) {
/*      */       
/* 1353 */       int var3 = packetIn.func_149255_a(var2);
/* 1354 */       int var4 = packetIn.func_149253_b(var2);
/* 1355 */       this.clientWorldController.doPreChunk(var3, var4, true);
/* 1356 */       this.clientWorldController.invalidateBlockReceiveRegion(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
/* 1357 */       Chunk var5 = this.clientWorldController.getChunkFromChunkCoords(var3, var4);
/* 1358 */       var5.func_177439_a(packetIn.func_149256_c(var2), packetIn.func_179754_d(var2), true);
/* 1359 */       this.clientWorldController.markBlockRangeForRenderUpdate(var3 << 4, 0, var4 << 4, (var3 << 4) + 15, 256, (var4 << 4) + 15);
/*      */       
/* 1361 */       if (!(this.clientWorldController.provider instanceof net.minecraft.world.WorldProviderSurface))
/*      */       {
/* 1363 */         var5.resetRelightChecks();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleChangeGameState(S2BPacketChangeGameState packetIn) {
/* 1370 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1371 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/* 1372 */     int var3 = packetIn.func_149138_c();
/* 1373 */     float var4 = packetIn.func_149137_d();
/* 1374 */     int var5 = MathHelper.floor_float(var4 + 0.5F);
/*      */     
/* 1376 */     if (var3 >= 0 && var3 < S2BPacketChangeGameState.MESSAGE_NAMES.length && S2BPacketChangeGameState.MESSAGE_NAMES[var3] != null)
/*      */     {
/* 1378 */       var2.addChatComponentMessage((IChatComponent)new ChatComponentTranslation(S2BPacketChangeGameState.MESSAGE_NAMES[var3], new Object[0]));
/*      */     }
/*      */     
/* 1381 */     if (var3 == 1) {
/*      */       
/* 1383 */       this.clientWorldController.getWorldInfo().setRaining(true);
/* 1384 */       this.clientWorldController.setRainStrength(0.0F);
/*      */     }
/* 1386 */     else if (var3 == 2) {
/*      */       
/* 1388 */       this.clientWorldController.getWorldInfo().setRaining(false);
/* 1389 */       this.clientWorldController.setRainStrength(1.0F);
/*      */     }
/* 1391 */     else if (var3 == 3) {
/*      */       
/* 1393 */       this.gameController.playerController.setGameType(WorldSettings.GameType.getByID(var5));
/*      */     }
/* 1395 */     else if (var3 == 4) {
/*      */       
/* 1397 */       this.gameController.displayGuiScreen((GuiScreen)new GuiWinGame());
/*      */     }
/* 1399 */     else if (var3 == 5) {
/*      */       
/* 1401 */       GameSettings var6 = this.gameController.gameSettings;
/*      */       
/* 1403 */       if (var4 == 0.0F)
/*      */       {
/* 1405 */         this.gameController.displayGuiScreen((GuiScreen)new GuiScreenDemo());
/*      */       }
/* 1407 */       else if (var4 == 101.0F)
/*      */       {
/* 1409 */         this.gameController.ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentTranslation("demo.help.movement", new Object[] { GameSettings.getKeyDisplayString(var6.keyBindForward.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindLeft.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindBack.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindRight.getKeyCode()) }));
/*      */       }
/* 1411 */       else if (var4 == 102.0F)
/*      */       {
/* 1413 */         this.gameController.ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentTranslation("demo.help.jump", new Object[] { GameSettings.getKeyDisplayString(var6.keyBindJump.getKeyCode()) }));
/*      */       }
/* 1415 */       else if (var4 == 103.0F)
/*      */       {
/* 1417 */         this.gameController.ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentTranslation("demo.help.inventory", new Object[] { GameSettings.getKeyDisplayString(var6.keyBindInventory.getKeyCode()) }));
/*      */       }
/*      */     
/* 1420 */     } else if (var3 == 6) {
/*      */       
/* 1422 */       this.clientWorldController.playSound(var2.posX, var2.posY + var2.getEyeHeight(), var2.posZ, "random.successful_hit", 0.18F, 0.45F, false);
/*      */     }
/* 1424 */     else if (var3 == 7) {
/*      */       
/* 1426 */       this.clientWorldController.setRainStrength(var4);
/*      */     }
/* 1428 */     else if (var3 == 8) {
/*      */       
/* 1430 */       this.clientWorldController.setThunderStrength(var4);
/*      */     }
/* 1432 */     else if (var3 == 10) {
/*      */       
/* 1434 */       this.clientWorldController.spawnParticle(EnumParticleTypes.MOB_APPEARANCE, var2.posX, var2.posY, var2.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/* 1435 */       this.clientWorldController.playSound(var2.posX, var2.posY, var2.posZ, "mob.guardian.curse", 1.0F, 1.0F, false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMaps(S34PacketMaps packetIn) {
/* 1445 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1446 */     MapData var2 = ItemMap.loadMapData(packetIn.getMapId(), (World)this.gameController.theWorld);
/* 1447 */     packetIn.func_179734_a(var2);
/* 1448 */     this.gameController.entityRenderer.getMapItemRenderer().func_148246_a(var2);
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEffect(S28PacketEffect packetIn) {
/* 1453 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/* 1455 */     if (packetIn.isSoundServerwide()) {
/*      */       
/* 1457 */       this.gameController.theWorld.func_175669_a(packetIn.getSoundType(), packetIn.func_179746_d(), packetIn.getSoundData());
/*      */     }
/*      */     else {
/*      */       
/* 1461 */       this.gameController.theWorld.playAuxSFX(packetIn.getSoundType(), packetIn.func_179746_d(), packetIn.getSoundData());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleStatistics(S37PacketStatistics packetIn) {
/* 1470 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1471 */     boolean var2 = false;
/*      */ 
/*      */ 
/*      */     
/* 1475 */     for (Iterator<Map.Entry> var3 = packetIn.func_148974_c().entrySet().iterator(); var3.hasNext(); this.gameController.thePlayer.getStatFileWriter().func_150873_a((EntityPlayer)this.gameController.thePlayer, var5, var6)) {
/*      */       
/* 1477 */       Map.Entry var4 = var3.next();
/* 1478 */       StatBase var5 = (StatBase)var4.getKey();
/* 1479 */       int var6 = ((Integer)var4.getValue()).intValue();
/*      */       
/* 1481 */       if (var5.isAchievement() && var6 > 0) {
/*      */         
/* 1483 */         if (this.field_147308_k && this.gameController.thePlayer.getStatFileWriter().writeStat(var5) == 0) {
/*      */           
/* 1485 */           Achievement var7 = (Achievement)var5;
/* 1486 */           this.gameController.guiAchievement.displayAchievement(var7);
/* 1487 */           this.gameController.getTwitchStream().func_152911_a((Metadata)new MetadataAchievement(var7), 0L);
/*      */           
/* 1489 */           if (var5 == AchievementList.openInventory) {
/*      */             
/* 1491 */             this.gameController.gameSettings.showInventoryAchievementHint = false;
/* 1492 */             this.gameController.gameSettings.saveOptions();
/*      */           } 
/*      */         } 
/*      */         
/* 1496 */         var2 = true;
/*      */       } 
/*      */     } 
/*      */     
/* 1500 */     if (!this.field_147308_k && !var2 && this.gameController.gameSettings.showInventoryAchievementHint)
/*      */     {
/* 1502 */       this.gameController.guiAchievement.displayUnformattedAchievement(AchievementList.openInventory);
/*      */     }
/*      */     
/* 1505 */     this.field_147308_k = true;
/*      */     
/* 1507 */     if (this.gameController.currentScreen instanceof IProgressMeter)
/*      */     {
/* 1509 */       ((IProgressMeter)this.gameController.currentScreen).doneLoading();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleEntityEffect(S1DPacketEntityEffect packetIn) {
/* 1515 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1516 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149426_d());
/*      */     
/* 1518 */     if (var2 instanceof EntityLivingBase) {
/*      */       
/* 1520 */       PotionEffect var3 = new PotionEffect(packetIn.func_149427_e(), packetIn.func_180755_e(), packetIn.func_149428_f(), false, packetIn.func_179707_f());
/* 1521 */       var3.setPotionDurationMax(packetIn.func_149429_c());
/* 1522 */       ((EntityLivingBase)var2).addPotionEffect(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175098_a(S42PacketCombatEvent p_175098_1_) {
/* 1528 */     PacketThreadUtil.func_180031_a((Packet)p_175098_1_, (INetHandler)this, (IThreadListener)this.gameController);
/* 1529 */     Entity var2 = this.clientWorldController.getEntityByID(p_175098_1_.field_179775_c);
/* 1530 */     EntityLivingBase var3 = (var2 instanceof EntityLivingBase) ? (EntityLivingBase)var2 : null;
/*      */     
/* 1532 */     if (p_175098_1_.field_179776_a == S42PacketCombatEvent.Event.END_COMBAT) {
/*      */       
/* 1534 */       long var4 = (1000 * p_175098_1_.field_179772_d / 20);
/* 1535 */       MetadataCombat var6 = new MetadataCombat((EntityLivingBase)this.gameController.thePlayer, var3);
/* 1536 */       this.gameController.getTwitchStream().func_176026_a((Metadata)var6, 0L - var4, 0L);
/*      */     }
/* 1538 */     else if (p_175098_1_.field_179776_a == S42PacketCombatEvent.Event.ENTITY_DIED) {
/*      */       
/* 1540 */       Entity var7 = this.clientWorldController.getEntityByID(p_175098_1_.field_179774_b);
/*      */       
/* 1542 */       if (var7 instanceof EntityPlayer) {
/*      */         
/* 1544 */         MetadataPlayerDeath var5 = new MetadataPlayerDeath((EntityLivingBase)var7, var3);
/* 1545 */         var5.func_152807_a(p_175098_1_.field_179773_e);
/* 1546 */         this.gameController.getTwitchStream().func_152911_a((Metadata)var5, 0L);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175101_a(S41PacketServerDifficulty p_175101_1_) {
/* 1553 */     PacketThreadUtil.func_180031_a((Packet)p_175101_1_, (INetHandler)this, (IThreadListener)this.gameController);
/* 1554 */     this.gameController.theWorld.getWorldInfo().setDifficulty(p_175101_1_.func_179831_b());
/* 1555 */     this.gameController.theWorld.getWorldInfo().setDifficultyLocked(p_175101_1_.func_179830_a());
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175094_a(S43PacketCamera p_175094_1_) {
/* 1560 */     PacketThreadUtil.func_180031_a((Packet)p_175094_1_, (INetHandler)this, (IThreadListener)this.gameController);
/* 1561 */     Entity var2 = p_175094_1_.func_179780_a((World)this.clientWorldController);
/*      */     
/* 1563 */     if (var2 != null)
/*      */     {
/* 1565 */       this.gameController.func_175607_a(var2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175093_a(S44PacketWorldBorder p_175093_1_) {
/* 1571 */     PacketThreadUtil.func_180031_a((Packet)p_175093_1_, (INetHandler)this, (IThreadListener)this.gameController);
/* 1572 */     p_175093_1_.func_179788_a(this.clientWorldController.getWorldBorder());
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175099_a(S45PacketTitle p_175099_1_) {
/* 1577 */     PacketThreadUtil.func_180031_a((Packet)p_175099_1_, (INetHandler)this, (IThreadListener)this.gameController);
/* 1578 */     S45PacketTitle.Type var2 = p_175099_1_.func_179807_a();
/* 1579 */     String var3 = null;
/* 1580 */     String var4 = null;
/* 1581 */     String var5 = (p_175099_1_.func_179805_b() != null) ? p_175099_1_.func_179805_b().getFormattedText() : "";
/*      */     
/* 1583 */     switch (SwitchAction.field_178885_a[var2.ordinal()]) {
/*      */       
/*      */       case 1:
/* 1586 */         var3 = var5;
/*      */         break;
/*      */       
/*      */       case 2:
/* 1590 */         var4 = var5;
/*      */         break;
/*      */       
/*      */       case 3:
/* 1594 */         this.gameController.ingameGUI.func_175178_a("", "", -1, -1, -1);
/* 1595 */         this.gameController.ingameGUI.func_175177_a();
/*      */         return;
/*      */     } 
/*      */     
/* 1599 */     this.gameController.ingameGUI.func_175178_a(var3, var4, p_175099_1_.func_179806_c(), p_175099_1_.func_179804_d(), p_175099_1_.func_179803_e());
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175100_a(S46PacketSetCompressionLevel p_175100_1_) {
/* 1604 */     if (!this.netManager.isLocalChannel())
/*      */     {
/* 1606 */       this.netManager.setCompressionTreshold(p_175100_1_.func_179760_a());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175096_a(S47PacketPlayerListHeaderFooter p_175096_1_) {
/* 1612 */     this.gameController.ingameGUI.getTabList().setHeader((p_175096_1_.func_179700_a().getFormattedText().length() == 0) ? null : p_175096_1_.func_179700_a());
/* 1613 */     this.gameController.ingameGUI.getTabList().setFooter((p_175096_1_.func_179701_b().getFormattedText().length() == 0) ? null : p_175096_1_.func_179701_b());
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect packetIn) {
/* 1618 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1619 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149076_c());
/*      */     
/* 1621 */     if (var2 instanceof EntityLivingBase)
/*      */     {
/* 1623 */       ((EntityLivingBase)var2).removePotionEffectClient(packetIn.func_149075_d());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void handlePlayerListItem(S38PacketPlayerListItem packetIn) {
/* 1629 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1630 */     Iterator<S38PacketPlayerListItem.AddPlayerData> var2 = packetIn.func_179767_a().iterator();
/*      */     
/* 1632 */     while (var2.hasNext()) {
/*      */       
/* 1634 */       S38PacketPlayerListItem.AddPlayerData var3 = var2.next();
/*      */       
/* 1636 */       if (packetIn.func_179768_b() == S38PacketPlayerListItem.Action.REMOVE_PLAYER) {
/*      */         
/* 1638 */         this.playerInfoMap.remove(var3.func_179962_a().getId());
/*      */         
/*      */         continue;
/*      */       } 
/* 1642 */       NetworkPlayerInfo var4 = (NetworkPlayerInfo)this.playerInfoMap.get(var3.func_179962_a().getId());
/*      */       
/* 1644 */       if (packetIn.func_179768_b() == S38PacketPlayerListItem.Action.ADD_PLAYER) {
/*      */         
/* 1646 */         var4 = new NetworkPlayerInfo(var3);
/* 1647 */         this.playerInfoMap.put(var4.func_178845_a().getId(), var4);
/*      */       } 
/*      */       
/* 1650 */       if (var4 != null)
/*      */       {
/* 1652 */         switch (SwitchAction.field_178884_b[packetIn.func_179768_b().ordinal()]) {
/*      */           
/*      */           case 1:
/* 1655 */             var4.func_178839_a(var3.func_179960_c());
/* 1656 */             var4.func_178838_a(var3.func_179963_b());
/*      */ 
/*      */           
/*      */           case 2:
/* 1660 */             var4.func_178839_a(var3.func_179960_c());
/*      */ 
/*      */           
/*      */           case 3:
/* 1664 */             var4.func_178838_a(var3.func_179963_b());
/*      */ 
/*      */           
/*      */           case 4:
/* 1668 */             var4.func_178859_a(var3.func_179961_d());
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleKeepAlive(S00PacketKeepAlive packetIn) {
/* 1677 */     addToSendQueue((Packet)new C00PacketKeepAlive(packetIn.func_149134_c()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void handlePlayerAbilities(S39PacketPlayerAbilities packetIn) {
/* 1682 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1683 */     EntityPlayerSP var2 = this.gameController.thePlayer;
/* 1684 */     var2.capabilities.isFlying = packetIn.isFlying();
/* 1685 */     var2.capabilities.isCreativeMode = packetIn.isCreativeMode();
/* 1686 */     var2.capabilities.disableDamage = packetIn.isInvulnerable();
/* 1687 */     var2.capabilities.allowFlying = packetIn.isAllowFlying();
/* 1688 */     var2.capabilities.setFlySpeed(packetIn.getFlySpeed());
/* 1689 */     var2.capabilities.setPlayerWalkSpeed(packetIn.getWalkSpeed());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleTabComplete(S3APacketTabComplete packetIn) {
/* 1697 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1698 */     String[] var2 = packetIn.func_149630_c();
/*      */     
/* 1700 */     if (this.gameController.currentScreen instanceof GuiChat) {
/*      */       
/* 1702 */       GuiChat var3 = (GuiChat)this.gameController.currentScreen;
/* 1703 */       var3.onAutocompleteResponse(var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleSoundEffect(S29PacketSoundEffect packetIn) {
/* 1709 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1710 */     this.gameController.theWorld.playSound(packetIn.func_149207_d(), packetIn.func_149211_e(), packetIn.func_149210_f(), packetIn.func_149212_c(), packetIn.func_149208_g(), packetIn.func_149209_h(), false);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175095_a(S48PacketResourcePackSend p_175095_1_) {
/* 1715 */     final String var2 = p_175095_1_.func_179783_a();
/* 1716 */     final String var3 = p_175095_1_.func_179784_b();
/*      */     
/* 1718 */     if (var2.startsWith("level://")) {
/*      */       
/* 1720 */       String var4 = var2.substring("level://".length());
/* 1721 */       File var5 = new File(this.gameController.mcDataDir, "saves");
/* 1722 */       File var6 = new File(var5, var4);
/*      */       
/* 1724 */       if (var6.isFile())
/*      */       {
/* 1726 */         this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.ACCEPTED));
/* 1727 */         Futures.addCallback(this.gameController.getResourcePackRepository().func_177319_a(var6), new FutureCallback()
/*      */             {
/*      */               private static final String __OBFID = "CL_00000879";
/*      */               
/*      */               public void onSuccess(Object p_onSuccess_1_) {
/* 1732 */                 NetHandlerPlayClient.this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
/*      */               }
/*      */               
/*      */               public void onFailure(Throwable p_onFailure_1_) {
/* 1736 */                 NetHandlerPlayClient.this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
/*      */               }
/*      */             });
/*      */       }
/*      */       else
/*      */       {
/* 1742 */         this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1747 */     else if (this.gameController.getCurrentServerData() != null && this.gameController.getCurrentServerData().getResourceMode() == ServerData.ServerResourceMode.ENABLED) {
/*      */       
/* 1749 */       this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.ACCEPTED));
/* 1750 */       Futures.addCallback(this.gameController.getResourcePackRepository().func_180601_a(var2, var3), new FutureCallback()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002624";
/*      */             
/*      */             public void onSuccess(Object p_onSuccess_1_) {
/* 1755 */               NetHandlerPlayClient.this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
/*      */             }
/*      */             
/*      */             public void onFailure(Throwable p_onFailure_1_) {
/* 1759 */               NetHandlerPlayClient.this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
/*      */             }
/*      */           });
/*      */     }
/* 1763 */     else if (this.gameController.getCurrentServerData() != null && this.gameController.getCurrentServerData().getResourceMode() != ServerData.ServerResourceMode.PROMPT) {
/*      */       
/* 1765 */       this.netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.DECLINED));
/*      */     }
/*      */     else {
/*      */       
/* 1769 */       this.gameController.addScheduledTask(new Runnable()
/*      */           {
/*      */             private static final String __OBFID = "CL_00002623";
/*      */             
/*      */             public void run() {
/* 1774 */               NetHandlerPlayClient.this.gameController.displayGuiScreen((GuiScreen)new GuiYesNo(new GuiYesNoCallback()
/*      */                     {
/*      */                       private static final String __OBFID = "CL_00002622";
/*      */                       
/*      */                       public void confirmClicked(boolean result, int id) {
/* 1779 */                         (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController = Minecraft.getMinecraft();
/*      */                         
/* 1781 */                         if (result) {
/*      */                           
/* 1783 */                           if ((NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.getCurrentServerData() != null)
/*      */                           {
/* 1785 */                             (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.getCurrentServerData().setResourceMode(ServerData.ServerResourceMode.ENABLED);
/*      */                           }
/*      */                           
/* 1788 */                           (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.ACCEPTED));
/* 1789 */                           Futures.addCallback((NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.getResourcePackRepository().func_180601_a(var2, var3), new FutureCallback()
/*      */                               {
/*      */                                 private static final String __OBFID = "CL_00002621";
/*      */                                 
/*      */                                 public void onSuccess(Object p_onSuccess_1_) {
/* 1794 */                                   (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.null.access$0(NetHandlerPlayClient.null.null.this))).netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.SUCCESSFULLY_LOADED));
/*      */                                 }
/*      */                                 
/*      */                                 public void onFailure(Throwable p_onFailure_1_) {
/* 1798 */                                   (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.null.access$0(NetHandlerPlayClient.null.null.this))).netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.FAILED_DOWNLOAD));
/*      */                                 }
/*      */                               });
/*      */                         }
/*      */                         else {
/*      */                           
/* 1804 */                           if ((NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.getCurrentServerData() != null)
/*      */                           {
/* 1806 */                             (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.getCurrentServerData().setResourceMode(ServerData.ServerResourceMode.DISABLED);
/*      */                           }
/*      */                           
/* 1809 */                           (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).netManager.sendPacket((Packet)new C19PacketResourcePackStatus(var3, C19PacketResourcePackStatus.Action.DECLINED));
/*      */                         } 
/*      */                         
/* 1812 */                         ServerList.func_147414_b((NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.getCurrentServerData());
/* 1813 */                         (NetHandlerPlayClient.null.access$0(NetHandlerPlayClient.null.this)).gameController.displayGuiScreen(null);
/*      */                       }
/* 1815 */                     }I18n.format("multiplayer.texturePrompt.line1", new Object[0]), I18n.format("multiplayer.texturePrompt.line2", new Object[0]), 0));
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175097_a(S49PacketUpdateEntityNBT p_175097_1_) {
/* 1824 */     PacketThreadUtil.func_180031_a((Packet)p_175097_1_, (INetHandler)this, (IThreadListener)this.gameController);
/* 1825 */     Entity var2 = p_175097_1_.func_179764_a((World)this.clientWorldController);
/*      */     
/* 1827 */     if (var2 != null)
/*      */     {
/* 1829 */       var2.func_174834_g(p_175097_1_.func_179763_a());
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
/*      */   public void handleCustomPayload(S3FPacketCustomPayload packetIn) {
/* 1841 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/* 1843 */     if ("MC|TrList".equals(packetIn.getChannelName())) {
/*      */       
/* 1845 */       PacketBuffer var2 = packetIn.getBufferData();
/*      */ 
/*      */       
/*      */       try {
/* 1849 */         int var3 = var2.readInt();
/* 1850 */         GuiScreen var4 = this.gameController.currentScreen;
/*      */         
/* 1852 */         if (var4 != null && var4 instanceof GuiMerchant && var3 == this.gameController.thePlayer.openContainer.windowId)
/*      */         {
/* 1854 */           IMerchant var5 = ((GuiMerchant)var4).getMerchant();
/* 1855 */           MerchantRecipeList var6 = MerchantRecipeList.func_151390_b(var2);
/* 1856 */           var5.setRecipes(var6);
/*      */         }
/*      */       
/* 1859 */       } catch (IOException var10) {
/*      */         
/* 1861 */         logger.error("Couldn't load trade info", var10);
/*      */       }
/*      */       finally {
/*      */         
/* 1865 */         var2.release();
/*      */       }
/*      */     
/* 1868 */     } else if ("MC|Brand".equals(packetIn.getChannelName())) {
/*      */       
/* 1870 */       this.gameController.thePlayer.func_175158_f(packetIn.getBufferData().readStringFromBuffer(32767));
/*      */     }
/* 1872 */     else if ("MC|BOpen".equals(packetIn.getChannelName())) {
/*      */       
/* 1874 */       ItemStack var12 = this.gameController.thePlayer.getCurrentEquippedItem();
/*      */       
/* 1876 */       if (var12 != null && var12.getItem() == Items.written_book)
/*      */       {
/* 1878 */         this.gameController.displayGuiScreen((GuiScreen)new GuiScreenBook((EntityPlayer)this.gameController.thePlayer, var12, false));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleScoreboardObjective(S3BPacketScoreboardObjective packetIn) {
/* 1888 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1889 */     Scoreboard var2 = this.clientWorldController.getScoreboard();
/*      */ 
/*      */     
/* 1892 */     if (packetIn.func_149338_e() == 0) {
/*      */       
/* 1894 */       ScoreObjective var3 = var2.addScoreObjective(packetIn.func_149339_c(), IScoreObjectiveCriteria.DUMMY);
/* 1895 */       var3.setDisplayName(packetIn.func_149337_d());
/* 1896 */       var3.func_178767_a(packetIn.func_179817_d());
/*      */     }
/*      */     else {
/*      */       
/* 1900 */       ScoreObjective var3 = var2.getObjective(packetIn.func_149339_c());
/*      */       
/* 1902 */       if (packetIn.func_149338_e() == 1) {
/*      */         
/* 1904 */         var2.func_96519_k(var3);
/*      */       }
/* 1906 */       else if (packetIn.func_149338_e() == 2) {
/*      */         
/* 1908 */         var3.setDisplayName(packetIn.func_149337_d());
/* 1909 */         var3.func_178767_a(packetIn.func_179817_d());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleUpdateScore(S3CPacketUpdateScore packetIn) {
/* 1919 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1920 */     Scoreboard var2 = this.clientWorldController.getScoreboard();
/* 1921 */     ScoreObjective var3 = var2.getObjective(packetIn.func_149321_d());
/*      */     
/* 1923 */     if (packetIn.func_180751_d() == S3CPacketUpdateScore.Action.CHANGE) {
/*      */       
/* 1925 */       Score var4 = var2.getValueFromObjective(packetIn.func_149324_c(), var3);
/* 1926 */       var4.setScorePoints(packetIn.func_149323_e());
/*      */     }
/* 1928 */     else if (packetIn.func_180751_d() == S3CPacketUpdateScore.Action.REMOVE) {
/*      */       
/* 1930 */       if (StringUtils.isNullOrEmpty(packetIn.func_149321_d())) {
/*      */         
/* 1932 */         var2.func_178822_d(packetIn.func_149324_c(), null);
/*      */       }
/* 1934 */       else if (var3 != null) {
/*      */         
/* 1936 */         var2.func_178822_d(packetIn.func_149324_c(), var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleDisplayScoreboard(S3DPacketDisplayScoreboard packetIn) {
/* 1947 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1948 */     Scoreboard var2 = this.clientWorldController.getScoreboard();
/*      */     
/* 1950 */     if (packetIn.func_149370_d().length() == 0) {
/*      */       
/* 1952 */       var2.setObjectiveInDisplaySlot(packetIn.func_149371_c(), null);
/*      */     }
/*      */     else {
/*      */       
/* 1956 */       ScoreObjective var3 = var2.getObjective(packetIn.func_149370_d());
/* 1957 */       var2.setObjectiveInDisplaySlot(packetIn.func_149371_c(), var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleTeams(S3EPacketTeams packetIn) {
/*      */     ScorePlayerTeam var3;
/* 1967 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 1968 */     Scoreboard var2 = this.clientWorldController.getScoreboard();
/*      */ 
/*      */     
/* 1971 */     if (packetIn.func_149307_h() == 0) {
/*      */       
/* 1973 */       var3 = var2.createTeam(packetIn.func_149312_c());
/*      */     }
/*      */     else {
/*      */       
/* 1977 */       var3 = var2.getTeam(packetIn.func_149312_c());
/*      */     } 
/*      */     
/* 1980 */     if (packetIn.func_149307_h() == 0 || packetIn.func_149307_h() == 2) {
/*      */       
/* 1982 */       var3.setTeamName(packetIn.func_149306_d());
/* 1983 */       var3.setNamePrefix(packetIn.func_149311_e());
/* 1984 */       var3.setNameSuffix(packetIn.func_149309_f());
/* 1985 */       var3.func_178774_a(EnumChatFormatting.func_175744_a(packetIn.func_179813_h()));
/* 1986 */       var3.func_98298_a(packetIn.func_149308_i());
/* 1987 */       Team.EnumVisible var4 = Team.EnumVisible.func_178824_a(packetIn.func_179814_i());
/*      */       
/* 1989 */       if (var4 != null)
/*      */       {
/* 1991 */         var3.func_178772_a(var4);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1998 */     if (packetIn.func_149307_h() == 0 || packetIn.func_149307_h() == 3) {
/*      */       
/* 2000 */       Iterator<String> var6 = packetIn.func_149310_g().iterator();
/*      */       
/* 2002 */       while (var6.hasNext()) {
/*      */         
/* 2004 */         String var5 = var6.next();
/* 2005 */         var2.func_151392_a(var5, packetIn.func_149312_c());
/*      */       } 
/*      */     } 
/*      */     
/* 2009 */     if (packetIn.func_149307_h() == 4) {
/*      */       
/* 2011 */       Iterator<String> var6 = packetIn.func_149310_g().iterator();
/*      */       
/* 2013 */       while (var6.hasNext()) {
/*      */         
/* 2015 */         String var5 = var6.next();
/* 2016 */         var2.removePlayerFromTeam(var5, var3);
/*      */       } 
/*      */     } 
/*      */     
/* 2020 */     if (packetIn.func_149307_h() == 1)
/*      */     {
/* 2022 */       var2.removeTeam(var3);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleParticles(S2APacketParticles packetIn) {
/* 2032 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/*      */     
/* 2034 */     if (packetIn.func_149222_k() == 0) {
/*      */       
/* 2036 */       double var2 = (packetIn.func_149227_j() * packetIn.func_149221_g());
/* 2037 */       double var4 = (packetIn.func_149227_j() * packetIn.func_149224_h());
/* 2038 */       double var6 = (packetIn.func_149227_j() * packetIn.func_149223_i());
/*      */ 
/*      */       
/*      */       try {
/* 2042 */         this.clientWorldController.spawnParticle(packetIn.func_179749_a(), packetIn.func_179750_b(), packetIn.func_149220_d(), packetIn.func_149226_e(), packetIn.func_149225_f(), var2, var4, var6, packetIn.func_179748_k());
/*      */       }
/* 2044 */       catch (Throwable var17) {
/*      */         
/* 2046 */         logger.warn("Could not spawn particle effect " + packetIn.func_179749_a());
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2051 */       for (int var18 = 0; var18 < packetIn.func_149222_k(); var18++) {
/*      */         
/* 2053 */         double var3 = this.avRandomizer.nextGaussian() * packetIn.func_149221_g();
/* 2054 */         double var5 = this.avRandomizer.nextGaussian() * packetIn.func_149224_h();
/* 2055 */         double var7 = this.avRandomizer.nextGaussian() * packetIn.func_149223_i();
/* 2056 */         double var9 = this.avRandomizer.nextGaussian() * packetIn.func_149227_j();
/* 2057 */         double var11 = this.avRandomizer.nextGaussian() * packetIn.func_149227_j();
/* 2058 */         double var13 = this.avRandomizer.nextGaussian() * packetIn.func_149227_j();
/*      */ 
/*      */         
/*      */         try {
/* 2062 */           this.clientWorldController.spawnParticle(packetIn.func_179749_a(), packetIn.func_179750_b(), packetIn.func_149220_d() + var3, packetIn.func_149226_e() + var5, packetIn.func_149225_f() + var7, var9, var11, var13, packetIn.func_179748_k());
/*      */         }
/* 2064 */         catch (Throwable var16) {
/*      */           
/* 2066 */           logger.warn("Could not spawn particle effect " + packetIn.func_179749_a());
/*      */           return;
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
/*      */   public void handleEntityProperties(S20PacketEntityProperties packetIn) {
/* 2080 */     PacketThreadUtil.func_180031_a((Packet)packetIn, (INetHandler)this, (IThreadListener)this.gameController);
/* 2081 */     Entity var2 = this.clientWorldController.getEntityByID(packetIn.func_149442_c());
/*      */     
/* 2083 */     if (var2 != null) {
/*      */       
/* 2085 */       if (!(var2 instanceof EntityLivingBase))
/*      */       {
/* 2087 */         throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + var2 + ")");
/*      */       }
/*      */ 
/*      */       
/* 2091 */       BaseAttributeMap var3 = ((EntityLivingBase)var2).getAttributeMap();
/* 2092 */       Iterator<S20PacketEntityProperties.Snapshot> var4 = packetIn.func_149441_d().iterator();
/*      */       
/* 2094 */       while (var4.hasNext()) {
/*      */         
/* 2096 */         S20PacketEntityProperties.Snapshot var5 = var4.next();
/* 2097 */         IAttributeInstance var6 = var3.getAttributeInstanceByName(var5.func_151409_a());
/*      */         
/* 2099 */         if (var6 == null)
/*      */         {
/* 2101 */           var6 = var3.registerAttribute((IAttribute)new RangedAttribute(null, var5.func_151409_a(), 0.0D, 2.2250738585072014E-308D, Double.MAX_VALUE));
/*      */         }
/*      */         
/* 2104 */         var6.setBaseValue(var5.func_151410_b());
/* 2105 */         var6.removeAllModifiers();
/* 2106 */         Iterator<AttributeModifier> var7 = var5.func_151408_c().iterator();
/*      */         
/* 2108 */         while (var7.hasNext()) {
/*      */           
/* 2110 */           AttributeModifier var8 = var7.next();
/* 2111 */           var6.applyModifier(var8);
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
/*      */   public NetworkManager getNetworkManager() {
/* 2123 */     return this.netManager;
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection func_175106_d() {
/* 2128 */     return this.playerInfoMap.values();
/*      */   }
/*      */ 
/*      */   
/*      */   public NetworkPlayerInfo func_175102_a(UUID p_175102_1_) {
/* 2133 */     return (NetworkPlayerInfo)this.playerInfoMap.get(p_175102_1_);
/*      */   }
/*      */   
/*      */   public NetworkPlayerInfo func_175104_a(String p_175104_1_) {
/*      */     NetworkPlayerInfo var3;
/* 2138 */     Iterator<NetworkPlayerInfo> var2 = this.playerInfoMap.values().iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/* 2143 */       if (!var2.hasNext())
/*      */       {
/* 2145 */         return null;
/*      */       }
/*      */       
/* 2148 */       var3 = var2.next();
/*      */     }
/* 2150 */     while (!var3.func_178845_a().getName().equals(p_175104_1_));
/*      */     
/* 2152 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public GameProfile func_175105_e() {
/* 2157 */     return this.field_175107_d;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final class SwitchAction
/*      */   {
/* 2205 */     static final int[] field_178885_a = new int[(S45PacketTitle.Type.values()).length];
/*      */     
/*      */     static {
/*      */       try {
/* 2209 */         field_178885_a[S45PacketTitle.Type.TITLE.ordinal()] = 1;
/*      */       }
/* 2211 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2218 */         field_178885_a[S45PacketTitle.Type.SUBTITLE.ordinal()] = 2;
/*      */       }
/* 2220 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2227 */         field_178885_a[S45PacketTitle.Type.RESET.ordinal()] = 3;
/*      */       }
/* 2229 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */     
/*      */     static final int[] field_178884_b = new int[(S38PacketPlayerListItem.Action.values()).length];
/*      */     private static final String __OBFID = "CL_00002620";
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\network\NetHandlerPlayClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */