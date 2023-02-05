import java.util.ArrayList;
import java.util.Scanner;

public class RushHour extends Game{
    private static ArrayList<Car> cars = new ArrayList<>();
    private static int size;
    private static Car redCar;
	
	public static boolean isSolved() {
		
        for (int i = 0; i < size; i++) {
            if (cars.get(i) instanceof Car) {
                Car car = (Car) cars.get(i);
                if (car.getLength() == 2 && car.isHorizontal() && car.getCol() + car.getLength() - 1 == size - 1) {
                    return true;
                }
            }
        }
        return false;
    }
	
	public static showBoard(){
		for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					boolean isOccupied = false;
					for (Car car : cars) {
						if (car.isHorizontal() && car.getRow() == i && j >= car.getCol() && j < car.getCol() + car.getLength()) {
							System.out.print(cars.indexOf(car) + 1 + " ");
							isOccupied = true;
							break;
						} else if (!car.isHorizontal() && car.getCol() == j && i >= car.getRow() && i < car.getRow() + car.getLength()) {
							System.out.print(cars.indexOf(car) + 1 + " ");
							isOccupied = true;
							break;
						}
					}
					if (!isOccupied) {
						System.out.print("- ");
					}
				}
				System.out.println();
			}
	}

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter board size: ");
        size = sc.nextInt();

        System.out.print("Enter number of cars: ");
        int numOfCars = sc.nextInt();

        for (int i = 0; i < numOfCars; i++) {
            System.out.print("Enter car " + (i + 1) + " row, column, length and orientation (h/v): ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            int length = sc.nextInt();
            String orientation = sc.next();
            boolean isHorizontal = orientation.equals("h");
            boolean isRed = false;

            if (i == 0) {
                System.out.print("Is this the red car (y/n)? ");
                isRed = sc.next().equals("y");
                if (isRed) {
                    redCar = new Car(row, col, length, isHorizontal, isRed);
                }
            }
            if (row < 0 || row >= size || col < 0 || col >= size || row + (isHorizontal ? 0 : length - 1) >= size || col + (isHorizontal ? length - 1 : 0) >= size) {
                System.out.println("Invalid car placement. Car is outside the board.");
                i--;
                continue;
            }
            for (Car car : cars) {
                int endRow = car.isHorizontal() ? car.getRow() : car.getRow() + car.getLength() - 1;
                int endCol = car.isHorizontal() ? car.getCol() + car.getLength() - 1 : car.getCol();

                if (row <= endRow && row + (isHorizontal ? 0 : length - 1) >= car.getRow() && col <= endCol && col + (isHorizontal? length - 1 : 0) >= car.getCol()) {
                    System.out.println("Invalid car placement. Another car is already in this position.");
                    i--;
                    continue;
                }
            }
            cars.add(new Car(row, col, length, isHorizontal, isRed));
        }

        while (isSolved()){
			System.out.println("Enter move (f/b) and car number: ");
			String move = sc.next();
			int carNum = sc.nextInt();
			Car selectedCar = cars.get(carNum - 1);

			if (move.equals("f")) {
				if (selectedCar.isHorizontal()) {
					if (selectedCar.getCol() + selectedCar.getLength() >= size) {
						System.out.println("Invalid move. Car is at the end of the board.");
					} else {
						boolean isValid = true;
						for (Car car : cars) {
							if (selectedCar.getRow() == car.getRow() && selectedCar.getCol() + selectedCar.getLength() == car.getCol()) {
								System.out.println("Invalid move. Another car is blocking the way.");
								isValid = false;
								break;
							}
						}
						if (isValid) {
							selectedCar.setCol(selectedCar.getCol() + 1);
						}
					}
				} else {
					if (selectedCar.getRow() + selectedCar.getLength() >= size) {
						System.out.println("Invalid move. Car is at the end of the board.");
					} else {
						boolean isValid = true;
						for (Car car : cars) {
							if (selectedCar.getCol() == car.getCol() && selectedCar.getRow() + selectedCar.getLength() == car.getRow()) {
								System.out.println("Invalid move. Another car is blocking the way.");
								isValid = false;
								break;
							}
						}
						if (isValid) {
							selectedCar.setRow(selectedCar.getRow() + 1);
						}
					}
				}
			} else {
				if (selectedCar.isHorizontal()) {
					if (selectedCar.getCol() <= 0) {
						System.out.println("Invalid move. Car is at the beginning of the board.");
					} else {
						boolean isValid = true;
						for (Car car : cars) {
							if (selectedCar.getRow() == car.getRow() && selectedCar.getCol() - 1 == car.getCol() + car.getLength() - 1) {
								System.out.println("Invalid move. Another car is blocking the way.");
								isValid = false;
								break;
							}
						}
						if (isValid) {
							selectedCar.setCol(selectedCar.getCol() - 1);
						}
					}
				} else {
					if (selectedCar.getRow() <= 0) {
						System.out.println("Invalid move. Car is at the beginning of the board.");
					} else {
						boolean isValid = true;
						for (Car car : cars) {
							if (selectedCar.getCol() == car.getCol() && selectedCar.getRow() - 1 == car.getRow() + car.getLength() - 1) {
								System.out.println("Invalid move. Another car is blocking the way.");
								isValid = false;
								break;
							}
						}
						if (isValid) {
							selectedCar.setRow(selectedCar.getRow() - 1);
						}
					}
				}
			}

			System.out.println("Current Board: ");
			showBoard();
			
		}
		
		System.out.println("Solved!");
    }
}
