package lesson2;

import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

class FileParser {

    private final Path path;

    FileParser(String path) {
        this.path = Paths.get(path);
    }

    ProductSet parse() {

        ProductSet productSet = new ProductSet();

        try {
            Iterator<String> it = Files.lines(this.path).iterator();
            it.next();
            while (it.hasNext()) {
                String[] line = it.next().split("\t");
                productSet.add(new Product.ProductBuilder()
                        .group1(line[0])
                        .group2(line[1])
                        .group3(line[2])
                        .group4(line[3])
                        .group5(line[4])
                        .shortName(line[5])
                        .code(Long.parseLong(line[6]))
                        .articul(line[7])
                        .fullName(line[8])
                        .price(Integer.parseInt(line[9]))
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productSet;

    }

    void watchForChanges() {

        System.out.println("Waiting for changes at " + path.getFileName());

        try (final WatchService service = path.getParent().getFileSystem().newWatchService()){
            final WatchKey watchKey = path.getParent().register(service, ENTRY_MODIFY);
            try {
                final WatchKey wk = service.take();
                for (WatchEvent<?>  event : wk.pollEvents()) {
                    final Path changedPath = (Path) event.context();
                    if (changedPath.endsWith(path.getFileName())) {
                        System.out.println(path.getFileName() + " is changed!");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
