import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract Question class representing a generic question
abstract class Question {
    private String prompt;

    public Question(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    // Abstract method to be implemented by concrete question types
    public abstract void display();

    // Abstract method to be implemented by concrete question types
    public abstract boolean checkAnswer(String answer);
}

// MultipleChoiceQuestion class representing a multiple choice question
class MultipleChoiceQuestion extends Question {
    private List<String> choices;
    private int correctChoiceIndex;

    public MultipleChoiceQuestion(String prompt, List<String> choices, int correctChoiceIndex) {
        super(prompt);
        this.choices = choices;
        this.correctChoiceIndex = correctChoiceIndex;
    }

    @Override
    public void display() {
        System.out.println(getPrompt());
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ". " + choices.get(i));
        }
    }

    @Override
    public boolean checkAnswer(String answer) {
        try {
            int selectedChoice = Integer.parseInt(answer);
            return selectedChoice == correctChoiceIndex + 1;
        } catch (NumberFormatException e) {
            // If the input is not a valid integer
            return false;
        }
    }
}

// TrueFalseQuestion class representing a true/false question
class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String prompt, boolean correctAnswer) {
        super(prompt);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void display() {
        System.out.println(getPrompt());
        System.out.println("1. True");
        System.out.println("2. False");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equalsIgnoreCase(String.valueOf(correctAnswer));
    }
}

// Quiz class representing a collection of questions
class Quiz {
    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            question.display();
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            if (question.checkAnswer(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
            System.out.println();
        }

        System.out.println("Quiz completed. Your score: " + score + "/" + questions.size());
    }
}

public class QuizPlatform {
    public static void main(String[] args) {
        // Create a quiz
        Quiz quiz = new Quiz();

        // Add some sample questions
        quiz.addQuestion(new MultipleChoiceQuestion("What is the capital of France?",
                List.of("Paris", "London", "Berlin", "Rome"), 0));
        quiz.addQuestion(new TrueFalseQuestion("Java is a programming language.", true));
        quiz.addQuestion(new MultipleChoiceQuestion("Which planet is known as the Red Planet?",
                List.of("Mars", "Venus", "Jupiter", "Saturn"), 0));
        quiz.addQuestion(new TrueFalseQuestion("The sun is a planet.", false));

        // Take the quiz
        quiz.takeQuiz();
    }
}
