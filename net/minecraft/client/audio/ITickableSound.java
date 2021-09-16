package net.minecraft.client.audio;

import net.minecraft.server.gui.IUpdatePlayerListBox;

public interface ITickableSound extends ISound, IUpdatePlayerListBox {
  boolean isDonePlaying();
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\ITickableSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */