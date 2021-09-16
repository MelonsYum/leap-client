/*     */ package net.minecraft.network;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import gnu.trove.map.TIntObjectMap;
/*     */ import gnu.trove.map.hash.TIntObjectHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.network.handshake.client.C00Handshake;
/*     */ import net.minecraft.network.login.client.C00PacketLoginStart;
/*     */ import net.minecraft.network.login.client.C01PacketEncryptionResponse;
/*     */ import net.minecraft.network.login.server.S00PacketDisconnect;
/*     */ import net.minecraft.network.login.server.S01PacketEncryptionRequest;
/*     */ import net.minecraft.network.login.server.S02PacketLoginSuccess;
/*     */ import net.minecraft.network.login.server.S03PacketEnableCompression;
/*     */ import net.minecraft.network.play.client.C00PacketKeepAlive;
/*     */ import net.minecraft.network.play.client.C01PacketChatMessage;
/*     */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*     */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*     */ import net.minecraft.network.play.client.C0APacketAnimation;
/*     */ import net.minecraft.network.play.client.C0BPacketEntityAction;
/*     */ import net.minecraft.network.play.client.C0CPacketInput;
/*     */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*     */ import net.minecraft.network.play.client.C0EPacketClickWindow;
/*     */ import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
/*     */ import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
/*     */ import net.minecraft.network.play.client.C11PacketEnchantItem;
/*     */ import net.minecraft.network.play.client.C12PacketUpdateSign;
/*     */ import net.minecraft.network.play.client.C13PacketPlayerAbilities;
/*     */ import net.minecraft.network.play.client.C14PacketTabComplete;
/*     */ import net.minecraft.network.play.client.C15PacketClientSettings;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*     */ import net.minecraft.network.play.client.C18PacketSpectate;
/*     */ import net.minecraft.network.play.client.C19PacketResourcePackStatus;
/*     */ import net.minecraft.network.play.server.S00PacketKeepAlive;
/*     */ import net.minecraft.network.play.server.S01PacketJoinGame;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.network.play.server.S03PacketTimeUpdate;
/*     */ import net.minecraft.network.play.server.S04PacketEntityEquipment;
/*     */ import net.minecraft.network.play.server.S05PacketSpawnPosition;
/*     */ import net.minecraft.network.play.server.S06PacketUpdateHealth;
/*     */ import net.minecraft.network.play.server.S07PacketRespawn;
/*     */ import net.minecraft.network.play.server.S08PacketPlayerPosLook;
/*     */ import net.minecraft.network.play.server.S09PacketHeldItemChange;
/*     */ import net.minecraft.network.play.server.S0APacketUseBed;
/*     */ import net.minecraft.network.play.server.S0BPacketAnimation;
/*     */ import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
/*     */ import net.minecraft.network.play.server.S0DPacketCollectItem;
/*     */ import net.minecraft.network.play.server.S0EPacketSpawnObject;
/*     */ import net.minecraft.network.play.server.S0FPacketSpawnMob;
/*     */ import net.minecraft.network.play.server.S10PacketSpawnPainting;
/*     */ import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
/*     */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*     */ import net.minecraft.network.play.server.S13PacketDestroyEntities;
/*     */ import net.minecraft.network.play.server.S14PacketEntity;
/*     */ import net.minecraft.network.play.server.S18PacketEntityTeleport;
/*     */ import net.minecraft.network.play.server.S19PacketEntityHeadLook;
/*     */ import net.minecraft.network.play.server.S19PacketEntityStatus;
/*     */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*     */ import net.minecraft.network.play.server.S1CPacketEntityMetadata;
/*     */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*     */ import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
/*     */ import net.minecraft.network.play.server.S1FPacketSetExperience;
/*     */ import net.minecraft.network.play.server.S20PacketEntityProperties;
/*     */ import net.minecraft.network.play.server.S21PacketChunkData;
/*     */ import net.minecraft.network.play.server.S22PacketMultiBlockChange;
/*     */ import net.minecraft.network.play.server.S23PacketBlockChange;
/*     */ import net.minecraft.network.play.server.S24PacketBlockAction;
/*     */ import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
/*     */ import net.minecraft.network.play.server.S26PacketMapChunkBulk;
/*     */ import net.minecraft.network.play.server.S27PacketExplosion;
/*     */ import net.minecraft.network.play.server.S28PacketEffect;
/*     */ import net.minecraft.network.play.server.S29PacketSoundEffect;
/*     */ import net.minecraft.network.play.server.S2APacketParticles;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
/*     */ import net.minecraft.network.play.server.S2DPacketOpenWindow;
/*     */ import net.minecraft.network.play.server.S2EPacketCloseWindow;
/*     */ import net.minecraft.network.play.server.S2FPacketSetSlot;
/*     */ import net.minecraft.network.play.server.S30PacketWindowItems;
/*     */ import net.minecraft.network.play.server.S31PacketWindowProperty;
/*     */ import net.minecraft.network.play.server.S32PacketConfirmTransaction;
/*     */ import net.minecraft.network.play.server.S33PacketUpdateSign;
/*     */ import net.minecraft.network.play.server.S34PacketMaps;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.network.play.server.S36PacketSignEditorOpen;
/*     */ import net.minecraft.network.play.server.S37PacketStatistics;
/*     */ import net.minecraft.network.play.server.S38PacketPlayerListItem;
/*     */ import net.minecraft.network.play.server.S39PacketPlayerAbilities;
/*     */ import net.minecraft.network.play.server.S3APacketTabComplete;
/*     */ import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
/*     */ import net.minecraft.network.play.server.S3CPacketUpdateScore;
/*     */ import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
/*     */ import net.minecraft.network.play.server.S3EPacketTeams;
/*     */ import net.minecraft.network.play.server.S3FPacketCustomPayload;
/*     */ import net.minecraft.network.play.server.S40PacketDisconnect;
/*     */ import net.minecraft.network.play.server.S41PacketServerDifficulty;
/*     */ import net.minecraft.network.play.server.S42PacketCombatEvent;
/*     */ import net.minecraft.network.play.server.S43PacketCamera;
/*     */ import net.minecraft.network.play.server.S44PacketWorldBorder;
/*     */ import net.minecraft.network.play.server.S45PacketTitle;
/*     */ import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
/*     */ import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
/*     */ import net.minecraft.network.play.server.S48PacketResourcePackSend;
/*     */ import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
/*     */ import net.minecraft.network.status.client.C00PacketServerQuery;
/*     */ import net.minecraft.network.status.client.C01PacketPing;
/*     */ import net.minecraft.network.status.server.S00PacketServerInfo;
/*     */ import net.minecraft.network.status.server.S01PacketPong;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ 
/*     */ public enum EnumConnectionState
/*     */ {
/* 119 */   HANDSHAKING("HANDSHAKING", 0, -1, null) {
/*     */     private static final String __OBFID = "CL_00001246";
/*     */     
/*     */     EnumConnectionState(String $anonymous0, int $anonymous1, int $anonymous2, Object $anonymous3) {
/* 123 */       registerPacket(EnumPacketDirection.SERVERBOUND, C00Handshake.class);
/*     */     }
/*     */   },
/* 126 */   PLAY("PLAY", 1, 0, null) {
/*     */     private static final String __OBFID = "CL_00001250";
/*     */     
/*     */     EnumConnectionState(String $anonymous0, int $anonymous1, int $anonymous2, Object $anonymous3) {
/* 130 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketKeepAlive.class);
/* 131 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketJoinGame.class);
/* 132 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S02PacketChat.class);
/* 133 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S03PacketTimeUpdate.class);
/* 134 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S04PacketEntityEquipment.class);
/* 135 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S05PacketSpawnPosition.class);
/* 136 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S06PacketUpdateHealth.class);
/* 137 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S07PacketRespawn.class);
/* 138 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S08PacketPlayerPosLook.class);
/* 139 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S09PacketHeldItemChange.class);
/* 140 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S0APacketUseBed.class);
/* 141 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S0BPacketAnimation.class);
/* 142 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S0CPacketSpawnPlayer.class);
/* 143 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S0DPacketCollectItem.class);
/* 144 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S0EPacketSpawnObject.class);
/* 145 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S0FPacketSpawnMob.class);
/* 146 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S10PacketSpawnPainting.class);
/* 147 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S11PacketSpawnExperienceOrb.class);
/* 148 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S12PacketEntityVelocity.class);
/* 149 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S13PacketDestroyEntities.class);
/* 150 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S14PacketEntity.class);
/* 151 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S14PacketEntity.S15PacketEntityRelMove.class);
/* 152 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S14PacketEntity.S16PacketEntityLook.class);
/* 153 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S14PacketEntity.S17PacketEntityLookMove.class);
/* 154 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S18PacketEntityTeleport.class);
/* 155 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S19PacketEntityHeadLook.class);
/* 156 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S19PacketEntityStatus.class);
/* 157 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S1BPacketEntityAttach.class);
/* 158 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S1CPacketEntityMetadata.class);
/* 159 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S1DPacketEntityEffect.class);
/* 160 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S1EPacketRemoveEntityEffect.class);
/* 161 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S1FPacketSetExperience.class);
/* 162 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S20PacketEntityProperties.class);
/* 163 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S21PacketChunkData.class);
/* 164 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S22PacketMultiBlockChange.class);
/* 165 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S23PacketBlockChange.class);
/* 166 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S24PacketBlockAction.class);
/* 167 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S25PacketBlockBreakAnim.class);
/* 168 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S26PacketMapChunkBulk.class);
/* 169 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S27PacketExplosion.class);
/* 170 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S28PacketEffect.class);
/* 171 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S29PacketSoundEffect.class);
/* 172 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S2APacketParticles.class);
/* 173 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S2BPacketChangeGameState.class);
/* 174 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S2CPacketSpawnGlobalEntity.class);
/* 175 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S2DPacketOpenWindow.class);
/* 176 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S2EPacketCloseWindow.class);
/* 177 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S2FPacketSetSlot.class);
/* 178 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S30PacketWindowItems.class);
/* 179 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S31PacketWindowProperty.class);
/* 180 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S32PacketConfirmTransaction.class);
/* 181 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S33PacketUpdateSign.class);
/* 182 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S34PacketMaps.class);
/* 183 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S35PacketUpdateTileEntity.class);
/* 184 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S36PacketSignEditorOpen.class);
/* 185 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S37PacketStatistics.class);
/* 186 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S38PacketPlayerListItem.class);
/* 187 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S39PacketPlayerAbilities.class);
/* 188 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S3APacketTabComplete.class);
/* 189 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S3BPacketScoreboardObjective.class);
/* 190 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S3CPacketUpdateScore.class);
/* 191 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S3DPacketDisplayScoreboard.class);
/* 192 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S3EPacketTeams.class);
/* 193 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S3FPacketCustomPayload.class);
/* 194 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S40PacketDisconnect.class);
/* 195 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S41PacketServerDifficulty.class);
/* 196 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S42PacketCombatEvent.class);
/* 197 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S43PacketCamera.class);
/* 198 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S44PacketWorldBorder.class);
/* 199 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S45PacketTitle.class);
/* 200 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S46PacketSetCompressionLevel.class);
/* 201 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S47PacketPlayerListHeaderFooter.class);
/* 202 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S48PacketResourcePackSend.class);
/* 203 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S49PacketUpdateEntityNBT.class);
/* 204 */       registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketKeepAlive.class);
/* 205 */       registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketChatMessage.class);
/* 206 */       registerPacket(EnumPacketDirection.SERVERBOUND, C02PacketUseEntity.class);
/* 207 */       registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.class);
/* 208 */       registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.C04PacketPlayerPosition.class);
/* 209 */       registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.C05PacketPlayerLook.class);
/* 210 */       registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.C06PacketPlayerPosLook.class);
/* 211 */       registerPacket(EnumPacketDirection.SERVERBOUND, C07PacketPlayerDigging.class);
/* 212 */       registerPacket(EnumPacketDirection.SERVERBOUND, C08PacketPlayerBlockPlacement.class);
/* 213 */       registerPacket(EnumPacketDirection.SERVERBOUND, C09PacketHeldItemChange.class);
/* 214 */       registerPacket(EnumPacketDirection.SERVERBOUND, C0APacketAnimation.class);
/* 215 */       registerPacket(EnumPacketDirection.SERVERBOUND, C0BPacketEntityAction.class);
/* 216 */       registerPacket(EnumPacketDirection.SERVERBOUND, C0CPacketInput.class);
/* 217 */       registerPacket(EnumPacketDirection.SERVERBOUND, C0DPacketCloseWindow.class);
/* 218 */       registerPacket(EnumPacketDirection.SERVERBOUND, C0EPacketClickWindow.class);
/* 219 */       registerPacket(EnumPacketDirection.SERVERBOUND, C0FPacketConfirmTransaction.class);
/* 220 */       registerPacket(EnumPacketDirection.SERVERBOUND, C10PacketCreativeInventoryAction.class);
/* 221 */       registerPacket(EnumPacketDirection.SERVERBOUND, C11PacketEnchantItem.class);
/* 222 */       registerPacket(EnumPacketDirection.SERVERBOUND, C12PacketUpdateSign.class);
/* 223 */       registerPacket(EnumPacketDirection.SERVERBOUND, C13PacketPlayerAbilities.class);
/* 224 */       registerPacket(EnumPacketDirection.SERVERBOUND, C14PacketTabComplete.class);
/* 225 */       registerPacket(EnumPacketDirection.SERVERBOUND, C15PacketClientSettings.class);
/* 226 */       registerPacket(EnumPacketDirection.SERVERBOUND, C16PacketClientStatus.class);
/* 227 */       registerPacket(EnumPacketDirection.SERVERBOUND, C17PacketCustomPayload.class);
/* 228 */       registerPacket(EnumPacketDirection.SERVERBOUND, C18PacketSpectate.class);
/* 229 */       registerPacket(EnumPacketDirection.SERVERBOUND, C19PacketResourcePackStatus.class);
/*     */     }
/*     */   },
/* 232 */   STATUS("STATUS", 2, 1, null) {
/*     */     private static final String __OBFID = "CL_00001247";
/*     */     
/*     */     EnumConnectionState(String $anonymous0, int $anonymous1, int $anonymous2, Object $anonymous3) {
/* 236 */       registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketServerQuery.class);
/* 237 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketServerInfo.class);
/* 238 */       registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketPing.class);
/* 239 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketPong.class);
/*     */     }
/*     */   },
/* 242 */   LOGIN("LOGIN", 3, 2, null) {
/*     */     private static final String __OBFID = "CL_00001249";
/*     */     
/*     */     EnumConnectionState(String $anonymous0, int $anonymous1, int $anonymous2, Object $anonymous3) {
/* 246 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketDisconnect.class);
/* 247 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketEncryptionRequest.class);
/* 248 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S02PacketLoginSuccess.class);
/* 249 */       registerPacket(EnumPacketDirection.CLIENTBOUND, S03PacketEnableCompression.class);
/* 250 */       registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketLoginStart.class);
/* 251 */       registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketEncryptionResponse.class);
/*     */     } };
/*     */   static {
/* 254 */     STATES_BY_ID = (TIntObjectMap)new TIntObjectHashMap();
/* 255 */     STATES_BY_CLASS = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/* 259 */     $VALUES = new EnumConnectionState[] { HANDSHAKING, PLAY, STATUS, LOGIN };
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
/* 322 */     EnumConnectionState[] var0 = values();
/* 323 */     int var1 = var0.length;
/*     */     
/* 325 */     for (int var2 = 0; var2 < var1; var2++) {
/*     */       
/* 327 */       EnumConnectionState var3 = var0[var2];
/* 328 */       STATES_BY_ID.put(var3.getId(), var3);
/* 329 */       Iterator<EnumPacketDirection> var4 = var3.directionMaps.keySet().iterator();
/*     */       
/* 331 */       while (var4.hasNext()) {
/*     */         
/* 333 */         EnumPacketDirection var5 = var4.next();
/*     */ 
/*     */         
/* 336 */         for (Iterator<Class<?>> var6 = ((BiMap)var3.directionMaps.get(var5)).values().iterator(); var6.hasNext(); STATES_BY_CLASS.put(var7, var3)) {
/*     */           
/* 338 */           Class<?> var7 = var6.next();
/*     */           
/* 340 */           if (STATES_BY_CLASS.containsKey(var7) && STATES_BY_CLASS.get(var7) != var3)
/*     */           {
/* 342 */             throw new Error("Packet " + var7 + " is already assigned to protocol " + STATES_BY_CLASS.get(var7) + " - can't reassign to " + var3);
/*     */           }
/*     */ 
/*     */           
/*     */           try {
/* 347 */             var7.newInstance();
/*     */           }
/* 349 */           catch (Throwable var9) {
/*     */             
/* 351 */             throw new Error("Packet " + var7 + " fails instantiation checks! " + var7);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final TIntObjectMap STATES_BY_ID;
/*     */   private static final Map STATES_BY_CLASS;
/*     */   private final int id;
/*     */   private final Map directionMaps;
/*     */   private static final EnumConnectionState[] $VALUES;
/*     */   private static final String __OBFID = "CL_00001245";
/*     */   
/*     */   EnumConnectionState(String p_i45152_1_, int p_i45152_2_, int protocolId) {
/*     */     this.directionMaps = Maps.newEnumMap(EnumPacketDirection.class);
/*     */     this.id = protocolId;
/*     */   }
/*     */   
/*     */   protected EnumConnectionState registerPacket(EnumPacketDirection direction, Class packetClass) {
/*     */     Object var3 = this.directionMaps.get(direction);
/*     */     if (var3 == null) {
/*     */       var3 = HashBiMap.create();
/*     */       this.directionMaps.put(direction, var3);
/*     */     } 
/*     */     if (((BiMap)var3).containsValue(packetClass)) {
/*     */       String var4 = direction + " packet " + packetClass + " is already known to ID " + ((BiMap)var3).inverse().get(packetClass);
/*     */       LogManager.getLogger().fatal(var4);
/*     */       throw new IllegalArgumentException(var4);
/*     */     } 
/*     */     ((BiMap)var3).put(Integer.valueOf(((BiMap)var3).size()), packetClass);
/*     */     return this;
/*     */   }
/*     */   
/*     */   public Integer getPacketId(EnumPacketDirection direction, Packet packetIn) {
/*     */     return (Integer)((BiMap)this.directionMaps.get(direction)).inverse().get(packetIn.getClass());
/*     */   }
/*     */   
/*     */   public Packet getPacket(EnumPacketDirection direction, int packetId) throws InstantiationException, IllegalAccessException {
/*     */     Class<Packet> var3 = (Class)((BiMap)this.directionMaps.get(direction)).get(Integer.valueOf(packetId));
/*     */     return (var3 == null) ? null : var3.newInstance();
/*     */   }
/*     */   
/*     */   public int getId() {
/*     */     return this.id;
/*     */   }
/*     */   
/*     */   public static EnumConnectionState getById(int stateId) {
/*     */     return (EnumConnectionState)STATES_BY_ID.get(stateId);
/*     */   }
/*     */   
/*     */   public static EnumConnectionState getFromPacket(Packet packetIn) {
/*     */     return (EnumConnectionState)STATES_BY_CLASS.get(packetIn.getClass());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\EnumConnectionState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */