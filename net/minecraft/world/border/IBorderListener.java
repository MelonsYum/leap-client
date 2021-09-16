package net.minecraft.world.border;

public interface IBorderListener {
  void onSizeChanged(WorldBorder paramWorldBorder, double paramDouble);
  
  void func_177692_a(WorldBorder paramWorldBorder, double paramDouble1, double paramDouble2, long paramLong);
  
  void onCenterChanged(WorldBorder paramWorldBorder, double paramDouble1, double paramDouble2);
  
  void onWarningTimeChanged(WorldBorder paramWorldBorder, int paramInt);
  
  void onWarningDistanceChanged(WorldBorder paramWorldBorder, int paramInt);
  
  void func_177696_b(WorldBorder paramWorldBorder, double paramDouble);
  
  void func_177695_c(WorldBorder paramWorldBorder, double paramDouble);
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\border\IBorderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */