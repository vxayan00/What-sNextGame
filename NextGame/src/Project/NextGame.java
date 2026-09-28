package Project;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class NextGame {
private boolean gameOver = false;
private void readHighScores() 
{
String fileName = "highscores.txt";
String playerName = "";
int playerScore = 0;
try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
String line;
System.out.println("Highscores");
System.out.println("------------------------");
while ((line = reader.readLine()) != null) {
if (line.isBlank()) {
continue;
}
String[] parts = line.split(",");
playerName = parts[0].trim();
playerScore = Integer.parseInt(parts[1].trim()); 
System.out.println(playerScore + " - " + playerName);
}
reader.close();
} catch (IOException e) {
System.out.println("Could not read the file: " + e.getMessage());
} catch (NumberFormatException e) {
System.out.println("Found invalid number in file: " + e.getMessage());
}
}
private void updateHighScores(String currentPlayerName, int currentPlayerScore) 
{
String fileName = "highscores.txt";
ArrayList<String> player = new ArrayList<String>();
ArrayList<Integer> score = new ArrayList<Integer>();
try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
String line;
while ((line = reader.readLine()) != null) {
if (line.isBlank()) {
continue;
}
String[] parts = line.split(",");
if (parts.length >= 2) {
player.add(parts[0].trim());
score.add(Integer.parseInt(parts[1].trim()));
}
}
} catch (IOException e) {
System.out.println("Could not read the file: " + e.getMessage());
} catch (NumberFormatException e) {
System.out.println("Found invalid number in file: " + e.getMessage());
}
boolean found = false;
for (int i = 0; i < player.size(); i++) {
if (currentPlayerName.equalsIgnoreCase(player.get(i))) {
if (currentPlayerScore < score.get(i)) {
score.set(i, currentPlayerScore);
}
found = true;
break;
}
}
if (!found) {
player.add(currentPlayerName);

score.add(currentPlayerScore);
}
try (PrintWriter outFile = new PrintWriter(fileName)) {
for (int i = 0; i < player.size(); i++) {
outFile.println(player.get(i) + "," + score.get(i));
}
} catch (FileNotFoundException e) {
System.out.println("File not found: " + e.getMessage());
}
}
private void displayIntro() {
System.out.println("Game: What's Next?");
System.out.println("Objective: Identify the Sequence of 5 numbers between 1 and 5 using the fewest turns. If you wish to quit guessing and give up,");
System.out.println("Enter a ZERO for one of your guesses and the game will display the solution and quit.");
System.out.println(" GOOD LUCK!!!");
}
private int[] loadArray()
{
Random r = new Random();
int[] numbers = new int[5];
int index;
for (index = 0; index < numbers.length; index++)
{
numbers[index] = r.nextInt(5) + 1;
}
return numbers;
}
private boolean uniqueCheck(int[] array)
{
boolean zeroUnique = false;
boolean oneUnique = false;
boolean twoUnique = false;
boolean threeUnique = false;
boolean fourUnique = false;
boolean allUnique = false;
if ((array[0] != array[1]) && (array[0] != array[2]) && (array[0] != array[3]) && (array[0] != array[4]))
{
zeroUnique = true;
}
if ((array[1] != array[0]) && (array[1] != array[2]) && (array[1] != array[3]) && (array[1] != array[4]))
{
oneUnique = true;
}
if ((array[2] != array[0]) && (array[2] != array[1]) && (array[2] != array[3]) && (array[2] != array[4]))
{
twoUnique = true;
}
if ((array[3] != array[0]) && (array[3] != array[2]) && (array[1] != array[3]) && (array[3] != array[4]))
{
threeUnique = true;
}
if ((array[4] != array[0]) && (array[4] != array[2]) && (array[4] != array[3]) && (array[1] != array[4]))
{
fourUnique = true;
}
if (zeroUnique && oneUnique && twoUnique && threeUnique && fourUnique)
{
allUnique = true;
}
return allUnique;
}
private int[] correctUniqueArray(int[] array) 
{
boolean allUnique = uniqueCheck(array);
while (!allUnique) 
{ 
allUnique = uniqueCheck(array);
if(allUnique)
{
break;
}
else
{
array = loadArray();

}
}
return array;
}
private boolean arrayCompare(int[] correctArray, int[] guessArray) 
{
return Arrays.equals(correctArray, guessArray);
}
private boolean inputType(String userInput) 
{
boolean isValid = false;
int index;
char stringChar;
for (index = 0; index < userInput.length(); index++) 
{
stringChar = userInput.charAt(index);
if(!Character.isDigit(stringChar))
{
isValid = false;
break;
}
else {
isValid = true;
}
}
return isValid;
}
private int ansCorrect(int[] correctArray, int[] guessArray) 
{
int correctCounter = 0;
if(correctArray[0] == guessArray[0]) 
{
correctCounter++;
}
if(correctArray[1] == guessArray[1]) 
{
correctCounter++;
}
if(correctArray[2] == guessArray[2]) 
{
correctCounter++;
}
if(correctArray[3] == guessArray[3]) 
{
correctCounter++;
}
if(correctArray[4] == guessArray[4]) 
{
correctCounter++;
}
return correctCounter;
}
private int[] inputToArray(String userInput) 
{
String[] stringArray = userInput.split("");
int[] guessArray = new int[stringArray.length];
for (int i = 0; i < stringArray.length; i++) 
{
guessArray[i] = Integer.parseInt(stringArray[i]);
}
return guessArray;
}
private void printCorrectArray(int[] correctArray) 
{
System.out.println("Game Number Sequence");
System.out.println("-----------------------");
System.out.printf("%d | %d | %d | %d | %d\n", correctArray[0], correctArray[1], correctArray[2], correctArray[3], correctArray[4]);
System.out.println("-----------------------");
}
private boolean gameOver(int[] correctArray, int[] guessArray) 
{

boolean arraysMatch = false;
boolean zeroInArray = false;
boolean overCondition = false;
arraysMatch = arrayCompare(correctArray, guessArray);
if(guessArray[0] == 0 || guessArray[1] == 0 || guessArray[2] == 0 || guessArray[3] == 0 || guessArray[4] == 0) 
{
zeroInArray = true;
}
if(arraysMatch || zeroInArray) 
{
overCondition = true;
}
return overCondition;
}
private String getInput() 
{ 
Scanner scanner = new Scanner(System.in);
System.out.print("Enter your 5 numbers (no spaces): ");
String userInput = scanner.nextLine();
return userInput;
}
public void run() {
try {
displayIntro();
int[] correctArray = correctUniqueArray(loadArray());
int[] guessArray = new int[5];
Scanner scanner = new Scanner(System.in);
int turnCounter = 0;
String currentPlayerName = "";
String userInput = getInput();
turnCounter++;
guessArray = inputToArray(userInput);
gameOver = gameOver(correctArray, guessArray);
System.out.print("== Turn " + turnCounter + " == ");
System.out.printf("Number sequence: %d, %d, %d, %d, %d\n", guessArray[0], guessArray[1], guessArray[2], guessArray[3], guessArray[4
]);
System.out.println("You have " + ansCorrect(correctArray, guessArray) + " correct.");
while(gameOver == false) 
{
userInput = getInput();
turnCounter++;
guessArray = inputToArray(userInput);
gameOver = gameOver(correctArray, guessArray);
System.out.print("== Turn " + turnCounter + " == ");
System.out.printf("Number sequence: %d, %d, %d, %d, %d\n", guessArray[0], guessArray[1], guessArray[2], guessArray[3], 
guessArray[4]);
System.out.println("You have " + ansCorrect(correctArray, guessArray) + " correct.");
if(inputType(userInput) == false) 
{
System.out.println("You need to enter digits."); 
}
else if (userInput.equalsIgnoreCase(""))
{
System.out.println("Well, you at least have to enter something.");
}
else if (userInput.isEmpty() || userInput.isBlank())
{
System.out.println("Well, you at least have to enter something.");
}
else if (Integer.parseInt(userInput) < 11111 || Integer.parseInt(userInput) > 55555)
{
System.out.println("You need to enter numbers between 1 and 5.");
}
}
if(arrayCompare(correctArray, guessArray) == true)
{
System.out.println("Congrats! You win!"); 
}

printCorrectArray(correctArray);
System.out.println("Please enter your name: ");
currentPlayerName = scanner.next();
updateHighScores(currentPlayerName,turnCounter);
readHighScores();
}
catch (InputMismatchException e) {
e.toString();
System.out.println("Invalid input. Please enter numbers only.");
}
catch(ArrayIndexOutOfBoundsException e) 
{
e.toString();
System.out.println("Only enter 5 numbers.");
}
catch(NumberFormatException e) 
{
System.out.println("Stop typing weird stuff.");
}
catch(Exception e) 
{
System.out.println(e.toString());
System.out.print("I'm not sure what you did, but it was wrong.");
}
}
}