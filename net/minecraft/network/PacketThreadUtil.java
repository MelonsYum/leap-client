/*    */ package net.minecraft.network;
/*    */ 
/*    */ import net.minecraft.util.IThreadListener;
/*    */ 
/*    */ 
/*    */ public class PacketThreadUtil
/*    */ {
/*    */   private static final String __OBFID = "CL_00002306";
/*    */   
/*    */   public static void func_180031_a(final Packet p_180031_0_, final INetHandler p_180031_1_, IThreadListener p_180031_2_) {
/* 11 */     if (!p_180031_2_.isCallingFromMinecraftThread()) {
/*    */       
/* 13 */       p_180031_2_.addScheduledTask(new Runnable()
/*    */           {
/*    */             private static final String __OBFID = "CL_00002305";
/*    */             
/*    */             public void run() {
/* 18 */               p_180031_0_.processPacket(p_180031_1_);
/*    */             }
/*    */           });
/* 21 */       throw ThreadQuickExitException.field_179886_a;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\PacketThreadUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */