/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.passive.EntityRabbit;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderRabbit
/*    */   extends RenderLiving {
/* 11 */   private static final ResourceLocation field_177127_a = new ResourceLocation("textures/entity/rabbit/brown.png");
/* 12 */   private static final ResourceLocation field_177126_e = new ResourceLocation("textures/entity/rabbit/white.png");
/* 13 */   private static final ResourceLocation field_177132_j = new ResourceLocation("textures/entity/rabbit/black.png");
/* 14 */   private static final ResourceLocation field_177133_k = new ResourceLocation("textures/entity/rabbit/gold.png");
/* 15 */   private static final ResourceLocation field_177130_l = new ResourceLocation("textures/entity/rabbit/salt.png");
/* 16 */   private static final ResourceLocation field_177131_m = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
/* 17 */   private static final ResourceLocation field_177128_n = new ResourceLocation("textures/entity/rabbit/toast.png");
/* 18 */   private static final ResourceLocation field_177129_o = new ResourceLocation("textures/entity/rabbit/caerbannog.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00002432";
/*    */   
/*    */   public RenderRabbit(RenderManager p_i46146_1_, ModelBase p_i46146_2_, float p_i46146_3_) {
/* 23 */     super(p_i46146_1_, p_i46146_2_, p_i46146_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_177125_a(EntityRabbit p_177125_1_) {
/* 28 */     String var2 = EnumChatFormatting.getTextWithoutFormattingCodes(p_177125_1_.getName());
/*    */     
/* 30 */     if (var2 != null && var2.equals("Toast"))
/*    */     {
/* 32 */       return field_177128_n;
/*    */     }
/*    */ 
/*    */     
/* 36 */     switch (p_177125_1_.func_175531_cl()) {
/*    */ 
/*    */       
/*    */       default:
/* 40 */         return field_177127_a;
/*    */       
/*    */       case 1:
/* 43 */         return field_177126_e;
/*    */       
/*    */       case 2:
/* 46 */         return field_177132_j;
/*    */       
/*    */       case 3:
/* 49 */         return field_177131_m;
/*    */       
/*    */       case 4:
/* 52 */         return field_177133_k;
/*    */       
/*    */       case 5:
/* 55 */         return field_177130_l;
/*    */       case 99:
/*    */         break;
/* 58 */     }  return field_177129_o;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 68 */     return func_177125_a((EntityRabbit)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */