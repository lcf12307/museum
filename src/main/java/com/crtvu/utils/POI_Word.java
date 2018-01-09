package com.crtvu.utils;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;

public class POI_Word{
    public  static float[] readData(String file){
        float[] s = new float[100];
        try {
            FileInputStream in=new FileInputStream(file);
            POIFSFileSystem pfs=new POIFSFileSystem(in);
            HWPFDocument hwpf=new HWPFDocument(pfs);
            Range range =hwpf.getRange();
            TableIterator it=new TableIterator(range);
            int index=0;
            int count = 0;
            while(it.hasNext()){
                Table tb=(Table)it.next();
                if (count == 2){
                    for(int i=1;i<tb.numRows();i++){
                        TableRow tr=tb.getRow(i);
                        TableCell td;
                        td=tr.getCell(4);
                        System.out.println(td.text().split("FORMTEXT ")[1].trim());
                        System.out.println(Float.valueOf(td.text().split("FORMTEXT ")[1].trim()));
                        s[index++] = Float.valueOf(td.text().split("FORMTEXT ")[1].trim());

                    }
                }
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}