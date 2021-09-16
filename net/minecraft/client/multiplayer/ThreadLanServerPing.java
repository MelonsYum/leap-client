/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ThreadLanServerPing
/*     */   extends Thread {
/*  13 */   private static final AtomicInteger field_148658_a = new AtomicInteger(0);
/*  14 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private final String motd;
/*     */   
/*     */   private final DatagramSocket socket;
/*     */   
/*     */   private boolean isStopping = true;
/*     */   private final String address;
/*     */   private static final String __OBFID = "CL_00001137";
/*     */   
/*     */   public ThreadLanServerPing(String p_i1321_1_, String p_i1321_2_) throws IOException {
/*  25 */     super("LanServerPinger #" + field_148658_a.incrementAndGet());
/*  26 */     this.motd = p_i1321_1_;
/*  27 */     this.address = p_i1321_2_;
/*  28 */     setDaemon(true);
/*  29 */     this.socket = new DatagramSocket();
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  34 */     String var1 = getPingResponse(this.motd, this.address);
/*  35 */     byte[] var2 = var1.getBytes();
/*     */     
/*  37 */     while (!isInterrupted() && this.isStopping) {
/*     */ 
/*     */       
/*     */       try {
/*  41 */         InetAddress var3 = InetAddress.getByName("224.0.2.60");
/*  42 */         DatagramPacket var4 = new DatagramPacket(var2, var2.length, var3, 4445);
/*  43 */         this.socket.send(var4);
/*     */       }
/*  45 */       catch (IOException var6) {
/*     */         
/*  47 */         logger.warn("LanServerPinger: " + var6.getMessage());
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/*     */       try {
/*  53 */         sleep(1500L);
/*     */       }
/*  55 */       catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interrupt() {
/*  64 */     super.interrupt();
/*  65 */     this.isStopping = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getPingResponse(String p_77525_0_, String p_77525_1_) {
/*  70 */     return "[MOTD]" + p_77525_0_ + "[/MOTD][AD]" + p_77525_1_ + "[/AD]";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getMotdFromPingResponse(String p_77524_0_) {
/*  75 */     int var1 = p_77524_0_.indexOf("[MOTD]");
/*     */     
/*  77 */     if (var1 < 0)
/*     */     {
/*  79 */       return "missing no";
/*     */     }
/*     */ 
/*     */     
/*  83 */     int var2 = p_77524_0_.indexOf("[/MOTD]", var1 + "[MOTD]".length());
/*  84 */     return (var2 < var1) ? "missing no" : p_77524_0_.substring(var1 + "[MOTD]".length(), var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getAdFromPingResponse(String p_77523_0_) {
/*  90 */     int var1 = p_77523_0_.indexOf("[/MOTD]");
/*     */     
/*  92 */     if (var1 < 0)
/*     */     {
/*  94 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  98 */     int var2 = p_77523_0_.indexOf("[/MOTD]", var1 + "[/MOTD]".length());
/*     */     
/* 100 */     if (var2 >= 0)
/*     */     {
/* 102 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 106 */     int var3 = p_77523_0_.indexOf("[AD]", var1 + "[/MOTD]".length());
/*     */     
/* 108 */     if (var3 < 0)
/*     */     {
/* 110 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 114 */     int var4 = p_77523_0_.indexOf("[/AD]", var3 + "[AD]".length());
/* 115 */     return (var4 < var3) ? null : p_77523_0_.substring(var3 + "[AD]".length(), var4);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\ThreadLanServerPing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */