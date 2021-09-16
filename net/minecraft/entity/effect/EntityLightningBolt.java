/*     */ package net.minecraft.entity.effect;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityLightningBolt
/*     */   extends EntityWeatherEffect
/*     */ {
/*     */   private int lightningState;
/*     */   public long boltVertex;
/*     */   private int boltLivingTime;
/*     */   private static final String __OBFID = "CL_00001666";
/*     */   
/*     */   public EntityLightningBolt(World worldIn, double p_i1703_2_, double p_i1703_4_, double p_i1703_6_) {
/*  33 */     super(worldIn);
/*  34 */     setLocationAndAngles(p_i1703_2_, p_i1703_4_, p_i1703_6_, 0.0F, 0.0F);
/*  35 */     this.lightningState = 2;
/*  36 */     this.boltVertex = this.rand.nextLong();
/*  37 */     this.boltLivingTime = this.rand.nextInt(3) + 1;
/*     */     
/*  39 */     if (!worldIn.isRemote && worldIn.getGameRules().getGameRuleBooleanValue("doFireTick") && (worldIn.getDifficulty() == EnumDifficulty.NORMAL || worldIn.getDifficulty() == EnumDifficulty.HARD) && worldIn.isAreaLoaded(new BlockPos(this), 10)) {
/*     */       
/*  41 */       BlockPos var8 = new BlockPos(this);
/*     */       
/*  43 */       if (worldIn.getBlockState(var8).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(worldIn, var8))
/*     */       {
/*  45 */         worldIn.setBlockState(var8, Blocks.fire.getDefaultState());
/*     */       }
/*     */       
/*  48 */       for (int var9 = 0; var9 < 4; var9++) {
/*     */         
/*  50 */         BlockPos var10 = var8.add(this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1, this.rand.nextInt(3) - 1);
/*     */         
/*  52 */         if (worldIn.getBlockState(var10).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(worldIn, var10))
/*     */         {
/*  54 */           worldIn.setBlockState(var10, Blocks.fire.getDefaultState());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  65 */     super.onUpdate();
/*     */     
/*  67 */     if (this.lightningState == 2) {
/*     */       
/*  69 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "ambient.weather.thunder", 10000.0F, 0.8F + this.rand.nextFloat() * 0.2F);
/*  70 */       this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
/*     */     } 
/*     */     
/*  73 */     this.lightningState--;
/*     */     
/*  75 */     if (this.lightningState < 0)
/*     */     {
/*  77 */       if (this.boltLivingTime == 0) {
/*     */         
/*  79 */         setDead();
/*     */       }
/*  81 */       else if (this.lightningState < -this.rand.nextInt(10)) {
/*     */         
/*  83 */         this.boltLivingTime--;
/*  84 */         this.lightningState = 1;
/*  85 */         this.boltVertex = this.rand.nextLong();
/*  86 */         BlockPos var1 = new BlockPos(this);
/*     */         
/*  88 */         if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("doFireTick") && this.worldObj.isAreaLoaded(var1, 10) && this.worldObj.getBlockState(var1).getBlock().getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(this.worldObj, var1))
/*     */         {
/*  90 */           this.worldObj.setBlockState(var1, Blocks.fire.getDefaultState());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  95 */     if (this.lightningState >= 0)
/*     */     {
/*  97 */       if (this.worldObj.isRemote) {
/*     */         
/*  99 */         this.worldObj.setLastLightningBolt(2);
/*     */       }
/*     */       else {
/*     */         
/* 103 */         double var6 = 3.0D;
/* 104 */         List<Entity> var3 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, new AxisAlignedBB(this.posX - var6, this.posY - var6, this.posZ - var6, this.posX + var6, this.posY + 6.0D + var6, this.posZ + var6));
/*     */         
/* 106 */         for (int var4 = 0; var4 < var3.size(); var4++) {
/*     */           
/* 108 */           Entity var5 = var3.get(var4);
/* 109 */           var5.onStruckByLightning(this);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void entityInit() {}
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\effect\EntityLightningBolt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */