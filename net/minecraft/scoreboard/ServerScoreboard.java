/*     */ package net.minecraft.scoreboard;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
/*     */ import net.minecraft.network.play.server.S3CPacketUpdateScore;
/*     */ import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
/*     */ import net.minecraft.network.play.server.S3EPacketTeams;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class ServerScoreboard
/*     */   extends Scoreboard {
/*     */   private final MinecraftServer scoreboardMCServer;
/*  21 */   private final Set field_96553_b = Sets.newHashSet();
/*     */   
/*     */   private ScoreboardSaveData field_96554_c;
/*     */   private static final String __OBFID = "CL_00001424";
/*     */   
/*     */   public ServerScoreboard(MinecraftServer p_i1501_1_) {
/*  27 */     this.scoreboardMCServer = p_i1501_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96536_a(Score p_96536_1_) {
/*  32 */     super.func_96536_a(p_96536_1_);
/*     */     
/*  34 */     if (this.field_96553_b.contains(p_96536_1_.getObjective()))
/*     */     {
/*  36 */       this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3CPacketUpdateScore(p_96536_1_));
/*     */     }
/*     */     
/*  39 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96516_a(String p_96516_1_) {
/*  44 */     super.func_96516_a(p_96516_1_);
/*  45 */     this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3CPacketUpdateScore(p_96516_1_));
/*  46 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178820_a(String p_178820_1_, ScoreObjective p_178820_2_) {
/*  51 */     super.func_178820_a(p_178820_1_, p_178820_2_);
/*  52 */     this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3CPacketUpdateScore(p_178820_1_, p_178820_2_));
/*  53 */     func_96551_b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObjectiveInDisplaySlot(int p_96530_1_, ScoreObjective p_96530_2_) {
/*  61 */     ScoreObjective var3 = getObjectiveInDisplaySlot(p_96530_1_);
/*  62 */     super.setObjectiveInDisplaySlot(p_96530_1_, p_96530_2_);
/*     */     
/*  64 */     if (var3 != p_96530_2_ && var3 != null)
/*     */     {
/*  66 */       if (func_96552_h(var3) > 0) {
/*     */         
/*  68 */         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3DPacketDisplayScoreboard(p_96530_1_, p_96530_2_));
/*     */       }
/*     */       else {
/*     */         
/*  72 */         func_96546_g(var3);
/*     */       } 
/*     */     }
/*     */     
/*  76 */     if (p_96530_2_ != null)
/*     */     {
/*  78 */       if (this.field_96553_b.contains(p_96530_2_)) {
/*     */         
/*  80 */         this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3DPacketDisplayScoreboard(p_96530_1_, p_96530_2_));
/*     */       }
/*     */       else {
/*     */         
/*  84 */         func_96549_e(p_96530_2_);
/*     */       } 
/*     */     }
/*     */     
/*  88 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_151392_a(String p_151392_1_, String p_151392_2_) {
/*  93 */     if (super.func_151392_a(p_151392_1_, p_151392_2_)) {
/*     */       
/*  95 */       ScorePlayerTeam var3 = getTeam(p_151392_2_);
/*  96 */       this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(var3, Arrays.asList(new String[] { p_151392_1_ }, ), 3));
/*  97 */       func_96551_b();
/*  98 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayerFromTeam(String p_96512_1_, ScorePlayerTeam p_96512_2_) {
/* 112 */     super.removePlayerFromTeam(p_96512_1_, p_96512_2_);
/* 113 */     this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(p_96512_2_, Arrays.asList(new String[] { p_96512_1_ }, ), 4));
/* 114 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96522_a(ScoreObjective p_96522_1_) {
/* 119 */     super.func_96522_a(p_96522_1_);
/* 120 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96532_b(ScoreObjective p_96532_1_) {
/* 125 */     super.func_96532_b(p_96532_1_);
/*     */     
/* 127 */     if (this.field_96553_b.contains(p_96532_1_))
/*     */     {
/* 129 */       this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3BPacketScoreboardObjective(p_96532_1_, 2));
/*     */     }
/*     */     
/* 132 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96533_c(ScoreObjective p_96533_1_) {
/* 137 */     super.func_96533_c(p_96533_1_);
/*     */     
/* 139 */     if (this.field_96553_b.contains(p_96533_1_))
/*     */     {
/* 141 */       func_96546_g(p_96533_1_);
/*     */     }
/*     */     
/* 144 */     func_96551_b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void broadcastTeamCreated(ScorePlayerTeam p_96523_1_) {
/* 152 */     super.broadcastTeamCreated(p_96523_1_);
/* 153 */     this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(p_96523_1_, 0));
/* 154 */     func_96551_b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void broadcastTeamRemoved(ScorePlayerTeam p_96538_1_) {
/* 162 */     super.broadcastTeamRemoved(p_96538_1_);
/* 163 */     this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(p_96538_1_, 2));
/* 164 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96513_c(ScorePlayerTeam p_96513_1_) {
/* 169 */     super.func_96513_c(p_96513_1_);
/* 170 */     this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S3EPacketTeams(p_96513_1_, 1));
/* 171 */     func_96551_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96547_a(ScoreboardSaveData p_96547_1_) {
/* 176 */     this.field_96554_c = p_96547_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_96551_b() {
/* 181 */     if (this.field_96554_c != null)
/*     */     {
/* 183 */       this.field_96554_c.markDirty();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_96550_d(ScoreObjective p_96550_1_) {
/* 189 */     ArrayList<S3BPacketScoreboardObjective> var2 = Lists.newArrayList();
/* 190 */     var2.add(new S3BPacketScoreboardObjective(p_96550_1_, 0));
/*     */     
/* 192 */     for (int var3 = 0; var3 < 19; var3++) {
/*     */       
/* 194 */       if (getObjectiveInDisplaySlot(var3) == p_96550_1_)
/*     */       {
/* 196 */         var2.add(new S3DPacketDisplayScoreboard(var3, p_96550_1_));
/*     */       }
/*     */     } 
/*     */     
/* 200 */     Iterator<Score> var5 = getSortedScores(p_96550_1_).iterator();
/*     */     
/* 202 */     while (var5.hasNext()) {
/*     */       
/* 204 */       Score var4 = var5.next();
/* 205 */       var2.add(new S3CPacketUpdateScore(var4));
/*     */     } 
/*     */     
/* 208 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96549_e(ScoreObjective p_96549_1_) {
/* 213 */     List var2 = func_96550_d(p_96549_1_);
/* 214 */     Iterator<EntityPlayerMP> var3 = (this.scoreboardMCServer.getConfigurationManager()).playerEntityList.iterator();
/*     */     
/* 216 */     while (var3.hasNext()) {
/*     */       
/* 218 */       EntityPlayerMP var4 = var3.next();
/* 219 */       Iterator<Packet> var5 = var2.iterator();
/*     */       
/* 221 */       while (var5.hasNext()) {
/*     */         
/* 223 */         Packet var6 = var5.next();
/* 224 */         var4.playerNetServerHandler.sendPacket(var6);
/*     */       } 
/*     */     } 
/*     */     
/* 228 */     this.field_96553_b.add(p_96549_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_96548_f(ScoreObjective p_96548_1_) {
/* 233 */     ArrayList<S3BPacketScoreboardObjective> var2 = Lists.newArrayList();
/* 234 */     var2.add(new S3BPacketScoreboardObjective(p_96548_1_, 1));
/*     */     
/* 236 */     for (int var3 = 0; var3 < 19; var3++) {
/*     */       
/* 238 */       if (getObjectiveInDisplaySlot(var3) == p_96548_1_)
/*     */       {
/* 240 */         var2.add(new S3DPacketDisplayScoreboard(var3, p_96548_1_));
/*     */       }
/*     */     } 
/*     */     
/* 244 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_96546_g(ScoreObjective p_96546_1_) {
/* 249 */     List var2 = func_96548_f(p_96546_1_);
/* 250 */     Iterator<EntityPlayerMP> var3 = (this.scoreboardMCServer.getConfigurationManager()).playerEntityList.iterator();
/*     */     
/* 252 */     while (var3.hasNext()) {
/*     */       
/* 254 */       EntityPlayerMP var4 = var3.next();
/* 255 */       Iterator<Packet> var5 = var2.iterator();
/*     */       
/* 257 */       while (var5.hasNext()) {
/*     */         
/* 259 */         Packet var6 = var5.next();
/* 260 */         var4.playerNetServerHandler.sendPacket(var6);
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     this.field_96553_b.remove(p_96546_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_96552_h(ScoreObjective p_96552_1_) {
/* 269 */     int var2 = 0;
/*     */     
/* 271 */     for (int var3 = 0; var3 < 19; var3++) {
/*     */       
/* 273 */       if (getObjectiveInDisplaySlot(var3) == p_96552_1_)
/*     */       {
/* 275 */         var2++;
/*     */       }
/*     */     } 
/*     */     
/* 279 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\ServerScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */