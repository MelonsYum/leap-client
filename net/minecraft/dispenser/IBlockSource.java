package net.minecraft.dispenser;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public interface IBlockSource extends ILocatableSource {
  double getX();
  
  double getY();
  
  double getZ();
  
  BlockPos getBlockPos();
  
  Block getBlock();
  
  int getBlockMetadata();
  
  TileEntity getBlockTileEntity();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\dispenser\IBlockSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */