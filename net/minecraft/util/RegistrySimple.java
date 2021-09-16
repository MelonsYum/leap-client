/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class RegistrySimple
/*    */   implements IRegistry {
/* 14 */   private static final Logger logger = LogManager.getLogger();
/*    */ 
/*    */   
/* 17 */   protected final Map registryObjects = createUnderlyingMap();
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00001210";
/*    */ 
/*    */ 
/*    */   
/*    */   protected Map createUnderlyingMap() {
/* 25 */     return Maps.newHashMap();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getObject(Object p_82594_1_) {
/* 30 */     return this.registryObjects.get(p_82594_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void putObject(Object p_82595_1_, Object p_82595_2_) {
/* 38 */     Validate.notNull(p_82595_1_);
/* 39 */     Validate.notNull(p_82595_2_);
/*    */     
/* 41 */     if (this.registryObjects.containsKey(p_82595_1_))
/*    */     {
/* 43 */       logger.debug("Adding duplicate key '" + p_82595_1_ + "' to registry");
/*    */     }
/*    */     
/* 46 */     this.registryObjects.put(p_82595_1_, p_82595_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set getKeys() {
/* 54 */     return Collections.unmodifiableSet(this.registryObjects.keySet());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean containsKey(Object p_148741_1_) {
/* 62 */     return this.registryObjects.containsKey(p_148741_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterator iterator() {
/* 67 */     return this.registryObjects.values().iterator();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\RegistrySimple.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */