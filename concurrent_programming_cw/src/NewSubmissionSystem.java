import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NewSubmissionSystem {
    private final ExecutorService service;
    private final SubmissionStats stats;

    public NewSubmissionSystem(int poolSize) {
        this.stats = new SubmissionStats();
        this.service = Executors.newFixedThreadPool(poolSize);
    }

    public void processAllSubmissions(int noOfSubmissions) {
        stats.setStartTime();
        System.out.println("Processing" + noOfSubmissions + " submissions...");

        CountDownLatch latch = new CountDownLatch(noOfSubmissions);

        for (int i = 0; i < noOfSubmissions; i++) {
            int studentId = i+1;
            String studentName = "STD" + studentId;

            service.submit(() -> {
                try {
                    Student student = new Student(studentId, studentName);

                    boolean success = student.submitExam("Final Exam");

                    if(success){
                        stats.addSuccessfulSubmission();
                    }else{
                        stats.addFailedSubmission();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    stats.addFailedSubmission();
                }finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stats.setEndTime();
        System.out.println("All submissions finished.");
        stats.printStatisticSummery();

    }

    public void shutdown() {
        service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
            throw new RuntimeException(e);
        }
        System.out.println("The System Shutdown Complete...");
    }

}
