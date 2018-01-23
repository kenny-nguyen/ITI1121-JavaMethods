import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using linked
 *  elements. The linked structure is circular and uses a dummy node.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class LinearFrequencyTable implements FrequencyTable {
    private LinkedList list;
    // Linked elements

    private static class Elem {

	private String key;
	private long count;
	private Elem previous;
	private Elem next;

	private Elem(String key, Elem previous, Elem next) {
	    this.key = key;
	    this.count = 0;
	    this.previous = previous;
	    this.next = next;
	}

    }

    private Elem head;
    private int size;

    /** Constructs and empty <strong>FrequencyTable</strong>.
     */

    public LinearFrequencyTable() {
	head = new Elem(null, null, null); // dummy node
	head.previous = head; // making the dummy node circular
	head.next = head; // making the dummy node circular
	size = 0;
    }

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */

    public int size() {
	return size;
    }
  
    /** Returns the frequency value associated with this key.
     *
     *  @param key key whose frequency value is to be returned
     *  @return the frequency associated with this key
     *  @throws NoSuchElementException if the key is not found
     */

    public long get(String key){
        Elem tmp = head.next;
        while (tmp !=head){
            if (tmp.key.equals(key)){
                return tmp.count;
            }
            tmp=tmp.next;
        }
        throw new NoSuchElementException("The key is not found.");
    }

    /** Creates an entry in the frequency table and initializes its
     *  count to zero. The keys are kept in order (according to their
     *  method <strong>compareTo</strong>).
     *
     *  @param key key with which the specified value is to be associated
     *  @throws IllegalArgumentException if the key was alreaddy present
     */

    public void init(String key) {
        Elem node;
        if(size()==0){
            node = new Elem(key,head,head);
            head.next=node;
            head.previous=node;
            size++;

        }else{
            Elem tmp;
            tmp = head.next;
            int check=0;

            while(check<size){

                if(tmp.key.compareTo(key)==0) throw new IllegalArgumentException("The key " +key+ "is already present");

                if (tmp.key.compareTo(key)>0){
                    node = new Elem(key,tmp.previous,tmp);
                    tmp.previous.next=node;
                    tmp.previous = node;
                    size++;
                    break;
                }
            tmp=tmp.next;
            check++;
            }

            if (tmp.key==null){
            node = new Elem(key,head.previous,head);
            head.previous.next=node;
            head.previous=node;
            size++;

            }
        }

    }

    /** The method updates the frequency associed with the key by one.
     *
     *  @param key key with which the specified value is to be associated
     *  @throws NoSuchElementException if the key is not found
     */

    public void update(String key) {

    int check=0;
    Elem tmp = head.next;

        while(check<size()){

            if (tmp.key.compareTo(key)==0){
                tmp.count++;
                break;
            }

            tmp=tmp.next;
            check++;
        }

        if(tmp.key==null) throw new NoSuchElementException("Key" +key+ "not found");

    }

    /** Returns the list of keys in order, according to the method
     *  <strong>compareTo</strong> of the key objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {
    Elem tmp = head.next;
    LinkedList list = new LinkedList();
    for (int i=0;i<size();i++){
        list.add(i, tmp.key);
        tmp=tmp.next;
    }
    System.out.println("hey");
    return list;
    }

    /** Returns an array containing the frequencies of the keys in the
     *  order specified by the method <strong>compareTo</strong> of
     *  the key objects.
     *
     *  @return an array of frequency counts
     */

    public long[] values() {
    Elem tmp = head.next;
    long[] array = new long[size];
    for (int i=0;i<size();i++){
        array[i] = tmp.count;
        tmp=tmp.next;
    }
    return array;
    }

    /** Returns a <code>String</code> representations of the elements
     * of the frequency table.
     *  
     *  @return the string representation
     */

    public String toString() {

	StringBuffer str = new StringBuffer("{");
	Elem p = head.next;

	while (p != head) {
	    str.append("{key="+p.key+", count="+p.count+"}");
	    if (p.next != head) {
		str.append(",");
	    }
	    p = p.next;
	}
	str.append("}");
	return str.toString();
    }

}
