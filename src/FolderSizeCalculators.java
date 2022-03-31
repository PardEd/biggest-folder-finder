import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculators extends RecursiveTask<Long> {

    private final Node node;

    public FolderSizeCalculators(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {

        File folder = node.getFolder();
        if (folder.isFile()) {
            long length = folder.length();
            node.setSize(length);
            return length;
        }
        long sum = 0;
        List<FolderSizeCalculators> subTasks = new LinkedList<>();
        File[] files = folder.listFiles();

        assert files != null;
        for (File file : files) {
            Node child = new Node(file);
            FolderSizeCalculators task = new FolderSizeCalculators(child);
            task.fork();
            subTasks.add(task);
            node.addChild(child);
        }

        for (FolderSizeCalculators task : subTasks) {
            sum += task.join();
        }
        node.setSize(sum);
        return sum;
    }
}