package net.minecraftforge.client.model;

import com.google.common.base.Function;

public interface IModelState extends Function<IModelPart, TRSRTransformation> {
  TRSRTransformation apply(IModelPart paramIModelPart);
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraftforge\client\model\IModelState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */