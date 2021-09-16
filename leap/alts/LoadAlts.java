/*    */ package leap.alts;
/*    */ 
/*    */ import com.mojang.authlib.Agent;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
/*    */ import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.net.Proxy;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import leap.Client;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoadAlts
/*    */ {
/*    */   private File dir;
/*    */   private File dataFile;
/* 31 */   public int currentTab = (Module.Category.values()).length - 1;
/*    */   
/*    */   private AltLoginThread loginThread;
/* 34 */   Module.Category category = Module.Category.values()[this.currentTab];
/* 35 */   List<Module> modules = Client.getModulesByCategory(this.category);
/*    */   
/*    */   public LoadAlts() {
/* 38 */     this.dir = new File((Minecraft.getMinecraft()).mcDataDir, Client.name);
/* 39 */     if (!this.dir.exists()) {
/* 40 */       this.dir.mkdir();
/*    */     }
/* 42 */     this.dataFile = new File(this.dir, "alts.txt");
/* 43 */     if (!this.dataFile.exists()) {
/*    */       
/* 45 */       try { this.dataFile.createNewFile(); }
/* 46 */       catch (IOException e) { e.printStackTrace(); }
/*    */     
/*    */     }
/* 49 */     load();
/*    */   }
/*    */   
/*    */   public void load() {
/* 53 */     ArrayList<String> lines = new ArrayList<>();
/*    */     
/*    */     try {
/* 56 */       BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
/* 57 */       String line = reader.readLine();
/* 58 */       while (line != null) {
/* 59 */         lines.add(line);
/* 60 */         line = reader.readLine();
/*    */       } 
/* 62 */       reader.close();
/* 63 */     } catch (Exception e) {
/* 64 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 67 */     for (String s : lines) {
/* 68 */       String[] args = s.split(" ");
/* 69 */       if (args.length == 2) {
/* 70 */         YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
/* 71 */         YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)service.createUserAuthentication(Agent.MINECRAFT);
/*    */         
/* 73 */         auth.setUsername(args[0]);
/* 74 */         auth.setPassword(args[1]);
/*    */         
/* 76 */         AltManager.registry.add(new Alt(args[0], args[1]));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\LoadAlts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */