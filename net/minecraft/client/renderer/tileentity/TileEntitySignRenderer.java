/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiUtilRenderComponents;
/*     */ import net.minecraft.client.model.ModelSign;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntitySign;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.CustomColors;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TileEntitySignRenderer
/*     */   extends TileEntitySpecialRenderer
/*     */ {
/*  21 */   private static final ResourceLocation field_147513_b = new ResourceLocation("textures/entity/sign.png");
/*     */ 
/*     */   
/*  24 */   private final ModelSign model = new ModelSign();
/*     */ 
/*     */   
/*     */   public void func_180541_a(TileEntitySign p_180541_1_, double p_180541_2_, double p_180541_4_, double p_180541_6_, float p_180541_8_, int p_180541_9_) {
/*  28 */     Block var10 = p_180541_1_.getBlockType();
/*  29 */     GlStateManager.pushMatrix();
/*  30 */     float var11 = 0.6666667F;
/*     */ 
/*     */     
/*  33 */     if (var10 == Blocks.standing_sign) {
/*     */       
/*  35 */       GlStateManager.translate((float)p_180541_2_ + 0.5F, (float)p_180541_4_ + 0.75F * var11, (float)p_180541_6_ + 0.5F);
/*  36 */       float var20 = (p_180541_1_.getBlockMetadata() * 360) / 16.0F;
/*  37 */       GlStateManager.rotate(-var20, 0.0F, 1.0F, 0.0F);
/*  38 */       this.model.signStick.showModel = true;
/*     */     }
/*     */     else {
/*     */       
/*  42 */       int var19 = p_180541_1_.getBlockMetadata();
/*  43 */       float f = 0.0F;
/*     */       
/*  45 */       if (var19 == 2)
/*     */       {
/*  47 */         f = 180.0F;
/*     */       }
/*     */       
/*  50 */       if (var19 == 4)
/*     */       {
/*  52 */         f = 90.0F;
/*     */       }
/*     */       
/*  55 */       if (var19 == 5)
/*     */       {
/*  57 */         f = -90.0F;
/*     */       }
/*     */       
/*  60 */       GlStateManager.translate((float)p_180541_2_ + 0.5F, (float)p_180541_4_ + 0.75F * var11, (float)p_180541_6_ + 0.5F);
/*  61 */       GlStateManager.rotate(-f, 0.0F, 1.0F, 0.0F);
/*  62 */       GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
/*  63 */       this.model.signStick.showModel = false;
/*     */     } 
/*     */     
/*  66 */     if (p_180541_9_ >= 0) {
/*     */       
/*  68 */       bindTexture(DESTROY_STAGES[p_180541_9_]);
/*  69 */       GlStateManager.matrixMode(5890);
/*  70 */       GlStateManager.pushMatrix();
/*  71 */       GlStateManager.scale(4.0F, 2.0F, 1.0F);
/*  72 */       GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
/*  73 */       GlStateManager.matrixMode(5888);
/*     */     }
/*     */     else {
/*     */       
/*  77 */       bindTexture(field_147513_b);
/*     */     } 
/*     */     
/*  80 */     GlStateManager.enableRescaleNormal();
/*  81 */     GlStateManager.pushMatrix();
/*  82 */     GlStateManager.scale(var11, -var11, -var11);
/*  83 */     this.model.renderSign();
/*  84 */     GlStateManager.popMatrix();
/*  85 */     FontRenderer var201 = getFontRenderer();
/*  86 */     float var13 = 0.015625F * var11;
/*  87 */     GlStateManager.translate(0.0F, 0.5F * var11, 0.07F * var11);
/*  88 */     GlStateManager.scale(var13, -var13, var13);
/*  89 */     GL11.glNormal3f(0.0F, 0.0F, -1.0F * var13);
/*  90 */     GlStateManager.depthMask(false);
/*  91 */     int var14 = 0;
/*     */     
/*  93 */     if (Config.isCustomColors())
/*     */     {
/*  95 */       var14 = CustomColors.getSignTextColor(var14);
/*     */     }
/*     */     
/*  98 */     if (p_180541_9_ < 0)
/*     */     {
/* 100 */       for (int var15 = 0; var15 < p_180541_1_.signText.length; var15++) {
/*     */         
/* 102 */         if (p_180541_1_.signText[var15] != null) {
/*     */           
/* 104 */           IChatComponent var16 = p_180541_1_.signText[var15];
/* 105 */           List<IChatComponent> var17 = GuiUtilRenderComponents.func_178908_a(var16, 90, var201, false, true);
/* 106 */           String var18 = (var17 != null && var17.size() > 0) ? ((IChatComponent)var17.get(0)).getFormattedText() : "";
/*     */           
/* 108 */           if (var15 == p_180541_1_.lineBeingEdited) {
/*     */             
/* 110 */             var18 = "> " + var18 + " <";
/* 111 */             var201.drawString(var18, (-var201.getStringWidth(var18) / 2), (var15 * 10 - p_180541_1_.signText.length * 5), var14);
/*     */           }
/*     */           else {
/*     */             
/* 115 */             var201.drawString(var18, (-var201.getStringWidth(var18) / 2), (var15 * 10 - p_180541_1_.signText.length * 5), var14);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 121 */     GlStateManager.depthMask(true);
/* 122 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 123 */     GlStateManager.popMatrix();
/*     */     
/* 125 */     if (p_180541_9_ >= 0) {
/*     */       
/* 127 */       GlStateManager.matrixMode(5890);
/* 128 */       GlStateManager.popMatrix();
/* 129 */       GlStateManager.matrixMode(5888);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 135 */     func_180541_a((TileEntitySign)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntitySignRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */