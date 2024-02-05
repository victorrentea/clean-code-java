package sample.two;

import java.util.Optional;

public class DataSegment {
  private ChannelType channelType;
  private TelephonySource telSource;
  private Optional<DialerParticipant> dialerParticipant;

  public ChannelType getChannelType() {
    return channelType;
  }

  public void setChannelType(ChannelType channelType) {
    this.channelType = channelType;
  }

  public TelephonySource getTelSource() {
    return telSource;
  }

  public void setTelSource(TelephonySource telSource) {
    this.telSource = telSource;
  }

  public Optional<DialerParticipant> getDialerParticipant() {
    return dialerParticipant;
  }

  public void setDialerParticipant(Optional<DialerParticipant> dialerParticipant) {
    this.dialerParticipant = dialerParticipant;
  }
}
