package silbenlesen.gui;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import silbenlesen.Activator;
import silbenlesen.model.ISilbenKarte;
import silbenlesen.model.SilbenModel;

public class ButtonJB extends Button {
  @Override
  protected void checkSubclass() {}

  private final ISilbenKarte silbenKarte;
  private final ButtonJB     button;

  public ButtonJB(Composite parent, int style, ISilbenKarte iSilbenKarte, final SilbenModel sm) {
    super(parent, style);
    final SilbenModel silbenModel = sm;
    silbenKarte = iSilbenKarte;
    button = this;
    this.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        if (silbenKarte.isWaitingForChoosen()) {
          // System.out.println("OK");
          silbenKarte.setAlreadyFound(true);
          // Activator.getDefault().playSound("Richtig");
          button.setText("");
          // try {
          // Thread.sleep(2000);
          // } catch (InterruptedException e1) {
          // // TODO Auto-generated catch block
          // e1.printStackTrace();
          // }
          silbenModel.pickNextSilbe();
          // silbenModel.remove(silbenKarte);
          silbenKarte.setIsWaiting(false);
        } else {
          // System.out.println("Fehler");
          sm.exerciseControl.incErrorCounter();
          Activator.getDefault().playSound("Falsch");
        }
      }
    });
  }
}
