/*     */ package shadersmod.client;
/*     */ 
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ 
/*     */ public class GuiButtonEnumShaderOption
/*     */   extends GuiButton {
/*   8 */   private EnumShaderOption enumShaderOption = null;
/*     */ 
/*     */   
/*     */   public GuiButtonEnumShaderOption(EnumShaderOption enumShaderOption, int x, int y, int widthIn, int heightIn) {
/*  12 */     super(enumShaderOption.ordinal(), x, y, widthIn, heightIn, getButtonText(enumShaderOption));
/*  13 */     this.enumShaderOption = enumShaderOption;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumShaderOption getEnumShaderOption() {
/*  18 */     return this.enumShaderOption;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getButtonText(EnumShaderOption eso) {
/*  23 */     String nameText = String.valueOf(I18n.format(eso.getResourceKey(), new Object[0])) + ": ";
/*     */     
/*  25 */     switch (eso) {
/*     */       
/*     */       case ANTIALIASING:
/*  28 */         return String.valueOf(nameText) + GuiShaders.toStringAa(Shaders.configAntialiasingLevel);
/*     */       
/*     */       case NORMAL_MAP:
/*  31 */         return String.valueOf(nameText) + GuiShaders.toStringOnOff(Shaders.configNormalMap);
/*     */       
/*     */       case SPECULAR_MAP:
/*  34 */         return String.valueOf(nameText) + GuiShaders.toStringOnOff(Shaders.configSpecularMap);
/*     */       
/*     */       case RENDER_RES_MUL:
/*  37 */         return String.valueOf(nameText) + GuiShaders.toStringQuality(Shaders.configRenderResMul);
/*     */       
/*     */       case SHADOW_RES_MUL:
/*  40 */         return String.valueOf(nameText) + GuiShaders.toStringQuality(Shaders.configShadowResMul);
/*     */       
/*     */       case HAND_DEPTH_MUL:
/*  43 */         return String.valueOf(nameText) + GuiShaders.toStringHandDepth(Shaders.configHandDepthMul);
/*     */       
/*     */       case CLOUD_SHADOW:
/*  46 */         return String.valueOf(nameText) + GuiShaders.toStringOnOff(Shaders.configCloudShadow);
/*     */       
/*     */       case OLD_LIGHTING:
/*  49 */         return String.valueOf(nameText) + Shaders.configOldLighting.getUserValue();
/*     */       
/*     */       case SHADOW_CLIP_FRUSTRUM:
/*  52 */         return String.valueOf(nameText) + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum);
/*     */       
/*     */       case TWEAK_BLOCK_DAMAGE:
/*  55 */         return String.valueOf(nameText) + GuiShaders.toStringOnOff(Shaders.configTweakBlockDamage);
/*     */     } 
/*     */     
/*  58 */     return String.valueOf(nameText) + Shaders.getEnumShaderOption(eso);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateButtonText() {
/*  64 */     this.displayString = getButtonText(this.enumShaderOption);
/*     */   }
/*     */   
/*     */   static class NamelessClass895471824
/*     */   {
/*  69 */     static final int[] $SwitchMap$shadersmod$client$EnumShaderOption = new int[(EnumShaderOption.values()).length];
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/*  75 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.ANTIALIASING.ordinal()] = 1;
/*     */       }
/*  77 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  84 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.NORMAL_MAP.ordinal()] = 2;
/*     */       }
/*  86 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  93 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SPECULAR_MAP.ordinal()] = 3;
/*     */       }
/*  95 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 102 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.RENDER_RES_MUL.ordinal()] = 4;
/*     */       }
/* 104 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 111 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADOW_RES_MUL.ordinal()] = 5;
/*     */       }
/* 113 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 120 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.HAND_DEPTH_MUL.ordinal()] = 6;
/*     */       }
/* 122 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 129 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.CLOUD_SHADOW.ordinal()] = 7;
/*     */       }
/* 131 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 138 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.OLD_LIGHTING.ordinal()] = 8;
/*     */       }
/* 140 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 147 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADOW_CLIP_FRUSTRUM.ordinal()] = 9;
/*     */       }
/* 149 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 156 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TWEAK_BLOCK_DAMAGE.ordinal()] = 10;
/*     */       }
/* 158 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\GuiButtonEnumShaderOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */