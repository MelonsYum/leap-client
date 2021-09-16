/*    */ package shadersmod.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*    */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*    */ 
/*    */ public class SVertexFormat
/*    */ {
/*    */   public static final int vertexSizeBlock = 14;
/*    */   public static final int offsetMidTexCoord = 8;
/*    */   public static final int offsetTangent = 10;
/*    */   public static final int offsetEntity = 12;
/* 12 */   public static final VertexFormat defVertexFormatTextured = makeDefVertexFormatTextured();
/*    */ 
/*    */   
/*    */   public static VertexFormat makeDefVertexFormatBlock() {
/* 16 */     VertexFormat vf = new VertexFormat();
/* 17 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/* 18 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.COLOR, 4));
/* 19 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/* 20 */     vf.func_177349_a(new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.UV, 2));
/* 21 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.NORMAL, 3));
/* 22 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.PADDING, 1));
/* 23 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 24 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 25 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 26 */     return vf;
/*    */   }
/*    */ 
/*    */   
/*    */   public static VertexFormat makeDefVertexFormatItem() {
/* 31 */     VertexFormat vf = new VertexFormat();
/* 32 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/* 33 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.COLOR, 4));
/* 34 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/* 35 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 36 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.NORMAL, 3));
/* 37 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.PADDING, 1));
/* 38 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 39 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 40 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 41 */     return vf;
/*    */   }
/*    */ 
/*    */   
/*    */   public static VertexFormat makeDefVertexFormatTextured() {
/* 46 */     VertexFormat vf = new VertexFormat();
/* 47 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/* 48 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.PADDING, 4));
/* 49 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/* 50 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 51 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.NORMAL, 3));
/* 52 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.PADDING, 1));
/* 53 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 54 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 55 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 56 */     return vf;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setDefBakedFormat(VertexFormat vf) {
/* 61 */     vf.clear();
/* 62 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/* 63 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.COLOR, 4));
/* 64 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/* 65 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 66 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.NORMAL, 3));
/* 67 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.PADDING, 1));
/* 68 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.PADDING, 2));
/* 69 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/* 70 */     vf.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.PADDING, 4));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\SVertexFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */