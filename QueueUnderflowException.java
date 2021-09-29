public class QueueUnderflowException extends Exception {

  public QueueUnderflowException() {
    super("Dequeue method has been called on an empty queue");
  }

}