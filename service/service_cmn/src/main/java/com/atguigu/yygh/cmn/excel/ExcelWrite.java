package com.atguigu.yygh.cmn.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiZhanHong
 * @Date: 2023/05/29/16:57
 * @Description:
 */
public class ExcelWrite {
    public static void main(String[] args) {
        List<Student> students=new ArrayList<Student>();
        students.add(new Student(1,"朱晓溪",18,true));
        students.add(new Student(2,"常永亮",18,true));
        students.add(new Student(3,"段磊",18,true));
        students.add(new Student(4,"田佳",18,true));

        List<Student> studentList=new ArrayList<Student>();
        studentList.add(new Student(5,"梁启晨",18,true));
        studentList.add(new Student(6,"王志峰",18,true));


        ExcelWriter excelWriter = EasyExcel.write("C:\\Users\\lizhanhong\\Desktop\\abc.xlsx", Student.class).build();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "学生列表1").build();
        WriteSheet sheet2 = EasyExcel.writerSheet(1, "学生列表2").build();
        excelWriter.write(students,sheet1);
        excelWriter.write(studentList,sheet2);

        //关闭
        excelWriter.finish();
    }
}
