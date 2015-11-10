/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mazenbadr
 */
public class Dijkstra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hashtable<Integer, ArrayList<int[]>> graph = new Hashtable<>();
        InputStream stream = ClassLoader.getSystemResourceAsStream("dijkstraData.txt");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] vals = line.trim().split("\\s+");
                int key = -1;
                ArrayList<int[]> values = new ArrayList<int[]>();

                for (int i = 0; i < vals.length; i++) {
                    int[] value = new int[2];
                    if (i == 0) {
                        key = Integer.parseInt(vals[i]);
                    } else {
                        String[] temp = vals[i].split(",");
                        int j = 0;
                        for (String s : temp) {
                            value[j++] = Integer.parseInt(s);
                        }
                        values.add(value);
                    }

                }
                graph.put(key, values);
            }
        } catch (IOException ex) {
            Logger.getLogger(Dijkstra.class.getName()).log(Level.SEVERE, null, ex);
        }

        HashSet<Integer> explored = new HashSet<Integer>();
        int[] distances = new int[graph.size() + 1];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[1] = 0;
        while (explored.size() < graph.size()) {
            int node = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < distances.length; j++) {
                if (distances[j] < min && !explored.contains(j)) {
                    min = distances[j];
                    node = j;
                }
            }
            explored.add(node);
            int distance = 0;
            for (int[] child : graph.get(node)) {
                if (distances[node] + child[1] < distances[child[0]] && !explored.contains(child[0])) {
                    distance = distances[node] + child[1];
                    distances[child[0]] = distance;
                }
            }            
        }
        int[] tempArray = {7,37,59,82,99,115,133,165,188,197};
        for (int temp : tempArray) {
            System.out.print(distances[temp]);
            System.out.print(",");
        }
    }

}
