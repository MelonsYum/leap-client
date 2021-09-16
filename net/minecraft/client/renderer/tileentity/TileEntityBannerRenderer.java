/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBanner;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.LayeredColorMaskTexture;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBanner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class TileEntityBannerRenderer extends TileEntitySpecialRenderer {
/*  22 */   private static final Map field_178466_c = Maps.newHashMap();
/*  23 */   private static final ResourceLocation field_178464_d = new ResourceLocation("textures/entity/banner_base.png");
/*  24 */   private ModelBanner field_178465_e = new ModelBanner();
/*     */   
/*     */   private static final String __OBFID = "CL_00002473";
/*     */   
/*     */   public void func_180545_a(TileEntityBanner p_180545_1_, double p_180545_2_, double p_180545_4_, double p_180545_6_, float p_180545_8_, int p_180545_9_) {
/*  29 */     boolean var10 = (p_180545_1_.getWorld() != null);
/*  30 */     boolean var11 = !(var10 && p_180545_1_.getBlockType() != Blocks.standing_banner);
/*  31 */     int var12 = var10 ? p_180545_1_.getBlockMetadata() : 0;
/*  32 */     long var13 = var10 ? p_180545_1_.getWorld().getTotalWorldTime() : 0L;
/*  33 */     GlStateManager.pushMatrix();
/*  34 */     float var15 = 0.6666667F;
/*     */ 
/*     */     
/*  37 */     if (var11) {
/*     */       
/*  39 */       GlStateManager.translate((float)p_180545_2_ + 0.5F, (float)p_180545_4_ + 0.75F * var15, (float)p_180545_6_ + 0.5F);
/*  40 */       float var16 = (var12 * 360) / 16.0F;
/*  41 */       GlStateManager.rotate(-var16, 0.0F, 1.0F, 0.0F);
/*  42 */       this.field_178465_e.bannerStand.showModel = true;
/*     */     }
/*     */     else {
/*     */       
/*  46 */       float f = 0.0F;
/*     */       
/*  48 */       if (var12 == 2)
/*     */       {
/*  50 */         f = 180.0F;
/*     */       }
/*     */       
/*  53 */       if (var12 == 4)
/*     */       {
/*  55 */         f = 90.0F;
/*     */       }
/*     */       
/*  58 */       if (var12 == 5)
/*     */       {
/*  60 */         f = -90.0F;
/*     */       }
/*     */       
/*  63 */       GlStateManager.translate((float)p_180545_2_ + 0.5F, (float)p_180545_4_ - 0.25F * var15, (float)p_180545_6_ + 0.5F);
/*  64 */       GlStateManager.rotate(-f, 0.0F, 1.0F, 0.0F);
/*  65 */       GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
/*  66 */       this.field_178465_e.bannerStand.showModel = false;
/*     */     } 
/*     */     
/*  69 */     BlockPos var19 = p_180545_1_.getPos();
/*  70 */     float var17 = (var19.getX() * 7 + var19.getY() * 9 + var19.getZ() * 13) + (float)var13 + p_180545_8_;
/*  71 */     this.field_178465_e.bannerSlate.rotateAngleX = (-0.0125F + 0.01F * MathHelper.cos(var17 * 3.1415927F * 0.02F)) * 3.1415927F;
/*  72 */     GlStateManager.enableRescaleNormal();
/*  73 */     ResourceLocation var18 = func_178463_a(p_180545_1_);
/*     */     
/*  75 */     if (var18 != null) {
/*     */       
/*  77 */       bindTexture(var18);
/*  78 */       GlStateManager.pushMatrix();
/*  79 */       GlStateManager.scale(var15, -var15, -var15);
/*  80 */       this.field_178465_e.func_178687_a();
/*  81 */       GlStateManager.popMatrix();
/*     */     } 
/*     */     
/*  84 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  85 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private ResourceLocation func_178463_a(TileEntityBanner p_178463_1_) {
/*  90 */     String var2 = p_178463_1_.func_175116_e();
/*     */     
/*  92 */     if (var2.isEmpty())
/*     */     {
/*  94 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  98 */     TimedBannerTexture var3 = (TimedBannerTexture)field_178466_c.get(var2);
/*     */     
/* 100 */     if (var3 == null) {
/*     */       
/* 102 */       if (field_178466_c.size() >= 256) {
/*     */         
/* 104 */         long var4 = System.currentTimeMillis();
/* 105 */         Iterator<String> var6 = field_178466_c.keySet().iterator();
/*     */         
/* 107 */         while (var6.hasNext()) {
/*     */           
/* 109 */           String var7 = var6.next();
/* 110 */           TimedBannerTexture var8 = (TimedBannerTexture)field_178466_c.get(var7);
/*     */           
/* 112 */           if (var4 - var8.field_178472_a > 60000L) {
/*     */             
/* 114 */             Minecraft.getMinecraft().getTextureManager().deleteTexture(var8.field_178471_b);
/* 115 */             var6.remove();
/*     */           } 
/*     */         } 
/*     */         
/* 119 */         if (field_178466_c.size() >= 256)
/*     */         {
/* 121 */           return null;
/*     */         }
/*     */       } 
/*     */       
/* 125 */       List var9 = p_178463_1_.func_175114_c();
/* 126 */       List var5 = p_178463_1_.func_175110_d();
/* 127 */       ArrayList<String> var10 = Lists.newArrayList();
/* 128 */       Iterator<TileEntityBanner.EnumBannerPattern> var11 = var9.iterator();
/*     */       
/* 130 */       while (var11.hasNext()) {
/*     */         
/* 132 */         TileEntityBanner.EnumBannerPattern var12 = var11.next();
/* 133 */         var10.add("textures/entity/banner/" + var12.func_177271_a() + ".png");
/*     */       } 
/*     */       
/* 136 */       var3 = new TimedBannerTexture(null);
/* 137 */       var3.field_178471_b = new ResourceLocation(var2);
/* 138 */       Minecraft.getMinecraft().getTextureManager().loadTexture(var3.field_178471_b, (ITextureObject)new LayeredColorMaskTexture(field_178464_d, var10, var5));
/* 139 */       field_178466_c.put(var2, var3);
/*     */     } 
/*     */     
/* 142 */     var3.field_178472_a = System.currentTimeMillis();
/* 143 */     return var3.field_178471_b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
/* 149 */     func_180545_a((TileEntityBanner)p_180535_1_, p_180535_2_, p_180535_4_, p_180535_6_, p_180535_8_, p_180535_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   static class TimedBannerTexture
/*     */   {
/*     */     public long field_178472_a;
/*     */     public ResourceLocation field_178471_b;
/*     */     private static final String __OBFID = "CL_00002471";
/*     */     
/*     */     private TimedBannerTexture() {}
/*     */     
/*     */     TimedBannerTexture(Object p_i46209_1_) {
/* 162 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityBannerRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */