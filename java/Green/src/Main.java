import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {

    public static String OS = "windows";

    public static String startTime = "";
    public static String endTime = "";

    static File directory = new File("");
    static String courseFile = "";

    public static int times = 1;

    public static void commit() throws IOException {
        String cmd = courseFile + File.separator + "nircmd elevate git commit -a -m \"commit file\"";
        System.out.println(cmd);
        Runtime.getRuntime().exec(cmd);
    }

    public static void modifyFile() throws IOException {
        File file = new File(courseFile + File.separator + "xx.txt");
        if (!file.exists())
            file.createNewFile();

        FileOutputStream out = new FileOutputStream(file);
        out.write(new String(System.currentTimeMillis() + "").getBytes());
        out.close();
    }

    private static void modifyTime() throws ParseException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        Date startDate = format.parse(startTime);
        Date endDate = format.parse(endTime);

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        long startTimeMillis = startDate.getTime();

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        long endTimeMillis = endDate.getTime();

        long oneDay = 1000 * 60 * 60 * 24l;

        long time = startTimeMillis;
        while (time <= endTimeMillis) {
            Date d = new Date(time + 1000);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            String date = df.format(d);
            System.out.println(date);
            String cmd = courseFile + File.separator + "nircmd elevate cmd /c date " + date;
            Runtime.getRuntime().exec(cmd);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < times; i++) {
                modifyFile();
                commit();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            time += oneDay;
        }
    }

    public static void main(String[] args) {

        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String osName = System.getProperty("os.name");
        System.out.println(osName);
        if (osName.matches("^(?i)Windows.*$")) {// Window ϵͳ

        } else {// Linux ϵͳ

        }

        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(courseFile);

        if (args.length == 1) {
            startTime = args[0];
        } else if (args.length == 2) {
            startTime = args[0];
            times = Integer.parseInt(args[1]);
        } else {
            for (int i = 0; i < args.length; i += 2) {
                if (args[i].equals("-start")) {
                    startTime = args[i + 1];
                } else if (args[i].equals("-end")) {
                    endTime = args[i + 1];
                } else if (args[i].equals("-times")) {
                    times = Integer.parseInt(args[i + 1]);
                } else {
                    System.out.println("args error");
                }
            }
        }
        
        if(startTime == null || startTime.length() <1){
            System.err.println("time error");
            return;
        }
        
        if(endTime == null || endTime.length()<1){
            endTime = startTime;
        }

        try {
            modifyTime();
        } catch (ParseException e) {
            System.out.println("time error");
        } catch (IOException e) {
            System.out.println("error:" + e.toString());
        }
    }
}
