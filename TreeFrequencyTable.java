import java.util.NoSuchElementException;

/** Implements the interface <code>FrequencyTable</code> using a
 *  binary search tree.
 *
 * @author Marcel Turcotte (turcott@eecs.uottawa.ca)
 */

public class TreeFrequencyTable implements FrequencyTable {

    // Stores the elements of this binary search tree (frequency
    // table)
    
    private static class Elem {
    
        private String key;
        private long count;
    
        private Elem left;
        private Elem right;
    
        private Elem(String key) {
            this.key = key;
            this.count = 0;
            left = null;
            right = null;
        }
    }

    private Elem root = null; // A reference to the root element
    private int size = 0; // The size of the tree
    LinkedList store = new LinkedList();
    LinkedList<Long> storeValues = new LinkedList<Long>();

    /** The size of the frequency table.
     *
     * @return the size of the frequency table
     */
    
    public int size() {
        return size;
    }
  
    /** Creates an entry in the frequency table and initializes its
     *  count to zero.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void init(String key) {
    
        Elem newNode = new Elem(key);
        if (root==null){
            root = newNode;
            return;
        }
        Elem current = root;
        Elem parent = null;
        while (true){
            parent = current;
            if (parent.key.compareTo(key)>0){
                current = current.left;
                if (current==null){
                    parent.left = newNode;
                    return;
                }
            }else{
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    return;
                }
            }
        }

    
    }
  
    /** The method updates the frequency associed with the key by one.
     *
     * @param key key with which the specified value is to be associated
     */
  
    public void update(String key) {

        Elem tmp = new Elem(key);
        Elem current = root;
        while (current!=null){
            if (current.key.compareTo(key)==0){
                current.count++;
                break;
            }
            else if(current.key.compareTo(key)>0){
                current = current.left;
            }
            else{
                current = current.right;
            }

        }
        if (current==null){
            System.out.println("The key '" +key+ "' does not exist.");
        }
    
    }
  
    /**
     * Looks up for key in this TreeFrequencyTable, returns associated value.
     *
     * @param key value to look for
     * @return value the value associated with this key
     * @throws NoSuchElementException if the key is not found
     */
  
    public long get(String key) {
    
        Elem current = root;
        while (current!=null){
            if(current.key.compareTo(key)==0){
                break;
            }
            else if (current.key.compareTo(key)>0){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }
        if (current==null) throw new NoSuchElementException("Key does not exist.");
        return current.count;
    }
  
    /** Returns the list of keys in order, according to the method compareTo of the key
     *  objects.
     *
     *  @return the list of keys in order
     */

    public LinkedList<String> keys() {
        store = new LinkedList<String>();
        display(root);
        return store;
    }

    /**
    * Add values from increasing order to a linked list.
    *
    *@param root
    */
    private void display(Elem root){

        if (root!=null){
            display(root.left);
            store.addLast(root.key);
            display(root.right);
        }
    }

    /** Returns the values in the order specified by the method compareTo of the key
     *  objects.
     *
     *  @return the values
     */

    public long[] values() {

        storeValues = new LinkedList<Long>();
        display2(root);
        long[] val = new long[storeValues.size()];
        for (int i=0; i<val.length;i++){
            val[i] = storeValues.get(i);
        }
        return val;
    }

    /**
    * Place values in order specified by method compareTo.
    *@param root
    */
    private void display2(Elem root){
        if (root!=null){
            display2(root.left);
            storeValues.addLast(root.count);
            display2(root.right);
        }
    }

    /** Returns a String representation of the tree.
     *
     * @return a String representation of the tree.
     */

    public String toString() {
        return toString( root );
    }

    // Helper method.
  
    private String toString(Elem current) {
    
        if (current == null) {
            return "{}";
        }
    
        return "{" + toString(current.left) + "[" + current.key + "," + current.count + "]" + toString(current.right) + "}";
    }
  
}
