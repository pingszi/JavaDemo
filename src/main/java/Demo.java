import java.util.regex.Pattern;

public class Demo {
	public static void main(String[] args) {
		System.out.println(Pattern.compile("/druid/user/*").matcher("/druid/user/test.js").find());
	}
}
