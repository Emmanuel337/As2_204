public class StackOverflowException extends Exception {

  public StackOverflowException() {
    super("Push method has been called on a full stack");
  }

}