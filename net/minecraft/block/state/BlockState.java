/*     */ package net.minecraft.block.state;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.collect.HashBasedTable;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableTable;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Table;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.util.Cartesian;
/*     */ import net.minecraft.util.MapPopulator;
/*     */ 
/*     */ public class BlockState {
/*  30 */   private static final Joiner COMMA_JOINER = Joiner.on(", ");
/*     */ 
/*     */   
/*  33 */   private static final Function GET_NAME_FUNC = new Function()
/*     */     {
/*     */       private static final String __OBFID = "CL_00002029";
/*     */       
/*     */       public String apply(IProperty property) {
/*  38 */         return (property == null) ? "<NULL>" : property.getName();
/*     */       }
/*     */       
/*     */       public Object apply(Object p_apply_1_) {
/*  42 */         return apply((IProperty)p_apply_1_);
/*     */       }
/*     */     };
/*     */   
/*     */   private final Block block;
/*     */   private final ImmutableList properties;
/*     */   private final ImmutableList validStates;
/*     */   private static final String __OBFID = "CL_00002030";
/*     */   
/*     */   public BlockState(Block blockIn, IProperty... properties) {
/*  52 */     this.block = blockIn;
/*  53 */     Arrays.sort(properties, new Comparator<IProperty>()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002028";
/*     */           
/*     */           public int compare(IProperty left, IProperty right) {
/*  58 */             return left.getName().compareTo(right.getName());
/*     */           }
/*     */           
/*     */           public int compare(Object p_compare_1_, Object p_compare_2_) {
/*  62 */             return compare((IProperty)p_compare_1_, (IProperty)p_compare_2_);
/*     */           }
/*     */         });
/*  65 */     this.properties = ImmutableList.copyOf((Object[])properties);
/*  66 */     LinkedHashMap<Map, StateImplemenation> var3 = Maps.newLinkedHashMap();
/*  67 */     ArrayList<StateImplemenation> var4 = Lists.newArrayList();
/*  68 */     Iterable var5 = Cartesian.cartesianProduct(getAllowedValues());
/*  69 */     Iterator<List> var6 = var5.iterator();
/*     */     
/*  71 */     while (var6.hasNext()) {
/*     */       
/*  73 */       List var7 = var6.next();
/*  74 */       Map var8 = MapPopulator.createMap((Iterable)this.properties, var7);
/*  75 */       StateImplemenation var9 = new StateImplemenation(blockIn, ImmutableMap.copyOf(var8), null);
/*  76 */       var3.put(var8, var9);
/*  77 */       var4.add(var9);
/*     */     } 
/*     */     
/*  80 */     var6 = (Iterator)var4.iterator();
/*     */     
/*  82 */     while (var6.hasNext()) {
/*     */       
/*  84 */       StateImplemenation var10 = (StateImplemenation)var6.next();
/*  85 */       var10.buildPropertyValueTable(var3);
/*     */     } 
/*     */     
/*  88 */     this.validStates = ImmutableList.copyOf(var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableList getValidStates() {
/*  93 */     return this.validStates;
/*     */   }
/*     */ 
/*     */   
/*     */   private List getAllowedValues() {
/*  98 */     ArrayList<Collection> var1 = Lists.newArrayList();
/*     */     
/* 100 */     for (int var2 = 0; var2 < this.properties.size(); var2++)
/*     */     {
/* 102 */       var1.add(((IProperty)this.properties.get(var2)).getAllowedValues());
/*     */     }
/*     */     
/* 105 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getBaseState() {
/* 110 */     return (IBlockState)this.validStates.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/* 115 */     return this.block;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection getProperties() {
/* 120 */     return (Collection)this.properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     return Objects.toStringHelper(this).add("block", Block.blockRegistry.getNameForObject(this.block)).add("properties", Iterables.transform((Iterable)this.properties, GET_NAME_FUNC)).toString();
/*     */   }
/*     */   
/*     */   static class StateImplemenation
/*     */     extends BlockStateBase
/*     */   {
/*     */     private final Block block;
/*     */     private final ImmutableMap properties;
/*     */     private ImmutableTable propertyValueTable;
/*     */     private static final String __OBFID = "CL_00002027";
/*     */     
/*     */     private StateImplemenation(Block p_i45660_1_, ImmutableMap p_i45660_2_) {
/* 137 */       this.block = p_i45660_1_;
/* 138 */       this.properties = p_i45660_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection getPropertyNames() {
/* 143 */       return Collections.unmodifiableCollection((Collection<?>)this.properties.keySet());
/*     */     }
/*     */ 
/*     */     
/*     */     public Comparable getValue(IProperty property) {
/* 148 */       if (!this.properties.containsKey(property))
/*     */       {
/* 150 */         throw new IllegalArgumentException("Cannot get property " + property + " as it does not exist in " + this.block.getBlockState());
/*     */       }
/*     */ 
/*     */       
/* 154 */       return property.getValueClass().cast(this.properties.get(property));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public IBlockState withProperty(IProperty property, Comparable value) {
/* 160 */       if (!this.properties.containsKey(property))
/*     */       {
/* 162 */         throw new IllegalArgumentException("Cannot set property " + property + " as it does not exist in " + this.block.getBlockState());
/*     */       }
/* 164 */       if (!property.getAllowedValues().contains(value))
/*     */       {
/* 166 */         throw new IllegalArgumentException("Cannot set property " + property + " to " + value + " on block " + Block.blockRegistry.getNameForObject(this.block) + ", it is not an allowed value");
/*     */       }
/*     */ 
/*     */       
/* 170 */       return (this.properties.get(property) == value) ? this : (IBlockState)this.propertyValueTable.get(property, value);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public ImmutableMap getProperties() {
/* 176 */       return this.properties;
/*     */     }
/*     */ 
/*     */     
/*     */     public Block getBlock() {
/* 181 */       return this.block;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object p_equals_1_) {
/* 186 */       return (this == p_equals_1_);
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 191 */       return this.properties.hashCode();
/*     */     }
/*     */ 
/*     */     
/*     */     public void buildPropertyValueTable(Map map) {
/* 196 */       if (this.propertyValueTable != null)
/*     */       {
/* 198 */         throw new IllegalStateException();
/*     */       }
/*     */ 
/*     */       
/* 202 */       HashBasedTable var2 = HashBasedTable.create();
/* 203 */       UnmodifiableIterator<IProperty> unmodifiableIterator = this.properties.keySet().iterator();
/*     */       
/* 205 */       while (unmodifiableIterator.hasNext()) {
/*     */         
/* 207 */         IProperty var4 = unmodifiableIterator.next();
/* 208 */         Iterator<Comparable> var5 = var4.getAllowedValues().iterator();
/*     */         
/* 210 */         while (var5.hasNext()) {
/*     */           
/* 212 */           Comparable var6 = var5.next();
/*     */           
/* 214 */           if (var6 != this.properties.get(var4))
/*     */           {
/* 216 */             var2.put(var4, var6, map.get(setPropertyValue(var4, var6)));
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 221 */       this.propertyValueTable = ImmutableTable.copyOf((Table)var2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Map setPropertyValue(IProperty property, Comparable value) {
/* 227 */       HashMap<IProperty, Comparable> var3 = Maps.newHashMap((Map)this.properties);
/* 228 */       var3.put(property, value);
/* 229 */       return var3;
/*     */     }
/*     */ 
/*     */     
/*     */     StateImplemenation(Block p_i45661_1_, ImmutableMap p_i45661_2_, Object p_i45661_3_) {
/* 234 */       this(p_i45661_1_, p_i45661_2_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\BlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */