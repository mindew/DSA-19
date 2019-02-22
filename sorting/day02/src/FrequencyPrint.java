import java.util.*;


public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        String result = "";
        String delims = "[ ]+";
        HashMap<String, Integer> dict = new HashMap();
        String[] tokens = s.split(delims);

        for (int x = 0; x < tokens.length; x++) {
            if (dict.containsKey(tokens[x])) {
                int currval = dict.get(tokens[x]);
                currval++;
                dict.put(tokens[x], currval);
            } else {
                dict.put(tokens[x], 1);
            }
        }


        for (Object objectName : dict.keySet()) {
            System.out.println(objectName);
            System.out.println(dict.get(objectName));
        }
        System.out.println("hashmapends");

        HashMap<Integer, String> reverseddict = new HashMap();
        Set<String> ksdict = dict.keySet();

        for (String kesss: ksdict) {
            int newKey = dict.get(kesss);
            System.out.println(newKey);
            if (reverseddict.containsKey(newKey)) {
                // if the value of dict's key exists in reverseddict
                // get the existing string of the key in reverseddict
                String word = reverseddict.get(newKey);
                // if newKey is greater than 1
                // in other words, this word happens more than once
                for (int i = 0; i < newKey; i++) {
                    word = word + " " + kesss;
                }

                reverseddict.put(newKey, word);

            } else {
                String word = "";
                for (int i = 0; i < newKey; i++) {
                    word = word + " " + kesss;
                }
                reverseddict.put(newKey, word);
            }
        }

        Set<Integer> ks = reverseddict.keySet();
        int maxelem = Collections.max(ks);
        for (int x = 1; x <= maxelem; x++) {
            if (reverseddict.containsKey(x)) {
                result = result + reverseddict.get(x);
            }
        }


        System.out.println(result);
        return result.strip();

    }

}
