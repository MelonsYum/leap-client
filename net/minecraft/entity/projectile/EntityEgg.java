/*    */ package net.minecraft.entity.projectile;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityChicken;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityEgg
/*    */   extends EntityThrowable {
/*    */   private static final String __OBFID = "CL_00001724";
/*    */   
/*    */   public EntityEgg(World worldIn) {
/* 18 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEgg(World worldIn, EntityLivingBase p_i1780_2_) {
/* 23 */     super(worldIn, p_i1780_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEgg(World worldIn, double p_i1781_2_, double p_i1781_4_, double p_i1781_6_) {
/* 28 */     super(worldIn, p_i1781_2_, p_i1781_4_, p_i1781_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(MovingObjectPosition p_70184_1_) {
/* 36 */     if (p_70184_1_.entityHit != null)
/*    */     {
/* 38 */       p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, (Entity)getThrower()), 0.0F);
/*    */     }
/*    */     
/* 41 */     if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0) {
/*    */       
/* 43 */       byte var2 = 1;
/*    */       
/* 45 */       if (this.rand.nextInt(32) == 0)
/*    */       {
/* 47 */         var2 = 4;
/*    */       }
/*    */       
/* 50 */       for (int var3 = 0; var3 < var2; var3++) {
/*    */         
/* 52 */         EntityChicken var4 = new EntityChicken(this.worldObj);
/* 53 */         var4.setGrowingAge(-24000);
/* 54 */         var4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/* 55 */         this.worldObj.spawnEntityInWorld((Entity)var4);
/*    */       } 
/*    */     } 
/*    */     
/* 59 */     double var5 = 0.08D;
/*    */     
/* 61 */     for (int var6 = 0; var6 < 8; var6++) {
/*    */       
/* 63 */       this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, (this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, new int[] { Item.getIdFromItem(Items.egg) });
/*    */     } 
/*    */     
/* 66 */     if (!this.worldObj.isRemote)
/*    */     {
/* 68 */       setDead();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */