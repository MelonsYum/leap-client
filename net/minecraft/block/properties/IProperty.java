package net.minecraft.block.properties;

import java.util.Collection;

public interface IProperty {
  String getName();
  
  Collection getAllowedValues();
  
  Class getValueClass();
  
  String getName(Comparable paramComparable);
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\properties\IProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */