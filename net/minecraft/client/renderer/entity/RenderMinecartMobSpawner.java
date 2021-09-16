/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
/*    */ import net.minecraft.entity.ai.EntityMinecartMobSpawner;
/*    */ import net.minecraft.entity.item.EntityMinecart;
/*    */ import net.minecraft.init.Blocks;
/*    */ 
/*    */ public class RenderMinecartMobSpawner
/*    */   extends RenderMinecart
/*    */ {
/*    */   private static final String __OBFID = "CL_00001014";
/*    */   
/*    */   public RenderMinecartMobSpawner(RenderManager p_i46154_1_) {
/* 15 */     super(p_i46154_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_177081_a(EntityMinecartMobSpawner p_177081_1_, float p_177081_2_, IBlockState p_177081_3_) {
/* 20 */     super.func_180560_a((EntityMinecart)p_177081_1_, p_177081_2_, p_177081_3_);
/*    */     
/* 22 */     if (p_177081_3_.getBlock() == Blocks.mob_spawner)
/*    */     {
/* 24 */       TileEntityMobSpawnerRenderer.func_147517_a(p_177081_1_.func_98039_d(), p_177081_1_.posX, p_177081_1_.posY, p_177081_1_.posZ, p_177081_2_);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180560_a(EntityMinecart p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
/* 30 */     func_177081_a((EntityMinecartMobSpawner)p_180560_1_, p_180560_2_, p_180560_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderMinecartMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */