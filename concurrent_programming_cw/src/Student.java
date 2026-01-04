import java.util.Random;

public class Student {
    private  final int studentId;
    private  final String studentName;
    private Random random;
    private  final long time;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.time = System.currentTimeMillis();
        this.random = new Random();
    }

    public boolean submitExam(String examName){
        int studentId = this.studentId;
        String studentName = this.studentName;

        System.out.println("Submitting exam #" + studentId + " " + studentName);

        try {
            Thread.sleep(random.nextInt(10)+1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        long processTime = System.currentTimeMillis() - this.time;

        System.out.println("Student" + studentId + examName + "processed in " + processTime + " ms");

        int randomInt = random.nextInt(100);

        return randomInt >= 5;

    }

}
