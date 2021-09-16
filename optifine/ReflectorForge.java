/*    */ package optifine;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class ReflectorForge
/*    */ {
/*    */   public static void FMLClientHandler_trackBrokenTexture(ResourceLocation loc, String message) {
/* 15 */     if (!Reflector.FMLClientHandler_trackBrokenTexture.exists()) {
/*    */       
/* 17 */       Object instance = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
/* 18 */       Reflector.call(instance, Reflector.FMLClientHandler_trackBrokenTexture, new Object[] { loc, message });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void FMLClientHandler_trackMissingTexture(ResourceLocation loc) {
/* 24 */     if (!Reflector.FMLClientHandler_trackMissingTexture.exists()) {
/*    */       
/* 26 */       Object instance = Reflector.call(Reflector.FMLClientHandler_instance, new Object[0]);
/* 27 */       Reflector.call(instance, Reflector.FMLClientHandler_trackMissingTexture, new Object[] { loc });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void putLaunchBlackboard(String key, Object value) {
/* 33 */     Map<String, Object> blackboard = (Map)Reflector.getFieldValue(Reflector.Launch_blackboard);
/*    */     
/* 35 */     if (blackboard != null)
/*    */     {
/* 37 */       blackboard.put(key, value);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static InputStream getOptiFineResourceStream(String path) {
/* 43 */     if (!Reflector.OptiFineClassTransformer_instance.exists())
/*    */     {
/* 45 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 49 */     Object instance = Reflector.getFieldValue(Reflector.OptiFineClassTransformer_instance);
/*    */     
/* 51 */     if (instance == null)
/*    */     {
/* 53 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 57 */     if (path.startsWith("/"))
/*    */     {
/* 59 */       path = path.substring(1);
/*    */     }
/*    */     
/* 62 */     byte[] bytes = (byte[])Reflector.call(instance, Reflector.OptiFineClassTransformer_getOptiFineResource, new Object[] { path });
/*    */     
/* 64 */     if (bytes == null)
/*    */     {
/* 66 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 70 */     ByteArrayInputStream in = new ByteArrayInputStream(bytes);
/* 71 */     return in;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean blockHasTileEntity(IBlockState state) {
/* 79 */     Block block = state.getBlock();
/* 80 */     return !Reflector.ForgeBlock_hasTileEntity.exists() ? block.hasTileEntity() : Reflector.callBoolean(block, Reflector.ForgeBlock_hasTileEntity, new Object[] { state });
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isItemDamaged(ItemStack stack) {
/* 85 */     return !Reflector.ForgeItem_showDurabilityBar.exists() ? stack.isItemDamaged() : Reflector.callBoolean(stack.getItem(), Reflector.ForgeItem_showDurabilityBar, new Object[] { stack });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ReflectorForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */