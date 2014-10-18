package game.util;

public class Logger {

	public static void log(String message) {
		Throwable t = new Throwable();
		StackTraceElement directCaller = t.getStackTrace()[1];
		System.out.println(sun.reflect.Reflection.getCallerClass(2).getSimpleName() + "." + directCaller.getMethodName() + "()" + ": " + message);
	}

}
