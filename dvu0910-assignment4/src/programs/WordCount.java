/**
 * Assignment 4
 */

package programs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * method to help Wordcount class.
 * 
 * @author Duy Vu
 * @version Spring 2021
 *
 */

public final class WordCount {

    /**
     * run class.
     */
    private WordCount() {
        
    }
    
    /**
     * method to print get num and name of file.
     * @param theArgs the args
     */
    public static void main(final String[] theArgs) {
        System.out.println("Please enter the file name");
        final Scanner sc = new Scanner(System.in);       
        final String nameFile = sc.next();
        
        
        System.out.println("Enter number of word you want to know");
        final int n = sc.nextInt();
        
        topFrequency(n, nameFile);
        sc.close();

    }

    /**
     * method to sort word to array list.
     * 
     * @param theFileName file need to check
     * @return words after sort.
     */
    private static List<String> readWordFile(final File theFileName) {

        final List<String> sortWordFile = new ArrayList<>();
        final Scanner scannerFile;

        try {
            scannerFile = new Scanner(theFileName);
            while (scannerFile.hasNext()) {
                final String[] words = scannerFile.next().trim().split("[^a-zA-Z']+");
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() > 0  && !words[i].equals("'")) {
                       
                        sortWordFile.add(words[i].toLowerCase());
                    }
                }

            }
            scannerFile.close();
        } catch (final FileNotFoundException e) {
            System.out.print(" Can not file found");
            System.exit(0);
        }
        return sortWordFile;

    }

    /**
     * count frequency word in map.
     * 
     * @param theList a list
     * @param theMap  connect word with count
     * @return theMap
     */

    private static Map<String, Integer> countWordInMap(final List<String> theList,
                                                   final Map<String, Integer> theMap) {
        
        for (int i = 0; i < theList.size(); i++) {
            final String word = theList.get(i);
            if (theMap.containsKey(word)) {
                int countWord = theMap.get(word);
                countWord++;
                theMap.put(word, countWord);
            } else {
                theMap.put(word, 1);
            }
        }
        return theMap;

    }

    /**
     * support toFrequency compare words.
     * 
     * @param theMap 
     * @return listWord
     */
    
    private static ArrayList<Entry<String, Integer>> topFrequency(
                                                final Map<String, Integer> theMap) {
        final ArrayList<Entry<String, Integer>> listWord =
                new ArrayList<Entry<String, Integer>>(theMap.entrySet());
        Collections.sort(listWord, new Comparator<Entry<String, Integer>>() {

            @Override
            public int compare(final Entry<String, Integer> o1,
                    final Entry<String, Integer> o2) {
                // TODO Auto-generated method stub
                return o2.getValue().compareTo(o1.getValue());
            }

        });
        return listWord;

    }

    /**
     * method to run class.
     * 
     * @param theN number word to find requency.
     * @param theFileName the file need to run.
     */
    private static void topFrequency(final int theN, final String  theFileName) {
        final File file = new File(theFileName);
        final int numRun = 10;
        final List<String> listWord = readWordFile(file);
        
        long minTimeHash = Long.MAX_VALUE;
        for (int j = 0; j < numRun; j++) {
            System.out.println("Using HashMap");
            final long startTime = System.currentTimeMillis();
            final Map<String, Integer> hashMap = new HashMap<String, Integer>();
            countWordInMap(listWord, hashMap);
            final ArrayList<Entry<String, Integer>> entryList = topFrequency(hashMap);
            for (int i = 0; i < theN && i < entryList.size(); i++) {

                System.out.println(entryList.get(i));
            }
            System.out.println("----------------------------------");
            final long endTime = System.currentTimeMillis();
            final long currentTime = endTime - startTime;
            if (currentTime < minTimeHash) {
                minTimeHash = currentTime;
            }

            hashMap.clear();
            entryList.clear();
        }
        
        
        long minTimeTree = Long.MAX_VALUE;
        for (int j = 0; j < numRun; j++) {
            System.out.println("Using TreeMap");
            final long startTime = System.currentTimeMillis();
            final Map<String, Integer> treeMap = new TreeMap<String, Integer>();
            countWordInMap(listWord, treeMap);
            final ArrayList<Entry<String, Integer>> entryList = topFrequency(treeMap);
            for (int i = 0; i < theN && i < entryList.size(); i++) {
                System.out.println(entryList.get(i));
            }
            System.out.println("------------------------------");
            final long endTime = System.currentTimeMillis();
            final long currentTime = endTime - startTime;
            if (currentTime < minTimeTree) {
                minTimeTree = currentTime;
            }
            treeMap.clear();

            entryList.clear();
        }

        
        System.out.println("Using " + theFileName + " and using HashMap");
        System.out.println("Best time for 10 runs: " + minTimeHash + "ms");
        
        System.out.println("Using  " + theFileName + " and using TreeMap");
        System.out.println("Best time for 10 runs:  " + minTimeTree + "ms ");
        

    }  

}
