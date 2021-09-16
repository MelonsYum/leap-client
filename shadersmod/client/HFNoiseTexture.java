/*    */ package shadersmod.client;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import org.lwjgl.BufferUtils;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class HFNoiseTexture
/*    */ {
/* 10 */   public int texID = GL11.glGenTextures();
/* 11 */   public int textureUnit = 15;
/*    */ 
/*    */   
/*    */   public HFNoiseTexture(int width, int height) {
/* 15 */     byte[] image = genHFNoiseImage(width, height);
/* 16 */     ByteBuffer data = BufferUtils.createByteBuffer(image.length);
/* 17 */     data.put(image);
/* 18 */     data.flip();
/* 19 */     GlStateManager.func_179144_i(this.texID);
/* 20 */     GL11.glTexImage2D(3553, 0, 6407, width, height, 0, 6407, 5121, data);
/* 21 */     GL11.glTexParameteri(3553, 10242, 10497);
/* 22 */     GL11.glTexParameteri(3553, 10243, 10497);
/* 23 */     GL11.glTexParameteri(3553, 10240, 9729);
/* 24 */     GL11.glTexParameteri(3553, 10241, 9729);
/* 25 */     GlStateManager.func_179144_i(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getID() {
/* 30 */     return this.texID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void destroy() {
/* 35 */     GlStateManager.func_179150_h(this.texID);
/* 36 */     this.texID = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   private int random(int seed) {
/* 41 */     seed ^= seed << 13;
/* 42 */     seed ^= seed >> 17;
/* 43 */     seed ^= seed << 5;
/* 44 */     return seed;
/*    */   }
/*    */ 
/*    */   
/*    */   private byte random(int x, int y, int z) {
/* 49 */     int seed = (random(x) + random(y * 19)) * random(z * 23) - z;
/* 50 */     return (byte)(random(seed) % 128);
/*    */   }
/*    */ 
/*    */   
/*    */   private byte[] genHFNoiseImage(int width, int height) {
/* 55 */     byte[] image = new byte[width * height * 3];
/* 56 */     int index = 0;
/*    */     
/* 58 */     for (int y = 0; y < height; y++) {
/*    */       
/* 60 */       for (int x = 0; x < width; x++) {
/*    */         
/* 62 */         for (int z = 1; z < 4; z++)
/*    */         {
/* 64 */           image[index++] = random(x, y, z);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return image;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\HFNoiseTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */