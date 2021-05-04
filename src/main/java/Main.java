
import java.io.*;
import java.util.zip.*;



public class Main {

    public static void main(String[] args){
        File file1 = new File("csv_example2.csv");

        try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(args[0]));
             OutputStream out = new FileOutputStream(file1)
        ) {

            byte[] buf = new byte[1024 * 4];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            String nextString;
            String stroke;
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            File file = new File("csv_example.csv");
            // Если файл не существует, то создадим его
            if (!file.exists())
                file.createNewFile();
            FileWriter fw;
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

                while ((nextString = reader.readLine()) != null) {
                   stroke = nextString.replaceAll("\\s+", ";").concat("\n");

                    bw.write(stroke);

                }
            reader.close();
            bw.close();
            fw.close();
            file1.deleteOnExit();

        }
        catch (IOException e) {
        e.printStackTrace();
        }

    }

}
