/*    */ package net.minecraft.world.demo;
/*    */ 
/*    */ import net.minecraft.profiler.Profiler;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ import net.minecraft.world.WorldType;
/*    */ import net.minecraft.world.storage.ISaveHandler;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ public class DemoWorldServer
/*    */   extends WorldServer {
/* 13 */   private static final long demoWorldSeed = "North Carolina".hashCode();
/* 14 */   public static final WorldSettings demoWorldSettings = (new WorldSettings(demoWorldSeed, WorldSettings.GameType.SURVIVAL, true, false, WorldType.DEFAULT)).enableBonusChest();
/*    */   
/*    */   private static final String __OBFID = "CL_00001428";
/*    */   
/*    */   public DemoWorldServer(MinecraftServer server, ISaveHandler saveHandlerIn, WorldInfo worldInfoIn, int dimensionId, Profiler profilerIn) {
/* 19 */     super(server, saveHandlerIn, worldInfoIn, dimensionId, profilerIn);
/* 20 */     this.worldInfo.populateFromWorldSettings(demoWorldSettings);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\demo\DemoWorldServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */