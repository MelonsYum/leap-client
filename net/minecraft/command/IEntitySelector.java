/*    */ package net.minecraft.command;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class IEntitySelector
/*    */ {
/* 14 */   public static final Predicate selectAnything = new Predicate()
/*    */     {
/*    */       private static final String __OBFID = "CL_00001541";
/*    */       
/*    */       public boolean func_180131_a(Entity p_180131_1_) {
/* 19 */         return p_180131_1_.isEntityAlive();
/*    */       }
/*    */       
/*    */       public boolean apply(Object p_apply_1_) {
/* 23 */         return func_180131_a((Entity)p_apply_1_);
/*    */       }
/*    */     };
/* 26 */   public static final Predicate field_152785_b = new Predicate()
/*    */     {
/*    */       private static final String __OBFID = "CL_00001542";
/*    */       
/*    */       public boolean func_180130_a(Entity p_180130_1_) {
/* 31 */         return (p_180130_1_.isEntityAlive() && p_180130_1_.riddenByEntity == null && p_180130_1_.ridingEntity == null);
/*    */       }
/*    */       
/*    */       public boolean apply(Object p_apply_1_) {
/* 35 */         return func_180130_a((Entity)p_apply_1_);
/*    */       }
/*    */     };
/* 38 */   public static final Predicate selectInventories = new Predicate()
/*    */     {
/*    */       private static final String __OBFID = "CL_00001867";
/*    */       
/*    */       public boolean func_180102_a(Entity p_180102_1_) {
/* 43 */         return (p_180102_1_ instanceof net.minecraft.inventory.IInventory && p_180102_1_.isEntityAlive());
/*    */       }
/*    */       
/*    */       public boolean apply(Object p_apply_1_) {
/* 47 */         return func_180102_a((Entity)p_apply_1_);
/*    */       }
/*    */     };
/* 50 */   public static final Predicate field_180132_d = new Predicate()
/*    */     {
/*    */       private static final String __OBFID = "CL_00002256";
/*    */       
/*    */       public boolean func_180103_a(Entity p_180103_1_) {
/* 55 */         return !(p_180103_1_ instanceof EntityPlayer && ((EntityPlayer)p_180103_1_).func_175149_v());
/*    */       }
/*    */       
/*    */       public boolean apply(Object p_apply_1_) {
/* 59 */         return func_180103_a((Entity)p_apply_1_);
/*    */       }
/*    */     };
/*    */   private static final String __OBFID = "CL_00002257";
/*    */   
/*    */   public static class ArmoredMob
/*    */     implements Predicate
/*    */   {
/*    */     private final ItemStack field_96567_c;
/*    */     private static final String __OBFID = "CL_00001543";
/*    */     
/*    */     public ArmoredMob(ItemStack p_i1584_1_) {
/* 71 */       this.field_96567_c = p_i1584_1_;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean func_180100_a(Entity p_180100_1_) {
/* 76 */       if (!p_180100_1_.isEntityAlive())
/*    */       {
/* 78 */         return false;
/*    */       }
/* 80 */       if (!(p_180100_1_ instanceof EntityLivingBase))
/*    */       {
/* 82 */         return false;
/*    */       }
/*    */ 
/*    */       
/* 86 */       EntityLivingBase var2 = (EntityLivingBase)p_180100_1_;
/* 87 */       return (var2.getEquipmentInSlot(EntityLiving.getArmorPosition(this.field_96567_c)) != null) ? false : ((var2 instanceof EntityLiving) ? ((EntityLiving)var2).canPickUpLoot() : ((var2 instanceof net.minecraft.entity.item.EntityArmorStand) ? true : (var2 instanceof EntityPlayer)));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean apply(Object p_apply_1_) {
/* 93 */       return func_180100_a((Entity)p_apply_1_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\IEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */