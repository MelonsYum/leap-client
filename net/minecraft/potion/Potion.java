/*     */ package net.minecraft.potion;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Potion
/*     */ {
/*  23 */   public static final Potion[] potionTypes = new Potion[32];
/*  24 */   private static final Map field_180150_I = Maps.newHashMap();
/*  25 */   public static final Potion field_180151_b = null;
/*  26 */   public static final Potion moveSpeed = (new Potion(1, new ResourceLocation("speed"), false, 8171462)).setPotionName("potion.moveSpeed").setIconIndex(0, 0).registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
/*  27 */   public static final Potion moveSlowdown = (new Potion(2, new ResourceLocation("slowness"), true, 5926017)).setPotionName("potion.moveSlowdown").setIconIndex(1, 0).registerPotionAttributeModifier(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
/*  28 */   public static final Potion digSpeed = (new Potion(3, new ResourceLocation("haste"), false, 14270531)).setPotionName("potion.digSpeed").setIconIndex(2, 0).setEffectiveness(1.5D);
/*  29 */   public static final Potion digSlowdown = (new Potion(4, new ResourceLocation("mining_fatigue"), true, 4866583)).setPotionName("potion.digSlowDown").setIconIndex(3, 0);
/*  30 */   public static final Potion damageBoost = (new PotionAttackDamage(5, new ResourceLocation("strength"), false, 9643043)).setPotionName("potion.damageBoost").setIconIndex(4, 0).registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5D, 2);
/*  31 */   public static final Potion heal = (new PotionHealth(6, new ResourceLocation("instant_health"), false, 16262179)).setPotionName("potion.heal");
/*  32 */   public static final Potion harm = (new PotionHealth(7, new ResourceLocation("instant_damage"), true, 4393481)).setPotionName("potion.harm");
/*  33 */   public static final Potion jump = (new Potion(8, new ResourceLocation("jump_boost"), false, 2293580)).setPotionName("potion.jump").setIconIndex(2, 1);
/*  34 */   public static final Potion confusion = (new Potion(9, new ResourceLocation("nausea"), true, 5578058)).setPotionName("potion.confusion").setIconIndex(3, 1).setEffectiveness(0.25D);
/*     */ 
/*     */   
/*  37 */   public static final Potion regeneration = (new Potion(10, new ResourceLocation("regeneration"), false, 13458603)).setPotionName("potion.regeneration").setIconIndex(7, 0).setEffectiveness(0.25D);
/*  38 */   public static final Potion resistance = (new Potion(11, new ResourceLocation("resistance"), false, 10044730)).setPotionName("potion.resistance").setIconIndex(6, 1);
/*     */ 
/*     */   
/*  41 */   public static final Potion fireResistance = (new Potion(12, new ResourceLocation("fire_resistance"), false, 14981690)).setPotionName("potion.fireResistance").setIconIndex(7, 1);
/*     */ 
/*     */   
/*  44 */   public static final Potion waterBreathing = (new Potion(13, new ResourceLocation("water_breathing"), false, 3035801)).setPotionName("potion.waterBreathing").setIconIndex(0, 2);
/*     */ 
/*     */   
/*  47 */   public static final Potion invisibility = (new Potion(14, new ResourceLocation("invisibility"), false, 8356754)).setPotionName("potion.invisibility").setIconIndex(0, 1);
/*     */ 
/*     */   
/*  50 */   public static final Potion blindness = (new Potion(15, new ResourceLocation("blindness"), true, 2039587)).setPotionName("potion.blindness").setIconIndex(5, 1).setEffectiveness(0.25D);
/*     */ 
/*     */   
/*  53 */   public static final Potion nightVision = (new Potion(16, new ResourceLocation("night_vision"), false, 2039713)).setPotionName("potion.nightVision").setIconIndex(4, 1);
/*     */ 
/*     */   
/*  56 */   public static final Potion hunger = (new Potion(17, new ResourceLocation("hunger"), true, 5797459)).setPotionName("potion.hunger").setIconIndex(1, 1);
/*     */ 
/*     */   
/*  59 */   public static final Potion weakness = (new PotionAttackDamage(18, new ResourceLocation("weakness"), true, 4738376)).setPotionName("potion.weakness").setIconIndex(5, 0).registerPotionAttributeModifier(SharedMonsterAttributes.attackDamage, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
/*     */ 
/*     */   
/*  62 */   public static final Potion poison = (new Potion(19, new ResourceLocation("poison"), true, 5149489)).setPotionName("potion.poison").setIconIndex(6, 0).setEffectiveness(0.25D);
/*     */ 
/*     */   
/*  65 */   public static final Potion wither = (new Potion(20, new ResourceLocation("wither"), true, 3484199)).setPotionName("potion.wither").setIconIndex(1, 2).setEffectiveness(0.25D);
/*  66 */   public static final Potion field_180152_w = (new PotionHealthBoost(21, new ResourceLocation("health_boost"), false, 16284963)).setPotionName("potion.healthBoost").setIconIndex(2, 2).registerPotionAttributeModifier(SharedMonsterAttributes.maxHealth, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
/*     */ 
/*     */   
/*  69 */   public static final Potion absorption = (new PotionAbsoption(22, new ResourceLocation("absorption"), false, 2445989)).setPotionName("potion.absorption").setIconIndex(2, 2);
/*     */ 
/*     */   
/*  72 */   public static final Potion saturation = (new PotionHealth(23, new ResourceLocation("saturation"), false, 16262179)).setPotionName("potion.saturation");
/*  73 */   public static final Potion field_180153_z = null;
/*  74 */   public static final Potion field_180147_A = null;
/*  75 */   public static final Potion field_180148_B = null;
/*  76 */   public static final Potion field_180149_C = null;
/*  77 */   public static final Potion field_180143_D = null;
/*  78 */   public static final Potion field_180144_E = null;
/*  79 */   public static final Potion field_180145_F = null;
/*  80 */   public static final Potion field_180146_G = null;
/*     */ 
/*     */   
/*     */   public final int id;
/*     */ 
/*     */   
/*  86 */   private final Map attributeModifierMap = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean isBadEffect;
/*     */ 
/*     */ 
/*     */   
/*     */   private final int liquidColor;
/*     */ 
/*     */   
/*  97 */   private String name = "";
/*     */ 
/*     */   
/* 100 */   private int statusIconIndex = -1;
/*     */   
/*     */   private double effectiveness;
/*     */   private boolean usable;
/*     */   private static final String __OBFID = "CL_00001528";
/*     */   
/*     */   protected Potion(int p_i45897_1_, ResourceLocation p_i45897_2_, boolean p_i45897_3_, int p_i45897_4_) {
/* 107 */     this.id = p_i45897_1_;
/* 108 */     potionTypes[p_i45897_1_] = this;
/* 109 */     field_180150_I.put(p_i45897_2_, this);
/* 110 */     this.isBadEffect = p_i45897_3_;
/*     */     
/* 112 */     if (p_i45897_3_) {
/*     */       
/* 114 */       this.effectiveness = 0.5D;
/*     */     }
/*     */     else {
/*     */       
/* 118 */       this.effectiveness = 1.0D;
/*     */     } 
/*     */     
/* 121 */     this.liquidColor = p_i45897_4_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Potion func_180142_b(String p_180142_0_) {
/* 126 */     return (Potion)field_180150_I.get(new ResourceLocation(p_180142_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] func_180141_c() {
/* 131 */     String[] var0 = new String[field_180150_I.size()];
/* 132 */     int var1 = 0;
/*     */ 
/*     */     
/* 135 */     for (Iterator<ResourceLocation> var2 = field_180150_I.keySet().iterator(); var2.hasNext(); var0[var1++] = var3.toString())
/*     */     {
/* 137 */       ResourceLocation var3 = var2.next();
/*     */     }
/*     */     
/* 140 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Potion setIconIndex(int p_76399_1_, int p_76399_2_) {
/* 148 */     this.statusIconIndex = p_76399_1_ + p_76399_2_ * 8;
/* 149 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getId() {
/* 157 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performEffect(EntityLivingBase p_76394_1_, int p_76394_2_) {
/* 162 */     if (this.id == regeneration.id) {
/*     */       
/* 164 */       if (p_76394_1_.getHealth() < p_76394_1_.getMaxHealth())
/*     */       {
/* 166 */         p_76394_1_.heal(1.0F);
/*     */       }
/*     */     }
/* 169 */     else if (this.id == poison.id) {
/*     */       
/* 171 */       if (p_76394_1_.getHealth() > 1.0F)
/*     */       {
/* 173 */         p_76394_1_.attackEntityFrom(DamageSource.magic, 1.0F);
/*     */       }
/*     */     }
/* 176 */     else if (this.id == wither.id) {
/*     */       
/* 178 */       p_76394_1_.attackEntityFrom(DamageSource.wither, 1.0F);
/*     */     }
/* 180 */     else if (this.id == hunger.id && p_76394_1_ instanceof EntityPlayer) {
/*     */       
/* 182 */       ((EntityPlayer)p_76394_1_).addExhaustion(0.025F * (p_76394_2_ + 1));
/*     */     }
/* 184 */     else if (this.id == saturation.id && p_76394_1_ instanceof EntityPlayer) {
/*     */       
/* 186 */       if (!p_76394_1_.worldObj.isRemote)
/*     */       {
/* 188 */         ((EntityPlayer)p_76394_1_).getFoodStats().addStats(p_76394_2_ + 1, 1.0F);
/*     */       }
/*     */     }
/* 191 */     else if ((this.id != heal.id || p_76394_1_.isEntityUndead()) && (this.id != harm.id || !p_76394_1_.isEntityUndead())) {
/*     */       
/* 193 */       if ((this.id == harm.id && !p_76394_1_.isEntityUndead()) || (this.id == heal.id && p_76394_1_.isEntityUndead()))
/*     */       {
/* 195 */         p_76394_1_.attackEntityFrom(DamageSource.magic, (6 << p_76394_2_));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 200 */       p_76394_1_.heal(Math.max(4 << p_76394_2_, 0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180793_a(Entity p_180793_1_, Entity p_180793_2_, EntityLivingBase p_180793_3_, int p_180793_4_, double p_180793_5_) {
/* 208 */     if ((this.id != heal.id || p_180793_3_.isEntityUndead()) && (this.id != harm.id || !p_180793_3_.isEntityUndead())) {
/*     */       
/* 210 */       if ((this.id == harm.id && !p_180793_3_.isEntityUndead()) || (this.id == heal.id && p_180793_3_.isEntityUndead())) {
/*     */         
/* 212 */         int var7 = (int)(p_180793_5_ * (6 << p_180793_4_) + 0.5D);
/*     */         
/* 214 */         if (p_180793_1_ == null)
/*     */         {
/* 216 */           p_180793_3_.attackEntityFrom(DamageSource.magic, var7);
/*     */         }
/*     */         else
/*     */         {
/* 220 */           p_180793_3_.attackEntityFrom(DamageSource.causeIndirectMagicDamage(p_180793_1_, p_180793_2_), var7);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 226 */       int var7 = (int)(p_180793_5_ * (4 << p_180793_4_) + 0.5D);
/* 227 */       p_180793_3_.heal(var7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInstant() {
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReady(int p_76397_1_, int p_76397_2_) {
/* 246 */     if (this.id == regeneration.id) {
/*     */       
/* 248 */       int var3 = 50 >> p_76397_2_;
/* 249 */       return (var3 > 0) ? ((p_76397_1_ % var3 == 0)) : true;
/*     */     } 
/* 251 */     if (this.id == poison.id) {
/*     */       
/* 253 */       int var3 = 25 >> p_76397_2_;
/* 254 */       return (var3 > 0) ? ((p_76397_1_ % var3 == 0)) : true;
/*     */     } 
/* 256 */     if (this.id == wither.id) {
/*     */       
/* 258 */       int var3 = 40 >> p_76397_2_;
/* 259 */       return (var3 > 0) ? ((p_76397_1_ % var3 == 0)) : true;
/*     */     } 
/*     */ 
/*     */     
/* 263 */     return (this.id == hunger.id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Potion setPotionName(String p_76390_1_) {
/* 272 */     this.name = p_76390_1_;
/* 273 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 281 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasStatusIcon() {
/* 289 */     return (this.statusIconIndex >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStatusIconIndex() {
/* 297 */     return this.statusIconIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBadEffect() {
/* 305 */     return this.isBadEffect;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDurationString(PotionEffect p_76389_0_) {
/* 310 */     if (p_76389_0_.getIsPotionDurationMax())
/*     */     {
/* 312 */       return "**:**";
/*     */     }
/*     */ 
/*     */     
/* 316 */     int var1 = p_76389_0_.getDuration();
/* 317 */     return StringUtils.ticksToElapsedTime(var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Potion setEffectiveness(double p_76404_1_) {
/* 323 */     this.effectiveness = p_76404_1_;
/* 324 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEffectiveness() {
/* 329 */     return this.effectiveness;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUsable() {
/* 334 */     return this.usable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLiquidColor() {
/* 342 */     return this.liquidColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Potion registerPotionAttributeModifier(IAttribute p_111184_1_, String p_111184_2_, double p_111184_3_, int p_111184_5_) {
/* 350 */     AttributeModifier var6 = new AttributeModifier(UUID.fromString(p_111184_2_), getName(), p_111184_3_, p_111184_5_);
/* 351 */     this.attributeModifierMap.put(p_111184_1_, var6);
/* 352 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_111186_k() {
/* 357 */     return this.attributeModifierMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttributesModifiersFromEntity(EntityLivingBase p_111187_1_, BaseAttributeMap p_111187_2_, int p_111187_3_) {
/* 362 */     Iterator<Map.Entry> var4 = this.attributeModifierMap.entrySet().iterator();
/*     */     
/* 364 */     while (var4.hasNext()) {
/*     */       
/* 366 */       Map.Entry var5 = var4.next();
/* 367 */       IAttributeInstance var6 = p_111187_2_.getAttributeInstance((IAttribute)var5.getKey());
/*     */       
/* 369 */       if (var6 != null)
/*     */       {
/* 371 */         var6.removeModifier((AttributeModifier)var5.getValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyAttributesModifiersToEntity(EntityLivingBase p_111185_1_, BaseAttributeMap p_111185_2_, int p_111185_3_) {
/* 378 */     Iterator<Map.Entry> var4 = this.attributeModifierMap.entrySet().iterator();
/*     */     
/* 380 */     while (var4.hasNext()) {
/*     */       
/* 382 */       Map.Entry var5 = var4.next();
/* 383 */       IAttributeInstance var6 = p_111185_2_.getAttributeInstance((IAttribute)var5.getKey());
/*     */       
/* 385 */       if (var6 != null) {
/*     */         
/* 387 */         AttributeModifier var7 = (AttributeModifier)var5.getValue();
/* 388 */         var6.removeModifier(var7);
/* 389 */         var6.applyModifier(new AttributeModifier(var7.getID(), String.valueOf(getName()) + " " + p_111185_3_, func_111183_a(p_111185_3_, var7), var7.getOperation()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_111183_a(int p_111183_1_, AttributeModifier p_111183_2_) {
/* 396 */     return p_111183_2_.getAmount() * (p_111183_1_ + 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\potion\Potion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */