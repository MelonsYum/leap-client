/*    */ package net.minecraft.client.renderer.block.statemap;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collections;
/*    */ import java.util.IdentityHashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ public class BlockStateMapper
/*    */ {
/* 15 */   private Map field_178450_a = Maps.newIdentityHashMap();
/* 16 */   private Set field_178449_b = Sets.newIdentityHashSet();
/*    */   
/*    */   private static final String __OBFID = "CL_00002478";
/*    */   
/*    */   public void func_178447_a(Block p_178447_1_, IStateMapper p_178447_2_) {
/* 21 */     this.field_178450_a.put(p_178447_1_, p_178447_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void registerBuiltInBlocks(Block... p_178448_1_) {
/* 26 */     Collections.addAll(this.field_178449_b, p_178448_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map func_178446_a() {
/* 31 */     IdentityHashMap var1 = Maps.newIdentityHashMap();
/* 32 */     Iterator<Block> var2 = Block.blockRegistry.iterator();
/*    */     
/* 34 */     while (var2.hasNext()) {
/*    */       
/* 36 */       Block var3 = var2.next();
/*    */       
/* 38 */       if (!this.field_178449_b.contains(var3))
/*    */       {
/* 40 */         var1.putAll(((IStateMapper)Objects.firstNonNull(this.field_178450_a.get(var3), new DefaultStateMapper())).func_178130_a(var3));
/*    */       }
/*    */     } 
/*    */     
/* 44 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\statemap\BlockStateMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */