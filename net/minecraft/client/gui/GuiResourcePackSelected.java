/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ 
/*    */ public class GuiResourcePackSelected
/*    */   extends GuiResourcePackList
/*    */ {
/*    */   private static final String __OBFID = "CL_00000827";
/*    */   
/*    */   public GuiResourcePackSelected(Minecraft mcIn, int p_i45056_2_, int p_i45056_3_, List p_i45056_4_) {
/* 13 */     super(mcIn, p_i45056_2_, p_i45056_3_, p_i45056_4_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getListHeader() {
/* 18 */     return I18n.format("resourcePack.selected.title", new Object[0]);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiResourcePackSelected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */