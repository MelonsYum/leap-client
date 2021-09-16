/*     */ package net.minecraft.event;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ public class HoverEvent
/*     */ {
/*     */   private final Action action;
/*     */   private final IChatComponent value;
/*     */   private static final String __OBFID = "CL_00001264";
/*     */   
/*     */   public HoverEvent(Action p_i45158_1_, IChatComponent p_i45158_2_) {
/*  15 */     this.action = p_i45158_1_;
/*  16 */     this.value = p_i45158_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Action getAction() {
/*  24 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent getValue() {
/*  33 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  38 */     if (this == p_equals_1_)
/*     */     {
/*  40 */       return true;
/*     */     }
/*  42 */     if (p_equals_1_ != null && getClass() == p_equals_1_.getClass()) {
/*     */       
/*  44 */       HoverEvent var2 = (HoverEvent)p_equals_1_;
/*     */       
/*  46 */       if (this.action != var2.action)
/*     */       {
/*  48 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  52 */       if (this.value != null) {
/*     */         
/*  54 */         if (!this.value.equals(var2.value))
/*     */         {
/*  56 */           return false;
/*     */         }
/*     */       }
/*  59 */       else if (var2.value != null) {
/*     */         
/*  61 */         return false;
/*     */       } 
/*     */       
/*  64 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  75 */     return "HoverEvent{action=" + this.action + ", value='" + this.value + '\'' + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  80 */     int var1 = this.action.hashCode();
/*  81 */     var1 = 31 * var1 + ((this.value != null) ? this.value.hashCode() : 0);
/*  82 */     return var1;
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/*  87 */     SHOW_TEXT("SHOW_TEXT", 0, "show_text", true),
/*  88 */     SHOW_ACHIEVEMENT("SHOW_ACHIEVEMENT", 1, "show_achievement", true),
/*  89 */     SHOW_ITEM("SHOW_ITEM", 2, "show_item", true),
/*  90 */     SHOW_ENTITY("SHOW_ENTITY", 3, "show_entity", true);
/*  91 */     private static final Map nameMapping = Maps.newHashMap();
/*     */     
/*     */     private final boolean allowedInChat;
/*     */     private final String canonicalName;
/*  95 */     private static final Action[] $VALUES = new Action[] { SHOW_TEXT, SHOW_ACHIEVEMENT, SHOW_ITEM, SHOW_ENTITY };
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
/*     */     private static final String __OBFID = "CL_00001265";
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
/* 120 */       Action[] var0 = values();
/* 121 */       int var1 = var0.length;
/*     */       
/* 123 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 125 */         Action var3 = var0[var2];
/* 126 */         nameMapping.put(var3.getCanonicalName(), var3);
/*     */       } 
/*     */     }
/*     */     
/*     */     Action(String p_i45157_1_, int p_i45157_2_, String p_i45157_3_, boolean p_i45157_4_) {
/*     */       this.canonicalName = p_i45157_3_;
/*     */       this.allowedInChat = p_i45157_4_;
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
/*     */     public static Action getValueByCanonicalName(String p_150684_0_) {
/*     */       return (Action)nameMapping.get(p_150684_0_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\event\HoverEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */