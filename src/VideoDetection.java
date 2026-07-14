
package criminalfacedetection;

import org.opencv.core.*;
import org.opencv.face.LBPHFaceRecognizer;
import org.opencv.videoio.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;
import org.opencv.highgui.HighGui;
import javax.swing.*;

public class VideoDetection {

    static {
        System.load("C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\opencv\\build\\java\\x64\\opencv_java470.dll");
    }

    public void startCamera() {

        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(null, "Camera not found!");
            return;
        }

        CascadeClassifier faceDetector = new CascadeClassifier(
            "C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\CriminalFaceDetection\\haarcascade_frontalface_alt.xml"
        );

        LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
        recognizer.read(
            "C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\CriminalFaceDetection\\trained_model.xml"
        );

        Mat frame = new Mat();

        JOptionPane.showMessageDialog(null, "Press OK to start camera...");

        while (true) {

            camera.read(frame);

            if (frame.empty()) break;

            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);

            MatOfRect faces = new MatOfRect();
            faceDetector.detectMultiScale(gray, faces);

            for (Rect rect : faces.toArray()) {

                Mat face = new Mat(gray, rect);

                int[] label = new int[1];
                double[] confidence = new double[1];

                recognizer.predict(face, label, confidence);

                String result;

                if (confidence[0] < 80) {
                    result = "CRIMINAL";
                } else {
                    result = "NOT CRIMINAL";
                }

                // Draw rectangle
                Imgproc.rectangle(frame, rect, new Scalar(0, 255, 0), 2);

                // Put text
                Imgproc.putText(frame, result,
                        new Point(rect.x, rect.y - 10),
                        Imgproc.FONT_HERSHEY_SIMPLEX,
                        0.8, new Scalar(0, 0, 255), 2);
            }

            // Show frame in window
            HighGui.imshow("Live Detection", frame);

            // Exit on ESC key
            if (HighGui.waitKey(30) == 27) {
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}