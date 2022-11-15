package springboot.service.Impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springboot.mybatis.po.TStudent;
import springboot.service.ImportExcelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ImportExcelServiceImpl implements ImportExcelService {
    @Override
    public List<TStudent> importExcelforStudentLogin(MultipartFile file, HttpServletRequest httpServletRequest,
                                                     HttpServletResponse httpServletResponse, String fileName,
                                                     InputStream inputStream){
        Workbook workbook=null;
        try{
            if (fileName==null){
                return null;
            }
            if(fileName.endsWith(".xls")){
                workbook=new HSSFWorkbook(inputStream);
            }else if (fileName.endsWith(".xlsx")){
                workbook=new XSSFWorkbook(inputStream);
            }else {
                throw new IOException("不支持该文档类型");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Sheet sheet=workbook.getSheetAt(0);
        int rowStart=Math.min(2,sheet.getFirstRowNum());
        int rowEnd=Math.max(200,sheet.getLastRowNum());
        for (int rowNum=rowStart;rowNum<rowEnd;rowNum++){
            CellReference cellReference=new CellReference(rowNum,0);

        }
        return null;
    }
}
