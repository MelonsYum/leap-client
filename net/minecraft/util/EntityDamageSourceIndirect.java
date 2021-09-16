/*    */ package net.minecraft.util;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class EntityDamageSourceIndirect
/*    */   extends EntityDamageSource
/*    */ {
/*    */   private Entity indirectEntity;
/*    */   private static final String __OBFID = "CL_00001523";
/*    */   
/*    */   public EntityDamageSourceIndirect(String p_i1568_1_, Entity p_i1568_2_, Entity p_i1568_3_) {
/* 14 */     super(p_i1568_1_, p_i1568_2_);
/* 15 */     this.indirectEntity = p_i1568_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getSourceOfDamage() {
/* 20 */     return this.damageSourceEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getEntity() {
/* 25 */     return this.indirectEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IChatComponent getDeathMessage(EntityLivingBase p_151519_1_) {
/* 33 */     IChatComponent var2 = (this.indirectEntity == null) ? this.damageSourceEntity.getDisplayName() : this.indirectEntity.getDisplayName();
/* 34 */     ItemStack var3 = (this.indirectEntity instanceof EntityLivingBase) ? ((EntityLivingBase)this.indirectEntity).getHeldItem() : null;
/* 35 */     String var4 = "death.attack." + this.damageType;
/* 36 */     String var5 = String.valueOf(var4) + ".item";
/* 37 */     return (var3 != null && var3.hasDisplayName() && StatCollector.canTranslate(var5)) ? new ChatComponentTranslation(var5, new Object[] { p_151519_1_.getDisplayName(), var2, var3.getChatComponent() }) : new ChatComponentTranslation(var4, new Object[] { p_151519_1_.getDisplayName(), var2 });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EntityDamageSourceIndirect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */