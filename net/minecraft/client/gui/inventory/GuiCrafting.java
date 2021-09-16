/*    */ package net.minecraft.client.gui.inventory;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerWorkbench;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class GuiCrafting extends GuiContainer {
/* 13 */   private static final ResourceLocation craftingTableGuiTextures = new ResourceLocation("textures/gui/container/crafting_table.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000750";
/*    */   
/*    */   public GuiCrafting(InventoryPlayer p_i45504_1_, World worldIn) {
/* 18 */     this(p_i45504_1_, worldIn, BlockPos.ORIGIN);
/*    */   }
/*    */ 
/*    */   
/*    */   public GuiCrafting(InventoryPlayer p_i45505_1_, World worldIn, BlockPos p_i45505_3_) {
/* 23 */     super((Container)new ContainerWorkbench(p_i45505_1_, worldIn, p_i45505_3_));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 31 */     fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 28.0D, 6.0D, 4210752);
/* 32 */     fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8.0D, (this.ySize - 96 + 2), 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 40 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 41 */     this.mc.getTextureManager().bindTexture(craftingTableGuiTextures);
/* 42 */     int var4 = (width - this.xSize) / 2;
/* 43 */     int var5 = (height - this.ySize) / 2;
/* 44 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */