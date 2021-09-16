/*    */ package net.minecraft.client.renderer.texture;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class LayeredColorMaskTexture
/*    */   extends AbstractTexture
/*    */ {
/* 19 */   private static final Logger field_174947_f = LogManager.getLogger();
/*    */   
/*    */   private final ResourceLocation field_174948_g;
/*    */   private final List field_174949_h;
/*    */   private final List field_174950_i;
/*    */   private static final String __OBFID = "CL_00002404";
/*    */   
/*    */   public LayeredColorMaskTexture(ResourceLocation p_i46101_1_, List p_i46101_2_, List p_i46101_3_) {
/* 27 */     this.field_174948_g = p_i46101_1_;
/* 28 */     this.field_174949_h = p_i46101_2_;
/* 29 */     this.field_174950_i = p_i46101_3_;
/*    */   }
/*    */   
/*    */   public void loadTexture(IResourceManager p_110551_1_) throws IOException {
/*    */     BufferedImage var2;
/* 34 */     deleteGlTexture();
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 39 */       BufferedImage var3 = TextureUtil.func_177053_a(p_110551_1_.getResource(this.field_174948_g).getInputStream());
/* 40 */       int var4 = var3.getType();
/*    */       
/* 42 */       if (var4 == 0)
/*    */       {
/* 44 */         var4 = 6;
/*    */       }
/*    */       
/* 47 */       var2 = new BufferedImage(var3.getWidth(), var3.getHeight(), var4);
/* 48 */       Graphics var5 = var2.getGraphics();
/* 49 */       var5.drawImage(var3, 0, 0, null);
/*    */       
/* 51 */       for (int var6 = 0; var6 < this.field_174949_h.size() && var6 < this.field_174950_i.size(); var6++) {
/*    */         
/* 53 */         String var7 = this.field_174949_h.get(var6);
/* 54 */         MapColor var8 = ((EnumDyeColor)this.field_174950_i.get(var6)).func_176768_e();
/*    */         
/* 56 */         if (var7 != null) {
/*    */           
/* 58 */           InputStream var9 = p_110551_1_.getResource(new ResourceLocation(var7)).getInputStream();
/* 59 */           BufferedImage var10 = TextureUtil.func_177053_a(var9);
/*    */           
/* 61 */           if (var10.getWidth() == var2.getWidth() && var10.getHeight() == var2.getHeight() && var10.getType() == 6)
/*    */           {
/* 63 */             for (int var11 = 0; var11 < var10.getHeight(); var11++) {
/*    */               
/* 65 */               for (int var12 = 0; var12 < var10.getWidth(); var12++) {
/*    */                 
/* 67 */                 int var13 = var10.getRGB(var12, var11);
/*    */                 
/* 69 */                 if ((var13 & 0xFF000000) != 0) {
/*    */                   
/* 71 */                   int var14 = (var13 & 0xFF0000) << 8 & 0xFF000000;
/* 72 */                   int var15 = var3.getRGB(var12, var11);
/* 73 */                   int var16 = MathHelper.func_180188_d(var15, var8.colorValue) & 0xFFFFFF;
/* 74 */                   var10.setRGB(var12, var11, var14 | var16);
/*    */                 } 
/*    */               } 
/*    */             } 
/*    */             
/* 79 */             var2.getGraphics().drawImage(var10, 0, 0, null);
/*    */           }
/*    */         
/*    */         } 
/*    */       } 
/* 84 */     } catch (IOException var17) {
/*    */       
/* 86 */       field_174947_f.error("Couldn't load layered image", var17);
/*    */       
/*    */       return;
/*    */     } 
/* 90 */     TextureUtil.uploadTextureImage(getGlTextureId(), var2);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\texture\LayeredColorMaskTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */