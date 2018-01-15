package AG02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Priority_Queue {
	static Node[] heap = new Node[1000];
	static int index_num=1;

	static class Node {
		int priority;
		String value;

		public Node() {
			priority = 0;
			value = null;
		}

		public Node(int priority, String value) {
			this.priority = priority;
			this.value = value;
		}

		public Node(int priority) {
			this.priority = priority;
			this.value = null;
		}

		public Node(String value) {
			this.priority = -1;
			this.value = value;
		}

		public int getpriority() {
			return this.priority;
		}

		public String getvalue() {
			return this.value;
		}

		public void setpriority(int priority) {
			this.priority = priority;
		}

		public void setvalue(String value) {
			this.value = value;
		}

	}

	public static int parent(int i) {
		int parent=i/2;
		return parent;
	}

	public static int left_child(int i) {
		return 2 * i;
	}

	public static int right_child(int i) {
		return 2 * i + 1;
	}

	public static void max_heapify(Node[] heap, int i) {
		int L = left_child(i);
		int R = right_child(i);
		int largest = 0;
		
		if (L <index_num && (heap[L].getpriority() > heap[i].getpriority())) {
			largest = L;
		} else {
			largest = i;
		}
		if (R <index_num && (heap[R].getpriority() > heap[largest].getpriority())) {
			largest = R;
		}
		if (largest != i) {
			Node temp;
			temp = heap[i];
			heap[i] = heap[largest];
			heap[largest]=temp;
			
			max_heapify(heap, largest);
		}
	}

	public static void build_max_heap(Node[] heap) {
		
		for (int i = index_num / 2; i > 0; i--) {
			max_heapify(heap, i);
		}
	}

	public static void insert(Node[] heap, int priority, String value) {
		int temp_sort =index_num;
		heap[index_num] = new Node(priority,value);
		
		while(temp_sort!=1&&(heap[temp_sort].getpriority()>=heap[parent(temp_sort)].getpriority())) {
			
			Node temp;
			temp=heap[temp_sort];
			heap[temp_sort]=heap[parent(temp_sort)];
			heap[parent(temp_sort)]=temp;
			
			temp_sort=parent(temp_sort);
		}
		index_num++;

	}

	public static void increase_key(Node[] heap, int i, int key) {
		if (key < heap[i].getpriority()) {
			System.out.println("원래 우선순위보다 더 커야합니다.");
		}

		heap[i].setpriority(key);

		build_max_heap(heap);
	}

	public static Node max(Node[] heap) {
		return heap[1];
	}

	public static void delete(Node[] heap, int index) {
		if(index+1==index_num) {
		heap[index] = heap[index_num];
		index_num--;
		}
		else {
			heap[index] = heap[index_num-1];
			 heap[index_num-1]=null;
			index_num--;
			max_heapify(heap, index);
		}
		
	}

	public static Node extract_max(Node[] heap) {
		Node max=heap[1];
		delete(heap,1);
		return max;
	}

	public static void main(String[] arg) {
		
		try {
			// input.txt파일을 읽어 오기 위해 객체 생성
			BufferedReader br = new BufferedReader(new FileReader("data_heap.txt"));
			
			String s;
			
			while((s = br.readLine()) != null){
				StringTokenizer str = new StringTokenizer(s, ",");			
				heap[index_num]= new Node(Integer.parseInt(str.nextToken()),str.nextToken());
				index_num++;
			}
			build_max_heap(heap);

			
			
			while (true) {
				System.out.println("*** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다 ****\n");

				for (int i = 1; i <index_num; i++) {
					System.out.println(heap[i].getpriority() + ", " + heap[i].getvalue());

				}


				System.out.println("\n-----------------------------------------------------");
				System.out.println("1. 작업 추가	2.최대값 	3.최대 우선순위 작업 처리");
				System.out.println("4. 원소 키값 증가	5. 작업 제거	6.종료");
				System.out.println("-----------------------------------------------------");
				Scanner scan = new Scanner(System.in);
				int number = scan.nextInt();

				switch (number) {
				case 1:
					System.out.print("신규 작업명 (20Bytes 이내): ");
					String value = scan.next();
					System.out.print("우선 순위(0~999) : ");
					int priority = scan.nextInt();
					insert(heap,priority, value);
					
					System.out.println("");
					break;
				case 2:
					Node max = max(heap);
					System.out.println("**** MAX  : " + max.getpriority() + ", " + max.getvalue()+" ****");
					break;
				case 3:
					max = extract_max(heap);
					System.out.println(max.getpriority() + "," + max.getvalue() + " 처리");
					break;
				case 4:
					System.out.print("수정할 노드  x : ");
					int index = scan.nextInt();
					System.out.print("수정할 key : ");
					int key = scan.nextInt();
					increase_key(heap,index, key);
					System.out.println("");
					break;
				case 5:
					System.out.print("삭제할 노드 x입력: ");
					index = scan.nextInt();
					delete(heap,index);
					break;
				case 6:
					System.exit(0);
					break;

				default:
					System.out.println("****잘못된 입력입니다. ****\n");
				}

			}


		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

}
