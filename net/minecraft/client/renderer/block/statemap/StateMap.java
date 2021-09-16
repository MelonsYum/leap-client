/*    */ package net.minecraft.client.renderer.block.statemap;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class StateMap
/*    */   extends StateMapperBase {
/*    */   private final IProperty field_178142_a;
/*    */   private final String field_178141_c;
/*    */   private final List field_178140_d;
/*    */   private static final String __OBFID = "CL_00002476";
/*    */   
/*    */   private StateMap(IProperty p_i46210_1_, String p_i46210_2_, List p_i46210_3_) {
/* 24 */     this.field_178142_a = p_i46210_1_;
/* 25 */     this.field_178141_c = p_i46210_2_;
/* 26 */     this.field_178140_d = p_i46210_3_;
/*    */   }
/*    */   
/*    */   protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/*    */     String var3;
/* 31 */     LinkedHashMap var2 = Maps.newLinkedHashMap((Map)p_178132_1_.getProperties());
/*    */ 
/*    */     
/* 34 */     if (this.field_178142_a == null) {
/*    */       
/* 36 */       var3 = ((ResourceLocation)Block.blockRegistry.getNameForObject(p_178132_1_.getBlock())).toString();
/*    */     }
/*    */     else {
/*    */       
/* 40 */       var3 = this.field_178142_a.getName((Comparable)var2.remove(this.field_178142_a));
/*    */     } 
/*    */     
/* 43 */     if (this.field_178141_c != null)
/*    */     {
/* 45 */       var3 = String.valueOf(var3) + this.field_178141_c;
/*    */     }
/*    */     
/* 48 */     Iterator<IProperty> var4 = this.field_178140_d.iterator();
/*    */     
/* 50 */     while (var4.hasNext()) {
/*    */       
/* 52 */       IProperty var5 = var4.next();
/* 53 */       var2.remove(var5);
/*    */     } 
/*    */     
/* 56 */     return new ModelResourceLocation(var3, func_178131_a(var2));
/*    */   }
/*    */ 
/*    */   
/*    */   StateMap(IProperty p_i46211_1_, String p_i46211_2_, List p_i46211_3_, Object p_i46211_4_) {
/* 61 */     this(p_i46211_1_, p_i46211_2_, p_i46211_3_);
/*    */   }
/*    */   
/*    */   public static class Builder
/*    */   {
/*    */     private IProperty field_178445_a;
/*    */     private String field_178443_b;
/* 68 */     private final List field_178444_c = Lists.newArrayList();
/*    */     
/*    */     private static final String __OBFID = "CL_00002474";
/*    */     
/*    */     public Builder func_178440_a(IProperty p_178440_1_) {
/* 73 */       this.field_178445_a = p_178440_1_;
/* 74 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder func_178439_a(String p_178439_1_) {
/* 79 */       this.field_178443_b = p_178439_1_;
/* 80 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public Builder func_178442_a(IProperty... p_178442_1_) {
/* 85 */       Collections.addAll(this.field_178444_c, p_178442_1_);
/* 86 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public StateMap build() {
/* 91 */       return new StateMap(this.field_178445_a, this.field_178443_b, this.field_178444_c, null);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\statemap\StateMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */