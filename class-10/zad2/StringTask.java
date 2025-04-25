package zad2;

public class StringTask implements Runnable {
    private String text;
    private final String copy;
    private final int times;
    private volatile TaskState taskState;
    private Thread task;

    public StringTask(String copy, int times){
        this.text = "";
        this.copy = copy;
        this.times = times;
        this.taskState = TaskState.CREATED;
    }

    public String getResult() {
        return this.text;
    }

    public TaskState getState() {
        return this.taskState;
    }

    public void start() {
        this.taskState = TaskState.RUNNING;
        this.task = new Thread(this);
        this.task.start();
    }

    public void abort() {
        this.taskState = TaskState.ABORTED;
        this.task.interrupt();
    }

    public boolean isDone() {
        return this.taskState == TaskState.READY || this.taskState == TaskState.ABORTED;
    }

    @Override
    public void run() {
        for(int i = 0; i < this.times; i++) {
            if(Thread.currentThread().isInterrupted()){
                return;
            }
            this.text = this.text + this.copy;
        }
        this.taskState = TaskState.READY;
    }
}
