package silbenlesen;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
  // The plug-in ID
  public static final String PLUGIN_ID = "Silbenlesen"; //$NON-NLS-1$
  // The shared instance
  private static Activator   plugin;

  /**
   * The constructor
   */
  public Activator() {}

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in relative path
   * @param path the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }

  public void playSound(String name) {
    InputStream streamRes = this.getClass().getResourceAsStream("/resources/Mama/" + name.toLowerCase() + ".wav");
    try {
      AudioInputStream stream = AudioSystem.getAudioInputStream(streamRes);
      // AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat());
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(stream);
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
