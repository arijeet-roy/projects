package com.bplustree.third;


/** Project members :
 * ARIJEET ROY 
 * KWANGHOON AN */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class BPlusTree<Key extends Comparable<? super Key>, Value>
{
    /** Pointer to the root node. It starts as a leaf node but according to number of keys changes to inner node. */
    private Node root;
    /** the maximum number of keys in the leaf node, M must be > 0. We set it to 4 for our case*/
    private final int M;
    /** the maximum number of keys in inner node, the number of pointer is N+1, N must be > 2. 
     * We set it to 4 for our case */
    private final int N;

    
    /** Create a new empty tree. */
    public BPlusTree(int n) {
	this(n, n);
    }

    public BPlusTree(int m, int n) {
        M = m;
        N = n;
        root = new LNode();
    }

    public static void main(String[] args) {
    	
		BPlusTree<String, String> bPlusTree = new BPlusTree<>(4,4);
		String command = args[0];
		/*
		 * Create the b+ tree after reading text from the text file with buffer size set to 1K and inserting
		 * records sequentially into the b+ tree. Use that b+ tree to create an index file with the index and offset
		 * pointing to the record in the original text file.
		 */
		/*
		 * KWANGHOON AN*/
		if( command.equals("create") ){
			
			String txtName = args[1];
			String indxName = args[2];
			
			int indxLength = Integer.valueOf(args[3]);
			int offset = 0;

			String tempBuffer = "";
			char[] buffer = null;
			boolean isBufferFull = false;
			
			try {
	    		int startptr = 0;
	    		int wholeOff = 0;
	    		int recptr = 0;
				BufferedReader reader = new BufferedReader(new FileReader(txtName));
				while( reader.ready() ){
					buffer = new char[1024];
					reader.read(buffer);
					String key = String.valueOf(buffer);
					while( recptr < 1024 && !isBufferFull){
						while( buffer[recptr] != '\n' ){
							recptr++;
							wholeOff++;
							if( recptr >= 1024 ){
								isBufferFull = true;
								tempBuffer = key.substring(startptr, recptr);
								break;
							}
						}
						if( !isBufferFull ){
							if( tempBuffer.equals("") ){
								bPlusTree.insert(key.substring(startptr,  startptr + indxLength), String.valueOf((offset)) , key.substring(startptr + indxLength+1, recptr));
								offset = wholeOff+1;
							}
							else{
								String boundaryKey = tempBuffer + key.substring(startptr,  recptr);
								bPlusTree.insert(boundaryKey.substring(0, indxLength), String.valueOf((offset)), boundaryKey.substring(indxLength + 1));
								tempBuffer = "";
								offset = wholeOff+1;
							}
							wholeOff++;
						}
						else{
							break;
						}
						recptr++;
						startptr = recptr;
					}
					isBufferFull = false;
					startptr = 0;
					recptr = 0;
				}

				
				/*
				 * Delete the index file and recreate every time so that file gets overwritten after evry creation*/
		    	File deleteIdx = new File(indxName);
		    	deleteIdx.delete();
		    	
		    	BufferedWriter writer = null;
		    	
				try {
					writer = new BufferedWriter(new FileWriter(indxName, true));
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	try {
					writer.append(txtName + "\n");
					writer.append(offset + "\n");
					writer.flush();
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    	bPlusTree.dump(txtName,String.valueOf(indxLength),indxName);
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
	    	catch (IOException e) {
					e.printStackTrace();
			}

		}
		/*
		 * Insert a new record
		 * */
		/*
		 * KWANGHOON AN*/
		else if( command.equals("insert") ){
			String indxName = args[1];
			String indX = args[2];
			
			if( indX.length() != 15 ){	// Base step, not allow bad data
				System.out.println("Index length must be 15");
				return ;
			}
			String record = "";
			for( int i=0; i<args.length-3; i++ )
				record += args[ i+3 ] + " " ;
			record = record.substring(0, record.length() -1);
			//append the record to the end of the text file.
			bPlusTree.writeTodisk(indX, record, indxName);
			//add the index of the new record to the index file at the proper position along with the offset.
			bPlusTree.updateIndexFile(indxName, indX.length(), bPlusTree);
			
		}
		/*
		 * find the record and return it along with the index*/
		/*
		 * KWANGHOON AN*/
		else if( command.equals("find") ){
			String indxName = args[1];
			String indX = args[2];
			if( indX.length() != 15 ){	// Base step, not allow bad data
				System.out.println("Index length must be 15");
				return ;
			}
			bPlusTree.findOffset(indX, indxName);
		}
		/*
		 * list sequential records starting from the given index.
		 * If not available, start from the next index.
		 * */
		/*
		 * ARIJEET ROY*/
		else if( command.equals("list") ){
			String indxName = args[1];
			String indX = args[2];
			int numOfindx = Integer.valueOf(args[3]);
			if( indX.length() != 15 ){	// Base step, not allow bad data
				System.out.println("Index length must be 15");
				return ;
			}
			bPlusTree.findOffsetList(indX, numOfindx, indxName);
			
		}else{
			System.out.println("Wrong Command line, Please check parameter");
		}
		

	}
    
    /*
     * update the index file after inserting new record in the text file*/
    /*
	 * ARIJEET ROY*/
    @SuppressWarnings({ "resource", "unchecked" })
    private void updateIndexFile(String indexFileName, int indxLength, BPlusTree<String, String> bPlusTree) {
	// TODO Auto-generated method stub
    	int offset = 0;

		String tempBuffer = "";
		char[] buffer = null;
		boolean isBufferFull = false;
		String originName = "";
    	try {
    		int startptr = 0;
    		int wholeOff = 0;
    		int recptr = 0;
    		BufferedReader originTxt = new BufferedReader(new FileReader(indexFileName));
    		originName = originTxt.readLine();
			BufferedReader reader = new BufferedReader(new FileReader(originName));
			while( reader.ready() ){
				buffer = new char[1024];
				reader.read(buffer);
				String key = String.valueOf(buffer);
				while( recptr < 1024 && !isBufferFull){
					while( buffer[recptr] != '\n' ){
						recptr++;
						wholeOff++;
						if( recptr >= 1024 ){
							isBufferFull = true;
							tempBuffer = key.substring(startptr, recptr);
							break;
						}
					}
					if( !isBufferFull ){
						if( tempBuffer.equals("") ){
							bPlusTree.insert(key.substring(startptr,  startptr + indxLength), String.valueOf((offset)) , key.substring(startptr + indxLength+1, recptr));
							offset = wholeOff+1;
						}
						else{
							String boundaryKey = tempBuffer + key.substring(startptr,  recptr);
							bPlusTree.insert(boundaryKey.substring(0, indxLength), String.valueOf((offset)), boundaryKey.substring(indxLength + 1));
							tempBuffer = "";
							offset = wholeOff+1;
						}
						wholeOff++;
					}
					else{
						break;
					}
					recptr++;
					startptr = recptr;
				}
				isBufferFull = false;
				startptr = 0;
				recptr = 0;
			}
	    } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
				e.printStackTrace();
		}

    	//delete the index file and recreate
    	File deleteIdx = new File(indexFileName);
    	deleteIdx.delete();
    	
    	BufferedWriter writer = null;
    	
		try {
			writer = new BufferedWriter(new FileWriter(indexFileName, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			writer.append(originName + "\n");
			writer.append(offset + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	bPlusTree.dump(originName,String.valueOf(indxLength),indexFileName);
    	
	    }    
	
    /*
     * find the list of sequential records starting from the given key.*/
    /*
	 * ARIJEET ROY*/
	    private void findOffsetList(String key, int count, String indxName) {
    	List<Integer> offsetList = new ArrayList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader(indxName))) {

			String line;
			//ignore the 1st two lines reserved for metadata.
			br.readLine();
			br.readLine();
			while ((line = br.readLine()) != null && line.substring(0, 15).compareTo(key) < 0) {
			}
			
			do {
				offsetList.add(Integer.valueOf(line.substring(16)));
				count--;
			}
			while ((line = br.readLine()) != null && count > 0);
			

		} catch (IOException e) {
			e.printStackTrace();
		}
    	for(int i=0; i<offsetList.size(); i++)
    		retrieveRecords(offsetList.get(i), indxName);
	}
	
	    /*
	     * find the offset of the given record from the index file*/
	    /*
		 * KWANGHOON AN*/
	    private void findOffset(String key, String indxName) {
	    	
	    	try (BufferedReader br = new BufferedReader(new FileReader(indxName))) {

				String line;
				//ignore the 1st two lines reserved for metadata.
				br.readLine();
				br.readLine();
				while ((line = br.readLine()) != null && line.substring(0, 15).compareTo(key) < 0) {
				}
				if( key.equals(line.substring(0, 15)))
					retrieveRecords(Integer.valueOf(line.substring(16)), indxName);
				else
					System.out.println("There is no such a same index file");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    		
		}
    
	    /*
	     * write the new record into the text file at the end.*/
	    /*
		 * KWANGHOON AN*/
    public void writeTodisk(String indx, String record, String indxName){
    	
    	try {
    		@SuppressWarnings("resource")
			BufferedReader originTxt = new BufferedReader(new FileReader(indxName));
			BufferedWriter bufferWriter = new BufferedWriter( new FileWriter(originTxt.readLine(), true) );
			bufferWriter.append(indx + " " + record  +"\n");
			bufferWriter.flush();
			bufferWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
     * insert a new node to the b+ tree starting from the root.*/
    /*
	 * ARIJEET ROY*/
    public void insert(Key key, String offset, Value value) {
//	System.out.println("insert key=" + key);
	Split result = root.insert(key, offset,  value);
        if (result != null) {
	    // The old root was split into two parts.
	    // We have to create a new root pointing to them
            INode _root = new INode();
            _root.num = 1;
            _root.keys[0] = result.key;
            _root.children[0] = result.left;
            _root.children[1] = result.right;
            root = _root;
        }
    }
    
    /*
     * retrieve the given record from the text file and return with an offset of the data*/
    /*
	 * KWANGHOON AN*/
    public void retrieveRecords(int offSet, String indxName){
    	
    	try {
    		BufferedReader originTxt = new BufferedReader(new FileReader(indxName));
			RandomAccessFile raf = new RandomAccessFile(originTxt.readLine(), "r");
			raf.seek(offSet);
			System.out.println("At "+offSet + ", record:" +raf.readLine());
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
	         ex.printStackTrace();
	    }
    	
    }

    /** 
     * Looks for the given key. If it is not found, it returns null.
     * If it is found, it returns the associated value.
     */
    /*
	 * ARIJEET ROY*/
    public Value find(Key key) {
        Node node = root;
        while (node instanceof BPlusTree.INode) { // need to traverse down to the leaf
	    @SuppressWarnings("unchecked")
		INode inner = (INode) node;
            int idx = inner.getLoc(key);
            node = inner.children[idx];
        }

        //We are @ leaf after while loop
        @SuppressWarnings("unchecked")
		LNode leaf = (LNode) node;
        int idx = leaf.getLoc(key);
        if (idx < leaf.num && leaf.keys[idx].equals(key)) {
	    return leaf.values[idx];
        } else {
	    return null;
        }
    }
    
    /*
	 * ARIJEET ROY*/
    public void listSequentialRecords(Key key, int count) {
    	//find head of list of LNodes
    	//find the leftmost leaf node.
    	Node node = root;
    	while (node instanceof BPlusTree.INode) { // need to traverse down to the leaf
    	    @SuppressWarnings("unchecked")
			INode inner = (INode) node;
                int idx = inner.getLoc(key);
                node = inner.children[idx];
        }
    	
    	@SuppressWarnings("unchecked")
		LNode leaf = (LNode) node;
    	int idx = leaf.getLoc(key);
    	
    	//Printing the sequential records
    	System.out.println(idx +" "+ leaf.num);
        if (idx < leaf.num) {
//        	System.out.println("lNode h==0");
    	    for (int i = idx; i < leaf.num && count > 0; i++, count--){
    	    	System.out.println(leaf.keys[i] + " " + leaf.values[i]);
    	    }
        } else {
        	leaf = leaf.next;
        }
    	while(leaf != null && count > 0) {
//    		System.out.println("lNode h==0");
    	    for (int i = 0; i < leaf.num && count > 0; i++, count--){
    	    	System.out.println(leaf.keys[i] + " " + leaf.values[i]);
    	    }
    		leaf = leaf.next;
    	}
    }

    public void dump(String txtName, String indxLength, String indxName) {
	root.dump(txtName, indxLength, indxName);
    }

    abstract class Node {
	protected int num; //number of keys
	protected Key[] keys;

	abstract public int getLoc(Key key);
	// returns null if no split, otherwise returns split info
	abstract public Split insert(Key key, String offSet,Value value);
	abstract public void dump(String txtName, String indxLength, String indxName);
    }

    /**
     * Leaf node which contains the index as well as pointer to the record*/
    @SuppressWarnings("unchecked")
    /*
	 * KWANGHOON AN*/
	class LNode extends Node {
    	
    final String[] offset = new String[M];
	final Value[] values = (Value[]) new Object[M];
	{ keys = (Key[]) new Comparable[M]; }
	LNode next = null;
	
	/**
	 * Returns the position where 'key' should be inserted in a leaf node
	 * that has the given keys.
	 */
	public int getLoc(Key key) {
	    // Simple linear search for searching the value.
	    for (int i = 0; i < num; i++) {
		if (keys[i].toString().compareTo(key.toString()) >= 0) {
		    return i;
		}
	    }
	    return num;
	}

	/*
	 * insert record at the leaf node and split if necessary.*/
	public Split insert(Key key, String offSet, Value value) {
	    // Simple linear search
	    int i = getLoc(key);
	    if (this.num == M) { // The node was full. We must split it
		int mid = (M+1)/2;
		int sNum = this.num - mid;
		LNode sibling = new LNode();
		sibling.num = sNum;
		
		if(this.next == null) {
			sibling.next = null;
			
		} else {
			sibling.next = this.next;
		}
		this.next = sibling;
		
		System.arraycopy(this.keys, mid, sibling.keys, 0, sNum);
		System.arraycopy(this.offset, mid, sibling.offset, 0, sNum);
		System.arraycopy(this.values, mid, sibling.values, 0, sNum);
		this.num = mid;
		if (i < mid) {
		    // Inserted element goes to left sibling
		    this.insertNonfull(key, offSet,value, i);
		} else {
		    // Inserted element goes to right sibling
		    sibling.insertNonfull(key, offSet ,value, i-mid);
		}
		// Notify the parent about the split
		Split result = new Split(sibling.keys[0], //make the right's key >= result.key
					 this,
					 sibling);
		return result;
	    } else {
		// The node was not full
		this.insertNonfull(key, offSet,value, i);
		return null;
	    }
	}

	private void insertNonfull(Key key, String offSet,Value value, int idx) {
	    //if (idx < M && keys[idx].equals(key)) {
	    if (idx < num && keys[idx].equals(key)) {
		// We are inserting a duplicate value, simply overwrite the old one
	    	values[idx] = value;
	    } else {
		// The key we are inserting is unique
	    	System.arraycopy(keys, idx, keys, idx+1, num-idx);
	 		System.arraycopy(offset, idx, offset, idx+1, num-idx);
	 		System.arraycopy(values, idx, values, idx+1, num-idx);

	 		keys[idx] = key;
	 		offset[idx] = offSet;
	 		values[idx] = value;
	 		num++;
	    }
	}

	/*
	 * create a dump of the index file after creation of the b+ tree.
	 * Hence the records in the index file are always in a sorted order. along with the offset*/
	public void dump(String txtName, String indxLength, String indxName) {
//	    System.out.println("lNode h==0");
//		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(indxName, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	    for (int i = 0; i < num; i++){
		try {
//			System.out.println(keys[i] + " " + offset[i]);
			writer.append(keys[i] + " " + offset[i] + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	    }
	    try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    }

    /*
     * Inner node object which does not contain the actual data but instead contains pointer to the leaf node which
     * contains the actual data or pointer to the data*/
    /*
	 * ARIJEET ROY*/
    @SuppressWarnings("unchecked")
	class INode extends Node {
	final Node[] children = new BPlusTree.Node[N+1];
	{ keys = (Key[]) new Comparable[N]; }

	/**
	 * Returns the position where 'key' should be inserted in an inner node
	 * that has the given keys.
	 */
	public int getLoc(Key key) {
	    // Simple linear search. Faster for small values of N or M
	    for (int i = 0; i < num; i++) {
		if (keys[i].toString().compareTo(key.toString()) > 0) {
		    return i;
		}
	    }
	    return num;
	}

	public Split insert(Key key, String offSet,Value value) {
	    /* Early split if node is full.
	     */

	    if (this.num == N) { // Split
		int mid = (N+1)/2;
		int sNum = this.num - mid;
		INode sibling = new INode();
		sibling.num = sNum;
		System.arraycopy(this.keys, mid, sibling.keys, 0, sNum);
		System.arraycopy(this.children, mid, sibling.children, 0, sNum+1);

		this.num = mid-1;//this is important, so the middle one elevate to next depth(height), inner node's key don't repeat itself

		// Set up the return variable
		Split result = new Split(this.keys[mid-1],
					 this,
					 sibling);

		// Now insert in the appropriate sibling
		if (key.compareTo(result.key) < 0) {
		    this.insertNonfull(key, offSet, value);
		} else {
		    sibling.insertNonfull(key, offSet,value);
		}
		return result;

	    } else {// No split
		this.insertNonfull(key, offSet, value);
		return null;
	    }
	}

	private void insertNonfull(Key key, String offSet, Value value) {
	    // Simple linear search
	    int idx = getLoc(key);
	    Split result = children[idx].insert(key, offSet,value);

	    if (result != null) {
		if (idx == num) {
		    // Insertion at the rightmost key
		    keys[idx] = result.key;
		    children[idx] = result.left;
		    children[idx+1] = result.right;
		    num++;
		} else {
		    // Insertion not at the rightmost key
		    //shift i>idx to the right
		    System.arraycopy(keys, idx, keys, idx+1, num-idx);
		    System.arraycopy(children, idx, children, idx+1, num-idx+1);

		    children[idx] = result.left;
		    children[idx+1] = result.right;
		    keys[idx] = result.key;
		    num++;
		}
	    } // else the current node is not affected
	}

	/**
	 * This one only dump integer key
	 * This prints out the respective leaf nodes which contain the records or pointers to the records themselves.
	 */
	public void dump(String txtName, String indxLength, String indxName) {
	    for (int i = 0; i < num; i++){
		children[i].dump(txtName, indxLength, indxName);
	    }
	    children[num].dump(txtName, indxLength, indxName);
	}
    }

    class Split {
	public final Key key;
	public final Node left;
	public final Node right;

	public Split(Key k, Node l, Node r) {
	    key = k; left = l; right = r;
	}
    }
}