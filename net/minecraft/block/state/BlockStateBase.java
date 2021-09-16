/*     */ package net.minecraft.block.state;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.collect.ImmutableTable;
/*     */ import com.google.common.collect.Iterables;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public abstract class BlockStateBase
/*     */   implements IBlockState {
/*  16 */   private static final Joiner COMMA_JOINER = Joiner.on(',');
/*  17 */   private static final Function field_177233_b = new Function()
/*     */     {
/*     */       private static final String __OBFID = "CL_00002031";
/*     */       
/*     */       public String func_177225_a(Map.Entry p_177225_1_) {
/*  22 */         if (p_177225_1_ == null)
/*     */         {
/*  24 */           return "<NULL>";
/*     */         }
/*     */ 
/*     */         
/*  28 */         IProperty var2 = (IProperty)p_177225_1_.getKey();
/*  29 */         return String.valueOf(var2.getName()) + "=" + var2.getName((Comparable)p_177225_1_.getValue());
/*     */       }
/*     */ 
/*     */       
/*     */       public Object apply(Object p_apply_1_) {
/*  34 */         return func_177225_a((Map.Entry)p_apply_1_);
/*     */       }
/*     */     };
/*     */   private static final String __OBFID = "CL_00002032";
/*  38 */   private int blockId = -1;
/*  39 */   private int blockStateId = -1;
/*  40 */   private int metadata = -1;
/*  41 */   private ResourceLocation blockLocation = null;
/*     */ 
/*     */   
/*     */   public int getBlockId() {
/*  45 */     if (this.blockId < 0)
/*     */     {
/*  47 */       this.blockId = Block.getIdFromBlock(getBlock());
/*     */     }
/*     */     
/*  50 */     return this.blockId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockStateId() {
/*  55 */     if (this.blockStateId < 0)
/*     */     {
/*  57 */       this.blockStateId = Block.getStateId(this);
/*     */     }
/*     */     
/*  60 */     return this.blockStateId;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetadata() {
/*  65 */     if (this.metadata < 0)
/*     */     {
/*  67 */       this.metadata = getBlock().getMetaFromState(this);
/*     */     }
/*     */     
/*  70 */     return this.metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getBlockLocation() {
/*  75 */     if (this.blockLocation == null)
/*     */     {
/*  77 */       this.blockLocation = (ResourceLocation)Block.blockRegistry.getNameForObject(getBlock());
/*     */     }
/*     */     
/*  80 */     return this.blockLocation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState cycleProperty(IProperty property) {
/*  89 */     return withProperty(property, (Comparable)cyclePropertyValue(property.getAllowedValues(), getValue(property)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Object cyclePropertyValue(Collection values, Object currentValue) {
/* 100 */     Iterator<E> var2 = values.iterator();
/*     */     
/* 102 */     while (var2.hasNext()) {
/*     */       
/* 104 */       if (var2.next().equals(currentValue)) {
/*     */         
/* 106 */         if (var2.hasNext())
/*     */         {
/* 108 */           return var2.next();
/*     */         }
/*     */         
/* 111 */         return values.iterator().next();
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     return var2.next();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     StringBuilder var1 = new StringBuilder();
/* 121 */     var1.append(Block.blockRegistry.getNameForObject(getBlock()));
/*     */     
/* 123 */     if (!getProperties().isEmpty()) {
/*     */       
/* 125 */       var1.append("[");
/* 126 */       COMMA_JOINER.appendTo(var1, Iterables.transform((Iterable)getProperties().entrySet(), field_177233_b));
/* 127 */       var1.append("]");
/*     */     } 
/*     */     
/* 130 */     return var1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableTable<IProperty, Comparable, IBlockState> getPropertyValueTable() {
/* 135 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\BlockStateBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */