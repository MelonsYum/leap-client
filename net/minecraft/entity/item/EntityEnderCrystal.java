/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEnderCrystal
/*     */   extends Entity
/*     */ {
/*     */   public int innerRotation;
/*     */   public int health;
/*     */   private static final String __OBFID = "CL_00001658";
/*     */   
/*     */   public EntityEnderCrystal(World worldIn) {
/*  21 */     super(worldIn);
/*  22 */     this.preventEntitySpawning = true;
/*  23 */     setSize(2.0F, 2.0F);
/*  24 */     this.health = 5;
/*  25 */     this.innerRotation = this.rand.nextInt(100000);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityEnderCrystal(World worldIn, double p_i1699_2_, double p_i1699_4_, double p_i1699_6_) {
/*  30 */     this(worldIn);
/*  31 */     setPosition(p_i1699_2_, p_i1699_4_, p_i1699_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  45 */     this.dataWatcher.addObject(8, Integer.valueOf(this.health));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  53 */     this.prevPosX = this.posX;
/*  54 */     this.prevPosY = this.posY;
/*  55 */     this.prevPosZ = this.posZ;
/*  56 */     this.innerRotation++;
/*  57 */     this.dataWatcher.updateObject(8, Integer.valueOf(this.health));
/*  58 */     int var1 = MathHelper.floor_double(this.posX);
/*  59 */     int var2 = MathHelper.floor_double(this.posY);
/*  60 */     int var3 = MathHelper.floor_double(this.posZ);
/*     */     
/*  62 */     if (this.worldObj.provider instanceof net.minecraft.world.WorldProviderEnd && this.worldObj.getBlockState(new BlockPos(var1, var2, var3)).getBlock() != Blocks.fire)
/*     */     {
/*  64 */       this.worldObj.setBlockState(new BlockPos(var1, var2, var3), Blocks.fire.getDefaultState());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  91 */     if (func_180431_b(source))
/*     */     {
/*  93 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  97 */     if (!this.isDead && !this.worldObj.isRemote) {
/*     */       
/*  99 */       this.health = 0;
/*     */       
/* 101 */       if (this.health <= 0) {
/*     */         
/* 103 */         setDead();
/*     */         
/* 105 */         if (!this.worldObj.isRemote)
/*     */         {
/* 107 */           this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, 6.0F, true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */