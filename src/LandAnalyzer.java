import java.util.*;

public class LandAnalyzer {
	private static final int FIELD_WIDTH = 10;
	private static final int FIELD_LENGTH = 15;
	private static final LandNode[][] FIELD = new LandNode[FIELD_WIDTH][FIELD_LENGTH];
	private static int count;
	private static ArrayList<Integer> results = new ArrayList<Integer>(1);
	

	public static void main(String[] args) {
		String coordinates;
		
		//initialize array of land nodes
		for(int i=0; i < FIELD_WIDTH; i++)
			for(int j=0; j < FIELD_LENGTH; j++)
				FIELD[i][j]=new LandNode();
		
		showField();
		
		//input coordinates and plot barren land
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter coordinates of barren land(Enter to quit):");
			coordinates = scanner.nextLine();
			if(!(coordinates.isEmpty()))
				plotBarrenLand(coordinates);
		}
		while (!(coordinates.isEmpty()));
		
		showField();
		
		//Visit each node and attempt flood fill
		for(int i=0; i < FIELD_WIDTH; i++)
			for(int j=0; j < FIELD_LENGTH; j++) {
				if (!(FIELD[i][j].isBarren()) && !(FIELD[i][j].isVisited())) {
					count = 0;
					floodFill(i,j);
					results.add(count);
				}
			}
		
		Collections.sort(results);
		for (Integer element : results)
			System.out.print(element + " ");
		
	}
	
	public static boolean plotBarrenLand(String coordinates) {
		boolean result = false;
		
		try {
			Scanner scanner = new Scanner(coordinates);
			int x1=scanner.nextInt();
			int y1=scanner.nextInt();
			int x2=scanner.nextInt();
			int y2=scanner.nextInt();

			for(int i=x1; i<= x2; i++)
				for(int j=y1; j<=y2; j++)
					FIELD[i][j].setBarren();
			result = true;
		}
		catch(Exception e){
			result=false;
			System.out.println("Invalid entry, try again");
		}
		finally {
			return result;
		}
	}
	
	public static void showField() {
		System.out.println("Field:");
		for(int j=FIELD_LENGTH-1; j >= 0 ; j--) {
			for(int i=0; i < FIELD_WIDTH; i++) {
				if(FIELD[i][j].isBarren())
					System.out.print(1+" ");
				else
					System.out.print(0+" ");
			}
			System.out.println();
		}
	}
	
	public static void floodFill(int x, int y) {
		if(!isValidNode(x,y))
			return;
		else {
			FIELD[x][y].setVisited();
			count++;
			if(isValidNode(x+1,y))
				floodFill(x+1, y);
			if(isValidNode(x,y+1))
				floodFill(x, y+1);
			if(isValidNode(x-1,y))
				floodFill(x-1, y);
			if(isValidNode(x,y-1))
				floodFill(x, y-1);
		}
	}
	
	public static boolean isValidNode(int x, int y) {
		boolean result=true;
		if(x>FIELD_WIDTH-1 || y>FIELD_LENGTH-1)
			result = false;
		else if(x<0 || y<0)
			result = false;
		else if(FIELD[x][y].isBarren() || FIELD[x][y].isVisited())
			result = false;
		return result;
	}
	
}
