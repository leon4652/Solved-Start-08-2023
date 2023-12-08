import java.util.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    public static void main(String[] args) throws Exception {
    	N = Integer.parseInt(br.readLine());
    	

    
    	//1. Comparator 사용
    	PriorityQueue<StudentNotComparable> pq = new PriorityQueue<>(
    			new Comparator<>() {
					@Override
					public int compare(StudentNotComparable o1, StudentNotComparable o2) {
						//내부 구문 생략 .. Student Class의 compareTo 참고할 것.
						return 0;
					}
    			}
    			);
    	
    	//2. Comparable 사용
    	Student arr[] = new Student[N];
    	
    	
    	for(int i = 0; i < N; i++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		String name = st.nextToken();
    		int kor = Integer.parseInt(st.nextToken());
    		int eng = Integer.parseInt(st.nextToken());
    		int math = Integer.parseInt(st.nextToken());
    		
    		//1. PQ용
    		pq.offer(new StudentNotComparable(name, kor, eng, math));
    		
    		//2. Comparable용
    		arr[i] = new Student(name, kor, eng, math); //이후 sort
    	}
    	Arrays.sort(arr);
    	
    	for(int i = 0; i < N; i++) {
    		System.out.println(arr[i].name);
    	}
    	
    } //EOM
} //EOC

class Student implements Comparable<Student> {
	String name;
	int kor, eng, math;
	
	@Override
	public int compareTo(Student s) {
		if(this.kor == s.kor) {
			if(this.eng == s.eng) {
				if(this.math == s.math) {
					return this.name.compareTo(s.name); //String의 경우 compareTo 사용
				}
				return s.math - this.math;
			}
			return this.eng - s.eng;
		}
		return s.kor - this.kor;
	}
	
	public Student(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
}

//Comparable 사용하지 않음
class StudentNotComparable {
	String name;
	int kor, eng, math;
	public StudentNotComparable(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
}
