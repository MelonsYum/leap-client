/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ public class SoundPoolEntry
/*    */ {
/*    */   private final ResourceLocation field_148656_a;
/*    */   private final boolean field_148654_b;
/*    */   private double field_148655_c;
/*    */   private double field_148653_d;
/*    */   private static final String __OBFID = "CL_00001140";
/*    */   
/*    */   public SoundPoolEntry(ResourceLocation p_i45113_1_, double p_i45113_2_, double p_i45113_4_, boolean p_i45113_6_) {
/* 15 */     this.field_148656_a = p_i45113_1_;
/* 16 */     this.field_148655_c = p_i45113_2_;
/* 17 */     this.field_148653_d = p_i45113_4_;
/* 18 */     this.field_148654_b = p_i45113_6_;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundPoolEntry(SoundPoolEntry p_i45114_1_) {
/* 23 */     this.field_148656_a = p_i45114_1_.field_148656_a;
/* 24 */     this.field_148655_c = p_i45114_1_.field_148655_c;
/* 25 */     this.field_148653_d = p_i45114_1_.field_148653_d;
/* 26 */     this.field_148654_b = p_i45114_1_.field_148654_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getSoundPoolEntryLocation() {
/* 31 */     return this.field_148656_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getPitch() {
/* 36 */     return this.field_148655_c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPitch(double p_148651_1_) {
/* 41 */     this.field_148655_c = p_148651_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getVolume() {
/* 46 */     return this.field_148653_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVolume(double p_148647_1_) {
/* 51 */     this.field_148653_d = p_148647_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isStreamingSound() {
/* 56 */     return this.field_148654_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundPoolEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */