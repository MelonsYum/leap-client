/*    */ package net.minecraft.entity.monster;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.IEntityLivingData;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.world.DifficultyInstance;
/*    */ import net.minecraft.world.EnumDifficulty;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityCaveSpider
/*    */   extends EntitySpider
/*    */ {
/*    */   private static final String __OBFID = "CL_00001683";
/*    */   
/*    */   public EntityCaveSpider(World worldIn) {
/* 19 */     super(worldIn);
/* 20 */     setSize(0.7F, 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 25 */     super.applyEntityAttributes();
/* 26 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 31 */     if (super.attackEntityAsMob(p_70652_1_)) {
/*    */       
/* 33 */       if (p_70652_1_ instanceof EntityLivingBase) {
/*    */         
/* 35 */         byte var2 = 0;
/*    */         
/* 37 */         if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
/*    */           
/* 39 */           var2 = 7;
/*    */         }
/* 41 */         else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
/*    */           
/* 43 */           var2 = 15;
/*    */         } 
/*    */         
/* 46 */         if (var2 > 0)
/*    */         {
/* 48 */           ((EntityLivingBase)p_70652_1_).addPotionEffect(new PotionEffect(Potion.poison.id, var2 * 20, 0));
/*    */         }
/*    */       } 
/*    */       
/* 52 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 56 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 62 */     return p_180482_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getEyeHeight() {
/* 67 */     return 0.45F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */