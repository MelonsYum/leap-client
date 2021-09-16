/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ServerList;
/*     */ import net.minecraft.client.network.LanServerDetector;
/*     */ 
/*     */ public class ServerSelectionList
/*     */   extends GuiListExtended {
/*     */   private final GuiMultiplayer owner;
/*  13 */   private final List field_148198_l = Lists.newArrayList();
/*  14 */   private final List field_148199_m = Lists.newArrayList();
/*  15 */   private final GuiListExtended.IGuiListEntry lanScanEntry = new ServerListEntryLanScan();
/*  16 */   private int field_148197_o = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00000819";
/*     */   
/*     */   public ServerSelectionList(GuiMultiplayer p_i45049_1_, Minecraft mcIn, int p_i45049_3_, int p_i45049_4_, int p_i45049_5_, int p_i45049_6_, int p_i45049_7_) {
/*  21 */     super(mcIn, p_i45049_3_, p_i45049_4_, p_i45049_5_, p_i45049_6_, p_i45049_7_);
/*  22 */     this.owner = p_i45049_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiListExtended.IGuiListEntry getListEntry(int p_148180_1_) {
/*  30 */     if (p_148180_1_ < this.field_148198_l.size())
/*     */     {
/*  32 */       return this.field_148198_l.get(p_148180_1_);
/*     */     }
/*     */ 
/*     */     
/*  36 */     p_148180_1_ -= this.field_148198_l.size();
/*     */     
/*  38 */     if (p_148180_1_ == 0)
/*     */     {
/*  40 */       return this.lanScanEntry;
/*     */     }
/*     */ 
/*     */     
/*  44 */     p_148180_1_--;
/*  45 */     return this.field_148199_m.get(p_148180_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getSize() {
/*  52 */     return this.field_148198_l.size() + 1 + this.field_148199_m.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148192_c(int p_148192_1_) {
/*  57 */     this.field_148197_o = p_148192_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSelected(int slotIndex) {
/*  65 */     return (slotIndex == this.field_148197_o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_148193_k() {
/*  70 */     return this.field_148197_o;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148195_a(ServerList p_148195_1_) {
/*  75 */     this.field_148198_l.clear();
/*     */     
/*  77 */     for (int var2 = 0; var2 < p_148195_1_.countServers(); var2++)
/*     */     {
/*  79 */       this.field_148198_l.add(new ServerListEntryNormal(this.owner, p_148195_1_.getServerData(var2)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148194_a(List p_148194_1_) {
/*  85 */     this.field_148199_m.clear();
/*  86 */     Iterator<LanServerDetector.LanServer> var2 = p_148194_1_.iterator();
/*     */     
/*  88 */     while (var2.hasNext()) {
/*     */       
/*  90 */       LanServerDetector.LanServer var3 = var2.next();
/*  91 */       this.field_148199_m.add(new ServerListEntryLanDetected(this.owner, var3));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getScrollBarX() {
/*  97 */     return super.getScrollBarX() + 30;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getListWidth() {
/* 105 */     return super.getListWidth() + 85;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\ServerSelectionList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */