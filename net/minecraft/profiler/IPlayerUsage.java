package net.minecraft.profiler;

public interface IPlayerUsage {
  void addServerStatsToSnooper(PlayerUsageSnooper paramPlayerUsageSnooper);
  
  void addServerTypeToSnooper(PlayerUsageSnooper paramPlayerUsageSnooper);
  
  boolean isSnooperEnabled();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\profiler\IPlayerUsage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */