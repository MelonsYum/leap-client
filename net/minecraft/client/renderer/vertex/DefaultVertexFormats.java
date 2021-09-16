/*    */ package net.minecraft.client.renderer.vertex;
/*    */ 
/*    */ import optifine.Config;
/*    */ import optifine.Reflector;
/*    */ import shadersmod.client.SVertexFormat;
/*    */ 
/*    */ public class DefaultVertexFormats
/*    */ {
/*  9 */   public static VertexFormat field_176600_a = new VertexFormat();
/* 10 */   public static VertexFormat field_176599_b = new VertexFormat();
/* 11 */   private static final VertexFormat BLOCK_VANILLA = field_176600_a;
/* 12 */   private static final VertexFormat ITEM_VANILLA = field_176599_b;
/*    */   
/*    */   private static final String __OBFID = "CL_00002403";
/*    */   
/*    */   public static void updateVertexFormats() {
/* 17 */     if (Config.isShaders()) {
/*    */       
/* 19 */       field_176600_a = SVertexFormat.makeDefVertexFormatBlock();
/* 20 */       field_176599_b = SVertexFormat.makeDefVertexFormatItem();
/*    */     }
/*    */     else {
/*    */       
/* 24 */       field_176600_a = BLOCK_VANILLA;
/* 25 */       field_176599_b = ITEM_VANILLA;
/*    */     } 
/*    */     
/* 28 */     if (Reflector.Attributes_DEFAULT_BAKED_FORMAT.exists()) {
/*    */       
/* 30 */       VertexFormat vfSrc = field_176599_b;
/* 31 */       VertexFormat vfDst = (VertexFormat)Reflector.getFieldValue(Reflector.Attributes_DEFAULT_BAKED_FORMAT);
/* 32 */       vfDst.clear();
/*    */       
/* 34 */       for (int i = 0; i < vfSrc.func_177345_h(); i++)
/*    */       {
/* 36 */         vfDst.func_177349_a(vfSrc.func_177348_c(i));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   static {
/* 43 */     field_176600_a.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/* 44 */     field_176600_a.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.COLOR, 4));
/* 45 */     field_176600_a.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/* 46 */     field_176600_a.func_177349_a(new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.UV, 2));
/* 47 */     field_176599_b.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/* 48 */     field_176599_b.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.COLOR, 4));
/* 49 */     field_176599_b.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/* 50 */     field_176599_b.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.NORMAL, 3));
/* 51 */     field_176599_b.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.PADDING, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\vertex\DefaultVertexFormats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */