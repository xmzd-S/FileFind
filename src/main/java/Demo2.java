import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 查找某文件夹下所有的log文件并且搜寻log文件的内容,
 * 并将搜寻到的内容存到数组中
 */
public class Demo2 {
    public static void main(String[] args) throws IOException {
        String find1="R(35,73)";
        String find2="R(72,73)";
        //总存储
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();

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
                ArrayList<String> stringArrayList=new ArrayList<>();
                stringArrayList.add(name);
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
                                //按空格分割
                                String[] s = line.split("\\s+");
                                for(String s2: s){
                                    //除掉首尾空格
                                    String trim = s2.replaceAll(" ","");
                                    stringArrayList.add(trim);
                                }
                                System.out.println(line);
                            }
                            if (line.contains(find2)){
                                String[] s = line.split("\\s+");
                                for(String s2: s){
                                    //除掉首尾空格
                                    String trim = s2.replaceAll(" ","");
                                    stringArrayList.add(trim);
                                }
                                System.out.println(line);
                                break first;
                            }
                        }

                    }
                }
                arrayList.add(stringArrayList);
            }
        }
        System.out.println("===================================================================");
        for (ArrayList<String> arrayList1:arrayList){
            int i=0;
            for (String s : arrayList1){
                System.out.print(i++ +"  " + s+"  ");
            }
            System.out.println();
        }
    }




    @Test
    public void testSplit(){
        String s = " hello  world";
        String[] s1 = s.split(" ");
        for (String s2 : s1){
            System.out.println(s2 );
        }
    }
    @Test
    public void testTrim(){
        String s= "               hello                         world               0";
        String trim = s.replaceAll(" ","");
        System.out.println(trim);
    }

}
