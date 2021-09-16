/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.client.network.NetHandlerPlayClient;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PlayerControllerOF
/*    */   extends PlayerControllerMP
/*    */ {
/*    */   private boolean acting = false;
/*    */   
/*    */   public PlayerControllerOF(Minecraft mcIn, NetHandlerPlayClient netHandler) {
/* 21 */     super(mcIn, netHandler);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_180511_b(BlockPos loc, EnumFacing face) {
/* 26 */     this.acting = true;
/* 27 */     boolean res = super.func_180511_b(loc, face);
/* 28 */     this.acting = false;
/* 29 */     return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_180512_c(BlockPos posBlock, EnumFacing directionFacing) {
/* 34 */     this.acting = true;
/* 35 */     boolean res = super.func_180512_c(posBlock, directionFacing);
/* 36 */     this.acting = false;
/* 37 */     return res;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean sendUseItem(EntityPlayer player, World worldIn, ItemStack stack) {
/* 45 */     this.acting = true;
/* 46 */     boolean res = super.sendUseItem(player, worldIn, stack);
/* 47 */     this.acting = false;
/* 48 */     return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_178890_a(EntityPlayerSP player, WorldClient worldIn, ItemStack stack, BlockPos pos, EnumFacing facing, Vec3 vec) {
/* 53 */     this.acting = true;
/* 54 */     boolean res = super.func_178890_a(player, worldIn, stack, pos, facing, vec);
/* 55 */     this.acting = false;
/* 56 */     return res;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isActing() {
/* 61 */     return this.acting;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerControllerOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */