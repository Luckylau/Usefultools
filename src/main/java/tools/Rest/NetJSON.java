package tools.Rest;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 针对Neutron封装JSON工具类
 * @author Jasonlau
 * @Email laujunbupt0913@163.com
 *
 */
public class NetJSON {
	private static final Logger LOGGER = LoggerFactory.getLogger(NetJSON.class);
	private static Gson gson=null;
	
	static{
		GsonBuilder builder=new GsonBuilder();
		gson=builder.excludeFieldsWithoutExposeAnnotation().create();//没有@Expose注释的属性将不会被序列化	
	}
	/**
	 * 普通将object转化为json
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
        return gson.toJson(object);
    }
	/**
	 * 普通将json转化为object(集合泛型)
	 * @param json
	 * @param typeOfT
	 * @return
	 */
    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
    /**
     * 普通将json转化为object,(对象类型)
     * @param json
     * @param classOfT
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
    
    /**
     * 特定neutron将object转化为json
     * @param object
     * @param rootLabel
     * @return
     */
	public static String toJson(Object object,String rootLabel){
		return "{\""+rootLabel+"\":"+gson.toJson(object)+"}";
	}
	


	/**
	 * 特定neutron将json转化为object(对象类型)
	 * @param json
	 * @param rootLabel
	 * @param classOfT
	 * @return
	 */
	
	public static <T> T fromJson(String json,String rootLabel,Class<T> classOfT ){
		if (json == null) {
            LOGGER.warn("json is null !!");
            return null;
        }
		
		return gson.fromJson(JSONObject.fromObject(json).getString(rootLabel), classOfT);
		
	}
	
	/**
	 * 特定neutron将json转化为object(集合泛型)
	 * @param json
	 * @param rootLabel
	 * @param typeOfT
	 * @return
	 */
	public static <T> T fromJson(String json,String rootLabel,Type typeOfT ){
		if (json == null) {
            LOGGER.warn("json is null !!");
            return null;
        }
		
		return gson.fromJson(JSONObject.fromObject(json).getString(rootLabel), typeOfT);
		
	}
	
	

}
