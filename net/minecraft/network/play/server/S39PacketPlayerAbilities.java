/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ 
/*     */ public class S39PacketPlayerAbilities
/*     */   implements Packet
/*     */ {
/*     */   private boolean invulnerable;
/*     */   private boolean flying;
/*     */   private boolean allowFlying;
/*     */   private boolean creativeMode;
/*     */   private float flySpeed;
/*     */   private float walkSpeed;
/*     */   private static final String __OBFID = "CL_00001317";
/*     */   
/*     */   public S39PacketPlayerAbilities() {}
/*     */   
/*     */   public S39PacketPlayerAbilities(PlayerCapabilities capabilities) {
/*  24 */     setInvulnerable(capabilities.disableDamage);
/*  25 */     setFlying(capabilities.isFlying);
/*  26 */     setAllowFlying(capabilities.allowFlying);
/*  27 */     setCreativeMode(capabilities.isCreativeMode);
/*  28 */     setFlySpeed(capabilities.getFlySpeed());
/*  29 */     setWalkSpeed(capabilities.getWalkSpeed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  37 */     byte var2 = data.readByte();
/*  38 */     setInvulnerable(((var2 & 0x1) > 0));
/*  39 */     setFlying(((var2 & 0x2) > 0));
/*  40 */     setAllowFlying(((var2 & 0x4) > 0));
/*  41 */     setCreativeMode(((var2 & 0x8) > 0));
/*  42 */     setFlySpeed(data.readFloat());
/*  43 */     setWalkSpeed(data.readFloat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  51 */     byte var2 = 0;
/*     */     
/*  53 */     if (isInvulnerable())
/*     */     {
/*  55 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     
/*  58 */     if (isFlying())
/*     */     {
/*  60 */       var2 = (byte)(var2 | 0x2);
/*     */     }
/*     */     
/*  63 */     if (isAllowFlying())
/*     */     {
/*  65 */       var2 = (byte)(var2 | 0x4);
/*     */     }
/*     */     
/*  68 */     if (isCreativeMode())
/*     */     {
/*  70 */       var2 = (byte)(var2 | 0x8);
/*     */     }
/*     */     
/*  73 */     data.writeByte(var2);
/*  74 */     data.writeFloat(this.flySpeed);
/*  75 */     data.writeFloat(this.walkSpeed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180742_a(INetHandlerPlayClient p_180742_1_) {
/*  80 */     p_180742_1_.handlePlayerAbilities(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInvulnerable() {
/*  85 */     return this.invulnerable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvulnerable(boolean isInvulnerable) {
/*  90 */     this.invulnerable = isInvulnerable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlying() {
/*  95 */     return this.flying;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlying(boolean isFlying) {
/* 100 */     this.flying = isFlying;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllowFlying() {
/* 105 */     return this.allowFlying;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowFlying(boolean isAllowFlying) {
/* 110 */     this.allowFlying = isAllowFlying;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCreativeMode() {
/* 115 */     return this.creativeMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreativeMode(boolean isCreativeMode) {
/* 120 */     this.creativeMode = isCreativeMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFlySpeed() {
/* 125 */     return this.flySpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlySpeed(float flySpeedIn) {
/* 130 */     this.flySpeed = flySpeedIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getWalkSpeed() {
/* 135 */     return this.walkSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWalkSpeed(float walkSpeedIn) {
/* 140 */     this.walkSpeed = walkSpeedIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 148 */     func_180742_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S39PacketPlayerAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */