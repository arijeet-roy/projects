package com.assignments;
import java.util.Scanner;

/*
 * ARIJEET ROY
 * axr165030
 */

public class CustomHashTable {
	//set default starting table size to 11 which is the closest prime number to 10
      private final int DEFAULT_TABLE_SIZE = 11;
      //set default load factor to 0.5
      private float threshold = 0.5f;
      //max size of the table after which rehashing would occur.
      private int maxSize = (int) (threshold * DEFAULT_TABLE_SIZE);
      //count for maintaining the size of the hashtable.
      private int size = 0;
 
      HashEntry[] table;
 
      CustomHashTable() {
            table = new HashEntry[DEFAULT_TABLE_SIZE];
            for (int i = 0; i < DEFAULT_TABLE_SIZE; i++)
                  table[i] = null;
      }
 
      //set the user defined threshold for the hash table. In this case it is equal to 0.5
      void setThreshold(float threshold) {
            this.threshold = threshold;
            maxSize = (int) (table.length * threshold);
      }
 
      public String get(String key) {
    	  	int hashCode = key.hashCode();
            int hash = (hashCode % table.length);
            int initialHash = -1;
            while (hash != initialHash
                        && (table[hash] == DeletedEntry.getUniqueDeletedEntry()
                        || table[hash] != null
                        && table[hash].getKey() != key)) {
                  if (initialHash == -1)
                        initialHash = hash;
                  hash = (hash + 1) % table.length;
            }
            if (table[hash] == null || hash == initialHash)
                  return "-1";
            else
                  return table[hash].getValue();
      }
 
      public void put(String key, String value) {
    	  int hashCode = key.hashCode();
          int hash = (hashCode % table.length);
            int initialHash = -1;
            int indexOfDeletedEntry = -1;
            while (hash != initialHash
                        && (table[hash] == DeletedEntry.getUniqueDeletedEntry()
                        || table[hash] != null
                        && table[hash].getKey() != key)) {
                  if (initialHash == -1)
                        initialHash = hash;
                  if (table[hash] == DeletedEntry.getUniqueDeletedEntry())
                        indexOfDeletedEntry = hash;
                  hash = (hash + 1) % table.length;
            }
            if ((table[hash] == null || hash == initialHash)
                        && indexOfDeletedEntry != -1) {
                  table[indexOfDeletedEntry] = new HashEntry(key, value);
                  size++;
            } else if (initialHash != hash)
                  if (table[hash] != DeletedEntry.getUniqueDeletedEntry()
                             && table[hash] != null && table[hash].getKey() == key)
                        table[hash].setValue(value);
                  else {
                        table[hash] = new HashEntry(key, value);
                        size++;
                  }
            
            //resize in case the number of elements crosses the threshold
            if (size > maxSize) {
            	//display table before resizing
            	System.out.println("Table contents before rehash ---->");
            	displayTable();
            	resize();
            	System.out.println("Table contents after rehash ---->");
            	displayTable();
            }
      }
 
      private void displayTable() {
    	  System.out.println("Table size : "+table.length);
    	  System.out.println();
    	  for (int i = 0; i < table.length; i++) {
  			if(table[i] != null) {
  				System.out.println(table[i].getKey() + "   " + table[i].getValue());				
  			}
  			else {
  				System.out.println("NULL         NULL");
  			}
  		}
    	  System.out.println();
      }

      //for resizing, decide the next prime number greater than double the current size of the table.
	private int findNextPrime(int tableSize) {
    	  tableSize = 2 * tableSize;
    	  while(checkPrime(tableSize) == false) {
    		  tableSize++;
    	  }
    	  return tableSize;
	  }
      
      private boolean checkPrime(int tableSize) {
    	  if(tableSize == 1) return false;
    	  if(tableSize == 2) return true;
    	  if(tableSize % 2 == 0) return false;
    	  for(int i=3; (i*i) <= tableSize; i+=2) {
    		  if(tableSize % i == 0) return false;
    	  }
    	  return true;
      }
      
      //return the size of the hashtable.
      private int size() {
    	return size;  
      }
      
      private void resize() {
            int tableSize = findNextPrime(table.length);
            maxSize = (int) (tableSize * threshold);
            HashEntry[] oldTable = table;
            table = new HashEntry[tableSize];
            size = 0;
            for (int i = 0; i < oldTable.length; i++)
                  if (oldTable[i] != null
                             && oldTable[i] != DeletedEntry.getUniqueDeletedEntry())
                        put(oldTable[i].getKey(), oldTable[i].getValue());
      }
 
      public void remove(String key) {
    	  int hashCode = key.hashCode();
          int hash = (hashCode % table.length);
            int initialHash = -1;
            while (hash != initialHash
                        && (table[hash] == DeletedEntry.getUniqueDeletedEntry()
                        || table[hash] != null
                        && table[hash].getKey() != key)) {
                  if (initialHash == -1)
                        initialHash = hash;
                  hash = (hash + 1) % table.length;
            }
            if (hash != initialHash && table[hash] != null) {
                  table[hash] = DeletedEntry.getUniqueDeletedEntry();
                  size--;
            }
      }
      
      public static void main(String[] args) {
    	  CustomHashTable table = new CustomHashTable();
    	  table.setThreshold(0.5f);
    	  Scanner scanner = new Scanner(System.in);
    	  System.out.println("Enter the number of students to be entered into the record : ");
    	  int numOfStudents = scanner.nextInt();
    	  System.out.println("Enter the student id along with the name for the "+numOfStudents+" students");
    	  for (int i = 0; i < numOfStudents; i++) {
    		  String studentId = scanner.next();
    		  String studentName = scanner.next();
    		  table.put(studentId, studentName);
    	  }
    	  System.out.println("Final Hashtable after insertion of data for 20 students --->");
    	  table.displayTable();
    	  scanner.close();
		
	}
}

class HashEntry {
    private String key;
    private String value;

    HashEntry(String key, String value) {
          this.key = key;
          this.value = value;
    }

    public String getValue() {
          return value;
    }

    public void setValue(String value) {
          this.value = value;
    }

    public String getKey() {
          return key;
    }
}

//A deleted entry is marked in this case for lazy deletion.
class DeletedEntry extends HashEntry {
    private static DeletedEntry entry = null;

    private DeletedEntry() {
          super("-1", "Deleted Entry");
    }

    public static DeletedEntry getUniqueDeletedEntry() {
          if (entry == null)
                entry = new DeletedEntry();
          return entry;
    }
}
