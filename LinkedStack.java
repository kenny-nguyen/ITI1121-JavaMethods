/** Implements the interface <code>Stack</code> using linked elements.
 *
 *
 * @author  Marcel Turcotte (turcotte@eecs.uottawa.ca)
 */

public class LinkedStack<E> implements Stack<E> {

    // Objects of the class Elem are used to store the elements of the
    // stack.
    private int size=0;


    private static class Elem<T> {
        private T value;
        private Elem<T> next;

        private Elem(T value, Elem<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    // Reference to the top element
    
    private Elem<E> top;

    /** Returns <code>true</code> if this stack is empty, and
     * <code>false</code> otherwise.
     *
     * @return <code>true</code> if this stack is empty, and
     * <code>false</code> otherwise.
     */

    public boolean isEmpty() {
        return top == null;
    }

    /** Inserts an element onto the stack.
     *
     * @param value the element to be inserted
     */

    public void push(E value) {

	if (value == null) {
	    throw new NullPointerException();
	}
	
        top = new Elem<E>(value, top);
        size++;
    }

    /** Returns the top element, without removing it.
     *
     * @return the top element
     */

    public E peek() {

	// pre-condition: the stack is not empty
	
        return top.value;
    }

    /** Removes and returns the top element.
     *
     *  @return the top element
     */

    public E pop() {

	// pre-condition: the stack is not empty
	
        E saved = top.value;
        top = top.next;
        size--;
        return saved;

    }

    /** Removes the top element of the stack. The element inserted at
     * the bottom of the stack.
     */
    public void roll(){
        if (this.isEmpty()){
            return;
        }
        LinkedStack<E> temp= new LinkedStack<E>();
        E first=this.pop();
        pack(temp,first);
    }

    /**
    * empty the stack and populates a temporary linkedstack.
    *
    * @param temp a linkedstack used to help the method roll()
    * @param first a value of the list 
    */
    private void pack(LinkedStack<E> temp,E first){
        if(this.isEmpty()){ //base case
                this.push(first);
                unpack(temp);
            }
            else{ //general case
        
                temp.push(this.pop()); //empty the stack,and populate temp
                pack(temp,first);
            }
        }

    /**
    * populates the stack for roll() method
    * @param temp a value of the stack.
    */
    private void unpack(LinkedStack<E> temp){
        if(temp.isEmpty()){ //base case
                // do nothing
        }
        else //general case
        {
            this.push(temp.pop());
            unpack(temp);
        }
    }


    /** Removes the botttom element. The element is inserted on the
     * top of the stack.
     */

    public void unroll() {
        if (this.isEmpty()){
            return;
        }
        LinkedStack<E> temp = new LinkedStack<E>();
        unrollPack(temp);
        E last = temp.pop();
        unrollUnPack(temp, last);


    }

    /**
    *populates a temporary stack to assist the unroll() method
    *@param temp a value of the stack
    */
    private void unrollPack(LinkedStack<E> temp){

        if (this.isEmpty()){
            //do nothing
        }
        else{
            temp.push(this.pop());
            unrollPack(temp);

        }

    }

    /**
    *
    *@param temp a temporary stack used to help the unroll() method
    *@param last a value of the stack
    */
    private void unrollUnPack(LinkedStack<E> temp, E last){
        if (temp.isEmpty()){
            this.push(last);
        }

        else{
            this.push(temp.pop());
            unrollUnPack(temp, last);
        }
    }

    /**
    *returns the size of a given stack
    *@return size
    */
    private int size(){
        return size;
    }

    /** Returns a string representation of the stack.
     *
     * @return a string representation
     */

    @Override public String toString() {
	StringBuffer stackStr = new StringBuffer("{");

	Elem<E> current = top;
	
	while (current != null) {
	    stackStr.append(current.value);
	    if (current.next != null) {
		stackStr.append(",");
	    }
	    current = current.next;
	}
	stackStr.append("}");

	return stackStr.toString();
    }
    
}
