package com.guarented.ecommerce.commonUtils;

import org.bson.Document;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnect {
	
	public MongoCollection getMongoDBColl(String mongo_uri,String DBName,String CollectionName){
		
	MongoClientURI URI = new MongoClientURI(mongo_uri);
	 MongoClient mongoClient = new MongoClient(URI);
	//MongoClient mongoClient = new MongoClient("192.168.2.95", 27017);
	
	 MongoDatabase db = mongoClient.getDatabase(DBName); 
    
   	 MongoCollection<Document> coll= db.getCollection(CollectionName);
	return coll;
	}
	
	
	public JSONObject getDocumentJsonByUniqueKey(String mongo_uri,String DBName,String CollectionName,String uniqueKey,String keyValue){
		MongoCollection<Document> coll =  getMongoDBColl(mongo_uri, DBName, CollectionName);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(uniqueKey, keyValue);
		FindIterable<Document> cursor = coll.find(whereQuery);
		
		   String doc= cursor.first().toJson();
		   
		   System.out.println("doc : "+doc);
		   
		   JSONObject obj=new JSONObject(doc);
		return obj;
		   
	}
	
	public String getFlatAttribute(String mongo_uri,String DBName,String CollectionName,String uniqueKey,String keyValue,String atrributeName){
		JSONObject obj=getDocumentJsonByUniqueKey(mongo_uri,DBName,CollectionName,uniqueKey,keyValue);
		   String attributeVal=obj.getString(atrributeName);
		   System.out.println("attributeVal : "+attributeVal);
		return attributeVal;
	}
	
	public String getCustomAttribute(String mongo_uri,String DBName,String CollectionName,String uniqueKey,String keyValue,String atrributeName){
		JSONObject obj=getDocumentJsonByUniqueKey(mongo_uri,DBName,CollectionName,uniqueKey,keyValue);
		JSONObject obj1=obj.getJSONObject("customAttributes");		
		String attributeVal=obj1.getString(atrributeName);
		System.out.println("customAttributeVal : "+attributeVal);
		return attributeVal;
	}
	 
	
	
}
