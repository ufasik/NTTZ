import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CirclePointPosition {
    public static void main(String[] args) {
        try {

            File centerFile = new File("test1.txt");
            Scanner centerScanner = new Scanner(centerFile);

            float centerX = centerScanner.nextFloat();
            float centerY = centerScanner.nextFloat();
            float radius = centerScanner.nextFloat();

            centerScanner.close();


            File pointFile = new File("test2.txt");
            Scanner pointScanner = new Scanner(pointFile);

            StringBuilder result = new StringBuilder();


            while (pointScanner.hasNextFloat()) {
                float pointX = pointScanner.nextFloat();
                float pointY = pointScanner.nextFloat();


                float distance = (float) Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));


                if (distance < radius) {
                    result.append("1 ");
                } else if (distance == radius) {
                    result.append("0 ");
                } else {
                    result.append("2 ");
                }
            }

            pointScanner.close();

            System.out.println(result.toString().trim());

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }
}