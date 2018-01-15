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
		// input.txt������ �о� ���� ���� ��ü ����
		BufferedReader br = new BufferedReader(new FileReader("10000.txt"));
		// 201502105_output.txt�� �̸����� ������ ���� ���� ��ü ����
		BufferedWriter out = new BufferedWriter(new FileWriter("201502105_quick.txt"));
		String s;
		int key = 0; // ������ ���� �����ϴ� ����
		int sort_count = 0; // ���ĵ� �迭�� �ε����� �����ϴ� ����
		int i = 0; // ���Ͽ��� �о�� ���ڵ��� �迭�� �ֱ� ���� ���Ǵ� index

		s = br.readLine(); // ������ �о�´�.

		// �о�� ���� s�� ������ �������� ��ū���� �ڸ���.
		StringTokenizer str = new StringTokenizer(s, " ");
		// �ڸ� ��ū�� ���� �ϱ� ���� �迭 ����
		int temp_array[] = new int[str.countTokens()];

		// ��ū�� ���� ������ �ݺ����� ������ ������ ������ temp_array�迭�� ��ū���� �ִ´�.
		while (str.hasMoreTokens()) {
			temp_array[i] = Integer.parseInt(str.nextToken());
			i++;
		}
		
		long startTime = System.currentTimeMillis();

		quicksort(temp_array,0,temp_array.length-1);
		
		// ���� �ð��� endTime�� ����
		long endTime = System.currentTimeMillis();

		// �迭�� ����� ���� ���Ϸ� �Ἥ ���
		for (int j = 0; j < temp_array.length; j++) {
			out.write(temp_array[j] + " ");

		}
		// elpasedTime�� sort�ϴµ� �ɸ� �ð��� �����
		long elapsedTime = (endTime - startTime);
		System.out.println("quick Sort= " + elapsedTime);

		// ���ϴݱ�
		out.close();
		
		
	}
		catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
