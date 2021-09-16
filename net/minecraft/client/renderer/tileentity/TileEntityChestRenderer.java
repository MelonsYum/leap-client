/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.client.model.ModelChest;
/*     */ import net.minecraft.client.model.ModelLargeChest;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class TileEntityChestRenderer
/*     */   extends TileEntitySpecialRenderer {
/*  15 */   private static final ResourceLocation textureTrappedDouble = new ResourceLocation("textures/entity/chest/trapped_double.png");
/*  16 */   private static final ResourceLocation textureChristmasDouble = new ResourceLocation("textures/entity/chest/christmas_double.png");
/*  17 */   private static final ResourceLocation textureNormalDouble = new ResourceLocation("textures/entity/chest/normal_double.png");
/*  18 */   private static final ResourceLocation textureTrapped = new ResourceLocation("textures/entity/chest/trapped.png");
/*  19 */   private static final ResourceLocation textureChristmas = new ResourceLocation("textures/entity/chest/christmas.png");
/*  20 */   private static final ResourceLocation textureNormal = new ResourceLocation("textures/entity/chest/normal.png");
/*  21 */   private ModelChest simpleChest = new ModelChest();
/*  22 */   private ModelChest largeChest = (ModelChest)new ModelLargeChest();
/*     */   
/*     */   private boolean isChristams;
/*     */   private static final String __OBFID = "CL_00000965";
/*     */   
/*     */   public TileEntityChestRenderer() {
/*  28 */     Calendar var1 = Calendar.getInstance();
/*     */     
/*  30 */     if (var1.get(2) + 1 == 12 && var1.get(5) >= 24 && var1.get(5) <= 26)
/*     */     {
/*  32 */       this.isChristams = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180538_a(TileEntityChest p_180538_1_, double p_180538_2_, double p_180538_4_, double p_180538_6_, float p_180538_8_, int p_180538_9_) {
/*     */     int var10;
/*  40 */     if (!p_180538_1_.hasWorldObj()) {
/*     */       
/*  42 */       var10 = 0;
/*     */     }
/*     */     else {
/*     */       
/*  46 */       Block var11 = p_180538_1_.getBlockType();
/*  47 */       var10 = p_180538_1_.getBlockMetadata();
/*     */       
/*  49 */       if (var11 instanceof BlockChest && var10 == 0) {
/*     */         
/*  51 */         ((BlockChest)var11).checkForSurroundingChests(p_180538_1_.getWorld(), p_180538_1_.getPos(), p_180538_1_.getWorld().getBlockState(p_180538_1_.getPos()));
/*  52 */         var10 = p_180538_1_.getBlockMetadata();
/*     */       } 
/*     */       
/*  55 */       p_180538_1_.checkForAdjacentChests();
/*     */     } 
/*     */     
/*  58 */     if (p_180538_1_.adjacentChestZNeg == null && p_180538_1_.adjacentChestXNeg == null) {
/*     */       ModelChest var15;
/*     */ 
/*     */       
/*  62 */       if (p_180538_1_.adjacentChestXPos == null && p_180538_1_.adjacentChestZPos == null) {
/*     */         
/*  64 */         var15 = this.simpleChest;
/*     */         
/*  66 */         if (p_180538_9_ >= 0)
/*     */         {
/*  68 */           bindTexture(DESTROY_STAGES[p_180538_9_]);
/*  69 */           GlStateManager.matrixMode(5890);
/*  70 */           GlStateManager.pushMatrix();
/*  71 */           GlStateManager.scale(4.0F, 4.0F, 1.0F);
/*  72 */           GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
/*  73 */           GlStateManager.matrixMode(5888);
/*     */         }
/*  75 */         else if (p_180538_1_.getChestType() == 1)
/*     */         {
/*  77 */           bindTexture(textureTrapped);
/*     */         }
/*  79 */         else if (this.isChristams)
/*     */         {
/*  81 */           bindTexture(textureChristmas);
/*     */         }
/*     */         else
/*     */         {
/*  85 */           bindTexture(textureNormal);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  90 */         var15 = this.largeChest;
/*     */         
/*  92 */         if (p_180538_9_ >= 0) {
/*     */           
/*  94 */           bindTexture(DESTROY_STAGES[p_180538_9_]);
/*  95 */           GlStateManager.matrixMode(5890);
/*  96 */           GlStateManager.pushMatrix();
/*  97 */           GlStateManager.scale(8.0F, 4.0F, 1.0F);
/*  98 */           GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
/*  99 */           GlStateManager.matrixMode(5888);
/*     */         }
/* 101 */         else if (p_180538_1_.getChestType() == 1) {
/*     */           
/* 103 */           bindTexture(textureTrappedDouble);
/*     */         }
/* 105 */         else if (this.isChristams) {
/*     */           
/* 107 */           bindTexture(textureChristmasDouble);
/*     */         }
/*     */         else {
/*     */           
/* 111 */           bindTexture(textureNormalDouble);
/*     */         } 
/*     */       } 
/*     */       
/* 115 */       GlStateManager.pushMatrix();
/* 116 */       GlStateManager.enableRescaleNormal();
/*     */       
/* 118 */       if (p_180538_9_ < 0)
/*     */       {
/* 120 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       }
/*     */       
/* 123 */       GlStateManager.translate((float)p_180538_2_, (float)p_180538_4_ + 1.0F, (float)p_180538_6_ + 1.0F);
/* 124 */       GlStateManager.scale(1.0F, -1.0F, -1.0F);
/* 125 */       GlStateManager.translate(0.5F, 0.5F, 0.5F);
/* 126 */       short var12 = 0;
/*     */       
/* 128 */       if (var10 == 2)
/*     */       {
/* 130 */         var12 = 180;
/*     */       }
/*     */       
/* 133 */       if (var10 == 3)
/*     */       {
/* 135 */         var12 = 0;
/*     */       }
/*     */       
/* 138 */       if (var10 == 4)
/*     */       {
/* 140 */         var12 = 90;
/*     */       }
/*     */       
/* 143 */       if (var10 == 5)
/*     */       {
/* 145 */         var12 = -90;
/*     */       }
/*     */       
/* 148 */       if (var10 == 2 && p_180538_1_.adjacentChestXPos != null)
/*     */       {
/* 150 */         GlStateManager.translate(1.0F, 0.0F, 0.0F);
/*     */       }
/*     */       
/* 153 */       if (var10 == 5 && p_180538_1_.adjacentChestZPos != null)
/*     */       {
/* 155 */         GlStateManager.translate(0.0F, 0.0F, -1.0F);
/*     */       }
/*     */       
/* 158 */       GlStateManager.rotate(var12, 0.0F, 1.0F, 0.0F);
/* 159 */       GlStateManager.translate(-0.5F, -0.5F, -0.5F);
/* 160 */       float var13 = p_180538_1_.prevLidAngle + (p_180538_1_.lidAngle - p_180538_1_.prevLidAngle) * p_180538_8_;
/*     */ 
/*     */       
/* 163 */       if (p_180538_1_.adjacentChestZNeg != null) {
/*     */         
/* 165 */         float var14 = p_180538_1_.adjacentChestZNeg.prevLidAngle + (p_180538_1_.adjacentChestZNeg.lidAngle - p_180538_1_.adjacentChestZNeg.prevLidAngle) * p_180538_8_;
/*     */         
/* 167 */         if (var14 > var13)
/*     */         {
/* 169 */           var13 = var14;
/*     */         }
/*     */       } 
/*     */       
/* 173 */       if (p_180538_1_.adjacentChestXNeg != null) {
/*     */         
/* 175 */         float var14 = p_180538_1_.adjacentChestXNeg.prevLidAngle + (p_180538_1_.adjacentChestXNeg.lidAngle - p_180538_1_.adjacentChestXNeg.prevLidAngle) * p_180538_8_;
/*     */         
/* 177 */         if (var14 > var13)
/*     */         {
/* 179 */           var13 = var14;
/*     */         }
/*     */       } 
/*     */       
/* 183 */       var13 = 1.0F - var13;
/* 184 */       var13 = 1.0F - var13 * var13 * var13;
/* 185 */       var15.chestLid.rotateAngleX = -(var13 * 3.1415927F / 2.0F);
/* 186 */       var15.renderAll();
/* 187 */       GlStateManager.disableRescaleNormal();
/* 188 */       GlStateManager.popMatrix();
/* 189 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 191 */       if (p_180538_9_ >= 0) {
/*     */         
/* 193 */         GlStateManager.matrixMode(5890);
/* 194 */         GlStateManager.popMatrix();
/* 195 */         GlStateManager.matrixMode(5888);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 202 */     func_180538_a((TileEntityChest)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */