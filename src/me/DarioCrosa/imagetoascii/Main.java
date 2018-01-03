package me.DarioCrosa.imagetoascii;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            System.out.println("Reading image from " + path);
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println("Error while loading image:");
            e.printStackTrace();
            System.exit(1);
        }
        return image;
    }

    private static void writeCompleteASCII(String toWrite, String path) {
        try {
            System.out.println("Saving file to " + path);
            FileWriter writer = new FileWriter(path);
            writer.write(toWrite);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error while saving file:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Integer getInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException  e) {
            System.out.println("Error: \"" + string + "\" is not a number");
            return null;
        }
    }

    private static void printHelp() {
        System.out.println("Help for ImageToAscii \n" +
                "Options: \n" +
                "-help: shows this \n" +
                "-input, -in: input image path (required) \n" +
                "-output, -out: output image path (if not specified set to \"./out.txt\") \n" +
                "-width, -w: width of the output \n" +
                "-height, -h: height of the output \n" +
                "-chars, -c: specify a list of chars (from darkest to lightest) to use for the conversion. Default is \"%&#MHGw*+-. \"");
        System.exit(0);
    }

    public static void main(String[] args) {

        // if there are no args print help and exit
        if (args.length == 0) printHelp();

        // initialize vars
        String inputFile = null;
        String outputFile = null;
        String chars = "";
        Integer width = null;
        Integer height = null;

        // Parse args
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                switch (args[i]){
                    case "-help": printHelp();
                        break;
                    case "-input": inputFile = args[++i]; // input
                        break;
                    case "-in": inputFile = args[++i];
                        break;
                    case "-output": outputFile = args[++i]; // output
                        break;
                    case "-out": outputFile = args[++i];
                        break;
                    case "-width": width = getInteger(args[++i]); // output width
                        break;
                    case "-w": width = getInteger(args[++i]);
                        break;
                    case "-height": height = getInteger(args[++i]); // output height
                        break;
                    case "-h": height = getInteger(args[++i]);
                        break;
                    case "-chars": chars = args[++i];
                        break;
                    case "-c": chars = args[++i];
                        break;
                    default: System.out.println(args[i] + " is not an argument");
                        break;
                }
            }
        }

        // Check if input and output have been specified
        if (inputFile == null) {
            System.err.println("Error: no input image specified");
            System.exit(1);
        }
        if (outputFile == null) {
            System.out.println("No output file specified, outputting to \"./ASCII_out.txt\"");
            outputFile = "./out.txt";
        }

        // load image
        BufferedImage img = loadImage(inputFile);

        System.out.println("input = " + inputFile);
        System.out.println("output = " + outputFile);

        // make sure that all the arguments have been set
        if(width == null) {
            width = img.getWidth();
        }
        if (height == null) {
            height = img.getHeight();
        }

        // convert image
        System.out.println("Converting image");
        String AsciiImage = new BufferedImageToASCII(img, width, height, chars).getOutput();

        // write to disk
        writeCompleteASCII(AsciiImage, outputFile);
    }
}
