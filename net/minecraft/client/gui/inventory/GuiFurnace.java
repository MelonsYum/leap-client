/*    */ package net.minecraft.client.gui.inventory;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerFurnace;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.tileentity.TileEntityFurnace;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiFurnace extends GuiContainer {
/* 12 */   private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("textures/gui/container/furnace.png");
/*    */   
/*    */   private final InventoryPlayer field_175383_v;
/*    */   private IInventory tileFurnace;
/*    */   private static final String __OBFID = "CL_00000758";
/*    */   
/*    */   public GuiFurnace(InventoryPlayer p_i45501_1_, IInventory p_i45501_2_) {
/* 19 */     super((Container)new ContainerFurnace(p_i45501_1_, p_i45501_2_));
/* 20 */     this.field_175383_v = p_i45501_1_;
/* 21 */     this.tileFurnace = p_i45501_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 29 */     String var3 = this.tileFurnace.getDisplayName().getUnformattedText();
/* 30 */     fontRendererObj.drawString(var3, (this.xSize / 2 - fontRendererObj.getStringWidth(var3) / 2), 6.0D, 4210752);
/* 31 */     fontRendererObj.drawString(this.field_175383_v.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 39 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 40 */     this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
/* 41 */     int var4 = (width - this.xSize) / 2;
/* 42 */     int var5 = (height - this.ySize) / 2;
/* 43 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */ 
/*    */     
/* 46 */     if (TileEntityFurnace.func_174903_a(this.tileFurnace)) {
/*    */       
/* 48 */       int i = func_175382_i(13);
/* 49 */       drawTexturedModalRect(var4 + 56, var5 + 36 + 12 - i, 176, 12 - i, 14, i + 1);
/*    */     } 
/*    */     
/* 52 */     int var6 = func_175381_h(24);
/* 53 */     drawTexturedModalRect(var4 + 79, var5 + 34, 176, 14, var6 + 1, 16);
/*    */   }
/*    */ 
/*    */   
/*    */   private int func_175381_h(int p_175381_1_) {
/* 58 */     int var2 = this.tileFurnace.getField(2);
/* 59 */     int var3 = this.tileFurnace.getField(3);
/* 60 */     return (var3 != 0 && var2 != 0) ? (var2 * p_175381_1_ / var3) : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   private int func_175382_i(int p_175382_1_) {
/* 65 */     int var2 = this.tileFurnace.getField(1);
/*    */     
/* 67 */     if (var2 == 0)
/*    */     {
/* 69 */       var2 = 200;
/*    */     }
/*    */     
/* 72 */     return this.tileFurnace.getField(0) * p_175382_1_ / var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */