/*     */ package net.minecraft.event;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ public class ClickEvent
/*     */ {
/*     */   private final Action action;
/*     */   private final String value;
/*     */   private static final String __OBFID = "CL_00001260";
/*     */   
/*     */   public ClickEvent(Action p_i45156_1_, String p_i45156_2_) {
/*  14 */     this.action = p_i45156_1_;
/*  15 */     this.value = p_i45156_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getAction() {
/*  23 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  32 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  37 */     if (this == p_equals_1_)
/*     */     {
/*  39 */       return true;
/*     */     }
/*  41 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */       
/*  43 */       ClickEvent var2 = (ClickEvent)p_equals_1_;
/*     */       
/*  45 */       if (this.action != var2.action)
/*     */       {
/*  47 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  51 */       if (this.value != null) {
/*     */         
/*  53 */         if (!this.value.equals(var2.value))
/*     */         {
/*  55 */           return false;
/*     */         }
/*     */       }
/*  58 */       else if (var2.value != null) {
/*     */         
/*  60 */         return false;
/*     */       } 
/*     */       
/*  63 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  74 */     return "ClickEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  79 */     int var1 = this.action.hashCode();
/*  80 */     var1 = 31 * var1 + ((this.value != null) ? this.value.hashCode() : 0);
/*  81 */     return var1;
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/*  86 */     OPEN_URL("OPEN_URL", 0, "open_url", true),
/*  87 */     OPEN_FILE("OPEN_FILE", 1, "open_file", false),
/*  88 */     RUN_COMMAND("RUN_COMMAND", 2, "run_command", true),
/*  89 */     TWITCH_USER_INFO("TWITCH_USER_INFO", 3, "twitch_user_info", false),
/*  90 */     SUGGEST_COMMAND("SUGGEST_COMMAND", 4, "suggest_command", true),
/*  91 */     CHANGE_PAGE("CHANGE_PAGE", 5, "change_page", true);
/*  92 */     private static final Map nameMapping = Maps.newHashMap();
/*     */     
/*     */     private final boolean allowedInChat;
/*     */     private final String canonicalName;
/*  96 */     private static final Action[] $VALUES = new Action[] { OPEN_URL, OPEN_FILE, RUN_COMMAND, TWITCH_USER_INFO, SUGGEST_COMMAND, CHANGE_PAGE };
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
/*     */     private static final String __OBFID = "CL_00001261";
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
/*     */     static {
/* 121 */       Action[] var0 = values();
/* 122 */       int var1 = var0.length;
/*     */       
/* 124 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 126 */         Action var3 = var0[var2];
/* 127 */         nameMapping.put(var3.getCanonicalName(), var3);
/*     */       } 
/*     */     }
/*     */     
/*     */     Action(String p_i45155_1_, int p_i45155_2_, String p_i45155_3_, boolean p_i45155_4_) {
/*     */       this.canonicalName = p_i45155_3_;
/*     */       this.allowedInChat = p_i45155_4_;
/*     */     }
/*     */     
/*     */     public boolean shouldAllowInChat() {
/*     */       return this.allowedInChat;
/*     */     }
/*     */     
/*     */     public String getCanonicalName() {
/*     */       return this.canonicalName;
/*     */     }
/*     */     
/*     */     public static Action getValueByCanonicalName(String p_150672_0_) {
/*     */       return (Action)nameMapping.get(p_150672_0_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\event\ClickEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */