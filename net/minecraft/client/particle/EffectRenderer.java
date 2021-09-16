/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ public class EffectRenderer {
/*  36 */   private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
/*     */   
/*     */   protected World worldObj;
/*     */   
/*  40 */   private static List[][] fxLayers = new List[4][];
/*  41 */   private List field_178933_d = Lists.newArrayList();
/*     */   
/*     */   private static TextureManager renderer;
/*     */   
/*  45 */   private Random rand = new Random();
/*  46 */   private static Map field_178932_g = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00000915";
/*     */   
/*     */   public EffectRenderer(World worldIn, TextureManager p_i1220_2_) {
/*  51 */     this.worldObj = worldIn;
/*  52 */     renderer = p_i1220_2_;
/*     */     
/*  54 */     for (int var3 = 0; var3 < 4; var3++) {
/*     */       
/*  56 */       fxLayers[var3] = new List[2];
/*     */       
/*  58 */       for (int var4 = 0; var4 < 2; var4++)
/*     */       {
/*  60 */         fxLayers[var3][var4] = Lists.newArrayList();
/*     */       }
/*     */     } 
/*     */     
/*  64 */     func_178930_c();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178930_c() {
/*  69 */     func_178929_a(EnumParticleTypes.EXPLOSION_NORMAL.func_179348_c(), new EntityExplodeFX.Factory());
/*  70 */     func_178929_a(EnumParticleTypes.WATER_BUBBLE.func_179348_c(), new EntityBubbleFX.Factory());
/*  71 */     func_178929_a(EnumParticleTypes.WATER_SPLASH.func_179348_c(), new EntitySplashFX.Factory());
/*  72 */     func_178929_a(EnumParticleTypes.WATER_WAKE.func_179348_c(), new EntityFishWakeFX.Factory());
/*  73 */     func_178929_a(EnumParticleTypes.WATER_DROP.func_179348_c(), new EntityRainFX.Factory());
/*  74 */     func_178929_a(EnumParticleTypes.SUSPENDED.func_179348_c(), new EntitySuspendFX.Factory());
/*  75 */     func_178929_a(EnumParticleTypes.SUSPENDED_DEPTH.func_179348_c(), new EntityAuraFX.Factory());
/*  76 */     func_178929_a(EnumParticleTypes.CRIT.func_179348_c(), new EntityCrit2FX.Factory());
/*  77 */     func_178929_a(EnumParticleTypes.CRIT_MAGIC.func_179348_c(), new EntityCrit2FX.MagicFactory());
/*  78 */     func_178929_a(EnumParticleTypes.SMOKE_NORMAL.func_179348_c(), new EntitySmokeFX.Factory());
/*  79 */     func_178929_a(EnumParticleTypes.SMOKE_LARGE.func_179348_c(), new EntityCritFX.Factory());
/*  80 */     func_178929_a(EnumParticleTypes.SPELL.func_179348_c(), new EntitySpellParticleFX.Factory());
/*  81 */     func_178929_a(EnumParticleTypes.SPELL_INSTANT.func_179348_c(), new EntitySpellParticleFX.InstantFactory());
/*  82 */     func_178929_a(EnumParticleTypes.SPELL_MOB.func_179348_c(), new EntitySpellParticleFX.MobFactory());
/*  83 */     func_178929_a(EnumParticleTypes.SPELL_MOB_AMBIENT.func_179348_c(), new EntitySpellParticleFX.AmbientMobFactory());
/*  84 */     func_178929_a(EnumParticleTypes.SPELL_WITCH.func_179348_c(), new EntitySpellParticleFX.WitchFactory());
/*  85 */     func_178929_a(EnumParticleTypes.DRIP_WATER.func_179348_c(), new EntityDropParticleFX.WaterFactory());
/*  86 */     func_178929_a(EnumParticleTypes.DRIP_LAVA.func_179348_c(), new EntityDropParticleFX.LavaFactory());
/*  87 */     func_178929_a(EnumParticleTypes.VILLAGER_ANGRY.func_179348_c(), new EntityHeartFX.AngryVillagerFactory());
/*  88 */     func_178929_a(EnumParticleTypes.VILLAGER_HAPPY.func_179348_c(), new EntityAuraFX.HappyVillagerFactory());
/*  89 */     func_178929_a(EnumParticleTypes.TOWN_AURA.func_179348_c(), new EntityAuraFX.Factory());
/*  90 */     func_178929_a(EnumParticleTypes.NOTE.func_179348_c(), new EntityNoteFX.Factory());
/*  91 */     func_178929_a(EnumParticleTypes.PORTAL.func_179348_c(), new EntityPortalFX.Factory());
/*  92 */     func_178929_a(EnumParticleTypes.ENCHANTMENT_TABLE.func_179348_c(), new EntityEnchantmentTableParticleFX.EnchantmentTable());
/*  93 */     func_178929_a(EnumParticleTypes.FLAME.func_179348_c(), new EntityFlameFX.Factory());
/*  94 */     func_178929_a(EnumParticleTypes.LAVA.func_179348_c(), new EntityLavaFX.Factory());
/*  95 */     func_178929_a(EnumParticleTypes.FOOTSTEP.func_179348_c(), new EntityFootStepFX.Factory());
/*  96 */     func_178929_a(EnumParticleTypes.CLOUD.func_179348_c(), new EntityCloudFX.Factory());
/*  97 */     func_178929_a(EnumParticleTypes.REDSTONE.func_179348_c(), new EntityReddustFX.Factory());
/*  98 */     func_178929_a(EnumParticleTypes.SNOWBALL.func_179348_c(), new EntityBreakingFX.SnowballFactory());
/*  99 */     func_178929_a(EnumParticleTypes.SNOW_SHOVEL.func_179348_c(), new EntitySnowShovelFX.Factory());
/* 100 */     func_178929_a(EnumParticleTypes.SLIME.func_179348_c(), new EntityBreakingFX.SlimeFactory());
/* 101 */     func_178929_a(EnumParticleTypes.HEART.func_179348_c(), new EntityHeartFX.Factory());
/* 102 */     func_178929_a(EnumParticleTypes.BARRIER.func_179348_c(), new Barrier.Factory());
/* 103 */     func_178929_a(EnumParticleTypes.ITEM_CRACK.func_179348_c(), new EntityBreakingFX.Factory());
/* 104 */     func_178929_a(EnumParticleTypes.BLOCK_CRACK.func_179348_c(), new EntityDiggingFX.Factory());
/* 105 */     func_178929_a(EnumParticleTypes.BLOCK_DUST.func_179348_c(), new EntityBlockDustFX.Factory());
/* 106 */     func_178929_a(EnumParticleTypes.EXPLOSION_HUGE.func_179348_c(), new EntityHugeExplodeFX.Factory());
/* 107 */     func_178929_a(EnumParticleTypes.EXPLOSION_LARGE.func_179348_c(), new EntityLargeExplodeFX.Factory());
/* 108 */     func_178929_a(EnumParticleTypes.FIREWORKS_SPARK.func_179348_c(), new EntityFireworkStarterFX_Factory());
/* 109 */     func_178929_a(EnumParticleTypes.MOB_APPEARANCE.func_179348_c(), new MobAppearance.Factory());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_178929_a(int p_178929_1_, IParticleFactory p_178929_2_) {
/* 114 */     field_178932_g.put(Integer.valueOf(p_178929_1_), p_178929_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178926_a(Entity p_178926_1_, EnumParticleTypes p_178926_2_) {
/* 119 */     this.field_178933_d.add(new EntityParticleEmitter(this.worldObj, p_178926_1_, p_178926_2_));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFX func_178927_a(int p_178927_1_, double p_178927_2_, double p_178927_4_, double p_178927_6_, double p_178927_8_, double p_178927_10_, double p_178927_12_, int... p_178927_14_) {
/* 124 */     IParticleFactory var15 = (IParticleFactory)field_178932_g.get(Integer.valueOf(p_178927_1_));
/*     */     
/* 126 */     if (var15 != null) {
/*     */       
/* 128 */       EntityFX var16 = var15.func_178902_a(p_178927_1_, this.worldObj, p_178927_2_, p_178927_4_, p_178927_6_, p_178927_8_, p_178927_10_, p_178927_12_, p_178927_14_);
/*     */       
/* 130 */       if (var16 != null) {
/*     */         
/* 132 */         addEffect(var16);
/* 133 */         return var16;
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffect(EntityFX p_78873_1_) {
/* 142 */     if (p_78873_1_ != null)
/*     */     {
/* 144 */       if (!(p_78873_1_ instanceof EntityFireworkSparkFX) || Config.isFireworkParticles()) {
/*     */         
/* 146 */         int var2 = p_78873_1_.getFXLayer();
/* 147 */         int var3 = (p_78873_1_.func_174838_j() != 1.0F) ? 0 : 1;
/*     */         
/* 149 */         if (fxLayers[var2][var3].size() >= 4000)
/*     */         {
/* 151 */           fxLayers[var2][var3].remove(0);
/*     */         }
/*     */         
/* 154 */         if (!(p_78873_1_ instanceof Barrier) || !reuseBarrierParticle(p_78873_1_, fxLayers[var2][var3]))
/*     */         {
/* 156 */           fxLayers[var2][var3].add(p_78873_1_);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEffects() {
/* 164 */     for (int var4 = 0; var4 < 4; var4++)
/*     */     {
/* 166 */       func_178922_a(var4);
/*     */     }
/*     */     
/* 169 */     ArrayList<EntityParticleEmitter> var41 = Lists.newArrayList();
/* 170 */     Iterator<EntityParticleEmitter> var2 = this.field_178933_d.iterator();
/*     */     
/* 172 */     while (var2.hasNext()) {
/*     */       
/* 174 */       EntityParticleEmitter var3 = var2.next();
/* 175 */       var3.onUpdate();
/*     */       
/* 177 */       if (var3.isDead)
/*     */       {
/* 179 */         var41.add(var3);
/*     */       }
/*     */     } 
/*     */     
/* 183 */     this.field_178933_d.removeAll(var41);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178922_a(int p_178922_1_) {
/* 188 */     for (int var2 = 0; var2 < 2; var2++)
/*     */     {
/* 190 */       func_178925_a(fxLayers[p_178922_1_][var2]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178925_a(List<EntityFX> p_178925_1_) {
/* 196 */     ArrayList<EntityFX> var2 = Lists.newArrayList();
/*     */     
/* 198 */     for (int var3 = 0; var3 < p_178925_1_.size(); var3++) {
/*     */       
/* 200 */       EntityFX var4 = p_178925_1_.get(var3);
/* 201 */       func_178923_d(var4);
/*     */       
/* 203 */       if (var4.isDead)
/*     */       {
/* 205 */         var2.add(var4);
/*     */       }
/*     */     } 
/*     */     
/* 209 */     p_178925_1_.removeAll(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_178923_d(final EntityFX p_178923_1_) {
/*     */     try {
/* 216 */       p_178923_1_.onUpdate();
/*     */     }
/* 218 */     catch (Throwable var6) {
/*     */       
/* 220 */       CrashReport var3 = CrashReport.makeCrashReport(var6, "Ticking Particle");
/* 221 */       CrashReportCategory var4 = var3.makeCategory("Particle being ticked");
/* 222 */       final int var5 = p_178923_1_.getFXLayer();
/* 223 */       var4.addCrashSectionCallable("Particle", new Callable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00000916";
/*     */             
/*     */             public String call() {
/* 228 */               return p_178923_1_.toString();
/*     */             }
/*     */           });
/* 231 */       var4.addCrashSectionCallable("Particle Type", new Callable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00000917";
/*     */             
/*     */             public String call() {
/* 236 */               return (var5 == 0) ? "MISC_TEXTURE" : ((var5 == 1) ? "TERRAIN_TEXTURE" : ((var5 == 3) ? "ENTITY_PARTICLE_TEXTURE" : ("Unknown - " + var5)));
/*     */             }
/*     */           });
/* 239 */       throw new ReportedException(var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderParticles(Entity p_78874_1_, float partialTicks) {
/* 248 */     float var3 = ActiveRenderInfo.func_178808_b();
/* 249 */     float var4 = ActiveRenderInfo.func_178803_d();
/* 250 */     float var5 = ActiveRenderInfo.func_178805_e();
/* 251 */     float var6 = ActiveRenderInfo.func_178807_f();
/* 252 */     float var7 = ActiveRenderInfo.func_178809_c();
/* 253 */     EntityFX.interpPosX = p_78874_1_.lastTickPosX + (p_78874_1_.posX - p_78874_1_.lastTickPosX) * partialTicks;
/* 254 */     EntityFX.interpPosY = p_78874_1_.lastTickPosY + (p_78874_1_.posY - p_78874_1_.lastTickPosY) * partialTicks;
/* 255 */     EntityFX.interpPosZ = p_78874_1_.lastTickPosZ + (p_78874_1_.posZ - p_78874_1_.lastTickPosZ) * partialTicks;
/* 256 */     GlStateManager.enableBlend();
/* 257 */     GlStateManager.blendFunc(770, 771);
/* 258 */     GlStateManager.alphaFunc(516, 0.003921569F);
/*     */     
/* 260 */     for (int var8_nf = 0; var8_nf < 3; var8_nf++) {
/*     */       
/* 262 */       final int var8 = var8_nf;
/*     */       
/* 264 */       for (int var9 = 0; var9 < 2; var9++) {
/*     */         
/* 266 */         if (!fxLayers[var8][var9].isEmpty()) {
/*     */           
/* 268 */           switch (var9) {
/*     */             
/*     */             case 0:
/* 271 */               GlStateManager.depthMask(false);
/*     */               break;
/*     */             
/*     */             case 1:
/* 275 */               GlStateManager.depthMask(true);
/*     */               break;
/*     */           } 
/* 278 */           switch (var8) {
/*     */ 
/*     */             
/*     */             default:
/* 282 */               renderer.bindTexture(particleTextures);
/*     */               break;
/*     */             
/*     */             case 1:
/* 286 */               renderer.bindTexture(TextureMap.locationBlocksTexture);
/*     */               break;
/*     */           } 
/* 289 */           GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 290 */           Tessellator var10 = Tessellator.getInstance();
/* 291 */           WorldRenderer var11 = var10.getWorldRenderer();
/* 292 */           var11.startDrawingQuads();
/*     */           
/* 294 */           for (int var12 = 0; var12 < fxLayers[var8][var9].size(); var12++) {
/*     */             
/* 296 */             final EntityFX var13 = fxLayers[var8][var9].get(var12);
/* 297 */             var11.func_178963_b(var13.getBrightnessForRender(partialTicks));
/*     */ 
/*     */             
/*     */             try {
/* 301 */               var13.func_180434_a(var11, p_78874_1_, partialTicks, var3, var7, var4, var5, var6);
/*     */             }
/* 303 */             catch (Throwable var18) {
/*     */               
/* 305 */               CrashReport var15 = CrashReport.makeCrashReport(var18, "Rendering Particle");
/* 306 */               CrashReportCategory var16 = var15.makeCategory("Particle being rendered");
/* 307 */               var16.addCrashSectionCallable("Particle", new Callable()
/*     */                   {
/*     */                     private static final String __OBFID = "CL_00000918";
/*     */                     
/*     */                     public String call() {
/* 312 */                       return var13.toString();
/*     */                     }
/*     */                   });
/* 315 */               var16.addCrashSectionCallable("Particle Type", new Callable()
/*     */                   {
/*     */                     private static final String __OBFID = "CL_00000919";
/*     */                     
/*     */                     public String call() {
/* 320 */                       return (var8 == 0) ? "MISC_TEXTURE" : ((var8 == 1) ? "TERRAIN_TEXTURE" : ((var8 == 3) ? "ENTITY_PARTICLE_TEXTURE" : ("Unknown - " + var8)));
/*     */                     }
/*     */                   });
/* 323 */               throw new ReportedException(var15);
/*     */             } 
/*     */           } 
/*     */           
/* 327 */           var10.draw();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 332 */     GlStateManager.depthMask(true);
/* 333 */     GlStateManager.disableBlend();
/* 334 */     GlStateManager.alphaFunc(516, 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderLitParticles(Entity p_78872_1_, float p_78872_2_) {
/* 339 */     float var3 = 0.017453292F;
/* 340 */     float var4 = MathHelper.cos(p_78872_1_.rotationYaw * 0.017453292F);
/* 341 */     float var5 = MathHelper.sin(p_78872_1_.rotationYaw * 0.017453292F);
/* 342 */     float var6 = -var5 * MathHelper.sin(p_78872_1_.rotationPitch * 0.017453292F);
/* 343 */     float var7 = var4 * MathHelper.sin(p_78872_1_.rotationPitch * 0.017453292F);
/* 344 */     float var8 = MathHelper.cos(p_78872_1_.rotationPitch * 0.017453292F);
/*     */     
/* 346 */     for (int var9 = 0; var9 < 2; var9++) {
/*     */       
/* 348 */       List<EntityFX> var10 = fxLayers[3][var9];
/*     */       
/* 350 */       if (!var10.isEmpty()) {
/*     */         
/* 352 */         Tessellator var11 = Tessellator.getInstance();
/* 353 */         WorldRenderer var12 = var11.getWorldRenderer();
/*     */         
/* 355 */         for (int var13 = 0; var13 < var10.size(); var13++) {
/*     */           
/* 357 */           EntityFX var14 = var10.get(var13);
/* 358 */           var12.func_178963_b(var14.getBrightnessForRender(p_78872_2_));
/* 359 */           var14.func_180434_a(var12, p_78872_1_, p_78872_2_, var4, var8, var5, var6, var7);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearEffects(World worldIn) {
/* 367 */     this.worldObj = worldIn;
/*     */     
/* 369 */     for (int var2 = 0; var2 < 4; var2++) {
/*     */       
/* 371 */       for (int var3 = 0; var3 < 2; var3++)
/*     */       {
/* 373 */         fxLayers[var2][var3].clear();
/*     */       }
/*     */     } 
/*     */     
/* 377 */     this.field_178933_d.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180533_a(BlockPos p_180533_1_, IBlockState p_180533_2_) {
/*     */     boolean notAir;
/* 384 */     if (Reflector.ForgeBlock_addDestroyEffects.exists() && Reflector.ForgeBlock_isAir.exists()) {
/*     */       
/* 386 */       Block var3 = p_180533_2_.getBlock();
/* 387 */       Reflector.callBoolean(var3, Reflector.ForgeBlock_isAir, new Object[] { this.worldObj, p_180533_1_ });
/* 388 */       notAir = (!Reflector.callBoolean(var3, Reflector.ForgeBlock_isAir, new Object[] { this.worldObj, p_180533_1_ }) && !Reflector.callBoolean(var3, Reflector.ForgeBlock_addDestroyEffects, new Object[] { this.worldObj, p_180533_1_, this }));
/*     */     }
/*     */     else {
/*     */       
/* 392 */       notAir = (p_180533_2_.getBlock().getMaterial() != Material.air);
/*     */     } 
/*     */     
/* 395 */     if (notAir) {
/*     */       
/* 397 */       p_180533_2_ = p_180533_2_.getBlock().getActualState(p_180533_2_, (IBlockAccess)this.worldObj, p_180533_1_);
/* 398 */       byte var14 = 4;
/*     */       
/* 400 */       for (int var4 = 0; var4 < var14; var4++) {
/*     */         
/* 402 */         for (int var5 = 0; var5 < var14; var5++) {
/*     */           
/* 404 */           for (int var6 = 0; var6 < var14; var6++) {
/*     */             
/* 406 */             double var7 = p_180533_1_.getX() + (var4 + 0.5D) / var14;
/* 407 */             double var9 = p_180533_1_.getY() + (var5 + 0.5D) / var14;
/* 408 */             double var11 = p_180533_1_.getZ() + (var6 + 0.5D) / var14;
/* 409 */             addEffect((new EntityDiggingFX(this.worldObj, var7, var9, var11, var7 - p_180533_1_.getX() - 0.5D, var9 - p_180533_1_.getY() - 0.5D, var11 - p_180533_1_.getZ() - 0.5D, p_180533_2_)).func_174846_a(p_180533_1_));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180532_a(BlockPos p_180532_1_, EnumFacing p_180532_2_) {
/* 418 */     IBlockState var3 = this.worldObj.getBlockState(p_180532_1_);
/* 419 */     Block var4 = var3.getBlock();
/*     */     
/* 421 */     if (var4.getRenderType() != -1) {
/*     */       
/* 423 */       int var5 = p_180532_1_.getX();
/* 424 */       int var6 = p_180532_1_.getY();
/* 425 */       int var7 = p_180532_1_.getZ();
/* 426 */       float var8 = 0.1F;
/* 427 */       double var9 = var5 + this.rand.nextDouble() * (var4.getBlockBoundsMaxX() - var4.getBlockBoundsMinX() - (var8 * 2.0F)) + var8 + var4.getBlockBoundsMinX();
/* 428 */       double var11 = var6 + this.rand.nextDouble() * (var4.getBlockBoundsMaxY() - var4.getBlockBoundsMinY() - (var8 * 2.0F)) + var8 + var4.getBlockBoundsMinY();
/* 429 */       double var13 = var7 + this.rand.nextDouble() * (var4.getBlockBoundsMaxZ() - var4.getBlockBoundsMinZ() - (var8 * 2.0F)) + var8 + var4.getBlockBoundsMinZ();
/*     */       
/* 431 */       if (p_180532_2_ == EnumFacing.DOWN)
/*     */       {
/* 433 */         var11 = var6 + var4.getBlockBoundsMinY() - var8;
/*     */       }
/*     */       
/* 436 */       if (p_180532_2_ == EnumFacing.UP)
/*     */       {
/* 438 */         var11 = var6 + var4.getBlockBoundsMaxY() + var8;
/*     */       }
/*     */       
/* 441 */       if (p_180532_2_ == EnumFacing.NORTH)
/*     */       {
/* 443 */         var13 = var7 + var4.getBlockBoundsMinZ() - var8;
/*     */       }
/*     */       
/* 446 */       if (p_180532_2_ == EnumFacing.SOUTH)
/*     */       {
/* 448 */         var13 = var7 + var4.getBlockBoundsMaxZ() + var8;
/*     */       }
/*     */       
/* 451 */       if (p_180532_2_ == EnumFacing.WEST)
/*     */       {
/* 453 */         var9 = var5 + var4.getBlockBoundsMinX() - var8;
/*     */       }
/*     */       
/* 456 */       if (p_180532_2_ == EnumFacing.EAST)
/*     */       {
/* 458 */         var9 = var5 + var4.getBlockBoundsMaxX() + var8;
/*     */       }
/*     */       
/* 461 */       addEffect((new EntityDiggingFX(this.worldObj, var9, var11, var13, 0.0D, 0.0D, 0.0D, var3)).func_174846_a(p_180532_1_).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178928_b(EntityFX p_178928_1_) {
/* 467 */     func_178924_a(p_178928_1_, 1, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178931_c(EntityFX p_178931_1_) {
/* 472 */     func_178924_a(p_178931_1_, 0, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178924_a(EntityFX p_178924_1_, int p_178924_2_, int p_178924_3_) {
/* 477 */     for (int var4 = 0; var4 < 4; var4++) {
/*     */       
/* 479 */       if (fxLayers[var4][p_178924_2_].contains(p_178924_1_)) {
/*     */         
/* 481 */         fxLayers[var4][p_178924_2_].remove(p_178924_1_);
/* 482 */         fxLayers[var4][p_178924_3_].add(p_178924_1_);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStatistics() {
/* 489 */     int var1 = 0;
/*     */     
/* 491 */     for (int var2 = 0; var2 < 4; var2++) {
/*     */       
/* 493 */       for (int var3 = 0; var3 < 2; var3++)
/*     */       {
/* 495 */         var1 += fxLayers[var2][var3].size();
/*     */       }
/*     */     } 
/*     */     
/* 499 */     return var1;
/*     */   }
/*     */   
/*     */   private boolean reuseBarrierParticle(EntityFX entityfx, List<EntityFX> list) {
/*     */     EntityFX efx;
/* 504 */     Iterator<EntityFX> it = list.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 509 */       if (!it.hasNext())
/*     */       {
/* 511 */         return false;
/*     */       }
/*     */       
/* 514 */       efx = it.next();
/*     */     }
/* 516 */     while (!(efx instanceof Barrier) || entityfx.posX != efx.posX || entityfx.posY != efx.posY || entityfx.posZ != efx.posZ);
/*     */     
/* 518 */     efx.particleAge = 0;
/* 519 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addBlockHitEffects(BlockPos pos, MovingObjectPosition target) {
/* 524 */     Block block = this.worldObj.getBlockState(pos).getBlock();
/* 525 */     boolean blockAddHitEffects = Reflector.callBoolean(block, Reflector.ForgeBlock_addHitEffects, new Object[] { this.worldObj, target, this });
/*     */     
/* 527 */     if (block != null && !blockAddHitEffects)
/*     */     {
/* 529 */       func_180532_a(pos, target.field_178784_b);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EffectRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */