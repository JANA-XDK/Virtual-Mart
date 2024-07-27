package com.example.sceneformar;

import android.content.Context;
import org.tensorflow.lite.Interpreter;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import android.content.res.AssetFileDescriptor;
import java.util.Random;

public class ApparelClassifier {
    private Interpreter interpreter;
    private Random random = new Random();

    public ApparelClassifier(Context context) throws IOException {
        MappedByteBuffer model = loadModelFile(context, "fashion_mnist_model.tflite");
        interpreter = new Interpreter(model);
    }

    private MappedByteBuffer loadModelFile(Context context, String modelFile) throws IOException {
        AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(modelFile);
        FileInputStream inputStream = new FileInputStream(assetFileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = assetFileDescriptor.getStartOffset();
        long declaredLength = assetFileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public float[] classify(float[] input) {
        float[][] output = new float[1][10]; // Adjust the size based on your model's output
        interpreter.run(input, output);
        return output[0];
    }

    public String getRecommendation(float userSize) {
        String[] smallMessages = {
                "This shirt might be a bit small.",
                "Try a larger size for a better fit.",
                "Might be snug, consider sizing up."
        };

        String[] perfectMessages = {
                "Perfect for your choice!",
                "Great choice, it'll fit just right.",
                "A perfect match for your size!"
        };

        String[] largeMessages = {
                "This shirt might be a bit large.",
                "Consider going for a smaller size.",
                "A little roomy, how about a size down?"
        };

        String[] outOfRangeMessages = {
                "Oops! Size not available for now...",
                "Sorry, we don't have that size.",
                "Try a different size, this one's not in stock."
        };

        String message;

        if (userSize >= 0 && userSize <= 30) {
            message = smallMessages[random.nextInt(smallMessages.length)];
        } else if (userSize >= 31 && userSize <= 45) {
            message = perfectMessages[random.nextInt(perfectMessages.length)];
        } else if (userSize >= 46 && userSize <= 100) {
            message = largeMessages[random.nextInt(largeMessages.length)];
        } else {
            message = outOfRangeMessages[random.nextInt(outOfRangeMessages.length)];
        }

        return message;
    }
}
