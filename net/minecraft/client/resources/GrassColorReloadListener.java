/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.ColorizerGrass;
/*    */ 
/*    */ public class GrassColorReloadListener
/*    */   implements IResourceManagerReloadListener {
/* 10 */   private static final ResourceLocation field_130078_a = new ResourceLocation("textures/colormap/grass.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001078";
/*    */ 
/*    */   
/*    */   public void onResourceManagerReload(IResourceManager p_110549_1_) {
/*    */     try {
/* 17 */       ColorizerGrass.setGrassBiomeColorizer(TextureUtil.readImageData(p_110549_1_, field_130078_a));
/*    */     }
/* 19 */     catch (IOException iOException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\GrassColorReloadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */