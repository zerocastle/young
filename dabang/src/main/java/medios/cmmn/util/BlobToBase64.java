package medios.cmmn.util;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Base64Utils;

public class BlobToBase64 {

	public String convert(Blob blob) throws Exception {
		byte[] blobAsBytes = null;	
		blobAsBytes = blob.getBytes(1,(int) blob.length());
		blob.free(); 
		Base64Utils.encodeToString(blobAsBytes);
		return "data:"+"bmp"+";base64,"+Base64Utils.encodeToString(blobAsBytes);
	}
	public String convert(byte[] blobAsBytes) throws Exception {

		Base64Utils.encodeToString(blobAsBytes);
		return "data:"+"bmp"+";base64,"+Base64Utils.encodeToString(blobAsBytes);
	}
	
	public String convert(Blob blob,String fileType)  throws Exception {
		byte[] blobAsBytes = null;
		blobAsBytes = blob.getBytes(1,(int) blob.length());
		blob.free();
		return "data:"+fileType+";base64,"+Base64Utils.encodeToString(blobAsBytes);
	}

	public String convert(byte[] blobAsBytes,String fileType)  throws Exception {
		
		return "data:"+fileType+";base64,"+Base64Utils.encodeToString(blobAsBytes);
	}	
	
	public List<String> convertList(List<Blob> list)  throws Exception {
		List<String> relist = new ArrayList<String>();
		for(Blob blob : list){
			relist.add(this.convert(blob));
		}
		return relist;
	}

	public List<String> convertBList(List<byte[] > list)  throws Exception {
		List<String> relist = new ArrayList<String>();
		for(byte[]  abytes : list){
			relist.add(this.convert(abytes));
		}
		return relist;
	}
	
	public List<String> convertList(List<Blob> list,String fileType)  throws Exception {
		List<String> relist = new ArrayList<String>();
		for(Blob blob : list){
			relist.add(this.convert(blob,fileType));
		}
		return relist;
	}

	public List<String> convertBList(List<byte[] > list,String fileType)  throws Exception {
		List<String> relist = new ArrayList<String>();
		for(byte[]  abytes : list){
			relist.add(this.convert(abytes,fileType));
		}
		return relist;
	}	
}
