/*    */ package net.minecraft.entity.item;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class EntityMinecartEmpty
/*    */   extends EntityMinecart
/*    */ {
/*    */   private static final String __OBFID = "CL_00001677";
/*    */   
/*    */   public EntityMinecartEmpty(World worldIn) {
/* 13 */     super(worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartEmpty(World worldIn, double p_i1723_2_, double p_i1723_4_, double p_i1723_6_) {
/* 18 */     super(worldIn, p_i1723_2_, p_i1723_4_, p_i1723_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean interactFirst(EntityPlayer playerIn) {
/* 26 */     if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != playerIn)
/*    */     {
/* 28 */       return true;
/*    */     }
/* 30 */     if (this.riddenByEntity != null && this.riddenByEntity != playerIn)
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 36 */     if (!this.worldObj.isRemote)
/*    */     {
/* 38 */       playerIn.mountEntity(this);
/*    */     }
/*    */     
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onActivatorRailPass(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {
/* 50 */     if (p_96095_4_) {
/*    */       
/* 52 */       if (this.riddenByEntity != null)
/*    */       {
/* 54 */         this.riddenByEntity.mountEntity(null);
/*    */       }
/*    */       
/* 57 */       if (getRollingAmplitude() == 0) {
/*    */         
/* 59 */         setRollingDirection(-getRollingDirection());
/* 60 */         setRollingAmplitude(10);
/* 61 */         setDamage(50.0F);
/* 62 */         setBeenAttacked();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecart.EnumMinecartType func_180456_s() {
/* 69 */     return EntityMinecart.EnumMinecartType.RIDEABLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecartEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */