/*    */ package net.minecraft.stats;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.IJsonSerializable;
/*    */ import net.minecraft.util.TupleIntJsonSerializable;
/*    */ 
/*    */ public class StatFileWriter
/*    */ {
/* 11 */   protected final Map field_150875_a = Maps.newConcurrentMap();
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00001481";
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasAchievementUnlocked(Achievement p_77443_1_) {
/* 19 */     return (writeStat(p_77443_1_) > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUnlockAchievement(Achievement p_77442_1_) {
/* 27 */     return !(p_77442_1_.parentAchievement != null && !hasAchievementUnlocked(p_77442_1_.parentAchievement));
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_150874_c(Achievement p_150874_1_) {
/* 32 */     if (hasAchievementUnlocked(p_150874_1_))
/*    */     {
/* 34 */       return 0;
/*    */     }
/*    */ 
/*    */     
/* 38 */     int var2 = 0;
/*    */     
/* 40 */     for (Achievement var3 = p_150874_1_.parentAchievement; var3 != null && !hasAchievementUnlocked(var3); var2++)
/*    */     {
/* 42 */       var3 = var3.parentAchievement;
/*    */     }
/*    */     
/* 45 */     return var2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_150871_b(EntityPlayer p_150871_1_, StatBase p_150871_2_, int p_150871_3_) {
/* 51 */     if (!p_150871_2_.isAchievement() || canUnlockAchievement((Achievement)p_150871_2_))
/*    */     {
/* 53 */       func_150873_a(p_150871_1_, p_150871_2_, writeStat(p_150871_2_) + p_150871_3_);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_150873_a(EntityPlayer p_150873_1_, StatBase p_150873_2_, int p_150873_3_) {
/* 59 */     TupleIntJsonSerializable var4 = (TupleIntJsonSerializable)this.field_150875_a.get(p_150873_2_);
/*    */     
/* 61 */     if (var4 == null) {
/*    */       
/* 63 */       var4 = new TupleIntJsonSerializable();
/* 64 */       this.field_150875_a.put(p_150873_2_, var4);
/*    */     } 
/*    */     
/* 67 */     var4.setIntegerValue(p_150873_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int writeStat(StatBase p_77444_1_) {
/* 72 */     TupleIntJsonSerializable var2 = (TupleIntJsonSerializable)this.field_150875_a.get(p_77444_1_);
/* 73 */     return (var2 == null) ? 0 : var2.getIntegerValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public IJsonSerializable func_150870_b(StatBase p_150870_1_) {
/* 78 */     TupleIntJsonSerializable var2 = (TupleIntJsonSerializable)this.field_150875_a.get(p_150870_1_);
/* 79 */     return (var2 != null) ? var2.getJsonSerializableValue() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public IJsonSerializable func_150872_a(StatBase p_150872_1_, IJsonSerializable p_150872_2_) {
/* 84 */     TupleIntJsonSerializable var3 = (TupleIntJsonSerializable)this.field_150875_a.get(p_150872_1_);
/*    */     
/* 86 */     if (var3 == null) {
/*    */       
/* 88 */       var3 = new TupleIntJsonSerializable();
/* 89 */       this.field_150875_a.put(p_150872_1_, var3);
/*    */     } 
/*    */     
/* 92 */     var3.setJsonSerializableValue(p_150872_2_);
/* 93 */     return p_150872_2_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\StatFileWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */