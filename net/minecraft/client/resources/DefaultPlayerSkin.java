/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class DefaultPlayerSkin
/*    */ {
/*  8 */   private static final ResourceLocation field_177337_a = new ResourceLocation("textures/entity/steve.png");
/*  9 */   private static final ResourceLocation field_177336_b = new ResourceLocation("textures/entity/alex.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00002396";
/*    */   
/*    */   public static ResourceLocation func_177335_a() {
/* 14 */     return field_177337_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ResourceLocation func_177334_a(UUID p_177334_0_) {
/* 19 */     return func_177333_c(p_177334_0_) ? field_177336_b : field_177337_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public static String func_177332_b(UUID p_177332_0_) {
/* 24 */     return func_177333_c(p_177332_0_) ? "slim" : "default";
/*    */   }
/*    */ 
/*    */   
/*    */   private static boolean func_177333_c(UUID p_177333_0_) {
/* 29 */     return ((p_177333_0_.hashCode() & 0x1) == 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\DefaultPlayerSkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */