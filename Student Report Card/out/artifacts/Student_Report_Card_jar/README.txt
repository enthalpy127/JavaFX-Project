Before to be able to run the program, please edit a line in the window batch file.

You need to copy the file path of your lib folder under "javafx-sdk-16" folder which is inside this PROJECT folder. 

(Because JavaFX is not a part of Java since Java 11. We have to manually tell the compiler to find the JavaFX.)

After you copy the file path, paste it after "java --module-path". (Your file path must be in double-quote if your file path contains one or more white spaces)

For example, the command in bat file should be like that ==> java --module-path "C:\User\user\Student Report Card\javafx-sdk-16\lib" --add-modules javafx.controls,javafx.fxml -jar "Student Report Card.jar"