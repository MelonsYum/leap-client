package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;

public interface IChunkProvider {
  boolean chunkExists(int paramInt1, int paramInt2);
  
  Chunk provideChunk(int paramInt1, int paramInt2);
  
  Chunk func_177459_a(BlockPos paramBlockPos);
  
  void populate(IChunkProvider paramIChunkProvider, int paramInt1, int paramInt2);
  
  boolean func_177460_a(IChunkProvider paramIChunkProvider, Chunk paramChunk, int paramInt1, int paramInt2);
  
  boolean saveChunks(boolean paramBoolean, IProgressUpdate paramIProgressUpdate);
  
  boolean unloadQueuedChunks();
  
  boolean canSave();
  
  String makeString();
  
  List func_177458_a(EnumCreatureType paramEnumCreatureType, BlockPos paramBlockPos);
  
  BlockPos func_180513_a(World paramWorld, String paramString, BlockPos paramBlockPos);
  
  int getLoadedChunkCount();
  
  void func_180514_a(Chunk paramChunk, int paramInt1, int paramInt2);
  
  void saveExtraData();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\IChunkProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */