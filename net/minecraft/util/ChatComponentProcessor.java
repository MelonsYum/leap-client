/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.EntityNotFoundException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.PlayerSelector;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class ChatComponentProcessor
/*    */ {
/*    */   private static final String __OBFID = "CL_00002310";
/*    */   
/*    */   public static IChatComponent func_179985_a(ICommandSender p_179985_0_, IChatComponent p_179985_1_, Entity p_179985_2_) throws CommandException {
/* 17 */     Object var3 = null;
/*    */     
/* 19 */     if (p_179985_1_ instanceof ChatComponentScore) {
/*    */       
/* 21 */       ChatComponentScore var4 = (ChatComponentScore)p_179985_1_;
/* 22 */       String var5 = var4.func_179995_g();
/*    */       
/* 24 */       if (PlayerSelector.hasArguments(var5)) {
/*    */         
/* 26 */         List<Entity> var6 = PlayerSelector.func_179656_b(p_179985_0_, var5, Entity.class);
/*    */         
/* 28 */         if (var6.size() != 1)
/*    */         {
/* 30 */           throw new EntityNotFoundException();
/*    */         }
/*    */         
/* 33 */         var5 = ((Entity)var6.get(0)).getName();
/*    */       } 
/*    */       
/* 36 */       var3 = (p_179985_2_ != null && var5.equals("*")) ? new ChatComponentScore(p_179985_2_.getName(), var4.func_179994_h()) : new ChatComponentScore(var5, var4.func_179994_h());
/* 37 */       ((ChatComponentScore)var3).func_179997_b(var4.getUnformattedTextForChat());
/*    */     }
/* 39 */     else if (p_179985_1_ instanceof ChatComponentSelector) {
/*    */       
/* 41 */       String var7 = ((ChatComponentSelector)p_179985_1_).func_179992_g();
/* 42 */       var3 = PlayerSelector.func_150869_b(p_179985_0_, var7);
/*    */       
/* 44 */       if (var3 == null)
/*    */       {
/* 46 */         var3 = new ChatComponentText("");
/*    */       }
/*    */     }
/* 49 */     else if (p_179985_1_ instanceof ChatComponentText) {
/*    */       
/* 51 */       var3 = new ChatComponentText(((ChatComponentText)p_179985_1_).getChatComponentText_TextValue());
/*    */     }
/*    */     else {
/*    */       
/* 55 */       if (!(p_179985_1_ instanceof ChatComponentTranslation))
/*    */       {
/* 57 */         return p_179985_1_;
/*    */       }
/*    */       
/* 60 */       Object[] var8 = ((ChatComponentTranslation)p_179985_1_).getFormatArgs();
/*    */       
/* 62 */       for (int var10 = 0; var10 < var8.length; var10++) {
/*    */         
/* 64 */         Object var12 = var8[var10];
/*    */         
/* 66 */         if (var12 instanceof IChatComponent)
/*    */         {
/* 68 */           var8[var10] = func_179985_a(p_179985_0_, (IChatComponent)var12, p_179985_2_);
/*    */         }
/*    */       } 
/*    */       
/* 72 */       var3 = new ChatComponentTranslation(((ChatComponentTranslation)p_179985_1_).getKey(), var8);
/*    */     } 
/*    */     
/* 75 */     ChatStyle var9 = p_179985_1_.getChatStyle();
/*    */     
/* 77 */     if (var9 != null)
/*    */     {
/* 79 */       ((IChatComponent)var3).setChatStyle(var9.createShallowCopy());
/*    */     }
/*    */     
/* 82 */     Iterator<IChatComponent> var11 = p_179985_1_.getSiblings().iterator();
/*    */     
/* 84 */     while (var11.hasNext()) {
/*    */       
/* 86 */       IChatComponent var13 = var11.next();
/* 87 */       ((IChatComponent)var3).appendSibling(func_179985_a(p_179985_0_, var13, p_179985_2_));
/*    */     } 
/*    */     
/* 90 */     return (IChatComponent)var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ChatComponentProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */