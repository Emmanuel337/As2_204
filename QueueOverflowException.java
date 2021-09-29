public class QueueOverflowException extends Exception {

  public QueueOverflowException() {
    super("Enqueue method has been called on a full queue");
  }

}