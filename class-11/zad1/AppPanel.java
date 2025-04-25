package zad1;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class AppPanel extends JPanel {

    java.util.List<Drawable> drawableList;

    ExecutorService executorService;
    List<Future<String>> futureList;

    DefaultListModel<String> listModel;
    JList<String> jList;

    JScrollPane textAreaScroll;
    JScrollPane jListScroll;

    JPanel listPanel;
    JPanel controlPanel;
    JPanel buttonPanel;
    JButton addTaskButton;
    JButton cancelTaskButton;
    JButton getStateButton;
    JButton getResultButton;
    JButton clearButton;
    JTextArea textArea;

    Timer timer = new Timer(16, (e) -> repaint());

    public AppPanel(ExecutorService executorService, List<Future<String>> futureList, List<Drawable> drawableList){
        this.executorService = executorService;
        this.futureList = futureList;
        this.drawableList = drawableList;

        setPreferredSize(new Dimension(400, 400));
        setLayout(new BorderLayout());

        listPanel = new JPanel(new GridLayout(1, 1));
        add(listPanel, BorderLayout.CENTER);

        listModel = new DefaultListModel<>();
        jList = new JList<>(this.listModel);
        jListScroll = new JScrollPane(jList);
        listPanel.add(jListScroll);

        controlPanel = new JPanel(new BorderLayout());
        add(controlPanel, BorderLayout.SOUTH);

        textArea = new JTextArea();
        textArea.setEnabled(false);
        textAreaScroll = new JScrollPane(textArea);
        controlPanel.add(textAreaScroll, BorderLayout.CENTER);

        buttonPanel = new JPanel(new GridLayout(5, 1));
        controlPanel.add(buttonPanel, BorderLayout.EAST);

        addTaskButton = new JButton("Add task");
        addTaskButton.addActionListener(e -> addTask());
        buttonPanel.add(addTaskButton);

        cancelTaskButton = new JButton("Cancel task");
        cancelTaskButton.addActionListener(e -> cancelTask());
        buttonPanel.add(cancelTaskButton);

        getStateButton = new JButton("Get state");
        getStateButton.addActionListener(e -> getState());
        buttonPanel.add(getStateButton);

        getResultButton = new JButton("Get result");
        getResultButton.addActionListener(e -> getResult());
        buttonPanel.add(getResultButton);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clear());
        buttonPanel.add(clearButton);


        listPanel.setBorder(BorderFactory.createTitledBorder("Tasks"));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        timer.start();
    }

    private void addTask(){
        Future<String> future = executorService.submit(() -> {
            int id = futureList.size();

            listModel.addElement("Task " + id);
            textArea.append("Task " + id + " (created)\n");
            this.scrollDown();

            Enemy enemy = Enemy.createEnemy(drawableList);
            drawableList.add(enemy);

            int time = 0;
            try{
                textArea.append("Task " + id + " (running)\n");
                while(time != 10000 && enemy.isAlive()){
                    Thread.sleep(1);
                    time += 1;
                }
                textArea.append("Task " + id + " (finished)\n");
            } catch (InterruptedException e){
                textArea.append("Task " + id + " (interrupted)\n");
                drawableList.remove(enemy);

                this.scrollDown();
                return "killed";
            }
            drawableList.remove(enemy);
            this.scrollDown();
            return "finished";
        });

        futureList.add(future);
    }

    private void cancelTask(){
        int id = jList.getSelectedIndex();
        Future<?> future = futureList.get(id);
        future.cancel(true);
    }

    private void getState(){
        int id = jList.getSelectedIndex();
        Future<?> future = futureList.get(id);
        StringBuilder state = new StringBuilder();
        if(future.isDone()){
            state.append("finished");
        } else if (future.isCancelled()){
            state.append("interrupted");
        } else {
            state.append("running");
        }

        textArea.append("Task " + (id + 1) + " (" + state + ")\n");
        this.scrollDown();
    }

    private void getResult() {
        int id = jList.getSelectedIndex();
        Future<?> future = futureList.get(id);

        try{
            if(future.isDone()){
                textArea.append("Task " + (id + 1) + " (result: " + future.get() + ")\n");
            } else {
                textArea.append("Task " + (id + 1) + " (result: not finished)\n");
            }
        } catch (InterruptedException | ExecutionException | CancellationException ignored) {
            textArea.append("Task " + (id + 1) + " (result: interrupted)\n");
        }

        this.scrollDown();
    }

    public void clear(){
        for(Future<?> future: this.futureList){
            if(!future.isDone()){
                return;
            }
        }
        this.listModel.clear();
        this.futureList.clear();
        this.textArea.setText("");
        this.scrollDown();
    }

    private void scrollDown(){
        JScrollBar vertical = jListScroll.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() - 1 );

        this.textArea.setCaretPosition(textArea.getDocument().getLength());

    }
}
