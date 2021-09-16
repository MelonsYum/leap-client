/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ServerList {
/*  16 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private final Minecraft mc;
/*     */ 
/*     */   
/*  22 */   private final List servers = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00000891";
/*     */   
/*     */   public ServerList(Minecraft mcIn) {
/*  27 */     this.mc = mcIn;
/*  28 */     loadServerList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadServerList() {
/*     */     try {
/*  39 */       this.servers.clear();
/*  40 */       NBTTagCompound var1 = CompressedStreamTools.read(new File(this.mc.mcDataDir, "servers.dat"));
/*     */       
/*  42 */       if (var1 == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  47 */       NBTTagList var2 = var1.getTagList("servers", 10);
/*     */       
/*  49 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*     */       {
/*  51 */         this.servers.add(ServerData.getServerDataFromNBTCompound(var2.getCompoundTagAt(var3)));
/*     */       }
/*     */     }
/*  54 */     catch (Exception var4) {
/*     */       
/*  56 */       logger.error("Couldn't load server list", var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveServerList() {
/*     */     try {
/*  68 */       NBTTagList var1 = new NBTTagList();
/*  69 */       Iterator<ServerData> var2 = this.servers.iterator();
/*     */       
/*  71 */       while (var2.hasNext()) {
/*     */         
/*  73 */         ServerData var3 = var2.next();
/*  74 */         var1.appendTag((NBTBase)var3.getNBTCompound());
/*     */       } 
/*     */       
/*  77 */       NBTTagCompound var5 = new NBTTagCompound();
/*  78 */       var5.setTag("servers", (NBTBase)var1);
/*  79 */       CompressedStreamTools.safeWrite(var5, new File(this.mc.mcDataDir, "servers.dat"));
/*     */     }
/*  81 */     catch (Exception var4) {
/*     */       
/*  83 */       logger.error("Couldn't save server list", var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServerData getServerData(int p_78850_1_) {
/*  92 */     return this.servers.get(p_78850_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeServerData(int p_78851_1_) {
/* 100 */     this.servers.remove(p_78851_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addServerData(ServerData p_78849_1_) {
/* 108 */     this.servers.add(p_78849_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int countServers() {
/* 116 */     return this.servers.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void swapServers(int p_78857_1_, int p_78857_2_) {
/* 124 */     ServerData var3 = getServerData(p_78857_1_);
/* 125 */     this.servers.set(p_78857_1_, getServerData(p_78857_2_));
/* 126 */     this.servers.set(p_78857_2_, var3);
/* 127 */     saveServerList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_147413_a(int p_147413_1_, ServerData p_147413_2_) {
/* 132 */     this.servers.set(p_147413_1_, p_147413_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_147414_b(ServerData p_147414_0_) {
/* 137 */     ServerList var1 = new ServerList(Minecraft.getMinecraft());
/* 138 */     var1.loadServerList();
/*     */     
/* 140 */     for (int var2 = 0; var2 < var1.countServers(); var2++) {
/*     */       
/* 142 */       ServerData var3 = var1.getServerData(var2);
/*     */       
/* 144 */       if (var3.serverName.equals(p_147414_0_.serverName) && var3.serverIP.equals(p_147414_0_.serverIP)) {
/*     */         
/* 146 */         var1.func_147413_a(var2, p_147414_0_);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 151 */     var1.saveServerList();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\ServerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */