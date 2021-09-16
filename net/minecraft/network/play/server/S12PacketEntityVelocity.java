/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class S12PacketEntityVelocity
/*     */   implements Packet
/*     */ {
/*     */   public int entityID;
/*     */   public int motionX;
/*     */   public int motionY;
/*     */   public int motionZ;
/*     */   private static final String __OBFID = "CL_00001328";
/*     */   
/*     */   public S12PacketEntityVelocity() {}
/*     */   
/*     */   public S12PacketEntityVelocity(Entity entity) {
/*  30 */     this(entity.getEntityId(), entity.motionX, entity.motionY, entity.motionZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public S12PacketEntityVelocity(int entityIDIn, double motionXIn, double motionYIn, double motionZIn) {
/*  36 */     this.entityID = entityIDIn;
/*  37 */     double var8 = 3.9D;
/*     */     
/*  39 */     if (motionXIn < -var8)
/*     */     {
/*  41 */       motionXIn = -var8;
/*     */     }
/*     */     
/*  44 */     if (motionYIn < -var8)
/*     */     {
/*  46 */       motionYIn = -var8;
/*     */     }
/*     */     
/*  49 */     if (motionZIn < -var8)
/*     */     {
/*  51 */       motionZIn = -var8;
/*     */     }
/*     */     
/*  54 */     if (motionXIn > var8)
/*     */     {
/*  56 */       motionXIn = var8;
/*     */     }
/*     */     
/*  59 */     if (motionYIn > var8)
/*     */     {
/*  61 */       motionYIn = var8;
/*     */     }
/*     */     
/*  64 */     if (motionZIn > var8)
/*     */     {
/*  66 */       motionZIn = var8;
/*     */     }
/*     */     
/*  69 */     this.motionX = (int)(motionXIn * 8000.0D);
/*  70 */     this.motionY = (int)(motionYIn * 8000.0D);
/*  71 */     this.motionZ = (int)(motionZIn * 8000.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  79 */     this.entityID = data.readVarIntFromBuffer();
/*  80 */     this.motionX = data.readShort();
/*  81 */     this.motionY = data.readShort();
/*  82 */     this.motionZ = data.readShort();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  91 */     data.writeVarIntToBuffer(this.entityID);
/*  92 */     data.writeShort(this.motionX);
/*  93 */     data.writeShort(this.motionY);
/*  94 */     data.writeShort(this.motionZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/* 103 */     handler.handleEntityVelocity(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEntityID() {
/* 108 */     return this.entityID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMotionX() {
/* 113 */     return this.motionX;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMotionY() {
/* 118 */     return this.motionY;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMotionZ() {
/* 123 */     return this.motionZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 131 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S12PacketEntityVelocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */