package springboot.service;

import org.springframework.web.multipart.MultipartFile;
import springboot.mybatis.po.TStudent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

public interface ImportExcelService {
    public List<TStudent> importExcelforStudentLogin(MultipartFile file, HttpServletRequest httpServletRequest,
                                                     HttpServletResponse httpServletResponse, String fileName,
                                                     InputStream inputStream);
}
