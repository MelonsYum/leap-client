/*    */ package net.minecraft.client.gui.inventory;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityHorse;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerHorseInventory;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiScreenHorseInventory extends GuiContainer {
/* 12 */   private static final ResourceLocation horseGuiTextures = new ResourceLocation("textures/gui/container/horse.png");
/*    */   
/*    */   private IInventory field_147030_v;
/*    */   private IInventory field_147029_w;
/*    */   private EntityHorse field_147034_x;
/*    */   private float field_147033_y;
/*    */   private float field_147032_z;
/*    */   private static final String __OBFID = "CL_00000760";
/*    */   
/*    */   public GuiScreenHorseInventory(IInventory p_i1093_1_, IInventory p_i1093_2_, EntityHorse p_i1093_3_) {
/* 22 */     super((Container)new ContainerHorseInventory(p_i1093_1_, p_i1093_2_, p_i1093_3_, (EntityPlayer)(Minecraft.getMinecraft()).thePlayer));
/* 23 */     this.field_147030_v = p_i1093_1_;
/* 24 */     this.field_147029_w = p_i1093_2_;
/* 25 */     this.field_147034_x = p_i1093_3_;
/* 26 */     this.allowUserInput = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 34 */     fontRendererObj.drawString(this.field_147029_w.getDisplayName().getUnformattedText(), 8.0D, 6.0D, 4210752);
/* 35 */     fontRendererObj.drawString(this.field_147030_v.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 43 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 44 */     this.mc.getTextureManager().bindTexture(horseGuiTextures);
/* 45 */     int var4 = (width - this.xSize) / 2;
/* 46 */     int var5 = (height - this.ySize) / 2;
/* 47 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*    */     
/* 49 */     if (this.field_147034_x.isChested())
/*    */     {
/* 51 */       drawTexturedModalRect(var4 + 79, var5 + 17, 0, this.ySize, 90, 54);
/*    */     }
/*    */     
/* 54 */     if (this.field_147034_x.canWearArmor())
/*    */     {
/* 56 */       drawTexturedModalRect(var4 + 7, var5 + 35, 0, this.ySize + 54, 18, 18);
/*    */     }
/*    */     
/* 59 */     GuiInventory.drawEntityOnScreen(var4 + 51, var5 + 60, 17, (var4 + 51) - this.field_147033_y, (var5 + 75 - 50) - this.field_147032_z, (EntityLivingBase)this.field_147034_x);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 67 */     this.field_147033_y = mouseX;
/* 68 */     this.field_147032_z = mouseY;
/* 69 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiScreenHorseInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */