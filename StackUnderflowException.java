public class StackUnderflowException extends Exception {

  public StackUnderflowException() {
    super("Pop or top method has been called on an empty stack");
  }

}