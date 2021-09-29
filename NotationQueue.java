import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {

  private Object[] elements;
  private int first;
  private int last;
  private int numElements;
  private int capacity;


  
  public NotationQueue() {
    capacity = 20;
    elements = new Object[capacity];

  }

  
  public NotationQueue(int capacity) {
    this.capacity = capacity;
    this.first = this.last = -1;
    this.numElements = 0;
    elements = new Object[capacity];
  }

   
  public boolean isEmpty() {
    return numElements == 0;
  }

  @Override
  public void fill(ArrayList<T> list) {
    ArrayList<T> cloneList = new ArrayList<>(list);
    cloneList.forEach(t -> {
      try {
        enqueue(t);
      } catch (QueueOverflowException ex) {
        ex.getMessage();
      }
    });
  }
   
  public boolean isFull() {
    return capacity == numElements;
  }

   
  @Override
  public int size() {
    return numElements;
  }

   
  @Override
  public boolean enqueue(T c) throws QueueOverflowException {
    if (isFull()) {
      throw new QueueOverflowException();
    }
    
    if(isEmpty()) {
      first = last = 0;
    } else {
      last++;
    }
    numElements++;
    elements[last] = c;
    return true;
  }

   
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
   
    for (int i = first; i <= last; i++) {
      sb.append(elements[i]);
    }
    return sb.toString();
  }

   
  public T dequeue() throws QueueUnderflowException {
	    if (isEmpty()) {
	      throw new QueueUnderflowException();
	    }
	    @SuppressWarnings("unchecked")
	    T firstInLine = (T) elements[first];
	    if (firstInLine == null)
	      return null;
	    elements[first] = null;
	    first++;
	    numElements--;
	    return firstInLine;
	  }
  
  @Override
  public String toString(String delimiter) {
    StringBuilder SB = new StringBuilder();
    
    for (int i = first; i < last; i++) {
      SB.append(elements[i] + delimiter);
    }
    SB.append(elements[last]);
    return SB.toString();
  }

}