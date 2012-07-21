package silbenlesen.gui;

import java.util.List;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import silbenlesen.Activator;
import silbenlesen.model.IModelChangeListener;
import silbenlesen.model.ISilbenKarte;
import silbenlesen.model.SilbenModel;

public class SilbenView extends ViewPart implements IModelChangeListener {
  public static final String ID      = "silbenlesen.gui.SilbenView";         //$NON-NLS-1$
  private final FormToolkit  toolkit = new FormToolkit(Display.getCurrent());
  // private Composite cont;
  private Composite          _parent;
  private Composite          current_container;
  private SilbenModel        sm;

  // private Composite cont;
  public SilbenView() {}

  /**
   * // * The content provider class is responsible for providing objects to the view. It can wrap existing objects in adapters or simply return
   * objects // * as-is. These objects may be sensitive to the current input of the view, or ignore it and always show the same content (like Task
   * List, for // * example). //
   */
  // class ViewContentProvider implements IStructuredContentProvider {
  // @Override
  // public void inputChanged(Viewer v, Object oldInput, Object newInput) {}
  //
  // @Override
  // public void dispose() {}
  //
  // @Override
  // public Object[] getElements(Object parent) {
  // if (parent instanceof Object[]) {
  // return (Object[]) parent;
  // }
  // return new Object[0];
  // }
  // }
  //
  // class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
  // @Override
  // public String getColumnText(Object obj, int index) {
  // return getText(obj);
  // }
  //
  // @Override
  // public Image getColumnImage(Object obj, int index) {
  // return getImage(obj);
  // }
  //
  // @Override
  // public Image getImage(Object obj) {
  // return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
  // }
  // }
  /**
   * Create contents of the view part.
   * @param parent
   */
  @Override
  public void createPartControl(Composite parent) {
    _parent = parent;
    // cont = toolkit.createComposite(parent, SWT.NONE);
    fillContainer(_parent);
  }

  private void fillContainer(Composite parent) {
    // new Composite(parent, SWT.NONE);
    if (current_container != null) current_container.dispose();
    sm = new SilbenModel();
    sm.setModelChangeListener(this);
    Composite container = toolkit.createComposite(parent, SWT.NONE);
    current_container = container;
    toolkit.paintBordersFor(container);
    container.setLayout(new GridLayout(sm.maxY, true));
    addButtons(container, sm);
    {
      Button btnNewButton = new Button(container, SWT.NONE);
      btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
      btnNewButton.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          // System.out.println("ddddd");
          sm.pickNextSilbe();
          sm.exerciseControl.startExercise();
        }
      });
      btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
      toolkit.adapt(btnNewButton, true, true);
      btnNewButton.setText("Start");
    }
    {
      Button btnNewButton_1 = new Button(container, SWT.NONE);
      btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
      btnNewButton_1.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          List<ISilbenKarte> all = sm.getAllSilben();
          for (ISilbenKarte iSilbenKarte : all) {
            if (iSilbenKarte.isWaitingForChoosen()) {
              Activator.getDefault().playSound(iSilbenKarte.getSilbe());
              return;
            }
          }
        }
      });
      btnNewButton_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
      toolkit.adapt(btnNewButton_1, true, true);
      btnNewButton_1.setText("Wiederhohlen");
    }
    {
      Button btnNewButton_2 = new Button(container, SWT.NONE);
      btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
      btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
      btnNewButton_2.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent e) {
          System.out.println("");
          fillContainer(_parent);
          // if (parent != null) {
          // parent.getc
          // }
        }
      });
      toolkit.adapt(btnNewButton_2, true, true);
      btnNewButton_2.setText("Neues Spiel");
    }
    // new Label(container, SWT.NONE);
    // new Label(container, SWT.NONE);
    // new Label(container, SWT.NONE);
    createActions();
    initializeToolBar();
    initializeMenu();
    // container.redraw();
    _parent.layout(true);
  }

  private void addButtons(Composite container, SilbenModel sm) {
    for (ISilbenKarte iSilbenKarte : sm.getAllSilben()) {
      GridData gd = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
      gd.widthHint = 200;
      gd.heightHint = 50;
      ButtonJB btnNewButton = new ButtonJB(container, SWT.NONE, iSilbenKarte, sm);
      btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
      btnNewButton.setLayoutData(gd);
      toolkit.adapt(btnNewButton, true, true);
      btnNewButton.setText(iSilbenKarte.getSilbe());
    }
  }

  @Override
  public void dispose() {
    toolkit.dispose();
    super.dispose();
  }

  /**
   * Create the actions.
   */
  private void createActions() {
    // Create the actions
  }

  /**
   * Initialize the toolbar.
   */
  private void initializeToolBar() {
    IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
  }

  /**
   * Initialize the menu.
   */
  private void initializeMenu() {
    IMenuManager manager = getViewSite().getActionBars().getMenuManager();
  }

  @Override
  public void setFocus() {
    // Set the focus
  }

  // @Override
  // public void modleChanged() {}
  @Override
  public void endReached() {
    sm.exerciseControl.stopExercise();
    System.out.println("Fehler:" + sm.exerciseControl.getErrorAmount() + " in " + sm.exerciseControl.getExecTimeInSec() + " Sekunden");
    if (sm.exerciseControl.getErrorAmount() == 0) {
      Activator.getDefault().playSound("richtig");
    }
    MessageBox mb = new MessageBox(_parent.getShell(), SWT.ICON_INFORMATION);
    mb.setText("Ergebnis");
    mb.setMessage("Zeit:" + sm.exerciseControl.getExecTimeInSec() + " Sekunden\nFehler:" + sm.exerciseControl.getErrorAmount());
    // // Move the dialog to the center of the top level shell.
    // Rectangle shellBounds = _parent.getShell().getBounds();
    // Point dialogSize = mb.get
    // dialog.setLocation(shellBounds.x + (shellBounds.width - dialogSize.x) / 2, shellBounds.y + (shellBounds.height - dialogSize.y) / 2);
    // open
    mb.open();
    fillContainer(_parent); // restart
  }
}
