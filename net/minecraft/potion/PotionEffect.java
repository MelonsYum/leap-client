/*     */ package net.minecraft.potion;
/*     */ 
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class PotionEffect
/*     */ {
/*  10 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private int potionID;
/*     */ 
/*     */   
/*     */   private int duration;
/*     */ 
/*     */   
/*     */   private int amplifier;
/*     */ 
/*     */   
/*     */   private boolean isSplashPotion;
/*     */   
/*     */   private boolean isAmbient;
/*     */   
/*     */   private boolean isPotionDurationMax;
/*     */   
/*     */   private boolean showParticles;
/*     */   
/*     */   private static final String __OBFID = "CL_00001529";
/*     */ 
/*     */   
/*     */   public PotionEffect(int id, int effectDuration) {
/*  34 */     this(id, effectDuration, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect(int id, int effectDuration, int effectAmplifier) {
/*  39 */     this(id, effectDuration, effectAmplifier, false, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect(int id, int effectDuration, int effectAmplifier, boolean ambient, boolean showParticles) {
/*  44 */     this.potionID = id;
/*  45 */     this.duration = effectDuration;
/*  46 */     this.amplifier = effectAmplifier;
/*  47 */     this.isAmbient = ambient;
/*  48 */     this.showParticles = showParticles;
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect(PotionEffect other) {
/*  53 */     this.potionID = other.potionID;
/*  54 */     this.duration = other.duration;
/*  55 */     this.amplifier = other.amplifier;
/*  56 */     this.isAmbient = other.isAmbient;
/*  57 */     this.showParticles = other.showParticles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void combine(PotionEffect other) {
/*  66 */     if (this.potionID != other.potionID)
/*     */     {
/*  68 */       LOGGER.warn("This method should only be called for matching effects!");
/*     */     }
/*     */     
/*  71 */     if (other.amplifier > this.amplifier) {
/*     */       
/*  73 */       this.amplifier = other.amplifier;
/*  74 */       this.duration = other.duration;
/*     */     }
/*  76 */     else if (other.amplifier == this.amplifier && this.duration < other.duration) {
/*     */       
/*  78 */       this.duration = other.duration;
/*     */     }
/*  80 */     else if (!other.isAmbient && this.isAmbient) {
/*     */       
/*  82 */       this.isAmbient = other.isAmbient;
/*     */     } 
/*     */     
/*  85 */     this.showParticles = other.showParticles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPotionID() {
/*  93 */     return this.potionID;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuration() {
/*  98 */     return this.duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAmplifier() {
/* 103 */     return this.amplifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSplashPotion(boolean splashPotion) {
/* 111 */     this.isSplashPotion = splashPotion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsAmbient() {
/* 119 */     return this.isAmbient;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180154_f() {
/* 124 */     return this.showParticles;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onUpdate(EntityLivingBase entityIn) {
/* 129 */     if (this.duration > 0) {
/*     */       
/* 131 */       if (Potion.potionTypes[this.potionID].isReady(this.duration, this.amplifier))
/*     */       {
/* 133 */         performEffect(entityIn);
/*     */       }
/*     */       
/* 136 */       deincrementDuration();
/*     */     } 
/*     */     
/* 139 */     return (this.duration > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private int deincrementDuration() {
/* 144 */     return --this.duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performEffect(EntityLivingBase entityIn) {
/* 149 */     if (this.duration > 0)
/*     */     {
/* 151 */       Potion.potionTypes[this.potionID].performEffect(entityIn, this.amplifier);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEffectName() {
/* 157 */     return Potion.potionTypes[this.potionID].getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 162 */     return this.potionID;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 167 */     String var1 = "";
/*     */     
/* 169 */     if (getAmplifier() > 0) {
/*     */       
/* 171 */       var1 = String.valueOf(getEffectName()) + " x " + (getAmplifier() + 1) + ", Duration: " + getDuration();
/*     */     }
/*     */     else {
/*     */       
/* 175 */       var1 = String.valueOf(getEffectName()) + ", Duration: " + getDuration();
/*     */     } 
/*     */     
/* 178 */     if (this.isSplashPotion)
/*     */     {
/* 180 */       var1 = String.valueOf(var1) + ", Splash: true";
/*     */     }
/*     */     
/* 183 */     if (!this.showParticles)
/*     */     {
/* 185 */       var1 = String.valueOf(var1) + ", Particles: false";
/*     */     }
/*     */     
/* 188 */     return Potion.potionTypes[this.potionID].isUsable() ? ("(" + var1 + ")") : var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/* 193 */     if (!(p_equals_1_ instanceof PotionEffect))
/*     */     {
/* 195 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 199 */     PotionEffect var2 = (PotionEffect)p_equals_1_;
/* 200 */     return (this.potionID == var2.potionID && this.amplifier == var2.amplifier && this.duration == var2.duration && this.isSplashPotion == var2.isSplashPotion && this.isAmbient == var2.isAmbient);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeCustomPotionEffectToNBT(NBTTagCompound nbt) {
/* 209 */     nbt.setByte("Id", (byte)getPotionID());
/* 210 */     nbt.setByte("Amplifier", (byte)getAmplifier());
/* 211 */     nbt.setInteger("Duration", getDuration());
/* 212 */     nbt.setBoolean("Ambient", getIsAmbient());
/* 213 */     nbt.setBoolean("ShowParticles", func_180154_f());
/* 214 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PotionEffect readCustomPotionEffectFromNBT(NBTTagCompound nbt) {
/* 222 */     byte var1 = nbt.getByte("Id");
/*     */     
/* 224 */     if (var1 >= 0 && var1 < Potion.potionTypes.length && Potion.potionTypes[var1] != null) {
/*     */       
/* 226 */       byte var2 = nbt.getByte("Amplifier");
/* 227 */       int var3 = nbt.getInteger("Duration");
/* 228 */       boolean var4 = nbt.getBoolean("Ambient");
/* 229 */       boolean var5 = true;
/*     */       
/* 231 */       if (nbt.hasKey("ShowParticles", 1))
/*     */       {
/* 233 */         var5 = nbt.getBoolean("ShowParticles");
/*     */       }
/*     */       
/* 236 */       return new PotionEffect(var1, var3, var2, var4, var5);
/*     */     } 
/*     */ 
/*     */     
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPotionDurationMax(boolean maxDuration) {
/* 249 */     this.isPotionDurationMax = maxDuration;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsPotionDurationMax() {
/* 254 */     return this.isPotionDurationMax;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\potion\PotionEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */