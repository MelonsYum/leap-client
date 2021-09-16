/*    */ package net.minecraft.client.gui.inventory;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import leap.Client;
/*    */ import leap.util.JColor;
/*    */ import leap.util.Shape;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class GuiChest
/*    */   extends GuiContainer
/*    */ {
/* 19 */   private static final ResourceLocation field_147017_u = new ResourceLocation("textures/gui/container/generic_54.png");
/*    */   
/*    */   private IInventory upperChestInventory;
/*    */   
/*    */   private IInventory lowerChestInventory;
/*    */   
/*    */   private int inventoryRows;
/*    */   
/*    */   private static final String __OBFID = "CL_00000749";
/*    */ 
/*    */   
/*    */   public GuiChest(IInventory p_i46315_1_, IInventory p_i46315_2_) {
/* 31 */     super((Container)new ContainerChest(p_i46315_1_, p_i46315_2_, (EntityPlayer)(Minecraft.getMinecraft()).thePlayer));
/* 32 */     this.upperChestInventory = p_i46315_1_;
/* 33 */     this.lowerChestInventory = p_i46315_2_;
/* 34 */     this.allowUserInput = false;
/* 35 */     short var3 = 222;
/* 36 */     int var4 = var3 - 108;
/* 37 */     this.inventoryRows = p_i46315_2_.getSizeInventory() / 9;
/* 38 */     this.ySize = var4 + this.inventoryRows * 18;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 47 */     Client.customFontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8.0D, 6.0D, new JColor(Color.white.getRGB()));
/* 48 */     Client.customFontRenderer.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), new JColor(Color.white.getRGB()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 57 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 58 */     this.mc.getTextureManager().bindTexture(field_147017_u);
/* 59 */     int var4 = (width - this.xSize) / 2;
/* 60 */     int var5 = (height - this.ySize) / 2;
/*    */     
/* 62 */     Shape.color((new Color(0, 0, 0, 120)).getRGB());
/* 63 */     Shape.drawRoundedRect(var4, var5, this.xSize, (this.inventoryRows * 18 + 120), 4.0F);
/*    */ 
/*    */     
/* 66 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
/*    */ 
/*    */     
/* 69 */     drawTexturedModalRect(var4, var5 + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */