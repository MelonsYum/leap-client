/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*    */ import net.minecraft.profiler.Profiler;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraft.world.storage.ISaveHandler;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ public class WorldServerOF extends WorldServer {
/*    */   private MinecraftServer mcServer;
/*    */   
/*    */   public WorldServerOF(MinecraftServer par1MinecraftServer, ISaveHandler par2iSaveHandler, WorldInfo worldInfo, int par4, Profiler par6Profiler) {
/* 16 */     super(par1MinecraftServer, par2iSaveHandler, worldInfo, par4, par6Profiler);
/* 17 */     this.mcServer = par1MinecraftServer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick() {
/* 25 */     super.tick();
/*    */     
/* 27 */     if (!Config.isTimeDefault())
/*    */     {
/* 29 */       fixWorldTime();
/*    */     }
/*    */     
/* 32 */     if (Config.waterOpacityChanged) {
/*    */       
/* 34 */       Config.waterOpacityChanged = false;
/* 35 */       ClearWater.updateWaterOpacity(Config.getGameSettings(), (World)this);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void updateWeather() {
/* 44 */     if (!Config.isWeatherEnabled())
/*    */     {
/* 46 */       fixWorldWeather();
/*    */     }
/*    */     
/* 49 */     super.updateWeather();
/*    */   }
/*    */ 
/*    */   
/*    */   private void fixWorldWeather() {
/* 54 */     if (this.worldInfo.isRaining() || this.worldInfo.isThundering()) {
/*    */       
/* 56 */       this.worldInfo.setRainTime(0);
/* 57 */       this.worldInfo.setRaining(false);
/* 58 */       setRainStrength(0.0F);
/* 59 */       this.worldInfo.setThunderTime(0);
/* 60 */       this.worldInfo.setThundering(false);
/* 61 */       setThunderStrength(0.0F);
/* 62 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(2, 0.0F));
/* 63 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(7, 0.0F));
/* 64 */       this.mcServer.getConfigurationManager().sendPacketToAllPlayers((Packet)new S2BPacketChangeGameState(8, 0.0F));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void fixWorldTime() {
/* 70 */     if (this.worldInfo.getGameType().getID() == 1) {
/*    */       
/* 72 */       long time = getWorldTime();
/* 73 */       long timeOfDay = time % 24000L;
/*    */       
/* 75 */       if (Config.isTimeDayOnly()) {
/*    */         
/* 77 */         if (timeOfDay <= 1000L)
/*    */         {
/* 79 */           setWorldTime(time - timeOfDay + 1001L);
/*    */         }
/*    */         
/* 82 */         if (timeOfDay >= 11000L)
/*    */         {
/* 84 */           setWorldTime(time - timeOfDay + 24001L);
/*    */         }
/*    */       } 
/*    */       
/* 88 */       if (Config.isTimeNightOnly()) {
/*    */         
/* 90 */         if (timeOfDay <= 14000L)
/*    */         {
/* 92 */           setWorldTime(time - timeOfDay + 14001L);
/*    */         }
/*    */         
/* 95 */         if (timeOfDay >= 22000L)
/*    */         {
/* 97 */           setWorldTime(time - timeOfDay + 24000L + 14001L);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\WorldServerOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */