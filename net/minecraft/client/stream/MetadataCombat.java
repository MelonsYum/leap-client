/*    */ package net.minecraft.client.stream;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class MetadataCombat
/*    */   extends Metadata
/*    */ {
/*    */   private static final String __OBFID = "CL_00002377";
/*    */   
/*    */   public MetadataCombat(EntityLivingBase p_i46067_1_, EntityLivingBase p_i46067_2_) {
/* 11 */     super("player_combat");
/* 12 */     func_152808_a("player", p_i46067_1_.getName());
/*    */     
/* 14 */     if (p_i46067_2_ != null)
/*    */     {
/* 16 */       func_152808_a("primary_opponent", p_i46067_2_.getName());
/*    */     }
/*    */     
/* 19 */     if (p_i46067_2_ != null) {
/*    */       
/* 21 */       func_152807_a("Combat between " + p_i46067_1_.getName() + " and " + p_i46067_2_.getName());
/*    */     }
/*    */     else {
/*    */       
/* 25 */       func_152807_a("Combat between " + p_i46067_1_.getName() + " and others");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\MetadataCombat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */