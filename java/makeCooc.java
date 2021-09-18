import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class makeCooc {
    
    public static void main(String[] args) throws Exception {
               
        List<String> items = new ArrayList<String>();
       
        File file = new File("shopItems.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String item;
        while ((item = br.readLine()) != null) {
            items.add(item);
        }

        br.close();

        FileWriter myWriter = new FileWriter("itemCooc.txt");
                
        int i;
        i=0;
        
        while (i < items.size()) {
            String word1;
            word1 = items.get(i);
            int j;
            j=0;
            while (j < items.size()) {
                String word2;
                word2 = items.get(j);
                if (!word2.equals(word1)) {
                    int u, v, x, y;
                    u=0; v=0; x=0; y=0;
                                        
                    File file_ = new File("sessionBaskets.txt");
                    BufferedReader br_ = new BufferedReader(new FileReader(file_));
                    String line;

                    while ((line = br_.readLine()) != null) {
                        int[] k = new int[2];
                        k[0] = countPair(word1, word2, line)[0];
                        k[1] = countPair(word1, word2, line)[1];
                        if ((k[0]==1) & (k[1]==1)) u++;
                        if ((k[0]==1) & (k[1]==0)) v++;
                        if ((k[0]==0) & (k[1]==1)) x++;
                        if ((k[0]==0) & (k[1]==0)) y++;
                    }
                    br_.close();
                    
                    myWriter.write(word1 + "\t" + word2 + "\t" + u + "\t" + v + "\t" + x + "\t" + y + "\n");
                }
                j++;
            }
            i++;
        }
        myWriter.close();
    }
    public static int[] countPair(String word1, String word2, String line) throws Exception {

        int[] k = new int[2];
        k[0]=0;
        k[1]=0;

        String[] words = line.split("\t");
        for(String s : words) {
            if(s.equals(word1)) k[0]++;
            if(s.equals(word2)) k[1]++;
        }
        
        return k;
    }
     
}