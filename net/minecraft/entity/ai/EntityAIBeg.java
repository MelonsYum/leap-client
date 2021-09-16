/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.EntityWolf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityAIBeg
/*    */   extends EntityAIBase {
/*    */   private EntityWolf theWolf;
/*    */   private EntityPlayer thePlayer;
/*    */   private World worldObject;
/*    */   private float minPlayerDistance;
/*    */   private int field_75384_e;
/*    */   private static final String __OBFID = "CL_00001576";
/*    */   
/*    */   public EntityAIBeg(EntityWolf p_i1617_1_, float p_i1617_2_) {
/* 20 */     this.theWolf = p_i1617_1_;
/* 21 */     this.worldObject = p_i1617_1_.worldObj;
/* 22 */     this.minPlayerDistance = p_i1617_2_;
/* 23 */     setMutexBits(2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 31 */     this.thePlayer = this.worldObject.getClosestPlayerToEntity((Entity)this.theWolf, this.minPlayerDistance);
/* 32 */     return (this.thePlayer == null) ? false : hasPlayerGotBoneInHand(this.thePlayer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 40 */     return !this.thePlayer.isEntityAlive() ? false : ((this.theWolf.getDistanceSqToEntity((Entity)this.thePlayer) > (this.minPlayerDistance * this.minPlayerDistance)) ? false : ((this.field_75384_e > 0 && hasPlayerGotBoneInHand(this.thePlayer))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 48 */     this.theWolf.func_70918_i(true);
/* 49 */     this.field_75384_e = 40 + this.theWolf.getRNG().nextInt(40);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {
/* 57 */     this.theWolf.func_70918_i(false);
/* 58 */     this.thePlayer = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 66 */     this.theWolf.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, this.theWolf.getVerticalFaceSpeed());
/* 67 */     this.field_75384_e--;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean hasPlayerGotBoneInHand(EntityPlayer p_75382_1_) {
/* 75 */     ItemStack var2 = p_75382_1_.inventory.getCurrentItem();
/* 76 */     return (var2 == null) ? false : ((!this.theWolf.isTamed() && var2.getItem() == Items.bone) ? true : this.theWolf.isBreedingItem(var2));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIBeg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */