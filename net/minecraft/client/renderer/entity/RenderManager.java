/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBed;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelChicken;
/*     */ import net.minecraft.client.model.ModelCow;
/*     */ import net.minecraft.client.model.ModelHorse;
/*     */ import net.minecraft.client.model.ModelOcelot;
/*     */ import net.minecraft.client.model.ModelPig;
/*     */ import net.minecraft.client.model.ModelRabbit;
/*     */ import net.minecraft.client.model.ModelSheep2;
/*     */ import net.minecraft.client.model.ModelSlime;
/*     */ import net.minecraft.client.model.ModelSquid;
/*     */ import net.minecraft.client.model.ModelWolf;
/*     */ import net.minecraft.client.model.ModelZombie;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.culling.ICamera;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
/*     */ import net.minecraft.client.renderer.tileentity.RenderItemFrame;
/*     */ import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLeashKnot;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityMinecartMobSpawner;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.entity.boss.EntityWither;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ import net.minecraft.entity.item.EntityBoat;
/*     */ import net.minecraft.entity.item.EntityEnderCrystal;
/*     */ import net.minecraft.entity.item.EntityEnderEye;
/*     */ import net.minecraft.entity.item.EntityEnderPearl;
/*     */ import net.minecraft.entity.item.EntityExpBottle;
/*     */ import net.minecraft.entity.item.EntityFallingBlock;
/*     */ import net.minecraft.entity.item.EntityFireworkRocket;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.entity.item.EntityMinecartTNT;
/*     */ import net.minecraft.entity.item.EntityPainting;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.EntityBlaze;
/*     */ import net.minecraft.entity.monster.EntityCaveSpider;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntityEndermite;
/*     */ import net.minecraft.entity.monster.EntityGhast;
/*     */ import net.minecraft.entity.monster.EntityGiantZombie;
/*     */ import net.minecraft.entity.monster.EntityGuardian;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
/*     */ import net.minecraft.entity.monster.EntityMagmaCube;
/*     */ import net.minecraft.entity.monster.EntityPigZombie;
/*     */ import net.minecraft.entity.monster.EntitySilverfish;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.monster.EntitySnowman;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityMooshroom;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntityRabbit;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntitySquid;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.entity.projectile.EntityEgg;
/*     */ import net.minecraft.entity.projectile.EntityFishHook;
/*     */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*     */ import net.minecraft.entity.projectile.EntityPotion;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.entity.projectile.EntitySnowball;
/*     */ import net.minecraft.entity.projectile.EntityWitherSkull;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import optifine.PlayerItemsLayer;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ public class RenderManager {
/* 109 */   private Map entityRenderMap = Maps.newHashMap();
/* 110 */   private Map field_178636_l = Maps.newHashMap();
/*     */   
/*     */   private RenderPlayer field_178637_m;
/*     */   
/*     */   private FontRenderer textRenderer;
/*     */   
/*     */   public static double renderPosX;
/*     */   
/*     */   public static double renderPosY;
/*     */   
/*     */   public static double renderPosZ;
/*     */   
/*     */   public TextureManager renderEngine;
/*     */   
/*     */   public World worldObj;
/*     */   
/*     */   public Entity livingPlayer;
/*     */   
/*     */   public Entity field_147941_i;
/*     */   
/*     */   public float playerViewY;
/*     */   
/*     */   public float playerViewX;
/*     */   public GameSettings options;
/*     */   public double viewerPosX;
/*     */   public double viewerPosY;
/*     */   public double viewerPosZ;
/*     */   private boolean field_178639_r = false;
/*     */   private boolean field_178638_s = true;
/*     */   private boolean debugBoundingBox = false;
/*     */   private static final String __OBFID = "CL_00000991";
/*     */   
/*     */   public RenderManager(TextureManager p_i46180_1_, RenderItem p_i46180_2_) {
/* 143 */     this.renderEngine = p_i46180_1_;
/* 144 */     this.entityRenderMap.put(EntityCaveSpider.class, new RenderCaveSpider(this));
/* 145 */     this.entityRenderMap.put(EntitySpider.class, new RenderSpider(this));
/* 146 */     this.entityRenderMap.put(EntityPig.class, new RenderPig(this, (ModelBase)new ModelPig(), 0.7F));
/* 147 */     this.entityRenderMap.put(EntitySheep.class, new RenderSheep(this, (ModelBase)new ModelSheep2(), 0.7F));
/* 148 */     this.entityRenderMap.put(EntityCow.class, new RenderCow(this, (ModelBase)new ModelCow(), 0.7F));
/* 149 */     this.entityRenderMap.put(EntityMooshroom.class, new RenderMooshroom(this, (ModelBase)new ModelCow(), 0.7F));
/* 150 */     this.entityRenderMap.put(EntityWolf.class, new RenderWolf(this, (ModelBase)new ModelWolf(), 0.5F));
/* 151 */     this.entityRenderMap.put(EntityChicken.class, new RenderChicken(this, (ModelBase)new ModelChicken(), 0.3F));
/* 152 */     this.entityRenderMap.put(EntityOcelot.class, new RenderOcelot(this, (ModelBase)new ModelOcelot(), 0.4F));
/* 153 */     this.entityRenderMap.put(EntityRabbit.class, new RenderRabbit(this, (ModelBase)new ModelRabbit(), 0.3F));
/* 154 */     this.entityRenderMap.put(EntitySilverfish.class, new RenderSilverfish(this));
/* 155 */     this.entityRenderMap.put(EntityEndermite.class, new RenderEndermite(this));
/* 156 */     this.entityRenderMap.put(EntityCreeper.class, new RenderCreeper(this));
/* 157 */     this.entityRenderMap.put(EntityEnderman.class, new RenderEnderman(this));
/* 158 */     this.entityRenderMap.put(EntitySnowman.class, new RenderSnowMan(this));
/* 159 */     this.entityRenderMap.put(EntitySkeleton.class, new RenderSkeleton(this));
/* 160 */     this.entityRenderMap.put(EntityWitch.class, new RenderWitch(this));
/* 161 */     this.entityRenderMap.put(EntityBlaze.class, new RenderBlaze(this));
/* 162 */     this.entityRenderMap.put(EntityPigZombie.class, new RenderPigZombie(this));
/* 163 */     this.entityRenderMap.put(EntityZombie.class, new RenderZombie(this));
/* 164 */     this.entityRenderMap.put(EntitySlime.class, new RenderSlime(this, (ModelBase)new ModelSlime(16), 0.25F));
/* 165 */     this.entityRenderMap.put(EntityMagmaCube.class, new RenderMagmaCube(this));
/* 166 */     this.entityRenderMap.put(EntityGiantZombie.class, new RenderGiantZombie(this, (ModelBase)new ModelZombie(), 0.5F, 6.0F));
/* 167 */     this.entityRenderMap.put(EntityGhast.class, new RenderGhast(this));
/* 168 */     this.entityRenderMap.put(EntitySquid.class, new RenderSquid(this, (ModelBase)new ModelSquid(), 0.7F));
/* 169 */     this.entityRenderMap.put(EntityVillager.class, new RenderVillager(this));
/* 170 */     this.entityRenderMap.put(EntityIronGolem.class, new RenderIronGolem(this));
/* 171 */     this.entityRenderMap.put(EntityBat.class, new RenderBat(this));
/* 172 */     this.entityRenderMap.put(EntityGuardian.class, new RenderGuardian(this));
/* 173 */     this.entityRenderMap.put(EntityDragon.class, new RenderDragon(this));
/* 174 */     this.entityRenderMap.put(EntityEnderCrystal.class, new RenderEnderCrystal(this));
/* 175 */     this.entityRenderMap.put(EntityWither.class, new RenderWither(this));
/* 176 */     this.entityRenderMap.put(Entity.class, new RenderEntity(this));
/* 177 */     this.entityRenderMap.put(EntityPainting.class, new RenderPainting(this));
/* 178 */     this.entityRenderMap.put(EntityItemFrame.class, new RenderItemFrame(this, p_i46180_2_));
/* 179 */     this.entityRenderMap.put(EntityLeashKnot.class, new RenderLeashKnot(this));
/* 180 */     this.entityRenderMap.put(EntityArrow.class, new RenderArrow(this));
/* 181 */     this.entityRenderMap.put(EntitySnowball.class, new RenderSnowball(this, Items.snowball, p_i46180_2_));
/* 182 */     this.entityRenderMap.put(EntityEnderPearl.class, new RenderSnowball(this, Items.ender_pearl, p_i46180_2_));
/* 183 */     this.entityRenderMap.put(EntityEnderEye.class, new RenderSnowball(this, Items.ender_eye, p_i46180_2_));
/* 184 */     this.entityRenderMap.put(EntityEgg.class, new RenderSnowball(this, Items.egg, p_i46180_2_));
/* 185 */     this.entityRenderMap.put(EntityPotion.class, new RenderPotion(this, p_i46180_2_));
/* 186 */     this.entityRenderMap.put(EntityExpBottle.class, new RenderSnowball(this, Items.experience_bottle, p_i46180_2_));
/* 187 */     this.entityRenderMap.put(EntityFireworkRocket.class, new RenderSnowball(this, Items.fireworks, p_i46180_2_));
/* 188 */     this.entityRenderMap.put(EntityLargeFireball.class, new RenderFireball(this, 2.0F));
/* 189 */     this.entityRenderMap.put(EntitySmallFireball.class, new RenderFireball(this, 0.5F));
/* 190 */     this.entityRenderMap.put(EntityWitherSkull.class, new RenderWitherSkull(this));
/* 191 */     this.entityRenderMap.put(EntityItem.class, new RenderEntityItem(this, p_i46180_2_));
/* 192 */     this.entityRenderMap.put(EntityXPOrb.class, new RenderXPOrb(this));
/* 193 */     this.entityRenderMap.put(EntityTNTPrimed.class, new RenderTNTPrimed(this));
/* 194 */     this.entityRenderMap.put(EntityFallingBlock.class, new RenderFallingBlock(this));
/* 195 */     this.entityRenderMap.put(EntityArmorStand.class, new ArmorStandRenderer(this));
/* 196 */     this.entityRenderMap.put(EntityMinecartTNT.class, new RenderTntMinecart(this));
/* 197 */     this.entityRenderMap.put(EntityMinecartMobSpawner.class, new RenderMinecartMobSpawner(this));
/* 198 */     this.entityRenderMap.put(EntityMinecart.class, new RenderMinecart(this));
/* 199 */     this.entityRenderMap.put(EntityBoat.class, new RenderBoat(this));
/* 200 */     this.entityRenderMap.put(EntityFishHook.class, new RenderFish(this));
/* 201 */     this.entityRenderMap.put(EntityHorse.class, new RenderHorse(this, new ModelHorse(), 0.75F));
/* 202 */     this.entityRenderMap.put(EntityLightningBolt.class, new RenderLightningBolt(this));
/* 203 */     this.field_178637_m = new RenderPlayer(this);
/* 204 */     this.field_178636_l.put("default", this.field_178637_m);
/* 205 */     this.field_178636_l.put("slim", new RenderPlayer(this, true));
/* 206 */     PlayerItemsLayer.register(this.field_178636_l);
/*     */     
/* 208 */     if (Reflector.RenderingRegistry_loadEntityRenderers.exists())
/*     */     {
/* 210 */       Reflector.call(Reflector.RenderingRegistry_loadEntityRenderers, new Object[] { this, this.entityRenderMap });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178628_a(double p_178628_1_, double p_178628_3_, double p_178628_5_) {
/* 216 */     renderPosX = p_178628_1_;
/* 217 */     renderPosY = p_178628_3_;
/* 218 */     renderPosZ = p_178628_5_;
/*     */   }
/*     */ 
/*     */   
/*     */   public Render getEntityClassRenderObject(Class<Entity> p_78715_1_) {
/* 223 */     Render var2 = (Render)this.entityRenderMap.get(p_78715_1_);
/*     */     
/* 225 */     if (var2 == null && p_78715_1_ != Entity.class) {
/*     */       
/* 227 */       var2 = getEntityClassRenderObject(p_78715_1_.getSuperclass());
/* 228 */       this.entityRenderMap.put(p_78715_1_, var2);
/*     */     } 
/*     */     
/* 231 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Render getEntityRenderObject(Entity p_78713_1_) {
/* 236 */     if (p_78713_1_ instanceof AbstractClientPlayer) {
/*     */       
/* 238 */       String var2 = ((AbstractClientPlayer)p_78713_1_).func_175154_l();
/* 239 */       RenderPlayer var3 = (RenderPlayer)this.field_178636_l.get(var2);
/* 240 */       return (var3 != null) ? var3 : this.field_178637_m;
/*     */     } 
/*     */ 
/*     */     
/* 244 */     return getEntityClassRenderObject(p_78713_1_.getClass());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180597_a(World worldIn, FontRenderer p_180597_2_, Entity p_180597_3_, Entity p_180597_4_, GameSettings p_180597_5_, float p_180597_6_) {
/* 250 */     this.worldObj = worldIn;
/* 251 */     this.options = p_180597_5_;
/* 252 */     this.livingPlayer = p_180597_3_;
/* 253 */     this.field_147941_i = p_180597_4_;
/* 254 */     this.textRenderer = p_180597_2_;
/*     */     
/* 256 */     if (p_180597_3_ instanceof EntityLivingBase && ((EntityLivingBase)p_180597_3_).isPlayerSleeping()) {
/*     */       
/* 258 */       IBlockState var7 = worldIn.getBlockState(new BlockPos(p_180597_3_));
/* 259 */       Block var8 = var7.getBlock();
/*     */       
/* 261 */       if (Reflector.callBoolean(Reflector.ForgeBlock_isBed, new Object[] { worldIn, new BlockPos(p_180597_3_), p_180597_3_ }))
/*     */       {
/* 263 */         EnumFacing var9 = (EnumFacing)Reflector.call(var8, Reflector.ForgeBlock_getBedDirection, new Object[] { worldIn, new BlockPos(p_180597_3_) });
/* 264 */         int var91 = var9.getHorizontalIndex();
/* 265 */         this.playerViewY = (var91 * 90 + 180);
/* 266 */         this.playerViewX = 0.0F;
/*     */       }
/* 268 */       else if (var8 == Blocks.bed)
/*     */       {
/* 270 */         int var92 = ((EnumFacing)var7.getValue((IProperty)BlockBed.AGE)).getHorizontalIndex();
/* 271 */         this.playerViewY = (var92 * 90 + 180);
/* 272 */         this.playerViewX = 0.0F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 277 */       this.playerViewY = p_180597_3_.prevRotationYaw + (p_180597_3_.rotationYaw - p_180597_3_.prevRotationYaw) * p_180597_6_;
/* 278 */       this.playerViewX = p_180597_3_.prevRotationPitch + (p_180597_3_.rotationPitch - p_180597_3_.prevRotationPitch) * p_180597_6_;
/*     */     } 
/*     */     
/* 281 */     if (p_180597_5_.thirdPersonView == 2)
/*     */     {
/* 283 */       this.playerViewY += 180.0F;
/*     */     }
/*     */     
/* 286 */     this.viewerPosX = p_180597_3_.lastTickPosX + (p_180597_3_.posX - p_180597_3_.lastTickPosX) * p_180597_6_;
/* 287 */     this.viewerPosY = p_180597_3_.lastTickPosY + (p_180597_3_.posY - p_180597_3_.lastTickPosY) * p_180597_6_;
/* 288 */     this.viewerPosZ = p_180597_3_.lastTickPosZ + (p_180597_3_.posZ - p_180597_3_.lastTickPosZ) * p_180597_6_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178631_a(float p_178631_1_) {
/* 293 */     this.playerViewY = p_178631_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178627_a() {
/* 298 */     return this.field_178638_s;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178633_a(boolean p_178633_1_) {
/* 303 */     this.field_178638_s = p_178633_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178629_b(boolean p_178629_1_) {
/* 308 */     this.debugBoundingBox = p_178629_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178634_b() {
/* 313 */     return this.debugBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderEntitySimple(Entity p_147937_1_, float p_147937_2_) {
/* 318 */     return renderEntityStatic(p_147937_1_, p_147937_2_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178635_a(Entity p_178635_1_, ICamera p_178635_2_, double p_178635_3_, double p_178635_5_, double p_178635_7_) {
/* 323 */     Render var9 = getEntityRenderObject(p_178635_1_);
/* 324 */     return (var9 != null && var9.func_177071_a(p_178635_1_, p_178635_2_, p_178635_3_, p_178635_5_, p_178635_7_));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderEntityStatic(Entity p_147936_1_, float p_147936_2_, boolean p_147936_3_) {
/* 329 */     if (p_147936_1_.ticksExisted == 0) {
/*     */       
/* 331 */       p_147936_1_.lastTickPosX = p_147936_1_.posX;
/* 332 */       p_147936_1_.lastTickPosY = p_147936_1_.posY;
/* 333 */       p_147936_1_.lastTickPosZ = p_147936_1_.posZ;
/*     */     } 
/*     */     
/* 336 */     double var4 = p_147936_1_.lastTickPosX + (p_147936_1_.posX - p_147936_1_.lastTickPosX) * p_147936_2_;
/* 337 */     double var6 = p_147936_1_.lastTickPosY + (p_147936_1_.posY - p_147936_1_.lastTickPosY) * p_147936_2_;
/* 338 */     double var8 = p_147936_1_.lastTickPosZ + (p_147936_1_.posZ - p_147936_1_.lastTickPosZ) * p_147936_2_;
/* 339 */     float var10 = p_147936_1_.prevRotationYaw + (p_147936_1_.rotationYaw - p_147936_1_.prevRotationYaw) * p_147936_2_;
/* 340 */     int var11 = p_147936_1_.getBrightnessForRender(p_147936_2_);
/*     */     
/* 342 */     if (p_147936_1_.isBurning())
/*     */     {
/* 344 */       var11 = 15728880;
/*     */     }
/*     */     
/* 347 */     int var12 = var11 % 65536;
/* 348 */     int var13 = var11 / 65536;
/* 349 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var12 / 1.0F, var13 / 1.0F);
/* 350 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 351 */     return doRenderEntity(p_147936_1_, var4 - renderPosX, var6 - renderPosY, var8 - renderPosZ, var10, p_147936_2_, p_147936_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178630_b(Entity p_178630_1_, float p_178630_2_) {
/* 356 */     double var3 = p_178630_1_.lastTickPosX + (p_178630_1_.posX - p_178630_1_.lastTickPosX) * p_178630_2_;
/* 357 */     double var5 = p_178630_1_.lastTickPosY + (p_178630_1_.posY - p_178630_1_.lastTickPosY) * p_178630_2_;
/* 358 */     double var7 = p_178630_1_.lastTickPosZ + (p_178630_1_.posZ - p_178630_1_.lastTickPosZ) * p_178630_2_;
/* 359 */     Render var9 = getEntityRenderObject(p_178630_1_);
/*     */     
/* 361 */     if (var9 != null && this.renderEngine != null) {
/*     */       
/* 363 */       int var10 = p_178630_1_.getBrightnessForRender(p_178630_2_);
/* 364 */       int var11 = var10 % 65536;
/* 365 */       int var12 = var10 / 65536;
/* 366 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var11 / 1.0F, var12 / 1.0F);
/* 367 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 368 */       var9.func_177067_a(p_178630_1_, var3 - renderPosX, var5 - renderPosY, var7 - renderPosZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderEntityWithPosYaw(Entity p_147940_1_, double p_147940_2_, double p_147940_4_, double p_147940_6_, float p_147940_8_, float p_147940_9_) {
/* 374 */     return doRenderEntity(p_147940_1_, p_147940_2_, p_147940_4_, p_147940_6_, p_147940_8_, p_147940_9_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doRenderEntity(Entity p_147939_1_, double p_147939_2_, double p_147939_4_, double p_147939_6_, float p_147939_8_, float p_147939_9_, boolean p_147939_10_) {
/* 379 */     Render var11 = null;
/*     */ 
/*     */     
/*     */     try {
/* 383 */       var11 = getEntityRenderObject(p_147939_1_);
/*     */       
/* 385 */       if (var11 != null && this.renderEngine != null) {
/*     */ 
/*     */         
/*     */         try {
/* 389 */           if (var11 instanceof RendererLivingEntity)
/*     */           {
/* 391 */             ((RendererLivingEntity)var11).func_177086_a(this.field_178639_r);
/*     */           }
/*     */           
/* 394 */           var11.doRender(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
/*     */         }
/* 396 */         catch (Throwable var18) {
/*     */           
/* 398 */           throw new ReportedException(CrashReport.makeCrashReport(var18, "Rendering entity in world"));
/*     */         } 
/*     */ 
/*     */         
/*     */         try {
/* 403 */           if (!this.field_178639_r)
/*     */           {
/* 405 */             var11.doRenderShadowAndFire(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
/*     */           }
/*     */         }
/* 408 */         catch (Throwable var17) {
/*     */           
/* 410 */           throw new ReportedException(CrashReport.makeCrashReport(var17, "Post-rendering entity in world"));
/*     */         } 
/*     */         
/* 413 */         if (this.debugBoundingBox && !p_147939_1_.isInvisible() && !p_147939_10_) {
/*     */           try
/*     */           {
/*     */             
/* 417 */             renderDebugBoundingBox(p_147939_1_, p_147939_2_, p_147939_4_, p_147939_6_, p_147939_8_, p_147939_9_);
/*     */           }
/* 419 */           catch (Throwable var16)
/*     */           {
/* 421 */             throw new ReportedException(CrashReport.makeCrashReport(var16, "Rendering entity hitbox in world"));
/*     */           }
/*     */         
/*     */         }
/* 425 */       } else if (this.renderEngine != null) {
/*     */         
/* 427 */         return false;
/*     */       } 
/*     */       
/* 430 */       return true;
/*     */     }
/* 432 */     catch (Throwable var19) {
/*     */       
/* 434 */       CrashReport var13 = CrashReport.makeCrashReport(var19, "Rendering entity in world");
/* 435 */       CrashReportCategory var14 = var13.makeCategory("Entity being rendered");
/* 436 */       p_147939_1_.addEntityCrashInfo(var14);
/* 437 */       CrashReportCategory var15 = var13.makeCategory("Renderer details");
/* 438 */       var15.addCrashSection("Assigned renderer", var11);
/* 439 */       var15.addCrashSection("Location", CrashReportCategory.getCoordinateInfo(p_147939_2_, p_147939_4_, p_147939_6_));
/* 440 */       var15.addCrashSection("Rotation", Float.valueOf(p_147939_8_));
/* 441 */       var15.addCrashSection("Delta", Float.valueOf(p_147939_9_));
/* 442 */       throw new ReportedException(var13);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderDebugBoundingBox(Entity p_85094_1_, double p_85094_2_, double p_85094_4_, double p_85094_6_, float p_85094_8_, float p_85094_9_) {
/* 451 */     GlStateManager.depthMask(false);
/* 452 */     GlStateManager.func_179090_x();
/* 453 */     GlStateManager.disableLighting();
/* 454 */     GlStateManager.disableCull();
/* 455 */     GlStateManager.disableBlend();
/* 456 */     float var10 = p_85094_1_.width / 2.0F;
/* 457 */     AxisAlignedBB var11 = p_85094_1_.getEntityBoundingBox();
/* 458 */     AxisAlignedBB var12 = new AxisAlignedBB(var11.minX - p_85094_1_.posX + p_85094_2_, var11.minY - p_85094_1_.posY + p_85094_4_, var11.minZ - p_85094_1_.posZ + p_85094_6_, var11.maxX - p_85094_1_.posX + p_85094_2_, var11.maxY - p_85094_1_.posY + p_85094_4_, var11.maxZ - p_85094_1_.posZ + p_85094_6_);
/* 459 */     RenderGlobal.drawOutlinedBoundingBox(var12, 16777215);
/*     */     
/* 461 */     if (p_85094_1_ instanceof EntityLivingBase) {
/*     */       
/* 463 */       float var16 = 0.01F;
/* 464 */       RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(p_85094_2_ - var10, p_85094_4_ + p_85094_1_.getEyeHeight() - 0.009999999776482582D, p_85094_6_ - var10, p_85094_2_ + var10, p_85094_4_ + p_85094_1_.getEyeHeight() + 0.009999999776482582D, p_85094_6_ + var10), 16711680);
/*     */     } 
/*     */     
/* 467 */     Tessellator var161 = Tessellator.getInstance();
/* 468 */     WorldRenderer var14 = var161.getWorldRenderer();
/* 469 */     Vec3 var15 = p_85094_1_.getLook(p_85094_9_);
/* 470 */     var14.startDrawing(3);
/* 471 */     var14.func_178991_c(255);
/* 472 */     var14.addVertex(p_85094_2_, p_85094_4_ + p_85094_1_.getEyeHeight(), p_85094_6_);
/* 473 */     var14.addVertex(p_85094_2_ + var15.xCoord * 2.0D, p_85094_4_ + p_85094_1_.getEyeHeight() + var15.yCoord * 2.0D, p_85094_6_ + var15.zCoord * 2.0D);
/* 474 */     var161.draw();
/* 475 */     GlStateManager.func_179098_w();
/* 476 */     GlStateManager.enableLighting();
/* 477 */     GlStateManager.enableCull();
/* 478 */     GlStateManager.disableBlend();
/* 479 */     GlStateManager.depthMask(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(World worldIn) {
/* 487 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDistanceToCamera(double p_78714_1_, double p_78714_3_, double p_78714_5_) {
/* 492 */     double var7 = p_78714_1_ - this.viewerPosX;
/* 493 */     double var9 = p_78714_3_ - this.viewerPosY;
/* 494 */     double var11 = p_78714_5_ - this.viewerPosZ;
/* 495 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderer getFontRenderer() {
/* 503 */     return this.textRenderer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178632_c(boolean p_178632_1_) {
/* 508 */     this.field_178639_r = p_178632_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map getEntityRenderMap() {
/* 513 */     return this.entityRenderMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntityRenderMap(Map entityRenderMap) {
/* 518 */     this.entityRenderMap = entityRenderMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, RenderPlayer> getSkinMap() {
/* 523 */     return Collections.unmodifiableMap(this.field_178636_l);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */