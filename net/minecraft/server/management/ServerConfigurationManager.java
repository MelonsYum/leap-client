/*      */ package net.minecraft.server.management;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import java.io.File;
/*      */ import java.net.SocketAddress;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.network.NetHandlerPlayServer;
/*      */ import net.minecraft.network.NetworkManager;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.PacketBuffer;
/*      */ import net.minecraft.network.play.server.S01PacketJoinGame;
/*      */ import net.minecraft.network.play.server.S02PacketChat;
/*      */ import net.minecraft.network.play.server.S03PacketTimeUpdate;
/*      */ import net.minecraft.network.play.server.S05PacketSpawnPosition;
/*      */ import net.minecraft.network.play.server.S07PacketRespawn;
/*      */ import net.minecraft.network.play.server.S09PacketHeldItemChange;
/*      */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*      */ import net.minecraft.network.play.server.S1FPacketSetExperience;
/*      */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*      */ import net.minecraft.network.play.server.S38PacketPlayerListItem;
/*      */ import net.minecraft.network.play.server.S39PacketPlayerAbilities;
/*      */ import net.minecraft.network.play.server.S3EPacketTeams;
/*      */ import net.minecraft.network.play.server.S3FPacketCustomPayload;
/*      */ import net.minecraft.network.play.server.S41PacketServerDifficulty;
/*      */ import net.minecraft.network.play.server.S44PacketWorldBorder;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.scoreboard.ScoreObjective;
/*      */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*      */ import net.minecraft.scoreboard.ServerScoreboard;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.stats.StatList;
/*      */ import net.minecraft.stats.StatisticsFile;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.border.IBorderListener;
/*      */ import net.minecraft.world.border.WorldBorder;
/*      */ import net.minecraft.world.demo.DemoWorldManager;
/*      */ import net.minecraft.world.storage.IPlayerFileData;
/*      */ import net.minecraft.world.storage.WorldInfo;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ public abstract class ServerConfigurationManager
/*      */ {
/*   68 */   public static final File FILE_PLAYERBANS = new File("banned-players.json");
/*   69 */   public static final File FILE_IPBANS = new File("banned-ips.json");
/*   70 */   public static final File FILE_OPS = new File("ops.json");
/*   71 */   public static final File FILE_WHITELIST = new File("whitelist.json");
/*   72 */   private static final Logger logger = LogManager.getLogger();
/*   73 */   private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
/*      */ 
/*      */   
/*      */   private final MinecraftServer mcServer;
/*      */ 
/*      */   
/*   79 */   public final List playerEntityList = Lists.newArrayList();
/*   80 */   public final Map field_177454_f = Maps.newHashMap();
/*      */ 
/*      */   
/*      */   private final UserListBans bannedPlayers;
/*      */ 
/*      */   
/*      */   private final BanList bannedIPs;
/*      */ 
/*      */   
/*      */   private final UserListOps ops;
/*      */ 
/*      */   
/*      */   private final UserListWhitelist whiteListedPlayers;
/*      */ 
/*      */   
/*      */   private final Map playerStatFiles;
/*      */   
/*      */   private IPlayerFileData playerNBTManagerObj;
/*      */   
/*      */   private boolean whiteListEnforced;
/*      */   
/*      */   protected int maxPlayers;
/*      */   
/*      */   private int viewDistance;
/*      */   
/*      */   private WorldSettings.GameType gameType;
/*      */   
/*      */   private boolean commandsAllowedForAll;
/*      */   
/*      */   private int playerPingIndex;
/*      */   
/*      */   private static final String __OBFID = "CL_00001423";
/*      */ 
/*      */   
/*      */   public ServerConfigurationManager(MinecraftServer server) {
/*  115 */     this.bannedPlayers = new UserListBans(FILE_PLAYERBANS);
/*  116 */     this.bannedIPs = new BanList(FILE_IPBANS);
/*  117 */     this.ops = new UserListOps(FILE_OPS);
/*  118 */     this.whiteListedPlayers = new UserListWhitelist(FILE_WHITELIST);
/*  119 */     this.playerStatFiles = Maps.newHashMap();
/*  120 */     this.mcServer = server;
/*  121 */     this.bannedPlayers.setLanServer(false);
/*  122 */     this.bannedIPs.setLanServer(false);
/*  123 */     this.maxPlayers = 8;
/*      */   }
/*      */   
/*      */   public void initializeConnectionToPlayer(NetworkManager netManager, EntityPlayerMP playerIn) {
/*      */     ChatComponentTranslation var13;
/*  128 */     GameProfile var3 = playerIn.getGameProfile();
/*  129 */     PlayerProfileCache var4 = this.mcServer.getPlayerProfileCache();
/*  130 */     GameProfile var5 = var4.func_152652_a(var3.getId());
/*  131 */     String var6 = (var5 == null) ? var3.getName() : var5.getName();
/*  132 */     var4.func_152649_a(var3);
/*  133 */     NBTTagCompound var7 = readPlayerDataFromFile(playerIn);
/*  134 */     playerIn.setWorld((World)this.mcServer.worldServerForDimension(playerIn.dimension));
/*  135 */     playerIn.theItemInWorldManager.setWorld((WorldServer)playerIn.worldObj);
/*  136 */     String var8 = "local";
/*      */     
/*  138 */     if (netManager.getRemoteAddress() != null)
/*      */     {
/*  140 */       var8 = netManager.getRemoteAddress().toString();
/*      */     }
/*      */     
/*  143 */     logger.info(String.valueOf(playerIn.getName()) + "[" + var8 + "] logged in with entity id " + playerIn.getEntityId() + " at (" + playerIn.posX + ", " + playerIn.posY + ", " + playerIn.posZ + ")");
/*  144 */     WorldServer var9 = this.mcServer.worldServerForDimension(playerIn.dimension);
/*  145 */     WorldInfo var10 = var9.getWorldInfo();
/*  146 */     BlockPos var11 = var9.getSpawnPoint();
/*  147 */     func_72381_a(playerIn, null, (World)var9);
/*  148 */     NetHandlerPlayServer var12 = new NetHandlerPlayServer(this.mcServer, netManager, playerIn);
/*  149 */     var12.sendPacket((Packet)new S01PacketJoinGame(playerIn.getEntityId(), playerIn.theItemInWorldManager.getGameType(), var10.isHardcoreModeEnabled(), var9.provider.getDimensionId(), var9.getDifficulty(), getMaxPlayers(), var10.getTerrainType(), var9.getGameRules().getGameRuleBooleanValue("reducedDebugInfo")));
/*  150 */     var12.sendPacket((Packet)new S3FPacketCustomPayload("MC|Brand", (new PacketBuffer(Unpooled.buffer())).writeString(getServerInstance().getServerModName())));
/*  151 */     var12.sendPacket((Packet)new S41PacketServerDifficulty(var10.getDifficulty(), var10.isDifficultyLocked()));
/*  152 */     var12.sendPacket((Packet)new S05PacketSpawnPosition(var11));
/*  153 */     var12.sendPacket((Packet)new S39PacketPlayerAbilities(playerIn.capabilities));
/*  154 */     var12.sendPacket((Packet)new S09PacketHeldItemChange(playerIn.inventory.currentItem));
/*  155 */     playerIn.getStatFile().func_150877_d();
/*  156 */     playerIn.getStatFile().func_150884_b(playerIn);
/*  157 */     func_96456_a((ServerScoreboard)var9.getScoreboard(), playerIn);
/*  158 */     this.mcServer.refreshStatusNextTick();
/*      */ 
/*      */     
/*  161 */     if (!playerIn.getName().equalsIgnoreCase(var6)) {
/*      */       
/*  163 */       var13 = new ChatComponentTranslation("multiplayer.player.joined.renamed", new Object[] { playerIn.getDisplayName(), var6 });
/*      */     }
/*      */     else {
/*      */       
/*  167 */       var13 = new ChatComponentTranslation("multiplayer.player.joined", new Object[] { playerIn.getDisplayName() });
/*      */     } 
/*      */     
/*  170 */     var13.getChatStyle().setColor(EnumChatFormatting.YELLOW);
/*  171 */     sendChatMsg((IChatComponent)var13);
/*  172 */     playerLoggedIn(playerIn);
/*  173 */     var12.setPlayerLocation(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
/*  174 */     updateTimeAndWeatherForPlayer(playerIn, var9);
/*      */     
/*  176 */     if (this.mcServer.getResourcePackUrl().length() > 0)
/*      */     {
/*  178 */       playerIn.func_175397_a(this.mcServer.getResourcePackUrl(), this.mcServer.getResourcePackHash());
/*      */     }
/*      */     
/*  181 */     Iterator<PotionEffect> var14 = playerIn.getActivePotionEffects().iterator();
/*      */     
/*  183 */     while (var14.hasNext()) {
/*      */       
/*  185 */       PotionEffect var15 = var14.next();
/*  186 */       var12.sendPacket((Packet)new S1DPacketEntityEffect(playerIn.getEntityId(), var15));
/*      */     } 
/*      */     
/*  189 */     playerIn.addSelfToInternalCraftingInventory();
/*      */     
/*  191 */     if (var7 != null && var7.hasKey("Riding", 10)) {
/*      */       
/*  193 */       Entity var16 = EntityList.createEntityFromNBT(var7.getCompoundTag("Riding"), (World)var9);
/*      */       
/*  195 */       if (var16 != null) {
/*      */         
/*  197 */         var16.forceSpawn = true;
/*  198 */         var9.spawnEntityInWorld(var16);
/*  199 */         playerIn.mountEntity(var16);
/*  200 */         var16.forceSpawn = false;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_96456_a(ServerScoreboard scoreboardIn, EntityPlayerMP playerIn) {
/*  207 */     HashSet<ScoreObjective> var3 = Sets.newHashSet();
/*  208 */     Iterator<ScorePlayerTeam> var4 = scoreboardIn.getTeams().iterator();
/*      */     
/*  210 */     while (var4.hasNext()) {
/*      */       
/*  212 */       ScorePlayerTeam var5 = var4.next();
/*  213 */       playerIn.playerNetServerHandler.sendPacket((Packet)new S3EPacketTeams(var5, 0));
/*      */     } 
/*      */     
/*  216 */     for (int var9 = 0; var9 < 19; var9++) {
/*      */       
/*  218 */       ScoreObjective var10 = scoreboardIn.getObjectiveInDisplaySlot(var9);
/*      */       
/*  220 */       if (var10 != null && !var3.contains(var10)) {
/*      */         
/*  222 */         List var6 = scoreboardIn.func_96550_d(var10);
/*  223 */         Iterator<Packet> var7 = var6.iterator();
/*      */         
/*  225 */         while (var7.hasNext()) {
/*      */           
/*  227 */           Packet var8 = var7.next();
/*  228 */           playerIn.playerNetServerHandler.sendPacket(var8);
/*      */         } 
/*      */         
/*  231 */         var3.add(var10);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerManager(WorldServer[] p_72364_1_) {
/*  241 */     this.playerNBTManagerObj = p_72364_1_[0].getSaveHandler().getPlayerNBTManager();
/*  242 */     p_72364_1_[0].getWorldBorder().addListener(new IBorderListener()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002267";
/*      */           
/*      */           public void onSizeChanged(WorldBorder border, double newSize) {
/*  247 */             ServerConfigurationManager.this.sendPacketToAllPlayers((Packet)new S44PacketWorldBorder(border, S44PacketWorldBorder.Action.SET_SIZE));
/*      */           }
/*      */           
/*      */           public void func_177692_a(WorldBorder border, double p_177692_2_, double p_177692_4_, long p_177692_6_) {
/*  251 */             ServerConfigurationManager.this.sendPacketToAllPlayers((Packet)new S44PacketWorldBorder(border, S44PacketWorldBorder.Action.LERP_SIZE));
/*      */           }
/*      */           
/*      */           public void onCenterChanged(WorldBorder border, double x, double z) {
/*  255 */             ServerConfigurationManager.this.sendPacketToAllPlayers((Packet)new S44PacketWorldBorder(border, S44PacketWorldBorder.Action.SET_CENTER));
/*      */           }
/*      */           
/*      */           public void onWarningTimeChanged(WorldBorder border, int p_177691_2_) {
/*  259 */             ServerConfigurationManager.this.sendPacketToAllPlayers((Packet)new S44PacketWorldBorder(border, S44PacketWorldBorder.Action.SET_WARNING_TIME));
/*      */           }
/*      */           public void func_177696_b(WorldBorder border, double p_177696_2_) {}
/*      */           public void onWarningDistanceChanged(WorldBorder border, int p_177690_2_) {
/*  263 */             ServerConfigurationManager.this.sendPacketToAllPlayers((Packet)new S44PacketWorldBorder(border, S44PacketWorldBorder.Action.SET_WARNING_BLOCKS));
/*      */           }
/*      */ 
/*      */           
/*      */           public void func_177695_c(WorldBorder border, double p_177695_2_) {}
/*      */         });
/*      */   }
/*      */   
/*      */   public void func_72375_a(EntityPlayerMP playerIn, WorldServer worldIn) {
/*  272 */     WorldServer var3 = playerIn.getServerForPlayer();
/*      */     
/*  274 */     if (worldIn != null)
/*      */     {
/*  276 */       worldIn.getPlayerManager().removePlayer(playerIn);
/*      */     }
/*      */     
/*  279 */     var3.getPlayerManager().addPlayer(playerIn);
/*  280 */     var3.theChunkProviderServer.loadChunk((int)playerIn.posX >> 4, (int)playerIn.posZ >> 4);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEntityViewDistance() {
/*  285 */     return PlayerManager.getFurthestViewableBlock(getViewDistance());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP playerIn) {
/*  293 */     NBTTagCompound var3, var2 = this.mcServer.worldServers[0].getWorldInfo().getPlayerNBTTagCompound();
/*      */ 
/*      */     
/*  296 */     if (playerIn.getName().equals(this.mcServer.getServerOwner()) && var2 != null) {
/*      */       
/*  298 */       playerIn.readFromNBT(var2);
/*  299 */       var3 = var2;
/*  300 */       logger.debug("loading single player");
/*      */     }
/*      */     else {
/*      */       
/*  304 */       var3 = this.playerNBTManagerObj.readPlayerData((EntityPlayer)playerIn);
/*      */     } 
/*      */     
/*  307 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writePlayerData(EntityPlayerMP playerIn) {
/*  315 */     this.playerNBTManagerObj.writePlayerData((EntityPlayer)playerIn);
/*  316 */     StatisticsFile var2 = (StatisticsFile)this.playerStatFiles.get(playerIn.getUniqueID());
/*      */     
/*  318 */     if (var2 != null)
/*      */     {
/*  320 */       var2.func_150883_b();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playerLoggedIn(EntityPlayerMP playerIn) {
/*  329 */     this.playerEntityList.add(playerIn);
/*  330 */     this.field_177454_f.put(playerIn.getUniqueID(), playerIn);
/*  331 */     sendPacketToAllPlayers((Packet)new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.ADD_PLAYER, new EntityPlayerMP[] { playerIn }));
/*  332 */     WorldServer var2 = this.mcServer.worldServerForDimension(playerIn.dimension);
/*  333 */     var2.spawnEntityInWorld((Entity)playerIn);
/*  334 */     func_72375_a(playerIn, null);
/*      */     
/*  336 */     for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
/*      */       
/*  338 */       EntityPlayerMP var4 = this.playerEntityList.get(var3);
/*  339 */       playerIn.playerNetServerHandler.sendPacket((Packet)new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.ADD_PLAYER, new EntityPlayerMP[] { var4 }));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void serverUpdateMountedMovingPlayer(EntityPlayerMP playerIn) {
/*  348 */     playerIn.getServerForPlayer().getPlayerManager().updateMountedMovingPlayer(playerIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playerLoggedOut(EntityPlayerMP playerIn) {
/*  356 */     playerIn.triggerAchievement(StatList.leaveGameStat);
/*  357 */     writePlayerData(playerIn);
/*  358 */     WorldServer var2 = playerIn.getServerForPlayer();
/*      */     
/*  360 */     if (playerIn.ridingEntity != null) {
/*      */       
/*  362 */       var2.removePlayerEntityDangerously(playerIn.ridingEntity);
/*  363 */       logger.debug("removing player mount");
/*      */     } 
/*      */     
/*  366 */     var2.removeEntity((Entity)playerIn);
/*  367 */     var2.getPlayerManager().removePlayer(playerIn);
/*  368 */     this.playerEntityList.remove(playerIn);
/*  369 */     this.field_177454_f.remove(playerIn.getUniqueID());
/*  370 */     this.playerStatFiles.remove(playerIn.getUniqueID());
/*  371 */     sendPacketToAllPlayers((Packet)new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.REMOVE_PLAYER, new EntityPlayerMP[] { playerIn }));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String allowUserToConnect(SocketAddress address, GameProfile profile) {
/*  381 */     if (this.bannedPlayers.isBanned(profile)) {
/*      */       
/*  383 */       UserListBansEntry var5 = (UserListBansEntry)this.bannedPlayers.getEntry(profile);
/*  384 */       String var4 = "You are banned from this server!\nReason: " + var5.getBanReason();
/*      */       
/*  386 */       if (var5.getBanEndDate() != null)
/*      */       {
/*  388 */         var4 = String.valueOf(var4) + "\nYour ban will be removed on " + dateFormat.format(var5.getBanEndDate());
/*      */       }
/*      */       
/*  391 */       return var4;
/*      */     } 
/*  393 */     if (!canJoin(profile))
/*      */     {
/*  395 */       return "You are not white-listed on this server!";
/*      */     }
/*  397 */     if (this.bannedIPs.isBanned(address)) {
/*      */       
/*  399 */       IPBanEntry var3 = this.bannedIPs.getBanEntry(address);
/*  400 */       String var4 = "Your IP address is banned from this server!\nReason: " + var3.getBanReason();
/*      */       
/*  402 */       if (var3.getBanEndDate() != null)
/*      */       {
/*  404 */         var4 = String.valueOf(var4) + "\nYour ban will be removed on " + dateFormat.format(var3.getBanEndDate());
/*      */       }
/*      */       
/*  407 */       return var4;
/*      */     } 
/*      */ 
/*      */     
/*  411 */     return (this.playerEntityList.size() >= this.maxPlayers) ? "The server is full!" : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayerMP createPlayerForUser(GameProfile profile) {
/*      */     Object var7;
/*  420 */     UUID var2 = EntityPlayer.getUUID(profile);
/*  421 */     ArrayList<EntityPlayerMP> var3 = Lists.newArrayList();
/*      */ 
/*      */     
/*  424 */     for (int var4 = 0; var4 < this.playerEntityList.size(); var4++) {
/*      */       
/*  426 */       EntityPlayerMP var5 = this.playerEntityList.get(var4);
/*      */       
/*  428 */       if (var5.getUniqueID().equals(var2))
/*      */       {
/*  430 */         var3.add(var5);
/*      */       }
/*      */     } 
/*      */     
/*  434 */     Iterator<EntityPlayerMP> var6 = var3.iterator();
/*      */     
/*  436 */     while (var6.hasNext()) {
/*      */       
/*  438 */       EntityPlayerMP var5 = var6.next();
/*  439 */       var5.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  444 */     if (this.mcServer.isDemo()) {
/*      */       
/*  446 */       var7 = new DemoWorldManager((World)this.mcServer.worldServerForDimension(0));
/*      */     }
/*      */     else {
/*      */       
/*  450 */       var7 = new ItemInWorldManager((World)this.mcServer.worldServerForDimension(0));
/*      */     } 
/*      */     
/*  453 */     return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), profile, (ItemInWorldManager)var7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayerMP recreatePlayerEntity(EntityPlayerMP playerIn, int dimension, boolean conqueredEnd) {
/*      */     Object var6;
/*  461 */     playerIn.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(playerIn);
/*  462 */     playerIn.getServerForPlayer().getEntityTracker().untrackEntity((Entity)playerIn);
/*  463 */     playerIn.getServerForPlayer().getPlayerManager().removePlayer(playerIn);
/*  464 */     this.playerEntityList.remove(playerIn);
/*  465 */     this.mcServer.worldServerForDimension(playerIn.dimension).removePlayerEntityDangerously((Entity)playerIn);
/*  466 */     BlockPos var4 = playerIn.func_180470_cg();
/*  467 */     boolean var5 = playerIn.isSpawnForced();
/*  468 */     playerIn.dimension = dimension;
/*      */ 
/*      */     
/*  471 */     if (this.mcServer.isDemo()) {
/*      */       
/*  473 */       var6 = new DemoWorldManager((World)this.mcServer.worldServerForDimension(playerIn.dimension));
/*      */     }
/*      */     else {
/*      */       
/*  477 */       var6 = new ItemInWorldManager((World)this.mcServer.worldServerForDimension(playerIn.dimension));
/*      */     } 
/*      */     
/*  480 */     EntityPlayerMP var7 = new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(playerIn.dimension), playerIn.getGameProfile(), (ItemInWorldManager)var6);
/*  481 */     var7.playerNetServerHandler = playerIn.playerNetServerHandler;
/*  482 */     var7.clonePlayer((EntityPlayer)playerIn, conqueredEnd);
/*  483 */     var7.setEntityId(playerIn.getEntityId());
/*  484 */     var7.func_174817_o((Entity)playerIn);
/*  485 */     WorldServer var8 = this.mcServer.worldServerForDimension(playerIn.dimension);
/*  486 */     func_72381_a(var7, playerIn, (World)var8);
/*      */ 
/*      */     
/*  489 */     if (var4 != null) {
/*      */       
/*  491 */       BlockPos blockPos = EntityPlayer.func_180467_a((World)this.mcServer.worldServerForDimension(playerIn.dimension), var4, var5);
/*      */       
/*  493 */       if (blockPos != null) {
/*      */         
/*  495 */         var7.setLocationAndAngles((blockPos.getX() + 0.5F), (blockPos.getY() + 0.1F), (blockPos.getZ() + 0.5F), 0.0F, 0.0F);
/*  496 */         var7.func_180473_a(var4, var5);
/*      */       }
/*      */       else {
/*      */         
/*  500 */         var7.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(0, 0.0F));
/*      */       } 
/*      */     } 
/*      */     
/*  504 */     var8.theChunkProviderServer.loadChunk((int)var7.posX >> 4, (int)var7.posZ >> 4);
/*      */     
/*  506 */     while (!var8.getCollidingBoundingBoxes((Entity)var7, var7.getEntityBoundingBox()).isEmpty() && var7.posY < 256.0D)
/*      */     {
/*  508 */       var7.setPosition(var7.posX, var7.posY + 1.0D, var7.posZ);
/*      */     }
/*      */     
/*  511 */     var7.playerNetServerHandler.sendPacket((Packet)new S07PacketRespawn(var7.dimension, var7.worldObj.getDifficulty(), var7.worldObj.getWorldInfo().getTerrainType(), var7.theItemInWorldManager.getGameType()));
/*  512 */     BlockPos var9 = var8.getSpawnPoint();
/*  513 */     var7.playerNetServerHandler.setPlayerLocation(var7.posX, var7.posY, var7.posZ, var7.rotationYaw, var7.rotationPitch);
/*  514 */     var7.playerNetServerHandler.sendPacket((Packet)new S05PacketSpawnPosition(var9));
/*  515 */     var7.playerNetServerHandler.sendPacket((Packet)new S1FPacketSetExperience(var7.experience, var7.experienceTotal, var7.experienceLevel));
/*  516 */     updateTimeAndWeatherForPlayer(var7, var8);
/*  517 */     var8.getPlayerManager().addPlayer(var7);
/*  518 */     var8.spawnEntityInWorld((Entity)var7);
/*  519 */     this.playerEntityList.add(var7);
/*  520 */     this.field_177454_f.put(var7.getUniqueID(), var7);
/*  521 */     var7.addSelfToInternalCraftingInventory();
/*  522 */     var7.setHealth(var7.getHealth());
/*  523 */     return var7;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transferPlayerToDimension(EntityPlayerMP playerIn, int dimension) {
/*  531 */     int var3 = playerIn.dimension;
/*  532 */     WorldServer var4 = this.mcServer.worldServerForDimension(playerIn.dimension);
/*  533 */     playerIn.dimension = dimension;
/*  534 */     WorldServer var5 = this.mcServer.worldServerForDimension(playerIn.dimension);
/*  535 */     playerIn.playerNetServerHandler.sendPacket((Packet)new S07PacketRespawn(playerIn.dimension, playerIn.worldObj.getDifficulty(), playerIn.worldObj.getWorldInfo().getTerrainType(), playerIn.theItemInWorldManager.getGameType()));
/*  536 */     var4.removePlayerEntityDangerously((Entity)playerIn);
/*  537 */     playerIn.isDead = false;
/*  538 */     transferEntityToWorld((Entity)playerIn, var3, var4, var5);
/*  539 */     func_72375_a(playerIn, var4);
/*  540 */     playerIn.playerNetServerHandler.setPlayerLocation(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
/*  541 */     playerIn.theItemInWorldManager.setWorld(var5);
/*  542 */     updateTimeAndWeatherForPlayer(playerIn, var5);
/*  543 */     syncPlayerInventory(playerIn);
/*  544 */     Iterator<PotionEffect> var6 = playerIn.getActivePotionEffects().iterator();
/*      */     
/*  546 */     while (var6.hasNext()) {
/*      */       
/*  548 */       PotionEffect var7 = var6.next();
/*  549 */       playerIn.playerNetServerHandler.sendPacket((Packet)new S1DPacketEntityEffect(playerIn.getEntityId(), var7));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void transferEntityToWorld(Entity entityIn, int p_82448_2_, WorldServer p_82448_3_, WorldServer p_82448_4_) {
/*  558 */     double var5 = entityIn.posX;
/*  559 */     double var7 = entityIn.posZ;
/*  560 */     double var9 = 8.0D;
/*  561 */     float var11 = entityIn.rotationYaw;
/*  562 */     p_82448_3_.theProfiler.startSection("moving");
/*      */     
/*  564 */     if (entityIn.dimension == -1) {
/*      */       
/*  566 */       var5 = MathHelper.clamp_double(var5 / var9, p_82448_4_.getWorldBorder().minX() + 16.0D, p_82448_4_.getWorldBorder().maxX() - 16.0D);
/*  567 */       var7 = MathHelper.clamp_double(var7 / var9, p_82448_4_.getWorldBorder().minZ() + 16.0D, p_82448_4_.getWorldBorder().maxZ() - 16.0D);
/*  568 */       entityIn.setLocationAndAngles(var5, entityIn.posY, var7, entityIn.rotationYaw, entityIn.rotationPitch);
/*      */       
/*  570 */       if (entityIn.isEntityAlive())
/*      */       {
/*  572 */         p_82448_3_.updateEntityWithOptionalForce(entityIn, false);
/*      */       }
/*      */     }
/*  575 */     else if (entityIn.dimension == 0) {
/*      */       
/*  577 */       var5 = MathHelper.clamp_double(var5 * var9, p_82448_4_.getWorldBorder().minX() + 16.0D, p_82448_4_.getWorldBorder().maxX() - 16.0D);
/*  578 */       var7 = MathHelper.clamp_double(var7 * var9, p_82448_4_.getWorldBorder().minZ() + 16.0D, p_82448_4_.getWorldBorder().maxZ() - 16.0D);
/*  579 */       entityIn.setLocationAndAngles(var5, entityIn.posY, var7, entityIn.rotationYaw, entityIn.rotationPitch);
/*      */       
/*  581 */       if (entityIn.isEntityAlive())
/*      */       {
/*  583 */         p_82448_3_.updateEntityWithOptionalForce(entityIn, false);
/*      */       }
/*      */     } else {
/*      */       BlockPos var12;
/*      */ 
/*      */ 
/*      */       
/*  590 */       if (p_82448_2_ == 1) {
/*      */         
/*  592 */         var12 = p_82448_4_.getSpawnPoint();
/*      */       }
/*      */       else {
/*      */         
/*  596 */         var12 = p_82448_4_.func_180504_m();
/*      */       } 
/*      */       
/*  599 */       var5 = var12.getX();
/*  600 */       entityIn.posY = var12.getY();
/*  601 */       var7 = var12.getZ();
/*  602 */       entityIn.setLocationAndAngles(var5, entityIn.posY, var7, 90.0F, 0.0F);
/*      */       
/*  604 */       if (entityIn.isEntityAlive())
/*      */       {
/*  606 */         p_82448_3_.updateEntityWithOptionalForce(entityIn, false);
/*      */       }
/*      */     } 
/*      */     
/*  610 */     p_82448_3_.theProfiler.endSection();
/*      */     
/*  612 */     if (p_82448_2_ != 1) {
/*      */       
/*  614 */       p_82448_3_.theProfiler.startSection("placing");
/*  615 */       var5 = MathHelper.clamp_int((int)var5, -29999872, 29999872);
/*  616 */       var7 = MathHelper.clamp_int((int)var7, -29999872, 29999872);
/*      */       
/*  618 */       if (entityIn.isEntityAlive()) {
/*      */         
/*  620 */         entityIn.setLocationAndAngles(var5, entityIn.posY, var7, entityIn.rotationYaw, entityIn.rotationPitch);
/*  621 */         p_82448_4_.getDefaultTeleporter().func_180266_a(entityIn, var11);
/*  622 */         p_82448_4_.spawnEntityInWorld(entityIn);
/*  623 */         p_82448_4_.updateEntityWithOptionalForce(entityIn, false);
/*      */       } 
/*      */       
/*  626 */       p_82448_3_.theProfiler.endSection();
/*      */     } 
/*      */     
/*  629 */     entityIn.setWorld((World)p_82448_4_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onTick() {
/*  637 */     if (++this.playerPingIndex > 600) {
/*      */       
/*  639 */       sendPacketToAllPlayers((Packet)new S38PacketPlayerListItem(S38PacketPlayerListItem.Action.UPDATE_LATENCY, this.playerEntityList));
/*  640 */       this.playerPingIndex = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayers(Packet packetIn) {
/*  646 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++)
/*      */     {
/*  648 */       ((EntityPlayerMP)this.playerEntityList.get(var2)).playerNetServerHandler.sendPacket(packetIn);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendPacketToAllPlayersInDimension(Packet packetIn, int dimension) {
/*  654 */     for (int var3 = 0; var3 < this.playerEntityList.size(); var3++) {
/*      */       
/*  656 */       EntityPlayerMP var4 = this.playerEntityList.get(var3);
/*      */       
/*  658 */       if (var4.dimension == dimension)
/*      */       {
/*  660 */         var4.playerNetServerHandler.sendPacket(packetIn);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_177453_a(EntityPlayer p_177453_1_, IChatComponent p_177453_2_) {
/*  667 */     Team var3 = p_177453_1_.getTeam();
/*      */     
/*  669 */     if (var3 != null) {
/*      */       
/*  671 */       Collection var4 = var3.getMembershipCollection();
/*  672 */       Iterator<String> var5 = var4.iterator();
/*      */       
/*  674 */       while (var5.hasNext()) {
/*      */         
/*  676 */         String var6 = var5.next();
/*  677 */         EntityPlayerMP var7 = getPlayerByUsername(var6);
/*      */         
/*  679 */         if (var7 != null && var7 != p_177453_1_)
/*      */         {
/*  681 */           var7.addChatMessage(p_177453_2_);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_177452_b(EntityPlayer p_177452_1_, IChatComponent p_177452_2_) {
/*  689 */     Team var3 = p_177452_1_.getTeam();
/*      */     
/*  691 */     if (var3 == null) {
/*      */       
/*  693 */       sendChatMsg(p_177452_2_);
/*      */     }
/*      */     else {
/*      */       
/*  697 */       for (int var4 = 0; var4 < this.playerEntityList.size(); var4++) {
/*      */         
/*  699 */         EntityPlayerMP var5 = this.playerEntityList.get(var4);
/*      */         
/*  701 */         if (var5.getTeam() != var3)
/*      */         {
/*  703 */           var5.addChatMessage(p_177452_2_);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String func_180602_f() {
/*  711 */     String var1 = "";
/*      */     
/*  713 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++) {
/*      */       
/*  715 */       if (var2 > 0)
/*      */       {
/*  717 */         var1 = String.valueOf(var1) + ", ";
/*      */       }
/*      */       
/*  720 */       var1 = String.valueOf(var1) + ((EntityPlayerMP)this.playerEntityList.get(var2)).getName();
/*      */     } 
/*      */     
/*  723 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getAllUsernames() {
/*  731 */     String[] var1 = new String[this.playerEntityList.size()];
/*      */     
/*  733 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++)
/*      */     {
/*  735 */       var1[var2] = ((EntityPlayerMP)this.playerEntityList.get(var2)).getName();
/*      */     }
/*      */     
/*  738 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public GameProfile[] getAllProfiles() {
/*  743 */     GameProfile[] var1 = new GameProfile[this.playerEntityList.size()];
/*      */     
/*  745 */     for (int var2 = 0; var2 < this.playerEntityList.size(); var2++)
/*      */     {
/*  747 */       var1[var2] = ((EntityPlayerMP)this.playerEntityList.get(var2)).getGameProfile();
/*      */     }
/*      */     
/*  750 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public UserListBans getBannedPlayers() {
/*  755 */     return this.bannedPlayers;
/*      */   }
/*      */ 
/*      */   
/*      */   public BanList getBannedIPs() {
/*  760 */     return this.bannedIPs;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addOp(GameProfile profile) {
/*  765 */     this.ops.addEntry(new UserListOpsEntry(profile, this.mcServer.getOpPermissionLevel()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeOp(GameProfile profile) {
/*  770 */     this.ops.removeEntry(profile);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canJoin(GameProfile profile) {
/*  775 */     return !(this.whiteListEnforced && !this.ops.hasEntry(profile) && !this.whiteListedPlayers.hasEntry(profile));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canSendCommands(GameProfile profile) {
/*  780 */     return !(!this.ops.hasEntry(profile) && (!this.mcServer.isSinglePlayer() || !this.mcServer.worldServers[0].getWorldInfo().areCommandsAllowed() || !this.mcServer.getServerOwner().equalsIgnoreCase(profile.getName())) && !this.commandsAllowedForAll);
/*      */   }
/*      */   
/*      */   public EntityPlayerMP getPlayerByUsername(String username) {
/*      */     EntityPlayerMP var3;
/*  785 */     Iterator<EntityPlayerMP> var2 = this.playerEntityList.iterator();
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  790 */       if (!var2.hasNext())
/*      */       {
/*  792 */         return null;
/*      */       }
/*      */       
/*  795 */       var3 = var2.next();
/*      */     }
/*  797 */     while (!var3.getName().equalsIgnoreCase(username));
/*      */     
/*  799 */     return var3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendToAllNear(double x, double y, double z, double radius, int dimension, Packet packetIn) {
/*  807 */     sendToAllNearExcept(null, x, y, z, radius, dimension, packetIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendToAllNearExcept(EntityPlayer p_148543_1_, double x, double y, double z, double radius, int dimension, Packet p_148543_11_) {
/*  816 */     for (int var12 = 0; var12 < this.playerEntityList.size(); var12++) {
/*      */       
/*  818 */       EntityPlayerMP var13 = this.playerEntityList.get(var12);
/*      */       
/*  820 */       if (var13 != p_148543_1_ && var13.dimension == dimension) {
/*      */         
/*  822 */         double var14 = x - var13.posX;
/*  823 */         double var16 = y - var13.posY;
/*  824 */         double var18 = z - var13.posZ;
/*      */         
/*  826 */         if (var14 * var14 + var16 * var16 + var18 * var18 < radius * radius)
/*      */         {
/*  828 */           var13.playerNetServerHandler.sendPacket(p_148543_11_);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveAllPlayerData() {
/*  839 */     for (int var1 = 0; var1 < this.playerEntityList.size(); var1++)
/*      */     {
/*  841 */       writePlayerData(this.playerEntityList.get(var1));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void addWhitelistedPlayer(GameProfile profile) {
/*  847 */     this.whiteListedPlayers.addEntry(new UserListWhitelistEntry(profile));
/*      */   }
/*      */ 
/*      */   
/*      */   public void removePlayerFromWhitelist(GameProfile profile) {
/*  852 */     this.whiteListedPlayers.removeEntry(profile);
/*      */   }
/*      */ 
/*      */   
/*      */   public UserListWhitelist getWhitelistedPlayers() {
/*  857 */     return this.whiteListedPlayers;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getWhitelistedPlayerNames() {
/*  862 */     return this.whiteListedPlayers.getKeys();
/*      */   }
/*      */ 
/*      */   
/*      */   public UserListOps getOppedPlayers() {
/*  867 */     return this.ops;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getOppedPlayerNames() {
/*  872 */     return this.ops.getKeys();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadWhiteList() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTimeAndWeatherForPlayer(EntityPlayerMP playerIn, WorldServer worldIn) {
/*  885 */     WorldBorder var3 = this.mcServer.worldServers[0].getWorldBorder();
/*  886 */     playerIn.playerNetServerHandler.sendPacket((Packet)new S44PacketWorldBorder(var3, S44PacketWorldBorder.Action.INITIALIZE));
/*  887 */     playerIn.playerNetServerHandler.sendPacket((Packet)new S03PacketTimeUpdate(worldIn.getTotalWorldTime(), worldIn.getWorldTime(), worldIn.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));
/*      */     
/*  889 */     if (worldIn.isRaining()) {
/*      */       
/*  891 */       playerIn.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(1, 0.0F));
/*  892 */       playerIn.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(7, worldIn.getRainStrength(1.0F)));
/*  893 */       playerIn.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(8, worldIn.getWeightedThunderStrength(1.0F)));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void syncPlayerInventory(EntityPlayerMP playerIn) {
/*  902 */     playerIn.sendContainerToPlayer(playerIn.inventoryContainer);
/*  903 */     playerIn.setPlayerHealthUpdated();
/*  904 */     playerIn.playerNetServerHandler.sendPacket((Packet)new S09PacketHeldItemChange(playerIn.inventory.currentItem));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentPlayerCount() {
/*  912 */     return this.playerEntityList.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxPlayers() {
/*  920 */     return this.maxPlayers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getAvailablePlayerDat() {
/*  928 */     return this.mcServer.worldServers[0].getSaveHandler().getPlayerNBTManager().getAvailablePlayerDat();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWhiteListEnabled(boolean whitelistEnabled) {
/*  933 */     this.whiteListEnforced = whitelistEnabled;
/*      */   }
/*      */ 
/*      */   
/*      */   public List getPlayersMatchingAddress(String address) {
/*  938 */     ArrayList<EntityPlayerMP> var2 = Lists.newArrayList();
/*  939 */     Iterator<EntityPlayerMP> var3 = this.playerEntityList.iterator();
/*      */     
/*  941 */     while (var3.hasNext()) {
/*      */       
/*  943 */       EntityPlayerMP var4 = var3.next();
/*      */       
/*  945 */       if (var4.getPlayerIP().equals(address))
/*      */       {
/*  947 */         var2.add(var4);
/*      */       }
/*      */     } 
/*      */     
/*  951 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getViewDistance() {
/*  959 */     return this.viewDistance;
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftServer getServerInstance() {
/*  964 */     return this.mcServer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NBTTagCompound getHostPlayerData() {
/*  972 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152604_a(WorldSettings.GameType p_152604_1_) {
/*  977 */     this.gameType = p_152604_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_72381_a(EntityPlayerMP p_72381_1_, EntityPlayerMP p_72381_2_, World worldIn) {
/*  982 */     if (p_72381_2_ != null) {
/*      */       
/*  984 */       p_72381_1_.theItemInWorldManager.setGameType(p_72381_2_.theItemInWorldManager.getGameType());
/*      */     }
/*  986 */     else if (this.gameType != null) {
/*      */       
/*  988 */       p_72381_1_.theItemInWorldManager.setGameType(this.gameType);
/*      */     } 
/*      */     
/*  991 */     p_72381_1_.theItemInWorldManager.initializeGameType(worldIn.getWorldInfo().getGameType());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCommandsAllowedForAll(boolean p_72387_1_) {
/*  999 */     this.commandsAllowedForAll = p_72387_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAllPlayers() {
/* 1007 */     for (int var1 = 0; var1 < this.playerEntityList.size(); var1++)
/*      */     {
/* 1009 */       ((EntityPlayerMP)this.playerEntityList.get(var1)).playerNetServerHandler.kickPlayerFromServer("Server closed");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendChatMsgImpl(IChatComponent component, boolean isChat) {
/* 1015 */     this.mcServer.addChatMessage(component);
/* 1016 */     int var3 = isChat ? 1 : 0;
/* 1017 */     sendPacketToAllPlayers((Packet)new S02PacketChat(component, (byte)var3));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendChatMsg(IChatComponent component) {
/* 1025 */     sendChatMsgImpl(component, true);
/*      */   }
/*      */ 
/*      */   
/*      */   public StatisticsFile getPlayerStatsFile(EntityPlayer playerIn) {
/* 1030 */     UUID var2 = playerIn.getUniqueID();
/* 1031 */     StatisticsFile var3 = (var2 == null) ? null : (StatisticsFile)this.playerStatFiles.get(var2);
/*      */     
/* 1033 */     if (var3 == null) {
/*      */       
/* 1035 */       File var4 = new File(this.mcServer.worldServerForDimension(0).getSaveHandler().getWorldDirectory(), "stats");
/* 1036 */       File var5 = new File(var4, String.valueOf(var2.toString()) + ".json");
/*      */       
/* 1038 */       if (!var5.exists()) {
/*      */         
/* 1040 */         File var6 = new File(var4, String.valueOf(playerIn.getName()) + ".json");
/*      */         
/* 1042 */         if (var6.exists() && var6.isFile())
/*      */         {
/* 1044 */           var6.renameTo(var5);
/*      */         }
/*      */       } 
/*      */       
/* 1048 */       var3 = new StatisticsFile(this.mcServer, var5);
/* 1049 */       var3.func_150882_a();
/* 1050 */       this.playerStatFiles.put(var2, var3);
/*      */     } 
/*      */     
/* 1053 */     return var3;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setViewDistance(int distance) {
/* 1058 */     this.viewDistance = distance;
/*      */     
/* 1060 */     if (this.mcServer.worldServers != null) {
/*      */       
/* 1062 */       WorldServer[] var2 = this.mcServer.worldServers;
/* 1063 */       int var3 = var2.length;
/*      */       
/* 1065 */       for (int var4 = 0; var4 < var3; var4++) {
/*      */         
/* 1067 */         WorldServer var5 = var2[var4];
/*      */         
/* 1069 */         if (var5 != null)
/*      */         {
/* 1071 */           var5.getPlayerManager().func_152622_a(distance);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayerMP func_177451_a(UUID p_177451_1_) {
/* 1079 */     return (EntityPlayerMP)this.field_177454_f.get(p_177451_1_);
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\ServerConfigurationManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */