/*     */ package net.minecraft.network.play.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayServer;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class C02PacketUseEntity
/*     */   implements Packet
/*     */ {
/*     */   private int entityId;
/*     */   private Action action;
/*     */   private Vec3 field_179713_c;
/*     */   private static final String __OBFID = "CL_00001357";
/*     */   
/*     */   public C02PacketUseEntity() {}
/*     */   
/*     */   public C02PacketUseEntity(Entity p_i45251_1_, Action p_i45251_2_) {
/*  23 */     this.entityId = p_i45251_1_.getEntityId();
/*  24 */     this.action = p_i45251_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public C02PacketUseEntity(Entity p_i45944_1_, Vec3 p_i45944_2_) {
/*  29 */     this(p_i45944_1_, Action.INTERACT_AT);
/*  30 */     this.field_179713_c = p_i45944_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  38 */     this.entityId = data.readVarIntFromBuffer();
/*  39 */     this.action = (Action)data.readEnumValue(Action.class);
/*     */     
/*  41 */     if (this.action == Action.INTERACT_AT)
/*     */     {
/*  43 */       this.field_179713_c = new Vec3(data.readFloat(), data.readFloat(), data.readFloat());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  52 */     data.writeVarIntToBuffer(this.entityId);
/*  53 */     data.writeEnumValue(this.action);
/*     */     
/*  55 */     if (this.action == Action.INTERACT_AT) {
/*     */       
/*  57 */       data.writeFloat((float)this.field_179713_c.xCoord);
/*  58 */       data.writeFloat((float)this.field_179713_c.yCoord);
/*  59 */       data.writeFloat((float)this.field_179713_c.zCoord);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayServer handler) {
/*  68 */     handler.processUseEntity(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getEntityFromWorld(World worldIn) {
/*  73 */     return worldIn.getEntityByID(this.entityId);
/*     */   }
/*     */ 
/*     */   
/*     */   public Action getAction() {
/*  78 */     return this.action;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 func_179712_b() {
/*  83 */     return this.field_179713_c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/*  91 */     processPacket((INetHandlerPlayServer)handler);
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/*  96 */     INTERACT("INTERACT", 0),
/*  97 */     ATTACK("ATTACK", 1),
/*  98 */     INTERACT_AT("INTERACT_AT", 2);
/*     */     
/* 100 */     private static final Action[] $VALUES = new Action[] { INTERACT, ATTACK, INTERACT_AT };
/*     */     private static final String __OBFID = "CL_00001358";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C02PacketUseEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */