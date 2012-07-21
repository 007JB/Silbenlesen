package silbenlesen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import silbenlesen.Activator;

public class SilbenModel {
  public final int                 maxX = 3;
  public final int                 maxY = 3;
  private final List<ISilbenKarte> karten;
  private IModelChangeListener     mcListener;
  public final ExerciseControl     exerciseControl;

  public SilbenModel() {
    exerciseControl = new ExerciseControl();
    karten = new ArrayList<ISilbenKarte>();
    for (int x = 1; x <= maxX; x++) {
      for (int y = 1; y <= maxY; y++) {
        ISilbenKarte sk = SilbenFactory.getSilbe();
        addSilbe(sk);
      }
    }
    // pickNextSilbe();
  }

  private boolean addSilbe(ISilbenKarte sk) {
    karten.add(sk);
    return true;
  }

  public void remove(ISilbenKarte sk) {
    karten.remove(sk);
    pickNextSilbe();
  }

  public List<ISilbenKarte> getAllSilben() {
    return karten;
  }

  public void pickNextSilbe() {
    boolean found = false;
    int pick = -1;
    while (!found) {
      pick = new Random().nextInt(karten.size());
      if (!karten.get(pick).wasAlreadyFound()) {
        found = true;
      }
      if (noMoreCards()) {
        found = true;
        mcListener.endReached();
        return;
      }
    }
    // System.out.println("wait:" + karten.get(pick).getSilbe());
    karten.get(pick).setIsWaiting(true);
    for (int i = 0; i < karten.size(); i++) {
      karten.get(i).setIsWaiting(karten.get(i).getSilbe().equalsIgnoreCase(karten.get(pick).getSilbe()));
    }
    Activator.getDefault().playSound(karten.get(pick).getSilbe());
  }

  private boolean noMoreCards() {
    for (int i = 0; i < karten.size(); i++) {
      if (!karten.get(i).wasAlreadyFound()) return false;
    }
    return true;
  }

  public void setModelChangeListener(IModelChangeListener listener) {
    mcListener = listener;
  }
}
