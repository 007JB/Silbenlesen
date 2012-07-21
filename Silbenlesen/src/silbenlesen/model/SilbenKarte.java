package silbenlesen.model;

public class SilbenKarte implements ISilbenKarte {
  private final String silbe;
  private boolean      isWaiting;
  private boolean      found;

  public SilbenKarte(String string) {
    silbe = string;
    isWaiting = false;
    found = false;
  }

  @Override
  public String getSilbe() {
    return silbe;
  }

  @Override
  public void setIsWaiting(boolean isWating) {
    this.isWaiting = isWating;
  }

  @Override
  public boolean isWaitingForChoosen() {
    return isWaiting;
  }

  @Override
  public boolean wasAlreadyFound() {
    return found;
  }

  @Override
  public void setAlreadyFound(boolean found) {
    this.found = found;
  }
}
