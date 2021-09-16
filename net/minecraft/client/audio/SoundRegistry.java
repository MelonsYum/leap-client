/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.RegistrySimple;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoundRegistry
/*    */   extends RegistrySimple
/*    */ {
/*    */   private Map field_148764_a;
/*    */   private static final String __OBFID = "CL_00001151";
/*    */   
/*    */   protected Map createUnderlyingMap() {
/* 17 */     this.field_148764_a = Maps.newHashMap();
/* 18 */     return this.field_148764_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerSound(SoundEventAccessorComposite p_148762_1_) {
/* 23 */     putObject(p_148762_1_.getSoundEventLocation(), p_148762_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void clearMap() {
/* 31 */     this.field_148764_a.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */