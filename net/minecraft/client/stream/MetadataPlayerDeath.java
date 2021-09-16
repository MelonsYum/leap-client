/*    */ package net.minecraft.client.stream;
/*    */ 
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ 
/*    */ public class MetadataPlayerDeath
/*    */   extends Metadata
/*    */ {
/*    */   private static final String __OBFID = "CL_00002376";
/*    */   
/*    */   public MetadataPlayerDeath(EntityLivingBase p_i46066_1_, EntityLivingBase p_i46066_2_) {
/* 11 */     super("player_death");
/*    */     
/* 13 */     if (p_i46066_1_ != null)
/*    */     {
/* 15 */       func_152808_a("player", p_i46066_1_.getName());
/*    */     }
/*    */     
/* 18 */     if (p_i46066_2_ != null)
/*    */     {
/* 20 */       func_152808_a("killer", p_i46066_2_.getName());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\MetadataPlayerDeath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */