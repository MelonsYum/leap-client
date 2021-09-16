/*     */ package net.minecraft.client.network;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.MulticastSocket;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ThreadLanServerPing;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class LanServerDetector
/*     */ {
/*  20 */   private static final AtomicInteger field_148551_a = new AtomicInteger(0);
/*  21 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private static final String __OBFID = "CL_00001133";
/*     */   
/*     */   public static class LanServer
/*     */   {
/*     */     private String lanServerMotd;
/*     */     private String lanServerIpPort;
/*     */     private long timeLastSeen;
/*     */     private static final String __OBFID = "CL_00001134";
/*     */     
/*     */     public LanServer(String p_i1319_1_, String p_i1319_2_) {
/*  33 */       this.lanServerMotd = p_i1319_1_;
/*  34 */       this.lanServerIpPort = p_i1319_2_;
/*  35 */       this.timeLastSeen = Minecraft.getSystemTime();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getServerMotd() {
/*  40 */       return this.lanServerMotd;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getServerIpPort() {
/*  45 */       return this.lanServerIpPort;
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateLastSeen() {
/*  50 */       this.timeLastSeen = Minecraft.getSystemTime();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LanServerList {
/*     */     public LanServerList() {
/*  56 */       this.listOfLanServers = Lists.newArrayList();
/*     */     }
/*     */     
/*     */     private List listOfLanServers;
/*     */     
/*     */     public synchronized boolean getWasUpdated() {
/*  62 */       return this.wasUpdated;
/*     */     }
/*     */     boolean wasUpdated; private static final String __OBFID = "CL_00001136";
/*     */     
/*     */     public synchronized void setWasNotUpdated() {
/*  67 */       this.wasUpdated = false;
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized List getLanServers() {
/*  72 */       return Collections.unmodifiableList(this.listOfLanServers);
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void func_77551_a(String p_77551_1_, InetAddress p_77551_2_) {
/*  77 */       String var3 = ThreadLanServerPing.getMotdFromPingResponse(p_77551_1_);
/*  78 */       String var4 = ThreadLanServerPing.getAdFromPingResponse(p_77551_1_);
/*     */       
/*  80 */       if (var4 != null) {
/*     */         
/*  82 */         var4 = String.valueOf(p_77551_2_.getHostAddress()) + ":" + var4;
/*  83 */         boolean var5 = false;
/*  84 */         Iterator<LanServerDetector.LanServer> var6 = this.listOfLanServers.iterator();
/*     */         
/*  86 */         while (var6.hasNext()) {
/*     */           
/*  88 */           LanServerDetector.LanServer var7 = var6.next();
/*     */           
/*  90 */           if (var7.getServerIpPort().equals(var4)) {
/*     */             
/*  92 */             var7.updateLastSeen();
/*  93 */             var5 = true;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*  98 */         if (!var5) {
/*     */           
/* 100 */           this.listOfLanServers.add(new LanServerDetector.LanServer(var3, var4));
/* 101 */           this.wasUpdated = true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ThreadLanServerFind
/*     */     extends Thread
/*     */   {
/*     */     private final LanServerDetector.LanServerList localServerList;
/*     */     private final InetAddress broadcastAddress;
/*     */     private final MulticastSocket socket;
/*     */     private static final String __OBFID = "CL_00001135";
/*     */     
/*     */     public ThreadLanServerFind(LanServerDetector.LanServerList p_i1320_1_) throws IOException {
/* 116 */       super("LanServerDetector #" + LanServerDetector.field_148551_a.incrementAndGet());
/* 117 */       this.localServerList = p_i1320_1_;
/* 118 */       setDaemon(true);
/* 119 */       this.socket = new MulticastSocket(4445);
/* 120 */       this.broadcastAddress = InetAddress.getByName("224.0.2.60");
/* 121 */       this.socket.setSoTimeout(5000);
/* 122 */       this.socket.joinGroup(this.broadcastAddress);
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 127 */       byte[] var2 = new byte[1024];
/*     */       
/* 129 */       while (!isInterrupted()) {
/*     */         
/* 131 */         DatagramPacket var1 = new DatagramPacket(var2, var2.length);
/*     */ 
/*     */         
/*     */         try {
/* 135 */           this.socket.receive(var1);
/*     */         }
/* 137 */         catch (SocketTimeoutException var5) {
/*     */           
/*     */           continue;
/*     */         }
/* 141 */         catch (IOException var6) {
/*     */           
/* 143 */           LanServerDetector.logger.error("Couldn't ping server", var6);
/*     */           
/*     */           break;
/*     */         } 
/* 147 */         String var3 = new String(var1.getData(), var1.getOffset(), var1.getLength());
/* 148 */         LanServerDetector.logger.debug(var1.getAddress() + ": " + var3);
/* 149 */         this.localServerList.func_77551_a(var3, var1.getAddress());
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 154 */         this.socket.leaveGroup(this.broadcastAddress);
/*     */       }
/* 156 */       catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 161 */       this.socket.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\network\LanServerDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */