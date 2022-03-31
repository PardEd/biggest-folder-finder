import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

//        for (int i = 0; i < args.length; i++){
//            System.out.println(i + " => " + args[i]);
//        }
//        System.exit(0);

        ParametersBag bag = new ParametersBag(args);

        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        System.out.println("Непонятный размер: " + file.length());
        System.out.println();

        FolderSizeCalculators calculators = new FolderSizeCalculators(root);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(calculators);
        System.out.println(root);
    }
}


