/*    */ package net.minecraft.client.gui.inventory;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.ContainerBrewingStand;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class GuiBrewingStand extends GuiContainer {
/* 11 */   private static final ResourceLocation brewingStandGuiTextures = new ResourceLocation("textures/gui/container/brewing_stand.png");
/*    */   
/*    */   private final InventoryPlayer field_175384_v;
/*    */   private IInventory tileBrewingStand;
/*    */   private static final String __OBFID = "CL_00000746";
/*    */   
/*    */   public GuiBrewingStand(InventoryPlayer p_i45506_1_, IInventory p_i45506_2_) {
/* 18 */     super((Container)new ContainerBrewingStand(p_i45506_1_, p_i45506_2_));
/* 19 */     this.field_175384_v = p_i45506_1_;
/* 20 */     this.tileBrewingStand = p_i45506_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 28 */     String var3 = this.tileBrewingStand.getDisplayName().getUnformattedText();
/* 29 */     fontRendererObj.drawString(var3, (this.xSize / 2 - fontRendererObj.getStringWidth(var3) / 2), 6.0D, 4210752);
/* 30 */     fontRendererObj.drawString(this.field_175384_v.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 38 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 39 */     this.mc.getTextureManager().bindTexture(brewingStandGuiTextures);
/* 40 */     int var4 = (width - this.xSize) / 2;
/* 41 */     int var5 = (height - this.ySize) / 2;
/* 42 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 43 */     int var6 = this.tileBrewingStand.getField(0);
/*    */     
/* 45 */     if (var6 > 0) {
/*    */       
/* 47 */       int var7 = (int)(28.0F * (1.0F - var6 / 400.0F));
/*    */       
/* 49 */       if (var7 > 0)
/*    */       {
/* 51 */         drawTexturedModalRect(var4 + 97, var5 + 16, 176, 0, 9, var7);
/*    */       }
/*    */       
/* 54 */       int var8 = var6 / 2 % 7;
/*    */       
/* 56 */       switch (var8) {
/*    */         
/*    */         case 0:
/* 59 */           var7 = 29;
/*    */           break;
/*    */         
/*    */         case 1:
/* 63 */           var7 = 24;
/*    */           break;
/*    */         
/*    */         case 2:
/* 67 */           var7 = 20;
/*    */           break;
/*    */         
/*    */         case 3:
/* 71 */           var7 = 16;
/*    */           break;
/*    */         
/*    */         case 4:
/* 75 */           var7 = 11;
/*    */           break;
/*    */         
/*    */         case 5:
/* 79 */           var7 = 6;
/*    */           break;
/*    */         
/*    */         case 6:
/* 83 */           var7 = 0;
/*    */           break;
/*    */       } 
/* 86 */       if (var7 > 0)
/*    */       {
/* 88 */         drawTexturedModalRect(var4 + 65, var5 + 14 + 29 - var7, 185, 29 - var7, 12, var7);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */