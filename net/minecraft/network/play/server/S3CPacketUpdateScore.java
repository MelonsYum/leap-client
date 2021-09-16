/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ 
/*     */ public class S3CPacketUpdateScore
/*     */   implements Packet {
/*  13 */   private String name = "";
/*  14 */   private String objective = "";
/*     */   
/*     */   private int value;
/*     */   private Action action;
/*     */   private static final String __OBFID = "CL_00001335";
/*     */   
/*     */   public S3CPacketUpdateScore() {}
/*     */   
/*     */   public S3CPacketUpdateScore(Score scoreIn) {
/*  23 */     this.name = scoreIn.getPlayerName();
/*  24 */     this.objective = scoreIn.getObjective().getName();
/*  25 */     this.value = scoreIn.getScorePoints();
/*  26 */     this.action = Action.CHANGE;
/*     */   }
/*     */ 
/*     */   
/*     */   public S3CPacketUpdateScore(String nameIn) {
/*  31 */     this.name = nameIn;
/*  32 */     this.objective = "";
/*  33 */     this.value = 0;
/*  34 */     this.action = Action.REMOVE;
/*     */   }
/*     */ 
/*     */   
/*     */   public S3CPacketUpdateScore(String nameIn, ScoreObjective objectiveIn) {
/*  39 */     this.name = nameIn;
/*  40 */     this.objective = objectiveIn.getName();
/*  41 */     this.value = 0;
/*  42 */     this.action = Action.REMOVE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  50 */     this.name = data.readStringFromBuffer(40);
/*  51 */     this.action = (Action)data.readEnumValue(Action.class);
/*  52 */     this.objective = data.readStringFromBuffer(16);
/*     */     
/*  54 */     if (this.action != Action.REMOVE)
/*     */     {
/*  56 */       this.value = data.readVarIntFromBuffer();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  65 */     data.writeString(this.name);
/*  66 */     data.writeEnumValue(this.action);
/*  67 */     data.writeString(this.objective);
/*     */     
/*  69 */     if (this.action != Action.REMOVE)
/*     */     {
/*  71 */       data.writeVarIntToBuffer(this.value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  80 */     handler.handleUpdateScore(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149324_c() {
/*  85 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149321_d() {
/*  90 */     return this.objective;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149323_e() {
/*  95 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public Action func_180751_d() {
/* 100 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 108 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/* 113 */     CHANGE("CHANGE", 0),
/* 114 */     REMOVE("REMOVE", 1);
/*     */     
/* 116 */     private static final Action[] $VALUES = new Action[] { CHANGE, REMOVE };
/*     */     private static final String __OBFID = "CL_00002288";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S3CPacketUpdateScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */