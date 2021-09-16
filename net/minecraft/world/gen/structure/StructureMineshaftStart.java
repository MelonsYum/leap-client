/*    */ package net.minecraft.world.gen.structure;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class StructureMineshaftStart
/*    */   extends StructureStart
/*    */ {
/*    */   private static final String __OBFID = "CL_00000450";
/*    */   
/*    */   public StructureMineshaftStart() {}
/*    */   
/*    */   public StructureMineshaftStart(World worldIn, Random p_i2039_2_, int p_i2039_3_, int p_i2039_4_) {
/* 14 */     super(p_i2039_3_, p_i2039_4_);
/* 15 */     StructureMineshaftPieces.Room var5 = new StructureMineshaftPieces.Room(0, p_i2039_2_, (p_i2039_3_ << 4) + 2, (p_i2039_4_ << 4) + 2);
/* 16 */     this.components.add(var5);
/* 17 */     var5.buildComponent(var5, this.components, p_i2039_2_);
/* 18 */     updateBoundingBox();
/* 19 */     markAvailableHeight(worldIn, p_i2039_2_, 10);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureMineshaftStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */