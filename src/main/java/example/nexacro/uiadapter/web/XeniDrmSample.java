package example.nexacro.uiadapter.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.http.HttpServletResponse;

import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.data.VariableList;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;
import com.nexacro.java.xapi.tx.PlatformType;
import com.nexacro.java.xeni.extend.XeniExcelDataStorageBase;
import com.nexacro.java.xeni.util.CommUtil;


public class XeniDrmSample implements XeniExcelDataStorageBase {
	public XeniDrmSample() {
		System.err.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	}
	
	///////////////////////////////////////////
	// DRM을 위한 확장 구현에서는 수정없이 사용한다. 
	public InputStream loadTargetStream(String filepath) throws Exception {
		File file = new File(filepath);

		return new FileInputStream(file);
	}

	///////////////////////////////////////////
	// DRM을 위한 확장 구현을 위해 수정한다.
	public String saveImportStream(VariableList varlist, InputStream in,
			String filepath) throws Exception {

		///////////////////////////////////////////
		// 1. 전달받은 엑셀파일 Stream을 저장하기 위한 디렉토리 및 임시 디렉토리 생성한다. 
		// 
		int nIdx = filepath.lastIndexOf("/");
		String sPath = filepath.substring(0, nIdx);
		String fileName = filepath.substring(nIdx + 1);
		String srcFile = sPath + "/__temp_" + fileName;

		File file = new File(sPath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 2. nexacro에서 전달받은 Excel Stream을 임시파일로 저장한다. 
		// 이 상태는 DRM이 적용된 파일일 것이다. 
		//
		OutputStream out = new FileOutputStream(srcFile);

		byte[] buf = new byte[1024];
		int length = 0;
		while((length = in.read(buf)) > 0) {
			out.write(buf, 0, length);
		}

		out.flush();
		out.close();
		in.close();
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 3. 여기서부터 DRM 솔류션에 따라서 다른 로직을 구현하면 됨. 
		//
		// nexacro으로 부터 받은 DRM이 적용된 Excel 파일의 DRM 해제한다.
		// 아래 함수는 샘플로 작성한 것이고 사용하는 DRM 솔류션에서 제공하는 DRM 해제 로직을 추가하면 된다. 
		/*/
		boolean isSuccess = DrmUtil.extractDRM(srcFile, filepath);
		/*/
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 4. 사용된 임시 파일을 삭제한다. 
		//
		File delFile = new File(srcFile);
		if(delFile.exists()) {
			file.delete();
		}
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 5. 이후 Excel 파일을 Platform데이타로 변환하는 기능은 XENI 내부에서 진행하므로 구현할 필요가 없다. 
		//
		///////////////////////////////////////////

		return null;
	}

	public int saveExportStream(VariableList varlist, DataSet dscmd,
			ByteArrayOutputStream out, String filepath, String fileurl,
			HttpServletResponse response) throws Exception {

		///////////////////////////////////////////
		// 0. Grid 데이타의 Excel 파일 생성은 XENI 내부에서 진행하므로 구현할 필요가 없다. 
		//  ByteArrayOutputStream out로 전달된다. 
		///////////////////////////////////////////

		
		///////////////////////////////////////////
		// 1. 엑셀파일 Stream을 저장하기 위한 디렉토리 및 임시 디렉토리 생성한다. 
		//    임시파일을 저장할 디렉토리는 수정 가능하다. 
		// 
		int nIdx = filepath.lastIndexOf("/");
		String sPath = filepath.substring(0, nIdx);
		String fileName = filepath.substring(nIdx + 1);
		String srcFile = sPath + "/__temp_" + fileName;

		File file = new File(sPath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 2. 엑셀파일 Stream을 임시 엑셀파일에 저장한다. 
		// 
		FileOutputStream fout = new FileOutputStream(srcFile);
		fout.write(out.toByteArray());

		fout.close();
		out.close();
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 3. 여기서부터 DRM 솔류션에 따라서 다른 로직을 구현하면 됨. 
		//    DRM 미적용된 Excel파일을 적용 처리
		//    DRM 솔류션에서 제공하는 DRM 적용 로직을 추가한다. 
		//    아래는 샘플로 작성한 코드로 DRM 솔류션별로 다를 수 있다.
		//////////////////////////////
		// String id = varlist.getString("id");
		// String name = varlist.getString("name");
		// String code = varlist.getString("code");
		// String dept = varlist.getString("dept");
		// boolean isSuccess = DrmUtil.packagingDRM(srcFile, filepath, id, name, code, dept);
		///////////////////////////////////////////

		///////////////////////////////////////////
		// 4. 사용된 임시 파일을 삭제한다.
		//
		File delFile = new File(srcFile);
		if(delFile.exists()) {
			file.delete();
		}
		///////////////////////////////////////////

		
		///////////////////////////////////////////
		// 5. 생성된 엑셀파일(DRM이 적용된)의 정보(URL) 및 변환 결과를 넥사크로플랫폼으로 전달한다. 
		// 이 구성은 다른 로직이 필요없이 그대로 사용하면 된다. 
		//
		DataSet dsRes = CommUtil.getDatasetExportResponse(dscmd);

		PlatformData resData = new PlatformData();
		VariableList varList = resData.getVariableList();

		varList.add("ErrorCode", 0);
		varList.add("ErrorMsg", "SUCCESS");

		dsRes.set(0, "url", fileurl);
		resData.addDataSet(dsRes);

		HttpPlatformResponse platformRes = new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_SSV, "UTF-8");
		platformRes.setData(resData);
		platformRes.sendData();
		///////////////////////////////////////////

		return 0;
	}

	///////////////////////////////////////////
	// DRM 적용을 위해서는 구현이 필요없다. 
	public DataSet saveExportStream(VariableList varlist, DataSet dscmd,
			ByteArrayOutputStream out, String filepath, String fileurl)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
