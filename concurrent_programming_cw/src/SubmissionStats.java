import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SubmissionStats {
    private  final AtomicInteger noOfSuccessfulSubmissions;
    private  final AtomicInteger noOfFailedSubmissions;
    private AtomicLong startTime;
    private AtomicLong endTime;

    public SubmissionStats() {
        noOfSuccessfulSubmissions = new AtomicInteger(0);
        noOfFailedSubmissions = new AtomicInteger(0);
        startTime = new AtomicLong(0);
        endTime = new AtomicLong(0);
    }

    public void addSuccessfulSubmission() {
        noOfSuccessfulSubmissions.incrementAndGet();
    }

    public void addFailedSubmission() {
        noOfFailedSubmissions.incrementAndGet();
    }

    public int getNoOfSuccessfulSubmissions() {
        return noOfSuccessfulSubmissions.get();
    }

    public int getNoOfFailedSubmissions() {
        return noOfFailedSubmissions.get();
    }

    public int getTotalNumberOfSubmissions() {
        return  getNoOfSuccessfulSubmissions() + getNoOfFailedSubmissions();
    }

    public void setStartTime() {
        this.startTime.set(System.currentTimeMillis());
    }

    public void setEndTime() {
        this.endTime.set(System.currentTimeMillis());
    }

    public double getSuccessRate(){
        int total = getTotalNumberOfSubmissions();
        if (total == 0) return 0.0;
        return (noOfSuccessfulSubmissions.get() * 100.0) / total;
    }

    public double getTotalSubmissionTimeInSeconds(){
        return (endTime.get() - startTime.get()/1000.0);
    }

    public double getThroughPut() {
        double time = getTotalSubmissionTimeInSeconds();
        if (time == 0) return 0;
        return getTotalNumberOfSubmissions() / time;
    }

    public void printStatisticSummery() {
        System.out.println("\n--- SUBMISSION STATISTICS ---");
        System.out.println("Total Time Taken: " + getTotalSubmissionTimeInSeconds() + " seconds");
        System.out.println("Total Submissions Processed: " + getTotalNumberOfSubmissions());
        System.out.println("Number of Successful Submissions: " + getNoOfSuccessfulSubmissions());
        System.out.println("Number of Failed Submissions: " + getNoOfFailedSubmissions());
        System.out.printf("Success rate percentage: %.2f%%%n", getSuccessRate());
        System.out.printf("System Throughput: %.2f submissions/sec%n", getThroughPut());
        System.out.println("-----------------------------");
    }


}
