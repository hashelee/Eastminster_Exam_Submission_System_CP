public  class ExamSubmissionSimulator{
    public static void main(String[] args) {

    int poolSize = Runtime.getRuntime().availableProcessors() * 2;

    NewSubmissionSystem submissionSystem = new NewSubmissionSystem(poolSize);

    submissionSystem.processAllSubmissions(100_000);
    submissionSystem.shutdown();
}}
