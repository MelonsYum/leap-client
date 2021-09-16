/*     */ package net.minecraft.network.play.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayServer;
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
/*     */ public class C0EPacketClickWindow
/*     */   implements Packet
/*     */ {
/*     */   private int windowId;
/*     */   private int slotId;
/*     */   private int usedButton;
/*     */   private short actionNumber;
/*     */   private ItemStack clickedItem;
/*     */   private int mode;
/*     */   private static final String __OBFID = "CL_00001353";
/*     */   
/*     */   public C0EPacketClickWindow() {}
/*     */   
/*     */   public C0EPacketClickWindow(int p_i45246_1_, int p_i45246_2_, int p_i45246_3_, int p_i45246_4_, ItemStack p_i45246_5_, short p_i45246_6_) {
/*  35 */     this.windowId = p_i45246_1_;
/*  36 */     this.slotId = p_i45246_2_;
/*  37 */     this.usedButton = p_i45246_3_;
/*  38 */     this.clickedItem = (p_i45246_5_ != null) ? p_i45246_5_.copy() : null;
/*  39 */     this.actionNumber = p_i45246_6_;
/*  40 */     this.mode = p_i45246_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayServer handler) {
/*  48 */     handler.processClickWindow(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  56 */     this.windowId = data.readByte();
/*  57 */     this.slotId = data.readShort();
/*  58 */     this.usedButton = data.readByte();
/*  59 */     this.actionNumber = data.readShort();
/*  60 */     this.mode = data.readByte();
/*  61 */     this.clickedItem = data.readItemStackFromBuffer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  69 */     data.writeByte(this.windowId);
/*  70 */     data.writeShort(this.slotId);
/*  71 */     data.writeByte(this.usedButton);
/*  72 */     data.writeShort(this.actionNumber);
/*  73 */     data.writeByte(this.mode);
/*  74 */     data.writeItemStackToBuffer(this.clickedItem);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWindowId() {
/*  79 */     return this.windowId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSlotId() {
/*  84 */     return this.slotId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getUsedButton() {
/*  89 */     return this.usedButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public short getActionNumber() {
/*  94 */     return this.actionNumber;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getClickedItem() {
/*  99 */     return this.clickedItem;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMode() {
/* 104 */     return this.mode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 112 */     processPacket((INetHandlerPlayServer)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C0EPacketClickWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */