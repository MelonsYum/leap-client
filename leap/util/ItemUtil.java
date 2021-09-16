/*     */ package leap.util;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderEntityItem;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class ItemUtil
/*     */ {
/*  21 */   public static Random random = new Random();
/*  22 */   public static Minecraft mc = Minecraft.getMinecraft();
/*  23 */   public static RenderItem renderItem = mc.getRenderItem();
/*     */   public static long tick;
/*     */   public static double rotation;
/*     */   
/*     */   public static void doRender(Entity par1Entity, double x, double y, double z, float par8, float par9) {
/*  28 */     rotation = 0.5D;
/*  29 */     if (!mc.inGameHasFocus) {
/*  30 */       rotation = 0.0D;
/*     */     }
/*  32 */     EntityItem entityitem = (EntityItem)par1Entity;
/*  33 */     ItemStack itemstack = entityitem.getEntityItem();
/*  34 */     if (itemstack.getItem() != null) {
/*  35 */       random.setSeed(187L);
/*  36 */       (mc.getRenderManager()).renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/*  37 */       (mc.getRenderManager()).renderEngine.getTexture(TextureMap.locationBlocksTexture).func_174936_b(false, 
/*  38 */           false);
/*  39 */       GlStateManager.enableRescaleNormal();
/*  40 */       GlStateManager.alphaFunc(516, 0.1F);
/*  41 */       GlStateManager.enableBlend();
/*  42 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  43 */       GlStateManager.pushMatrix();
/*  44 */       IBakedModel ibakedmodel = renderItem.getItemModelMesher().getItemModel(itemstack);
/*  45 */       int i = func_177077_a(entityitem, x, y, z, par9, ibakedmodel);
/*  46 */       BlockPos blockpos = new BlockPos((Entity)entityitem);
/*  47 */       if (entityitem.rotationPitch > 360.0F) {
/*  48 */         entityitem.rotationPitch = 1.0F;
/*     */       }
/*  50 */       if (!Double.isNaN(entityitem.posX) && !Double.isNaN(entityitem.posY) && !Double.isNaN(entityitem.posZ) && entityitem.worldObj != null) {
/*  51 */         if (entityitem.onGround) {
/*  52 */           if (entityitem.rotationPitch != 0.0F && entityitem.rotationPitch != 90.0F && 
/*  53 */             entityitem.rotationPitch != 180.0F && entityitem.rotationPitch != 270.0F) {
/*  54 */             double d0 = formPositiv(entityitem.rotationPitch);
/*  55 */             double d1 = formPositiv(entityitem.rotationPitch - 90.0F);
/*  56 */             double d2 = formPositiv(entityitem.rotationPitch - 180.0F);
/*  57 */             double d3 = formPositiv(entityitem.rotationPitch - 270.0F);
/*  58 */             if (d0 <= d1 && d0 <= d2 && d0 <= d3) {
/*  59 */               if (entityitem.rotationPitch < 0.0F) {
/*  60 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch + rotation);
/*     */               } else {
/*  62 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch - rotation);
/*     */               } 
/*     */             }
/*  65 */             if (d1 < d0 && d1 <= d2 && d1 <= d3) {
/*  66 */               if (entityitem.rotationPitch - 90.0F < 0.0F) {
/*  67 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch + rotation);
/*     */               } else {
/*  69 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch - rotation);
/*     */               } 
/*     */             }
/*  72 */             if (d2 < d1 && d2 < d0 && d2 <= d3) {
/*  73 */               if (entityitem.rotationPitch - 180.0F < 0.0F) {
/*  74 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch + rotation);
/*     */               } else {
/*  76 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch - rotation);
/*     */               } 
/*     */             }
/*  79 */             if (d3 < d1 && d3 < d2 && d3 < d0) {
/*  80 */               if (entityitem.rotationPitch - 270.0F < 0.0F) {
/*  81 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch + rotation);
/*     */               } else {
/*  83 */                 entityitem.rotationPitch = (float)(entityitem.rotationPitch - rotation);
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } else {
/*  88 */           BlockPos blockpos1 = new BlockPos((Entity)entityitem);
/*  89 */           blockpos1.add(0, 1, 0);
/*  90 */           Material material = entityitem.worldObj.getBlockState(blockpos1).getBlock().getMaterial();
/*  91 */           Material material1 = entityitem.worldObj.getBlockState(blockpos).getBlock().getMaterial();
/*  92 */           boolean flag1 = entityitem.isInsideOfMaterial(Material.water);
/*  93 */           boolean flag2 = entityitem.isInWater();
/*  94 */           if ((flag1 | ((material == Material.water) ? 1 : 0) | ((material1 == Material.water) ? 1 : 0) | flag2) != 0) {
/*  95 */             entityitem.rotationPitch = (float)(entityitem.rotationPitch + rotation / 4.0D);
/*     */           } else {
/*  97 */             entityitem.rotationPitch = (float)(entityitem.rotationPitch + rotation * 2.0D);
/*     */           } 
/*     */         } 
/*     */       }
/* 101 */       GL11.glRotatef(entityitem.rotationYaw, 0.0F, 1.0F, 0.0F);
/* 102 */       GL11.glRotatef(entityitem.rotationPitch + 90.0F, 1.0F, 0.0F, 0.0F);
/* 103 */       for (int j = 0; j < i; j++) {
/* 104 */         if (ibakedmodel.isAmbientOcclusionEnabled()) {
/* 105 */           GlStateManager.pushMatrix();
/* 106 */           GlStateManager.scale(0.5F, 0.5F, 0.5F);
/* 107 */           RenderEntityItem.field_177080_a.func_180454_a(itemstack, ibakedmodel);
/* 108 */           GlStateManager.popMatrix();
/*     */         } else {
/* 110 */           GlStateManager.pushMatrix();
/* 111 */           if (j > 0 && shouldSpreadItems()) {
/* 112 */             GlStateManager.translate(0.0F, 0.0F, 0.046875F * j);
/*     */           }
/* 114 */           RenderEntityItem.field_177080_a.func_180454_a(itemstack, ibakedmodel);
/* 115 */           if (!shouldSpreadItems()) {
/* 116 */             GlStateManager.translate(0.0F, 0.0F, 0.046875F);
/*     */           }
/* 118 */           GlStateManager.popMatrix();
/*     */         } 
/*     */       } 
/* 121 */       GlStateManager.popMatrix();
/* 122 */       GlStateManager.disableRescaleNormal();
/* 123 */       GlStateManager.disableBlend();
/* 124 */       (mc.getRenderManager()).renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int func_177077_a(EntityItem items, double x, double y, double z, float p_177077_8_, IBakedModel p_177077_9_) {
/* 129 */     ItemStack itemstack = items.getEntityItem();
/* 130 */     Item item = itemstack.getItem();
/* 131 */     if (item == null) {
/* 132 */       return 0;
/*     */     }
/* 134 */     boolean flag = p_177077_9_.isAmbientOcclusionEnabled();
/* 135 */     int i = func_177078_a(itemstack);
/* 136 */     float f = 0.25F;
/* 137 */     float f1 = 0.0F;
/* 138 */     GlStateManager.translate((float)x, (float)y + f1 + 0.03F, (float)z);
/* 139 */     float f2 = 0.0F;
/* 140 */     if (flag || ((mc.getRenderManager()).options != null && (mc.getRenderManager()).options.fancyGraphics)) {
/* 141 */       GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
/*     */     }
/* 143 */     if (!flag) {
/* 144 */       f2 = -0.0F * (i - 1) * 0.5F;
/* 145 */       float f3 = -0.0F * (i - 1) * 0.5F;
/* 146 */       float f4 = -0.046875F * (i - 1) * 0.5F;
/* 147 */       GlStateManager.translate(f2, f3, f4);
/*     */     } 
/* 149 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 150 */     return i;
/*     */   }
/*     */   
/*     */   public static boolean shouldSpreadItems() {
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   public static double formPositiv(float rotationPitch) {
/* 158 */     return ((rotationPitch > 0.0F) ? rotationPitch : -rotationPitch);
/*     */   }
/*     */   
/*     */   public static int func_177078_a(ItemStack stack) {
/* 162 */     byte b0 = 1;
/* 163 */     if (stack.stackSize > 48) {
/* 164 */       b0 = 5;
/* 165 */     } else if (stack.stackSize > 32) {
/* 166 */       b0 = 4;
/* 167 */     } else if (stack.stackSize > 16) {
/* 168 */       b0 = 3;
/* 169 */     } else if (stack.stackSize > 1) {
/* 170 */       b0 = 2;
/*     */     } 
/* 172 */     return b0;
/*     */   }
/*     */   
/*     */   public static byte getMiniBlockCount(ItemStack stack, byte original) {
/* 176 */     return original;
/*     */   }
/*     */   
/*     */   public static byte getMiniItemCount(ItemStack stack, byte original) {
/* 180 */     return original;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\ItemUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */