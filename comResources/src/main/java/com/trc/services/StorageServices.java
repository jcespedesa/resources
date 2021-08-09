package com.trc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServices 
{
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${cloudStorage.aws.credentials.access-key}")
	private String accessKey;
	
	@Value("${cloudStorage.aws.credentials.secret-key}")
	private String secretKey;
	
	
	@Autowired
	private AmazonS3 s3client;
	
	private static final String SUFFIX="/";
	
	
	public String uploadFile(MultipartFile file)
	{
		File fileObj=convertMultiPartFileToFile(file);
		
		String fileName=System.currentTimeMillis()+"-"+file.getOriginalFilename();
		
		s3client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		fileObj.delete();
		
		return "File uploaded : "+ fileName;

	}
	
	
	public byte[] downloadFile(String fileName) 
	{
        
		S3Object s3Object=s3client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream=s3Object.getObjectContent();
        try {
            byte[] content=IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public String deleteFile(String fileName) 
	{
        s3client.deleteObject(bucketName, fileName);
        return fileName + " file was removed ...";
    }
	
	
	private File convertMultiPartFileToFile(MultipartFile file)
	{

		File convertedFile=new File(file.getOriginalFilename());
		
		try (FileOutputStream fos = new FileOutputStream(convertedFile))
		{
			fos.write(file.getBytes());
		}catch(IOException e)
		{
			System.out.println("Error converting multi part file to file...");
		}
		return convertedFile;
		
	}
	
	
    //Get all files from S3 bucket
    
    public List<String> listFiles() 
    {

        ListObjectsRequest listObjectsRequest=new ListObjectsRequest()
             .withBucketName(bucketName);

        List<String> keys=new ArrayList<>();

        ObjectListing objects=s3client.listObjects(listObjectsRequest);

        while (true) 
        {
            List<S3ObjectSummary> objectSummaries=objects.getObjectSummaries();
            
            if (objectSummaries.size() < 1) 
            {
                break;
            }

            for(S3ObjectSummary item : objectSummaries) 
            {
                if(!item.getKey().endsWith("/"))
                    keys.add(item.getKey());
            }

            objects=s3client.listNextBatchOfObjects(objects);
        }

        return keys;
    }
	
	
    //Create a folder in the S3 bucket
    
    public void createFolder(String bucketName, String folderName) 
    {
    	    	
    	//AWSCredentials credentials=new BasicAWSCredentials(accessKey,secretKey);
                    	
    	
    	// create a client connection based on credentials
        // AmazonS3 s3client=new s3Client(credentials);
    	
        // create meta-data for your folder and set content-length to 0
        ObjectMetadata metadata=new ObjectMetadata();
        
        metadata.setContentLength(0);
        
        // create empty content
        InputStream emptyContent=new ByteArrayInputStream(new byte[0]);
        // create a PutObjectRequest passing the folder name suffixed by /
        
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + SUFFIX, emptyContent, metadata);
        
        // send request to S3 to create folder
        s3client.putObject(putObjectRequest);
    }
    
    
    
     // This method first deletes all the files in given folder and than the folder itself
     
    public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) 
    {
        
    	List<S3ObjectSummary> fileList=client.listObjects(bucketName, folderName).getObjectSummaries();
    	
        for (S3ObjectSummary file : fileList) 
        {
            client.deleteObject(bucketName, file.getKey());
        }
        
        client.deleteObject(bucketName, folderName);
    }
    
    
    
    
}
