
package TextEditor;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class text {
    private LinkedList<Character> text;
    private int cursorPosition;
    private Stack<EditorState> undoStack;

    public text() {
        text = new LinkedList<>();
        cursorPosition = 0;
        undoStack = new Stack<>();
    }

    public void insertText(String input) {
        saveState();
        for (char ch : input.toCharArray()) {
            text.add(cursorPosition++, ch);
        }
    }

    public void deleteText(int length) {
        saveState();
        for (int i = 0; i < length && cursorPosition > 0; i++) {
            text.remove(--cursorPosition);
        }
    }

    public void moveCursorLeft() {
        if (cursorPosition > 0) {
            cursorPosition--;
        }
    }

    public void moveCursorRight() {
        if (cursorPosition < text.size()) {
            cursorPosition++;
        }
    }

    public void displayText() {
        for (char ch : text) {
            System.out.print(ch);
        }
        System.out.println();
    }

    private void saveState() {
        undoStack.push(new EditorState(new LinkedList<>(text), cursorPosition));
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            EditorState previousState = undoStack.pop();
            this.text = previousState.getText();
            this.cursorPosition = previousState.getCursorPosition();
        }
    }

    private static class EditorState {
        private LinkedList<Character> text;
        private int cursorPosition;

        public EditorState(LinkedList<Character> text, int cursorPosition) {
            this.text = text;
            this.cursorPosition = cursorPosition;
        }

        public LinkedList<Character> getText() {
            return text;
        }

        public int getCursorPosition() {
            return cursorPosition;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        text editor = new text();
        String command;

        while (true) {
            System.out.println("Enter command (insert, delete, left, right, display, undo, exit):");
            command = scanner.nextLine().trim();

            switch (command) {
                case "insert":
                    System.out.println("Enter text to insert:");
                    String textToInsert = scanner.nextLine();
                    editor.insertText(textToInsert);
                    break;
                case "delete":
                    System.out.println("Enter number of characters to delete:");
                    int lengthToDelete = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    editor.deleteText(lengthToDelete);
                    break;
                case "left":
                    editor.moveCursorLeft();
                    break;
                case "right":
                    editor.moveCursorRight();
                    break;
                case "display":
                    editor.displayText();
                    break;
                case "undo":
                    editor.undo();
                    break;
                case "exit":
                    System.out.println("Exiting editor.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}

// *-------------------------------------------------------- */

// * Insert Text */
// ! Time Complexity = O(n)
// ! Space Complexity = O(n)

// * Delete Text */
// ! Time Complexity = O(n)
// ! Space Complexity = O(n)

// * Move Cursor(left,right) */
// ! Time Complexity = O(1)
// ! Space Complexity = O(1)

// * Display Text */
// ! Time Complexity = O(n)
// ! Space Complexity = O(1)

// * Undo */
// ! Time Complexity = O(1)
// ! Space Complexity = O(n)

// * Overall the editor uses O(n) space due to the text storage and undo task */