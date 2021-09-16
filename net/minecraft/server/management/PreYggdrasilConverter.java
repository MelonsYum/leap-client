/*    */ package net.minecraft.server.management;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.collect.Iterators;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.authlib.Agent;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.ProfileLookupCallback;
/*    */ import java.io.File;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.StringUtils;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class PreYggdrasilConverter {
/* 21 */   private static final Logger LOGGER = LogManager.getLogger();
/* 22 */   public static final File OLD_IPBAN_FILE = new File("banned-ips.txt");
/* 23 */   public static final File OLD_PLAYERBAN_FILE = new File("banned-players.txt");
/* 24 */   public static final File OLD_OPS_FILE = new File("ops.txt");
/* 25 */   public static final File OLD_WHITELIST_FILE = new File("white-list.txt");
/*    */   
/*    */   private static final String __OBFID = "CL_00001882";
/*    */   
/*    */   private static void lookupNames(MinecraftServer server, Collection names, ProfileLookupCallback callback) {
/* 30 */     String[] var3 = (String[])Iterators.toArray((Iterator)Iterators.filter(names.iterator(), new Predicate()
/*    */           {
/*    */             private static final String __OBFID = "CL_00001881";
/*    */             
/*    */             public boolean func_152733_a(String p_152733_1_) {
/* 35 */               return !StringUtils.isNullOrEmpty(p_152733_1_);
/*    */             }
/*    */             
/*    */             public boolean apply(Object p_apply_1_) {
/* 39 */               return func_152733_a((String)p_apply_1_);
/*    */             }
/* 41 */           }), String.class);
/*    */     
/* 43 */     if (server.isServerInOnlineMode()) {
/*    */       
/* 45 */       server.getGameProfileRepository().findProfilesByNames(var3, Agent.MINECRAFT, callback);
/*    */     }
/*    */     else {
/*    */       
/* 49 */       String[] var4 = var3;
/* 50 */       int var5 = var3.length;
/*    */       
/* 52 */       for (int var6 = 0; var6 < var5; var6++) {
/*    */         
/* 54 */         String var7 = var4[var6];
/* 55 */         UUID var8 = EntityPlayer.getUUID(new GameProfile(null, var7));
/* 56 */         GameProfile var9 = new GameProfile(var8, var7);
/* 57 */         callback.onProfileLookupSucceeded(var9);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static String func_152719_a(String p_152719_0_) {
/* 64 */     if (!StringUtils.isNullOrEmpty(p_152719_0_) && p_152719_0_.length() <= 16) {
/*    */       
/* 66 */       final MinecraftServer var1 = MinecraftServer.getServer();
/* 67 */       GameProfile var2 = var1.getPlayerProfileCache().getGameProfileForUsername(p_152719_0_);
/*    */       
/* 69 */       if (var2 != null && var2.getId() != null)
/*    */       {
/* 71 */         return var2.getId().toString();
/*    */       }
/* 73 */       if (!var1.isSinglePlayer() && var1.isServerInOnlineMode()) {
/*    */         
/* 75 */         final ArrayList<GameProfile> var3 = Lists.newArrayList();
/* 76 */         ProfileLookupCallback var4 = new ProfileLookupCallback()
/*    */           {
/*    */             private static final String __OBFID = "CL_00001880";
/*    */             
/*    */             public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
/* 81 */               var1.getPlayerProfileCache().func_152649_a(p_onProfileLookupSucceeded_1_);
/* 82 */               var3.add(p_onProfileLookupSucceeded_1_);
/*    */             }
/*    */             
/*    */             public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
/* 86 */               PreYggdrasilConverter.LOGGER.warn("Could not lookup user whitelist entry for " + p_onProfileLookupFailed_1_.getName(), p_onProfileLookupFailed_2_);
/*    */             }
/*    */           };
/* 89 */         lookupNames(var1, Lists.newArrayList((Object[])new String[] { p_152719_0_ }, ), var4);
/* 90 */         return (var3.size() > 0 && ((GameProfile)var3.get(0)).getId() != null) ? ((GameProfile)var3.get(0)).getId().toString() : "";
/*    */       } 
/*    */ 
/*    */       
/* 94 */       return EntityPlayer.getUUID(new GameProfile(null, p_152719_0_)).toString();
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 99 */     return p_152719_0_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\PreYggdrasilConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */