package ie.atu.sw;

public class PrintLoading {
	
	public void print() throws InterruptedException {
		System.out.print(ConsoleColour.GREEN);
		int size = 100;
		for (int i = 0; i < size; i++) {
			printProgress(i + 1, size);	
			Thread.sleep(10);
		}
		System.out.print(ConsoleColour.WHITE);
	}
	public static void printProgress(int index, int total) {
		if (index > total) return;

		int size = 50;
		char done = '█';
		char todo = '░';

		int complete = (100 * index) / total;
		int completeLen = size * complete / 100;

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append((i < completeLen) ? done : todo);
		}

		System.out.print("\r" + sb + "] " + complete + "%");

		if (index == total) System.out.println("\n");
	}
}
