package QGeneral;

public class PrintEnglishNumber {
	public static void main(String[] args) {
		int a = 901345762;
		System.out.println("num: " + a + "\n" + convertToEnglish(a));
		
		int b = 50000;
		System.out.println("num: " + b + "\n" + convertToEnglish(b));
	}
	
	public static final String[] Big = {"hundred", "thousand", "million"};
	public static final String[] TwentyToNinety = {"twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
	public static final String[] ElevenToNineteen = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	public static final String[] OneToTen = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
	
	public static String convertToEnglish(int num) {
		if(num == 0)
			return "zero";
		
		boolean isNeg = false;
		if(num < 0) {
			isNeg = true;
			num = -num;
		}
		
		//count the number of three digit sets from left to right
		int threeCnt = 0;
		String eng = "";
		
		while(num > 0) {
			//start using big endings when the three digit set count is > 0
			if(threeCnt > 0) {
				eng = convert3Digits(num % 1000) + " " + Big[threeCnt] + " " + eng;
			} else {
				//for num < 1000, no need for big ending 
				eng = convert3Digits(num % 1000);
			}
			
			num /= 1000;
			++threeCnt;
		}
		
		if(isNeg) 
			eng = "negative " + eng;
		
		return eng;
	}
	
	public static String convert3Digits(int num) {
		//in case num is just 3 zeros (000) just ignore it
		if(num == 0) {
			return "";
		}
		
		//convert 1 to 10
		if(num <= 10) {
			return OneToTen[num-1];
		} 
		
		//convert 11 to 19
		if(num < 20) {
			return ElevenToNineteen[(num%9)-1];
		}
		
		//convert 20 to 99 in tenth and oneth places
		String eng = "";
		
		//in case oneth place is 0 (ex. 900) just ignore it and remove it
		if(num%10 > 0) {
			eng += OneToTen[(num%10)-1];
		}
		num /= 10;	
		
		//in case tenth place is 0 (ex. 900) just ignore it and remove it
		if(num%10 > 0) {
			//check if oneth place was not 0 so its actually some english
			//otherwise don't add in the ending space
			if(eng.compareTo("") == 0) {
				eng = TwentyToNinety[((num%10)%8)-2];
			} else {
				eng = TwentyToNinety[((num%10)%8)-2] + " " + eng;
			}
		}
		num /= 10;
		
		//convert hundredth place if num > 99
		if(num > 0) {
			eng = OneToTen[(num%10)-1] + " " + Big[0] + " " + eng;
			num /= 10;
		}
		
		return eng;
	}
}
