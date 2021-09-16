/*    */ package leap.events.listeners;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class EventSendPacket
/*    */   extends Event<EventSendPacket> {
/*    */   private Packet packet;
/*    */   
/*    */   public EventSendPacket(Packet packet) {
/* 15 */     this.packet = packet;
/* 16 */     setPacket(packet);
/*    */   }
/*    */   
/*    */   public Packet getPacket() {
/* 20 */     return this.packet;
/*    */   }
/*    */   public void setPacket(Packet packet) {
/* 23 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public static Block getBlockAtPos(BlockPos inBlockPos) {
/* 27 */     IBlockState s = (Minecraft.getMinecraft()).theWorld.getBlockState(inBlockPos);
/* 28 */     return s.getBlock();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\events\listeners\EventSendPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */