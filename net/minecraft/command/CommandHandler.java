/*     */ package net.minecraft.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class CommandHandler
/*     */   implements ICommandManager {
/*  21 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*  24 */   private final Map commandMap = Maps.newHashMap();
/*     */ 
/*     */   
/*  27 */   private final Set commandSet = Sets.newHashSet();
/*     */   
/*     */   private static final String __OBFID = "CL_00001765";
/*     */   
/*     */   public int executeCommand(ICommandSender sender, String command) {
/*  32 */     command = command.trim();
/*     */     
/*  34 */     if (command.startsWith("/"))
/*     */     {
/*  36 */       command = command.substring(1);
/*     */     }
/*     */     
/*  39 */     String[] var3 = command.split(" ");
/*  40 */     String var4 = var3[0];
/*  41 */     var3 = dropFirstString(var3);
/*  42 */     ICommand var5 = (ICommand)this.commandMap.get(var4);
/*  43 */     int var6 = getUsernameIndex(var5, var3);
/*  44 */     int var7 = 0;
/*     */ 
/*     */     
/*  47 */     if (var5 == null) {
/*     */       
/*  49 */       ChatComponentTranslation var8 = new ChatComponentTranslation("commands.generic.notFound", new Object[0]);
/*  50 */       var8.getChatStyle().setColor(EnumChatFormatting.RED);
/*  51 */       sender.addChatMessage((IChatComponent)var8);
/*     */     }
/*  53 */     else if (var5.canCommandSenderUseCommand(sender)) {
/*     */       
/*  55 */       if (var6 > -1)
/*     */       {
/*  57 */         List var12 = PlayerSelector.func_179656_b(sender, var3[var6], Entity.class);
/*  58 */         String var9 = var3[var6];
/*  59 */         sender.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, var12.size());
/*  60 */         Iterator<Entity> var10 = var12.iterator();
/*     */         
/*  62 */         while (var10.hasNext()) {
/*     */           
/*  64 */           Entity var11 = var10.next();
/*  65 */           var3[var6] = var11.getUniqueID().toString();
/*     */           
/*  67 */           if (func_175786_a(sender, var3, var5, command))
/*     */           {
/*  69 */             var7++;
/*     */           }
/*     */         } 
/*     */         
/*  73 */         var3[var6] = var9;
/*     */       }
/*     */       else
/*     */       {
/*  77 */         sender.func_174794_a(CommandResultStats.Type.AFFECTED_ENTITIES, 1);
/*     */         
/*  79 */         if (func_175786_a(sender, var3, var5, command))
/*     */         {
/*  81 */           var7++;
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  87 */       ChatComponentTranslation var8 = new ChatComponentTranslation("commands.generic.permission", new Object[0]);
/*  88 */       var8.getChatStyle().setColor(EnumChatFormatting.RED);
/*  89 */       sender.addChatMessage((IChatComponent)var8);
/*     */     } 
/*     */     
/*  92 */     sender.func_174794_a(CommandResultStats.Type.SUCCESS_COUNT, var7);
/*  93 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_175786_a(ICommandSender p_175786_1_, String[] p_175786_2_, ICommand p_175786_3_, String p_175786_4_) {
/*     */     try {
/* 102 */       p_175786_3_.processCommand(p_175786_1_, p_175786_2_);
/* 103 */       return true;
/*     */     }
/* 105 */     catch (WrongUsageException var7) {
/*     */       
/* 107 */       ChatComponentTranslation var6 = new ChatComponentTranslation("commands.generic.usage", new Object[] { new ChatComponentTranslation(var7.getMessage(), var7.getErrorOjbects()) });
/* 108 */       var6.getChatStyle().setColor(EnumChatFormatting.RED);
/* 109 */       p_175786_1_.addChatMessage((IChatComponent)var6);
/*     */     }
/* 111 */     catch (CommandException var8) {
/*     */       
/* 113 */       ChatComponentTranslation var6 = new ChatComponentTranslation(var8.getMessage(), var8.getErrorOjbects());
/* 114 */       var6.getChatStyle().setColor(EnumChatFormatting.RED);
/* 115 */       p_175786_1_.addChatMessage((IChatComponent)var6);
/*     */     }
/* 117 */     catch (Throwable var9) {
/*     */       
/* 119 */       ChatComponentTranslation var6 = new ChatComponentTranslation("commands.generic.exception", new Object[0]);
/* 120 */       var6.getChatStyle().setColor(EnumChatFormatting.RED);
/* 121 */       p_175786_1_.addChatMessage((IChatComponent)var6);
/* 122 */       logger.error("Couldn't process command: '" + p_175786_4_ + "'", var9);
/*     */     } 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ICommand registerCommand(ICommand p_71560_1_) {
/* 133 */     this.commandMap.put(p_71560_1_.getCommandName(), p_71560_1_);
/* 134 */     this.commandSet.add(p_71560_1_);
/* 135 */     Iterator<String> var2 = p_71560_1_.getCommandAliases().iterator();
/*     */     
/* 137 */     while (var2.hasNext()) {
/*     */       
/* 139 */       String var3 = var2.next();
/* 140 */       ICommand var4 = (ICommand)this.commandMap.get(var3);
/*     */       
/* 142 */       if (var4 == null || !var4.getCommandName().equals(var3))
/*     */       {
/* 144 */         this.commandMap.put(var3, p_71560_1_);
/*     */       }
/*     */     } 
/*     */     
/* 148 */     return p_71560_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] dropFirstString(String[] p_71559_0_) {
/* 156 */     String[] var1 = new String[p_71559_0_.length - 1];
/* 157 */     System.arraycopy(p_71559_0_, 1, var1, 0, p_71559_0_.length - 1);
/* 158 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getTabCompletionOptions(ICommandSender sender, String input, BlockPos pos) {
/* 163 */     String[] var4 = input.split(" ", -1);
/* 164 */     String var5 = var4[0];
/*     */     
/* 166 */     if (var4.length == 1) {
/*     */       
/* 168 */       ArrayList var9 = Lists.newArrayList();
/* 169 */       Iterator<Map.Entry> var7 = this.commandMap.entrySet().iterator();
/*     */       
/* 171 */       while (var7.hasNext()) {
/*     */         
/* 173 */         Map.Entry var8 = var7.next();
/*     */         
/* 175 */         if (CommandBase.doesStringStartWith(var5, (String)var8.getKey()) && ((ICommand)var8.getValue()).canCommandSenderUseCommand(sender))
/*     */         {
/* 177 */           var9.add(var8.getKey());
/*     */         }
/*     */       } 
/*     */       
/* 181 */       return var9;
/*     */     } 
/*     */ 
/*     */     
/* 185 */     if (var4.length > 1) {
/*     */       
/* 187 */       ICommand var6 = (ICommand)this.commandMap.get(var5);
/*     */       
/* 189 */       if (var6 != null && var6.canCommandSenderUseCommand(sender))
/*     */       {
/* 191 */         return var6.addTabCompletionOptions(sender, dropFirstString(var4), pos);
/*     */       }
/*     */     } 
/*     */     
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCommands(ICommandSender sender) {
/* 204 */     ArrayList<ICommand> var2 = Lists.newArrayList();
/* 205 */     Iterator<ICommand> var3 = this.commandSet.iterator();
/*     */     
/* 207 */     while (var3.hasNext()) {
/*     */       
/* 209 */       ICommand var4 = var3.next();
/*     */       
/* 211 */       if (var4.canCommandSenderUseCommand(sender))
/*     */       {
/* 213 */         var2.add(var4);
/*     */       }
/*     */     } 
/*     */     
/* 217 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getCommands() {
/* 225 */     return this.commandMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getUsernameIndex(ICommand p_82370_1_, String[] p_82370_2_) {
/* 233 */     if (p_82370_1_ == null)
/*     */     {
/* 235 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 239 */     for (int var3 = 0; var3 < p_82370_2_.length; var3++) {
/*     */       
/* 241 */       if (p_82370_1_.isUsernameIndex(p_82370_2_, var3) && PlayerSelector.matchesMultiplePlayers(p_82370_2_[var3]))
/*     */       {
/* 243 */         return var3;
/*     */       }
/*     */     } 
/*     */     
/* 247 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */