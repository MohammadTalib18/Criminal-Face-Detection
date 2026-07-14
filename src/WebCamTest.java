/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package criminalfacedetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class WebCamTest {
   static {
    System.load("C:\\Users\\mohdt\\OneDrive\\Documents\\NetBeansProjects\\opencv\\build\\java\\x64\\opencv_java470.dll");
}

    public static void main(String[] args) {
        VideoCapture camera = new VideoCapture(0); // 0 = default webcam

        if (!camera.isOpened()) {
            System.out.println("Error: Camera not detected!");
            return;
        }

        Mat frame = new Mat();

        while (true) {
            camera.read(frame); // capture frame

            if (frame.empty()) {
                System.out.println("Error: No frame captured");
                break;
            }

            HighGui.imshow("Webcam Feed", frame);

            // Press ESC to exit
            if (HighGui.waitKey(30) == 27) {
                break;
            }
        }

        camera.release();
        HighGui.destroyAllWindows();
    }
}
