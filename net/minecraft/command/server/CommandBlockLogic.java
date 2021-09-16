/*     */ package net.minecraft.command.server;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.command.CommandResultStats;
/*     */ import net.minecraft.command.ICommandManager;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class CommandBlockLogic
/*     */   implements ICommandSender
/*     */ {
/*  23 */   private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");
/*     */ 
/*     */   
/*     */   private int successCount;
/*     */   
/*     */   private boolean trackOutput = true;
/*     */   
/*  30 */   private IChatComponent lastOutput = null;
/*     */ 
/*     */   
/*  33 */   private String commandStored = "";
/*     */ 
/*     */   
/*  36 */   private String customName = "@";
/*  37 */   private final CommandResultStats field_175575_g = new CommandResultStats();
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000128";
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSuccessCount() {
/*  45 */     return this.successCount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent getLastOutput() {
/*  53 */     return this.lastOutput;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeDataToNBT(NBTTagCompound p_145758_1_) {
/*  61 */     p_145758_1_.setString("Command", this.commandStored);
/*  62 */     p_145758_1_.setInteger("SuccessCount", this.successCount);
/*  63 */     p_145758_1_.setString("CustomName", this.customName);
/*  64 */     p_145758_1_.setBoolean("TrackOutput", this.trackOutput);
/*     */     
/*  66 */     if (this.lastOutput != null && this.trackOutput)
/*     */     {
/*  68 */       p_145758_1_.setString("LastOutput", IChatComponent.Serializer.componentToJson(this.lastOutput));
/*     */     }
/*     */     
/*  71 */     this.field_175575_g.func_179670_b(p_145758_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readDataFromNBT(NBTTagCompound p_145759_1_) {
/*  79 */     this.commandStored = p_145759_1_.getString("Command");
/*  80 */     this.successCount = p_145759_1_.getInteger("SuccessCount");
/*     */     
/*  82 */     if (p_145759_1_.hasKey("CustomName", 8))
/*     */     {
/*  84 */       this.customName = p_145759_1_.getString("CustomName");
/*     */     }
/*     */     
/*  87 */     if (p_145759_1_.hasKey("TrackOutput", 1))
/*     */     {
/*  89 */       this.trackOutput = p_145759_1_.getBoolean("TrackOutput");
/*     */     }
/*     */     
/*  92 */     if (p_145759_1_.hasKey("LastOutput", 8) && this.trackOutput)
/*     */     {
/*  94 */       this.lastOutput = IChatComponent.Serializer.jsonToComponent(p_145759_1_.getString("LastOutput"));
/*     */     }
/*     */     
/*  97 */     this.field_175575_g.func_179668_a(p_145759_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 105 */     return (permissionLevel <= 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommand(String p_145752_1_) {
/* 113 */     this.commandStored = p_145752_1_;
/* 114 */     this.successCount = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomName() {
/* 122 */     return this.commandStored;
/*     */   }
/*     */ 
/*     */   
/*     */   public void trigger(World worldIn) {
/* 127 */     if (worldIn.isRemote)
/*     */     {
/* 129 */       this.successCount = 0;
/*     */     }
/*     */     
/* 132 */     MinecraftServer var2 = MinecraftServer.getServer();
/*     */     
/* 134 */     if (var2 != null && var2.func_175578_N() && var2.isCommandBlockEnabled()) {
/*     */       
/* 136 */       ICommandManager var3 = var2.getCommandManager();
/*     */ 
/*     */       
/*     */       try {
/* 140 */         this.lastOutput = null;
/* 141 */         this.successCount = var3.executeCommand(this, this.commandStored);
/*     */       }
/* 143 */       catch (Throwable var7) {
/*     */         
/* 145 */         CrashReport var5 = CrashReport.makeCrashReport(var7, "Executing command block");
/* 146 */         CrashReportCategory var6 = var5.makeCategory("Command to be executed");
/* 147 */         var6.addCrashSectionCallable("Command", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00002154";
/*     */               
/*     */               public String func_180324_a() {
/* 152 */                 return CommandBlockLogic.this.getCustomName();
/*     */               }
/*     */               
/*     */               public Object call() {
/* 156 */                 return func_180324_a();
/*     */               }
/*     */             });
/* 159 */         var6.addCrashSectionCallable("Name", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00002153";
/*     */               
/*     */               public String func_180326_a() {
/* 164 */                 return CommandBlockLogic.this.getName();
/*     */               }
/*     */               
/*     */               public Object call() {
/* 168 */                 return func_180326_a();
/*     */               }
/*     */             });
/* 171 */         throw new ReportedException(var5);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 176 */       this.successCount = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 185 */     return this.customName;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent getDisplayName() {
/* 190 */     return (IChatComponent)new ChatComponentText(getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145754_b(String p_145754_1_) {
/* 195 */     this.customName = p_145754_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addChatMessage(IChatComponent message) {
/* 206 */     if (this.trackOutput && getEntityWorld() != null && !(getEntityWorld()).isRemote) {
/*     */       
/* 208 */       this.lastOutput = (new ChatComponentText("[" + timestampFormat.format(new Date()) + "] ")).appendSibling(message);
/* 209 */       func_145756_e();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean sendCommandFeedback() {
/* 215 */     MinecraftServer var1 = MinecraftServer.getServer();
/* 216 */     return !(var1 != null && var1.func_175578_N() && !var1.worldServers[0].getGameRules().getGameRuleBooleanValue("commandBlockOutput"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
/* 221 */     this.field_175575_g.func_179672_a(this, p_174794_1_, p_174794_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void func_145756_e();
/*     */   
/*     */   public abstract int func_145751_f();
/*     */   
/*     */   public abstract void func_145757_a(ByteBuf paramByteBuf);
/*     */   
/*     */   public void func_145750_b(IChatComponent p_145750_1_) {
/* 232 */     this.lastOutput = p_145750_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175573_a(boolean p_175573_1_) {
/* 237 */     this.trackOutput = p_175573_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175571_m() {
/* 242 */     return this.trackOutput;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175574_a(EntityPlayer p_175574_1_) {
/* 247 */     if (!p_175574_1_.capabilities.isCreativeMode)
/*     */     {
/* 249 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 253 */     if ((p_175574_1_.getEntityWorld()).isRemote)
/*     */     {
/* 255 */       p_175574_1_.func_146095_a(this);
/*     */     }
/*     */     
/* 258 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandResultStats func_175572_n() {
/* 264 */     return this.field_175575_g;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\server\CommandBlockLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */