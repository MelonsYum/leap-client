/*    */ package net.minecraft.client.gui.inventory;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerDispenser;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiDispenser extends GuiContainer {
/* 11 */   private static final ResourceLocation dispenserGuiTextures = new ResourceLocation("textures/gui/container/dispenser.png");
/*    */   
/*    */   private final InventoryPlayer field_175376_w;
/*    */   public IInventory field_175377_u;
/*    */   private static final String __OBFID = "CL_00000765";
/*    */   
/*    */   public GuiDispenser(InventoryPlayer p_i45503_1_, IInventory p_i45503_2_) {
/* 18 */     super((Container)new ContainerDispenser((IInventory)p_i45503_1_, p_i45503_2_));
/* 19 */     this.field_175376_w = p_i45503_1_;
/* 20 */     this.field_175377_u = p_i45503_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 28 */     String var3 = this.field_175377_u.getDisplayName().getUnformattedText();
/* 29 */     fontRendererObj.drawString(var3, (this.xSize / 2 - fontRendererObj.getStringWidth(var3) / 2), 6.0D, 4210752);
/* 30 */     fontRendererObj.drawString(this.field_175376_w.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 38 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 39 */     this.mc.getTextureManager().bindTexture(dispenserGuiTextures);
/* 40 */     int var4 = (width - this.xSize) / 2;
/* 41 */     int var5 = (height - this.ySize) / 2;
/* 42 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */