package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Test1 {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[a-z]");
		Matcher matcher = pattern.matcher("hello");
		if(matcher.matches()){
			System.out.println("yes");
		}else{
			System.out.println("no");
		}
		
	}
	
}
