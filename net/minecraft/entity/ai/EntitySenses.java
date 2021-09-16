/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySenses
/*    */ {
/*    */   EntityLiving entityObj;
/* 13 */   List seenEntities = Lists.newArrayList();
/*    */ 
/*    */   
/* 16 */   List unseenEntities = Lists.newArrayList();
/*    */   
/*    */   private static final String __OBFID = "CL_00001628";
/*    */   
/*    */   public EntitySenses(EntityLiving p_i1672_1_) {
/* 21 */     this.entityObj = p_i1672_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void clearSensingCache() {
/* 29 */     this.seenEntities.clear();
/* 30 */     this.unseenEntities.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canSee(Entity p_75522_1_) {
/* 38 */     if (this.seenEntities.contains(p_75522_1_))
/*    */     {
/* 40 */       return true;
/*    */     }
/* 42 */     if (this.unseenEntities.contains(p_75522_1_))
/*    */     {
/* 44 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 48 */     this.entityObj.worldObj.theProfiler.startSection("canSee");
/* 49 */     boolean var2 = this.entityObj.canEntityBeSeen(p_75522_1_);
/* 50 */     this.entityObj.worldObj.theProfiler.endSection();
/*    */     
/* 52 */     if (var2) {
/*    */       
/* 54 */       this.seenEntities.add(p_75522_1_);
/*    */     }
/*    */     else {
/*    */       
/* 58 */       this.unseenEntities.add(p_75522_1_);
/*    */     } 
/*    */     
/* 61 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntitySenses.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */