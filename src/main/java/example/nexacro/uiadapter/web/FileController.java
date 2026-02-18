package example.nexacro.uiadapter.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nexacro.uiadapter.jakarta.core.NexacroException;
import com.nexacro.uiadapter.jakarta.core.annotation.ParamDataSet;
import com.nexacro.uiadapter.jakarta.core.data.NexacroFileResult;
import com.nexacro.uiadapter.jakarta.core.data.NexacroMultiFileResult;
import com.nexacro.uiadapter.jakarta.core.data.NexacroResult;
import com.nexacro.uiadapter.jakarta.core.util.CharsetUtil;
import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataTypes;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.datatype.PlatformDataType;
import com.nexacro.java.xapi.tx.DataDeserializer;
import com.nexacro.java.xapi.tx.DataSerializerFactory;
import com.nexacro.java.xapi.tx.PlatformException;
import com.nexacro.java.xapi.tx.PlatformType;

/**
 * <pre>
 * @title   
 * @desc    아래의 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        Controller Sample Class - N
 * @package com.nexacro.sample.web
 * <pre>
 * @author  TOBESOFT
 * @since   2017. 11. 7.
 * @version 1.0
 * @see
 * =================== 변경 내역 ==================
 * 날짜			변경자		내용
 * ------------------------------------------------
 * 2017. 11. 7.		TOBESOFT	최초작성
 */
@Controller
public class FileController {
	private Logger logger = LoggerFactory.getLogger(FileController.class);
	
	private static final String SP = File.separator;
    private static final String PATH = "WEB-INF"+SP+"upload";
	
	@Autowired
    private WebApplicationContext appContext;

	/**
	 * 
	 * <pre>
	 * @desc 업로드 파일
	 * @param  
	 * @return NexacroResult
	 * @throws 
	 * </pre>
	 */
    @RequestMapping(value = "/advancedUploadFiles.do" )
    public NexacroResult uploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if(!(request instanceof MultipartHttpServletRequest)) {
            if(logger.isDebugEnabled()) {
            	logger.debug("Request is not a MultipartHttpServletRequest");
            }
            return new NexacroResult();
        }
        
        logger.debug("-------------------- nexacro platform uploadFiles ---------------------------");
    	String characterEncoding = request.getCharacterEncoding();
        if(characterEncoding == null) {
            characterEncoding = PlatformType.DEFAULT_CHAR_SET;
        }
        String charsetOfRequest = CharsetUtil.getCharsetOfRequest(request, characterEncoding);
        String queryString      = request.getQueryString();
        Map<String, String> queryMap = getQueryMap(queryString, charsetOfRequest);
        String filefolder = queryMap.get("filefolder");

        DataSet resultDs = createDataSet4UploadResult();
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        uploadParameters(multipartRequest);
        uploadMultipartFiles(multipartRequest, resultDs, filefolder);
        
        NexacroResult nexacroResult = new NexacroResult();
        nexacroResult.addDataSet(resultDs);

        return nexacroResult;
    }

    /**
     * 
     * <pre>
     * @desc 다운로드 파일
     * @param  
     * @return NexacroFileResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "/advancedDownloadFile.do")
    public NexacroFileResult downloadFile(HttpServletRequest request) throws Exception {
        
    	logger.debug("-------------------- nexacro platform downloadFile ---------------------------");
        String characterEncoding = request.getCharacterEncoding();
        if(characterEncoding == null) {
            characterEncoding = PlatformType.DEFAULT_CHAR_SET;
        }
        String charsetOfRequest = CharsetUtil.getCharsetOfRequest(request, characterEncoding);
        String queryString      = request.getQueryString();
        Map<String, String> queryMap = getQueryMap(queryString, charsetOfRequest);
        
        String filefolder = queryMap.get("filefolder");
        
        String fileName = queryMap.get("file");
        if(fileName == null) {
            throw new NexacroException("No input fileName specified.");
        }
        
        // was의 uri encoding을 사용안함. (서버의 설정을 변경하여야 함. URIEncoding="UTF-8")
        // already decode..
        // tomcat의 기본 uriencoding 형식 + web.xml의 charsetfilter utf8 (runtime version 은  uriencoding 되지  않고 있음)
        fileName = URLDecoder.decode(fileName, charsetOfRequest);
        fileName = removedPathTraversal(fileName);
        
        String filePath ;
        if(filefolder == null) {
        	filePath     = getFilePath();
        } else {
        	filePath     = getFilePath() + SP + filefolder;
        }
        String realFileName = filePath + SP + fileName;
        
        logger.debug("     FILE PATH :" + filePath);
        logger.debug("     FILE NAME :" + fileName);
        
        File file = new File(realFileName);
        
        NexacroFileResult result = new NexacroFileResult(file);
        return result;
    }

    /**
     * 
     * <pre>
     * @desc 다운로드 파일
     * @param  
     * @return NexacroFileResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "/multiDownloadFiles.do")
    public NexacroFileResult multiDownloadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	logger.debug("-------------------- nexacro multiDownloadFiles ---------------------------");
        String characterEncoding = request.getCharacterEncoding();
        if(characterEncoding == null) {
            characterEncoding = PlatformType.DEFAULT_CHAR_SET;
        }
        String charsetOfRequest = CharsetUtil.getCharsetOfRequest(request, characterEncoding);
        String queryString      = request.getQueryString();
        Map<String, String> queryMap = getQueryMap(queryString, charsetOfRequest);
        
        String filePath ;
        
        String filefolder = queryMap.get("filefolder");
        if(filefolder==null) {
        	filePath = getFilePath();
        	filefolder = "download";
        } else {
        	filePath = getFilePath() + SP + filefolder;
        }
        
        String filenamelist = request.getParameter("filenamelist");
        if(filenamelist == null) {
            throw new NexacroException("No input fileName specified.");
        }
        
        // was의 uri encoding을 사용안함. (서버의 설정을 변경하여야 함. URIEncoding="UTF-8")
        // already decode..
        // tomcat의 기본 uriencoding 형식 + web.xml의 charsetfilter utf8 (runtime version 은  uriencoding 되지  않고 있음)
        filenamelist = URLDecoder.decode(filenamelist, charsetOfRequest);
        filenamelist = removedPathTraversal(filenamelist);
        
        NexacroMultiFileResult result = new NexacroMultiFileResult(filefolder, filePath, filenamelist);
        return result;
    }

    /**
     * 
     * <pre>
     * @desc 다운로드 파일
     * @param  
     * @return NexacroFileResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "/advancedDownloadFiles.do")
    public void downloadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	logger.debug("-------------------- nexacro platform downloadFile ---------------------------");
        String characterEncoding = request.getCharacterEncoding();
        if(characterEncoding == null) {
            characterEncoding = PlatformType.DEFAULT_CHAR_SET;
        }
        String charsetOfRequest = CharsetUtil.getCharsetOfRequest(request, characterEncoding);
        String queryString      = request.getQueryString();
        Map<String, String> queryMap = getQueryMap(queryString, charsetOfRequest);
        
        String filefolder = queryMap.get("filefolder");
        String filenamelist = request.getParameter("filenamelist");
        if(filenamelist == null) {
            throw new NexacroException("No input fileName specified.");
        }
        
        // was의 uri encoding을 사용안함. (서버의 설정을 변경하여야 함. URIEncoding="UTF-8")
        // already decode..
        // tomcat의 기본 uriencoding 형식 + web.xml의 charsetfilter utf8 (runtime version 은  uriencoding 되지  않고 있음)
        filenamelist = URLDecoder.decode(filenamelist, charsetOfRequest);
        filenamelist = removedPathTraversal(filenamelist);
        
        String[] fileNameArr = filenamelist.split(",");
        String filePath    ;
        if(filefolder==null) {
        	filePath = getFilePath();
        	filefolder = "download";
        } else {
        	filePath = getFilePath() + SP + filefolder;
        }
        String realFileName;

		ServletOutputStream out_stream	= null;
		BufferedInputStream in_stream	= null;
		ZipOutputStream		zout_stream	= null;
	
		try
		{
			response.flushBuffer();
			response.setContentType("application/octet;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename = \"" + filefolder + ".zip" + "\"");
			
			//out = pageContext.pushBody();
	
			out_stream = response.getOutputStream();
			zout_stream = new ZipOutputStream(out_stream);		
			zout_stream.setLevel(8);
			
			for( String fileName : fileNameArr){
		        // was의 uri encoding을 사용안함. (서버의 설정을 변경하여야 함. URIEncoding="UTF-8")
		        // already decode..
		        // tomcat의 기본 uriencoding 형식 + web.xml의 charsetfilter utf8 (runtime version 은  uriencoding 되지  않고 있음)
		        fileName = URLDecoder.decode(fileName, charsetOfRequest);
		        fileName = removedPathTraversal(fileName);
		        
		        realFileName = filePath + SP + fileName;
				
				File fis = new File(realFileName);
				in_stream = new BufferedInputStream(new FileInputStream(fis));
				
				ZipEntry zentry = new ZipEntry(fileName);
				zentry.setTime(fis.lastModified());
				zout_stream.putNextEntry(zentry);
				
				byte[] buffer = new byte[1024];
				int n = 0;
				while ((n = in_stream.read(buffer, 0, 1024)) != -1)
				{
					zout_stream.write(buffer, 0, n);
				}
				
				zout_stream.closeEntry();	  
		  	}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (zout_stream != null)
			{
				try
				{
					zout_stream.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			if (out_stream != null)
			{
				try
				{
					out_stream.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			if (in_stream != null)
			{
				try
				{
					in_stream.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
    }

    /**
     * 
     * <pre>
     * @desc 다운로드 파일 목록
     * @param  
     * @return NexacroFileResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "/advancedDownloadList.do")
    public NexacroResult downloadList(HttpServletRequest request, @ParamDataSet(name = "ds_search", required = false) Map<String,String> searchInfo) throws Exception {
        
    	logger.debug("-------------------- nexacro platform downloadFile List ---------------------------");
        String characterEncoding = request.getCharacterEncoding();
        if(characterEncoding == null) {
            characterEncoding = PlatformType.DEFAULT_CHAR_SET;
        }
        String charsetOfRequest = CharsetUtil.getCharsetOfRequest(request, characterEncoding);
        String queryString      = request.getQueryString();
        Map<String, String> queryMap = getQueryMap(queryString, charsetOfRequest);
        
        String filefolder = queryMap.get("filefolder");
        String filePath    ;
        if(filefolder==null) {
        	filePath = getFilePath();
        	filefolder = "download";
        } else {
        	filePath = getFilePath() + SP + filefolder;
        }

    	String url = request.getRequestURL() + PATH + filefolder;
    	logger.debug("-------url------ :"+url);
    	
    	//PlatformData resData = new PlatformData();
    	//VariableList resVarList = resData.getVariableList();
    	
    	DataSet dsList = getDownloadList(filePath);

        NexacroResult result = new NexacroResult();
        result.addDataSet(dsList);
        
        return result;
    }
    
    /**
     * <pre>
     * @desc 삭제 파일 
     * @param  
     * @return NexacroResult
     * @throws 
     * </pre>
     */
    @RequestMapping(value = "/advancedDeleteFiles.do")
    public NexacroResult deleteFiles(@ParamDataSet(name="input") DataSet dsInput) throws Exception {
    	logger.debug("-------------------- nexacro platform deleteFiles ---------------------------");
    	
        if(dsInput == null) {
            throw new NexacroException("No input DataSet('input') specified.");
        }
        
        NexacroResult result = new NexacroResult();
        String filePath     = getFilePath();
        String errorMessage = "";
        int rowCount = dsInput.getRowCount();
        
        logger.debug("    filePath :" + filePath);
        logger.debug("    rowCount :" + rowCount);
        
        for (int i = 0; i < rowCount; i++) {

            String fileRealNm = dsInput.getString(i, "file");
            if(fileRealNm == null || fileRealNm.length() == 0) {
                continue;
            }
            
            String fileName = removedPathTraversal(fileRealNm);

            logger.debug("    fileName :" + fileName);
            
            if (errorMessage.length() > 0) {
                errorMessage += "\r\n";
            }

            try {
                File f = new File(filePath + File.separator, fileName);
                if (f.exists()) {
                    if (f.delete()) {
                        errorMessage += "'" + fileName + "' Delete Success";
                    } else {
                        errorMessage += "'" + fileName + "' Delete failed";
                    }
                } else {
                    errorMessage += "'" + fileName + "' File not available";
                }
            } catch (Exception e) {
                errorMessage += "'" + fileName + "' " + e;
                NexacroException nexacroException = new NexacroException();
                nexacroException.setErrorCode(-1);
                nexacroException.setErrorMsg(errorMessage);
            }
            
            logger.debug("    errorMessage :" + errorMessage);
        }
        
        result.addVariable("ErrorCode", 0);
        result.addVariable("ErrorMsg" , errorMessage);
        
        return result;
    }
    
    
    private void uploadParameters(MultipartHttpServletRequest multipartRequest) throws NexacroException {
        // parameter and multipart parameter
        Enumeration<String> parameterNames = multipartRequest.getParameterNames();

        while(parameterNames.hasMoreElements()) {
            
            String parameterName = parameterNames.nextElement();
            if(parameterName == null || parameterName.length() == 0) {
                continue;
            }
            
            String value = multipartRequest.getParameter(parameterName);
            
            if("inputDatasets".equals(parameterName)) {
                
                PlatformData platformData = new PlatformData();
                StringReader reader = new StringReader(value);
                DataDeserializer deserializer = DataSerializerFactory.getDeserializer(PlatformType.CONTENT_TYPE_SSV);
                try {
                    platformData = deserializer.readData(reader, null, PlatformType.DEFAULT_CHAR_SET);
                } catch (PlatformException e) {
                    logger.debug("xml data not deserialize. data={}", value);
                    // throw new NexacroException("get platformData failed. e="
                    // + e);
                    continue;
                }
                
                //필요할 경우 사용.
                DataSet dsInput = platformData.getDataSet("ds_input");
                logger.debug("dsInput data=\n{}", dsInput.saveXml());
                
                //TODO
                //이후 처리는 각 업무로직에 맞게 사용할 것.
                continue;
                
            } else {
                String filePath = getFilePath();
                String fileName = removedPathTraversal(value);
                File f = new File(filePath + SP, fileName);
                if (f.exists()) {
                    f.delete();
                }
            }
            
        }
        
    }
    
    
    private String getFilePath() {
        ServletContext sc = appContext.getServletContext();
        String realPath = sc.getRealPath("/");
        String uploadPath = realPath + PATH;
        logger.debug("--------------| getFilePath = "+uploadPath+" |-----------------------");
        return uploadPath;
    }
    
    private void uploadMultipartFiles(MultipartHttpServletRequest multipartRequest, DataSet resultDs, String sFilefolder) throws IOException {
        
        // files..
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String filePath;
        if(sFilefolder == null) {
        	filePath = getFilePath();
        } else {
        	filePath = getFilePath() + SP + sFilefolder;
        }
        
        Set<String> keySet = fileMap.keySet();
        for(String name: keySet) {
            
            MultipartFile multipartFile = fileMap.get(name);

            String originalFilename = multipartFile.getOriginalFilename();

            // IE에서 파일업로드 시 DataSet 파라매터의 Content-Type이 설정되지 않아 여기로 옴. 무시.
            if(originalFilename == null || originalFilename.length() == 0) {
                continue;
            }
            
            File destination = new File(filePath);
            
            if( destination.exists() == false) {
            	boolean mkdirs = destination.mkdirs();
            	destination.setWritable(true);
            	
            	logger.debug("-------------- create directory ----------------------" + mkdirs);
            }
            
            File targetFile = new File(filePath+SP+originalFilename);

            InputStream inputStream = multipartFile.getInputStream();
            FileCopyUtils.copy(inputStream, new FileOutputStream(targetFile));
            
            int row = resultDs.newRow();
            resultDs.set(row, "fileid", originalFilename);
            resultDs.set(row, "filename", originalFilename);
            resultDs.set(row, "filesize", targetFile.length());
            
            if(logger.isDebugEnabled()) {
                logger.debug("uploaded file write success. file="+originalFilename);
            }
        }
    }
    
    private DataSet getDownloadList(String sFilePth) throws IOException {
        
    	DataSet dsList = new DataSet("dsList");
    	dsList.addColumn("FILE_PATH", DataTypes.STRING, 255);
    	dsList.addColumn("FILE_NAME", DataTypes.STRING, 255);
    	dsList.addColumn("FILE_URL", DataTypes.STRING, 255);
    	dsList.addColumn("FILE_SIZE", DataTypes.STRING, 255);

        // files..
        File 	dir		= new File(sFilePth);

        String[] fileNames = dir.list();
        for(String filename: fileNames) {
            
            File targetFile = new File(sFilePth+SP+filename);
            
            int row = dsList.newRow();

            dsList.set(row, "FILE_PATH", targetFile.getPath());
            dsList.set(row, "FILE_NAME", filename);
            dsList.set(row, "FILE_URL" , filename);
			long megabyte = targetFile.length() / 1024;
            dsList.set(row, "FILE_SIZE", megabyte+ " kb");

            if(logger.isDebugEnabled()) {
                logger.debug("file info in download Folder. file="+targetFile.getName());
            }
        }
        return dsList;
    }
       
    private String removedPathTraversal(String fileName) {
        if(fileName == null) {
            return null;
        }
        
        fileName = fileName.replace("/", "");
        fileName = fileName.replace("\\", "");
//        fileName = fileName.replace(".", "");
        fileName = fileName.replace("&", "");
        return fileName;
    }
    
    private DataSet createDataSet4UploadResult() {
        
        DataSet ds = new DataSet("ds_output");
        ds.addColumn("fileid", PlatformDataType.STRING);
        ds.addColumn("fileimg", PlatformDataType.STRING);
        ds.addColumn("filename", PlatformDataType.STRING);
        ds.addColumn("filesize", PlatformDataType.INT);
        ds.addColumn("tranfilesize", PlatformDataType.INT);
        ds.addColumn("prog", PlatformDataType.INT);
        
        return ds;
    }
    
    private Map<String, String> getQueryMap(String queryString, String charset) throws UnsupportedEncodingException {

        String decodeQs = URLDecoder.decode(queryString, charset);
        int questionIndex = decodeQs.indexOf("?");
        String parameterString = decodeQs.substring(questionIndex + 1);
        String[] parameterPairs = parameterString.split("&");

        String parameterName;
        String parameterValue;
        Map<String, String> map = new HashMap<String, String>();
        for(int i=0; i<parameterPairs.length; i++) {
            String[] keyAndValue = parameterPairs[i].split("=");
            parameterName = null;
            parameterValue = null;
            if(keyAndValue.length>0) {
                parameterName = keyAndValue[0];
                parameterValue = keyAndValue[1];
                map.put(parameterName, parameterValue);
            }
        }
        
        return map;
    }
}