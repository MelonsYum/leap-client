/*     */ package net.minecraft.client.particle;
/*     */ 
/*     */ import net.minecraft.block.BlockLiquid;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDropParticleFX
/*     */   extends EntityFX
/*     */ {
/*     */   private Material materialType;
/*     */   private int bobTimer;
/*     */   private static final String __OBFID = "CL_00000901";
/*     */   
/*     */   protected EntityDropParticleFX(World worldIn, double p_i1203_2_, double p_i1203_4_, double p_i1203_6_, Material p_i1203_8_) {
/*  22 */     super(worldIn, p_i1203_2_, p_i1203_4_, p_i1203_6_, 0.0D, 0.0D, 0.0D);
/*  23 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*     */     
/*  25 */     if (p_i1203_8_ == Material.water) {
/*     */       
/*  27 */       this.particleRed = 0.0F;
/*  28 */       this.particleGreen = 0.0F;
/*  29 */       this.particleBlue = 1.0F;
/*     */     }
/*     */     else {
/*     */       
/*  33 */       this.particleRed = 1.0F;
/*  34 */       this.particleGreen = 0.0F;
/*  35 */       this.particleBlue = 0.0F;
/*     */     } 
/*     */     
/*  38 */     setParticleTextureIndex(113);
/*  39 */     setSize(0.01F, 0.01F);
/*  40 */     this.particleGravity = 0.06F;
/*  41 */     this.materialType = p_i1203_8_;
/*  42 */     this.bobTimer = 40;
/*  43 */     this.particleMaxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
/*  44 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/*  49 */     return (this.materialType == Material.water) ? super.getBrightnessForRender(p_70070_1_) : 257;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/*  57 */     return (this.materialType == Material.water) ? super.getBrightness(p_70013_1_) : 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  65 */     this.prevPosX = this.posX;
/*  66 */     this.prevPosY = this.posY;
/*  67 */     this.prevPosZ = this.posZ;
/*     */     
/*  69 */     if (this.materialType == Material.water) {
/*     */       
/*  71 */       this.particleRed = 0.2F;
/*  72 */       this.particleGreen = 0.3F;
/*  73 */       this.particleBlue = 1.0F;
/*     */     }
/*     */     else {
/*     */       
/*  77 */       this.particleRed = 1.0F;
/*  78 */       this.particleGreen = 16.0F / (40 - this.bobTimer + 16);
/*  79 */       this.particleBlue = 4.0F / (40 - this.bobTimer + 8);
/*     */     } 
/*     */     
/*  82 */     this.motionY -= this.particleGravity;
/*     */     
/*  84 */     if (this.bobTimer-- > 0) {
/*     */       
/*  86 */       this.motionX *= 0.02D;
/*  87 */       this.motionY *= 0.02D;
/*  88 */       this.motionZ *= 0.02D;
/*  89 */       setParticleTextureIndex(113);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       setParticleTextureIndex(112);
/*     */     } 
/*     */     
/*  96 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  97 */     this.motionX *= 0.9800000190734863D;
/*  98 */     this.motionY *= 0.9800000190734863D;
/*  99 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/* 101 */     if (this.particleMaxAge-- <= 0)
/*     */     {
/* 103 */       setDead();
/*     */     }
/*     */     
/* 106 */     if (this.onGround) {
/*     */       
/* 108 */       if (this.materialType == Material.water) {
/*     */         
/* 110 */         setDead();
/* 111 */         this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */       else {
/*     */         
/* 115 */         setParticleTextureIndex(114);
/*     */       } 
/*     */       
/* 118 */       this.motionX *= 0.699999988079071D;
/* 119 */       this.motionZ *= 0.699999988079071D;
/*     */     } 
/*     */     
/* 122 */     BlockPos var1 = new BlockPos(this);
/* 123 */     IBlockState var2 = this.worldObj.getBlockState(var1);
/* 124 */     Material var3 = var2.getBlock().getMaterial();
/*     */     
/* 126 */     if (var3.isLiquid() || var3.isSolid()) {
/*     */       
/* 128 */       double var4 = 0.0D;
/*     */       
/* 130 */       if (var2.getBlock() instanceof BlockLiquid)
/*     */       {
/* 132 */         var4 = BlockLiquid.getLiquidHeightPercent(((Integer)var2.getValue((IProperty)BlockLiquid.LEVEL)).intValue());
/*     */       }
/*     */       
/* 135 */       double var6 = (MathHelper.floor_double(this.posY) + 1) - var4;
/*     */       
/* 137 */       if (this.posY < var6)
/*     */       {
/* 139 */         setDead();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class LavaFactory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002607";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 150 */       return new EntityDropParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, Material.lava);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WaterFactory
/*     */     implements IParticleFactory
/*     */   {
/*     */     private static final String __OBFID = "CL_00002606";
/*     */     
/*     */     public EntityFX func_178902_a(int p_178902_1_, World worldIn, double p_178902_3_, double p_178902_5_, double p_178902_7_, double p_178902_9_, double p_178902_11_, double p_178902_13_, int... p_178902_15_) {
/* 160 */       return new EntityDropParticleFX(worldIn, p_178902_3_, p_178902_5_, p_178902_7_, Material.water);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\particle\EntityDropParticleFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */