import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class FrequencyQueries {


	// Complete the freqQuery function below.
	static List<Integer> freqQuery(List<List<Integer>> queries) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, HashSet<Integer>> set = new HashMap<Integer, HashSet<Integer>>();

		List<Integer> result = new ArrayList<Integer>();
		for (List<Integer> query : queries) {
			int temp = procFreqQuery(query, map, set);
			if (temp >= 0) {
				result.add(temp);
			}
		}

		System.out.println(Arrays.toString(result.toArray()));
		return result;

	}

	private static int procFreqQuery(List<Integer> query, Map<Integer, Integer> map,
			Map<Integer, HashSet<Integer>> occurenceMap) {
		int operation = query.get(0);
		int number = query.get(1);

	
		switch (operation) {
		case 1:
			methodX(number, map, occurenceMap);
			break;
		case 2:
			methodY(number, map, occurenceMap);
			break;
		case 3:
			return methodZ(number, map, occurenceMap);
		}

		return -1;
	}

	static void methodX(int number, Map<Integer, Integer> map, Map<Integer, HashSet<Integer>> occurenceMap) {

		// Map contains the number, occurence.
		Integer occurence = map.get(number);

		int oldOccurence = 0;
		int newOccurence = 0;

		if (occurence == null) {
			newOccurence = 1;
		} else {
			oldOccurence = occurence.intValue();
			newOccurence = occurence.intValue() + 1;
		}
		map.put(number, newOccurence);
		
		udapteOccurenceMap(map,occurenceMap, number, oldOccurence, newOccurence);

	}

	private static void udapteOccurenceMap(Map<Integer, Integer> map, Map<Integer, HashSet<Integer>> occurenceMap, int number, int moveFromOccurence, int moveToOccurence) {
	
		HashSet<Integer> set1 = occurenceMap.get(moveFromOccurence);
		HashSet<Integer> set2 = occurenceMap.get(moveToOccurence);
		Integer occurence = map.get(number);
		int currentOccurence = occurence==null?0:occurence.intValue();
		
		if(set1==null) {
			set1 = new HashSet<Integer>();
		}
		
		if(set2==null) {
			set2 = new HashSet<Integer>();
		}
		
		
		
		if(currentOccurence==0) {
			set1.remove(number);
			set2.remove(number);

		}else {
			set2.add(number);
			set1.remove(number);
		}
		
				
			
		if(set2!=null)
			occurenceMap.put(moveToOccurence,set2);
		if(set1!=null)
			occurenceMap.put(moveFromOccurence,set1);
		

		
	}

	static void methodY(int number, Map<Integer, Integer> map, Map<Integer, HashSet<Integer>> occurenceMap) {
		Integer occurence = map.get(number);
		int oldOccurence = 0;

		int newOccrence = 0;
		if (occurence != null) {
			oldOccurence = occurence.intValue();
			newOccrence = occurence.intValue() - 1;

			if (newOccrence == 0) {
				map.remove(number);
			} else {
				map.put(number, newOccrence);
			}
			udapteOccurenceMap(map,occurenceMap, number, oldOccurence, newOccrence);
		}

	}

	static int methodZ(int number, Map<Integer, Integer> map, Map<Integer, HashSet<Integer>> occurenceMap) {

		if (occurenceMap.get(number) != null&&occurenceMap.get(number).size()>0) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		int q = Integer.parseInt(bufferedReader.readLine().trim());

		List<List<Integer>> queries = new ArrayList<>();

		for (int i = 0; i < q; i++) {
			String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

			List<Integer> queriesRowItems = new ArrayList<>();

			for (int j = 0; j < 2; j++) {
				int queriesItem = Integer.parseInt(queriesRowTempItems[j]);
				queriesRowItems.add(queriesItem);
			}

			queries.add(queriesRowItems);
		}

		List<Integer> ans = freqQuery(queries);

		for (int i = 0; i < ans.size(); i++) {
			bufferedWriter.write(String.valueOf(ans.get(i)));

			if (i != ans.size() - 1) {
				bufferedWriter.write("\n");
			}
		}

		bufferedWriter.newLine();

		bufferedReader.close();
		bufferedWriter.close();
	}
}
