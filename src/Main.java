import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        ParametersBag bag = new ParametersBag(args);

        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        FolderSizeCalculators calculators = new FolderSizeCalculators(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculators);
        System.out.println(root);
    }
}


