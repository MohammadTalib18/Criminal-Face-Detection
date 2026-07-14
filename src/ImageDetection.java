/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criminalfacedetection;

import org.opencv.core.*;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;
import javax.swing.*;
import java.io.File;
import org.opencv.face.LBPHFaceRecognizer; 

public class ImageDetection { 

    static {
        // ✅ CHANGE PATH BASED ON YOUR SYSTEM
        System.load("C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\opencv\\build\\java\\x64\\opencv_java470.dll");
    }

    public void detectFromImage() {

        // Step 1: Open File Chooser
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fileChooser.getSelectedFile();

        // Step 2: Load Image
        Mat image = Imgcodecs.imread(file.getAbsolutePath());

        if (image.empty()) {
            JOptionPane.showMessageDialog(null, "Invalid Image!");
            return;
        }

        // Step 3: Convert to Gray
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // Step 4: Load Haarcascade
        CascadeClassifier faceDetector = new CascadeClassifier(
            "C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\CriminalFaceDetection\\haarcascade_frontalface_alt.xml"
        );

        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(gray, faces);

        Rect[] facesArray = faces.toArray();

        if (facesArray.length == 0) {
            JOptionPane.showMessageDialog(null, "No Face Detected!");
            return;
        }

        // Step 5: Load trained model
        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.read(
            "C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\CriminalFaceDetection\\trained_model.xml"
        );

        boolean isCriminal = false;

        for (Rect face : facesArray) {
            Mat faceROI = new Mat(gray, face);

            int[] label = new int[1];
            double[] confidence = new double[1];

            recognizer.predict(faceROI, label, confidence);

            System.out.println("Confidence: " + confidence[0]);

            if (confidence[0] < 80) {
                isCriminal = true;
                break;
            }
        }

        // Step 6: Result
        if (isCriminal) {
            JOptionPane.showMessageDialog(null, "⚠ Criminal Detected!");
        } else {
            JOptionPane.showMessageDialog(null, "✅ Not a Criminal");
        }
    }
}