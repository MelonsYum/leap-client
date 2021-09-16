/*    */ package net.minecraft.client.gui.spectator;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.spectator.categories.TeleportToPlayer;
/*    */ import net.minecraft.client.gui.spectator.categories.TeleportToTeam;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class BaseSpectatorGroup
/*    */   implements ISpectatorMenuView {
/* 12 */   private final List field_178671_a = Lists.newArrayList();
/*    */   
/*    */   private static final String __OBFID = "CL_00001928";
/*    */   
/*    */   public BaseSpectatorGroup() {
/* 17 */     this.field_178671_a.add(new TeleportToPlayer());
/* 18 */     this.field_178671_a.add(new TeleportToTeam());
/*    */   }
/*    */ 
/*    */   
/*    */   public List func_178669_a() {
/* 23 */     return this.field_178671_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_178670_b() {
/* 28 */     return (IChatComponent)new ChatComponentText("Press a key to select a command, and again to use it.");
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\spectator\BaseSpectatorGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */