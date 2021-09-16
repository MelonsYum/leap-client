package net.minecraft.block.state;

import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;

public interface IBlockState {
  Collection getPropertyNames();
  
  Comparable getValue(IProperty paramIProperty);
  
  IBlockState withProperty(IProperty paramIProperty, Comparable paramComparable);
  
  IBlockState cycleProperty(IProperty paramIProperty);
  
  ImmutableMap getProperties();
  
  Block getBlock();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\state\IBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */