/*    */ package net.minecraft.util;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class EntityDamageSource
/*    */   extends DamageSource
/*    */ {
/*    */   protected Entity damageSourceEntity;
/*    */   private boolean field_180140_r = false;
/*    */   private static final String __OBFID = "CL_00001522";
/*    */   
/*    */   public EntityDamageSource(String p_i1567_1_, Entity p_i1567_2_) {
/* 16 */     super(p_i1567_1_);
/* 17 */     this.damageSourceEntity = p_i1567_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityDamageSource func_180138_v() {
/* 22 */     this.field_180140_r = true;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_180139_w() {
/* 28 */     return this.field_180140_r;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getEntity() {
/* 33 */     return this.damageSourceEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChatComponent getDeathMessage(EntityLivingBase p_151519_1_) {
/* 41 */     ItemStack var2 = (this.damageSourceEntity instanceof EntityLivingBase) ? ((EntityLivingBase)this.damageSourceEntity).getHeldItem() : null;
/* 42 */     String var3 = "death.attack." + this.damageType;
/* 43 */     String var4 = String.valueOf(var3) + ".item";
/* 44 */     return (var2 != null && var2.hasDisplayName() && StatCollector.canTranslate(var4)) ? new ChatComponentTranslation(var4, new Object[] { p_151519_1_.getDisplayName(), this.damageSourceEntity.getDisplayName(), var2.getChatComponent() }) : new ChatComponentTranslation(var3, new Object[] { p_151519_1_.getDisplayName(), this.damageSourceEntity.getDisplayName() });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isDifficultyScaled() {
/* 52 */     return (this.damageSourceEntity != null && this.damageSourceEntity instanceof EntityLivingBase && !(this.damageSourceEntity instanceof net.minecraft.entity.player.EntityPlayer));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EntityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */