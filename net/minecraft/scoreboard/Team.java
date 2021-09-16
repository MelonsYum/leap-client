/*    */ package net.minecraft.scoreboard;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Team
/*    */ {
/*    */   private static final String __OBFID = "CL_00000621";
/*    */   
/*    */   public boolean isSameTeam(Team other) {
/* 16 */     return (other == null) ? false : ((this == other));
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract String getRegisteredName();
/*    */ 
/*    */   
/*    */   public abstract String formatString(String paramString);
/*    */ 
/*    */   
/*    */   public abstract boolean func_98297_h();
/*    */   
/*    */   public abstract boolean getAllowFriendlyFire();
/*    */   
/*    */   public abstract EnumVisible func_178770_i();
/*    */   
/*    */   public abstract Collection getMembershipCollection();
/*    */   
/*    */   public abstract EnumVisible func_178771_j();
/*    */   
/*    */   public enum EnumVisible
/*    */   {
/* 38 */     ALWAYS("ALWAYS", 0, "always", 0),
/* 39 */     NEVER("NEVER", 1, "never", 1),
/* 40 */     HIDE_FOR_OTHER_TEAMS("HIDE_FOR_OTHER_TEAMS", 2, "hideForOtherTeams", 2),
/* 41 */     HIDE_FOR_OWN_TEAM("HIDE_FOR_OWN_TEAM", 3, "hideForOwnTeam", 3);
/* 42 */     private static Map field_178828_g = Maps.newHashMap();
/*    */     
/*    */     public final String field_178830_e;
/*    */     public final int field_178827_f;
/* 46 */     private static final EnumVisible[] $VALUES = new EnumVisible[] { ALWAYS, NEVER, HIDE_FOR_OTHER_TEAMS, HIDE_FOR_OWN_TEAM };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     private static final String __OBFID = "CL_00001962";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     static {
/* 66 */       EnumVisible[] var0 = values();
/* 67 */       int var1 = var0.length;
/*    */       
/* 69 */       for (int var2 = 0; var2 < var1; var2++) {
/*    */         
/* 71 */         EnumVisible var3 = var0[var2];
/* 72 */         field_178828_g.put(var3.field_178830_e, var3);
/*    */       } 
/*    */     }
/*    */     
/*    */     public static String[] func_178825_a() {
/*    */       return (String[])field_178828_g.keySet().toArray((Object[])new String[field_178828_g.size()]);
/*    */     }
/*    */     
/*    */     public static EnumVisible func_178824_a(String p_178824_0_) {
/*    */       return (EnumVisible)field_178828_g.get(p_178824_0_);
/*    */     }
/*    */     
/*    */     EnumVisible(String p_i45550_1_, int p_i45550_2_, String p_i45550_3_, int p_i45550_4_) {
/*    */       this.field_178830_e = p_i45550_3_;
/*    */       this.field_178827_f = p_i45550_4_;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\scoreboard\Team.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */