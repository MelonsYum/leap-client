/*    */ package net.minecraft.client.renderer.texture;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import shadersmod.client.MultiTexID;
/*    */ import shadersmod.client.ShadersTex;
/*    */ 
/*    */ public abstract class AbstractTexture
/*    */   implements ITextureObject {
/* 10 */   protected int glTextureId = -1;
/*    */   
/*    */   protected boolean field_174940_b;
/*    */   
/*    */   protected boolean field_174941_c;
/*    */   protected boolean field_174938_d;
/*    */   
/*    */   public void func_174937_a(boolean p_174937_1_, boolean p_174937_2_) {
/*    */     int var5;
/*    */     short var6;
/* 20 */     this.field_174940_b = p_174937_1_;
/* 21 */     this.field_174941_c = p_174937_2_;
/* 22 */     boolean var3 = true;
/* 23 */     boolean var4 = true;
/*    */ 
/*    */ 
/*    */     
/* 27 */     if (p_174937_1_) {
/*    */       
/* 29 */       var5 = p_174937_2_ ? 9987 : 9729;
/* 30 */       var6 = 9729;
/*    */     }
/*    */     else {
/*    */       
/* 34 */       var5 = p_174937_2_ ? 9986 : 9728;
/* 35 */       var6 = 9728;
/*    */     } 
/*    */     
/* 38 */     GlStateManager.func_179144_i(getGlTextureId());
/* 39 */     GL11.glTexParameteri(3553, 10241, var5);
/* 40 */     GL11.glTexParameteri(3553, 10240, var6);
/*    */   }
/*    */   protected boolean field_174939_e; private static final String __OBFID = "CL_00001047"; public MultiTexID multiTex;
/*    */   
/*    */   public void func_174936_b(boolean p_174936_1_, boolean p_174936_2_) {
/* 45 */     this.field_174938_d = this.field_174940_b;
/* 46 */     this.field_174939_e = this.field_174941_c;
/* 47 */     func_174937_a(p_174936_1_, p_174936_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_174935_a() {
/* 52 */     func_174937_a(this.field_174938_d, this.field_174939_e);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGlTextureId() {
/* 57 */     if (this.glTextureId == -1)
/*    */     {
/* 59 */       this.glTextureId = TextureUtil.glGenTextures();
/*    */     }
/*    */     
/* 62 */     return this.glTextureId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deleteGlTexture() {
/* 67 */     ShadersTex.deleteTextures(this, this.glTextureId);
/*    */     
/* 69 */     if (this.glTextureId != -1) {
/*    */       
/* 71 */       TextureUtil.deleteTexture(this.glTextureId);
/* 72 */       this.glTextureId = -1;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public MultiTexID getMultiTexID() {
/* 78 */     return ShadersTex.getMultiTexID(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\AbstractTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */