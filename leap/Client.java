/*     */ package leap;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import leap.alts.LoadAlts;
/*     */ import leap.alts.SaveAlts;
/*     */ import leap.clickgui.save.ClickGuiSave;
/*     */ import leap.clickgui.save.SaveExit;
/*     */ import leap.clickgui.save.SaveLoad;
/*     */ import leap.commands.CommandManager;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventChat;
/*     */ import leap.events.listeners.EventKey;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.irc.IRC;
/*     */ import leap.modules.Module;
/*     */ import leap.modules.combat.AntiBots;
/*     */ import leap.modules.combat.AutoFood;
/*     */ import leap.modules.combat.AutoGapple;
/*     */ import leap.modules.combat.AutoPotion;
/*     */ import leap.modules.combat.BowAimbot;
/*     */ import leap.modules.combat.Criticals;
/*     */ import leap.modules.combat.KillAura;
/*     */ import leap.modules.combat.Regen;
/*     */ import leap.modules.combat.TargetHUD;
/*     */ import leap.modules.combat.TargetStrafe;
/*     */ import leap.modules.configs.Hypixel;
/*     */ import leap.modules.configs.Mineplex;
/*     */ import leap.modules.configs.Vanilla;
/*     */ import leap.modules.ghost.AutoClicker;
/*     */ import leap.modules.ghost.Eagle;
/*     */ import leap.modules.memes.Derp;
/*     */ import leap.modules.memes.LSD;
/*     */ import leap.modules.memes.Ligma;
/*     */ import leap.modules.movement.AirJump;
/*     */ import leap.modules.movement.AntiVoid;
/*     */ import leap.modules.movement.BowFly;
/*     */ import leap.modules.movement.DisablerModule;
/*     */ import leap.modules.movement.Fly;
/*     */ import leap.modules.movement.InvMove;
/*     */ import leap.modules.movement.LongJump;
/*     */ import leap.modules.movement.NoSlow;
/*     */ import leap.modules.movement.PearlFly;
/*     */ import leap.modules.movement.Phase;
/*     */ import leap.modules.movement.Speed;
/*     */ import leap.modules.movement.Sprint;
/*     */ import leap.modules.movement.Step;
/*     */ import leap.modules.movement.Teleport;
/*     */ import leap.modules.movement.Velocity;
/*     */ import leap.modules.player.AntiAtlas;
/*     */ import leap.modules.player.AutoArmor;
/*     */ import leap.modules.player.AutoHypixel;
/*     */ import leap.modules.player.AutoTool;
/*     */ import leap.modules.player.BedFucking;
/*     */ import leap.modules.player.CakeEater;
/*     */ import leap.modules.player.ChestAura;
/*     */ import leap.modules.player.ChestSteal;
/*     */ import leap.modules.player.IRCM;
/*     */ import leap.modules.player.InvCleaner;
/*     */ import leap.modules.player.NoFall;
/*     */ import leap.modules.player.Scaffold;
/*     */ import leap.modules.player.SpeedMine;
/*     */ import leap.modules.render.Ambiance;
/*     */ import leap.modules.render.Animations;
/*     */ import leap.modules.render.Appearance;
/*     */ import leap.modules.render.BetterChat;
/*     */ import leap.modules.render.Capes;
/*     */ import leap.modules.render.ChestESP;
/*     */ import leap.modules.render.ClickGUI;
/*     */ import leap.modules.render.DamageEffects;
/*     */ import leap.modules.render.ESP;
/*     */ import leap.modules.render.FPSBooster;
/*     */ import leap.modules.render.FakePlayer;
/*     */ import leap.modules.render.FullBright;
/*     */ import leap.modules.render.Hats;
/*     */ import leap.modules.render.Hotbar;
/*     */ import leap.modules.render.ItemPhysics;
/*     */ import leap.modules.render.KeyStrokes;
/*     */ import leap.modules.render.NameTags;
/*     */ import leap.modules.render.NotificationsModule;
/*     */ import leap.modules.render.Rotations;
/*     */ import leap.modules.render.Scoreboard;
/*     */ import leap.modules.render.Streamer;
/*     */ import leap.modules.render.TabGUI;
/*     */ import leap.notifications.Notification;
/*     */ import leap.notifications.NotificationManager;
/*     */ import leap.notifications.NotificationType;
/*     */ import leap.settings.Setting;
/*     */ import leap.ui.HUD;
/*     */ import leap.util.AudioUtil;
/*     */ import leap.util.Timer;
/*     */ import leap.util.font.CustomFontRenderer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
/*     */ import net.minecraft.network.play.server.S40PacketDisconnect;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.Display;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Client
/*     */ {
/* 120 */   public static String name = "Leap Client"; public static String version = "0.9";
/*     */   public static String realPlayerName;
/* 122 */   public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>();
/* 123 */   public static HUD hud = new HUD();
/* 124 */   public static final Client INSTANCE = new Client();
/*     */   public static SaveLoad saveLoad;
/*     */   public static SaveExit saveExit;
/*     */   public static SaveAlts saveAlts;
/*     */   public static LoadAlts loadAlts;
/*     */   public static ClickGUI clickGui;
/*     */   public static ClickGuiSave clickGuiSave;
/*     */   private static IRC irc;
/* 132 */   private static final Timer inventoryTimer = new Timer();
/* 133 */   public static Timer playTimer = new Timer();
/*     */   public static CustomFontRenderer customFontRenderer;
/*     */   public static CustomFontRenderer customFontRendererBig;
/*     */   public static CustomFontRenderer customFontRendererSmall;
/*     */   public static CustomFontRenderer customFontRendererExtraBig;
/* 138 */   public static NotificationManager notificationManager = new NotificationManager();
/* 139 */   public static CommandManager commandManager = new CommandManager();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void startup() {
/* 145 */     System.out.println("Starting " + name + " " + version);
/* 146 */     Display.setTitle(String.valueOf(name) + " v" + version);
/* 147 */     if ((Minecraft.getMinecraft()).thePlayer != null) {
/* 148 */       realPlayerName = (Minecraft.getMinecraft()).thePlayer.getName();
/*     */     }
/*     */     
/* 151 */     modules.add(new Fly());
/* 152 */     modules.add(new Sprint());
/* 153 */     modules.add(new FullBright());
/* 154 */     modules.add(new NoFall());
/* 155 */     modules.add(new TabGUI());
/* 156 */     modules.add(new KillAura());
/* 157 */     modules.add(new AutoTool());
/* 158 */     modules.add(new AntiBots());
/* 159 */     modules.add(new AutoClicker());
/* 160 */     modules.add(new NoSlow());
/* 161 */     modules.add(new Regen());
/* 162 */     modules.add(new ChestSteal());
/* 163 */     modules.add(new LSD());
/* 164 */     modules.add(new Velocity());
/* 165 */     modules.add(new LongJump());
/* 166 */     modules.add(new Phase());
/* 167 */     modules.add(new HUD());
/* 168 */     modules.add(new TargetHUD());
/* 169 */     modules.add(new Criticals());
/* 170 */     modules.add(new AirJump());
/* 171 */     modules.add(new Scaffold());
/* 172 */     modules.add(new Scoreboard());
/* 173 */     modules.add(new SpeedMine());
/* 174 */     modules.add(new Ambiance());
/* 175 */     modules.add(new AntiVoid());
/* 176 */     modules.add(new FakePlayer());
/* 177 */     modules.add(new DisablerModule());
/* 178 */     modules.add(new Eagle());
/* 179 */     modules.add(new KeyStrokes());
/* 180 */     modules.add(new Teleport());
/*     */     
/* 182 */     modules.add(new InvCleaner());
/*     */     
/* 184 */     modules.add(new Speed());
/* 185 */     modules.add(new InvMove());
/* 186 */     modules.add(new AutoGapple());
/* 187 */     modules.add(new AutoFood());
/* 188 */     modules.add(new Capes());
/* 189 */     modules.add(new TargetStrafe());
/* 190 */     modules.add(new AutoPotion());
/* 191 */     modules.add(new ItemPhysics());
/* 192 */     modules.add(new CakeEater());
/* 193 */     modules.add(new BedFucking());
/* 194 */     modules.add(new BowFly());
/* 195 */     modules.add(new PearlFly());
/* 196 */     modules.add(new ChestESP());
/* 197 */     modules.add(new BetterChat());
/* 198 */     modules.add(new BowAimbot());
/* 199 */     modules.add(new AntiAtlas());
/* 200 */     modules.add(new Animations());
/* 201 */     modules.add(new Step());
/* 202 */     modules.add(new AutoArmor());
/* 203 */     modules.add(new FPSBooster());
/* 204 */     modules.add(new ESP());
/* 205 */     modules.add(new Streamer());
/* 206 */     modules.add(new AutoHypixel());
/* 207 */     modules.add(new Rotations());
/* 208 */     modules.add(new Ligma());
/* 209 */     modules.add(new Hotbar());
/* 210 */     modules.add(new Hats());
/* 211 */     modules.add(new DamageEffects());
/* 212 */     modules.add(new Appearance());
/* 213 */     modules.add(new NotificationsModule());
/* 214 */     modules.add(new ChestAura());
/* 215 */     modules.add(new IRCM());
/* 216 */     modules.add(new NameTags());
/* 217 */     modules.add(new Derp());
/* 218 */     modules.add(clickGui = new ClickGUI());
/*     */ 
/*     */     
/* 221 */     modules.add(new Hypixel());
/* 222 */     modules.add(new Mineplex());
/* 223 */     modules.add(new Vanilla());
/*     */     
/* 225 */     saveLoad = new SaveLoad();
/* 226 */     loadAlts = new LoadAlts();
/* 227 */     addCapes();
/* 228 */     AudioUtil.init();
/* 229 */     customFontRendererBig = new CustomFontRenderer(new Font("Noto", 0, 24), true, true);
/* 230 */     customFontRenderer = new CustomFontRenderer(new Font("Noto", 0, 18), true, true);
/* 231 */     customFontRendererSmall = new CustomFontRenderer(new Font("Noto", 0, 14), true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void shutdown() {
/* 237 */     saveExit = new SaveExit();
/* 238 */     saveAlts = new SaveAlts();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void startIRC(String username) {
/* 243 */     irc = new IRC(username, "irc.us.ircnet.net", 6969);
/*     */   }
/*     */ 
/*     */   
/*     */   public IRC getIrc() {
/* 248 */     return irc;
/*     */   }
/*     */   
/*     */   public static boolean invCooldownElapsed(long time) {
/* 252 */     return inventoryTimer.hasTimeElapsed(time, true);
/*     */   }
/*     */   
/*     */   public static void onEvent(Event e) {
/* 256 */     if (e instanceof EventChat) {
/* 257 */       commandManager.handleChat((EventChat)e);
/*     */     }
/*     */     
/* 260 */     for (Module m : modules) {
/*     */       
/* 262 */       if (!m.toggled) {
/* 263 */         m.onStandby();
/*     */       }
/*     */       
/* 266 */       Minecraft mc = Minecraft.getMinecraft();
/*     */ 
/*     */       
/* 269 */       if (!m.toggled) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 274 */       if (e instanceof EventReceivePacket) {
/*     */         
/* 276 */         EventReceivePacket event = (EventReceivePacket)e;
/*     */         
/* 278 */         if (event.getPacket() instanceof S40PacketDisconnect) {
/*     */           
/* 280 */           S40PacketDisconnect packet = (S40PacketDisconnect)event.getPacket();
/*     */           
/* 282 */           mc.thePlayer.addChatComponentMessage(S40PacketDisconnect.reason);
/*     */           
/* 284 */           event.setCancelled(true);
/*     */           
/*     */           return;
/*     */         } 
/* 288 */         if (event.getPacket() instanceof S3BPacketScoreboardObjective) {
/* 289 */           S3BPacketScoreboardObjective packet = (S3BPacketScoreboardObjective)event.getPacket();
/*     */           
/* 291 */           S3BPacketScoreboardObjective.field_149341_b = "§bLeap Client";
/*     */         } 
/*     */         
/* 294 */         if (((EventReceivePacket)e).getPacket() instanceof net.minecraft.network.play.server.S08PacketPlayerPosLook)
/*     */         {
/* 296 */           for (Module ms : modules) {
/* 297 */             if (ms.name == "Speed" || ms.name == "Scaffold") {
/* 298 */               ms.toggled = false;
/* 299 */               Notification.post(NotificationType.WARNING, "WARNING:", String.valueOf(ms.name) + " was disabled due to a lagback!", 3.0D);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 305 */       m.onEvent(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void keyPress(int key) {
/* 312 */     onEvent((Event)new EventKey(key));
/* 313 */     for (Module m : modules) {
/* 314 */       if (m.getKey() == key) {
/* 315 */         m.toggle();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Module getModule(String name) {
/* 322 */     for (Module m : modules) {
/* 323 */       if (m.name.equalsIgnoreCase(name)) {
/* 324 */         return m;
/*     */       }
/*     */     } 
/* 327 */     return null;
/*     */   }
/*     */   
/*     */   public static List<Module> getModulesByCategory(Module.Category c) {
/* 331 */     List<Module> modules = new ArrayList<>();
/*     */     
/* 333 */     for (Module m : Client.modules) {
/* 334 */       if (m.category == c) {
/* 335 */         modules.add(m);
/*     */       }
/*     */     } 
/* 338 */     return modules;
/*     */   }
/*     */   
/*     */   public static void addChatMessage(String message) {
/* 342 */     message = "§b" + name + "§f: " + message;
/*     */     
/* 344 */     (Minecraft.getMinecraft()).thePlayer.addChatMessage((IChatComponent)new ChatComponentText(message));
/*     */   }
/*     */   
/*     */   public static List<Setting> getSettingsByCategory(Module.Category c) {
/* 348 */     List<Setting> setting = new ArrayList<>();
/*     */     
/* 350 */     for (Module m : modules) {
/* 351 */       if (m.category == c)
/* 352 */         setting.addAll(m.settings); 
/*     */     } 
/* 354 */     return setting;
/*     */   }
/*     */   
/*     */   public static Setting getSettingByName(Module mod, String name) {
/* 358 */     for (Module m : modules) {
/* 359 */       for (Setting set : m.settings) {
/* 360 */         if (set.name.equalsIgnoreCase(name) && m.name == mod.name) {
/* 361 */           return set;
/*     */         }
/*     */       } 
/*     */     } 
/* 365 */     System.err.println("[Leap] Error Setting NOT found: '" + name + "'!");
/* 366 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final Client getInstance() {
/* 371 */     return INSTANCE;
/*     */   }
/*     */   
/*     */   public static void addCapes() {
/* 375 */     String url = "https://minecraft.novaskin.me/skin/2827633143/download";
/* 376 */     ThreadDownloadImageData image = new ThreadDownloadImageData(null, url, null, null);
/*     */     
/* 378 */     (Minecraft.getMinecraft()).renderEngine.loadTexture(new ResourceLocation("cloaks/" + (Minecraft.getMinecraft()).session.getUsername()), (ITextureObject)image);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */