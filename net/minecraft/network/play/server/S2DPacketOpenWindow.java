/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class S2DPacketOpenWindow
/*     */   implements Packet
/*     */ {
/*     */   private int windowId;
/*     */   private String inventoryType;
/*     */   private IChatComponent windowTitle;
/*     */   private int slotCount;
/*     */   private int entityId;
/*     */   private static final String __OBFID = "CL_00001293";
/*     */   
/*     */   public S2DPacketOpenWindow() {}
/*     */   
/*     */   public S2DPacketOpenWindow(int p_i45981_1_, String p_i45981_2_, IChatComponent p_i45981_3_) {
/*  23 */     this(p_i45981_1_, p_i45981_2_, p_i45981_3_, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public S2DPacketOpenWindow(int p_i45982_1_, String p_i45982_2_, IChatComponent p_i45982_3_, int p_i45982_4_) {
/*  28 */     this.windowId = p_i45982_1_;
/*  29 */     this.inventoryType = p_i45982_2_;
/*  30 */     this.windowTitle = p_i45982_3_;
/*  31 */     this.slotCount = p_i45982_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public S2DPacketOpenWindow(int p_i45983_1_, String p_i45983_2_, IChatComponent p_i45983_3_, int p_i45983_4_, int p_i45983_5_) {
/*  36 */     this(p_i45983_1_, p_i45983_2_, p_i45983_3_, p_i45983_4_);
/*  37 */     this.entityId = p_i45983_5_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/*  45 */     handler.handleOpenWindow(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  53 */     this.windowId = data.readUnsignedByte();
/*  54 */     this.inventoryType = data.readStringFromBuffer(32);
/*  55 */     this.windowTitle = data.readChatComponent();
/*  56 */     this.slotCount = data.readUnsignedByte();
/*     */     
/*  58 */     if (this.inventoryType.equals("EntityHorse"))
/*     */     {
/*  60 */       this.entityId = data.readInt();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  69 */     data.writeByte(this.windowId);
/*  70 */     data.writeString(this.inventoryType);
/*  71 */     data.writeChatComponent(this.windowTitle);
/*  72 */     data.writeByte(this.slotCount);
/*     */     
/*  74 */     if (this.inventoryType.equals("EntityHorse"))
/*     */     {
/*  76 */       data.writeInt(this.entityId);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148901_c() {
/*  82 */     return this.windowId;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_148902_e() {
/*  87 */     return this.inventoryType;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent func_179840_c() {
/*  92 */     return this.windowTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148898_f() {
/*  97 */     return this.slotCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148897_h() {
/* 102 */     return this.entityId;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_148900_g() {
/* 107 */     return (this.slotCount > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 115 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S2DPacketOpenWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */