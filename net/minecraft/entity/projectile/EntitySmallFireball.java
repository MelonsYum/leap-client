/*    */ package net.minecraft.entity.projectile;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntitySmallFireball
/*    */   extends EntityFireball
/*    */ {
/*    */   private static final String __OBFID = "CL_00001721";
/*    */   
/*    */   public EntitySmallFireball(World worldIn) {
/* 17 */     super(worldIn);
/* 18 */     setSize(0.3125F, 0.3125F);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySmallFireball(World worldIn, EntityLivingBase p_i1771_2_, double p_i1771_3_, double p_i1771_5_, double p_i1771_7_) {
/* 23 */     super(worldIn, p_i1771_2_, p_i1771_3_, p_i1771_5_, p_i1771_7_);
/* 24 */     setSize(0.3125F, 0.3125F);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySmallFireball(World worldIn, double p_i1772_2_, double p_i1772_4_, double p_i1772_6_, double p_i1772_8_, double p_i1772_10_, double p_i1772_12_) {
/* 29 */     super(worldIn, p_i1772_2_, p_i1772_4_, p_i1772_6_, p_i1772_8_, p_i1772_10_, p_i1772_12_);
/* 30 */     setSize(0.3125F, 0.3125F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onImpact(MovingObjectPosition p_70227_1_) {
/* 38 */     if (!this.worldObj.isRemote) {
/*    */ 
/*    */ 
/*    */       
/* 42 */       if (p_70227_1_.entityHit != null) {
/*    */         
/* 44 */         boolean var2 = p_70227_1_.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, (Entity)this.shootingEntity), 5.0F);
/*    */         
/* 46 */         if (var2)
/*    */         {
/* 48 */           func_174815_a(this.shootingEntity, p_70227_1_.entityHit);
/*    */           
/* 50 */           if (!p_70227_1_.entityHit.isImmuneToFire())
/*    */           {
/* 52 */             p_70227_1_.entityHit.setFire(5);
/*    */           }
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 58 */         boolean var2 = true;
/*    */         
/* 60 */         if (this.shootingEntity != null && this.shootingEntity instanceof net.minecraft.entity.EntityLiving)
/*    */         {
/* 62 */           var2 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
/*    */         }
/*    */         
/* 65 */         if (var2) {
/*    */           
/* 67 */           BlockPos var3 = p_70227_1_.func_178782_a().offset(p_70227_1_.field_178784_b);
/*    */           
/* 69 */           if (this.worldObj.isAirBlock(var3))
/*    */           {
/* 71 */             this.worldObj.setBlockState(var3, Blocks.fire.getDefaultState());
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 76 */       setDead();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBeCollidedWith() {
/* 85 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 93 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntitySmallFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */