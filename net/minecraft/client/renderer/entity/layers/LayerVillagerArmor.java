/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelZombieVillager;
/*    */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*    */ 
/*    */ public class LayerVillagerArmor
/*    */   extends LayerBipedArmor {
/*    */   private static final String __OBFID = "CL_00002409";
/*    */   
/*    */   public LayerVillagerArmor(RendererLivingEntity p_i46108_1_) {
/* 12 */     super(p_i46108_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_177177_a() {
/* 17 */     this.field_177189_c = (ModelBase)new ModelZombieVillager(0.5F, 0.0F, true);
/* 18 */     this.field_177186_d = (ModelBase)new ModelZombieVillager(1.0F, 0.0F, true);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerVillagerArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */