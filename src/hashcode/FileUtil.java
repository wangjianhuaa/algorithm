package hashcode;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * 工具类
 * @author wangjianhua
 * @date 2021-05-13 9:56
 */
public class FileUtil {
    /**
     * 读取本地文件 单词表
     * @param url 路径
     * @return 去重之后的单词集合
     */
    public static Set<String> readWordSet(String url){
        Set<String> set = new HashSet<>();
        try{
            InputStreamReader isr = new InputStreamReader(new FileInputStream(url), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine())!=null){
                String[] ss = line.split("\t");
                set.add(ss[1]);
            }
            br.close();
            isr.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return set;

    }
}
