/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.client.SVertexBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldVertexBufferUploader
/*     */ {
/*     */   public int draw(WorldRenderer p_178177_1_, int p_178177_2_) {
/*  19 */     if (p_178177_2_ > 0) {
/*     */       
/*  21 */       VertexFormat var3 = p_178177_1_.func_178973_g();
/*  22 */       int var4 = var3.func_177338_f();
/*  23 */       ByteBuffer var5 = p_178177_1_.func_178966_f();
/*  24 */       List<VertexFormatElement> var6 = var3.func_177343_g();
/*  25 */       Iterator<VertexFormatElement> var7 = var6.iterator();
/*  26 */       boolean forgePreDrawExists = Reflector.ForgeVertexFormatElementEnumUseage_preDraw.exists();
/*  27 */       boolean forgePostDrawExists = Reflector.ForgeVertexFormatElementEnumUseage_postDraw.exists();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  32 */       while (var7.hasNext()) {
/*     */         
/*  34 */         VertexFormatElement var8 = var7.next();
/*  35 */         VertexFormatElement.EnumUseage var9 = var8.func_177375_c();
/*     */         
/*  37 */         if (forgePreDrawExists) {
/*     */           
/*  39 */           Reflector.callVoid(var9, Reflector.ForgeVertexFormatElementEnumUseage_preDraw, new Object[] { var8, Integer.valueOf(var4), var5 });
/*     */           
/*     */           continue;
/*     */         } 
/*  43 */         int var10 = var8.func_177367_b().func_177397_c();
/*  44 */         int wr = var8.func_177369_e();
/*     */         
/*  46 */         switch (SwitchEnumUseage.field_178958_a[var9.ordinal()]) {
/*     */           
/*     */           case 1:
/*  49 */             var5.position(var8.func_177373_a());
/*  50 */             GL11.glVertexPointer(var8.func_177370_d(), var10, var4, var5);
/*  51 */             GL11.glEnableClientState(32884);
/*     */ 
/*     */           
/*     */           case 2:
/*  55 */             var5.position(var8.func_177373_a());
/*  56 */             OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + wr);
/*  57 */             GL11.glTexCoordPointer(var8.func_177370_d(), var10, var4, var5);
/*  58 */             GL11.glEnableClientState(32888);
/*  59 */             OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */ 
/*     */           
/*     */           case 3:
/*  63 */             var5.position(var8.func_177373_a());
/*  64 */             GL11.glColorPointer(var8.func_177370_d(), var10, var4, var5);
/*  65 */             GL11.glEnableClientState(32886);
/*     */ 
/*     */           
/*     */           case 4:
/*  69 */             var5.position(var8.func_177373_a());
/*  70 */             GL11.glNormalPointer(var10, var4, var5);
/*  71 */             GL11.glEnableClientState(32885);
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*  76 */       if (p_178177_1_.isMultiTexture()) {
/*     */         
/*  78 */         p_178177_1_.drawMultiTexture();
/*     */       }
/*  80 */       else if (Config.isShaders()) {
/*     */         
/*  82 */         SVertexBuilder.drawArrays(p_178177_1_.getDrawMode(), 0, p_178177_1_.func_178989_h(), p_178177_1_);
/*     */       }
/*     */       else {
/*     */         
/*  86 */         GL11.glDrawArrays(p_178177_1_.getDrawMode(), 0, p_178177_1_.func_178989_h());
/*     */       } 
/*     */       
/*  89 */       var7 = var6.iterator();
/*     */       
/*  91 */       while (var7.hasNext()) {
/*     */         
/*  93 */         VertexFormatElement var8 = var7.next();
/*  94 */         VertexFormatElement.EnumUseage var9 = var8.func_177375_c();
/*     */         
/*  96 */         if (forgePostDrawExists) {
/*     */           
/*  98 */           Reflector.callVoid(var9, Reflector.ForgeVertexFormatElementEnumUseage_postDraw, new Object[] { var8, Integer.valueOf(var4), var5 });
/*     */           
/*     */           continue;
/*     */         } 
/* 102 */         int var10 = var8.func_177369_e();
/*     */         
/* 104 */         switch (SwitchEnumUseage.field_178958_a[var9.ordinal()]) {
/*     */           
/*     */           case 1:
/* 107 */             GL11.glDisableClientState(32884);
/*     */ 
/*     */           
/*     */           case 2:
/* 111 */             OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + var10);
/* 112 */             GL11.glDisableClientState(32888);
/* 113 */             OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */ 
/*     */           
/*     */           case 3:
/* 117 */             GL11.glDisableClientState(32886);
/* 118 */             GlStateManager.func_179117_G();
/*     */ 
/*     */           
/*     */           case 4:
/* 122 */             GL11.glDisableClientState(32885);
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/*     */     } 
/* 128 */     p_178177_1_.reset();
/* 129 */     return p_178177_2_;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumUseage
/*     */   {
/* 134 */     static final int[] field_178958_a = new int[(VertexFormatElement.EnumUseage.values()).length];
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 140 */         field_178958_a[VertexFormatElement.EnumUseage.POSITION.ordinal()] = 1;
/*     */       }
/* 142 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 149 */         field_178958_a[VertexFormatElement.EnumUseage.UV.ordinal()] = 2;
/*     */       }
/* 151 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 158 */         field_178958_a[VertexFormatElement.EnumUseage.COLOR.ordinal()] = 3;
/*     */       }
/* 160 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 167 */         field_178958_a[VertexFormatElement.EnumUseage.NORMAL.ordinal()] = 4;
/*     */       }
/* 169 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\WorldVertexBufferUploader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */