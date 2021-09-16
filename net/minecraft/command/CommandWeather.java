/*    */ package net.minecraft.command;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ public class CommandWeather
/*    */   extends CommandBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00001185";
/*    */   
/*    */   public String getCommandName() {
/* 16 */     return "weather";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRequiredPermissionLevel() {
/* 24 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommandUsage(ICommandSender sender) {
/* 29 */     return "commands.weather.usage";
/*    */   }
/*    */ 
/*    */   
/*    */   public void processCommand(ICommandSender sender, String[] args) throws CommandException {
/* 34 */     if (args.length >= 1 && args.length <= 2) {
/*    */       
/* 36 */       int var3 = (300 + (new Random()).nextInt(600)) * 20;
/*    */       
/* 38 */       if (args.length >= 2)
/*    */       {
/* 40 */         var3 = parseInt(args[1], 1, 1000000) * 20;
/*    */       }
/*    */       
/* 43 */       WorldServer var4 = (MinecraftServer.getServer()).worldServers[0];
/* 44 */       WorldInfo var5 = var4.getWorldInfo();
/*    */       
/* 46 */       if ("clear".equalsIgnoreCase(args[0]))
/*    */       {
/* 48 */         var5.func_176142_i(var3);
/* 49 */         var5.setRainTime(0);
/* 50 */         var5.setThunderTime(0);
/* 51 */         var5.setRaining(false);
/* 52 */         var5.setThundering(false);
/* 53 */         notifyOperators(sender, this, "commands.weather.clear", new Object[0]);
/*    */       }
/* 55 */       else if ("rain".equalsIgnoreCase(args[0]))
/*    */       {
/* 57 */         var5.func_176142_i(0);
/* 58 */         var5.setRainTime(var3);
/* 59 */         var5.setThunderTime(var3);
/* 60 */         var5.setRaining(true);
/* 61 */         var5.setThundering(false);
/* 62 */         notifyOperators(sender, this, "commands.weather.rain", new Object[0]);
/*    */       }
/*    */       else
/*    */       {
/* 66 */         if (!"thunder".equalsIgnoreCase(args[0]))
/*    */         {
/* 68 */           throw new WrongUsageException("commands.weather.usage", new Object[0]);
/*    */         }
/*    */         
/* 71 */         var5.func_176142_i(0);
/* 72 */         var5.setRainTime(var3);
/* 73 */         var5.setThunderTime(var3);
/* 74 */         var5.setRaining(true);
/* 75 */         var5.setThundering(true);
/* 76 */         notifyOperators(sender, this, "commands.weather.thunder", new Object[0]);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 81 */       throw new WrongUsageException("commands.weather.usage", new Object[0]);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
/* 87 */     return (args.length == 1) ? getListOfStringsMatchingLastWord(args, new String[] { "clear", "rain", "thunder" }) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandWeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */