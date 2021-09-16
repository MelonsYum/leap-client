/*     */ package net.minecraft.network.play.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class C13PacketPlayerAbilities
/*     */   implements Packet
/*     */ {
/*     */   private boolean invulnerable;
/*     */   private boolean flying;
/*     */   private boolean allowFlying;
/*     */   private boolean creativeMode;
/*     */   private float flySpeed;
/*     */   private float walkSpeed;
/*     */   private static final String __OBFID = "CL_00001364";
/*     */   
/*     */   public C13PacketPlayerAbilities() {}
/*     */   
/*     */   public C13PacketPlayerAbilities(PlayerCapabilities capabilities) {
/*  26 */     setInvulnerable(capabilities.disableDamage);
/*  27 */     setFlying(capabilities.isFlying);
/*  28 */     setAllowFlying(capabilities.allowFlying);
/*  29 */     setCreativeMode(capabilities.isCreativeMode);
/*  30 */     setFlySpeed(capabilities.getFlySpeed());
/*  31 */     setWalkSpeed(capabilities.getWalkSpeed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  40 */     byte var2 = data.readByte();
/*  41 */     setInvulnerable(((var2 & 0x1) > 0));
/*  42 */     setFlying(((var2 & 0x2) > 0));
/*  43 */     setAllowFlying(((var2 & 0x4) > 0));
/*  44 */     setCreativeMode(((var2 & 0x8) > 0));
/*  45 */     setFlySpeed(data.readFloat());
/*  46 */     setWalkSpeed(data.readFloat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  56 */     byte var2 = 0;
/*     */     
/*  58 */     if (isInvulnerable())
/*     */     {
/*  60 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     
/*  63 */     if (isFlying())
/*     */     {
/*  65 */       var2 = (byte)(var2 | 0x2);
/*     */     }
/*     */     
/*  68 */     if (isAllowFlying())
/*     */     {
/*  70 */       var2 = (byte)(var2 | 0x4);
/*     */     }
/*     */     
/*  73 */     if (isCreativeMode())
/*     */     {
/*  75 */       var2 = (byte)(var2 | 0x8);
/*     */     }
/*     */     
/*  78 */     data.writeByte(var2);
/*  79 */     data.writeFloat(this.flySpeed);
/*  80 */     data.writeFloat(this.walkSpeed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180761_a(INetHandlerPlayServer p_180761_1_) {
/*  85 */     p_180761_1_.processPlayerAbilities(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInvulnerable() {
/*  90 */     return this.invulnerable;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvulnerable(boolean isInvulnerable) {
/*  95 */     this.invulnerable = isInvulnerable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlying() {
/* 100 */     return this.flying;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlying(boolean isFlying) {
/* 105 */     this.flying = isFlying;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllowFlying() {
/* 110 */     return this.allowFlying;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowFlying(boolean isAllowFlying) {
/* 115 */     this.allowFlying = isAllowFlying;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCreativeMode() {
/* 120 */     return this.creativeMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCreativeMode(boolean isCreativeMode) {
/* 125 */     this.creativeMode = isCreativeMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlySpeed(float flySpeedIn) {
/* 130 */     this.flySpeed = flySpeedIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWalkSpeed(float walkSpeedIn) {
/* 135 */     this.walkSpeed = walkSpeedIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 143 */     func_180761_a((INetHandlerPlayServer)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C13PacketPlayerAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */