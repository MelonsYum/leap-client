/*    */ package net.minecraft.block.state;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BlockWorldState
/*    */ {
/*    */   private final World world;
/*    */   private final BlockPos pos;
/*    */   private IBlockState field_177514_c;
/*    */   private TileEntity field_177511_d;
/*    */   private boolean field_177512_e;
/*    */   private static final String __OBFID = "CL_00002026";
/*    */   
/*    */   public BlockWorldState(World worldIn, BlockPos p_i45659_2_) {
/* 19 */     this.world = worldIn;
/* 20 */     this.pos = p_i45659_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState func_177509_a() {
/* 25 */     if (this.field_177514_c == null && this.world.isBlockLoaded(this.pos))
/*    */     {
/* 27 */       this.field_177514_c = this.world.getBlockState(this.pos);
/*    */     }
/*    */     
/* 30 */     return this.field_177514_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity func_177507_b() {
/* 35 */     if (this.field_177511_d == null && !this.field_177512_e) {
/*    */       
/* 37 */       this.field_177511_d = this.world.getTileEntity(this.pos);
/* 38 */       this.field_177512_e = true;
/*    */     } 
/*    */     
/* 41 */     return this.field_177511_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos getPos() {
/* 46 */     return this.pos;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Predicate hasState(final Predicate p_177510_0_) {
/* 51 */     return new Predicate()
/*    */       {
/*    */         private static final String __OBFID = "CL_00002025";
/*    */         
/*    */         public boolean func_177503_a(BlockWorldState p_177503_1_) {
/* 56 */           return (p_177503_1_ != null && p_177510_0_.apply(p_177503_1_.func_177509_a()));
/*    */         }
/*    */         
/*    */         public boolean apply(Object p_apply_1_) {
/* 60 */           return func_177503_a((BlockWorldState)p_apply_1_);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\BlockWorldState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */