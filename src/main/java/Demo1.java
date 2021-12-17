import java.io.*;
import java.util.Scanner;

/**
 * 查找某文件夹下所有的log文件并且搜寻log文件的内容
 */
public class Demo1 {
    public static void main(String[] args) throws IOException {
        String find1="R(35,73)";
        String find2="R(72,73)";

        System.out.println("输入文件夹路径:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        //获取文件夹对象
        File file = new File(path);
        //获取该文件夹下所有子文件
        File[] childFiles = file.listFiles();
        //遍历子文件
        for (File file1:childFiles){
            String name = file1.getName();
            //筛选出log文件

            if (name.contains("log")){
                //对log文件进行读操作
                System.out.println(name);
                FileReader fileReader = new FileReader(file1);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line="";
                //一行一行的进行读取,注意find1和find2的顺序
                first: while ((line =bufferedReader.readLine())!=null){
                    if (line.contains("Optimized Parameters")){
                        while ((line =bufferedReader.readLine())!=null){
                            if (line.contains(find1)){
                                System.out.println(line);
                            }
                            if (line.contains(find2)){
                                System.out.println(line);
                                break first;
                            }
                        }
                    }
                }


            }
        }
    }
}
