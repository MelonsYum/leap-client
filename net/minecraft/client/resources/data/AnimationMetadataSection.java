/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class AnimationMetadataSection
/*    */   implements IMetadataSection
/*    */ {
/*    */   private final List animationFrames;
/*    */   private final int frameWidth;
/*    */   private final int frameHeight;
/*    */   private final int frameTime;
/*    */   private final boolean field_177220_e;
/*    */   private static final String __OBFID = "CL_00001106";
/*    */   
/*    */   public AnimationMetadataSection(List p_i46088_1_, int p_i46088_2_, int p_i46088_3_, int p_i46088_4_, boolean p_i46088_5_) {
/* 20 */     this.animationFrames = p_i46088_1_;
/* 21 */     this.frameWidth = p_i46088_2_;
/* 22 */     this.frameHeight = p_i46088_3_;
/* 23 */     this.frameTime = p_i46088_4_;
/* 24 */     this.field_177220_e = p_i46088_5_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameHeight() {
/* 29 */     return this.frameHeight;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameWidth() {
/* 34 */     return this.frameWidth;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameCount() {
/* 39 */     return this.animationFrames.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameTime() {
/* 44 */     return this.frameTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_177219_e() {
/* 49 */     return this.field_177220_e;
/*    */   }
/*    */ 
/*    */   
/*    */   private AnimationFrame getAnimationFrame(int p_130072_1_) {
/* 54 */     return this.animationFrames.get(p_130072_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameTimeSingle(int p_110472_1_) {
/* 59 */     AnimationFrame var2 = getAnimationFrame(p_110472_1_);
/* 60 */     return var2.hasNoTime() ? this.frameTime : var2.getFrameTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean frameHasTime(int p_110470_1_) {
/* 65 */     return !((AnimationFrame)this.animationFrames.get(p_110470_1_)).hasNoTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameIndex(int p_110468_1_) {
/* 70 */     return ((AnimationFrame)this.animationFrames.get(p_110468_1_)).getFrameIndex();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set getFrameIndexSet() {
/* 75 */     HashSet<Integer> var1 = Sets.newHashSet();
/* 76 */     Iterator<AnimationFrame> var2 = this.animationFrames.iterator();
/*    */     
/* 78 */     while (var2.hasNext()) {
/*    */       
/* 80 */       AnimationFrame var3 = var2.next();
/* 81 */       var1.add(Integer.valueOf(var3.getFrameIndex()));
/*    */     } 
/*    */     
/* 84 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\AnimationMetadataSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */