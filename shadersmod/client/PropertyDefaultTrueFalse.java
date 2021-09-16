/*    */ package shadersmod.client;
/*    */ 
/*    */ public class PropertyDefaultTrueFalse
/*    */   extends Property {
/*  5 */   public static final String[] PROPERTY_VALUES = new String[] { "default", "true", "false" };
/*  6 */   public static final String[] USER_VALUES = new String[] { "Default", "ON", "OFF" };
/*    */ 
/*    */   
/*    */   public PropertyDefaultTrueFalse(String propertyName, String userName, int defaultValue) {
/* 10 */     super(propertyName, PROPERTY_VALUES, userName, USER_VALUES, defaultValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDefault() {
/* 15 */     return (getValue() == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTrue() {
/* 20 */     return (getValue() == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFalse() {
/* 25 */     return (getValue() == 2);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\PropertyDefaultTrueFalse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */