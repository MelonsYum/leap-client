/*     */ package net.minecraft.util;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.entity.projectile.EntityFireball;
/*     */ import net.minecraft.world.Explosion;
/*     */ 
/*     */ public class DamageSource
/*     */ {
/*  12 */   public static DamageSource inFire = (new DamageSource("inFire")).setFireDamage();
/*  13 */   public static DamageSource field_180137_b = new DamageSource("lightningBolt");
/*  14 */   public static DamageSource onFire = (new DamageSource("onFire")).setDamageBypassesArmor().setFireDamage();
/*  15 */   public static DamageSource lava = (new DamageSource("lava")).setFireDamage();
/*  16 */   public static DamageSource inWall = (new DamageSource("inWall")).setDamageBypassesArmor();
/*  17 */   public static DamageSource drown = (new DamageSource("drown")).setDamageBypassesArmor();
/*  18 */   public static DamageSource starve = (new DamageSource("starve")).setDamageBypassesArmor().setDamageIsAbsolute();
/*  19 */   public static DamageSource cactus = new DamageSource("cactus");
/*  20 */   public static DamageSource fall = (new DamageSource("fall")).setDamageBypassesArmor();
/*  21 */   public static DamageSource outOfWorld = (new DamageSource("outOfWorld")).setDamageBypassesArmor().setDamageAllowedInCreativeMode();
/*  22 */   public static DamageSource generic = (new DamageSource("generic")).setDamageBypassesArmor();
/*  23 */   public static DamageSource magic = (new DamageSource("magic")).setDamageBypassesArmor().setMagicDamage();
/*  24 */   public static DamageSource wither = (new DamageSource("wither")).setDamageBypassesArmor();
/*  25 */   public static DamageSource anvil = new DamageSource("anvil");
/*  26 */   public static DamageSource fallingBlock = new DamageSource("fallingBlock");
/*     */ 
/*     */   
/*     */   private boolean isUnblockable;
/*     */ 
/*     */   
/*     */   private boolean isDamageAllowedInCreativeMode;
/*     */   
/*     */   private boolean damageIsAbsolute;
/*     */   
/*  36 */   private float hungerDamage = 0.3F;
/*     */ 
/*     */   
/*     */   private boolean fireDamage;
/*     */ 
/*     */   
/*     */   private boolean projectile;
/*     */ 
/*     */   
/*     */   private boolean difficultyScaled;
/*     */   
/*     */   private boolean magicDamage;
/*     */   
/*     */   private boolean explosion;
/*     */   
/*     */   public String damageType;
/*     */   
/*     */   private static final String __OBFID = "CL_00001521";
/*     */ 
/*     */   
/*     */   public static DamageSource causeMobDamage(EntityLivingBase p_76358_0_) {
/*  57 */     return new EntityDamageSource("mob", (Entity)p_76358_0_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causePlayerDamage(EntityPlayer p_76365_0_) {
/*  65 */     return new EntityDamageSource("player", (Entity)p_76365_0_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeArrowDamage(EntityArrow p_76353_0_, Entity p_76353_1_) {
/*  73 */     return (new EntityDamageSourceIndirect("arrow", (Entity)p_76353_0_, p_76353_1_)).setProjectile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeFireballDamage(EntityFireball p_76362_0_, Entity p_76362_1_) {
/*  81 */     return (p_76362_1_ == null) ? (new EntityDamageSourceIndirect("onFire", (Entity)p_76362_0_, (Entity)p_76362_0_)).setFireDamage().setProjectile() : (new EntityDamageSourceIndirect("fireball", (Entity)p_76362_0_, p_76362_1_)).setFireDamage().setProjectile();
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource causeThrownDamage(Entity p_76356_0_, Entity p_76356_1_) {
/*  86 */     return (new EntityDamageSourceIndirect("thrown", p_76356_0_, p_76356_1_)).setProjectile();
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource causeIndirectMagicDamage(Entity p_76354_0_, Entity p_76354_1_) {
/*  91 */     return (new EntityDamageSourceIndirect("indirectMagic", p_76354_0_, p_76354_1_)).setDamageBypassesArmor().setMagicDamage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DamageSource causeThornsDamage(Entity p_92087_0_) {
/*  99 */     return (new EntityDamageSource("thorns", p_92087_0_)).func_180138_v().setMagicDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource setExplosionSource(Explosion p_94539_0_) {
/* 104 */     return (p_94539_0_ != null && p_94539_0_.getExplosivePlacedBy() != null) ? (new EntityDamageSource("explosion.player", (Entity)p_94539_0_.getExplosivePlacedBy())).setDifficultyScaled().setExplosion() : (new DamageSource("explosion")).setDifficultyScaled().setExplosion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isProjectile() {
/* 112 */     return this.projectile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource setProjectile() {
/* 120 */     this.projectile = true;
/* 121 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExplosion() {
/* 126 */     return this.explosion;
/*     */   }
/*     */ 
/*     */   
/*     */   public DamageSource setExplosion() {
/* 131 */     this.explosion = true;
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUnblockable() {
/* 137 */     return this.isUnblockable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHungerDamage() {
/* 145 */     return this.hungerDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canHarmInCreative() {
/* 150 */     return this.isDamageAllowedInCreativeMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamageAbsolute() {
/* 158 */     return this.damageIsAbsolute;
/*     */   }
/*     */ 
/*     */   
/*     */   protected DamageSource(String p_i1566_1_) {
/* 163 */     this.damageType = p_i1566_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getSourceOfDamage() {
/* 168 */     return getEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getEntity() {
/* 173 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected DamageSource setDamageBypassesArmor() {
/* 178 */     this.isUnblockable = true;
/* 179 */     this.hungerDamage = 0.0F;
/* 180 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected DamageSource setDamageAllowedInCreativeMode() {
/* 185 */     this.isDamageAllowedInCreativeMode = true;
/* 186 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DamageSource setDamageIsAbsolute() {
/* 195 */     this.damageIsAbsolute = true;
/* 196 */     this.hungerDamage = 0.0F;
/* 197 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DamageSource setFireDamage() {
/* 205 */     this.fireDamage = true;
/* 206 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent getDeathMessage(EntityLivingBase p_151519_1_) {
/* 214 */     EntityLivingBase var2 = p_151519_1_.func_94060_bK();
/* 215 */     String var3 = "death.attack." + this.damageType;
/* 216 */     String var4 = String.valueOf(var3) + ".player";
/* 217 */     return (var2 != null && StatCollector.canTranslate(var4)) ? new ChatComponentTranslation(var4, new Object[] { p_151519_1_.getDisplayName(), var2.getDisplayName() }) : new ChatComponentTranslation(var3, new Object[] { p_151519_1_.getDisplayName() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFireDamage() {
/* 225 */     return this.fireDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDamageType() {
/* 233 */     return this.damageType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource setDifficultyScaled() {
/* 241 */     this.difficultyScaled = true;
/* 242 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDifficultyScaled() {
/* 250 */     return this.difficultyScaled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMagicDamage() {
/* 258 */     return this.magicDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DamageSource setMagicDamage() {
/* 266 */     this.magicDamage = true;
/* 267 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180136_u() {
/* 272 */     Entity var1 = getEntity();
/* 273 */     return (var1 instanceof EntityPlayer && ((EntityPlayer)var1).capabilities.isCreativeMode);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\DamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */