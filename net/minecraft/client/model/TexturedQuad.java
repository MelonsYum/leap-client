/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.renderer.WorldRenderer;
/*    */ import net.minecraft.util.Vec3;
/*    */ import optifine.Config;
/*    */ import shadersmod.client.SVertexBuilder;
/*    */ 
/*    */ 
/*    */ public class TexturedQuad
/*    */ {
/*    */   public PositionTextureVertex[] vertexPositions;
/*    */   public int nVertices;
/*    */   private boolean invertNormal;
/*    */   private static final String __OBFID = "CL_00000850";
/*    */   
/*    */   public TexturedQuad(PositionTextureVertex[] vertices) {
/* 18 */     this.vertexPositions = vertices;
/* 19 */     this.nVertices = vertices.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public TexturedQuad(PositionTextureVertex[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight) {
/* 24 */     this(vertices);
/* 25 */     float var8 = 0.0F / textureWidth;
/* 26 */     float var9 = 0.0F / textureHeight;
/* 27 */     vertices[0] = vertices[0].setTexturePosition(texcoordU2 / textureWidth - var8, texcoordV1 / textureHeight + var9);
/* 28 */     vertices[1] = vertices[1].setTexturePosition(texcoordU1 / textureWidth + var8, texcoordV1 / textureHeight + var9);
/* 29 */     vertices[2] = vertices[2].setTexturePosition(texcoordU1 / textureWidth + var8, texcoordV2 / textureHeight - var9);
/* 30 */     vertices[3] = vertices[3].setTexturePosition(texcoordU2 / textureWidth - var8, texcoordV2 / textureHeight - var9);
/*    */   }
/*    */ 
/*    */   
/*    */   public void flipFace() {
/* 35 */     PositionTextureVertex[] var1 = new PositionTextureVertex[this.vertexPositions.length];
/*    */     
/* 37 */     for (int var2 = 0; var2 < this.vertexPositions.length; var2++)
/*    */     {
/* 39 */       var1[var2] = this.vertexPositions[this.vertexPositions.length - var2 - 1];
/*    */     }
/*    */     
/* 42 */     this.vertexPositions = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178765_a(WorldRenderer renderer, float scale) {
/* 47 */     Vec3 var3 = (this.vertexPositions[1]).vector3D.subtractReverse((this.vertexPositions[0]).vector3D);
/* 48 */     Vec3 var4 = (this.vertexPositions[1]).vector3D.subtractReverse((this.vertexPositions[2]).vector3D);
/* 49 */     Vec3 var5 = var4.crossProduct(var3).normalize();
/* 50 */     renderer.startDrawingQuads();
/*    */     
/* 52 */     if (Config.isShaders())
/*    */     {
/* 54 */       SVertexBuilder.startTexturedQuad(renderer);
/*    */     }
/*    */     
/* 57 */     if (this.invertNormal) {
/*    */       
/* 59 */       renderer.func_178980_d(-((float)var5.xCoord), -((float)var5.yCoord), -((float)var5.zCoord));
/*    */     }
/*    */     else {
/*    */       
/* 63 */       renderer.func_178980_d((float)var5.xCoord, (float)var5.yCoord, (float)var5.zCoord);
/*    */     } 
/*    */     
/* 66 */     for (int var6 = 0; var6 < 4; var6++) {
/*    */       
/* 68 */       PositionTextureVertex var7 = this.vertexPositions[var6];
/* 69 */       renderer.addVertexWithUV(var7.vector3D.xCoord * scale, var7.vector3D.yCoord * scale, var7.vector3D.zCoord * scale, var7.texturePositionX, var7.texturePositionY);
/*    */     } 
/*    */     
/* 72 */     Tessellator.getInstance().draw();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\TexturedQuad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */