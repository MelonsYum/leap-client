/*     */ package net.minecraft.world;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentProtection;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityTNTPrimed;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Explosion
/*     */ {
/*     */   private final boolean isFlaming;
/*     */   private final boolean isSmoking;
/*     */   private final Random explosionRNG;
/*     */   private final World worldObj;
/*     */   private final double explosionX;
/*     */   private final double explosionY;
/*     */   private final double explosionZ;
/*     */   private final Entity exploder;
/*     */   private final float explosionSize;
/*     */   private final List affectedBlockPositions;
/*     */   private final Map field_77288_k;
/*     */   private static final String __OBFID = "CL_00000134";
/*     */   
/*     */   public Explosion(World worldIn, Entity p_i45752_2_, double p_i45752_3_, double p_i45752_5_, double p_i45752_7_, float p_i45752_9_, List p_i45752_10_) {
/*  49 */     this(worldIn, p_i45752_2_, p_i45752_3_, p_i45752_5_, p_i45752_7_, p_i45752_9_, false, true, p_i45752_10_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Explosion(World worldIn, Entity p_i45753_2_, double p_i45753_3_, double p_i45753_5_, double p_i45753_7_, float p_i45753_9_, boolean p_i45753_10_, boolean p_i45753_11_, List p_i45753_12_) {
/*  54 */     this(worldIn, p_i45753_2_, p_i45753_3_, p_i45753_5_, p_i45753_7_, p_i45753_9_, p_i45753_10_, p_i45753_11_);
/*  55 */     this.affectedBlockPositions.addAll(p_i45753_12_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Explosion(World worldIn, Entity p_i45754_2_, double p_i45754_3_, double p_i45754_5_, double p_i45754_7_, float p_i45754_9_, boolean p_i45754_10_, boolean p_i45754_11_) {
/*  60 */     this.explosionRNG = new Random();
/*  61 */     this.affectedBlockPositions = Lists.newArrayList();
/*  62 */     this.field_77288_k = Maps.newHashMap();
/*  63 */     this.worldObj = worldIn;
/*  64 */     this.exploder = p_i45754_2_;
/*  65 */     this.explosionSize = p_i45754_9_;
/*  66 */     this.explosionX = p_i45754_3_;
/*  67 */     this.explosionY = p_i45754_5_;
/*  68 */     this.explosionZ = p_i45754_7_;
/*  69 */     this.isFlaming = p_i45754_10_;
/*  70 */     this.isSmoking = p_i45754_11_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExplosionA() {
/*  78 */     HashSet<BlockPos> var1 = Sets.newHashSet();
/*  79 */     boolean var2 = true;
/*     */ 
/*     */ 
/*     */     
/*  83 */     for (int var3 = 0; var3 < 16; var3++) {
/*     */       
/*  85 */       for (int i = 0; i < 16; i++) {
/*     */         
/*  87 */         for (int j = 0; j < 16; j++) {
/*     */           
/*  89 */           if (var3 == 0 || var3 == 15 || i == 0 || i == 15 || j == 0 || j == 15) {
/*     */             
/*  91 */             double var6 = (var3 / 15.0F * 2.0F - 1.0F);
/*  92 */             double var8 = (i / 15.0F * 2.0F - 1.0F);
/*  93 */             double var10 = (j / 15.0F * 2.0F - 1.0F);
/*  94 */             double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
/*  95 */             var6 /= var12;
/*  96 */             var8 /= var12;
/*  97 */             var10 /= var12;
/*  98 */             float var14 = this.explosionSize * (0.7F + this.worldObj.rand.nextFloat() * 0.6F);
/*  99 */             double var15 = this.explosionX;
/* 100 */             double var17 = this.explosionY;
/* 101 */             double var19 = this.explosionZ;
/*     */             
/* 103 */             for (float var21 = 0.3F; var14 > 0.0F; var14 -= 0.22500001F) {
/*     */               
/* 105 */               BlockPos var22 = new BlockPos(var15, var17, var19);
/* 106 */               IBlockState var23 = this.worldObj.getBlockState(var22);
/*     */               
/* 108 */               if (var23.getBlock().getMaterial() != Material.air) {
/*     */                 
/* 110 */                 float var24 = (this.exploder != null) ? this.exploder.getExplosionResistance(this, this.worldObj, var22, var23) : var23.getBlock().getExplosionResistance(null);
/* 111 */                 var14 -= (var24 + 0.3F) * 0.3F;
/*     */               } 
/*     */               
/* 114 */               if (var14 > 0.0F && (this.exploder == null || this.exploder.func_174816_a(this, this.worldObj, var22, var23, var14)))
/*     */               {
/* 116 */                 var1.add(var22);
/*     */               }
/*     */               
/* 119 */               var15 += var6 * 0.30000001192092896D;
/* 120 */               var17 += var8 * 0.30000001192092896D;
/* 121 */               var19 += var10 * 0.30000001192092896D;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     this.affectedBlockPositions.addAll(var1);
/* 129 */     float var30 = this.explosionSize * 2.0F;
/* 130 */     int var4 = MathHelper.floor_double(this.explosionX - var30 - 1.0D);
/* 131 */     int var5 = MathHelper.floor_double(this.explosionX + var30 + 1.0D);
/* 132 */     int var31 = MathHelper.floor_double(this.explosionY - var30 - 1.0D);
/* 133 */     int var7 = MathHelper.floor_double(this.explosionY + var30 + 1.0D);
/* 134 */     int var32 = MathHelper.floor_double(this.explosionZ - var30 - 1.0D);
/* 135 */     int var9 = MathHelper.floor_double(this.explosionZ + var30 + 1.0D);
/* 136 */     List<Entity> var33 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, new AxisAlignedBB(var4, var31, var32, var5, var7, var9));
/* 137 */     Vec3 var11 = new Vec3(this.explosionX, this.explosionY, this.explosionZ);
/*     */     
/* 139 */     for (int var34 = 0; var34 < var33.size(); var34++) {
/*     */       
/* 141 */       Entity var13 = var33.get(var34);
/*     */       
/* 143 */       if (!var13.func_180427_aV()) {
/*     */         
/* 145 */         double var35 = var13.getDistance(this.explosionX, this.explosionY, this.explosionZ) / var30;
/*     */         
/* 147 */         if (var35 <= 1.0D) {
/*     */           
/* 149 */           double var16 = var13.posX - this.explosionX;
/* 150 */           double var18 = var13.posY + var13.getEyeHeight() - this.explosionY;
/* 151 */           double var20 = var13.posZ - this.explosionZ;
/* 152 */           double var36 = MathHelper.sqrt_double(var16 * var16 + var18 * var18 + var20 * var20);
/*     */           
/* 154 */           if (var36 != 0.0D) {
/*     */             
/* 156 */             var16 /= var36;
/* 157 */             var18 /= var36;
/* 158 */             var20 /= var36;
/* 159 */             double var37 = this.worldObj.getBlockDensity(var11, var13.getEntityBoundingBox());
/* 160 */             double var26 = (1.0D - var35) * var37;
/* 161 */             var13.attackEntityFrom(DamageSource.setExplosionSource(this), (int)((var26 * var26 + var26) / 2.0D * 8.0D * var30 + 1.0D));
/* 162 */             double var28 = EnchantmentProtection.func_92092_a(var13, var26);
/* 163 */             var13.motionX += var16 * var28;
/* 164 */             var13.motionY += var18 * var28;
/* 165 */             var13.motionZ += var20 * var28;
/*     */             
/* 167 */             if (var13 instanceof EntityPlayer)
/*     */             {
/* 169 */               this.field_77288_k.put((EntityPlayer)var13, new Vec3(var16 * var26, var18 * var26, var20 * var26));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doExplosionB(boolean p_77279_1_) {
/* 182 */     this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
/*     */     
/* 184 */     if (this.explosionSize >= 2.0F && this.isSmoking) {
/*     */       
/* 186 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */     else {
/*     */       
/* 190 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.explosionX, this.explosionY, this.explosionZ, 1.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 196 */     if (this.isSmoking) {
/*     */       
/* 198 */       Iterator<BlockPos> var2 = this.affectedBlockPositions.iterator();
/*     */       
/* 200 */       while (var2.hasNext()) {
/*     */         
/* 202 */         BlockPos var3 = var2.next();
/* 203 */         Block var4 = this.worldObj.getBlockState(var3).getBlock();
/*     */         
/* 205 */         if (p_77279_1_) {
/*     */           
/* 207 */           double var5 = (var3.getX() + this.worldObj.rand.nextFloat());
/* 208 */           double var7 = (var3.getY() + this.worldObj.rand.nextFloat());
/* 209 */           double var9 = (var3.getZ() + this.worldObj.rand.nextFloat());
/* 210 */           double var11 = var5 - this.explosionX;
/* 211 */           double var13 = var7 - this.explosionY;
/* 212 */           double var15 = var9 - this.explosionZ;
/* 213 */           double var17 = MathHelper.sqrt_double(var11 * var11 + var13 * var13 + var15 * var15);
/* 214 */           var11 /= var17;
/* 215 */           var13 /= var17;
/* 216 */           var15 /= var17;
/* 217 */           double var19 = 0.5D / (var17 / this.explosionSize + 0.1D);
/* 218 */           var19 *= (this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3F);
/* 219 */           var11 *= var19;
/* 220 */           var13 *= var19;
/* 221 */           var15 *= var19;
/* 222 */           this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (var5 + this.explosionX * 1.0D) / 2.0D, (var7 + this.explosionY * 1.0D) / 2.0D, (var9 + this.explosionZ * 1.0D) / 2.0D, var11, var13, var15, new int[0]);
/* 223 */           this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var5, var7, var9, var11, var13, var15, new int[0]);
/*     */         } 
/*     */         
/* 226 */         if (var4.getMaterial() != Material.air) {
/*     */           
/* 228 */           if (var4.canDropFromExplosion(this))
/*     */           {
/* 230 */             var4.dropBlockAsItemWithChance(this.worldObj, var3, this.worldObj.getBlockState(var3), 1.0F / this.explosionSize, 0);
/*     */           }
/*     */           
/* 233 */           this.worldObj.setBlockState(var3, Blocks.air.getDefaultState(), 3);
/* 234 */           var4.onBlockDestroyedByExplosion(this.worldObj, var3, this);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 239 */     if (this.isFlaming) {
/*     */       
/* 241 */       Iterator<BlockPos> var2 = this.affectedBlockPositions.iterator();
/*     */       
/* 243 */       while (var2.hasNext()) {
/*     */         
/* 245 */         BlockPos var3 = var2.next();
/*     */         
/* 247 */         if (this.worldObj.getBlockState(var3).getBlock().getMaterial() == Material.air && this.worldObj.getBlockState(var3.offsetDown()).getBlock().isFullBlock() && this.explosionRNG.nextInt(3) == 0)
/*     */         {
/* 249 */           this.worldObj.setBlockState(var3, Blocks.fire.getDefaultState());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_77277_b() {
/* 257 */     return this.field_77288_k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase getExplosivePlacedBy() {
/* 265 */     return (this.exploder == null) ? null : ((this.exploder instanceof EntityTNTPrimed) ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : ((this.exploder instanceof EntityLivingBase) ? (EntityLivingBase)this.exploder : null));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180342_d() {
/* 270 */     this.affectedBlockPositions.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_180343_e() {
/* 275 */     return this.affectedBlockPositions;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\Explosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */