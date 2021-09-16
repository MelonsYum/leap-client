/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.BlockRendererDispatcher;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.item.EntityMinecart;
/*    */ import net.minecraft.entity.item.EntityMinecartTNT;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class RenderTntMinecart
/*    */   extends RenderMinecart
/*    */ {
/*    */   private static final String __OBFID = "CL_00001029";
/*    */   
/*    */   public RenderTntMinecart(RenderManager p_i46135_1_) {
/* 18 */     super(p_i46135_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180561_a(EntityMinecartTNT p_180561_1_, float p_180561_2_, IBlockState p_180561_3_) {
/* 23 */     int var4 = p_180561_1_.func_94104_d();
/*    */     
/* 25 */     if (var4 > -1 && var4 - p_180561_2_ + 1.0F < 10.0F) {
/*    */       
/* 27 */       float var5 = 1.0F - (var4 - p_180561_2_ + 1.0F) / 10.0F;
/* 28 */       var5 = MathHelper.clamp_float(var5, 0.0F, 1.0F);
/* 29 */       var5 *= var5;
/* 30 */       var5 *= var5;
/* 31 */       float var6 = 1.0F + var5 * 0.3F;
/* 32 */       GlStateManager.scale(var6, var6, var6);
/*    */     } 
/*    */     
/* 35 */     super.func_180560_a((EntityMinecart)p_180561_1_, p_180561_2_, p_180561_3_);
/*    */     
/* 37 */     if (var4 > -1 && var4 / 5 % 2 == 0) {
/*    */       
/* 39 */       BlockRendererDispatcher var7 = Minecraft.getMinecraft().getBlockRendererDispatcher();
/* 40 */       GlStateManager.func_179090_x();
/* 41 */       GlStateManager.disableLighting();
/* 42 */       GlStateManager.enableBlend();
/* 43 */       GlStateManager.blendFunc(770, 772);
/* 44 */       GlStateManager.color(1.0F, 1.0F, 1.0F, (1.0F - (var4 - p_180561_2_ + 1.0F) / 100.0F) * 0.8F);
/* 45 */       GlStateManager.pushMatrix();
/* 46 */       var7.func_175016_a(Blocks.tnt.getDefaultState(), 1.0F);
/* 47 */       GlStateManager.popMatrix();
/* 48 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 49 */       GlStateManager.disableBlend();
/* 50 */       GlStateManager.enableLighting();
/* 51 */       GlStateManager.func_179098_w();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180560_a(EntityMinecart p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
/* 57 */     func_180561_a((EntityMinecartTNT)p_180560_1_, p_180560_2_, p_180560_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderTntMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */