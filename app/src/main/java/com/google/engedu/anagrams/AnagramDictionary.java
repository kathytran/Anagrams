/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import android.util.Log;
import java.util.HashSet;
import java.util.HashMap;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList;
    private HashSet<String> wordSet;
    private HashMap<String, ArrayList<String>> lettersToWord;  // groups anagrams together


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        wordList = new ArrayList<>();
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            String sortedWord = sortLetters(word);
            if (lettersToWord.containsKey(sortedWord)) {          // if the sorted word has a corresponding key,
                ArrayList<String> listWords = lettersToWord.get(sortedWord);  // add current word to ArrayList at that key
                listWords.add(word);
                lettersToWord.put(sortedWord, listWords);
            }
            else {                                         // otherwise add current word to new ArrayList
                ArrayList<String> listWords = new ArrayList<>();                                                 // and store in the HashMap with the corresponding key
                listWords.add(word);
                lettersToWord.put(sortedWord, listWords);
            }
        }

    }

    private String sortLetters(String word) {   // sorts the letters of the word alphabetically
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        String sortedLetters = new String(letters);
        return sortedLetters;
        }


    public boolean isGoodWord(String word, String base) {           // checks if word is valid in wordSet and doesn't contain
        return wordSet.contains(word) && !word.contains(base);      // the base word as a substring
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        String sortedTargetWord = sortLetters(targetWord);
        ArrayList<String> result = new ArrayList<String>();
        for (String word : wordList) {
            if (sortLetters(word).equals(sortedTargetWord)) {
                Log.v("Game", word);
                result.add(word);
            }
        }
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {  // takes a string and finds all anagrams that
        ArrayList<String> result = new ArrayList<String>();           // can be formed by adding one letter to that word
        char[] alphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();



        return result;
    }

    public String pickGoodStarterWord() {
        return "stop";
    }
}
