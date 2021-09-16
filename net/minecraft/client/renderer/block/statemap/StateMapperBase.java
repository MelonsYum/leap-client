/*    */ package net.minecraft.client.renderer.block.statemap;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*    */ 
/*    */ public abstract class StateMapperBase
/*    */   implements IStateMapper {
/* 14 */   protected Map field_178133_b = Maps.newLinkedHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00002479";
/*    */   
/*    */   public String func_178131_a(Map p_178131_1_) {
/* 19 */     StringBuilder var2 = new StringBuilder();
/* 20 */     Iterator<Map.Entry> var3 = p_178131_1_.entrySet().iterator();
/*    */     
/* 22 */     while (var3.hasNext()) {
/*    */       
/* 24 */       Map.Entry var4 = var3.next();
/*    */       
/* 26 */       if (var2.length() != 0)
/*    */       {
/* 28 */         var2.append(",");
/*    */       }
/*    */       
/* 31 */       IProperty var5 = (IProperty)var4.getKey();
/* 32 */       Comparable var6 = (Comparable)var4.getValue();
/* 33 */       var2.append(var5.getName());
/* 34 */       var2.append("=");
/* 35 */       var2.append(var5.getName(var6));
/*    */     } 
/*    */     
/* 38 */     if (var2.length() == 0)
/*    */     {
/* 40 */       var2.append("normal");
/*    */     }
/*    */     
/* 43 */     return var2.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public Map func_178130_a(Block p_178130_1_) {
/* 48 */     UnmodifiableIterator<IBlockState> unmodifiableIterator = p_178130_1_.getBlockState().getValidStates().iterator();
/*    */     
/* 50 */     while (unmodifiableIterator.hasNext()) {
/*    */       
/* 52 */       IBlockState var3 = unmodifiableIterator.next();
/* 53 */       this.field_178133_b.put(var3, func_178132_a(var3));
/*    */     } 
/*    */     
/* 56 */     return this.field_178133_b;
/*    */   }
/*    */   
/*    */   protected abstract ModelResourceLocation func_178132_a(IBlockState paramIBlockState);
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\statemap\StateMapperBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */