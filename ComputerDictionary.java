import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ComputerDictionary {

    private Map<String, String> dictionary = new HashMap<>();
    private final String DATA_FILE = "dictionary.txt"; // file to store data

    private JFrame frame;
    private JTextField termField;
    private JTextArea meaningArea;
    private JTextArea outputArea;

    public ComputerDictionary() {
        // Preload 50 basic computer terms
        dictionary.put("Algorithm", "A step-by-step procedure for solving a problem.");
        dictionary.put("Array", "A collection of elements of the same type stored in contiguous memory.");
        dictionary.put("Binary", "A numbering system using only 0 and 1.");
        dictionary.put("Bit", "The smallest unit of data in computing, either 0 or 1.");
        dictionary.put("Boolean", "A data type with only two values: true or false.");
        dictionary.put("Bug", "An error or flaw in a program that causes it to malfunction.");
        dictionary.put("Class", "A blueprint for creating objects in object-oriented programming.");
        dictionary.put("Cloud Computing", "Delivery of computing services over the internet.");
        dictionary.put("Compiler", "A program that converts source code into executable code.");
        dictionary.put("CPU", "Central Processing Unit; the brain of the computer.");
        dictionary.put("Data Structure", "A way to organize and store data efficiently.");
        dictionary.put("Database", "An organized collection of data that can be accessed electronically.");
        dictionary.put("Debugging", "The process of identifying and fixing errors in code.");
        dictionary.put("Encryption", "Converting data into a coded form to prevent unauthorized access.");
        dictionary.put("Exception", "An event that disrupts the normal flow of a program.");
        dictionary.put("Function", "A block of code that performs a specific task.");
        dictionary.put("Garbage Collection", "Automatic memory management in some programming languages.");
        dictionary.put("HashMap", "A collection that stores key-value pairs with fast access.");
        dictionary.put("HTML", "HyperText Markup Language, used to create web pages.");
        dictionary.put("HTTP", "HyperText Transfer Protocol; protocol for transferring web data.");
        dictionary.put("IDE", "Integrated Development Environment; a software for coding efficiently.");
        dictionary.put("Inheritance", "A mechanism where one class acquires properties of another class.");
        dictionary.put("IP Address", "A unique identifier for a device on a network.");
        dictionary.put("JSON", "JavaScript Object Notation; a lightweight data interchange format.");
        dictionary.put("Java", "A high-level, object-oriented programming language.");
        dictionary.put("JavaScript", "A scripting language used to create dynamic web content.");
        dictionary.put("Kernel", "The core part of an operating system that manages resources.");
        dictionary.put("Linked List", "A data structure where elements are linked using pointers.");
        dictionary.put("Loop", "A control structure that repeats code multiple times.");
        dictionary.put("Method", "A function defined inside a class.");
        dictionary.put("Network", "A system of interconnected computers that share resources.");
        dictionary.put("Object", "An instance of a class containing data and methods.");
        dictionary.put("OOP", "Object-Oriented Programming; a programming paradigm based on objects.");
        dictionary.put("Operating System", "Software that manages computer hardware and software resources.");
        dictionary.put("Pointer", "A variable that stores the address of another variable.");
        dictionary.put("Process", "An instance of a program that is running.");
        dictionary.put("Recursion", "A technique where a function calls itself.");
        dictionary.put("Runtime", "The period during which a program is executing.");
        dictionary.put("Stack", "A data structure that follows Last-In-First-Out order.");
        dictionary.put("Syntax", "The set of rules defining valid statements in a programming language.");
        dictionary.put("Thread", "A lightweight process that can run concurrently within a program.");
        dictionary.put("Variable", "A storage location in programming that holds data.");
        dictionary.put("Virtual Memory", "A memory management technique that uses disk space as RAM.");
        dictionary.put("Web Server", "A system that serves web pages to clients over the internet.");
        dictionary.put("Wi-Fi", "Wireless technology for connecting devices to a network.");
        dictionary.put("XML", "eXtensible Markup Language used to store and transport data.");
        dictionary.put("Algorithm Complexity", "A measure of the efficiency of an algorithm.");
        dictionary.put("API", "Application Programming Interface; a set of functions for software interaction.");
        dictionary.put("Cache", "A small, fast memory for frequently used data.");
        dictionary.put("Database Index", "A data structure to speed up queries in a database.");
        dictionary.put("Framework", "A reusable set of libraries or classes for software development.");
        dictionary.put("Git", "A version control system to track changes in code.");
        dictionary.put("IDE Plugin", "An add-on that extends the capabilities of an IDE.");
        dictionary.put("Recursion Base Case", "The condition that stops recursive function calls.");

        loadDictionary(); // load previously saved data from file (if any)
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Computer Science Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(new BorderLayout());

        // Top panel for input
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        topPanel.add(new JLabel("Term:"));
        termField = new JTextField();
        topPanel.add(termField);

        topPanel.add(new JLabel("Meaning:"));
        meaningArea = new JTextArea(3, 20);
        meaningArea.setLineWrap(true);
        topPanel.add(new JScrollPane(meaningArea));

        frame.add(topPanel, BorderLayout.NORTH);

        // Center panel for output
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton searchBtn = new JButton("Search");
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton listBtn = new JButton("List All");

        buttonPanel.add(searchBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(listBtn);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        searchBtn.addActionListener(e -> searchTerm());
        addBtn.addActionListener(e -> addTerm());
        updateBtn.addActionListener(e -> updateTerm());
        deleteBtn.addActionListener(e -> deleteTerm());
        listBtn.addActionListener(e -> listAllTerms());

        frame.setVisible(true);
    }

    // Case-insensitive search
    private void searchTerm() {
        String term = termField.getText().trim();
        if (term.isEmpty()) {
            outputArea.setText("Please enter a term to search.");
            return;
        }

        String meaning = dictionary.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(term))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

        if (meaning != null) {
            outputArea.setText(term + ": " + meaning);
        } else {
            outputArea.setText("Term not found in the dictionary.");
        }
    }

    private void addTerm() {
        String term = termField.getText().trim();
        if (term.isEmpty()) {
            outputArea.setText("Enter a term to add.");
            return;
        }
        if (dictionary.containsKey(term)) {
            outputArea.setText("Term already exists. Try updating it instead.");
            return;
        }
        String meaning = meaningArea.getText().trim();
        dictionary.put(term, meaning);
        outputArea.setText("Term added successfully!");
        saveDictionary();
        clearFields();
    }

    private void updateTerm() {
        String term = termField.getText().trim();
        if (!dictionary.containsKey(term)) {
            outputArea.setText("Term does not exist. Try adding it first.");
            return;
        }
        String meaning = meaningArea.getText().trim();
        dictionary.put(term, meaning);
        outputArea.setText("Term updated successfully!");
        saveDictionary();
        clearFields();
    }

    private void deleteTerm() {
        String term = termField.getText().trim();
        if (dictionary.remove(term) != null) {
            outputArea.setText("Term deleted successfully!");
            saveDictionary();
            clearFields();
        } else {
            outputArea.setText("Term not found in the dictionary.");
        }
    }

    private void listAllTerms() {
        if (dictionary.isEmpty()) {
            outputArea.setText("Dictionary is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("All terms in the dictionary:\n");
        dictionary.keySet().stream().sorted().forEach(term ->
                sb.append(term).append(": ").append(dictionary.get(term)).append("\n")
        );
        outputArea.setText(sb.toString());
    }

    private void clearFields() {
        termField.setText("");
        meaningArea.setText("");
    }

    private void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                writer.write(entry.getKey() + "##" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving dictionary: " + e.getMessage());
        }
    }

    private void loadDictionary() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("##", 2);
                if (parts.length == 2) {
                    dictionary.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading dictionary: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComputerDictionary::new);
    }
}
