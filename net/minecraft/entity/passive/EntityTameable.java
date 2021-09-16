/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.ai.EntityAISit;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.server.management.PreYggdrasilConverter;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityTameable
/*     */   extends EntityAnimal implements IEntityOwnable {
/*  18 */   protected EntityAISit aiSit = new EntityAISit(this);
/*     */   
/*     */   private static final String __OBFID = "CL_00001561";
/*     */   
/*     */   public EntityTameable(World worldIn) {
/*  23 */     super(worldIn);
/*  24 */     func_175544_ck();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  29 */     super.entityInit();
/*  30 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*  31 */     this.dataWatcher.addObject(17, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  39 */     super.writeEntityToNBT(tagCompound);
/*     */     
/*  41 */     if (func_152113_b() == null) {
/*     */       
/*  43 */       tagCompound.setString("OwnerUUID", "");
/*     */     }
/*     */     else {
/*     */       
/*  47 */       tagCompound.setString("OwnerUUID", func_152113_b());
/*     */     } 
/*     */     
/*  50 */     tagCompound.setBoolean("Sitting", isSitting());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  58 */     super.readEntityFromNBT(tagCompund);
/*  59 */     String var2 = "";
/*     */     
/*  61 */     if (tagCompund.hasKey("OwnerUUID", 8)) {
/*     */       
/*  63 */       var2 = tagCompund.getString("OwnerUUID");
/*     */     }
/*     */     else {
/*     */       
/*  67 */       String var3 = tagCompund.getString("Owner");
/*  68 */       var2 = PreYggdrasilConverter.func_152719_a(var3);
/*     */     } 
/*     */     
/*  71 */     if (var2.length() > 0) {
/*     */       
/*  73 */       func_152115_b(var2);
/*  74 */       setTamed(true);
/*     */     } 
/*     */     
/*  77 */     this.aiSit.setSitting(tagCompund.getBoolean("Sitting"));
/*  78 */     setSitting(tagCompund.getBoolean("Sitting"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playTameEffect(boolean p_70908_1_) {
/*  86 */     EnumParticleTypes var2 = EnumParticleTypes.HEART;
/*     */     
/*  88 */     if (!p_70908_1_)
/*     */     {
/*  90 */       var2 = EnumParticleTypes.SMOKE_NORMAL;
/*     */     }
/*     */     
/*  93 */     for (int var3 = 0; var3 < 7; var3++) {
/*     */       
/*  95 */       double var4 = this.rand.nextGaussian() * 0.02D;
/*  96 */       double var6 = this.rand.nextGaussian() * 0.02D;
/*  97 */       double var8 = this.rand.nextGaussian() * 0.02D;
/*  98 */       this.worldObj.spawnParticle(var2, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var4, var6, var8, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 104 */     if (p_70103_1_ == 7) {
/*     */       
/* 106 */       playTameEffect(true);
/*     */     }
/* 108 */     else if (p_70103_1_ == 6) {
/*     */       
/* 110 */       playTameEffect(false);
/*     */     }
/*     */     else {
/*     */       
/* 114 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 120 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean p_70903_1_) {
/* 125 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 127 */     if (p_70903_1_) {
/*     */       
/* 129 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x4)));
/*     */     }
/*     */     else {
/*     */       
/* 133 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFB)));
/*     */     } 
/*     */     
/* 136 */     func_175544_ck();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175544_ck() {}
/*     */   
/*     */   public boolean isSitting() {
/* 143 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSitting(boolean p_70904_1_) {
/* 148 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 150 */     if (p_70904_1_) {
/*     */       
/* 152 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 156 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_152113_b() {
/* 162 */     return this.dataWatcher.getWatchableObjectString(17);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152115_b(String p_152115_1_) {
/* 167 */     this.dataWatcher.updateObject(17, p_152115_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase func_180492_cm() {
/*     */     try {
/* 174 */       UUID var1 = UUID.fromString(func_152113_b());
/* 175 */       return (var1 == null) ? null : (EntityLivingBase)this.worldObj.getPlayerEntityByUUID(var1);
/*     */     }
/* 177 */     catch (IllegalArgumentException var2) {
/*     */       
/* 179 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152114_e(EntityLivingBase p_152114_1_) {
/* 185 */     return (p_152114_1_ == func_180492_cm());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAISit getAISit() {
/* 193 */     return this.aiSit;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Team getTeam() {
/* 203 */     if (isTamed()) {
/*     */       
/* 205 */       EntityLivingBase var1 = func_180492_cm();
/*     */       
/* 207 */       if (var1 != null)
/*     */       {
/* 209 */         return var1.getTeam();
/*     */       }
/*     */     } 
/*     */     
/* 213 */     return super.getTeam();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnSameTeam(EntityLivingBase p_142014_1_) {
/* 218 */     if (isTamed()) {
/*     */       
/* 220 */       EntityLivingBase var2 = func_180492_cm();
/*     */       
/* 222 */       if (p_142014_1_ == var2)
/*     */       {
/* 224 */         return true;
/*     */       }
/*     */       
/* 227 */       if (var2 != null)
/*     */       {
/* 229 */         return var2.isOnSameTeam(p_142014_1_);
/*     */       }
/*     */     } 
/*     */     
/* 233 */     return super.isOnSameTeam(p_142014_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource cause) {
/* 241 */     if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("showDeathMessages") && hasCustomName() && func_180492_cm() instanceof EntityPlayerMP)
/*     */     {
/* 243 */       ((EntityPlayerMP)func_180492_cm()).addChatMessage(getCombatTracker().func_151521_b());
/*     */     }
/*     */     
/* 246 */     super.onDeath(cause);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getOwner() {
/* 251 */     return (Entity)func_180492_cm();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityTameable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */