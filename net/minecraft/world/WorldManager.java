/*     */ package net.minecraft.world;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
/*     */ import net.minecraft.network.play.server.S28PacketEffect;
/*     */ import net.minecraft.network.play.server.S29PacketSoundEffect;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldManager
/*     */   implements IWorldAccess
/*     */ {
/*     */   private MinecraftServer mcServer;
/*     */   private WorldServer theWorldServer;
/*     */   private static final String __OBFID = "CL_00001433";
/*     */   
/*     */   public WorldManager(MinecraftServer p_i1517_1_, WorldServer p_i1517_2_) {
/*  24 */     this.mcServer = p_i1517_1_;
/*  25 */     this.theWorldServer = p_i1517_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180442_a(int p_180442_1_, boolean p_180442_2_, double p_180442_3_, double p_180442_5_, double p_180442_7_, double p_180442_9_, double p_180442_11_, double p_180442_13_, int... p_180442_15_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityAdded(Entity entityIn) {
/*  36 */     this.theWorldServer.getEntityTracker().trackEntity(entityIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityRemoved(Entity entityIn) {
/*  45 */     this.theWorldServer.getEntityTracker().untrackEntity(entityIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(String soundName, double x, double y, double z, float volume, float pitch) {
/*  53 */     this.mcServer.getConfigurationManager().sendToAllNear(x, y, z, (volume > 1.0F) ? (16.0F * volume) : 16.0D, this.theWorldServer.provider.getDimensionId(), (Packet)new S29PacketSoundEffect(soundName, x, y, z, volume, pitch));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSoundToNearExcept(EntityPlayer except, String soundName, double x, double y, double z, float volume, float pitch) {
/*  61 */     this.mcServer.getConfigurationManager().sendToAllNearExcept(except, x, y, z, (volume > 1.0F) ? (16.0F * volume) : 16.0D, this.theWorldServer.provider.getDimensionId(), (Packet)new S29PacketSoundEffect(soundName, x, y, z, volume, pitch));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void markBlockForUpdate(BlockPos pos) {
/*  72 */     this.theWorldServer.getPlayerManager().func_180244_a(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void notifyLightSet(BlockPos pos) {}
/*     */   
/*     */   public void func_174961_a(String p_174961_1_, BlockPos p_174961_2_) {}
/*     */   
/*     */   public void func_180439_a(EntityPlayer p_180439_1_, int p_180439_2_, BlockPos p_180439_3_, int p_180439_4_) {
/*  81 */     this.mcServer.getConfigurationManager().sendToAllNearExcept(p_180439_1_, p_180439_3_.getX(), p_180439_3_.getY(), p_180439_3_.getZ(), 64.0D, this.theWorldServer.provider.getDimensionId(), (Packet)new S28PacketEffect(p_180439_2_, p_180439_3_, p_180439_4_, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180440_a(int p_180440_1_, BlockPos p_180440_2_, int p_180440_3_) {
/*  86 */     this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S28PacketEffect(p_180440_1_, p_180440_2_, p_180440_3_, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
/*  91 */     Iterator<EntityPlayerMP> var4 = (this.mcServer.getConfigurationManager()).playerEntityList.iterator();
/*     */     
/*  93 */     while (var4.hasNext()) {
/*     */       
/*  95 */       EntityPlayerMP var5 = var4.next();
/*     */       
/*  97 */       if (var5 != null && var5.worldObj == this.theWorldServer && var5.getEntityId() != breakerId) {
/*     */         
/*  99 */         double var6 = pos.getX() - var5.posX;
/* 100 */         double var8 = pos.getY() - var5.posY;
/* 101 */         double var10 = pos.getZ() - var5.posZ;
/*     */         
/* 103 */         if (var6 * var6 + var8 * var8 + var10 * var10 < 1024.0D)
/*     */         {
/* 105 */           var5.playerNetServerHandler.sendPacket((Packet)new S25PacketBlockBreakAnim(breakerId, pos, progress));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */