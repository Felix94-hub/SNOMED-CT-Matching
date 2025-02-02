package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.CTController;
import modelCTSNOMED.CTModel.CTDescription;

public class CTView {
		
	public CTView(CTController controller) throws IOException {
		
		controller.setView(this);

		printHelp();
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		String file ="File.txt";
		System.out.print("\nEnter command: ");
		String input = scanner.nextLine();
		FileWriter writer = new FileWriter(file);
		
		
		while (!"exit".equals(input)) {
			switch (input) {
		        case "help":
		        	printHelp();
		            break;
		        case "add":
		            System.out.print("Add term to map: ");
		            String term = scanner.nextLine();
		            List<String> mappedTerms = new ArrayList<String>();
		
		            System.out.print("Add mapped term: ");
		            String mappedTermInput = scanner.nextLine();
		            while (!"q".equals(mappedTermInput)) {
		                mappedTerms.add(mappedTermInput);
		                System.out.print("Add another mapped term: ");
		                mappedTermInput = scanner.nextLine();
		            }
		            for(String str: mappedTerms) {
		            	writer.write(str + System.lineSeparator());
		            }
		            break;
		        case "show":
		            System.out.print("Enter term to show: ");
		            term = scanner.nextLine();
		            System.out.print("Enter semantic tag: ");
		            String semanticTag = scanner.nextLine();
		            System.out.print("Enter limit: ");
		            int limit = scanner.nextInt();
		            controller.showTerm(term, semanticTag, limit);
		            break;
		        case "tags":
		        	controller.showSemanticTags();
		        case "save":
		            controller.saveToFile();
		            break;
	        }
			System.out.print("Enter command: ");
			input = scanner.nextLine();
		}
		System.out.println("Exit");
		
	}
	
	private void printHelp() {
        System.out.println("help - shows this information");
        System.out.println("show - shows the concepts of a term (example: age, stomach, heart...)");
        System.out.println("tags - shows semantic tags (finding)");
        System.out.println("save - save terms to file");
        System.out.println("exit - exits the application");
        //...
	}

	public void printDescriptions(List<CTDescription> descriptions) {
		for (int i = 0; i < descriptions.size(); i++) {
			CTDescription description = descriptions.get(i);
			System.out.println(i + 1 + ". " + "\tTerm: " + description.getTerm() + "\n\tConcept ID: " + description.getConceptId() + "\n ");
		}
	}

	public void printSemanticTags(List<String> semanticTags) {
		for (int i = 0; i < semanticTags.size(); i++) {
			System.out.println(i + 1 + ". " + "\tSemanticTag: " + semanticTags.get(i) + "\n ");
		}
	}
}