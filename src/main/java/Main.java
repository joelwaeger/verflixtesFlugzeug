import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Main { 
	
	public static void main(String[] args) throws IOException {
		// Karten generieren lassen & auf File schreiben mit Reader / Writer klasse
		
		Random random = new Random();
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Files/flugzeug.txt")));
		ArrayList<Color> colors = new ArrayList<>();
		colors.add(Color.BLUE);
		colors.add(Color.RED);
		colors.add(Color.GREEN);
		
		for (int i = 0; i < 9; i++) {
			for (int a = 0; a < 4; a++) {

				Side side = new Side(colors.get(random.nextInt(2)), random.nextInt(1));
				writer.write(side.getColor() + "," + side.getPart() + "/");
			}

		};
		writer.close();
		
		// Karten von File lesen und als liste bekommen
		
		 List<Card> cards = new ArrayList<Card>();
		String LineReader;

		FileReader fr = new FileReader("Files/flugzeug.txt");
		BufferedReader br = new BufferedReader(fr);

		while ((LineReader = br.readLine()) != null) {
			System.out.println(LineReader);
			Side[] parts = LineReader.split("/");
			cards.add(new Card(parts[0], parts[1], parts[2], parts[3]));
		}
		// Solver aufrufen und Solutions in Liste speichern
		Solver solver = new Solver();	
		List<Card> solution = new ArrayList<Card>();
		
		List<Card> allSolutions = solver.getSolutions(partialBoard, remainingCards);
		
		// Etwas mit Liste machen (ausgabe auf Konsole, File..)
		
		FileOutputStream solutionfile = new FileOutputStream("Files/Solutionsflugzeug.txt");
		ObjectOutputStream oos = new ObjectOutputStream(solutionfile);
		oos.writeObject(allSolutions);
		oos.close();
		
		System.out.println(allSolutions);
	}


}
