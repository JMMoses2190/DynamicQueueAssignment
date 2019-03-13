import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DynamicNode {

  private Object info;
  private DynamicNode next;

  public DynamicNode(Object x, DynamicNode n) {
    info = x;
    next = n;
  }

  public Object getInfo() {
    return info;
  }

  public DynamicNode getNext() {
    return next;
  }

  public void setInfo(Object x) {
    info = x;
  }

  public void setNext(DynamicNode n) {
    next = n;
  }

  public String toString() {
    return info.toString();
  }
}


public class DynamicQueue {

  private DynamicNode front, rear;
  int tail = 0;
  private int MAXSIZE = 4;

  public DynamicQueue() {
    front = rear = null;
  }

  public boolean isEmpty() {
    return (front == null);
  }

  /**
   * checks if queue is full
   */
  public boolean isFull() {

    int count = 0;
    DynamicNode p = front;
    while (p != null) {
      p = p.getNext();
      count++;
    }
    if (count == MAXSIZE) {
      return true;
    } else {
      return false;
    }

  }

  public void insert(Object x) {
    DynamicNode p = new DynamicNode(x, null);

    if (isEmpty()) {
      front = rear = p;
    } else {
      rear.setNext(p);

    }
    rear = p;
  }


  public Object remove() {
    if (isEmpty()) {
      System.out.println("Queue Underflow");
      System.exit(1);
    }
    DynamicNode p = front;
    Object temp = p.getInfo();
    front = p.getNext();

    if (front == null) {
      rear = null;
    }
    return temp;
  }

  public void print() {
    if (front == null) {
      System.out.println("Empty");
    }
    DynamicNode p = front;
    while (p != null) {
      System.out.print(p.getInfo() + ((p.getNext() != null) ? "->" : ""));
      p = p.getNext();
    }
    System.out.println();
  }

  /**
   * search queue to see if object already exists in the queue
   *
   * @return x
   */
  public boolean searchQueue(Object x) {
    DynamicNode p = front;
    Object temp = p.getInfo();

    if (!isEmpty()) {
      if (temp == x) {
        return false;
      } else {
        temp = p.getNext();
      }
    }

    return false;
  }

  /**
   * removes the front of the queue when the queue is full
   *
   * @return a new queue
   */
  public Object removeFront(DynamicQueue queue) {

    if (isEmpty()) {
      System.out.println("Queue underflow");
      System.exit(1);
    }
    DynamicNode p = front;
    Object temp = p.getInfo();
    front = p.getNext();

    if (front == null) {
      rear = null;
    }
    return temp;
  }

  public void delete(Object x) {
    if (isEmpty()) {
      System.out.println("queue underflow");
      System.exit(1);
    }

    DynamicNode p = front;
    Object temp = x;
    Object q = p.getInfo();
    while (temp != q) {
      q = p.getNext().getInfo();
    }

    p = null;


  }


  public static void main(String args[]) throws FileNotFoundException {

    File file = new File("data.txt");
    Scanner scan = new Scanner(file);

    int N = 4;
    DynamicQueue[] queues = new DynamicQueue[N];
    queues[0] = new DynamicQueue();
    queues[1] = new DynamicQueue();
    queues[2] = new DynamicQueue();
    queues[3] = new DynamicQueue();

    DynamicQueue queue = new DynamicQueue();

    char[] front = new char[10];

    while (scan.hasNext()) {                  //checks is there is a new line in the file

      char nextChar = scan.next().charAt(0);  //reads next char

      int queueID = scan.nextInt();           //reads next int as a queue identifier

      if (queues[queueID].isEmpty()) {
        //if the queue is empty, the char is added to the front of the queue
        queues[queueID].insert(nextChar);
        front[queueID] = nextChar;
        System.out.println("Read key " + nextChar + " for queue " + queueID + ". Inserting "
            + nextChar + " in rear. Q" + queueID + ": ");
        queues[queueID].print();
      } else {                                //proceeds if the the queue already has an item in it
        //checks if the char is already in the queue
        if (queues[queueID].searchQueue(nextChar)) {
          queues[queueID].delete(nextChar);
          queues[queueID].insert(nextChar);
          System.out.println("Read key " + nextChar + " for queue " + queueID + ". Moving "
              + nextChar + " to rear. Q" + queueID + ": ");
          queues[queueID].print();
        } else {
          //checks if the queue is already full
          if (!(queues[queueID].isFull())) {

            queues[queueID].insert(nextChar);
            System.out.println("Read key " + nextChar + " for queue " + queueID + ". Inserting "
                + nextChar + " in rear. Q" + queueID + ": ");
            queues[queueID].print();

          } else {
            //if none of the prior conditions are met then the char is added to the rear
            queues[queueID].removeFront(queues[queueID]);
            queues[queueID].insert(nextChar);
            System.out.println("Read key " + nextChar + " for queue " + queueID + ". Q is full, "
                + "removing front. Inserting " + nextChar + " in rear. Q" + queueID + ": ");
            queues[queueID].print();
          }
        }

      }

    }

    System.out.println("Q0: ");
    queues[0].print();
    System.out.println("Q1: ");
    queues[1].print();
    System.out.println("Q2: ");
    queues[2].print();
    System.out.println("Q3: ");
    queues[3].print();
  }

}
