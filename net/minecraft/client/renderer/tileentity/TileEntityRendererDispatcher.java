/*     */ package net.minecraft.client.renderer.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBanner;
/*     */ import net.minecraft.tileentity.TileEntityBeacon;
/*     */ import net.minecraft.tileentity.TileEntityChest;
/*     */ import net.minecraft.tileentity.TileEntityEnchantmentTable;
/*     */ import net.minecraft.tileentity.TileEntityEndPortal;
/*     */ import net.minecraft.tileentity.TileEntityEnderChest;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.tileentity.TileEntityPiston;
/*     */ import net.minecraft.tileentity.TileEntitySign;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class TileEntityRendererDispatcher
/*     */ {
/*  30 */   private Map mapSpecialRenderers = Maps.newHashMap();
/*  31 */   public static TileEntityRendererDispatcher instance = new TileEntityRendererDispatcher();
/*     */   
/*     */   private FontRenderer field_147557_n;
/*     */   
/*     */   public static double staticPlayerX;
/*     */   
/*     */   public static double staticPlayerY;
/*     */   
/*     */   public static double staticPlayerZ;
/*     */   
/*     */   public TextureManager renderEngine;
/*     */   
/*     */   public World worldObj;
/*     */   
/*     */   public Entity field_147551_g;
/*     */   public float field_147562_h;
/*     */   public float field_147563_i;
/*     */   public double field_147560_j;
/*     */   public double field_147561_k;
/*     */   public double field_147558_l;
/*     */   private static final String __OBFID = "CL_00000963";
/*     */   
/*     */   private TileEntityRendererDispatcher() {
/*  54 */     this.mapSpecialRenderers.put(TileEntitySign.class, new TileEntitySignRenderer());
/*  55 */     this.mapSpecialRenderers.put(TileEntityMobSpawner.class, new TileEntityMobSpawnerRenderer());
/*  56 */     this.mapSpecialRenderers.put(TileEntityPiston.class, new TileEntityPistonRenderer());
/*  57 */     this.mapSpecialRenderers.put(TileEntityChest.class, new TileEntityChestRenderer());
/*  58 */     this.mapSpecialRenderers.put(TileEntityEnderChest.class, new TileEntityEnderChestRenderer());
/*  59 */     this.mapSpecialRenderers.put(TileEntityEnchantmentTable.class, new TileEntityEnchantmentTableRenderer());
/*  60 */     this.mapSpecialRenderers.put(TileEntityEndPortal.class, new TileEntityEndPortalRenderer());
/*  61 */     this.mapSpecialRenderers.put(TileEntityBeacon.class, new TileEntityBeaconRenderer());
/*  62 */     this.mapSpecialRenderers.put(TileEntitySkull.class, new TileEntitySkullRenderer());
/*  63 */     this.mapSpecialRenderers.put(TileEntityBanner.class, new TileEntityBannerRenderer());
/*  64 */     Iterator<TileEntitySpecialRenderer> var1 = this.mapSpecialRenderers.values().iterator();
/*     */     
/*  66 */     while (var1.hasNext()) {
/*     */       
/*  68 */       TileEntitySpecialRenderer var2 = var1.next();
/*  69 */       var2.setRendererDispatcher(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntitySpecialRenderer getSpecialRendererByClass(Class<TileEntity> p_147546_1_) {
/*  75 */     TileEntitySpecialRenderer var2 = (TileEntitySpecialRenderer)this.mapSpecialRenderers.get(p_147546_1_);
/*     */     
/*  77 */     if (var2 == null && p_147546_1_ != TileEntity.class) {
/*     */       
/*  79 */       var2 = getSpecialRendererByClass(p_147546_1_.getSuperclass());
/*  80 */       this.mapSpecialRenderers.put(p_147546_1_, var2);
/*     */     } 
/*     */     
/*  83 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSpecialRenderer(TileEntity p_147545_1_) {
/*  91 */     return (getSpecialRenderer(p_147545_1_) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntitySpecialRenderer getSpecialRenderer(TileEntity p_147547_1_) {
/*  96 */     return (p_147547_1_ == null) ? null : getSpecialRendererByClass(p_147547_1_.getClass());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178470_a(World worldIn, TextureManager p_178470_2_, FontRenderer p_178470_3_, Entity p_178470_4_, float p_178470_5_) {
/* 101 */     if (this.worldObj != worldIn)
/*     */     {
/* 103 */       func_147543_a(worldIn);
/*     */     }
/*     */     
/* 106 */     this.renderEngine = p_178470_2_;
/* 107 */     this.field_147551_g = p_178470_4_;
/* 108 */     this.field_147557_n = p_178470_3_;
/* 109 */     this.field_147562_h = p_178470_4_.prevRotationYaw + (p_178470_4_.rotationYaw - p_178470_4_.prevRotationYaw) * p_178470_5_;
/* 110 */     this.field_147563_i = p_178470_4_.prevRotationPitch + (p_178470_4_.rotationPitch - p_178470_4_.prevRotationPitch) * p_178470_5_;
/* 111 */     this.field_147560_j = p_178470_4_.lastTickPosX + (p_178470_4_.posX - p_178470_4_.lastTickPosX) * p_178470_5_;
/* 112 */     this.field_147561_k = p_178470_4_.lastTickPosY + (p_178470_4_.posY - p_178470_4_.lastTickPosY) * p_178470_5_;
/* 113 */     this.field_147558_l = p_178470_4_.lastTickPosZ + (p_178470_4_.posZ - p_178470_4_.lastTickPosZ) * p_178470_5_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180546_a(TileEntity p_180546_1_, float p_180546_2_, int p_180546_3_) {
/* 118 */     if (p_180546_1_.getDistanceSq(this.field_147560_j, this.field_147561_k, this.field_147558_l) < p_180546_1_.getMaxRenderDistanceSquared()) {
/*     */       
/* 120 */       int var4 = this.worldObj.getCombinedLight(p_180546_1_.getPos(), 0);
/* 121 */       int var5 = var4 % 65536;
/* 122 */       int var6 = var4 / 65536;
/* 123 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var5 / 1.0F, var6 / 1.0F);
/* 124 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 125 */       BlockPos var7 = p_180546_1_.getPos();
/* 126 */       func_178469_a(p_180546_1_, var7.getX() - staticPlayerX, var7.getY() - staticPlayerY, var7.getZ() - staticPlayerZ, p_180546_2_, p_180546_3_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTileEntityAt(TileEntity p_147549_1_, double p_147549_2_, double p_147549_4_, double p_147549_6_, float p_147549_8_) {
/* 135 */     func_178469_a(p_147549_1_, p_147549_2_, p_147549_4_, p_147549_6_, p_147549_8_, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178469_a(TileEntity p_178469_1_, double p_178469_2_, double p_178469_4_, double p_178469_6_, float p_178469_8_, int p_178469_9_) {
/* 140 */     TileEntitySpecialRenderer var10 = getSpecialRenderer(p_178469_1_);
/*     */     
/* 142 */     if (var10 != null) {
/*     */       
/*     */       try {
/*     */         
/* 146 */         var10.renderTileEntityAt(p_178469_1_, p_178469_2_, p_178469_4_, p_178469_6_, p_178469_8_, p_178469_9_);
/*     */       }
/* 148 */       catch (Throwable var14) {
/*     */         
/* 150 */         CrashReport var12 = CrashReport.makeCrashReport(var14, "Rendering Block Entity");
/* 151 */         CrashReportCategory var13 = var12.makeCategory("Block Entity Details");
/* 152 */         p_178469_1_.addInfoToCrashReport(var13);
/* 153 */         throw new ReportedException(var12);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_147543_a(World worldIn) {
/* 160 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public FontRenderer getFontRenderer() {
/* 165 */     return this.field_147557_n;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\TileEntityRendererDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */