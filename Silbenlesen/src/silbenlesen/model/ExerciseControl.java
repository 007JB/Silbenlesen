package silbenlesen.model;

public class ExerciseControl {
  private int  errorCnt = 0;
  private long startTime;
  private long execTime;

  public void incErrorCounter() {
    errorCnt++;
  }

  private void clear() {
    errorCnt = 0;
  }

  public int getErrorAmount() {
    return errorCnt;
  }

  public void startExercise() {
    clear();
    startTime = System.currentTimeMillis();
  }

  public void stopExercise() {
    execTime = System.currentTimeMillis() - startTime;
  }

  public long getExecTimeInSec() {
    return execTime / 1000;
  }
}
