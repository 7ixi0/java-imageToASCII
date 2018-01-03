# IMAGE TO ASCII #

# Features
Convert an image (GIF, PNG, JPEG, BMP, and WBMP) to ASCII (.txt file)

# Usage
1. Compile into .jar
2. `java -jar path/to/compiled/jar [options]`

## Options
* -help: shows help
* -input, -in: input image path (required)
* -output, -out: output image path (if not specified set to "./out.txt")
* -width, -w: width of the output
* -height, -h: height of the output
* -chars, -c: specify a list of chars (from darkest to lightest) to use for the conversion. Default is "%&#MHGw*+-. "