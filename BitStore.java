import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

public class BitStore{

	public static void post(Integer n){
		ArrayList<String> list = get();
		//list.add(n.toString());
		try{
			FileWriter fw = new FileWriter(new File("Story.txt"));
			fw.write(new Integer(list.size() + 1).toString() + '\n');//liczba ile na nowej liscie
			//System.out.println(list);//!!
			fw.write(dzisiaj() + n.toString() + '\n');
			for (String i : list){
				fw.write(i + '\n');
			}
			fw.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	private static ArrayList<String> get(){
		Scanner sc;
		int size = 0;
		ArrayList<String> list = null;
		try{
			String tmp = "brak";
			sc = new Scanner(new File("Story.txt"));
			size = sc.nextInt();//liczba linii
			list = new ArrayList<String>();
			sc.nextLine();
			for (int i=0; i<size; i++){
				tmp = sc.nextLine();
				//BitCoin.log(tmp);
				list.add(tmp);
			}
			sc.close();
		}catch(Exception e){
			//BitCoin.log(e);
			System.out.println(e);
		}
		return list;
	}

	public static String dzisiaj(){
		Date date = new Date(); //Date.getHours() <= 15 ? getYesterdayDate() : new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy :: HH:mm:ss");
		String format = formatter.format(date);
		//System.out.println(format);
		return format + ": ";
	}

	public static Integer getMax(){
		int result = -1;
		int tmp = 0; 
		try{
			Scanner sc = new Scanner(new File("Story.txt"));
			int size = sc.nextInt();
			sc.nextLine();
			for (int i=0; i<size; i++){
				sc.next();
				sc.next();
				sc.next();
				tmp = sc.nextInt();
				sc.nextLine();
				if (tmp > result)
					result = tmp;
			}
			sc.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return result;
	}

	public static Integer getMin(){
		double result = Double.POSITIVE_INFINITY;
		int tmp = 0;
		String tmp1 = "";
		String tmp2 = "";
		String tmp3 = "";
		String tmpData = "";
		try{
			Scanner sc = new Scanner(new File("Story.txt"));
			int size = sc.nextInt();
			sc.nextLine();
			for (int i=0; i<size; i++){
				tmp1 = sc.next();
				tmp2 = sc.next();
				tmp3 = sc.next();
				tmp = sc.nextInt();
				sc.nextLine();
				if (tmp < result){
					tmpData = tmp1 + " " + tmp2 + " " + tmp3;//jakos to przekazac na zwnatrz i dopisac w getMax()
					result = tmp;
				}
			}
			sc.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return (int) result;///tu się nie kompiluje
	}

	public static void main(String[] args) {
		//BitCoin.log(get());
		//System.out.println(get());
		//post(5);
		System.out.println(getMax());
		System.out.println(getMin());
	}
}
