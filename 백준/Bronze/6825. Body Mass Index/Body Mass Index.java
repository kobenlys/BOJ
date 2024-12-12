import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double weight = Double.parseDouble(br.readLine());
		double height = Double.parseDouble(br.readLine());
		                
		double BMI = weight / (height * height);
		
		if(BMI < 18.5) {
			System.out.println("Underweight");
		} else if(BMI <= 25 && BMI >= 18.5) {
			System.out.println("Normal weight");
		} else if(BMI > 25){
			System.out.println("Overweight");
		}
	}
}