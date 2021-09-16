package net.minecraft.entity.ai.attributes;

import java.util.Collection;
import java.util.UUID;

public interface IAttributeInstance {
  IAttribute getAttribute();
  
  double getBaseValue();
  
  void setBaseValue(double paramDouble);
  
  Collection getModifiersByOperation(int paramInt);
  
  Collection func_111122_c();
  
  boolean func_180374_a(AttributeModifier paramAttributeModifier);
  
  AttributeModifier getModifier(UUID paramUUID);
  
  void applyModifier(AttributeModifier paramAttributeModifier);
  
  void removeModifier(AttributeModifier paramAttributeModifier);
  
  void removeAllModifiers();
  
  double getAttributeValue();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\attributes\IAttributeInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */