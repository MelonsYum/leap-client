/*    */ package shadersmod.client;
/*    */ 
/*    */ public enum EnumShaderOption
/*    */ {
/*  5 */   ANTIALIASING("ANTIALIASING", 0, "of.options.shaders.ANTIALIASING", "antialiasingLevel", "0"),
/*  6 */   NORMAL_MAP("NORMAL_MAP", 1, "of.options.shaders.NORMAL_MAP", "normalMapEnabled", "true"),
/*  7 */   SPECULAR_MAP("SPECULAR_MAP", 2, "of.options.shaders.SPECULAR_MAP", "specularMapEnabled", "true"),
/*  8 */   RENDER_RES_MUL("RENDER_RES_MUL", 3, "of.options.shaders.RENDER_RES_MUL", "renderResMul", "1.0"),
/*  9 */   SHADOW_RES_MUL("SHADOW_RES_MUL", 4, "of.options.shaders.SHADOW_RES_MUL", "shadowResMul", "1.0"),
/* 10 */   HAND_DEPTH_MUL("HAND_DEPTH_MUL", 5, "of.options.shaders.HAND_DEPTH_MUL", "handDepthMul", "0.125"),
/* 11 */   CLOUD_SHADOW("CLOUD_SHADOW", 6, "of.options.shaders.CLOUD_SHADOW", "cloudShadow", "true"),
/* 12 */   OLD_LIGHTING("OLD_LIGHTING", 7, "of.options.shaders.OLD_LIGHTING", "oldLighting", "default"),
/* 13 */   SHADER_PACK("SHADER_PACK", 8, "of.options.shaders.SHADER_PACK", "shaderPack", ""),
/* 14 */   TWEAK_BLOCK_DAMAGE("TWEAK_BLOCK_DAMAGE", 9, "of.options.shaders.TWEAK_BLOCK_DAMAGE", "tweakBlockDamage", "false"),
/* 15 */   SHADOW_CLIP_FRUSTRUM("SHADOW_CLIP_FRUSTRUM", 10, "of.options.shaders.SHADOW_CLIP_FRUSTRUM", "shadowClipFrustrum", "true"),
/* 16 */   TEX_MIN_FIL_B("TEX_MIN_FIL_B", 11, "of.options.shaders.TEX_MIN_FIL_B", "TexMinFilB", "0"),
/* 17 */   TEX_MIN_FIL_N("TEX_MIN_FIL_N", 12, "of.options.shaders.TEX_MIN_FIL_N", "TexMinFilN", "0"),
/* 18 */   TEX_MIN_FIL_S("TEX_MIN_FIL_S", 13, "of.options.shaders.TEX_MIN_FIL_S", "TexMinFilS", "0"),
/* 19 */   TEX_MAG_FIL_B("TEX_MAG_FIL_B", 14, "of.options.shaders.TEX_MAG_FIL_B", "TexMagFilB", "0"),
/* 20 */   TEX_MAG_FIL_N("TEX_MAG_FIL_N", 15, "of.options.shaders.TEX_MAG_FIL_N", "TexMagFilN", "0"),
/* 21 */   TEX_MAG_FIL_S("TEX_MAG_FIL_S", 16, "of.options.shaders.TEX_MAG_FIL_S", "TexMagFilS", "0");
/* 22 */   private String resourceKey = null;
/* 23 */   private String propertyKey = null;
/* 24 */   private String valueDefault = null;
/*    */   static {
/* 26 */     $VALUES = new EnumShaderOption[] { ANTIALIASING, NORMAL_MAP, SPECULAR_MAP, RENDER_RES_MUL, SHADOW_RES_MUL, HAND_DEPTH_MUL, CLOUD_SHADOW, OLD_LIGHTING, SHADER_PACK, TWEAK_BLOCK_DAMAGE, SHADOW_CLIP_FRUSTRUM, TEX_MIN_FIL_B, TEX_MIN_FIL_N, TEX_MIN_FIL_S, TEX_MAG_FIL_B, TEX_MAG_FIL_N, TEX_MAG_FIL_S };
/*    */   }
/*    */   private static final EnumShaderOption[] $VALUES;
/*    */   EnumShaderOption(String var1, int var2, String resourceKey, String propertyKey, String valueDefault) {
/* 30 */     this.resourceKey = resourceKey;
/* 31 */     this.propertyKey = propertyKey;
/* 32 */     this.valueDefault = valueDefault;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResourceKey() {
/* 37 */     return this.resourceKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyKey() {
/* 42 */     return this.propertyKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValueDefault() {
/* 47 */     return this.valueDefault;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\EnumShaderOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */