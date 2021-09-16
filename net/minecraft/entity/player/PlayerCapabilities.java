/*    */ package net.minecraft.entity.player;
/*    */ 
/*    */ import net.minecraft.nbt.NBTBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerCapabilities
/*    */ {
/*    */   public boolean disableDamage;
/*    */   public boolean isFlying;
/*    */   public boolean allowFlying;
/*    */   public boolean isCreativeMode;
/*    */   public boolean allowEdit = true;
/* 23 */   private float flySpeed = 0.05F;
/* 24 */   private float walkSpeed = 0.1F;
/*    */   
/*    */   private static final String __OBFID = "CL_00001708";
/*    */   
/*    */   public void writeCapabilitiesToNBT(NBTTagCompound p_75091_1_) {
/* 29 */     NBTTagCompound var2 = new NBTTagCompound();
/* 30 */     var2.setBoolean("invulnerable", this.disableDamage);
/* 31 */     var2.setBoolean("flying", this.isFlying);
/* 32 */     var2.setBoolean("mayfly", this.allowFlying);
/* 33 */     var2.setBoolean("instabuild", this.isCreativeMode);
/* 34 */     var2.setBoolean("mayBuild", this.allowEdit);
/* 35 */     var2.setFloat("flySpeed", this.flySpeed);
/* 36 */     var2.setFloat("walkSpeed", this.walkSpeed);
/* 37 */     p_75091_1_.setTag("abilities", (NBTBase)var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void readCapabilitiesFromNBT(NBTTagCompound p_75095_1_) {
/* 42 */     if (p_75095_1_.hasKey("abilities", 10)) {
/*    */       
/* 44 */       NBTTagCompound var2 = p_75095_1_.getCompoundTag("abilities");
/* 45 */       this.disableDamage = var2.getBoolean("invulnerable");
/* 46 */       this.isFlying = var2.getBoolean("flying");
/* 47 */       this.allowFlying = var2.getBoolean("mayfly");
/* 48 */       this.isCreativeMode = var2.getBoolean("instabuild");
/*    */       
/* 50 */       if (var2.hasKey("flySpeed", 99)) {
/*    */         
/* 52 */         this.flySpeed = var2.getFloat("flySpeed");
/* 53 */         this.walkSpeed = var2.getFloat("walkSpeed");
/*    */       } 
/*    */       
/* 56 */       if (var2.hasKey("mayBuild", 1))
/*    */       {
/* 58 */         this.allowEdit = var2.getBoolean("mayBuild");
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float getFlySpeed() {
/* 65 */     return this.flySpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFlySpeed(float p_75092_1_) {
/* 70 */     this.flySpeed = p_75092_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getWalkSpeed() {
/* 75 */     return this.walkSpeed;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPlayerWalkSpeed(float p_82877_1_) {
/* 80 */     this.walkSpeed = p_82877_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\player\PlayerCapabilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */