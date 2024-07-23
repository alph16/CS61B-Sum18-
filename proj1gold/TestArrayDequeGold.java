import static org.junit.Assert.*;
import org.junit.Test;
import edu.princeton.cs.algs4.StdRandom;

public class TestArrayDequeGold {
    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solutionDeque = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder();
    
        for (int i = 0; i < 1000; i++) {
            double number = StdRandom.uniform();
    
            if (number < 0.25) {
                studentDeque.addFirst(i);
                solutionDeque.addFirst(i);
                message.append("addFirst(" + i + ")\n");
            } else if (number >= 0.25 && number < 0.5) {
                studentDeque.addLast(i);
                solutionDeque.addLast(i);
                message.append("addLast(" + i + ")\n");
            } else if (number >= 0.5 && number < 0.75) {
                if (!studentDeque.isEmpty()) {
                    Integer studentFirst = studentDeque.removeFirst();
                    Integer solutionFirst = solutionDeque.removeFirst();
                    message.append("removeFirst()\n");
                    assertEquals(message.toString(), solutionFirst, studentFirst);
                }
            } else {
                if (!studentDeque.isEmpty()) {
                    Integer studentLast = studentDeque.removeLast();
                    Integer solutionLast = solutionDeque.removeLast();
                    message.append("removeLast()\n");
                    assertEquals(message.toString(), solutionLast, studentLast);
                }
            }
        }
    }
}