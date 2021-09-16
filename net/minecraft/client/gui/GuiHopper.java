/*    */ package net.minecraft.client.gui;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerHopper;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiHopper extends GuiContainer {
/* 13 */   private static final ResourceLocation field_147085_u = new ResourceLocation("textures/gui/container/hopper.png");
/*    */   
/*    */   private IInventory field_147084_v;
/*    */   private IInventory field_147083_w;
/*    */   private static final String __OBFID = "CL_00000759";
/*    */   
/*    */   public GuiHopper(InventoryPlayer p_i1092_1_, IInventory p_i1092_2_) {
/* 20 */     super((Container)new ContainerHopper(p_i1092_1_, p_i1092_2_, (EntityPlayer)(Minecraft.getMinecraft()).thePlayer));
/* 21 */     this.field_147084_v = (IInventory)p_i1092_1_;
/* 22 */     this.field_147083_w = p_i1092_2_;
/* 23 */     this.allowUserInput = false;
/* 24 */     this.ySize = 133;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 32 */     fontRendererObj.drawString(this.field_147083_w.getDisplayName().getUnformattedText(), 8.0D, 6.0D, 4210752);
/* 33 */     fontRendererObj.drawString(this.field_147084_v.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 41 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 42 */     this.mc.getTextureManager().bindTexture(field_147085_u);
/* 43 */     int var4 = (width - this.xSize) / 2;
/* 44 */     int var5 = (height - this.ySize) / 2;
/* 45 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */