/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombatEntry
/*    */ {
/*    */   private final DamageSource damageSrc;
/*    */   private final int field_94567_b;
/*    */   private final float field_94568_c;
/*    */   private final float field_94565_d;
/*    */   private final String field_94566_e;
/*    */   private final float field_94564_f;
/*    */   private static final String __OBFID = "CL_00001519";
/*    */   
/*    */   public CombatEntry(DamageSource p_i1564_1_, int p_i1564_2_, float p_i1564_3_, float p_i1564_4_, String p_i1564_5_, float p_i1564_6_) {
/* 17 */     this.damageSrc = p_i1564_1_;
/* 18 */     this.field_94567_b = p_i1564_2_;
/* 19 */     this.field_94568_c = p_i1564_4_;
/* 20 */     this.field_94565_d = p_i1564_3_;
/* 21 */     this.field_94566_e = p_i1564_5_;
/* 22 */     this.field_94564_f = p_i1564_6_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DamageSource getDamageSrc() {
/* 30 */     return this.damageSrc;
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_94563_c() {
/* 35 */     return this.field_94568_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_94559_f() {
/* 40 */     return this.damageSrc.getEntity() instanceof net.minecraft.entity.EntityLivingBase;
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_94562_g() {
/* 45 */     return this.field_94566_e;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_151522_h() {
/* 50 */     return (getDamageSrc().getEntity() == null) ? null : getDamageSrc().getEntity().getDisplayName();
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_94561_i() {
/* 55 */     return (this.damageSrc == DamageSource.outOfWorld) ? Float.MAX_VALUE : this.field_94564_f;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\CombatEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */