package AG02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Quick_Sort {
	
	public static int partition(int[] array,int start, int end) {
		int x = array[end];
		int i = start - 1;
		for(int j = start; j < end; j++) {
			if(array[j] <= x) {
				i+=1;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}		
		i+=1;
		int temp = array[i];
		array[i] = array[end];
		array[end] = temp;
		
		return i;
	}
	public static void quicksort(int[] array, int p, int r) {
		int q;
		if (p<r) {
			q=partition(array, p,r);
			quicksort(array,p,q-1);
			quicksort(array,q+1,r);
		}
		
	}
	
	
	public static void main(String[] arg) {
	try {
		// input.txt파일을 읽어 오기 위해 객체 생성
		BufferedReader br = new BufferedReader(new FileReader("10000.txt"));
		// 201502105_output.txt의 이름으로 파일을 쓰기 위해 객체 생성
		BufferedWriter out = new BufferedWriter(new FileWriter("201502105_quick.txt"));
		String s;
		int key = 0; // 정렬할 값을 저장하는 변수
		int sort_count = 0; // 정렬된 배열의 인덱스를 저장하는 변수
		int i = 0; // 파일에서 읽어온 숫자들을 배열에 넣기 위해 사용되는 index

		s = br.readLine(); // 한줄을 읽어온다.

		// 읽어온 한줄 s를 공백을 기준으로 토큰으로 자른다.
		StringTokenizer str = new StringTokenizer(s, " ");
		// 자른 토큰을 저장 하기 위한 배열 생성
		int temp_array[] = new int[str.countTokens()];

		// 토큰이 없을 때까지 반복문을 돌려서 위에서 생성한 temp_array배열에 토큰들을 넣는다.
		while (str.hasMoreTokens()) {
			temp_array[i] = Integer.parseInt(str.nextToken());
			i++;
		}
		
		long startTime = System.currentTimeMillis();

		quicksort(temp_array,0,temp_array.length-1);
		
		// 현재 시간을 endTime에 저장
		long endTime = System.currentTimeMillis();

		// 배열에 저장된 값을 파일로 써서 출력
		for (int j = 0; j < temp_array.length; j++) {
			out.write(temp_array[j] + " ");

		}
		// elpasedTime에 sort하는데 걸린 시간이 저장됨
		long elapsedTime = (endTime - startTime);
		System.out.println("quick Sort= " + elapsedTime);

		// 파일닫기
		out.close();
		
		
	}
		catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
