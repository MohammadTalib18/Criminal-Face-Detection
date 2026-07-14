
package criminalfacedetection;

import org.opencv.core.*;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.util.ArrayList;

public class TrainModel {

    static {
        System.load("C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\opencv\\build\\java\\x64\\opencv_java470.dll");
    }

    public static void main(String[] args) {

        File folder = new File("dataset"); // your images folder

        File[] files = folder.listFiles();

        ArrayList<Mat> images = new ArrayList<>();
        ArrayList<Integer> labels = new ArrayList<>();

        int label = 0;

        for (File file : files) {
            Mat img = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);
            images.add(img);
            labels.add(label);
            label++;
        }

        MatOfInt labelsMat = new MatOfInt();
        labelsMat.fromList(labels);

        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.train(images, labelsMat);

        recognizer.save("trained_model.xml");
        recognizer.read(
    "C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\YourProject\\trained_model.xml");

        System.out.println("Model Trained Successfully!");
    }
}