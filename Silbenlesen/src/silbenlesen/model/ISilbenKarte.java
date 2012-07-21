package silbenlesen.model;

public interface ISilbenKarte {
  public String getSilbe();

  public void setIsWaiting(boolean isWating);

  public boolean isWaitingForChoosen();

  public boolean wasAlreadyFound();

  public void setAlreadyFound(boolean found);
}
