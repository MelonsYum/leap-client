package net.minecraft.network;

import java.io.IOException;

public interface Packet {
  void readPacketData(PacketBuffer paramPacketBuffer) throws IOException;
  
  void writePacketData(PacketBuffer paramPacketBuffer) throws IOException;
  
  void processPacket(INetHandler paramINetHandler);
}


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\Packet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */