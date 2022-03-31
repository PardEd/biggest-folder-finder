import java.io.File;
import java.util.ArrayList;

public class Node {

    private final File folder;
    private ArrayList<Node> children;
    private long size;
    private int level;
    private long limit;

    public Node(File folder){
        this.folder = folder;
        children = new ArrayList<>();
    }

    public Node(File folder, long limit) {
        this(folder);
        this.limit = limit;
    }

    public File getFolder() {
        return folder;
    }

    public long setLimit(long limit){
        return limit;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        node.setLimit(limit);
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String toString() {
        String size = SizeCalculator.getFromHumanReadableSize(getSize());
        StringBuilder builder = new StringBuilder();
        builder.append(folder.getName()).append(" - ").append(size).append("\n  ");
        for (Node child : children) {
            if (child.getSize() > limit) {
                builder.append("  ".repeat(level)).append(child);
            }
        }
        return builder.toString();
    }
}
