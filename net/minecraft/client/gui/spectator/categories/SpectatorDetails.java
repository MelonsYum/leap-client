/*    */ package net.minecraft.client.gui.spectator.categories;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
/*    */ import net.minecraft.client.gui.spectator.ISpectatorMenuView;
/*    */ import net.minecraft.client.gui.spectator.SpectatorMenu;
/*    */ 
/*    */ 
/*    */ public class SpectatorDetails
/*    */ {
/*    */   private final ISpectatorMenuView field_178684_a;
/*    */   private final List field_178682_b;
/*    */   private final int field_178683_c;
/*    */   private static final String __OBFID = "CL_00001923";
/*    */   
/*    */   public SpectatorDetails(ISpectatorMenuView p_i45494_1_, List p_i45494_2_, int p_i45494_3_) {
/* 18 */     this.field_178684_a = p_i45494_1_;
/* 19 */     this.field_178682_b = p_i45494_2_;
/* 20 */     this.field_178683_c = p_i45494_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public ISpectatorMenuObject func_178680_a(int p_178680_1_) {
/* 25 */     return (p_178680_1_ >= 0 && p_178680_1_ < this.field_178682_b.size()) ? (ISpectatorMenuObject)Objects.firstNonNull(this.field_178682_b.get(p_178680_1_), SpectatorMenu.field_178657_a) : SpectatorMenu.field_178657_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_178681_b() {
/* 30 */     return this.field_178683_c;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\spectator\categories\SpectatorDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */